/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.communication.factory.XStreamFilter
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.communication.tcp.filter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.DefaultWriteFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;

import pub.caterpillar.communication.tcp.standard.CommunicationStandard;



public class XStreamFilter extends IoFilterAdapter {

	@Override
	public void filterWrite(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		Object message = writeRequest.getMessage();
		if (message instanceof IoBuffer){
			IoBuffer ioBuffer = (IoBuffer)writeRequest.getMessage();
			System.out.println(new String(ioBuffer.array(),0,ioBuffer.limit(),"utf-8"));
			super.filterWrite(nextFilter, session, writeRequest);
		}else if (message instanceof String){
			String msg = (String)message;
			IoBuffer SendBuffer = IoBuffer.allocate(CommunicationStandard.BUFFER_SIZE);
	   		byte[] head = CommunicationStandard.initPacketHeader(msg);
    		byte[] msgbytes = msg.getBytes(CommunicationStandard.CHARSET_NAME);
    		SendBuffer.put(head);
    		int i = 0;
    		int left = 0;
    		while(true){
    			if (msgbytes.length-i <= SendBuffer.remaining()){
    				SendBuffer.put(msgbytes,i,msgbytes.length-i);
    				SendBuffer.flip();  
    				SendBuffer.shrink();//鍙敼鍙榣imit
    		        WriteRequest newWriteRequest = new DefaultWriteRequest(SendBuffer, writeRequest.getFuture(), writeRequest.getDestination());
    		        nextFilter.filterWrite(session, newWriteRequest);
    		        writeRequest.getFuture().awaitUninterruptibly();
    				return;
    			}
    			left= SendBuffer.remaining(); 
	    		SendBuffer.put(msgbytes,i,SendBuffer.remaining());
	    		i+=left;
				SendBuffer.flip();  
				WriteFuture future =   new DefaultWriteFuture(session);
		        WriteRequest newWriteRequest = new DefaultWriteRequest(SendBuffer,future, writeRequest.getDestination());
		        nextFilter.filterWrite(session, newWriteRequest);
		        future.awaitUninterruptibly();//杩欏彞璇濅笉瑕佸幓鎺�鍚﹀垯椤哄簭浼氫贡鎺�
		        SendBuffer.clear();
    		}
		}
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		try {
			if (message instanceof IoBuffer){
				IoBuffer buffer = (IoBuffer) message;
				while(buffer.remaining()>0){
					IoBuffer recvBuffer = (IoBuffer) session.getAttribute("recvBuffer");
					if (recvBuffer==null){
						int messageLength = findPackHead(buffer);
						if (messageLength >0 ){
							recvBuffer = IoBuffer.allocate(messageLength);
							session.setAttribute("recvBuffer", recvBuffer);
						}else{
							return;
						}
	
					}

					int remain = Math.min(recvBuffer.remaining(), buffer.remaining());
					recvBuffer.put(buffer.array(),buffer.position(),remain);
					buffer.position(buffer.position()+remain);
					if (recvBuffer.remaining()==0){
						recvBuffer.flip();
						session.removeAttribute("recvBuffer");
						nextFilter.messageReceived(session, new String(recvBuffer.array(),0,recvBuffer.limit(),CommunicationStandard.CHARSET_NAME));
					}
				}
			}
		} catch (Exception e) {
			session.removeAttribute("recvBuffer");
			throw e;
		}
		
	}
	
	private int findPackHead(IoBuffer recvBuffer){
		byte[] byteheader = new byte[CommunicationStandard.HEADER_LENGTH];
		for (int i=recvBuffer.position();i<recvBuffer.limit()-CommunicationStandard.HEADER_LENGTH;i++){
			recvBuffer.position(i);
			if (recvBuffer.get(0) != CommunicationStandard.SYN_HEADER){
				continue;
			}
			recvBuffer.get(byteheader);
			int messageLength = CommunicationStandard.parsePacketHeader(byteheader);
			if (messageLength>0){
				return messageLength;
			}
		}
		return 0;
	}

}
