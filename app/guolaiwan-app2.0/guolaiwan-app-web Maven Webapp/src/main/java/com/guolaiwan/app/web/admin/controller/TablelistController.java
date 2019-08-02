package com.guolaiwan.app.web.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.guolaiwan.app.web.smartParking.vo.AttractionsVo;
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
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

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
	@Autowired
	private  TableStatusDAO Table_Status;

	// 餐桌列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/tablelist/list", strMap);
		return mv;
   }
	// 餐桌列表页面
	@RequestMapping(value = "/addv", method = RequestMethod.GET)
	public ModelAndView addv(HttpServletRequest request ,String merchantId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("admin/tablelist/add", strMap);
		mv.addObject("merchantId",merchantId);
		return mv;
	}
	
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/add.do")
	public Map<String, Object> historysettle(HttpServletRequest request) throws Exception{ //添加房间
		String tablename = request.getParameter("name");//名称
        String tableNo = request.getParameter("tableNo");//桌号
        String tier = request.getParameter("title");//层
        String type = request.getParameter("type");//人数
        String bookprice = request.getParameter("bookprice");//订金
        double money =  Double.parseDouble(bookprice)*100;
        String radio = request.getParameter("radio");//包间
        System.out.println("  !!!!!!!!!!!!!!!!!!!!!!!!!!!!" +radio);
        String merchantId = request.getParameter("merchantId");//包间\
        TablePO add = new TablePO();
        add.setTablename(tablename);
        add.setTableNo(tableNo);
        add.setTier(tier);
        add.setTableState(0+"");
        add.setMerchantId(Long.parseLong(merchantId));
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
		String TableDate = request.getParameter("TableDate");//时间
		String type = request.getParameter("type");//午晚
		List<TableVo>   _merchants = TableVo.getConverter(TableVo.class).convert(addpo,
				TableVo.class);
		for (TableVo tablePO : _merchants) {
			if (TableDate != null&&type !=null && TableDate !="" ) {//传入已选时间
				TableStatusPO  TableStatus =	Table_Status.findBytidt(tablePO.getId(),TableDate,BookType.fromName(type));//查询中间表
				if (TableStatus != null  ) {
					System.out.println("  --------------------");
					tablePO.setTableState("2");//已预订
					tablePO.setTableMenu(TableStatus.getTableMenu());
					tablePO.setUserName("刘 "); ////////////////////////////////////////////////////////////////用户名称
					tablePO.setUserPhone("18731560959"); //////////////////////////////////////////////////// 手机
					tablePO.setMenuTime(TableStatus.getDate()); //时间
					tablePO.setType(TableStatus.getType().toString()); //中午晚上
				}
			}else {
				 SimpleDateFormat def = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				 Date date = new Date();
			     SimpleDateFormat df = new SimpleDateFormat("HH");
			     String str = df.format(date);
			     int a = Integer.parseInt(str);
				 if (a > 06 && a <= 13) {  //判断6 点 之前是中午 13点
					 System.out.println("中午");
					 type =  "LUNCH";
				 }else if (a > 13 && a <= 19) { // 13 -19 我晚上
			         System.out.println("下午");
			         type =   "DINNER";
			     }else {   // 都不满足  时间加一天查询第二天中午的桌
			    	 Calendar c = Calendar.getInstance();
			    	 c.add(Calendar.DAY_OF_MONTH, 1);
			    	 type =  "LUNCH";
			    	 System.out.println("增加一天后日期:"+def.format(c.getTime()));
				 }
				 System.out.println(def.format(new Date()).toString()+"  -----------------------------------------------");
				 TableStatusPO  TableStatus =	Table_Status.findBytidt(tablePO.getId(),def.format(new Date()).toString(),BookType.fromString(type));//查询中间表
				 if (TableStatus != null  ) {
						tablePO.setTableState("2");//已预订
						tablePO.setTableMenu(TableStatus.getTableMenu());
						tablePO.setMenuTime(TableStatus.getDate()); //时间
						tablePO.setType(TableStatus.getType().toString()); //中午晚上
						tablePO.setUserName("刘"); ////////////////////////////////////////////////////////////////用户名称
						tablePO.setUserPhone("18731560959"); //////////////////////////////////////////////////// 手机
			    }
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("po", _merchants);
		return map;
	}
	
	
}
