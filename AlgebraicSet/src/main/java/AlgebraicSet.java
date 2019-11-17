import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * AlgebraicSet.java -- Can take the intersection, union, and difference of two Sets
 * Jason Melnik
 * 11/16/2019
 */
public class AlgebraicSet<T> implements Set<T>{
	Set<T> thisSet;
	
	/**
	 * Creates a AlgebraicSet object that takes in a Set and sets this classes Set called thisSet to start
	 * @param start is a Set that takes in a generic object.
	 */
	public AlgebraicSet(Set<T> start) {
		thisSet = start;
	}
	
	/**
	 * Creates a AlgebraicSet object that contains an empty Set
	 */
	public AlgebraicSet() {
		thisSet = new HashSet<T>();
	}
	
	/**
	 * intersect method takes in a set and returns the intersection of this classes Set and the Set that is inputed
	 * @param inp is a Set that will be used for the intersection
	 * @return Set<T> this returns the set that is the intersection of this classes Set and the inp Set
	 */
	public Set<T> intersect(Set<T> inp) {
		Set<T> intersect = new HashSet<T>();
		
		T[] input = (T[]) inp.toArray();
		
		for(int x = 0; x < input.length; x++) {
			if(thisSet.contains(input[x])) {
				intersect.add(input[x]);
			}
		}
		
		return intersect;
	}
	
	/**
	 * union method takes in a set and returns the union of this classes Set and the Set that is inputed
	 * @param inp is a Set that will be used for the union
	 * @return Set<T> this returns the set that is the union of this classes Set and the inp Set
	 */
	public Set<T> union(Set<T> inp) {
		T[] input = (T[]) inp.toArray();
		Set<T> union = new HashSet<T>(thisSet);
		
		for(int x = 0; x < input.length; x++) {
			union.add(input[x]);
		}
		
		return union;
	}
	
	/**
	 * difference method takes in a set and returns the difference of this classes Set and the Set that is inputed
	 * @param inp is a Set that will be used for the difference
	 * @return Set<T> this returns the set that is the difference of this classes Set and the inp Set
	 */
	public Set<T> difference(Set<T> inp) {
		Set<T> difference = new HashSet<T>();
		T[] thisArr = (T[]) thisSet.toArray();
		
		for(int x = 0; x < thisArr.length; x++) {
			if(!inp.contains(thisArr[x])) {
				difference.add(thisArr[x]);
			}
		}
		return difference;
	}
	
	/**
	 * this method returns the size of this classes Set
	 * @return int length of this classes Set
	 */
	public int size() {
		// TODO Auto-generated method stub
		return thisSet.size();
	}

	/**
	 * this method returns whether this classes Set is empty or not
	 * @return boolean that is false if Set is not empty or true of it is
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return thisSet.isEmpty();
	}
	
	/**
	 * this method returns a boolean if wheather or not this classes Set contains a certain object
	 * @param Object o which is a object to search in this classes Set
	 * @return boolean if this o paramater is in this classes Set
	 */
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return thisSet.contains(o);
	}

	/**
	 * this method returns an iterator for this classes set
	 * @return Iterator<T> which is an iterator for this classes Set
	 */
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return thisSet.iterator();
	}
	
	/**
	 * this method returns an array of each object in this Set
	 * @return Object[] which is the array of this Set.
	 */
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return thisSet.toArray();
	}

	/**
	 * this method returns an array of each object in this Set
	 * @param Object[] a which is used to convert into the set
	 * @return Object[] which is the array of this Set.
	 */
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return thisSet.toArray(a);
	}

	/**
	 * this method adds an object to the set
	 * @param T e which is the object being added to the Set
	 * @return boolean in whether or not it can be added(if e is already in the set then it will return false if not then true)
	 */
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return thisSet.add(e);
	}

	/**
	 * this method removes an object to the set
	 * @param T e which is the object being removed in the Set
	 * @return boolean in whether or not it can be removes(if e is already in the set then it will return true if not then false)
	 */
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return thisSet.remove(o);
	}

	/**
	 * this method removes everything in the Set making it empty
	 */
	public void clear() {
		thisSet.clear();
	}
	
	/**
	 * this method finds out if a collection is in this Set
	 * @param Collection c which is what we will be using to find if its in the Set
	 * @return boolean in whether or not the collection is in the Set(if c is in the set then it will return true if not then false)
	 */
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.containsAll(c);
	}
	
	/**
	 * this method adds a collection to the Set
	 * @param Collection c is what gets added to the Set
	 * @return boolean in whether or not the collection is already in the Set(if c is already in the set then it will return false if not then true)
	 */
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.addAll(c);
	}

	/**
	 * this method makes the Set to only be what the collection is
	 * @param Collection c is what the set will be if it has it
	 * @return boolean in whether or not the collection is in the Set(if c is in the set then it will return true if not then false)
	 */
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.retainAll(c);
	}

	/**
	 * this method removes a collection to the Set
	 * @param Collection c is what gets removed to the Set
	 * @return boolean in whether or not the collection is in the Set(if c is in the set then it will return true if not then false)
	 */
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.retainAll(c);
	}
	
	/**
	 * this method returns a neat String to represent this Object
	 * @return String which contains every object inside this Set in a neat order with commas
	 */
	public String toString() {
		HashSet temp = (HashSet) thisSet;
		
		return temp.toString();
		
	}
	
}
