import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class LoadBalancer extends Thread{
    static String hosts[] = {"localhost", "localhost"};
    static int ports[] = {8081,8082};
    static int nbHosts = 2;
    static Random rand = new Random();
    private Socket client;

    public LoadBalancer(Socket s1) {
        this.client = s1;
    }
    public static void main(String[] args) throws IOException {
        ServerSocket ss1 = new ServerSocket(8080);
        while (true){
            Socket s1 = ss1.accept();
            LoadBalancer lb = new LoadBalancer(s1);
            lb.start();
        }
    }
    public void run(){
        int target = LoadBalancer.rand.nextInt(LoadBalancer.nbHosts);
        try {
            Socket server = new Socket (LoadBalancer.hosts[target],LoadBalancer.ports[target]);
            OutputStream clientOS = client.getOutputStream();
            InputStream clientIS = client.getInputStream();
            OutputStream serverOS = server.getOutputStream();
            InputStream serverIS = server.getInputStream();
            byte [] buff = new byte [1024];
            int nbCR = clientIS.read(buff);
            serverOS.write(buff,0,nbCR);
            int nbSR = serverIS.read(buff);
            clientOS.write(buff,0,nbSR);
            server.close();
            client.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    }