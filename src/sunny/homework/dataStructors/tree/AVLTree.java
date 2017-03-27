package sunny.homework.dataStructors.tree;

/**
 * 平衡二叉树类 
 * @Created by Sunny on 2017年3月23日
 */
public class AVLTree<T extends Comparable<T>> {
	private AVLNode<T> root;

	public static void main(String[] args) {
		AVLTree<Integer> avl = new AVLTree<Integer>();
		avl.insert(5);
		avl.insert(2);
		avl.insert(6);
		avl.insert(2);
		avl.insert(2);
		avl.insert(4);
		avl.midOrder(avl.root);
		avl.delete(avl.contains(6));
		System.out.println();
		avl.midOrder(avl.root);

	}

	/**
	 * 插入值为data的节点
	 * 
	 * @param data
	 *            节点的值
	 * @return 插入成功返回true
	 * @Created 2017年3月23日
	 */
	public void insert(T data) {
		if (contains(data) != null)
			return;
		if (root == null) {
			root = new AVLNode<T>(data);
			return;
		}
		AVLNode<T> cur = root;
		while (true) {
			// 如果 data比当前结点的值小
			if (data.compareTo(cur.data) < 0) {
				if (cur.left != null) {
					cur = cur.left;
				} else {
					cur.left = new AVLNode<T>(data);
					cur.left.parent = cur;
					break;
				}
			} else {
				if (cur.right != null) {
					cur = cur.right;
				} else {
					cur.right = new AVLNode<T>(data);
					cur.right.parent = cur;
					break;
				}
			}
		}
		/*
		 * 插入后可能改变某个子树的高度，从而使节点的平衡因子大于2 通过旋转操作对树进行调整；
		 */
		root.balance = calBalance(root);

		// 左子树高度大于右子树，右旋
		if (root.balance >= 2) {
			// 左子树的右孙高度大于左孙，先左旋左子树
			if ((root.left.balance = calBalance(root.left)) < 0)
				left_rotate(root.left);
			right_rotate(root);
		}
		// 右子树高度大于左子树，左旋
		else if (root.balance <= -2) {
			// 右子树的左孙高度大于右孙，先右旋右子树
			if ((root.right.balance = calBalance(root.right)) > 0)
				right_rotate(root.right);
			left_rotate(root);
		}
	}

	// 右旋操作
	private void right_rotate(AVLNode<T> cur) {
		AVLNode<T> parent = cur.parent;
		AVLNode<T> leftSon = cur.left;
		AVLNode<T> rightGrandSon = cur.left.right;
		if (leftSon == null)
			return;
		leftSon.parent = parent;
		if (parent != null) {
			if (parent.left == cur)
				parent.left = cur.left;
			else if (parent.right == cur)
				parent.right = cur.left;
		}
		cur.parent = leftSon;
		leftSon.right = cur;

		cur.left = rightGrandSon;
		if (rightGrandSon != null)
			rightGrandSon.parent = cur;

		if (parent == null)
			root = leftSon;
		// 重新计算节点的深度

	}

	// 左旋操作
	private void left_rotate(AVLNode<T> cur) {
		AVLNode<T> parent = cur.parent;
		AVLNode<T> rightSon = cur.right;
		AVLNode<T> leftGrandSon = cur.right.left;
		if (rightSon == null)
			return;
		rightSon.parent = parent;
		if (parent != null) {
			if (parent.left == cur)
				parent.left = rightSon;
			else if (parent.right == cur)
				parent.right = rightSon;
		}
		cur.parent = rightSon;
		rightSon.left = cur;

		cur.right = leftGrandSon;
		if (leftGrandSon != null)
			leftGrandSon.parent = cur;

		if (parent == null)
			root = rightSon;
	}

