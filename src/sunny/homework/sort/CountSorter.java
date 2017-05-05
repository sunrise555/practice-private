package sunny.homework.sort;

/**
 * �������򣺶���ÿһ��������Ԫ��a, �ҵ������������б�aС��Ԫ�صĸ���
 * ���Ӷȣ�O(n)  O(n)
 * �ȶ��ԣ��ȶ�
 * @Created by Sunny on 2017��5��4��
 */
public class CountSorter implements Sorter{
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,3,44,2,5,6,3,6,1,98};
		int[] arr2 = {1,2,3,4,3,44,2,5,6,3,6,1,98};
		CountSorter cs = new CountSorter();
		cs.sort(arr);
		cs.notStableSort(arr2);
		System.out.println("�ȶ���������");
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("���ȶ���������");
		for (int i : arr2) {
			System.out.print(i + " ");
		}
	}
	@Override
	public void sort(int[] arr) {
		int len = arr.length;
		// ����Ԫ��ֵ����С��MAX
		final int MAX = 256;
		
		// ���ֱ��ͼ���������C
		int[] c = new int[MAX];
		
		// ���������������B
		int[] b = new int[MAX];
		
		for (int i = 0; i < len; i++) {
			// ��¼ÿ��Ԫ�س��ֵĴ���
			c[arr[i]]++;
		}
		
		for (int i = 1; i < MAX; i++) {
			// �����ۻ�ֱ��ͼ
			c[i] += c[i-1];
		}
		
		// �ô�����ʹ��int i = 0; i < len; i++,��Ϊ���������Ԫ�ص�˳�������򣬲��ȶ�
		for (int i = len - 1; i >= 0; i--) {
			// ��ÿ���������Ԫ�ط�����ȷ��λ��
			b[--c[arr[i]]] = arr[i];
			// ��Ӧ���ۻ�ֵ��һ����֤������ȶ���
		}
		
		for (int i = 0; i < len; i++) {
			arr[i] = b[i];
		}		
	}
	
	// ֻʹ��һ������C�������������ȶ�
	public void notStableSort(int[] arr) {
		int len = arr.length;
		// ����Ԫ��ֵ����С��MAX
		final int MAX = 256;
		
		// ���ֱ��ͼ���������C
		int[] c = new int[MAX];
		
		for (int i = 0; i < len; i++) {
			// ��¼ÿ��Ԫ�س��ֵĴ���
			c[arr[i]]++;
		}
		
		for (int i = 1; i < MAX; i++) {
            c[i] += c[i-1];
        }
		
		int j = 0;
		// ������C���б���
		for (int i = 1; i < MAX; i++) {
			int diff = c[i] - c[i-1];
			if(c[i]!=0 && diff!=0) {
				while(diff!=0){
					arr[j] = i;
					j++;
					diff--;
				}
			}
            
        }
	}

}
