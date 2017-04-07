package sunny.homework.rpc;

import java.lang.reflect.Proxy;

import sunny.homework.calculator.Calculator;

/**
 * �ͻ���
 * @Created by Sunny on 2017��4��7��
 */
public class Client {
	public static void main(String[] args) {
		// Ϊ��ʹ�ͻ��˲�ֱ�ӵ��÷���˵ķ�����ֻ�ÿͻ���֪������echo�ӿ�
		// ʹ�ô���ģʽ������socketͨ��
//		Class<EchoService> cl = EchoService.class;
//		EchoService echo = (EchoService) Proxy.newProxyInstance(cl.getClassLoader(), 
//				new Class<?>[]{EchoService.class}, new DynamicProxyHandler());
//		
//		for (int i = 0; i < 3; i++) {
//            System.out.println(echo.echo(String.valueOf(i)));
//        }
		
		Class<?> cl = Calculator.class;
		Calculator cal = (Calculator) Proxy.newProxyInstance(cl.getClassLoader(), 
				new Class<?>[]{Calculator.class}, new DynamicProxyHandler());
		String expression = "1+2-3";
		System.out.println(expression + " ������Ϊ��" + cal.calculate(expression));
	}
}
