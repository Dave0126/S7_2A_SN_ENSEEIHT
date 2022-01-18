package RMI;

import RMI_RPC.Server.HelloService;
import RMI_RPC.Server.HelloServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;

public class ServerSimple {
    public static void main(String[] args) {
        try {
            Service service1 = new ServiceImpl("service1");
            Service service2 = new ServiceImpl("service2");

            // 创建并启动注册器Registry
            Registry registry = LocateRegistry.createRegistry(9999);
            System.out.println("\n服务器已启动...\n");

            registry.rebind("RemoteService1",service1); // service1 是一个 远程对象，名字是 RemoteService1
            registry.rebind("RemoteService2",service2);

            System.out.println("创建了两个远程对象: ");
            System.out.println(service1.getClass().getName());
            System.out.println(service2.getClass().getName());

            // 该程序不会停止，因为RMI注册器会一直监听9999端口，监听客户端有没有查找远程对象

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
