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
 * ������ģʽ
 * @Created by Sunny on 2017��7��19��
 */
public class EchoServer {
	 private Selector selector = null;  
	    private ServerSocketChannel serverSocketChannel = null;  
	    private int port = 8000;  
	    private Charset charset = Charset.forName("GBK");  
	  
	    public EchoServer() throws IOException  
	    {  
	    	// ����һ��selector����
	        selector = Selector.open();  
	        serverSocketChannel = ServerSocketChannel.open();  
	        
	        // ʹͬһ�����ڹرշ������󣬽�����������ʱ���԰���ͬ�ĵ�ַ�Ͷ˿�
	        serverSocketChannel.socket().setReuseAddress(true);  
	        // ����Ϊ������ģʽ
	        serverSocketChannel.configureBlocking(false);  
	        serverSocketChannel.socket().bind(new InetSocketAddress(port));  
	        System.out.println("����������");  
	    }  
	  
	    public void service() throws IOException  
	    {  
	    	// ΪserverSocketChannelע�����Ӿ���
	        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  
	        // selector.select()���ص�ǰ����¼��Ѿ�������SelectionKey�ĸ�����
	        // �����ǰû���κ��¼�������select()�����ͻ�������ȥ
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
	                    if( key.isAcceptable() )  // ���Ӿ����¼�
	                    {  
	                    	
	                        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();  
	                        SocketChannel socketChannel = (SocketChannel)ssc.accept();  
	                        System.out.println("���յ��ͻ����ӣ�����:" + socketChannel.socket().getInetAddress()  
	                                + ":" + socketChannel.socket().getPort());  
	                        socketChannel.configureBlocking(false);  
	                        ByteBuffer buffer = ByteBuffer.allocate(1024);  
	                        // �ͻ���ע���������д�����¼�
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
	        buffer.flip(); //�Ѽ�����Ϊλ�ã���λ����Ϊ0  
	        String data = decode(buffer);  //���ֽ���ת��Ϊ�ַ���
	        if( data.indexOf("/r/n") == -1 )  // ÿ����һ�����ݾ����
	            return;  
	        String outputData = data.substring(0, data.indexOf("/n") + 1); // ��ȡһ������  
	        System.out.print(outputData);  
	        // ���ַ���ת��Ϊ�ֽ���
	        ByteBuffer outputBuffer = encode("echo:" + outputData);  
	        //����һ���ַ���  
	        while( outputBuffer.hasRemaining() )  
	            socketChannel.write(outputBuffer);  
	        ByteBuffer temp = encode(outputData);  
	        buffer.position(temp.limit());  
	        buffer.compact(); //ɾ���Ѿ�������ַ���  
	        if( outputData.equals("bye/r/n") )  
	        {  
	            key.cancel();  
	            socketChannel.close();  
	            System.out.println("�ر���ͻ�������");  
	        }  
	    }  
	  
	    public void receive(SelectionKey key) throws IOException  
	    {  
	    	// ���ظ���
	        ByteBuffer buffer = (ByteBuffer)key.attachment();  
	        // �ͻ����ŵ�
	        SocketChannel socketChannel = (SocketChannel)key.channel();  
	        // ���ݻ���
	        ByteBuffer readBuff = ByteBuffer.allocate(32);  
	        socketChannel.read(readBuff);  
	        readBuff.flip(); // ����position��limit��λ�� 
	        buffer.limit(buffer.capacity());  
	        buffer.put(readBuff); //�Ѷ��������ݷŵ�buffer��  
	    }  
	  
	    public String decode(ByteBuffer buffer)  
	    { //����  
	        CharBuffer charBuffer = charset.decode(buffer);  
	        return charBuffer.toString();  
	    }  
	  
	    public ByteBuffer encode(String str)  
	    { //����  
	        return charset.encode(str);  
	    }  
	  
	    public static void main(String args[]) throws Exception  
	    {  
	        EchoServer server = new EchoServer();  
	        server.service();  
	    }  
}
