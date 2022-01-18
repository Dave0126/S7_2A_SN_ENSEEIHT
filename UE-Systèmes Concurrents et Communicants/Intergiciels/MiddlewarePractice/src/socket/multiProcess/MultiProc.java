package socket.multiProcess;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public  class MultiProc implements Runnable {
    // 构造方法传参
    public MultiProc(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;

    @Override
    public  void run() {
        try{
            // Server 向 Client 发送消息
            OutputStream out = socket.getOutputStream();
            //byte[] buffer = new byte[1024];
            // String -> byte : String.getBytes();
            byte[] write_Buffer  = "Hello Client! I am Server".getBytes(StandardCharsets.UTF_8);
            out.write(write_Buffer);

            // 接收 Client 发送的消息
            InputStream in = socket.getInputStream();
            byte[] in_Buffer = new byte[100];
            in.read(in_Buffer);
            System.out.println("Client -> Server 接收到的消息是："+new String(in_Buffer));

            in.close();
            out.close();
            socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
