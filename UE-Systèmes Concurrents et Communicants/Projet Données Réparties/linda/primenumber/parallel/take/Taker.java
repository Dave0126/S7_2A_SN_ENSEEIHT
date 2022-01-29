package linda.primenumber.parallel.take;

import linda.*;

public class Taker extends Thread {

    private Linda linda;
    private int start;
    private int end;
    private int number;

    public Taker(Linda linda,int start, int end, int number) {
        this.linda = linda;
        this.start = start;
        this.end = end;
        this.number = number;
    }

    public void run() {
        // Take des tuples multiple de number entre <start> et <end>
        for (int k = start; k < end; k++) {
            if (k%number == 0){
                linda.tryTake(new Tuple(k));
            }
        }
        //System.out.println("Taker finish (from "+start +" to " + (end-1) +" ) avec number = " + number);
    }
}

