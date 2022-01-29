package linda.search.parallelinteruption;

import linda.*;

public class Interupt {

    public static void main(String[] args) {
        // Verification arguments
    	if (args.length != 1) {
            System.err.println("Usage: java Interupt.java <lindaURL>");
            return;
    	}

        System.out.println("Arret d'un Searcher");

        Linda linda = new linda.server.LindaClient(args[0]);
        linda.write(new linda.Tuple(Code.Interupt));
    }
}
