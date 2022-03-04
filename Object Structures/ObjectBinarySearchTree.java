/*  -------------------------------------------------------
 *  ObjectBinarySearchTree.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Email:   mferlaino73@gmail.com
 *  Date:	 Feb 25, 2019
 *  Composite Computing Co.
 *  ------------------------------------------------------- */

// Imports
import java.io.Serializable;

//Suppress
@SuppressWarnings({"serial"})

public class ObjectBinarySearchTree implements Cloneable, Serializable{
	/*
	 * --- Node Class ---
	 */
	private class Node {
		// Variables
		private Node left, right;
		private Object object;
		
		// No-arg Constructor
		public Node() {
			object = null;
			left = right = null;		
		}
		
		// Single-arg Constructor
		public Node(Object object) {
			this.object = object;
			left = right = null;	
		}
	}
	
	/*
	 * --- ObjectBinarySearchTree Class Below ---
	 */
	// Variables
	private Node root;
	
	// No-arg Constructor
	public ObjectBinarySearchTree() {
		root = null;
	}
	
	/*
	 * --- ObjectBinarySearchTree Methods ---       
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
	public void insert(Object object) {
		root = insert(object, root);
	}
	
	// insert()
	private Node insert(Object object, Node root) {
		// Base Cases
		if (root == null) {
			root = new Node(object);
			return root;
		}
		
		if (root.object.equals(object)) 
			return root;
		
		// Recursive Calls
		if (object.toString().compareTo(root.object.toString()) < 0) 
			root.left = insert(object, root.left);
		
		else if (object.toString().compareTo(root.object.toString()) > 0) 
			root.right = insert(object, root.right);
		
		return root;
	}
	
	// remove()
	public void remove(Object object) throws Exception {
		if (search(object) == null) 
			throw new Exception("ObjectBinarySearchTreeException on remove. Object not in tree!");
		
		else 
			root = remove(object, root);
	}
	
	// remove()
	private Node remove(Object object, Node root) {
		Node newSubtree;
		
		// Base Case
		if (root == null) 
			return root;
		
		// Recursive Calls
		if (object.equals(root.object)) 
			root = removeNode(object, root);
		
		else if (object.toString().compareTo(root.object.toString()) < 0) {
			newSubtree = remove(object, root.left);
			root.left = newSubtree;
		}
		
		else {
			newSubtree = remove(object, root.right);
			root.right = newSubtree;
		}
		
		return root;
	}
	
	// removeNode()
	private Node removeNode(Object object, Node root) {
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
			root.object = leftMostNode.object;
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
	public Node search(Object object) {
		return search(object, root);
	}
	
	// search()
	private Node search(Object object, Node root) {
		// Base Case
		if (root == null) 
			return null;
		
		if (root.object.equals(object)) 
			return root;
		
		// Recursive Call
		if (object.toString().compareTo(root.object.toString()) > 0) 
			return search(object, root.right);
		
		return search(object, root.left);
	}
	
	// findMin()
	public Object findMin() throws Exception{
		return findMin(root);
	}
	
	// findMin()
	private Object findMin(Node root) throws Exception{
		// Base Case
		if (root == null) 
			throw new Exception("ObjectBinarySearchTreeException on findMin: tree is empty!");
		
		if (root.left == null) 
			return root.object;
		
		// Recursive Call
		return findMin(root.left);
	}
	
	// findMax()
	public Object findMax() throws Exception{
		return findMax(root);
	}
	
	// findMax()
	private Object findMax(Node root) throws Exception{
		// Base Case 
		if (root == null) 
			throw new Exception("on findMax: tree is empty!");
		
		if (root.right == null) 
			return root.object;
		
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
		System.out.print("(" + root.object + ") --> ");
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
		System.out.print("(" + root.object + ") --> ");
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
		System.out.print("(" + root.object + ") --> ");
	}
	
}
