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

		default:
			throw new Exception("����������");
		}
		return sorter;
	}
}
