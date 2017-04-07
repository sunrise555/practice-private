package sunny.homework.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * 动态代理类
 * @Created by Sunny on 2017年4月7日
 */
public class DynamicProxyHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// 完成客户端socket通信的部分
		Socket s = null;
		// 序列化对象
		ObjectOutputStream oos = null;
		// 反序列化对象
		ObjectInputStream ois = null;
		
		try {
			s = new Socket();
			s.connect(new InetSocketAddress("localhost", 8081));
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			// 以 UTF-8 修改版格式写入此 String 的基本数据
			//oos.writeUTF("sunny.homework.rpc.EchoServiceImpl");
			oos.writeUTF("sunny.homework.calculator.BinaryTreeCaculator");
			oos.writeUTF(method.getName());
			// 将参数类型、参数args对象写入流中
			oos.writeObject(method.getParameterTypes());
			oos.writeObject(args);
			
			// 将流中的对象反序列化
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
