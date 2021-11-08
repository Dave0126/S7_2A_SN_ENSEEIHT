// Time-stamp: <08 Apr 2008 11:35 queinnec@enseeiht.fr>

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Synchro.Assert;

/** Lecteurs/rédacteurs
 * stratégie d'ordonnancement: priorité aux rédacteurs,
 * implantation: avec un moniteur. */
public class LectRed_PrioLecteur implements LectRed
{
    // Protection des variables partagées
    private Lock moniteur;
    private Condition access; //申请读

    //variable d'etat
    private int nbLecteurs; //正在读的读者人数
    private int nbEcriteurs; //正在写的人数

    private int nbLectAtt;

    
    public LectRed_PrioLecteur()
    {
        this.moniteur = new ReentrantLock();
        this.access = moniteur.newCondition ();

        this.nbLecteurs = 0;
        this.nbEcriteurs = 0;

        this.nbLectAtt = 0;
    }
/** 开始读 */
    public void demanderLecture() throws InterruptedException 
    {
        moniteur.lock();
        try {
            while ( nbEcriteurs != 0) {
                nbLectAtt++;
                try {
                    access.await();  //if someone is writing, then block(access.await()) himself. 如果（有人在写），则 阻塞自己
                }
                catch(InterruptedException e) {}
                nbLectAtt--;
            }
            nbLecteurs++; //离开了wait（）则表明已被唤醒，可以去读了
        }
        finally {
           moniteur.unlock(); 
        }
    }
/** 结束读 */
    public void terminerLecture() throws InterruptedException
    {
        moniteur.lock();
        // nbLecteurs > 0 && !ecriture
        try {
            nbLecteurs--;
            // nbLecteurs >= 0
            if (nbLecteurs ==0 && nbLectAtt == 0){
                //if nobaby reading and waiting for reading -> access for reading
                access.signalAll();
            }
        }
        finally{
            moniteur.unlock();
        }
    }

    public void demanderEcriture() throws InterruptedException
    {
        moniteur.lock();
        try {
            while ( nbLecteurs + nbEcriteurs + nbLectAtt > 0){
                try { access.await();} // 如果（有人正在写 或 正在读 或 等待读），则 阻塞。这里体现读优先，读者可通过LectAtt插队
                catch(InterruptedException e) {}
            }
            nbEcriteurs++;
        }
        // nbLecteur=0 && ecriture
        finally{
            moniteur.unlock();
        }
    }

    public void terminerEcriture() throws InterruptedException
    {
        moniteur.lock();
        // nbLecteur=0 && ecriture
        try{
            nbEcriteurs--;       
            access.signalAll();
        }
        finally{
            moniteur.unlock();
        }
    }

    public String nomStrategie()
    {
        return "Stratégie: Priorité Lecteur.";
    }
}
