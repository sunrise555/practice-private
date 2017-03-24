package sunny.homework.dataStructors.tree;

/**
 * 红黑树节点类
 * @Created by Sunny on 2017年3月24日
 */
public class RBNode<T> {
	public T data;
	
	public RBNode<T> left;
	public RBNode<T> right;
	public RBNode<T> parent;
	
	public boolean color;
	
	public RBNode(T data, boolean color) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.color = color;
	}
	

}
