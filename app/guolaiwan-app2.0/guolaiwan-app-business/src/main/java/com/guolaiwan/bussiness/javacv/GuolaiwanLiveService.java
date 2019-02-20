package com.guolaiwan.bussiness.javacv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.bytedeco.javacv.Frame;

import com.sun.tools.classfile.Dependencies.Recorder;

public class GuolaiwanLiveService {

	private GuolaiwanSender sender;
	private Map<String,GuolaiwanGetter> getters=new HashMap<String, GuolaiwanGetter>();
	private int width;
	private int height;

	
	/*private static GuolaiwanLiveService instance;
    private GuolaiwanLiveService() {}
    public static GuolaiwanLiveService getInstance() {
        if (instance == null) {
            instance = new GuolaiwanLiveService();
        }
        return instance;
    }*/
	
	
    public void init(String pubName,int width,int height) {
		// TODO Auto-generated constructor stub
    	this.width=width;
    	this.height=height;
    	sender=new GuolaiwanSender(pubName, width, height);
	}
    
    public void addGetter(String pubName){
    	GuolaiwanGetter getter = new GuolaiwanGetter(pubName, width, height,sender);
    	getters.put(pubName,getter);
    	getter.start();
    }
    
    public void removeGetter(String pubName){
    	GuolaiwanGetter getter = getters.get(pubName);
    	if(getter!=null){
    		getter.destory();
    	}
    	
    }
    
    public void destory(){
        sender.destory();
        for (String getterName : getters.keySet()) {
			getters.get(getterName).destory();
		}
    }
    
    public void switchLive(String oldSubName, String newSubName){
    	//这里可能有先后问题，但是看测试效果，不把精力浪费在几乎不存在的事情上
    	if(getters.get(oldSubName)!=null){
    		getters.get(oldSubName).setUsed(false);
    	}
    	
    	getters.get(newSubName).setUsed(true);
    }
	
}
