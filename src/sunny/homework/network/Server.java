package sunny.homework.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��������
 * Created by sunny_hbqq on 2017/3/27.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // ��8080�˿ڿ���һ��ServerSocket
        ServerSocket ss = new ServerSocket(8080);
        // �ȴ�����
        Socket conn = ss.accept();
        // Connected to the target VM, address: '127.0.0.1:2293', transport: 'socket'
        // reader�������϶�ȡ����
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        // writer��������д������
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        String s = br.readLine();
        // ����ȡ���ַ���ת��Ϊ��д
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
