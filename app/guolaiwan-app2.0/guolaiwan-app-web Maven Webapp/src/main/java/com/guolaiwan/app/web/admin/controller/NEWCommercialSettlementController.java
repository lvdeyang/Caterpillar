package com.guolaiwan.app.web.admin.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.Utils.ExportExcelSeedBack;
import com.guolaiwan.app.web.admin.vo.BalanceVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.BalanceDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.BalancePO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/newcommercialsettlement")
public class NEWCommercialSettlementController extends BaseController {
	@Autowired
	private MerchantDAO conn_Merchant;

	@Autowired
	private ProductDAO conn_Product;

	@Autowired
	private OrderInfoDAO conn_OrderInfo;

	@Autowired
	private BalanceDAO conn_Balance;

	// 显示列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getCommer(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<BalancePO> balancePO = conn_Balance.findAll();
		Double cashAmount=0d;
		Double getAmount=0d;
		for (BalancePO balancePO2 : balancePO) {
			cashAmount+=balancePO2.getAmount();
			getAmount+=balancePO2.getAccrued();
		}
        strMap.put("cashAmount", cashAmount/100);
        strMap.put("getAmount", getAmount/100);
		ModelAndView mv = new ModelAndView("admin/newcommercialsettlement/list", strMap);
		
		return mv;
	}

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(HttpServletRequest request, int page, int limit) throws Exception {

		String sName = request.getParameter("sName");
		String sTime = request.getParameter("sTime");
		List<BalancePO> balancePO = conn_Balance.findAllSort(page, limit);
		
		List<BalancePO> balancePOs = new ArrayList<BalancePO>();
		int count = conn_Balance.countAll();

		if (sName == null && sTime == null) {
			balancePOs = balancePO;
		} else if (sName != null && sTime == "") {
			balancePOs = conn_Balance.getSettleHistoryList(sName, page, limit);
		} else if (sTime != null && sName == "") {
			String startd = sTime.substring(0, 19);
			String settled = sTime.substring(21, sTime.length());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = formatter.parse(startd);
			Date settleDate = formatter.parse(settled);
			for (int i = 0; i < balancePO.size(); i++) {
				if (balancePO.get(i).getSettleDate().after(startDate)
						&& balancePO.get(i).getSettleDate().before(settleDate)) {
					balancePOs.add(balancePO.get(i));
				}
			}
		}

		List<BalanceVO> balanceVO = BalanceVO.getConverter(BalanceVO.class).convert(balancePOs, BalanceVO.class);
		request.getSession().setAttribute("balancelist", balanceVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", balanceVO);
		map.put("code", 0);
		map.put("count", count*1000);
		return map;
	}

	// 导出总表
	@ResponseBody
	@RequestMapping(value = "/deriveall.do/{id}", method = RequestMethod.GET)
	public String DeriveAll(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BalancePO balancePO = conn_Balance.get(id);
		MerchantPO merchantPO = conn_Merchant.get(balancePO.getMerchantId());
		// 导出文件的标题
		String title = "商家总表" + DateUtil.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 设置表格标题行
		String[] headers = new String[] { "序号", "商户名称", "总金额", "返还金额", "提成金额", "姓名", "开户行", "银行卡号", "结算日期" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] obj = new Object[headers.length];
		obj[1] = merchantPO.getShopName();
		obj[2] = new DecimalFormat("0.00").format((balancePO.getAmount()+balancePO.getAccrued()) / 100);
		obj[3] = new DecimalFormat("0.00").format(((double) balancePO.getAmount() / 100));
		obj[4] = new DecimalFormat("0.00").format(balancePO.getAccrued() / 100);
		obj[5] = merchantPO.getShopLinkperson();
		obj[6] = merchantPO.getShopOpenBank();
		obj[7] = merchantPO.getShopBankId();
		obj[8] = balancePO.getSettleDate();
		dataList.add(obj);

		outputList(title, headers, dataList, response);

		return "success";
	}

	// 导出详情表
	@ResponseBody
	@RequestMapping(value = "/derivedetails.do/{merid}/{id}", method = RequestMethod.GET)
	public String DeriveDetails(@PathVariable Long merid, @PathVariable Long id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<OrderInfoPO> orderInfoPOs = conn_OrderInfo.getAllDeriveDetails(merid);
		List<BalancePO> balancePOs = conn_Balance.getOneById(id);
		List<OrderInfoVO> orderInfoVOs = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs,
				OrderInfoVO.class);
		List<BalanceVO> balanceVOs = BalanceVO.getConverter(BalanceVO.class).convert(balancePOs, BalanceVO.class);
		// 导出文件的标题
		String title = "商家详情表" + DateUtil.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 设置表格标题行
		String[] headers = new String[] { "序号", "订单号", "商户名称", "商品数量", "商品单价", "板块名称", "产品名称", "总金额", "支付金额", "提成比例",
				"返还金额", "提成金额", "支付方式", "下单日期", "订单状态", "验单日期" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		DecimalFormat df = new DecimalFormat("0.00");
		double allprice = 0, orderallmoney = 0, allpaymoney = 0, givemoney = 0, tichengmoney = 0;
		for (int i = 0; i < orderInfoVOs.size(); i++) {
            String[] oBDates=orderInfoVOs.get(i).getSettleDate().split(" ");
            String[] bBDates=balanceVOs.get(0).getSettleDate().split(" ");
			if ((orderInfoPOs.get(i).getOrderState().equals(OrderStateType.TESTED)||
					orderInfoPOs.get(i).getOrderState().equals(OrderStateType.RECEIPT)||
					orderInfoPOs.get(i).getOrderState().equals(OrderStateType.COMMENTED)
					)&& oBDates[0].equals(bBDates[0])) {
				Object[] obj = new Object[headers.length];
				obj[1] = orderInfoVOs.get(i).getOrderNO();
				obj[2] = orderInfoVOs.get(i).getShopName();
				obj[3] = orderInfoVOs.get(i).getProductNum();
				obj[4] = orderInfoVOs.get(i).getProductPrice();
				allprice += Double.valueOf(orderInfoVOs.get(i).getProductPrice());
				obj[5] = orderInfoVOs.get(i).getBkName();
				obj[6] = orderInfoVOs.get(i).getProductName();
				obj[7] = orderInfoVOs.get(i).getOrderAllMoney();
				orderallmoney += Double.valueOf(orderInfoVOs.get(i).getOrderAllMoney());
				obj[8] = orderInfoVOs.get(i).getPayMoney();
				allpaymoney += Double.valueOf(orderInfoVOs.get(i).getPayMoney());
				if (orderInfoVOs.get(i).getProductId() == 0) {
					
                    List<ProductPO> productPOs=conn_Product.findByMerchantId(orderInfoVOs.get(i).getShopId());
                    if(!productPOs.isEmpty()){
                    	ProductPO productPO = productPOs.get(0);
                    	double accrued = (double)productPO.getProductCommissionPrice() / 100;
    					double accruedmoney = 0;
    					if (productPO.getProductCommissionCode() == 1) {
    						accruedmoney = productPO.getProductCommissionPrice() * (orderInfoVOs.get(i).getProductNum()==0?1:orderInfoVOs.get(i).getProductNum())
    								/ 100;
    					} else {
    						accruedmoney = productPO.getProductCommissionPrice()
    								* Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) / 100;
    					}
    					obj[9] = df.format(accrued);
    					obj[10] = df.format(Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) - accruedmoney);
    					givemoney += Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) - accruedmoney;
    					obj[11] = df.format(accruedmoney);
    					tichengmoney += accruedmoney;
                    }
					
				} else {
					ProductPO productPO = conn_Product.get(orderInfoVOs.get(i).getProductId());

					double accrued = (double)productPO.getProductCommissionPrice() / 100;
					double accruedmoney = 0;
					if (orderInfoPOs.get(i).getActivityId() != 0) {
						accrued=0;
					} else if (productPO.getProductCommissionCode() == 1) {
						accruedmoney = productPO.getProductCommissionPrice() * (orderInfoVOs.get(i).getProductNum()==0?1:orderInfoVOs.get(i).getProductNum())
								/ 100;
					} else {
						accruedmoney = productPO.getProductCommissionPrice()
								* Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) / 100;
					}
					obj[9] = df.format(accrued);
					obj[10] = df.format(Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) - accruedmoney);
					givemoney += Double.parseDouble(orderInfoVOs.get(i).getPayMoney()) - accruedmoney;
					obj[11] = df.format(accruedmoney);
					tichengmoney += accruedmoney;
				}

				obj[12] = orderInfoVOs.get(i).getPayMode();
				obj[13] = orderInfoVOs.get(i).getPayDate();
				obj[14] = orderInfoVOs.get(i).getOrderState();
				obj[15] = orderInfoVOs.get(i).getYdDate();
				dataList.add(obj);
			}

		}
		Object[] obj = new Object[headers.length];
		obj[1] = "合计";
		obj[4] = df.format(allprice);
		obj[7] = df.format(orderallmoney);
		obj[8] = df.format(allpaymoney);
		obj[10] = df.format(givemoney);
		obj[11] = df.format(tichengmoney);
		dataList.add(obj);

		outputList(title, headers, dataList, response);

		return "success";
	}

	// 导出总表
	@ResponseBody
	@RequestMapping(value = "/deriveallorder.do", method = RequestMethod.GET)
	public String DeriveAllOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<BalanceVO> balanceVOs = (List<BalanceVO>) request.getSession().getAttribute("balancelist");
		// 导出文件的标题
		String title = "商家总表" + DateUtil.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		// 设置表格标题行
		String[] headers = new String[] { "序号", "商户名称", "总金额", "返还金额", "提成金额", "姓名", "开户行", "银行卡号", "结算日期" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		DecimalFormat df = new DecimalFormat("0.00");
		double allmoney = 0, givemoney = 0, tichengmoney = 0;
		for (int i = 0; i < balanceVOs.size(); i++) {
			Object[] obj = new Object[headers.length];
			MerchantPO merchantPO = conn_Merchant.get(balanceVOs.get(i).getMerchantId());
			obj[1] = balanceVOs.get(i).getMerchantName();
			double amount1=Double.valueOf(balanceVOs.get(i).getAmount());
			double accure1=Double.valueOf(balanceVOs.get(i).getAccrued());
			obj[2] = df.format(amount1+accure1);
			allmoney += amount1+accure1;
			obj[3] = balanceVOs.get(i).getAmount();
			givemoney += Double.parseDouble(balanceVOs.get(i).getAmount());
			obj[4] = balanceVOs.get(i).getAccrued();
			tichengmoney += Double.valueOf(balanceVOs.get(i).getAccrued());
			obj[5] = merchantPO.getShopLinkperson();
			obj[6] = merchantPO.getShopOpenBank();
			obj[7] = balanceVOs.get(i).getBankNo();
			obj[8] = balanceVOs.get(i).getSettleDate();
			dataList.add(obj);
		}
		Object[] obj = new Object[headers.length];
		obj[1] = "合计";
		obj[2] = df.format(allmoney);
		obj[3] = df.format(givemoney);
		obj[4] = df.format(tichengmoney);
		dataList.add(obj);

		outputList(title, headers, dataList, response);

		return "success";
	}

	// 输出表
	public void outputList(String title, String headers[], List<Object[]> dataList, HttpServletResponse response)
			throws Exception {
		String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "utf-8") + "\"";
		response.setContentType("octets/stream");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", headStr);
		ServletOutputStream out = response.getOutputStream();
		// ExportExcel ex = new ExportExcel(title, headers, dataList);//有标题
		ExportExcelSeedBack ex = new ExportExcelSeedBack(title, headers, dataList);// 没有标题
		ex.export(out);
	}
}
