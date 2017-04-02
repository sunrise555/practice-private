package sunny.homework.sort;


/**
 * 排序相关的题目的解法
 * @Created by Sunny on 2017年3月31日
 */
public class SorterExercise {
	
	public static void main(String[] args) {
		int[] arr = {2,3,1,4,5,8,9,6,7};
		arr = mDisSort(arr,3);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
//		int[] midNum = getMidNum(arr);
//		for (int num:midNum) {
//			System.out.println(num);
//		}
		System.out.println(getCubeRoot(28));
	}
	/*有一个有序数组，长度为 n，将其打乱，但是每个数字离开原来的位置的距离不会超过m，
	 * 其中，m 远小于 n。请问，能否设计一个方法，使得数组重新有序，并且时间复杂度为O(mn)?*/
	public static int[] mDisSort(int arr[], int m) {
		// 分析：第一位的值必须位于离它最近的m个元素之中，找到后交换位置则第一位有序，递归第二位
		for (int i = 0; i < arr.length; i++) {
			int minPos = i;
			int pivot = arr[i];
			for (int j = i; j <= i + m; j++) {
				if (j<arr.length) {
					// 找到m个元素的最小值
					if (arr[j]<pivot) {
						pivot = arr[j];
						minPos = j;
					}
				}else
					break;
			}
			arr[minPos] = arr[i];
			arr[i] = pivot;
		}
		return arr;
	}
	
	/*使用一个最大堆和一个最小堆找出一个数列的中位数。
	 * 这个数列的长度是未定的，随时都有会新的数加进来，要求你设计的系统必须随时可以给出当前时刻的中位数*/
	// 解题思路：对arr先建立最大堆，再对结果建立最小堆，中位数即在数组中间
	public static int[] getMidNum(int[] arr) {
		HeapSort.Heap.buildUpMaxHeap(arr);
		HeapSort.Heap.buildUpMinHeap(arr);
		if ((arr.length & 1) != 0) {
			// 数组长度为奇数，中位数坐标
			int[] midNum = {arr[(arr.length - 1) >> 1]};
			return midNum;
		} else {
			int[] midNum = {arr[(arr.length - 1) >> 1],arr[((arr.length - 1) >> 1) + 1]};
			return midNum;
		}

	}

	/*
	 * 输入一个x，求出它的实数立方根
	 * x的立方根一定位于[k,x/(k*k)]区域内
	 * */
	static double getCubeRoot(double num) {
		double k = 1.0;

		while (Math.abs(num - k * k * k) > 1e-9) {
			k = (k + num / (k * k) ) / 2.0;
		}

		return k;

	}
}
