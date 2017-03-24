package sunny.homework.dataStructors.tree;

/**
 * ������ڵ���
 * @Created by Sunny on 2017��3��24��
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
