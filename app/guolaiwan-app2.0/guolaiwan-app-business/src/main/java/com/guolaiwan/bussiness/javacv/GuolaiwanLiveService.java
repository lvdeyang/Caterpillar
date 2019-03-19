package com.guolaiwan.bussiness.javacv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

import com.sun.tools.classfile.Dependencies.Recorder;

public class GuolaiwanLiveService {

	private GuolaiwanSender sender;
	private Map<String,GuolaiwanGetter> getters = new HashMap<String, GuolaiwanGetter>();
	private int width;
	private int height;

	
    public void init(String pubName,int width,int height) {
    	this.width=width;
    	this.height=height;
    	sender=new GuolaiwanSender(pubName, width, height);
	}
    
    //机位开直播调用
    public void addGetter(String subLivePubName){
    	GuolaiwanGetter getter = new GuolaiwanGetter(subLivePubName, width, height,sender);
    	getters.put(subLivePubName,getter);
    	getter.start();
    }
    
    //导播上传成功后调用
    public void addMatPlayGetter(String liveId,String matPlayVideoPath){
    	GuolaiwanGetter getter = new GuolaiwanGetter(liveId,matPlayVideoPath,width, height,sender);
    	getters.put(liveId,getter);
    	getter.start();
    }
    
    public void removeGetter(String pubName){
    	GuolaiwanGetter getter = getters.get(pubName);
    	if(getter!=null){
    		getter.destory();
    	}
    	getters.remove(pubName);
    }
    
    public void destory(){
        sender.destory();
        if(getters.size() > 0){
        	for (String getterName : getters.keySet()) {
    			getters.get(getterName).destory();
    		}
        }
    }
    
    public void switchLive(String oldSubLivePubName, String newSubLivePubName){
    	//这里可能有先后问题，但是看测试效果，不把精力浪费在几乎不存在的事情上
    	if(getters.get(oldSubLivePubName) != null){
    		GuolaiwanGetter oldGetter = getters.get(oldSubLivePubName);
    		oldGetter.setUsed(false);
    	}
    	GuolaiwanGetter newGetter = getters.get(newSubLivePubName);
    	newGetter.setUsed(true);
    }
	
}
