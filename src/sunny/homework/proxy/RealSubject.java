package sunny.homework.proxy;
/**
 * ��ʵ�ı�����Ķ���
 * @Created by Sunny on 2017��4��7��
 */
public class RealSubject implements Subject {

	@Override
	public void request(int id) {
		System.out.println("RealSubject.request��" + id);
	}

	@Override
	public int getId(int id) {
		System.out.println("RealSubject.getId��" + id);
		return id;
	}

}
