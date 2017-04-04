package sunny.homework.sort;

/**
 * �������򣺲��ȶ�/ԭ���û���ʱ�临�Ӷ�O(N logN)
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
     * ��������
     * @param arr ����������
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
        // ѡȡ�������ұ�Ԫ����Ϊ�ֽ��
        int pivot = arr[end];
        int i = begin;
        int j = end;
        while (i!=j) {
            // �ҵ�����pivot��Ԫ�ص����꣬��i=jʱ˵��С��pivot��Ԫ�ض���ͬһ��
            while (i != j && arr[i] < pivot)
                i++;
            // �ҵ�С��pivot��Ԫ�ص����꣬��i=jʱ˵������pivot��Ԫ�ض���ͬһ��
            while (j != i && arr[j] >= pivot)
                j--;

            // ����
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // ��pivot�������м�
        int temp  = arr[end];
        arr[end] = arr[i];
        arr[i] = temp;
        return i;
    }
}
