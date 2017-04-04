package sunny.homework.sort;

/**
 * 归并排序(稳定，时间复杂度O(nlogn),空间复杂度O(n))
 * Created by sunny on 2017/4/4.
 */
public class MergeSorter implements Sorter {
    public static void main(String[] args) {
        Sorter s = new MergeSorter();
        int[] arr = {38,65,97,65,76,13,27,49};
        s.sort(arr);
        for (int i: arr) {
            System.out.print(i + " ");
        }
    }
    private int[] arr2;
    @Override
    public void sort(int[] arr) {
        arr2 = new int[arr.length];
        mergeSort(arr,0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            // 分治
            mergeSort(arr, begin,mid);
            mergeSort(arr, mid + 1, end);
            // 归并
            merge(arr, begin, mid, end);
        }
    }

    private void merge(int[] arr1, int low, int mid, int high) {
        int i = low, j = mid + 1, k = low;
        // 将两个数组中较小的元素依次插入
        while (i <= mid && j <= high) {
            if (arr1[i] <= arr1[j])
                arr2[k++] = arr1[i++];
            else
                arr2[k++] = arr1[j++];
        }
        // 左边未遍历完，直接将剩下的值复制到新数组
        while (i <= mid)
            arr2[k++] = arr1[i++];
        // 右边未遍历完，直接将剩下的值复制到新数组
        while (j <= high)
            arr2[k++] = arr1[j++];

        for (i = low; i <= high; i++) {
            arr1[i] = arr2[i];
        }
    }
}
