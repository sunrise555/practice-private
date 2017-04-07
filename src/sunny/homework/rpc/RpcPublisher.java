package sunny.homework.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��rpc������������
 * @Created by Sunny on 2017��4��7��
 */
public class RpcPublisher {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Socket clientSocket = null;
        ServerSocket ss = null;
        
        try {
        	// �����󶨵��ض��˿ڵķ������׽���
			ss = new ServerSocket(8081);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        // ������������
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
				
				// ������˱��ص��÷�������ֵ���л�д������
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
