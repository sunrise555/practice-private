package sunny.homework.rpc;

import java.lang.reflect.Proxy;

import sunny.homework.calculator.Calculator;

/**
 * 客户端
 * @Created by Sunny on 2017年4月7日
 */
public class Client {
	public static void main(String[] args) {
		// 为了使客户端不直接调用服务端的方法，只让客户端知道调用echo接口
		// 使用代理模式来隐藏socket通信
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
		System.out.println(expression + " 计算结果为：" + cal.calculate(expression));
	}
}
