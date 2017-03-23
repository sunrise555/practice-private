package sunny.homework.dataStructors.tree;


/**
 * 平衡二叉树节点类
 * Created by Sunny on 2017年3月23日
 */
public class AVLNode<T> {
	public T data;
	
	// 节点高度
	public int height;
	
	// 节点的平衡因子
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
