package sunny.homework.sort;

/**
 * 快速排序：不稳定/原地置换，时间复杂度O(N logN)
 * Created by Sunny on 2017/4/4.
 */
public class QuickSorter implements Sorter {

    public static void main(String[] args) {
        Sorter s = new QuickSorter();
        int[] arr = {38,65,97,76,13,27,49};
        s.sort(arr);
        for (int i:arr) {
            System.out.print(i + " ");
        }
    }
    /**
     * 快速排序
     * @param arr 待排序数组
     */
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int begin, int end) {
        if (begin >= end)
            return;

        int k = partition(arr, begin, end);
        quickSort(arr, begin, k-1);
        quickSort(arr, k + 1, end);

    }

    private int partition(int[] arr, int begin, int end) {
        // 选取数组最右边元素最为分界点
        int pivot = arr[end];
        int i = begin;
        int j = end;
        while (i!=j) {
            // 找到大于pivot的元素的坐标，当i=j时说明小于pivot的元素都在同一边
            while (i != j && arr[i] < pivot)
                i++;
            // 找到小于pivot的元素的坐标，当i=j时说明大于pivot的元素都在同一边
            while (j != i && arr[j] >= pivot)
                j--;

            // 交换
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // 将pivot交换到中间
        int temp  = arr[end];
        arr[end] = arr[i];
        arr[i] = temp;
        return i;
    }
}
