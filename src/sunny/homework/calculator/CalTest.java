package sunny.homework.calculator;

import java.io.IOException;
import java.io.InputStream;

/**
 * ���Ժ�׺���ʽ�������������������������
 * Created by Sunny on 2017��3��16��
 */
public class CalTest {
	public static void main(String[] args) throws IOException {
		InputStream in = System.in;
		//�����������ʽ
		int result = NoRecursionCalculator.calculate(in);
		System.out.println(result);
		//�����׺���ʽ
		in = System.in;
		int result2 = SuffixCalculator.calculate(in);
		System.out.println(result2);
	}
}
