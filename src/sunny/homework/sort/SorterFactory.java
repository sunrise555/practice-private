package sunny.homework.sort;

public class SorterFactory {
	public static Sorter getSorter(String sorterName) throws Exception {
		Sorter sorter = null;
		switch (sorterName) {
		case "Bubble":
			sorter = new BubbleSorter();
			break;
		case "Insert":
			sorter = new InsertSorter();
			break;
		case "Heap":
			sorter = new HeapSort();
			break;
		case "Quick":
			sorter = new QuickSorter();
			break;
		case "Merge":
			sorter = new MergeSorter();
			break;

		default:
			throw new Exception("类名不存在");
		}
		return sorter;
	}
}
