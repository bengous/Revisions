import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileManagerService extends Remote {

    void uploadFile(File file, ClientItf client) throws RemoteException;

    void downloadFile(int id, ClientItf clientItf) throws RemoteException;

}