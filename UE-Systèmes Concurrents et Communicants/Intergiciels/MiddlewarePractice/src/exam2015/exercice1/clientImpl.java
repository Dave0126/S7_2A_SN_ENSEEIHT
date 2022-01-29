package exam2015.exercice1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class clientImpl implements client{
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",8080);
        InputStream in = client.getInputStream();
        byte[] in_buf = new byte[100];
        in.read(in_buf);
        String msg = new String(in_buf);
        System.out.println(msg);

        msg = "copy that!";
        OutputStream out = client.getOutputStream();
        out.write(msg.getBytes(StandardCharsets.UTF_8));
    }

}
