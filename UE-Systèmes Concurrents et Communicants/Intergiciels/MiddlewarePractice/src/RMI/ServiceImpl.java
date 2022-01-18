package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    private String name;

    protected ServiceImpl(String name) throws RemoteException {
        this.name = name;
    }

    // client -远程调用-> server.service
    // 在 server 执行完毕后，将结果返回 client
    @Override
    public String echo(String msg) throws RemoteException{
        System.out.println(name + ": 调用echo()方法。");
        return "echo: " + msg + " from " + name;
    }
}
