package linda.search.parallelinteruption;

import linda.*;
import linda.Linda.eventMode;
import linda.Linda.eventTiming;
import java.util.Arrays;
import java.util.UUID;

public class Searcher extends Thread {

    public final static int MAXNBBYTES = 1000000;
    
    private static Linda linda;
    private static boolean isStarter;
    private static Tuple requete;
    private static UUID currentreqUUID;
    private static String currentword;
    private static boolean interuptionSignal;

    public Searcher(Linda lindaArg, boolean isStarterArg) {
        linda = lindaArg;
        isStarter = isStarterArg;
        interuptionSignal = false;
    }

    public static void main(String[] args) {
        // Verification arguments
    	if (args.length != 1) {
            System.err.println("Usage: java Searcher.java <lindaURL>");
            return;
    	}
        System.out.println("Searcher connecting to " + args[0]);
        Linda linda_connection = new linda.server.LindaClient(args[0]);
        Searcher searcher = new Searcher(linda_connection, true);
        System.out.println("Searcher connected !");
        searcher.start();
    }

    public void run() {
        // Attente requete
        if (isStarter){
            //On s'abonne à un Tuple (Interupt) pour le searcher
            linda.eventRegister(eventMode.TAKE, eventTiming.IMMEDIATE, new Tuple(Code.Interupt), new InteruptCb()); // On s'abonne a un signal interuption

            System.out.println("Searcher waiting for request");
            linda.eventRegister(eventMode.TAKE, eventTiming.IMMEDIATE, new Tuple(Code.Request, UUID.class, String.class, Long.class), new SearcherCb());

            //linda.take(new Tuple("attente"));
            // Bloque tant que non interruption
        } else {
            // On s'abonne à un Tuple (Interupt,UUID) pour les fils
            linda.eventRegister(eventMode.READ, eventTiming.IMMEDIATE, new Tuple(Code.Interupt, currentreqUUID), new InteruptCb()); 
            search(requete);
        }
    }
    
    private static class SearcherCb implements Callback {
        public void call(Tuple requeteRecuperee) {
            requete = requeteRecuperee;
            UUID reqUUID = (UUID)requete.get(1);
            String word = (String)requete.get(2);
            long size = (long)requete.get(3);
            currentreqUUID = reqUUID;
            currentword = word;

            if(size == 0) {
                linda.write(new Tuple(Code.Request, reqUUID, word, Long.valueOf(0))); // rend la requete pour Manager
                return;
            }

            int nbChild = (int)(size / MAXNBBYTES);
            Thread[] allFils = new Thread[nbChild];
            
            System.out.println("Searcher got a request");

            // Creation des fils
            for(int i = 0 ; i < nbChild ; i++) {
                Searcher fils = new Searcher(linda, false);
                allFils[i] = fils;
                fils.start();
            }

            // Recherche pere
            search(requete);

            
            if (!interuptionSignal){
                // Attente des fils
                for(int i = 0 ; i < nbChild ; i++) {
                    try {
                        allFils[i].join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Signal fin de recherche
                System.out.println("Main Searcher has completed request");
                linda.write(new Tuple(Code.Request, reqUUID, word, Long.valueOf(0))); // rend la requete pour Manager
                linda.write(new Tuple(Code.Searcher, "done", reqUUID));

                // Re-abonnement
                linda.eventRegister(eventMode.READ, eventTiming.IMMEDIATE, new Tuple(Code.Request, UUID.class, String.class, Long.class), this);
            } else {
                System.out.println("Arret du searcher");
                return;
            }
        }
    }

    private static void search(Tuple treq){
        UUID reqUUID = (UUID)treq.get(1);
        String req = (String) treq.get(2);
        Tuple tv;

        if (isStarter) {
            System.out.println("Searcher looking for: " + req);
        } else {
            System.out.println("Child Searcher looking for: " + req);
        }

        while ((tv = linda.tryTake(new Tuple(Code.Value, String.class, reqUUID))) != null && !interuptionSignal) {
            String val = (String) tv.get(1);
            int dist = getLevenshteinDistance(req, val);
            if (dist < 10) { // arbitrary
                linda.write(new Tuple(Code.Result, reqUUID, val, dist));
            }
        }
    }

    private static class InteruptCb implements Callback {
        public void call(Tuple interuTuple) {
            if (isStarter && currentword!=null){
                linda.write(new Tuple(Code.Request, currentreqUUID, currentword, Long.valueOf(0))); // rend la requete pour qu'un autre searcher la finisse
                linda.write(new Tuple(Code.Interupt, currentreqUUID));
                System.out.println("Arreter de self (searcher)");
            }            
            
            interuptionSignal = true;
        }
    }
    /*****************************************************************/

    /* Levenshtein distance is rather slow */
    /* Copied from https://www.baeldung.com/java-levenshtein-distance */
    static int getLevenshteinDistance(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1] 
                                   + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
                                   dp[i - 1][j] + 1, 
                                   dp[i][j - 1] + 1);
                }
            }
        }
        return dp[x.length()][y.length()];
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

}

