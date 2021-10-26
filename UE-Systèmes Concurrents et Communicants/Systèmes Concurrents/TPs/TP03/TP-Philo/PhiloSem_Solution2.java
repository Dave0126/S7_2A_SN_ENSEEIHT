// Time-stamp: <08 déc 2009 08:30 queinnec@enseeiht.fr>

import java.util.concurrent.Semaphore;
import java.util.Arrays;


public class PhiloSem_Solution2 implements StrategiePhilo {

private Semaphore mutex;
private Semaphore[] semPhilo;
private enum ETAT{
    MANGER,
    PENSER,
    DEMANDER
    } 
private ETAT[] etat;

    /****************************************************************/

    public PhiloSem_Solution2 (int nbPhilosophes) {
        semPhilo = new Semaphore[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            semPhilo[i] = new Semaphore(0);
        }
        mutex = new Semaphore(1);
        etat = new ETAT[nbPhilosophes];
    }

    /** Le philosophe no demande les fourchettes.
     *  no: Identifiant du philosophe. *
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException
    {
        
        mutex.acquire();
        etat [no] = ETAT.DEMANDER;
        if (peutManger(no)) {
            etat [no] = ETAT.MANGER;
            //Arrays.fill(etat,ETAT.MANGER);
            semPhilo[no].release();
        }
        mutex.release();
        semPhilo[no].acquire();
        //Thread.sleep(1000);
           
    }

    public boolean peutManger (int no){
        
        return 
        etat[Main.FourchetteGauche(no)] != ETAT.MANGER &&
        etat[Main.FourchetteDroite(no)] != ETAT.MANGER &&
        etat[no] == ETAT.DEMANDER;
        
    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no) throws InterruptedException
    {
        
        mutex.acquire();
        if (peutManger(Main.FourchetteGauche(no))){
            etat[no] = ETAT.MANGER;
            semPhilo[Main.FourchetteGauche(no)].release();
        }
        if (peutManger(Main.FourchetteDroite(no))){
            etat[no] = ETAT.MANGER;
            semPhilo[Main.FourchetteDroite(no)].release();
        }
        mutex.release();
        
        
    }

    /** Nom de cette stratégie (pour la fenêtre d'affichage). */
    public String nom() {
        return "Implantation Sémaphores, stratégie 'Prendre les 2 fourchettes en meme temps (Solution 2)'";
    }

}

