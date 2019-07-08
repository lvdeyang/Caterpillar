package com.chenxi.web.yueba.mobile.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chenxi.web.classes.SystemConfig;
import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.dao.ArticleDao;
import com.chenxi.web.dao.OnlineClassesDao;
import com.chenxi.web.dao.ProductDao;
import com.chenxi.web.dao.UserDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ClassesPo;
import com.chenxi.web.po.OnlineClassesPo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.util.Img2Base64Util;
import com.chenxi.web.weixin.JsTicketUtil;
import com.chenxi.web.yueba.admin.dao.ComboDao;
import com.chenxi.web.yueba.admin.dao.CommentDao;
import com.chenxi.web.yueba.admin.dao.DaysTypeDao;
import com.chenxi.web.yueba.admin.dao.OrderDao;
import com.chenxi.web.yueba.admin.dao.RegionDao;
import com.chenxi.web.yueba.admin.dao.SeeRecordDao;
import com.chenxi.web.yueba.admin.dao.WorkerDao;
import com.chenxi.web.yueba.admin.po.ComboPo;
import com.chenxi.web.yueba.admin.po.DaysTypePo;
import com.chenxi.web.yueba.admin.po.OrderPo;
import com.chenxi.web.yueba.admin.po.RegionsPo;
import com.chenxi.web.yueba.admin.po.SeeRecordPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import net.sf.ehcache.search.parser.MValue;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;

@Controller
@RequestMapping("/worker")
public class MWorkerContoller extends BaseController {
	
	@Autowired
	WorkerDao conn_worker;
	@Autowired
	RegionDao conn_region;
	@Autowired
	DaysTypeDao conn_daystype;
	@Autowired
	ComboDao conn_combo;
	@Autowired
	CommentDao conn_comment;
	@Autowired
	OrderDao conn_order;
	@Autowired
	SeeRecordDao conn_seerecord;
	
	@RequestMapping(value = "/mobile/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		WorkerPo workerPo=conn_worker.get(workerId);
		workerPo.setOrderCount(workerPo.getBaseOrderCount()+conn_order.countCompleteByWorker(workerPo.getId()));
		strMap.put("worker", workerPo);
		
		StringBuffer sb=new StringBuffer();
		sb.append("<tr><td></td>");
		List<RegionsPo> regionsPos=conn_region.findAll();
	
		for (RegionsPo regionsPo : regionsPos) {
			sb.append("<td>"+regionsPo.getRegionName()+"</td>");
		
		}
		sb.append("</tr>");
        List<DaysTypePo> daysTypePos=conn_daystype.findAll();
        for (DaysTypePo daysTypePo : daysTypePos) {
        	sb.append("<tr><td>"+daysTypePo.getDays()+"天</td>");
        	for (RegionsPo regionsPo : regionsPos) {
        		ComboPo comboPo=conn_combo.findByRegionAndDays(regionsPo.getRegionName(), daysTypePo.getDays(),workerPo.getLevel());
    			sb.append("<td>"+(comboPo==null?"-":comboPo.getPrice())+"</td>");
    			
    		}
        	sb.append("</tr>");
		}
        strMap.put("priceHtml", sb.toString());
        
        strMap.put("comments",conn_comment.findByworkerId(workerId, 1, 3));
        
        strMap.put("childCount", conn_order.countByField("workerId", workerId));
        OrderPo currentOrder=conn_order.findMaxWorderOrder(workerId);
        String minDate=DateUtil.format(new Date(),"yyyy-MM-dd");
        if(currentOrder!=null){
        	minDate=DateUtil.format(DateUtil.addHour(currentOrder.getFromDate(),currentOrder.getDays()+1),"yyyy-MM-dd");
        }
        
		strMap.put("minDate", minDate);
        SeeRecordPo seeRecordPo=new SeeRecordPo();
        seeRecordPo.setUpdateTime(new Date());
    	HttpSession session = request.getSession();
    	Object userId=session.getAttribute("userId");
        seeRecordPo.setUserId(Long.parseLong(userId+""));
        seeRecordPo.setWorkerId(workerId);
        conn_seerecord.save(seeRecordPo);
		ModelAndView mv = new ModelAndView("yuebamobile/worker", strMap);
		return mv;
	}
	@RequestMapping(value = "/mobile/addorder", method = RequestMethod.GET)
	public ModelAndView addorder(HttpServletRequest request,long workerId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		WorkerPo workerPo=conn_worker.get(workerId);
		List<RegionsPo> regionsPos=conn_region.findAll();
		List<DaysTypePo> daysTypePos=conn_daystype.findAll();
		strMap.put("worker", workerPo);
		strMap.put("regions", regionsPos);
		strMap.put("daystypes", daysTypePos);
		ModelAndView mv = new ModelAndView("yuebamobile/subscribe", strMap);
		return mv;
	}
	@Autowired
	UserDao conn_user;
	
