package sunny.homework.dataStructors.linkedList;

public class DoubleLinkList {
	public DoubleLinkNode head;
	public DoubleLinkNode tail;
	
	// ��ѯ�� index �������
	public DoubleLinkNode queryNode(int index) {
		DoubleLinkNode cur = head;
		int i = 0;
		while(i<index) {
			cur.data = cur.next.data;
			cur.next = cur.next.next;
			cur.prev = cur;
			i++;
		}
		return cur;
	} 
	// �� toBeDelete ��������ɾ��
	public void deleteNode(DoubleLinkNode toBeDelete) {
		toBeDelete.next.prev = toBeDelete.prev;
		toBeDelete.prev.next = toBeDelete.next;
	} 
	// ��toBeInsert���뵽pos������
	public void insertNode(DoubleLinkNode pos, DoubleLinkNode toBeInsert) {
		pos.next.prev = toBeInsert;
		toBeInsert.prev = pos;
		toBeInsert.next = pos.next;
		pos.next = toBeInsert;
	} 
	
	public static void main(String[] args) {
		DoubleLinkList list = new DoubleLinkList();
		list.head = new DoubleLinkNode(1);
		list.tail = new DoubleLinkNode(10);
		list.head.next = list.tail;
		list.tail.prev = list.head;
		DoubleLinkNode node = new DoubleLinkNode(2);
		list.insertNode(list.head, node);
		System.out.println(list.queryNode(1).data);
	}

}
