package sunny.homework.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 将rpc发布到网络中
 * @Created by Sunny on 2017年4月7日
 */
public class RpcPublisher {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Socket clientSocket = null;
        ServerSocket ss = null;
        
        try {
        	// 创建绑定到特定端口的服务器套接字
			ss = new ServerSocket(8081);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // 在网络上侦听
        while (true) {
        	try {
				clientSocket = ss.accept();
				ois = new ObjectInputStream(clientSocket.getInputStream());
				oos = new ObjectOutputStream(clientSocket.getOutputStream());
				
				String serviceName = ois.readUTF();
				String methodName = ois.readUTF();
				
				Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
				Object[] parameters = (Object[]) ois.readObject();
				
				Class<?> service = Class.forName(serviceName);
				Method method = service.getMethod(methodName, parameterTypes);
				
				// 将服务端本地调用方法返回值序列化写入流中
				oos.writeObject(method.invoke(service.newInstance(), parameters));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (clientSocket != null) {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
			}
        }
	}
}
