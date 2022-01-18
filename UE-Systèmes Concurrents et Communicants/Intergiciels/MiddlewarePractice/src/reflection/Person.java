package reflection;

public class Person implements MyInterface, MyInterface2{

    public Person(int ID) {
        this.ID = ID;
    }

    public Person() {
    }

    public Person(String name, int ID, int age) {
        this.name = name;
        this.ID = ID;
        this.age = age;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    private String name;
    private int ID;

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    public String publicField;

    @Override
    public void interfaceMethod() {
        System.out.println("this is a method of interface...");
    }

    public static void staticMethod(){
        System.out.println("this is static method");
    }

    private void privateMethod() {
        System.out.println("this is a privateMethod");
    }

    private void privateMethodWithName(String name) {
        System.out.println( name + "say : this is a privateMethodWithName");
    }

    @Override
    public void interfaceMethod2() {
        System.out.println("this is a method of interface 2 ...");
    }
}
