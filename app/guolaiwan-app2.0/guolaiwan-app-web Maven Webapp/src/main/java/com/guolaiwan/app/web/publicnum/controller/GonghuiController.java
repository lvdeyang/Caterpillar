package com.guolaiwan.app.web.publicnum.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bytedeco.javacpp.RealSense.intrinsics;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import com.guolaiwan.app.web.publicnum.vo.VideoUploadVo;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.gonghui.dao.VideoDao;
import com.guolaiwan.bussiness.gonghui.dao.VideoSurpportDao;
import com.guolaiwan.bussiness.gonghui.po.VideoPo;
import com.guolaiwan.bussiness.gonghui.po.VideoSurpportPo;
import com.guolaiwan.bussiness.javacv.FishNewLiveService;

import javassist.expr.NewArray;
import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/gonghui")
public class GonghuiController {
	@Autowired
	SysConfigDAO conn_sys;
	@Autowired
	VideoDao conn_video;
	@Autowired
	private VideoSurpportDao conn_videoSurpport;
	private HashMap<String, VideoUploadVo> videoUploadMap=new HashMap<String, VideoUploadVo>();

	@RequestMapping(value = "/video/index")
	public ModelAndView homeIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/gonghui/home");
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		List<VideoPo> videoPos=conn_video.findSendByUserId(userId);
		if(videoPos!=null&&videoPos.size()>0){
			VideoPo videoPo=videoPos.get(0);
			String msg="";
			if(videoPo.getPassed()==1){
				mv.addObject("msg", "视频《"+videoPo.getVideoName()+"》,审核通过");
			}else if(videoPo.getPassed()==2){
				mv.addObject("msg", "视频《"+videoPo.getVideoName()+"》,审核未通过");
			}
			videoPo.setSend(1);
		}
		return mv;
	}
	
	@RequestMapping(value = "/video/upload/index")
	public ModelAndView videoIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/gonghui/upload");
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		List<VideoPo> videoPos=conn_video.findSendByUserId(userId);
		if(videoPos!=null&&videoPos.size()>0){
			VideoPo videoPo=videoPos.get(0);
			String msg="";
			if(videoPo.getPassed()==1){
				mv.addObject("msg", "视频《"+videoPo.getVideoName()+"》,审核通过");
			}else if(videoPo.getPassed()==2){
				mv.addObject("msg", "视频《"+videoPo.getVideoName()+"》,审核未通过");
			}
			videoPo.setSend(1);
		}
		return mv;
	}
	@RequestMapping(value = "/video/list/index")
	public ModelAndView listIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/gonghui/list");
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		List<VideoPo> videoPos=conn_video.findSendByUserId(userId);
		if(videoPos!=null&&videoPos.size()>0){
			VideoPo videoPo=videoPos.get(0);
			String msg="";
			if(videoPo.getPassed()==1){
				mv.addObject("msg", "视频《"+videoPo.getVideoName()+"》,审核通过");
			}else if(videoPo.getPassed()==2){
				mv.addObject("msg", "视频《"+videoPo.getVideoName()+"》,审核未通过");
			}
			videoPo.setSend(1);
		}
		return mv;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public ModelAndView uploadVideo(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/gonghui/video/list/index");
		
		String company=request.getParameter("company");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String videoName=request.getParameter("videoName");
		String coverUrl=request.getParameter("coverUrl");
		String playUrl=request.getParameter("playUrl");
		String companyType=request.getParameter("companyType");
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		VideoPo videoPo=new VideoPo();
		videoPo.setaCount(0);
		videoPo.setName(name);
		videoPo.setPhone(phone);
		videoPo.setCompany(company);
		videoPo.setCompanyType(companyType);
		videoPo.setPassed(0);
		videoPo.setPassedStr("审核中");
		videoPo.setVideoName(videoName);
		videoPo.setTooss(0);
		if(coverUrl==null||"".equals(coverUrl)){
			
			String[] fileUrls=playUrl.split("\\.");
			String imageUrl="";
			for (int i=0;i<fileUrls.length-1;i++) {
				String urlStr=fileUrls[i];
				if(imageUrl.equals("")){
					imageUrl+=urlStr;
				}else{
					imageUrl+="."+urlStr;
				}
			}
			imageUrl+=".jpg";
		
			videoPo.setCoverUrl(imageUrl);
		}else{
			videoPo.setCoverUrl(coverUrl);
		}
		
		videoPo.setPlayUrl(playUrl);
		videoPo.setUserId(userId);
		conn_video.save(videoPo);
		return mv;
	}

	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getUploadPercent", method = RequestMethod.GET)
	public Object getPercent(HttpServletRequest request) throws Exception{
		String fileName=request.getParameter("fileName");
		VideoUploadVo videoUploadvo=videoUploadMap.get(fileName);

		Map<String, Object> ret=new HashMap<String, Object>();
		if(videoUploadvo!=null){
			ret.put("data", videoUploadvo);
		}else{
			ret.put("data", "error");
		}
		return ret;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getUploadStatus", method = RequestMethod.GET)
	public Object getUploadStatus(HttpServletRequest request) throws Exception{
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        List<VideoPo> videoList=conn_video.findCheckingByUserId(userId);
		Map<String, Object> ret=new HashMap<String, Object>();
		if(videoList!=null){
			ret.put("data", "您有一个视频《"+videoList.get(0).getVideoName()+"》正在审核中，请等待管理审核");
		}else{
			ret.put("data", "error");
		}
		return ret;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Object upload(HttpServletRequest request) throws Exception {

		String url = "";
        System.out.println("Mr1:******************************"+new Date());
		if (!ServletFileUpload.isMultipartContent(request)) {

		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置缓冲区大小，这里是4kb
		factory.setSizeThreshold(409600);
		// 设置缓冲区目录
		factory.setRepository(null);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大文件尺寸，这里是180MB
		upload.setSizeMax(1000000000);
		// 得到所有的文件
		System.out.println("Mr2:******************************"+new Date());
		List<FileItem> items = upload.parseRequest(request);
		System.out.println("Mr3:******************************"+new Date());
		Iterator<FileItem> i = items.iterator();
		File fileIt = null;
		SysConfigPO sys = conn_sys.getSysConfig();
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			
			//判断已有文件则直接返回
			List<VideoPo> haveVideos=conn_video.findByField("playUrl", "gonghui/"+fi.getName());
			if(haveVideos!=null&&haveVideos.size()>0){
				VideoPo haveVideo=haveVideos.get(0);
				String webPath =  sys.getWebUrl()+"/" + haveVideo.getPlayUrl();
				if(haveVideo.getTooss()==0){
					webPath =  "http://www.guolaiwan.net/file/" + haveVideo.getPlayUrl();
				}
				
				JSONObject obj = new JSONObject();
				obj.put("url", haveVideo.getPlayUrl());
				obj.put("webPath", webPath);
				return obj;
			}
			
			
			
			VideoUploadVo videoUploadvo=new VideoUploadVo();
			videoUploadvo.setFileName(fi.getName());
			videoUploadvo.setFileSize(fi.getSize());
			videoUploadvo.setUploadSize(0);
			videoUploadvo.setUploadPercent(70);
			videoUploadMap.put(fi.getName(), videoUploadvo);
			url = uploadFile(fi);
		}
		System.out.println("Mr8:******************************"+new Date());
		String webPath =  "http://www.guolaiwan.net/file/" + url;
		JSONObject obj = new JSONObject();
		obj.put("url", url);
		obj.put("webPath", webPath);
		return obj;
	}
	public static void writeFile(String fileAbsolutePath, byte[] content) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileAbsolutePath);
        fos.write(content);
        fos.close();
    }

	private String uploadFile(FileItem file) {
		System.out.println("Mr4:******************************"+file.getName()+new Date());
		SysConfigPO sys = conn_sys.getSysConfig();

		String url = "gonghui" + File.separator + file.getName();
		String[] fileNames=file.getName().split("\\.");
		String imageName="";
		for (int i=0;i<fileNames.length-1;i++) {
			String nameStr=fileNames[i];
			if(imageName.equals("")){
				imageName+=nameStr;
			}else{
				imageName+="."+nameStr;
			}
		}
		imageName+=".jpg";
		String iamgeurl = "gonghui" + File.separator + imageName;
		File folder = new File(sys.getFolderUrl() + "gonghui");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			
			// 获取输出流
			OutputStream os = new FileOutputStream(sys.getFolderUrl() + url);
			// 获取输入流 CommonsMultipartFile 中可以直接得到文件的流
			InputStream is = file.getInputStream();
			int temp;
			// 一个一个字节的读取并写入
			int tempsize=0;
			byte[] bytes = new byte[1024];//存储读取到的多个字节
		    int len = 0;//记录每次读取的有效字节个数
			//while ((temp = is.read()) != (-1)) {
		    while ((len = is.read(bytes)) != -1) {
				os.write(bytes);
				tempsize+=bytes.length;
				if(tempsize==1000){
					tempsize=0;
					VideoUploadVo videoUploadvo=videoUploadMap.get(file.getName());
					if(videoUploadvo!=null){
						videoUploadvo.setUploadSize(videoUploadvo.getUploadSize()+1000);
						int percent=(int) (70+(videoUploadvo.getUploadSize()*30)/(videoUploadvo.getFileSize()));
						videoUploadvo.setUploadPercent(percent);
					}
				}
			}
			os.flush();
			os.close();
			is.close();
			
			System.out.println("Mr5:******************************"+file.getName()+new Date());
			File newFile=new File(sys.getFolderUrl() + url);
			//OSSUtils.createFolder("glw-old-file", "file/gonghui/");
			//OSSUtils.uploadObjectOSS1("file/gonghui/", file.getName(), file.getSize(), file.getInputStream());
			
		
			System.out.println("Mr6:******************************"+file.getName()+new Date());
			if(url.indexOf(".mp4")!=-1||url.indexOf(".MP4")!=-1){
			
				File newImageFile = new File(sys.getFolderUrl() +iamgeurl);
				FFmpegFrameGrabber grabber=new FFmpegFrameGrabber(newFile);
				grabber.start();
	            int lenght = grabber.getLengthInFrames();
	            int i = 0;
	            Frame f = null;
	            while (i < lenght) {
	                // 过滤前5帧，避免出现全黑的图片，依自己情况而定
	                f = grabber.grabFrame();
	                if ((i > 5) && (f.image != null)) {
	                    break;
	                }
	                i++;
	            }
	            Java2DFrameConverter converter =new Java2DFrameConverter();
	            BufferedImage fecthedImage =converter.getBufferedImage(f);
	            //下边是缩放
	            //BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	            //bi.getGraphics().drawImage(fecthedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null);
	            //ff.flush();
	            ImageIO.write(fecthedImage, "jpg", newImageFile);
	            grabber.stop();
	            grabber.close();
	            //OSSUtils.createFolder("glw-old-file", "file/gonghui/");
				//OSSUtils.uploadObjectOSS("file/gonghui/", newImageFile.getName(), newImageFile, new FileInputStream(newImageFile));
				System.out.println("Mr7:******************************"+file.getName()+new Date());
				
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
			
		}finally {
			videoUploadMap.remove(file.getName());
			return url;
		}

	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/videoList", method = RequestMethod.GET)
	public Object videoList(HttpServletRequest request) throws Exception{
		int page=Integer.parseInt(request.getParameter("page"));
		List<VideoPo> videolist=conn_video.findPassByPage(page, 10);
		if(videolist!=null){
			for (VideoPo videoPo : videolist) {
				if(videoPo.getTooss()==0){
					videoPo.setRealCoverUrl("http://www.guolaiwan.net/file/"+videoPo.getCoverUrl());
					videoPo.setRealPlayUrl("http://www.guolaiwan.net/file/"+videoPo.getPlayUrl());
				}else{
					videoPo.setRealCoverUrl(conn_sys.getSysConfig().getWebUrl()+"/"+videoPo.getCoverUrl());
					videoPo.setRealPlayUrl(conn_sys.getSysConfig().getWebUrl()+"/"+videoPo.getPlayUrl());
				}
			}
		}
		
		Map<String, Object> ret=new HashMap<String, Object>();
		ret.put("list", videolist==null?new ArrayList<VideoPo>():videolist);
		
		return ret;
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/acount", method = RequestMethod.POST)
	public Object acount(HttpServletRequest request) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        /*long count=conn_videoSurpport.countTodayByUser(userId);
        if(count>=5){
        	ret.put("msg", "一人一天最多投五票");
        	return ret;
        }*/
		String id=request.getParameter("id");
		synchronized(id){
			VideoPo videoPo=conn_video.get(Long.parseLong(id));
			videoPo.setaCount(videoPo.getaCount()+1);
			conn_video.save(videoPo);
			VideoSurpportPo videoSurpportPo=new VideoSurpportPo();
			videoSurpportPo.setUserId(userId);
			videoSurpportPo.setVideoId(videoPo.getId());
			videoSurpportPo.setUpdateTime(new Date());
			conn_videoSurpport.save(videoSurpportPo);
			return videoPo;
		}
		
	}
}
