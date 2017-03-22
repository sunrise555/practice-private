package sunny.homework.dataStructors.stack;

import java.util.ArrayList;


/**
 * 数据结构：栈 Created by Sunny on 2017年3月15日
 */
public class Stack<T> {
	private ArrayList<T> list;
	private int size;
	private int top = 0;// 指向栈顶

	public Stack(int size) {
		this.size = size;
		this.list = new ArrayList<T>(size);
	}

	public void push(T item) {
		if (this.top <= this.size) {
			this.list.add(item);
			this.top++;
		}
	}

	public T pop() {
		if (!isEmpty()) {
			return this.list.remove(--top);
		} else {
			return null;
		}
	}

	public T getTop() {
		if(isEmpty())
			return null;
		return list.get(list.size()-1);
	}

	public boolean isEmpty() {
		return this.top == 0;
	}
}
