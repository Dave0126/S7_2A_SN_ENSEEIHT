package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    public String echo (String msg) throws RemoteException;
}
