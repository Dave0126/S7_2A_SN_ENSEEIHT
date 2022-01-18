package RMI_RPC.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

public class ServiceTask implements Runnable{
    // map：服务端所有可供客户端访问的接口，都注册到map中
    // key: 接口的名字；
    // value：具体实现
    private static HashMap<String,Class> serviceRegister = new HashMap();

    private Socket server;

    public ServiceTask(Socket server) {
        this.server = server;
    }

    public ServiceTask() {
    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try{
            // 接受到客户端连接 及 请求
            in = new ObjectInputStream(server.getInputStream()); // 客户端发出的所有请求
            // 因为序列化流ObjectInputStream对发送数据的顺序严格要求，因此需要参照发送顺序逐个接收
            String serviceName = in.readUTF();
            String methodName = in.readUTF();
            Class[] parameterTypes = (Class[]) in.readObject(); // 方法的参数类型
            Object[] arguments = (Object[]) in.readObject();// 方法的参数

            // 根据客户请求，在map中找到对应的具体接口
            Class serviceClass = serviceRegister.get(serviceName); // 接口的对象
            Method serviceMethod = serviceClass.getMethod(methodName,parameterTypes);
            // 执行该方法
            Object result = serviceMethod.invoke(serviceClass.newInstance(),arguments); // 返回任意类型（Object）的result

            // ->客户端 把方法执行完毕后的返回值 传回客户端
            out = new ObjectOutputStream(server.getOutputStream());
            out.writeObject(result);

            // 返回客户端业务
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
