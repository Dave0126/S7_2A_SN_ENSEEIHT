package linda.primenumber.parallel.takewrite;

import linda.*;
import linda.primenumber.parallel.take.Taker;
import linda.primenumber.parallel.write.Writer;

// *****************************************
// VERSION OU L'ECRITURE DES ENTIERS < N ET
// LA SUPPRESSION DES MULTIPLES DANS LINDA  
// PAR LES WRITER/TAKER EST PARALLELISEE
// *****************************************

public class Main {

    public static void main(String args[]) {
        // Verification parametres
    	if (args.length != 2) {
            System.err.println("Usage: java Main.java n nbThread");
            return;
    	}

        int n = Integer.parseInt(args[0]);
        int nbThread = Integer.parseInt(args[1]);
        
        if (nbThread > n) {
            System.out.println("Using " + (n-1) + " thread");
            nbThread = n-1;
        } else if (nbThread > 10) {
            System.out.println("Using max number of thread (=10)");
            nbThread = 10;
        }

        //calcule du temps d'execution
        long depart, fin;
        depart = System.nanoTime();
        
        Linda linda = new linda.shm.CentralizedLinda();
        int step = ((n-1)/nbThread)+1;
        int end;
        Thread[] writers = new Thread[nbThread];
        Thread[] takers = new Thread[nbThread];
        
        // Ecriture des tuples de 2 à n
        int idthread = 0;
        for (int i = 2; i <= n; i = i + step){
            end = Integer.min(i + step,n+1);
            //creation d'un thread
            Writer writer = new Writer(linda,i,end);
            writers[idthread] = writer;
            (new Thread(writer)).start();
            idthread+=1;
        }

        // Attente des writers
        for (int i = 0; i < idthread; i++){
            try {
                writers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Parcours crible
        for (int i = 2; i <= n; i++){
            if (linda.tryRead(new Tuple(i)) != null) {
                // Elimination des multiples de i
                for (int k = i+1; k <= n; k = k + step) {
                    idthread = 0;
                    end = Integer.min(k + step,n+1);
                    Taker taker = new Taker(linda,k,end,i);
                    takers[idthread] = taker;
                    (new Thread(taker)).start();
                    idthread += 1;
                }
            }
        }

        // Attente des takers avant d'afficher le résultat
        for (int i = 0; i < idthread; i++){
            try {
                takers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        fin = System.nanoTime();
        System.out.println("Duree execution (ms): " + (fin - depart)/1000000L);

        linda.debug("Résultat");
    }
}
