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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.DistributorVO;
import com.guolaiwan.app.web.admin.vo.TableVo;
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

import pub.caterpillar.mvc.controller.BaseController;

/**
 * 
 * @author 一只会写bug的井蛙
 *
 */
@Controller
@RequestMapping("/admin/table")
public class TableController extends BaseController {

	@Autowired
	private TableDAO conn_table;
	@Autowired
	private TableStatusDAO conn_tablestatus;
	@Autowired
	private OrderInfoDAO conn_orderInfo;
	
	// 餐桌列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();

		ModelAndView mv = new ModelAndView("admin/table/list", strMap);
		return mv;
	}
	
	// 餐桌列表数据
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> showTables(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String date = request.getParameter("date");
		String type = request.getParameter("atype");
		
//		long merchantId = Long.parseLong(request.getParameter("merchantId"));
		
		List<TablePO> tables = conn_table.findByMerchantId(1L);
		ArrayList<TableVo> tableVos = new ArrayList<TableVo>();
		
		if (tables != null) {
			for (TablePO tablePO : tables) {
				
				TableVo tableVo = new TableVo();
				
				TableStatusPO tableStatusPO = conn_tablestatus.findBytidt(tablePO.getId(), date, BookType.fromString(type));
				
				
				if (tableStatusPO != null) {
					tableVo.setTableId(tablePO.getId());
					tableVo.setBill(tableStatusPO.getBill());
					tableVo.setSize(tablePO.getSize());
					tableVo.setTableNo(tablePO.getTableNo());
					tableVo.setTableStatus(tableStatusPO.getTableStatus().toString());
					tableVo.setName(tablePO.getName());
					
					tableVos.add(tableVo);
				} else {
					TableStatusPO resettable = new TableStatusPO();
					resettable.setTableId(tablePO.getId());
					resettable.setDate(date);
					resettable.setType(BookType.fromString(type));
					resettable.setTableStatus(TableStatus.FREE);
				
					
					conn_tablestatus.save(resettable);
					
					tableVo.setBill(0);
					tableVo.setSize(tablePO.getSize());
					tableVo.setTableId(tablePO.getId());
					tableVo.setTableNo(tablePO.getTableNo());
					tableVo.setTableStatus(TableStatus.FREE.toString());
					tableVo.setName(tablePO.getName());
					
					tableVos.add(tableVo);
				}
			}
		} 
		
		map.put("tables", tableVos);
		return map;
	}
	
	//显示添加餐桌页面
	@RequestMapping("/addv")
	public String AddView(){
		return "admin/table/add";
	}

	//添加餐桌
	@ResponseBody
	@RequestMapping(value="/add.do",method= RequestMethod.POST)
	public String add(HttpServletRequest request){
		
		long tableNo = Long.parseLong(request.getParameter("tableNo"));
		long size = Long.parseLong(request.getParameter("size"));
		String name = request.getParameter("name");
		long bookprice = Long.parseLong(request.getParameter("bookprice"));
		
		TablePO table = new TablePO();
		// 需要从session获取
		table.setMerchantId(1L);
		table.setTableNo(tableNo);
		table.setSize(size);
		table.setName(name);
		table.setBookprice(bookprice);
		
		conn_table.save(table);
		
		return "success";
	}
	
	//删除餐桌
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String tableId =request.getParameter("tableId");
		String[] split = tableId.split("-");
		long splitId = Long.parseLong(split[1]);
		
		conn_table.deleteByField("id", splitId);
		
		return "success";
	}
	
	// 获取菜单
	@ResponseBody
	@RequestMapping(value="/showMenu.do",method= RequestMethod.POST)
	public Map<String, Object> getTableId(HttpServletRequest request) throws Exception{
		
		Map<String, Object> map = new HashMap<>();
		
		long tableId = Long.parseLong(request.getParameter("tableId"));
		
		String date = request.getParameter("date");
		String type = request.getParameter("type");
	
		TableStatusPO tableStatus = conn_tablestatus.findBytidt(tableId, date, BookType.fromString(type));
		long tableStatusId = tableStatus.getId();
		
		List<OrderInfoPO> tableOrders = conn_orderInfo.findTableOrders(tableStatusId, OrderStateType.CONFIRMED);
		
		long total = 0;
		for (OrderInfoPO orderInfoPO : tableOrders) {
			
			long price = orderInfoPO.getProductPrice();
			total += price;
		}
		
		map.put("total", total);
		map.put("tableStatusId", tableStatusId);
		map.put("tableOrders", tableOrders);
		
		return map;
	}
	
	//餐桌验单
	@ResponseBody
	@RequestMapping(value="/checkOrders.do",method= RequestMethod.POST)
	public String checkOrders(HttpServletRequest request){
		
		long tableStatusId = Long.parseLong(request.getParameter("tableStatusId"));
		
		TableStatusPO tableStatus = conn_tablestatus.get(tableStatusId);
		tableStatus.setTableStatus(TableStatus.FREE);
		
		conn_tablestatus.update(tableStatus);
		
		List<OrderInfoPO> orders = conn_orderInfo.findTableOrders(tableStatusId, OrderStateType.CONFIRMED);
		for (OrderInfoPO orderInfoPO : orders) {
			// 支付成功?支付完成?
			orderInfoPO.setOrderState(OrderStateType.PAYSUCCESS);
		}
		return "success";
	}
	
}
