package RMI_RPC.Tests;

import RMI_RPC.Client.Client;
import RMI_RPC.Client.ClientImpl;
import RMI_RPC.Server.HelloService;

import java.net.InetSocketAddress;

public class RPCClientTest {
    public static void main(String[] args) throws ClassNotFoundException {
        HelloService service = ClientImpl.getRemoteProxyObj(Class.forName("RMI_RPC.Server.HelloService"), new InetSocketAddress("127.0.0.1", 9999));
        System.out.println(service.sayHi("DAI"));

    }
}
