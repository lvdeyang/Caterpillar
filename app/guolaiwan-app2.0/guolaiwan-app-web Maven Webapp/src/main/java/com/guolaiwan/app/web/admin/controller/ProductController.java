package com.guolaiwan.app.web.admin.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;
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

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.ChildProductVO;
import com.guolaiwan.app.web.admin.vo.CommentVO;
import com.guolaiwan.app.web.admin.vo.DistributeProductVO;
import com.guolaiwan.app.web.admin.vo.LanVO;
import com.guolaiwan.app.web.admin.vo.ProductPackVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.distribute.vo.DistributePolicyVo;
import com.guolaiwan.app.web.distribute.vo.RegionVo;
import com.guolaiwan.bussiness.admin.dao.BewriteDAO;
import com.guolaiwan.bussiness.admin.dao.ChildPicAndContentDAO;
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.dao.CommentDAO;
import com.guolaiwan.bussiness.admin.dao.GroupBuyDAO;
import com.guolaiwan.bussiness.admin.dao.LanDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.PicproRelationDAO;
import com.guolaiwan.bussiness.admin.dao.PictureDAO;
import com.guolaiwan.bussiness.admin.dao.ProductComboDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.ProductLayerDAO;
import com.guolaiwan.bussiness.admin.dao.ProductPackDAO;
import com.guolaiwan.bussiness.admin.dao.ProductRegionDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.enumeration.ProductSaleType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ChildPicAndContentPO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.CommentPO;
import com.guolaiwan.bussiness.admin.po.GroupBuyPO;
import com.guolaiwan.bussiness.admin.po.LanPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.PicproRelationPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;
import com.guolaiwan.bussiness.admin.po.ProductLayerPo;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.ProductPackPO;
import com.guolaiwan.bussiness.admin.po.ProductRegionPo;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.distribute.dao.DistributePolicyDao;
import com.guolaiwan.bussiness.distribute.dao.DistributeProductDao;
import com.guolaiwan.bussiness.distribute.dao.DistributorDao;
import com.guolaiwan.bussiness.distribute.dao.RegionDao;
import com.guolaiwan.bussiness.distribute.po.DistributePolicy;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;
import com.guolaiwan.bussiness.distribute.po.RegionPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private ModularClassDAO conn_modularClass;

	@Autowired
	private CityInfoDAO conn_cityInfo;

	@Autowired
	private ChildProductDAO conn_childProduct;

	@Autowired
	private PicproRelationDAO conn_picproRelation;

	@Autowired
	private PictureDAO conn_picture;

	@Autowired
	private BewriteDAO conn_bewrite;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private ProductPackDAO conn_productPack;

	@Autowired
	private DistributeProductDao conn_distributeProduct;

	@Autowired
	private DistributePolicyDao conn_distributePolicy;
	
	@Autowired
	private ProductComboDAO conn_productcombo;
	@Autowired
	private GroupBuyDAO conn_groupbuydao;
	
	
	// 列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		// 公司下的商品的数量
		if (getLoginInfo() != null) {
			long comId = getLoginInfo().getComId();
			int count = conn_product.countByCom(comId);
			// 获取所有模块和子模块
			List<ModularPO> modulars = conn_modular.findAll();
			List<ModularClassPO> Classes = conn_modularClass.findAll();
			strMap.put("count", count);
			strMap.put("modulars", modulars);
			strMap.put("Classes", Classes);
		} else {
			long marchantId = getMerchantInfo().getMerchantId();
			int count = conn_product.countByMerchantId(marchantId);
			List<ModularPO> modulars = conn_modular.findAll();
			List<ModularClassPO> Classes = conn_modularClass.findAll();
			strMap.put("count", count);
			strMap.put("modulars", modulars);
			strMap.put("Classes", Classes);
		}

		ModelAndView mv = new ModelAndView("admin/product/list", strMap);
		return mv;
	}

	// 增加页面
	@RequestMapping("/addv")
	public ModelAndView addProduct() {
		ModelAndView mv = new ModelAndView("admin/product/add");
		return mv;
	}

	// 信息页面
	@RequestMapping(value = "/infov", method = RequestMethod.GET)
	public ModelAndView productInfo(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		ProductPO productp = conn_product.get(uuid);
		ProductVO productV = new ProductVO().set(productp);
		//张羽  将购买商品的最低购买数量带到页面
		productV.setProductRestrictNumber(productp.getProductRestrictNumber());
		
		//预约购票新增
		productV.setNumTicketsByDay(productp.getNumTicketsByDay());
		productV.setNumTicketsByDayType(productp.getNumTicketsByDayType());
//		productV.setNumByOnePerson(productp.getNumByOnePerson());
		
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		String beginDate = sdf.format(productp.getProductBeginDate());
		String ectiveDate = sdf.format(productp.getProductEctivedate());
		String endDate = sdf.format(productp.getProductEnddate());
		if(productV.getProductCommissionCode()==1){
			productV.setProductCommissionPrice(productV.getProductCommissionPrice()/100);
		}
		ModelAndView mv = new ModelAndView("admin/product/modify");
		if(conn_groupbuydao.findByProductId(productV.getId())!=null){
			GroupBuyPO groupBuyPO = conn_groupbuydao.findByProductId(productV.getId());
			strMap.put("groupbuypo", groupBuyPO);
		}
		strMap.put("product", productV);
		strMap.put("beginDate", beginDate);
		strMap.put("ectiveDate", ectiveDate);
		strMap.put("endDate", endDate);
		String[] pic = productp.getProductMorePic().split(",");
		for (int i = 1; i <= pic.length; i++) {
			String imgstr = "img" + i;
			strMap.put(imgstr, pic[i - 1]);
		}
		strMap.put("sysConfig", sysConfig);

		mv.addAllObjects(strMap);
		return mv;
	}

	// 获取的数据
	@ResponseBody
	@RequestMapping(value = "/proList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getProList(int page, int limit) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (getLoginInfo() != null) {
			long comId = getLoginInfo().getComId();
			int count = conn_product.countByCom(comId);
			List<ProductPO> listpo = conn_product.findByCom(comId, page, limit);
			List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);

			map.put("data", listvo);
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", count);
		} else {
			long merchantId = getMerchantInfo().getMerchantId();
			int count = conn_product.countByMerchantId(merchantId);
			List<ProductPO> listpo = conn_product.findByMerchant(merchantId, page, limit);
			List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);

			map.put("data", listvo);
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", count);
		}

		return map;
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		ProductPO product = new ProductPO();
		// 名称、副标题、板块、分类、时间
		String modularCode = request.getParameter("modularCode");
		String Integra = request.getParameter("Integra"); //积分商品
		String site = request.getParameter("site"); //积分商品
		String modularName = request.getParameter("modularName");
		String productName = request.getParameter("productName");
		String modularClassId = request.getParameter("modularClassId");
		String modularClass = request.getParameter("modularClass");
		String productSubtitle = request.getParameter("productSubtitle");
		String productBeginDate = request.getParameter("productBeginDate");
		String productEnddate = request.getParameter("productEnddate");
		String productIntroduce = request.getParameter("productIntroduce");
		String productEctivedate = request.getParameter("productEctivedate");
		String psType = request.getParameter("psType");
		String goldNum = request.getParameter("goldNum");
		String productDayCount=request.getParameter("productDayCount");
		String datesOn=request.getParameter("datesOn");
		String costMessage=request.getParameter("costMessage");
		String remarks=request.getParameter("remarks");
		String notes=request.getParameter("notes");
		String traffic=request.getParameter("traffic");
		String regionFirst=request.getParameter("regionFirst");
		String regionSecond=request.getParameter("regionSecond");
		String regionThird=request.getParameter("regionThird");
		String videourl=request.getParameter("videourl");
		//分销商品ID
		String distributeId=request.getParameter("distributeId");
		//张羽 新增 5/1 商品购买最低数量限制
		String productRestrictNumber=request.getParameter("productRestrictNumber");
		
		//预约购票新增
		String numTicketsByDay=request.getParameter("numTicketsByDay");
		String numTicketsByDayType=request.getParameter("numTicketsByDayType");
