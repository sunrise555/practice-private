package sunny.homework.calculator;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试后缀表达式计算器和正常四则运算计算器
 * Created by Sunny on 2017年3月16日
 */
public class CalTest {
	public static void main(String[] args) throws IOException {
		InputStream in = System.in;
		//输入正常表达式
		int result = NoRecursionCalculator.calculate(in);
		System.out.println(result);
		//输入后缀表达式
		in = System.in;
		int result2 = SuffixCalculator.calculate(in);
		System.out.println(result2);
	}
}
