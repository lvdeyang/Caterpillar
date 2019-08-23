package com.guolaiwan.bussiness.javacv;

import java.util.HashMap;
import java.util.Map;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacpp.RealSense.intrinsics;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class FishNewLiveService extends Thread {

	private String url1;
	private String url2;
	private String outurl;
	private int flg = 1;
	private boolean isStart = false;
	private static Map<String, FishNewLiveService> fishNewLiveServiceMap = new HashMap<String, FishNewLiveService>();

	FFmpegFrameGrabber grabber = null;
	FFmpegFrameGrabber grabber2 = null;
	FFmpegFrameRecorder recorder = null;

	public FishNewLiveService(String _url1, String _url2, String _outurl) {
		url1 = _url1;
		url2 = _url2;
		outurl = _outurl;

	}

	public static FishNewLiveService getinstance(String _url1, String _url2, String _outurl) {
		if (fishNewLiveServiceMap.get(_outurl) == null) {
			FishNewLiveService fishNewLiveService = new FishNewLiveService(_url1, _url2, _outurl);
			fishNewLiveServiceMap.put(_outurl, fishNewLiveService);
		}
		return fishNewLiveServiceMap.get(_outurl);
	}

	public boolean isStart() {
		return isStart;
	}

	public void startPush() {
		this.start();
		isStart = true;
	}

	public void switchPro(int no) {
		flg = no;
	}

	public void stopPush() {

		// 这里要清理哦
		try {
			if (grabber != null) {
				grabber.stop();
				grabber.release();
			}
			if (grabber2 != null) {
				grabber2.stop();
				grabber2.release();
			}
			if (recorder != null) {
				recorder.stop();
				recorder.release();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		fishNewLiveServiceMap.remove(outurl);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			Loader.load(opencv_objdetect.class);

			grabber = FFmpegFrameGrabber.createDefault(url1);
			grabber.start();
			grabber2 = FFmpegFrameGrabber.createDefault(url2);
			grabber2.start();
			recorder = new FFmpegFrameRecorder(outurl, 640, 360, 1);
			recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
			recorder.setFormat("flv"); // rtmp的类型
			recorder.setFrameRate(25);
			recorder.setGopSize(25);
			recorder.start();
			Frame frame = null;
			Frame frame2 = null;
			long startTime = 0;
			int count = 0;

			while ((frame = grabber.grab()) != null && (frame2 = grabber2.grab()) != null) {
				if (flg == 1) {
					recorder.setTimestamp(frame.timestamp);// 时间戳
					recorder.record(frame);
				} else if (flg == 2) {
					recorder.setTimestamp(frame.timestamp);// 时间戳
					frame2.timestamp = frame.timestamp;
					recorder.record(frame2);
				}
			}
			grabber.stop();
			grabber.release();
		
			grabber2.stop();
			grabber2.release();
			recorder.stop();
			recorder.release();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
