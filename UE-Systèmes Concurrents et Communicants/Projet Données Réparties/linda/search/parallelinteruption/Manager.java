package linda.search.parallelinteruption;

import java.util.UUID;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

import linda.*;

public class Manager extends Thread {

    private Linda linda;

    private UUID reqUUID;
    private String pathname;
    private String search;
    private int bestvalue = Integer.MAX_VALUE; // lower is better
    private String bestresult;
    private long depart, fin;

    public Manager(Linda linda_connection, String pathname, String search) {
        this.pathname = pathname;
        this.search = search;
        this.linda = linda_connection;
    }

    public static void main(String[] args) {
        // Verification arguments
    	if (args.length != 3) {
            System.err.println("Usage: java Manager.java <word> <file> <lindaURL>");
            return;
    	}

        System.out.println("Manager connecting to " + args[2]);
        Linda linda_connection = new linda.server.LindaClient(args[2]);
        Manager manager = new Manager(linda_connection, args[1], args[0]);
        System.out.println("Manager connected ! ");
        manager.start();
    }

    private void addSearch(String search) {
        this.search = search;
        System.out.println("Manager adding request " + this.reqUUID + " for " + search);
        linda.eventRegister(Linda.eventMode.TAKE, Linda.eventTiming.IMMEDIATE, new Tuple(Code.Result, this.reqUUID, String.class, Integer.class), new CbGetResult());
        long l = (long)(new File(pathname)).length();
        linda.write(new Tuple(Code.Request, this.reqUUID, this.search, l));
        depart = System.nanoTime();
    }

    private void loadData(String pathname) {
        this.reqUUID = UUID.randomUUID();
        try (Stream<String> stream = Files.lines(Paths.get(pathname))) {
            stream.limit(10000).forEach(s -> linda.write(new Tuple(Code.Value, s.trim(), this.reqUUID)));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForEndSearch() {
        linda.take(new Tuple(Code.Searcher, "done", this.reqUUID));
        linda.take(new Tuple(Code.Request, this.reqUUID, String.class, Long.class)); // remove query
        System.out.println("Manager received result for request " + reqUUID + " : " + bestresult);
        fin = System.nanoTime();
        System.out.println("Duree execution (ms): " + (fin - depart)/1000000L);
    }

    private class CbGetResult implements linda.Callback {
        public void call(Tuple t) {  // [ Result, ?UUID, ?String, ?Integer ]
            String s = (String) t.get(2);
            Integer v = (Integer) t.get(3);
            if (v < bestvalue) {
                bestvalue = v;
                bestresult = s;
                System.out.println("New best (" + bestvalue + "): \"" + bestresult + "\"" + " for word " + search);
            }
            linda.eventRegister(Linda.eventMode.TAKE, Linda.eventTiming.IMMEDIATE, new Tuple(Code.Result, reqUUID, String.class, Integer.class), this);
        }
    }
    
    public void run() {
        this.loadData(pathname);
        this.addSearch(search);
        this.waitForEndSearch();
    }
}
