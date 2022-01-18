package RMI_RPC.Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.lang.reflect.Proxy;
import java.net.Socket;

import static java.net.Proxy.*;

public class ClientImpl {
    // 获取代表服务端接口的动态代理对象
    // serviceName: 请求的接口名
    // addr: 带请求服务端的ip：端口
    public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress addr) {
        /*
            Proxy.newProxyInstance(a,b,c)
            a: 类加载器：要代理哪个类，就将那个类的类加载器传入第一个参数
            b：需要代理的对象，具有哪些方法 --接口
         */
            return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new InvocationHandler() {
                @Override
                // proxy: 代理的对象
                // method：代理的方法
                // args：代理方法的参数列表
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 客户端向服务端发送请求：请求具体某一个接口
                    Socket client = new Socket();
                    ObjectOutputStream out = null;
                    ObjectInputStream in = null;

                    try{
                        // socketAddress : IP + Port
                        client.connect(addr);
                        // 发送：序列化流（对象流）
                        out = new ObjectOutputStream(client.getOutputStream());
                        // 需要发送的内容：
                        // 1、接口的名字
                        out.writeUTF(serviceInterface.getName());
                        // 2、方法名及其参数、参数类型
                        out.writeUTF(method.getName());
                        out.writeObject(method.getParameterTypes());
                        out.writeObject(args);
                        // 等待服务端处理...

                        // 接收服务端处理后的返回值
                        in = new ObjectInputStream(client.getInputStream());
                        // 客户端 -> 服务端 -> 返回值
                        return in.readObject();
                    } catch (Exception e){
                        e.printStackTrace();
                        return null;
                    }
                    finally {
                        if (in != null){
                            in.close();
                        }
                        if (out != null){
                            out.close();
                        }
                    }
                }
            });
    }
}
