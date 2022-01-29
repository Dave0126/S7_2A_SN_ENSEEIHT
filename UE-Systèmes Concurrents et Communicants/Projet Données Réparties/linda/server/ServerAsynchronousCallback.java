package linda.server;

import java.rmi.RemoteException;

import linda.Callback;
import linda.Tuple;

public class ServerAsynchronousCallback implements Callback {
    private RemoteCallback rcb;

    public ServerAsynchronousCallback (RemoteCallback rcb) { 
        this.rcb = rcb;
    }
        
    public void call(final Tuple t) {
        new Thread() {
            public void run() {
                try {
                    rcb.call(t);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
