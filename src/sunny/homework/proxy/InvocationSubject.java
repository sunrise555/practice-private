package sunny.homework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationSubject implements InvocationHandler {
	private Subject target;
	
	InvocationSubject(Subject subject) {
		this.target = subject;
	}
	/*��ʱִ�е�InvocationHandler.invoke()����������
	 * ��$Proxy.java�п��Կ���:
	 * 	request(){
	 * 		super.h.invoke(this, m4, new Object[] {Integer.valueOf(i)});
	 * }
	 * ͬʱĬ�϶�̬������toString()��hashCode()��equals(Object obj)
	 * 
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("log : " + method.getName() + " invoked with " + args);
		// ����invokeǰ�ɲ����Լ����߼�����
		return method.invoke(target, args);
	}
}
