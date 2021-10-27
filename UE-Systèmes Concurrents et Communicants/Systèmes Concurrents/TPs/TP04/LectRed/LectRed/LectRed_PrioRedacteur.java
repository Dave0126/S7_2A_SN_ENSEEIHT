// Time-stamp: <08 Apr 2008 11:35 queinnec@enseeiht.fr>

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Synchro.Assert;

/** Lecteurs/rédacteurs
 * stratégie d'ordonnancement: priorité aux rédacteurs,
 * implantation: avec un moniteur. */
public class LectRed_PrioRedacteur implements LectRed
{
    // Protection des variables partagées
    private Lock moniteur;

    //variable d'etat
    private int nbLecteurs;
    private boolean ecriture;

    private int nbLectAtt;

    private Condition accesLecture;

    private Condition accesEcriture;


    public LectRed_PrioRedacteur()
    {
        this.moniteur = new ReentrantLock();
        this.accesLecture = moniteur.newCondition ();
        this.accesEcriture = moniteur.newCondition ();
        this.nbLecteurs = 0;
        this.ecriture = false;
        this.nbLectAtt = 0;
    }

    public void demanderLecture() throws InterruptedException
    {
        moniteur.lock();
        if ( ecriture || nbLectAtt > 0){
            accesLecture.await();
        }
        //!ecriture
        nbLecteurs ++;
        //!ecriture && nbLecteurs > 0
        accesLecture.signal();
        moniteur.unlock();
    }

    public void terminerLecture() throws InterruptedException
    {
        moniteur.lock();
        // nbLecteurs > 0 && !ecriture
        nbLecteurs --;
        // nbLecteurs >= 0
        if (nbLecteurs == 0){
            //!ecriture && nbLecteurs = 0
            accesEcriture.signal();
        }
        moniteur.unlock();
    }

    public void demanderEcriture() throws InterruptedException
    {
        moniteur.lock();
        if (!(nbLecteurs==0 && !ecriture)){
            accesEcriture.await();
        }
        // nbLecteur=0 && !ecriture
        ecriture = true;
        // nbLecteur=0 && ecriture
        moniteur.unlock();
    }

    public void terminerEcriture() throws InterruptedException
    {
        moniteur.lock();
        // nbLecteur=0 && ecriture
        ecriture = false;
        // nbLecteur=0 && !ecriture
        if (nbLectAtt > 0) {
            accesLecture.signal();
        }
        else  {
            accesEcriture.signal();
        }
        moniteur.unlock();

    }

    public String nomStrategie()
    {
        return "Stratégie: Priorité Rédacteurs.";
    }
}
