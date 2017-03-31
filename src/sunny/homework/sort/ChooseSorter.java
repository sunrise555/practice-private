package sunny.homework.sort;

/**
 * 选择算法(贪心思想：O(n^2))
 * @Created by Sunny on 2017年3月31日
 */
public class ChooseSorter implements Sorter{

	@Override
	public void sort(int[] arr) {
		int pivot, pos = -1;

        for (int i = 0; i < arr.length; i++) {
            pivot = Integer.MAX_VALUE;
            // 每次找到最小值
            for (int j = i; j < arr.length; j++) {
                if (pivot > arr[j]) {
                    pivot = arr[j];
                    pos = j;
                }
            }
            // 将最小值放在第一位
            arr[pos] = arr[i];
            arr[i] = pivot;
        }		
	}

}
