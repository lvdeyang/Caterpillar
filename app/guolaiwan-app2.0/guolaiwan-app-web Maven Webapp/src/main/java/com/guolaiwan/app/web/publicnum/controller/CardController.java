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
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/card")
public class CardController {
	
	@RequestMapping(value = "/home/index")
	public ModelAndView homeIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/card/home");
		
		return mv;
	}
	
	@RequestMapping(value = "/card/index")
	public ModelAndView cardIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/card/info");
		
		return mv;
	}
	
	@RequestMapping(value = "/card2/index")
	public ModelAndView card2Index(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/card/info1");
		
		return mv;
	}
	
	
}
