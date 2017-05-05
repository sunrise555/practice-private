package sunny.homework.sort;

import static java.lang.Math.*;

/**
 * 基数排序：分治思想，分趟排序，对于n^2则分两次排序，每次得到的radix范围为[0,n]，对于n^3则分3次排序
 * 实现：有n个数，范围在0~n^2中，对这组数进行排序
 * @Created by Sunny on 2017年5月5日
 */
public class RadixSorter implements Sorter{
	
	public static void main(String[] args) {
		int[] a = {1,21,67,77848,34657,567,21,5,76,35674,34};
		RadixSorter rs = new RadixSorter(100,3);
		rs.sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}
	private int n; 
	private int power;
	
	public RadixSorter() {
	}
	public RadixSorter(int n, int power) {
		this.n = n;
		this.power = power;
	}
	@Override
	public void sort(int[] a) {
		int[] c = new int[n];
        int[] remainder = new int[a.length];
        int[] b = new int[a.length];

        for (int i = 0; i < power; i++) {
            for (int j = 0; j < a.length; j++) {
                int temp = getRadix(a[j], i, n);
                remainder[j] = temp;
                c[temp]++;
            }

            // 对得到的radix进行计数排序，保证排序的稳定性
            for (int k = 1; k < n; k++) {
                c[k] += c[k - 1];
            }

            for (int j = a.length - 1; j >= 0; j--) {
                b[--c[remainder[j]]] = a[j];
            }

            for (int j = 0; j < n; j++) {
                c[j] = 0;
            }

            for (int j = 0; j < a.length; j++) {
                a[j] = b[j];
            }
        }
	}
	
	// 获取当前基数
	private int getRadix(int a, int i, int n) {
		int radix = ((int) (a /pow(n, i))) % n;
		return radix;
	}

}
