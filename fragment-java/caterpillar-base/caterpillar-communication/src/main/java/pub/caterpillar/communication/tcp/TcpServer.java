/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package pub.caterpillar.communication.tcp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import pub.caterpillar.communication.tcp.standard.CommunicationStandard;




public class TcpServer extends IoHandlerAdapter {
	
	NioSocketAcceptor acceptor;
    
    SocketAddress localAddress;
    

    

    
    public TcpServer(String ip,int port) throws IOException {
        
        acceptor = new NioSocketAcceptor();
        
        //acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new RequestCodecFactory(true)));
        
        acceptor.setHandler(this);

        @SuppressWarnings("unused")
		SocketSessionConfig scfg = acceptor.getSessionConfig();

        
        acceptor.bind(localAddress=new InetSocketAddress(ip,port));

        System.out.println("Server started...");
    }

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof IoBuffer){
			IoBuffer buffer = (IoBuffer) message;	
			IoBuffer recvBuffer = (IoBuffer) session.getAttribute("recvBuffer");
	    	if (recvBuffer==null){
		    	byte[] byteheader = new byte[CommunicationStandard.HEADER_LENGTH];
		           
	    		buffer.get(byteheader, 0, CommunicationStandard.HEADER_LENGTH);
	    		int messageLength = CommunicationStandard.parsePacketHeader(byteheader);
	    		if (messageLength >0 ){
	    			recvBuffer = IoBuffer.allocate(messageLength);
	    			session.setAttribute("recvBuffer", recvBuffer);
	    		}else{
	    			return;
	    		}

	    	}
	    	if (recvBuffer!=null){
    			byte[] left = new byte[Math.min(recvBuffer.remaining(), buffer.remaining())];
    				buffer.get(left);
    			recvBuffer.put(left);
	    	}
			if (recvBuffer!=null && recvBuffer.remaining()==0){
				recvBuffer.flip();
				String recv = new String(recvBuffer.array(),"UTF-8");
				System.out.println("[server recv]->"+recv);
				session.removeAttribute("recvBuffer");
				session.write(recv);
			}
		}
		
		
		
		
	}
	
	
	public synchronized void SendMessage(IoSession session,String message) throws UnsupportedEncodingException{
		
		IoBuffer SendBuffer = IoBuffer.allocate(CommunicationStandard.BUFFER_SIZE);
		byte[] head = CommunicationStandard.initPacketHeader(message);
		byte[] msg = message.getBytes(CommunicationStandard.CHARSET_NAME);
		//System.out.println(CommunicationStandard.bytes2HexString(msg));
		System.out.println("[server send]->"+message);
		SendBuffer.put(head);
		int i = 0;
		int left = 0;
		while(true){
			if (msg.length-i < SendBuffer.remaining()){
				SendBuffer.put(msg,i,msg.length-i);
				SendBuffer.flip();  
				SendBuffer.shrink();//
				WriteFuture writeFuture = session.write(SendBuffer.duplicate());
				writeFuture.awaitUninterruptibly();
		        SendBuffer.clear();
				return;
			}
			left= SendBuffer.remaining(); 
    		SendBuffer.put(msg,i,SendBuffer.remaining());
    		i+=left;
			SendBuffer.flip();  
			WriteFuture writeFuture = session.write(SendBuffer.duplicate());
			writeFuture.awaitUninterruptibly();//SendBuffer.clear();
		}

}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("receive a session");
		while(true){
			//SendMessage(session, "<message id='9223372036854775807");	
			WriteFuture writeFuture = session.write("sldkfjlsdslfaj;f");
			writeFuture.awaitUninterruptibly();
			Thread.sleep(1000);
		}
	}


}
