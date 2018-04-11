package sunny.haina;

import java.util.Stack;

/**
 * 知乎专栏--进击的Java新人课后作业
 * Created by Sunny on 2017年3月7日
 */
public class HomeWork {

	public static void main(String[] args) {
//		System.out.println(5/2);
//		System.out.println(5%3);
//		System.out.println(oct2Bin(25));
//		System.out.println(Integer.toBinaryString(25));
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.SIZE);
//		System.out.println(Integer.toBinaryString(31<<1));
//		String s="10610";
//		String s2 = new String();
//		
//		System.out.println(s2);
//		System.out.println(transform("221",10,2));
//		System.out.println(transform("11011101",2,10));
//		short a = Byte.MIN_VALUE;
//		int b = Short.MIN_VALUE;
//		short c1 = Byte.MAX_VALUE ;
//		int d1 = (byte)(Byte.MAX_VALUE +2);
//		short c = Byte.MAX_VALUE + 1;
//		byte d = (byte)(Byte.MAX_VALUE + 1);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
//		System.out.println(d);
//		System.out.println(c1);
//		System.out.println(d1);
//		int[][] r = solve(16);
//		System.out.println(r);
		
		//测试primitive类型
		primitiveTest();
		System.out.println(0xf);
		System.out.println(((Long)1000L) == 1000);//true
        System.out.println(((Long)1000L) == Long.valueOf(1000));//false
        System.out.println(new Long(1).equals(1L));//true
		System.out.println(1L == 1);
//		int[] a = {1,4,2,54,66,73,2,6};
//		sort(a);
//		for (int i : a) {
//			System.out.print(i+",");
//		}
	}
	
	/**
	 * 输入是一个十进制整数，输出是它的二进制表示的字符串
	 * @param a 十进制整数
	 * @return 二进制表示的字符串
	 */
	static String oct2Bin(int a) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (int i = 31; i >= 0; i--) {
			if((a & (1 << i)) !=0) {
				sb.append(1);
				flag = true;
			}else if(flag){
				sb.append(0);
			}
		}
		if(!flag){//針對數字0
			sb.append(0);
		}
		return sb.toString();
	}
	/**
	 * 进行数制的相互转换,输入字符串仅含0-9字符，不包括负数的转换
	 * @param s 原始的字符串
	 * @param radixSrc 源进制
	 * @param radixTgt 目标进制
	 * @return 目标进制字符串
	 */
	static String transform(String s, int radixSrc, int radixTgt) {
		int temp = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			//转换为十进制
			temp += (s.charAt(len-1-i) - '0')*Math.pow(radixSrc, i);
		}
		Stack<Integer> stack = new Stack<Integer>();
		while(temp!=0) {
			stack.push(temp%radixTgt);
			temp /= radixTgt;
		}
		//String s = new String(stack.size())没有此构造方法
		StringBuilder sb = new StringBuilder();
		while(stack.size()!=0) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
	/**
	 * 有一架天平，它有20个砝码，这20个砝码的重量分别为 1,3,9,27···3^19。
	 * 只要被称的物品的重量为位于区间 [1,(3^20−1)/2] 的整数，就可以使用这架天平进行称量。
	 * 假设物品一直放在天平的左边，现在给出每个物品的重量，请打印出称量的方案。输出格式为两组数字，
	 * 第一组代表天平左边要放的砝码，第二组代表天平右边要放的砝码。这两组数中间用空格隔开。
	 * 每一组内部的数使用逗号隔开。
	 * 如果天平的某一边不需要放砝码，那就打印 empty
	 * @param w 物品重量
	 * @return 天平两边需要放置砝码的重量
	 */
	static int[][] solve(int w) {
		int[][] result = new int[2][20];
		final int LEFT = 0, RIGHT = 1;
		int pl = 0, pr = 0;
		int r = 0;
		int addW = 1;
		while(w > 0) {
			r = w%3;
			if(r==2) {
				result[LEFT][pl++] = addW;
				//在右边增加一个addW（3^n）重的砝码，
				// 右边就相当于2*3^n+3^n = 1*3^(n+1)相当于将2消除。向高位移动
				w = (w+1)/3;
			}else if(r==1) {
				result[RIGHT][pr++] = addW;
				w = w/3;
			}else {
				w = w/3;
			}
			addW *= 3;
		}
		return result;
	}
	
	static void primitiveTest() {
		Integer a = new Integer(1);
        Integer b = Integer.valueOf(1);//返回一个表示1的integer对象，该方法有可能通过缓存经常请求的值而显著提高空间和时间性能
        Integer c = inc(0);
        Integer d = 1;
        System.out.println(a == b);  // false
        System.out.println(a.equals(b)); // true
        System.out.println(a == c); // false 
        System.out.println(a.equals(c)); // true

        System.out.println(a == d);  // false 
        System.out.println(a.equals(d)); // true

        System.out.println(b == c);  // true
        System.out.println(b.equals(c));  //true

        System.out.println(b == d);  // true
        System.out.println(b.equals(d)); // true

        System.out.println((((Long)1L) == 1)+"(Long)1L) == 1"); // true
        System.out.println(new Long(1).equals(1)); // false

        Long e = 100L;
        Long f = 100L;
//		public static Long valueOf(long l) {
//			final int offset = 128;
//			if (l >= -128 && l <= 127) { // will cache
//				return LongCache.cache[(int)l + offset];
//			}
//			return new Long(l);
//		}
        System.out.println(e == f);  // true
        e = 1000L;
        f = 1000L;
        System.out.println(e == f);  // false
	}
	
	public static Integer inc(Integer x) {
        return x + 1;
    }
	
	 public static void sort(int[] a)
	    {
	        int temp = 0;
	        for (int i = a.length - 1; i > 0; i--)
	        {
	            for (int j = 0; j < i; j++)
	            {
	                if (a[j + 1] < a[j])
	                {
	                    temp = a[j];
	                    a[j] = a[j + 1];
	                    a[j + 1] = temp;
	                }
	            }
	        }
	    }
}
