package sunny.homework.calculator;

import java.io.IOException;
import java.io.InputStream;

import sunny.homework.dataStructors.stack.Stack;
import sunny.homework.decorator.MyTokenStream;
import sunny.homework.decorator.Token.TokenType;
import sunny.homework.decorator.TokenStream;


public class SuffixCalculator {
	public  static Integer calculate(InputStream in) throws  IOException {
		Integer result = null;
		TokenStream ts = new MyTokenStream(in);
		Stack<Integer> s = new Stack<Integer>(10);
		while (ts.getToken().tokenType != TokenType.NONE) {
			if (ts.getToken().tokenType == TokenType.INT) {
				s.push(Integer.valueOf(ts.getToken().value.toString()));
			} else {
				switch (ts.getToken().value.toString()) {
				case "+":
					s.push(s.pop() + s.pop());
					break;
				case "-":
					s.push(-s.pop() + s.pop());
					break;
				case "*":
					s.push(s.pop() * s.pop());
					break;
				case "/":
					s.push(1 / s.pop() * s.pop());
					break;
				}
			}
			ts.consumeToken();
		}
		result = s.pop();
		if(!s.isEmpty()){
			return null;
		}
		return result;
	}
}
