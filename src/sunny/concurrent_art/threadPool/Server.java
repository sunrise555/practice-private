package concurrent_art.threadPool;

/**
 * Created by Sunny on 2017/8/19.
 */
public class Server {
    public static void main(String[] args) throws Exception{
        SimpleHttpServer.setBasePath("F:\\DevelopmentWorkplace\\Intellij\\JVM_Practices\\src\\concurrent_art\\threadPool");
        SimpleHttpServer.start();
    }
}
