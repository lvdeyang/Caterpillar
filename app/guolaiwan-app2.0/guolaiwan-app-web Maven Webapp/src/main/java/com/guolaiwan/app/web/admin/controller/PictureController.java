package com.guolaiwan.app.web.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.PictureVO;
import com.guolaiwan.bussiness.admin.dao.PictureDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;


@Controller
@RequestMapping("/admin/picture")
public class PictureController extends BaseController{

	@Autowired
	private PictureDAO conn_picture;
	@Autowired
	private SysConfigDAO conn_sysConfig;

	//同时添加
	@RequestMapping(value="/upload",method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		int count = conn_picture.getCountByPage();
		strMap.put("count",count);
		ModelAndView mv = new ModelAndView("admin/picture/list",strMap);
		return mv;
	}


	@ResponseBody
	@RequestMapping(value="/upload.do",method= RequestMethod.POST)
	public Map<String,Object> upload(@RequestParam("images") CommonsMultipartFile file) throws Exception{
		Map<String, Object> map= new HashMap<String, Object>();
		System.out.println("file:"+file);
		System.out.println(file.getFileItem());
		System.out.println(file.getSize());
		//创建日期文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String folderName = sdf.format(d);  //文件名
		String path=conn_sysConfig.getSysConfig().getFolderUrl()+folderName;

		//文件名
		String fileName = file.getOriginalFilename();  
		String newName = d.getTime()+fileName.substring(fileName.lastIndexOf(".") ); //时间戳+后缀名

		File folder =new File(path);
		if(folder.exists() ==false){     //如果路径不存在
			if(folder.getParentFile().exists()==false){
				map.put("code", "1");
				map.put("error", "文件路径错误！");
				return map;
			}
			folder.mkdir();
		}
		//上传
		File newFile=new File(path+"/"+newName);
		String config = conn_sysConfig.getSysConfig().getWebUrl()+"/"+folderName+"/"+newName;
		file.transferTo(newFile);           //写

		//写数据库
		PicturePO picture = new PicturePO();


		if(file.getSize()/1024>1024l){
			picture.setFileSize(new DecimalFormat("###.##").format((double)file.getSize()/1024/1024)+"M"); 
		}else if(file.getSize()/1024/1024>1024l){
			picture.setFileSize(new DecimalFormat("###.##").format((double)file.getSize()/1024/1024/1024)+"G");
		}else{
			picture.setFileSize(file.getSize()/1024+"kb") ;
		}
		picture.setFolde(folderName);
		picture.setUpdateTime(d);
		picture.setOldName(fileName);
		picture.setNewName(newName);
		conn_picture.save(picture);
		picture.setWebUrl(config);
		picture.setIntroduce("上传成功！");
		picture.setIf_valid(1);
		PictureVO pic = new PictureVO().set(picture);
		//上传到阿里云oss
		OSSUtils.createFolder("glw-old-file", "file/"+folderName+"/");
		OSSUtils.uploadObjectOSS("file/"+folderName+"/", newName,newFile, new FileInputStream(newFile));
		map.put("pic", pic);
		map.put("path", config);
		map.put("code", "0");
		return map;
	}

	// 异步读取。。。
	@ResponseBody
	@RequestMapping(value="/picList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int pagecurr,int ilimit) throws Exception {
		List<PicturePO> listpo = conn_picture.getPictureByPage(pagecurr,ilimit);
		List<PictureVO> listvo = PictureVO.getConverter(PictureVO.class).convert(listpo, PictureVO.class);
		for (PictureVO pictureVO : listvo) {
			String webUrl = pictureVO.getWebUrl();
			String[] splits = webUrl.split("\\.");
			if(splits[3].equals("MP4")||splits[3].equals("mp4")){
				pictureVO.setType("mp4");
			}else{
				pictureVO.setType("jgp");
			}
		}
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}


	// 异步删除。。。
	@ResponseBody
	@RequestMapping(value="picdel.do", method= RequestMethod.POST)
	public String picDel(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		conn_picture.deleteByUuid(uuid);
		return "success";
	}

	// 异步删除。。。
	@ResponseBody
	@RequestMapping(value="delAll.do", method= RequestMethod.POST)
	public String DelAll(HttpServletRequest request) throws Exception {
		String[] uuids  = request.getParameterValues("uuids[]");
		if(uuids.length ==0||uuids ==null) return "success";
		for (String uuid : uuids) {
			conn_picture.deleteByUuid(uuid);
		}
		return "success";
	}

	//通用选择图片
	@RequestMapping(value="/sellist",method= RequestMethod.GET)
	public ModelAndView selhome(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		String txtID=request.getParameter("sel");
		String imgurl=request.getParameter("img");
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		int count = conn_picture.getCountByPage();
		strMap.put("count",count);
		strMap.put("sel", txtID);
		strMap.put("imgurl", imgurl);
		strMap.put("sysConfig", sysConfig);

		ModelAndView mv = new ModelAndView("admin/picture/selectlist",strMap);
		return mv;
	}


	//通用添加图片
	@RequestMapping(value="/addlist",method= RequestMethod.GET)
	public ModelAndView addhome(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		String sId=request.getParameter("sId");
		String sImg =request.getParameter("sImg");
		String source =request.getParameter("source");
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		int count = conn_picture.getCountByPage();
		strMap.put("count",count);
		strMap.put("sId", sId);
		strMap.put("sImg", sImg);
		if (source.equals("activityrule")) {
			strMap.put("source", "activity");
			strMap.put("picdo", "rulepic.do");
		}else if(source.equals("live1")){
			strMap.put("source", "live");
			strMap.put("picdo", "giftpic.do");
		}
		else if(source.equals("live2")){
			strMap.put("source", "live");
			strMap.put("picdo", "addImg.do");
		}else if(source.equals("live3")){
			strMap.put("source", "live");
			strMap.put("picdo", "addredImg.do");
		}
		else if(source.equals("vote")){
			strMap.put("source", "vote");
			strMap.put("picdo", "votemodularpic");
		}
		else if(source.equals("option")){
			strMap.put("source", "vote");
			strMap.put("picdo", "voteoptionpic");
		}
		else if(source.equals("downpic")){
			strMap.put("source", "vote");
			strMap.put("picdo", "votedownpic");
		}
		else if(source.equals("votePic")){
			strMap.put("source", "vote");
			strMap.put("picdo", "votepics");
		}
		else if(source.equals("questionnaire")){
			strMap.put("source", "questionnaire");
			strMap.put("picdo", "backgroundpic");
		}
		else if(source.equals("supersell")){
			strMap.put("source", "supersell");
			strMap.put("picdo", "pic.do");
		}
		else {
			strMap.put("source", source);
			strMap.put("picdo", "pic.do");
		}
		strMap.put("sysConfig", sysConfig);

		ModelAndView mv = new ModelAndView("admin/picture/addlist",strMap);
		return mv;
	}


	
}