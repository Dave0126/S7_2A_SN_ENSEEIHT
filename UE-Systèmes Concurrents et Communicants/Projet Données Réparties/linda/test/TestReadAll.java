package linda.test;

import java.util.Collection;

import linda.*;

public class TestReadAll {

    public static void main(String[] a) {
        final Linda linda = new linda.shm.CentralizedLinda();
        //final Linda linda = new linda.server.LindaClient("//localhost:4000/MonServeur");
                
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Tuple t1 = new Tuple(4, 5);
                System.out.println("(0) write: " + t1);
                linda.write(t1);

                Tuple t2 = new Tuple("hello", 15);
                System.out.println("(0) write: " + t2);
                linda.write(t2);

                linda.debug("(0)");

                Tuple t3 = new Tuple(4, "foo");
                System.out.println("(0) write: " + t3);
                linda.write(t3);

                Tuple t4 = new Tuple(10, "nothing");
                System.out.println("(0) write: " + t4);
                linda.write(t4);

                Tuple t5 = new Tuple(6, "void");
                System.out.println("(0) write: " + t5);
                linda.write(t5);
                                
                linda.debug("(0)");
            }
        }.start();

        new Thread() {  
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("(1) ReadAll");
                Tuple motif = new Tuple(Integer.class, String.class);
                Collection<Tuple> res = linda.readAll(motif);
                System.out.println("(1) Resultat:" + res);
                linda.debug("(1)");
            }
        }.start();
    }
}