	@RequestMapping(value = "/mobile/apply", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object openid=session.getAttribute("openid");
		List<WorkerPo> workerPos=conn_worker.findByField("openId", openid);
		ModelAndView mv =null;
		Map<String, Object> strMap = new HashMap<String, Object>();
		if(workerPos==null||workerPos.isEmpty()){
			mv = new ModelAndView("yuebamobile/apply", strMap);
		}else{
			strMap.put("workerId", workerPos.get(0).getId());
			strMap.put("worker", workerPos.get(0));
			session.setAttribute("workerId", workerPos.get(0).getId());
			mv = new ModelAndView("yuebamobile/workerpersonal", strMap);
		}
		return mv;
	}
	
	@RequestMapping(value = "/mobile/reapply", method = RequestMethod.GET)
	public ModelAndView reapply(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object workerId=session.getAttribute("workerId");
		conn_worker.delete(Long.parseLong(workerId+""));
		session.setAttribute("workerId", null);
		ModelAndView mv =null;
		Map<String, Object> strMap = new HashMap<String, Object>();
		mv = new ModelAndView("yuebamobile/apply", strMap);
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/mobile/apply.do", method = RequestMethod.POST)
	public Object doApply(HttpServletRequest request) throws Exception {
		String realName=request.getParameter("realName");
		String idCard=request.getParameter("idCard");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String photo=request.getParameter("photo");
		String idCardPhoto=request.getParameter("idCardPhoto");
		String healthPhoto=request.getParameter("healthPhoto");
		String expertPhoto=request.getParameter("expertPhoto");
		String age=request.getParameter("age");
		WorkerPo workerPo=new WorkerPo();
		workerPo.setAddress(address);
		workerPo.setAge(Integer.parseInt(age));
		workerPo.setIdCard(idCard);
		workerPo.setPhone(phone);
		workerPo.setRealName(realName);
		workerPo.setStatus(WorkerStatus.CHECKING);
		
		HttpSession session = request.getSession();
		Object openid=session.getAttribute("openid");
		workerPo.setOpenId(openid+"");
		
		
		File file=new File(SystemConfig.IMAGE_PATH+File.separator+"file"+File.separator+DateUtil.format(new Date(), "yyyyMMdd"));
		if(!file.exists()){//如果文件夹不存在
			file.mkdir();//创建文件夹
		}

		
		
		if(photo!=null&&!photo.isEmpty()){
			String shortPhotoPath="file"+File.separator+DateUtil.format(new Date(), "yyyyMMdd")+File.separator+UUID.randomUUID()+".jpg";
			String photoPath=SystemConfig.IMAGE_PATH+File.separator+shortPhotoPath;
			Img2Base64Util.generateImage(photo,photoPath);
			System.out.println(shortPhotoPath);
			workerPo.setPhoto(shortPhotoPath);
		}
		if(idCardPhoto!=null&&!idCardPhoto.isEmpty()){
			String shortIdPhotoPath="file"+File.separator+DateUtil.format(new Date(), "yyyyMMdd")+File.separator+UUID.randomUUID()+".jpg";
			String photoIdPath=SystemConfig.IMAGE_PATH+File.separator+shortIdPhotoPath;
			Img2Base64Util.generateImage(idCardPhoto,photoIdPath);
			workerPo.setIdCardPhoto(shortIdPhotoPath);
		}
		
		if(healthPhoto!=null&&!healthPhoto.isEmpty()){
			String shortPhotoHealthPath="file"+File.separator+DateUtil.format(new Date(), "yyyyMMdd")+File.separator+UUID.randomUUID()+".jpg";
			String photoHealthPath=SystemConfig.IMAGE_PATH+File.separator+shortPhotoHealthPath;
			Img2Base64Util.generateImage(healthPhoto,photoHealthPath);
			workerPo.setHealthPhoto(shortPhotoHealthPath);
		}
		
		if(expertPhoto!=null&&!expertPhoto.isEmpty()){
			String shortPhotoExPath="file"+File.separator+DateUtil.format(new Date(), "yyyyMMdd")+File.separator+UUID.randomUUID()+".jpg";
			String photoExPath=SystemConfig.IMAGE_PATH+File.separator+shortPhotoExPath;
			Img2Base64Util.generateImage(expertPhoto,photoExPath);
			workerPo.setExpertPhoto(shortPhotoExPath);
		}
		
		conn_worker.save(workerPo);
		
		return "success";
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/wx/prev")
	public Object preScan(HttpServletRequest request, String url) {
		url = url.replace("FISH", "&");
		System.out.println(url);
		Map ret = new HashMap();
		String jsapi_ticket = JsTicketUtil.getJsapiTicket();
		String nonce_str = JsTicketUtil.create_nonce_str();
		String timestamp = JsTicketUtil.create_timestamp();
		String string1;
		String signature = "";
		int length = url.indexOf("#");
		String uri = url;
		if (length > 0) {
			uri = url.substring(0, length);// 当前网页的URL，不包含#及其后面部分
		}
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = JsTicketUtil.byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(signature);
		System.out.println(WXContants.AppId);
		ret.put("appId", WXContants.AppId);
		ret.put("url", uri);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

}
