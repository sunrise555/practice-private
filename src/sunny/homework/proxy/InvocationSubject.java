package sunny.homework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationSubject implements InvocationHandler {
	private Subject target;
	
	InvocationSubject(Subject subject) {
		this.target = subject;
	}
	/*何时执行的InvocationHandler.invoke()方法？？？
	 * 在$Proxy.java中可以看到:
	 * 	request(){
	 * 		super.h.invoke(this, m4, new Object[] {Integer.valueOf(i)});
	 * }
	 * 同时默认动态代理了toString()，hashCode()，equals(Object obj)
	 * 
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("log : " + method.getName() + " invoked with " + args);
		// 调用invoke前可插入自己的逻辑功能
		return method.invoke(target, args);
	}
}
