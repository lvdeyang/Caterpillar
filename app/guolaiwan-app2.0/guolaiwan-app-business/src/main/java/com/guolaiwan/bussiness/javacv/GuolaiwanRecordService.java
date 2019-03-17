package com.guolaiwan.bussiness.javacv;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder.Exception;

import pub.caterpillar.weixin.constants.WXContants;

public class GuolaiwanRecordService extends Thread {
//	private String subivePubName;
//	private String path;
//	private int width;
//	private int height;
	private boolean isStop = false;
	private FFmpegFrameGrabber grabber;
	private FFmpegFrameRecorder recorder;
	public GuolaiwanRecordService(String subivePubName, String path, int width, int height) {
		grabber = new FFmpegFrameGrabber("rtmp://"+WXContants.Website+"/live/" + subivePubName);
		recorder = new FFmpegFrameRecorder(path, width, height, 1);
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
		System.out.println(subivePubName + "录制  grabber start");
	}

	@Override
	public void run() {
		try {
			grabber.start();
			recorder.start();
		} catch (Exception | org.bytedeco.javacv.FrameGrabber.Exception e) {
			e.printStackTrace();
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
	}
	
	public void destory() {
		isStop = true;	
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
