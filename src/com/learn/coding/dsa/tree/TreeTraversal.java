package com.learn.coding.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {

	static class Node {

		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

	}

	private static void inorder(Node root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return;
		}
		
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);

	}

	private static void postorder(Node root) {
		// TODO Auto-generated method stub
		
		if(root==null) return;
		
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data + " ");

	}

	private static void preorder(Node root) {
		// TODO Auto-generated method stub
		//NLR
		
		if(root==null) {
			return;
		}
		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);

	}

	private static void bfs(Node root) {
		// TODO Auto-generated method stub

		if (root == null) {
			return;
		}

		Queue<Node> queue = new LinkedList<TreeTraversal.Node>();

		queue.offer(root);

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			System.out.print(current.data + " ");

			if (current.left != null) {
				queue.offer(current.left);
			}

			if (current.right != null) {
				queue.offer(current.right);

			}
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

		System.out.print("BFS: ");
		bfs(root);

		System.out.print("\nPreorder: ");
		preorder(root);

		System.out.print("\nInorder: ");
		inorder(root);

		System.out.print("\nPostorder: ");
		postorder(root);
	}

	/*
	 * 
	 *  BFS:       1 2 3 4 5 6 7

		Preorder:  1 2 4 5 3 6 7

		Inorder:   4 2 5 1 6 3 7

		Postorder: 4 5 2 6 7 3 1
	 * 
	 * 
	 */
	
}
