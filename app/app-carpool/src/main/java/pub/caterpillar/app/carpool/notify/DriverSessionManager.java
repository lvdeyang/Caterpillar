package pub.caterpillar.app.carpool.notify;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

public class DriverSessionManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverSessionManager.class);

	private ConcurrentHashMap<String, Session> wscache = new ConcurrentHashMap<String, Session>();
	
	private static DriverSessionManager instance;
	
	private DriverSessionManager(){}
	
	public static DriverSessionManager getInstance(){
		if(instance == null){
			instance = new DriverSessionManager();
		}
		return instance;
	}
	
	public void add(String mobile, Session session) throws IOException{
		if(wscache.containsKey(mobile)){
			Session session_old = wscache.get(mobile);
			wscache.remove(mobile);
			//销毁session
			session_old.close();
			LOGGER.info(new StringBufferWrapper().append("旧的会话销毁，mobile：").append(mobile).toString());
		}
		wscache.put(mobile, session);
		LOGGER.info(new StringBufferWrapper().append("新的会话开启，mobile：").append(mobile).toString());
	}
	
	public Session getSession(String mobile){
		return this.wscache.get(mobile);
	}
	
	public void remove(Session session){
		Set<String> keySet = this.wscache.keySet();
		for(String key:keySet){
			if(this.wscache.get(key).equals(session)){
				this.wscache.remove(key);
				LOGGER.info(new StringBufferWrapper().append("会话销毁，mobile：").append(key).toString());
				return;
			}
		}
	}
	
	public Set<String> filterMobile(String mobile){
		Set<String> userList = new HashSet<String>();
		Set<String> keySet = this.wscache.keySet();
		for(String key:keySet){
			if(!key.equals(mobile)){
				userList.add(key);
			}
		}
		return userList;
	}
	
	public Set<Session> filterSessionByMobile(String mobile){
		Set<Session> sessionList = new HashSet<Session>();
		Set<String> keySet = this.wscache.keySet();
		for(String key:keySet){
			if(!key.equals(mobile)){
				sessionList.add(wscache.get(key));
			}
		}
		return sessionList;
	}
	
}
