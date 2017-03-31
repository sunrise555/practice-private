package sunny.homework.sort;

/**
 * 插入排序(稳定、时间复杂度最大为O(n^2))
 * 对于有序的数组，时间复杂度是O(n)
 * 循环次数与输入数组的初始顺序有关
 * @Created by Sunny on 2017年3月31日
 */
public class InsertSorter implements Sorter{

	@Override
	public void sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			// 哨兵：存储每次待插入的元素
            int pivot = arr[i];
            // 哨兵左边的序列
            int j = i - 1;
            for (; j >= 0; j--) {
                if (pivot < arr[j]) {
                	// 若插入的值小于左边元素，左边元素右移一位
                    arr[j+1] = arr[j];
                }
                else {
                    break;
                }
            }
            // 将哨兵插入正确的位置
            arr[j+1] = pivot;
        }
    }		

}
