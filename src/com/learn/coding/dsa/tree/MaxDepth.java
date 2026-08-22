package com.learn.coding.dsa.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaxDepth {

	public static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
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

		root.left.left.left = new Node(6);

		System.out.println("Node Depth hieght : "  + maxNodeDepthDFSRecursion(root));
		System.out.println("Edge Depth height : " + maxEdgeDepthDFSRecursion(root));
		System.out.println(maxDepthBFS(root));

	}

	private static int maxNodeDepthDFSRecursion(Node root) {
		// node base

		if (root == null)
			return 0;

		int leftDepth = maxNodeDepthDFSRecursion(root.left);
		int rightDepth = maxNodeDepthDFSRecursion(root.right);

		return 1 + Math.max(leftDepth, rightDepth);
	}
	
	private static int maxEdgeDepthDFSRecursion(Node root) {
	    // Edge-based height: empty tree = -1, leaf node = 0
	    if (root == null)
	        return -1;

	    int leftHeight = maxEdgeDepthDFSRecursion(root.left);
	    int rightHeight = maxEdgeDepthDFSRecursion(root.right);

	    return 1 + Math.max(leftHeight, rightHeight);
	}

	private static int maxDepthBFS(Node root) {

		if (root == null)
			return 0;

		Queue<Node> queue = new ArrayDeque<MaxDepth.Node>();
		
		queue.offer(root);
		int depth =0;
		
		while(!queue.isEmpty()) {
			
			int level = queue.size();
			
			for (int i = 0; i < level; i++) {
				
				Node current = queue.poll();
				
				if(current.left!=null) {
					queue.offer(current.left);
				}
				
				if(current.right!=null) {
					queue.offer(current.right);
				}
			}
			
			depth++;			
		}
		
		return depth;

	}

}
