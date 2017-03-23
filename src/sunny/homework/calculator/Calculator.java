package sunny.homework.calculator;

import java.io.IOException;
import java.io.InputStream;

import sunny.homework.dataStructors.stack.Stack;
import sunny.homework.decorator.MyTokenStream;
import sunny.homework.decorator.Token;
import sunny.homework.decorator.Token.TokenType;
import sunny.homework.decorator.TokenStream;

/**
 * 四则运算（非递归实现，无法处理负数）
 * Created by Sunny on 2017年3月21日
 */
public class Calculator {
	public static Integer calculate(InputStream in) throws IOException {
		Integer result = null;
		TokenStream ts = new MyTokenStream(System.in);
		Stack<Integer> numbers = new Stack<>(100);
		Stack<Token> operators = new Stack<>(100);
		while (ts.getToken().tokenType != Token.TokenType.NONE) {
			if (ts.getToken().tokenType == Token.TokenType.INT) {
				numbers.push((Integer) ts.getToken().value);
				ts.consumeToken();
			} else {
				/*
				 * 四种情况操作符入栈： 
				 * 1、栈为空 
				 * 2、栈顶操作符为（,当前操作符入栈 
				 * 3、当前操作符为（,直接入栈
				 * 4、栈顶操作符不为（时,当前操作符优先级大于栈顶操作符，直接入栈
				 */
				if (operators.getTop() == null
						|| preOrder(operators.getTop().tokenType,
								ts.getToken().tokenType) < 0) {
					operators.push(ts.getToken());
					ts.consumeToken();
				} else if (ts.getToken().tokenType == TokenType.RPAR) {
					while (operators.getTop().tokenType != TokenType.LPAR) {
						binaryCalc(numbers, operators);
					}
					operators.pop();
					ts.consumeToken();
				} else {
					binaryCalc(numbers, operators);
					operators.push(ts.getToken());
//					if(preOrder(operators.getTop().tokenType,
//							ts.getToken().tokenType)  > 0)
//						binaryCalc(numbers, operators);
					ts.consumeToken();
				}
			}
		}
		while (!operators.isEmpty()) {
			binaryCalc(numbers, operators);
		}
		result = numbers.getTop();
		return result;
	}

	// 加减乘除运算
	private static void binaryCalc(Stack<Integer> numbers,
			Stack<Token> operators) {
		int a = numbers.pop();
		int b = numbers.pop();

		Token oprt = operators.pop();
		int d = 0;
		if (oprt.tokenType == Token.TokenType.PLUS)
			d = b + a;
		else if (oprt.tokenType == Token.TokenType.MULT)
			d = a * b;
		else if (oprt.tokenType == Token.TokenType.MINUS)
			d = b - a;
		else if (oprt.tokenType == Token.TokenType.DIV)
			d = b / a;

		numbers.push(d);
	}

	// 判断优先级，+、-优先级小于*、/，
	// 栈顶操作符优先级比当前操作符优先级小：返回-1，当前操作符进栈
	private static int preOrder(Token.TokenType left, Token.TokenType right) {
		//当栈顶操作符为（,之后的操作符均入栈
		//当前操作符为（直接入栈
		if (left == TokenType.LPAR || right == TokenType.LPAR)
			return -1;
		if (left == Token.TokenType.PLUS || left == Token.TokenType.MINUS) {
			if (right == Token.TokenType.MULT || right == Token.TokenType.DIV)
				return -1;
			else
				return 1;
		} else
			return 1;
	}
}
