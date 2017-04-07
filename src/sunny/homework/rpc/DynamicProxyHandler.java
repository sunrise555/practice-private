package sunny.homework.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * ��̬������
 * @Created by Sunny on 2017��4��7��
 */
public class DynamicProxyHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// ��ɿͻ���socketͨ�ŵĲ���
		Socket s = null;
		// ���л�����
		ObjectOutputStream oos = null;
		// �����л�����
		ObjectInputStream ois = null;
		
		try {
			s = new Socket();
			s.connect(new InetSocketAddress("localhost", 8081));
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			// �� UTF-8 �޸İ��ʽд��� String �Ļ�������
			//oos.writeUTF("sunny.homework.rpc.EchoServiceImpl");
			oos.writeUTF("sunny.homework.calculator.BinaryTreeCaculator");
			oos.writeUTF(method.getName());
			// ���������͡�����args����д������
			oos.writeObject(method.getParameterTypes());
			oos.writeObject(args);
			
			// �����еĶ������л�
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null)
                s.close();

            if (oos != null)
                oos.close();

            if (ois != null)
                ois.close();
		}
		return null;
	}

}
