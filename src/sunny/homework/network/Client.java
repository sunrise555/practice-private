package sunny.homework.network;

import java.io.*;
import java.net.Socket;

/**
 * Created by sunny_hbqq on 2017/3/27.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // Socket在构造时就发起向服务器的连接
        Socket conn = new Socket("127.0.0.1", 8080);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write("hello\n");
        bw.flush();
        String s = br.readLine();
        System.out.println(s);

        bw.write("world\n");
        bw.flush();
        s = br.readLine();
        System.out.println(s);


        br.close();
        bw.close();
        conn.close();

    }
}
