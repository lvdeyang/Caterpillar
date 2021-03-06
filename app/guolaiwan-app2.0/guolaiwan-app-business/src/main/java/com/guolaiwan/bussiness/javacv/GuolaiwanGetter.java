package com.guolaiwan.bussiness.javacv;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import pub.caterpillar.weixin.constants.WXContants;

public class GuolaiwanGetter extends Thread {
    private FFmpegFrameGrabber grabber;
    private boolean isRun=true;
    private boolean isUsed=false;
    private GuolaiwanSender sender;
    //垫播pubName使用liveId,机位直播pubName使用subLivePubName
    private String pubName;
	public GuolaiwanGetter(String pubName,int width,int height, GuolaiwanSender sender) {
	    this.sender=sender;
	    this.pubName=pubName;
		try {
			grabber = FFmpegFrameGrabber.createDefault("rtmp://"+WXContants.Website+"/live/" + pubName);
			grabber.start();
			System.out.println(pubName + " grabber start");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//垫播用
	public GuolaiwanGetter(String liveId,String matPlayVideoPath,int width,int height, GuolaiwanSender sender) {
	    this.sender=sender;
	    this.pubName=liveId;
		try {
			//TODO 改地址
			grabber = FFmpegFrameGrabber.createDefault(matPlayVideoPath);
			grabber.start();
			System.out.println("matPlay grabber start");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while (isRun) {
				Frame currentframe = grabber.grab();
				if (currentframe == null) {
					continue;
				}
				if(isUsed){
					sender.send(currentframe,this.pubName);
				}
			}
			grabber.stop();
			grabber.release();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void setUsed(boolean isUsed){
		this.isUsed=isUsed;
	}
	
	public void destory(){
		isRun=false;
		//grabber stop在run方法中
	}
    
}
