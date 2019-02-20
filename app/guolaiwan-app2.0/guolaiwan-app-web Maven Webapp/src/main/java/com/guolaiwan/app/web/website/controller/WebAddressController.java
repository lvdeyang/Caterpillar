package com.guolaiwan.app.web.website.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.ModularClassDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.guolaiwan.bussiness.website.po.AddressPO;

@Controller
@RequestMapping("/user/address")
public class WebAddressController extends WebBaseControll{
	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private AddressDAO conn_address;

	@Autowired
	private ModularClassDAO conn_modularClass;


	//增加地址
	@ResponseBody
	@RequestMapping(value = "saveOrUpdate.do", method= RequestMethod.POST)
	public String saveOrUpdate(HttpServletRequest request) throws Exception {
		long userId = Long.parseLong(request.getParameter("userId"));

		String addConAddress = request.getParameter("addConAddress");
		String addConEmail = request.getParameter("addConEmail");
		String addConName = request.getParameter("addConName");
		String addConPhone = request.getParameter("addConPhone");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		AddressPO address = new AddressPO();;
		if(request.getParameter("uuid")!=null&&request.getParameter("uuid").length()>0){
			address = conn_address.get(request.getParameter("uuid"));
		}else{
			int acount = conn_address.countByField("userId", userId);
			if(acount>=5){
				return "error";
			}
		}
		address.setConsigneeAddress(addConAddress);
		address.setConsigneeEmail(addConEmail);
		address.setConsigneeName(addConName);
		address.setConsigneePhone(addConPhone);
		address.setProvince(province);
		address.setCity(city);
		address.setDistrict(district);


		address.setUserId(userId);

		conn_address.saveOrUpdate(address);

		return "success";
	}
	//删除地址
	@ResponseBody
	@RequestMapping(value = "del.do", method= RequestMethod.POST)
	public String delAddress(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		conn_address.deleteByUuid(uuid);
		return "success";
	}

	//获取一个地址
	@ResponseBody
	@RequestMapping(value = "getOne.do", method= RequestMethod.POST)
	public Map<String,Object> getOne(HttpServletRequest request) throws Exception {
		Map<String,Object> strMap =  new HashMap<String,Object>();
		String uuid = request.getParameter("uuid");
		AddressPO address = conn_address.get(uuid);
		strMap.put("address", address);
		return strMap;
	}


	//设置默认的地址
	@ResponseBody
	@RequestMapping(value = "setd.do", method= RequestMethod.POST)
	public String setd(HttpServletRequest request) throws Exception {
		Map<String,Object> strMap =  new HashMap<String,Object>();
		String uuid = request.getParameter("uuid");
		long userId = Long.parseLong(request.getParameter("userId"));
		
		String[] fields = {"userId","defaultAddress"};
		Object[] values = {userId,1};
		AddressPO addressn = conn_address.getByFields(fields, values);
		if(addressn!=null){
			addressn.setDefaultAddress(0);
			conn_address.saveOrUpdate(addressn);
		}
		AddressPO address = conn_address.get(uuid);
		address.setDefaultAddress(1);
		conn_address.saveOrUpdate(address);
		return "success";
	}

}
