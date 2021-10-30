/**
 * @project java_algorithms
 * @package c2k.algorithms.datastructures.lists
 * @file _List.java
 * @createdOn 30 oct. 2021 | @at 12:38:24
 */
package c2k.algorithms.datastructures.lists;

import java.util.Iterator;

/**
 * A contract for lists
 * 
 * @author Christian Kenneth, christiankennethkisoma@gmail.com
 */
@SuppressWarnings("unchecked")
public interface _List<E> extends Iterable<E>{
	
	int size();
	E get(int index);
	E peekFirst();
	E peekLast();
	E replace(int index, E element);
	int indexOf(E element);
	int lastIndexOf(E element);
	void clear();
	
	void addFirst(E element);
	void addLast(E element);
	void addAt(int index, E element);
	void addAll(E... elements);

	
	E removeFirst();
	E removeLast();
	E removeAt(int index);
	boolean removeFirst(E element);
	boolean removeLast(E element);
	boolean remove(E element);
	E[] toArray();

	default boolean isEmpty() { return size() == 0; }
	default boolean contains(E element) { return indexOf(element) != -1; }
	default boolean containsAll(E... elements) {
		if (elements.equals(null)) throw new NullPointerException();
		for (E element : elements)
			if (!contains(element)) return false;
		return true;
	}
	default void add(E element) { addLast(element); }
	default void removeAll() { clear(); }
	default boolean removeAll(E... elements) {
		if (!containsAll(elements)) return false;
		for (E element : elements)
			remove(element);
		return true;
	}
	default Iterator<E> iterator() {
		return new Iterator<E>() {
			int index = 0;
			E[] array = toArray();
			@Override public boolean hasNext() { return index < array.length ; }
			@Override public E next() { return array[index++]; }
		};
	}
	default String arrayToString() {
		if (isEmpty()) return "[]";
		E[] array = toArray();
		StringBuilder builder = new StringBuilder().append("[");
		for (int i = 0; i < array.length - 1; i++)
			builder.append(array[i] + ", ");
		return builder.append(array[array.length - 1] + "]").toString();
	}
	
}