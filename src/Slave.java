import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Slave extends UnicastRemoteObject implements RingItf {

    private static final int PORT = 8888;
    private static int ID = 0;

    private RingItf nextSlave;

    private int myID;

    public Slave() throws RemoteException {
        ID++; // each new slave ++
        myID = ID;
        try {
            Registry registry = LocateRegistry.createRegistry(PORT);
            // export
            registry.bind("slave_" + ID, this); // export slave
            // lookup next
            nextSlave = (RingItf) registry.lookup("slave_" + myID++);
        } catch (RemoteException | AlreadyBoundException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(int id, File file, boolean stored, ClientItf client) throws RemoteException {
        // check if enough space
        if (file.length() < new File("/").getFreeSpace()) {
            // we can store the file - do it!!
            nextSlave.store(id, file, true, client );
        } else {
            nextSlave.store(id, file, false, client ); // someone else will store it
        }
    }

    @Override
    public void getFile(int id, File file, ClientItf clientItf) throws RemoteException {
        if (file == null) {
            File lookedFile = new File("/" + id);
            if (file.exists()) {
                // if my storage has file the return it
                file = lookedFile;
            }
        }
        nextSlave.getFile(id, file, clientItf);
    }
}