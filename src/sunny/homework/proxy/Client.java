package sunny.homework.proxy;

/**
 * 客户端
 * @Created by Sunny on 2017年4月7日
 */
public class Client {
	public static void main(String[] args) {
//		Subject s = new Proxy(new RealSubject());
//		s.request(2);
//		System.out.println("-----------------------------");
//		s.getId(7);
		// 生成$Proxy0.class文件 保存路径为：com/sun/proxy
		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		dynamicProxy();
		
		
		// 输出
			//		log : request invoked with [Ljava.lang.Object;@73a28541
			//		RealSubject.request：1
	}
	
	/**
	 * 动态代理
	 * 
	 * @Created 2017年4月7日
	 *
	 */
	public static void dynamicProxy() {
		Subject realSubject = new RealSubject();
		Subject proxy = (Subject) java.lang.reflect.Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), 
				realSubject.getClass().getInterfaces(), new InvocationSubject(realSubject));
		System.out.println("关于newProxyInstance返回的proxy的部分信息：");
		System.out.println("proxy是否为Proxy的一个实例？" + (proxy instanceof java.lang.reflect.Proxy));
		System.out.println("proxy的class类：" + proxy.getClass().getName());
		System.out.println("subject的父类是：" + proxy.getClass().getSuperclass());
		System.out.println("proxy实现的接口是：");
		Class<?>[] interfaces = proxy.getClass().getInterfaces();
		
		for (Class<?> class1 : interfaces) {
			System.out.print(class1.getName() + " ");
		}
//		proxy的class类：com.sun.proxy.$Proxy0
//		subject的父类是：class java.lang.reflect.Proxy
//		proxy实现的接口是：
//		sunny.homework.proxy.Subject 
		
		System.out.println("\n\n" + "运行结果：");
		proxy.request(1);
	}
}
