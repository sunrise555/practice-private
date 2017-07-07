package sunny.homework.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * channel���������Ƿ�װ���첽����
 * ����ʸ��I/O Scatter/Gather
 * @Created by Sunny on 2017��7��6��
 */
public class ClientServer {
	public static void main(String[] args) {
		SocketChannel socketChannel = null;
		
		try {
			socketChannel = SocketChannel.open();
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
			
//			ByteBuffer writeBuffer = ByteBuffer.allocate(128);
//			writeBuffer.put("hello world".getBytes());
//			
//			writeBuffer.flip();//����Ϊдģʽ
//			
//			socketChannel.write(writeBuffer);//д��channel
//			socketChannel.close();
			
			/*ʹ�ñ���ʸ��I/O  Gather�ڲ�������������������*/
			ByteBuffer writeBuffer = ByteBuffer.allocate(128);
			ByteBuffer writeBuffer2 = ByteBuffer.allocate(16);
			
			writeBuffer.put("hello ".getBytes());
			writeBuffer2.put("world".getBytes());
			
			writeBuffer.flip();
			writeBuffer2.flip();
			
			ByteBuffer[] bb = {writeBuffer, writeBuffer2};
			socketChannel.write(bb);
			socketChannel.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
