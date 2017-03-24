package sunny.homework.dataStructors.tree;

/**
 * 红黑树类
 * 
 * @Created by Sunny on 2017年3月24日
 */
public class RBTree<T extends Comparable<T>> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	public RBNode<T> root;

	public static void main(String[] args) {
		RBTree<Integer> rbt = new RBTree<Integer>();
		rbt.put(1);
		rbt.put(2);
		rbt.put(3);
		rbt.put(4);
		rbt.put(5);
		rbt.put(6);
		rbt.put(7);
		rbt.put(8);
		rbt.midOrder(rbt.root);
		System.out.println();
		System.out.println(rbt.height(rbt.root));
	}

	/**
	 * 插入
	 * 
	 * @param data
	 * @Created 2017年3月24日
	 *
	 */
	public void put(T data) {
		if (contains(data) != null)
			return;
		if (root == null) {
			root = new RBNode<T>(data, BLACK);
			return;
		}

		RBNode<T> cur = root;
		while (true) {
			// 如果 data比当前结点的值小
			if (data.compareTo(cur.data) < 0) {
				if (cur.left != null) {
					cur = cur.left;
				} else {
					cur.left = new RBNode<T>(data, RED);
					cur.left.parent = cur;
					break;
				}
			} else {
				if (cur.right != null) {
					cur = cur.right;
				} else {
					cur.right = new RBNode<T>(data, RED);
					cur.right.parent = cur;
					break;
				}
			}
		}
		fixAfterPut(cur);
	}

	// 插入后作调整，如果父节点为红色，则说明必定有父节点 
	//#传入的是父节点
	private void fixAfterPut(RBNode<T> cur) {
		if (isRed(cur)) {
			// 节点为左孩子
			if (cur.parent.left == cur) {
				// 叔叔为红色
				if (isRed(cur.parent.right))
					flipColors(cur);
				//叔叔为黑色
				else {
					if (isRed(cur.left)) {
						cur.color = BLACK;
						cur.parent.color = RED;
						right_rotate(cur.parent);
					} else if (isRed(cur.right)) {
						RBNode<T> temp = left_rotate(cur);
						temp.color = BLACK;
						temp.parent.color = RED;
						right_rotate(temp.parent);
					}
				}
			}
			// 节点为右孩子
			else {
				// 叔叔为红色
				if (isRed(cur.parent.left))
					flipColors(cur);
				// 叔叔为黑色
				else {
					if (isRed(cur.right)) {
						cur.color = BLACK;
						cur.parent.color = RED;
						left_rotate(cur.parent);
					} else if (isRed(cur.left)) {
						RBNode<T> temp = right_rotate(cur);
						temp.color = BLACK;
						temp.parent.color = RED;
						left_rotate(temp.parent);
					}
				}
			}
		}
	}

	// 判断color
	private boolean isRed(RBNode<T> node) {
		if (node == null) // 节点为null与节点为黑色是同一效果
			return false;
		return node.color == RED;
	}

	// 颜色变换
	private void flipColors(RBNode<T> cur) {
		// 将自己、兄弟变黑，父亲变红
		cur.parent.left.color = BLACK;
		cur.parent.right.color = BLACK;
		cur.parent.color = RED;
		// 若为根节点
		if (cur.parent.parent == null) {
			cur.parent.color = BLACK;
			return;
		}
		// 将父亲作为新插入的节点，继续做调整
		fixAfterPut(cur.parent.parent);
	}

	// 右旋操作
	private RBNode<T> right_rotate(RBNode<T> cur) {
		RBNode<T> parent = cur.parent;
		RBNode<T> leftSon = cur.left;
		RBNode<T> rightGrandSon = cur.left.right;
		if (leftSon == null)
			return null;
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
		return leftSon;
	}

	// 左旋操作
	private RBNode<T> left_rotate(RBNode<T> cur) {
		RBNode<T> parent = cur.parent;
		RBNode<T> rightSon = cur.right;
		RBNode<T> leftGrandSon = cur.right.left;
		if (rightSon == null)
			return null;
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
		return rightSon;
	}

	/**
	 * 检查节点t是否在查找树中
	 * 
	 * @param t
	 * @return 节点t在树中返回该节点，不存在返回null
	 */
	public RBNode<T> contains(T t) {
		RBNode<T> current = root;

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
	 * 递归型中序遍历
	 * 
	 * @param cur
	 */
	public void midOrder(RBNode<T> cur) {
		if (cur.left != null)
			midOrder(cur.left);
		System.out.print(cur.data + " ");
		if (cur.right != null)
			midOrder(cur.right);
	}
	
	// 计算当前节点高度
	private int height(RBNode<T> cur) {
		if (null == cur)
			return 0;

		int leftHeight = height(cur.left);
		int rightHeight = height(cur.right);
		return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
	}
}
