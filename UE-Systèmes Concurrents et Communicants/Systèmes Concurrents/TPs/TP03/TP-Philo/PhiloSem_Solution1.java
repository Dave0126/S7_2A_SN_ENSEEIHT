// Time-stamp: <08 déc 2009 08:30 queinnec@enseeiht.fr>

import java.util.concurrent.Semaphore;


public class PhiloSem_Solution1 implements StrategiePhilo {

private Semaphore[] fourchette;

    /****************************************************************/

    public PhiloSem_Solution1 (int nbPhilosophes) {
        fourchette = new Semaphore[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            fourchette[i] = new Semaphore(1);
        }
    
    }

    /** Le philosophe no demande les fourchettes.
     *  no: Identifiant du philosophe. *
     *  Précondition : il n'en possède aucune.
     *  Postcondition : quand cette méthode retourne, il possède les deux fourchettes adjacentes à son assiette. */
    public void demanderFourchettes (int no) throws InterruptedException
    {
        fourchette[Main.FourchetteGauche(no)].acquire();
        //Thread.sleep(1000);
        fourchette[Main.FourchetteDroite(no)].acquire();
        
        
        // if left fork and right fork aare free, we can demande
        // 其中所有哲学家先拿右叉，然后是左叉。
        /**突出显示此版本中可能出现的死锁情况。一个简单的方法是在叉夹之间引入足够的时间。实现（使用信号量）此基本版本的各种改编以避免死锁。演示每次适应如何避免死胡同 */
    }

    /** Le philosophe no rend les fourchettes.
     *  Précondition : il possède les deux fourchettes adjacentes à son assiette.
     *  Postcondition : il n'en possède aucune. Les fourchettes peuvent être libres ou réattribuées à un autre philosophe. */
    public void libererFourchettes (int no)
    {
        // Premierement, le philosophe rend le FourchetteDroit, apres il rend le Gauche
        fourchette[Main.FourchetteGauche(no)].release();
        fourchette[Main.FourchetteDroite(no)].release();
        
    }

    /** Nom de cette stratégie (pour la fenêtre d'affichage). */
    public String nom() {
        return "Implantation Sémaphores, stratégie '1 Sémophore par Fourchette (Solution 1)'";
    }

}

