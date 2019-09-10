package com.guolaiwan.app.web.listener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.dom4j.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;

import com.guolaiwan.app.web.admin.vo.CommercialSettlementVO;
import com.guolaiwan.bussiness.admin.dao.AdminDAO;
import com.guolaiwan.bussiness.admin.dao.BalanceDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.MenuDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.RoleDAO;
import com.guolaiwan.bussiness.admin.dao.RoleMenuDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VoteProductDAO;
import com.guolaiwan.bussiness.admin.enumeration.CompanyType;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.BalancePO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.RoleMenuPO;
import com.guolaiwan.bussiness.admin.po.RolePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.VoteProductPO;
import com.guolaiwan.bussiness.gateway.GateWayTcpManager;

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.init.InitLoader;
import pub.caterpillar.weixin.constants.WXContants;

public class ContextListener extends InitLoader {

	private MerchantDAO conn_Merchant;
	private ProductDAO conn_Product;
	private OrderInfoDAO conn_OrderInfo;
	private BalanceDAO conn_Balance;
	private VoteProductDAO voteProductDao;

	@Override
	public void customInitialize() {


		initOrderThread();
		foodCompetition();
		try {
			//initGateSocket();
		} catch (Exception e) {
			// TODO: handle exception
		}


		System.out.println("context 初始化!");

	}

	private void initGateSocket(){

		GateWayTcpManager gmanager=new GateWayTcpManager();
	}


	private void initOrderThread(){

		conn_Merchant = SpringContext.getBean("com.guolaiwan.bussiness.merchant.dao.MerchantDAO");
		conn_Product = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.ProductDAO");
		conn_OrderInfo = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.OrderInfoDAO");
		conn_Balance = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.BalanceDAO");

		// 定时商家结算
		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				List<OrderInfoPO> deliOrderInfoPOs=conn_OrderInfo.findByField("orderState", OrderStateType.DELIVER);
				for (OrderInfoPO orderInfoPO : deliOrderInfoPOs) {
					long dayCount=DateUtil.daysBetween(orderInfoPO.getUpdateTime(), new Date());
					if(dayCount>7){
						orderInfoPO.setOrderState(OrderStateType.TESTED);
						conn_OrderInfo.update(orderInfoPO);
					}				
				}



				SimpleDateFormat sdy = new SimpleDateFormat("yyyy");
				SimpleDateFormat sdm = new SimpleDateFormat("MM");
				SimpleDateFormat sdd = new SimpleDateFormat("dd");
				Date date = new Date();
				int year = Integer.parseInt(sdy.format(date));
				int month = Integer.parseInt(sdm.format(date));
				int day = Integer.parseInt(sdd.format(date));
				Calendar cal = Calendar.getInstance();
				cal.set(year, month, day);
				int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

				cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, 1);
				cal.set(Calendar.DAY_OF_MONTH, 0);
				int lastday = Integer.parseInt(sdd.format(cal.getTime()));

				if (day != 10 && day != 20 && day != lastday) {
					return;
				}

				if (date.getHours() != 23) {
					return;
				}

				try {
					List<BalancePO> chksBalancePOs = conn_Balance.getByToday();
					if (chksBalancePOs != null && !chksBalancePOs.isEmpty()) {
						return;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				List<MerchantPO> merchantPOs = new ArrayList<MerchantPO>();
				List<ProductPO> productPOs = new ArrayList<ProductPO>();
				List<OrderInfoPO> orderInfoPOs = new ArrayList<OrderInfoPO>();
				List<CommercialSettlementVO> volist = new ArrayList<CommercialSettlementVO>();
				String num = "";
				int count;
				merchantPOs = conn_Merchant.findAll();

				if (merchantPOs != null) {

					for (int i = 0; i < merchantPOs.size(); i++) {
						// Mr黄擦涛的屁股
						List<OrderInfoPO> balanceOrders = conn_OrderInfo
								.getOrdersByMerBalanced(merchantPOs.get(i).getId());
						double totalBalance = 0;
						double accured = 0;
						for (OrderInfoPO order : balanceOrders) {
							totalBalance += order.getPayMoney();
							if (order.getProductId() > 0) {
								ProductPO productPO = conn_Product.get(order.getProductId());
								if (order.getActivityId() != 0) {

								} else if (productPO.getProductCommissionCode() == 1) {
									accured += productPO.getProductCommissionPrice() * order.getProductNum();
								} else {
									accured += order.getPayMoney() * productPO.getProductCommissionPrice() / 100;
								}
							}else{
								List<ProductPO> productPOs2=conn_Product.findByMerchantId(order.getShopId());
								if(!productPOs2.isEmpty()){
									ProductPO productPO = productPOs2.get(0);
									if (productPO.getProductCommissionCode() == 1) {
										accured += productPO.getProductCommissionPrice() * order.getProductNum();
									} else {
										accured += order.getPayMoney() * productPO.getProductCommissionPrice() / 100;
									}
								}

							}
							order.setBalance(1);
							order.setSettleDate(new Date());
							conn_OrderInfo.saveOrUpdate(order);
						}
						if (balanceOrders.size() != 0) {
							BalancePO balancePO = new BalancePO();
							balancePO.setAmount(totalBalance - accured);
							balancePO.setBankNo(merchantPOs.get(i).getShopBankId());
							balancePO.setMerchantId(merchantPOs.get(i).getId());
							balancePO.setMerchantName(merchantPOs.get(i).getShopName());
							balancePO.setAccrued(accured);
							balancePO.setSettleDate(new Date());
							conn_Balance.save(balancePO);
						}
					}

				}
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 3600 * 1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);


	}



	private void foodCompetition(){ // 美食大赛
		voteProductDao = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.VoteProductDAO");
		// 定时排序
		TimerTask timrTask = new TimerTask() {
			@Override
			public void run() {
				// 定时排序
				List<VoteProductPO> voteProduct = 	voteProductDao.getVoteProduct();
				if(voteProduct != null){
					List<VoteProductPO>  listProduct =  new ArrayList<VoteProductPO>();
					/*System.out.println(voteProduct.size() +	" :::");*/

					for(int i = 0; i < voteProduct.size(); i++){
						listProduct.add(voteProduct.get(i));
					}

					Collections.sort(voteProduct, new Comparator<VoteProductPO>(){

						@Override
						public int compare(VoteProductPO o1, VoteProductPO o2){
							if(o1.getModularcode() == o2.getModularcode()){
								return (int) ((o2.getAllvotes()) - (o1.getAllvotes()));
							}else{
								return 1;
							}
						};

					});

					/*for(int i = 0; i < 4; i++){
						System.out.println(voteProduct.get(i).getId() + "---" + voteProduct.get(i).getProductName() );
					}
					for(int i = 0; i < 4; i++){
						System.out.println(listProduct.get(i).getId() + "---" + listProduct.get(i).getProductName() +"  ------");
					}*/
					int index = 0;
					for(int i = 0; i < voteProduct.size(); i++){
						if (!voteProduct.get(i).getId() .equals( listProduct.get(i).getId() )) {
							index = i;
							System.out.println(voteProduct.get(i).getProductName());
							voteProduct.get(i).setRanking(index+1);
							voteProductDao.saveOrUpdate(voteProduct.get(i));
						}else{
							index = i;
						}
					}
				}
			};
		};

		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 60 * 1000;
		timer.scheduleAtFixedRate(timrTask, delay, intevalPeriod);
	}





}
