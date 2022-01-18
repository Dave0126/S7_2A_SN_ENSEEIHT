package RMI_RPC.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceRigsterImpl implements ServiceRegister{
    // map：服务端所有可供客户端访问的接口，都注册到map中
    // key: 接口的名字；
    // value：具体实现
    private static HashMap<String,Class> serviceRegister = new HashMap();
    private static int port;
    // 连接池：连接池有多个连接对象，每个连接对象都可以处理一个客户请求
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static boolean isRunning = false; // 表示当前服务是否已经开启

    public ServiceRigsterImpl( int port) {
        this.port = port;
    }

    // 开启服务端的服务
    @Override
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port)); // 绑定端口

        isRunning = true;

        while (true){ // 并发 -> 多线程
            // 具体的服务内容：接受客户端请求，处理请求，并返回结果
            System.out.println("服务器已启动...");
            try {
                // 客户端每发出一次请求，则服务端从连接池中获取一个线程对象去处理
                Socket server = serverSocket.accept();
                // 每执行一次，就会从连接池里取一个线程来执行
                executorService.execute(new ServiceTask(server));


            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    // 关闭服务
    @Override
    public void close() {
        isRunning = false;
        executorService.shutdown();

    }

    @Override
    public void register(String serviceName, Class serveicImpl) {
        serviceRegister.put(serviceName, serveicImpl);


    }
}
