package sunny.homework.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 可开启多线程的RPC服务器
 * @Created by Sunny on 2017年4月19日
 */
public class ThreadRpcServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Socket clientSocket = null;
	    ServerSocket ss = null;
	    
	    try {
	    	// 创建绑定到特定端口的服务器套接字
			ss = new ServerSocket(8081);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    while(true) {
	    	try {
	    		clientSocket = ss.accept();
			} catch (Exception e) {
				continue;
			}
	    	new ServerThread(clientSocket).start();
	    }
	}
}
