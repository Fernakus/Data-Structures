/*  -------------------------------------------------------
 *  ExpressionTree.java
 *  -------------------------------------------------------
 *  Author:  Matthew Ferlaino
 *  Course:	 COSC2006A
 *  ID:      169657520
 *  Email:   mferlaino@algomau.ca
 *  Date:	 Jan 22, 2019
 *  ------------------------------------------------------- */

// Import 
import java.io.Serializable;

// Class
@SuppressWarnings({"serial"})

public class ExpressionTree implements Serializable, Cloneable{
	/*
	 * --- Node Class ---
	 */
	private class Node {
		// Variables
		private char aChar;
		private Node left, right;
		
		// Single-arg Constructor
		public Node(char aChar) {
			this.aChar = aChar;
			left = right = null;
		}
				
		// Triple-arg Constructor
		public Node(char aChar, Node left, Node right) {
			this.aChar = aChar;
			this.left = left;
			this.right = right;
		}
	}
	
	/*
	 * --- ExpressionTree Object Below ---
	 */
	// Variables
	private Node root;
	
	// No-arg Constuctor
	public ExpressionTree() {
		root = null;
	}
	
	// Single-arg Constuctor
	public ExpressionTree(StringBuilder expressionString) throws Exception {
		root = buildTree(expressionString);
	}
	
	/*
	 * --- ExpressionTree Methods ---         
	 * 1) evaluateTree()                            
	 * 2) build()
	 * 3) isOperator()
	 * 4) prePrint();
	 * 5) inPrint();
	 * 6) postPrint();      
	 * 7) print()                      						 
	 */
	
	// evaluateTree()
	public double evaluateTree() {
		return evaluateTree(root);
	}
	
	// evalTree()
	private double evaluateTree(Node root) {
		char nodeChar = root.aChar;
		
		// Base Case
		if(!isOperator(nodeChar)) 
			return (double)nodeChar;
		
		double valueLeft = evaluateTree(root.left);
		double valueRight = evaluateTree(root.right);
		double result = 0.0;
		
		switch(nodeChar) {
			case '+': 
				result = valueLeft + valueRight;	
			case '-': 
				result = valueLeft - valueRight;
			case '/': 
				result = valueLeft / valueRight;
			case '*': 
				result = valueLeft * valueRight;
		}
		
		return result;
	}
		
	// build()
	public void build(StringBuilder expression) throws Exception {
		root = buildTree(expression);
	}
	
	// build()
	private Node buildTree(StringBuilder expression) throws Exception {
	
		// Base Case
		if (expression.length() == 0) 
			throw new Exception("ExpressionTreeException on buildTree: invalid expression!");
		
		// Recursive
		char aChar = expression.charAt(0); 
		expression.deleteCharAt(0); 
		
		// Builds the left side 
		if (isOperator(aChar)) 
			return new Node(aChar, buildTree(expression), buildTree(expression));
		
		else 
			return new Node(aChar);
		
	}
	
	// isOperator()
	public boolean isOperator(char aChar) {
		switch(aChar) {
			case '+':
			case '-':
			case '/':
			case '*':
			case '(':
			case ')':
				return true;
		}
		return false;
	}
	
	// prePrint()
	public void prePrint() {
		prePrint(root);
		System.out.println("nullity");
	}
	
	// prePrint()
	private void prePrint(Node root) {
		// Base Case 
		if (root == null) 
			return;
		
		// Recursive Traversal
		System.out.print("(" + root.aChar + ") --> ");
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
		System.out.print("(" + root.aChar + ") --> ");
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
		System.out.print("(" + root.aChar + ") --> ");
	}
		
	// print()
	public void print() {
		print(root);
		System.out.println();
	}	
	
	// print()
	private void print(Node root) {
		// Base Case
		if (root == null) 
			return;
		
		// Begin Printing
		if(root.left != null) 
			System.out.print("(");
		print(root.left);
		
		System.out.print("" + root.aChar + "");
		print(root.right);
		if (root.right != null) 
			System.out.print(")");
	}	
}
