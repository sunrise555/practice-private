package sunny.homework.sort;

/**
 * 计数排序：对于每一个待排序元素a, 找到待排序数组中比a小的元素的个数
 * 复杂度：O(n)  O(n)
 * 稳定性：稳定
 * @Created by Sunny on 2017年5月4日
 */
public class CountSorter implements Sorter{
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,3,44,2,5,6,3,6,1,98};
		int[] arr2 = {1,2,3,4,3,44,2,5,6,3,6,1,98};
		CountSorter cs = new CountSorter();
		cs.sort(arr);
		cs.notStableSort(arr2);
		System.out.println("稳定排序结果：");
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("不稳定排序结果：");
		for (int i : arr2) {
			System.out.print(i + " ");
		}
	}
	@Override
	public void sort(int[] arr) {
		int len = arr.length;
		// 排序元素值必须小于MAX
		final int MAX = 256;
		
		// 存放直方图结果的数组C
		int[] c = new int[MAX];
		
		// 存放排序结果的数组B
		int[] b = new int[MAX];
		
		for (int i = 0; i < len; i++) {
			// 记录每个元素出现的次数
			c[arr[i]]++;
		}
		
		for (int i = 1; i < MAX; i++) {
			// 计算累积直方图
			c[i] += c[i-1];
		}
		
		// 该处不能使用int i = 0; i < len; i++,因为这样会相等元素的顺序变成逆序，不稳定
		for (int i = len - 1; i >= 0; i--) {
			// 将每个待排序的元素放置正确的位置
			b[--c[arr[i]]] = arr[i];
			// 对应的累积值减一，保证排序的稳定性
		}
		
		for (int i = 0; i < len; i++) {
			arr[i] = b[i];
		}		
	}
	
	// 只使用一个数组C进行排序，排序不稳定
	public void notStableSort(int[] arr) {
		int len = arr.length;
		// 排序元素值必须小于MAX
		final int MAX = 256;
		
		// 存放直方图结果的数组C
		int[] c = new int[MAX];
		
		for (int i = 0; i < len; i++) {
			// 记录每个元素出现的次数
			c[arr[i]]++;
		}
		
		for (int i = 1; i < MAX; i++) {
            c[i] += c[i-1];
        }
		
		int j = 0;
		// 对数组C进行遍历
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