	// 计算平衡因子
	private int calBalance(AVLNode<T> cur) {
		int balance = 0;
		if (cur.left == null) {
			if (cur.right == null)
				return 0;
			else
				balance = 0 - height(cur.right);
		} else {
			if (cur.right == null)
				balance = height(cur.left);
			else
				balance = height(cur.left) - height(cur.right);
		}
		return balance;
	}

	// 计算当前节点高度
	private int height(AVLNode<T> cur) {
		if (null == cur)
			return 0;

		int leftHeight = height(cur.left);
		int rightHeight = height(cur.right);
		return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
	}
	/**
	 * 检查节点t是否在查找树中
	 * 
	 * @param t
	 * @return 若存在返回该节点,不存在则返回null
	 */
	public AVLNode<T> contains(T t) {

		AVLNode<T> current = root;

		while (current != null) {
			// 找到
			if (t.compareTo((T) current.data) == 0) {
				return current;
			}
			// 归于左子树
			else if (t.compareTo((T) current.data) < 0) {
				current = current.left;
			}
			// 归于右子树
			else {
				current = current.right;
			}
		}
		return null;
	}

	/**
	 * 删除节点，按照中序遍历的顺利
	 * @param delNode 待删除的节点
	 * @Created 2017年3月23日
	 *
	 */
	public void delete(AVLNode<T> delNode) {
		//节点不存在
		if(delNode == null || contains(delNode.data) == null)
			return;	
	
		AVLNode<T> p = delNode.parent;
		//如果n为叶子节点，直接删除
		if (delNode.left == null && delNode.right == null){
			if (delNode == root) {
				root = null;
			}else if(p.left == delNode) {
				p.left = null;
			}else if(p.right == delNode) {
				p.right = null;
			}
		}
		//当前节点有2个孩子节点
		else if(delNode.left != null && delNode.right != null) {
			AVLNode<T> next = successor(delNode);
			delNode.data = next.data;
			delete(next);
		}
		//节点只有一个孩子节点
		else {
			AVLNode<T> child = delNode.left == null?delNode.right:delNode.left;
			if (delNode == root) {
				child.parent = null;
				root = child;
			}else{
				child.parent = p;
				if(delNode == p.left) {
					p.left = child;
				}else {
					p.right = child;
				}
			}
		}
		
		AVLNode<T> cur = p;
		// 删除后若不平衡，则需旋转操作，沿路径向上回溯
		while(cur != null) { 
			cur.balance = calBalance(cur);
			// 左子树高度大于右子树，右旋
			if (cur.balance >= 2) {
				// 左子树的右孙高度大于左孙，先左旋左子树
				if ((cur.left.balance = calBalance(cur.left)) < 0)
					left_rotate(cur.left);
				right_rotate(cur);
			}
			// 右子树高度大于左子树，左旋
			else if (cur.balance <= -2) {
				// 右子树的左孙高度大于右孙，先右旋右子树
				if ((cur.right.balance = calBalance(cur.right)) > 0)
					right_rotate(cur.right);
				left_rotate(cur);
			}
			cur = cur.parent;
		}
		
	}
	
	/*
	 * 查找中序后继节点
	 * @param n 当前节点
	 * @return 中序后继节点
	 * @Created 2017年3月23日
	 *
	 */
	private AVLNode<T> successor(AVLNode<T> n) {
		if (null == n)
			return null;

		AVLNode<T> p;
		if (n.right != null) {
			// 右子树中的最小值
			p = n.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;
		} else {
			p = n.parent;
			// 父节点不为null，且当前节点是父节点的右孩子
			while (p != null && p.left != n) {
				n = p;
				p = n.parent;
			}
			// 若当前节点是父节点的左孩子，直接返回父节点
			return p;
		}
	}
	/**
	 * 递归型中序遍历
	 * 
	 * @param cur
	 */
	public void midOrder(AVLNode<T> cur) {
		if (cur.left != null)
			midOrder(cur.left);
		System.out.print(cur.data + " ");
		if (cur.right != null)
			midOrder(cur.right);
	}
}
