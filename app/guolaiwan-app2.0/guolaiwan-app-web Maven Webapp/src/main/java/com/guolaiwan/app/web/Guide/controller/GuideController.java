package com.guolaiwan.app.web.Guide.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.guolaiwan.bussiness.admin.dao.ChildProductDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

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
		System.out.println(childProcucts.size()+"------------");
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
			List<UserInfoPO> upolist = conn_userInfo.getUserByUid(userId);
			String[] childId =   upolist.get(0).getChildId().split(",");
			for (String string : childId) {
				if (merchantId.equals(string)) {
					return success("");
				}
			}
			String child = upolist.get(0).getChildId();
			String str = null;
			if( !"".equals(child) && merchantId != null ){
				str = child +","+merchantId;
			}else {
				str = merchantId;
			}
			upolist.get(0).setChildId(str);
			conn_userInfo.saveOrUpdate(upolist.get(0));
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
