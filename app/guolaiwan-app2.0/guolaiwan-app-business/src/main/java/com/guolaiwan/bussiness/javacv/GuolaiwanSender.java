package com.guolaiwan.bussiness.javacv;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder.Exception;

import pub.caterpillar.weixin.constants.WXContants;

import org.bytedeco.javacv.OpenCVFrameConverter;

public class GuolaiwanSender{
	private FFmpegFrameRecorder recorder;
    private boolean isStart=false;
	public GuolaiwanSender(String pubName,int width,int height) {
		recorder = new FFmpegFrameRecorder("rtmp://"+WXContants.Website+"/live/" + pubName, width,
				height, 1);
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
		recorder.setFormat("flv"); // rtmp的类型
		recorder.setFrameRate(25);
		recorder.setGopSize(25);
	}
	
	public void destory(){
		try {
			recorder.stop();
			recorder.release();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(Frame frame,String name){
		try {
			if(!isStart){
				recorder.start();
				isStart=true;
			}
			recorder.setTimestamp(frame.timestamp);
			recorder.record(frame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
