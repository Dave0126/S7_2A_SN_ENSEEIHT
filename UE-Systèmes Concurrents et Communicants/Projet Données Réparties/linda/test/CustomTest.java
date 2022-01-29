package linda.test;

import java.util.HashMap;

import linda.*;

public class CustomTest {

    public static void main(String[] a) {

        HashMap<Tuple, Integer> cbs = new HashMap<>();

        Tuple t1 = new Tuple(4, 5);
        Tuple t11 = new Tuple(4, 5);
        //Tuple t2 = new Tuple("hello", 15);

        System.out.println(cbs.get(t1));
        cbs.put(t1, 1);
        System.out.println("Get t1:");
        System.out.println(cbs.get(t1));
        System.out.println("Get t11:");
        System.out.println(cbs.get(t11));
        System.out.println("Get t1 copy:");
        System.out.println(cbs.get(t1.deepclone()));
    }
}
