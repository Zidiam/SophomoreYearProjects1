package jsjf;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import jsjf.exceptions.*;

public class ArrayList<T> implements ListADT<T>, Iterable<T>{
	private final static int DEFAULT_CAPACITY = 100;
	private final static int NOT_FOUND = -1;
	protected int rear;
	protected T[] list;
	protected int modCount;
	
	public ArrayList() {
		rear = 0;
		list = (T []) new Object[DEFAULT_CAPACITY];
	}
	
	public ArrayList(int capacity) {
		rear = 0;
		list = (T []) new Object[capacity];
		modCount = 0;
	}
	

	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	public void add(T element) {
		if(size() == list.length)
			expandCapacity();
		
		list[rear] = element;
		rear ++;
		modCount++;
		
	}
	
	public void add(T element, int pos) {
		if(size() == list.length)
			expandCapacity();
		
		for(int i = rear; i > pos; i--) {
			list[i] = list[i - 1];
		}
		
		list[pos] = element;
		rear ++;
		modCount++;
		
	}

	protected void expandCapacity() {
		T[] temp =  (T []) new Object[size() * 2];
		for(int x = 0; x < list.length; x++){
				temp[x] = list[x];
		}
		list = temp;
		
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}


	public T next() {
		// TODO Auto-generated method stub
		return null;
	}


	public T removeFirst() {
		if(isEmpty()) {
			throw new EmptyCollectionException("ArrayList");
		}
		
		T element = list[0];
		rear --;
		
		for(int scan = 0; scan < rear; scan++) {
			list[scan] = list[scan+1];
		}
		
		list[rear] = null;
		
		return element;
		
	}


	public T removeLast() {
		T element;
		
		if(isEmpty()) {
			throw new EmptyCollectionException("ArrayList");
		}
		
		rear--;
		element = list[rear];
		list[rear] = null;
		
		return element;
	}


	public T remove(T element) {
		T result;
		int index = find(element);
		if(index == NOT_FOUND) {
			throw new ElementNotFoundException("ArrayList");
		}
		
		result = list[index];
		rear--;
		
		for(int scan = index; scan < rear; scan++)
			list[scan] = list[scan + 1];
		
		list[rear] = null;
		modCount ++;
		return result;
	}
	
	private int find(T element) {
		int scan = 0;
		int result = NOT_FOUND;
		boolean found = false;
		if(!isEmpty()) {
			while(!found && scan < rear) {
				if(element.equals(list[scan]))
					found = true;
				else
					scan ++;
			}
		}
		
		if(found) {
			result = scan;
		}
		
		return result;
	}


	public T first() {
		if(isEmpty())
			throw new ElementNotFoundException("t");
		return list[0];
	}


	public T last() {
		if(isEmpty())
			throw new ElementNotFoundException("t");
		return list[rear -1];
	}


	public boolean contains(T element) {
		return find(element) != NOT_FOUND;
	}


	public boolean isEmpty() {
		return rear == 0;
	}


	public int size() {
		return rear;
	}


	private class ArrayListIterator implements Iterator<T> {
		int itModCount;
		int currentDex;
		
		public ArrayListIterator() {
			itModCount = modCount;
			currentDex = 0;
		}
		
		public boolean hasNext() {
			if(itModCount != modCount)
					throw new ConcurrentModificationException();
			return currentDex < rear;
		}
		
		public T next() {
			if(itModCount != modCount)
				throw new ConcurrentModificationException();
			currentDex ++;
			return (T) list[currentDex - 1];
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


	@Override
	public Iterator<T> interator() {
		return new ArrayIterator<T>(list, rear);
	}
	
	public String toString() {
		String result = "";
		for(int x = 0; x < rear; x++) {
			result = result + list[x].toString() + " ";
		}
		
		return result;
	}
	
}
