package com.guolaiwan.app.web.admin.controller;

import java.io.UnsupportedEncodingException;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.guolaiwan.app.web.admin.Utils.ExportExcelSeedBack;
import com.guolaiwan.app.web.admin.vo.MerchantVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.dao.MerModularDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantBusinessDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantChildrenDao;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ModularDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.MerchantSpecialBusiness;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantBusinessPO;
import com.guolaiwan.bussiness.admin.po.MerchantChildrenPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;

/**
 * 
 * <p>
 * Title: MerchantController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: guolaiwan
 * </p>
 * 
 * @author Mr.Sun
 * @date 2017年11月28日 上午9:12:37
 */

@Controller
@RequestMapping("/admin/merchant")
public class MerchantController extends BaseController {

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private UserInfoDAO conn_userinfo;

	@Autowired
	private ModularClassDAO conn_modularClass;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private CityInfoDAO conn_cityInfo;

	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private MerModularDAO conn_merModular;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	// 商户列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int allcount = 1;
		if (getLoginInfo() != null) {
			Long comId = getLoginInfo().getComId();
			String[] names = { "comId" };
			Object[] values = { comId };
			allcount = conn_merchant.getCountByPageA(names, values);
		}
		strMap.put("allcount", allcount);
		strMap.put("allpages", GetAllPages(allcount, 5));
		ModelAndView mv = new ModelAndView("admin/merchant/list", strMap);
		return mv;
	}

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<MerchantPO> listpo = new ArrayList<MerchantPO>();
		if (getLoginInfo() != null) {

			Long comId = getLoginInfo().getComId();
			String mName = request.getParameter("mName");
			strMap.put("comId", comId);
			strMap.put("shopName", mName);
			listpo = conn_merchant.findByPageC(strMap, page, limit);
		} else {
			listpo.add(conn_merchant.get(getMerchantInfo().getMerchantId()));
		}

		int allcount = conn_merchant.countAll();
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		for (MerchantVO merchantVO : listvo) {
			String[] field = { "productMerchantID" };
			Object[] value = { merchantVO.getId() };
			int productCount = conn_product.countByFields(field, value);
			merchantVO.setProductCount(productCount);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", allcount);
		return map;
	}

	// 添加商户弹出窗口
	@RequestMapping("/addv")
	public String AddView() {
		return "admin/merchant/add";
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {

		String shopName = request.getParameter("shopName");
		String shopLoginName = request.getParameter("shopLoginName");
		String shopLoginPwd = request.getParameter("shopLoginPwd");
		String shopHeading = request.getParameter("shopHeading");
		String shopQualifications = request.getParameter("shopQualifications");
		String distributionId = request.getParameter("distributionId");
		String shopQQ = request.getParameter("shopQQ");
		String shopTel = request.getParameter("shopTel");
		String shopAddress = request.getParameter("shopAddress");
		String shopBankId = request.getParameter("shopBankId");
		String shopOpenBank = request.getParameter("shopOpenBank");
		String shopLinkperson = request.getParameter("shopLinkperson");
		String shopPic = request.getParameter("shopPic");
		String shopMpic = request.getParameter("shopMpic");
		String shopIntroduction = request.getParameter("shopIntroduction");
		String shopLongitude = request.getParameter("shopLongitude");
		String shopLatitude = request.getParameter("shopLatitude");
		String modularName = request.getParameter("modularName");
		String modularCode = request.getParameter("modularCode");
		String modularClass = request.getParameter("modularClass");
		String modularClassId = request.getParameter("modularClassId");
		String like = request.getParameter("like"); //特色
		String businessDate = request.getParameter("businessDate"); //商家时间
		
		
		String modularName1 = request.getParameter("modularName1");
		String modularCode1 = request.getParameter("modularCode1");
		String modularClass1 = request.getParameter("modularClass1");
		String modularClassId1 = request.getParameter("modularClassId1");
		
		String modularName2 = request.getParameter("modularName2");
		String modularCode2 = request.getParameter("modularCode2");
		String modularClass2 = request.getParameter("modularClass2");
		String modularClassId2 = request.getParameter("modularClassId2");
		
		
		String shopAuditstates = request.getParameter("shopAuditstates");
		String signPic = request.getParameter("signPic");
		String shopyd = request.getParameter("shopyd");
		
		String beginAge = request.getParameter("beginAge");
		String beginTime = request.getParameter("beginTime");
		String endAge = request.getParameter("endAge");
		String endTime = request.getParameter("endTime");
		
		String beginTimeDate = beginAge.concat(" ").concat(beginTime);
		String endTimeDate = endAge.concat(" ").concat(endTime);
		

		
		MerchantPO merchant = new MerchantPO();
		MerchantPO check = conn_merchant.getByField("shopLoginName", shopLoginName);
		if (check != null) {
			return "has";
		}

		String business = request.getParameter("business");
		if (business == null || business == "") {
			merchant.setBusiness(MerchantSpecialBusiness.BASIC);
		} else {
			merchant.setBusiness(MerchantSpecialBusiness.fromString(business));
		}

		UserInfoPO chkuser = conn_userinfo.getUserByPhone(shopLoginName);
		if (chkuser != null) {
			return "has";
		}

		UserInfoPO user = new UserInfoPO();
		user.setUserPhone(shopLoginName);
		user.setUserPassword(Sha1Util.getSha1(shopLoginPwd));
		user.setUpdateTime(new Date());
		user.setSource(0);
        if(like != "" && like!=null){
        	merchant.setFeature(like);
        }
        if(businessDate != "" && businessDate!=null){
        	merchant.setBusinessDate(businessDate);
        }
		merchant.setDistributionId(Integer.parseInt(distributionId));
		merchant.setShopName(shopName);
		merchant.setShopLoginName(shopLoginName);
		merchant.setShopLoginPwd(Sha1Util.getSha1(shopLoginPwd));
		merchant.setShopHeading(shopHeading);
		merchant.setShopQualifications(shopQualifications);
		merchant.setShopQQ(shopQQ);
		merchant.setShopTel(shopTel);
		merchant.setShopAddress(shopAddress);
		merchant.setShopBankId(shopBankId);
		merchant.setShopOpenBank(shopOpenBank);
		merchant.setShopLinkperson(shopLinkperson);
		merchant.setShopPic(shopPic);
		merchant.setShopMpic(shopMpic);
		merchant.setShopIntroduction(shopIntroduction);
		merchant.setShopLongitude(shopLongitude);
		merchant.setShopLatitude(shopLatitude);
		merchant.setShopAuditState(ShopAuditStateType.fromString(shopAuditstates));
		merchant.setModularName(modularName);
		merchant.setModularCode(modularCode);
		merchant.setModularClass(modularClass);
		merchant.setModularClassId(modularClassId);
		
		
		merchant.setModularName1(modularName1);
		merchant.setModularCode1(modularCode1);
		merchant.setModularClass1(modularClass1);
		merchant.setModularClassId1(modularClassId1);
		
		merchant.setModularName2(modularName2);
		merchant.setModularCode2(modularCode2);
		merchant.setModularClass2(modularClass2);
		merchant.setModularClassId2(modularClassId2);
		
		
		merchant.setSignPic(signPic);
		merchant.setShopyd(Integer.parseInt(shopyd));

		merchant.setUpdateTime(new Date());
		// session
		merchant.setComId(getLoginInfo().getComId());
		merchant.setComName(getLoginInfo().getComName());
		merchant.setCityCode(getLoginInfo().getCityCode());
		merchant.setCityName(getLoginInfo().getCityName());
		merchant.setBeginTimeDate(beginTimeDate);
		merchant.setEndTimeDate(endTimeDate);
		user.setMerchant(merchant);
		merchant.setUser(user);

		conn_userinfo.save(user);
		conn_merchant.save(merchant);

		return "success";
	}

	// 验证登录名是否重复
	@ResponseBody
	@RequestMapping(value = "/verifyLoginName.do", method = RequestMethod.POST)
	public String IsLoginName(HttpServletRequest request) throws Exception {
		String shopLoginName = request.getParameter("shopLoginName");

		MerchantPO merchant = new MerchantPO();
		MerchantPO check = conn_merchant.getByField("shopLoginName", shopLoginName);
		if (check != null) {
			return "has";
		}

		return "success";
	}

	// 修改页面弹出窗口
	@RequestMapping(value = "/updatev", method = RequestMethod.GET)
	public ModelAndView updateView(HttpServletRequest request) {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("list", merchant);
		strMap.put("sysConfig", sysConfig);
		String[] pic = merchant.getShopMpic().split(",");
		for (int i = 1; i <= pic.length; i++) {
			String imgstr = "img" + i;
			strMap.put(imgstr, pic[i - 1]);
		}
		ModelAndView mv = new ModelAndView("admin/merchant/modify");
		mv.addAllObjects(strMap);

		return mv;
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);
		UserInfoPO user = merchant.getUser();
		String shopName = request.getParameter("shopName");
		String shopLoginName = request.getParameter("shopLoginName");
		String shopLoginPwd = request.getParameter("shopLoginPwd");
		String shopHeading = request.getParameter("shopHeading");
		String shopQualifications = request.getParameter("shopQualifications");
		String shopQQ = request.getParameter("shopQQ");
		String shopTel = request.getParameter("shopTel");
		String shopAddress = request.getParameter("shopAddress");
		String shopBankId = request.getParameter("shopBankId");
		String shopOpenBank = request.getParameter("shopOpenBank");
		String shopLinkperson = request.getParameter("shopLinkperson");
		String shopPic = request.getParameter("shopPic");
		String shopMpic = request.getParameter("shopMpic");
		String shopIntroduction = request.getParameter("shopIntroduction");
		String shopLongitude = request.getParameter("shopLongitude");
		String shopLatitude = request.getParameter("shopLatitude");
		String modularName = request.getParameter("modularName");
		String modularCode = request.getParameter("modularCode");
		String modularClass = request.getParameter("modularClass");
		String modularClassId = request.getParameter("modularClassId");
		String shopyd = request.getParameter("shopyd");
		
		String modularName1 = request.getParameter("modularName1");
		String modularCode1 = request.getParameter("modularCode1");
		String modularClass1 = request.getParameter("modularClass1");
		String modularClassId1 = request.getParameter("modularClassId1");
		
		String modularName2 = request.getParameter("modularName2");
		String modularCode2 = request.getParameter("modularCode2");
		String modularClass2 = request.getParameter("modularClass2");
		String modularClassId2 = request.getParameter("modularClassId2");
		
		
		String shopLoginPwdj = Sha1Util.getSha1(shopLoginPwd);
		if (shopLoginPwd.length() > 20) {
			shopLoginPwdj = shopLoginPwd;
		}

		String shopAuditstates = request.getParameter("shopAuditstates");
		String business = request.getParameter("business");
		if (business == null || business == "") {
			merchant.setBusiness(MerchantSpecialBusiness.BASIC);
		} else {
			merchant.setBusiness(MerchantSpecialBusiness.fromString(business));
		}

		merchant.setShopName(shopName);
		merchant.setShopLoginName(shopLoginName);
		merchant.setShopLoginPwd(shopLoginPwdj);
		merchant.setShopHeading(shopHeading);
		merchant.setShopQualifications(shopQualifications);
		merchant.setShopQQ(shopQQ);
		merchant.setShopTel(shopTel);
		merchant.setShopAddress(shopAddress);
		merchant.setShopBankId(shopBankId);
		merchant.setShopOpenBank(shopOpenBank);
		merchant.setShopLinkperson(shopLinkperson);
		merchant.setShopPic(shopPic);
		merchant.setShopMpic(shopMpic);
		merchant.setShopIntroduction(shopIntroduction);
		merchant.setShopLongitude(shopLongitude);
		merchant.setShopLatitude(shopLatitude);
		merchant.setModularName(modularName);
		merchant.setModularCode(modularCode);
		merchant.setModularClass(modularClass);
		merchant.setModularClassId(modularClassId);
		merchant.setShopAuditState(ShopAuditStateType.fromString(shopAuditstates));
		merchant.setShopyd(Integer.parseInt(shopyd));
		user.setUserPhone(shopLoginName);
		user.setUserPassword(shopLoginPwdj);

		merchant.setModularName1(modularName1);
		merchant.setModularCode1(modularCode1);
		merchant.setModularClass1(modularClass1);
		merchant.setModularClassId1(modularClassId1);
		
		merchant.setModularName2(modularName2);
		merchant.setModularCode2(modularCode2);
		merchant.setModularClass2(modularClass2);
		merchant.setModularClassId2(modularClassId2);
		
		
		user.setMerchant(merchant);
		merchant.setUser(user);

		conn_userinfo.saveOrUpdate(user);
		conn_merchant.update(merchant);

		return "success";
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);

		// 解关联
		UserInfoPO user = merchant.getUser();
		merchant.setUser(null);
		user.setMerchant(null);

		conn_merchant.delete(merchant);
		conn_userinfo.delete(user);
		return "success";
	}

	// 待审核商家列表页面
	@RequestMapping(value = "/checklist", method = RequestMethod.GET)
	public ModelAndView check(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<CityInfoPO> cityList = conn_cityInfo.findAll();
		int allcount = conn_merchant.countByField("shopAuditState", ShopAuditStateType.D);

		strMap.put("allcount", allcount);
		strMap.put("cityList", cityList);
		strMap.put("allpages", GetAllPages(allcount, 5));

		ModelAndView mv = new ModelAndView("admin/merchant/checklist", strMap);
		return mv;
	}

	// 待审核商家列表
	@ResponseBody
	@RequestMapping(value = "/checklist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getCheckList(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		Long comId = getLoginInfo().getComId();
		String mName = request.getParameter("mName");
		strMap.put("comId", comId);
		strMap.put("shopName", mName);
		strMap.put("shopAuditState", ShopAuditStateType.D);
		List<MerchantPO> listpo = conn_merchant.findByPageC(strMap, page, limit);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		int count = conn_merchant.countByPageC(strMap);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("msg", "");
		map.put("code", "0");
		map.put("count", count);
		return map;
	}

	// 审核通过列表页面
	@RequestMapping(value = "/passlist", method = RequestMethod.GET)
	public ModelAndView checkc(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<CityInfoPO> cityList = conn_cityInfo.findAll();
		int checkcount = conn_merchant.countByField("shopAuditState", ShopAuditStateType.T);
		strMap.put("cityList", cityList);
		strMap.put("checkcount", checkcount);
		strMap.put("allpages", GetAllPages(checkcount, 5));
		ModelAndView mv = new ModelAndView("admin/merchant/passlist", strMap);
		return mv;
	}

	// 分城市查询
	@ResponseBody
	@RequestMapping(value = "/getMerByCity.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> countByCityp(HttpServletRequest request) throws Exception {
		String shopAuditState = request.getParameter("shopAuditState");
		String cityCode = request.getParameter("cityCode");
		int pagecurr = Integer.parseInt(request.getParameter("pagecurr"));
		Map<String, Object> newmap = new HashMap<String, Object>();

		newmap.put("cityCode", cityCode);
		newmap.put("shopAuditState", ShopAuditStateType.fromString(shopAuditState));

		List<MerchantPO> listpo = conn_merchant.getListByPageE(newmap, pagecurr, 10);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("getlist", listvo);
		return map;
	}

	// 分城市計數 getCountByCity.do
	@ResponseBody
	@RequestMapping(value = "/getCountByCity.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> countByCitypC(HttpServletRequest request) throws Exception {
		String cityCode = request.getParameter("cityCode");
		String shopAuditState = request.getParameter("shopAuditState");
		Map<String, Object> newmap = new HashMap<String, Object>();
		if (!cityCode.equals("-1")) {
			newmap.put("cityCode", cityCode);
		}
		newmap.put("shopAuditState", ShopAuditStateType.fromString(shopAuditState));
		int count = conn_merchant.getCountByCity(newmap);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("getcount", count);
		return map;
	}

	// 审核通过商家列表
	@ResponseBody
	@RequestMapping(value = "/passlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getCheckListc(int page, int limit, HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		Long comId = getLoginInfo().getComId();
		String mName = request.getParameter("mName");
		strMap.put("comId", comId);
		strMap.put("shopName", mName);
		strMap.put("shopAuditState", ShopAuditStateType.T);
		List<MerchantPO> listpo = conn_merchant.findByPageC(strMap, page, limit);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		int count = conn_merchant.countByPageC(strMap);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("msg", "");
		map.put("code", "0");
		map.put("count", count);
		return map;
	}

	// 审核商家详情
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView checkView(HttpServletRequest request) {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		MerchantPO menu = conn_merchant.get(uuid);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("list", menu);
		strMap.put("sysConfig", sysConfig);

		String[] pic = menu.getShopMpic().split(",");
		for (int i = 1; i <= pic.length; i++) {
			String imgstr = "img" + i;
			strMap.put(imgstr, pic[i - 1]);
		}
		ModelAndView mv = new ModelAndView("admin/merchant/check");
		mv.addAllObjects(strMap);

		return mv;
	}

	/*// 审核结论
	@ResponseBody
	@RequestMapping(value = "/checkResult.do", method = RequestMethod.POST)
	public String checkResult(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);

		String shopAuditState = request.getParameter("shopAuditState");
		String shopAuditopinion = request.getParameter("shopAuditopinion");

		merchant.setShopAuditState(ShopAuditStateType.fromString(shopAuditState));
		merchant.setShopAuditopinion(shopAuditopinion);

		conn_merchant.update(merchant);

		return "success";
	}*/
	
		// 审核结论   新改动的方法4/25 添加了在更改商户状态为下架的时候同时更改相关的所有商品状态为下架
		@ResponseBody
		@RequestMapping(value = "/checkResult.do", method = RequestMethod.POST)
		public String checkResult(HttpServletRequest request) throws Exception {
			String uuid = request.getParameter("uuid");
			MerchantPO merchant = conn_merchant.get(uuid);

			String shopAuditState = request.getParameter("shopAuditState");
			String shopAuditopinion = request.getParameter("shopAuditopinion");

			merchant.setShopAuditState(ShopAuditStateType.fromString(shopAuditState));
			merchant.setShopAuditopinion(shopAuditopinion);
			if(shopAuditState.equals("T")){
				List<ProductPO> byMerchantId = conn_product.findByMerchantId(merchant.getId());
				for (ProductPO productPO : byMerchantId) {
					productPO.setProductAuditstatus(ShopAuditStateType.T);
					conn_product.update(productPO);
				}
			}else{
				List<ProductPO> byMerchantId = conn_product.findByMerchantId(merchant.getId());
				for (ProductPO productPO : byMerchantId) {
					productPO.setProductAuditstatus(ShopAuditStateType.N);
					conn_product.update(productPO);
				}
			}
			
			conn_merchant.update(merchant);
			
			return "success";
		}

	@RequestMapping(value = "/sellist", method = RequestMethod.GET)
	public ModelAndView selhome(HttpServletRequest request) {
		String mcname = request.getParameter("mcname");
		String mcuuid = request.getParameter("mcuuid");
		String mchref = request.getParameter("mchref");
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_merchant.getCountByPage();
		strMap.put("count", count);
		strMap.put("mcuuid", mcuuid);
		strMap.put("mcname", mcname);
		strMap.put("mchref", mchref);
		ModelAndView mv = new ModelAndView("admin/merchant/selectlist", strMap);
		return mv;
	}

	// 详情页面
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request) {
		String uuid = request.getParameter("uuid");
		Map<String, Object> strMap = new HashMap<String, Object>();
		MerchantPO merchant = conn_merchant.get(uuid);
		strMap.put("merchant", merchant);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		strMap.put("sysConfig", sysConfig);
		String[] pic = merchant.getShopMpic().split(",");
		for (int i = 1; i <= pic.length; i++) {
			String imgstr = "img" + i;
			strMap.put(imgstr, pic[i - 1]);
		}
		ModelAndView mv = new ModelAndView("admin/merchant/info");
		mv.addAllObjects(strMap);
		return mv;
	}

	// 商家商品列表
	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView productList(HttpServletRequest request) {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("merchant");
		MerchantPO merchant = conn_merchant.get(uuid);
		long merchantId = merchant.getId();
		String[] field = { "productMerchantID" };
		Object[] value = { merchantId };
		int productCount = conn_product.countByFields(field, value);
		List<MerModularPO> merModulars = merchant.getMerModulars();
		int has = 1;
		if (merModulars.size() == 0 || merModulars == null) {
			has = 0;
		}
		strMap.put("merchant", merchant);
		strMap.put("merModulars", merModulars);
		strMap.put("has", has);
		strMap.put("count", productCount);

		ModelAndView mv = new ModelAndView("admin/merchant/productList");
		mv.addAllObjects(strMap);
		return mv;
	}

	@RequestMapping(value = "/toUpdateUserName", method = RequestMethod.GET)
	public ModelAndView toUpdateUserName(HttpServletRequest request) {
		ModelAndView mav = null;
		String merchantId = request.getParameter("merchantId");
		String type = request.getParameter("type");
		request.setAttribute("merchantId", merchantId);
		request.setAttribute("type", type);
		mav = new ModelAndView("admin/merchant/adduser");
		return mav;
	}

	// 商家商品
	@ResponseBody
	@RequestMapping(value = "/productList.do", method = RequestMethod.POST)
	public String pList(HttpServletRequest request) throws Exception {
		int pagecurr = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		long merchant = Long.parseLong(request.getParameter("merchant"));

		String[] fields = { "productMerchantID" };
		Object[] values = { merchant };
		int productCount = conn_product.countByFields(fields, values);
		List<ProductPO> listpo = conn_product.findByFields(fields, values, pagecurr, limit);
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(listvo));
		String jsonString = jsonArray.toString();
		String backStr = "{\"code\":0,\"msg\":\"\",\"count\":" + productCount + ",\"data\":" + jsonString + "}";
		return backStr;
	}

	@ResponseBody
	@RequestMapping(value = "getMerchantBySc.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ModelAndView getMerchantBySc(HttpServletRequest request) throws Exception {
		ModelAndView mav = null;
		String merName = request.getParameter("merchantName");
		request.setAttribute("merName", merName);
		mav = new ModelAndView("admin/merchant/mohuselect");
		return mav;
	}

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "mohuList.do/{merName}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getMoHuList(@PathVariable String merName, int page, int limit,
			HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		Long comId = getLoginInfo().getComId();
		String mName = request.getParameter("mName");
		strMap.put("comId", comId);
		strMap.put("shopName", mName);
		List<MerchantPO> listpo = conn_merchant.findByPageC(strMap, page, limit);
		int allcount = conn_merchant.countByPageC(strMap);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		for (MerchantVO merchantVO : listvo) {
			String[] field = { "productMerchantID" };
			Object[] value = { merchantVO.getId() };
			int productCount = conn_product.countByFields(field, value);
			merchantVO.setProductCount(productCount);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", listvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", allcount);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/userlist.do", method = RequestMethod.POST)
	public Map<String, Object> getUsers(HttpServletRequest request, int page, int limit) throws Exception {
		String userPhone = request.getParameter("userPhone");
		String nickname = request.getParameter("nickname");
		String openId = request.getParameter("openId");
		List<UserInfoPO> userinfo = conn_userinfo.GetListbyPage(page, limit, userPhone, nickname, openId);
		List<UserInfoVO> _userinfo = UserInfoVO.getConverter(UserInfoVO.class).convert(userinfo, UserInfoVO.class);
		int count = conn_userinfo.countByPhone(userPhone);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", _userinfo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	@Autowired
	private MerchantBusinessDAO conn_merchantBusiness;

	@ResponseBody
	@RequestMapping(value = "/chooseUser.do", method = RequestMethod.POST)
	public String chooseUser(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String merchantId = request.getParameter("merchantId");
		int type = Integer.parseInt(request.getParameter("type"));
		if(type==1){
			MerchantBusinessPO merchantBusiness = conn_merchantBusiness
					.getMerchantBusinessBymerchantId(Long.parseLong(merchantId));
			List<UserInfoPO> user = conn_userinfo.getUserByUid(Long.parseLong(userId));
			List<MerchantPO> merchant = conn_merchant.getMerchantById(Long.parseLong(merchantId));
			merchant.get(0).setUserName(user.get(0).getUserPhone());
			conn_merchant.saveOrUpdate(merchant.get(0));
			if (merchantBusiness == null) {
				merchantBusiness = new MerchantBusinessPO();
				merchantBusiness.setMerchantId(Long.parseLong(merchantId));
				merchantBusiness.setUserId(Long.parseLong(userId));
				conn_merchantBusiness.save(merchantBusiness);
				return "success";
			} else {
				merchantBusiness.setUserId(Long.parseLong(userId));
				return "success";
			}
		}else{
			System.out.println("这是选择客服人员");
			List<UserInfoPO> user = conn_userinfo.getUserByUid(Long.parseLong(userId));
			List<MerchantPO> merchant = conn_merchant.getMerchantById(Long.parseLong(merchantId));
			merchant.get(0).setChatUserId(user.get(0).getId());
			conn_merchant.saveOrUpdate(merchant.get(0));
			return "success";
		}
	}

	@RequestMapping(value = "/exportallmerchant")
	public String derive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<MerchantPO> listpo = new ArrayList<MerchantPO>();
		if (getLoginInfo() != null) {
			Long comId = getLoginInfo().getComId();
			String mName = request.getParameter("mName");
			String status=request.getParameter("status");
			strMap.put("comId", comId);
			strMap.put("shopName", mName);
			if(status!=null&&!status.equals("")){
				strMap.put("status", status);
			}
			listpo = conn_merchant.findAllByMapParams(strMap);
		} else {
			listpo.add(conn_merchant.get(getMerchantInfo().getMerchantId()));
		}
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		String title = "商户" + DateUtil.format(new Date(), "yyyyMMddhhmmss") + ".xls";
		String[] headers = new String[] { "序号", "商家名称", "商户地址", "联系人", "联系电话", "板块","登录名" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		if (listvo != null) {
			for (int i = 0; i < listvo.size(); i++) {
				Object[] obj = new Object[headers.length];
				obj[1] = listvo.get(i).getShopName();
				obj[2] = listvo.get(i).getShopAddress();
				obj[3] = listvo.get(i).getShopLinkperson();
				obj[4] = listvo.get(i).getShopTel();
				obj[5] = listvo.get(i).getModularName();
				obj[6] = listvo.get(i).getShopLoginName();
				dataList.add(obj);
			}
		}
		outputList(title, headers, dataList, response);
		return "success";
	}

	// 导出表
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
	
	/**
	 *  跳轉添加子商戶頁面
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/skip.do" )
	public ModelAndView merchantSkip(HttpServletRequest request)  {
		ModelAndView  mView = new ModelAndView("admin/merchant/addmerchant");
		String merchantName = request.getParameter("name");
		String id =request.getParameter("id");						
		mView.addObject("merchantId", id);
		mView.addObject("merchantName", merchantName);
		return mView;
	}
	
	/**
	 * 
	 * 商户子信息
	 * 
	 * */
	// 子信息异步读取列表分页
		@ResponseBody
		@RequestMapping(value = "/lists.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
		public Map<String, Object> getLists(int page, int limit, HttpServletRequest request) throws Exception {
		    String 	merchantId = request.getParameter("merchantId");
			List<MerchantPO> listp = conn_merchant.findAll(page, limit);	
			List<MerchantPO> listpo = new ArrayList<MerchantPO>();
			for(MerchantPO merchantPO : listp){
				if(String.valueOf(merchantPO.getId()).equals(merchantId)){
					continue;	
				}
				else{
					listpo.add(merchantPO);
				}
			}
			List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
			int allcount = conn_merchant.countAll();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("data", listvo);
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", allcount);
			return map;
		}
		
		@Autowired	 
		private MerchantChildrenDao conn_merch;
	/**
	 *  添加商戶與子商戶信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value = "/subordinate.do",method=RequestMethod.POST)
	public String addSubordinate(HttpServletRequest request) {		
	try{	
		String childrenId = request.getParameter("childrenId");
		String merchantId =	request.getParameter("merchantId");
		List<MerchantPO> childrenName = conn_merchant.findByField("id", Long.parseLong(childrenId));
		List<MerchantPO> merchantName = conn_merchant.findByField("id", Long.parseLong(merchantId));
		MerchantChildrenPO merch = new MerchantChildrenPO();
		merch.setMerchantId(Long.parseLong(merchantId));
		merch.setChildrenId(Long.parseLong(childrenId));
		merch.setMerchantName(merchantName.get(0).getShopName());
		merch.setChildrenName(childrenName.get(0).getShopName());
		conn_merch.save(merch);
		return "success";
	}catch(Exception e){
		return "false";
	}	
}
	/**
	 * 获取商户排序的相关内容
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="sort.do")
	public ModelAndView getMercSortInfo(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		String merchantId =  request.getParameter("id");
		MerchantPO  merPo = conn_merchant.get(Long.parseLong(merchantId));
		List<MerchantPO> merchantPOs = conn_merchant.findModularCode(merPo.getModularCode());
		 int nowOrder = -1;
		for(int i = 0;i<merchantPOs.size();i++){
		  String id = String.valueOf(merchantPOs.get(i).getId());
			if(id.equals(merchantId)){
				nowOrder = i+1;	
			}			
		}
		map.put("merchant", merPo);	
		map.put("nowOrder",nowOrder );
	    return new ModelAndView("admin/merchant/sortlist",map);
	}	
	
	/**
	 * 商户排序
	 * */
	@ResponseBody
	@RequestMapping(value="sortChange")
	public String MercSortChange(HttpServletRequest request){
		String merchantId =  request.getParameter("id");
		String state =  request.getParameter("state");
		String modularCode = request.getParameter("modularCode");		
		List<MerchantPO> merchantPOs = conn_merchant.findModularCode(modularCode);
		MerchantPO _merPo  = conn_merchant.get(Long.parseLong(merchantId));
		long index1  =  _merPo.getProductSortIndex();
		int  number = -1;
		for(int i = 0; i< merchantPOs.size();i++){
		   String id  =  String.valueOf(merchantPOs.get(i).getId());
		   if(merchantId.equals(id)){
			   //上移
				if("0".equals(state)){
                      if(i != 0){
                    	  number = i-1;  
                    	  System.out.println("上移");
                      } 				   
                      else{
                    	return "0";//商户位置已经在第一的返回标识  
                      }
				}
				//下移
				else if ("1".equals(state)) {
					if(i  < (merchantPOs.size()-1)){
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
				   long index2 = merchantPOs.get(number).getProductSortIndex();
				   merchantPOs.get(number).setProductSortIndex(index1);
				   _merPo.setProductSortIndex(index2);
				   conn_merchant.update(merchantPOs.get(number));
				   conn_merchant.update(_merPo);				
		   }	
		   
		}						
	           return "success";
	}	
	
}
