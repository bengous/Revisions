import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class MasterServer implements FileManagerService, RingItf {

    private final int MASTER = 0;
    private RingItf firstSlaveReference;

    private int file_id;
    private HashMap<Integer, String> fileSystem;

    public MasterServer() {
        file_id = 0;
        fileSystem = new HashMap<>();
        // lookup the first slave
        try {
            Registry registry = LocateRegistry.getRegistry();
            firstSlaveReference = (RingItf) registry.lookup("slave_1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // what the client sees

    @Override
    public void uploadFile(File file, ClientItf client) throws RemoteException {
        file_id++;
        firstSlaveReference.store(file_id, file, false, client);
    }

    @Override
    public void downloadFile(int id, ClientItf clientItf) throws RemoteException {
        // the first slave may have it or ask the next slave and so on
        if (fileSystem.containsKey(id)) {
            firstSlaveReference.getFile(id, null, clientItf);
        } else {
            // GFY
        }
    }

    // ring --

    @Override
    public void store(int id, File file, boolean stored, ClientItf client) throws RemoteException {
        if (stored) {
            fileSystem.put(file_id, file.getName());
        }
        // notify client
    }

    @Override
    public void getFile(int id, File file, ClientItf clientItf) throws RemoteException {
        clientItf.receive(file);
    }
}