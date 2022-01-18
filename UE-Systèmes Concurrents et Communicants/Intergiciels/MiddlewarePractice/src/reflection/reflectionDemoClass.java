package reflection;

public class reflectionDemoClass {
    public static void main(String[] args) throws ClassNotFoundException {
        // 3 ways to get reflection : class
            // <1> Class.forName()
            // <2> xx.class
            // <3> obj.getClass()
        Class<?> personClass1 =  Class.forName("reflection.Person"); // 全类名：包名 + 类名
        System.out.println(personClass1);


        Class<?> personClass2 = Person.class;
        System.out.println(personClass2);

        Person person = new Person(3);
        Class <?> personClass3 = person.getClass();
        System.out.println(personClass3);

    }
}
