import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class AlgebraicSet implements Set<Object>{
	Set<Object> thisSet;
	
	public AlgebraicSet(Set<Object> start) {
		thisSet = start;
	}
	
	public AlgebraicSet() {
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

	public Iterator<Object> iterator() {
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

	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return thisSet.add(e);
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return thisSet.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return thisSet.containsAll(c);
	}

	public boolean addAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return thisSet.addAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return thisSet.retainAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return thisSet.removeAll(c);
	}

	public void clear() {
		thisSet.clear();
	}
	
}
