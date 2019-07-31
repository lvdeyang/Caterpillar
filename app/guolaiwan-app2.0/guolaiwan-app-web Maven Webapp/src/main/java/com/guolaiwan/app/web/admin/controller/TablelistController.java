package com.guolaiwan.app.web.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.DistributorVO;
import com.guolaiwan.app.web.admin.vo.TableVo;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.TableDAO;
import com.guolaiwan.bussiness.admin.dao.TableStatusDAO;
import com.guolaiwan.bussiness.admin.enumeration.BookType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.TableStatus;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.DistributorPO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.RolePO;
import com.guolaiwan.bussiness.admin.po.TablePO;
import com.guolaiwan.bussiness.admin.po.TableStatusPO;
import com.sun.jna.platform.win32.WinDef.LONG;

import pub.caterpillar.mvc.controller.BaseController;

/**
 * 
 * @author 一只会写bug的井蛙
 *
 */
@Controller
@RequestMapping("/admin/tablelist")
public class TablelistController extends BaseController {
	@Autowired
	private  TableDAO Table;

	// 餐桌列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();

		ModelAndView mv = new ModelAndView("admin/tablelist/list", strMap);
		return mv;
   }
	// 餐桌列表页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/tablelist/add", strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add.do",method = RequestMethod.POST)
	public Map<String, Object> historysettle(HttpServletRequest request) throws Exception{ //添加房间
		String tablename = request.getParameter("name");//名称
        String tableNo = request.getParameter("tableNo");//桌号
        String tier = request.getParameter("title");//层
        String type = request.getParameter("type");//人数
        String bookprice = request.getParameter("bookprice");//订金
        double money =  Double.parseDouble(bookprice)*100;
        String radio = request.getParameter("radio");//包间
        TablePO add = new TablePO();
        add.setTablename(tablename);
        add.setTableNo(tableNo);
        add.setTier(tier);
        add.setSize(Long.parseLong(type));
        add.setBookprice((long)money);
        add.setRoom(Integer.parseInt(radio));
        Table.saveOrUpdate(add);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		return map;
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
	public Map<String, Object> SeleFinish(long merchantId ,HttpServletRequest request) throws Exception {
		List<TablePO> addpo =  Table.findByMerchantId(merchantId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("po", addpo);
		return map;
	}
	
	
}
