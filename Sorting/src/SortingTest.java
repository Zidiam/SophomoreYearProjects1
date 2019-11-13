import java.util.Random;

/**
 * SortPhoneList driver for testing an object sort.
 *
 * @author Java Foundations
 * @version 4.0
 */
public class SortingTest
{
	/**
	 * Creates an array of Contact objects, sorts them, then prints
	 * them.
	 */
	private static Random rand = new Random();
	
	
	public static Integer[] randomInts(int ammount, int start, int end) {
		Integer[] temp = new Integer[ammount];
		
		for(int x = 0; x < temp.length; x++) {
			temp[x] = (rand.nextInt(end+1) + start);
		}
		
		return temp;
	}
	
	public static void main(String[] args)
	{
		int ammount = 100;
		int start = 0;
		int end = 100;
		
		Integer[] numbers = randomInts(ammount, start, end);

		Sorting.quickSort(numbers);

		for (Integer x : numbers)
			System.out.print(x + ", ");
	}
}

