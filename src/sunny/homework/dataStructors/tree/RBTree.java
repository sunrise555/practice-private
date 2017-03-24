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
			root = new RBNode<T>(data, BLACK);
			return;
		}

		RBNode<T> cur = root;
		while (true) {
			// ��� data�ȵ�ǰ����ֵС
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

	// �������������������ڵ�Ϊ��ɫ����˵���ض��и��ڵ� 
	//#������Ǹ��ڵ�
	private void fixAfterPut(RBNode<T> cur) {
		if (isRed(cur)) {
			// �ڵ�Ϊ����
			if (cur.parent.left == cur) {
				// ����Ϊ��ɫ
				if (isRed(cur.parent.right))
					flipColors(cur);
				//����Ϊ��ɫ
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
		if (node == null) // �ڵ�Ϊnull��ڵ�Ϊ��ɫ��ͬһЧ��
			return false;
		return node.color == RED;
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
			if (t.compareTo((T) current.data) == 0) {
				return current;
			}
			// ����������
			else if (t.compareTo((T) current.data) < 0) {
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
}
