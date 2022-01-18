package reflection;

import java.lang.reflect.Constructor;

public class reflectionDemoConstuctor {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> personClass =  Class.forName("reflection.Person");

        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors){
            System.out.println(constructor);
        }
    }
}
