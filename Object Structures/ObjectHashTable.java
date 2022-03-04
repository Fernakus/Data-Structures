/*  -------------------------------------------------------
 *  ObjectHashTable.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.io.Serializable;
import java.util.ArrayList;

// Suppress
@SuppressWarnings({ "unchecked", "serial" })

// Class
public class ObjectHashTable implements Cloneable, Serializable{

	// Variables
	private final int INITIAL_MAX = 1000;
	private int hashKey, size;
	private ArrayList<Object>[] hashTable;

	// No-arg Constructor
	public ObjectHashTable() {
		hashTable = new ArrayList[INITIAL_MAX];
		size = 0;
	}
		
	/*
	 * --- ObjectHashTable Methods ---        
	 * 1. add()                                 
	 * 2. get()
	 * 3. size()
	 * 4. resizeHashTable()
	 * 5. remove()
	 * 6. hash()                             
	 * 7. isFull()()  							    
	 * 8. print() 	
	 * 9. printWithIndex()						 
	 */
	
	// add()
	public void add(Object object) throws Exception{
		// Generate the hash
		hashKey = hash(object);
		
		// Exception case
		if (isFull()) 
			throw new Exception("ObjectHashTableException on put(): Table is full!");
		
		else 
			if (hashTable[hashKey] != null) {
				if (hashTable[hashKey].contains(object))
					return;
				else 
					hashTable[hashKey].add(object);
			}
				
		
			else {
				hashTable[hashKey] = new ArrayList<Object>();
				hashTable[hashKey].add(object);
			}
	}
	
	// get()
	public Object get(Object object) {
		hashKey = hash(object);
		
		if (hashTable[hashKey] == null) 
			return null;
		
		else if(hashTable[hashKey].size() == 1) 
			return hashTable[hashKey].get(0);
		
		else {
			for (Object obj : hashTable[hashKey]) 
				if (obj.equals(object)) 
					return obj;						
		}
		
		return null;
	}

	// size()
	public int size() {
		return size;
	}
	
	// resizeTable()
	public void resizeHashTable(int factor) throws Exception{
		if (factor * INITIAL_MAX < size()) 
			throw new Exception("ObjectHashTableException on resizeHashTable: resized hash table to a size < the current amount of objects stored in it!");
		
		ArrayList<Object>[] newHashTable = new ArrayList[INITIAL_MAX * factor];
		
		for (int i = 0; i < hashTable.length; i++) 
			newHashTable[i] = hashTable[i];
		
		hashTable = newHashTable;
	}
	
	// remove()
	public void remove(Object object) {
		hashKey = hash(object);
		
		if (hashTable[hashKey] == null) 
			return;
			
		else {
			hashTable[hashKey].remove(object);		
			
			if (hashTable[hashKey].size() == 0)
				hashTable[hashKey] = null;
		}
	}
	
	// hash()
	public int hash(Object object) {
		String hash = object.hashCode() + "";
		char sum = '0';
		int five = 5;
		for (int i = 0; i < hash.length(); i++) 
			sum += ((hash.charAt(i)) * (Math.pow(2, five += 5)) % INITIAL_MAX);
		return sum % INITIAL_MAX;
	}
	
	// isFull()
	public boolean isFull() {
		return size == INITIAL_MAX;
	}
	
	// print()
	public void print() {
		System.out.println();
		System.out.println("--- Current ObjectHashTable ---");
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
		System.out.println("--- Current ObjectHashTable ---");
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
}
