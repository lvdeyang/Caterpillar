package com.guolaiwan.bussiness.javacv;

import java.util.Date;

import javax.swing.JFrame;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.RealSense.intrinsics;
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

import pub.caterpillar.weixin.constants.WXContants;

public class TestClass6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputFile = "D:\\smartvideo片源\\片源\\综艺\\欢乐喜剧人  160221_高清.mp4";

		String outputFile = "rtmp://"+WXContants.Website+"/live/test";

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
		FFmpegFrameGrabber grabber=FFmpegFrameGrabber.createDefault(inputFile);
		grabber.start();
		FFmpegFrameGrabber grabber2=FFmpegFrameGrabber.createDefault("D:\\smartvideo片源\\片源\\综艺\\王牌对王牌 160304_高清.mp4");
		grabber2.start();
		FFmpegFrameRecorder recorder=new FFmpegFrameRecorder(outputFile, 672, 378,1);
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
		recorder.setFormat("flv"); // rtmp的类型
		recorder.setFrameRate(25);
		recorder.setGopSize(25);
		recorder.start();
		Frame frame=null;
		Frame frame2=null;
		long startTime=0;
		int count=0;
		while ((frame = grabber.grab()) != null&&(frame2 = grabber2.grab()) != null) {
			recorder.setTimestamp(frame.timestamp);
			if(count%200==0){
				System.out.println("recorder:"+recorder.getTimestamp());
				System.out.println("frame1:"+frame.timestamp);
				System.out.println("frame2:"+frame2.timestamp);
			}
			
		    if(count<1000){
		    	// 时间戳
		    	
				recorder.record(frame);
		    }else if(count<2000){
		    	//recorder.setTimestamp(frame2.timestamp);// 时间戳
		    	//frame2.timestamp=frame.timestamp;
				recorder.record(frame2);
		    }else if(count<3000){
		    	//recorder.setTimestamp(frame.timestamp);// 时间戳
				recorder.record(frame);
				
		    }else if(count<4000){
		    	//recorder.setTimestamp(frame2.timestamp);// 时间戳
		    	//frame2.timestamp=frame.timestamp;
				recorder.record(frame2);
		    }
			if(count==4000){
				count=0;
			}
			count++;
		}
        grabber.stop();
        grabber.release();
        recorder.stop();
        recorder.release();
		System.exit(2);
	}

}
