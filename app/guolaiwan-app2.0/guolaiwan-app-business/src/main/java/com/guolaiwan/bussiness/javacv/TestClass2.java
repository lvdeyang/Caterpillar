package com.guolaiwan.bussiness.javacv;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class TestClass2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputFile = "216A45";

		String outputFile = "D:\\216A45.mp4";
		
		GuoliawanLiveServiceWrapper wrapper=GuoliawanLiveServiceWrapper.getInstance();
		wrapper.startRecord(inputFile, outputFile);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wrapper.stopRecord(inputFile);
        
	}

}

