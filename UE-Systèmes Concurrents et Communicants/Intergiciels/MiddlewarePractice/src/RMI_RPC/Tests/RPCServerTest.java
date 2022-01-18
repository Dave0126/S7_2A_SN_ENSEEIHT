package RMI_RPC.Tests;

import RMI_RPC.Server.HelloService;
import RMI_RPC.Server.HelloServiceImpl;
import RMI_RPC.Server.ServiceRegister;
import RMI_RPC.Server.ServiceRigsterImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RPCServerTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // 服务中心
        ServiceRegister server = new ServiceRigsterImpl(9999);
        server.register("HelloService", HelloServiceImpl.class);
        server.start();


    }
}
