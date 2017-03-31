package sunny.homework.sort;

/**
 * ��������(�ȶ���ʱ�临�Ӷ����ΪO(n^2))
 * ������������飬ʱ�临�Ӷ���O(n)
 * ѭ����������������ĳ�ʼ˳���й�
 * @Created by Sunny on 2017��3��31��
 */
public class InsertSorter implements Sorter{

	@Override
	public void sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			// �ڱ����洢ÿ�δ������Ԫ��
            int pivot = arr[i];
            // �ڱ���ߵ�����
            int j = i - 1;
            for (; j >= 0; j--) {
                if (pivot < arr[j]) {
                	// �������ֵС�����Ԫ�أ����Ԫ������һλ
                    arr[j+1] = arr[j];
                }
                else {
                    break;
                }
            }
            // ���ڱ�������ȷ��λ��
            arr[j+1] = pivot;
        }
    }		

}
