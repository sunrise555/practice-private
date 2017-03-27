package sunny.homework.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 * Created by sunny_hbqq on 2017/3/27.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 在8080端口开启一个ServerSocket
        ServerSocket ss = new ServerSocket(8080);
        // 等待连接
        Socket conn = ss.accept();
        // Connected to the target VM, address: '127.0.0.1:2293', transport: 'socket'
        // reader从网络上读取数据
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        // writer向网络上写入数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        String s = br.readLine();
        // 将读取的字符串转换为大写
        while (s != null) {
            System.out.println(s);
            bw.write(s.toUpperCase() + "\n");
            bw.flush();
            s = br.readLine();
        }

        br.close();
        bw.close();
        conn.close();

    }
}
