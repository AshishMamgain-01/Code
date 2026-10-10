package com.learn.coding.dsa.tree;

import com.learn.coding.dsa.tree.Height.TreeNode;

public class LCA {

	static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		if (root == null || root == p || root == q) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);

		TreeNode right = lowestCommonAncestor(root.right, p, q);

		if (left != null && right != null) {
			return root;
		}

		return left != null ? left : right;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		
		 TreeNode p = root.right.left;          // Node 6
	     TreeNode q = root.left.right;         // Node 5
	     TreeNode lca1 = LCA.lowestCommonAncestor(root, p, q);
	     System.out.println("LCA of 6 and 5: " + lca1.data);
		
		

	}

}
