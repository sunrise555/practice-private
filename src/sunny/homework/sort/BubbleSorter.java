package sunny.homework.sort;

/**
 * 冒泡排序(稳定、时间复杂度O(n^2))
 * Created by sunny_hbqq on 2017/3/29.
 */
public class BubbleSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
        	boolean exchange = false; // 是否进行了交换
			for (int j = 0; j < arr.length - i - 1; j++) {
				if(arr[j]>arr[j+1]) {// 每次将最大的数浮到数组最后
					exchange = true;
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			if (!exchange) {// 没有交换说明数组有序，直接返回
				break;
			}
		}
    }
}
