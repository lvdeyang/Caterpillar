package com.guolaiwan.app.web.business.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.aoyou.AoYouV1Service;
import com.guolaiwan.app.aoyou.bo.AoYouOrder;
import com.guolaiwan.app.aoyou.bo.AoYouOrder.AoYouOrderDetail;
import com.guolaiwan.app.aoyou.bo.AoYouOrder.BookDetail;
import com.guolaiwan.app.aoyou.util.AoyouIDUtil;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.qimingxin.TravelService;
import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;

import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.AoYouOrderDao;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.PictureDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserOnedayBuyDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.AoYouOrderPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MessagePO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;

import com.guolaiwan.bussiness.nanshan.dao.MessageMiddleClientDao;
import com.guolaiwan.bussiness.nanshan.po.MessageMiddleClientPO;

import cn.hutool.json.JSONUtil;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

@RestController
@RequestMapping("/product/package")
public class ProductPackageController extends BaseController {

	@Autowired
	private ProductDAO productDao;

	@Autowired
	private MerchantDAO merchantDao;

	@Autowired
	private ActivityRelDAO conn_acre;

	@Autowired
	private OrderInfoDAO conn_orif;

	@Autowired
	private CommentDAO conn_comm;

	@Autowired
	private MessageDAO conn_message;

	@Autowired
	private UserInfoDAO conn_user;

	@Autowired
	private ProductComboDAO conn_combo;

	@Autowired
	private UserOnedayBuyDAO conn_userone;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private PictureDAO conn_picture;

	@Autowired
	private MessageMiddleClientDao conn_mesMidleClien;
	
	@Autowired
	private AoYouOrderDao aoYouOrderDao;

	/**
	 * 首页跳转购票页
	 */
	@RequestMapping(value = "/purchase/jump")
	public ModelAndView purchaseJump(HttpServletRequest request) {
		String merchanId = request.getParameter("merchantId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productMerchantID", merchanId);
		return new ModelAndView("mobile/business/purchase", map);
	}

	// 查询商品信息
	@RequestMapping(value = "/list")
	public Map<String, Object> findPackageName(HttpServletRequest request) throws NumberFormatException, Exception {
		// 返回封装数据 商品价格 商品封装
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> pro_pric = new ArrayList<Object>();
		List<ProductVO> pro_list = new ArrayList<ProductVO>();

		// 获取merchantId
		String merhcantId = request.getParameter("merchantId");
		// 分页条件封装
		String pageNum = request.getParameter("pageNum");
		int pageSize = 5;
		Map<String, Object> mapp = new HashMap<String, Object>();
		mapp.put("productMerchantID", Long.parseLong(merhcantId));
		mapp.put("productAuditstatus", ShopAuditStateType.T);
		mapp.put("productIsShow", 1);
		// 过滤 不符合日期及审核未通过的商品
		long nowDate = new Date().getTime();
		List<ProductPO> productPOs = productDao.findByPageC(mapp, Integer.valueOf(pageNum), pageSize);
		// List<ProductPO> productPOs = new ArrayList<ProductPO>();
		/*
		 * for(int i= 0 ;i<temproductPOs.size();i++){ //获取审核状态
		 * ShopAuditStateType state =
		 * temproductPOs.get(i).getProductAuditstatus(); int isShow =
		 * temproductPOs.get(i).getProductIsShow(); if( state !=
		 * ShopAuditStateType.T || isShow != 1){ continue; }
		 * productPOs.add(temproductPOs.get(i)); }
		 */
		// 分页获取所有商品
		List<ProductVO> pro_vo = new ProductVO().getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
		// 判断活动是否过期 状态
		boolean is_overtime;
		// 获取活动商品id
		Map<Long, ActivityRelPO> activ_id = new HashMap<Long, ActivityRelPO>();
		List<ActivityRelPO> activ_po = conn_acre.findAll();
		for (ActivityRelPO po : activ_po) {
			if (nowDate > po.getBeginDate().getTime() && nowDate < po.getEndDate().getTime()) {
				is_overtime = true;
			} else {
				is_overtime = false;
				continue;
			}
			if (is_overtime) {
				if (po.getProductStock() > 0) {
					activ_id.put(po.getProductId(), po);
				}
			}
		}
		DecimalFormat def = new DecimalFormat("0.00");
		// 评分集合
		List<String> gradeList = new ArrayList<String>();
		// 销售量集合
		List<Integer> marketList = new ArrayList<Integer>();
		// 判断商品是否为活动商品
		boolean isGrade = false;
		Set<Long> pro_id = activ_id.keySet();
		for (ProductVO pro : pro_vo) {
			// 活动票
			if (pro_id.contains(pro.getId())) {
				pro.setProductName(pro.getProductName() + ",act");
				pro_pric.add(def.format(Double.parseDouble(activ_id.get(pro.getId()).getPrice() + "") / 100));
				pro_list.add(pro);
				isGrade = true;
			} else {
				// 普通票
				if (pro.getProductStock() > 0) {
					pro.setProductName(pro.getProductName() + ",comm");
					pro_pric.add(pro.getProductPrice());
					pro_list.add(pro);
					isGrade = true;
				}
			}
			if (isGrade) {
				// 评分
				double grade1 = 4.6;
				double grade2 = 0.0;
				// 获取该商品的所有订单
				DecimalFormat defs = new DecimalFormat("0.0");
				int count = conn_orif.countByField("productId", pro.getId());
				int gradeSegment = count % 3;
				switch (gradeSegment) {
				case 0:
					grade2 = 0.1;
					break;
				case 1:
					grade2 = 0.2;
					break;
				case 2:
					grade2 = 0.3;
					break;
				default:
					break;
				}
				String grade = defs.format(grade1 + grade2);
				marketList.add(count);
				gradeList.add(grade);
				map.put("marketList", marketList);
				map.put("gradeList", gradeList);
				isGrade = false;
			}
		}
		map.put("productPOs", pro_list);
		map.put("strArryList", pro_pric);
		return map;
	}

