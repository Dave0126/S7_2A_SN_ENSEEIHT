package exam2015.exercice1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class serverImpl implements server{

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        Socket server = socket.accept();

        OutputStream out = server.getOutputStream();
        String msg = "nihao";
        out.write(msg.getBytes(StandardCharsets.UTF_8));

        InputStream in = server.getInputStream();
        byte[] in_buf = new byte[100];
        in.read(in_buf);
        msg = new String(in_buf);

        System.out.println(msg);
    }

}
