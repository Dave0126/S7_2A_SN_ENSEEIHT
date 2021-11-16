import java.rmi.*;
import java.io.*;

public class SFicheImpl implements SFiche {
    private String Nom;
	private String Email;
    public SFicheImpl(String name, String email) throws RemoteException {
        this.Nom = name;
        this.Email = email;

    } 

    @Override
    public String getNom() {
        // TODO Auto-generated method stub
        return this.Nom;
    }
    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        return this.Email;
    }
    
}
