package sunny.homework.sort;
/**
 * 排序相关的题目的解法
 * @Created by Sunny on 2017年3月31日
 */
public class SorterExercise {
	
	public static void main(String[] args) {
		int[] arr = {2,3,1,4,5,8,9,10,6,7};
		arr = mDisSort(arr,3);
		for (int i : arr) {
			System.out.print(i + " ");
		}
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
}
