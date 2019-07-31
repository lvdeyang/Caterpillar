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

import com.guolaiwan.bussiness.admin.dao.AddTheRommDAO;
import com.guolaiwan.bussiness.admin.po.AddTheroomPO;

@Controller
@RequestMapping("/admin/room")
public class RoomController {

	@Autowired
	private AddTheRommDAO addtheroomDAO;
	
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
		List<AddTheroomPO> roompo =  addtheroomDAO.findByPro(merchantId);
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
        AddTheroomPO add = new AddTheroomPO();
        add.setMerchantId(merchantId);
        add.setName(name);
        add.setTier(tier);
        add.setRoomimg(roomimg);
        add.setPrice(Integer.parseInt(price)*100);
        add.setIdentity(identity);
        add.setState(1);
        add.setRoomdetails(roomdetails);
        addtheroomDAO.save(add);
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/amend.do",method = RequestMethod.POST) // 修改上架 退房
	public Map<String, Object> SetAmend(HttpServletRequest request) throws Exception{//添加入驻信息
		String a = request.getParameter("state"); //状态
		String id = request.getParameter("id");
		AddTheroomPO addpo =  addtheroomDAO.get(Long.parseLong(id));
		addpo.setState(Integer.parseInt(a));
		addtheroomDAO.saveOrUpdate(addpo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST) // 修改上架 退房
	public Map<String, Object> delete(HttpServletRequest request) throws Exception{//添加入驻信息
		String id = request.getParameter("id");
		addtheroomDAO.delete(Long.parseLong(id));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		return map;
	}
	
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
        AddTheroomPO add = addtheroomDAO.get(Long.parseLong(roomId));
        if(roomimg!=""&&roomimg!=null)add.setRoomimg(roomimg);
        if(name!=""&&name!=null) add.setName(name);
        if(tier!=""&&tier!=null) add.setTier(tier);
        if(price!=""&&price!=null) add.setPrice(Integer.parseInt(price)*100);
        if(identity!=""&&identity!=null) add.setIdentity(identity);
        if(roomdetails!=""&&roomdetails!=null) add.setRoomdetails(roomdetails);
        add.setState(1);
        addtheroomDAO.save(add);
		
		return "success";
	}

}
