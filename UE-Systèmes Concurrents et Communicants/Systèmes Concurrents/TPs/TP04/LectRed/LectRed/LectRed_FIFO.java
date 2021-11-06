// Time-stamp: <08 Apr 2008 11:35 queinnec@enseeiht.fr>

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Synchro.Assert;

/** Lecteurs/rédacteurs
 * stratégie d'ordonnancement: priorité aux rédacteurs,
 * implantation: avec un moniteur. */
public class LectRed_FIFO implements LectRed
{
    // Protection des variables partagées
    private Lock moniteur;

    //variable d'etat
    private int nbLecteurs;
    private boolean redacteur;

    private Condition acces;
    private Condition sas;


    public LectRed_FIFO()
    {
        this.moniteur = new ReentrantLock();
        this.acces = moniteur.newCondition ();
        this.sas = moniteur.newCondition ();
        this.nbLecteurs = 0;
        this.redacteur = false;
    }

    public void demanderLecture() throws InterruptedException
    {
        moniteur.lock();
        if ((!sas.equals(null))){
            acces.await();
        }
        if (redacteur){
            sas.await();
        }
        //!redacteur
        nbLecteurs ++;
        //!redacteur && nbLecteurs > 0
        acces.signal();
        moniteur.unlock();
    }

    public void terminerLecture() throws InterruptedException
    {
        moniteur.lock();
        // nbLecteurs > 0 && !redacteur
        nbLecteurs --;
        // nbLecteurs >= 0
        if (nbLecteurs == 0){
            //!redacteur && nbLecteurs = 0
            sas.signal();
        }
        moniteur.unlock();
    }

    public void demanderEcriture() throws InterruptedException
    {
        moniteur.lock();
        if(!sas.equals(null)){
            acces.await();
        }
        if (nbLecteurs>0 && redacteur){
            acces.await();
        }
        // nbLecteur=0 && !redacteur
        redacteur = true;
        // nbLecteur=0 && redacteur
        acces.signal();
        moniteur.unlock();
    }

    public void terminerEcriture() throws InterruptedException
    {
        moniteur.lock();
        // nbLecteur=0 && redacteur
        redacteur = false;
        // nbLecteur=0 && !redacteur
        sas.signal();
        moniteur.unlock();

    }

    public String nomStrategie()
    {
        return "Stratégie: FIFO.";
    }
}
