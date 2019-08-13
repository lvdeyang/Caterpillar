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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.TableVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.app.web.weixin.YuebaWxPayConstants;
import com.guolaiwan.app.web.weixin.YuebaWxUtil;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.TableDAO;
import com.guolaiwan.bussiness.admin.dao.TableStatusDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.BookType;
import com.guolaiwan.bussiness.admin.po.TablePO;
import com.guolaiwan.bussiness.admin.po.TableStatusPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.sun.jna.platform.win32.WinDef.LONG;

import pub.caterpillar.weixin.constants.WXContants;


@Controller
@RequestMapping("/reservetable")  //订桌
public class tableControll extends WebBaseControll  {
	@Autowired
	private  TableDAO Table;
	@Autowired
	private  TableStatusDAO Table_Status;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private UserInfoDAO conn_user;
	
	
	
	@RequestMapping(value = "/tables/home") //订桌首页
	public ModelAndView tables(HttpServletRequest request,Long merchantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tables");
		if (merchantId != null && merchantId != -1) mv.addObject("merchantId",merchantId);
		return mv;
	}
	@RequestMapping(value = "/diningtable/tablePayment") //订桌支付
	public ModelAndView tablePayment(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tablePayment");
		return mv;
	}
	@RequestMapping(value = "/diningtable/tablesDetails") //订桌详情
	public ModelAndView tablesDetails(HttpServletRequest request ,long tablesId,long merchantId ,String repast ,String tableDate) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tablesDetails");
		mv.addObject("tablesId",tablesId);
		mv.addObject("merchantId",merchantId);
		if (repast != "")mv.addObject("repast",repast);
		if (tableDate != "")mv.addObject("tableDate",tableDate);
		return mv;
	}
	@RequestMapping(value = "/diningtable/tableSuccess")//订桌
	public ModelAndView home(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tableSuccess");
		return mv;
	}
	
	@RequestMapping(value = "/mobile/tables")//订桌
	public ModelAndView mobile(HttpServletRequest request ,long merchantId ) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("diningtable/reservetable/tableMoblie");
		mv.addObject("merchantId",merchantId);
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
		String feature = pageObject.getString("feature");   //特色     pageObject.getString("feature")
		int soleTable = 0; //判断是否 全部都是包间 或 餐桌
		List<TableVo>   _merchants = null;
		Map<String, Object> map = new HashMap<String, Object>();
		String nowDate = "";
		String repast = "";
		
		if(merchantId != null && merchantId != null){
			List<TablePO> addpo = null;
			if ( tier != null && tier !="" &&   "".equals(feature) || feature == null) {  //根据层查询
				addpo =  Table.findByMerchantId(merchant,tier);
				int   table = Table.ByMerchantId(merchant,tier,Integer.parseInt("0"));
				int   room = Table.ByMerchantId(merchant,tier,Integer.parseInt("1"));
				if (table >0 && room <= 0 ) { //判断此层只有桌
					soleTable = 1;
				}else if ( table <=0 && room > 0) {//判断此层只有房
					soleTable = 2;
				} 
			}else{// 根据特色层查询
				String  split[]  =  feature.split(",");
				addpo =  Table.findByFeature(merchant,tier,split);
				int   table = Table.ByMerchantId(merchant,tier,Integer.parseInt("0"),split);
				int   room = Table.ByMerchantId(merchant,tier,Integer.parseInt("1"),split);
				if (table >0 && room <= 0 ) { //判断此层只有桌
					soleTable = 1;
				}else if ( table <=0 && room > 0) {//判断此层只有房
					soleTable = 2;
				} 
			}
			_merchants = TableVo.getConverter(TableVo.class).convert(addpo,
					TableVo.class);
			
			TableStatusPO TableStatus= null;
			for (TableVo tableVo : _merchants) {
				if ( !"" .equals(TableDate) &&  !"" .equals(type) ) {//传入已选时间
					TableStatus =	Table_Status.findBytidt(tableVo.getId(),TableDate,BookType.fromName(type));//查询中间表
				}else { //如果用户没有选择 时间 和就餐时间  自己判断时间  午餐晚餐
					//进入方法时先获取当前时间   在判定就餐时间是午餐还是晚餐
					SimpleDateFormat def = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
					Date date = new Date();
					SimpleDateFormat df = new SimpleDateFormat("HH");
					String str = df.format(date);
					int a = Integer.parseInt(str);
					String format =   def.format(new Date()).toString(); //当前时间
					if (a > 06 && a <= 12) {  //判断6 点 之前是中午 12点
						type =  "LUNCH";
					}else if (a > 12 && a <= 19) { // 13 -19 我晚上
						type =   "DINNER";
					}else {   // 都不满足  时间加一天查询第二天中午的桌
						Calendar c = Calendar.getInstance();
						c.add(Calendar.DAY_OF_MONTH, 1);
						format = def.format(c.getTime());
						type =  "LUNCH";
					}
					nowDate = format;
					repast = BookType.fromString(type).toString();
					TableStatus =	Table_Status.findBytidt(tableVo.getId(),format,BookType.fromString(type));//查询中间表
				}
				if ( TableStatus != null  ) {
					tableVo.setTableState("2");//已预订
					tableVo.setTableMenu(TableStatus.getTableMenu());
					tableVo.setMenuTime(TableStatus.getTableDate()); //时间
					tableVo.setType(TableStatus.getType().toString()); //中午晚上
					tableVo.setUserName("刘"); ////////////////////////////////////////////////////////////////用户名称
					tableVo.setUserPhone("18731560959"); //////////////////////////////////////////////////// 手机
					tableVo.setTableStatusId(TableStatus.getId());
				}
			}
		}
		map.put("date", nowDate);
		map.put("repast", repast);
		map.put("merchant", _merchants);
		map.put("sole", soleTable);
		return map;
	}



	@ResponseBody
	@RequestMapping(value = "/search.do",method = RequestMethod.POST) // 搜索功能
	public Map<String, Object> SetAmend(HttpServletRequest request) throws Exception{//添加入驻信息
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String merchantId = pageObject.getString("merchantId");  
		Long  merchant  =Long.parseLong(merchantId);

		String search = pageObject.getString("search");   //搜索
		String TableDate = pageObject.getString("tableDate");   //时间
		String type = pageObject.getString("type");   //午餐晚餐

		int soleTable  = 0;
		List<TablePO> addpo  = Table.findSearch(merchant,search);
		int   table = Table.getfindSearch(merchant,search);
		int   room = Table.getfindSearch(merchant,search);

		if (table >0 && room <= 0 ) { //判断此层只有桌
			soleTable = 1;
		}else if ( table <=0 && room > 0) {//判断此层只有房
			soleTable = 2;
		} 
		List<TableVo>  _merchants = TableVo.getConverter(TableVo.class).convert(addpo,
				TableVo.class);
		TableStatusPO TableStatus= null;
		for (TableVo tableVo : _merchants) {
			if ( !"" .equals(TableDate) &&  !"" .equals(type) ) {//传入已选时间
				TableStatus =	Table_Status.findBytidt(tableVo.getId(),TableDate,BookType.fromName(type));//查询中间表
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
				TableStatus =	Table_Status.findBytidt(tableVo.getId(),def.format(new Date()).toString(),BookType.fromString(type));//查询中间表
			}
			if ( TableStatus != null  ) {
				tableVo.setTableState("2");//已预订
				tableVo.setTableMenu(TableStatus.getTableMenu());
				tableVo.setMenuTime(TableStatus.getTableDate()); //时间
				tableVo.setType(TableStatus.getType().toString()); //中午晚上
				tableVo.setUserName("刘"); ////////////////////////////////////////////////////////////////用户名称
				tableVo.setUserPhone("18731560959"); //////////////////////////////////////////////////// 手机
				tableVo.setTableStatusId(TableStatus.getId());
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchant", _merchants);
		map.put("sole", soleTable);
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/particulars.do",method = RequestMethod.POST) // 搜索功能
	public Map<String, Object> getParticulars(HttpServletRequest request) throws Exception{//添加入驻信息
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String tableId = pageObject.getString("tableId");  
		TablePO addpo =  Table.getByField("id",Long.parseLong(tableId));
		if (addpo.getDetailsImg() !="" &&addpo.getDetailsImg() !=null ) {
			addpo.setDetailsImg(conn_sysConfig.getSysConfig().getWebUrl()+addpo.getDetailsImg()+"");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", addpo);
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/addition.do",method = RequestMethod.POST) // 保存用户信息
	public Map<String, Object> addition(HttpServletRequest request) throws Exception{//添加入驻信息
		String param = getRequestJson(request);
		 Long userId = 	(Long) request.getSession().getAttribute("userId");
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String merchantId = pageObject.getString("merchantId");  
		String tableId = pageObject.getString("tablesId");  
		String userName = pageObject.getString("userName");  
		String userPhone = pageObject.getString("userPhone");  
		String tableDate = pageObject.getString("tableDate");  
		String type = pageObject.getString("repast");  
		TableStatusPO tableStatus = new TableStatusPO();
		tableStatus.setUserName(userName);
		tableStatus.setUserPhone(userPhone);
		tableStatus.setTableDate(tableDate);
		tableStatus.setType(BookType.fromString(type));
		tableStatus.setTableState("NOTPAY");
		tableStatus.setUserId(userId+"");
		tableStatus.setMerchantId(Long.parseLong(merchantId));
		tableStatus.setTableId(Long.parseLong(tableId));
		Table_Status.save(tableStatus);
		long id  =  tableStatus.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", id);
		return map;
	}


	
	
	
	

	// 购买订桌
	@ResponseBody
	@RequestMapping(value = "/prev/table/{id}/{money}")
	public Object prevRtrenew(@PathVariable String id, @PathVariable Integer money,  HttpServletRequest request) throws Exception {
		String orderNo = "table-" + id  /* +"-"ID+"-"景ID */;
		TableStatusPO TableStatus =	Table_Status.getByField("id",Long.parseLong(id));//查询中间表
		TableStatus.setOderNo(orderNo);
		Table_Status.saveOrUpdate(TableStatus);
		int payMoney = 1;
		Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user = conn_user.get(userId);
		YuebaWxPayConstants.set("http://" + WXContants.Website + "/website/wxreport/tablePayment", WxConfig.appId,
				WxConfig.appsrcret);
		// 统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, payMoney,
				"192.165.56.64", user.getUserOpenID());
		// 生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		// 将订单号放入map，用以支付后处理
		map.put("orderNo", orderNo);
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
