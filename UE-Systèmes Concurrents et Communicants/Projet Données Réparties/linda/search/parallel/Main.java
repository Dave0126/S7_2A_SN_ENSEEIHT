package linda.search.parallel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import linda.*;

public class Main {
    public static String urlserver = "//localhost:4000/LindaServer";
    public static final int MAXNBSEARCHER = 10;
    public static final int MAXNBREQUEST = 10;
    public static final int MAXNBFILS = 4;

    public static void main(String args[]) {
        // Verification arguments
    	if (args.length != 2) {
            System.err.println("Usage: java Main.java <requestfile> <nbsearchers>");
            return;
    	}

        int nbsearcher = Integer.parseInt(args[1]);
        BufferedReader reader = null;
        String line = null;
        String[] request;
        Manager manager;
        Searcher searcher;
        Linda linda_connection = new linda.server.LindaClient(urlserver);

        if (nbsearcher > MAXNBSEARCHER) {
            nbsearcher = MAXNBSEARCHER;
        }
        
        // Creation des chercheurs principaux
        for (int i = 0; i < nbsearcher; i++){
            System.out.println("Starting new main Searcher...");
            //(new Thread(searcher)).start();
            searcher = new Searcher(linda_connection, true);
            searcher.start();
        }

        //Lecture premiere ligne fichier requests
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            System.out.println("Reading file " + args[0]);
            line = reader.readLine();
            System.out.println("Reading new line :" + line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Lecture du reste/creation des manager
        while (line != null) {
            request = line.split(" ");

            System.out.println("Starting new Manager...");
            manager = new Manager(linda_connection, request[1], request[0]);
            //(new Thread(manager)).start();
            manager.start();

            try {
                line = reader.readLine();
                System.out.println("Reading new line :" + line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
