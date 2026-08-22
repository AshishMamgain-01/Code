package com.learn.coding.dsa.tree;

import java.util.ArrayDeque;
import java.util.Currency;
import java.util.Deque;
import java.util.Stack;

public class DFSIterative {

	public static class Node {

		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);

		root.left.left = new Node(4);
		root.left.right = new Node(5);

		root.right.left = new Node(6);
		root.right.right = new Node(7);

		//System.out.print("BFS: ");
		//bfs(root);

		System.out.print("\nPreorder: ");
		preorder(root);

		//System.out.print("\nInorder: ");
		//inorder(root);

		//System.out.print("\nPostorder: ");
		//postorder(root);

	}

	private static void postorder(Node root) {
		// TODO Auto-generated method stub

	}


	private static void preorder(Node root) {
		//NLR		

		if (root == null)
			return;

		Deque<Node> dqueue = new ArrayDeque<Node>();

		dqueue.offer(root);

		while (!dqueue.isEmpty()) {

			Node current = dqueue.poll();

			System.out.print(current.data + " ");

			if (current.right != null) {
				dqueue.offer(current.right);

			}
			if (current.left != null) {
				dqueue.offer(current.left);
			}

		}

	}

	private static void bfs(Node root) {
		

	}

	private static void inorder(Node root) {
		// TODO Auto-generated method stub

	}

}
