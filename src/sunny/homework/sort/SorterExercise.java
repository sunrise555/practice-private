package sunny.homework.sort;
/**
 * ������ص���Ŀ�Ľⷨ
 * @Created by Sunny on 2017��3��31��
 */
public class SorterExercise {
	/*��һ���������飬����Ϊ n��������ң�����ÿ�������뿪ԭ����λ�õľ��벻�ᳬ��m��
	 * ���У�m ԶС�� n�����ʣ��ܷ����һ��������ʹ�������������򣬲���ʱ�临�Ӷ�ΪO(mn)?*/
	public void demo01(int arr[], int m) {
		// ��������һλ��ֵ����λ�����������m��Ԫ��֮�У��ҵ��󽻻�λ�����һλ���򣬵ݹ�ڶ�λ
		for (int i = 0; i < arr.length; i++) {
			int minPos = i;
			int pivot = arr[i];
			for (int j = i; j <= i + m; j++) {
				// �ҵ�m��Ԫ�ص���Сֵ
				if (arr[j]<pivot) {
					pivot = arr[j];
					minPos = j;
				}
			}
			arr[minPos] = arr[i];
			arr[i] = pivot;
		}
	}
}
