/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.communication.tcp.HeartBeat
 * @description:TODO
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.communication.tcp.component;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import pub.caterpillar.communication.tcp.TcpClient;
import pub.caterpillar.communication.tcp.executor.Executor;
import pub.caterpillar.communication.tcp.standard.CommunicationStandard;






public class HeartBeat implements Runnable{
	Set<TcpClient> heartbeatList = new LinkedHashSet<TcpClient>();
	List<TcpClient> tempList = new LinkedList<TcpClient>();
	
	Long interval = CommunicationStandard.HEART_BEAT_INTERVAL;
	
	public void pushHeartBeat(Collection<TcpClient> clients){
		synchronized (heartbeatList) {
			heartbeatList.addAll(clients);
			heartbeatList.notifyAll();
		}
	}
	
	public void pushHeartBeat(TcpClient client){
		if (client == null){
			return;
		}
		synchronized (heartbeatList) {
			heartbeatList.add(client);
			heartbeatList.notifyAll();
		}
	}
	
	public void heartBeating() throws InterruptedException{
		synchronized (this.heartbeatList) {
			tempList.clear();
			while (this.heartbeatList.isEmpty()){
				this.heartbeatList.wait();
			}
			tempList.addAll(this.heartbeatList);
			this.heartbeatList.clear();
		}
		final CountDownLatch latch = new CountDownLatch(tempList.size());
		for (final TcpClient client:tempList){
			Executor.exec(new Runnable() {
				
				@Override
				public void run() {
					try {
						
							if (client.isClosed()) return;
							boolean reachable = client.getRemoteIp().isReachable(null,64,5000);
							if (reachable){
								pushHeartBeat(client);
							}else if(!client.isClosed()){
								if (client.getRemoteIp().isReachable(null,64,5000)){
									pushHeartBeat(client);
								}else{
									//ComLogger.err(client.getRomoteAddress(), "address is not reachable");
									client.disConnect();	
								}
							}
						
					} catch (Exception e) {
						pushHeartBeat(client);
						e.printStackTrace();
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
			heartBeating();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void setInterval(Long interval) {
		this.interval = interval;
	}
	
	
}
