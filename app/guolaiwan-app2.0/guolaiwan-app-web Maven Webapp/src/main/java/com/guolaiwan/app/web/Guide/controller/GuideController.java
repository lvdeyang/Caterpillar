package com.guolaiwan.app.web.Guide.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.ChildProductVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.Parking.dao.AppMessageDao;
import com.guolaiwan.bussiness.Parking.po.AppMessagePO;
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.util.date.DateUtil;

@Controller
@RequestMapping("/guide")
public class GuideController extends WebBaseControll  {

	@Autowired
	private ProductDAO conn_product;
	@Autowired
	private ChildProductDAO conn_childProduct;
	@Autowired
	private MerchantDAO mer_chant;
	@Autowired
	private UserInfoDAO conn_userInfo;
	
	@Autowired
	private AppMessageDao App_message;
	
	
	
	@RequestMapping(value = "/visitors/guidemap/{chantId}")
	public ModelAndView camera(HttpServletRequest request ,@PathVariable String chantId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("guide/guidemap/wanbuddhayuan");
		mv.addObject("chantId", chantId);
		return mv;
	}
	@RequestMapping(value = "/visitors/home")
	public ModelAndView home(HttpServletRequest request ) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("guide/guidemap/home");
		return mv;
	}
	
	@RequestMapping(value = "/visitors/app")
	public ModelAndView app(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("guide/guidemap/homepage");
		return mv;
	}
	
	
	/**
	 * 
	 * app 添加   数据
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/setNeeded", method = RequestMethod.GET)
	public Map<String, Object> setAppMessage(HttpServletRequest request, HttpServletResponse response,String merchantId)
			throws Exception {
		AppMessagePO  Message =  	App_message.getMessage();
		Long userId = (Long) request.getSession().getAttribute("userId");
		MerchantPO  MerchantPO = null;
		if (merchantId != null) {
			 MerchantPO =   mer_chant.getMerchantById(Long.parseLong(merchantId)).get(0);
		}
		if (Message != null && MerchantPO !=null) {
			Message.setUserId(userId);
			Message.setMerchantId(Long.parseLong(merchantId));
			Message.setLocationLatitude(MerchantPO.getLocationLatitude());
			Message.setLocationLongitude(MerchantPO.getLocationLongitude());
			Message.setMerchantName(MerchantPO.getShopName());
			App_message.saveOrUpdate(Message);	
		}else if ( MerchantPO !=null)  {
			AppMessagePO appmess = new AppMessagePO();
			appmess.setUserId(userId);
			appmess.setMerchantId(Long.parseLong(merchantId));
			appmess.setLocationLatitude(MerchantPO.getLocationLatitude());
			appmess.setLocationLongitude(MerchantPO.getLocationLongitude());
			appmess.setMerchantName(MerchantPO.getShopName());
			App_message.saveOrUpdate(appmess);
		}
		return success("");
	}
	
	
	
	
	
	/**
	 * 
	 * app 万佛园 数据
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getNeededMessage", method = RequestMethod.GET)
	public Map<String, Object> getAppMessage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AppMessagePO  Message =  App_message.getMessage();
		Map<String, Object> map = new HashMap<String, Object>();
		if (Message != null) {
			long dis=new Date().getTime()- Message.getUpdateTime().getTime();
			if(dis/1000<300){
				map.put("userId", Message.getUserId()); //用户id
				map.put("merchantId", Message.getMerchantId());//景区id
				map.put("merchantName", Message.getMerchantName());//景区id
				map.put("Latitude", Message.getLocationLatitude()); //经度
				map.put("Longitude", Message.getLocationLongitude());//纬度
			}
		}
		return success(map);
	}
	
	
	
	/**
	 * 获取讲节点列表
	 * 经纬度
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildByPro", method = RequestMethod.POST)
	public Map<String, Object> getChildByPro(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long userId = (Long) request.getSession().getAttribute("userId");
		System.out.println(userId);
		// 解析json
		String param = getRequestJson(request);
		Map<String, Object> map = new HashMap<String, Object>();
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		long merchantId = pageObject.getLong("merchantId");
		
		
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
		List<UserInfoPO> polist = conn_userInfo.getUserByUid(userId);
		List<UserInfoVO> volist = UserInfoVO.getConverter(UserInfoVO.class).convert(polist, UserInfoVO.class);
		for (UserInfoVO userInfoVO : volist) {
			System.out.println(userInfoVO.getChildId());
		}
		map.put("result", result);
		map.put("volist", volist.get(0));
		return success(map);
	}
	
	/**
	 * 
	 * 查询       贴图  xy   导览图片      初始显示位置
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	public Map<String, Object> getMessage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 解析json
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		long merchantId = pageObject.getLong("chantId");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<MerchantPO> product = mer_chant.getMerchantById(merchantId);
		if (product == null) {
			return success(null);
		}
		for (MerchantPO merchantPO : product) {
			dataMap.put("Xlatitude", merchantPO.getXlatitude());
			dataMap.put("Xlongitude",merchantPO.getXlongitude());
			dataMap.put("Ylatitude", merchantPO.getYlatitude());
			dataMap.put("Ylongitude", merchantPO.getYlongitude());
			dataMap.put("latitude", merchantPO.getLocationLatitude());
			dataMap.put("longitude", merchantPO.getLocationLongitude());
			dataMap.put("pictures", merchantPO.getChartletPictures());
		}
		return success(dataMap);
	}
	
	
	/**
	 * 
	 * 清除用户浏览记录
	 * @param productId
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delectchildId", method = RequestMethod.POST)
	public Map<String, Object> DelectChildId(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long userId = (Long) request.getSession().getAttribute("userId");
		List<UserInfoPO> upolist = conn_userInfo.getUserByUid(userId);
		String str = "";
		upolist.get(0).setChildId(str);
		conn_userInfo.saveOrUpdate(upolist.get(0));
		return success("");
	}
	
	
	
	
	

	
	
	/**
	 *  用户每浏览一个导览点执行新增Controller
	 */
	@ResponseBody
	@RequestMapping(value = "/addChildByUidCid", method = RequestMethod.POST)
	public  Map<String, Object> addChildByUidCid(HttpServletRequest request , HttpServletResponse response) {
		Long userId = (Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String merchantId = pageObject.getString("ChildId");
		if(merchantId != null){
			UserInfoPO upolist = conn_userInfo.getUserByUid(userId).get(0);
			if (upolist.getChildId() != null && upolist.getChildId() != "") {
				String[] childId =   upolist.getChildId().split(",");
				for (String string : childId) {
					if (merchantId.equals(string)) {
						return success("");
					}
				}
			}	
			String child = upolist.getChildId();
			String str = null;
			if( !"".equals(child) && child != null ){
				str = child +","+merchantId;
			}else {
				str = merchantId;
			}
			
			upolist.setChildId(str);
			conn_userInfo.saveOrUpdate(upolist);
		}
		
		return success("");
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
