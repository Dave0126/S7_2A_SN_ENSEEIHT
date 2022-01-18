package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientSimple {
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.getRegistry(9999); // 得到端口号为9999的RMI注册器

            Service service1 = (Service) registry.lookup("RemoteService1");
            Service service2 = (Service) registry.lookup("RemoteService2");

            Class stubClass = service1.getClass();
            System.out.println("service1是"+ stubClass.getName() +"的实例");

            Class[] stubInterface = stubClass.getInterfaces();
            for (int i = 0; i < stubInterface.length; i++){
                System.out.println(stubInterface[i]);
            }

            System.out.println(service1.echo("Hello!"));
            System.out.println(service2.echo("Hi!"));

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
