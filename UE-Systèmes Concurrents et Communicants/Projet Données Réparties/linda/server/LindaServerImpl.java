package linda.server;

import linda.Tuple;
import linda.Linda.eventMode;
import linda.Linda.eventTiming;
import linda.shm.CentralizedLinda;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

public class LindaServerImpl extends UnicastRemoteObject implements LindaServer {
    
	private CentralizedLinda localLinda;

    protected LindaServerImpl() throws RemoteException {
        localLinda = new CentralizedLinda();
    }

    public static void main(String[] args) {
        LindaServer server;

        // Creating rmiregistry
		try {
            System.out.println("Creating rmi registry...");
			LocateRegistry.createRegistry(4000);
            String url = "//localhost:4000/LindaServer";
            System.out.println("Starting server at " + url + "...");
		    server = new LindaServerImpl();
		    Naming.bind(url, server);
            System.out.println("Server started");
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
		}
    }

    @Override
    public void write(Tuple t) throws RemoteException {
        localLinda.write(t);
    }

    @Override
    public Tuple take(Tuple template) throws RemoteException {
        return localLinda.take(template);
    }

    @Override
    public Tuple read(Tuple template) throws RemoteException {
        return localLinda.read(template);
    }

    @Override
    public Tuple tryTake(Tuple template) throws RemoteException {
        return localLinda.tryTake(template);
    }

    @Override
    public Tuple tryRead(Tuple template) throws RemoteException {
        return localLinda.tryRead(template);
    }

    @Override
    public Collection<Tuple> takeAll(Tuple template) throws RemoteException {
        return localLinda.takeAll(template);
    }

    @Override
    public Collection<Tuple> readAll(Tuple template) throws RemoteException {
        return localLinda.readAll(template);
    }

    @Override
    public void eventRegister(eventMode mode, eventTiming timing, Tuple template, RemoteCallback remoteCallback) throws RemoteException {
        localLinda.eventRegister(mode, timing, template, new ServerAsynchronousCallback(remoteCallback));
    }

    @Override
    public void debug(String prefix) throws RemoteException {
        localLinda.debug(prefix);
    }
}