/*  -------------------------------------------------------
 *  GenericQueue.java
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
public class GenericQueue<T extends Comparable> implements Cloneable, Serializable{
	/*
	 * -- Node Class --
	 */
	private class Node<T extends Comparable> {
		// Variables 
		private T data;
		private Node next;
		
		// Double-arg Constructor
		public Node(T data) {
			this.data = data;
			next = null;
		}	
	}
	
	/*
	 * -- GenericQueue Class Below --
	 */
	// Variables
	private Node front, back;
	private int size;
	
	// No-arg Constructor
	public GenericQueue() {
		front = back = null;
		size = 0;
	}
	
	/*
	 * -- GenericQueue Methods --       
	 * 1. isEmpty()                                 
	 * 2. enqueue()
	 * 3. dequeue()
	 * 4. dequeueAll()
	 * 5. next()                         
	 * 6. print()  	
	 * 7. size()						    						 
	 */
	
	// isEmpty()
	public boolean isEmpty() {
		return front == null && back == null;
	}

	// enqueue()
	public void enqueue(T data) {
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
			T data = (T)front.data;

			if (front == back) 
				front = back = null;
			
			else 
				front = front.next;
			
			--size;
			return data;
		}
		
		else throw new Exception("GenericQueueException on dequeue: empty queue!"); 
	}

	// dequeueAll()
	public void dequeueAll() {
		back = null;
	}

	// peek()
	public T peek() throws Exception{
		if (!isEmpty())
			return (T)front.data;
		
		else throw new Exception("GenericQueueException on peek: empty queue!");
	}

	// print()
	public void print() {		
		System.out.println("-- Current GenericQueue --");

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
