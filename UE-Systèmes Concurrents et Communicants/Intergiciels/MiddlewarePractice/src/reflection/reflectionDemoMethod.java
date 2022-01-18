package reflection;

import java.lang.reflect.Method;

public class reflectionDemoMethod {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> personClass =  Class.forName("reflection.Person");
        // 获取所有公共的方法，不仅当前类，连同父类的方法也拿到了，以及实现的接口方法；但无私有方法
        Method[] pubilcMethods = personClass.getMethods();
        for (Method method : pubilcMethods) {
            System.out.println(method);
        }

        System.out.println("\n\n--------------------------------------------------------");
        System.out.println("获取当前类的所有方法：");
        // 如何获取 *当前类* 的 *所有方法*
        // 1。只能是当前类
        // 2。 忽略访问修饰符限制
        Method[] methodsDeclared = personClass.getDeclaredMethods();
        for (Method method : methodsDeclared){
            System.out.println(method);
        }
    }
}
