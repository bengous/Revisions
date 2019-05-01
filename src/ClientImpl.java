import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements ClientItf {

    ClientImpl() throws RemoteException {

    }

    @Override
    public void receive(File file) throws RemoteException {

    }
}
