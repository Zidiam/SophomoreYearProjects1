import jsjf.LinkedBinaryTree;

/*
 * HuffElement.java -- An element that holds a name and value
 * Jason Melnik
 * 10/20/2019
 * 
 */
public class HuffElement implements Comparable{
	String name;
	int value;
	
	/**
	 * This creates a HuffElement with a name and value
	 * @param name is a String that identifies the name of this object
	 * @param value is an int that identifies the recurrence  of name
	 */
	public HuffElement(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public HuffElement(LinkedBinaryTree<HuffElement> first, LinkedBinaryTree<HuffElement> second) {
		combineTree(first, second);
	}
	
	/**
	 * The value of this object is important because it is the amount of recurrence it has.
	 * @return a integer value of this object
	 * 
	 */
	public Integer getValue() {
		return value;
	}
	
	/**
	 * The name of the object which is the letter or string
	 * @return a string that identifies what letter or string this object is called
	 * 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This is a way to identify this object(by its name and value)
	 * @return a string that contains the value and name of this object
	 * 
	 */
	public String toString() {
		return "Value: " + value + " Name: " + name;
	}
	
	/**
	 * This method combines two HuffElements and makes this object into the new combination.
	 * This method combines first and seconds name and value.
	 * @param first the LinkedBinaryTree<HuffElement> which is used to get the HuffElement to get combined with second
	 * @param second the LinkedBinaryTree<HuffElement> which is used to get the HuffElement to get combined with first
	 * 
	 */
	public void combineTree(LinkedBinaryTree<HuffElement> first, LinkedBinaryTree<HuffElement> second) {
		String name = first.getRootElement().getName() + second.getRootElement().getName();
		int value = first.getRootElement().getValue() + second.getRootElement().getValue();
		
		this.name = name;
		this.value = value;
	}

	/**
	 * This is a comparable method which compares two HuffElements by their value.
	 * @param obj is an Object which would need to be a HuffElement and will be used to compare to this object
	 * @return an int value that is either positive, 0, or negative depending on how similar two objects are
	 * 
	 */
	@Override
	public int compareTo(Object obj) {
		HuffElement huff = (HuffElement) obj;
		if(this.value == huff.value) {
			return 0;
		}
		if(this.value > huff.value) {
			return this.value - huff.value;
		}
		else {
			return this.value - huff.value;
		}
	}

}
