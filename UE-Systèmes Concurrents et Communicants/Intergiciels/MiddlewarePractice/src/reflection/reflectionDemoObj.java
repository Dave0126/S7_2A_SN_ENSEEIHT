package reflection;

public class reflectionDemoObj {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> personClass =  Class.forName("reflection.Person");

        Object instance = personClass.newInstance();
        Person per = (Person) instance;
        per.interfaceMethod();
    }
}
