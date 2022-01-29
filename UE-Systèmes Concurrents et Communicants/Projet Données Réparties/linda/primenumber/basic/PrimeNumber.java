package linda.primenumber.basic;

import linda.*;

public class PrimeNumber {

    public static void main(String args[]) {
    	if (args.length != 1) {
            System.err.println("Usage: java PrimeNumber.java max");
            return;
    	}
        int n = Integer.parseInt(args[0]);

        //calcule du temps d'execution
        long depart, fin;
        depart = System.nanoTime();

        Linda linda = new linda.shm.CentralizedLinda();

        // Ecriture des tuples de 2 à n
        for (int i = 2;i<=n;i++){
            linda.write(new Tuple(i));
        }

        // Parcours crible
        for (int i = 2; i <= n; i++){
            if (linda.tryRead(new Tuple(i)) != null) {
                // Elimination des multiples de i
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
