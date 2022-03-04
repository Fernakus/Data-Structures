/*  -------------------------------------------------------
 *  GenericStack.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.io.Serializable;

// Suppress
@SuppressWarnings({"rawtypes", "unchecked", "serial", "hiding"})

// Class
public class GenericStack<T extends Comparable> implements Cloneable, Serializable{
	/*
	 * --- Node Class ---
	 */
	public class Node<T extends Comparable> {
		// Variables 
		private T data;
		private Node next;
		
		// Double-arg Constructor
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/*
	 * --- GenericStack Class Below ---
	 */	
	// Variables	
	private Node top;
	private int size;
	
	// No-arg Constructor
	public GenericStack() {
		top = null;
		size = 0;
	}
	
	/* --- Methods ---
	 * 1. peek()
	 * 2. push()
	 * 3. pop()
	 * 4. popAll()
	 * 5. isEmpty()
	 * 6. size()
	 * 7. print()
	 * 8. toString()
	 */
	
	// peek()
	public T peek() throws Exception {
		if (!isEmpty()) 
			return (T)top.data;
		
		else throw new Exception("GenericStackException on peek: empty stack!");
	}

	// push()
	public void push(T data) {
		top = new Node(data, top);
		++size;
	}

	// pop()
	public T pop() throws Exception {
		if (!isEmpty()) {
			T temp = (T)top.data;
			top = top.next;
			--size;
			return temp;
		}
			
		else throw new Exception("GenericStackException on pop: empty stack!");
	}

	// popAll()
	public void popAll() {
		top = null;	
	}

	// isEmpty()
	public boolean isEmpty() {
		return top == null;
	}
	
	// size()
	public int size() {
		return size;
	}
	
	// print()
	public void print() {
		// Save top
		Node topCopy = top;
		
		// Print 
		System.out.println("--- Current GenericStack ---");
		while (top != null) {
			System.out.println(top.data);
			top = top.next;
		}
		
		// Restore top
		top = topCopy;
	}
}
