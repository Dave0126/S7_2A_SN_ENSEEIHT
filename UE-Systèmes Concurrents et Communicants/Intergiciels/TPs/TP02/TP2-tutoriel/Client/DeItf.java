import java.rmi.*;

public interface DeItf extends Remote {
    public int getRandom() throws RemoteException;
    public int getCalls() throws RemoteException;
}
