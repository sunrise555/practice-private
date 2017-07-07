package sunny.homework.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class WebServer {
	public static void main(String[] args) {
		try {
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
			SocketChannel socketChannel = ssc.accept();
			
			ByteBuffer readBuffer = ByteBuffer.allocate(128);
			socketChannel.read(readBuffer);
			
			readBuffer.flip();// 调整position和limit的位置，变为读模式
			
			while (readBuffer.hasRemaining()) {
				System.out.println((char)readBuffer.get());
			}
			
			socketChannel.close();
			ssc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
