package com.guolaiwan.bussiness.javacv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.FlyCapture2.Image;
import org.bytedeco.javacpp.RealSense.intrinsics;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;

import pub.caterpillar.weixin.constants.WXContants;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class GuoliawanLiveServiceWrapper {
	private int width = 640;//分辨率
	private int height = 360;
	private Map<String, GuolaiwanLiveService> serviceMap = new HashMap<String, GuolaiwanLiveService>();
	private Map<String, GuolaiwanRecordService> recordMap = new HashMap<String, GuolaiwanRecordService>();
	
	private static GuoliawanLiveServiceWrapper instance;
    private GuoliawanLiveServiceWrapper() {}
    public static GuoliawanLiveServiceWrapper getInstance() {
        if (instance == null) {
            instance = new GuoliawanLiveServiceWrapper();
        }
        return instance;
    }
	
	public void startLive(String pubName,String subLivePubName) {
		serviceMap.get(pubName).switchLive(null, subLivePubName);
	}
	
	public void startSubLive(String pubName, String subLivePubName) {
		if(serviceMap.get(pubName) == null){
			GuolaiwanLiveService guolaiwanLiveService = new GuolaiwanLiveService();
			guolaiwanLiveService.init(pubName, width, height);
			serviceMap.put(pubName, guolaiwanLiveService);
		}
		serviceMap.get(pubName).addGetter(subLivePubName);
	}
	
	public void startMatPlay(String pubName, String liveId,String matPlayVideoPath){
		if(serviceMap.get(pubName) == null){
			GuolaiwanLiveService guolaiwanLiveService = new GuolaiwanLiveService();
			guolaiwanLiveService.init(pubName, width, height);
			serviceMap.put(pubName, guolaiwanLiveService);
		}
		serviceMap.get(pubName).addMatPlayGetter(liveId, matPlayVideoPath);
	}
	
	public void stopLive(String pubName) {
		serviceMap.get(pubName).destory();
		serviceMap.remove(pubName);
	}
	
	public void stopSubLive(String pubName, String subName) {
		serviceMap.get(pubName).removeGetter(subName);
	}

	public void stopMatPlay(String pubName, String liveId){
		serviceMap.get(pubName).removeGetter(liveId);
	}

	public void switchSubLive(String pubName,String oldsubName, String subName) {
		serviceMap.get(pubName).switchLive(oldsubName, subName);
	}
	
	// path为存储路径，以文件名结束（/video/live/ceshi.mp4）
	public void startRecord(String subivePubName, String path) {
		GuolaiwanRecordService service = new GuolaiwanRecordService(subivePubName, path, width, height);
		service.start();
		recordMap.put(subivePubName, service);
	}

	public void stopRecord(String subivePubName) {
		recordMap.get(subivePubName).destory();
	}

	public void getImage(String pubName, String path) {
		FFmpegFrameGrabber ff = new FFmpegFrameGrabber("rtmp://"+WXContants.Website+"/live/" + pubName);
		try {
			ff.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ff.getAudioChannels();
		String rotate = ff.getVideoMetadata("rotate");// 视频的旋转角度
		int lenght = ff.getLengthInFrames();
		int i = 0;
		Frame f = null;

		
		try {
			f = ff.grabFrame();
			IplImage src = null;
			if (null != rotate && rotate.length() > 1) {
				OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
				src = converter.convert(f);
				f = converter.convert(rotate(src, Integer.valueOf(rotate)));
			}
			doExecuteFrame(f, path);
			i++;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private IplImage rotate(IplImage src, int angle) {
		IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
		opencv_core.cvTranspose(src, img);
		opencv_core.cvFlip(img, img, angle);
		return img;
	}

	private void doExecuteFrame(Frame f, String targetFileName) {
		if (null == f || null == f.image) {
			return;
		}
		Java2DFrameConverter converter = new Java2DFrameConverter();
		BufferedImage bi = converter.getBufferedImage(f);
		File output = new File(targetFileName);
		try {
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
