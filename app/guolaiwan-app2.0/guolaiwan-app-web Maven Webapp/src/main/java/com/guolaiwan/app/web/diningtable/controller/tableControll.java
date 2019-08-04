package com.guolaiwan.app.web.diningtable.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.TableVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.TableDAO;
import com.guolaiwan.bussiness.admin.dao.TableStatusDAO;
import com.guolaiwan.bussiness.admin.enumeration.BookType;
import com.guolaiwan.bussiness.admin.po.TablePO;
import com.guolaiwan.bussiness.admin.po.TableStatusPO;


@Controller
@RequestMapping("/reservetable")  //订桌
public class tableControll extends WebBaseControll  {
	@Autowired
	private  TableDAO Table;
	@Autowired
	private  TableStatusDAO Table_Status;

	@RequestMapping(value = "/tables/home") //订桌首页
	public ModelAndView tables(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tables");
		return mv;
	}
	@RequestMapping(value = "/diningtable/tablePayment") //订桌支付
	public ModelAndView tablePayment(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tablePayment");
		return mv;
	}
	@RequestMapping(value = "/diningtable/tablesDetails") //订桌详情
	public ModelAndView tablesDetails(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tablesDetails");
		return mv;
	}
	@RequestMapping(value = "/diningtable/tableSuccess")//订桌
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tableSuccess");
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
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String merchantId = pageObject.getString("merchantId");  
		Long  merchant  =Long.parseLong(merchantId);
		String tier = pageObject.getString("tier");   //层数
		String TableDate = pageObject.getString("tableDate");   //时间
		String type = pageObject.getString("type");   //午餐晚餐
		List<TableVo>   _merchants= null;
		if(merchantId != null && merchantId != null){
			List<TablePO> addpo = null;
			if ( tier != null && tier !="" ) {
				addpo =  Table.findByMerchantId(merchant,tier);
			}else  {
				addpo =  Table.findByMerchantId(merchant);
			}
			   _merchants = TableVo.getConverter(TableVo.class).convert(addpo,
					TableVo.class);
			for (TableVo tablePO : _merchants) {
				System.out.println(" ================================");
				TableStatusPO TableStatus= null;
				if ( !"" .equals(TableDate) &&  !"" .equals(type) ) {//传入已选时间
					TableStatus =	Table_Status.findBytidt(tablePO.getId(),TableDate,BookType.fromName(type));//查询中间表
				}else {
					SimpleDateFormat def = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
					Date date = new Date();
					SimpleDateFormat df = new SimpleDateFormat("HH");
					String str = df.format(date);
					int a = Integer.parseInt(str);
					if (a > 06 && a <= 12) {  //判断6 点 之前是中午 12点
						type =  "LUNCH";
					}else if (a > 12 && a <= 19) { // 13 -19 我晚上
						type =   "DINNER";
					}else {   // 都不满足  时间加一天查询第二天中午的桌
						Calendar c = Calendar.getInstance();
						c.add(Calendar.DAY_OF_MONTH, 1);
						type =  "LUNCH";
					}
					TableStatus =	Table_Status.findBytidt(tablePO.getId(),def.format(new Date()).toString(),BookType.fromString(type));//查询中间表
				}
				if ( TableStatus != null  ) {
					System.out.println(TableStatus.getId()+"   --------1-1-1-1-1-1-1-");
					tablePO.setTableState("2");//已预订
					tablePO.setTableMenu(TableStatus.getTableMenu());
					tablePO.setMenuTime(TableStatus.getDate()); //时间
					tablePO.setType(TableStatus.getType().toString()); //中午晚上
					tablePO.setUserName("刘"); ////////////////////////////////////////////////////////////////用户名称
					tablePO.setUserPhone("18731560959"); //////////////////////////////////////////////////// 手机
					tablePO.setTableStatusId(TableStatus.getId());
				}
			}
		}
		for (TableVo tableVo : _merchants) {
			System.out.println(tableVo.getId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchant", _merchants);
		return map;
	}
	
	
	
	
	
	
	
	
	

	private String getRequestJson(HttpServletRequest request) {
		try {
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			request.getInputStream().close();
			br.close();
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}
	
	
	
	
	

	
}
