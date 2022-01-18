package socket.singleProcess;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyClient {
    public static void main(String[] args) throws IOException {
        // 客户端 连接 服务端 发布的服务
        Socket client = new Socket("127.0.0.1", 9999);

        // 接收 Server 发送的消息InputStream
        InputStream in = client.getInputStream();
        byte[] in_Buffer = new byte[100];
        in.read(in_Buffer); // 把 in的数据 读取到 buffer 里
        // byte[] -> String
        System.out.println("Server -> Client 接收到的消息是："+new String(in_Buffer));

        // Client 向 Server 发送消息
        OutputStream out = client.getOutputStream();
        out.write("你好Server，我是Client".getBytes(StandardCharsets.UTF_8));

        in.close();
        out.close();
        client.close();

    }
}
