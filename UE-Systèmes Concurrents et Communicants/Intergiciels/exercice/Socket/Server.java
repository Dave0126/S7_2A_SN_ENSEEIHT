import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000); // 在本机IP（可缺省，默认本地IP）下, 端口:2000 进行监听

        System.out.println("服务器准备就绪...");
        System.out.println("服务器端信息：" + serverSocket.getInetAddress() + " Port: " + serverSocket.getLocalPort());

        while (true) { // 无限循环
            // 等待客户端链接
            Socket client =  serverSocket.accept();
            // 构造客户端异步线程
            ClientProcessing clientProcessing = new ClientProcessing(client);
            // 启动客户端线程
            clientProcessing.start();
        }
    }


    // 多个客户端需要一步处理, 继承自Thread类
    // 处理客户端消息
    private static class ClientProcessing extends Thread {
        private Socket client;
        private boolean flag = true;

        // 构造体
        ClientProcessing (Socket client) {
            this.client = client;
        }

            public void run() {
                System.out.println("新客户端信息：" + client.getInetAddress() + " Port: " + client.getLocalPort());

                try {
                    // 得到打印流，用于数据输出；服务器回送数据
                    PrintStream socketOutput = new PrintStream(client.getOutputStream());
                    // 得到输入流，用于接收数据
                    BufferedReader socketInput = new BufferedReader(new InputStreamReader(client.getInputStream()));

                    while (flag){
                        // 客户端拿到一条数据
                        String str = socketInput.readLine();
                        if ("bye".equalsIgnoreCase(str)) { // 收到“bye”则关闭客户端
                            flag = false;
                            // 回送
                            socketOutput.println("bye");
                        } else {
                            // 打印到屏幕，并回送数据长度
                            System.out.println(str);
                            socketOutput.println("回送数据长度：" + str.length());
                        }
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("连接异常断开");
                    e.printStackTrace();
                } finally {
                    try {
                        client.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("客户端已退出：" + client.getInetAddress() + " Port: " + client.getLocalPort());
            }
    }
}
