/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.communication.tcp.Reconnector
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.communication.tcp.component;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import pub.caterpillar.communication.tcp.TcpClient;
import pub.caterpillar.communication.tcp.executor.Executor;
import pub.caterpillar.communication.tcp.standard.CommunicationStandard;




public class Reconnector implements Runnable{
	Set<TcpClient> reconnectList = new HashSet<TcpClient>();

	List<TcpClient> tempList = new LinkedList<TcpClient>();
	
	Long interval = CommunicationStandard.RECONNECT_INTERVAL;
	
	public void pushReconnectList(Collection<TcpClient> clients){
		synchronized (reconnectList) {
			reconnectList.addAll(clients);
			reconnectList.notifyAll();
		}
	}
	
	public void pushReconnectList(TcpClient client){
		synchronized (reconnectList) {
			reconnectList.add(client);
			reconnectList.notifyAll();
		}
	}
	

	

	
	
	public void reconnect() throws InterruptedException{
		synchronized (this.reconnectList) {
			tempList.clear();
			while (this.reconnectList.isEmpty()){
				this.reconnectList.wait();
			}
			tempList.addAll(this.reconnectList);
			this.reconnectList.clear();
		}
		final CountDownLatch latch = new CountDownLatch(tempList.size());
		for (final TcpClient client:tempList){
			Executor.exec(new Runnable() {
				
				@Override
				public void run() {
					try {
						if (!client.connect()){
							pushReconnectList(client);
						}
					
					}catch (Exception e) {
						e.printStackTrace();
						pushReconnectList(client);
					}finally{
						latch.countDown();
					}
					
				}
			});
		}
		latch.await();
		
	}


	@Override
	public void run() {
		try {
			reconnect();
		} catch (Exception e) {
			System.out.println("");
			System.out.println(reconnectList);
			e.printStackTrace();
		}
	}



	public void setInterval(Long interval) {
		this.interval = interval;
	}
	
	
	
}
