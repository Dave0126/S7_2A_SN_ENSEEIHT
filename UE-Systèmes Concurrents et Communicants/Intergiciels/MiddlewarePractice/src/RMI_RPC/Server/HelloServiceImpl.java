package RMI_RPC.Server;

public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHi(String name) {
        return "hi! "+ name;
    }
}
