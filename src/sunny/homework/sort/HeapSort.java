package sunny.homework.sort;

/**
 * ������(���ȶ���ʱ�临�Ӷ�O(nlogn),�ռ临�Ӷ�O(1))
 * @Created by Sunny on 2017��4��1��
 */
public class HeapSort implements Sorter {
	
	@Override
	public void sort(int[] arr) {
		Heap.buildUpHeap(arr);
		
		// ÿ��ȡ���Ѷ�Ԫ�أ������һ���ڵ㽻��
		for (int i = arr.length - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			// �ԶѶ����е���
			Heap.maxHeapify(arr, i, 0);
		}
	}
	
	// Ƕ���ࣺ�� O(lgn)
	static class Heap{
		// �������ѣ�root����������������ѵ�����
		static void maxHeapify(int[] arr, int length, int root) {
			if(root > length)
				return;
			
			int largerPos = root;
			int leftPos = 2 * root + 1;// �����±�
			int rightPos = 2 * root + 2;
			
			// �ҵ����������нϴ��ֵ
			if (leftPos < length && arr[leftPos] > arr[largerPos])
				largerPos = leftPos;
			
			if (rightPos < length && arr[rightPos] >arr[largerPos])
				largerPos = rightPos;
			if (root != largerPos) {
				// ����ڵ㽻��
				int temp = arr[root];
				arr[root] = arr[largerPos];
				arr[largerPos] = temp;
				
				// ��largerPos��Ϊ�µ�root���ݹ�
				maxHeapify(arr, length, largerPos);
			}
		}
		
		// �Ե����Ͻ��� O(n)
		static void buildUpHeap(int[] arr) {
			if (arr.length < 1)
				return;
			
			// ���һ����Ҷ�ӽڵ�lastNotLeaf�����꣺�����һ���ڵ�ĸ���
			int lastNotLeaf = (arr.length - 2) / 2;
			
			while (lastNotLeaf >=0) {
				maxHeapify(arr, arr.length, lastNotLeaf);
				lastNotLeaf--;
			}
		}
		
		public static void main(String[] args) {
			int[] arr = {1,4,2,6,5,7,8,3};
			Sorter  sorter = new HeapSort();
			sorter.sort(arr);
			for (int i : arr) {
				System.out.print(i + " ");
			}
		}
	}
	

}

