package com.guolaiwan.bussiness.javacv;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder.Exception;

import pub.caterpillar.weixin.constants.WXContants;

public class GuolaiwanRecordService extends Thread {
	private String pubName;
	private String path;
	private int width;
	private int height;
	private boolean isStop = false;

	public GuolaiwanRecordService(String pubName, String path, int width, int height) {
		this.pubName = pubName;
		this.path = path;
		this.width = width;
		this.height = height;
	}

	public void destory() {
		isStop = true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("rtmp://"+WXContants.Website+"/live/"+pubName);
		// 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
		FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(path, width, height, 1);

		try {
			grabber.start();
			recorder.start();
		} catch (org.bytedeco.javacv.FrameGrabber.Exception | Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while (!isStop) {
  
			try {
				
				Frame frame = null;
				if((frame = grabber.grabFrame()) != null) {
					recorder.setTimestamp(frame.timestamp);
					recorder.record(frame);
				}
			} catch (org.bytedeco.javacv.FrameGrabber.Exception | Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			recorder.stop();
			recorder.release();
			grabber.stop();
			grabber.release();
		} catch (Exception | org.bytedeco.javacv.FrameGrabber.Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
