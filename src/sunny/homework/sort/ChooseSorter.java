package sunny.homework.sort;

/**
 * ѡ���㷨(̰��˼�룺O(n^2))
 * @Created by Sunny on 2017��3��31��
 */
public class ChooseSorter implements Sorter{

	@Override
	public void sort(int[] arr) {
		int pivot, pos = -1;

        for (int i = 0; i < arr.length; i++) {
            pivot = Integer.MAX_VALUE;
            // ÿ���ҵ���Сֵ
            for (int j = i; j < arr.length; j++) {
                if (pivot > arr[j]) {
                    pivot = arr[j];
                    pos = j;
                }
            }
            // ����Сֵ���ڵ�һλ
            arr[pos] = arr[i];
            arr[i] = pivot;
        }		
	}

}
