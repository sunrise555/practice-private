package sunny.homework.dataStructors.tree;

/**
 * Created by sunny_hbqq on 2017/3/18.
 */
public class Node<T> {
    public T data;
    public Node<T> left;
    public Node<T> right;
  //�ǵݹ��ж�״̬EIP����ǵ�ǰ�ڵ��Ѿ����ʵ���һ���ˣ�δ���0�������2�����ж�������1�����ж�������3
    public int state;
  //ʵ�ֵ�����ʱ��Ҫ�������ʸ��ڵ㣬���ֱ����Node��������parent�������
    public Node<T> parent;

    public Node(T d) {
        this.data = d;
    }
}
