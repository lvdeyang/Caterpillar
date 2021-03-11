package com.guolaiwan.app.web.publicnum.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.gonghui.dao.VideoDao;
import com.guolaiwan.bussiness.gonghui.dao.VideoSurpportDao;
import com.guolaiwan.bussiness.gonghui.po.VideoPo;
import com.guolaiwan.bussiness.gonghui.po.VideoSurpportPo;
import com.guolaiwan.bussiness.javacv.FishNewLiveService;

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
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Object upload(HttpServletRequest request) throws Exception {

		String url = "";

		if (!ServletFileUpload.isMultipartContent(request)) {

		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置缓冲区大小，这里是4kb
		factory.setSizeThreshold(40960);
		// 设置缓冲区目录
		factory.setRepository(null);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大文件尺寸，这里是180MB
		upload.setSizeMax(181943040);
		// 得到所有的文件
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> i = items.iterator();
		File fileIt = null;
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			url = uploadFile(fi);
		}

		SysConfigPO sys = conn_sys.getSysConfig();
		String webPath = sys.getWebUrl() + "/" + url;
		JSONObject obj = new JSONObject();
		obj.put("url", url);
		obj.put("webPath", webPath);
		return obj;
	}

	private String uploadFile(FileItem file) {

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
			while ((temp = is.read()) != (-1)) {
				os.write(temp);
			}
			os.flush();
			os.close();
			is.close();

			File newFile = new File(sys.getFolderUrl() + url);
			OSSUtils.createFolder("glw-old-file", "file/gonghui/");
			OSSUtils.uploadObjectOSS("file/gonghui/", file.getName(), newFile, new FileInputStream(newFile));
			
			
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
			
            OSSUtils.createFolder("glw-old-file", "file/gonghui/");
			OSSUtils.uploadObjectOSS("file/gonghui/", newImageFile.getName(), newImageFile, new FileInputStream(newImageFile));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return url;
		}

	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/videoList", method = RequestMethod.GET)
	public Object videoList(HttpServletRequest request) throws Exception{
		int page=Integer.parseInt(request.getParameter("page"));
		List<VideoPo> videolist=conn_video.findPassByPage(page, 10);
		Map<String, Object> ret=new HashMap<String, Object>();
		ret.put("list", videolist);
		ret.put("webPath", conn_sys.getSysConfig().getWebUrl());
		
		return ret;
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/acount", method = RequestMethod.POST)
	public Object acount(HttpServletRequest request) throws Exception{
		Map<String, Object> ret=new HashMap<String, Object>();
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        long count=conn_videoSurpport.countTodayByUser(userId);
        if(count>=5){
        	ret.put("msg", "一人一天最多投五票");
        	return ret;
        }
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
