import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.Hashtable;

public class CarnetImpl extends UnicastRemoteObject implements Carnet{
    protected CarnetImpl() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    private int numero;
    private Hashtable <String, RFiche> table = new Hashtable<String, RFiche>();
    private CarnetImpl autre;


    @Override
    public void Ajouter(SFiche sf) throws RemoteException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public RFiche Consulter(String nom, boolean forward) throws RemoteException {
        // TODO Auto-generated method stub
        RFiche rf = table.get(nom);
        if (rf == null && forward){
            if (this.autre == null){
                //lookup pour recepteur l'autre carnet
            }
            rf = this.autre.Consulter(nom, false);
        }
        return rf;
    }
    
}
