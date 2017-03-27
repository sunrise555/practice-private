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

		rbt.put(12);
		rbt.put(1);
		rbt.put(9);
		rbt.put(2);
		rbt.put(0);
		rbt.put(11);
		rbt.put(7);
		rbt.put(19);
		rbt.put(4);
		rbt.put(15);
		rbt.put(18);
		rbt.put(5);
		rbt.put(14);
		rbt.put(13);
		rbt.put(10);
		rbt.put(16);
		rbt.put(6);
		rbt.put(3);
		rbt.put(8);
		rbt.put(17);
		rbt.midOrder(rbt.root);
		System.out.println();
		System.out.println(rbt.height(rbt.root));
		rbt.delete(rbt.contains(12));
		rbt.delete(rbt.contains(1));
		rbt.delete(rbt.contains(9));
		rbt.delete(rbt.contains(2));
		rbt.delete(rbt.contains(0));
		rbt.delete(rbt.contains(11));
		rbt.delete(rbt.contains(7));
		rbt.delete(rbt.contains(19));
		rbt.delete(rbt.contains(4));
		rbt.delete(rbt.contains(15));
		rbt.delete(rbt.contains(18));
		rbt.delete(rbt.contains(5));
		rbt.delete(rbt.contains(14));
		rbt.delete(rbt.contains(13));
		rbt.delete(rbt.contains(10));
		rbt.delete(rbt.contains(16));
		rbt.delete(rbt.contains(6));
		rbt.delete(rbt.contains(3));
		rbt.delete(rbt.contains(8));
		rbt.delete(rbt.contains(17));
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
			root = new RBNode<>(data, BLACK);
			return;
		}

		RBNode<T> cur = root;
		while (true) {
			// 如果 data比当前结点的值小
			if (data.compareTo(cur.data) < 0) {
				if (cur.left != null) {
					cur = cur.left;
				} else {
					cur.left = new RBNode<>(data, RED);
					cur.left.parent = cur;
					break;
				}
			} else {
				if (cur.right != null) {
					cur = cur.right;
				} else {
					cur.right = new RBNode<>(data, RED);
					cur.right.parent = cur;
					break;
				}
			}
		}
		fixAfterPut(cur);
	}

	// 插入后作调整，如果父节点为红色，则说明必定有父节点
	// #传入的是父节点
	private void fixAfterPut(RBNode<T> cur) {
		if (isRed(cur)) {
			// 节点为左孩子
			if (cur.parent.left == cur) {
				// 叔叔为红色
				if (isRed(cur.parent.right))
					flipColors(cur);
				// 叔叔为黑色
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
		// 节点为null与节点为黑色是同一效果
		return node != null && node.color == RED;
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
			if (t.compareTo(current.data) == 0) {
				return current;
			}
			// 归于左子树
			else if (t.compareTo(current.data) < 0) {
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
		if (cur == null)
			return;
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

	/**
	 * 删除操作（非递归）
	 * @param n
	 */
	public void delete(RBNode<T> n) {
		if (n == null || null == contains(n.data))
			return;

		RBNode<T> p = n.parent;
		// 待删除节点有2个外部子节点
		if (n.left != null && n.right != null) {
			// 后继节点
			RBNode<T> back = successor(n);
			n.data = back.data;
			delete(back);
			return;
		}
		// 待删除节点没有外部子节点
		else if (n.left == null && n.right == null) {
			if (isLeft(n))
				p.left = null;
			else if (isRight(n))
				p.right = null;
			else {// 说明待删除的节点子节点为null，且父节点为null，此时树中仅剩根节点
				root = null;
				return;
			}
		}
		// 待删除节点只有一个外部子节点
		else {
			if (isLeft(n)) {
				p.left = n.left == null?n.right:n.left;
				p.left.parent = p;
			}
			else if (isRight(n)) {
				p.right = n.left == null?n.right:n.left;
				p.right.parent = p;
			}
			// n为根节点
			else {
				root = n.left == null?n.right:n.left;
				root.parent = null;
				root.color = BLACK;
				return;
			}
			if (isRed(n)) {
				return;
			}
			// 删除节点为黑色，且有1个红色子节点，将子节点颜色变黑,结束算法
			else if (isRed(n.left)) {
				n.left.color = BLACK;
				return;
			}else if (isRed(n.right)) {
				n.right.color = BLACK;
				return;
			}else {
				// 子节点一定为黑色,子节点作为新的双黑节点
				n = n.left == null?n.right:n.left;
			}
		}

		if (!isRed(n))
			fixAfterDelete(n,p);
	}

	// 双黑节点出现后的调整,输入节点及其父节点
	private void fixAfterDelete(RBNode<T> n, RBNode<T> p) {
		// 兄弟节点，一定不为null
		RBNode<T> brother;
		if (p.left == null || p.right == null)
			brother = p.left == null?p.right:p.left;
		else
			brother = isLeft(n)?p.right:p.left;
		// 兄弟节点为红色，先做颜色变换和旋转操作
		if (isRed(brother)) {
			// 父节点与兄弟交换颜色
			boolean temp = p.color;
			p.color = brother.color;
			brother.color = temp;
			// 兄弟在右，则左旋；反之
			if (isRight(brother))
				left_rotate(p);
			else
				right_rotate(p);
			// 得到新的兄弟节点
			brother = p.left == null?p.right:p.left;
		}
		// 兄弟节点存在红色子节点
		if (isRed(brother.left) || isRed(brother.right)) {
			// 红色子节点
			RBNode<T> redC = isRed(brother.left)?brother.left:brother.right;
			fixWhenBrotherHasRed(p,brother,redC);
		}
		//兄弟节点存在2个黑色子节点，包含NIL节点
		else {
			brother.color = RED;
			if (isRed(p)) {
				p.color = BLACK;
			}else {
				// 若父节点原来为black，则父节点作为新的双黑节点继续调整，直到根节点
				if (p.parent != null)
					fixAfterDelete(p,p.parent);
			}
		}
	}

	// 当兄弟节点存在红色子节点的情况下进行调整
	// 输入参数：父节点、兄弟节点、红色子节点
	private void fixWhenBrotherHasRed(RBNode<T> p, RBNode<T> b, RBNode<T> c) {
		if (isRight(b) && isLeft(c)) {
			right_rotate(b);
			// 近侄子和兄弟互换，变为远侄子情况
			RBNode<T> temp;
			temp = c;
			c = b;
			b = temp;
		}
		if (isLeft(b) && isRight(c)) {
			left_rotate(b);
			// 近侄子和兄弟互换，变为远侄子情况
			RBNode<T> temp;
			temp = c;
			c = b;
			b = temp;
		}
		b.color = p.color;
		p.color = BLACK;
		c.color = BLACK;
		if (isRight(b))
			left_rotate(p);
		else
			right_rotate(p);
	}

	/**
	 * 查找中序后继
	 * @param n
	 * @return
	 */
	private RBNode<T> successor(RBNode<T> n) {
		if (null == n)
			return null;

		RBNode<T> p;
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

	private boolean isLeft(RBNode<T> n) {
		if (n == null || n.parent == null)
			return false;
		return n.parent.left == n;
	}

	private boolean isRight(RBNode<T> n) {
		if (n == null || n.parent == null)
			return false;
		return n.parent.right == n;
	}
}
