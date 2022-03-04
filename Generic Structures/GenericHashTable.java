/*  -------------------------------------------------------
 *  GenericHashTable.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.util.ArrayList;
import java.io.Serializable;

// Suppress
@SuppressWarnings({"rawtypes", "unchecked", "serial"})

// Class
public class GenericHashTable<T extends Comparable> implements Cloneable, Serializable{
	// Variables
	private final int INITIAL_MAX = 1000;
	private int hashKey, size;
	private ArrayList<T>[] hashTable;

	// No-arg Constructor
	public GenericHashTable() {
		hashTable = new ArrayList[INITIAL_MAX];
		size = 0;
	}
		
	/*
	 * --- GenericHashTable Methods ---        
	 * 1.  add()                                 
	 * 2.  get()
	 * 3.  size()
	 * 4.  resizeTable()
	 * 5.  remove()
	 * 6.  hash()                             
	 * 7.  isFull()
	 * 8.  isEmpty()						    
	 * 9.  print() 	
	 * 10. printWithIndex()						 
	 */
	
	// add()
	public void add(T data) throws Exception{
		hashKey = hash(data);
		
		if (isFull()) 
			throw new Exception("ObjectHashTableException on add(): table is full!");
		
		else {
			if (hashTable[hashKey] != null) {
				if (hashTable[hashKey].contains(data))
					return;
				else {
					hashTable[hashKey].add(data);
					size++;
				}
			}
				
		
			else {
				hashTable[hashKey] = new ArrayList<T>();
				hashTable[hashKey].add(data);
				size++;
			}
		}
	}
	
	// get()
	public T get(T data) {
		hashKey = hash(data);
		
		if (hashTable[hashKey] == null) 
			return null;
		
		else if(hashTable[hashKey].size() == 1) 
			return hashTable[hashKey].get(0);
		
		else {
			for (T obj : hashTable[hashKey]) 
				if (obj.compareTo(data) == 0) 
					return obj;						
		}
		
		return null;
	}

	// size()
	public int size() {
		return size;
	}
	
	// resizeTable()
	public void resizeTable(int factor) throws Exception {
		if (INITIAL_MAX * factor < size()) 
			throw new Exception("GenericHashTableException on resizeTable: tried to resize the table to a table size < the number of items currently in the table!");
		
		ArrayList<T>[] newHashTable = new ArrayList[INITIAL_MAX * factor];
		
		// Copy current data to new array
		for (int i = 0; i < hashTable.length; i++) 
			newHashTable[i] = hashTable[i];
			
		hashTable = newHashTable;
	}
	
	// remove()
	public void remove(T data) throws Exception{
		hashKey = hash(data);
		
		if (isEmpty()) 
			throw new Exception("GenericHashTableException on remove(): Table is empty!");
		
		if (hashTable[hashKey] == null) 
			return;
			
		else {
			if (hashTable[hashKey].remove(data)) 
				size--;
				
			if (hashTable[hashKey].size() == 0)
				hashTable[hashKey] = null;
		}
	}
	
	// hash()
	public int hash(T data) {
		String hash = data.hashCode() + "";
		char sum = '0';
		int five = 5;
		for (int i = 0; i < hash.length(); i++) 
			sum += ((hash.charAt(i)) * (Math.pow(2, five += 5)) % INITIAL_MAX);
		return sum % INITIAL_MAX;
	}
	
	// isFull()
	public boolean isFull() {
		return size == hashTable.length;
	}
	
	// isEmpty()
	public boolean isEmpty() {
		return size == 0;
	}
	
	// print()
	public void print() {
		System.out.println();
		System.out.println("--- Current GenericHashTable ---");
		for (int i = 0; i < INITIAL_MAX; i++) {
			if (hashTable[i] == null) 
				continue;
			
			else 
				for (Object obj : hashTable[i])
					System.out.print(obj.toString() + " ");
			
			System.out.println();
		}
	}
	
	// printWithIndex()
	public void printWithIndex() {
		System.out.println();
		System.out.println("--- Current GenericHashTable ---");
		for (int i = 0; i < INITIAL_MAX; i++) {
			if (hashTable[i] == null) 
				continue;
			
			else {
				System.out.print("[Index " + i + "]: ");
				for (Object obj : hashTable[i])
					System.out.print(obj.toString() + " ");
			}
			System.out.println();
		}
	}

	// getHashTable()
	public ArrayList<T>[] getHashTable() {
		return hashTable;
	}
}
