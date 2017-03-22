package sunny.homework.dataStructors.tree;

/**
 * Created by sunny_hbqq on 2017/3/18.
 */
public class Node<T> {
    public T data;
    public Node<T> left;
    public Node<T> right;
  //非递归判断状态EIP，标记当前节点已经访问到哪一步了：未输出0，已输出2、已判断左子树1，已判断右子树3
    public int state;
  //实现迭代器时需要经常访问父节点，因此直接在Node类中增加parent这个属性
    public Node<T> parent;

    public Node(T d) {
        this.data = d;
    }
}
