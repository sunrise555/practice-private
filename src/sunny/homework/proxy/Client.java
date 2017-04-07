package sunny.homework.proxy;

/**
 * �ͻ���
 * @Created by Sunny on 2017��4��7��
 */
public class Client {
	public static void main(String[] args) {
//		Subject s = new Proxy(new RealSubject());
//		s.request(2);
//		System.out.println("-----------------------------");
//		s.getId(7);
		// ����$Proxy0.class�ļ� ����·��Ϊ��com/sun/proxy
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		dynamicProxy();
		
		
		// ���
			//		log : request invoked with [Ljava.lang.Object;@73a28541
			//		RealSubject.request��1
	}
	
	/**
	 * ��̬����
	 * 
	 * @Created 2017��4��7��
	 *
	 */
	public static void dynamicProxy() {
		Subject realSubject = new RealSubject();
		Subject proxy = (Subject) java.lang.reflect.Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), 
				realSubject.getClass().getInterfaces(), new InvocationSubject(realSubject));
		System.out.println("����newProxyInstance���ص�proxy�Ĳ�����Ϣ��");
		System.out.println("proxy�Ƿ�ΪProxy��һ��ʵ����" + (proxy instanceof java.lang.reflect.Proxy));
		System.out.println("proxy��class�ࣺ" + proxy.getClass().getName());
		System.out.println("subject�ĸ����ǣ�" + proxy.getClass().getSuperclass());
		System.out.println("proxyʵ�ֵĽӿ��ǣ�");
		Class<?>[] interfaces = proxy.getClass().getInterfaces();
		
		for (Class<?> class1 : interfaces) {
			System.out.print(class1.getName() + " ");
		}
//		proxy��class�ࣺcom.sun.proxy.$Proxy0
//		subject�ĸ����ǣ�class java.lang.reflect.Proxy
//		proxyʵ�ֵĽӿ��ǣ�
//		sunny.homework.proxy.Subject 
		
		System.out.println("\n\n" + "���н����");
		proxy.request(1);
	}
}
