import java.rmi.registry.*;
import java.rmi.*;

public class Arbitre {

    public static void main (String[] args) {

        try {
            DeImpl de = new DeImpl();
            Registry registry = LocateRegistry.createRegistry(4000);
            Naming.rebind("//localhost:4000/michel", de);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
