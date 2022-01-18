package reflection;

public class reflectionDemoInterface {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> personClass =  Class.forName("reflection.Person");

        Class<?>[] interfaces = personClass.getInterfaces();
        for (Class<?> inter : interfaces){
            System.out.println(inter);
        }
    }
}
