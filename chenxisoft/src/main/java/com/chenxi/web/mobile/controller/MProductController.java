package com.chenxi.web.mobile.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxi.web.classes.Moudular;
import com.chenxi.web.dao.ProductDao;
import com.chenxi.web.dao.UserRecordDao;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.po.RecommPo;
import com.chenxi.web.po.UserRecordPo;

import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;

@Controller
@RequestMapping("/product")
public class MProductController {
	@Autowired
	UserRecordDao conn_userrecord;
	@Autowired
	ProductDao conn_product;
	@JsonBody
	@ResponseBody
	@RequestMapping(value = "mobile/record", method = RequestMethod.GET)
	public Object userRecord(HttpServletRequest request, HttpServletResponse response, long productId)
			throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ProductPo productPo=conn_product.get(productId);
		UserRecordPo userRecordPo=new UserRecordPo();
		userRecordPo.setClassId(productPo.getClassesId());
		userRecordPo.setContentId(productPo.getId());
		HttpSession session = request.getSession();
		//userRecordPo.setUserId(Long.parseLong(session.getAttribute("userId").toString()));
		conn_userrecord.save(userRecordPo);
		return ret;
	}
}
