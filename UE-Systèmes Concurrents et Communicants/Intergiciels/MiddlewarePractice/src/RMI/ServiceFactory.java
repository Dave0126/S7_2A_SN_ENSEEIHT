package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceFactory extends Remote {
    public Service getService(String serviceName) throws RemoteException;
}
