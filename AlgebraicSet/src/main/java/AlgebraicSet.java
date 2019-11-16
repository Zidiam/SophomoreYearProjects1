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
	
	public AlgebraicSet(Set<T> start) {
		thisSet = start;
	}
	
	public AlgebraicSet() {
		thisSet = new HashSet<T>();
	}
	
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
	
	public Set<T> union(Set<T> inp) {
		T[] input = (T[]) inp.toArray();
		Set<T> union = new HashSet<T>(thisSet);
		
		for(int x = 0; x < input.length; x++) {
			union.add(input[x]);
		}
		
		return union;
	}
	
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
	
	public int size() {
		// TODO Auto-generated method stub
		return thisSet.size();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return thisSet.isEmpty();
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return thisSet.contains(o);
	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return thisSet.iterator();
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return thisSet.toArray();
	}

	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return thisSet.toArray(a);
	}

	public boolean add(T e) {
		// TODO Auto-generated method stub
		return thisSet.add(e);
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return thisSet.remove(o);
	}

	public void clear() {
		thisSet.clear();
	}

	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.containsAll(c);
	}

	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.addAll(c);
	}

	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.retainAll(c);
	}

	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return thisSet.retainAll(c);
	}
	
	public String toString() {
		HashSet temp = (HashSet) thisSet;
		
		return temp.toString();
		
	}
	
}
