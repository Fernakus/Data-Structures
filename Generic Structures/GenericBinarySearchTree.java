/*  -------------------------------------------------------
 *  GenericBinarySearchTree.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.io.Serializable;

// Suppress
@SuppressWarnings({"rawtypes", "serial"})

public class GenericBinarySearchTree<T extends Comparable> implements Cloneable, Serializable{
	/*
	 * --- Node Class ---
	 */
	private class Node {
		// Variables
		private Node left, right;
		private T data;
		
		// No-arg Constructor
		public Node() {
			data = null;
			left = right = null;		
		}
		
		// Single-arg Constructor
		public Node(T data) {
			this.data = data;
			left = right = null;	
		}
	}
	
	/*
	 * --- GenericBinarySearchTree Class Below ---
	 */
	// Variables
	private Node root;
	
	// No-arg Constructor
	public GenericBinarySearchTree() {
		root = null;
	}
	
	/*
	 * --- GenericBinarySearchTree Methods ---       
	 * 1. insert() 
	 * 2. remove()	            
	 * 3. search()              
	 * 4. findMin()             
	 * 5. findMax()             
	 * 6. height()              
	 * 7. prePrint()            
	 * 8. inPrint()             
	 * 9. postPrint()         
	 */
		
	// insert()
	public void insert(T data) {
		root = insert(data, root);
	}
	
	// insert()
	private Node insert(T data, Node root) {
		// Base Cases
		if (root == null) {
			root = new Node(data);
			return root;
		}
		
		if (root.data.equals(data)) 
			return root;
		
		// Recursive Calls
		if (data.toString().compareTo(root.data.toString()) < 0) 
			root.left = insert(data, root.left);
		
		else if (data.toString().compareTo(root.data.toString()) > 0) 
			root.right = insert(data, root.right);
		
		return root;
	}
	
	// remove()
	public void remove(T data) throws Exception {
		if (search(data) == null) 
			throw new Exception("GenericBinarySearchTreeException on remove: Data not in tree!");
		
		else 
			root = remove(data, root);
	}
	
	// remove()
	private Node remove(T data, Node root) {
		Node newSubtree;
		
		// Base Case
		if (root == null) 
			return root;
		
		// Recursive Calls
		if (data.equals(root.data)) 
			root = removeNode(data, root);
		
		else if (data.toString().compareTo(root.data.toString()) < 0) {
			newSubtree = remove(data, root.left);
			root.left = newSubtree;
		}
		
		else {
			newSubtree = remove(data, root.right);
			root.right = newSubtree;
		}
		
		return root;
	}
	
	// removeNode()
	private Node removeNode(T data, Node root) {
		// Variables
		Node leftMostNode = new Node();
		
		/* -- 4 Cases To Consider --
		 * 1) The root is a leaf. 
		 * 2) The root has no left child.
		 * 3) The root has no right child. 
		 * 4) The root has two children. 
		 */
		if ((root.left == null) && (root.right == null)) 
			return null;
		
		else if (root.left == null) 
			return root.right;
		
		else if (root.right == null) 
			return root.left;
		
		else {
			leftMostNode = findLeftMost(root.right);
			root.data = leftMostNode.data;
			root.right = deleteLeftMost(root.right);
			return root;
		}
	}
	
	// findLeftMost()
	private Node findLeftMost(Node root) {
		// Base Case
		if (root.left == null) 
			return root;
		
		// Recursive Call
		return findLeftMost(root.left);
	}
	
	// deleteLeftMost()
	private Node deleteLeftMost(Node root) {
		// Base Case
		if (root.left == null) 
			return root.right;
		
		// Recursive Call
		else {
			root.left = deleteLeftMost(root.left);
			return root;
		}
	}
	
	// search()
	public Node search(T data) {
		return search(data, root);
	}
	
	// search()
	private Node search(T data, Node root) {
		// Base Case
		if (root == null) 
			return null;
		
		if (root.data.equals(data)) 
			return root;
		
		// Recursive Call
		if (data.toString().compareTo(root.data.toString()) > 0) 
			return search(data, root.right);
		
		return search(data, root.left);
	}
	
	// findMin()
	public T findMin() throws Exception{
		return findMin(root);
	}
	
	// findMin()
	private T findMin(Node root) throws Exception{
		// Base Case
		if (root == null) 
			throw new Exception("GenericBinarySearchTreeException on findMin: tree is empty!");
		
		if (root.left == null) 
			return root.data;
		
		// Recursive Call
		return findMin(root.left);
	}
	
	// findMax()
	public T findMax() throws Exception{
		return findMax(root);
	}
	
	// findMax()
	private T findMax(Node root) throws Exception{
		// Base Case 
		if (root == null) 
			throw new Exception("GenericBinarySearchTreeException on findMax: tree is empty!");
		
		if (root.right == null) 
			return root.data;
		
		// Recursive Call
		return findMax(root.right);	
	}
	
	// height()
	public int height() {
		return height(root);
	}
	
	// height()
	private int height(Node root) {
		// Base Case
		if (root == null) 
			return 0;
		
		// Recursive Calls
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	// prePrint()
	public void prePrint() {
		prePrint(root);
		System.out.println("null");
	}
	
	// prePrint()
	private void prePrint(Node root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		System.out.print("(" + root.data + ") --> ");
		prePrint(root.left);
		prePrint(root.right);
	}
	
	// inPrint()
	public void inPrint() {
		inPrint(root);
		System.out.println("nullity");
	}
	
	// inPrint()
	private void inPrint(Node root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		inPrint(root.left);
		System.out.print("(" + root.data + ") --> ");
		inPrint(root.right);
	}
	
	// postPrint()
	public void postPrint() {
		postPrint(root);
		System.out.println("nullity");
	}
	
	// postPrint()
	private void postPrint(Node root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		postPrint(root.left);
		postPrint(root.right);
		System.out.print("(" + root.data + ") --> ");
	}
	
}
