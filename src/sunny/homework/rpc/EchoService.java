package sunny.homework.rpc;
/**
 * 客户端向服务端发起调用所使用的接口
 * @Created by Sunny on 2017年4月7日
 */
public interface EchoService {
	String echo(String request);
}
