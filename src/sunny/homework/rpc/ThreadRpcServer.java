package sunny.homework.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �ɿ������̵߳�RPC������
 * @Created by Sunny on 2017��4��19��
 */
public class ThreadRpcServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Socket clientSocket = null;
	    ServerSocket ss = null;
	    
	    try {
	    	// �����󶨵��ض��˿ڵķ������׽���
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
