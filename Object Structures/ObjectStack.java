/*  -------------------------------------------------------
 *  ObjectStack.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.io.Serializable;

// Suppress
@SuppressWarnings({"serial"})

// Class
public class ObjectStack implements Cloneable, Serializable{
	/*
	 * --- Node Class ---
	 */
	public class Node {
		// Variables 
		private Object object;
		private Node next;
		
		// Double-arg Constructor
		public Node(Object object, Node next) {
			this.object = object;
			this.next = next;
		}
	}
	
	/*
	 * --- ObjectStack Class Below ---
	 */	
	// Variables	
	private Node top;
	private int size;
	
	// No-arg Constructor
	public ObjectStack() {
		top = null;
		size = 0;
	}
	
	/* --- ObjectStack Methods ---
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
	public Object peek() throws Exception {
		if (!isEmpty()) 
			return top.object;
		
		else throw new Exception("ObjectStackException on peek: empty stack!");
	}

	// push()
	public void push(Object data) {
		top = new Node(data, top);
		++size;
	}

	// pop()
	public Object pop() throws Exception {
		if (!isEmpty()) {
			Object temp = top.object;
			top = top.next;
			--size;
			return temp;
		}
			
		else throw new Exception("ObjectStackException on pop: empty stack!");
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
		System.out.println("--- Current ObjectStack ---");
		while (top != null) {
			System.out.println(top.object);
			top = top.next;
		}
		
		// Restore top
		top = topCopy;
	}
}
