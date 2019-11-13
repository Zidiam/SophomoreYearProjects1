/**
 * Sorting demonstrates sorting and searching on an array 
 * of objects.
 *
 * @author Java Foundations
 * @version 4.0 
 */
public class Sorting 
{
	/**
	 * Sorts the specified array of integers using the selection
	 * sort algorithm.
	 *
	 * @param data the array to be sorted
	 */
	
	private static int comparedSS = 0;
	private static int comparedIS = 0;
	private static int comparedBS = 0;
	private static int comparedQS = 0;
	private static int comparedMS = 0;
	
	public static int getComparedSS(){
		return comparedSS;
	}
	
	public static int getComparedIS(){
		return comparedIS;
	}
	
	public static int getComparedBS(){
		return comparedBS;
	}
	
	public static int getComparedQS(){
		return comparedQS;
	}
	
	public static int getComparedMS(){
		return comparedMS;
	}
	
	public static void resetInts() {
		comparedSS = 0;
		comparedIS = 0;
		comparedQS = 0;
		comparedBS = 0;
		comparedMS = 0;
	}
	
	public static <T extends Comparable<T>> 
	Integer[] selectionSort(Integer[] data)
	{
		int min;

		for (int index = 0; index < data.length - 1; index++)
		{
			min = index;
			for (int scan = index + 1; scan < data.length; scan++) {
				comparedSS ++;
				if (data[scan].compareTo(data[min]) < 0) {
					min = scan;
				}
			}
			swap(data, min, index);
		}
		
		return data;
	}

	/**
	 * Swaps to elements in an array. Used by various sorting algorithms.
	 * 
	 * @param data   the array in which the elements are swapped
	 * @param index1 the index of the first element to be swapped
	 * @param index2 the index of the second element to be swapped
	 */
	private static <T extends Comparable<T>> 
	void swap(T[] data, int index1, int index2)
	{
		T temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}

	/**
	 * Sorts the specified array of objects using an insertion
	 * sort algorithm.
	 *
	 * @param data the array to be sorted
	 * @return 
	 */
	public static <T extends Comparable<T>> 
	Integer[] insertionSort(Integer[] data)
	{
		for (int index = 1; index < data.length; index++)
		{
			Integer key = data[index];
			int position = index;

			// shift larger values to the right 
			while (position > 0 && data[position-1].compareTo(key) > 0)
			{
				comparedIS ++;
				data[position] = data[position - 1];
				position--;
			}
			comparedIS ++;

			data[position] = key;
		}
		
		return data;
	}

	/**
	 * Sorts the specified array of objects using a bubble sort
	 * algorithm.
	 *
	 * @param data the array to be sorted
	 * @return 
	 */
	public static <T extends Comparable<T>> 
	Integer[] bubbleSort(Integer[] data)
	{
		int position, scan;

		for (position =  data.length - 1; position >= 0; position--)
		{
			for (scan = 0; scan <= position - 1; scan++)
			{
				comparedBS ++;
				if (data[scan].compareTo(data[scan + 1]) > 0) {
					swap(data, scan, scan + 1);
				}
			}
		}
		return data;
	}

	/**
	 * Sorts the specified array of objects using the quick sort algorithm.
	 * 
	 * @param data the array to be sorted
	 * @return 
	 */
	public static <T extends Comparable<T>> 
	Integer[] quickSort(Integer[] data)
	{
		quickSort(data, 0, data.length - 1);
		return data;
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * quick sort algorithm. 
	 * 
	 * @param data the array to be sorted
	 * @param min  the minimum index in the range to be sorted
	 * @param max  the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> 
	void quickSort(Integer[] data, int min, int max)
	{
		if (min < max)
		{
			// create partitions
			int indexofpartition = partition(data, min, max);

			// sort the left partition (lower values)
			quickSort(data, min, indexofpartition - 1);

			// sort the right partition (higher values)
			quickSort(data, indexofpartition + 1, max);
		}
	}

	/**
	 * Used by the quick sort algorithm to find the partition.
	 * 
	 * @param data the array to be sorted
	 * @param min  the minimum index in the range to be sorted
	 * @param max  the maximum index in the range to be sorted
	 */
	private static <T extends Comparable<T>> 
	int partition(Integer[] data, int min, int max)
	{
		Integer partitionelement;
		int left, right;
		int middle = (min + max) / 2;

		// use the middle data value as the partition element
		partitionelement = data[middle];
		
		// move it out of the way for now
		swap(data, middle, min);

		left = min;
		right = max;

		while (left < right)
		{
			// search for an element that is > the partition element
			while (left < right && data[left].compareTo(partitionelement) <= 0) {
				comparedQS ++;
				left++;
			}
			comparedQS ++;

			// search for an element that is < the partition element
			while (data[right].compareTo(partitionelement) > 0) {
				comparedQS ++;
				right--;
			}
			comparedQS ++;
			// swap the elements
			if (left < right) {
				swap(data, left, right);
			}
		}

		// move the partition element into place
		swap(data, min, right);

		return right;
	}
	
	/**
	 * Sorts the specified array of objects using the merge sort
	 * algorithm.
	 *
	 * @param data the array to be sorted
	 * @return 
	 */
	public static <T extends Comparable<T>>
	Integer[] mergeSort(Integer[] data)
	{
		mergeSort(data, 0, data.length - 1);
		return data;
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * merge sort algorithm.
	 *
	 * @param data the array to be sorted
	 * @param min  the index of the first element 
	 * @param max  the index of the last element
	 */
	private static <T extends Comparable<T>>
	void mergeSort(Integer[] data, int min, int max)
	{
		if (min < max)
		{
			int mid = (min + max) / 2;
			mergeSort(data, min, mid);
			mergeSort(data, mid+1, max);
			merge(data, min, mid, max);
		}
	}

	/**
	 * Merges two sorted subarrays of the specified array.
	 *
	 * @param data the array to be sorted
	 * @param first the beginning index of the first subarray 
	 * @param mid the ending index fo the first subarray
	 * @param last the ending index of the second subarray
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<T>>
	void merge(Integer[] data, int first, int mid, int last)
	{
		Integer[] temp = new Integer[data.length];

		int first1 = first, last1 = mid;  // endpoints of first subarray
		int first2 = mid + 1, last2 = last;  // endpoints of second subarray
		int index = first1;  // next index open in temp array

		//  Copy smaller item from each subarray into temp until one
		//  of the subarrays is exhausted
		while (first1 <= last1 && first2 <= last2)
		{
			if (data[first1].compareTo(data[first2]) < 0)
			{
				comparedMS++;
				temp[index] = data[first1];
				first1++;
			}
			else
			{
				comparedMS++;
				temp[index] = data[first2];
				first2++;
			}
			index++;
		}

		//  Copy remaining elements from first subarray, if any
		while (first1 <= last1)
		{
			temp[index] = data[first1];
			first1++;
			index++;
		}

		//  Copy remaining elements from second subarray, if any
		while (first2 <= last2)
		{
			temp[index] = data[first2];
			first2++;
			index++;
		}

		//  Copy merged data into original array
		for (index = first; index <= last; index++)
			data[index] = temp[index];
	}

}

