/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:com.suma.xmanager.tcp.listener.NIOServer
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.communication.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import pub.caterpillar.communication.tcp.standard.CommunicationStandard;





public class NIOServer {


    private  ByteBuffer sendbuffer = ByteBuffer.allocate(CommunicationStandard.BUFFER_SIZE);

    private  Selector selector;
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(CommunicationStandard.BUFFER_SIZE);
    

    public NIOServer(int port) throws IOException {
   
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(new InetSocketAddress(port));

        selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server Start----"+port+":");
    }



    private void listen() throws IOException {
        while (true) {


            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //iterator.remove();
               // System.out.println(selectionKeys.size());
                try {
                        handleKey(selectionKey);
                	if (selectionKey.isAcceptable()){
                		iterator.remove();
                	}
				} catch (CancelledKeyException e) {
					// TODO: handle exception
					System.err.println("鏂紑杩炴帴");
				}catch(Exception e){
					e.printStackTrace();
				}
            }
        }
    }


    private void handleKey(SelectionKey selectionKey) throws IOException {

        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count=0;

        if (selectionKey.isAcceptable()) {
        	System.out.println("accept");
           
            server = (ServerSocketChannel) selectionKey.channel();

            client = server.accept();

            if (client == null){
            	return;
            }
            client.configureBlocking(false);

            client.register(selector, SelectionKey.OP_READ);
            
        } else if (selectionKey.isReadable()) {

        	
            client = (SocketChannel) selectionKey.channel();

            receivebuffer.clear();

            try {
				count = client.read(receivebuffer);
				if (count > 0) {
					System.out.println(client.socket().getInetAddress());
				    receiveText = new String( receivebuffer.array(),0,count,"utf-8");
				    System.out.println(receiveText);
				    receivebuffer.flip();
				    ((SocketChannel)selectionKey.channel()).write(receivebuffer);
				    send((SocketChannel)selectionKey.channel(),receivebuffer.array());
				}
				 //client.register(selector, SelectionKey.OP_WRITE);
			} catch (Exception e) {
				selectionKey.cancel();
				System.out.println("closereadkey");
			}
        } else if (selectionKey.isWritable()) {
        	System.out.println("write");

            sendText="<message id=\"9223372036854775807\">HeartBeat";

            send((SocketChannel)selectionKey.channel(),sendText.getBytes());
            ((SocketChannel)selectionKey.channel()).register(selector, SelectionKey.OP_READ);		
        }
    }
    
    
    public synchronized void send(SocketChannel channel,byte[] b){
        sendbuffer.clear();

        sendbuffer.put(b);
       
        sendbuffer.flip();
        
        try {
			channel.write(sendbuffer);
		} catch (Exception e) {
			try {
				channel.close();
			} catch (Exception e1) {
				
			}
			System.out.println("");
		}
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int port = 58889;
        NIOServer server = new NIOServer(port);
        server.listen();
    }
}