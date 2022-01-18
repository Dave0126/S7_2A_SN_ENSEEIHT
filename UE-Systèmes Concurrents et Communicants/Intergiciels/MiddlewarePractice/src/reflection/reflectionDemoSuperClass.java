package reflection;

public class reflectionDemoSuperClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> personClass =  Class.forName("reflection.Person");

        Class <?> superClass = personClass.getSuperclass();
        System.out.println(superClass);
    }
}
