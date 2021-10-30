/**
 * @project java_algorithms
 * @package c2k.algorithms.datastructures.lists
 * @file DoublyLinkedList.java
 * @createdOn 30 oct. 2021 | @at 21:14:53
 */
package c2k.algorithms.datastructures.lists;

/**
 * Implementation of a doubly linked list in java
 * 
 * @author Christian Kenneth, christiankennethkisoma@gmail.com
 */
@SuppressWarnings("unchecked")
public class DoublyLinkedList<E> implements _List<E> {
	
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	private class Node<E> {
		E data = null;
		Node<E> previous, next;
		
		Node(E data, Node<E> previous, Node<E> next){
			this.data = data;
			this.previous = previous;
			this.next = next;
		}
		
		@Override public String toString () {
			return data.toString();
		}
	}

	@Override public int size() {
		return this.size;
	}

	@Override public E get(int index) {
		if (isEmpty()) throw new NullPointerException();
		if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		Node<E> traversal = this.head;
		for ( int i = 0; i < this.size; i++ , traversal = traversal.next)
			if (index == i) return traversal.data;
		return null;
	}

	@Override public E peekFirst() {
		if (isEmpty()) throw new NullPointerException();
		return head.data;
	}

	@Override public E peekLast() {
		if (isEmpty()) throw new NullPointerException();
		return tail.data;
	}

	@Override public E replace(int index, E element) {
		if (isEmpty()) throw new NullPointerException();
		if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		Node<E> traversal = this.head;
		E data = null;
		for (int i = 0; i < index; i++)
			traversal = traversal.next;
		data = traversal.data;
		traversal.data = element;
		return data;
	}

	@Override public int indexOf(E element) {
		if (isEmpty()) throw new NullPointerException();
		Node<E> traversal = this.head;
		for (int i = 0; i < this.size ; i++) {
			if (traversal.data.equals(element)) return i;
			traversal = traversal.next;
		}
		return -1;
	}

	@Override public int lastIndexOf(E element) {
		if (isEmpty()) throw new NullPointerException();
		Node<E> traversal = this.tail;
		for (int i = this.size-1 ; i > 0 ; i--) {
			if (traversal.data.equals(element)) return i;
			traversal = traversal.previous;
		}
		return -1;
	}

	@Override public void clear() {
		Node<E> traversal = this.head;
		for (int i = 0; i < this.size; i++) {
			Node<E> next = traversal.next;
			traversal.data = null;
			traversal.next = traversal.previous = null;
			traversal = next;
		}
		this.head = this.tail = null;
		this.size = 0;
	}

	@Override public void addFirst(E element) {
		if (isEmpty()) head = tail = new Node<E>(element, null, null);
		else {
			this.head.previous = new Node<E>(element, null, this.head);
			this.head = this.head.previous;
		}
		this.size++;
	}

	@Override public void addLast(E element) {
		if (isEmpty()) head = tail = new Node<E>(element, null, null);
		else {
			this.tail.next = new Node<E>(element, tail, null);
			this.tail = this.tail.next;
		}
		size++;
	}

	@Override public void addAt(int index, E element) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		if (index == 0) { addFirst(element); return; }
		Node<E> traversal = this.head;
		for (int i = 0; i < index ; i++)
			traversal = traversal.next;
		Node<E> temp = new Node<>(element, traversal.previous, traversal);
		traversal.previous.next = temp;
		traversal.previous = temp;
		this.size++;
	}

	@Override public void addAll(E... elements) {
		if (elements.length <= 0 || elements.equals(null)) throw new IllegalArgumentException();
		for (E element : elements) add(element);
	}

	@Override public E removeFirst() {
		if (isEmpty()) throw new NullPointerException();
		E data = head.data;
		head.data = null;
		head = head.next;
		head.previous = null;
		this.size--;
		return data;
	}

	@Override public E removeLast() {
		if (isEmpty()) throw new NullPointerException();
		E data = tail.data;
		tail.data = null;
		tail = tail.previous;
		tail.next = null;
		this.size--;
		return data;
	}

	private void removeNode(Node<E> node) {
		node.data = null;
		node.previous.next = node.next;
		node.next.previous = node.previous;
		node.previous = node.next = null;
	}
	
	@Override public E removeAt(int index) {
		if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		Node<E> traversal = this.head;
		for (int i = 0; i < index ; i++)
			traversal = traversal.next;
		E data = traversal.data;
		removeNode(traversal);
		size--;
		return data;
	}

	@Override public boolean removeFirst(E element) {
		if (!contains(element)) return false;
		removeAt(indexOf(element));
		return true;
	}

	@Override public boolean removeLast(E element) {
		if (!contains(element)) return false;
		removeAt(lastIndexOf(element));
		return true;
	}

	@Override public boolean remove(E element) {
		return removeFirst(element);
	}

	@Override
	public E[] toArray() {
		if (isEmpty()) return null;
		E[] array = (E[]) new Object[this.size];
		Node<E> traversal = this.head;
		for ( int i = 0; i < this.size; i++, traversal = traversal.next) array[i] = traversal.data;
		return array;
	}
	
	@Override public String toString() {
		return arrayToString();
	}

}
