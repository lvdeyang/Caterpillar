package com.guolaiwan.app.interfac.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.mp4parser.Container;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.builder.DefaultMp4Builder;
import org.mp4parser.muxer.container.mp4.MovieCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.guolaiwan.app.interfac.alipay.AliAppOrderInfo;
import com.guolaiwan.app.interfac.util.FilterSensitive;
import com.guolaiwan.app.interfac.util.KdniaoTrackQueryAPI;
import com.guolaiwan.app.web.admin.vo.ActiveBundleVO;
import com.guolaiwan.app.web.admin.vo.ActivityRelVO;
import com.guolaiwan.app.web.admin.vo.ActivityVO;
import com.guolaiwan.app.web.admin.vo.CarouselVO;
import com.guolaiwan.app.web.admin.vo.ChildPicAndContentVO;
import com.guolaiwan.app.web.admin.vo.ChildProductVO;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.CompanyVO;
import com.guolaiwan.app.web.admin.vo.DistributorVO;
import com.guolaiwan.app.web.admin.vo.LanVO;
import com.guolaiwan.app.web.admin.vo.LiveAdvertisementVO;
import com.guolaiwan.app.web.admin.vo.LiveGiftVO;
import com.guolaiwan.app.web.admin.vo.LiveProductVO;
import com.guolaiwan.app.web.admin.vo.LiveRebroadcastVO;
import com.guolaiwan.app.web.admin.vo.LiveRecordVO;
import com.guolaiwan.app.web.admin.vo.LiveVO;
import com.guolaiwan.app.web.admin.vo.LogisticVO;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ModularVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.PictureVO;
import com.guolaiwan.app.web.admin.vo.ProLatitudeLongitudeVO;
import com.guolaiwan.app.web.admin.vo.ProTourismPictureVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.admin.vo.ProfessionalLiveVO;
import com.guolaiwan.app.web.admin.vo.ShareVO;
import com.guolaiwan.app.web.admin.vo.SubLiveVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.admin.vo.VPCommentVO;
import com.guolaiwan.app.web.admin.vo.VideoPicVO;
import com.guolaiwan.app.web.website.alisms.controller.Alisms;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.app.web.website.user.exception.PasswordErrorException;
import com.guolaiwan.app.web.website.user.exception.UnkonwnUserException;
import com.guolaiwan.app.web.website.vo.AddressVO;
import com.guolaiwan.app.web.weixin.SendMsgUtil;
import com.guolaiwan.bussiness.admin.dao.ActivityBundleDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityDAO;
import com.guolaiwan.bussiness.admin.dao.ActivityRelDAO;
import com.guolaiwan.bussiness.admin.dao.AuctionDAO;
import com.guolaiwan.bussiness.admin.dao.CarouselDAO;
import com.guolaiwan.bussiness.admin.dao.ChildPicAndContentDAO;
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.dao.CollectionDAO;
import com.guolaiwan.bussiness.admin.dao.ColumnDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorDAO;
import com.guolaiwan.bussiness.admin.dao.DistributorProductDAO;
import com.guolaiwan.bussiness.admin.dao.ExpressDao;
import com.guolaiwan.bussiness.admin.dao.LanDAO;
import com.guolaiwan.bussiness.admin.dao.LiveAdvertisementDAO;
import com.guolaiwan.bussiness.admin.dao.LiveDAO;
import com.guolaiwan.bussiness.admin.dao.LiveGiftDAO;
import com.guolaiwan.bussiness.admin.dao.LiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.LiveProductDAO;
import com.guolaiwan.bussiness.admin.dao.LiveRebroadcastDAO;
import com.guolaiwan.bussiness.admin.dao.LogisticsDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantUserDao;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.OrderPeopleDao;
import com.guolaiwan.bussiness.admin.dao.PhoneCodeDAO;
import com.guolaiwan.bussiness.admin.dao.PicproRelationDAO;
import com.guolaiwan.bussiness.admin.dao.PictureDAO;
import com.guolaiwan.bussiness.admin.dao.ProLatitudeLongitudeDAO;
import com.guolaiwan.bussiness.admin.dao.ProTourismPictureDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveDirectorDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMatPlayVedioDAO;
import com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMessageDAO;
import com.guolaiwan.bussiness.admin.dao.RoomStatusDao;
import com.guolaiwan.bussiness.admin.dao.ShareDAO;
import com.guolaiwan.bussiness.admin.dao.SurpportBuyDao;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.TodayHotSearchDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.UserOnedayBuyDAO;
import com.guolaiwan.bussiness.admin.dao.VPCommentDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.dao.VideoPicDAO;
import com.guolaiwan.bussiness.admin.dao.live.LiveOrderDao;
import com.guolaiwan.bussiness.admin.dao.live.LiveRecordDao;
import com.guolaiwan.bussiness.admin.dao.live.SubLiveDao;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.enumeration.ActivityType;
import com.guolaiwan.bussiness.admin.enumeration.LiveProductType;
import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.enumeration.VideoPicType;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.AuctionPO;
import com.guolaiwan.bussiness.admin.po.CarouselPO;
import com.guolaiwan.bussiness.admin.po.ChildPicAndContentPO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.CollectionPO;
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.DistributorPO;
import com.guolaiwan.bussiness.admin.po.DistributorProductPO;
import com.guolaiwan.bussiness.admin.po.ExpressPO;
import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.LiveAdvertisementPO;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;
import com.guolaiwan.bussiness.admin.po.LiveRebroadcastPO;
import com.guolaiwan.bussiness.admin.po.LogisticsPo;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.OrderPeoplePo;
import com.guolaiwan.bussiness.admin.po.PhoneCodePO;
import com.guolaiwan.bussiness.admin.po.PicproRelationPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.ProLatitudeLongitudePO;
import com.guolaiwan.bussiness.admin.po.ProTourismPicturePO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.ProfessionalLiveDirectorPO;
import com.guolaiwan.bussiness.admin.po.ProfessionalLiveMatPlayVedioPO;
import com.guolaiwan.bussiness.admin.po.ProfessionalLiveMessagePO;
import com.guolaiwan.bussiness.admin.po.ProfessionalLivePO;
import com.guolaiwan.bussiness.admin.po.RoomStatusPO;
import com.guolaiwan.bussiness.admin.po.SharePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.TodayHotSearchPO;
import com.guolaiwan.bussiness.admin.po.TodayHotSearchsPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;
import com.guolaiwan.bussiness.admin.po.VPCommentPO;
import com.guolaiwan.bussiness.admin.po.VPRelPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;
import com.guolaiwan.bussiness.admin.po.live.LiveOrderPO;
import com.guolaiwan.bussiness.admin.po.live.LiveRecordPO;
import com.guolaiwan.bussiness.admin.po.live.SubLivePO;
import com.guolaiwan.bussiness.javacv.GuoliawanLiveServiceWrapper;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.commons.img.VerifyCodeUtils;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.html2text.ReduceHtml2Text;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;
import pub.caterpillar.weixin.constants.WXContants;
import pub.caterpillar.weixin.wxpay.GuolaiwanWxPayApp;
import pub.caterpillar.weixin.wxpay.WXPayConstants.SignType;
import pub.caterpillar.weixin.wxpay.WXPayUtil;

@Controller
@RequestMapping("/phoneApp")
public class PhoneController extends WebBaseControll {

	// 默认查询数量
	private final int pageNum = 1;
	private final int pageSizeL = 6;
	private final int pageSizeI = 8;
	private final int pageSize = 100;
	private final int recommendSize = 6;

	@Autowired
	private ModularDAO conn_modular;
	@Autowired
	private ModularClassDAO conn_modularClass;
	@Autowired
	private ProductDAO conn_product;
	@Autowired
	private SysConfigDAO conn_sysConfig;
	@Autowired
	private UserInfoDAO conn_user;
	@Autowired
	private OrderInfoDAO conn_order;
	@Autowired
	private AddressDAO conn_address;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	private ChildProductDAO conn_childProduct;
	@Autowired
	private PicproRelationDAO conn_picproRelation;
	@Autowired
	private PictureDAO conn_picture;
	@Autowired
	private LiveDAO conn_live;
	@Autowired
	private LiveProductDAO conn_liveProduct;
	@Autowired
	private LiveMessageDAO conn_liveMessage;
	@Autowired
	private AuctionDAO conn_auction;
	@Autowired
	private CompanyDAO conn_company;
	@Autowired
	private ActivityDAO conn_activity;
	@Autowired
	private ActivityRelDAO conn_activityRel;
	@Autowired
	private DistributorDAO conn_distributor;
	@Autowired
	private CommentDAO conn_comment;
	@Autowired
	private DistributorProductDAO conn_distributorProduct;
	@Autowired
	private PhoneCodeDAO conn_phoneCode;
	@Autowired
	private CollectionDAO conn_collection;
	@Autowired
	private VideoPicDAO conn_videoPic;
	@Autowired
	private VPRelDAO conn_vPRel;
	@Autowired
	private VPCommentDAO conn_vPComment;
	@Autowired
	private LiveOrderDao conn_liveOrder;
	@Autowired
	private SubLiveDao conn_subLive;
	@Autowired
	private ProfessionalLiveMessageDAO conn_professionalLiveMessageDao;
	@Autowired
	private ProfessionalLiveDirectorDAO conn_professionalLiveDirectorDao;
	@Autowired
	private ProfessionalLiveMatPlayVedioDAO conn_professionalLiveMatPlayVedioDao;
	@Autowired
	private ProfessionalLiveDAO conn_professionalLiveDao;
	@Autowired
	private LiveRecordDao conn_recordDao;
	@Autowired
	private LiveAdvertisementDAO conn_liveAdvertisementDao;
	@Autowired
	private ExpressDao expressdao;

