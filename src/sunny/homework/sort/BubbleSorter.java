package sunny.homework.sort;

/**
 * ð������(�ȶ���ʱ�临�Ӷ�O(n^2))
 * Created by sunny_hbqq on 2017/3/29.
 */
public class BubbleSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
        	boolean exchange = false; // �Ƿ�����˽���
			for (int j = 0; j < arr.length - i - 1; j++) {
				if(arr[j]>arr[j+1]) {// ÿ�ν������������������
					exchange = true;
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			if (!exchange) {// û�н���˵����������ֱ�ӷ���
				break;
			}
		}
    }
}
