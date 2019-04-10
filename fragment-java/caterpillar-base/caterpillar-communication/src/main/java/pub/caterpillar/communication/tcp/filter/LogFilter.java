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
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

import pub.caterpillar.communication.tcp.standard.CommunicationStandard;


public class LogFilter extends IoFilterAdapter {
	


	@Override
	public void filterWrite(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		Object message = writeRequest.getMessage();
		String msg = null;
		if (message instanceof String){
			msg = (String)message;
		}else if (message instanceof IoBuffer){
			IoBuffer buffer = (IoBuffer)message;
			msg = new String(buffer.array(),0,buffer.limit(),CommunicationStandard.CHARSET_NAME);
		}
		super.filterWrite(nextFilter, session, writeRequest);
		//ComLogger.log(session.getRemoteAddress(), "send", msg);
		
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		String msg = null;
		if (message instanceof String){
			msg = (String)message;
		}
		if(msg.contains("read-cfg"))
		{
			msg="";
		}
		//ComLogger.log(session.getRemoteAddress(), "recv", msg);
		super.messageReceived(nextFilter, session, message);
	}

	@Override
	public void sessionOpened(NextFilter nextFilter, IoSession session)
			throws Exception {
		//ComLogger.log(session.getRemoteAddress(), "online", null);
		super.sessionOpened(nextFilter, session);
	}
	
	@Override
	public void sessionClosed(NextFilter nextFilter, IoSession session)
			throws Exception {
		//ComLogger.log(session.getRemoteAddress(), "offline", null);
		super.sessionClosed(nextFilter, session);
	}
	
	@Override
	public void exceptionCaught(NextFilter nextFilter, IoSession session,
			Throwable cause) throws Exception {
		//ComLogger.err(session.getRemoteAddress(),  cause);
		super.exceptionCaught(nextFilter, session, cause);
	}

}
