package sunny.homework.decorator;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		TokenStream ts = new MyTokenStream(System.in); // ��׼�����������
		while (ts.getToken().tokenType != Token.TokenType.NONE) {
			System.out.print(ts.getToken() + " ");
			ts.consumeToken();
		}
	}
}
