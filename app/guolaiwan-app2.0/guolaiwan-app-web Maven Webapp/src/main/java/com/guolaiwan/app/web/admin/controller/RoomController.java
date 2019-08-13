package com.guolaiwan.app.web.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.admin.dao.AddTheRoomDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.po.AddTheRoomPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MessagePO;
import com.guolaiwan.bussiness.nanshan.dao.CurrentRoomSateDao;
import com.guolaiwan.bussiness.nanshan.dao.MessageMiddleClientDao;
import com.guolaiwan.bussiness.nanshan.po.CurrentRoomSatePO;
import com.guolaiwan.bussiness.nanshan.po.MessageMiddleClientPO;

import javassist.expr.NewArray;

@Controller
@RequestMapping("/admin/room")
public class RoomController {

	@Autowired
	private AddTheRoomDAO addtheroomDAO;
	
	@Autowired
	private MerchantDAO merchantDAO;
	
	@Autowired
	private CurrentRoomSateDao roomState;
	@Autowired
	private MessageDAO MessageDAO;
	@Autowired
	private MessageMiddleClientDao mesMidClient;
	@Autowired
	private CurrentRoomSateDao CurrentRoomSateDao;
	/**
	 * 跳转房间管理
	 * @param request
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "/roomlist")
	public ModelAndView updateView(HttpServletRequest request,String merchantId) {
		ModelAndView mv = new ModelAndView("admin/merchantroom/list");
		mv.addObject("merchantId", merchantId);
		return mv;
	}
	
	
	
	//显示列表
	@RequestMapping(value="/addv",method= RequestMethod.GET)
	public ModelAndView getaddv(String merchantId){
		ModelAndView mv = new ModelAndView("admin/merchantroom/add");
		mv.addObject("merchantId", merchantId);
		return mv;
	}	
	
	//添加新房间
	@ResponseBody
	@RequestMapping(value = "/add.do",method = RequestMethod.POST)
	public String historysettle(HttpServletRequest request) throws Exception{ //添加房间
		String name = request.getParameter("name");
        String tier = request.getParameter("tier");
        String price = request.getParameter("price");
        String identity = request.getParameter("identity");//房间类型
        String merchantId = request.getParameter("merchantId");
        String roomdetails = request.getParameter("roomdetails");
        String roomimg = request.getParameter("img5");
        String isreception = request.getParameter("isreception");
        String iswifi = request.getParameter("iswifi");
        String iskettle = request.getParameter("iskettle");
        String istoilet = request.getParameter("istoilet");
        String istv = request.getParameter("istv");
        String isfan = request.getParameter("isfan");
        AddTheRoomPO add = new AddTheRoomPO();
        add.setMerchantId(merchantId);
        add.setName(name);
        add.setTier(tier);
        add.setRoomimg(roomimg);
        add.setPrice(Integer.parseInt(price)*100);
        add.setIdentity(identity);
        add.setState(1);
        if(roomdetails!=null)add.setRoomdetails(roomdetails);
        if(isfan!=null)add.setIsfan(Integer.parseInt(isfan));
        if(iskettle!=null)add.setIskettle(Integer.parseInt(iskettle));
        if(isreception!=null)add.setIsreception(Integer.parseInt(isreception));
        if(istoilet!=null)add.setIstoilet(Integer.parseInt(istoilet));
        if(istv!=null)add.setIstv(Integer.parseInt(istv));
        if(iswifi!=null)add.setIswifi(Integer.parseInt(iswifi));
        addtheroomDAO.save(add);
        
        //获取roomId
        CurrentRoomSatePO cSatePO = new CurrentRoomSatePO();         
        cSatePO.setRoomId(add.getId());
        cSatePO.setRoomState("0");     
        roomState.save(cSatePO);      	
		return "success";
	}
	
	//修改状态
	@ResponseBody
	@RequestMapping(value = "/amend.do",method = RequestMethod.POST) // 修改上架 退房
	public Map<String, Object> SetAmend(HttpServletRequest request) throws Exception{//添加入驻信息
		String a = request.getParameter("state"); //状态
		String id = request.getParameter("id");
		AddTheRoomPO addpo =  addtheroomDAO.get(Long.parseLong(id));
		addpo.setState(Integer.parseInt(a));
		addtheroomDAO.saveOrUpdate(addpo);		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		return map;
	}
	
	//删除房间
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST) 
	public Map<String, Object> delete(HttpServletRequest request) throws Exception{//添加入驻信息
		String id = request.getParameter("id");
		addtheroomDAO.delete(Long.parseLong(id));
		roomState.deleteByField("roomId", Long.parseLong(id));
		mesMidClient.deleteByField("roomId", Long.parseLong(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		return map;
	}
	
	//修改房间的各种信息
	@ResponseBody
	@RequestMapping(value = "/updateroom",method = RequestMethod.POST)
	public String updateroom(HttpServletRequest request) throws Exception{ //添加房间
		String roomId=request.getParameter("roomId");
		String name = request.getParameter("name");
        String tier = request.getParameter("tier");
        String price = request.getParameter("price");
        String identity = request.getParameter("identity");//房间类型
        String roomdetails = request.getParameter("roomdetails");
        String roomimg = request.getParameter("img5");
        String isreception = request.getParameter("isreception");
        String iswifi = request.getParameter("iswifi");
        String iskettle = request.getParameter("iskettle");
        String istoilet = request.getParameter("istoilet");
        String istv = request.getParameter("istv");
        String isfan = request.getParameter("isfan");
        AddTheRoomPO add = addtheroomDAO.get(Long.parseLong(roomId));
        if(roomimg!=""&&roomimg!=null)add.setRoomimg(roomimg);
        if(name!=""&&name!=null) add.setName(name);
        if(tier!=""&&tier!=null) add.setTier(tier);
        if(price!=""&&price!=null) add.setPrice(Integer.parseInt(price)*100);
        if(identity!=""&&identity!=null) add.setIdentity(identity);
        if(roomdetails!=""&&roomdetails!=null) add.setRoomdetails(roomdetails);
        if(isfan!=null){add.setIsfan(Integer.parseInt(isfan));}else{add.setIsfan(0);} 
        if(iskettle!=null){add.setIskettle(Integer.parseInt(iskettle));}else{add.setIskettle(0);}
        if(isreception!=null){add.setIsreception(Integer.parseInt(isreception));}else{add.setIsreception(0);}
        if(istoilet!=null){add.setIstoilet(Integer.parseInt(istoilet));}else{add.setIstoilet(0);}
        if(istv!=null){add.setIstv(Integer.parseInt(istv));}else{add.setIstv(0);}
        if(iswifi!=null){add.setIswifi(Integer.parseInt(iswifi));}else{add.setIswifi(0);}
        addtheroomDAO.save(add);
        		
		return "success";
	}
	
	//判断是不是住宿 商户中心有没有房间管理
	@ResponseBody
	@RequestMapping(value = "/isroomoption",method = RequestMethod.POST)
	public String isRoomOption(HttpServletRequest request) throws Exception{ //添加房间
		String merchantId=request.getParameter("merchantId");
		MerchantPO merchant = merchantDAO.get(Long.parseLong(merchantId));
		if(merchant.getModularCode().equals("0002")){
			return "success";
		}else{
			return "error";
		}
	}

	//进入房间管理的页面
	@RequestMapping(value="/gotoroomoption",method= RequestMethod.GET)
	public ModelAndView goToRoomOption(String merchantId){
		ModelAndView mv = new ModelAndView("mobile/pubadmin/roomoption");
		mv.addObject("merchantId", merchantId);
		return mv;
	}	
	
	/**
	 * 查询    初始显示 后台
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getlist.do", method = RequestMethod.POST)
	public Map<String, Object> SeleFinish(HttpServletRequest request) throws Exception {
		String merchantId=request.getParameter("merchantId");
		List<AddTheRoomPO> roompo =  addtheroomDAO.findByPro(merchantId);
	    List<String> roomStatList = new ArrayList<String>(); 
		//时间解析
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		//获取时间
		String inRoomDate = request.getParameter("inRoomDate");
	    long  sdf_inRoomDate = sdf.parse(inRoomDate).getTime();
		String outRoomDate = request.getParameter("outRoomDate");
		long  sdf_outRoomDate = sdf.parse(outRoomDate).getTime();
		//房间在当前时间的状态
		boolean oneReal = false;
		boolean twoReal = false;
		String CurrentState = null;
	    //查询房间当前状态
		for(AddTheRoomPO po :roompo ){		   		
		   //对多个状态进行筛选		  
			 if(0 == po.getState()){
				 CurrentState = "0";//下架状态 
			 }else{ 
				long id =  po.getId();	
				List<CurrentRoomSatePO> cRoomSatePOs = roomState.findByRoomId(id);
				for(CurrentRoomSatePO cRoomSatePO : cRoomSatePOs){
				//上架状态 				
			    try{			   	
			    //获取日期			    
			    long beginTime = sdf.parse(cRoomSatePO.getInRoomDate()).getTime();
			    long endTime = sdf.parse(cRoomSatePO.getOutRoomDate()).getTime();
			    	if(beginTime>=sdf_inRoomDate && beginTime<sdf_outRoomDate){	
			    		if("1".equals(cRoomSatePO.getRoomState())){			    			
			    			 oneReal = true;
			    		}		    						    						    			
			    	}else if(endTime>sdf_inRoomDate && endTime <sdf_outRoomDate){					    	  
			    		if("1".equals(cRoomSatePO.getRoomState())){			    			
			    			twoReal = true;
			    		}							    	
					}					    					    					    	     			    
			     }
			    //房间时间为空
			    catch(Exception e){
			    	
			    }			 			  
			 }
			   //根据判断结果返回状态	
			   if(oneReal || twoReal){
				   CurrentState = "2";//已售状态
			   }else{
				   CurrentState = "1";//空闲状态  
			   }					
		   }
			 roomStatList.add(CurrentState);	
		}
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CurrentState",roomStatList);
		map.put("po", roompo);
		return map;
	}
	
	//商户预定信息
	@ResponseBody
	@RequestMapping(value = "/addInRoomMessage",method=RequestMethod.POST)
	public String addInRoomMessage(HttpServletRequest request){
		//获取预定人信息
		String  name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String  card =  request.getParameter("card");
		System.out.println();
		//获取房间ID
		String roomId = request.getParameter("roomId");
		//获取预定时间
		String inRoomDate = request.getParameter("inRoomDate");
		String outRoomDate = request.getParameter("outRoomDate");
		
		String[] strName = name.split(",");
		String[] strPhone = phone.split(",");
		String[] strCard = card.split(",");
		//预定人信息保存
		String merchantId = request.getParameter("merchantId");
		for(int i = 0; i< strName.length;i++){
			//保存预订人详细信息
			MessagePO mPo = new MessagePO();
			mPo.setName(strName[i]);
			mPo.setPhone(strPhone[i]);
			mPo.setNumber(strCard[i]);
			mPo.setMerchantid(merchantId);
			MessageDAO.save(mPo);
			//预订人对应房间
		    MessageMiddleClientPO  middleClientPO = new MessageMiddleClientPO();
		    middleClientPO.setMerchantId(Long.parseLong(merchantId));
		    middleClientPO.setMessageId(mPo.getId());
		    middleClientPO.setPayState("1");
		    middleClientPO.setRoomId(Long.parseLong(roomId));
		    middleClientPO.setStartDate(inRoomDate);
		    middleClientPO.setEndDate(outRoomDate);
		    mesMidClient.save(middleClientPO);
            //添加房间状态信息
		    CurrentRoomSatePO cRoomSatePO = new CurrentRoomSatePO();		  
		    cRoomSatePO.setInRoomDate(inRoomDate);
		    cRoomSatePO.setOutRoomDate(outRoomDate);
		    cRoomSatePO.setRoomId(Long.parseLong(roomId));
		    cRoomSatePO.setRoomState("1");
		    CurrentRoomSateDao.save(cRoomSatePO);
		}
			      
	     return "success";
	}
	
	// 已住房间信息回显
	@ResponseBody
	@RequestMapping(value="/echoRoomClient")
	public Map<String,Object>  echoRoomClient(HttpServletRequest request) throws ParseException{
		//保存筛选出来的房间对应的用户信息
		Map<String, Object>  map = new HashMap<String, Object>();
		List<MessageMiddleClientPO> mClientPO = new ArrayList<MessageMiddleClientPO>();
		//获取信息
	    String  roomId = request.getParameter("roomId");
	    String  beginDate = request.getParameter("inRoomDate");
	    String  endDate = request.getParameter("outRoomDate");
	    List<MessageMiddleClientPO> middleClientPO  = mesMidClient.findByField("roomId", Long.parseLong(roomId));
	    //日期解析
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    long begindate = sdf.parse(beginDate).getTime();
	    long enddate = sdf.parse(endDate).getTime();
	    //筛选信息
	    long minNumber = 0;
	    String minInRoomDate = null;
	    String minOutRoomDate = null;
	    Boolean real =false;
	    boolean contrast = true;
	    for(MessageMiddleClientPO po : middleClientPO){
	       long inRoomDate = sdf.parse(po.getStartDate()).getTime(); 
	       long outRoomDate = sdf.parse(po.getEndDate()).getTime();	           
	       //支付状态判断
	       if(null != po.getPayState() && "1".equals(po.getPayState())){
	       if(inRoomDate>=begindate && inRoomDate< enddate){
	    	   mClientPO.add(po); 
	    	   real=true;
	       }else if (outRoomDate >begindate && outRoomDate<enddate) {
	    	   mClientPO.add(po);
	    	   real=true;
		   }
	       //获取最小时间
	      if(real){
	       //日期参照赋值
           if(contrast){       	   
        	   minNumber =  inRoomDate; 
        	   minInRoomDate = po.getStartDate();
        	   minOutRoomDate = po.getEndDate();
        	   contrast =false;
        	   
           }
	       if(minNumber > inRoomDate){
	    	   minNumber = inRoomDate;
	    	   minInRoomDate = po.getStartDate();
	    	   minOutRoomDate = po.getEndDate();	    	     	  
	       }
	       real=false;	
	      }
	     }
	    }
	   //客户信息进行房间分类
	   for(int i = 0;i<mClientPO.size();i++){
	    	if(!(minInRoomDate.equals(mClientPO.get(i).getStartDate()))){	    		
	    		mClientPO.remove(i);
	    		//保持索引不变
	    		--i;
	    	}	    	
	    }
	    //查询出住房人信息
	    List<String> names = new ArrayList<String>();
	    List<String> phones = new ArrayList<String>();
	    List<String> cards = new ArrayList<String>();
	    for(MessageMiddleClientPO po : mClientPO){
	      MessagePO mPo = MessageDAO.get(po.getMessageId());
	      names.add(mPo.getName());
	      phones.add(mPo.getPhone());
	      cards.add(mPo.getNumber());
	    } 
	   
	    map.put("beginDate",minInRoomDate);
	    map.put("endDate",minOutRoomDate);
	    map.put("nameList", names);
	    map.put("phoneList", phones);
	    map.put("cardList", cards);
	    return map;	
	}
	
	//房间预定取消
	@ResponseBody
	@RequestMapping(value="/deleteInRoomMessage")
	public String deleteInRoomMessage(long roomId ,String inRoomDate, String outRoomDate){		
		//房间状态信息进行修改
		String[] cur_fields = {"roomId","inRoomDate","outRoomDate"};
		Object[] cur_values = {roomId,inRoomDate,outRoomDate};
		List<CurrentRoomSatePO> cRoomSatePOs =  CurrentRoomSateDao.findByFields(cur_fields, cur_values);
		for(CurrentRoomSatePO po : cRoomSatePOs){
		   //修改房间状态	
		   po.setRoomState("0");
		   CurrentRoomSateDao.saveOrUpdate(po);				
		}
		//房间对应住户状态信息进行修改
		String[] mes_fields = {"roomId","startDate","endDate"};
		Object[] mes_values = {roomId,inRoomDate,outRoomDate};
	    List<MessageMiddleClientPO> mClientPOs = mesMidClient.findByFields(mes_fields, mes_values);
		for(MessageMiddleClientPO po :mClientPOs ){
			po.setPayState("2");
			mesMidClient.saveOrUpdate(po);
		}
		return "success";
	}
	
}
