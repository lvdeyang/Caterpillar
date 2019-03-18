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
	private FFmpegFrameGrabber baseGrabber;
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
    
    //机位开直播调用
    public void addGetter(String subLivePubName){
    	GuolaiwanGetter getter = new GuolaiwanGetter(subLivePubName, width, height,sender);
    	if(getters.size() == 0){
    		//说明还没有开播机位
    		//切流时需要共享时间戳
    		//因此保存第一个开直播的机位的Grabber作为baseGrabber，将将它的时间戳作为所有机位以及信号流时间戳
    		if(baseGrabber == null){
    			baseGrabber = getter.getCurrGrabber();
    		}
    	}else{
    		//已经有开直播机位
    		getter.setBaseGrabber(baseGrabber);
    	}
    	getters.put(subLivePubName,getter);
    	getter.start();
    }
    
    //导播上传成功后调用
    public void addMatPlayGetter(String liveId,String matPlayVideoPath){
    	GuolaiwanGetter getter = new GuolaiwanGetter(liveId,matPlayVideoPath,width, height,sender);
    	if(getters.size() == 0){
    		//说明还没有开播机位
    		//切流时需要共享时间戳
    		//因此保存第一个开直播的机位的Grabber作为baseGrabber，将将它的时间戳作为所有机位以及信号流时间戳
    		if(baseGrabber == null){
    			baseGrabber = getter.getCurrGrabber();
    		}
    	}else{
    		//已经有开直播机位
    		getter.setBaseGrabber(baseGrabber);
    	}
    	getters.put(liveId,getter);
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
    
    public void switchLive(String oldSubLivePubName, String newSubLivePubName){
    	//这里可能有先后问题，但是看测试效果，不把精力浪费在几乎不存在的事情上
    	if(getters.get(oldSubLivePubName) != null){
    		getters.get(oldSubLivePubName).setUsed(false);
    	}
    	getters.get(newSubLivePubName).setUsed(true);
    }
	
}
