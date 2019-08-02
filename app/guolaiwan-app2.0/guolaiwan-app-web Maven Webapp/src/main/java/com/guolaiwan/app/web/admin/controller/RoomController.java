package com.guolaiwan.app.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.bussiness.admin.dao.AddTheRoomDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.po.AddTheRoomPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;

@Controller
@RequestMapping("/admin/room")
public class RoomController {

	@Autowired
	private AddTheRoomDAO addtheroomDAO;
	
	@Autowired
	private MerchantDAO merchantDAO;
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("po", roompo);
		return map;
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
	@RequestMapping(value = "/delete",method = RequestMethod.POST) // 修改上架 退房
	public Map<String, Object> delete(HttpServletRequest request) throws Exception{//添加入驻信息
		String id = request.getParameter("id");
		addtheroomDAO.delete(Long.parseLong(id));
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
        add.setState(1);
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
}
