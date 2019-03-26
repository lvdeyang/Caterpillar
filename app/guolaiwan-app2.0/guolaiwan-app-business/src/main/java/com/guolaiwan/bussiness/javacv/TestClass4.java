package com.guolaiwan.bussiness.javacv;

import javax.swing.JFrame;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.RealSense.timestamp_callback;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.omg.CORBA.TIMEOUT;

import pub.caterpillar.weixin.constants.WXContants;

public class TestClass4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputFile = "rtmp://" + WXContants.Website + "/live/216A45";

		String outputFile = "rtmp://" + WXContants.Website + "/live/test";

		try {
			recordPush(inputFile, outputFile, 25);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void recordPush(String inputFile, String outputFile, int v_rs)
			throws Exception, org.bytedeco.javacv.FrameRecorder.Exception, InterruptedException {
		Loader.load(opencv_objdetect.class);
		FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(inputFile);
		grabber.setOption("", "5000000");
		grabber.start();

		FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, 360, 640, 1);
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
		recorder.setFormat("flv"); // rtmp的类型
		recorder.setFrameRate(25);
		recorder.setGopSize(25);
		recorder.start();
		Frame frame = null;
		long startTime = 0;
		int count = 0;
		long videoTS = 0;
		while ((frame = grabber.grab()) != null) {

			if (startTime == 0)
				startTime = System.currentTimeMillis();

			// 创建一个 timestamp用来写入帧中
			videoTS = 1000 * (System.currentTimeMillis() - startTime);
			// 检查偏移量
			if (videoTS > recorder.getTimestamp()) {
				System.out.println("Lip-flap correction: " + videoTS + " : " + recorder.getTimestamp() + " -> "
						+ (videoTS - recorder.getTimestamp()));
				// 告诉录制器写入这个timestamp
				recorder.setTimestamp(videoTS);
			}

			//recorder.setTimestamp(frame.timestamp);// 时间戳
			recorder.record(frame);
		}
		grabber.stop();
		grabber.release();
		recorder.stop();
		recorder.release();
		System.exit(2);
	}

}
