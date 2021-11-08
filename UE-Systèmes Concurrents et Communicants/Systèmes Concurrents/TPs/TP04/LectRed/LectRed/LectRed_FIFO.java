// Time-stamp: <08 Apr 2008 11:35 queinnec@enseeiht.fr>

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.text.html.HTMLDocument.BlockElement;

import Synchro.Assert;

/** Lecteurs/rédacteurs
 * stratégie d'ordonnancement: priorité aux rédacteurs,
 * 策略：写者优先
 * implantation: avec un moniteur. */
public class LectRed_FIFO implements LectRed
{
    // Protection des variables partagées
    private Lock moniteur;

    private BlockingQueue<Thread> queue;
    //variable d'etat
    private int nbLecteurs; //正在读的读者人数
    private int nbEcriteurs;

    private int nbLectAtt;
    private int nbEcriAtt;

    private Condition accesLecture; //可以读
    private Condition accesEcriture; //可以写


    public LectRed_FIFO()
    {
        this.moniteur = new ReentrantLock(true);

        this.accesLecture = moniteur.newCondition ();
        this.accesEcriture = moniteur.newCondition ();

        this.nbLecteurs = 0;
        this.nbEcriteurs = 0;

        this.nbLectAtt = 0;
        this.nbEcriAtt = 0;
    }

/** 开始读 */
public void demanderLecture() throws InterruptedException 
{
    moniteur.lock();
    try {
        while ( nbEcriteurs + nbEcriAtt > 0){
            nbLectAtt++;
            try {
                accesLecture.await(); //如果（有人在写 || 等待读的人数 > 0），则 阻塞自己
            }
            catch (InterruptedException e) {}
            nbLectAtt--;
        }
        nbLecteurs++;
    }
    finally {
        moniteur.unlock();
    }
    
}
/** 结束读 */
    public void terminerLecture() throws InterruptedException
    {
        moniteur.lock();
        try {
            nbLecteurs--;
            if (nbLecteurs==0 && nbEcriAtt>0){
                accesEcriture.signalAll(); // 如果（没人读了），则 唤醒等待的第一个写者
            }
        }
        finally {
            moniteur.unlock();
        }
        
    }

    /** 开始写 */
    public void demanderEcriture() throws InterruptedException
    {
        moniteur.lock();
        try {
            while (nbLecteurs + nbEcriteurs > 0){
                nbEcriAtt++;
                try {
                    accesEcriture.await(); // 如果(有人在读 或 有人在写)，则 阻塞自己
                }
                catch (InterruptedException e) {}
                nbEcriAtt--;
            }
        nbEcriteurs++; // 修改写状态
        }
        finally{
            moniteur.unlock();
        }
    }

    /** 结束写 */
    public void terminerEcriture() throws InterruptedException
    {
        moniteur.lock();
        try {
            nbEcriteurs--; // 修改写状态
            if (nbEcriAtt > 0) {
                accesEcriture.signalAll(); // 如果有等待写，则唤醒（写者优先的体现）
            } else if (nbLectAtt > 0) {
                accesLecture.signalAll(); // 否则唤醒第一个等待的读者
            }
        }
        finally {
            moniteur.unlock();
        }
    }

    public String nomStrategie()
    {
        return "Stratégie: Priorité FIFO.";
    }
}
