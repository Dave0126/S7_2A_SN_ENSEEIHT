// Time-stamp: <08 déc 2009 08:30 queinnec@enseeiht.fr>

import java.util.concurrent.Semaphore;


public class PhiloSem_Solution0 implements StrategiePhilo {

private Semaphore mutex;

    /****************************************************************/

    public PhiloSem_Solution0 (int nbPhilosophes) {
        mutex = new Semaphore(1);
    }

    /** Le philosophe no demande les fourchettes.
     *  no: Identifiant du philosophe. *
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException
    {
        mutex.acquire();
        
    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no)
    {
        mutex.release();
    }

    /** Nom de cette stratégie (pour la fenêtre d'affichage). */
    public String nom() {
        return "Implantation Sémaphores, stratégie '1 seul mange a la fois, exclusion mutuelle (Solution 0)'";
    }

}

