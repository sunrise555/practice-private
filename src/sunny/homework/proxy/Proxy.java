package sunny.homework.proxy;
/**
 * 代理类
 * @Created by Sunny on 2017年4月7日
 */
public class Proxy implements Subject {
	
	private Subject target;
	
	public Proxy(Subject s) {
		this.target = s;
	}
	
	@Override
	public void request(int id) {
		System.out.println("Proxy log : " + id);
		target.request(id);
		
	}

	public void setTarget(Subject realSubject) {
		this.target = realSubject;
	}

	@Override
	public int getId(int id) {
		System.out.println("Proxy log : " + id);
		// 在被代理的对象的某个函数运行结束后打印函数的参数和返回值
		int result = target.getId(id);
		System.out.println("参数：" + id +" 返回值：" + id);
		return result;
	}
	
	

}
