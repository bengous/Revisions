import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RingItf extends Remote {

    void store(int id, File file, boolean stored, ClientItf client) throws RemoteException;

    void getFile(int id, File file, ClientItf clientItf) throws RemoteException;
}