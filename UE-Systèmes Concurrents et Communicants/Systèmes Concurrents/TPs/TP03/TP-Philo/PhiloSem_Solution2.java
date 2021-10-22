// Time-stamp: <08 déc 2009 08:30 queinnec@enseeiht.fr>

import java.util.concurrent.Semaphore;


public class PhiloSem_Solution2 implements StrategiePhilo {

private Semaphore mutex;
private Semaphore[] semPhilo;

    /****************************************************************/

    public PhiloSem_Solution2 (int nbPhilosophes) {
        semPhilo = new Semaphore[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            semPhilo[i] = new Semaphore(1);
        }
    
    }

    /** Le philosophe no demande les fourchettes.
     *  no: Identifiant du philosophe. *
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException
    {
        /** 
        mutex.acquire();
        etat [i] <- demander;
        if peutManger(i) {
            etat[nbPhilosophes] <- manger;
            mutex.release();
        }
        else {
            mutex.release();
            semPhilo[no].acquire();
        }
        */   
    }

    public void peutManger (int no){
        /**
        etat[Main.FourchetteGauche(no)] != manger;
        etat[Main.FourchetteDroite(no)] != manger;
        etat[no] <- demander;
        */
    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no)
    {
        /**
        mutex.acquire();
        if (peutManger(Main.FourchetteGauche(no))){
            etat[no] <- manger;
            semPhilo[Main.FourchetteGauche(no)].release();
        }
        if (peutManger(Main.FourchetteDroite(no))){
            etat[no] <- manger;
            semPhilo[Main.FourchetteDroite(no)].release();
        }
        mutex.release();
         */
        
    }

    /** Nom de cette stratégie (pour la fenêtre d'affichage). */
    public String nom() {
        return "Implantation Sémaphores, stratégie 'Prendre les 2 fourchettes en meme temps (Solution 2)'";
    }

}

