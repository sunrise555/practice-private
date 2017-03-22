package sunny.homework.decorator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import sunny.homework.decorator.Token.TokenType;



public class MyTokenStream implements TokenStream {
	private Token token;

	private BufferedReader reader;

	private int index = 0;// 输入字符串的游标

	private String expression;// 表达式输入字符串形式

	private int tokenIndex = 0;// token的游标

	private ArrayList<Token> tokens = new ArrayList<Token>();// 存储token

	public MyTokenStream(InputStream in) throws IOException {
		this.reader = new BufferedReader(new InputStreamReader(in));
		this.expression = reader.readLine();
		this.tokens = this.getTokens();
	}

	private ArrayList<Token> getTokens() {
		while (this.index != this.expression.length()) {
			switch (this.expression.charAt(index)) {
				case '(':
					token = new Token(TokenType.LPAR, "(");
					break;
				case ')':
					token = new Token(TokenType.RPAR, ")");
					break;
				case '+':
					token = new Token(TokenType.PLUS, "+");
					break;
				case '-':
					token = new Token(TokenType.MINUS, "-");
					break;
				case '*':
					token = new Token(TokenType.MULT, "*");
					break;
				case '/':
					token = new Token(TokenType.DIV, "/");
					break;
				case ' ':
					token = new Token(TokenType.SPACE, " ");
					break;
				default: {
					int num = this.expression.charAt(index) - 48;
					if (num >= 0 && num <= 9) {
						token = new Token(TokenType.INT, Integer.valueOf(num));
						// this.index++;
						if ((index + 1) == this.expression.length())
							break;
						int temp = this.expression.charAt(index + 1) - 48;
						while (temp >= 0 && temp <= 9) {
							this.index++;
							token.value = Integer.valueOf(token.value.toString())
									* 10 + Integer.valueOf(temp);
							if ((index + 1) == this.expression.length())
								break;
							temp = this.expression.charAt(index + 1) - 48;
						}
					}
				}
			}
			tokens.add(token);
			this.index++;
		}
		tokens.add(new Token(TokenType.NONE, "end"));
		return tokens;
	}

	@Override
	public Token getToken() throws IOException {
		return tokens.get(tokenIndex);
	}

	@Override
	public void consumeToken() {
		this.tokenIndex++;
	}

}
