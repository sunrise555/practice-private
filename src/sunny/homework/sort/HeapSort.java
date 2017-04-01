package sunny.homework.sort;

/**
 * 堆排序(不稳定，时间复杂度O(nlogn),空间复杂度O(1))
 * @Created by Sunny on 2017年4月1日
 */
public class HeapSort implements Sorter {
	
	@Override
	public void sort(int[] arr) {
		Heap.buildUpHeap(arr);
		
		// 每次取出堆顶元素，与最后一个节点交换
		for (int i = arr.length - 1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			// 对堆顶进行调整
			Heap.maxHeapify(arr, i, 0);
		}
	}
	
	// 嵌套类：堆 O(lgn)
	static class Heap{
		// 调整最大堆，root的左右子树均满足堆的性质
		static void maxHeapify(int[] arr, int length, int root) {
			if(root > length)
				return;
			
			int largerPos = root;
			int leftPos = 2 * root + 1;// 左孩子下标
			int rightPos = 2 * root + 2;
			
			// 找到左右子树中较大的值
			if (leftPos < length && arr[leftPos] > arr[largerPos])
				largerPos = leftPos;
			
			if (rightPos < length && arr[rightPos] >arr[largerPos])
				largerPos = rightPos;
			if (root != largerPos) {
				// 与根节点交换
				int temp = arr[root];
				arr[root] = arr[largerPos];
				arr[largerPos] = temp;
				
				// 将largerPos作为新的root，递归
				maxHeapify(arr, length, largerPos);
			}
		}
		
		// 自底向上建堆 O(n)
		static void buildUpHeap(int[] arr) {
			if (arr.length < 1)
				return;
			
			// 最后一个非叶子节点lastNotLeaf的坐标：是最后一个节点的父亲
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

