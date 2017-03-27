package sunny.homework.dataStructors.tree;
/**
 * �������
 * 
 * @Created by Sunny on 2017��3��24��
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
	 * ����
	 * 
	 * @param data
	 * @Created 2017��3��24��
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
			// ��� data�ȵ�ǰ����ֵС
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

	// �������������������ڵ�Ϊ��ɫ����˵���ض��и��ڵ�
	// #������Ǹ��ڵ�
	private void fixAfterPut(RBNode<T> cur) {
		if (isRed(cur)) {
			// �ڵ�Ϊ����
			if (cur.parent.left == cur) {
				// ����Ϊ��ɫ
				if (isRed(cur.parent.right))
					flipColors(cur);
				// ����Ϊ��ɫ
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
			// �ڵ�Ϊ�Һ���
			else {
				// ����Ϊ��ɫ
				if (isRed(cur.parent.left))
					flipColors(cur);
				// ����Ϊ��ɫ
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
	// �ж�color
	private boolean isRed(RBNode<T> node) {
		// �ڵ�Ϊnull��ڵ�Ϊ��ɫ��ͬһЧ��
		return node != null && node.color == RED;
	}

	// ��ɫ�任
	private void flipColors(RBNode<T> cur) {
		// ���Լ����ֵܱ�ڣ����ױ��
		cur.parent.left.color = BLACK;
		cur.parent.right.color = BLACK;
		cur.parent.color = RED;
		// ��Ϊ���ڵ�
		if (cur.parent.parent == null) {
			cur.parent.color = BLACK;
			return;
		}
		// ��������Ϊ�²���Ľڵ㣬����������
		fixAfterPut(cur.parent.parent);
	}

	// ��������
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

	// ��������
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
	 * ���ڵ�t�Ƿ��ڲ�������
	 * 
	 * @param t
	 * @return �ڵ�t�����з��ظýڵ㣬�����ڷ���null
	 */
	public RBNode<T> contains(T t) {
		RBNode<T> current = root;

		while (current != null) {
			// �ҵ�
			if (t.compareTo(current.data) == 0) {
				return current;
			}
			// ����������
			else if (t.compareTo(current.data) < 0) {
				current = current.left;
			}
			// ����������
			else {
				current = current.right;
			}
		}
		return null;
	}

	/**
	 * �ݹ����������
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

	// ���㵱ǰ�ڵ�߶�
	private int height(RBNode<T> cur) {
		if (null == cur)
			return 0;

		int leftHeight = height(cur.left);
		int rightHeight = height(cur.right);
		return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
	}

	/**
	 * ɾ���������ǵݹ飩
	 * @param n
	 */
	public void delete(RBNode<T> n) {
		if (n == null || null == contains(n.data))
			return;

		RBNode<T> p = n.parent;
		// ��ɾ���ڵ���2���ⲿ�ӽڵ�
		if (n.left != null && n.right != null) {
			// ��̽ڵ�
			RBNode<T> back = successor(n);
			n.data = back.data;
			delete(back);
			return;
		}
		// ��ɾ���ڵ�û���ⲿ�ӽڵ�
		else if (n.left == null && n.right == null) {
			if (isLeft(n))
				p.left = null;
			else if (isRight(n))
				p.right = null;
			else {// ˵����ɾ���Ľڵ��ӽڵ�Ϊnull���Ҹ��ڵ�Ϊnull����ʱ���н�ʣ���ڵ�
				root = null;
				return;
			}
		}
		// ��ɾ���ڵ�ֻ��һ���ⲿ�ӽڵ�
		else {
			if (isLeft(n)) {
				p.left = n.left == null?n.right:n.left;
				p.left.parent = p;
			}
			else if (isRight(n)) {
				p.right = n.left == null?n.right:n.left;
				p.right.parent = p;
			}
			// nΪ���ڵ�
			else {
				root = n.left == null?n.right:n.left;
				root.parent = null;
				root.color = BLACK;
				return;
			}
			if (isRed(n)) {
				return;
			}
			// ɾ���ڵ�Ϊ��ɫ������1����ɫ�ӽڵ㣬���ӽڵ���ɫ���,�����㷨
			else if (isRed(n.left)) {
				n.left.color = BLACK;
				return;
			}else if (isRed(n.right)) {
				n.right.color = BLACK;
				return;
			}else {
				// �ӽڵ�һ��Ϊ��ɫ,�ӽڵ���Ϊ�µ�˫�ڽڵ�
				n = n.left == null?n.right:n.left;
			}
		}

		if (!isRed(n))
			fixAfterDelete(n,p);
	}

	// ˫�ڽڵ���ֺ�ĵ���,����ڵ㼰�丸�ڵ�
	private void fixAfterDelete(RBNode<T> n, RBNode<T> p) {
		// �ֵܽڵ㣬һ����Ϊnull
		RBNode<T> brother;
		if (p.left == null || p.right == null)
			brother = p.left == null?p.right:p.left;
		else
			brother = isLeft(n)?p.right:p.left;
		// �ֵܽڵ�Ϊ��ɫ��������ɫ�任����ת����
		if (isRed(brother)) {
			// ���ڵ����ֵܽ�����ɫ
			boolean temp = p.color;
			p.color = brother.color;
			brother.color = temp;
			// �ֵ����ң�����������֮
			if (isRight(brother))
				left_rotate(p);
			else
				right_rotate(p);
			// �õ��µ��ֵܽڵ�
			brother = p.left == null?p.right:p.left;
		}
		// �ֵܽڵ���ں�ɫ�ӽڵ�
		if (isRed(brother.left) || isRed(brother.right)) {
			// ��ɫ�ӽڵ�
			RBNode<T> redC = isRed(brother.left)?brother.left:brother.right;
			fixWhenBrotherHasRed(p,brother,redC);
		}
		//�ֵܽڵ����2����ɫ�ӽڵ㣬����NIL�ڵ�
		else {
			brother.color = RED;
			if (isRed(p)) {
				p.color = BLACK;
			}else {
				// �����ڵ�ԭ��Ϊblack���򸸽ڵ���Ϊ�µ�˫�ڽڵ����������ֱ�����ڵ�
				if (p.parent != null)
					fixAfterDelete(p,p.parent);
			}
		}
	}

	// ���ֵܽڵ���ں�ɫ�ӽڵ������½��е���
	// ������������ڵ㡢�ֵܽڵ㡢��ɫ�ӽڵ�
	private void fixWhenBrotherHasRed(RBNode<T> p, RBNode<T> b, RBNode<T> c) {
		if (isRight(b) && isLeft(c)) {
			right_rotate(b);
			// ��ֶ�Ӻ��ֵܻ�������ΪԶֶ�����
			RBNode<T> temp;
			temp = c;
			c = b;
			b = temp;
		}
		if (isLeft(b) && isRight(c)) {
			left_rotate(b);
			// ��ֶ�Ӻ��ֵܻ�������ΪԶֶ�����
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
	 * ����������
	 * @param n
	 * @return
	 */
	private RBNode<T> successor(RBNode<T> n) {
		if (null == n)
			return null;

		RBNode<T> p;
		if (n.right != null) {
			// �������е���Сֵ
			p = n.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;
		} else {
			p = n.parent;
			// ���ڵ㲻Ϊnull���ҵ�ǰ�ڵ��Ǹ��ڵ���Һ���
			while (p != null && p.left != n) {
				n = p;
				p = n.parent;
			}
			// ����ǰ�ڵ��Ǹ��ڵ�����ӣ�ֱ�ӷ��ظ��ڵ�
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
