package com.learn.coding.dsa.tree;

public class Traversal_DFS_Recursion {

	static class Node {

		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

	}

	private static void inorder(Node root) {
		
		// LNR
		if (root == null) {
			return;
		}

		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);

	}

	private static void postorder(Node root) {
		
		// LRN
		if (root == null)
			return;

		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data + " ");

	}

	private static void preorder(Node root) {
		
		// NLR
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);

	}

	public static void main(String[] args) {

		Node root = new Node(1);

		root.left = new Node(2);
		root.right = new Node(3);

		root.left.left = new Node(4);
		root.left.right = new Node(5);

		root.right.left = new Node(6);
		root.right.right = new Node(7);

		System.out.print("DFS Preorder: ");
		preorder(root);

		System.out.print("\n DFS Inorder: ");
		inorder(root);

		System.out.print("\nDFS Postorder: ");
		postorder(root);
	}

	/*
	 * 
	 * BFS: 1 2 3 4 5 6 7
	 * 
	 * Preorder: 1 2 4 5 3 6 7
	 * 
	 * Inorder: 4 2 5 1 6 3 7
	 * 
	 * Postorder: 4 5 2 6 7 3 1
	 * 
	 * 
	 */

}
