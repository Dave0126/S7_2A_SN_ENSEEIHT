import java.rmi.*;
import java.rmi.server.*;

public class RFicheImpl extends UnicastRemoteObject implements RFiche{
	private String nom;
	private String email;
    public RFicheImpl(String name, String email) throws RemoteException {
        this.nom = name;
        this.email = email;

    }

    @Override
    public String getNom() throws RemoteException {
        // TODO Auto-generated method stub
        return this.nom;
    }

    @Override
    public String getEmail() throws RemoteException {
        // TODO Auto-generated method stub
        return this.email;
    }

}
