package linda.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import linda.Callback;
import linda.Tuple;

public class RemoteCallbackImpl extends UnicastRemoteObject implements RemoteCallback {

    private Callback innerCallback;

    // Callback distant permettant d'encapsuler un autre Callback
    public RemoteCallbackImpl(Callback innerCallback) throws RemoteException {
        this.innerCallback = innerCallback;
    }

    @Override
    public void call(Tuple t) throws RemoteException {
        innerCallback.call(t);
    }
}
