package linda.primenumber.parallel.write;

import linda.*;

public class Writer extends Thread {

    private Linda linda;
    private int start;
    private int end;

    public Writer(Linda linda,int start, int end) {
        this.linda = linda;
        this.start = start;
        this.end = end;
    }

    public void run() {
        //creation des tuples de <start> à <end>
        for (int i = start;i<end;i++){
            linda.write(new Tuple(i));
        }
        //System.out.println("Writer finish (from "+start +" to " + (end-1) +" )");
    }
}

