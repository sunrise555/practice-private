package sunny.homework.dataStructors.tree;


/**
 * ƽ��������ڵ���
 * Created by Sunny on 2017��3��23��
 */
public class AVLNode<T> {
	public T data;
	
	// �ڵ�߶�
	public int height;
	
	// �ڵ��ƽ������
	public int balance;
	
	public AVLNode<T> left;
	public AVLNode<T> right;
	public AVLNode<T> parent;
	
	public AVLNode(T data) {
		this.data = data;
		this.height = 1;
		this.balance = 0;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

}
