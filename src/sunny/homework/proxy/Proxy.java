package sunny.homework.proxy;
/**
 * ������
 * @Created by Sunny on 2017��4��7��
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
		// �ڱ�����Ķ����ĳ���������н������ӡ�����Ĳ����ͷ���ֵ
		int result = target.getId(id);
		System.out.println("������" + id +" ����ֵ��" + id);
		return result;
	}
	
	

}
