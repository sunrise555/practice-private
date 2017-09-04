package concurrent_art.threadPool;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Sunny on 2017/8/19.
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        SocketAddress remoteAddr=new InetSocketAddress("localhost",8080);
        socket.connect(remoteAddr);
        System.out.println("µÈ´ýÁ¬½Ó...");
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream("F:\\DevelopmentWorkplace\\Intellij\\JVM_Practices\\src\\concurrent_art\\threadPool\\index.html")));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            out.write(line);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = null;
        while ((s = reader.readLine()) != null)
            System.out.println(s);
    }
}
