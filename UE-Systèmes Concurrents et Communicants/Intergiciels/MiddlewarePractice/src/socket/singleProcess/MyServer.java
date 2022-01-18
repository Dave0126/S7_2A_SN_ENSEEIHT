package socket.singleProcess;

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
        Socket serverSocket = server.accept(); // 用于监听是否有客户端访问，返回一个Socket对象
        System.out.println("与客户端连接成功！");

        // Server 向 Client 发送消息
        OutputStream out = serverSocket.getOutputStream();
        //byte[] buffer = new byte[1024];
        // String -> byte : String.getBytes();
        byte[] write_Buffer  = "Hello Client! I am Server".getBytes(StandardCharsets.UTF_8);
        out.write(write_Buffer);

        // 接收 Client 发送的消息
        InputStream in = serverSocket.getInputStream();
        byte[] in_Buffer = new byte[100];
        in.read(in_Buffer);
        System.out.println("Client -> Server 接收到的消息是："+new String(in_Buffer));

        in.close();
        out.close();
        serverSocket.close();
        server.close();
    }
}
