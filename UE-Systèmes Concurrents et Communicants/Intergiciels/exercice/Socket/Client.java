import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket();

        try {
            clientSocket.setSoTimeout(3000); // 设置超时等待时间
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            clientSocket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000),3000); // 连接到Server
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("已发起服务器请求，并进入后续流程...");
        System.out.println("客户端信息：" + clientSocket.getLocalAddress() + " Port: " + clientSocket.getLocalPort());
        System.out.println("服务器信息：" + clientSocket.getInetAddress() + " Port: " + clientSocket.getPort());

        run(clientSocket);

        try {
            clientSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("客户端已退出！");

    }

    private static void run(Socket clientSocket) throws IOException {
        boolean flag = true;

        // 从terminal获得输入流
        InputStream in_keyboard =  System.in;
        BufferedReader in_Buffer = new BufferedReader( new InputStreamReader(in_keyboard));

        // 获得Socket输出流，发送给服务器， 并转换为打印流
        OutputStream clientOutputStream = clientSocket.getOutputStream();
        PrintStream clientPrintStream = new PrintStream(clientOutputStream);
        
        // 从服务器获得Socket输入流
        InputStream clientInputStream = clientSocket.getInputStream();
        BufferedReader socketInputBuffer = new BufferedReader( new InputStreamReader(clientInputStream));

        // 从键盘读一行
        while(flag) 
        {
            String str = in_Buffer.readLine();

            // 
            clientPrintStream.println(str);

            // 从服务器读取一行
            String msgFromServer = socketInputBuffer.readLine();
            System.out.println(msgFromServer);
            if ("bye".equalsIgnoreCase(msgFromServer)){
                flag = false;
                System.out.println("Client exiting...");
            } 
        }
        clientPrintStream.close();
        socketInputBuffer.close();
    } 
}
