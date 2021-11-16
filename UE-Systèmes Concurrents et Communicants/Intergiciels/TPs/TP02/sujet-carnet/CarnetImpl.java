import java.rmi.*;
import java.rmi.server.*;
import java.util.Hashtable;

public class CarnetImpl extends UnicastRemoteObject implements Carnet{
    protected CarnetImpl() throws RemoteException {
        //TODO Auto-generated constructor stub
    }

    private int numero;
    private Hashtable <String, RFiche> table = new Hashtable<String, RFiche>();
    private CarnetImpl autre;


    @Override
    public void Ajouter(SFiche sf) throws RemoteException {
        // TODO Auto-generated method stub
        String key = sf.getNom();
        RFiche value = new RFicheImpl(sf.getNom(),sf.getEmail());
        table.put(key, value);
        
        
    }

    @Override
    public RFiche Consulter(String nom, boolean forward) throws RemoteException {
        //  Auto-generated method stub
        RFiche rf = table.get(nom);
        if (rf == null && forward){
            if (this.autre == null){
                try {
                    //lookup pour recepteur l'autre carnet
                    Carnet carnet = (Carnet) Naming.lookup("//localhost:4000/Carnet", + 3 - numero);
                    this.autre = carnet;
                }
                
            }
            rf = this.autre.Consulter(nom, false);
        }
        return rf;
    }
    
}
