package sunny.homework.sort;
/**
 * ������ص���Ŀ�Ľⷨ
 * @Created by Sunny on 2017��3��31��
 */
public class SorterExercise {
	
	public static void main(String[] args) {
		int[] arr = {2,3,1,4,5,8,9,10,6,7};
		arr = mDisSort(arr,3);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
	/*��һ���������飬����Ϊ n��������ң�����ÿ�������뿪ԭ����λ�õľ��벻�ᳬ��m��
	 * ���У�m ԶС�� n�����ʣ��ܷ����һ��������ʹ�������������򣬲���ʱ�临�Ӷ�ΪO(mn)?*/
	public static int[] mDisSort(int arr[], int m) {
		// ��������һλ��ֵ����λ�����������m��Ԫ��֮�У��ҵ��󽻻�λ�����һλ���򣬵ݹ�ڶ�λ
		for (int i = 0; i < arr.length; i++) {
			int minPos = i;
			int pivot = arr[i];
			for (int j = i; j <= i + m; j++) {
				if (j<arr.length) {
					// �ҵ�m��Ԫ�ص���Сֵ
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
