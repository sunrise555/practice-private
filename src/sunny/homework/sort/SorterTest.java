package sunny.homework.sort;


/**
 * ������
 * Created by sunny_hbqq on 2017/3/29.
 */
public class SorterTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] arr = {1,4,2,6,5,7,8,3};
        Sorter sorter = new BubbleSorter();
        sorter.sort(arr);
        for (int i:arr)
            System.out.print(i + " ");

        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("����ʱ�䣺" + (end - start) + "ms");

    }
}
