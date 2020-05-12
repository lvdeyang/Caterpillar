package com.guolaiwan.app.gonghui.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.CompanyVO;
import com.guolaiwan.app.web.admin.vo.PictureVO;
import com.guolaiwan.app.web.publicnum.util.EmojiFilter;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.ClassPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.gonghui.dao.ArticleDao;
import com.guolaiwan.bussiness.gonghui.dao.ClassesDao;
import com.guolaiwan.bussiness.gonghui.dao.OnlineClassesDao;
import com.guolaiwan.bussiness.gonghui.dao.RecordDao;
import com.guolaiwan.bussiness.gonghui.po.ArticlePo;
import com.guolaiwan.bussiness.gonghui.po.ClassesPo;
import com.guolaiwan.bussiness.gonghui.po.OnlineClassesPo;
import com.guolaiwan.bussiness.gonghui.po.RecordPo;

import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.communication.http.client.HttpClient;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/recordapp/article")
public class AppArticleContoller extends BaseController {
	@Autowired ArticleDao conn_article;
	@Autowired ClassesDao conn_classes;
	@Autowired UserInfoDAO conn_user;
	
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String openid="";
		JSONObject userInfo = null;
		String nickname="";
		String headimgurl="";
		
		String code=request.getParameter("code");
		JSONObject params = new JSONObject();
		params.put("appid", WxConfig.appId);
		params.put("secret", WxConfig.appsrcret);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		String result = HttpClient.get("https://api.weixin.qq.com/sns/oauth2/access_token", params);
		JSONObject accessTokenInfo = JSON.parseObject(result);
		String access_token = accessTokenInfo.getString("access_token");
		openid = accessTokenInfo.getString("openid");
        params = new JSONObject();
		params.put("access_token", access_token);
		params.put("openid", openid);
		params.put("lang", "zh_CN");
		result = HttpClient.get("https://api.weixin.qq.com/sns/userinfo", params);
		userInfo = JSON.parseObject(result);
        
		try {
			nickname = EmojiFilter.emoji(userInfo.getString("nickname"));
		} catch (Exception e) {
			// TODO: handle exception
			nickname = "无法获取用户名";
		}
		headimgurl = URLDecoder.decode(userInfo.getString("headimgurl"));
	
		UserInfoPO userInfoPO = null;
		List<UserInfoPO> users = conn_user.getUsersByOpenId(openid);
		
		if (users == null||users.isEmpty()) {
			userInfoPO = new UserInfoPO();
			userInfoPO.setUpdateTime(new Date());
			userInfoPO.setUserOpenID(openid);
			conn_user.save(userInfoPO);
		}else{
			userInfoPO.setUserHeadimg(headimgurl);
			userInfoPO.setUserNickname(nickname);
			conn_user.saveOrUpdate(userInfoPO);
		}
		return success(userInfo);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/getTopClasses", method = RequestMethod.GET)
	public Map<String, Object> getTopClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ClassesPo> classesPos=conn_classes.findAll(1, 6);
		SysConfigPO sysConfigPO=conn_sysConfig.getSysConfig();
		for (ClassesPo classesPo : classesPos) {
			classesPo.setPic(sysConfigPO.getWebUrl()+classesPo.getPic());
		}
		return success(classesPos);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTopArticles", method = RequestMethod.GET)
	public Map<String, Object> getTopArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ArticlePo> articlePos=conn_article.findAll(1, 15);
		return success(articlePos);
	}

	@ResponseBody
	@RequestMapping(value = "/getAllClasses", method = RequestMethod.GET)
	public Map<String, Object> getAllClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ClassesPo> classesPos=conn_classes.findAll();
		SysConfigPO sysConfigPO=conn_sysConfig.getSysConfig();
		for (ClassesPo classesPo : classesPos) {
			classesPo.setPic(sysConfigPO.getWebUrl()+classesPo.getPic());
		}
		return success(classesPos);
	}
	
	@Autowired
	OnlineClassesDao conn_onlinearticle;
	
	@ResponseBody
	@RequestMapping(value = "/getArticleByClass", method = RequestMethod.GET)
	public Map<String, Object> getArticleByClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<OnlineClassesPo> onlineClassesPos=conn_onlinearticle.findByField("classesId", Long.parseLong(request.getParameter("classId").toString()));
		List<ArticlePo> articlePos=new ArrayList<ArticlePo>();
		for (OnlineClassesPo onlineClassesPo : onlineClassesPos) {
			articlePos.add(conn_article.get(onlineClassesPo.getContentId()));
		}
		
		
		return success(articlePos);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getMyRecords", method = RequestMethod.GET)
	public Map<String, Object> getMyRecords(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId=Long.parseLong(request.getParameter("userId").toString());
		List<RecordPo> recordPos=conn_record.findByField("userId", userId);
		return success(recordPos);
	}
	
	@Autowired
	SysConfigDAO conn_sysConfig;
	@Autowired
	RecordDao conn_record;
	
	@ResponseBody
	@RequestMapping(value="/upload.do",method= RequestMethod.POST)
	public Map<String,Object> upload(HttpServletRequest request,@RequestParam("audio") CommonsMultipartFile file) throws Exception{
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

		//写数据库
		RecordPo recordPo=new RecordPo();
		//PicturePO picture = new PicturePO();
        recordPo.setArticleId(Long.parseLong(request.getParameter("articleId").toString()));
        recordPo.setUserId(Long.parseLong(request.getParameter("userId").toString()));
		recordPo.setRecordUrl(path+"/"+newName);
		conn_record.save(recordPo);
		//上传到阿里云oss
		OSSUtils.createFolder("glw-old-file", "file/"+folderName+"/");
		OSSUtils.uploadObjectOSS("file/"+folderName+"/", newName,newFile, new FileInputStream(newFile));
		map.put("record", recordPo);
		return map;
	}
	
}
