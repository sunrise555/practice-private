package sunny.homework.algorithm;

import java.util.LinkedList;

/**
 * 八数码问题：3×3的棋盘 棋子0~8 棋盘上所有状态数9！-1
 * 核心：将棋盘状态进行编码——递增进位制
 * 
 * 		对于排列 8 7 6 2 5 3 4 1  增加辅助数组,记录每个数字的右边比该数字小的元素的个数
 *               如 7 6 5 1 3 1 1 0 最后一位肯定为0，除外，从右边第二位到最左边的进制一次为2进制、3进制、4, 5, 6, 7,8进制
 *               因此该排列最大表示的数即8！-1
 *               将辅助数组转化为数字=7*7！+6*6！5*5！+1*4！+3*3！+1*2！+1*1！
 *               将数字转化为辅助数组：将数字对2取模，得到最后一位数字，将数字除2的结果对3取模得到右边第二位，以此类推，最后在最右边添上0
 * 
 * @Created by Sunny on 2017年5月17日
 */
public class EightPuzzle {
	
	int[] visited; //标记一个节点是否访问过

	EightPuzzle() {
		visited = new int[11340];// 为节省内存，使用位图的方式，每个int有32位，9! / 32 = 11340
	}
	
	public static void main(String[] args) {
		int[] array = {8, 6, 7, 5, 4, 3, 2, 0, 1};
        new EightPuzzle().breadFirstSearch(array);
	}
	public void breadFirstSearch(int[] array) {
		int s = encode(array);
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(new Node(s, null));

        while (!q.isEmpty()) {
            Node t = q.poll();

            // 输出所有排列
            if (t.n == 1) {
                Node iter = t;
                while (iter != null) {
                    int [] arrayIter = decode(iter.n, 9);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            System.out.print(arrayIter[i * 3 + j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("*********");

                    iter = iter.previous;
                }
                return;
            }

            int[] state = decode(t.n, 9);
            int[] tmp = new int[9];
            int index = findSpace(state);

            for (int i = 0; i < state.length; i++) {
                tmp[i] = state[i];
            }

            // 找到0所在的位置，分别将0上下左右移动得到四种新的排列，加入链表
            int n = moveUp(tmp, index);
            if (n != s && !isChecked(n)) {
                visit(n);//标记该排列已被访问
                q.addLast(new Node(n, t));//将新的排列加入链表中
            }

            for (int i = 0; i < state.length; i++) {
                tmp[i] = state[i];
            }

            n = moveDown(tmp, index);
            if (n != s && !isChecked(n)) {
                visit(n);
                q.addLast(new Node(n, t));
            }

            for (int i = 0; i < state.length; i++) {
                tmp[i] = state[i];
            }

            n = moveLeft(tmp, index);
            if (n != s && !isChecked(n)) {
                visit(n);
                q.addLast(new Node(n, t));
            }

            for (int i = 0; i < state.length; i++) {
                tmp[i] = state[i];
            }

            n = moveRight(tmp, index);
            if (n != s && !isChecked(n)) {
                visit(n);
                q.addLast(new Node(n, t));
            }
        }
        
        System.out.println("no solution");
	}
	
	public int moveDown(int[] state, int index) {
//        int index = findSpace(state); // 找到0所在的位置

        if (index < 6) {
            state[index] = state[index + 3];
            state[index + 3] = 0;
        }

        return encode(state);
    }

    public int moveUp(int[] state, int index) {
//        int index = findSpace(state);

        if (index > 2) {
            state[index] = state[index - 3];
            state[index - 3] = 0;
        }

        return encode(state);
    }

    public int moveRight(int[] state, int index) {
//        int index = findSpace(state);

        if (index % 3 != 2) {
            state[index] = state[index + 1];
            state[index + 1] = 0;
        }

        return encode(state);
    }

    public int moveLeft(int[] state, int index) {
//        int index = findSpace(state);

        if (index % 3 != 0) {
            state[index] = state[index - 1];
            state[index - 1] = 0;
        }

        return encode(state);
    }

    // 找到0所在的位置
    public int findSpace(int[] state) {
        for (int i = 0; i < 9; i++) {
            if (state[i] == 0)
                return i;
        }

        return -1;
    }

    // 判断当前排列是否已经访问过
    public boolean isChecked(int n) {
        int a = n / 32; //9! / 32 = 11340  
        int b = n & 31;  //n对32取余     n&31 == n%32

        return ((visited[a] & (1 << b)) != 0);// 判断visited[a]的第b位是否为1
    }

    public void visit(int n) {
        int a = n / 32;
        int b = n & 31;

        visited[a] |= (1 << b);// 将标志位置1
    }

	/**
	 * 对一个全排列进行编码
	 * @param arr
	 * @return 编码后的数字
	 * @Created 2017年5月17日
	 *
	 */
	static int encode(int[] arr) {
		int result = 0;
		int len = arr.length;
		int[] aux = new int[len];
		
		for (int i = 0; i < len; i++) {
			int base = 1;
			int j = i + 1;
			while(j < len) {
				base *= len - j; // 计算阶乘
				if(arr[i] > arr[j])
					aux[i]++;
				j++;
			}
			result += aux[i] * base;
		}

		return result;
	}
	
	/**
	 * 对一个数组进行解码
	 * @param num 待解码的数字
	 * @param len   全排列的长度
	 * @return 解码后的全排列
	 * @Created 2017年5月17日
	 *
	 */
	public static int[] decode(int num, int len) {
        int[] aux = new int[len]; // 辅助数组
        int[] arr = new int[len]; // 原数组
        int auxIndex = len - 2;
        // 转化为辅助数组
        for (int base = 2; base <= len ; base++) {
			aux[auxIndex] = num % base; // 从右向左赋值，最后一位必为0
			auxIndex--;
			num = num / base;
		}
        
        // 输出辅助数组
//        for (int i : aux) {
//			System.out.print(i + " ");
//		}
        
//        System.out.println();
        
        // 根据辅助数组，转化为原数组
        for (int i = 0; i < len; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(aux[j] == 0) {
					aux[j] = -1;
					arr[j] = i;
					break;
				}else if(aux[j] > 0){
					aux[j] -= 1;
				}
			}
		}
        
//        for (int i : arr) {
//			System.out.print(i + " ");
//		}
//        System.out.println();
        return arr;
    }

	class Node {
        int n;
        Node previous; //前序节点

        public Node(int n, Node prev) {
            this.n = n;
            this.previous = prev;
        }
    }
}
