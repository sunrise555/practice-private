package sunny.homework.decorator;

import java.io.IOException;
/**
 * TokenStream适配器
 * Created by Sunny on 2017年3月15日
 */
public interface TokenStream {
	/**
	 * 获取当前游标所指向的Token对象
	 * @return Token对象
	 * @throws IOException
	 */
	public Token getToken()  throws IOException;
	
	/**
	 * 将当前游标向后移1位
	 */
    public void consumeToken();
}
