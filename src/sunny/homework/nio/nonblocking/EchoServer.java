package sunny.homework.nio.nonblocking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 非阻塞模式
 * @Created by Sunny on 2017年7月19日
 */
public class EchoServer {
	 private Selector selector = null;  
	    private ServerSocketChannel serverSocketChannel = null;  
	    private int port = 8000;  
	    private Charset charset = Charset.forName("GBK");  
	  
	    public EchoServer() throws IOException  
	    {  
	    	// 创建一个selector对象
	        selector = Selector.open();  
	        serverSocketChannel = ServerSocketChannel.open();  
	        
	        // 使同一主机在关闭服务器后，紧接着再启动时可以绑定相同的地址和端口
	        serverSocketChannel.socket().setReuseAddress(true);  
	        // 设置为非阻塞模式
	        serverSocketChannel.configureBlocking(false);  
	        serverSocketChannel.socket().bind(new InetSocketAddress(port));  
	        System.out.println("服务器启动");  
	    }  
	  
	    public void service() throws IOException  
	    {  
	    	// 为serverSocketChannel注册连接就绪
	        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  
	        // selector.select()返回当前相关事件已经发生的SelectionKey的个数。
	        // 如果当前没有任何事件发生，select()方法就会阻塞下去
	        while( selector.select() > 0 )  
	        {  
	        	
	            Set<SelectionKey> readyKeys = selector.selectedKeys();  
	            Iterator it = readyKeys.iterator();  
	            while( it.hasNext() )  
	            {  
	                SelectionKey key = null;  
	                try  
	                {  
	                    key = (SelectionKey)it.next();  
	                    it.remove();  
	                    if( key.isAcceptable() )  // 连接就绪事件
	                    {  
	                    	
	                        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();  
	                        SocketChannel socketChannel = (SocketChannel)ssc.accept();  
	                        System.out.println("接收到客户连接，来自:" + socketChannel.socket().getInetAddress()  
	                                + ":" + socketChannel.socket().getPort());  
	                        socketChannel.configureBlocking(false);  
	                        ByteBuffer buffer = ByteBuffer.allocate(1024);  
	                        // 客户端注册读就绪和写就绪事件
	                        socketChannel.register(selector, SelectionKey.OP_READ  
	                                | SelectionKey.OP_WRITE, buffer);  
	                    }  
	                    if( key.isReadable() )  
	                    {  
	                        receive(key);  
	                    }  
	                    if( key.isWritable() )  
	                    {  
	                        send(key);  
	                    }  
	                }  
	                catch( IOException e )  
	                {  
	                    e.printStackTrace();  
	                    try  
	                    {  
	                        if( key != null )  
	                        {  
	                            key.cancel();  
	                            key.channel().close();  
	                        }  
	                    }  
	                    catch( Exception ex )  
	                    {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }//#while  
	        }//#while  
	    }  
	  
	    public void send(SelectionKey key) throws IOException  
	    {  
	        ByteBuffer buffer = (ByteBuffer)key.attachment();  
	        SocketChannel socketChannel = (SocketChannel)key.channel();  
	        buffer.flip(); //把极限设为位置，把位置设为0  
	        String data = decode(buffer);  //将字节流转换为字符流
	        if( data.indexOf("/r/n") == -1 )  // 每读到一行数据就输出
	            return;  
	        String outputData = data.substring(0, data.indexOf("/n") + 1); // 截取一行数据  
	        System.out.print(outputData);  
	        // 将字符流转换为字节流
	        ByteBuffer outputBuffer = encode("echo:" + outputData);  
	        //发送一行字符串  
	        while( outputBuffer.hasRemaining() )  
	            socketChannel.write(outputBuffer);  
	        ByteBuffer temp = encode(outputData);  
	        buffer.position(temp.limit());  
	        buffer.compact(); //删除已经处理的字符串  
	        if( outputData.equals("bye/r/n") )  
	        {  
	            key.cancel();  
	            socketChannel.close();  
	            System.out.println("关闭与客户的连接");  
	        }  
	    }  
	  
	    public void receive(SelectionKey key) throws IOException  
	    {  
	    	// 返回附件
	        ByteBuffer buffer = (ByteBuffer)key.attachment();  
	        // 客户端信道
	        SocketChannel socketChannel = (SocketChannel)key.channel();  
	        // 数据缓存
	        ByteBuffer readBuff = ByteBuffer.allocate(32);  
	        socketChannel.read(readBuff);  
	        readBuff.flip(); // 调整position和limit的位置 
	        buffer.limit(buffer.capacity());  
	        buffer.put(readBuff); //把读到的数据放到buffer中  
	    }  
	  
	    public String decode(ByteBuffer buffer)  
	    { //解码  
	        CharBuffer charBuffer = charset.decode(buffer);  
	        return charBuffer.toString();  
	    }  
	  
	    public ByteBuffer encode(String str)  
	    { //编码  
	        return charset.encode(str);  
	    }  
	  
	    public static void main(String args[]) throws Exception  
	    {  
	        EchoServer server = new EchoServer();  
	        server.service();  
	    }  
}
