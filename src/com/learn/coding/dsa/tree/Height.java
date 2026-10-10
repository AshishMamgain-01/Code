package com.learn.coding.dsa.tree;

public class Height {

	// number of nodes on the longest path:
	public static class TreeNode {

		int data;
		TreeNode left;
		TreeNode right;

		TreeNode(int data) {
			this.data = data;
		}

	}

	// number of nodes on the longest path:
	static int height(TreeNode root) {
		if (root == null)
			return 0;
		return 1 + Math.max(height(root.left), height(root.right));
	}

	// number of edges:
	static int longestPathEdges(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(height(root.left), height(root.right)) + 1 - 1;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		System.out.println("Height as per number of nodes on the longest path: " + height(root));
		System.out.println("Height as per number of edges: " + longestPathEdges(root));

	}

}
