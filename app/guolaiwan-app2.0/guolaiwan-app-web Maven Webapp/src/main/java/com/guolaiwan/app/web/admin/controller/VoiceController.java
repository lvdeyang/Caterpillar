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
import com.guolaiwan.app.web.admin.vo.VoiceVO;
import com.guolaiwan.bussiness.admin.dao.VoiceDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.VoicePO;

import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;


@Controller
@RequestMapping("/admin/Voice")
public class VoiceController extends BaseController{

	@Autowired
	private VoiceDAO conn_Voice;
	@Autowired
	private SysConfigDAO conn_sysConfig;

	//同时添加
	@RequestMapping(value="/upload",method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		int count = conn_Voice.getCountByPage();
		strMap.put("count",count);
		ModelAndView mv = new ModelAndView("admin/Voice/list",strMap);
		return mv;
	}


	@ResponseBody
	@RequestMapping(value="/upload.do",method= RequestMethod.POST)
	public Map<String,Object> upload(@RequestParam("images") CommonsMultipartFile file) throws Exception{
		Map<String, Object> map= new HashMap<String, Object>();
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

		
		OSSUtils.createFolder("glw-old-file", "file/"+folderName+"/");
		OSSUtils.uploadObjectOSS("file/"+folderName+"/", newName,newFile, new FileInputStream(newFile));
		
		//写数据库
		VoicePO Voice = new VoicePO();


		if(file.getSize()/1024>1024l){
			Voice.setFileSize(new DecimalFormat("###.##").format((double)file.getSize()/1024/1024)+"M"); 
		}else if(file.getSize()/1024/1024>1024l){
			Voice.setFileSize(new DecimalFormat("###.##").format((double)file.getSize()/1024/1024/1024)+"G");
		}else{
			Voice.setFileSize(file.getSize()/1024+"kb") ;
		}
		Voice.setFolde(folderName);
		Voice.setUpdateTime(d);
		Voice.setOldName(fileName);
		Voice.setNewName(newName);
		conn_Voice.save(Voice);
		Voice.setWebUrl(config);
		Voice.setIntroduce("上传成功！");
		Voice.setIf_valid(1);
		VoiceVO pic = new VoiceVO().set(Voice);
		map.put("pic", pic);
		map.put("path", config);
		map.put("code", "0");
		return map;
	}

	// 异步读取。。。
	@ResponseBody
	@RequestMapping(value="/picList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int pagecurr,int ilimit) throws Exception {
		List<VoicePO> listpo = conn_Voice.getVoiceByPage(pagecurr,ilimit);
		List<VoiceVO> listvo = VoiceVO.getConverter(VoiceVO.class).convert(listpo, VoiceVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}


	// 异步删除。。。
	@ResponseBody
	@RequestMapping(value="picdel.do", method= RequestMethod.POST)
	public String picDel(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		conn_Voice.deleteByUuid(uuid);
		return "success";
	}

	// 异步删除。。。
	@ResponseBody
	@RequestMapping(value="delAll.do", method= RequestMethod.POST)
	public String DelAll(HttpServletRequest request) throws Exception {
		String[] uuids  = request.getParameterValues("uuids[]");
		if(uuids.length ==0||uuids ==null) return "success";
		for (String uuid : uuids) {
			conn_Voice.deleteByUuid(uuid);
		}
		return "success";
	}

	//通用选择图片
	@RequestMapping(value="/sellist",method= RequestMethod.GET)
	public ModelAndView selhome(HttpServletRequest request){
		String txtID=request.getParameter("sel");
		String imgurl=request.getParameter("img");
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		Map<String, Object> strMap=new HashMap<String, Object>();
		int count = conn_Voice.getCountByPage();
		strMap.put("count",count);
		strMap.put("sel", txtID);
		strMap.put("imgurl", imgurl);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/Voice/selectlist",strMap);
		return mv;
	}


	//	long  startTime=System.currentTimeMillis();
	//	long  endTime=System.currentTimeMillis();
	//	System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
}
