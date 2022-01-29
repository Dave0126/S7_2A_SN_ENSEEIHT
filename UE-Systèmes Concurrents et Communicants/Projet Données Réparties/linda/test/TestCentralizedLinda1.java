package linda.test;

import linda.*;

public class TestCentralizedLinda1 {
    
    public static void main() {
        final Linda linda = new linda.shm.CentralizedLinda();

        Tuple t1 = new Tuple (0,0);
        Tuple t2 = new Tuple (1,true);
        Tuple t3 = new Tuple ("t3", "test");

        Tuple motif1 = new Tuple (Integer.class, Integer.class);
        Tuple motif2 = new Tuple (Integer.class, Boolean.class);
        Tuple motif3 = new Tuple (String.class, String.class);

        new Thread(){
            public void run() {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                linda.write(t1);
                linda.write(t2);
                linda.write(t3);

            }
        }.start();

        new Thread(){
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                assert(linda.read(motif1).matches(t1));
                assert(linda.read(motif2).matches(t2));
                assert(linda.read(motif3).matches(t3));
                assert(linda.read(motif1).matches(t3));
            }
        }.start();
    }

}
