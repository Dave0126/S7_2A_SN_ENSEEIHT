package linda.primenumber.parallel.write;

import linda.*;

// *****************************************
// VERSION OU L'ECRITURE DES ENTIERS < N DANS  
// LINDA PAR LES WRITER EST PARALLELISEE
// *****************************************

public class Main {

    public static void main(String args[]) {
    	if (args.length != 2) {
            System.err.println("Usage: java Main.java n nbThread");
            return;
    	}

        int n = Integer.parseInt(args[0]);
        int nbThread = Integer.parseInt(args[1]);

        //calcule du temps d'execution
        long depart, fin;
        depart = System.nanoTime();
        
        if (nbThread > n) {
            System.out.println("Using " + (n-1)+ " thread");
            nbThread = n-1;
        } else if (nbThread > 10) {
            System.out.println("Using max number of thread (=10)");
            nbThread = 10;
        }

        Linda linda = new linda.shm.CentralizedLinda();
        int step = ((n-1)/nbThread)+1;
        int end;
        Thread[] writers = new Thread[nbThread];
        
        //creation parallel des tuples de <2> à <n>
        int idthread = 0;
        for (int i = 2; i <= n; i = i + step){
            end = Integer.min(i + step,n+1);
            //creation d'un thread
            Writer writer = new Writer(linda,i,end);
            writers[idthread] = writer;
            (new Thread(writer)).start();
            idthread+=1;
        }

        for (int i = 0; i < idthread; i++){
            try {
                writers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 2; i <= n; i++){
            if (linda.tryRead(new Tuple(i)) != null) {
                for (int k = i+1; k <= n; k++) {
                    if (k%i == 0){
                        linda.tryTake(new Tuple(k));
                    }
                }
            }
        }

        fin = System.nanoTime();
        System.out.println("Duree execution (ms): " + (fin - depart)/1000000L);

        linda.debug("Résultat");
    }
}
