import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private static final int PORT = 8888;
    private static MasterServer service;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(PORT);
            service = (MasterServer) registry.lookup("master");

            ClientImpl me = new ClientImpl();

            // do stuff
            service.uploadFile(new File("myNewFile"), me);
            service.downloadFile(12345, me);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}