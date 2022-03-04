/*  -------------------------------------------------------
 *  GenericHeap.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.io.Serializable;

// Suppress
@SuppressWarnings({"rawtypes", "unchecked", "serial"})

// Class
public class GenericHeap<T extends Comparable> implements Cloneable, Serializable{
	// Variables
	private final int INITIAL_MAX = 1000;
	private int size;
	private Comparable[] priorityQueue;	
	
	// No-arg Constructor
	public GenericHeap() {
		priorityQueue = new Comparable[INITIAL_MAX];
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
	public void enqueue(T data) throws Exception{
		// Make sure we are not full
		if (size() < INITIAL_MAX) {
			priorityQueue[size++] = data;
			heapifyUp(size-1);
		}

		else throw new Exception("GenericHeapException on insert: heap full!");
	}
	
	// dequeue()
	public T dequeue() {
		// Save root
		T root = (T)priorityQueue[0];
		
		// Get ride of root
		priorityQueue[0] = priorityQueue[--size];
		
		heapifyDown(0);
		
		return root;
	}
	
	// resizeTable()
	public void resizeTable(int factor) throws Exception {
		if (INITIAL_MAX * factor < size()) 
			throw new Exception("GenericHeapException on resizeTable: tried to resize the heap to a heap size < the number of items currently in the heap!");
		
		Comparable[] newPriorityQueue = new Comparable[INITIAL_MAX * factor];
		
		// Copy current data to new array
		for (int i = 0; i < priorityQueue.length; i++) 
			newPriorityQueue[i] = priorityQueue[i];
			
		priorityQueue = newPriorityQueue;
	}
	
	// heapifyDown()
	public void heapifyDown(int index) {
		// Calculate Child
		int child = 2 * index + 1;
		
		// Base Cases
		if (child >= size()) 
			return;
		
		if (child + 1 < size() && priorityQueue[index].compareTo(priorityQueue[child]) < 0) 
			child++;
		
		if (priorityQueue[index].compareTo(priorityQueue[child]) >= 0) 
			return;
		
		// Swap
		T copy = (T)priorityQueue[index];
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
		
		if (priorityQueue[index].compareTo(priorityQueue[parent]) < 0)
			return;
		
		else {
			// Swap
			T copy = (T)priorityQueue[index];
			priorityQueue[index] = priorityQueue[parent];
			priorityQueue[parent] = copy;
			
			heapifyUp(parent);
		}
	}
	
	// print()
	public void print() {
		System.out.println("-- Current GenericHeap --");
		for (int i = 0; i < priorityQueue.length; i++) {
			if (priorityQueue[i] != null) 
				System.out.println(priorityQueue[i]);
		}
	}
	
	// size()
	public int size() {
		return size;
	}

	// upNext()
	public T upNext() {
		return (T)priorityQueue[0];
	}
}
