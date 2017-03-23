package sunny.homework.calculator;

import java.io.IOException;
import java.io.InputStream;

import sunny.homework.dataStructors.stack.Stack;
import sunny.homework.decorator.MyTokenStream;
import sunny.homework.decorator.Token;
import sunny.homework.decorator.Token.TokenType;
import sunny.homework.decorator.TokenStream;

/**
 * �������㣨�ǵݹ�ʵ�֣��޷���������
 * Created by Sunny on 2017��3��21��
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
				 * ���������������ջ�� 
				 * 1��ջΪ�� 
				 * 2��ջ��������Ϊ��,��ǰ��������ջ 
				 * 3����ǰ������Ϊ��,ֱ����ջ
				 * 4��ջ����������Ϊ��ʱ,��ǰ���������ȼ�����ջ����������ֱ����ջ
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

	// �Ӽ��˳�����
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

	// �ж����ȼ���+��-���ȼ�С��*��/��
	// ջ�����������ȼ��ȵ�ǰ���������ȼ�С������-1����ǰ��������ջ
	private static int preOrder(Token.TokenType left, Token.TokenType right) {
		//��ջ��������Ϊ��,֮��Ĳ���������ջ
		//��ǰ������Ϊ��ֱ����ջ
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
