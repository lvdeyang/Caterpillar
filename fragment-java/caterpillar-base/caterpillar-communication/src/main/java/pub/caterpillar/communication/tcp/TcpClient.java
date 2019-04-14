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

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import pub.caterpillar.communication.tcp.standard.CommunicationStandard;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



/**
 * An UDP client taht just send thousands of small messages to a UdpServer. 
 * 
 * This class is used for performance test purposes. It does nothing at all, but send a message
 * repetitly to a server.
 * 
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class TcpClient extends IoHandlerAdapter{
	
    IoConnector connector;

    IoSession session;
    
    SocketAddress localAddress;
    
    SocketAddress remoteAddress;
    
    ITcpManager manager;
    
    InetAddress remoteIp;
    
    Object key;
    
    String status;
    
    //
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    private final ReentrantLock sendLock = new ReentrantLock(true);
    
    public final ReentrantLock recvLock = new ReentrantLock();
    
    public final Condition recvCondition = recvLock.newCondition();

    private final ReentrantLock transactionLock = new ReentrantLock(true);
    
    public TcpClient(String localHost,String remoteHost,int remotePort) { 
        
        connector = new NioSocketConnector();
        //connector.getFilterChain().addLast("codec", new XStreamFilter());
        //connector.getFilterChain().addLast("log", new LogFilter());
        connector.setHandler(this);
        connector.setConnectTimeoutMillis(CommunicationStandard.CONNECTION_TIME_OUT);
        SocketSessionConfig dcfg = (SocketSessionConfig) connector.getSessionConfig();

      //  dcfg.setTcpNoDelay(true);
       // dcfg.setKeepAlive(true);
        dcfg.setReceiveBufferSize(CommunicationStandard.BUFFER_SIZE);
        dcfg.setSendBufferSize(CommunicationStandard.BUFFER_SIZE);
        dcfg.setSoLinger(0);
        dcfg.setWriteTimeout(5000);
        dcfg.setTrafficClass(0x04 | 0x10);
        localAddress = new InetSocketAddress(localHost, 0);
        remoteAddress = new InetSocketAddress(remoteHost, remotePort);
        
        try {
			remoteIp = InetAddress.getByName(remoteHost);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }

    public boolean connect() throws Exception{
    	writeLockInterruptibly();
    	try {
			if (connector.isDisposed()){
				throw new Exception();
			}
			ConnectFuture connFuture = connector.connect(remoteAddress, localAddress);
			//ComLogger.log(remoteAddress, "connecting...", null);
			connFuture.awaitUninterruptibly(CommunicationStandard.CONNECT_TIME_OUT);
			if (connector.isActive()){
				session = connFuture.getSession();
			}else{
				//ComLogger.log(remoteAddress, "connect", "fail");
			}
			return session!=null&&session.isConnected();
		} finally {
			writeUnlock();
		}
    }
    
    public boolean isClosed() throws InterruptedException{
    	readLockInterruptibly();
    	try {
			if (this.session==null||!session.isConnected()){
				return true;
			}
			return false;
		} finally {
			readUnlock();
		}
    }


	//鍐欓攣
	public void send(String msg) throws InterruptedException{
//		trySendLock();
//		try {
			readLockInterruptibly();
			try {
				WriteFuture writeFuture = session.write(msg);
				writeFuture.awaitUninterruptibly();
			} finally{
				readUnlock();
			}
//		} finally {
//			sendUnlock();
//		}
	}
	
	public void sendByte(byte[] msg) throws InterruptedException{
//		trySendLock();
//		try {
			readLockInterruptibly();
			try {
				WriteFuture writeFuture = session.write(IoBuffer.wrap(msg));//发字节流时得他妈用iobuffer弄一下！
				writeFuture.awaitUninterruptibly();
			} finally{
				readUnlock();
			}
//		} finally {
//			sendUnlock();
//		}
	}
    

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (manager != null && message instanceof String){
			String msg = (String)message;
			String msgType = CommunicationStandard.getMessageType(msg.getBytes(CommunicationStandard.CHARSET_NAME));
			if (CommunicationStandard.RESPONSE.equalsIgnoreCase(msgType)){
				manager.messageReceived(key,msg);
			} else if (CommunicationStandard.RESPONSE_SET.equalsIgnoreCase(msgType)) {
				manager.messageReceived(key,msg);
			} else if (CommunicationStandard.ALERT.equalsIgnoreCase(msgType)) {
				manager.alarmReceived(key,msg);
			} else if (CommunicationStandard.RECOVER.equalsIgnoreCase(msgType)){
				manager.recoverReceived(key, msg);
			} else {
				manager.requestMsgReceived(key,msg);
			}
		}else{
			System.out.println("[recv:]" + message);
			IoBuffer ioBuffer = (IoBuffer)message; 
		    byte[] b = new byte[ioBuffer.limit()];  
		    ioBuffer.get(b);
		    if((int)b[4]==33){
		    	manager.statusMsgReceived("", b);
		    }
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		Boolean manualClose = (Boolean) session.getAttribute("ManualClose", false);
		if (manualClose) {
			session.removeAttribute("ManualClose");
			return;
		}else if (manager != null){
			manager.sessionClosed(key);
		}
		
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		if (manager != null){
			manager.sessionCreated(key);
		}
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		if (manager != null){
			manager.sessionIdle(key, status);
		}
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		if (manager != null){
			manager.sessionOpened(key);
		}
		
	}
    //鍐欓攣
	public void shutDown() throws InterruptedException {
		writeLockInterruptibly();
		try {
			if (session != null){
				//ComLogger.log(remoteAddress, "shutdown...", null);
				session.setAttribute("ManualClose", true);
				if (session.isConnected()) {
					session.close(true);
				}
			}
			connector.dispose();
			//ComLogger.log(remoteAddress, "shutdown success", null);
		} finally {
			writeUnlock();
		}
	}
	
	public void disConnect() throws InterruptedException{
		writeLockInterruptibly();
		try {
			if (session != null && session.isConnected()) 
			session.close(true);	
		} finally {
			writeUnlock();
		}
	}

	public ITcpManager getManager() {
		return manager;
	}

	public void setManager(ITcpManager manager) {
		this.manager = manager;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((remoteAddress == null) ? 0 : remoteAddress.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TcpClient other = (TcpClient) obj;
		if (remoteAddress == null) {
			if (other.remoteAddress != null)
				return false;
		} else if (!remoteAddress.equals(other.remoteAddress))
			return false;
		return true;
	}
	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public InetAddress getRemoteIp() {
		return remoteIp;
	}

	public IoConnector getConnector() {
		return connector;
	}



	protected void writeLockInterruptibly() throws InterruptedException{
		try {
			lock.writeLock().lockInterruptibly();
		} catch (InterruptedException e) {
			throw e;
		}
	}
	
	protected void readLockInterruptibly() throws InterruptedException{
		try {
			lock.readLock().lockInterruptibly();
		} catch (InterruptedException e) {
			throw e;
		}
	}
	
	protected void readUnlock(){
		lock.readLock().unlock();
	}

	protected void writeUnlock(){
		lock.writeLock().unlock();
	}
	public void trySendLock() throws Exception{
		try {
			boolean hasLocked = sendLock.tryLock(CommunicationStandard.SEND_LOCK_TIME_OUT, TimeUnit.MILLISECONDS);
			if (!hasLocked){
				throw new Exception();
			}
		} catch (InterruptedException e) {
			throw e;
		}
	}
	
	public void sendUnlock(){
		sendLock.unlock();
	}
	
	public void recvLock() throws InterruptedException{
		try {
			recvLock.lockInterruptibly();
		} catch (InterruptedException e) {
			throw e;
		}
	}
	
	public void recvAwait(boolean waitLong) throws Exception{
		try {
			Boolean timeOut = !recvCondition.await(
					waitLong ? CommunicationStandard.LONG_RESPONSE_TIME : CommunicationStandard.RESPONSE_TIME, 
					TimeUnit.MILLISECONDS);
			if (timeOut){
				throw new Exception();
			}
		} catch (InterruptedException e) {
			throw e;
		}
	}
	
	public void recvUnlock(){
		recvLock.unlock();
	}
	
	public void recvSignalAll(){
		recvCondition.signalAll();
	}
	
	public ReentrantLock transactionLock() throws Exception{
		try {
			boolean hasLocked = transactionLock.tryLock(CommunicationStandard.TRANSACTION_LOCK_TIME_OUT, TimeUnit.MILLISECONDS);
			if (!hasLocked){
				throw new Exception();
			}
			return transactionLock;
		} catch (InterruptedException e) {
			throw e;
		}
	}
	public void transactionUnlock(){
		transactionLock.unlock();
	}
	
	public SocketAddress getRomoteAddress(){
		return remoteAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