//		String numByOnePerson=request.getParameter("numByOnePerson");		
		
		int isgroup=0;
		//是不是拼团的商品
		if(request.getParameter("group")!=null&&request.getParameter("group")!=""){
			isgroup=Integer.parseInt(request.getParameter("group"));
		}
		product.setIsgroup(isgroup);
		
		if(distributeId!=null&&distributeId!=""){
			product.setDistributeId(distributeId);	
		}
		
		product.setProductRestrictNumber(Integer.parseInt(productRestrictNumber));
		
		//预约购票新增
		product.setNumTicketsByDay(Integer.parseInt(numTicketsByDay));
		product.setNumTicketsByDayType(Integer.parseInt(numTicketsByDayType));
//		product.setNumByOnePerson(Integer.parseInt(numByOnePerson));
		
		product.setRegionId(Long.parseLong(regionFirst));
		
		
		if(traffic!=null&&!traffic.isEmpty()){
			product.setTraffic(traffic);	
		}
		if(productDayCount!=null&&!productDayCount.isEmpty()){
			product.setProductDayCount(Integer.parseInt(productDayCount));	
		}
		product.setDatesOn(datesOn);
		product.setIntegralGoods(Integer.parseInt(Integra)); //添加积分商品
		product.setSite(site);//添加到店领取地址
		product.setCostMessage(costMessage);
		product.setNotes(notes);
		product.setRemarks(remarks);
		// 金币
		if (psType != null && psType != "") {
			product.setPsType(ProductSaleType.fromString(psType));
			long goldNumL = 0l;
			if (goldNum != null && goldNum != "") {
				goldNumL = Long.parseLong(goldNum);
			}
			product.setGoldNum(goldNumL);
		} else {
			product.setPsType(ProductSaleType.fromString("CASH"));
		}

		long productMerchantID = Long.parseLong(request.getParameter("productMerchantID"));
		String productMerchantName = request.getParameter("productMerchantName");
		String merMName = request.getParameter("merMName");
		if (!(merMName.length() == 0 || merMName == null)) {
			product.setMerMName(merMName);
		}
		String merMId = request.getParameter("merMId");
		if (!(merMId.length() == 0 || merMId == null)) {
			product.setMerMId(Long.parseLong(merMId));
		}
		// 提成
		int productCommissionCode = Integer.parseInt(request.getParameter("productCommissionCode"));
		long productCommissionPrice = (long) (Double.parseDouble(request.getParameter("productCommissionPrice"))*100);
		// 浏览量、排序、销量、积分
		if (!(request.getParameter("productShowNum") == "" || request.getParameter("productShowNum") == null)) {
			long productShowNum = Long.parseLong(request.getParameter("productShowNum"));
			product.setProductShowNum(productShowNum);
		}
		;
		if (!(request.getParameter("productSort") == "" || request.getParameter("productSort") == null)) {
			long productSort = Long.parseLong(request.getParameter("productSort"));
			product.setProductSort(productSort);
			;
		}
		;
		if (!(request.getParameter("productSaleNum") == "" || request.getParameter("productSaleNum") == null)) {
			long productSaleNum = Long.parseLong(request.getParameter("productSaleNum"));
			product.setProductSaleNum(productSaleNum);
			;
		}
		;
		if (!(request.getParameter("productntegral") == "" || request.getParameter("productntegral") == null)) {
			long productntegral = Long.parseLong(request.getParameter("productntegral"));
			product.setProductntegral(productntegral);
		}
		;
		if (!(request.getParameter("sent") == "" || request.getParameter("sent") == null)) {
			long sent = Long.parseLong(request.getParameter("sent"));
			product.setSent(sent);
			product.setProductType(1);// 需要邮寄
		} else {
			product.setProductType(0);// 不需要邮寄
		}
		;
		// 原价、现价、库存
		String oldprice=request.getParameter("productOldPrice");
		String price=request.getParameter("productPrice");
		BigDecimal bdo = new BigDecimal(oldprice);
		BigDecimal bd = new BigDecimal(price);
		BigDecimal bd1 = new BigDecimal(100);
		BigDecimal productOldPrice = bdo.multiply(bd1);
		BigDecimal productPrice = bd.multiply(bd1);
		long productStock = Long.parseLong(request.getParameter("productStock"));

		
		int ifFace = 1;
		if (request.getParameter("ifFace") == null)
			ifFace = 0;
		//张羽 4/28 添加退款限制
		int productIsRefund=1;
		if(request.getParameter("productIsRefund")==null)
			productIsRefund=0;
		// 显示、推荐
		int productIsShow = 1;
		if (request.getParameter("productIsShow") == null)
			productIsShow = 0;

		int productIndexRecommend = 1;
		if (request.getParameter("like[productIndexRecommend]") == null)
			productIndexRecommend = 0;

		int productListRecommend = 1;
		if (request.getParameter("like[productListRecommend]") == null)
			productListRecommend = 0;
		// 限购
		int productLimitType;
		if (request.getParameter("productLimitType") == null) {
			productLimitType = 0;
		} else {
			productLimitType = 1;
			long productLimitNum;
			if (request.getParameter("productLimitNum") == null) {
				productLimitNum = 0;
			} else {
				productLimitNum = Integer.parseInt(request.getParameter("productLimitNum"));
			}
			product.setProductLimitNum(productLimitNum);
		}
		product.setProductLimitType(productLimitType);
		// 图片
		String shopMpic = request.getParameter("shopMpic");
		String shopPic = request.getParameter("shopPic");

		// 审核状态
		String productAuditstates = request.getParameter("productAuditstates");

		// 前端显示图片（路径）
		product.setProductShowPic(shopPic);
		// 产品多图显示（路径）,
		product.setProductMorePic(shopMpic);
		// 传入参数
		MerchantPO merchant = conn_merchant.get(productMerchantID);
		String productMerchantJson = JSONObject.toJSONString(merchant);
		product.setProductMerchantJson(productMerchantJson);
		;
		// 商户ID
		product.setProductMerchantID(productMerchantID);
		// 商户名称
		product.setProductMerchantName(productMerchantName);
		// 提成模式（0：佣金1：比例）productCommissionCode
		product.setProductCommissionCode(productCommissionCode);
		// 提成金额（佣金：元。比例百分比：10，20。。） productCommissionPrice
		product.setProductCommissionPrice(productCommissionPrice);
		// 库存
		product.setProductStock(productStock);
		// 商品名称
		product.setProductName(productName);
		// 板块
		product.setProductModularCode(modularCode);
		
		//张羽 4/28 新增退款限制
		product.setProductIsRefund(productIsRefund);
		product.setProductModularCodeName(modularName);
		product.setProductClassCode(modularClassId);
		product.setProductClassName(modularClass);
		product.setProductSubtitle(productSubtitle);
		product.setProductBeginDate(sdf.parse(productBeginDate));
		product.setProductEnddate(sdf.parse(productEnddate));
		product.setProductEctivedate(sdf.parse(productEctivedate));
		product.setProductIntroduce(productIntroduce);
		product.setProductIsShow(productIsShow);
		product.setProductIndexRecommend(productIndexRecommend);
		product.setProductListRecommend(productListRecommend);
		product.setProductOldPrice(productOldPrice.longValue());
		product.setProductPrice(productPrice.longValue());
		product.setUpdateTime(d);
		product.setIfFace(ifFace);

		// 审核
		product.setProductAuditstatus(ShopAuditStateType.fromName(productAuditstates));

		// session
		if (getLoginInfo() != null) {
			product.setProductCityCode(getLoginInfo().getCityCode());
			product.setProductCityName(getLoginInfo().getCityName());
			product.setComId(getLoginInfo().getComId());
			product.setComName(getLoginInfo().getComName());
		} else {

		}
		product.setVideourl(videourl);
		
		
		String x=request.getParameter("x");

		String y=request.getParameter("y");
		
		String voiceUrl=request.getParameter("voice");
		
		String mapUrl=request.getParameter("mapUrl");
		
		int isInner=Integer.parseInt(request.getParameter("isInner"));//1室内，0室外
		
		product.setX(x);
		product.setY(y);
		product.setVoiceUrl(voiceUrl);
		product.setMapUrl(mapUrl);
		product.setIsInner(isInner);
		
		conn_product.save(product);
		//如果是的话
		if(isgroup==1){
			long grouptime=Long.parseLong(request.getParameter("grouptime"));
			int groupnum=Integer.parseInt(request.getParameter("groupnum"));
			long groupprice=Long.parseLong(request.getParameter("groupprice"));
			long productId=product.getId();
			GroupBuyPO group=new GroupBuyPO();
			group.setGrouptime(grouptime);
			group.setGroupnum(groupnum);
			//存入数据库转成单位为 分
			group.setGroupprice(groupprice*100);
			group.setProductid(productId);
			conn_groupbuydao.save(group);
		}
		
		
		
		

		return "success";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/editsort.do", method = RequestMethod.POST)
	public String editsort(HttpServletRequest request) throws Exception {
		long id = Long.parseLong(request.getParameter("id"));
		ProductPO product = conn_product.get(id);
		String field = request.getParameter("field");
		String value = request.getParameter("value");
		product.setProductSort(Long.parseLong(value));
		conn_product.save(product);
		return "success";
		
	}
	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public String modify(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ProductPO product = conn_product.get(request.getParameter("uuid"));
		// 名称、副标题、
		String productName = request.getParameter("productName");
		String productSubtitle = request.getParameter("productSubtitle");
		// 板块、分类、
		String modularCode = request.getParameter("modularCode");
		String modularName = request.getParameter("modularName");
		String modularClassId = request.getParameter("modularClassId");
		String modularClass = request.getParameter("modularClass");
		String Integra = request.getParameter("Integra"); //积分商品
		String site = request.getParameter("site"); //积分商品
		String videourl = request.getParameter("videourl"); //积分商品
		// 商家
		long productMerchantID = Long.parseLong(request.getParameter("productMerchantID"));
		String productMerchantName = request.getParameter("productMerchantName");
		String merMName = request.getParameter("merMName");
		String distributeId=request.getParameter("distributeId");
		if (distributeId!=""&&distributeId!=null) {
			product.setDistributeId(distributeId);
		}
		if (!(merMName.length() == 0 || merMName == null)) {
			product.setMerMName(merMName);
		}
		String merMId = request.getParameter("merMId");
		if (!(merMId.length() == 0 || merMId == null)) {
			product.setMerMId(Long.parseLong(merMId));
		}
		int isgroup=0;
		//是不是拼团的商品
		if(request.getParameter("group")!=null&&request.getParameter("group")!=""){
			isgroup=Integer.parseInt(request.getParameter("group"));
		}
		// 原价、现价、库存
		BigDecimal _OldPrice = new BigDecimal(request.getParameter("productOldPrice"));
		BigDecimal _nowPrice = new BigDecimal(request.getParameter("productPrice"));
		BigDecimal  tenNumber = new BigDecimal(100);
		BigDecimal orderPrice = _OldPrice.multiply(tenNumber);
		BigDecimal nowPrice = _nowPrice.multiply(tenNumber);
		
		long productOldPrice = orderPrice.longValue();
		long productPrice = nowPrice.longValue();
		

		long productStock = Long.parseLong(request.getParameter("productStock"));
		
		//最低购买数量 张羽 5/1
		int productRestrictNumber=Integer.parseInt(request.getParameter("productRestrictNumber"));
		product.setProductRestrictNumber(productRestrictNumber);
		
		// 时间
		String productBeginDate = request.getParameter("productBeginDate");
		String productEnddate = request.getParameter("productEnddate");
		String productEctivedate = request.getParameter("productEctivedate");

		// 图片
		String shopMpic = request.getParameter("shopMpic");
		String shopPic = request.getParameter("shopPic");
		// 提成
		int productCommissionCode = Integer.parseInt(request.getParameter("productCommissionCode"));
		long productCommissionPrice = (long) (Double.parseDouble(request.getParameter("productCommissionPrice"))*100);

		int ifFace = 1;
		if (request.getParameter("ifFace") == null)
			ifFace = 0;
		int productIsRefund=1;
		if(request.getParameter("productIsRefund")==null)
			productIsRefund=0;
		product.setProductIsRefund(productIsRefund);
		// 显示、推荐
		int productIsShow = 1;
		if (request.getParameter("productIsShow") == null)
			productIsShow = 0;
		int productIndexRecommend = 1;
		if (request.getParameter("like[productIndexRecommend]") == null)
			productIndexRecommend = 0;
		int productListRecommend = 1;
		if (request.getParameter("like[productListRecommend]") == null)
			productListRecommend = 0;

		// 限购
		int productLimitType;
		if (request.getParameter("productLimitType") == null) {
			productLimitType = 0;
		} else {
			productLimitType = 1;
			long productLimitNum;
			if (request.getParameter("productLimitNum") == null) {
				productLimitNum = 0;
			} else {
				productLimitNum = Integer.parseInt(request.getParameter("productLimitNum"));
			}
			product.setProductLimitNum(productLimitNum);
		}
		product.setProductLimitType(productLimitType);

		//预约购票新增
		int numTicketsByDayType;
		if (request.getParameter("numTicketsByDayType") == null) {
			numTicketsByDayType = 0;
		} else {
			numTicketsByDayType = 1;
			int numTicketsByDay = 0;
			if (request.getParameter("numTicketsByDay") == null) {
				numTicketsByDay = 0;
			} else {
				numTicketsByDay = Integer.parseInt(request.getParameter("numTicketsByDay"));
			}
			product.setNumTicketsByDay(numTicketsByDay);
		}
		product.setNumTicketsByDayType(numTicketsByDayType);
//		String numByOnePerson=request.getParameter("numByOnePerson");
//		product.setNumByOnePerson(Integer.parseInt(numByOnePerson));
		
		// 浏览量、排序、销量、积分
		if (!(request.getParameter("productShowNum") == "" || request.getParameter("productShowNum") == null)) {
			long productShowNum = Long.parseLong(request.getParameter("productShowNum"));
			product.setProductShowNum(productShowNum);
		}
		;
		if (!(request.getParameter("productSort") == "" || request.getParameter("productSort") == null)) {
			long productSort = Long.parseLong(request.getParameter("productSort"));
			product.setProductSort(productSort);
			;
		}
		;
		if (!(request.getParameter("productSaleNum") == "" || request.getParameter("productSaleNum") == null)) {
			long productSaleNum = Long.parseLong(request.getParameter("productSaleNum"));
			product.setProductSaleNum(productSaleNum);
			;
		}
		;
		if (!(request.getParameter("productntegral") == "" || request.getParameter("productntegral") == null)) {
			long productntegral = Long.parseLong(request.getParameter("productntegral"));
			product.setProductntegral(productntegral);
		}
		;

		// 介绍
		String productIntroduce = request.getParameter("productIntroduce");

		// 传入参数
		// 商户ID
		MerchantPO merchant = conn_merchant.get(productMerchantID);
		String productMerchantJson = JSONObject.toJSONString(merchant);

		// 审核状态
		String productAuditstates = request.getParameter("productAuditstates");

		//积分商品
		product.setIntegralGoods(Integer.parseInt(Integra));
		product.setSite(site);//添加到店领取地址
		// 商品、副标题
		product.setProductName(productName);
		product.setProductSubtitle(productSubtitle);

		// 板块
		product.setProductModularCode(modularCode);
		product.setProductModularCodeName(modularName);
		product.setProductClassCode(modularClassId);
		product.setProductClassName(modularClass);
 
		// 商家
		product.setProductMerchantID(productMerchantID);
		product.setProductMerchantName(productMerchantName);
		product.setProductMerchantJson(productMerchantJson);

		// 原价、现价
		product.setProductOldPrice(productOldPrice);
		product.setProductPrice(productPrice);
		product.setProductStock(productStock);

		// 时间
		product.setProductBeginDate(sdf.parse(productBeginDate));
		product.setProductEnddate(sdf.parse(productEnddate));
		product.setProductEctivedate(sdf.parse(productEctivedate));

		// 图片
		product.setProductShowPic(shopPic);
		product.setProductMorePic(shopMpic);

		// 提成
		product.setProductCommissionCode(productCommissionCode);
		if(productCommissionCode==0){
			product.setProductCommissionPrice(productCommissionPrice);
		}else{
			product.setProductCommissionPrice(productCommissionPrice);
		}
		

		// 显示推荐
		product.setProductIsShow(productIsShow);
		product.setProductIndexRecommend(productIndexRecommend);
		product.setProductListRecommend(productListRecommend);

		// 介绍
		product.setProductIntroduce(productIntroduce);

		// 审核
		product.setProductAuditstatus(ShopAuditStateType.fromString(productAuditstates));

		
		String productDayCount=request.getParameter("productDayCount");
		String datesOn=request.getParameter("datesOn");
		String costMessage=request.getParameter("costMessage");
		String remarks=request.getParameter("remarks");
		String notes=request.getParameter("notes");
		String traffic=request.getParameter("traffic");
		if(traffic!=null&&!traffic.isEmpty()){
			product.setTraffic(traffic);	
		}
		if(productDayCount!=null&&!productDayCount.isEmpty()){
			product.setProductDayCount(Integer.parseInt(productDayCount));	
		}
		if(productDayCount!=null&&!productDayCount.isEmpty()){
			product.setProductDayCount(Integer.parseInt(productDayCount));	
		}
		product.setDatesOn(datesOn);
		product.setCostMessage(costMessage);
		product.setNotes(notes);
		product.setRemarks(remarks);
		product.setIfFace(ifFace);
		product.setIsgroup(isgroup);
		product.setVideourl(videourl);
		
		String x=request.getParameter("x");

		String y=request.getParameter("y");
		
		String voiceUrl=request.getParameter("voice");
		
		String mapUrl=request.getParameter("mapUrl");
		
		int isInner=Integer.parseInt(request.getParameter("isInner"));//1室内，0室外
		
		product.setX(x);
		product.setY(y);
		product.setVoiceUrl(voiceUrl);
		product.setMapUrl(mapUrl);
		product.setIsInner(isInner);
		
		conn_product.saveOrUpdate(product);
		
		if(isgroup==1){
			//如果是的话
			int groupnum=Integer.parseInt(request.getParameter("groupnum"));
			long groupprice=Long.parseLong(request.getParameter("groupprice"));
			long grouptime=Long.parseLong(request.getParameter("grouptime"));
			long productId=product.getId();
			if(conn_groupbuydao.findByProductId(productId)!=null){
				GroupBuyPO group=conn_groupbuydao.findByProductId(productId);
				group.setGroupnum(groupnum);
				group.setGroupprice(groupprice*100);
				group.setProductid(productId);
				group.setGrouptime(grouptime);
				conn_groupbuydao.saveOrUpdate(group);
			}else{
				GroupBuyPO group=new GroupBuyPO();
				group.setGroupnum(groupnum);
				group.setGroupprice(groupprice*100);
				group.setProductid(productId);
				group.setGrouptime(grouptime);
				conn_groupbuydao.save(group);
			}
		}
		
		return "success";
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/distribute.do/{id}", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, @PathVariable long id) throws Exception {
		ProductPO productPO = conn_product.get(id);
		DistributeProduct distributeProduct = new DistributeProduct();
		distributeProduct.setDistributorId(productPO.getProductMerchantID());
		distributeProduct.setProduct(productPO);
		distributeProduct.setProleft(10000);
		distributeProduct.setPrice(productPO.getProductPrice());
		distributeProduct.setRegionId(0l);
		distributeProduct.setOnline(1);
		conn_distributeProduct.save(distributeProduct);
		return "success";
	}

	// 待审核列表
	@RequestMapping("/checklist")
	public ModelAndView auditProduct() {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long comId = getLoginInfo().getComId();
		int count = conn_product.countByComD(comId);

		List<CityInfoPO> cityList = conn_cityInfo.findAll();
		List<ModularPO> modulars = conn_modular.findAll();
		List<ModularClassPO> Classes = conn_modularClass.findAll();
		strMap.put("Classes", Classes);
		strMap.put("modulars", modulars);
		strMap.put("acount", count);
		strMap.put("cityList", cityList);
		ModelAndView mv = new ModelAndView("admin/product/checklist");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 获取待审核的全部数据
	@ResponseBody
	@RequestMapping(value = "/checklist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> auditList(int page, int limit) throws Exception {
		long comId = getLoginInfo().getComId();
		int count = conn_product.countByComD(comId);
		List<ProductPO> listpo = conn_product.findByComD(comId, page, limit);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("count", count);
		map.put("msg", "");
		map.put("code", "");
		return map;
	}

	// 审核通过列表
	@RequestMapping("/passlist")
	public ModelAndView passList() {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long comId = getLoginInfo().getComId();
		int count = conn_product.countByComT(comId);

		List<CityInfoPO> cityList = conn_cityInfo.findAll();
		List<ModularPO> modulars = conn_modular.findAll();
		List<ModularClassPO> Classes = conn_modularClass.findAll();
		strMap.put("Classes", Classes);
		strMap.put("modulars", modulars);
		strMap.put("pcount", count);
		strMap.put("cityList", cityList);
		ModelAndView mv = new ModelAndView("admin/product/passlist");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 获取审核通过的全部数据
	@ResponseBody
	@RequestMapping(value = "/passlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getPassList(int pagecurr) throws Exception {
		long comId = getLoginInfo().getComId();
		List<ProductPO> listpo = conn_product.findByComT(comId, pagecurr, 10);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("passlist", listvo);
		return map;
	}

	// 审核页面
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView check(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		ProductPO product = conn_product.get(uuid);
		ProductVO productV = new ProductVO().set(product);
		productV.setProductRestrictNumber(product.getProductRestrictNumber());
		
		//预约购票新增
		productV.setNumTicketsByDay(product.getNumTicketsByDay());
		productV.setNumTicketsByDayType(product.getNumTicketsByDayType());
//		productV.setNumByOnePerson(product.getNumByOnePerson());
				
		String beginDate = sdf.format(product.getProductBeginDate());
		String ectiveDate = sdf.format(product.getProductEctivedate());
		String endDate = sdf.format(product.getProductEnddate());
		if(conn_groupbuydao.findByProductId(productV.getId())!=null){
			GroupBuyPO groupBuyPO = conn_groupbuydao.findByProductId(productV.getId());
			strMap.put("groupbuypo", groupBuyPO);
		}
		strMap.put("product", productV);
		strMap.put("beginDate", beginDate);
		strMap.put("ectiveDate", ectiveDate);
		strMap.put("endDate", endDate);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/product/check");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 审核按钮
	@ResponseBody
	@RequestMapping(value = "/audit.do", method = RequestMethod.POST)
	public Map<String, Object> audit(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ProductPO prodcut = conn_product.get(request.getParameter("uuid"));
		if (prodcut == null) {
			strMap.put("code", "1");
			strMap.put("message", "没有该产品");
			return strMap;
		}
		String pass = request.getParameter("pass");
		String timeStr = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date())
				+ getLoginInfo().getAdminName();
		if (pass.equals("yes")) {
			prodcut.setProductAuditstatus(ShopAuditStateType.T);
			prodcut.setProductAuditAdvice(timeStr + "通过");
			strMap.put("message", prodcut.getProductName() + "审核通过！");
		}
		if (pass.equals("no")) {
			prodcut.setProductAuditstatus(ShopAuditStateType.N);
			prodcut.setProductAuditAdvice(timeStr + "不通过");
			strMap.put("message", "操作成功！");
		}
		conn_product.saveOrUpdate(prodcut);
		strMap.put("code", "0");
		return strMap;
	}

	// 详情页面
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		ProductPO product = conn_product.get(uuid);
		ProductVO productV = new ProductVO().set(product);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		String beginDate = sdf.format(product.getProductBeginDate());
		String ectiveDate = sdf.format(product.getProductEctivedate());
		String endDate = sdf.format(product.getProductEnddate());
		productV.setProductRestrictNumber(product.getProductRestrictNumber());
		
		//预约购票新增
		productV.setNumTicketsByDay(product.getNumTicketsByDay());
		productV.setNumTicketsByDayType(product.getNumTicketsByDayType());
//		productV.setNumByOnePerson(product.getNumByOnePerson());
				
		if(conn_groupbuydao.findByProductId(productV.getId())!=null){
			GroupBuyPO groupBuyPO = conn_groupbuydao.findByProductId(productV.getId());
			strMap.put("groupbuypo", groupBuyPO);
		}
		
		strMap.put("product", productV);
		strMap.put("beginDate", beginDate);
		strMap.put("ectiveDate", ectiveDate);// sysConfig
		strMap.put("endDate", endDate);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/product/info");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 详情页面
	@RequestMapping(value = "/infoId", method = RequestMethod.GET)
	public ModelAndView infoId(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> strMap = new HashMap<String, Object>();
		long id = Long.parseLong(request.getParameter("id"));
		ProductPO product = conn_product.get(id);
		ProductVO productV = new ProductVO().set(product);
		productV.setProductRestrictNumber(product.getProductRestrictNumber());
		
		//预约购票新增
		productV.setNumTicketsByDay(product.getNumTicketsByDay());
		productV.setNumTicketsByDayType(product.getNumTicketsByDayType());
//		productV.setNumByOnePerson(product.getNumByOnePerson());
		
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		String beginDate = sdf.format(product.getProductBeginDate());
		String ectiveDate = sdf.format(product.getProductEctivedate());
		String endDate = sdf.format(product.getProductEnddate());
		strMap.put("product", productV);
		strMap.put("beginDate", beginDate);
		strMap.put("ectiveDate", ectiveDate);// sysConfig
		strMap.put("endDate", endDate);
		strMap.put("sysConfig", sysConfig);
		ModelAndView mv = new ModelAndView("admin/product/info");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 查询
	@ResponseBody
	@RequestMapping(value = "/getProductBy.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> countByCityp(HttpServletRequest request) throws Exception {
		long comId = getLoginInfo().getComId();
		Map<String, Object> newmap = new HashMap<String, Object>();
		String productAuditstates = request.getParameter("productAuditstates");
		String productName = request.getParameter("productName");
		String shopName = request.getParameter("shopName");
		String modularCode = request.getParameter("modularCode");
		String classCode = request.getParameter("classCode");

		newmap.put("comId", comId);
		if (productName != null && productName.length() > 0) {
			newmap.put("productName", productName);
		}
		if (shopName != null && shopName.length() > 0) {
			newmap.put("productMerchantName", shopName);
		}
		if (modularCode != null && modularCode.length() > 0) {
			newmap.put("productModularCode", modularCode);
		}
		if (classCode != null && classCode.length() > 0) {
			newmap.put("productClassCode", classCode);
		}
		if (productAuditstates != null && productAuditstates != "") {
			newmap.put("productAuditstatus", ShopAuditStateType.fromString(productAuditstates));
		}
		int page =1;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		 
		int limit = 10;
		if(request.getParameter("limit")!=null){
			limit=Integer.parseInt(request.getParameter("limit"));
		}
		int count = conn_product.getCountByPageE(newmap);
		List<ProductPO> listpo = conn_product.getListByPageE(newmap, page, limit);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	// 删除产品
	@ResponseBody
	@RequestMapping(value = "prodel.do", method = RequestMethod.POST)
	public String proDel(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		conn_product.deleteByUuid(uuid);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "disprodel.do", method = RequestMethod.POST)
	public String disproDel(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
	    DistributeProduct distributeProduct=conn_distributeProduct.get(uuid);
	    
		return "success";
	}

	// 子产品页面
	@RequestMapping(value = "/child/list", method = RequestMethod.GET)
	public ModelAndView childList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long productID = Long.parseLong(request.getParameter("uuid"));
		int count = conn_childProduct.countByField("productID", productID);
		strMap.put("productID", productID);
		strMap.put("count", count);
		ModelAndView mv = new ModelAndView("admin/product/childlist", strMap);
		return mv;
	}

	// 子产品页面
	@ResponseBody
	@RequestMapping(value = "/child/childList.do", method = RequestMethod.POST)
	public Map<String, Object> getCList(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		long productID = Long.parseLong(request.getParameter("productID"));
		int currPage=Integer.parseInt(request.getParameter("pagecurr"));
		List<ChildProductPO> childListP = conn_childProduct.findByField("productID", productID, currPage, 10);
		
		List<ChildProductVO> childListV = ChildProductVO.getConverter(ChildProductVO.class).convert(childListP,
				ChildProductVO.class);

		strMap.put("childList", childListV);
		return strMap;
	}

	@Autowired
	private LanDAO conn_lan;
    @Autowired
    private ProductLayerDAO conn_layer;
    @Autowired
    private ProductRegionDAO conn_pregion;
	// 添加子产品页面
	@RequestMapping(value = "/child/addv", method = RequestMethod.GET)
	public ModelAndView addChild(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<LanPO> lanList = conn_lan.findAll();
		strMap.put("lanList", lanList);

		List<ProductLayerPo> layerPos=conn_layer.findAll();
		strMap.put("layerList", layerPos);
		
		
		
		long productID = Long.parseLong(request.getParameter("productID"));
		
		List<ProductRegionPo> regionPos=conn_pregion.findByField("productId", productID);
		strMap.put("regionList", regionPos);
		
		strMap.put("productID", productID);
		ModelAndView mv = new ModelAndView("admin/product/addchild", strMap);
		return mv;
	}

	@Autowired
	private ChildPicAndContentDAO conn_childPicAndContent;

	// 添加子产品
	@ResponseBody
	@RequestMapping(value = "/child/add.do", method = RequestMethod.POST)
	public String addchilddo(HttpServletRequest request) throws Exception {
		String childName = request.getParameter("childName");
		String voice = request.getParameter("voice");
		String childLongitude = request.getParameter("childLongitude");
		String childLatitude = request.getParameter("childLatitude");
		String content = request.getParameter("desc");
		String lanId = request.getParameter("lan");
		String scale = request.getParameter("scale");
        String linkedPoint=request.getParameter("linkedPoint");
        String wxChildLongitude = request.getParameter("wxChildLongitude");
		String wxChildLatitude = request.getParameter("wxChildLatitude");
		long productID = Long.parseLong(request.getParameter("productID"));
		String relevance = request.getParameter("relevance");
		String scope = request.getParameter("scope");

		ChildProductPO child = new ChildProductPO();
		
		child.setUpdateTime(new Date());
		child.setChildName(childName);
		child.setcVoice(voice);
		child.setChildLongitude(childLongitude);
		child.setChildLatitude(childLatitude);
		child.setProductID(productID);
		child.setLanId(Long.parseLong(lanId));
		child.setChildScale(scale);
		child.setRelevance(relevance);
		String imgids = request.getParameter("imgids");
		child.setChildPic(imgids);
		child.setContent(content);
		child.setScope(scope);
		child.setLinkedPoint(linkedPoint);
		child.setWxChildLongitude(wxChildLongitude);
		child.setWxChildLatitude(wxChildLatitude);
		
		String layerStr=request.getParameter("layer");
		String regionStr=request.getParameter("region");
		if(layerStr!=null){
			child.setLayer(Long.parseLong(layerStr));
		}
		if(regionStr!=null){
			child.setRegion(Long.parseLong(regionStr));
		}
		String startAngle=request.getParameter("startAngle");
		String endAngle=request.getParameter("endAngle");
		if(startAngle!=null){
			child.setStartAngle(startAngle);
		}
		if(endAngle!=null){
			child.setEndAngle(endAngle);
		}
		conn_childProduct.saveOrUpdate(child);
		return "success";
	}

	// 添加子产品页面
	@RequestMapping(value = "/child/updatev", method = RequestMethod.GET)
	public ModelAndView updateChild(HttpServletRequest request) throws Exception {
		List<LanPO> polist = conn_lan.getAllLans();
		List<LanVO> volist = LanVO.getConverter(LanVO.class).convert(polist, LanVO.class);
		Map<String, Object> strMap = new HashMap<String, Object>();
		long productID = Long.parseLong(request.getParameter("productID"));
		String uuid = request.getParameter("uuid");
		ChildProductPO child = conn_childProduct.get(uuid);// 获取子产品
		//List<PicproRelationPO> pprs = conn_picproRelation.getPprByChild(child.getId());
		//List<PicturePO> pics = conn_picture.getPicsByPprs(pprs);
		String pic1="",pic2="",pic3="";
		String[] pics=child.getChildPic().split(",");
		if(pics.length>0)pic1=pics[0];
		if(pics.length>1)pic2=pics[1];
		if(pics.length>2)pic3=pics[2];
		
		strMap.put("pic1", pic1);
		strMap.put("pic2", pic2);
		strMap.put("pic3", pic3);
		

		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		// 获取关联图片对象
		strMap.put("lanList", volist);
		strMap.put("productID", productID);
		strMap.put("child", child);
		strMap.put("sysConfig", sysConfig);
		
		List<ProductLayerPo> layerPos=conn_layer.findAll();
		strMap.put("layerList", layerPos);
		
		
		List<ProductRegionPo> regionPos=conn_pregion.findByField("productId", productID);
		strMap.put("regionList", regionPos);
		
		ModelAndView mv = new ModelAndView("admin/product/modifychild", strMap);
		return mv;
	}

	// 修改子产品
	@ResponseBody
	@RequestMapping(value = "/child/modify.do", method = RequestMethod.POST)
	public String modifyChilddo(HttpServletRequest request) throws Exception {
		String childName = request.getParameter("childName");
		String childPic = request.getParameter("childPic");
		String voice = request.getParameter("voice");
		String childLongitude = request.getParameter("childLongitude");
		String childLatitude = request.getParameter("childLatitude");
		String wxChildLongitude = request.getParameter("wxChildLongitude");
		String wxChildLatitude = request.getParameter("wxChildLatitude");
		String relevance = request.getParameter("relevance");
		String uuid = request.getParameter("uuid");
		String content = request.getParameter("desc");
		String lanId = request.getParameter("lan");
		String scale = request.getParameter("scale");
		String scope = request.getParameter("scope");
		String isTaught = request.getParameter("isTaught");
		String linkedPoint=request.getParameter("linkedPoint");
		ChildProductPO child = conn_childProduct.get(uuid);
		child.setUpdateTime(new Date());
		child.setChildName(childName);
		child.setChildPic(childPic);
		child.setcVoice(voice);
		child.setChildLongitude(childLongitude);
		child.setChildLatitude(childLatitude);
		child.setWxChildLongitude(wxChildLongitude);
		child.setWxChildLatitude(wxChildLatitude);
		child.setRelevance(relevance);
		child.setContent(content);
		child.setLanId(Long.parseLong(lanId));
		child.setChildScale(scale);
		child.setLinkedPoint(linkedPoint);
		child.setScope(scope);
		child.setIsTaught(Integer.parseInt(isTaught));
		String imgids = request.getParameter("imgids");
		child.setChildPic(imgids);
		
		
		String layerStr=request.getParameter("layer");
		String regionStr=request.getParameter("region");
		if(layerStr!=null){
			child.setLayer(Long.parseLong(layerStr));
		}
		if(regionStr!=null){
			child.setRegion(Long.parseLong(regionStr));
		}
		String startAngle=request.getParameter("startAngle");
		String endAngle=request.getParameter("endAngle");
		if(startAngle!=null){
			child.setStartAngle(startAngle);
		}
		if(endAngle!=null){
			child.setEndAngle(endAngle);
		}
		
		conn_childProduct.saveOrUpdate(child);
		return "success";
	}

	// 删除子产品表的数据
	@ResponseBody
	@RequestMapping(value = "/child/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {

		String uuid = request.getParameter("uuid");
		ChildProductPO child = conn_childProduct.get(uuid);
		// 删 子产品表
		conn_childProduct.delete(child);
		return "success";
	}

	// 套餐页面
	@RequestMapping(value = "/package", method = RequestMethod.GET)
	public ModelAndView proPackage(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long productID = Long.parseLong(request.getParameter("uuid"));
		int count = conn_product.get(productID).getPropacks().size();
		strMap.put("productID", productID);
		strMap.put("count", count);

		ModelAndView mv = new ModelAndView("admin/product/ppack/list", strMap);
		return mv;
	}

	// 套餐列表
	@ResponseBody
	@RequestMapping(value = "/ppackList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> ppackList(int pagecurr, long productID) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<ProductPackPO> ppacks = conn_product.get(productID).getPropacks();
		List<ProductPackVO> ppackvs = ProductPackVO.getConverter(ProductPackVO.class).convert(ppacks,
				ProductPackVO.class);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		for (ProductPackVO ppv : ppackvs) {
			ppv.setPic(sysConfig.getWebUrl() + ppv.getPic());
		}
		strMap.put("ppackvs", ppackvs);
		return strMap;
	}

	// 套餐添加页面
	@RequestMapping(value = "/ppack/addv", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView ppackAddv(int productID) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("productID", productID);
		ModelAndView mv = new ModelAndView("admin/product/ppack/add", strMap);
		return mv;
	}

	// 添加套餐
	@ResponseBody
	@RequestMapping(value = "/ppack/add.do", method = RequestMethod.POST)
	public String ppackAdd(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String pic = request.getParameter("pic");
		String price = request.getParameter("price");
		String oldPrice = request.getParameter("oldPrice");
		long productID = Long.parseLong(request.getParameter("productID"));

		ProductPO product = conn_product.get(productID);
		// 原价现价转成分
		long lprice = (long) (Double.parseDouble(price) * 100);
		long loldPrice = (long) (Double.parseDouble(oldPrice) * 100);

		// 套餐对象
		ProductPackPO ppack = new ProductPackPO();
		Date date = new Date();
		ppack.setName(name);
		ppack.setPic(pic);
		ppack.setPrice(lprice);
		ppack.setOldPrice(loldPrice);
		ppack.setUpdateTime(date);
		ppack.setProduct(product);
		conn_productPack.saveOrUpdate(ppack);

		return "success";
	}

	// 套餐删除
	@ResponseBody
	@RequestMapping(value = "/ppack/del.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String ppackList(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		conn_productPack.deleteByUuid(uuid);
		return "success";
	}

	// 跳转分销页
	@RequestMapping(value = "/distributeproductlist", method = RequestMethod.GET)
	public ModelAndView distributeProList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 公司下的商品的数量
		if (getLoginInfo() != null) {
			long comId = getLoginInfo().getComId();
			int count = conn_distributeProduct.countByCom(0l);
			// 获取所有模块和子模块
			List<ModularPO> modulars = conn_modular.findAll();
			List<ModularClassPO> Classes = conn_modularClass.findAll();
			map.put("count", count);
			map.put("modulars", modulars);
			map.put("Classes", Classes);
		} else {
			long merchantId = getMerchantInfo().getMerchantId();
			int count = 0;
			// 获取所有模块和子模块
			List<ModularPO> modulars = conn_modular.findAll();
			List<ModularClassPO> Classes = conn_modularClass.findAll();
			map.put("count", count);
			map.put("modulars", modulars);
			map.put("Classes", Classes);
		}

		ModelAndView mv = new ModelAndView("admin/product/distributelist", map);
		return mv;
	}

	@Autowired
	private RegionDao conn_region;
	@Autowired
	private DistributorDao conn_distributor;
	// 查询所有分销产品
	@ResponseBody
	@RequestMapping(value = "/distributeProductList.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getDisProList(int page, int limit) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (getLoginInfo() != null) {
			int count = conn_distributeProduct.countByCom(0l);
			List<DistributeProduct> listpo = conn_distributeProduct.queryOnlineByRegion(0l);
			List<DistributeProductVO> listvo = DistributeProductVO.getConverter(DistributeProductVO.class)
					.convert(listpo, DistributeProductVO.class);
			for (DistributeProductVO distributeProductVO : listvo) {
				RegionPo regionPo=conn_region.get(distributeProductVO.getRegionId());
				ProductPO productPO=conn_product.get(distributeProductVO.getDisProId());
				MerchantPO merchantPO=conn_merchant.get(distributeProductVO.getDistributorId());
				distributeProductVO.setRegionName(regionPo==null?"商户":regionPo.getName());
				distributeProductVO.setProductName(productPO.getProductName());
				distributeProductVO.setDistributorName(merchantPO.getShopName());
			}

			map.put("data", listvo);
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", count);
		}

		return map;
	}

	// 跳转分销政策页
	@RequestMapping(value = "/toDisProList.do/{id}", method = RequestMethod.GET)
	public ModelAndView distributePolicyList(HttpServletRequest request, @PathVariable Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (getLoginInfo() != null) {
			int count = conn_distributePolicy.countByDProduct(id);
			map.put("count", count);
			map.put("productId", id);

		} else {
			int count = conn_distributePolicy.countByDProduct(id);
			map.put("count", count);
			map.put("productId", id);
		}

		ModelAndView mv = new ModelAndView("admin/product/distributepolicy", map);
		return mv;
	}

	// 查询所有分销政策
	@RequestMapping(value = "/distributeProductPolicy.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> toDisProList(HttpServletRequest request, long id) throws Exception {
		int count = conn_distributePolicy.countByDProduct(id);
		List<DistributePolicy> listpo = conn_distributePolicy.queryByProduct(id);
		List<DistributePolicyVo> listvo = DistributePolicyVo.getConverter(DistributePolicyVo.class).convert(listpo,
				DistributePolicyVo.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	// 增加页面
	@RequestMapping("/addPolicy")
	public ModelAndView addProductPolicy(Long id) {
		ModelAndView mv = new ModelAndView("admin/product/addpropolicy");
		mv.addObject("productId", id);
		return mv;
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/addPolicySubmit.do", method = RequestMethod.POST)
	public String addPolicy(HttpServletRequest request, Long id) throws Exception {
		Set<DistributePolicy> sdp = new HashSet<DistributePolicy>();
		DistributePolicy dp = new DistributePolicy();
		DistributeProduct pro = conn_distributeProduct.get(id);
		int count = Integer.parseInt(request.getParameter("proCount"));// 获得政策计数
		double price = Double.valueOf(request.getParameter("proPrice"));// 获得政策价格
		dp.setCount(count);
		dp.setPrice(price);
		dp.setDistributeProduct(pro);
		dp.setUpdateTime(new Date());
		if (pro.getDistributePolicies() != null) {
			sdp = pro.getDistributePolicies();
		}
		sdp.add(dp);
		pro.setDistributePolicies(sdp);
		conn_distributeProduct.save(pro);
		return "success";
	}

	// 添加子产品页面
	@ResponseBody
	@RequestMapping(value = "/child/addx", method = RequestMethod.GET)
	public ModelAndView addChilds(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long productID = Long.parseLong(request.getParameter("productID"));
		strMap.put("productID", productID);
		ModelAndView mv = new ModelAndView("admin/product/addchilds", strMap);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/child/addx.do" , method = RequestMethod.GET)
	public String addChildsByCId(HttpServletRequest request){
		String productId = request.getParameter("productID");
		String childs = request.getParameter("childs");
		List<ChildProductPO> polist = conn_childProduct.getChildsByCIdIsCen(Long.parseLong(productId));
		polist.get(0).setChildRoad(childs);
		conn_childProduct.saveOrUpdate(polist.get(0));
		return "success";
	}
	
	@Autowired
	private CommentDAO conn_comment;
	
	@ResponseBody
	@RequestMapping(value = "/propl/{id}" , method = RequestMethod.GET)
	public ModelAndView getChildPL(@PathVariable Long id,HttpServletRequest request , HttpServletResponse response) throws Exception{
		ModelAndView mav = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("productID", id);
		map.put("code", "0");
		map.put("msg", "");
		mav = new ModelAndView("admin/product/childpropls" , map);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/propl/getChildPL.do/{proID}",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getChildPLS(@PathVariable long proID, int page,int limit)throws Exception{

		List<CommentPO> commentPOs = conn_comment.findByPro(proID,page,limit);
		List<CommentVO> commentVOs = CommentVO.getConverter(CommentVO.class).convert(commentPOs, CommentVO.class);
		int count = conn_comment.countByPro(proID);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", commentVOs);
		map.put("count", count);
		map.put("code", "0");
		map.put("msg", "");
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/propl/delcom.do/{uuid}")
	public String deleteComment(@PathVariable String uuid){

		CommentPO commentPOs = conn_comment.get(uuid);
		conn_comment.delete(commentPOs);
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/poliDel/{id}")
	public String deletePoli(@PathVariable long id){

		conn_distributePolicy.delete(id);
		
		return "success";
	}
	
	
	// 详情页面
	@ResponseBody
	@RequestMapping(value = "/propl/infocomment/{uuid}", method = RequestMethod.GET)
	public ModelAndView commentinfo(@PathVariable String uuid,HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		CommentPO commentPO = conn_comment.get(uuid);
		strMap.put("comment", commentPO);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		if (commentPO.getCommentMPic() != null) {

			String[] pic = commentPO.getCommentMPic().split(",");
			for(int i = 1; i<=pic.length;i++){
				String imgstr = "img"+i;
				strMap.put(imgstr, pic[i-1]);
			}
		}
		ModelAndView mv = new ModelAndView("admin/product/commentInfo");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	
	
	// 列表页面
	@RequestMapping(value = "/combolist", method = RequestMethod.GET)
	public ModelAndView combolist(HttpServletRequest request,long productId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		strMap.put("count", conn_productcombo.countByField("productId", productId));
		ModelAndView mv = new ModelAndView("admin/product/combo", strMap);
		mv.addObject("productId",productId);
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/comboList.do",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> comboList(long productId)throws Exception{
        List<ProductComboPO> comboPOs=conn_productcombo.findByField("productId", productId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", comboPOs);
		map.put("count", comboPOs.size());
		map.put("code", "0");
		map.put("msg", "");
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addcomv", method = RequestMethod.GET)
	public ModelAndView addcombov(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		long productID = Long.parseLong(request.getParameter("productId"));
		strMap.put("productId", productID);
		ModelAndView mv = new ModelAndView("admin/product/addcombo", strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/combodel.do")
	public String deleteCombo(long id){
		conn_productcombo.delete(id);
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "/addcombo.do" , method = RequestMethod.POST)
	public String addCombodo(HttpServletRequest request){
		String productId = request.getParameter("productID");
		String combo=request.getParameter("combo");
		String comboPrice=request.getParameter("comboPrice");
	    ProductComboPO comboPO=new ProductComboPO();
	    comboPO.setProductId(Long.parseLong(productId));
	    comboPO.setCombo(combo);
	    comboPO.setComboprice(Long.parseLong(comboPrice));
	    conn_productcombo.save(comboPO);
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/regions",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Map<String, Object> regionList(long parentId)throws Exception{
        List<RegionPo> regionPos=conn_region.findByField("parentId", parentId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", regionPos);		
		return map;
	}
	
	/**
	 * 获取商品排序的相关内容
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="sort.do")
	public ModelAndView getMercSortInfo(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		String productId =  request.getParameter("productId");
		ProductPO  _product = conn_product.get(Long.parseLong(productId));
		  MerchantPO  _merchant = conn_merchant.get(_product.getProductMerchantID());
		List<ProductPO> productPOs = conn_product.findByMerchantId(_product.getProductMerchantID());
		 int nowOrder = -1;
		for(int i = 0;i<productPOs.size();i++){
		  String id = String.valueOf(productPOs.get(i).getId());
		  
			if(id.equals(productId)){
				nowOrder = i+1;	
			}			
		}
		map.put("product", _product);	
		map.put("merchant", _merchant);
		map.put("nowOrder",nowOrder );
	    return new ModelAndView("admin/product/sortlist",map);
	}	
	
	/**
	 *  商品排序
	 * */
	@ResponseBody
	@RequestMapping(value="sortChange")
	public String MercSortChange(HttpServletRequest request){
		String merchantId =  request.getParameter("merchantId");
		String productId =  request.getParameter("productId");
		String state =  request.getParameter("state");
		ProductPO product = conn_product.get(Long.parseLong(productId));
		List<ProductPO>  _products = conn_product.findByMerchantId(Long.parseLong(merchantId));				
		long index1  =  product.getProductSort();
		int  number = -1;
		for(int i = 0; i< _products.size();i++){
		   String id  =  String.valueOf(_products.get(i).getId());
		   if(productId.equals(id)){
			   //上移
				if("0".equals(state)){
                      if(i != 0){
                    	  number = i-1;  
                      } 				   
                      else{
                    	return "0";//商户位置已经在第一的返回标识  
                      }
				}
				//下移
				else if ("1".equals(state)) {
					if(i  < (_products.size()-1)){
						number = i + 1;
						}
					else{						
						return "1";//商户位置已经在最底的返回标识
					}
				}
				//置顶
				else{
				   	 number = 0;
				} 
				   long index2 = _products.get(number).getProductSort();
				   _products.get(number).setProductSort(index1);
				   product.setProductSort(index2);
				   conn_product.update(_products.get(number));
				   conn_product.update(product);				
		   }	
		   
		}						
	           return "success";
	}	
}
