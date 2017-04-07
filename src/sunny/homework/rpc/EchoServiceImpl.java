package sunny.homework.rpc;
/**
 * 服务端EchoService的具体实现
 * @Created by Sunny on 2017年4月7日
 */
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String request) {
		return "echo:" + request;
	}

}
