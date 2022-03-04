/*  -------------------------------------------------------
 *  ObjectQueue.java
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
public class ObjectQueue implements Cloneable, Serializable{
	/*
	 * --- Node Class ---
	 */
	private class Node {
		// Variables 
		private Object data;
		private Node next;
		
		// Single-arg Constructor
		public Node(Object data) {
			this.data = data;
			next = null;
		}	
	}
	
	/*
	 * --- ObjectQueue Class Below ---
	 */
	// Variables
	private Node front, back;
	private int size;
	
	// No-arg Constructor
	public ObjectQueue() {
		front = back = null;
		size = 0;
	}
	
	/*
	 * --- ObjectQueue Methods ---       
	 * 1. isEmpty()                                 
	 * 2. enqueue()
	 * 3. dequeue()
	 * 4. dequeueAll()
	 * 5. upNext()                         
	 * 6. print()  	
	 * 7. size()						    						 
	 */
	
	// isEmpty()
	public boolean isEmpty() {
		return front == null && back == null;
	}

	// enqueue()
	public void enqueue(Object data) {
		Node node = new Node(data);
		
		if(isEmpty()) 
			front = back = node;
		
		else {
			back.next = node;
			back = node;
		}
		
		++size;
	}

	// dequeue()
	public Object dequeue() throws Exception{
		if (!isEmpty()) {
			Object data = front.data;

			if (front == back) 
				front = back = null;
			
			else 
				front = front.next;
			
			--size;
			return data;
		}
		
		else throw new Exception("ObjectQueueException on dequeue: empty queue!"); 
	}

	// dequeueAll()
	public void dequeueAll() {
		front = back = null;
	}

	// upNext()
	public Object upNext() throws Exception{
		if (!isEmpty())
			return front.data;
		
		else throw new Exception("ObjectQueueException on upNext: empty queue!");
	}

	// print()
	public void print() {		
		System.out.println("-- Current ObjectQueue --");

		// Create pointer
		Node frontCopy = front;
		
		while (front != null) {
			System.out.println(front.data);
			front = front.next;
		}
		
		front = frontCopy;
	}

	// size()
	public int size() {
		return size;
	}
}
