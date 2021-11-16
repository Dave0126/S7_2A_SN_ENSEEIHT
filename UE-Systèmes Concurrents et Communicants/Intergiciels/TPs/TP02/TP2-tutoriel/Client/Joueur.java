import java.rmi.*;

public class Joueur {

    public static void main (String[] args) {

        try {
            DeItf deItf = (DeItf) Naming.lookup("//localhost:4000/michel");
            int nb1 = deItf.getRandom();
            int nb2 = deItf.getRandom();
            int nb3 = deItf.getRandom();
            int nbCalls = deItf.getCalls();
            System.out.println("Vous avez tiré les valeurs " + nb1 + ", " + nb2 + ", " + nb3 + ". Le nombre de lancers total est actuellement de " + nbCalls);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
