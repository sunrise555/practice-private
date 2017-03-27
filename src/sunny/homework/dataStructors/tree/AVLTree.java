package sunny.homework.dataStructors.tree;

/**
 * ƽ��������� 
 * @Created by Sunny on 2017��3��23��
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
	 * ����ֵΪdata�Ľڵ�
	 * 
	 * @param data
	 *            �ڵ��ֵ
	 * @return ����ɹ�����true
	 * @Created 2017��3��23��
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
			// ��� data�ȵ�ǰ����ֵС
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
		 * �������ܸı�ĳ�������ĸ߶ȣ��Ӷ�ʹ�ڵ��ƽ�����Ӵ���2 ͨ����ת�����������е�����
		 */
		root.balance = calBalance(root);

		// �������߶ȴ���������������
		if (root.balance >= 2) {
			// ������������߶ȴ������������������
			if ((root.left.balance = calBalance(root.left)) < 0)
				left_rotate(root.left);
			right_rotate(root);
		}
		// �������߶ȴ���������������
		else if (root.balance <= -2) {
			// ������������߶ȴ������������������
			if ((root.right.balance = calBalance(root.right)) > 0)
				right_rotate(root.right);
			left_rotate(root);
		}
	}

	// ��������
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
		// ���¼���ڵ�����

	}

	// ��������
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

	// ����ƽ������
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

	// ���㵱ǰ�ڵ�߶�
	private int height(AVLNode<T> cur) {
		if (null == cur)
			return 0;

		int leftHeight = height(cur.left);
		int rightHeight = height(cur.right);
		return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
	}
	/**
	 * ���ڵ�t�Ƿ��ڲ�������
	 * 
	 * @param t
	 * @return �����ڷ��ظýڵ�,�������򷵻�null
	 */
	public AVLNode<T> contains(T t) {

		AVLNode<T> current = root;

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
	 * ɾ���ڵ㣬�������������˳��
	 * @param delNode ��ɾ���Ľڵ�
	 * @Created 2017��3��23��
	 *
	 */
	public void delete(AVLNode<T> delNode) {
		//�ڵ㲻����
		if(delNode == null || contains(delNode.data) == null)
			return;	
	
		AVLNode<T> p = delNode.parent;
		//���nΪҶ�ӽڵ㣬ֱ��ɾ��
		if (delNode.left == null && delNode.right == null){
			if (delNode == root) {
				root = null;
			}else if(p.left == delNode) {
				p.left = null;
			}else if(p.right == delNode) {
				p.right = null;
			}
		}
		//��ǰ�ڵ���2�����ӽڵ�
		else if(delNode.left != null && delNode.right != null) {
			AVLNode<T> next = successor(delNode);
			delNode.data = next.data;
			delete(next);
		}
		//�ڵ�ֻ��һ�����ӽڵ�
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
		// ɾ��������ƽ�⣬������ת��������·�����ϻ���
		while(cur != null) { 
			cur.balance = calBalance(cur);
			// �������߶ȴ���������������
			if (cur.balance >= 2) {
				// ������������߶ȴ������������������
				if ((cur.left.balance = calBalance(cur.left)) < 0)
					left_rotate(cur.left);
				right_rotate(cur);
			}
			// �������߶ȴ���������������
			else if (cur.balance <= -2) {
				// ������������߶ȴ������������������
				if ((cur.right.balance = calBalance(cur.right)) > 0)
					right_rotate(cur.right);
				left_rotate(cur);
			}
			cur = cur.parent;
		}
		
	}
	
	/*
	 * ���������̽ڵ�
	 * @param n ��ǰ�ڵ�
	 * @return �����̽ڵ�
	 * @Created 2017��3��23��
	 *
	 */
	private AVLNode<T> successor(AVLNode<T> n) {
		if (null == n)
			return null;

		AVLNode<T> p;
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
	/**
	 * �ݹ����������
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
