/*  -------------------------------------------------------
 *  ObjectHeap.java
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
public class ObjectHeap implements Cloneable, Serializable{
	// Variables
	private Object[] priorityQueue;	
	private final int MAX = 1000;
	private int size;
	
	// No-arg Constructor
	public ObjectHeap() {
		priorityQueue = new Object[MAX];
		size = 0;
	}
	
	/*
	 * --- GenericHeap Methods ---          
	 * 1. enqueue()                                 
	 * 2. dequeue()
	 * 3. heapifyDown()
	 * 4. heapifyUp()                       
	 * 5. print()  	
	 * 6. size()		
	 * 7. upNext()				    						 
	 */
	
	// enqueue()
	public void enqueue(Object data) throws Exception{
		// Make sure we are not full
		if (size() < MAX) {
			priorityQueue[size++] = data;
			heapifyUp(size-1);
		}

		else throw new Exception("ObjectHeapException on insert: heap full!");
	}
	
	// dequeue()
	public Object dequeue() {
		// Save root
		Object root = priorityQueue[0];
		
		// Get ride of root
		priorityQueue[0] = priorityQueue[--size];
		
		heapifyDown(0);
		
		return root;
	}
	
	// heapifyDown()
	public void heapifyDown(int index) {
		// Calculate Child
		int child = 2 * index + 1;
		
		// Base Cases
		if (child >= size()) 
			return;
		
		if (child + 1 < size() && priorityQueue[index].toString().compareTo(priorityQueue[child].toString()) < 0) 
			child++;
		
		if (priorityQueue[index].toString().compareTo(priorityQueue[child].toString()) >= 0) 
			return;
		
		// Swap
		Object copy = priorityQueue[index];
		priorityQueue[index] = priorityQueue[child];
		priorityQueue[child] = copy;
		
		heapifyDown(child);
	}
	
	// heapifyUp()
	public void heapifyUp(int index) {
		// Base Cases
		if (index <= 0) 
			return;

		// Calculate parent
		int parent = (index - 1)/2;
		
		if (priorityQueue[index].toString().compareTo(priorityQueue[parent].toString()) < 0)
			return;
		
		else {
			// Swap
			Object copy = priorityQueue[index];
			priorityQueue[index] = priorityQueue[parent];
			priorityQueue[parent] = copy;
			
			heapifyUp(parent);
		}
	}
	
	// print()
	public void print() {
		System.out.println("-- Current ObjectHeap --");
		for (int i = 0; i < priorityQueue.length; i++) {
			if (priorityQueue[i] != null) 
				System.out.println(priorityQueue[i].toString());
		}
	}
	
	// size()
	public int size() {
		return size;
	}

	// upNext()
	public Object upNext() {
		return (Object)priorityQueue[0];
	}
}
