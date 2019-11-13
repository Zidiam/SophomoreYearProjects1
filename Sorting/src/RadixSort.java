import java.util.Queue;
import java.util.LinkedList;

/**
 * RadixSort driver demonstrates the use of queues in the execution of a radix sort.
 *
 * @author Java Foundations
 * @version 4.0
 */
public class RadixSort     
{
	/**
	 * Performs a radix sort on a set of numeric values.
	 */
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{ 
		int[] list = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		String temp;
		Integer numObj;
		int digit, num;

		// Queue is an interface that is implemented by LinkedList--we declare digitQueues as an 
		// array of Queues because we don't want the rest of the List behavior
		
		// the <?> is necessary because we're creating an array--that process is mostly worked out by
		// the compiler, but technically the type of the element isn't worked out until runtime.
		
		Queue<Integer>[] digitQueues = (LinkedList<Integer>[])(new LinkedList<?>[10]);
		for (int digitVal = 0; digitVal <= 9; digitVal++)
			digitQueues[digitVal] = (Queue<Integer>)(new LinkedList<Integer>());

		// sort the list
		for (int position = 0; position <= 3; position++)
		{
			for (int scan = 0; scan < list.length; scan++)
			{
				temp = String.valueOf(list[scan]);
				digit = Character.digit(temp.charAt(3-position), 10);
				digitQueues[digit].add(list[scan]);
			}

			// gather numbers back into list
			num = 0;
			for (int digitVal = 0; digitVal <= 9; digitVal++)
			{
				while (!(digitQueues[digitVal].isEmpty()))
				{
					numObj = digitQueues[digitVal].remove();
					list[num] = numObj.intValue();
					num++;
				}
			}
		}

		// output the sorted list
		for (int scan = 0; scan < list.length; scan++)
			System.out.println(list[scan]);
	}
}

