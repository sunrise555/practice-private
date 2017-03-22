package sunny.homework.decorator;

import java.io.IOException;
/**
 * TokenStream������
 * Created by Sunny on 2017��3��15��
 */
public interface TokenStream {
	/**
	 * ��ȡ��ǰ�α���ָ���Token����
	 * @return Token����
	 * @throws IOException
	 */
	public Token getToken()  throws IOException;
	
	/**
	 * ����ǰ�α������1λ
	 */
    public void consumeToken();
}
