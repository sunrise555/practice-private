package sunny.homework.sort;

/**
 * ≤‚ ‘¿‡
 * Created by sunny_hbqq on 2017/3/29.
 */
public class SorterTest {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //int[] arr = {1,4,2,6,5,7,8,3};
        int[] arr = {2,1,3,4,5,6,7,8};
        //Sorter sorter = SorterFactory.getSorter("Insert");
        Sorter sorter = SorterFactory.getSorter("Bubble");
        sorter.sort(arr);
        for (int i:arr)
            System.out.print(i + " ");

        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("≈≈–Ú ±º‰£∫" + (end - start) + "ms");

    }
}
