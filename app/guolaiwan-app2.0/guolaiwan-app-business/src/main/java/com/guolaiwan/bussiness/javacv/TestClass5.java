package com.guolaiwan.bussiness.javacv;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.RealSense.timestamp_callback;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameRecorder;

import pub.caterpillar.weixin.constants.WXContants;

public class TestClass5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputFile = "D:\\smartvideo片源\\片源\\电影\\山炮进城_高清.mp4";

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
		grabber.start();
		FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, 672, 378, 1);
		//OpenCVFrameRecorder recorder=new OpenCVFrameRecorder(outputFile, 672, 378);
		//OpenCVFrameGrabber grabber=OpenCVFrameGrabber.createDefault(inputFile);
		//grabber.start();
		recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 28
		recorder.setFormat("flv"); // rtmp的类型
		recorder.setFrameRate(25);
		recorder.setGopSize(25);
		recorder.start();
		Frame frame = null;
		long startTime = 0;
		int count = 0;
		OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage(); 
		//位置
		Point point1 = new Point(10, 50);  
        // 颜色  
        Scalar scalar1 = new Scalar(0, 255, 255, 0);   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Mat logo = opencv_imgcodecs.imread("D:\\smartvideo片源\\cctv台标\\cctv1.jpg");

		while ((frame = grabber.grab()) != null) {

			Mat mat = converter.convertToMat(frame);
            if(mat==null){
            	continue;
            }
			// 加文字水印，opencv_imgproc.putText（图片，水印文字，文字位置，字体，字体大小，字体颜色，字体粗度，文字反锯齿，是否翻转文字）
			opencv_imgproc.putText(mat, "cao da xi shi sha x", point1, opencv_imgproc.CV_FONT_HERSHEY_TRIPLEX, 0.8, scalar1, 2,
					20, false);
			
			Mat ROI = mat.apply(new Rect(100, 100, logo.cols(), logo.rows()));
			opencv_core.addWeighted(logo, 0.5, logo, 0.5, 0.0, ROI);
			
			// 在窗口显示处理后的图像，Frame frame=converter.convert(mat);
            if(count==3000){
            	opencv_imgcodecs.imwrite("D:\\fish.jpg", logo);
            }
		    Frame frame1= converter.convert(mat);

			//recorder.setTimestamp(frame.timestamp);// 时间戳
			recorder.record(frame1);
			count++;
		}
		grabber.stop();
		grabber.release();
		recorder.stop();
		recorder.release();
		System.exit(2);
	}

}
