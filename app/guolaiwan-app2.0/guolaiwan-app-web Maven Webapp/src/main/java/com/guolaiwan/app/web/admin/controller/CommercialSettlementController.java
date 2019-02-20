package com.guolaiwan.app.web.admin.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.Utils.ExportExcelSeedBack;
import com.guolaiwan.app.web.admin.vo.CommercialSettlementVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.distribute.vo.RegionVo;
import com.guolaiwan.bussiness.admin.dao.BalanceDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.po.BalancePO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/commercialsettlement")
public class CommercialSettlementController extends BaseController {
	@Autowired
	private MerchantDAO conn_Merchant;
	
	@Autowired
	private ProductDAO conn_Product;
	
	@Autowired
	private OrderInfoDAO conn_OrderInfo;
	
	@Autowired
	private BalanceDAO conn_Balance;
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getCommer(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();

		ModelAndView mv = new ModelAndView("admin/commercialsettlement/list",strMap);
		return mv;
	}	
	
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(HttpServletRequest request,int page,int limit) throws Exception {

		String sName  = request.getParameter("sName");
		System.out.println(sName);
		List<MerchantPO> merchantPOs = new ArrayList<MerchantPO>();
		List<ProductPO> productPOs = new ArrayList<ProductPO>();
		List<OrderInfoPO> orderInfoPOs = new ArrayList<OrderInfoPO>();
		List<CommercialSettlementVO> volist = new ArrayList<CommercialSettlementVO>();
		String num = "";
		int count;
		HttpSession session = request.getSession();
		
		if (sName != null) {
			merchantPOs = conn_Merchant.getAllMerchant(sName);
		}else {
			merchantPOs = conn_Merchant.findAll();
		}
		
		if (merchantPOs != null) {
			
			for (int i = 0; i < merchantPOs.size(); i++) {
				CommercialSettlementVO cs = null;
				productPOs = conn_Product.getAllSettle(merchantPOs.get(i).getId());
				double price = 0;
				if(productPOs!= null){
					for (int j = 0; j < productPOs.size(); j++) {
						orderInfoPOs = conn_OrderInfo.getAllOrder(productPOs.get(j).getId(),page,limit);
						if (orderInfoPOs != null) {
							for (int k = 0; k < orderInfoPOs.size(); k++) {
								if(orderInfoPOs.get(k).getBalance() == 0){
									price += orderInfoPOs.get(k).getPayMoney();
									num += Long.toString(orderInfoPOs.get(k).getId())+".";
//									System.out.println(orderInfoPOs.get(k).getId());
									cs = new CommercialSettlementVO();
									cs.setId(merchantPOs.get(i).getId());
									cs.setShopBankId(merchantPOs.get(i).getShopBankId());
									cs.setShopName(merchantPOs.get(i).getShopName());
									cs.setPrice(price/100);
									volist.add(cs);
									
								}
							}
						}
					}
				}

			} 
		
			for(int f = 0 ; f < volist.size(); f++){
				if (f!=0) {
					if (volist.get(f).getId()==volist.get(f-1).getId()) {
						volist.remove(f-1);
						f--;
					}
				}
			}
			session.setAttribute("num", num);
			session.setAttribute("volist", volist);
		}else{
			volist = null;
			session.setAttribute("num", "");
			session.setAttribute("volist",volist);
		}
		
		Map<String, Object> map= new HashMap<String, Object>();
		if (volist != null) {
			map.put("data", volist);
			map.put("code", "0");
			map.put("count", volist.size());
		}else{
			map.put("data", volist);
			map.put("code", "0");
			map.put("count", 0);
		}
		return map;
	}
	
	//结算数据
	@ResponseBody
	@RequestMapping(value="/settle.do", method= RequestMethod.GET)
	public String AddAndUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String num = (String) session.getAttribute("num");
		List<CommercialSettlementVO> volist = (List<CommercialSettlementVO>) session.getAttribute("volist");
		
