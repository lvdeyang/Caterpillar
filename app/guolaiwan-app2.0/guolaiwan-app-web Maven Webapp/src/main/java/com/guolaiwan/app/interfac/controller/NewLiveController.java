package com.guolaiwan.app.interfac.controller;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.live.LiveOrderDao;
import com.guolaiwan.bussiness.admin.dao.live.LiveRecordDao;
import com.guolaiwan.bussiness.admin.dao.live.SubLiveDao;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.live.LiveOrderPO;
import com.guolaiwan.bussiness.admin.po.live.LiveRecordPO;
import com.guolaiwan.bussiness.admin.po.live.SubLivePO;
import com.guolaiwan.bussiness.javacv.GuoliawanLiveServiceWrapper;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/newlive")
public class NewLiveController extends WebBaseControll{
	
	@Autowired
	private LiveDAO conn_live;
	@Autowired
	private LiveRecordDao conn_record;
	@Autowired
	private SubLiveDao conn_subLive;
	@Autowired
	private LiveOrderDao conn_liveOrder;
	
	private static final String path="/usr/sbin/guolaiwan/tomcat/apache-tomcat-7.0.85-windows-x64/apache-tomcat-7.0.85/webapps/video/";
	
	
	/**
	 * 
	 * @param request
	 * @param oldId
	 * @param id
	 * @param liveId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/switch/{oldId}/{id}/{liveId}", method = RequestMethod.GET)//id为0时使用备源
	public Object switchLive(HttpServletRequest request,@PathVariable long oldId,@PathVariable long id,@PathVariable long liveId) throws Exception{
		SubLivePO subLivePO=conn_subLive.get(id);
		SubLivePO oldSubLivePO=conn_subLive.get(oldId);
		LivePO livePO=conn_live.get(liveId);
		GuoliawanLiveServiceWrapper.getInstance().switchSubLive(livePO.getPubName(), oldSubLivePO.getLiveName(),subLivePO.getLiveName());
	    subLivePO.setInuse(1);
	    oldSubLivePO.setInuse(0);
	    conn_subLive.save(subLivePO);
	    conn_subLive.save(oldSubLivePO);
        return null;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/startNewLive", method = RequestMethod.POST)//id为0时使用备源
	public Object startLive(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		long liveId=params.getLongValue("liveId");
		LivePO livePO=conn_live.get(liveId);
		//GuoliawanLiveServiceWrapper.getInstance().startLive(livePO.getPubName());
		livePO.setLiveStatusType(LiveStatusType.LIVING);
		conn_live.save(livePO);
        return null;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/stopNewLive", method = RequestMethod.POST)//id为0时使用备源
	public Object stopLive(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		long liveId=params.getLongValue("liveId");
		LivePO livePO=conn_live.get(liveId);
		GuoliawanLiveServiceWrapper.getInstance().stopLive(livePO.getPubName());
		livePO.setLiveStatusType(LiveStatusType.STOP);
		conn_live.save(livePO);
        return null;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/startsubLive", method = RequestMethod.POST)//id为0时使用备源
	public Object startsubLive(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		long liveId=params.getLongValue("liveId");
		long subLiveId=params.getLongValue("subLiveId");
		LivePO livePO=conn_live.get(liveId);
		SubLivePO subLivePO=conn_subLive.get(subLiveId);
		GuoliawanLiveServiceWrapper.getInstance().startSubLive(livePO.getPubName(), subLivePO.getLiveName());
		subLivePO.setStatus(LiveStatusType.LIVING);
		conn_subLive.save(subLivePO);
        return null;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/stopsubLive", method = RequestMethod.POST)//id为0时使用备源
	public Object stopsubLive(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		long liveId=params.getLongValue("liveId");
		long subLiveId=params.getLongValue("subLiveId");
		LivePO livePO=conn_live.get(liveId);
		SubLivePO subLivePO=conn_subLive.get(subLiveId);
		
		GuoliawanLiveServiceWrapper.getInstance().stopSubLive(livePO.getPubName(), subLivePO.getLiveName());
		subLivePO.setStatus(LiveStatusType.STOP);
		conn_subLive.save(subLivePO);
        return null;
		
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/startRecord/{liveId}", method = RequestMethod.GET)//id为0时使用备源
	public Object startRecord(HttpServletRequest request,@PathVariable long liveId) throws Exception{
		LivePO livePO=conn_live.get(liveId);
		LiveRecordPO liveRecordPO=new LiveRecordPO();
		liveRecordPO.setLiveId(liveId);
		liveRecordPO.setStartTime(new Date());
		liveRecordPO.setLocalPath(path+File.separator+livePO.getLiveName()+File.separator);
		liveRecordPO.setRecordName(livePO.getLiveName()+DateUtil.format(new Date()));
		GuoliawanLiveServiceWrapper.getInstance().startRecord(livePO.getPubName(),liveRecordPO.getLocalPath()+liveRecordPO.getRecordName());
		conn_record.save(liveRecordPO);
		
		return liveRecordPO;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/stopRecord/{liveId}", method = RequestMethod.GET)//id为0时使用备源
	public Object stopRecord(HttpServletRequest request,@PathVariable long liveId) throws Exception{
		LivePO live=conn_live.get(liveId);
	    LiveRecordPO liveRecord=conn_record.findByLiveAndIng(liveId);
	    liveRecord.setEndTime(new Date());
	    GuoliawanLiveServiceWrapper.getInstance().stopRecord(live.getPubName());
		conn_record.save(liveRecord);
		return liveRecord;
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/record/list/{liveId}", method = RequestMethod.GET)//id为0时使用备源
	public Object recordList(HttpServletRequest request,@PathVariable long liveId) throws Exception{
		return conn_record.findByLiveAndEnd(liveId);
		
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/record/delete/{id}", method = RequestMethod.GET)//id为0时使用备源
	public Object deleteRecord(HttpServletRequest request,@PathVariable long id) throws Exception{
		conn_record.delete(id);
		return null;
	}
	
	
}
