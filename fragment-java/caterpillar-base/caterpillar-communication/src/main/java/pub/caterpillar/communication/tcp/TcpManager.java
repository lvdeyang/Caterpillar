/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.communication.tcp.TcpManager
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.communication.tcp;



import org.apache.mina.core.session.IdleStatus;

import pub.caterpillar.communication.tcp.component.HeartBeat;
import pub.caterpillar.communication.tcp.component.Reconnector;
import pub.caterpillar.communication.tcp.standard.CommunicationStandard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TcpManager  implements ITcpManager {
	
	protected Map<Object, TcpClient> clients = new ConcurrentHashMap<Object, TcpClient>();
	
	protected Map<Object, Object> msgCatche = new ConcurrentHashMap<Object, Object>();

	protected Reconnector reconnector;

	protected HeartBeat heartBeat;

	@Override
	public TcpClient getOneConnect(Object key) throws Exception {
		TcpClient tcpClient = clients.get(key);
		if (tcpClient == null){
			throw new Exception();
		}
		return tcpClient;
	}

	@Override
	public void reconnectAll() {
		reconnector.pushReconnectList(clients.values());
	}
	

	@Override
	public void shutDown(Object key) throws InterruptedException {
		TcpClient client = clients.remove(key);;
		if (client!=null){
			client.shutDown();
		}
	}

	@Override
	public void exceptionCaught(Object key, Throwable cause)
			throws Exception {
		
	}

	@Override
	public void messageReceived(Object key, String message)
			throws Exception {

			Long msgId = CommunicationStandard.getMessageId(message, null);
			if (msgId == null){
				return;
			}
			TcpClient client = getOneConnect(key);
			client.recvLock();
			try{
				if (msgCatche.get(msgId).equals(key)){
					return;
				}
				msgCatche.put(msgId, message);
				client.recvSignalAll();
			}finally{
				client.recvUnlock();
			}
	}

	@Override
	public void messageSent(Object key, Object message) throws Exception {
		
	}
	@Override
	public TcpClient createClient(Object key,String localHost,String remoteHost,int remotePort) throws Exception{
		TcpClient client = new TcpClient(localHost, remoteHost, remotePort);
		if (clients.containsKey(key)){
			throw new RuntimeException("client key has existed");
		}else if(clients.containsValue(client)){
			throw new RuntimeException("client has existed");
		}
		client.setKey(key);
		client.setManager(this);
		clients.put(key, client);

		client.connect();

		if (client.isClosed()){
			client.shutDown();
			throw new RuntimeException("connect refruse!");
		}
		return client;
	}
	
	
	@Override
	public String sendMsg(Object key,Long msgId,String msg) throws Exception{
		return sendMsg(key, msgId, msg, false);
	}
	
	@Override
	public String sendMsg(Object key,Long msgId,String msg, boolean msgSet) throws Exception{
		TcpClient client = getOneConnect(key);
		if (client.isClosed()){
			throw new RuntimeException("client["+key+"] is closed");
		}
		if (msgId == null){
			msgId = CommunicationStandard.getMessageId(msg, null);
			if (msgId == null){
				throw new RuntimeException("can not find message id");
			}
		}
		msgCatche.put(msgId, key);
		try {
			client.trySendLock();
			try{
				client.recvLock();
				try{
					client.send(msg);
					client.recvAwait(msgSet);
				}finally{
					client.recvUnlock();
				}
			}finally{
				client.sendUnlock();
			}
			return (String) msgCatche.get(msgId);
		} catch (Exception e) {
			System.err.println("[client recv:500]->"+e.getMessage());
			throw e;
		}finally{
			msgCatche.remove(msgId);
		}
	}
	
	
	@Override
	public boolean sendByteMsg(Object key,byte[] msg, boolean msgSet) throws Exception{
		TcpClient client = getOneConnect(key);
		if (client.isClosed()){
			throw new RuntimeException("client["+key+"] is closed");
		}		
		try {
			client.trySendLock();
			//try{
			//	client.recvLock();
				try{
					client.sendByte(msg);
					//client.recvAwait(msgSet);
				//}finally{
				//	client.recvUnlock();
				//}
			}finally{
				client.sendUnlock();
			}
			return true;
		} catch (Exception e) {
			System.err.println("[client recv:500]->"+e.getMessage());
			throw e;
		}finally{
			//msgCatche.remove(msgId);
		}
	}
	

	@Override
	public void sessionClosed(Object key) throws Exception {
		reconnector.pushReconnectList(getOneConnect(key));
		notifyOffline(key);
	}
	@Override
	public void notifyOnline(Object key){
		
	}
	@Override
	public void notifyOffline(Object key){
		
	}

	@Override
	public void sessionCreated(Object key) throws Exception {
		
	}

	@Override
	public void sessionIdle(Object key, IdleStatus status)
			throws Exception {
	}

	@Override
	public void sessionOpened(Object key) throws Exception{
		//heartBeat.pushHeartBeat(getOneConnect(key));
		//notifyOnline(key);
	}

	@Override
	public void alarmReceived(Object key, String msg) {
		
	}
	
	@Override
	public void recoverReceived(Object key, String msg) {
		
	}
	@Override
	public boolean isOnline(Object key) throws Exception{
		TcpClient client = getOneConnect(key);
		return !client.isClosed();
	}

	@Override
	public void requestMsgReceived(Object key, String msg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void statusMsgReceived(Object key, byte[] msg) {
		// TODO Auto-generated method stub
		
	}

	
	
}
