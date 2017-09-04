package concurrent_art.threadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 基于线程池的简单web服务器
 * Created by Sunny on 2017/8/19.
 */
public class SimpleHttpServer {
    static ThreadPool<HttpRequestHandler> pool = new DefaultThreadPool
            <HttpRequestHandler>(10);

    // SimpleHttpServer的根路径
    static String basePath;
    static ServerSocket serverSocket;

    static int port = 8080;

    public static void setPoint(int port) {
        if (port > 0 )
            SimpleHttpServer.port = port;
    }

    public static void setBasePath(String basePath) {
        if (basePath!=null && new File(basePath).exists()
                && new File(basePath).isDirectory())
            SimpleHttpServer.basePath = basePath;
    }

    public static void start() throws Exception{
        serverSocket = new ServerSocket(8080);
        System.out.println("服务端启动...");
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            System.out.println("客户端已连接...");
            pool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                String header = reader.readLine().split(" ")[1];
                // 绝对路径
                String filePath = basePath + header;
                System.out.println("文件路径");
                out = new PrintWriter(socket.getOutputStream());

                if (filePath.endsWith("jpg") || filePath.endsWith("ico")) {
                    System.out.println("image");
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1)
                        baos.write(i);
                    byte[] array = baos.toByteArray();
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    socket.getOutputStream().write(array,0,array.length);
                } else {
                    System.out.println("text");
                    br = new BufferedReader(new InputStreamReader(
                            new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null)
                        out.println(line);
                }
            } catch (Exception e) {
                System.out.println("error");
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, out, reader, socket);
            }


        }
    }
    private static void close(Closeable... closeables) {
        if (null != closeables) {
            for (Closeable closeable:closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        SimpleHttpServer.setBasePath(
                "F:\\DevelopmentWorkplace\\Intellij\\JVM_Practices\\src\\concurrent_art\\threadPool\\index.html");
        SimpleHttpServer.start();
    }
}
