package sunny.homework.sort;


/**
 * ������ص���Ŀ�Ľⷨ
 * @Created by Sunny on 2017��3��31��
 */
public class SorterExercise {
	
	public static void main(String[] args) {
		int[] arr = {2,3,1,4,5,8,9,6,7};
//		arr = mDisSort(arr,3);
//		for (int i : arr) {
//			System.out.print(i + " ");
//		}
//		System.out.println();
		int[] midNum = getMidNum(arr);
//		for (int num:midNum) {
//			System.out.println(num);
//		}
		System.out.println(getCubeRoot(28));
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
	
	/*ʹ��һ�����Ѻ�һ����С���ҳ�һ�����е���λ����
	 * ������еĳ�����δ���ģ���ʱ���л��µ����ӽ�����Ҫ������Ƶ�ϵͳ������ʱ���Ը�����ǰʱ�̵���λ��*/
	// ����˼·����arr�Ƚ������ѣ��ٶԽ��������С�ѣ���λ�����������м�
	public static int[] getMidNum(int[] arr) {
		HeapSort.Heap.buildUpMaxHeap(arr);
		HeapSort.Heap.buildUpMinHeap(arr);
		if ((arr.length & 1) != 0) {
			// ���鳤��Ϊ��������λ������
			int[] midNum = {arr[(arr.length - 1) >> 1]};
			return midNum;
		} else {
			int[] midNum = {arr[(arr.length - 1) >> 1],arr[((arr.length - 1) >> 1) + 1]};
			return midNum;
		}

	}

	/*
	 * ţ�ٵ�����
	 * ����һ��x���������ʵ��������
	 * x��������һ��λ��[k,x/(k*k)]������
	 *
	 */
	static double getCubeRoot(double num) {
		double k = 1.0;

		while (Math.abs(num - k * k * k) > 1e-9) {
			k = (k + num / (k * k) ) / 2.0;
		}

		return k;

	}

	/*
	 * ���ַ���������������ֵ
	 * ��N�����ӣ����ǵĳ��ȷֱ�Li�ġ������ǵ����г�K���ȳ������ӣ�������������ж೤
	 */
	public double findCondition(int n, int k, double[] l) {
		double high = Double.MAX_VALUE, low = 0.0;
		final int MAX_ITER = 100;

		for (int i = 0; i < MAX_ITER; i++)
		{
			double mid = (high + low) / 2;
			if (meetCondition(n, k, l, mid))
				low = mid;
			else
				high = mid;
		}

		return high;
	}

	public boolean meetCondition(int N, int K, double[] L, double mid) {
		int num = 0;
		for (int i = 0; i < N; i++)
			num += (int)(L[i] / mid); // ��ǰK�����ĳ���λmid
		// ������С��K����˵��mid����Ӧ��high=mid
		return num >= K;
	}
}
