package reflection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class reflectionDemo2 {
    // 获取对象的实例，并操作对象
    public static void exo1() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> personClass =  Class.forName("reflection.Person");

        Person per = (Person) personClass.newInstance(); // 强制转化, newInstance()已经过时，被弃用

        per.setName("DAI");
        per.setAge(24);
        System.out.println(per.getName() + ":" + per.getAge());

    }

    // 操作属性
    public static void exo2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<?> personClass =  Class.forName("reflection.Person");

        Person per2 = (Person) personClass.newInstance(); // 强制转化
        Field fieldNAME = personClass.getDeclaredField("name");
        // 我们需要访问private修饰的name，但是private是私有的
        // 我们需要修改访问权限
        // 使用反射时，如果因为访问修饰符限制造成异常，可以通过 属性/方法.setAccessible(true)
        fieldNAME.setAccessible(true);
        fieldNAME.set(per2,"DAI Guohao"); // per2.setName("DAI Guohao")
        System.out.println(per2.getName());

        System.out.println("=====================");
        Method method = personClass.getDeclaredMethod("privateMethod", null);
        method.setAccessible(true);
        // 通过invoke（）调用方法
        method.invoke(per2,null);

        System.out.println("=====================");
        Method methodWithName = personClass.getDeclaredMethod("privateMethodWithName", String.class);
        methodWithName.setAccessible(true);
        // 通过invoke（）调用方法
        methodWithName.invoke(per2,"DAI Guohao");
    }

    // 操作构造方法
    public static void exo3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<?> personClass = Class.forName("reflection.Person");

        // Constructor<?>[] constructorsPublic = personClass.getConstructors();
        System.out.println("=====================");
        Constructor<?>[] constructorsAll = personClass.getDeclaredConstructors();
        for (Constructor constructor : constructorsAll){
            System.out.println(constructor);
        }

        // 获取指定的构造方法
        // 在反射中，基本类型 和 包装类型 不能通用
        System.out.println("=====================");
        Constructor constructor = personClass.getConstructor(int.class);
        System.out.println(constructor);

        System.out.println("=====================");
        Constructor constructorPrivate = personClass.getDeclaredConstructor(String.class,int.class);
        System.out.println(constructorPrivate);
    }

    // 动态加载 类名 和 方法, 通过修改Class.txt更改调用的类名和方法
    public static void exo4() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IOException {

        Properties properties = new Properties();
        properties.load(new FileReader("/Users/dave/IdeaProjects/MiddlewarePractice/src/reflection/Class.txt"));
        String className = properties.getProperty("classname");
        String methodName = properties.getProperty("methodname");

        Class<?> personClass = Class.forName(className);

        System.out.println("=====================");

        Method method = personClass.getMethod(methodName);
        method.invoke(personClass.newInstance());

    }

        public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IOException {
        exo1();
        exo2();
        exo3();
        exo4();

    }
}
