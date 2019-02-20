package com.guolaiwan.app.web.webScoket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;

public class WSManager {

	private ConcurrentHashMap<String, Session> wscache = new ConcurrentHashMap<String, Session>();
	
	private static WSManager instance;
	
	private WSManager(){}
	
	public static WSManager getInstance(){
		if(instance == null){
			instance = new WSManager();
		}
		return instance;
	}
	
	public void add(String key, Session session) throws IOException{
		if(wscache.containsKey(key)){
			Session session_old = wscache.get(key);
			wscache.remove(key);
			//销毁session
			session_old.close();
		}
		wscache.put(key, session);
	}
	
	public Session getSession(String key){
		return this.wscache.get(key);
	}
	
	public void remove(Session session){
		Set<String> keySet = this.wscache.keySet();
		for(String key:keySet){
			if(this.wscache.get(key).equals(session)){
				this.wscache.remove(key);
				return;
			}
		}
	}
	
	
	public Set<Session> findLiveSession(long liveId){
		Set<Session> sessionList = new HashSet<Session>();
		Set<String> keySet = this.wscache.keySet();
		for(String key:keySet){
			long parseLong = Long.parseLong(key.split("-")[1]);
			if(parseLong==liveId){
				sessionList.add(wscache.get(key));
			}
		}
		return sessionList;
	}
	
}
