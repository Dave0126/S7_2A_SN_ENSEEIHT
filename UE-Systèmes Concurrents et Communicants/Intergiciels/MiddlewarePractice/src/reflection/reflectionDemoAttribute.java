package reflection;

import java.lang.reflect.Field;

public class reflectionDemoAttribute {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获得所有公共属性
        Class<?> personClass =  Class.forName("reflection.Person");

        System.out.println("获得所有 *公共* 属性：xxx.getFields()");
        Field[] fields = personClass.getFields();
        for (Field field : fields){
            System.out.println(field);
        }

        System.out.println("\n\n--------------------------------------------------------");
        System.out.println("获取当前类的所有属性：xxx.getDeclaredFields()");
        // 如何获取 *当前类* 的 *所有属性*
        Field[] fieldsAll = personClass.getDeclaredFields();
        for (Field field : fieldsAll){
            System.out.println(field);
        }
    }
}