		if (volist.size() != 0 || volist != null) {
			System.out.println(num);
			String str = "";
			for (int i = 0; i < num.length(); i++) {
				
				if (num.substring(i, i+1).equals(".")) {

					OrderInfoPO orderInfoPO = new OrderInfoPO();
					orderInfoPO = conn_OrderInfo.get(Long.valueOf(str));
					orderInfoPO.setBalance(1);
					conn_OrderInfo.update(orderInfoPO);
					str = "";
				}else{
					str += num.substring(i,i+1);
				}
			}
			
			System.out.println(volist.size());
			for (int i = 0; i < volist.size(); i++) {
				BalancePO balancePO = new BalancePO();
				balancePO.setUpdateTime(new Date());
				balancePO.setAmount(volist.get(i).getPrice()*100);
				balancePO.setBankNo(volist.get(i).getShopBankId());
				balancePO.setMerchantId(volist.get(i).getId());
				balancePO.setMerchantName(volist.get(i).getShopName());
				conn_Balance.save(balancePO);
			}
			
			//导出文件的标题
	        String title = "商家订单"+DateUtil.format(new Date(), "yyyyMMddhhmmss")+".xls";
	        //设置表格标题行
	        String[] headers = new String[] {"序号","名称", "银行卡号","结算金额"};
	    
	        List<Object[]> dataList = new ArrayList<Object[]>();
	        
	        for (int i = 0; i < volist.size(); i++) {
	            Object[] obj = new Object[headers.length];
				obj [0] = volist.get(i).getId();
				obj [1] = volist.get(i).getShopName();
				obj [2] = volist.get(i).getShopBankId();
				obj [3] = volist.get(i).getPrice()+"¥";
				dataList.add(obj);
			}
	        
	        //防止中文乱码
	        String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
	        response.setContentType("octets/stream");
	        response.setContentType("APPLICATION/OCTET-STREAM");
	        response.setHeader("Content-Disposition", headStr);
	        ServletOutputStream out=response.getOutputStream();
	        //ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
	        ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);//没有标题
	        ex.export(out);

			return "success";
		}else {
			return "false";
		}
	}
	
	//显示历史页面
	@RequestMapping(value = "/history/{id}",method = RequestMethod.GET)
	public ModelAndView historysettlePage(@PathVariable Long id,HttpServletRequest request) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		ModelAndView mv = new ModelAndView("admin/commercialsettlement/historylist",map);
		return mv;
	}
	
	//显示页面
	@RequestMapping(value = "/order/{id}",method = RequestMethod.GET)
	public ModelAndView ordersettlePage(@PathVariable Long id,HttpServletRequest request) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		ModelAndView mv = new ModelAndView("admin/commercialsettlement/orderlist",map);
		return mv;
	}
	
	//显示历史数据
	@ResponseBody
	@RequestMapping(value = "/history/history.do/{id}",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> historysettle(@PathVariable Long id,HttpServletRequest request,int page,int limit) throws Exception{


		List<BalancePO> balancePOl = new ArrayList<BalancePO>();
		BalancePO balancePOs =new BalancePO();
		List<BalancePO> balancePO = conn_Balance.getBalanceList(id, page, limit);
		if (balancePO != null) {
			for (int i = 0; i < balancePO.size(); i++) {
				balancePOs.setAmount(balancePO.get(i).getAmount()/100);
				balancePOs.setId(balancePO.get(i).getId());
				balancePOs.setMerchantName(balancePO.get(i).getMerchantName());
				balancePOs.setBankNo(balancePO.get(i).getBankNo());
				balancePOl.add(balancePOs);
			}
		}
		int count = conn_Balance.GetCountbyPage(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", balancePOl);
		map.put("count", count);
		map.put("msg", "");
		map.put("code", "0");

		return map;
	}
	
	//显示订单数据
	@ResponseBody
	@RequestMapping(value = "/order/order.do/{id}",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> orderlist(@PathVariable Long id,HttpServletRequest request,int page,int limit) throws Exception{

		List<OrderInfoPO> orderInfoPOs = conn_OrderInfo.getOrderList(id, page, limit);
		List<OrderInfoVO> orderInfoVOs = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
		int count = conn_OrderInfo.GetCountbyPage(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", orderInfoVOs);
		map.put("count", count);
		map.put("msg", "");
		map.put("code", "0");

		return map;
	}
	
}

