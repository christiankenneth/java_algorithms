/**
 * @project java_algorithms
 * @package c2k.algorithms.datastructures.lists
 * @file DynamicArray.java
 * @createdOn 30 oct. 2021 | @at 13:19:58
 */
package c2k.algorithms.datastructures.lists;

/**
 * Implementation of a dynamic array in java
 * 
 * @author Christian Kenneth, christiankennethkisoma@gmail.com
 */
@SuppressWarnings("unchecked")
public class DynamicArray<E> implements _List<E> {
	
	private static final int MIN_CAPACITY = 8;
	private int capacity = 0;
	private int size = 0;
	private E[] elements = null;
	
	public DynamicArray() { this(MIN_CAPACITY); }
	public DynamicArray(int capacity) { 
		this.capacity = capacity;
		this.elements = (E[]) new Object[this.capacity];
	}

	@Override public int size() {
		return this.size;
	}

	@Override public E get(int index) {
		if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException();
		return this.elements[index];
	}

	@Override public E peekFirst() {
		if (isEmpty()) throw new NullPointerException();
		return this.elements[0];
	}

	@Override public E peekLast() {
		if (isEmpty()) throw new NullPointerException();
		return this.elements[this.size - 1];
	}

	@Override public E replace(int index, E element) {
		if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException();
		E data = this.elements[index];
		this.elements[index] = element;
		return data;
	}

	@Override public int indexOf(E element) {
		if (isEmpty()) throw new NullPointerException();
		for (int i = 0; i < this.size ; i++ )
			if (this.elements[i].equals((E) element)) return i;
		return -1;
	}

	@Override public int lastIndexOf(E element) {
		if (isEmpty()) throw new NullPointerException();
		for (int i = this.size-1 ; i > 0 ; i--)
			if (this.elements[i].equals((E) element)) return i;
		return -1;
	}

	@Override public void clear() {
		if (!isEmpty()) {
			for (int i = 0; i < this.size ; i++)
				this.elements[i] = null;
		}
		this.elements = null;
		this.size = 0;
		this.capacity = MIN_CAPACITY;
		this.elements = (E[]) new Object[this.capacity];
	}

	@Override public void addFirst(E element) {
		if (isEmpty()) 	addLast(element);
		else 			addAt(0, element);
	}

	@Override public void addLast(E element) {
		if (this.size >= this.capacity) {
			this.capacity *= 2;
			E[] array = (E[]) new Object[this.capacity];
			for (int i = 0; i < this.size ; i++ )
				array[i] = this.elements[i];
			this.elements = array;
		}
		this.elements[this.size++] = element;
	}

	@Override public void addAt(int index, E element) {
		if (index < 0 || index >= this.size) 	throw new ArrayIndexOutOfBoundsException();
		if (this.size >= this.capacity) 		this.capacity *= 2;
		E[] array = (E[]) new Object[this.capacity];
		for (int i = 0, j = 0; i < this.size ; i++ , j++) {
			if (index == i) array[j++] = element;
			array[j] = this.elements[i];
		}
		this.elements = array;
		this.size++;
	}

	@Override public void addAll(E... elements) {
		if (elements.length <= 0 || elements.equals(null)) throw new IllegalArgumentException();
		for (E element : elements) add((E) element);
	}

	@Override public E removeFirst() {
		if (isEmpty()) throw new NullPointerException();
		return removeAt(0);
	}

	@Override public E removeLast() {
		if (isEmpty()) throw new NullPointerException();
		E data = this.elements[this.size - 1];
		this.elements[--this.size] = null;
		return data;
	}

	@Override public E removeAt(int index) {
		if (index < 0 || index >= this.size) throw new ArrayIndexOutOfBoundsException();
		if (isEmpty()) throw new NullPointerException();
		E data = this.elements[index];
		E[] array = (E[]) new Object[this.capacity];
		for (int i = 0, j = 0; i < this.size ; i++, j++) {
			if ( index == i) i++;
			array[j] = this.elements[i]; 
		}
		this.elements = array;
		size--;
		return data;
	}

	@Override public boolean removeFirst(E element) {
		if (isEmpty()) throw new NullPointerException();
		if (!contains(element)) return false;
		removeAt(indexOf(element));
		return true;
	}

	@Override public boolean removeLast(E element) {
		if (isEmpty()) throw new NullPointerException();
		if (!contains(element)) return false;
		removeAt(lastIndexOf(element));
		return true;
	}

	@Override public boolean remove(E element) {
		return removeFirst(element);
	}
	
	@Override public E[] toArray() {
		E[] array = (E[]) new Object[this.size];
		for(int i = 0; i < this.size; i++)
			array[i] = this.elements[i];
		return array;
	}

	@Override public String toString() {
		return arrayToString();
	}
}
