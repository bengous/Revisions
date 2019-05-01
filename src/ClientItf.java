import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientItf extends Remote {
    void receive(File file) throws RemoteException;
}