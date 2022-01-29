package linda.search.basic;

import linda.*;

public class Main {

    public static void main(String args[]) {
    	if (args.length != 2) {
            System.err.println("linda.search.basic.Main search file.");
            return;
    	}

        //calcule du temps d'execution
        long depart, fin;
        depart = System.nanoTime();
        
        Linda linda = new linda.shm.CentralizedLinda();
        Manager manager = new Manager(linda, args[1], args[0]);
        Searcher searcher = new Searcher(linda);
        (new Thread(manager)).start();
        (new Thread(searcher)).start();

        fin = System.nanoTime();
        System.out.println("Duree execution (ms): " + (fin - depart)/1000000L);
    }
}
