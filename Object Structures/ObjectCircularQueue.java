/*  -------------------------------------------------------
 *  ObjectCircularQueue.java
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
public class ObjectCircularQueue implements Cloneable, Serializable{
	// Variables
	private final int INITIAL_MAX = 1000;
	private int frontIndex, backIndex, size;
	private Object[] circularQueue;

	// No-arg constructor
	public ObjectCircularQueue() {
		circularQueue = new Object[INITIAL_MAX];
		frontIndex = 0;
		backIndex = -1;
		size = 0;
	}
	
	/*
	 * --- ObjectCircularQueue Methods ---          
	 * 1.  isEmpty()  
	 * 2.  isFull()                               
	 * 3.  enqueue()
	 * 4.  dequeue()
	 * 5.  dequeueAll()
	 * 6.  upNext()  
	 * 7.  modulate()   
	 * 8.  size()                    
	 * 9.  print()  
	 * 10. resizeQueue()							    						 
	 */
	
	// isEmpty()
	public boolean isEmpty() {
		return frontIndex == modulate(backIndex);
	}

	// isFull()
	public boolean isFull() {
		return frontIndex == modulate(backIndex + 1);
	}
	
	// enqueue()
	public void enqueue(Object data) throws Exception {
		if (!isFull()) {
			backIndex = modulate(backIndex);
			circularQueue[backIndex] = data;
			size++;
		}
		
		else 
			throw new Exception("ObjectCircularQueueException on enqueue: queue full!");
	}
	
	// dequeue()
	public Object dequeue() throws Exception {
		if (!isEmpty()) {
			Object queueFront = circularQueue[frontIndex];
			frontIndex = modulate(frontIndex);
			size++;
			return queueFront;
		}
		
		else 
			throw new Exception("ObjectCircularQueueException on upNext: queue empty!");
	}
	
	// dequeueAll()
	public void dequeueAll() {
		circularQueue = new Comparable[INITIAL_MAX];
		frontIndex = 0;
		backIndex = -1;
		size = 0;
	}
	
	// upNext()
	public Object upNext() throws Exception {
		if (!isEmpty()) 
			return circularQueue[frontIndex];
		else 
			throw new Exception("ObjectCircularQueueException on upNext: queue empty!");
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

		System.out.println("--- Current ObjectCircularQueue ---");
		while (index <= backIndex) {
			if (circularQueue[index] != null) 
				System.out.println(circularQueue[index].toString());
			index++;
		}
		System.out.println(circularQueue[frontIndex].toString());
	}
	
	// resizeQueue
	public void resizeQueue(int factor) throws Exception{		
		if (factor * INITIAL_MAX < size()) 
			throw new Exception("ObjectCircularQueueException on resizeQueue: resized queue to a size < the current amount of enqueued objects!");
		
		Object[] newCircularQueue = new Object[INITIAL_MAX * factor];
		
		for (int i = 0; i < circularQueue.length; i++) 
			newCircularQueue[i] = circularQueue[i];
		
		circularQueue = newCircularQueue;
	}
}