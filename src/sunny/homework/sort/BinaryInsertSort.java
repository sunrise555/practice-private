package sunny.homework.sort;

/**
 *
 * 二分法排序()折半插入排序
 * Created by sunny on 2018/3/21
 */
public class BinaryInsertSort {

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int start = 0;
            int end = i - 1;
            int middle = 0;
            int temp = array[i];
            while (start <= end) {
                middle = (start + end) / 2;

                if (array[middle] > temp)//要排序元素在已经排过序的数组左边
                {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            }
            for (int j = i - 1; j > end; j--)//找到了要插入的位置，然后将这个位置以后的所有元素向后移动
            {
                array[j + 1] = array[j];
            }

            array[end + 1] = temp;

        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,3,5,2,6,8,7};
        BinaryInsertSort.sort(arr);
    }
}
