package sunny.homework.rpc;
/**
 * �����EchoService�ľ���ʵ��
 * @Created by Sunny on 2017��4��7��
 */
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String request) {
		return "echo:" + request;
	}

}
