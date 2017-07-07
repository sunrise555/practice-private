package sunny.homework.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * channel最大的作用是封装了异步操作
 * 本地矢量I/O Scatter/Gather
 * @Created by Sunny on 2017年7月6日
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
//			writeBuffer.flip();//调整为写模式
//			
//			socketChannel.write(writeBuffer);//写入channel
//			socketChannel.close();
			
			/*使用本地矢量I/O  Gather在并发环境下有明显优势*/
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
