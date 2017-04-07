package sunny.homework.proxy;
/**
 * 真实的被代理的对象
 * @Created by Sunny on 2017年4月7日
 */
public class RealSubject implements Subject {

	@Override
	public void request(int id) {
		System.out.println("RealSubject.request：" + id);
	}

	@Override
	public int getId(int id) {
		System.out.println("RealSubject.getId：" + id);
		return id;
	}

}
