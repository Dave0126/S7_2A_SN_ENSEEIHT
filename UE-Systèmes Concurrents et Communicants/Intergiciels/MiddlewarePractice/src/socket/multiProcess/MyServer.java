package socket.multiProcess;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyServer {
    public static void main(String[] args) throws IOException {
        // 默认本机IP
        // 暴露一个服务，本机IP：9999
        ServerSocket server = new ServerSocket(9999);
        System.out.println("启动Server，本机IP：端口" + server.getInetAddress() + ":" + server.getLocalPort());
        // 阻塞在此，等待客户端的连接，才能继续执行
        while (true){
            Socket serverSocket = server.accept(); // 用于监听是否有客户端访问，返回一个Socket对象
            System.out.println("与客户端连接成功！");
            MultiProc multiProc = new MultiProc(serverSocket);
            // Runnable -> Thread
            new Thread(multiProc).start(); // 启动线程
        }
    }

}
