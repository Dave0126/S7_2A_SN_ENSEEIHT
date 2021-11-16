import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class DeImpl extends UnicastRemoteObject implements DeItf {

    private Random alea;
    private int calls;

    public DeImpl() throws RemoteException {
        alea = new Random();
        calls = 0;
    }

    public int getRandom() throws RemoteException {
        calls += 1;
        return 1 + alea.nextInt(6);
    }

    public int getCalls() throws RemoteException {
        return calls;
    }
}
