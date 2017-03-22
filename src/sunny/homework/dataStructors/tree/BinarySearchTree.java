package sunny.homework.dataStructors.tree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by sunny_hbqq on 2017/3/18.
 */
public class BinarySearchTree<T  extends Comparable<T>> {
	public Node<T> root;

	/**
	 * 插入节点
	 * @param i
	 * @return
	 */
	public boolean insert(T i) {
		if (root == null) {
			root = new Node<T>(i);
			root.parent = null;
			return true;
		}

		Node<T> current = root;
		while (true) {
			// 如果 i 比当前结点的值小
			if (i.compareTo((T) current.data) < 0) {
				if (current.left != null) {
					current = current.left;
				} else {
					current.left = new Node<T>(i);
					current.left.parent = current;
					break;
				}
			} else {
				if (current.right != null)
					current = current.right;
				else {
					current.right = new Node<T>(i);
					current.right.parent = current;
					break;
				}
			}
		}
		return true;
	}

	/**
	 * 检查节点t是否在查找树中
	 * 
	 * @param t
	 * @return 节点t在树中返回true
	 */
	public boolean contains(T t) {

		Node<T> current = root;

		while (current != null) {
			// 找到
			if (t.compareTo((T) current.data) == 0) {
				return true;
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
		return false;
	}

	/**
	 * 递归型前序遍历
	 * 
	 * @param cur
	 */
	public void preOrder(Node<T> cur) {
		System.out.print(cur.data + " ");
		// Node leftNode = cur.left;
		if (cur.left != null) {
			preOrder(cur.left);
		}
		if (cur.right != null) {
			preOrder(cur.right);
		}
	}

	/**
	 * 递归型中序遍历
	 * 
	 * @param cur
	 */
	public void midOrder(Node<T> cur) {
		if (cur.left != null)
			midOrder(cur.left);
		System.out.print(cur.data + " ");
		if (cur.right != null)
			midOrder(cur.right);
	}

	/**
	 * 递归型后序遍历
	 * 
	 * @param cur
	 */
	public void postOrder(Node<T> cur) {
		if (cur.left != null) {
			postOrder(cur.left);
		}
		if (cur.right != null) {
			postOrder(cur.right);
		}
		System.out.print(cur.data + " ");
	}

	/**
	 * 递归型层次遍历
	 */
	public void levelOrder() {
		int depth = Depth(root);
		for (int i = 0; i <= depth; i++) {
			NodeAtLevel(root, i);
		}
	}

	/**
	 * 获取查找树的深度
	 * 
	 * @param cur
	 * @return
	 */
	public int Depth(Node<T> cur) {
		if (null == cur)
			return 0;

		int leftDepth = Depth(cur.left);
		int rightDepth = Depth(cur.right);
		return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
	}

	private void NodeAtLevel(Node<T> cur, int level) {
		if (cur == null || level < 1)
			return;
		if (level == 1) {
			System.out.print(cur.data + " ");
			return;
		}

		NodeAtLevel(cur.left, level - 1);
		NodeAtLevel(cur.right, level - 1);

	}

	/**
	 * 非递归中序遍历
	 */
	public void midOrderWithoutRecurs() {
		if (root == null)
			return;

		Stack<Node<T>> nodes = new Stack<Node<T>>();
		Node<T> current;

		nodes.push(root);

		while (!nodes.isEmpty()) {
			current = nodes.peek();
			if (current.state == 0) {
				if (current.left != null)
					nodes.push(current.left);
				current.state = 1;
			} else if (current.state == 1) {
				System.out.print(current.data);
				System.out.print(" ");
				current.state = 2;
			} else if (current.state == 2) {
				if (current.right != null)
					nodes.push(current.right);
				current.state = 3;
			} else if (current.state == 3) {
				nodes.pop();
				current.state = 0;
			}
		}
	}

	/**
	 * 非递归前序遍历
	 */
	public void preOrderWithoutRecurs() {
		if (root == null)
			return;

		Stack<Node<T>> nodes = new Stack<Node<T>>();
		Node<T> current;

		nodes.push(root);

		while (!nodes.isEmpty()) {
			current = nodes.peek();
			if (current.state == 0) {
				System.out.print(current.data);
				System.out.print(" ");
				current.state = 1;
			} else if (current.state == 1) {
				if (current.left != null)
					nodes.push(current.left);
				current.state = 2;
			} else if (current.state == 2) {
				if (current.right != null)
					nodes.push(current.right);
				current.state = 3;
			} else if (current.state == 3) {
				nodes.pop();
				current.state = 0;
			}
		}
	}

	/**
	 * 非递归型后序遍历
	 */
	public void postOrderWithoutRecurs() {
		if (root == null)
			return;

		Stack<Node<T>> nodes = new Stack<Node<T>>();
		Node<T> current;

		nodes.push(root);

		while (!nodes.isEmpty()) {
			current = nodes.peek();
			if (current.state == 0) {
				if (current.left != null)
					nodes.push(current.left);
				current.state = 1;
			} else if (current.state == 1) {
				if (current.right != null)
					nodes.push(current.right);
				current.state = 2;
			} else if (current.state == 2) {
				System.out.print(current.data);
				System.out.print(" ");
				current.state = 3;
			} else if (current.state == 3) {
				nodes.pop();
				current.state = 0;
			}
		}
	}

	/**
	 * 非递归型按层遍历
	 */
	public void levelOrderWithoutRecurs() {
		if (root == null)
			return;
		Queue<Node<T>> queue = new ArrayDeque<Node<T>>();
		Node<T> cur;

		queue.add(root);

		while (!queue.isEmpty()) {
			cur = queue.peek();
			if (cur.state == 0) {
				System.out.print(cur.data + " ");
				cur.state = 1;
			} else if (cur.state == 1) {
				if (cur.left != null)
					queue.add(cur.left);
				cur.state = 2;
			} else if (cur.state == 2) {
				if (cur.right != null)
					queue.add(cur.right);
				cur.state = 3;
			} else if (cur.state == 3) {
				queue.poll();
				cur.state = 0;
			}
		}

	}

	/**
	 * 查找中序后继
	 * 
	 * @param n
	 * @return
	 */
	private Node<T> successor(Node<T> n) {
		if (null == n)
			return null;

		Node<T> p;
		if (n.right != null) {
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

	public Iterator<Node<T>> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<Node<T>> {
		Node<T> cursor = head();
		Node<T> next;

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public Node<T> next() {
			next = cursor;
			cursor = successor(cursor);
			return next;
		}

		private Node<T> head() {
			Node<T> head = root;
			if (head == null)
				return null;
			while (head.left != null)
				head = head.left;
			return head;
		}

	}

	/**
	 * 若节点存在，按照中序遍历的顺序删除节点
	 * @param n 待删除的节点
	 * @return 删除成功，返回true
	 */
	public boolean removeInMidOrder(Node<T> n) {
		//节点不存在
		if(!contains(n.data))
			return false;
		Node<T> p = n.parent;
		//如果n为叶子节点，直接删除
		if (n.left == null && n.right == null){
			if (n == root) {
				root = null;
				return true;
			}
			
			if(p.left == n) {
				p.left = null;
				return true;
			}else if(p.right == n) {
				p.right = null;
				return true;
			}
		}
		//当前节点有2个孩子节点
		else if(n.left != null && n.right != null) {
			Node<T> next = successor(n);
			n.data = next.data;
			removeInMidOrder(next);
		}
		//节点只有一个孩子节点
		else {
			Node<T> child = n.left == null?n.right:n.left;
			if (n == root) {
				child.parent = null;
				root = child;
				return true;
			}
			
			child.parent = p;
			if(n == p.left) {
				p.left = child;
				return true;
			}else {
				p.right = child;
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(3);
		bst.insert(4);
		bst.insert(1);
		bst.insert(12);
		// bst.preOrder(bst.root);
		// System.out.println();
		// bst.preOrderWithoutRecurs();
		// System.out.println();
//		System.out.println("中序遍历结果：");
//		bst.midOrder(bst.root);
//		System.out.println();
//		Node<Integer> cur = bst.head();
//		while (cur != null) {
//			System.out.print(cur.data + " ");
//			cur = bst.successor(cur);
//
//		}
		System.out.println("中序迭代器结果：");
		Iterator<Node<Integer>> itr = bst.iterator();
		while (itr.hasNext())
			System.out.print(itr.next().data + " ");
		System.out.println();
		System.out.println("删除节点2的结果：");
		Iterator<Node<Integer>> itr2 = bst.iterator();
		Node<Integer> n;
		while (itr2.hasNext()) {
			n = itr2.next();
			if(n.data == 7) {
				bst.removeInMidOrder(n);
				break;
			}
		}
		Iterator<Node<Integer>> itr3 = bst.iterator();
		while (itr3.hasNext())
			System.out.print(itr3.next().data + " ");
		// bst.midOrderWithoutRecurs();
		// System.out.println();
		// bst.postOrder(bst.root);
		// System.out.println();
		// bst.postOrderWithoutRecurs();

		// bst.levelOrderWithoutRecurs();
		// System.out.println();
		// bst.levelOrder();
		 
	}

}