	/**
	 * 首页搜索
	 * 
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Map<String, Object> search(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String comId = pageObject.getString("comId");
		String type = pageObject.getString("type");
		String name = pageObject.getString("name");
		Integer page = pageObject.getInteger("page");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");
		Long comIdL = null;
		if (comId == null || comId.length() == 0) {
			comIdL = 1L;
		} else {
			comIdL = Long.parseLong(comId);
		}

		switch (type) {
		case "MERCHANT":
			// 修改平谷搜索功能 4/21
			List<MerchantPO> merchants = conn_merchant.appfindByComNew(name, page, pageSize);

			List<MerchantVO> _merchants = MerchantVO.getConverter(MerchantVO.class).convert(merchants,
					MerchantVO.class);

			for (MerchantVO merchantVO : _merchants) {

				// 图片
				merchantVO.setShopHeading(sysConfig.getWebUrl() + merchantVO.getShopHeading());
				merchantVO.setShopQualifications(sysConfig.getWebUrl() + merchantVO.getShopQualifications());
				merchantVO.setShopPic(sysConfig.getWebUrl() + merchantVO.getShopPic());

				// 多图
				String morePicStr = split(merchantVO.getShopMpic(), sysConfig.getWebUrl());
				merchantVO.setShopMpic(morePicStr);

				// 最小价格
				long minPrice = conn_product.getMinPriceByMer(merchantVO.getId());
				if (minPrice == 0l) {
					merchantVO.setAveragePrice("无数据");
				} else {
					merchantVO.setAveragePrice(df.format((double) minPrice / 100));
				}
				/*
				 * //简介
				 * merchantVO.setShopIntroduction(ReduceHtml2Text.removeHtmlTag(
				 * merchantVO.getShopIntroduction()));
				 */
			}
			// 修改平谷搜索功能 4/21
			int count = conn_merchant.appCount(name);
			dataMap.put("count", count);
			dataMap.put("merchants", _merchants);
			break;
		case "PRODUCT":
			// 修改平谷搜索功能 4/21
			List<ProductPO> products = conn_product.appfindByComNew(name, page, pageSize);
			List<ProductVO> _products = ProductVO.getConverter(ProductVO.class).convert(products, ProductVO.class);
			List<ProductVO> retProductVOs = new ArrayList<ProductVO>();
			for (ProductVO productVO : _products) {
				JSONObject shopJson = JSON.parseObject(productVO.getProductMerchantJson());
				ActivityRelPO relPO = conn_activityRel.getActivityRelByProductId(productVO.getId());
				if (relPO != null) {
					ProductVO origProductVO = (ProductVO) productVO.clone();
					origProductVO.setProductShowPic(sysConfig.getWebUrl() + origProductVO.getProductShowPic());
					origProductVO.setProductMorePic(split(origProductVO.getProductMorePic(), sysConfig.getWebUrl()));
					if (shopJson != null && !shopJson.equals("")) {
						origProductVO.setShopLatitude(shopJson.getString("shopLatitude"));
						origProductVO.setShopLongitude(shopJson.getString("shopLongitude"));
					}
					retProductVOs.add(origProductVO);
					ActivityPO activityPO = conn_activity.get(relPO.getActivityId());
					String price = productVO.getProductPrice();
					if (activityPO.getType().equals(ActivityType.FIXEDPRICE)) {
						if (relPO.getPrice() > 0) {
							productVO.setProductPrice(df.format(Double.parseDouble(relPO.getPrice() + "") / 100));
						} else {
							if (activityPO.getFixedPrice() > 0) {
								productVO.setProductPrice(
										df.format(Double.parseDouble(activityPO.getFixedPrice() + "") / 100));
							}
						}
					} else if (activityPO.getType().equals(ActivityType.DAZHE)) {
						if (relPO.getPrice() > 0) {
							productVO.setProductPrice(df.format(Double.parseDouble(relPO.getPrice() + "") / 100));
						} else {
							productVO.setProductPrice(df.format(Double.parseDouble(
									Long.parseLong(productVO.getProductPrice()) * activityPO.getDiscount() / 10 + "")
									/ 100));
						}
					}
					productVO.setActivityReId(relPO.getId());
					productVO.setIsSurpport(relPO.getSurpportBuy());

				} else {
					productVO.setActivityReId(0);
					productVO.setIsSurpport(0);
				}

				productVO.setProductShowPic(sysConfig.getWebUrl() + productVO.getProductShowPic());
				productVO.setProductMorePic(split(productVO.getProductMorePic(), sysConfig.getWebUrl()));
				if (shopJson != null && !shopJson.equals("")) {
					productVO.setShopLatitude(shopJson.getString("shopLatitude"));
					productVO.setShopLongitude(shopJson.getString("shopLongitude"));
				}
				retProductVOs.add(productVO);
			}
			// 修改平谷搜索功能 4/21
			int count1 = conn_product.appCountByComNew(name);
			dataMap.put("count", count1);
			dataMap.put("products", retProductVOs);
			break;

		default:
			return FORBIDDEN("错误的搜索类型！");
		}

		return success(dataMap);
	}

	/**
	 * 搜索提示 根据数据进行搜索
	 * 
	 * 
	 * @return 返回查询结果 如果未找返回空的list集合
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/prompt", method = RequestMethod.POST)
	public Map<String, Object> getprompt(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		List<String> list = new ArrayList<String>();
		String type = pageObject.getString("type");
		String name = pageObject.getString("name");
		if (name != null) {
			if ("MERCHANT".equals(type)) { // 商家
				List<MerchantPO> merchants = conn_merchant.appfindBy(name, 0, 10);
				if (merchants.size() == 0) {
					return success(merchants.size());
				}
				System.out.println("商家");
				List<MerchantVO> _merchants = MerchantVO.getConverter(MerchantVO.class).convert(merchants,
						MerchantVO.class);
				for (MerchantVO merchantVO : _merchants) {
					String shopname = merchantVO.getShopName();
					list.add(shopname);
				}
			}
			if ("PRODUCT".equals(type)) {
				// 商品
				List<ProductPO> products = conn_product.appPrompt(name, 0, 10);
				List<ProductVO> _products = ProductVO.getConverter(ProductVO.class).convert(products, ProductVO.class);
				for (ProductVO productVO : _products) {
					list.add(productVO.getProductModularCodeName());
					list.add(productVO.getProductName());
					list.add(productVO.getProductPrice());
				}

			}
		}
		return success(list);
	}

	/**
	 * 首页获取所有的分公司
	 * 
	 * @param 无参数
	 * @return companys
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompany", method = RequestMethod.GET)
	public Map<String, Object> getCompany(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CompanyPO> companys = conn_company.appfindAll();
		List<CompanyVO> _companys = CompanyVO.getConverter(CompanyVO.class).convert(companys, CompanyVO.class);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		for (CompanyVO companyVO : _companys) {
			companyVO.setPic(sysConfig.getWebUrl() + companyVO.getPic());
		}
		return success(_companys);
	}

	@Autowired
	private CarouselDAO conn_carousel;

	/**
	 * 首页 获取轮播图
	 * 
	 * @param comCode
	 * @return products
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRecommend", method = RequestMethod.GET)
	public Map<String, Object> getRecommend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String comCode = request.getParameter("comCode");
		CompanyPO company = conn_company.appGetByCode(comCode);
		if (company == null) {
			return ERROR("未获取到公司");
		}
		List<CarouselPO> carlist = conn_carousel.getCarouselByComId(company.getId());
		List<CarouselVO> convert = CarouselVO.getConverter(CarouselVO.class).convert(carlist, CarouselVO.class);
		for (CarouselVO cvo : convert) {
			List<ProductPO> product = conn_product.getProductByProId(cvo.getProductId());
			// cvo.setMerchantId(product.get(0).getProductMerchantID());
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		for (CarouselVO cpo : convert) {
			cpo.setSlidepic(sysConfig.getWebUrl() + cpo.getSlidepic());
		}
		return success(convert);
	}

	@Autowired
	private ActivityBundleDAO conn_activityBundle;

	@Autowired
	private TodayHotSearchDAO conn_todayHotSearch;

	@Autowired
	private ColumnDAO conn_column;

	/**
	 * 首页 获取所有菜单（模块）首页列表
	 * 
	 * @param comCode
	 * @return modulars、activitys、distributors
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getModulars", method = RequestMethod.GET)
	public Map<String, Object> getMenus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String comCode = request.getParameter("comCode");
		CompanyPO company = conn_company.appGetByCode(comCode);
		Map<String, Object> data = new HashMap<String, Object>();
		if (company == null) {
			return ERROR("未获取到公司");
		}

		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");

		// 模块
		List<ModularPO> modulars = conn_modular.appFindBycomId(company.getId());
		if (modulars != null) {
			List<ModularVO> _modulars = ModularVO.getConverter(ModularVO.class).convert(modulars, ModularVO.class);

			for (ModularVO modularVO : _modulars) {
				// 图片
				modularVO.setModularPic(sysConfig.getWebUrl() + modularVO.getModularPic());
				// 商家
				List<ColumnPO> columns = conn_column.getColumnByCode(modularVO.getModularCode());
				List<MerchantPO> merchants = new ArrayList<MerchantPO>();
				for (ColumnPO cpo : columns) {
					List<MerchantPO> mers = conn_merchant.getMerchantById(cpo.getMerchantId());
					if (mers != null && !mers.isEmpty()) {
						if (mers.get(0).getShopAuditState().equals(ShopAuditStateType.T)) {
							merchants.add(mers.get(0));
						}
					}
					List<MerchantVO> _merchants = MerchantVO.getConverter(MerchantVO.class).convert(merchants,
							MerchantVO.class);
					for (MerchantVO merchantVO : _merchants) {

						// 图片
						merchantVO.setShopHeading(sysConfig.getWebUrl() + merchantVO.getShopHeading());
						merchantVO.setShopQualifications(sysConfig.getWebUrl() + merchantVO.getShopQualifications());
						merchantVO.setShopPic(sysConfig.getWebUrl() + merchantVO.getShopPic());

						// 多图
						String morePicStr = split(merchantVO.getShopMpic(), sysConfig.getWebUrl());
						merchantVO.setShopMpic(morePicStr);

						// 最小价格
						long minPrice = conn_product.getMinPriceByMer(merchantVO.getId());

						if (minPrice == 0l) {
							merchantVO.setAveragePrice("无数据");
						} else {
							merchantVO.setAveragePrice(df.format((double) minPrice / 100));
						}
						/*
						 * //简介 merchantVO.setShopIntroduction(ReduceHtml2Text.
						 * removeHtmlTag( merchantVO.getShopIntroduction()));
						 */
					}
					modularVO.setMerchants(_merchants);
				}
				data.put("modulars", _modulars);
			}
		}
		// 活动
		List<ActivityPO> activitys = conn_activity.appFindBycomId(company.getId());
		if (activitys != null) {
			List<ActivityVO> _activitys = ActivityVO.getConverter(ActivityVO.class).convert(activitys,
					ActivityVO.class);
			for (ActivityVO activityVO : _activitys) {
				// 图片
				activityVO.setPic(sysConfig.getWebUrl() + activityVO.getPic());
				// 商品
				List<ProductDTO> _products = conn_activity.appFindProsByAct(activityVO.getId(), 1, pageSizeL);
				for (ProductDTO productDTO : _products) {
					productDTO.setProductShowPic(sysConfig.getWebUrl() + productDTO.getProductShowPic());
					productDTO.setProductPricesStr(df.format((double) productDTO.getProductPrice() / 100));
					productDTO.setProductOldPriceStr(df.format((double) productDTO.getProductOldPrice() / 100));
					productDTO.setActivityName(activityVO.getName());
					productDTO.setActivityId(activityVO.getId());
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					productDTO.setCutDownTimeStartDate(sdf.format(date) + " 10:00:00");
					productDTO.setCutDownTimeEndDate(sdf.format(date) + " 23:59:59");
					SimpleDateFormat sdfWithHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					productDTO.setNowDate(sdfWithHMS.format(date));
					// 多图
					productDTO.setProductMorePic(split(productDTO.getProductMorePic(), sysConfig.getWebUrl()));
				}
				activityVO.setProducts(_products);
			}
			data.put("activitys", _activitys);
		}

		// 经销商
		List<DistributorPO> distributors = conn_distributor.findAll();
		if (distributors != null) {
			List<DistributorVO> _distributors = DistributorVO.getConverter(DistributorVO.class).convert(distributors,
					DistributorVO.class);
			for (DistributorVO distributorVO : _distributors) {
				distributorVO.setShopPic(sysConfig.getWebUrl() + distributorVO.getShopPic());
				// 多图
				distributorVO.setShopMpic(split(distributorVO.getShopMpic(), sysConfig.getWebUrl()));
				// 人均价格
				distributorVO.setAveragePrice("无数据");
			}
			data.put("distributors", _distributors);
		}

		List<ActiveBundlePo> ablist = conn_activityBundle.findAll();
		if (ablist != null) {
			List<ActiveBundleVO> _ablist = ActiveBundleVO.getConverter(ActiveBundleVO.class).convert(ablist,
					ActiveBundleVO.class);
			data.put("SpecialEventsBean", _ablist);
		}

		List<TodayHotSearchPO> thspolist = conn_todayHotSearch.findAll();
		TodayHotSearchsPO ths = new TodayHotSearchsPO();
		List<TodayHotSearchPO> list = new ArrayList<TodayHotSearchPO>();
		for (TodayHotSearchPO thspo : thspolist) {
			list.add(thspo);
			data.put("todayhotsearch", ths);
		}

		ths.setList(list);
		return success(data);
	}

	/**
	 * 二级页面 轮播图（模块）
	 * 
	 * @param modularCode
	 * @return merchants
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRecommendByModu", method = RequestMethod.GET)
	public Map<String, Object> getRecommendByMou(HttpServletRequest request, HttpServletResponse response,
			String modularCode) throws Exception {
		if (modularCode == null || modularCode == "") {
			return ERROR("未获取到模块编码！");
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		List<MerchantPO> merchants = conn_merchant.getMerchantByModularCode(modularCode, 1, recommendSize);
		List<MerchantVO> _merchants = MerchantVO.getConverter(MerchantVO.class).convert(merchants, MerchantVO.class);
		for (MerchantVO merchantVO : _merchants) {
			merchantVO.setShopPic(sysConfig.getWebUrl() + merchantVO.getShopPic());
		}
		return success(_merchants);
	}

	/**
	 * 二级页面 商家列表 10个 有检索
	 * 
	 * @param modularCode
	 * @param page
	 * @param retrievals
	 *            ： type value
	 * @return merchants
	 * @return count
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getMerchantsByModu", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getMerchantsByMou(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> mapStr = new HashMap<String, Object>();
		Map<String, Object> mapStr1 = new HashMap<String, Object>();
		Map<String, Object> mapStr2 = new HashMap<String, Object>();
		// 解析json
		String param = getRequestJson(request);

		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		int page = Integer.parseInt(pageObject.getString("page"));
		String modularCode = pageObject.getString("modularCode");
		JSONArray retrievals = pageObject.getJSONArray("retrievals");// 解析json数组
		if (retrievals != null && retrievals.size() > 0) {
			for (int i = 0; i < retrievals.size(); i++) {
				String type = retrievals.getJSONObject(i).getString("type");
				String value = retrievals.getJSONObject(i).getString("value");
				switch (type) {
				case "modularClass": // 判断为分类的检索条件
					mapStr.put("modularClassId", value); // 查分类字段
					mapStr1.put("modularClassId1", value); // 查分类字段
					mapStr2.put("modularClassId2", value); // 查分类字段
					break;
				}
			}
		}

		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 获取商家列表
		List<MerchantPO> merchants = conn_merchant.getMerchantByModularCode(modularCode, mapStr, page, pageSizeI);
		merchants.addAll(conn_merchant.getMerchantByModularCode1(modularCode, mapStr1, page, pageSizeI));
		merchants.addAll(conn_merchant.getMerchantByModularCode2(modularCode, mapStr2, page, pageSizeI));
		List<MerchantVO> _merchants = MerchantVO.getConverter(MerchantVO.class).convert(merchants, MerchantVO.class);
		int count = conn_merchant.countMerchantByModularCode(modularCode, mapStr);

		DecimalFormat df = new DecimalFormat("0.00");

		for (MerchantVO merchantVO : _merchants) {
			// 图片
			merchantVO.setShopHeading(sysConfig.getWebUrl() + merchantVO.getShopHeading());
			merchantVO.setShopQualifications(sysConfig.getWebUrl() + merchantVO.getShopQualifications());
			merchantVO.setShopPic(sysConfig.getWebUrl() + merchantVO.getShopPic());
			// 多图
			String morePicStr = split(merchantVO.getShopMpic(), sysConfig.getWebUrl());
			merchantVO.setShopMpic(morePicStr);

			// 最小价格
			long minPrice = conn_product.getMinPriceByMer(merchantVO.getId());
			if (minPrice == 0l) {
				merchantVO.setAveragePrice("无数据");
			} else {
				merchantVO.setAveragePrice(df.format((double) minPrice / 100));
			}
		}
		dataMap.put("merchants", _merchants);
		dataMap.put("count", count);
		return success(dataMap);
	}

	/**
	 * 二级页面 轮播图（活动）
	 * 
	 * @param actId
	 * @return _products
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRecommendByAct", method = RequestMethod.GET)
	public Map<String, Object> getRecommendByMou(HttpServletRequest request, HttpServletResponse response, long actId)
			throws Exception {
		List<ProductDTO> _products = conn_activity.appFindProsByAct(actId, 1, pageSizeL);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");
		for (ProductDTO productDTO : _products) {
			productDTO.setProductShowPic(sysConfig.getWebUrl() + productDTO.getProductShowPic());
			productDTO.setProductPricesStr(df.format((double) productDTO.getProductPrice() / 100));
			productDTO.setProductOldPriceStr(df.format((double) productDTO.getProductOldPrice() / 100));
			// 多图
			productDTO.setProductMorePic(split(productDTO.getProductMorePic(), sysConfig.getWebUrl()));
		}
		return success(_products);
	}

	/**
	 * 二级页面 产品列表 10个
	 * 
	 * @param actId
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getProductsByAct", method = RequestMethod.GET)
	public Map<String, Object> getProductsByAct(HttpServletRequest request, HttpServletResponse response, long actId,
			int page) throws Exception {
		List<ProductDTO> _products = conn_activity.appFindProsByAct(actId, page, pageSize);
		int count = conn_activityRel.appCountByAct(actId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");
		for (ProductDTO productDTO : _products) {
			productDTO.setProductShowPic(sysConfig.getWebUrl() + productDTO.getProductShowPic());
			productDTO.setProductPricesStr(df.format((double) productDTO.getProductPrice() / 100));
			productDTO.setProductOldPriceStr(df.format((double) productDTO.getProductOldPrice() / 100));
			// 多图
			productDTO.setProductMorePic(split(productDTO.getProductMorePic(), sysConfig.getWebUrl()));
		}
		dataMap.put("products", _products);
		dataMap.put("count", count);
		return success(dataMap);
	}

	/**
	 * 二级页面检索（模块）
	 * 
	 * @param modularCode
	 * @return [{name,type,children},{}]
	 */
	@ResponseBody
	@RequestMapping(value = "/getRetrievalByModu", method = RequestMethod.GET)
	public Map<String, Object> getProductsByAct(HttpServletRequest request, HttpServletResponse response,
			String modularCode) throws Exception {
		if (modularCode == null || modularCode == "") {
			return ERROR("未获取到模块编码！");
		}

		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> Retrievals = new ArrayList<Map<String, Object>>();
		List<ModularClassPO> ModularClasses = conn_modularClass.appFindByModular(modularCode);
		dataMap.put("name", "分类");
		dataMap.put("type", "modularClass");
		dataMap.put("children", ModularClasses);
		Retrievals.add(dataMap);
		return success(Retrievals);
	}

	/**
	 * 经销商详情页 经销商详情
	 * 
	 * @param merchantID
	 * @return _merchant
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/distributorInfo", method = RequestMethod.GET)
	public Map<String, Object> distributorInfo(HttpServletRequest request, HttpServletResponse response,
			long distributorId) throws Exception {
		DistributorPO distributor = conn_distributor.get(distributorId);
		if (distributor == null) {
			return ERROR("未获取到经销商！");
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 经销商
		DistributorVO _distributor = new DistributorVO().set(distributor);
		_distributor.setShopPic(sysConfig.getWebUrl() + _distributor.getShopPic());// 商家显示图片
		_distributor.setShopMpic(split(_distributor.getShopMpic(), sysConfig.getWebUrl()));// 商家多图
		// 人均价格
		_distributor.setAveragePrice("无数据");
		// 经销商星级?
		return success(_distributor);
	}

	/**
	 * 经销商详情页 产品列表10个
	 * 
	 * @param merchantID
	 * @param page
	 * @return products count
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getProductsByDis", method = RequestMethod.GET)
	public Map<String, Object> getProductsByDis(HttpServletRequest request, HttpServletResponse response,
			long distributorId, int page) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");
		// 商品
		List<ProductVO> _products = new ArrayList<ProductVO>();
		List<DistributorProductPO> distributorProducts = conn_distributorProduct.findByDisId(distributorId, page,
				pageSize);
		for (DistributorProductPO distributorProductPO : distributorProducts) {
			ProductPO product = distributorProductPO.getProduct();

			ProductVO _product = new ProductVO().set(product);
			// 经销商价格
			_product.setDistributorPrice(df.format((double) distributorProductPO.getDistributorPrice() / 100));

			// 商品照片
			_product.setProductShowPic(sysConfig.getWebUrl() + _product.getProductShowPic());
			_products.add(_product);
		}
		int count = conn_distributorProduct.countByDisId(distributorId);
		dataMap.put("products", _products);
		dataMap.put("count", count);
		return success(dataMap);
	}

	/**
	 * 店面详情页 商家详情
	 * 
	 * @param merchantID
	 * @return _merchant
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/merchantInfo", method = RequestMethod.GET)
	public Map<String, Object> merchantInfo(HttpServletRequest request, HttpServletResponse response, long merchantID)
			throws Exception {
		MerchantPO merchant = conn_merchant.get(merchantID);
		if (merchant == null) {
			return ERROR("未获取到商家！");
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 商家
		MerchantVO _merchant = new MerchantVO().set(merchant);
		_merchant.setShopPic(sysConfig.getWebUrl() + _merchant.getShopPic());// 商家显示图片
		_merchant.setShopMpic(split(_merchant.getShopMpic(), sysConfig.getWebUrl()));// 商家多图

		// 收藏
		if (request.getParameter("userId") != null) {
			Long userId = Long.parseLong(request.getParameter("userId"));
			if (userId != null) {
				UserInfoPO user = conn_user.get(userId);
				CollectionPO collection = conn_collection.getByUserMerId(user, merchantID);
				if (collection != null) {
					_merchant.setIfcollection(1);
				}
			}
		}

		// 商家星级?
		return success(_merchant);
	}

	/**
	 * 店面详情页 商家检索
	 * 
	 * @param merchantID
	 * @param page
	 * @return products count
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getRetrievalByMer", method = RequestMethod.GET)
	public Map<String, Object> getRetrievalByMer(HttpServletRequest request, HttpServletResponse response,
			long merchantID, int page) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 商品
		List<ProductPO> products = conn_product.getProductsByMer(merchantID, page, pageSize);
		List<ProductVO> _products = ProductVO.getConverter(ProductVO.class).convert(products, ProductVO.class);
		int count = conn_product.countProductsByMer(merchantID);
		for (ProductVO productVO : _products) {
			productVO.setProductShowPic(sysConfig.getWebUrl() + productVO.getProductShowPic()); // 显示图片
		}
		dataMap.put("products", _products);
		dataMap.put("count", count);
		return success(dataMap);
	}

	/**
	 * 店面详情页 产品列表10个
	 * 
	 * @param merchantID
	 * @param page
	 * @return products count
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getProductsByMer", method = RequestMethod.GET)
	public Map<String, Object> getProductsByMer(HttpServletRequest request, HttpServletResponse response,
			long merchantID, Integer page) throws Exception {
		DecimalFormat df = new DecimalFormat("0.00");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 商品
		List<ProductPO> products = conn_product.getProductsByMer(merchantID, page, pageSize);
		List<ProductVO> _products = ProductVO.getConverter(ProductVO.class).convert(products, ProductVO.class);
		List<ProductVO> retProductVOs = new ArrayList<ProductVO>();
		int count = conn_product.countProductsByMer(merchantID);
		for (ProductVO productVO : _products) {
			ActivityRelPO relPO = conn_activityRel.getActivityRelByProductId(productVO.getId());
			if (relPO != null) {
				ProductVO origProductVO = (ProductVO) productVO.clone();
				origProductVO.setProductShowPic(sysConfig.getWebUrl() + origProductVO.getProductShowPic()); // 显示图片
				retProductVOs.add(origProductVO);
				ActivityPO activityPO = conn_activity.get(relPO.getActivityId());
				String price = productVO.getProductPrice();
				if (activityPO.getType().equals(ActivityType.FIXEDPRICE)) {
					if (relPO.getPrice() > 0) {
						productVO.setProductPrice(df.format(Double.parseDouble(relPO.getPrice() + "") / 100));
					} else {
						productVO.setProductPrice(df.format(Double.parseDouble(activityPO.getFixedPrice() + "") / 100));
					}
				} else if (activityPO.getType().equals(ActivityType.DAZHE)) {
					if (relPO.getPrice() > 0) {
						productVO.setProductPrice(df.format(Double.parseDouble(relPO.getPrice() + "") / 100));
					} else {
						productVO.setProductPrice(df.format(Double.parseDouble(
								Long.parseLong(productVO.getProductPrice()) * activityPO.getDiscount() / 10 + "")
								/ 100));
					}
				}
				productVO.setActivityReId(relPO.getId());
				productVO.setIsSurpport(relPO.getSurpportBuy());

			} else {
				productVO.setActivityReId(0);
				productVO.setIsSurpport(0);
			}

			productVO.setProductShowPic(sysConfig.getWebUrl() + productVO.getProductShowPic()); // 显示图片
			retProductVOs.add(productVO);
		}
		dataMap.put("products", retProductVOs);
		dataMap.put("count", count);
		return success(dataMap);
	}

	/**
	 * 店面详情页 到店支付
	 * 
	 * @param merchantID
	 * @param page
	 * @return products count
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/goToPay", method = RequestMethod.POST)
	public Map<String, Object> goToPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		// 解析Json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}

		JSONObject pageObject = JSON.parseObject(param);
		Long merchantId = Long.parseLong(pageObject.getString("merchantId"));
		Long userId = Long.parseLong(pageObject.getString("userId"));
		Double payMoneyStr = pageObject.getDouble("payMoney");

		/*
		 * String productId = request.getParameter("productId"); String num =
		 * request.getParameter("num"); String paytype =
		 * request.getParameter("payType"); Long userId =
		 * Long.parseLong(request.getParameter("userId"));
		 */

		OrderInfoPO order = new OrderInfoPO();
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		// 支付金额
		long payMoney = (long) (payMoneyStr * 100);
		// 订单总金额
		long orderAllMoney = payMoney;
		// 获取产品

		// 获取商家
		MerchantPO merchant = conn_merchant.get(merchantId);
		if (merchant == null) {
			return FORBIDDEN("未获取到商家！");
		}

		// 获取用户
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		// 会员ID
		order.setUserId(userId);
		order.setComId(merchant.getComId());
		if (user.getUserNickname() != null) {
			order.setUserName(user.getUserNickname());
		}
		if (user.getUserPhone() != null) {
			// 会员手机号
			order.setUserTel(user.getUserPhone());
			// 会员信息
			// 会员坐标经度
			// 会员坐标维度
			// 提成金额比例
		}

		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		String orderNO = getCityCodeByDomain() + "v" + merchant.getId() + "v" + merchant.getModularCode() + "v"
				+ df.format(date) + "v" + user.getId();
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
		// 商品图片
		order.setProductPic(merchant.getShopPic());
		// 所属板块DI
		order.setBkCode(merchant.getModularCode());
		// 所属板块名称
		order.setBkName(merchant.getModularName());
		// 套餐ID
		// 套餐名称

		/*
		 * long proportion = productPO.getProductCommissionPrice(); if
		 * (productPO.getProductCommissionCode() == 1) {
		 * order.setProportion(proportion); }
		 */
		/*
		 * // 提成方式（0：佣金1：比例）
		 * order.setRoyaltyName(productPO.getProductCommissionCode()); // 积分数
		 * long integralNum = Integer.parseInt(num) *
		 * productPO.getProductntegral(); order.setIntegralNum(integralNum); //
		 * 订单佣金金额(分) long proportionMoney; if
		 * (productPO.getProductCommissionCode() == 1) { proportionMoney =
		 * Integer.parseInt(num) * productPO.getProductPrice() * proportion /
		 * 100; } else { proportionMoney = Integer.parseInt(num) * proportion; }
		 * order.setProportionMoney(proportionMoney);
		 */
		// 支付金额
		order.setPayMoney(payMoney);
		// 订单总金额
		order.setOrderAllMoney(orderAllMoney);
		// 订单说明
		if (request.getParameter("orderRemark") != null && request.getParameter("orderRemark").length() > 0) {
			order.setOrderRemark(request.getParameter("orderRemark"));
		}
		/*
		 * // 订单支付类型 //ALIPAY WEICHAT
		 * order.setPayMode(PayType.fromString(paytype));
		 */
		// 有效期
		// 配送信息
		/*
		 * if(pageObject.getString("addressId")!=null&&pageObject.getString(
		 * "addressId")!=""){ Long addressId =
		 * Long.parseLong(pageObject.getString("addressId"));
		 * order.setMailAddress(addressId); }
		 */
		// 是否评价
		order.setCommentIs(0);
		// 预订日期
		order.setOrderBookDate(date);
		order.setOrderType(OrderType.MERCHANT);

		// 订单来源
		if (pageObject.getString("source") != null) {
			if (pageObject.getString("addressId") != null && pageObject.getString("addressId") != "") {
				Long addressId = Long.parseLong(pageObject.getString("addressId"));
				order.setMailAddress(addressId);
			}
			order.setOrderState(OrderStateType.NOTPAY);
			order.setPayMode(PayType.WEICHAT);
			order.setSource(OrderSource.PUBLICADDRESS);
		} else {
			// 订单状态
			order.setOrderState(OrderStateType.NOTPAY);
			order.setSource(OrderSource.APP);
		}
		conn_order.saveOrUpdate(order);
		return success(order);
	}

	// 删除未支付的订单
	@ResponseBody
	@RequestMapping(value = "/deleteorder", method = RequestMethod.POST)
	public Map<String, Object> DeleteOrder(HttpServletRequest request) throws Exception {

		String param = getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(param);
		Long orderId = Long.parseLong(pageObject.getString("orderId"));
		conn_order.delete(orderId);
		return success();
	}

	/**
	 * 店面详情页 到店支付 微信 支付宝
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/payOrderType", method = RequestMethod.POST)
	public Map<String, Object> payOrderType(HttpServletRequest request) throws Exception {

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String paytype = pageObject.getString("paytype");
		Long orderId = pageObject.getLong("orderId");// 解析json数组
		OrderInfoPO order = conn_order.get(orderId);
		order.setPayMode(PayType.fromString(paytype));
		String orderNO = order.getOrderNO();
		long allMoney = order.getPayMoney();
		// 调微信和支付宝
		if (paytype.equals("WEICHAT")) { // 微信
			return success(weichatPay(allMoney, order.getId() + ""));
		} else if (paytype.equals("ALIPAY")) { // 支付宝
			String sign = AliAppOrderInfo.getInstance().getSign(order.getProductId() + "", allMoney,
					order.getProductName(), order.getId() + "");
			return success(sign);
		} else {
			return ERROR("系统错误！");
		}
	}

	/**
	 * 商品详情页 商品详情
	 * 
	 * @param productId
	 * @return 商品 商户介绍
	 * @throws Exception
	 */
	@Autowired
	private ProductComboDAO conn_combo;
	@Autowired
	private LogisticsDao conn_logistics;

	@ResponseBody
	@RequestMapping(value = "/productInfo", method = RequestMethod.GET)
	public Map<String, Object> productInfo(HttpServletRequest request, HttpServletResponse response, long productId)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		ProductPO product = conn_product.get(productId);
		MerchantPO merchant = conn_merchant.get(product.getProductMerchantID());
		MerchantVO _merchant = new MerchantVO().set(merchant);
		if (product == null) {
			return ERROR("未获取到产品！");
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 商品
		ProductVO _product = new ProductVO().set(product);
		JSONObject merchantJson = JSONObject.parseObject(product.getProductMerchantJson());

		_product.setProductShowPic(sysConfig.getWebUrl() + _product.getProductShowPic()); // 显示图片
		_product.setProductMorePic(split(_product.getProductMorePic(), sysConfig.getWebUrl()));// 多图
		// 收藏
		if (request.getParameter("userId") != null && !request.getParameter("userId").equals("")) {
			Long userId = Long.parseLong(request.getParameter("userId"));
			if (userId != null) {
				UserInfoPO user = conn_user.get(userId);
				CollectionPO collection = conn_collection.getByUserProId(user, productId);
				if (collection != null) {
					_product.setIfcollection(1);
				}
			}
		}

		// 商户介绍
		String shopIntroduction = ReduceHtml2Text.removeHtmlTag(merchantJson.getString("shopIntroduction"));
		// 评论两条
		List<CommentPO> comments = conn_comment.findByPro(productId, 1, 20);
		List<String> userHeadImgs = new ArrayList<String>();
		List<String> useridList = new ArrayList<String>();
		for (CommentPO cp : comments) {
			List<UserInfoPO> user = conn_userInfo.getUserByUid(cp.getUser().getId());
			userHeadImgs.add(user.get(0).getUserHeadimg());
			useridList.add(cp.getUser().getId().toString());
		}
		List<CommentVO> _comments = CommentVO.getConverter(CommentVO.class).convert(comments, CommentVO.class);
		for (CommentVO commentVO : _comments) {// 图片？？
			commentVO.setCommentMPic(split(commentVO.getCommentMPic(), sysConfig.getWebUrl()));
		}
		// 评论数
		int commentCount = conn_comment.countByPro(productId);

		// 获取所有套餐
		List<ProductComboPO> comboList = conn_combo.findByField("productId", productId);
		// 获取物流列表
		List<LogisticsPo> logisticsPos = conn_logistics.findAll();
		List<LogisticVO> vos = LogisticVO.getConverter(LogisticVO.class).convert(logisticsPos, LogisticVO.class);
		dataMap.put("combos", comboList);
		dataMap.put("logistics", vos);
		dataMap.put("merchant", _merchant);
		dataMap.put("product", _product);
		dataMap.put("shopIntroduction", shopIntroduction);
		dataMap.put("comments", _comments);
		dataMap.put("commentCount", commentCount);
		dataMap.put("userimgs", userHeadImgs);
		dataMap.put("useridlist", useridList);
		dataMap.put("shopTel", merchantJson.getString("shopTel"));
		return success(dataMap);
	}

	@ResponseBody
	@RequestMapping(value = "/getmerchantid", method = RequestMethod.GET)
	public Map<String, Object> GetShopName(HttpServletResponse response, long merchantId) throws Exception {
		Map<String, Object> mv = new HashMap<String, Object>();

		MerchantPO merchantPO = conn_merchant.get(merchantId);
		mv.put("shopName", merchantPO.getShopName());

		return success(mv);
	}

	@Autowired
	UserOnedayBuyDAO conn_userone;

	/**
	 * 一元购商品详情页 商品详情
	 * 
	 * @param productId
	 * @return 商品 商户介绍
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/activityInfo", method = RequestMethod.GET)
	public Map<String, Object> activityInfo(HttpServletRequest request, HttpServletResponse response, long productId)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ProductPO product = conn_product.get(productId);
		if (product == null) {
			return ERROR("未获取到产品！");
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 商品
		ProductVO _product = new ProductVO().set(product);
		JSONObject merchantJson = JSONObject.parseObject(product.getProductMerchantJson());

		_product.setProductShowPic(sysConfig.getWebUrl() + _product.getProductShowPic()); // 显示图片
		_product.setProductMorePic(split(_product.getProductMorePic(), sysConfig.getWebUrl()));// 多图
		ActivityRelPO activityPro = conn_activityRel.getActivityRelByProductId(_product.getId());
		ActivityRelVO activityRelVO = new ActivityRelVO().set(activityPro);
		// 收藏
		Long userId = Long.parseLong(request.getParameter("userId"));
		if (userId != null) {
			UserInfoPO user = conn_user.get(userId);
			CollectionPO collection = conn_collection.getByUserIdProId(user.getId(), productId, activityPro.getId());
			if (collection != null) {
				_product.setIfcollection(1);
			}
			List<UserOneDayBuyPO> userOneDayBuyPOs = conn_userone.findDateBuy(userId, activityPro.getId(), new Date());
			if (activityPro.getOnePerDay() != 0 && userOneDayBuyPOs != null
					&& activityPro.getOnePerDay() <= userOneDayBuyPOs.size()) {
				dataMap.put("isXianGou", 1);
			}

		}
		// 测试
		DecimalFormat df = new DecimalFormat("0.00");
		ActivityPO activityPO = conn_activity.get(activityPro.getActivityId());
		if (activityPO.getType().equals(ActivityType.FIXEDPRICE)) {
			if (activityPro.getPrice() > 0) {
				_product.setProductPrice(df.format(Double.parseDouble(activityPro.getPrice() + "") / 100));
			} else {
				_product.setProductPrice(df.format(Double.parseDouble(activityPO.getFixedPrice() + "") / 100));
			}

		} else if (activityPO.getType().equals(ActivityType.DAZHE)) {
			if (activityPro.getPrice() > 0) {
				_product.setProductPrice(df.format(Double.parseDouble(activityPro.getPrice() + "") / 100));
			} else {
				_product.setProductPrice(df.format(Double.parseDouble(
						Long.parseLong(_product.getProductPrice()) * activityPO.getDiscount() / 10 + "") / 100));
			}
		}

		Date date = new Date();
		Date beginDate = activityPro.getBeginDate();
		Date endDate = activityPro.getEndDate();
		Date beginTime = activityPro.getBeginTime();
		Date endTime = activityPro.getEndTime();
		Date bookBegin = activityPro.getBookBeginTime();
		if (date.after(bookBegin)) {
			activityRelVO.setBookBeginTime(DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"));
		}

		String beginTimeStr = DateUtil.format(date, "yyyy-MM-dd") + " " + DateUtil.format(beginTime, "HH:mm:ss");
		Date beginTimeAll = DateUtil.parse(beginTimeStr, "yyyy-MM-dd HH:mm:ss");
		String endTimeStr = DateUtil.format(date, "yyyy-MM-dd") + " " + DateUtil.format(endTime, "HH:mm:ss");
		Date endTimeAll = DateUtil.parse(endTimeStr, "yyyy-MM-dd HH:mm:ss");

		if (date.before(beginDate) || date.before(beginTimeAll)) {
			_product.setIsEffective(0);
			long a = date.getTime();
			long b = beginDate.getTime();
			long b1 = beginTimeAll.getTime();
			long c = (b - a) / 1000;
			if (b < b1) {
				c = (b1 - a) / 1000;
			}
			dataMap.put("miao", c);
		} else if (date.after(beginDate) && date.after(beginTimeAll) && date.before(endTimeAll)
				&& date.before(endDate)) {
			_product.setIsEffective(1);
		} else if (date.after(endDate) || date.after(endTimeAll)) {
			_product.setIsEffective(2);
		}

		// 商户介绍
		String shopIntroduction = ReduceHtml2Text.removeHtmlTag(merchantJson.getString("shopIntroduction"));
		// 评论两条
		List<CommentPO> comments = conn_comment.findByPro(productId, 1, 20);
		List<String> userHeadImgs = new ArrayList<String>();
		List<String> useridList = new ArrayList<String>();
		for (CommentPO cp : comments) {
			List<UserInfoPO> user = conn_userInfo.getUserByUid(cp.getUser().getId());
			userHeadImgs.add(user.get(0).getUserHeadimg());
			useridList.add(cp.getUser().getId().toString());
		}
		List<CommentVO> _comments = CommentVO.getConverter(CommentVO.class).convert(comments, CommentVO.class);
		for (CommentVO commentVO : _comments) {// 图片？？
			commentVO.setCommentMPic(split(commentVO.getCommentMPic(), sysConfig.getWebUrl()));
		}
		// 评论数
		int commentCount = conn_comment.countByPro(productId);

		List<LogisticsPo> logisticsPos = conn_logistics.findAll();
		List<LogisticVO> vos = LogisticVO.getConverter(LogisticVO.class).convert(logisticsPos, LogisticVO.class);

		MerchantPO merchant = conn_merchant.get(product.getProductMerchantID());
		MerchantVO _merchant = new MerchantVO().set(merchant);

		List<ProductComboPO> comboList = conn_combo.findByField("productId", productId);
		dataMap.put("combos", comboList);
		dataMap.put("logistics", vos);
		dataMap.put("merchant", _merchant);
		dataMap.put("product", _product);
		dataMap.put("activityPro", activityRelVO);
		dataMap.put("shopIntroduction", shopIntroduction);
		dataMap.put("comments", _comments);
		dataMap.put("commentCount", commentCount);
		dataMap.put("userimgs", userHeadImgs);
		dataMap.put("useridlist", useridList);
		dataMap.put("shopTel", merchantJson.getString("shopTel"));
		dataMap.put("today", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"));
		return success(dataMap);
	}

	@ResponseBody
	@RequestMapping(value = "/refreshActivity", method = RequestMethod.GET)
	public Map<String, Object> refreshActivity(HttpServletRequest request, HttpServletResponse response, long productId,
			String bDate, int count) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ProductPO product = conn_product.get(productId);
		if (product == null) {
			return ERROR("未获取到产品！");
		}
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 商品
		ProductVO _product = new ProductVO().set(product);

		_product.setProductShowPic(sysConfig.getWebUrl() + _product.getProductShowPic()); // 显示图片
		_product.setProductMorePic(split(_product.getProductMorePic(), sysConfig.getWebUrl()));// 多图
		ActivityRelPO activityPro = conn_activityRel.getActivityRelByProductId(_product.getId());

		// 收藏
		Long userId = Long.parseLong(request.getParameter("userId"));
		if (userId != null) {
			UserInfoPO user = conn_user.get(userId);
			CollectionPO collection = conn_collection.getByUserProId(user, productId);
			if (collection != null) {
				_product.setIfcollection(1);
			}
			List<UserOneDayBuyPO> userOneDayBuyPOs = conn_userone.findDateBuy(userId, activityPro.getId(),
					DateUtil.parse(bDate, "yyyy-MM-dd HH:mm"));
			if (activityPro.getOnePerDay() != 0 && userOneDayBuyPOs != null
					&& activityPro.getOnePerDay() < (userOneDayBuyPOs.size() + count)) {
				dataMap.put("isXianGou", 1);
			}

		}
		// 测试
		DecimalFormat df = new DecimalFormat("0.00");
		ActivityPO activityPO = conn_activity.get(activityPro.getActivityId());
		if (activityPO.getType().equals(ActivityType.FIXEDPRICE)) {
			if (activityPro.getPrice() > 0) {
				_product.setProductPrice(df.format(Double.parseDouble(activityPro.getPrice() + "") / 100));
			} else {
				_product.setProductPrice(df.format(Double.parseDouble(activityPO.getFixedPrice() + "") / 100));
			}

		} else if (activityPO.getType().equals(ActivityType.DAZHE)) {
			if (activityPro.getPrice() > 0) {
				_product.setProductPrice(df.format(Double.parseDouble(activityPro.getPrice() + "") / 100));
			} else {
				_product.setProductPrice(df.format(Double.parseDouble(
						Long.parseLong(_product.getProductPrice()) * activityPO.getDiscount() / 10 + "") / 100));
			}
		}

		Date date = new Date();
		Date beginDate = activityPro.getBeginDate();
		Date endDate = activityPro.getEndDate();
		Date beginTime = activityPro.getBeginTime();
		Date endTime = activityPro.getEndTime();

		String beginTimeStr = DateUtil.format(date, "yyyy-MM-dd") + " " + DateUtil.format(beginTime, "HH:mm:ss");
		Date beginTimeAll = DateUtil.parse(beginTimeStr, "yyyy-MM-dd HH:mm:ss");
		String endTimeStr = DateUtil.format(date, "yyyy-MM-dd") + " " + DateUtil.format(endTime, "HH:mm:ss");
		Date endTimeAll = DateUtil.parse(endTimeStr, "yyyy-MM-dd HH:mm:ss");

		if (date.before(beginDate) || date.before(beginTimeAll)) {
			_product.setIsEffective(0);
			long a = date.getTime();
			long b = beginDate.getTime();
			long b1 = beginTimeAll.getTime();
			long c = (b - a) / 1000;
			if (b < b1) {
				c = (b1 - a) / 1000;
			}
			dataMap.put("miao", c);
		} else if (date.after(beginDate) && date.after(beginTimeAll) && date.before(endTimeAll)
				&& date.before(endDate)) {
			_product.setIsEffective(1);
		} else if (date.after(endDate) || date.after(endTimeAll)) {
			_product.setIsEffective(2);
		}

		dataMap.put("product", _product);
		return success(dataMap);
	}

	/**
	 * 商品详情页 产品经销商
	 * 
	 * @param productId
	 * @param page
	 * @return merchant products
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getDistributorByPro", method = RequestMethod.GET)
	public Map<String, Object> getDistributorByPro(HttpServletRequest request, HttpServletResponse response,
			long productId) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");
		ProductPO product = conn_product.get(productId);
		if (product == null) {
			return ERROR("未获取到产品！");
		}
		List<DistributorVO> _distributors = new ArrayList<DistributorVO>();

		List<DistributorProductPO> distributorProducts = product.getDistributorProduct();
		for (DistributorProductPO distributorProductPO : distributorProducts) {
			DistributorPO distributor = conn_distributor.get(distributorProductPO.getDistributorId());
			DistributorVO _distributor = new DistributorVO().set(distributor);
			// 经销商价格
			_distributor.setDistributorPrice(df.format((double) distributorProductPO.getDistributorPrice() / 100));
			// 单图
			_distributor.setShopPic(sysConfig.getWebUrl() + _distributor.getShopPic());
			// 多图
			_distributor.setShopMpic(split(_distributor.getShopMpic(), sysConfig.getWebUrl()));
			// 人均价格
			_distributor.setAveragePrice("无数据");
			_distributors.add(_distributor);
		}

		dataMap.put("distributors", _distributors);
		return success(dataMap);
	}

	/**
	 * 商品详情页 订单接口（分页）
	 * 
	 * @param productId
	 * @param page
	 * @return merchant products
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderByPro", method = RequestMethod.GET)
	public Map<String, Object> getOrderByPro(HttpServletRequest request, HttpServletResponse response, long productId,
			Integer page) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		ProductPO product = conn_product.get(productId);
		if (product == null) {
			return ERROR("未获取到产品！");
		}

		List<CommentPO> comments = conn_comment.findByPro(productId, page, pageSize);
		int count = conn_comment.countByPro(productId);
		List<CommentVO> _comments = CommentVO.getConverter(CommentVO.class).convert(comments, CommentVO.class);
		for (CommentVO commentVO : _comments) { // 评论图片 用户头像
			commentVO.setCommentMPic(split(commentVO.getCommentMPic(), sysConfig.getWebUrl()));
		}
		dataMap.put("count", count);
		dataMap.put("comments", _comments);
		return success(dataMap);
	}

	/**
	 * 用户注册验证码
	 * 
	 * @param phone
	 *            code
	 * @return phone code
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/registCode", method = RequestMethod.POST)
	public Map<String, Object> registCode(HttpServletRequest request, HttpServletResponse response1) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}

		JSONObject pageObject = JSON.parseObject(param);
		String phone = pageObject.getString("phone");
		/* String phone = request.getParameter("phone"); */
		// 用户名已经备注册
		UserInfoPO user = conn_user.getUserByPhone(phone);
		if (user != null) {
			return FORBIDDEN("手机号码已被注册！");
		}

		// code
		String code = VerifyCodeUtils.generateVerifyNum(6);
		PhoneCodePO phoneCode = conn_phoneCode.getByPhoneReg(phone);
		if (phoneCode == null) {
			phoneCode = new PhoneCodePO();
			phoneCode.setUpdateTime(new Date());
			phoneCode.setCode(code);
			phoneCode.setPhoneNum(phone);
			phoneCode.setSource(1);// App
			phoneCode.setType(1);// 注册
		} else {
			phoneCode.setCode(code);
		}
		conn_phoneCode.saveOrUpdate(phoneCode);

		SendSmsResponse response = Alisms.sendRegistSms(phone, code);
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());
		if (response.getCode() != null && response.getCode().equals("OK")) {
			dataMap.put("phone", phone);
			dataMap.put("code", code);
			return success(dataMap);
		} else {
			return ERROR("发送失败！aliCode=" + response.getCode() + ",aliMessage=" + response.getMessage());
		}
	}

	/**
	 * 用户注册（使用手机号和密码）
	 * 
	 * @param phone
	 * @param password
	 * @return 200
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public Map<String, Object> doRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String phone = pageObject.getString("phone");
		String password = pageObject.getString("password");
		String code = pageObject.getString("code");
		/*
		 * String phone = request.getParameter("phone"); String password =
		 * request.getParameter("password"); String code =
		 * request.getParameter("code");
		 */
		// 用户名已经备注册
		UserInfoPO user = conn_user.getUserByPhone(phone);
		if (user != null) {
			return FORBIDDEN("手机号码已被注册！");
		}

		// 验证code
		PhoneCodePO phoneCode = conn_phoneCode.getByPhoneReg(phone);
		if (phoneCode == null || phoneCode.getCode() == null) {
			return FORBIDDEN("验证码失效");
		}

		if (!phoneCode.getCode().equals(code)) {
			return FORBIDDEN("验证码错误！");
		}
		conn_phoneCode.delete(phoneCode);// 删除记录

		user = new UserInfoPO();
		user.setUpdateTime(new Date());
		user.setUserPhone(phone);
		user.setUserPassword(Sha1Util.getSha1(password));
		user.setSource(1);
		conn_user.save(user);

		data.put("userId", user.getId());
		data.put("phone", user.getUserPhone());
		return success(data);
	}

	/**
	 * 重置密码验证码
	 * 
	 * @param phone
	 *            code
	 * @return 200 phone code
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/repPasswordCode", method = RequestMethod.POST)
	public Map<String, Object> repPasswordCode(HttpServletRequest request, HttpServletResponse response1)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String phone = pageObject.getString("phone");

		// 手机号码未注册
		UserInfoPO user = conn_user.getUserByPhone(phone);
		if (user == null) {
			return FORBIDDEN("该手机号码未注册！");
		}
		// 生成code
		String code = VerifyCodeUtils.generateVerifyNum(6);
		PhoneCodePO phoneCode = conn_phoneCode.getByPhoneRep(phone);
		if (phoneCode == null) {
			phoneCode = new PhoneCodePO();
			phoneCode.setUpdateTime(new Date());
			phoneCode.setCode(code);
			phoneCode.setPhoneNum(phone);
			phoneCode.setSource(1);// App
			phoneCode.setType(2);// 重置密码
		} else {
			phoneCode.setCode(code);
		}

		SendSmsResponse response = Alisms.sendRepPasswordSms(phone, code);
		System.out.println("短信接口返回的数据----------------");
		System.out.println("Code=" + response.getCode());
		System.out.println("Message=" + response.getMessage());
		System.out.println("RequestId=" + response.getRequestId());
		System.out.println("BizId=" + response.getBizId());
		if (response.getCode() != null && response.getCode().equals("OK")) {
			dataMap.put("phone", phone);
			dataMap.put("code", code);
			return success(dataMap);
		} else {
			return ERROR("发送失败！aliCode=" + response.getCode() + ",aliMessage=" + response.getMessage());
		}
	}

	/**
	 * 重置密码（使用手机号和密码）
	 * 
	 * @param phone
	 * @param newPassword
	 * @return 200
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/repPassword", method = RequestMethod.POST)
	public Map<String, Object> repPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String phone = pageObject.getString("phone");
		String newPassword = pageObject.getString("newPassword");
		String code = pageObject.getString("code");

		// 验证code
		PhoneCodePO phoneCode = conn_phoneCode.getByPhoneRep(phone);
		if (phoneCode == null || phoneCode.getCode() == null) {
			return FORBIDDEN("验证码失效");
		}
		if (!phoneCode.getCode().equals(code)) {
			return FORBIDDEN("验证码错误！");
		}
		conn_phoneCode.delete(phoneCode);// 删除记录
		// 手机号码未注册
		UserInfoPO user = conn_user.getUserByPhone(phone);
		if (user == null) {
			return FORBIDDEN("该手机号码未注册！");
		}

		user.setUserPassword(Sha1Util.getSha1(newPassword));
		conn_user.save(user);
		return success();
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> data = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String phone = pageObject.getString("phone");
		String password = pageObject.getString("password");

		UserInfoPO user = conn_user.getUserByPhone(phone);
		if (user == null) {
			throw new UnkonwnUserException();
		}
		String s_pas = Sha1Util.getSha1(password);
		if (!s_pas.equals(user.getUserPassword())) {
			throw new PasswordErrorException();
		}
		// 判断是否为商户
		MerchantPO merchant = conn_merchant.getMerByUser(user);
		if (merchant == null) {
			data.put("merchant", "no");
		} else {
			data.put("merchant", "yes");
			data.put("merchantId", merchant.getId());
		}
		UserInfoVO _user = new UserInfoVO().set(user);
		data.put("userId", _user.getId());
		data.put("phone", _user.getUserPhone());
		return success(data);
	}

	/**
	 * 微信登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/weixinLogin", method = RequestMethod.POST)
	public Map<String, Object> weixinLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();

		String param = getRequestJson(request);
		System.out.println("json参数前:" + param);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
		}
		System.out.println("json参数后:" + param);
		JSONObject pageObject = JSON.parseObject(param);
		String openId = pageObject.getString("openId");
		String userHeadimg = pageObject.getString("userHeadimg");
		String userNickname = pageObject.getString("userNickname");

		UserInfoPO user = conn_user.getUserByOpenId(openId);
		if (user == null) {
			user = new UserInfoPO();
			user.setUpdateTime(new Date());
			user.setUserOpenID(openId);
			user.setUserHeadimg(userHeadimg);
			user.setUserNickname(userNickname);
			user.setSource(1);
			conn_user.save(user);
			data.put("merchant", "no");
		} else {
			// 判断是否为商户
			MerchantPO merchant = conn_merchant.getMerByUser(user);
			if (merchant == null) {
				data.put("merchant", "no");
			} else {
				data.put("merchant", "yes");
			}
		}
		UserInfoVO _user = new UserInfoVO().set(user);
		data.put("userId", _user.getId());
		data.put("phone", _user.getUserPhone());
		return success(data);
	}

	/**
	 * 完善电话号码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addPhone", method = RequestMethod.POST)
	public Map<String, Object> addPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		long userId = Long.parseLong(pageObject.getString("userId"));
		String phone = pageObject.getString("phone");
		String code = pageObject.getString("code");

		// 手机号
		UserInfoPO user = conn_user.getUserByPhone(phone);
		if (user != null) {
			return FORBIDDEN("手机号码已被注册！");
		}
		// 对比验证码
		PhoneCodePO phoneCode = conn_phoneCode.getByPhoneReg(phone);
		if (phoneCode == null || phoneCode.getCode() == null) {
			return FORBIDDEN("验证码失效");
		}
		if (!phoneCode.getCode().equals(code)) {
			return FORBIDDEN("验证码错误！");
		}
		conn_phoneCode.delete(phoneCode);// 删除记录
		user = conn_user.get(userId);
		user.setUserPhone(phone);
		conn_user.saveOrUpdate(user);
		return success();
	}

	/**
	 * 上传用户头像
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/user/uploadHeading", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestParam("images") CommonsMultipartFile file, Long userId) throws Exception {
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("未获取到用户！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		// 创建日期文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = new Date();
		String path = conn_sysConfig.getSysConfig().getFolderUrl() + "app/user/" + userId;

		// 文件名
		String fileName = file.getOriginalFilename();
		String newName = sdf.format(d) + fileName.substring(fileName.lastIndexOf(".")); // 时间戳+后缀名

		File folder = new File(path);
		if (folder.exists() == false) { // 如果路径不存在
			if (folder.getParentFile().exists() == false) {
				map.put("code", "1");
				map.put("error", "文件路径错误！");
				return map;
			}
			folder.mkdir();
		}
		// 上传
		File newFile = new File(path + "/" + newName);
		file.transferTo(newFile); // 写

		String config = conn_sysConfig.getSysConfig().getWebUrl() + "app/" + userId + "/" + newName;
		// 写数据库
		user.setUserHeadimg(config);
		conn_user.saveOrUpdate(user);

		return success(config);
	}

	/***
	 * 订单：添加用户地址
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/address/add", method = RequestMethod.POST)
	public Map<String, Object> addAddress(HttpServletRequest request) {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long userId = Long.parseLong(pageObject.getString("userId"));
		String province = pageObject.getString("province");// 省
		String city = pageObject.getString("city");// 市
		String district = pageObject.getString("district");// 区
		String consigneeAddress = pageObject.getString("consigneeAddress");// 详细地址
		String phone = pageObject.getString("addressphone");
		String consigneeName = pageObject.getString("consigneeName");// 收货人
		String idNum = pageObject.getString("idNum");
		// 获取用户
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("未获取到用户！");
		}

		// 新建地址
		int count = conn_address.countByUser(userId, 1);
		AddressPO address = new AddressPO();
		address.setUpdateTime(new Date()); // 更新时间
		address.setConsigneePhone(phone);// 联系电话
		address.setConsigneeName(consigneeName);// 联系人
		address.setProvince(province);// 省
		address.setCity(city);// 市
		address.setDistrict(district);// 区
		address.setConsigneeAddress(consigneeAddress);// 详细地址
		address.setUserId(userId);
		if (idNum != null) {
			address.setIdNum(idNum);
		}
		if (count == 0) {
			address.setDefaultAddress(1); // 默认地址
		}
		conn_address.save(address);

		return success();
	}

	/***
	 * 订单：订单删除
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/order/delbyID", method = RequestMethod.POST)
	public Map<String, Object> delbyID(HttpServletRequest request) {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long userId = Long.parseLong(pageObject.getString("userId"));
		Long orderID = Long.parseLong(pageObject.getString("orderId"));

		// 获取用户
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("未获取到用户！");
		}
		String[] fields = { "id", "orderState", "userId" };
		Object[] values = { orderID, OrderStateType.NOTPAY, userId };

		OrderInfoPO orderPO = conn_order.getByFields(fields, values);
		if (orderPO != null) {
			if (orderPO.getActivityId() != 0) {
				conn_userone.deleteByUserAndDate(orderPO.getUserId(), orderPO.getActivityId(),
						orderPO.getOrderBookDate());
				ActivityRelPO actPro = conn_activityRel.get(orderPO.getActivityId());
				actPro.setDayStock(actPro.getDayStock() + (int) orderPO.getProductNum());
				conn_activityRel.save(actPro);
			} else {
				ProductPO productPO = conn_product.get(orderPO.getProductId());
				productPO.setProductStock(productPO.getProductStock() + orderPO.getProductNum());
				conn_product.save(productPO);
			}
			conn_order.delete(orderID);
			return success();
		}

		return ERROR("订单不存在或者当前状态不可删除");

	}

	/***
	 * 订单：修改预定时间
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/order/changetime", method = RequestMethod.POST)
	public Map<String, Object> changetime(HttpServletRequest request) throws ParseException {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String time = pageObject.getString("time");
		String end = pageObject.getString("end");
		Long orderID = Long.parseLong(pageObject.getString("orderId"));
		String[] time1 = time.split(",");
		time = time1[0].substring(2, 6) + "-" + time1[1].substring(1, 3) + "-" + time1[2].substring(1, 3) + " "
				+ time1[3].substring(1, 3) + ":" + time1[4].substring(1, 3);

		String[] fields = { "id", "orderState" };
		Object[] values = { orderID, OrderStateType.NOTPAY };

		OrderInfoPO orderPO = conn_order.getByFields(fields, values);
		if (orderPO != null) {
			if (end != null && end.length() != 0) {
				orderPO.setEndBookDate(DateUtil.parse(time, DateUtil.dateTimePattenWithoutSecind));
			} else {
				orderPO.setOrderBookDate(DateUtil.parse(time, DateUtil.dateTimePattenWithoutSecind));
			}
			conn_order.update(orderPO);
			return success();
		}

		return ERROR("订单不存在");

	}

	/***
	 * 订单：收货地址列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/address/list", method = RequestMethod.GET)
	public Map<String, Object> addressList(HttpServletRequest request, Long userId) throws Exception {
		List<AddressPO> addresses = conn_address.getByUserIdAndDelFlg(userId, 0);
		List<AddressVO> _addresses = AddressVO.getConverter(AddressVO.class).convert(addresses, AddressVO.class);
		return success(_addresses);
	}

	/***
	 * 订单：删除收货地址
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/address/delete", method = RequestMethod.POST)
	public Map<String, Object> addressDelete(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		long userId = Long.parseLong(pageObject.getString("userId"));
		long addressId = Long.parseLong(pageObject.getString("addressId"));
		List<AddressPO> polist = conn_address.getAddressByUserIdAndAddressId(userId, addressId);
		if (polist.size() <= 0) {
			return null;
		}
		polist.get(0).setDelFlg(1);
		conn_address.save(polist.get(0));
		// conn_address.delete(polist.get(0));
		return success();
	}

	/**
	 * 订单：加入购物车
	 * 
	 * @param request
	 * @param userId
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/joinBasket", method = RequestMethod.POST)
	public Map<String, Object> joinBasket(HttpServletRequest request) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String productId = pageObject.getString("productId");
		Long userId = Long.parseLong(pageObject.getString("userId"));
		// 可选
		String num = pageObject.getString("productNum");
		String addressId = pageObject.getString("addressId");
		String activityId = pageObject.getString("activityId");
		/*
		 * //4/26 新增的comId值 获取 Long comId=null;
		 * if(pageObject.getString("comId")!=null&&pageObject.getString("comId")
		 * !=""){ comId=Long.parseLong(pageObject.getString("comId")); }
		 */
		String comboId = pageObject.getString("comboId");
		String logisticsId = pageObject.getString("logisticsId");
		/*
		 * String productId = request.getParameter("productId"); String num =
		 * request.getParameter("num"); String paytype =
		 * request.getParameter("payType"); Long userId =
		 * Long.parseLong(request.getParameter("userId"));
		 */

		String roomId = pageObject.getString("roomId");
		String roomName = pageObject.getString("roomName");
		ProductPO productPO = conn_product.get(Long.parseLong(productId));
		if (num == null || num.length() == 0) {
			num = "1";
		}

		OrderInfoPO order = new OrderInfoPO();
		// 4/26新增的comId值 获取 张羽 4/28 添加退款限制
		ProductPO productPO2 = conn_product.get(Long.parseLong(productId));
		order.setComId(productPO2.getComId());
		order.setProductIsRefund(productPO2.getProductIsRefund());

		if (logisticsId != null) {
			order.setLogisticsId(Long.parseLong(logisticsId));
		}
		if (comboId != null) {
			order.setComboId(Long.parseLong(comboId));
		}

		String orderStartDate = pageObject.getString("startDate");
		if (orderStartDate != null && orderStartDate != "" && orderStartDate.length() != 0) {
			orderStartDate = orderStartDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderStartDate, DateUtil.dateTimePattenWithoutSecind));
		}
		String endBookDate = pageObject.getString("endDate");
		if (endBookDate != null && endBookDate != "" && endBookDate.length() != 0) {
			endBookDate = endBookDate.replace("T", " ");
			order.setEndBookDate(DateUtil.parse(endBookDate, DateUtil.dateTimePattenWithoutSecind));
		}
		if (roomId != null && roomId != "" && roomId.length() != 0) {

			order.setRoomId(Long.parseLong(roomId));
			order.setRoomName(roomName);

			RoomStatusPO roomStatus = new RoomStatusPO();
			roomStatus.setStartDate(order.getOrderBookDate());
			roomStatus.setEndDate(order.getEndBookDate());
			roomStatus.setRoomId(order.getRoomId());
			roomStatus.setStatus(1);
			conn_roomstatus.save(roomStatus);
			order.setRoomStatusId(roomStatus.getId());
		}

		// 配送信息
		if (addressId != null && addressId.length() != 0) {
			Long addressIdl = Long.parseLong(pageObject.getString("addressId"));
			order.setMailAddress(addressIdl);
		}
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		long productprice = productPO.getProductPrice();
		// 支付金额
		long payMoney = Integer.parseInt(num) * productprice;
		// 订单总金额
		long orderAllMoney = payMoney;
		// 获取产品

		// 获取商家
		MerchantPO merchant = conn_merchant.get(productPO.getProductMerchantID());

		// 获取用户
		UserInfoPO user = conn_user.get(userId);
		String orderBookDate = pageObject.getString("bookDate");
		if (orderBookDate != null && orderBookDate != "" && orderBookDate.length() != 0) {
			orderBookDate = orderBookDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
		}
		// 用户电话
		if (user.getUserPhone() != null) {
			order.setUserTel(user.getUserPhone());
		}

		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		String orderNO = getCityCodeByDomain() + merchant.getId() + productPO.getProductModularCode() + df.format(date)
				+ userId;
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
		//
		if (comboId != null && !comboId.equals("0")) {
			ProductComboPO comboPO = conn_combo.get(Long.parseLong(comboId));
			payMoney = Integer.parseInt(num) * (comboPO.getComboprice());
			orderAllMoney = payMoney;
			productprice = comboPO.getComboprice();
		}

		// 商品单价
		if (activityId != null) {

			UserOneDayBuyPO buyPO = new UserOneDayBuyPO();
			buyPO.setUpdateTime(new Date());
			buyPO.setUserId(userId);
			buyPO.setProId(Long.parseLong(activityId));
			if (orderBookDate != null && orderBookDate != "" && orderBookDate.length() != 0) {
				orderBookDate = orderBookDate.replace("T", " ");
				buyPO.setBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
			}
			conn_userone.save(buyPO);
			order.setActivityId(Long.parseLong(activityId));

			ActivityRelPO activityRelPO = new ActivityRelPO();
			activityRelPO = conn_activityRel.get(Long.parseLong(activityId));
			payMoney = Integer.parseInt(num) * (activityRelPO.getPrice());
			orderAllMoney = payMoney;
			productprice = activityRelPO.getPrice();
		}

		/*
		 * if (order.getOrderBookDate() != null && order.getEndBookDate() !=
		 * null) { long bet = DateUtil.daysBetween(order.getOrderBookDate(),
		 * order.getEndBookDate()); payMoney = payMoney * (bet+1); orderAllMoney
		 * = payMoney; }
		 */
		// 张羽 修改支付时的价钱按照页面的计算来 4/30
		long bet = Long.parseLong(pageObject.getString("payMoney"));
		payMoney = payMoney * bet;
		orderAllMoney = payMoney;

		order.setProductPrice(productprice);
		// 所属板块DI
		order.setBkCode(productPO.getProductModularCode());
		// 所属板块名称
		order.setBkName(productPO.getProductModularCodeName());
		// 套餐ID
		// 套餐名称

		// 会员ID
		order.setUserId(userId);

		// 会员信息
		// 会员坐标经度
		// 会员坐标维度
		// 提成金额比例
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
		// 有效期

		// 是否评价
		order.setCommentIs(0);
		// 预订日期
		// order.setOrderBookDate(date);

		String photo = pageObject.getString("photo");
		String idNum = pageObject.getString("idNum");
		if (photo != null) {
			order.setPhoto(URLDecoder.decode(photo));
		}
		if (idNum != null) {
			order.setIdNum(idNum);
		}

		// 订单来源
		order.setOrderType(OrderType.MERCHANT);
		if (pageObject.getString("source") != null) {
			order.setSource(OrderSource.fromString(pageObject.getString("source")));
		} else {
			order.setSource(OrderSource.APP);
		}
		conn_order.saveOrUpdate(order);
		JSONArray array = pageObject.getJSONArray("idnums");
		if (array != null) {
			for (Object obj : array) {
				JSONObject jobj = (JSONObject) obj;
				OrderPeoplePo orderPeoplePo = new OrderPeoplePo();
				orderPeoplePo.setIdNum(jobj.getString("idNum"));
				orderPeoplePo.setPhoto(URLDecoder.decode(jobj.getString("photo")));
				orderPeoplePo.setOrderId(order.getId());
				orderPeoplePo.setName(jobj.getString("name"));
				conn_orderPeople.save(orderPeoplePo);
			}
		}
		return success();
	}

	@Autowired
	SurpportBuyDao conn_surpportbuy;
	@Autowired
	RoomStatusDao conn_roomstatus;
	@Autowired
	OrderPeopleDao conn_orderPeople;

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
		String productId = pageObject.getString("productId");
		String num = pageObject.getString("productNum");
		Long userId = Long.parseLong(pageObject.getString("userId"));
		String paytype = pageObject.getString("paytype");
		String activityId = pageObject.getString("activityId");
		/*
		 * //4/26 新增的comId值 获取 Long comId=null;
		 * if(pageObject.getString("comId")!=null&&pageObject.getString("comId")
		 * !=""){ comId=Long.parseLong(pageObject.getString("comId")); }
		 */
		String comboId = pageObject.getString("comboId");
		String logisticsId = pageObject.getString("logisticsId");
		/*
		 * String productId = request.getParameter("productId"); String num =
		 * request.getParameter("num"); String paytype =
		 * request.getParameter("payType"); Long userId =
		 * Long.parseLong(request.getParameter("userId"));
		 */
		String roomId = pageObject.getString("roomId");
		String roomName = pageObject.getString("roomName");
		ProductPO productPO = conn_product.get(Long.parseLong(productId));
		if (num == null) {
			num = "1";
		}

		OrderInfoPO order = new OrderInfoPO();
		// 4/26添加comId 张羽 4/28 添加退款限制
		ProductPO productPO2 = conn_product.get(Long.parseLong(productId));
		order.setComId(productPO2.getComId());
		order.setProductIsRefund(productPO2.getProductIsRefund());

		if (logisticsId != null) {
			order.setLogisticsId(Long.parseLong(logisticsId));
		}
		if (comboId != null) {
			order.setComboId(Long.parseLong(comboId));
		}

		String orderStartDate = pageObject.getString("startDate");
		if (orderStartDate != null && orderStartDate != "" && orderStartDate.length() != 0) {
			orderStartDate = orderStartDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderStartDate, DateUtil.dateTimePattenWithoutSecind));
		}
		String endBookDate = pageObject.getString("endDate");
		if (endBookDate != null && endBookDate != "" && endBookDate.length() != 0) {
			endBookDate = endBookDate.replace("T", " ");
			order.setEndBookDate(DateUtil.parse(endBookDate, DateUtil.dateTimePattenWithoutSecind));
		}
		if (roomId != null && roomId != "" && roomId.length() != 0) {

			order.setRoomId(Long.parseLong(roomId));
			order.setRoomName(roomName);

			RoomStatusPO roomStatus = new RoomStatusPO();
			roomStatus.setStartDate(order.getOrderBookDate());
			roomStatus.setEndDate(order.getEndBookDate());
			roomStatus.setRoomId(order.getRoomId());
			roomStatus.setStatus(1);
			conn_roomstatus.save(roomStatus);
			order.setRoomStatusId(roomStatus.getId());
		}

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();

		long productprice = productPO.getProductPrice();
		// 支付金额
		long payMoney = Integer.parseInt(num) * productprice;
		// 订单总金额
		long orderAllMoney = payMoney;
		// 获取产品

		// 获取商家
		MerchantPO merchant = conn_merchant.get(productPO.getProductMerchantID());
		UserInfoPO user = conn_user.get(userId);
		String orderBookDate = pageObject.getString("bookDate");
		if (orderBookDate != null && orderBookDate != "" && orderBookDate.length() != 0) {
			orderBookDate = orderBookDate.replace("T", " ");
			order.setOrderBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
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

		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		String orderNO = getCityCodeByDomain() + merchant.getId() + productPO.getProductModularCode() + df.format(date)
				+ userId;
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
		if (comboId != null && !comboId.equals("0")) {
			ProductComboPO comboPO = conn_combo.get(Long.parseLong(comboId));
			payMoney = Integer.parseInt(num) * (comboPO.getComboprice());
			orderAllMoney = payMoney;
			productprice = comboPO.getComboprice();
		}
		// 获取用户
		if (activityId != null) {

			UserOneDayBuyPO buyPO = new UserOneDayBuyPO();
			buyPO.setUpdateTime(new Date());
			buyPO.setUserId(userId);
			buyPO.setProId(Long.parseLong(activityId));
			if (orderBookDate != null && orderBookDate != "" && orderBookDate.length() != 0) {
				orderBookDate = orderBookDate.replace("T", " ");
				buyPO.setBookDate(DateUtil.parse(orderBookDate, DateUtil.dateTimePattenWithoutSecind));
			}
			conn_userone.save(buyPO);
			order.setActivityId(Long.parseLong(activityId));
			conn_surpportbuy.delSurpport(userId, Long.parseLong(activityId));

			ActivityRelPO activityRelPO = new ActivityRelPO();
			activityRelPO = conn_activityRel.get(Long.parseLong(activityId));
			payMoney = Integer.parseInt(num) * (activityRelPO.getPrice());
			orderAllMoney = payMoney;
			productprice = activityRelPO.getPrice();
		}

		/*
		 * if (order.getOrderBookDate() != null && order.getEndBookDate() !=
		 * null) { long bet = DateUtil.daysBetween(order.getOrderBookDate(),
		 * order.getEndBookDate()); payMoney = payMoney * (bet+1); orderAllMoney
		 * = payMoney; }
		 */
		// 张羽 修改支付时的价钱按照页面的计算来 4/30
		long bet = Long.parseLong(pageObject.getString("payMoney"));
		payMoney = payMoney * bet;
		orderAllMoney = payMoney;
		System.out.println(orderAllMoney + "-------------------------");
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
		// 配送信息
		if (pageObject.getString("addressId") != null && pageObject.getString("addressId") != "") {
			Long addressId = Long.parseLong(pageObject.getString("addressId"));
			order.setMailAddress(addressId);
		}
		// 是否评价
		order.setCommentIs(0);
		// // 预订日期
		// order.setOrderBookDate(date);

		String photo = pageObject.getString("photo");
		String idNum = pageObject.getString("idNum");
		if (photo != null) {
			order.setPhoto(URLDecoder.decode(photo));
		}
		if (idNum != null) {
			order.setIdNum(idNum);
		}

		if (pageObject.getString("source") != null) {
			order.setSource(OrderSource.fromString(pageObject.getString("source")));
		} else {
			// 订单来源
			order.setSource(OrderSource.APP);
		}
		order.setOrderType(OrderType.MERCHANT);
		productPO.setProductSaleNum(productPO.getProductSaleNum() + 1);
		productPO.setProductShowNum(productPO.getProductShowNum() + 1);
		conn_product.update(productPO);
		conn_order.saveOrUpdate(order);

		JSONArray array = pageObject.getJSONArray("idnums");
		if (array != null) {
			for (Object obj : array) {
				JSONObject jobj = (JSONObject) obj;
				OrderPeoplePo orderPeoplePo = new OrderPeoplePo();
				orderPeoplePo.setIdNum(jobj.getString("idNum"));
				orderPeoplePo.setPhoto(URLDecoder.decode(jobj.getString("photo")));
				orderPeoplePo.setOrderId(order.getId());
				orderPeoplePo.setName(jobj.getString("name"));
				conn_orderPeople.save(orderPeoplePo);
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

	/**
	 * 订单：获取购物车
	 * 
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getBasket", method = RequestMethod.GET)
	public Map<String, Object> getBasket(HttpServletRequest request, Long userId, Integer page) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		List<OrderInfoPO> orders = conn_order.getBasket(userId, page, pageSize);
		int count = conn_order.countBasket(userId);
		for (OrderInfoPO orderInfoPO : orders) {
			orderInfoPO.setProductPic(sysConfig.getWebUrl() + orderInfoPO.getProductPic());
		}
		List<OrderInfoVO> _orders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orders, OrderInfoVO.class);
		dataMap.put("orders", _orders);
		dataMap.put("count", count);
		return success(_orders);
	}

	/**
	 * 订单：编辑购物车
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editBasket", method = RequestMethod.POST)
	public Map<String, Object> editBasket(HttpServletRequest request) {

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		long orderId = pageObject.getLong("orderId");
		long productId = pageObject.getLong("productId");
		int productNum = pageObject.getInteger("productNum");
		OrderInfoPO order = conn_order.get(orderId);
		ProductPO product = conn_product.get(productId);

		order.setProductNum(productNum); // 个数
		long payMoney = productNum * (product.getProductPrice());
		long orderAllMoney = payMoney;
		order.setOrderAllMoney(orderAllMoney); // 总金额
		order.setPayMoney(payMoney);
		// 用户获得的积分数
		long integralNum = productNum * product.getProductntegral();
		order.setIntegralNum(integralNum);
		// 商家获得的订单佣金金额(分)
		long proportionMoney;
		long proportion = product.getProductCommissionPrice();
		if (product.getProductCommissionCode() == 1) {
			proportionMoney = productNum * product.getProductPrice() * proportion / 100;
		} else {
			proportionMoney = productNum * proportion;
		}
		order.setProportionMoney(proportionMoney);
		conn_order.saveOrUpdate(order);
		return success();
	}

	/**
	 * 订单：结算
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/payAllOrder", method = RequestMethod.POST)
	public Map<String, Object> payAllOrder(HttpServletRequest request) throws Exception {

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String addressId = pageObject.getString("addressId"); // 配送地址
		String paytype = pageObject.getString("paytype");
		JSONArray retrievals = pageObject.getJSONArray("orders");// 解析json数组

		Long[] orderIds = new Long[retrievals.size()];// 订单号
		String[] nums = new String[retrievals.size()];// 商品数量
		String[] pNames = new String[retrievals.size()];// 商品名
		long allMoney = 0l;
		for (int i = 0; i < retrievals.size(); i++) {
			long orderId = retrievals.getJSONObject(i).getLong("orderId");
			/*
			 * long productId =
			 * retrievals.getJSONObject(i).getLong("productId"); long productNum
			 * = retrievals.getJSONObject(i).getLong("productNum");
			 */
			OrderInfoPO order = conn_order.get(orderId);
			/*
			 * ProductPO product = conn_product.get(productId);
			 * 
			 * order.setProductNum(productNum); //个数 long payMoney = productNum
			 * * (product.getProductPrice()); long orderAllMoney = payMoney;
			 * order.setOrderAllMoney(orderAllMoney); //总金额
			 * order.setPayMoney(payMoney); //总金额 // 用户获得的积分数 long integralNum =
			 * productNum * product.getProductntegral();
			 * order.setIntegralNum(integralNum); // 商家获得的订单佣金金额(分) long
			 * proportionMoney; long proportion =
			 * product.getProductCommissionPrice(); if
			 * (product.getProductCommissionCode() == 1) { proportionMoney =
			 * productNum * product.getProductPrice() * proportion / 100; } else
			 * { proportionMoney = productNum * proportion; }
			 * order.setProportionMoney(proportionMoney);
			 * conn_order.saveOrUpdate(order);
			 */
			if (addressId != null && addressId.length() != 0) {
				order.setMailAddress(Long.parseLong(addressId));
			}
			order.setPayMode(PayType.fromString(paytype));

			orderIds[i] = order.getId();
			nums[i] = String.valueOf(order.getProductNum());
			pNames[i] = order.getProductName();
			allMoney = allMoney + order.getPayMoney();
		}
		String orderIdStr = StringUtils.join(orderIds, "A");
		String numsStr = StringUtils.join(nums, "A");
		String pNamesStr = StringUtils.join(pNames, "A");
		System.out.println("numsStr：allMoney：pNamesStr：numsStr--" + numsStr + allMoney + pNamesStr + orderIdStr);
		// 调微信和支付宝
		if (paytype.equals("WEICHAT")) { // 微信
			return success(weichatPay(allMoney, orderIdStr));
		} else if (paytype.equals("ALIPAY")) { // 支付宝
			String sign = AliAppOrderInfo.getInstance().getSign(numsStr, allMoney, pNamesStr, orderIdStr);
			return success(sign);
		} else {
			return ERROR("系统错误！");
		}
	}

	/**
	 * 订单：删除购物车
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delBasket", method = RequestMethod.POST)
	public Map<String, Object> delBasket(HttpServletRequest request) throws Exception {

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String orderNO = pageObject.getString("orderNO"); // 订单号
		OrderInfoPO order = conn_order.appgGetByRoderNo(orderNO);
		if (order == null) {
			return FORBIDDEN("未获取到订单！");
		}
		System.out.println("订单状态：" + order.getOrderState());
		if (order.getOrderState() == null || !order.getOrderState().equals(OrderStateType.NOTPAY)) {
			return FORBIDDEN("订单状态：" + order.getOrderState());
		}
		conn_order.delete(order);
		return success();
	}

	/**
	 * 订单：获取订单
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/order/get", method = RequestMethod.GET)
	public Map<String, Object> getOrder(Long userId, int type, // 1.未支付;2.已支付;3.已发货;4.待退款;5.已退款;6.已收货;7.已评价
			String uType, String ifpay, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		List<OrderInfoVO> orders = new ArrayList<OrderInfoVO>(); // 已付款订单

		if (uType.equals("USER")) {
			switch (type) {
			case 1:// 未支付
				List<OrderInfoPO> orderingOrderpos = conn_order.getOrdersByState(userId, OrderStateType.NOTPAY);
				List<OrderInfoVO> orderingOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderingOrderpos,
						OrderInfoVO.class);
				List<OrderInfoVO> checkOrders = new ArrayList<OrderInfoVO>();
				for (OrderInfoVO orderInfoVO : orderingOrders) {
					if (!orderInfoVO.getOrderBookDate().equals("")) {
						Date bookDate = DateUtil.parse(orderInfoVO.getOrderBookDate(), "yyyy年MM月dd日 HH:mm:ss");
						long between = DateUtil.daysBetween(new Date(), bookDate);
						if (bookDate.getTime() < new Date().getTime()) {
							continue;
						}
					}

					orderInfoVO.setProductRestrictNumber(
							conn_product.get(orderInfoVO.getProductId()).getProductRestrictNumber());

					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
					if (orderInfoVO.getComboId() != 0) {
						ProductComboPO comboPO = conn_combo.get(orderInfoVO.getComboId());
						orderInfoVO.setProductPrice(
								new DecimalFormat("0.00").format((double) comboPO.getComboprice() / 100));
						orderInfoVO.setComboName(comboPO.getCombo());
					} else {
						orderInfoVO.setComboName("标准");
					}
					LogisticsPo logisticsPo = conn_logistics.get(orderInfoVO.getLogisticsId());
					if (logisticsPo != null) {
						orderInfoVO.setLogisticsName(logisticsPo.getName());
					} else {
						orderInfoVO.setLogisticsName("-");
					}
					if (orderInfoVO.getActivityId() != 0) {
						ActivityRelPO activityRelPO = conn_activityRel.get(orderInfoVO.getActivityId());
						if (activityRelPO != null) {
							Date setDate = DateUtil.parse(orderInfoVO.getUpdateTime(), DateUtil.dateTimePattern);
							long between = DateUtil.daysBetween(setDate, new Date());
							if ((between * 24) > activityRelPO.getExpireTime()) {
								OrderInfoPO innerOrderInfoPO = conn_order.get(orderInfoVO.getId());
								conn_userone.deleteByUserAndDate(innerOrderInfoPO.getUserId(),
										innerOrderInfoPO.getActivityId(), innerOrderInfoPO.getOrderBookDate());
								ActivityRelPO actPro = conn_activityRel.get(innerOrderInfoPO.getActivityId());
								actPro.setDayStock(actPro.getDayStock() + (int) innerOrderInfoPO.getProductNum());
								conn_activityRel.save(actPro);

								continue;
							}
							orderInfoVO.setProductPrice(
									new DecimalFormat("0.00").format((double) activityRelPO.getPrice() / 100));
						} else {
							continue;
						}

					}
					checkOrders.add(orderInfoVO);
				}
				orders = checkOrders;
				break;

			case 2:// 已支付
				List<OrderInfoPO> orderedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.PAYSUCCESS);
				List<OrderInfoPO> orderedOrderpo2 = conn_order.getOrdersByState(userId, OrderStateType.PAYFINISH);

				if (orderedOrderpo2.size() > 0) {
					orderedOrderpos.addAll(orderedOrderpo2);
				}

				List<OrderInfoVO> orderedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderedOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : orderedOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}

				orders = orderedOrders;
				break;
			case 3:// 已发货
				List<OrderInfoPO> deliverOrderpos = conn_order.getOrdersByState(userId, OrderStateType.DELIVER);
				List<OrderInfoVO> deliverOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(deliverOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : deliverOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = deliverOrders;
				break;

			case 4:// 待退款
				List<OrderInfoPO> refundingOrderpos = conn_order.getOrdersByState(userId, OrderStateType.REFUNDING);
				List<OrderInfoVO> refundingOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(refundingOrderpos, OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : refundingOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = refundingOrders;
				break;
			case 5:// 已退款
				List<OrderInfoPO> refundedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.REFUNDED);
				List<OrderInfoVO> refundedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(refundedOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : refundedOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = refundedOrders;
				break;
			case 6:// 已收货
				List<OrderInfoPO> receiptOrderpos = conn_order.getOrdersByState(userId, OrderStateType.RECEIPT);
				List<OrderInfoVO> receiptOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(receiptOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : receiptOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = receiptOrders;
				break;
			case 7:// 已评价
				List<OrderInfoPO> commentedOrderpos = conn_order.getOrdersByState(userId, OrderStateType.COMMENTED);
				List<OrderInfoVO> commentedOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(commentedOrderpos, OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : commentedOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = commentedOrders;
				break;
			case 8:// 已验单
				List<OrderInfoPO> testOrderpos = conn_order.getOrdersByState(userId, OrderStateType.TESTED);
				List<OrderInfoVO> testOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(testOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : testOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = testOrders;
				break;

			default:
				break;
			}
		} else if (uType.equals("MERCHANT")) {
			UserInfoPO user = conn_user.get(userId);
			MerchantPO merchant = user.getMerchant();
			if (merchant == null) {
				return FORBIDDEN("该用户不是商户！");
			}
			long merchantId = merchant.getId();
			switch (type) {
			case 1:// 未支付
				List<OrderInfoPO> orderingOrderpos = conn_order.getOrdersByMerState(merchantId, OrderStateType.NOTPAY);
				List<OrderInfoVO> orderingOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderingOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : orderingOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
					if (orderInfoVO.getComboId() != 0) {
						ProductComboPO comboPO = conn_combo.get(orderInfoVO.getComboId());
						orderInfoVO.setProductPrice(
								new DecimalFormat("0.00").format((double) comboPO.getComboprice() / 100));
						orderInfoVO.setComboName(comboPO.getCombo());
					} else {
						orderInfoVO.setComboName("标准");
					}
					LogisticsPo logisticsPo = conn_logistics.get(orderInfoVO.getLogisticsId());
					if (logisticsPo != null) {
						orderInfoVO.setLogisticsName(logisticsPo.getName());
					} else {
						orderInfoVO.setLogisticsName("-");
					}

					if (orderInfoVO.getActivityId() != 0) {
						ActivityRelPO activityRelPO = conn_activityRel.get(orderInfoVO.getActivityId());
						orderInfoVO
								.setProductPrice(new DecimalFormat("0.00").format((double) activityRelPO.getPrice()));
					}
				}
				orders = orderingOrders;
				break;

			case 2:// 已支付
				List<OrderInfoPO> orderedOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.PAYSUCCESS);
				List<OrderInfoPO> orderedOrderpo2 = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.PAYFINISH);
				orderedOrderpos.addAll(orderedOrderpo2);
				List<OrderInfoVO> orderedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderedOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : orderedOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = orderedOrders;
				break;
			case 3:// 已发货
				List<OrderInfoPO> deliverOrderpos = conn_order.getOrdersByMerState(merchantId, OrderStateType.DELIVER);
				List<OrderInfoVO> deliverOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(deliverOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : deliverOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = deliverOrders;
				break;

			case 4:// 待退款
				List<OrderInfoPO> refundingOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.REFUNDING);
				List<OrderInfoVO> refundingOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(refundingOrderpos, OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : refundingOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = refundingOrders;
				break;
			case 5:// 已退款
				List<OrderInfoPO> refundedOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.REFUNDED);
				List<OrderInfoVO> refundedOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(refundedOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : refundedOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = refundedOrders;
				break;
			case 6:// 已收货
				List<OrderInfoPO> receiptOrderpos = conn_order.getOrdersByMerState(merchantId, OrderStateType.RECEIPT);
				List<OrderInfoVO> receiptOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(receiptOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : receiptOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = receiptOrders;
				break;
			case 7:// 已评价
				List<OrderInfoPO> commentedOrderpos = conn_order.getOrdersByMerState(merchantId,
						OrderStateType.COMMENTED);
				List<OrderInfoVO> commentedOrders = OrderInfoVO.getConverter(OrderInfoVO.class)
						.convert(commentedOrderpos, OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : commentedOrders) {

					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = commentedOrders;
				break;
			case 8:// 已验单
				List<OrderInfoPO> testOrderpos = conn_order.getOrdersByMerState(merchantId, OrderStateType.TESTED);
				List<OrderInfoVO> testOrders = OrderInfoVO.getConverter(OrderInfoVO.class).convert(testOrderpos,
						OrderInfoVO.class);

				for (OrderInfoVO orderInfoVO : testOrders) {
					orderInfoVO.setProductPic(sysConfig.getWebUrl() + orderInfoVO.getProductPic());
				}
				orders = testOrders;
				break;

			default:
				break;
			}
		} else {
			return FORBIDDEN("错误的用户类型！");
		}
		// 移除购物车中到店支付订单

		if (ifpay.equals("false")) {
			for (int i = 0; i < orders.size(); i++) {
				if (orders.get(i).getProductName() == null) {
					orders.remove(orders.get(i));
				}
			}
		}
		for (OrderInfoVO orderInfoVO : orders) {

			if (orderInfoVO.getProductName() == null) {
				orderInfoVO.setProductName("(到店支付)" + orderInfoVO.getShopName());
				orderInfoVO.setProductPrice(orderInfoVO.getPayMoney());
				orderInfoVO.setProductNum(1);
			}
		}
		return success(orders);

	}

	/**
	 * 订单：订单详情
	 * 
	 * @param orderId
	 * @return order 邮寄地址address
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/orderInfo", method = RequestMethod.GET)
	public Map<String, Object> orderInfo(HttpServletRequest request, Long orderId) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 订单
		OrderInfoPO order = conn_order.get(orderId);

		// 邮寄地址
		AddressPO address = conn_address.get(order.getMailAddress());
		if (address != null) {
			AddressVO _address = new AddressVO().set(address);
			dataMap.put("address", _address);
		} else {
			dataMap.put("address", null);
		}

		// 商家
		MerchantPO merchantPO = conn_merchant.get(order.getShopId());

		OrderInfoVO _order = new OrderInfoVO().set(order);
		if (_order.getProductName() == null) {
			_order.setProductName("");
		}
		_order.setYdNO(sysConfig.getWebUrl() + _order.getYdNO());
		_order.setProductPic(sysConfig.getWebUrl() + _order.getProductPic());
		_order.setShopLongitude(merchantPO.getShopLongitude());
		_order.setShopLatitude(merchantPO.getShopLatitude());

		if (_order.getComboId() != 0) {
			ProductComboPO comboPO = conn_combo.get(_order.getComboId());
			if (comboPO != null) {
				_order.setProductPrice(new DecimalFormat("0.00").format((double) comboPO.getComboprice() / 100));
				_order.setComboName(comboPO.getCombo());
			}

		} else {
			_order.setComboName("标准");
		}
		LogisticsPo logisticsPo = conn_logistics.get(_order.getLogisticsId());
		if (logisticsPo != null) {
			_order.setLogisticsName(logisticsPo.getName());
		} else {
			_order.setLogisticsName("-");
		}

		if (_order.getActivityId() != 0) {
			ActivityRelPO activityRelPO = conn_activityRel.get(_order.getActivityId());
			if (activityRelPO != null) {
				_order.setProductPrice(new DecimalFormat("0.00").format((double) activityRelPO.getPrice() / 100));
			}
		}
		dataMap.put("order", _order);
		return success(dataMap);
	}

	/**
	 * 订单：更改订单状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/order/updateStatus", method = RequestMethod.POST)
	public Map<String, Object> updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String orderNO = pageObject.getString("orderNO");
		String orderStatus = pageObject.getString("orderStatus");

		OrderInfoPO orderInfoPO = conn_order.getOrderByNo(orderNO);
		if (orderInfoPO == null) {
			return FORBIDDEN("未获取到订单！");
		}
		Date date = new Date();
		String orderStatuStr = orderInfoPO.getOrderState().getFiled();
		switch (orderStatus) {
		case "DELIVER":// 发货
			System.out.println("订单状态:" + orderStatuStr);
			if (!orderStatuStr.equals("PAYSUCCESS") && !orderStatuStr.equals("PAYFINISH")) {
				return FORBIDDEN("订单状态出错！现在的订单状态是：" + orderStatuStr);
			}
			orderInfoPO.setSendDate(date); // 发货时间
			orderInfoPO.setOrderState(OrderStateType.fromString(orderStatus));
			conn_order.update(orderInfoPO);
			break;
		case "REFUNDING":// 退款
			// 条件
			orderInfoPO.setOrderState(OrderStateType.fromString(orderStatus));
			orderInfoPO.setRefundReason(pageObject.getString("refundReason")); // 退款原因
			conn_order.update(orderInfoPO);
			break;
		case "RECEIPT":// 确认收货
			// 条件
			orderInfoPO.setDealDate(date); // 成交时间
			orderInfoPO.setOrderState(OrderStateType.fromString(orderStatus));
			conn_order.update(orderInfoPO);
			break;
		case "REFUNDED":// 商家同意退款
			// 条件
			orderInfoPO.setDealDate(date); // 成交时间
			// ???怎么退款
			orderInfoPO.setOrderState(OrderStateType.fromString(orderStatus));
			conn_order.update(orderInfoPO);
			break;
		case "REFUNDFAIL":// 商家拒绝退款
			// 条件
			// ???拒绝退款咋办
			orderInfoPO.setDealDate(date); // 成交时间
			orderInfoPO.setOrderState(OrderStateType.fromString(orderStatus));
			conn_order.update(orderInfoPO);
			break;
		case "COMMENTED":
			orderInfoPO.setOrderState(OrderStateType.fromString(orderStatus));
			conn_order.update(orderInfoPO);
			break;
		default:
			return FORBIDDEN("orderStatus参数出错！");
		}
		return success(orderInfoPO);
	}

	/**
	 * 订单：验单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/order/ydNow", method = RequestMethod.POST)
	public Map<String, Object> ydNow(HttpServletRequest request, HttpServletResponse response) throws Exception {

		OrderInfoPO orderInfoPO = null;

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}

		JSONObject pageObject = JSON.parseObject(param);

		String userId = pageObject.getString("userId");
		String orderNO = pageObject.getString("orderNo");
		// 获取订单
		orderInfoPO = conn_order.get(Long.parseLong(orderNO));
		if (orderInfoPO != null) {

			// 判断商家id是否一致
			if (userId != null) {
				long _userId = orderInfoPO.getShopId();
				MerchantPO merchantPO = conn_merchant.getMerByUser(conn_user.get(Long.parseLong(userId)));
				if (_userId != merchantPO.getId()) {
					String message = "这条订单不属于您！";
					return FORBIDDEN(message);
				}
			}
			if (orderInfoPO.getOrderBookDate() != null && orderInfoPO.getProductId() != 0) {
				MerchantPO merchantPO = conn_merchant.get(orderInfoPO.getShopId());
				if (merchantPO.getModularCode().equals("0001") || merchantPO.getModularCode().equals("0002")) {
					String today = DateUtil.format(new Date(), "yyyy-MM-dd");
					Date sDate = DateUtil.parse(today + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
					Date eDate = DateUtil.parse(today + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
					if (sDate.after(orderInfoPO.getOrderBookDate()) || eDate.before(orderInfoPO.getOrderBookDate())) {
						String message = "这条订单日期不是今天！";
						return FORBIDDEN(message);
					}
				}

			}

			// 判断订单状态？？？
			if (!orderInfoPO.getOrderState().equals(OrderStateType.PAYFINISH)
					&& !orderInfoPO.getOrderState().equals(OrderStateType.PAYSUCCESS)) {
				return FORBIDDEN("订单状态是：" + orderInfoPO.getOrderState());
			}
			// 更改订单的状态，验单时间
			orderInfoPO.setOrderState(OrderStateType.TESTED);
			Date date = new Date();
			orderInfoPO.setYdDate(date);
			conn_order.saveOrUpdate(orderInfoPO);
			sendMessage(orderInfoPO);
			return success(orderInfoPO.getOrderState());
		} else {
			String message = "订单信息不对！";
			return FORBIDDEN(message);
		}

	}

	@Autowired
	private MerchantUserDao conn_merchantUser;

	private void sendMessage(OrderInfoPO orderInfoPO) {

		ProductPO productPO = conn_product.get(orderInfoPO.getProductId());
		MerchantPO merchantPO = conn_merchant.get(orderInfoPO.getShopId());

		// 用户推送消息
		Double amount = Double.parseDouble(orderInfoPO.getPayMoney() + "") / 100;
		DecimalFormat df = new DecimalFormat("0.00");
		UserInfoPO buyUser = conn_user.get(orderInfoPO.getUserId());
		if (buyUser != null) {
			JSONObject obj = new JSONObject();
			obj.put("touser", buyUser.getUserOpenID());
			obj.put("template_id", "imgTupyObgSuKRYqZrc8VAXgzGePPEeuqwVG7IF_Rzw");
			obj.put("url", "");
			JSONObject microProObj = new JSONObject();
			microProObj.put("appid", "");
			microProObj.put("pagepath", "");
			obj.put("miniprogram", microProObj);
			JSONObject dataObject = new JSONObject();
			JSONObject firstObj = new JSONObject();
			firstObj.put("value", "您的订单验单完成");
			firstObj.put("color", "");
			dataObject.put("first", firstObj);
			String nowDate = DateUtil.format(new Date(), DateUtil.dateTimePattern);

			JSONObject nameObj = new JSONObject();
			nameObj.put("value", nowDate);
			nameObj.put("color", "");
			dataObject.put("keyword2", nameObj);

			JSONObject accountTypeObj = new JSONObject();
			accountTypeObj.put("value", orderInfoPO.getId());
			accountTypeObj.put("color", "");
			dataObject.put("keyword1", accountTypeObj);

			JSONObject remarkObj = new JSONObject();
			remarkObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName()
					: productPO.getProductName() + "(" + df.format(amount) + "元)");
			remarkObj.put("color", "");
			dataObject.put("remark", remarkObj);
			obj.put("data", dataObject);
			SendMsgUtil.sendTemplate(obj.toJSONString());
		}

		// 商户推送消息

		List<MerchantUser> merchantUsers = conn_merchantUser.findByField("merchantId", merchantPO.getId());
		for (MerchantUser merchantUser : merchantUsers) {
			UserInfoPO userInfoPO = conn_user.get(merchantUser.getUserId());
			if (userInfoPO == null) {
				continue;
			}
			JSONObject obj = new JSONObject();
			obj.put("touser", userInfoPO.getUserOpenID());
			obj.put("template_id", "imgTupyObgSuKRYqZrc8VAXgzGePPEeuqwVG7IF_Rzw");
			obj.put("url", "");
			JSONObject microProObj = new JSONObject();
			microProObj.put("appid", "");
			microProObj.put("pagepath", "");
			obj.put("miniprogram", microProObj);
			JSONObject dataObject = new JSONObject();
			JSONObject firstObj = new JSONObject();
			firstObj.put("value", "订单验单完成");
			firstObj.put("color", "");
			dataObject.put("first", firstObj);
			String nowDate = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");

			JSONObject nameObj = new JSONObject();
			nameObj.put("value", nowDate);
			nameObj.put("color", "");
			dataObject.put("keyword2", nameObj);

			JSONObject accountTypeObj = new JSONObject();
			accountTypeObj.put("value", orderInfoPO.getId());
			accountTypeObj.put("color", "");
			dataObject.put("keyword1", accountTypeObj);

			JSONObject remarkObj = new JSONObject();
			remarkObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName()
					: productPO.getProductName() + "(" + df.format(amount) + "元)");
			remarkObj.put("color", "");
			dataObject.put("remark", remarkObj);
			obj.put("data", dataObject);
			SendMsgUtil.sendTemplate(obj.toJSONString());
		}

		JSONObject obj = new JSONObject();
		obj.put("touser", "opVUYv9LtqKAbiaXInBqI01hlpYg");
		obj.put("template_id", "imgTupyObgSuKRYqZrc8VAXgzGePPEeuqwVG7IF_Rzw");
		obj.put("url", "");
		JSONObject microProObj = new JSONObject();
		microProObj.put("appid", "");
		microProObj.put("pagepath", "");
		obj.put("miniprogram", microProObj);
		JSONObject dataObject = new JSONObject();
		JSONObject firstObj = new JSONObject();
		firstObj.put("value", "订单验单完成");
		firstObj.put("color", "");
		dataObject.put("first", firstObj);
		String nowDate = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");

		JSONObject nameObj = new JSONObject();
		nameObj.put("value", nowDate);
		nameObj.put("color", "");
		dataObject.put("keyword2", nameObj);

		JSONObject accountTypeObj = new JSONObject();
		accountTypeObj.put("value", orderInfoPO.getId());
		accountTypeObj.put("color", "");
		dataObject.put("keyword1", accountTypeObj);

		JSONObject remarkObj = new JSONObject();
		remarkObj.put("value", productPO == null ? "到店支付订单:" + merchantPO.getShopName()
				: productPO.getProductName() + "(" + df.format(amount) + "元)");
		remarkObj.put("color", "");
		dataObject.put("remark", remarkObj);
		obj.put("data", dataObject);
		SendMsgUtil.sendTemplate(obj.toJSONString());

		// opVUYv9LtqKAbiaXInBqI01hlpYg
	}

	/**
	 * 订单：用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public Map<String, Object> getUser(HttpServletRequest request, HttpServletResponse response, Long userId)
			throws Exception {
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("未获取到用户！");
		}
		UserInfoVO _user = new UserInfoVO().set(user);
		return success(_user);
	}

	/**
	 * 订单：编辑用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public Map<String, Object> editUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		long userId = pageObject.getLong("userId"); // 用户Id
		String userPhone = pageObject.getString("userPhone");// 用户地址
		String userHeadimg = pageObject.getString("userHeadimg");// 用户头像
		String userNickname = pageObject.getString("userNickname");// 用户昵称
		String trueName = pageObject.getString("trueName");// 真实姓名
		String companyName = pageObject.getString("companyName");// 工作单位
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("未获取到用户！");
		}

		if (userPhone != null) {
			user.setUserPhone(userPhone);
		}
		if (userHeadimg != null) {
			user.setUserHeadimg(userHeadimg);
		}
		if (userNickname != null) {
			user.setUserNickname(userNickname);
		}
		if (trueName != null) {
			user.setTrueName(trueName);
		}
		if (companyName != null) {
			user.setCompanyName(companyName);
		}

		conn_user.saveOrUpdate(user);
		return success();

	}

	/**
	 * 添加评论
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public Map<String, Object> comment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);

		long userId = pageObject.getLong("userId"); // 用户Id
		long proId = pageObject.getLong("productId"); // 商品Id
		String content = pageObject.getString("content");// 评论内容
		int start = pageObject.getInteger("start");// 星级
		// commentMPic评论图片？？

		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("未获取到用户！");
		}
		CommentPO comment = new CommentPO();
		Date date = new Date();
		comment.setUpdateTime(date);
		comment.setContent(content); // 评论内容
		comment.setStart(start);// 评论星级
		comment.setProId(proId);// 商品Id
		comment.setUserDate(date);// 评论时间
		comment.setUser(user);
		conn_comment.saveOrUpdate(comment);
		return success();
	}

	/**
	 * 订单：收藏商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/collectionPro", method = RequestMethod.POST)
	public Map<String, Object> collectionPro(HttpServletRequest request, HttpServletResponse response) {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);

		long userId = pageObject.getLong("userId"); // 用户Id
		long proId = pageObject.getLong("productId"); // 商品Id
		long activityproductId = pageObject.getLong("activityproductId");

		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("没获取到用户！");
		}

		boolean b = true;
		for (CollectionPO cp : user.getCollections()) {
			if (cp.getProductId() == proId) {
				b = false;
			}
		}
		if (b) {
			CollectionPO collection = new CollectionPO();

			collection.setProductId(proId);
			collection.setUpdateTime(new Date());
			if (activityproductId != 0) {
				collection.setActivityproductId(activityproductId);
			}

			user.getCollections().add(collection);
			collection.setUser(user);
			conn_user.save(user);
			conn_collection.save(collection);
		}
		return success();
	}

	@ResponseBody
	@RequestMapping(value = "/mercollectionPro", method = RequestMethod.POST)
	public Map<String, Object> mercollectionPro(HttpServletRequest request, HttpServletResponse response) {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);

		long userId = pageObject.getLong("userId"); // 用户Id
		long merId = pageObject.getLong("merId"); // 商品Id

		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("没获取到用户！");
		}

		boolean b = true;
		for (CollectionPO cp : user.getCollections()) {
			if (cp.getMerchantId() == merId) {
				b = false;
			}
		}
		if (b) {
			CollectionPO collection = new CollectionPO();

			collection.setMerchantId(merId);
			collection.setUpdateTime(new Date());

			user.getCollections().add(collection);
			collection.setUser(user);
			conn_user.save(user);
			conn_collection.save(collection);
		}
		return success();
	}

	/**
	 * 订单：取消收藏
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delCollectionPro", method = RequestMethod.POST)
	public Map<String, Object> delCollectionPro(HttpServletRequest request, HttpServletResponse response) {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);

		long userId = pageObject.getLong("userId"); // 用户Id
		long collId = pageObject.getLong("collId"); // 收藏Id

		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("没获取到用户！");
		}
		CollectionPO collectionPO = conn_collection.get(collId);
		if (collectionPO == null) {
			return FORBIDDEN("没获取收藏！");
		}
		if (collectionPO.getUser().getId() != userId) {
			return FORBIDDEN("非法操作!");
		}
		// conn_collection.deleteByUser(user,collId);//级联删除还用加吗?
		conn_collection.delete(collId);
		return success();
	}

	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/delmerCollectionPro", method = RequestMethod.POST)
	public Map<String, Object> delmerCollectionPro(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 解析json
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();

		long userId = pageObject.getLong("userId"); // 用户Id
		long merId = pageObject.getLong("merId"); // 收藏Id

		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("没获取到用户！");
		}
		CollectionPO collectionPO = conn_collection.getByUserIdMerId(userId, merId);
		if (collectionPO == null) {
			return FORBIDDEN("没获取收藏！");
		}
		if (collectionPO.getUser().getId() != userId) {
			return FORBIDDEN("非法操作!");
		}

		conn_collection.delete(collectionPO);

		return success();
	}

	// 全选删除 商品
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/alldelete", method = RequestMethod.POST)
	public Map<String, Object> allDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析json
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		List<Long> list = new ArrayList<Long>();
		long userId = pageObject.getLong("userId"); // 用户Id
		String productId = pageObject.getString("productId");// 商品
		String[] strArr = productId.split("-");
		for (String string : strArr) {
			list.add(Long.parseLong(string));
		}
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("没获取到用户！");
		}
		List<CollectionPO> collectionPO = conn_collection.getByUserIdProduct(userId, list);
		System.out.println(collectionPO.size());
		for (CollectionPO collectionPO2 : collectionPO) {
			if (collectionPO2 == null) {
				return FORBIDDEN("没获取收藏！");
			}
			if (collectionPO2.getUser().getId() != userId) {
				return FORBIDDEN("非法操作!");
			}
			System.out.println(collectionPO2.getId());
			conn_collection.delete(collectionPO2);
		}
		return success();
	}

	// 全选删除 商家
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "/merdelete", method = RequestMethod.POST)
	public Map<String, Object> merdelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析json
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		List<Long> list = new ArrayList<Long>();
		long userId = pageObject.getLong("userId"); // 用户Id
		String merId = pageObject.getString("merId");// 商品
		String[] strArr = merId.split("-");
		for (String string : strArr) {
			list.add(Long.parseLong(string));
		}
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("没获取到用户！");
		}
		List<CollectionPO> collectionPO = conn_collection.getByUserIdMer(userId, list);
		System.out.println(collectionPO.size());
		for (CollectionPO collectionPO2 : collectionPO) {
			if (collectionPO2 == null) {
				return FORBIDDEN("没获取收藏！");
			}
			if (collectionPO2.getUser().getId() != userId) {
				return FORBIDDEN("非法操作!");
			}
			System.out.println(collectionPO2.getId());
			conn_collection.delete(collectionPO2);
		}
		return success();
	}

	/**
	 * 订单：我的收藏
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "getCollection", method = RequestMethod.GET)
	public Map<String, Object> getCollection(HttpServletRequest request, HttpServletResponse response, Long userId)
			throws Exception {
		UserInfoPO user = conn_user.get(userId);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		List<CollectionPO> collections = user.getCollections();
		List<ProductVO> _products = new ArrayList<ProductVO>();
		for (CollectionPO collectionPO : collections) {
			if (collectionPO.getProductId() != 0) {
				ProductPO product = conn_product.get(collectionPO.getProductId());
				if (product == null) {
					continue;
				}
				ProductVO _product = new ProductVO().set(product);
				_product.setisactivityproduct(0);
				if (collectionPO.getActivityproductId() != 0) {
					ActivityRelPO activityRelPO = conn_activityRel.get(collectionPO.getActivityproductId());
					if (activityRelPO == null) {
						conn_collection.delete(collectionPO);
						continue;
					}
					_product.setActivityReId(activityRelPO.getId());
					_product.setProductPrice(String.valueOf((double) activityRelPO.getPrice() / 100));
					_product.setisactivityproduct(1);
				}
				// 图片
				_product.setProductShowPic(sysConfig.getWebUrl() + _product.getProductShowPic());
				// 多图
				_product.setProductMorePic(split(_product.getProductMorePic(), sysConfig.getWebUrl()));
				_product.setProductMerchantJson(collectionPO.getId().toString());
				_products.add(_product);
			}

		}
		return success(_products);
	}

	@ResponseBody
	@RequestMapping(value = "getmerCollection", method = RequestMethod.GET)
	public Map<String, Object> getmerCollection(HttpServletRequest request, HttpServletResponse response, Long userId)
			throws Exception {
		UserInfoPO user = conn_user.get(userId);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		List<CollectionPO> collections = user.getCollections();
		List<MerchantVO> _merchants = new ArrayList<MerchantVO>();
		for (CollectionPO collectionPO : collections) {
			if (collectionPO.getMerchantId() != 0) {
				MerchantPO merchantPO = conn_merchant.get(collectionPO.getMerchantId());
				MerchantVO _merchant = new MerchantVO().set(merchantPO);
				// 图片

				_merchant.setShopPic(sysConfig.getWebUrl() + _merchant.getShopPic());// 商家显示图片
				// _merchant.setShopMpic(split(_merchant.getShopMpic(),
				// sysConfig.getWebUrl()));// 商家多图

				_merchants.add(_merchant);
			}
		}
		return success(_merchants);
	}

	/**
	 * 获取积分商品
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param comId
	 * @return GOLDNUM金币数
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/gold/products", method = RequestMethod.GET)
	public Map<String, Object> goldProducts(HttpServletRequest request, HttpServletResponse response, Integer page,
			Long comId) throws Exception {
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<ProductPO> products = conn_product.findByPsTypeCom(comId, page, pageSize);
		List<ProductVO> _products = ProductVO.getConverter(ProductVO.class).convert(products, ProductVO.class);
		for (ProductVO productVO : _products) {
			productVO.setProductShowPic(sysConfig.getWebUrl() + productVO.getProductShowPic());
		}
		int count = conn_product.countByPsTypeCom(comId);
		dataMap.put("products", _products);
		dataMap.put("count", count);
		return success(dataMap);
	}

	/**
	 * 对换积分商品
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param comId
	 * @return GOLDNUM金币数
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/convert/goldProduct", method = RequestMethod.POST)
	public Map<String, Object> convertGoldProduct(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long userId = pageObject.getLong("userId");
		Long productId = pageObject.getLong("productId");

		Map<String, Object> dataMap = new HashMap<String, Object>();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();

		UserInfoPO user = conn_user.get(userId);
		// 用户积分
		long userIntegral = user.getUserIntegral();
		ProductPO product = conn_product.getByGold(productId);
		if (product == null) {
			return FORBIDDEN("为获取到商品!");
		}
		long goldNum = product.getGoldNum();
		if (goldNum > userIntegral) {
			return FORBIDDEN("用户积分不足");
		}

		// 1.修改用户积分
		user.setUserIntegral(userIntegral - goldNum);
		// 2.生成一个已支付的订单
		OrderInfoPO order = new OrderInfoPO();
		order.setUpdateTime(new Date());

		// 支付金额
		long payMoney = product.getGoldNum();
		// 订单总金额
		long orderAllMoney = payMoney;
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

		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		String orderNO = getCityCodeByDomain() + "01" + "0000" + df.format(date) + userId;
		order.setOrderNO(orderNO);
		// 验单码
		// 下单时间
		order.setCreateDate(date);
		order.setUpdateTime(date);
		// 验单时间

		// 商品ID
		order.setProductId(product.getId());
		// 商品图片
		order.setProductPic(product.getProductShowPic());
		// 商品名称
		order.setProductName(product.getProductName());
		// 商品数量
		order.setProductNum(1);
		// 商品单价
		order.setProductPrice(product.getGoldNum());
		// 支付金额
		order.setPayMoney(payMoney);
		// 订单总金额
		order.setOrderAllMoney(orderAllMoney);
		// 订单状态
		order.setOrderState(OrderStateType.PAYSUCCESS);
		// 订单支付类型 //ALIPAY WEICHAT
		order.setPayMode(PayType.GOLDPAY);
		order.setOrderType(OrderType.MERCHANT);
		// 有效期
		// 配送信息
		if (pageObject.getString("addressId") != null && pageObject.getString("addressId") != "") {
			Long addressId = Long.parseLong(pageObject.getString("addressId"));
			order.setMailAddress(addressId);
		}
		/*
		 * if(request.getParameter("addressId")!=null&&request.getParameter(
		 * "addressId").length()>0){ Long addressId =
		 * Long.parseLong(request.getParameter("addressId"));
		 * order.setMailAddress(addressId); }
		 */

		// 是否评价
		order.setCommentIs(0);
		// 预订日期
		order.setOrderBookDate(date);

		// 订单来源
		order.setSource(OrderSource.APP);
		conn_order.saveOrUpdate(order);
		conn_user.saveOrUpdate(user);
		dataMap.put("order", order);
		return success(dataMap);
	}

	/**
	 * 获取直播列表(包含专业直播和普通直播)
	 * 
	 * @param liveType
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getLiveList", method = RequestMethod.GET)
	public Map<String, Object> getLiveInfo(String liveType, HttpServletRequest request, HttpServletResponse response,
			Integer page) throws Exception {
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		if (page == null) {
			page = 1;
		}
		// 使用liveType区分普通直播和专业直播
		// 普通直播和专业直播对应的表不一样
		if (liveType.equals("MERCHANT") || liveType.equals("USER")) {
			// 普通直播
			List<LivePO> livePOList = conn_live.appFindByLiveType(liveType, page, pageSize);
			List<LiveVO> liveVOList = LiveVO.getConverter(LiveVO.class).convert(livePOList, LiveVO.class);
			for (LiveVO liveVO : liveVOList) {
				liveVO.setCover(sysConfig.getWebUrl() + liveVO.getCover());
			}

			return success(liveVOList);
		} else {
			// 专业直播
			List<ProfessionalLivePO> professionalLivePOList = conn_professionalLiveDao.getProfessionalLiveList(page,
					pageSize);
			List<ProfessionalLiveVO> ProfessionalLiveVOList = ProfessionalLiveVO.getConverter(ProfessionalLiveVO.class)
					.convert(professionalLivePOList, ProfessionalLiveVO.class);

			return success(ProfessionalLiveVOList);
		}
	}

	/**
	 * 直播：开始直播
	 * 
	 * @param anchorId
	 * @param liveType
	 * @param liveName
	 * @return live
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/startLive", method = RequestMethod.POST)
	public Map<String, Object> startLive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 解析json: liveName liveType Id
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String liveName = pageObject.getString("liveName");
		String liveType = pageObject.getString("liveType");
		Long anchorId = pageObject.getLong("anchorId");
		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 新建直播或停播改直播中（存直播名）
		LivePO live = null;
		live = conn_live.findByUserId(anchorId);
		if (live == null) {
			UserInfoPO user = conn_user.get(anchorId);
			if (user == null) {
				return FORBIDDEN("未获取到用户!");
			}

			LivePO newLive = new LivePO();
			newLive.setUpdateTime(new Date());
			newLive.setFlag(true);
			newLive.setLiveType(LiveType.fromString(liveType));
			if (LiveType.USER.equals(LiveType.fromString(liveType))) {
				newLive.setUserId(anchorId);
				newLive.setPubName("user" + anchorId);
			} else {
				MerchantPO merchant = user.getMerchant();
				if (merchant == null) {
					return FORBIDDEN("未获取到商家!");
				}
				newLive.setMerchantId(merchant.getId());
				newLive.setPubName("merchant" + anchorId);
				newLive.setUserId(anchorId);
			}
			newLive.setLiveStatusType(LiveStatusType.LIVING);
			newLive.setLiveName(liveName);
			newLive.setCover("/app/live/covert.png");
			conn_live.save(newLive);
			live = newLive;
		}
		if (!LiveStatusType.FORBID.equals(live.getLiveStatusType())) {
			live.setLiveStatusType(LiveStatusType.LIVING);
			live.setLiveName(liveName);
		} else {
			return FORBIDDEN("你的直播间已被禁播,请联系管理员!");
		}
		dataMap.put("liveInfo", live);

		// 获取拍卖直播产品
		String[] fields = { "liveId", "locked" };
		Object[] values = { live.getId(), false };
		LiveProductPO LiveProductPOList = conn_liveProduct.getByFields(fields, values);
		dataMap.put("liveProductInfo", LiveProductPOList);

		return success(dataMap);
	}

	/**
	 * 直播：关闭直播
	 * 
	 * @param anchorId
	 * @param liveType
	 * @return live
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/stopLive", method = RequestMethod.POST)
	public Map<String, Object> stopLive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 接受参数
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		System.out.println("方法:stopLive,参数:" + param);
		JSONObject pageObject = JSON.parseObject(param);
		String liveType = pageObject.getString("liveType");
		Long anchorId = pageObject.getLong("anchorId");

		// 判断user为空
		UserInfoPO user = conn_user.get(anchorId);
		if (user == null) {
			return FORBIDDEN("未获取到用户!");
		}

		LivePO live = null;
		if (LiveType.USER.equals(LiveType.fromString(liveType))) {
			live = conn_live.findByUserId(anchorId);
			live.setLiveStatusType(LiveStatusType.STOP);
			return success(live);
		} else if (LiveType.MERCHANT.equals(LiveType.fromString(liveType))) {
			MerchantPO merchant = user.getMerchant();
			if (merchant == null) {
				return FORBIDDEN("未获取到商家!");
			}
			live = conn_live.findByUserId(anchorId);
			live.setLiveStatusType(LiveStatusType.STOP);
			return success(live);
		} else {
			return FORBIDDEN("错误的直播类型！");
		}

	}

	/**
	 * 直播：商户添加商品
	 * 
	 * @param merchantId
	 * @param liveId
	 * @param productId
	 * @param liveProductType
	 * @param price
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public Map<String, Object> addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取参数
		String param = getRequestJson(request);
		System.out.println("方法:addProduct,参数:" + param);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}

		JSONObject pageObject = JSON.parseObject(param);
		String liveProductType = pageObject.getString("liveProductType");
		Long merchantId = pageObject.getLong("merchantId");// 这个是app存的Id
		Long liveId = pageObject.getLong("liveId");
		Long productId = pageObject.getLong("productId");
		String priceStr = pageObject.getString("price");
		long price = (long) Double.parseDouble(priceStr) * 100;

		ProductPO product = conn_product.get(productId);
		if (product == null) {
			return FORBIDDEN("商品不存在！");
		}
		// 新建直播商品
		LiveProductPO liveProduct = new LiveProductPO();
		liveProduct.setUpdateTime(new Date());
		liveProduct.setMerchantId(merchantId);
		liveProduct.setMerchantName(conn_merchant.get(merchantId).getShopName());
		liveProduct.setLiveId(liveId);
		liveProduct.setProductId(productId);
		liveProduct.setLiveProductType(LiveProductType.fromString(liveProductType));
		liveProduct.setPrice(price);
		liveProduct.setLocked(false); // 锁定
		liveProduct.setPaid(false); // 未支付
		liveProduct.setFlag(false); // 查找
		liveProduct.setProductIsDel("0");
		liveProduct.setOrderId("0");
		liveProduct.setUserId("0");
		liveProduct.setHeadPic(conn_sysConfig.getSysConfig().getWebUrl() + product.getProductShowPic());
		liveProduct.setProductName(product.getProductName());

		conn_liveProduct.save(liveProduct);

		return success();
	}

	/**
	 * 直播：删除直播产品
	 * 
	 * @param merchantId
	 * @param liveId
	 * @param productId
	 * @param liveProductType
	 * @param price
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delLiveProduct", method = RequestMethod.POST)
	public Map<String, Object> delLiveProduct(

			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取参数
		String param = getRequestJson(request);
		System.out.println("方法:addProduct,参数:" + param);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String liveType = pageObject.getString("liveType");
		Long anchorId = pageObject.getLong("anchorId");
		Long liveProductID = pageObject.getLong("liveProductID");
		Long liveId = pageObject.getLong("liveId");

		// 判断user为空
		UserInfoPO user = conn_user.get(anchorId);
		if (user == null) {
			return FORBIDDEN("未获取到用户!");
		}

		LiveProductPO liveProduct = conn_liveProduct.findById(liveProductID);
		if (LiveType.USER.equals(LiveType.fromString(liveType))) {
			liveProduct.setProductIsDel("1");

			liveProduct.setFlag(false);
			conn_liveProduct.update(liveProduct);
			// conn_auction.deleteByProductID(liveProductID);
		} else if (LiveType.MERCHANT.equals(LiveType.fromString(liveType))) {
			MerchantPO merchant = user.getMerchant();
			if (merchant == null) {
				return FORBIDDEN("未获取到商家!");
			}

			liveProduct.setProductIsDel("1");

			liveProduct.setFlag(false);
			conn_liveProduct.update(liveProduct);
			// conn_auction.deleteByProductID(liveProductID);
		}

		return success();
	}

	/**
	 * 直播：添加评论
	 * 
	 * @param userId
	 * @param liveId
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addMessage", method = RequestMethod.POST)
	public Map<String, Object> addMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		param = param.replace("\\n", "");
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		System.out.println(param);
		JSONObject pageObject = JSON.parseObject(param);
		String message = pageObject.getString("message");

		// 过滤敏感词
		FilterSensitive fliter = new FilterSensitive();
		int position = fliter.readSensitiveWordFile(message);
		if (position != -1) {
			return ERROR("您的评论包含敏感话题!");
		}

		Long userId = Long.parseLong(pageObject.getString("userId"));
		Long liveId = Long.parseLong(pageObject.getString("liveId"));
		LiveMessagePO liveMessage = new LiveMessagePO();
		liveMessage.setUpdateTime(new Date());
		liveMessage.setUserId(userId);
		liveMessage.setUserName(conn_user.get(userId).getUserNickname());
		liveMessage.setLiveId(liveId);
		liveMessage.setMessage(message);
		liveMessage.setFlag(false);
		conn_liveMessage.save(liveMessage);
		return success();
	}

	/**
	 * 直播：用户出价
	 * 
	 * @param userId
	 * @param liveId
	 * @param liveProductId
	 * @param price
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/sendPrice", method = RequestMethod.POST)
	public Map<String, Object> sendPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String err = "";
		Long userId = 0L;
		Long liveId = 0L;
		Long liveProductId = 0L;
		Long price = 0L;
		Long addressId = 0L;
		try {

			String param = getRequestJson(request);
			if (param.indexOf("\\") >= 0) {
				param = param.replaceAll("\\\\", "");
				param = param.substring(1, param.length() - 1);
			}
			JSONObject pageObject = JSON.parseObject(param);
			userId = Long.parseLong(pageObject.getString("userId"));
			liveId = Long.parseLong(pageObject.getString("liveId"));
			liveProductId = Long.parseLong(pageObject.getString("liveProductId"));
			price = Long.parseLong(pageObject.getString("price"));
			addressId = Long.parseLong(pageObject.getString("addressId"));
			LiveProductPO liveProduct = conn_liveProduct.findById(liveProductId);
			if (liveProduct.isLocked()) {
				return ERROR("商品已被锁定!");
			}
			if (liveProduct.getPrice() >= price) {
				return ERROR("出价不符合规则!");
			}

			AuctionPO auction = new AuctionPO();
			auction.setUpdateTime(new Date());
			auction.setLiveProductId(liveProductId);
			auction.setUserId(userId);
			auction.setUserName(conn_user.get(userId).getUserNickname());
			auction.setLiveId(liveId);
			auction.setPrice(price);
			auction.setFlag(false);
			auction.setAddressId(addressId);

			conn_auction.save(auction);

			liveProduct.setPrice(price);
			liveProduct.setAuctionId(auction.getId());
			conn_liveProduct.save(liveProduct);
		} catch (Exception e) {
			// StringWriter sw = new StringWriter();
			// e.printStackTrace(new PrintWriter(sw,true));
			// err+=sw.toString();
			// err=e.toString();
			err = "系统错误";
		}
		if (err.length() > 0) {
			return ERROR(err);
		} else {
			return success();
		}
	}

	/**
	 * 直播：锁定商品
	 * 
	 * @param liveProductId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/confirmAuctionPrice", method = RequestMethod.POST)
	public Map<String, Object> confirmAuctionPrice(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long liveProductId = pageObject.getLong("liveProductId");

		LiveProductPO liveProduct = conn_liveProduct.findById(liveProductId);
		liveProduct.setLocked(true);
		liveProduct.setFlag(false);

		AuctionPO Auction = conn_auction.findByProductId(liveProduct.getLiveId());
		if (Auction == null) {
			return ERROR("无人出价");
		}
		Long userId = Auction.getUserId();

		Map<String, Object> data = new HashMap<String, Object>();

		String productId = String.valueOf(liveProduct.getProductId());
		String num = "1";
		// Long userId = Long.parseLong(liveProduct);
		// String paytype = pageObject.getString("paytype");

		/*
		 * String productId = request.getParameter("productId"); String num =
		 * request.getParameter("num"); String paytype =
		 * request.getParameter("payType"); Long userId =
		 * Long.parseLong(request.getParameter("userId"));
		 */

		ProductPO productPO = conn_product.get(Long.parseLong(productId));

		OrderInfoPO order = new OrderInfoPO();
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		// 支付金额
		long payMoney = Integer.parseInt(num) * (Auction.getPrice());
		// 订单总金额
		long orderAllMoney = payMoney;
		// 获取产品

		// 获取商家
		MerchantPO merchant = conn_merchant.get(productPO.getProductMerchantID());

		// 获取用户
		UserInfoPO user = conn_user.get(userId);
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

		// 订单号（城市编码+商家id+板块Code+时间戳+用户ID）
		String orderNO = getCityCodeByDomain() + merchant.getId() + productPO.getProductModularCode() + df.format(date)
				+ userId;
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
		// 商品单价
		order.setProductPrice(productPO.getProductPrice());
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
		order.setPayMode(PayType.fromString("WEICHAT"));
		// 有效期
		// 配送信息
		Long addressId = Auction.getAddressId();
		order.setMailAddress(addressId);
		// 是否评价
		order.setCommentIs(0);
		// 预订日期
		order.setOrderBookDate(date);

		// 订单来源
		order.setSource(OrderSource.LIVE);
		order.setOrderType(OrderType.MERCHANT);
		conn_order.saveOrUpdate(order);

		// 订单ID
		Long orderid = order.getId();
		liveProduct.setUserId(String.valueOf(userId));
		liveProduct.setOrderId(String.valueOf(orderid));
		conn_liveProduct.update(liveProduct);
		return success();
	}

	/**
	 * 直播：获取直播间商品列表
	 * 
	 * @param liveId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getLiveProductList", method = RequestMethod.GET)
	public Map<String, Object> getLiveProductList(Long liveId, Integer page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		List<LiveProductPO> liveProductPOs = conn_liveProduct.findByLiveId(liveId, page, 10);

		List<LiveProductVO> _liveProducts = new ArrayList<LiveProductVO>();
		int count = conn_liveProduct.countByLiveId(liveId);
		for (LiveProductPO liveProductPO : liveProductPOs) {
			LiveProductVO liveProduct = new LiveProductVO();
			liveProduct.setProductId(liveProductPO.getProductId());
			liveProduct.setLiveId(liveProductPO.getLiveId());
			liveProduct.setMerchantId(liveProductPO.getMerchantId());
			liveProduct.setLiveProductType(liveProductPO.getLiveProductType());
			liveProduct.setPrice(liveProductPO.getPrice());
			liveProduct.setLocked(liveProductPO.isLocked());
			liveProduct.setDealPrice(liveProductPO.getDealPrice());
			liveProduct.setAuctionId(liveProductPO.getAuctionId());
			liveProduct.setPaid(liveProductPO.isPaid());
			liveProduct.setHeadPic(conn_product.get(liveProductPO.getProductId()).getProductShowPic());
			liveProduct.setProductName(conn_product.get(liveProductPO.getProductId()).getProductName());
			// conn_auction.get(liveProductPO.getAuctionId()).getUserId();
			// conn_user.get(conn_auction.get(liveProductPO.getAuctionId()).getUserId()).getUserNickname();
			liveProduct.setUserName(
					conn_user.get(conn_auction.get(liveProductPO.getAuctionId()).getUserId()).getUserNickname());
			_liveProducts.add(liveProduct);
		}
		dataMap.put("count", count);
		dataMap.put("liveProducts", _liveProducts);
		return success(dataMap);
	}

	/**
	 * 直播：获取直播信息
	 * 
	 * @param liveId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getLiveInfo", method = RequestMethod.GET)
	public Map<String, Object> getLiveInfo(Long liveId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String err = "";
		try {
			SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
			LivePO live = conn_live.get(liveId);
			LiveVO _live = new LiveVO().set(live);
			UserInfoPO user = conn_user.get(live.getUserId());
			UserInfoVO _user = new UserInfoVO().set(user);
			_user.setUserHeadimg(splitSimple(_user.getUserHeadimg(), sysConfig.getWebUrl()));
			_live.setUser(_user);
			dataMap.put("liveInfo", _live);

			String[] fields = { "liveId", "locked" };
			// String[] values={liveId.toString(),"false"};
			Object[] values = { liveId, false };
			// conn_liveProduct.getByFields(fields, values)

			LiveProductPO LiveProductPOList = conn_liveProduct.getByFields(fields, values);
			// List<LiveProductPO>
			// LiveProductPOList=conn_liveProduct.findByLiveId(liveId);

			dataMap.put("liveProductInfo", LiveProductPOList);
		} catch (Exception e) {
			err = e.toString();
		}
		if (err.length() > 0) {
			return ERROR(err);
		} else {
			return success(dataMap);
		}

	}

	/**
	 * 导览首页检索条件
	 * 
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/guide/retrieval", method = RequestMethod.GET)
	public Map<String, Object> getGuideRetrieval(HttpServletRequest request, HttpServletResponse response, String comId)
			throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");

		Long comIdL = null;
		if (comId == null || comId.length() == 0) {
			comIdL = 1L;
		} else {
			comIdL = Long.parseLong(comId);
		}
		List<ModularPO> modulars = conn_modular.appFindBycomId(comIdL);

		List<ModularVO> _modulars = ModularVO.getConverter(ModularVO.class).convert(modulars, ModularVO.class);
		for (ModularVO modularVO : _modulars) {
			List<ModularClassPO> modularClass = conn_modularClass.appFindByModular(modularVO.getModularCode());
			modularVO.setModularClasses(modularClass);
		}
		return success(_modulars);
	}

	/**
	 * 导览首页标记点接口
	 * 
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/guide/home", method = RequestMethod.GET)
	public Map<String, Object> guideHome(HttpServletRequest request, HttpServletResponse response, String comId,
			String modular, String mclass) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		DecimalFormat df = new DecimalFormat("0.00");

		Long comIdL = null;
		if (comId == null || comId.length() == 0) {
			comIdL = 1L;
		} else {
			comIdL = Long.parseLong(comId);
		}
		CompanyPO company = conn_company.get(comIdL);
		if (company == null) {
			return FORBIDDEN("未获取到分公司！");
		}

		List<MerchantPO> merchants = conn_merchant.appfindByCom(comIdL, modular, mclass);
		List<MerchantVO> _merchants = MerchantVO.getConverter(MerchantVO.class).convert(merchants, MerchantVO.class);
		for (MerchantVO merchantVO : _merchants) {

			// 图片
			merchantVO.setShopHeading(sysConfig.getWebUrl() + merchantVO.getShopHeading());
			merchantVO.setShopQualifications(sysConfig.getWebUrl() + merchantVO.getShopQualifications());
			merchantVO.setShopPic(sysConfig.getWebUrl() + merchantVO.getShopPic());

			// 多图
			String morePicStr = split(merchantVO.getShopMpic(), sysConfig.getWebUrl());
			merchantVO.setShopMpic(morePicStr);

			// 最小价格
			long minPrice = conn_product.getMinPriceByMer(merchantVO.getId());

			if (minPrice == 0l) {
				merchantVO.setAveragePrice("无数据");
			} else {
				merchantVO.setAveragePrice(df.format((double) minPrice / 100));
			}
			/*
			 * //简介
			 * merchantVO.setShopIntroduction(ReduceHtml2Text.removeHtmlTag(
			 * merchantVO.getShopIntroduction()));
			 */
		}
		dataMap.put("multiple", company.getMultiple());
		dataMap.put("merchants", _merchants);
		return success(dataMap);
	}

	@ResponseBody
	@RequestMapping(value = "/getChildByPro/{productId}", method = RequestMethod.GET)
	public Map<String, Object> getChildByProId(@PathVariable Long productId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ProductPO product = conn_product.get(productId);
		if (product == null) {
			return success(null);
		}
		List<ChildProductPO> childProcucts = conn_childProduct.getChildByProAndLan(product.getId());
		List<ChildProductVO> _childProcucts = ChildProductVO.getConverter(ChildProductVO.class).convert(childProcucts,
				ChildProductVO.class);
		SysConfigPO sys = conn_sysConfig.getSysConfig();
		String lastUrl = "";
		for (ChildProductVO childProductVO : _childProcucts) {
			String picStr = childProductVO.getChildPic();
			String[] pics = picStr.split(",");
			for (String url : pics) {
				if (url.length() > 0) {
					lastUrl += sys.getWebUrl() + url + ",";
				}
			}
			childProductVO.setChildPic(lastUrl);
			childProductVO.setChineseGirl(sys.getWebUrl() + childProductVO.getChineseGirl());
		}

		return success(_childProcucts);
	}

	/**
	 * 获取讲节点列表
	 * 
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildByPro", method = RequestMethod.GET)
	public Map<String, Object> getChildByPro(Long merchantId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProductPO product = conn_product.getByMerchantId(merchantId);
		if (product == null) {
			return success(null);
		}
		List<ChildProductPO> childProcucts = conn_childProduct.getChildByProductId(product.getId());
		List<ChildProductVO> _childProcucts = ChildProductVO.getConverter(ChildProductVO.class).convert(childProcucts,
				ChildProductVO.class);
		List<ChildProductVO> result = new ArrayList<ChildProductVO>();
		for (ChildProductVO childProductVO : _childProcucts) {
			// 判断进行讲解的景点, 剔除路线点(非讲解点) XTH
			if (childProductVO.getIsTaught() == 1) {
				result.add(childProductVO);
			}
		}
		return success(result);
	}

	/**
	 * 获取子产品音频
	 * 
	 * @param childId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildVoice", method = RequestMethod.GET)
	public Map<String, Object> getChildVoice(Long childId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ChildProductPO child = conn_childProduct.get(childId);
		String childVoice = conn_sysConfig.getSysConfig().getWebUrl() + child.getChineseGirl();
		return success(childVoice);
	}

	/**
	 * 获取子产品图片列表
	 * 
	 * @param childId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildPic/{childId}/{page}", method = RequestMethod.GET)
	public Map<String, Object> getChildPic(Long childId, int page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 根据子产品id获取所有的图片关联对象
		List<PicproRelationPO> pprs = conn_picproRelation.getPprByChildID(childId, page, pageSize);
		// 关联对象转化成图片对象
		List<PicturePO> pictures = conn_picture.getPicsByPprs(pprs);
		List<PictureVO> _pictures = PictureVO.getConverter(PictureVO.class).convert(pictures, PictureVO.class);
		String weburl = conn_sysConfig.getSysConfig().getWebUrl();
		for (PictureVO pv : _pictures) {
			pv.setWebUrl(weburl + pv.getFolde() + File.separator + pv.getNewName());
		}
		return success(_pictures);
	}

	/**
	 * 小视频图文列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getVideoPics", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public Map<String, Object> getVideoPics(HttpServletRequest request, HttpServletResponse response, Integer page,
			Integer pageSize, String userId, String vType) throws Exception {

		request.setCharacterEncoding("UTF-8");
		String sName = new String((request.getParameter("sName")).getBytes("iso-8859-1"), "utf-8");
		String webUrl = conn_sysConfig.getSysConfig().getWebUrl();
		Long userIds = null;
		// 登录用户
		UserInfoPO seuser = null;
		if (userId != null && userId.length() > 0) {
			userIds = Long.parseLong(userId);
			seuser = conn_user.get(userIds);
		}

		// 判断类型
		VideoPicType t = null;
		if (vType != null && vType.length() > 0) {
			t = VideoPicType.fromString(vType);
		}

		// 小视频图文
		List<VideoPicPO> videoPics = conn_videoPic.findAllDesc(page, pageSize, t, sName, userIds);

		List<VideoPicVO> _videoPics = new ArrayList<VideoPicVO>();
		for (VideoPicPO videoPic : videoPics) {
			VideoPicVO _videoPic = new VideoPicVO().set(videoPic);
			// 路径
			_videoPic.setfUrl(split(_videoPic.getfUrl(), webUrl));

			// 点赞数
			int countPraise = conn_vPRel.countPraise(videoPic);
			_videoPic.setPraiseCount(countPraise);

			// 评论数
			int countComment = 0;
			List<VPCommentPO> vpComments = videoPic.getVPComments();
			if (vpComments != null) {
				countComment = vpComments.size();
			}
			_videoPic.setCommentCount(countComment);

			// 用户
			UserInfoPO user = conn_user.get(videoPic.getUserId());
			UserInfoVO _user = new UserInfoVO().set(user);
			_user.setUserHeadimg(splitSimple(_user.getUserHeadimg(), webUrl));
			_videoPic.setUser(_user);

			// 是否可以删除
			if (seuser != null && seuser.getId() == user.getId()) {
				_videoPic.setIsDelete(1);
			}

			// 是否已经点赞
			if (seuser != null) {
				VPRelPO praise = conn_vPRel.getPraiseByVU(videoPic, seuser.getId());
				if (praise != null) {
					_videoPic.setIsPraise(1);
				}
			}
			_videoPics.add(_videoPic);
		}
		return success(_videoPics);
	}

	/**
	 * 上传视频或图片
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/vpUpload.do", method = RequestMethod.POST)
	public Map<String, Object> vpUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file) throws Exception {
		String folderUrl = conn_sysConfig.getSysConfig().getFolderUrl();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String dateStr = df.format(d);
		// 保存路径
		String path = "cache" + File.separator + "videoPic" + File.separator + dateStr + File.separator;

		String path1 = folderUrl + path;// 文件路径
		// 文件名
		String fileName = file.getOriginalFilename();
		String newName = d.getTime() + fileName.substring(fileName.lastIndexOf("."));

		// 文件
		File folder = new File(path1);
		if (folder.exists() == false) {
			if (folder.getParentFile().exists() == false) {
				System.out.println("系统找不到路径!" + folder.getParentFile());
				return ERROR("路径出错!");
			}
			folder.mkdir();// 没有就新建
		}
		// 上传文件
		File newFile = new File(path1 + newName);
		file.transferTo(newFile);

		return success("http://" + WXContants.Website + "/file/" + path + newName);
	}

	/**
	 * 发布小视频和图文
	 * 
	 * @param request
	 * @param response
	 * @param fUrl
	 * @param type
	 * @param context
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/videoPic.do", method = RequestMethod.POST)
	public Map<String, Object> videoPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.获取参数
		String param = getRequestJson(request);

		JSONObject pageObject = JSON.parseObject(param);
		Long userId = pageObject.getLong("userId");
		String fUrl = pageObject.getString("fUrl");
		String type = pageObject.getString("type");
		String context = pageObject.getString("context");
		String sName = pageObject.getString("sName");
		String headPic = pageObject.getString("headPic");
		String murl = pageObject.getString("murl");
		// 2.移动文件
		String folderUrl = conn_sysConfig.getSysConfig().getFolderUrl();
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		if (fUrl != null && fUrl.length() > 0) {
			String[] fUrlsz = fUrl.split(",");
			for (int i = 0; i < fUrlsz.length; i++) {
				if (fUrlsz[i].indexOf("http://") != -1) {
				} else {
					String folder = folderUrl + fUrlsz[i].substring(0, fUrlsz[i].lastIndexOf(File.separator))
							.replace("cache" + File.separator, "");
					RemoveFile(folderUrl + fUrlsz[i], folder);
					fUrlsz[i] = "/" + fUrlsz[i].replace("cache" + File.separator, "").replace("\\", "/");
				}
			}
			fUrl = StringUtils.join(fUrlsz, ",");
		}
		// 3.操作数据库
		VideoPicPO videoPic = new VideoPicPO();
		videoPic.setUserId(user.getId());
		videoPic.setUpdateTime(new Date());
		videoPic.setContent(context);
		videoPic.setType(VideoPicType.fromString(type));
		videoPic.setfUrl(fUrl);
		if (sName != null) {
			videoPic.setName("[" + user.getTrueName() + "]" + sName);
		}
		if (headPic != null) {
			videoPic.setHeadPic(headPic);
		}
		videoPic.setMrul(murl);
		conn_videoPic.save(videoPic);
		return success();
	}

	@ResponseBody
	@RequestMapping(value = "/videoPicGet.do", method = RequestMethod.GET)
	public Map<String, Object> videoPicGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String dateStr = df.format(d);

		HC();
		return success();
	}

	// 截取第一屏
	private String Gethandler(String ffmpegPath, String upFilePath, String mediaPicPath) {
		String msg = "";
		List<String> cutpic = new ArrayList<String>();
		cutpic.add(ffmpegPath);
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
		cutpic.add("-y");
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("0"); // 添加起始时间为第17秒
		cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
		cutpic.add("0.001"); // 添加持续时间为1毫秒
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		cutpic.add("500*400"); // 添加截取的图片大小为350*240
		cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

		ProcessBuilder builder = new ProcessBuilder();
		try {

			builder.command(cutpic);
			builder.redirectErrorStream(true);
			// 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
			// 因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
			builder.start();
		} catch (Exception e) {
			msg = e.getMessage();
			System.out.println(e);
			e.printStackTrace();
		}

		return msg;
	}

	private void HC() {
		try {
			Movie countVideo = MovieCreator.build("G:\\headpic\\1529056195054.mp4");
			Movie countAudioEnglish = MovieCreator.build("G:\\headpic\\1529056277117.mp3");
			Track audioTrackEnglish = countAudioEnglish.getTracks().get(0);
			countVideo.addTrack(audioTrackEnglish);
			Container out = new DefaultMp4Builder().build(countVideo);
			FileOutputStream fos = new FileOutputStream(new File("D:\\demo_test.mp4"));
			out.writeContainer(fos.getChannel());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发布小视频和图文20180603
	 * 
	 * @param request
	 * @param response
	 * @param fUrl
	 * @param type
	 * @param context
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/videoPicAdd.do", method = RequestMethod.POST)
	public Map<String, Object> videoPicAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.获取参数
		String param = getRequestJson(request);

		JSONObject pageObject = JSON.parseObject(param);
		Long userId = pageObject.getLong("userId");
		String fUrl = pageObject.getString("fUrl");
		String type = pageObject.getString("type");
		String context = pageObject.getString("context");
		String sName = pageObject.getString("sName");
		String headPic = pageObject.getString("headPic");
		// 2.移动文件
		String folderUrl = conn_sysConfig.getSysConfig().getFolderUrl();
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		if (fUrl != null && fUrl.length() > 0) {
			String[] fUrlsz = fUrl.split(",");
			for (int i = 0; i < fUrlsz.length; i++) {
				if (fUrlsz[i].indexOf("http://") != -1) {
				} else {
					String folder = folderUrl + fUrlsz[i].substring(0, fUrlsz[i].lastIndexOf(File.separator))
							.replace("cache" + File.separator, "");
					RemoveFile(folderUrl + fUrlsz[i], folder);
					fUrlsz[i] = "/" + fUrlsz[i].replace("cache" + File.separator, "").replace("\\", "/");
				}
			}
			fUrl = StringUtils.join(fUrlsz, ",");
		}
		// 3.操作数据库
		VideoPicPO videoPic = new VideoPicPO();
		videoPic.setUserId(user.getId());
		videoPic.setUpdateTime(new Date());
		videoPic.setContent(context);
		videoPic.setType(VideoPicType.fromString(type));
		videoPic.setfUrl(fUrl);
		videoPic.setName(sName);
		videoPic.setHeadPic(headPic);
		conn_videoPic.save(videoPic);
		return success();
	}

	/**
	 * 更新视频和图文
	 * 
	 * @param request
	 * @param response
	 * @param fUrl
	 * @param type
	 * @param context
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/videoPicUpd.do", method = RequestMethod.POST)
	public Map<String, Object> videoPicUpdate(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1.获取参数
		String param = getRequestJson(request);

		JSONObject pageObject = JSON.parseObject(param);
		Long pid = pageObject.getLong("pid");
		Long userId = pageObject.getLong("userId");
		String fUrl = pageObject.getString("fUrl");
		// String type = pageObject.getString("type");
		String context = pageObject.getString("context");
		String sName = pageObject.getString("sName");
		String headPic = pageObject.getString("headPic");
		// 2.移动文件
		String folderUrl = conn_sysConfig.getSysConfig().getFolderUrl();
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		if (fUrl != null && fUrl.length() > 0) {
			String[] fUrlsz = fUrl.split(",");
			for (int i = 0; i < fUrlsz.length; i++) {
				if (fUrlsz[i].indexOf("http://") != -1) {
				} else {
					String folder = folderUrl + fUrlsz[i].substring(0, fUrlsz[i].lastIndexOf(File.separator))
							.replace("cache" + File.separator, "");
					RemoveFile(folderUrl + fUrlsz[i], folder);
					fUrlsz[i] = "/" + fUrlsz[i].replace("cache" + File.separator, "").replace("\\", "/");
				}
			}
			fUrl = StringUtils.join(fUrlsz, ",");
		}
		// 3.操作数据库
		VideoPicPO videoPic = conn_videoPic.get(pid);
		if (videoPic != null) {

			videoPic.setUserId(user.getId());
			videoPic.setUpdateTime(new Date());
			videoPic.setContent(context);
			// videoPic.setType(VideoPicType.fromString(type));
			videoPic.setfUrl(fUrl);
			videoPic.setName(sName);
			videoPic.setHeadPic(headPic);
			conn_videoPic.update(videoPic);
			return success();
		} else {
			return ERROR("当前文章不存在");
		}
	}

	/**
	 * 删除信息
	 * 
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/videoPicDel", method = RequestMethod.GET)
	public Map<String, Object> videoPicDel(

			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.获取参数
		// String param = getRequestJson(request);
		// JSONObject pageObject = JSON.parseObject(param);
		// Long userId = pageObject.getLong("userId");
		Long userId = Long.parseLong(request.getParameter("userId"));

		Long vpId = Long.parseLong(request.getParameter("vpid"));
		// Long vpId=pageObject.getLong("vpid");
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		VideoPicPO videoPic = conn_videoPic.get(vpId);
		Long d = (new Date().getTime() - videoPic.getUpdateTime().getTime()) / (60 * 60 * 1000);
		System.out.print(d);
		if (d > 2) {
			return ERROR("超过两小时不能删除！");
		}

		// 删除点赞列表
		// conn_vPRel.deleteByField("videoPic_id",vpId);
		conn_vPRel.deleteByFile(vpId);
		// conn_videoPic.deleteByHql(hql);
		// 删除评论列表
		conn_vPComment.deleteByFile(vpId);
		// 删除主表
		conn_videoPic.delete(vpId);
		return success();
	}

	/**
	 * 查看详情
	 * 
	 * @param request
	 * @param response
	 * @param vpId
	 * @return
	 * @throws Exception
	 */
	// TODO
	@ResponseBody
	@RequestMapping(value = "/videoPicInfo", method = RequestMethod.GET)
	public Map<String, Object> videoPicInfo(HttpServletRequest request, HttpServletResponse response, Long vpId,
			String UserId) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String webUrl = conn_sysConfig.getSysConfig().getWebUrl();
		UserInfoPO seuser = null;
		if (UserId != null && UserId.length() > 0) {
			seuser = conn_user.get(Long.parseLong(UserId));
		}

		VideoPicPO videoPic = conn_videoPic.get(vpId);
		VideoPicVO _videoPic = new VideoPicVO().set(videoPic);

		UserInfoPO vpuser = conn_user.get(videoPic.getUserId());
		UserInfoVO _vpuser = new UserInfoVO().set(vpuser);
		_vpuser.setUserHeadimg(splitSimple(_vpuser.getUserHeadimg(), webUrl));// 头像路径

		_videoPic.setfUrl(split(_videoPic.getfUrl(), webUrl));// 路径
		_videoPic.setUser(_vpuser); // 用户

		// 点赞数
		int countPraise = conn_vPRel.countPraise(videoPic);
		_videoPic.setPraiseCount(countPraise);

		// 获取评论
		List<VPCommentPO> vpComments = videoPic.getVPComments();

		// 是否点赞
		if (seuser != null) {
			VPRelPO praise = conn_vPRel.getPraiseByVU(videoPic, seuser.getId());
			if (praise != null) {
				_videoPic.setIsPraise(1);
			}
		}

		// 评论数
		int countComment = 0;
		int pages = 0;
		if (vpComments != null) {
			countComment = vpComments.size();
			pages = countComment % 10 == 0 ? countComment / 10 : countComment / 10 + 1;
		}
		_videoPic.setCommentCount(countComment);

		// 图文小视频,评论
		strMap.put("videoPic", _videoPic);
		strMap.put("pages", pages);
		return success(strMap);
	}

	/**
	 * 评论分页
	 * 
	 * @param vpId
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pageVpCommmets", method = RequestMethod.GET)
	public Map<String, Object> pageVpCommmets(Long vpId, Integer page, Integer pageSize, String UseId)
			throws Exception {
		VideoPicPO videoPicPO = conn_videoPic.get(vpId);
		if (videoPicPO == null)
			return ERROR("没获取到图文小视频");
		String webUrl = conn_sysConfig.getSysConfig().getWebUrl();
		List<VPCommentPO> comments = conn_videoPic.findCommentsPage(videoPicPO, page, pageSize);

		List<VPCommentVO> _vpComments = new ArrayList<VPCommentVO>();
		for (VPCommentPO vpCommentPO : comments) {
			UserInfoPO user = conn_user.get(vpCommentPO.getUserId());
			UserInfoVO _user = new UserInfoVO().set(user);
			_user.setUserHeadimg(splitSimple(_user.getUserHeadimg(), webUrl));// user头像

			VPCommentVO _vpComment = new VPCommentVO().set(vpCommentPO);
			_vpComment.setUser(_user);// 评论人;

			_vpComments.add(_vpComment);
		}
		return success(_vpComments);
	}

	/**
	 * 评论
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/vpComment.do", method = RequestMethod.POST)
	public Map<String, Object> vpComment(HttpServletRequest request) throws Exception {
		// 1.获取参数
		String param = getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(param);
		Long vpId = pageObject.getLong("vpId");
		Long userId = pageObject.getLong("userId");
		String commentText = pageObject.getString("commentText");

		// 2.操作数据库
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		if (commentText == null || commentText == "")
			return ERROR("不能发送空评论");
		VideoPicPO videoPic = conn_videoPic.get(vpId);

		// 评论
		VPCommentPO vpCommentPO = new VPCommentPO();
		vpCommentPO.setUpdateTime(new Date());
		vpCommentPO.setUserId(user.getId());
		vpCommentPO.setCommentText(commentText);

		// 关联
		videoPic.getVPComments().add(vpCommentPO);
		vpCommentPO.setVideoPic(videoPic);

		conn_videoPic.save(videoPic);
		conn_vPComment.save(vpCommentPO);
		return success();
	}

	/**
	 * 点赞,取消点赞
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/vpPraise.do", method = RequestMethod.POST)
	public Map<String, Object> vpPraise(HttpServletRequest request) throws Exception {
		// 1.获取参数
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			// param = param.substring(1, param.length()-1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long vpId = pageObject.getLong("vpId");
		Long userId = pageObject.getLong("userId");

		// 2.操作数据库
		Map<String, Object> strMap = new HashMap<String, Object>();
		Date date = new Date();
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return FORBIDDEN("未获取到用户！");
		}
		VideoPicPO videoPic = conn_videoPic.get(vpId);
		VPRelPO praise = conn_vPRel.getPraiseByVpU(videoPic, user.getId());
		if (praise == null) {
			VPRelPO vpRel = new VPRelPO();
			vpRel.setUpdateTime(date);
			vpRel.setPraiseTime(date);
			vpRel.setPraise(1);
			vpRel.setUserId(user.getId());
			vpRel.setVideoPic(videoPic);

			videoPic.getVPRels().add(vpRel);
			conn_videoPic.save(videoPic);
			conn_vPRel.save(vpRel);
			strMap.put("msg", "已赞");
			strMap.put("code", "1");
			return success(strMap);
		} else if (praise.getPraise() == 0) {
			praise.setPraiseTime(date);
			praise.setPraise(1);
			strMap.put("msg", "已赞");
			strMap.put("code", "1");
			return success(strMap);
		} else if (praise.getPraise() == 1) {
			praise.setPraiseTime(date);
			praise.setPraise(0);
			strMap.put("msg", "已取消");
			strMap.put("code", "0");
			return success(strMap);
		} else {
			strMap.put("msg", "error");
			return ERROR("操作失败!");
		}
	}

	// 删除
	@ResponseBody
	@RequestMapping(value = "/delVideoPic.do", method = RequestMethod.POST)
	public Map<String, Object> delVideoPic(HttpServletRequest request) throws Exception {

		// 1.获取参数
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			// param = param.substring(1, param.length()-1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long vpId = pageObject.getLong("vpId");
		Long userId = pageObject.getLong("userId");

		// 2.删除数据库
		UserInfoPO user = conn_user.get(userId);
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		VideoPicPO videoPic = conn_videoPic.get(vpId);
		if (videoPic == null) {
			return ERROR("未获取到图文,小视频！");
		}
		conn_videoPic.delete(videoPic);

		// 3.???删除文件路径
		return success();
	}

	// 更新接口
	@ResponseBody
	@RequestMapping(value = "/updateVersion", method = RequestMethod.GET)
	public Map<String, Object> updateVersion(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("versionCode", 9);
		strMap.put("versionName", "9");
		strMap.put("content", "");
		strMap.put("forceUpdate", true);
		strMap.put("url", "http://" + WXContants.Website + "/download/guolaiwan.apk");

		return success(strMap);
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

	// 微信支付方法
	public Map<String, String> weichatPay(long PayMoney, String tradeNum) {
		Map<String, String> reqData = new HashMap<String, String>();
		Map<String, String> resData = null;
		try {
			GuolaiwanWxPayApp wxPay = GuolaiwanWxPayApp
					.getInstance("http://" + WXContants.Website + "/website/wxreport/payreport");
			reqData.put("total_fee", "" + PayMoney); // 总价
			reqData.put("attach", "test"); // 订单的自定义数据
			reqData.put("body", "guolaiwan-order"); // 内容
			reqData.put("out_trade_no", tradeNum); // 订单号
			reqData.put("spbill_create_ip", "192.165.56.64");
			reqData.put("trade_type", "APP"); // 支付类型APP
			reqData.put("device_info", "WEB");
			resData = wxPay.pay(reqData);
			System.out.println("7" + resData);
			String noncestr = resData.get("nonce_str");
			String partnerid = resData.get("mch_id");
			String prepay_id = resData.get("prepay_id");

			resData.put("noncestr", noncestr);
			resData.put("package", "Sign=WXPay");
			resData.put("partnerid", partnerid);
			resData.put("prepayid", prepay_id);
			resData.put("timestamp", "" + new Date().getTime() / 1000);
			resData.remove("nonce_str");
			resData.remove("return_msg");
			resData.remove("mch_id");
			resData.remove("prepay_id");
			resData.remove("device_info");
			resData.remove("trade_type");
			resData.remove("result_code");
			resData.remove("return_code");
			resData.remove("sign");
			System.out.println(resData);
			resData.put("sign", WXPayUtil.generateSignature(resData, GuolaiwanWxPayApp.AppKey, SignType.MD5));

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		System.out.println(resData.toString());
		return resData;
	}

	@Autowired
	private LanDAO conn_lan;

	@ResponseBody
	@RequestMapping(value = "/getLanList", method = RequestMethod.GET)
	public Map<String, Object> getLanList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String merchantId = request.getParameter("merchantId");
		List<LanPO> polist = conn_lan.getLanByMerchantId(Long.parseLong(merchantId));
		List<LanVO> volist = LanVO.getConverter(LanVO.class).convert(polist, LanVO.class);
		return success(volist);
	}

	@Autowired
	private UserInfoDAO conn_userInfo;

	/**
	 * Liw 根据用户id查询此用户去过哪些景点
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildListByUid/{userId}", method = RequestMethod.GET)
	public String getChildByUid(@PathVariable Long userId) {
		List<UserInfoPO> upolist = conn_userInfo.getUserByUid(userId);
		String child = upolist.get(0).getChildId();
		if (child == null) {
			return "";
		} else {
			return child;
		}
	}

	/**
	 * Liw 用户每浏览一个导览点执行新增Controller
	 */
	@RequestMapping(value = "/addChildByUidCid/{userId}/{childIds}", method = RequestMethod.POST)
	public void addChildByUidCid(@PathVariable Long userId, @RequestParam String childIds) {
		UserInfoPO user = new UserInfoPO();
		List<UserInfoPO> upolist = conn_userInfo.getUserByUid(userId);
		String child = upolist.get(0).getChildId();
		String str = null;
		String[] s = childIds.split(",");
		for (String st : s) {
			str = child + "," + st;
		}
		user.setChildId(str);
		conn_userInfo.save(user);
	}

	/**
	 * Liw 根据页面传进来的用户id查询用户所有信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserInfoListById/{userId}", method = RequestMethod.GET)
	public UserInfoVO getUserByUid(@PathVariable Long userId) throws Exception {
		List<UserInfoPO> polist = conn_userInfo.getUserByUid(userId);
		List<UserInfoVO> volist = UserInfoVO.getConverter(UserInfoVO.class).convert(polist, UserInfoVO.class);
		return volist.get(0);
	}

	/**
	 * Liw 根据id查询景区经纬度表所有
	 */
	@Autowired
	private ProLatitudeLongitudeDAO conn_proLatitudeLongitude;

	@ResponseBody
	@RequestMapping(value = "/getProLaLoListById/{id}", method = RequestMethod.GET)
	public ProLatitudeLongitudeVO getProTourism(@PathVariable long id) throws Exception {
		List<ProLatitudeLongitudePO> polist = conn_proLatitudeLongitude.getProLatitudeLongitudeById(id);
		List<ProLatitudeLongitudeVO> volist = ProLatitudeLongitudeVO.getConverter(ProLatitudeLongitudeVO.class)
				.convert(polist, ProLatitudeLongitudeVO.class);
		return volist.get(0);
	}

	/**
	 * Liw 根据id查询景区图片表所有
	 */
	@Autowired
	private ProTourismPictureDAO conn_proTourismPicture;

	@ResponseBody
	@RequestMapping(value = "/getProToPicListById/{id}", method = RequestMethod.GET)
	public ProTourismPictureVO getPicture(@PathVariable long id) throws Exception {
		List<ProTourismPicturePO> polist = conn_proTourismPicture.getTourismPictureById(id);
		List<ProTourismPictureVO> volist = ProTourismPictureVO.getConverter(ProTourismPictureVO.class).convert(polist,
				ProTourismPictureVO.class);
		return volist.get(0);
	}

	/**
	 * Liw 根据导览点id,商品id和语言id查询一条导览点信息
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildProListById/{id}/{pid}/{lanid}", method = RequestMethod.GET)
	public ChildProductVO getChildByPidAndLanIdAndCid(@PathVariable String id, @PathVariable String pid,
			@PathVariable String lanid) throws Exception {
		List<ChildProductPO> polist = conn_childProduct.getChildByPidAndLanIdAndCid(Long.parseLong(id),
				Long.parseLong(pid), Long.parseLong(lanid));
		List<ChildProductVO> volist = ChildProductVO.getConverter(ChildProductVO.class).convert(polist,
				ChildProductVO.class);
		return volist.get(0);
	}

	/**
	 * CaiCY&Liw 用户清空当前景区导览点
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteChildsByIds", method = RequestMethod.POST)
	public Map<String, Object> deleteChildsByIds(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json = getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(json);
		String userId = pageObject.getString("userId");
		String childIdStr = pageObject.getString("childIdStr");
		String action = pageObject.getString("action");
		String[] splitChildIdStr = childIdStr.split(",");
		List<UserInfoPO> userByUid = conn_userInfo.getUserByUid(Long.parseLong(userId));
		String childId = userByUid.get(0).getChildId();
		List<String> requestChildIdList = new ArrayList<String>();
		if (childId == null || "".equals(childId)) {
			for (String str : childIdStr.split(",")) {
				requestChildIdList.add(str);
			}
		} else {
			String[] split = childId.split(",");
			for (String str : split) {
				requestChildIdList.add(str);
			}
		}
		if ("remove".equals(action)) {
			if (splitChildIdStr != null && !"".equals(splitChildIdStr)) {
				for (int i = 0; i < splitChildIdStr.length; i++) {
					if (requestChildIdList.contains(splitChildIdStr[i])) {
						requestChildIdList.remove(splitChildIdStr[i]);
					}
				}
				StringBuilder sBuilder = new StringBuilder();
				for (int i = 0; i < requestChildIdList.size(); i++) {
					if (i < requestChildIdList.size() - 1) {
						sBuilder.append(requestChildIdList.get(i) + ",");
					} else {
						sBuilder.append(requestChildIdList.get(i));
					}
				}
				userByUid.get(0).setChildId(sBuilder.toString());
				conn_userInfo.saveOrUpdate(userByUid.get(0));
				map.put("data", "清空成功");
				return success(map);
			}
		} else if ("add".equals(action)) {
			if (splitChildIdStr != null && !"".equals(splitChildIdStr)) {
				for (String str1 : splitChildIdStr) {
					if (!requestChildIdList.contains(str1)) {
						requestChildIdList.add(str1);
					}
				}
				String string = "";
				for (int i = 0; i < requestChildIdList.size(); i++) {
					if (i < requestChildIdList.size() - 1) {
						string = string + requestChildIdList.get(i) + ",";
					} else {
						string = string + requestChildIdList.get(i);
					}
				}
				userByUid.get(0).setChildId(string);
				conn_userInfo.saveOrUpdate(userByUid.get(0));
				map.put("data", "新增成功");
				return success(map);
			}
		}
		return null;
	}

	@Autowired
	private ShareDAO conn_share;

	@ResponseBody
	@RequestMapping(value = "/toShare", method = RequestMethod.POST)
	public Map<String, Object> toShare(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(json);
		String userId = pageObject.getString("userId");
		String merchantId = pageObject.getString("productId");
		String childIds = pageObject.getString("childIds");
		String step = pageObject.getString("step");
		String km = pageObject.getString("km");
		String calorie = pageObject.getString("calorie");
		String weather = pageObject.getString("weather");
		List<MerchantPO> polist = conn_merchant.getMerchantById(Long.parseLong(merchantId));
		List<MerchantVO> volist = MerchantVO.getConverter(MerchantVO.class).convert(polist, MerchantVO.class);
		List<ChildProductPO> childByProductId = conn_childProduct.getChildByProductId(Long.parseLong(merchantId));
		List<String> unchild = new ArrayList<String>();
		for (ChildProductPO cppo : childByProductId) {
			unchild.add(cppo.getId().toString());
		}
		String[] split = childIds.split(",");
		for (int i = 0; i < split.length; i++) {
			if (unchild.contains(split[i])) {
				unchild.remove(i);
			}
		}
		String demo = "";
		for (int j = 0; j < unchild.size(); j++) {
			if (j < unchild.size() - 1) {
				demo = demo + unchild.get(j) + ",";
			} else {
				demo = demo + unchild.get(j);
			}
		}
		String proName = volist.get(0).getShopName();
		SharePO share = new SharePO();
		share.setUserId(userId);
		share.setProductId(merchantId);
		share.setChildIds(childIds);
		share.setUnchildIds(demo);
		share.setStep(step);
		share.setKm(km);
		share.setCalorie(calorie);
		share.setWeather(weather);
		share.setProName(proName);
		conn_share.save(share);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("url", "http://" + WXContants.Website + "/guolaiwan/pubnum/share/list/" + share.getId());
		hashMap.put("childName", proName);
		return success(hashMap);
	}

	@ResponseBody
	@RequestMapping(value = "/getShare/{shareId}", method = RequestMethod.GET)
	public Map<String, Object> getChilds(@PathVariable long shareId, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SharePO> polist = conn_share.getShareById(shareId);
		List<ShareVO> volist = ShareVO.getConverter(ShareVO.class).convert(polist, ShareVO.class);
		String childIds = volist.get(0).getChildIds();
		List<ChildProductPO> list = new ArrayList<ChildProductPO>();
		if (!"".equals(childIds)) {
			String[] splits = childIds.split(",");
			for (String str : splits) {
				List<ChildProductPO> childlist = conn_childProduct.getChildsList(Long.parseLong(str));
				list.add(childlist.get(0));
			}
			List<ChildProductVO> childList = ChildProductVO.getConverter(ChildProductVO.class).convert(list,
					ChildProductVO.class);
			List<MerchantPO> mpolist = conn_merchant.getMerchantById(Long.parseLong(volist.get(0).getProductId()));
			List<MerchantVO> mvolist = MerchantVO.getConverter(MerchantVO.class).convert(mpolist, MerchantVO.class);
			map.put("LO", mvolist.get(0).getShopLongitude());
			map.put("LA", mvolist.get(0).getShopLatitude());
			map.put("ProName", mvolist.get(0).getShopName());
			Long merId = mvolist.get(0).getId();
			ProductPO ppolist = conn_product.getByMerchantId(merId);
			map.put("childList", childList);
			map.put("merId", ppolist.getId());
		}
		map.put("data", volist.get(0));
		return map;
	}

	@Autowired
	private ChildPicAndContentDAO conn_childPicAndContent;

	@ResponseBody
	@RequestMapping(value = "/getChildPicConById", method = RequestMethod.GET)
	public Map<String, Object> getChildsByChildId(HttpServletRequest request, String voiceId, String childId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ChildPicAndContentPO> polist = conn_childPicAndContent.getChildsByChildId(Long.parseLong(voiceId),
				Long.parseLong(childId));
		List<ChildPicAndContentVO> volist = ChildPicAndContentVO.getConverter(ChildPicAndContentVO.class)
				.convert(polist, ChildPicAndContentVO.class);
		String demo = "";
		for (ChildPicAndContentVO cpac : volist) {
			char[] charArray = cpac.getChildPic().toCharArray();
			if (charArray.length < 30) {
				for (int i = 0; i < charArray.length - 2; i++) {
					demo = demo + charArray[i];
				}
			} else if (charArray.length > 30 && charArray.length < 60) {
				for (int i = 0; i < charArray.length - 1; i++) {
					demo = demo + charArray[i];
				}
			} else {
				for (int i = 0; i < charArray.length; i++) {
					demo = demo + charArray[i];
				}
			}
		}
		volist.get(0).setChildPic(demo);
		map.put("data", volist);
		return success(map);
	}

	/**
	 * 经纬度计算工具 XTH
	 */
	private static double EARTH_RADIUS = 6371.393;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 计算两个经纬度之间的距离 XTH
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double getDis(double lat1, double lon1, double lat2, double lon2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double dis = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		dis = dis * EARTH_RADIUS;
		dis = Math.round(dis * 1000);
		return dis;
	}

	/**
	 * 规划路线 XTH
	 * 
	 * @param 当前经度currentLon
	 * @param 当前纬度currentLat
	 * @param 目标点childProductPO
	 * @param 景区中所有景点和路线点集合childProductPOList
	 * @return 路线规划结果集resultList
	 */
	public List<List<ChildProductPO>> planRoad(ChildProductPO start, ChildProductPO childProductPO,
			List<ChildProductPO> childProductPOList, List<ChildProductPO> resultList,
			List<List<ChildProductPO>> resultBundle) {
		// 当第一次调用方法时,结果集还未创建
		// 当递归调用方式时,结果集已存在,要保证结果集不变
		if (resultBundle == null) {
			resultBundle = new ArrayList<List<ChildProductPO>>();

		}
		if (resultList == null) {
			resultList = new ArrayList<ChildProductPO>(10);
			resultList.add(start);
		}
		// 如果景点和路线点集合:childProductPOList第一个元素就是终点:childProductPO
		if (start.getId().equals(childProductPO.getId())) {
			resultBundle.add(resultList);
			return resultBundle;
		}
		// Mr
		String linkpoint = start.getLinkedPoint();
		String[] linkPoints = linkpoint.split(",");
		int count = 0;
		for (String childId : linkPoints) {
			ChildProductPO linkChild = conn_childProduct.get(Long.parseLong(childId));
			boolean has = false;
			for (ChildProductPO hasChild : resultList) {
				if (linkChild.getId().equals(hasChild.getId())) {
					has = true;
					break;
				}
			}
			if (!has) {
				resultList.add(linkChild);
				if (linkChild.getId().equals(childProductPO.getId())) {
					resultBundle.add(resultList);
					break;
				}
				if (count == 0) {
					planRoad(linkChild, childProductPO, childProductPOList, resultList, resultBundle);
				} else {

					List<ChildProductPO> newReusltList = new ArrayList<ChildProductPO>();
					newReusltList.addAll(resultList);
					System.out.println("generate new:" + newReusltList.size());
					// resultBundle.add(newReusltList);
					planRoad(linkChild, childProductPO, childProductPOList, newReusltList, resultBundle);
				}

			}
			count++;
		}

		// Mr
		return resultBundle;

	}

	List<ChildProductPO> getshortest(List<List<ChildProductPO>> result) {
		double tempDis = 0;
		List<ChildProductPO> resultChild = null;
		for (List<ChildProductPO> list : result) {
			double curdis = 0;
			for (int i = 0; i < list.size() - 1; i++) {
				curdis += getDis(Double.parseDouble(list.get(i).getChildLatitude()),
						Double.parseDouble(list.get(i).getChildLongitude()),
						Double.parseDouble(list.get(i + 1).getChildLatitude()),
						Double.parseDouble(list.get(i + 1).getChildLongitude()));
			}
			if (tempDis == 0) {
				tempDis = curdis;
				resultChild = list;
			} else {
				if (tempDis > curdis) {
					tempDis = curdis;
					resultChild = list;
				}
			}
		}
		return resultChild;
	}

	/**
	 * 获取导航路线最短距离需要经过的坐标(经纬度) XTH
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoad", method = RequestMethod.POST)
	public Map<String, Object> getDijkstra(HttpServletRequest request) {
		System.out.println("first mr huang emmmmmmmmmmmmmm get in");
		// 接 参
		String requestJson = getRequestJson(request);
		// 转换为json数据
		JSONObject jsonData = JSON.parseObject(requestJson);
		// 客户当前位置的经纬度字符串
		// String nowLoAndLa = jsonData.getString("nowLoAndLa");
		String startId = jsonData.getString("startId");
		// 获取当前景点的id
		String childIdString = jsonData.getString("childId");
		// 客户当前位置的经纬度数组
		// String[] nowLoAndLaStrings = nowLoAndLa.split(",");
		// 客户当前位置的经度
		// String nowLoString = nowLoAndLaStrings[0];
		// 客户当前位置的纬度
		// String nowLaString = nowLoAndLaStrings[1];

		// 根据景区内景点id进行查询
		ChildProductPO childProduct = conn_childProduct.getChildById(Long.parseLong(childIdString)).get(0);
		// 获取父id
		long productID = childProduct.getProductID();
		// 通过父id：productId查询所有子景点的信息childProductPOList
		List<ChildProductPO> childProductPOList = conn_childProduct.getChildByProductId(productID);
		// double tempDis=0;
		ChildProductPO start = conn_childProduct.getChildById(Long.parseLong(startId)).get(0);
		/*
		 * for (ChildProductPO childProductPO : childProductPOList) { Double
		 * dis=getDis(Double.parseDouble(nowLaString),
		 * Double.parseDouble(nowLoString),
		 * Double.parseDouble(childProduct.getChildLatitude()),
		 * Double.parseDouble(childProduct.getChildLongitude()));
		 * if(tempDis==0){ tempDis=dis; start=childProductPO; }else
		 * if(tempDis<dis){ tempDis=dis; start=childProductPO; } }
		 */
		// 路线规划
		List<List<ChildProductPO>> roadPlanResult = planRoad(start, childProduct, childProductPOList, null, null);

		for (List<ChildProductPO> list : roadPlanResult) {
			System.out.println("new line:");
			for (ChildProductPO childProductPO : list) {
				System.out.println(childProductPO.getId() + childProductPO.getChildName());
			}
		}

		return success(getshortest(roadPlanResult));
	}

	/**
	 * Liw 评论删除
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/deletepl.do", method = RequestMethod.POST)
	public String deletepl(HttpServletRequest request) throws IOException {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		String userName = pageObject.getString("userId");
		long id = pageObject.getLong("proId");
		List<UserInfoPO> user = conn_userInfo.getUserByUid(Long.parseLong(userName));
		CommentPO com = conn_comment.getComById(id);
		if (com.getUser().getId() == user.get(0).getId()) {
			conn_comment.delete(id);
			return "success";
		} else {
			return "err";
		}
	}

	/**
	 * 核算专业直播申请费用计算XTH
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/checkPrice", method = RequestMethod.POST)
	public Map<String, Object> checkPrice(HttpServletRequest request) throws IOException {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数httpServletRequestParser解析成JSon
		JSONObject parseJSON = httpServletRequestParser.parseJSON();
		// 获取parseJSON中每个值
		String startTime = parseJSON.getString("startTime");
		String endTime = parseJSON.getString("endTime");
		String cameraCount = parseJSON.getString("cameraCount");
		String isSave = parseJSON.getString("isSave");
		String saveHour = parseJSON.getString("saveHour");
		String isMatPlay = parseJSON.getString("isMatPlay");
		// TODO 根据参数计算专业直播所需费用
		// 声明一个map集合
		Map<String, Object> hashMap = new HashMap<String, Object>(16);
		// 将核算出的数值放入map中
		hashMap.put("checkPrice", 55);
		return success(hashMap);
	}

	/**
	 * 专业直播申请
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public Map<String, Object> applyLive(HttpServletRequest request) throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		long userId = params.getLongValue("userId");
		String userType = params.getString("userType");
		LivePO livePO = conn_live.findByUserId(userId);

		if (livePO == null) {

			UserInfoPO user = conn_user.get(userId);
			LivePO newLivePO = new LivePO();
			newLivePO.setUserId(userId);
			newLivePO.setFlag(true);
			newLivePO.setLiveType(LiveType.fromString(userType));
			newLivePO.setLiveStatusType(LiveStatusType.STOP);

			if (LiveType.USER.equals(LiveType.fromString(userType))) {
				newLivePO.setPubName("user" + userId);
			} else {
				MerchantPO merchant = user.getMerchant();
				newLivePO.setMerchantId(merchant.getId());
				newLivePO.setPubName("merchant" + userId);
			}

			conn_live.save(newLivePO);
			livePO = conn_live.findByUserId(userId);
		}

		Long liveId = livePO.getId();
		// 处理专业直播申请:订单
		LiveOrderPO currentLiveOrder = conn_liveOrder.getCurrentLiveOrders(liveId);
		if (currentLiveOrder != null && currentLiveOrder.getStatus().equals(OrderStateType.NOTPAY)) {
			Long liverOrderId = currentLiveOrder.getId();
			conn_subLive.deleteByOrderId(liverOrderId);
			conn_liveOrder.deleteByLiveId(liveId);
		}
		int cameraCount = params.getIntValue("cameraCount");
		// int recordSize = params.getIntValue("recordSize");// MB
		String startTime = params.getString("startTime");
		String endTime = params.getString("endTime");
		String isSave = params.getString("isSave");
		String isMatPlay = params.getString("isMatPlay");
		int totalPrice = params.getIntValue("totalPrice");
		// 日期转换格式
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		Date startTimeeDate = simpleDateFormat.parse(startTime + "0分" + "0秒");
		Date endTimeeDate = simpleDateFormat.parse(endTime + "0分" + "0秒");
		// 日期转换格式
		SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LiveOrderPO liveOrderPO = new LiveOrderPO();
		liveOrderPO.setLiveId(liveId);
		liveOrderPO.setCount(cameraCount);
		liveOrderPO.setStatus(OrderStateType.NOTPAY);
		liveOrderPO.setStartTime(DateUtil.parse(simpleDateFormatt.format(startTimeeDate), DateUtil.dateTimePattern));
		liveOrderPO.setEndTime(DateUtil.parse(simpleDateFormatt.format(endTimeeDate), DateUtil.dateTimePattern));
		liveOrderPO.setTotalFee(totalPrice);
		if (isSave.equals("YES")) {
			// TODO 录制空间如何计算还未确定
			// 随机得到两位数10-99的数值
			int recordSize = (int) (Math.random() * 90 + 10);
			liveOrderPO.setRecordSize(recordSize);
			// int saveHour = params.getIntValue("saveHour");
		} else {
			liveOrderPO.setRecordSize(100);
		}
		if (isMatPlay.equals("YES")) {
			liveOrderPO.setIsMatPlay(1);
		} else {
			liveOrderPO.setIsMatPlay(0);
		}
		conn_liveOrder.saveOrUpdate(liveOrderPO);
		// 处理专业直播申请:机位
		List<SubLivePO> subLivePOList = new ArrayList<SubLivePO>();
		for (int i = 0; i < cameraCount; i++) {
			SubLivePO subLivePO = new SubLivePO();
			subLivePO.setLiveName(liveId + "Asub" + (i + 1));
			subLivePO.setLiveId(liveId);
			subLivePO.setStatus(LiveStatusType.STOP);
			subLivePO.setCameraNumber(i + 1);
			subLivePO.setLiveOrderPO(liveOrderPO);
			conn_subLive.save(subLivePO);
			subLivePOList.add(subLivePO);
		}
		liveOrderPO.setSubLivePOs(subLivePOList);
		// 处理专业直播申请:导播
		ProfessionalLiveDirectorPO professionalLiveDirectorPO = new ProfessionalLiveDirectorPO();
		professionalLiveDirectorPO.setInUse(0);
		professionalLiveDirectorPO.setLiveId(liveId);
		conn_professionalLiveDirectorDao.saveOrUpdate(professionalLiveDirectorPO);

		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("id", liveOrderPO.getId() + "");
		hashMap.put("liveId", liveOrderPO.getLiveId() + "");
		String startDateResult = simpleDateFormat.format(liveOrderPO.getStartTime());
		hashMap.put("startTime", startDateResult);
		String endDateResult = simpleDateFormat.format(liveOrderPO.getEndTime());
		hashMap.put("endTime", endDateResult);
		hashMap.put("count", liveOrderPO.getCount() + "");
		hashMap.put("recordSize", liveOrderPO.getRecordSize() + "");
		hashMap.put("status", liveOrderPO.getStatus() + "");
		hashMap.put("isMatPlay", liveOrderPO.getIsMatPlay() + "");
		hashMap.put("totalFee", liveOrderPO.getTotalFee() + "");
		return success(hashMap);
	}

	/**
	 * 专业(professional)直播支付
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/professionalLivePay", method = RequestMethod.POST)
	public Map<String, Object> professionalLivePay(HttpServletRequest request) throws Exception {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数parser解析成JSon
		JSONObject params = httpServletRequestParser.parseJSON();
		// 支付类型
		String payType = params.getString("payType");
		// 总价
		long totalFee = params.getLongValue("totalFee");
		// 订单id
		String orderId = params.getString("orderId");
		// 调微信和支付宝
		// 微信
		if (payType.equals("WEICHAT")) {
			Map<String, String> weichatPay = weichatPay(totalFee, orderId);
			weichatPay.put("orderId", orderId);
			return success(weichatPay);
			// 支付宝
		} else if (payType.equals("ALIPAY")) {
			//
			String sign = AliAppOrderInfo.getInstance().getSign("1", totalFee, "过来玩专业直播", orderId);
			// 声明一个map容器
			HashMap<String, Object> data = new HashMap<String, Object>(16);
			data.put("orderId", orderId);
			data.put("orderInfo", sign);
			return success(data);
		} else {
			return ERROR("系统错误！");
		}
	}

	/**
	 * 专业直播修改订单状态接口
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@ResponseBody
	@RequestMapping(value = "/professionalLiveUpdateOrderState", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveUpdateOrderState(HttpServletRequest request) throws Exception {
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		JSONObject params = httpServletRequestParser.parseJSON();
		long userId = params.getLongValue("userId");
		String orderStatus = params.getString("orderStatus");

		LivePO livePO = conn_live.findByUserId(userId);
		Long liveId = livePO.getId();
		LiveOrderPO liveOrderPO = conn_liveOrder.getCurrentLiveOrders(liveId);
		switch (orderStatus) {
		case "PAYSUCCESS":
			liveOrderPO.setStatus(OrderStateType.PAYSUCCESS);
			break;
		}
		conn_liveOrder.save(liveOrderPO);
		return success();
	}

	/**
	 * 专业直播获取订单信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/professionalLiveGetOrderInfo", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveGetOrderInfo(HttpServletRequest request) throws Exception {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数parser解析成JSon
		JSONObject params = httpServletRequestParser.parseJSON();
		// 用户id
		long userId = params.getLongValue("userId");
		// 查找userId对应的liveId
		LivePO livePO = conn_live.findByUserId(userId);
		Map<String, String> resultMap = new HashMap<String, String>();
		if (livePO != null) {
			Long liveId = livePO.getId();
			// 修改liveId对应的liveOrder数据的status
			LiveOrderPO liveOrderPO = conn_liveOrder.getCurrentLiveOrders(liveId);
			// 封装返回数据结果集
			// TODO 存储小时数需要换算
			if (liveOrderPO != null) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
				resultMap.put("id", liveOrderPO.getId() + "");
				resultMap.put("liveId", liveOrderPO.getLiveId() + "");
				String startDateResult = simpleDateFormat.format(liveOrderPO.getStartTime());
				resultMap.put("startTime", startDateResult);
				String endDateResult = simpleDateFormat.format(liveOrderPO.getEndTime());
				resultMap.put("endTime", endDateResult);
				resultMap.put("count", liveOrderPO.getCount() + "");
				resultMap.put("recordSize", liveOrderPO.getRecordSize() + "");
				resultMap.put("status", liveOrderPO.getStatus() + "");
				resultMap.put("isMatPlay", liveOrderPO.getIsMatPlay() + "");
				resultMap.put("totalFee", liveOrderPO.getTotalFee() + "");
			}
		}
		return success(resultMap);
	}

	/**
	 * 专业直播判断直播机位是否可用
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/professionalLivegetCameraUsable", method = RequestMethod.POST)
	public Map<String, Object> professionalLivegetCameraUsable(HttpServletRequest request) throws Exception {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数parser解析成JSon
		JSONObject params = httpServletRequestParser.parseJSON();
		long liveId = params.getLongValue("liveId");
		int cameraNumber = params.getIntValue("cameraNumber");

		SubLivePO subLivePO = conn_subLive.getSubLivePO(liveId, cameraNumber);
		Map<String, String> resultMap = new HashMap<String, String>();
		if (subLivePO != null) {
			int inUse = subLivePO.getInuse();
			resultMap.put("liveId", liveId + "");
			resultMap.put("cameraNumber", cameraNumber + "");
			resultMap.put("inUse", inUse + "");
		}
		return success(resultMap);
	}

	/**
	 * 专业直播修：机位开始直播
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveStartSubLive", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveStartSubLive(HttpServletRequest request) throws Exception {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数parser解析成JSon
		JSONObject params = httpServletRequestParser.parseJSON();
		long liveId = params.getLongValue("liveId");
		int cameraNumber = params.getIntValue("cameraNumber");
		String liveName = params.getString("liveName");
		LivePO livePO = conn_live.get(liveId);
		SubLivePO subLivePO = conn_subLive.getSubLivePO(liveId, cameraNumber);
		Map<String, String> resultMap = new HashMap<String, String>();
		// 修改机位状态为被使用
		subLivePO.setInuse(1);
		subLivePO.setLiveName(liveName);
		subLivePO.setPubName(subLivePO.getId() + livePO.getPubName());
		subLivePO.setStatus(LiveStatusType.LIVING);
		conn_subLive.saveOrUpdate(subLivePO);
		resultMap.put("id", subLivePO.getId() + "");
		resultMap.put("liveId", liveId + "");
		resultMap.put("liveName", liveName);
		resultMap.put("pubName", subLivePO.getPubName());
		resultMap.put("inUse", subLivePO.getInuse() + "");
		resultMap.put("status", subLivePO.getStatus() + "");
		return success(resultMap);
	}

	/**
	 * 专业直播修：机位关闭直播
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveStopSubLive", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveStopSubtLive(HttpServletRequest request) throws Exception {
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		long liveId = params.getLongValue("liveId");
		long subLiveId = params.getLongValue("subLiveId");
		LivePO livePO = conn_live.get(liveId);
		SubLivePO subLivePO = conn_subLive.get(subLiveId);
		ProfessionalLiveDirectorPO professionalLiveDirectorPO = conn_professionalLiveDirectorDao.findByLiveId(liveId);
		String cameraCanClose = professionalLiveDirectorPO.getCameraCanClose();
		if (cameraCanClose.equals("YES")) {
			// 可关闭状态
			// 修改机位状态为未被使用
			subLivePO.setInuse(0);
			subLivePO.setStatus(LiveStatusType.STOP);
			conn_subLive.saveOrUpdate(subLivePO);
			return success("YES");
		} else {
			return success("NO");
		}

	}

	/**
	 * 专业直播修：机位开始直播开启服务
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveStartSubLiveService", method = RequestMethod.POST)
	public Object professionalLiveStartSubLiveService(HttpServletRequest request) throws Exception {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数parser解析成JSon
		JSONObject params = httpServletRequestParser.parseJSON();
		long liveId = params.getLongValue("liveId");
		long subLiveId = params.getLongValue("subLiveId");
		LivePO livePO = conn_live.get(liveId);
		SubLivePO subLivePO = conn_subLive.get(subLiveId);
		GuoliawanLiveServiceWrapper.getInstance().startSubLive(livePO.getPubName(), subLivePO.getPubName());
		return null;
	}

	/**
	 * 专业直播修：机位直播停止服务
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveStopSubLiveService", method = RequestMethod.POST)
	public Object professionalLiveStopSubLiveService(HttpServletRequest request) throws Exception {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数parser解析成JSon
		JSONObject params = httpServletRequestParser.parseJSON();
		long liveId = params.getLongValue("liveId");
		long subLiveId = params.getLongValue("subLiveId");
		LivePO livePO = conn_live.get(liveId);
		SubLivePO subLivePO = conn_subLive.get(subLiveId);
		GuoliawanLiveServiceWrapper.getInstance().stopSubLive(livePO.getPubName(), subLivePO.getPubName());
		return null;
	}

	/**
	 * 专业直播评论
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveSendMessage", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveSendMessage(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String commentMessage = pageObject.getString("commentMessage");
		Long userId = Long.parseLong(pageObject.getString("userId"));
		Long liveId = Long.parseLong(pageObject.getString("liveId"));

		ProfessionalLiveMessagePO professionalLiveMessage = new ProfessionalLiveMessagePO();
		professionalLiveMessage.setUpdateTime(new Date());
		professionalLiveMessage.setUserId(userId);
		professionalLiveMessage.setUserName(conn_user.get(userId).getUserNickname());
		professionalLiveMessage.setLiveId(liveId);
		professionalLiveMessage.setMessage(commentMessage);
		professionalLiveMessage.setFlag(false);

		conn_professionalLiveMessageDao.save(professionalLiveMessage);
		return success();
	}

	/**
	 * 专业直播判断导播机位是否可用
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDirectorUsable", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDirectorUsable(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		JSONObject pageObject = JSON.parseObject(param);
		Long liveId = Long.parseLong(pageObject.getString("liveId"));

		ProfessionalLiveDirectorPO professionalLiveDirectorPO = conn_professionalLiveDirectorDao.findByLiveId(liveId);
		Long id = professionalLiveDirectorPO.getId();
		int inUse = professionalLiveDirectorPO.getInUse();

		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("id", id + "");
		resultMap.put("liveId", liveId + "");
		resultMap.put("inUse", inUse + "");
		return success(resultMap);
	}

	/**
	 * 专业直播修改导播位使用状态
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveUpdateDirectorUsable", method = RequestMethod.POST)
	public Object professionalLiveUpdateDirectorUsable(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		Long id = jsonObject.getLongValue("id");
		String isUsed = jsonObject.getString("isUsed");

		ProfessionalLiveDirectorPO professionalLiveDirectorPO = conn_professionalLiveDirectorDao.findById(id);

		if (isUsed.equals("YES")) {
			professionalLiveDirectorPO.setInUse(1);
		}
		if (isUsed.equals("NO")) {
			professionalLiveDirectorPO.setInUse(0);
		}

		conn_professionalLiveDirectorDao.saveOrUpdate(professionalLiveDirectorPO);

		return null;
	}

	/**
	 * 专业直播导播获取机位信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDirectorGetEveryCameraInfo", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDirectorGetEveryCameraInfo(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		Long liveId = jsonObject.getLongValue("liveId");

		List<SubLivePO> subLivePOList = conn_subLive.findSubLivePOsByLiveId(liveId);
		List<SubLiveVO> subLiveVOList = SubLiveVO.getConverter(SubLiveVO.class).convert(subLivePOList, SubLiveVO.class);

		LiveOrderPO liveOrder = conn_liveOrder.findLiveOrderByLiveId(liveId);

		Map<String, Object> resultMap = new HashMap<>();
		if (liveOrder.getRecordSize() > 0) {
			resultMap.put("isRecord", "1");
		} else {
			resultMap.put("isRecord", "0");
		}
		resultMap.put("isMatPlay", liveOrder.getIsMatPlay() + "");
		resultMap.put("subLives", subLiveVOList);
		return success(resultMap);
	}

	/**
	 * 专业直播导播获取机位播放状态接口
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDirectorGetCameraLiveState", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDirectorGetCameraLiveState(HttpServletRequest request) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		long id = jsonObject.getLongValue("id");
		SubLivePO subLivePO = conn_subLive.findSubLivePOById(id);
		long liveId = subLivePO.getLiveId();
		LiveOrderPO liveOrderPO = conn_liveOrder.findLiveOrderByLiveId(liveId);

		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("id", id + "");
		resultMap.put("liveId", liveId + "");
		resultMap.put("liveName", subLivePO.getLiveName());
		resultMap.put("pubName", subLivePO.getPubName());
		resultMap.put("cameraNumber", subLivePO.getCameraNumber() + "");
		resultMap.put("inuse", subLivePO.getInuse() + "");
		resultMap.put("status", subLivePO.getStatus() + "");
		if (liveOrderPO.getRecordSize() > 0) {
			resultMap.put("isRecord", 1 + "");
		} else {
			resultMap.put("isRecord", 0 + "");
		}
		resultMap.put("isMatPlay", liveOrderPO.getIsMatPlay() + "");
		return success(resultMap);
	}

	/**
	 * 专业直播:上传垫播视频
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadMatPlayVedio.do", method = RequestMethod.POST)
	public Map<String, Object> uploadMatPlayVedio(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("vedioFile") CommonsMultipartFile vedioFile) throws Exception {

		// 保存路径
		String storePath = conn_sysConfig.getSysConfig().getFolderUrl() + File.separator + "mat_play_video"
				+ File.separator;
		// 文件名:文件名就是liveId
		String vedioFileName = vedioFile.getOriginalFilename();
		storePath = storePath + vedioFileName + File.separator;
		// 存储文件夹
		File folder = new File(storePath);
		if (folder.exists() == false) {
			// 没有就新建
			folder.mkdir();
		} else {
			// 文件夹存在删除文件保证只有一个垫播视频存在
			File[] filesInFolder = folder.listFiles();
			// 遍历删除文件
			for (File file : filesInFolder) {
				file.delete();
			}
		}
		// 上传文件
		storePath = storePath + vedioFileName + ".mp4";
		File toUploadFile = new File(storePath);
		vedioFile.transferTo(toUploadFile);
		// videoPath 格式
		// G:\Develop\apache-tomcat-7.0.92-windows-x64\apache-tomcat-7.0.92\webapps\file\mat_play_vedio\46\46.mp4
		String videoPath = storePath;
		ProfessionalLiveMatPlayVedioPO professionalLiveMatPlayVedioPO = conn_professionalLiveMatPlayVedioDao
				.findByLiveId(Long.parseLong(vedioFileName));
		if (professionalLiveMatPlayVedioPO != null) {
			professionalLiveMatPlayVedioPO.setVedioPath(videoPath);
			conn_professionalLiveMatPlayVedioDao.saveOrUpdate(professionalLiveMatPlayVedioPO);
		} else {
			professionalLiveMatPlayVedioPO = new ProfessionalLiveMatPlayVedioPO();
			professionalLiveMatPlayVedioPO.setLiveId(Long.parseLong(vedioFileName));
			professionalLiveMatPlayVedioPO.setVedioPath(videoPath);
			conn_professionalLiveMatPlayVedioDao.saveOrUpdate(professionalLiveMatPlayVedioPO);
		}
		return success();
	}

	/**
	 * 专业直播修：导播开启垫播服务,关闭垫播服务不用做,APP关闭直播时判断哪个录制未关闭需要用户点击关闭
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveStartMatPlayService", method = RequestMethod.POST)
	public Object professionalLiveStartMatPlayService(HttpServletRequest request) throws Exception {
		// 接参
		HttpServletRequestParser httpServletRequestParser = new HttpServletRequestParser(request);
		// 将参数parser解析成JSon
		JSONObject params = httpServletRequestParser.parseJSON();
		long liveId = params.getLongValue("liveId");
		LivePO livePO = conn_live.get(liveId);
		ProfessionalLiveMatPlayVedioPO professionalLiveMatPlayVedioPO = conn_professionalLiveMatPlayVedioDao
				.findByLiveId(liveId);
		GuoliawanLiveServiceWrapper.getInstance().startMatPlay(livePO.getPubName(), liveId + "",
				professionalLiveMatPlayVedioPO.getVedioPath());
		return null;
	}

	/**
	 * 专业直播:导播位开始/关闭直播 关闭直播时liveName传空串
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDirectorUpdateLiveState", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDirectorUpdateLiveState(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);

		long liveId = jsonObject.getLongValue("liveId");
		String liveName = jsonObject.getString("liveName");
		String liveStatusType = jsonObject.getString("liveStatusType");

		ProfessionalLivePO findResultProfessionalLivePO = conn_professionalLiveDao
				.findProfessionalLivePOByLiveId(liveId);
		LivePO livePO = conn_live.get(liveId);
		String pubName = livePO.getPubName();

		findResultProfessionalLivePO.setLiveId(liveId);
		if (!liveName.equals("")) {
			findResultProfessionalLivePO.setLiveName(liveName);
		}
		if (liveStatusType.equals("LIVING")) {
			findResultProfessionalLivePO.setLiveStatusType("LIVING");
			conn_professionalLiveDao.saveOrUpdate(findResultProfessionalLivePO);
			// 这里无需调用导播位开直播方法
			// APP使用逻辑是先开机位直播，在设置主信号流，再开导播直播
			// 设置主信号流，调用professionalLiveDirectorChangeBroadCastCamera
			// 在professionalLiveDirectorChangeBroadCastCamera这个方法中会调用导播开直播方法
			// 所以注释下边啥也没有,慌了的话 ctrl + f 去看看
			// professionalLiveDirectorChangeBroadCastCamera
		} else {
			findResultProfessionalLivePO.setLiveStatusType("STOP");
			findResultProfessionalLivePO.setBroadcastCamera(0);
			conn_professionalLiveDao.saveOrUpdate(findResultProfessionalLivePO);
			GuoliawanLiveServiceWrapper.getInstance().stopLive(pubName);
		}
		return success();
	}

	/**
	 * 专业直播导播修改直播名称：这个名称在直播列表显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDirectorUpdateLiveName", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDirectorUpdateLiveName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);

		Long liveId = jsonObject.getLongValue("liveId");
		String liveName = jsonObject.getString("liveName");

		ProfessionalLivePO findResultProfessionalLivePO = conn_professionalLiveDao
				.findProfessionalLivePOByLiveId(liveId);
		findResultProfessionalLivePO.setLiveName(liveName);
		conn_professionalLiveDao.saveOrUpdate(findResultProfessionalLivePO);

		return success();
	}

	/**
	 * 专业直播观看用户获取当前直播状态 直播状态指的是：当前主播机位是哪个，以便于拿URL
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveWatcherGetLiveState", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveWatcherGetLiveState(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		Long liveId = jsonObject.getLongValue("liveId");

		ProfessionalLivePO findResultProfessionalLivePO = conn_professionalLiveDao
				.findProfessionalLivePOByLiveId(liveId);
		LivePO livePO = conn_live.get(liveId);

		int broadcastCamera = findResultProfessionalLivePO.getBroadcastCamera();
		String liveName = findResultProfessionalLivePO.getLiveName();

		String streamUrl = "rtmp://" + WXContants.Website + "/live/";
		if (broadcastCamera == 0) {
			streamUrl = "";
		} else {
			// 拼live表中的pubName
			streamUrl = streamUrl + livePO.getPubName();
		}
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("liveName", liveName);
		resultMap.put("vedioUrl", streamUrl);

		return success(resultMap);
	}

	/**
	 * 专业直播核对导播位状态：用于导播界面打开时的场景控制 导播界面载入时最先请求的接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveCheckDirectorLiveState", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveCheckDirectorLiveState(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		Long liveId = jsonObject.getLongValue("liveId");

		ProfessionalLivePO findResultProfessionalLivePO = conn_professionalLiveDao
				.findProfessionalLivePOByLiveId(liveId);
		ProfessionalLiveDirectorPO professionalLiveDirectorPO = conn_professionalLiveDirectorDao.findByLiveId(liveId);
		Map<String, String> resultMap = new HashMap<>();
		if (findResultProfessionalLivePO == null) {
			// 为空说明导播位未使用过第一次进入界面
			resultMap.put("liveName", "");
			resultMap.put("broadCastCamera", "");
			resultMap.put("liveStatus", "");
			resultMap.put("cameraCanClose", professionalLiveDirectorPO.getCameraCanClose());
			return success(resultMap);
		} else {
			resultMap.put("liveName", findResultProfessionalLivePO.getLiveName());
			resultMap.put("broadCastCamera", findResultProfessionalLivePO.getBroadcastCamera() + "");
			resultMap.put("liveStatus", findResultProfessionalLivePO.getLiveStatusType());
			resultMap.put("cameraCanClose", professionalLiveDirectorPO.getCameraCanClose());
			return success(resultMap);
		}
	}

	/**
	 * 专业直播设置信号流机位接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDirectorChangeBroadCastCamera", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDirectorChangeBroadCastCamera(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		long liveId = jsonObject.getLongValue("liveId");
		int newBroadCastCamera = Integer.parseInt(jsonObject.getString("broadCastCamera"));

		ProfessionalLivePO findResultProfessionalLivePO = conn_professionalLiveDao
				.findProfessionalLivePOByLiveId(liveId);

		int oldBroadCastCamera = 0;
		if (findResultProfessionalLivePO == null) {
			// 数据库里有没有liveId对应的数据，插！
			oldBroadCastCamera = 0;
			ProfessionalLivePO professionalLivePO = new ProfessionalLivePO();
			professionalLivePO.setLiveId(liveId);
			professionalLivePO.setBroadcastCamera(newBroadCastCamera);
			conn_professionalLiveDao.saveOrUpdate(professionalLivePO);
		} else {
			// 数据库里有liveId对应的数据，不插！
			oldBroadCastCamera = findResultProfessionalLivePO.getBroadcastCamera();
			findResultProfessionalLivePO.setLiveId(liveId);
			findResultProfessionalLivePO.setLiveName("");
			findResultProfessionalLivePO.setBroadcastCamera(newBroadCastCamera);
			findResultProfessionalLivePO.setLiveStatusType("STOP");
			findResultProfessionalLivePO.setLiveType("PROFESSIONAL_LIVE");
			conn_professionalLiveDao.saveOrUpdate(findResultProfessionalLivePO);
		}
		// 进行切流
		if (oldBroadCastCamera == 0) {
			// 调用导播位开启直播方法
			if (newBroadCastCamera != 7) {
				// 7为垫播逻辑和机位不同
				SubLivePO subLivePO = conn_subLive.getSubLivePO(liveId, newBroadCastCamera);
				LivePO livePO = conn_live.get(liveId);
				GuoliawanLiveServiceWrapper.getInstance().startLive(livePO.getPubName(), subLivePO.getPubName());
			} else {
				LivePO livePO = conn_live.get(liveId);
				GuoliawanLiveServiceWrapper.getInstance().startLive(livePO.getPubName(), liveId + "");
			}
		} else {
			if (newBroadCastCamera != 7) {
				// 还需要区分oldBroadCastCamera是否为垫播
				if (oldBroadCastCamera != 7) {
					// 调用切流方法
					SubLivePO newCameraSubLivePO = conn_subLive.getSubLivePO(liveId, newBroadCastCamera);
					SubLivePO oldCameraSubLivePO = conn_subLive.getSubLivePO(liveId, oldBroadCastCamera);
					LivePO livePO = conn_live.get(liveId);
					GuoliawanLiveServiceWrapper.getInstance().switchSubLive(livePO.getPubName(),
							oldCameraSubLivePO.getPubName(), newCameraSubLivePO.getPubName());
				} else {
					SubLivePO newCameraSubLivePO = conn_subLive.getSubLivePO(liveId, newBroadCastCamera);
					LivePO livePO = conn_live.get(liveId);
					GuoliawanLiveServiceWrapper.getInstance().switchSubLive(livePO.getPubName(), liveId + "",
							newCameraSubLivePO.getPubName());
				}
			} else {
				SubLivePO oldCameraSubLivePO = conn_subLive.getSubLivePO(liveId, oldBroadCastCamera);
				LivePO livePO = conn_live.get(liveId);
				GuoliawanLiveServiceWrapper.getInstance().switchSubLive(livePO.getPubName(),
						oldCameraSubLivePO.getPubName(), liveId + "");
			}

		}
		return success();
	}

	/**
	 * 专业直播导播机位开始录制
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveStartRecord", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveStartRecord(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		long liveId = jsonObject.getLongValue("liveId");
		long subLiveId = jsonObject.getLongValue("subLiveId");
		SubLivePO subLivePO = conn_subLive.findSubLivePOById(subLiveId);
		// 保存路径
		String storePath = conn_sysConfig.getSysConfig().getFolderUrl() + File.separator + "record_video"
				+ File.separator + liveId + File.separator;
		// 发布url
		// conn_sysConfig.getSysConfig().getWebUrl():http://localhost:8080/file/
		String pubUrl = conn_sysConfig.getSysConfig().getWebUrl() + "record_video";
		File folder = new File(storePath);
		if (folder.exists() == false) {
			// 没有就新建
			folder.mkdir();
		}
		// 开始录制
		subLivePO.setRecordState("RECORDING");
		conn_subLive.saveOrUpdate(subLivePO);
		LiveRecordPO liveRecordPO = new LiveRecordPO();
		liveRecordPO.setLiveId(liveId);
		liveRecordPO.setSubLiveId(subLiveId);
		liveRecordPO.setSubLiveName(subLivePO.getLiveName());
		liveRecordPO.setStartTime(new Date());
		// 需要替换路径中的"\"为"/"否则Recorder无法识别
		storePath.replace("\\", "/");
		liveRecordPO.setRecordName(new Date().getTime() + "");
		storePath = storePath + liveRecordPO.getRecordName() + ".mp4";
		liveRecordPO.setLocalPath(storePath);
		pubUrl = pubUrl + "/" + liveId + "/" + liveRecordPO.getRecordName() + ".mp4";
		liveRecordPO.setPubUrl(pubUrl);
		GuoliawanLiveServiceWrapper.getInstance().startRecord(subLivePO.getPubName(), storePath);
		conn_recordDao.save(liveRecordPO);
		return success();
	}

	/**
	 * 专业直播导播机位停止录制
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveStopRecord", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveStopRecord(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		long liveId = jsonObject.getLongValue("liveId");
		long subLiveId = jsonObject.getLongValue("subLiveId");
		SubLivePO subLivePO = conn_subLive.findSubLivePOById(subLiveId);
		// 停止录制
		subLivePO.setRecordState("STOP");
		conn_subLive.saveOrUpdate(subLivePO);
		LiveRecordPO liveRecordPO = conn_recordDao.findByLiveIdAndSubLiveIdAndIng(liveId, subLiveId);
		liveRecordPO.setEndTime(new Date());
		GuoliawanLiveServiceWrapper.getInstance().stopRecord(subLivePO.getPubName());
		conn_recordDao.saveOrUpdate(liveRecordPO);
		return success();
	}

	/**
	 * 专业直播获取录制视频列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveGetRecordVideoList", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveGetRecordVideoList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		long userId = jsonObject.getLongValue("userId");
		int currentPage = jsonObject.getIntValue("currentPage");

		LivePO livePO = conn_live.findByUserId(userId);
		Long liveId = livePO.getId();

		List<LiveRecordPO> liveRecordPOList = conn_recordDao.getVideoList(liveId, currentPage, pageSize);
		List<LiveRecordVO> liveRecordVOList = LiveRecordVO.getConverter(LiveRecordVO.class).convert(liveRecordPOList,
				LiveRecordVO.class);

		return success(liveRecordVOList);
	}

	/**
	 * 专业直播删除录制视频列表数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDeleteRecordVideoListItem", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDeleteRecordVideoListItem(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		long id = jsonObject.getLongValue("id");

		LiveRecordPO liveRecordPO = conn_recordDao.findById(id);
		// 删除文件
		File file = new File(liveRecordPO.getLocalPath());
		if (file.exists()) {
			file.delete();
		}
		// 删除数据
		conn_recordDao.delete(liveRecordPO);
		return success();
	}

	/**
	 * 专业直播:导播界面修改机位是否可关闭直播
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/professionalLiveDirectorUpdateCameraCanClose", method = RequestMethod.POST)
	public Map<String, Object> professionalLiveDirectorUpdateCameraCanClose(HttpServletRequest request)
			throws Exception {
		String param = getRequestJson(request);
		JSONObject jsonObject = JSON.parseObject(param);
		long liveId = jsonObject.getLongValue("liveId");
		// YES 或者 NO
		String cameraCanClose = jsonObject.getString("cameraCanClose");
		ProfessionalLiveDirectorPO professionalLiveDirectorPO = conn_professionalLiveDirectorDao.findByLiveId(liveId);
		professionalLiveDirectorPO.setCameraCanClose(cameraCanClose);
		conn_professionalLiveDirectorDao.saveOrUpdate(professionalLiveDirectorPO);
		return success();
	}

	/**
	 * 点赞方法 张羽
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/giveLike", method = RequestMethod.POST)
	public Map<String, Object> giveLike(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		param = param.replace("\\n", "");
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		Long liveId = Long.parseLong(pageObject.getString("liveId"));
		LivePO live = conn_live.get(liveId);
		live.setGiveLike(live.getGiveLike() + 1);
		conn_live.saveOrUpdate(live);
		return success(live);
	}

	/**
	 * 直播 获取广告轮播图 张羽 5/8
	 * 
	 * @param comCode
	 * @return products
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getAdvertisement", method = RequestMethod.GET)
	public Map<String, Object> getAdvertisement(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<LiveAdvertisementPO> advertisement = conn_liveAdvertisementDao.getList();
		List<LiveAdvertisementVO> advertisementvo = LiveAdvertisementVO.getConverter(LiveAdvertisementVO.class)
				.convert(advertisement, LiveAdvertisementVO.class);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		for (LiveAdvertisementVO rpo : advertisementvo) {
			rpo.setSlidepic(sysConfig.getWebUrl() + rpo.getSlidepic());
		}
		return success(advertisementvo);
	}

	@Autowired
	private LiveRebroadcastDAO conn_rdvertisementDao;

	@ResponseBody
	@RequestMapping(value = "/getAllRebroadcast", method = RequestMethod.POST)
	public Map<String, Object> getAllRebroadcast(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<LiveRebroadcastPO> rdvertisement = conn_rdvertisementDao.findAll();
		List<LiveRebroadcastVO> rdvertisementvo = LiveRebroadcastVO.getConverter(LiveRebroadcastVO.class)
				.convert(rdvertisement, LiveRebroadcastVO.class);
		return success(rdvertisementvo);
	}

	@Autowired
	private LiveGiftDAO conn_giftDao;

	@ResponseBody
	@RequestMapping(value = "/pushGift", method = RequestMethod.POST)
	public Map<String, Object> pushGift(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<LiveGiftPO> gifts = conn_giftDao.GetListbysort();
		List<LiveGiftVO> giftsvo = LiveGiftVO.getConverter(LiveGiftVO.class).convert(gifts, LiveGiftVO.class);
		return success(giftsvo);
	}

	// 获取用户钱包里的钱 张羽
	@ResponseBody
	@RequestMapping(value = "/wallet", method = RequestMethod.GET)
	public Map<String, Object> getWalletMoney(HttpServletRequest request, long userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		UserInfoPO user = conn_user.get(userId);
		UserInfoVO _user = new UserInfoVO().set(user);
		map.put("data", _user);
		return success(map);
	}

	// 封住物流信息的方法
	@ResponseBody
	@RequestMapping(value = "/logisticspage", method = RequestMethod.GET)
	public List<Map> logisticspage(HttpServletRequest request, Long orderId) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 订单
		OrderInfoPO order = conn_order.get(orderId);
		OrderInfoVO _order = new OrderInfoVO().set(order);

		LogisticsPo logisticsPo = conn_logistics.get(_order.getLogisticsId());

		ExpressPO findByUserId = expressdao.findByUserId(logisticsPo.getName());
		KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
		String orderTracesByJson = api.getOrderTracesByJson(findByUserId.getF2(), _order.getTrackingnumber());
		String aString = "[" + orderTracesByJson + "]";
		List<Map> list = JSONObject.parseArray(aString, Map.class);
		List<Map> list1 = (List<Map>) list.get(0).get("Traces");
		return list1;
	}

	// 封装农行需要的数据
	@RequestMapping(value = "/agriculturepay", method = RequestMethod.GET)
	public ModelAndView Agriculturalbanktopay(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("demo/MerchantPaymentIE");
		String sdate;
		Date ddate = new Date();
		sdate = (new SimpleDateFormat("YYYY/MM/DD"/* 日期格式 */)).format(ddate);
		String mm = (new SimpleDateFormat("HH:MM:ss"/* 时分秒格式 */)).format(ddate);
		request.setAttribute("PayTypeID", "ImmediatePay");// 设定交易类型
		request.setAttribute("OrderDate", "2019/06/15");// 设定订单日期
		request.setAttribute("OrderTime", mm);// 设定订单时间
		request.setAttribute("orderTimeoutDate", "20141019104901");// 设定订单有效期
		request.setAttribute("OrderNo", "ON20140924004");// 设定订单编号（必要信息）
		request.setAttribute("CurrencyCode", "156");// 设定交易币种
		request.setAttribute("PaymentRequestAmount", "2.0");// 设定交易金额
		request.setAttribute("Fee", "1.0");// 设定手续费金额
		request.setAttribute("AccountNo", "6228481259151909273");// 设定支付账户
		request.setAttribute("OrderDesc", "测试一下");// 说明
		request.setAttribute("OrderURL", "测试一下");// 订单地址
		request.setAttribute("ReceiverAddress", "收货地址");// 说明
		request.setAttribute("InstallmentMark", "0");// 分期标识
		request.setAttribute("CommodityType", "0101");// 商品种类
		request.setAttribute("BuyIP", "127.0.0.1");// 客户交易ip
		request.setAttribute("ExpiredDate", "1");// 订单保存时间
		request.setAttribute("PaymentType", "1");// 支付类型
		request.setAttribute("PaymentLinkType", "1");// 交易渠道1：internet网络接入////2：手机网络接入
														// 3：数字电视网络接入////4：智能客户端
		request.setAttribute("NotifyType", "0");// 通知方式 0：URL页面通知 1：服务器通知
		request.setAttribute("ResultNotifyURL", "http://yourwebsite/appname/MerchantResult.jsp");// 通知//URL////地址
		request.setAttribute("IsBreakAccount", "0");// 交易是否分账；是否支持向二级商户入账必须设定，////0:否1:是（二次清分商户必须填
													// 1）
		request.setAttribute("ReceiveAccount", "622848125915190921273");// 收款方账号
		request.setAttribute("MerchantRemarks", "测试一下咯");
		request.setAttribute("ReceiveAccName", "董谡焱");// ReceiveAccName
		request.setAttribute("SplitAccTemplate", "123465");
		return mv;
	}

}