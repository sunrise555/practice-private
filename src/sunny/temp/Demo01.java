package sunny.temp;

import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
//import java.util.LinkedList;

public class Demo01 {
	public static void main(String[] args) throws IOException {
		// byte[] buf = new byte[512];
		// System.out.println("hey, may I have your name, please? ");
		// int n = 0;
		// try {
		// n = System.in.read(buf);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// System.out.print("hello, ");
		// System.out.write(buf, 0, n);
		// System.out.println(buf);
		// char[] cbuf = new char[256];
		// System.out.println("hey, may I have your name, please? ");
		// int n = 0;
		// Reader r = new InputStreamReader(System.in);
		// try {
		// n = r.read(cbuf);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// System.out.println("hello, Mr. " + cbuf[0]);
		// System.out.println("n: "+n);
		// for (int i = 0; i < cbuf.length; i++) {
		// System.out.println(cbuf[i]);
		// }

		// int n = 10;
		// int t = fact(n);
		// System.out.println(t);
		/*
		 * 将numElements转变为大于numElements的最小的2次幂数，如10->16 ,17->32 int numElements
		 * = 10; int initialCapacity = 8; if (numElements >= initialCapacity) {
		 * initialCapacity = numElements; initialCapacity |= (initialCapacity
		 * >>> 1); initialCapacity |= (initialCapacity >>> 2); initialCapacity
		 * |= (initialCapacity >>> 4); initialCapacity |= (initialCapacity >>>
		 * 8); initialCapacity |= (initialCapacity >>> 16); initialCapacity++;
		 * 
		 * if (initialCapacity < 0) // Too many elements, must back off
		 * initialCapacity >>>= 1; }
		 */

		 Deque<Integer> q = new ArrayDeque<>();
		//Deque<Integer> q = new LinkedList<>();
		long begin = System.nanoTime();
		test(q);
		long end = System.nanoTime();
		System.out.println("took " + (end - begin) + "ns");
	}

	public static void test(Deque<Integer> q) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 10_000; j++) {
				q.addLast(j);
			}

			for (int j = 0; j < 10_000; j++) {
				q.removeFirst();
			}
		}
	}

	public static int fact(int n) {
		if (n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}
}
