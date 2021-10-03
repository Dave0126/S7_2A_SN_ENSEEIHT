// v0.1, 25/09/20 (PM)
package TP02;

class TamponBorné {
/* Pour simplifier la mise en œuvre du test, on se limite ici à un tampon d'entiers 
 * 为了简化测试的实现，我们将自己限制在一个整数缓冲区 
 * */
    private int taille; 			// nombre de cases du tampon 缓冲池数量 
    private int nbOccupé = 0; // 缓冲区中存在的项目数（最初为空） nombre d'items présents dans le tampon (vide initialement)
    private int queue = 0; 		// 队列尾部（循环缓冲区），插入 queue de file (tampon circulaire), insertions
    private int tête = 0; 		// 行首（循环缓冲区）tête de file (tampon circulaire), extractions

    private int trace = 0;// 仅用于方便测试 utile uniquement pour faciliter le test :
	// 我们会认为我们正在存放连续的跟踪值......												
    // on va considérer qu'on dépose les valeurs successives de trace...

    private int[] tampon;

    public TamponBorné(int t) {
        taille = t;
        tampon = new int [taille];
    }

    public synchronized void déposer() { // pour le test, les valeurs déposées sont prédéfinies

/** A compléter : attendre que le dépôt soit possible. 
  * Pour une attente bloquante, il suffit que le thread courant appelle wait() (ou this.wait())
  *  (c.-à-d. qu'on peut utiliser le verrou associé au TamponBorné sur lequel on appelle déposer() )
  *
  * A compléter : 等到可以déposer。
  * 对于阻塞等待，当前线程只需要调用wait()(或this.wait())
  *（即我们可以使用与我们称之为déposer() 的 BufferBorné 相关联的锁） 
  **/
    	System.out.println("Productor: (before) nbOccupé = " + nbOccupé);
    	while (nbOccupé >= taille) {
    		try {
    			System.out.println("Productor: The buffer is full, please wait...");
    			System.out.println();
    			this.wait();
    		} catch (InterruptedException e) {
    		e.printStackTrace();
    		}
    	}
    	
    	// dépôt
    	//System.out.println("Trace = " + trace);
		tampon[queue] = trace++; 			// tampon[queue] = i;
		System.out.println("Productor:Tampon[" + queue + "] = " + tampon[queue]);
		// queue = {0,1,2,3,4,5,6,7,8,9} {0,1,2,3,4,5,6,7,8,9} ...
		queue = (queue + 1) % taille; 
		//System.out.println("Queue(after) = " + queue);
		nbOccupé++;
		
        // affichage pour le test uniquement
     /* String msg="P : "+(trace-1);
        if (nbOccupé == taille) msg=msg+ " (PLEIN)";
        System.out.println(msg);	*/

/** A compléter : réveiller (notify()) un éventuel thread en attente d'accès au TamponBorné
  * A compléter : 唤醒: notify()一个可能等待访问TamponBorné的线程
  **/
		this.notify();
		
        System.out.println("Productor: nbOccupé (after) = " + nbOccupé);
        System.out.println();
    } //déposer()

    
    public synchronized int retirer() {	// Item remove()
        int i;							// Item i
        
        System.out.println("Customer: nbOccupé (before) = " + nbOccupé);

/** A compléter : attendre que le retrait soit possible
  * A compléter : 等到可以retirer 
  **/
        while (nbOccupé <= 0) {
    		try {
    			System.out.println("Customer: The buffer is empty, please wait...");
    			System.out.println();
    			this.wait();
    		} catch (InterruptedException e) {
    		e.printStackTrace();
    		}
    	}
        // retrait
        i = tampon[tête];
        System.out.println("Customer: Tampon[" + tête + "] = " + i);
        tête = (tête + 1) % taille;
        nbOccupé--;

        // affichage pour le test uniquement
     /* String msg="C : "+i;
        if (nbOccupé == 0) msg=msg+ " (VIDE)";
        System.out.println(msg);	*/

/** A compléter : réveiller un éventuel thread en attente d'accès au TamponBorné
  * A compléter : 唤醒: notify()一个可能等待访问TamponBorné的线程
  **/
        this.notify();
        System.out.println("Costumer: nbOccupé (after) = " + nbOccupé);
        System.out.println();
        return i;
    } // retirer
} // TamponBorné

//--------------------------------------inutile de modifier ce qui suit ------------------

class Producteur implements Runnable {
    private TamponBorné tampon;
    public Producteur(TamponBorné t) {
        tampon = t;
    }
    public void run() {
        try {
            Thread.sleep(10) ; // pour le test : initialement, les consommateurs trouveront tous un tampon vide
            for (int i = 0; i < 25; i++) {
                // possible de trouver des producteurs bloqués à la fin, selon le nb de consommateurs
                tampon.déposer();
                Thread.sleep(2*i); //producteurs ralentissent un peu
            }
        } catch (InterruptedException e) {
            System.out.println("interrompu");
        }
    }
}

class Consommateur implements Runnable {
    private TamponBorné tampon;
    private int identité;
    public Consommateur(TamponBorné t) {
        tampon = t;
    }
    public void run() {
        int res;
        for (int i = 0; i < 25; i++) {
            // possible de trouver des consommateurs bloqués à la fin, selon le nb de producteurs
            res = tampon.retirer();
            try {
                Thread.sleep(10*i); //consommateurs ralentissent davantage
            } catch (InterruptedException e) {
                System.out.println("interrompu");
            }
        }
    }
}

public class ProdConso {
    public static void main(String[] args) {
        int nbProd = 5;
        int nbConso = 10;
        int tailleTampon = 10;
        //aucun blindage : on suppose que les valeurs de paramètres fournies sont raisonnables
        //无屏蔽：假设提供的参数值是合理的 
        if (args.length != 3) {
            System.out.println("java ProdConso <nbProd> <nbConso> <nbCases>");
            System.out.println("-> choix par défaut : "+nbProd+"/"+nbConso+"/"+tailleTampon);
        } else {
            nbProd = Integer.parseInt (args[0]);
            nbConso = Integer.parseInt (args[1]);
            tailleTampon = Integer.parseInt (args[2]);
        }
        System.out.println("nbProd (arg1) : " + nbProd + " /nbConso (arg2) : " + nbConso
                           + " /nbCases) (arg3) : "+ tailleTampon);
        TamponBorné t = new TamponBorné(tailleTampon);
        for (int i = 0; i < nbProd; i++) {
            new Thread(new Producteur(t)).start() ;
        }
        for (int i = 0; i < nbConso; i++) {
            new Thread(new Consommateur(t)).start() ;
        }
        //ajouter éventuellement un thread pour gérer l'arrêt et une prise de cliché finale
    }
}