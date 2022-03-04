/*  -------------------------------------------------------
 *  GenericCircularQueue.java
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
public class GenericCircularQueue<T extends Comparable> implements Cloneable, Serializable{
	// Variables
	private final int INITIAL_MAX = 1000;
	private int frontIndex, backIndex, size;
	private Comparable[] circularQueue;

	// No-arg constructor
	public GenericCircularQueue() {
		circularQueue = new Comparable[INITIAL_MAX];
		frontIndex = 0;
		backIndex = -1;
		size = 0;
	}
	
	/*
	 * --- GenericCircularQueue Methods ---          
	 * 1.  isEmpty()  
	 * 2.  isFull()
	 * 3.  resizeQueue()                               
	 * 4.  enqueue()
	 * 5.  dequeue()
	 * 6.  dequeueAll()
	 * 7.  upNext()  
	 * 8.  modulate()   
	 * 9.  size()                    
	 * 10. print()  							    						 
	 */
	
	// isEmpty()
	public boolean isEmpty() {
		return frontIndex == modulate(backIndex);
	}

	// isFull()
	public boolean isFull() {
		return frontIndex == modulate(backIndex + 1);
	}
	
	// resizeQueue
	public void resizeQueue(int factor) throws Exception {
		if (INITIAL_MAX * factor < size()) 
			throw new Exception("GenericCircularQueueException on resizeQueue: tried to resize the queue to a queue size < the number of enqueue items!");
		
		Comparable[] newCircularQueue = new Comparable[INITIAL_MAX * factor];
		
		for (int i = 0; i < circularQueue.length; i++) 
			newCircularQueue[i] = circularQueue[i];
		
		circularQueue = newCircularQueue;
	}
		
	// enqueue()
	public void enqueue(T data) throws Exception {
		if (!isFull()) {
			backIndex = modulate(backIndex);
			circularQueue[backIndex] = data;
			size++;
		}
		
		else 
			throw new Exception("GenericCircularQueueException on enqueue: queue full!");
	}
	
	// dequeue()
	public Object dequeue() throws Exception {
		if (!isEmpty()) {
			T queueFront = (T)circularQueue[frontIndex];
			frontIndex = modulate(frontIndex);
			size++;
			return queueFront;
		}
		
		else 
			throw new Exception("GenericCircularQueueException on upNext: queue empty!");
	}
	
	// dequeueAll()
	public void dequeueAll() {
		circularQueue = new Comparable[INITIAL_MAX];
		frontIndex = 0;
		backIndex = -1;
		size = 0;
	}
	
	// upNext()
	public T upNext() throws Exception {
		if (!isEmpty()) 
			return (T)circularQueue[frontIndex];
		else 
			throw new Exception("GenericCircularQueueException on upNext: queue empty!");
	}

	// modulate()
	private int modulate(int index) {
		if (index + 1 >= INITIAL_MAX) 
			return (index + 1) - INITIAL_MAX;
		return 
			index + 1;
	}
	
	// size()
	public int size() {
		return size;
	}
	
	// print()
	public void print() {
		// Variables
		int index = frontIndex;

		System.out.println("-- Current GenericCircularQueue --");
		while (index <= backIndex) {
			if (circularQueue[index] != null) 
				System.out.println(circularQueue[index]);
			index++;
		}
		System.out.println(circularQueue[frontIndex]);
	}
}