	private double rad(double d) {
		return d * Math.PI / 180.0;
	}

	private double EARTH_RADIUS = 6378.137;

	/**
	 * 通过经纬度获取距离(单位：千米)
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return 距离
	 **/
	public double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		DecimalFormat dlf = new DecimalFormat("0.00");
		double ss = Double.parseDouble(dlf.format(s));

		return ss;
	}

	/**
	 * 商家 信息查询
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/merInfo", method = RequestMethod.POST)
	public Map<String, Object> getMerInfo(HttpServletRequest request, Double latitude, Double longitude)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String merchantId = request.getParameter("merchantId");
		// 获取商户信息
		List<MerchantPO> merchantPOs = merchantDao.findByField("id", Long.parseLong(merchantId));
		List<MerchantVO> mer_vo = new MerchantVO().getConverter(MerchantVO.class).convert(merchantPOs,
				MerchantVO.class);

		List<ProductPO> productPOs = productDao.findByField("productMerchantID", Long.parseLong(merchantId));
		List<ProductVO> pr_vo = new ProductVO().getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
		// 判断商品是否为购票产品
		for (int i = 0; i < pr_vo.size(); i++) {
			if ("0001".equals(pr_vo.get(i).getProductModularCode())) {
				continue;
			} else {
				pr_vo.remove(i);
			}
		}
		// 评分
		double grade1 = 4.6;
		double grade2 = 0.0;
		// 获取该商户的所有商品
		DecimalFormat defs = new DecimalFormat("0.0");
		int gradeSegment = productPOs.size() % 3;
		switch (gradeSegment) {
		case 0:
			grade2 = 0.1;
			break;
		case 1:
			grade2 = 0.2;
			break;
		case 2:
			grade2 = 0.3;
			break;
		default:
			break;
		}
		String grade = defs.format(grade1 + grade2);
		// 随机好评率
		int feedback = 94;
		Random random = new Random();
		while (feedback < 95) {
			feedback = random.nextInt(100);
		}
		map.put("distance", getDistance(Double.parseDouble(merchantPOs.get(0).getShopLatitude()),
				Double.parseDouble(merchantPOs.get(0).getShopLongitude()), latitude, longitude));
		/*
		 * map.put("beginTime", merchantPOs.get(0).getBeginTimeDate().split(" "
		 * )[1]); map.put("endTime", merchantPOs.get(0).getEndTimeDate().split(
		 * " ")[1]);
		 */
		map.put("grade", grade);
		map.put("feedback", feedback);
		map.put("merpos", mer_vo.get(0));
		map.put("prosinfos", pr_vo);
		return map;
	}

	/**
	 * 跳转购票详情页面
	 */
	@RequestMapping(value = "/commodity/jump")
	public ModelAndView commodityJump(HttpServletRequest request, long choice) {
		String merchantId = request.getParameter("merchantId");
		String proId = request.getParameter("proId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchantId", merchantId);
		map.put("proId", proId);
		if (choice == 0) {
			return new ModelAndView("mobile/business/commodity", map);
		} else {
			return new ModelAndView("mobile/business/activityPurchase", map);
		}
	}

	/**
	 * 单个商品详情
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/commodity/info", method = RequestMethod.GET)
	public Map<String, Object> getCommodityInfo(Long proId, Long merchantId, Long choice) throws Exception {
		// 封装前台数据
		Map<String, Object> map = new HashMap<String, Object>();
		List<MerchantPO> mer_po = new ArrayList<MerchantPO>();
		List<String> priceList = new ArrayList<String>();
		List<Integer> oderNumList = new ArrayList<Integer>();
		// 商户信息 商品信息 订单数量
		mer_po.add(merchantDao.get(merchantId));
		List<MerchantVO> mer_vo = new MerchantVO().getConverter(MerchantVO.class).convert(mer_po, MerchantVO.class);
		// 价格处理
		DecimalFormat def = new DecimalFormat("0.00");
		// 票种类型
		List<ProductComboPO> pComboPOs = conn_combo.findByField("productId", proId);
		for (ProductComboPO po : pComboPOs) {
			// 套餐票种的价格
			String price = def.format(Double.parseDouble(po.getComboprice() + "") / 100);
			priceList.add(price);
			// 套餐票种的订单数量
			String[] fields = { "productId", "comboId" };
			Long[] values = { proId, po.getId() };
			oderNumList.add(conn_orif.countByFields(fields, values));

		}
		// 票的订单数量
		int ticketOrderNumber = conn_orif.countByField("productId", proId);
		map.put("ticketOrderNumber", ticketOrderNumber);
		// 普通票
		if (choice == 0) {
			List<ProductVO> pr_vo = new ProductVO().getConverter(ProductVO.class)
					.convert(productDao.findByField("id", proId), ProductVO.class);
			// 开放时间
			String[] beginDateStr = pr_vo.get(0).getProductBeginDate().split(" ");
			String[] endDateStr = pr_vo.get(0).getProductEnddate().split(" ");
			map.put("beginDate", beginDateStr[0]);
			map.put("endDate", endDateStr[0]);
			map.put("proinfo", pr_vo);
		}
		// 活动票
		if (choice == 1) {
			List<ActivityRelVO> act_vo = new ActivityRelVO().getConverter(ActivityRelVO.class)
					.convert(conn_acre.findByField("productId", proId), ActivityRelVO.class);
			// 开放时间
			String beginDateStr = act_vo.get(0).getBeginTime();
			String endDateStr = act_vo.get(0).getEndTime();
			map.put("beginDate", beginDateStr);
			map.put("endDate", endDateStr);
			map.put("proinfo", act_vo);
		}
		// 评分
		double grade1 = 4.6;
		double grade2 = 0.0;
		// 获取该商品的所有订单
		DecimalFormat defs = new DecimalFormat("0.0");
		int count = conn_orif.countByField("productId", proId);
		int gradeSegment = count % 3;
		switch (gradeSegment) {
		case 0:
			grade2 = 0.1;
			break;
		case 1:
			grade2 = 0.2;
			break;
		case 2:
			grade2 = 0.3;
			break;
		default:
			break;
		}
		String grade = defs.format(grade1 + grade2);
		map.put("grade", grade);
		// 随机好评率
		int feedback = 94;
		Random random = new Random();
		while (feedback < 95) {
			feedback = random.nextInt(100);
		}
		map.put("feedback", feedback);

		map.put("pComboPOs", pComboPOs);
		map.put("priceList", priceList);
		map.put("merinfo", mer_vo);
		map.put("oderNumList", oderNumList);
		return map;

	}

	/**
	 * 商品评论
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/comment")
	public Map<String, Object> getProComment(Long proId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 该商品的所有评论
		List<CommentVO> comm_vo = new CommentVO().getConverter(CommentVO.class)
				.convert(conn_comm.findByField("proId", proId), CommentVO.class);
		map.put("comm", comm_vo);
		return map;
	}

	/**
	 * 跳转订单页面
	 */
	@Autowired
	ProductDAO conn_product;
	
	@RequestMapping(value = "/payment/jump")
	public ModelAndView paymentJump(HttpServletRequest request, long isCombo) {
		Map<String, Object> map = new HashMap<String, Object>();
		String merchantId = request.getParameter("merchantId");
		String proId = request.getParameter("proId");
		
		ProductPO productPO=conn_product.get(Long.parseLong(proId));
		
		String choice = request.getParameter("choice");
		// 是否为套餐
		if (isCombo == 1) {
			String comboId = request.getParameter("comboId");
			map.put("comboId", comboId);
		} else {
			// ordersTicket.jsp 支付报错
			map.put("comboId", -1);
		}
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("userId");
		map.put("userId", userId);
		map.put("merchantId", merchantId);
		map.put("proId", proId);
		map.put("choice", choice);
		map.put("isCombo", isCombo);
		map.put("resNum", productPO.getProductRestrictNumber());

		return new ModelAndView("mobile/business/ordersTicket", map);
	}

	/**
	 * 查询购票商品价格
	 * 
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/orders/info", method = RequestMethod.GET)
	public Map<String, Object> getOrdersInfo(HttpServletRequest request, long isCombo)
			throws NumberFormatException, Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		// 获取票的价格
		String ticketPrice = null;
		if (isCombo == 1) {
			String comboId = request.getParameter("comboId");
			DecimalFormat dlf = new DecimalFormat("0.00");
			ProductComboPO ComboPOs = conn_combo.get(Long.parseLong(comboId));
			// 套餐票的价格
			ticketPrice = dlf.format(Double.parseDouble(ComboPOs.getComboprice() + "") / 100);
			ProductPO productPO = productDao.get(ComboPOs.getProductId());
			map.put("ifFace", productPO.getIfFace());
		} else {
			String choice = request.getParameter("choice");
			String proId = request.getParameter("proId");
			ProductPO productPO = productDao.get(Long.parseLong(proId));
			map.put("ifFace", productPO.getIfFace());
			// 普通票价格
			if ("0".equals(choice)) {
				List<ProductVO> productVO = new ProductVO().getConverter(ProductVO.class)
						.convert(productDao.findByField("id", Long.parseLong(proId)), ProductVO.class);
				ticketPrice = productVO.get(0).getProductPrice();
			}
			// 活动票价格
			else {
				List<ActivityRelVO> activityRelVO = new ActivityRelVO().getConverter(ActivityRelVO.class)
						.convert(conn_acre.findByField("id", Long.parseLong(proId)), ActivityRelVO.class);
				ticketPrice = activityRelVO.get(0).getPrice();
			}
		}
		map.put("ticketPrice", ticketPrice);

		return map;
	}

	/**
	 * 购票人信息
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/add/info", method = RequestMethod.POST)
	public String saveTicketInfo(HttpServletRequest request) throws Exception {
		// 更新购票人id
		String state = request.getParameter("state");
		// 保存
		if ("0".equals(state)) {
			String base = request.getParameter("facedate");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String idcard = request.getParameter("idcard");
			HttpSession session = request.getSession();
			long userId = (long) session.getAttribute("userId");
			MessagePO mes = new MessagePO();
			Map<String, String> map = facePicture();
			if (map.get("folderUrl") != null) {
				PictureConvertor.GenerateImg(base, map.get("folderUrl"));
				mes.setBase(map.get("folderUrl"));
				mes.setFacePicWebUrl(map.get("webUrl"));
			}
			mes.setUserId(userId);
			mes.setName(name);
			mes.setNumber(idcard);
			mes.setState("0");
			mes.setPhone(phone);
			conn_message.save(mes);
		} else {
			// 更新
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String idcard = request.getParameter("idcard");
			String base = request.getParameter("facedate");
			MessagePO mes = conn_message.get(Long.parseLong(state));
			PictureConvertor.GenerateImg(base, mes.getBase());
			mes.setName(name);
			mes.setNumber(idcard);
			mes.setPhone(phone);
			conn_message.update(mes);
		}

		return "success";
	}

	// 生成图片地址
	public Map<String, String> facePicture() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 创建日期文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String folderName = sdf.format(d); // 文件名
		String path = conn_sysConfig.getSysConfig().getFolderUrl() + folderName;
		// 文件名
		String newName = UUID.randomUUID().toString() + ".jpg";

		File folder = new File(path);
		if (folder.exists() == false) { // 如果路径不存在
			folder.mkdir();
		}
		// 上传
		File newFile = new File(path + "/" + newName);
		String folderUrl = conn_sysConfig.getSysConfig().getFolderUrl() + folderName + "/" + newName;

		// webur地址
		String webUrl = conn_sysConfig.getSysConfig().getWebUrl() + folderName + "/" + newName;

		map.put("folderUrl", folderUrl);
		map.put("webUrl", webUrl);
		return map;
	}

	/**
	 * 实时查询数据库数量
	 * 
	 */
	@RequestMapping(value = "/commonticket")
	public Map<String, Object> getCommon(long proId, long choice, long ticketnumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 普通商品
		if (choice == 0) {
			ProductPO pro_po = productDao.get(proId);
			// 查询库存
			if (pro_po.getProductStock() > 0) {
				if (pro_po.getProductStock() < ticketnumber) {
					map.put("state", 1);// 库存比买的数量少
					map.put("Stock", pro_po.getProductStock());
				} else {
					// 判断是否存在最低限购数量
					if (pro_po.getProductRestrictNumber() != 0) {
						if (ticketnumber >= pro_po.getProductRestrictNumber()) {
							map.put("state", 3);// 购买成功
						} else {
							map.put("state", 2);// 买的数量低于最低限购数量
							map.put("limitNum", pro_po.getProductRestrictNumber());
						}
					} else {
						map.put("state", 3);// 购买成功
					}

				}
			} else {
				map.put("state", 0); // 库存为0的状态
			}
			// 活动商品
		} else {
			// 活动商品是否当天限购
			ActivityRelPO activityRelPO = conn_acre.get(proId);
			// 查询库存
			if (activityRelPO.getProductStock() > 0) {
				// 是否一日一个
				if (1 == activityRelPO.getOnePerDay()) {
					if (ticketnumber != 1) {
						map.put("state", 1);// 超出每日限购
					}
				} else {
					if (ticketnumber > activityRelPO.getProductStock()) {
						map.put("state", 2);// 购买数量大于库存量
						map.put("Stock", activityRelPO.getProductStock());
					} else {
						// 该商品是否限购
						long productId = activityRelPO.getProductId();
						ProductPO productPO = productDao.get(productId);
						if (productPO.getProductRestrictNumber() != 0) {
							if (ticketnumber >= productPO.getProductRestrictNumber()) {
								map.put("state", 4);// 购买成功
							} else {
								map.put("state", 3);// 买的数量少于最低限购数量
								map.put("limitNum", productPO.getProductRestrictNumber());
							}
						} else {
							map.put("state", 4);// 购买成功
						}
					}
				}
			} else {
				System.out.println("zhuangtaia 0");
				map.put("state", 0); // 库存为 0
			}
		}
		return map;
	}

	// Json 转 字符串
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

	/**
	 * 展示用户信息
	 */
	@RequestMapping(value = "/user/list")
	public Map<String, Object> getMessageInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("userId");
		try {
			List<MessagePO> mes_po = conn_message.findByField("userId", userId);
			map.put("message", mes_po);
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}

	/**
	 * 查询要修改用户信息
	 * 
	 */
	@RequestMapping(value = "/update/message")
	public Map<String, Object> updateMessage(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		MessagePO mes_po = conn_message.get(id);
		map.put("mes", mes_po);
		return map;
	}

	/**
	 * 商品时间判断
	 * 
	 */

	@RequestMapping(value = "/productDate", method = RequestMethod.POST)
	public Map<String, Object> getProdictDate(long id, long choice, String buyDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 判断票的类型
			// 普通票
			if (choice == 0) {
				ProductPO productPO = productDao.get(id);
				long beginTime = productPO.getProductBeginDate().getTime();
				long endTime = productPO.getProductEnddate().getTime();
				long buyTime = sdf.parse(buyDate).getTime();
				if (buyTime > beginTime && buyTime < endTime) {
					map.put("result", 1);// 日期符合
				} else {
					map.put("result", 0);// 日期不符合
				}
			}
			// 活动票
			else {
				ActivityRelPO aRelPO = conn_acre.get(id);
				long beginTime = aRelPO.getBeginDate().getTime();
				long endTime = aRelPO.getEndDate().getTime();
				long buyTime = sdf.parse(buyDate).getTime();
				if (buyTime > beginTime && buyTime < endTime) {
					map.put("result", 1);// 日期符合
				} else {
					map.put("result", 0);// 日期不符合
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}

	// 添加对应信息
	@RequestMapping(value = "/addMessage", method = RequestMethod.POST)
	public String addMessageOrder(HttpServletRequest request) {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String oderId = pageObject.getString("oderId");
		String clientLists = pageObject.getString("clientList");
		JSONArray clientList = pageObject.parseArray(clientLists);
		String proId = pageObject.getString("proId");
		String userId = pageObject.getString("userId");
		String merchantId = pageObject.getString("merchantId");
		// 遍历保存用户信息
		for (int i = 0; i < clientList.size(); i++) {
			if (clientList.get(i) != null) {
				long clientMessage = clientList.getLong(i);
				MessageMiddleClientPO mesMidClien = new MessageMiddleClientPO();
				mesMidClien.setMerchantId(Long.parseLong(merchantId));
				mesMidClien.setMessageId(clientMessage);
				mesMidClien.setOrderId(Long.parseLong(oderId));
				mesMidClien.setUserId(Long.parseLong(userId));
				mesMidClien.setProId(Long.parseLong(proId));
				conn_mesMidleClien.save(mesMidClien);
			}

		}
		return "success";
	}

	/**
	 * 删除用户信息
	 */
	@RequestMapping(value = "/deleteClientMessage")
	public String deleteClientMessage(long merssageId) {

		conn_message.delete(merssageId);

		return "success";
	}

	public static void main(String[] args) {
		long ll = 1;
		double dd = ll;

		System.out.println(dd / 100);
	}

	/**
	 * 订单：立即支付
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	public Map<String, Object> addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String id = pageObject.getString("id");
		String num = pageObject.getString("productNum");
		Long userId = Long.parseLong(pageObject.getString("userId"));
		String paytype = pageObject.getString("paytype");
		String isCombo = pageObject.getString("isCombo");
		String choice = pageObject.getString("choice");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date bookDate = sf.parse(pageObject.getString("bookDate"));

		// 中青旅==========================================================================================================
		//世园会
		if(AoyouIDUtil.isSyhID(id)){
			//根据产品id查产品信息，为订单号做准备
			ProductPO productPO = productDao.get(Long.parseLong(id));
			//根据商户id查商户信息，为订单号做准备
			MerchantPO merchant = merchantDao.get(productPO.getProductMerchantID());
			//生成订单号
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String orderNO = merchant.getId() + productPO.getProductModularCode() + df.format(new Date()) + userId;
			//取得勾选的购票人信息
			JSONArray split = (JSONArray) pageObject.get("messageUserIds");
	        Set<Long> idSet = new HashSet<>();
	        for (Object s : split) {
	            idSet.add(Long.valueOf((String) s));
	        }
	        List<MessagePO> messagePOList = conn_message.getAllByIds(idSet);
			//根据产品id查世园会票种id
			String syhID = AoyouIDUtil.getSyhID(id);
			//创建世园会票务订单start
			AoYouOrder aoYouOrder = new AoYouOrder();
			aoYouOrder.setTrade_no("glw-aoyou-" + orderNO);//过来玩提交世园会的订单
			aoYouOrder.setProd_id(Integer.parseInt(syhID));//世园会票种id
			aoYouOrder.setMobile_no(messagePOList.get(0).getPhone());//购买人的手机号
			aoYouOrder.setProd_count(Integer.parseInt(num));//购买票数
			List aoYouOrderDetailList = new ArrayList();
	        for (MessagePO messagePO : messagePOList) {
	        	AoYouOrderDetail aoYouOrderDetail = new AoYouOrderDetail();
				aoYouOrderDetail.setTicket_count(1);//单人购买票数限制1张
				aoYouOrderDetail.setHolder_name(messagePO.getName());//购买人名字
				aoYouOrderDetail.setHolder_mobile(messagePO.getPhone());//购买人手机号
				aoYouOrderDetail.setId_type(1);//证件类型（身份证传1）
				aoYouOrderDetail.setId_no(messagePO.getNumber());//购买人身份证号码
				aoYouOrderDetailList.add(aoYouOrderDetail);
			}
			aoYouOrder.setOrder_detail(aoYouOrderDetailList);
			BookDetail bookDetail = new BookDetail();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String bDate = sdf.format(bookDate);
			//根据预约日期取得预约信息中的场馆编号
			JSONObject syhInfo = AoYouV1Service.bookInfoQuery(Integer.parseInt(syhID),bDate,bDate);
			if(!"00000".equals(syhInfo.get("errcode"))){
				return ERROR(syhInfo.get("errmsg").toString());
			}
			JSONArray ja = JSON.parseObject(syhInfo.get("errdata").toString()).getJSONArray("bookinfolist");
			String venue_code = ja.getJSONObject(0).getString("venue_code");
			bookDetail.setBook_date(bDate);//预约入园日期
			//套餐票是下午，普通票是上午
			if ("1".equals(isCombo)) {
				bookDetail.setBook_period("2");//TODO 入院时间段 1-上午、2-下午、3-晚上
			} else {
				bookDetail.setBook_period("1");
			}
			bookDetail.setVenue_code(venue_code);//场馆编号
			bookDetail.setBook_count(Integer.parseInt(num));//预约人数要与购买票数一致
			aoYouOrder.setBook_detail(bookDetail);
			JSONObject aoyou = AoYouV1Service.createOrder(aoYouOrder);
			System.out.println("创建世园会票务订单返回结果:" + aoyou);
			if(!"00000".equals(aoyou.get("errcode"))){
				if(!"".equals(aoyou.getString("errdata"))){
					JSONObject resultmsg = JSON.parseObject(aoyou.get("errdata").toString());
					return ERROR(resultmsg.getString("resultmsg"));
				}
				return ERROR(aoyou.get("errmsg").toString());
			} else {
				AoYouOrderPO aoYouOrderPO = new AoYouOrderPO();
				JSONObject errdata = JSON.parseObject(aoyou.get("errdata").toString());
				aoYouOrderPO.setGlwOrderNO(orderNO);
				aoYouOrderPO.setTrade_no(errdata.getString("trade_no"));
				aoYouOrderPO.setSaleorder_no(errdata.getString("saleorder_no"));
				aoYouOrderPO.setOrderno(errdata.getString("orderno"));
				aoYouOrderPO.setMobile_no(errdata.getString("mobile_no"));
				aoYouOrderDao.saveOrUpdate(aoYouOrderPO);
			}
			//创建世园会票务订单end
		}
		
		//冰雪
		if(AoyouIDUtil.isBxID(id)){
			String bxID = AoyouIDUtil.getBxID(id);
		}
		// 中青旅==========================================================================================================
						
		OrderInfoPO order = new OrderInfoPO();

		ProductPO productPO = null;
		// 支付的金额
		Long productprice = null;
		// 获取 productId
		if ("0".equals(choice)) {
			// 库存进行修改
			productPO = productDao.get(Long.parseLong(id));
			// 是否限购
			if (1 == productPO.getProductLimitType()) {
				// 限购
				productPO.setProductLimitNum(productPO.getProductLimitNum() - Long.parseLong(num));
				productPO.setProductStock(productPO.getProductStock() - Long.parseLong(num));
			} else {
				productPO.setProductStock(productPO.getProductStock() - Long.parseLong(num));
			}

			productDao.update(productPO);
		} else {
			ActivityRelPO aRelPO = conn_acre.get(Long.parseLong(id));
			productPO = productDao.get(aRelPO.getProductId());

			// 活动库存修改
			aRelPO.setProductStock(aRelPO.getProductStock() - Long.parseLong(num));

			conn_acre.update(aRelPO);

		}
		// 获取商品信息
		// ProductPO productPO = productDao.get(Long.parseLong(productId));

		// 套餐票 价格信息
		if ("1".equals(isCombo)) {
			// 订单信息
			String comboId = pageObject.getString("ComboId");
			order.setComboId(Long.parseLong(comboId));
			// 查询价格
			ProductComboPO pComboPO = conn_combo.get(Long.parseLong(comboId));
			productprice = pComboPO.getComboprice();
		} else {
			// 普通票价格
			if ("0".equals(choice)) {
				productprice = productPO.getProductPrice();
			}
			// 活动票价格
			else {
				ActivityRelPO activityRelPO = conn_acre.get(Long.parseLong(id));
				productprice = activityRelPO.getPrice();

				UserOneDayBuyPO buyPO = new UserOneDayBuyPO();
				buyPO.setUpdateTime(new Date());
				buyPO.setUserId(userId);
				buyPO.setProId(Long.parseLong(id));

				conn_userone.save(buyPO);
				order.setActivityId(Long.parseLong(id));
			}
		}
		if (num == null) {
			num = "1";
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		// 支付金额
		long payMoney = Integer.parseInt(num) * productprice;
		// 订单总金额
		long orderAllMoney = payMoney;

		// 获取商家
		MerchantPO merchant = merchantDao.get(productPO.getProductMerchantID());
		UserInfoPO user = conn_user.get(userId);

		String orderStartDate = pageObject.getString("startDate");
		if (orderStartDate != null && orderStartDate != "" && orderStartDate.length() != 0) {
			order.setOrderBookDate(DateUtil.parse(orderStartDate, DateUtil.defaultDatePattern));
		}

		// 会员ID
		order.setUserId(userId);
		if (user.getUserPhone() != null) {
			// 会员手机号
			order.setUserTel(user.getUserPhone());
			// 会员信息
			// 会员坐标经度
			// 会员坐标维度
			// 提成金额比例
		}
		if (user.getUserNickname() != null) {
			order.setUserName(user.getUserNickname());
		}

		// 订单号（商家id+板块Code+时间戳+用户ID）
		String orderNO = merchant.getId() + productPO.getProductModularCode() + df.format(date) + userId;
		order.setOrderNO(orderNO);
		// 验单码

		// 下单时间
		order.setCreateDate(date);
		order.setUpdateTime(date);
		// 验单时间

		// 供应商ID
		order.setShopId(merchant.getId());
		// 供应商名称
		order.setShopName(merchant.getShopName());
		// 站点ID
		// 站点名称
		// 商品ID
		order.setProductId(productPO.getId());
		// 商品图片
		order.setProductPic(productPO.getProductShowPic());
		// 商品名称
		order.setProductName(productPO.getProductName());
		// 商品数量
		order.setProductNum(Long.parseLong(num));

		order.setComId(1L);
		// 商品单价
		order.setProductPrice(productprice);
		// 所属板块DI
		order.setBkCode(productPO.getProductModularCode());
		// 所属板块名称
		order.setBkName(productPO.getProductModularCodeName());

		// 套餐ID
		// 套餐名称

		long proportion = productPO.getProductCommissionPrice();
		if (productPO.getProductCommissionCode() == 1) {
			order.setProportion(proportion);
		}
		// 提成方式（0：佣金1：比例）
		order.setRoyaltyName(productPO.getProductCommissionCode());
		// 积分数
		long integralNum = Integer.parseInt(num) * productPO.getProductntegral();
		order.setIntegralNum(integralNum);
		// 订单佣金金额(分)
		long proportionMoney;
		if (productPO.getProductCommissionCode() == 1) {
			proportionMoney = Integer.parseInt(num) * productPO.getProductPrice() * proportion / 100;
		} else {
			proportionMoney = Integer.parseInt(num) * proportion;
		}
		order.setProportionMoney(proportionMoney);
		// 支付金额
		order.setPayMoney(payMoney);
		// 订单总金额
		order.setOrderAllMoney(orderAllMoney);
		// 订单说明
		if (request.getParameter("orderRemark") != null && request.getParameter("orderRemark").length() > 0) {
			order.setOrderRemark(request.getParameter("orderRemark"));
		}
		// 订单状态
		order.setOrderState(OrderStateType.NOTPAY);
		// 订单支付类型 //ALIPAY WEICHAT
		order.setPayMode(PayType.fromString(paytype));
		// 有效期

		// 是否评价
		order.setCommentIs(0);
		// // 预订日期
		order.setOrderBookDate(bookDate);

		if (pageObject.getString("source") != null) {
			order.setSource(OrderSource.fromString(pageObject.getString("source")));
		} else {
			// 订单来源
			order.setSource(OrderSource.APP);
		}
		order.setOrderType(OrderType.MERCHANT);
		productPO.setProductSaleNum(productPO.getProductSaleNum() + 1);
		productPO.setProductShowNum(productPO.getProductShowNum() + 1);
		productDao.update(productPO);
		conn_orif.saveOrUpdate(order);
		// 南湖票
		long nhProductId = 2469;
		System.out.println("productId 南湖的******" + order.toString());
		if (order.getProductId() == nhProductId) {
			System.out.println("进入到南湖票下单------");
			// contactMobile 手机号 ； contactName 姓名 ；otaOrderId 我方订单号；productId
			// 产品Id 南湖票Id；quantity 数量 ;useDate 使用日期 ；salePrice 销售价格
			long ll = order.getPayMoney();
			double price = ll;

			MessagePO messagePO = conn_message.getByUserId(order.getUserId());
			Integer numInteger = new Long(order.getProductNum()).intValue();
			SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("order对象" + order);
			System.out.println("票务参数" + messagePO.getPhone() + "###" + order.getUserName() + "###" + order.getOrderNO()
					+ "###" + "723026" + "###" + numInteger + "###" + myFmt.format(order.getOrderBookDate()) + "###"
					+ price / 100);
			String result = TravelService.createOrder(messagePO.getPhone(), order.getUserName(), order.getOrderNO(),
					"723026", numInteger, myFmt.format(order.getOrderBookDate()), price / 100);
			System.out.println("南湖票下单返回结果" + result);
			Map<String, Object> resultMap = JSONUtil.toBean(JSONUtil.parse(result), Map.class, false);
			if (resultMap.get("rspCode").equals("200")) {
				// 用订单拓展字段作为票务订单号
				order.setTargetId(resultMap.get("orderId").toString());
			}
		}
		long PayMoney = order.getPayMoney();
		/* String tradeNum=order.getOrderNO(); */
		String orderIdStr = String.valueOf(order.getId());
		// 调微信和支付宝
		if (paytype.equals("WEICHAT")) { // 微信
			Map<String, String> weichatPay = weichatPay(PayMoney, orderIdStr);
			weichatPay.put("orderId", order.getId() + "");
			return success(weichatPay);
		} else if (paytype.equals("ALIPAY")) {
			String productnum = String.valueOf(order.getProductNum());
			String pname = order.getProductName();
			// String num,Long allMoney,String productName,String orderNo
			String sign = AliAppOrderInfo.getInstance().getSign(productnum, PayMoney, pname, orderIdStr);
			data.put("orderId", order.getId());
			data.put("orderInfo", sign);
			return success(data);
		} else {
			return ERROR("系统错误！");
		}
	}

	public Map<String, String> weichatPay(long PayMoney, String tradeNum) {
		Map<String, String> reqData = new HashMap<String, String>();
		Map<String, String> resData = new HashMap<String, String>();
		// try {
		// GuolaiwanWxPayApp wxPay = GuolaiwanWxPayApp
		// .getInstance("http://" + WXContants.Website +
		// "/website/wxreport/payreport");
		// reqData.put("total_fee", "" + PayMoney); // 总价
		// reqData.put("attach", "test"); // 订单的自定义数据
		// reqData.put("body", "guolaiwan-order"); // 内容
		// reqData.put("out_trade_no", tradeNum); // 订单号
		// reqData.put("spbill_create_ip", "192.165.56.64");
		// reqData.put("trade_type", "APP"); // 支付类型APP
		// reqData.put("device_info", "WEB");
		// resData = wxPay.pay(reqData);
		// System.out.println("7" + resData);
		// String noncestr = resData.get("nonce_str");
		// String partnerid = resData.get("mch_id");
		// String prepay_id = resData.get("prepay_id");
		//
		// resData.put("noncestr", noncestr);
		// resData.put("package", "Sign=WXPay");
		// resData.put("partnerid", partnerid);
		// resData.put("prepayid", prepay_id);
		// resData.put("timestamp", "" + new Date().getTime() / 1000);
		// resData.remove("nonce_str");
		// resData.remove("return_msg");
		// resData.remove("mch_id");
		// resData.remove("prepay_id");
		// resData.remove("device_info");
		// resData.remove("trade_type");
		// resData.remove("result_code");
		// resData.remove("return_code");
		// resData.remove("sign");
		// System.out.println(resData);
		// resData.put("sign", WXPayUtil.generateSignature(resData,
		// GuolaiwanWxPayApp.AppKey, SignType.MD5));
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println(e.getMessage());
		// }
		System.out.println(resData.toString());
		return resData;

	}

	// 修改身份证采集表支付状态
	@ResponseBody
	@RequestMapping(value = "/updatemessage", method = RequestMethod.POST)
	public Map<String, String> updatemessage(String oderId) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		try {
			List<MessageMiddleClientPO> messagepo = conn_mesMidleClien.getByOderId(Long.parseLong(oderId));
			for (MessageMiddleClientPO po : messagepo) {
				po.setPayState("1");
				conn_mesMidleClien.update(po);
			}
			hashMap.put("msg", "0");
			return hashMap;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			hashMap.put("msg", "1");
			return hashMap;
		}

	}
}
