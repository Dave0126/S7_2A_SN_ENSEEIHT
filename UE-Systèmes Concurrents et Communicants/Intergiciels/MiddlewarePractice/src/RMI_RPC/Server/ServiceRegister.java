package RMI_RPC.Server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ServiceRegister {
    public void start() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
    public void close();
    public void register(String serviceName, Class serveicImpl);
}
