package sunny.homework.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * ����������߳�
 * @Created by Sunny on 2017��4��19��
 */
public class ServerThread extends Thread {
	// �ͻ�������
	private Socket clientSocket;
	
	public ServerThread() {}
	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() {
		ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        
        try {
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
