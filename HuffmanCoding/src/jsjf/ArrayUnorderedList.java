package jsjf;
import java.util.Iterator;
import jsjf.exceptions.*;

//public class UnorderedArrayList<T> extends ArrayList<T> implements UnorderedListADT{
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T>{
	
	public ArrayUnorderedList() {
		super();
	}
	
	public ArrayUnorderedList(int initialCapacity) {
		super(initialCapacity);
	}
	
	public void addAfter(T element, T target) {
		if(size() == list.length)
			expandCapacity();
		
		int scan = 0;
		
		while(scan < rear && !target.equals(list[scan]))
			scan ++;
		
		if(scan == rear)
			throw new ElementNotFoundException("UnorderedList");
		
		scan ++;
		
		for(int shift = rear; shift > scan; shift--)
			list[shift] = list[shift - 1];
		
		list[scan] = element;
		rear ++;
		modCount ++;
	}

	@Override
	public Iterator<T> interator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToFront(T element) {
		if(size() == list.length) {
			expandCapacity();
		}
		
		for(int scan = rear; scan > 0; scan --) {
			list[scan] = list[scan - 1];
		}
		
		list[0] = element;
		rear++;
	}

	@Override
	public void addToRear(T element) {
		if(size() == list.length) {
			expandCapacity();
		}
		
		list[rear] = element;
		rear ++;
	}
	
	public static void main(String[] args) {
		ArrayUnorderedList<Integer> list = new ArrayUnorderedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.addToFront(5);
		
	}
	
}
