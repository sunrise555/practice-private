package sunny.homework.algorithm;

import java.util.LinkedList;

/**
 * ���������⣺3��3������ ����0~8 ����������״̬��9��-1
 * ���ģ�������״̬���б��롪��������λ��
 * 
 * 		�������� 8 7 6 2 5 3 4 1  ���Ӹ�������,��¼ÿ�����ֵ��ұ߱ȸ�����С��Ԫ�صĸ���
 *               �� 7 6 5 1 3 1 1 0 ���һλ�϶�Ϊ0�����⣬���ұߵڶ�λ������ߵĽ���һ��Ϊ2���ơ�3���ơ�4, 5, 6, 7,8����
 *               ��˸���������ʾ������8��-1
 *               ����������ת��Ϊ����=7*7��+6*6��5*5��+1*4��+3*3��+1*2��+1*1��
 *               ������ת��Ϊ�������飺�����ֶ�2ȡģ���õ����һλ���֣������ֳ�2�Ľ����3ȡģ�õ��ұߵڶ�λ���Դ����ƣ���������ұ�����0
 * 
 * @Created by Sunny on 2017��5��17��
 */
public class EightPuzzle {
	
	int[] visited; //���һ���ڵ��Ƿ���ʹ�

	EightPuzzle() {
		visited = new int[11340];// Ϊ��ʡ�ڴ棬ʹ��λͼ�ķ�ʽ��ÿ��int��32λ��9! / 32 = 11340
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

            // �����������
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

            // �ҵ�0���ڵ�λ�ã��ֱ�0���������ƶ��õ������µ����У���������
            int n = moveUp(tmp, index);
            if (n != s && !isChecked(n)) {
                visit(n);//��Ǹ������ѱ�����
                q.addLast(new Node(n, t));//���µ����м���������
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
//        int index = findSpace(state); // �ҵ�0���ڵ�λ��

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

    // �ҵ�0���ڵ�λ��
    public int findSpace(int[] state) {
        for (int i = 0; i < 9; i++) {
            if (state[i] == 0)
                return i;
        }

        return -1;
    }

    // �жϵ�ǰ�����Ƿ��Ѿ����ʹ�
    public boolean isChecked(int n) {
        int a = n / 32; //9! / 32 = 11340  
        int b = n & 31;  //n��32ȡ��     n&31 == n%32

        return ((visited[a] & (1 << b)) != 0);// �ж�visited[a]�ĵ�bλ�Ƿ�Ϊ1
    }

    public void visit(int n) {
        int a = n / 32;
        int b = n & 31;

        visited[a] |= (1 << b);// ����־λ��1
    }

	/**
	 * ��һ��ȫ���н��б���
	 * @param arr
	 * @return ����������
	 * @Created 2017��5��17��
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
				base *= len - j; // ����׳�
				if(arr[i] > arr[j])
					aux[i]++;
				j++;
			}
			result += aux[i] * base;
		}

		return result;
	}
	
	/**
	 * ��һ��������н���
	 * @param num �����������
	 * @param len   ȫ���еĳ���
	 * @return ������ȫ����
	 * @Created 2017��5��17��
	 *
	 */
	public static int[] decode(int num, int len) {
        int[] aux = new int[len]; // ��������
        int[] arr = new int[len]; // ԭ����
        int auxIndex = len - 2;
        // ת��Ϊ��������
        for (int base = 2; base <= len ; base++) {
			aux[auxIndex] = num % base; // ��������ֵ�����һλ��Ϊ0
			auxIndex--;
			num = num / base;
		}
        
        // �����������
//        for (int i : aux) {
//			System.out.print(i + " ");
//		}
        
//        System.out.println();
        
        // ���ݸ������飬ת��Ϊԭ����
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
        Node previous; //ǰ��ڵ�

        public Node(int n, Node prev) {
            this.n = n;
            this.previous = prev;
        }
    }
}
