package com.learn.coding.dsa.tree;

import java.util.LinkedList;

public class Traversal_DFS_Iterative {

	public static class Node {

		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

	}
	
	//NLR
	private static void preorder(Node root) {
	    if (root == null) return;

	    LinkedList<Node> stack = new LinkedList<>();
	    stack.push(root);

	    while (!stack.isEmpty()) {
	        Node current = stack.pop();
	        System.out.print(current.data + " ");

	        if (current.right != null) stack.push(current.right);
	        if (current.left != null) stack.push(current.left);
	    }
	}


	
	//LNR
	private static void inorder(Node root) {
	    if (root == null) return;

	    LinkedList<Node> stack = new LinkedList<>();
	    Node current = root;

	    while (current != null || !stack.isEmpty()) {
	        while (current != null) {
	            stack.push(current);
	            current = current.left;
	        }
	        current = stack.pop();
	        System.out.print(current.data + " ");
	        current = current.right;
	    }
	}

	
	//LRN
	private static void postorder(Node root) {
	    if (root == null) return;

	    LinkedList<Node> stack1 = new LinkedList<>();
	    LinkedList<Node> stack2 = new LinkedList<>();
	    stack1.push(root);

	    while (!stack1.isEmpty()) {
	        Node current = stack1.pop();
	        stack2.push(current);

	        if (current.left != null) stack1.push(current.left);
	        if (current.right != null) stack1.push(current.right);
	    }

	    while (!stack2.isEmpty()) {
	        System.out.print(stack2.pop().data + " ");
	    }
	}

	public static void main(String[] args) {

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);

		root.left.left = new Node(4);
		root.left.right = new Node(5);

		root.right.left = new Node(6);
		root.right.right = new Node(7);

		System.out.print("\nPreorder: ");
		preorder(root);

		System.out.print("\nInorder: ");
		inorder(root);

		System.out.print("\nPostorder: ");
		postorder(root);

	}

	

	
}
