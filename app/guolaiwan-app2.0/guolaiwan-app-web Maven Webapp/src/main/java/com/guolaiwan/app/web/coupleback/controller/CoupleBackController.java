package com.guolaiwan.app.web.coupleback.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.coupleback.vo.CoupleBackVo;
import com.guolaiwan.app.web.smartParking.vo.AttractionsVo;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.Parking.dao.VehicleDao;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.coupleback.dao.CoupleBackDao;
import com.guolaiwan.bussiness.coupleback.po.CoupleBackPO;

//反馈首页
@Controller
@RequestMapping("/back")
public class CoupleBackController  extends WebBaseControll{



	@Autowired
	private CoupleBackDao couple_back;
	@Autowired
	private UserInfoDAO conn_user;




	/*****************************************************************************************************/


	/**
	 * 根据用户反馈添加数据
	 * @param request 头像  用户名
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addfeedback", method = RequestMethod.POST)
	public Map<String, Object> addFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = 	(Long) request.getSession().getAttribute("userId");
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		String content = pageObject.getString("content");
		UserInfoPO user = conn_user.get(userId); //获取用户头像
		if (user == null) {
			return ERROR("未获取到用户！");
		}
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format( now ); 
		CoupleBackPO coup = new CoupleBackPO();
		coup.setUserId(userId);
		coup.setHeadportrait(user.getUserHeadimg());
		coup.setUsername(user.getUserNickname());
		coup.setDate(date);
		coup.setContent(content);
		coup.setState(0);
		couple_back.save(coup);
		return success("");
	}

	/*****************************************************************************************************/


	/**
	 * 分页查询 反馈内容
	 * @param request 头像  用户名
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/selefeedback", method = RequestMethod.POST)
	public Map<String, Object> seleFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = getRequestJson(request);
		if (param.indexOf("\\") >= 0) {
			param = param.replaceAll("\\\\", "");
			param = param.substring(1, param.length() - 1);
		}
		JSONObject pageObject = JSON.parseObject(param);
		List<CoupleBackPO> coup = couple_back.getOrderform(0,100);
		List<CoupleBackVo> _merchants = CoupleBackVo.getConverter(CoupleBackVo.class).convert(coup,
				CoupleBackVo.class);
		return success(_merchants);
	}





	//转到jsp文件
	@RequestMapping(value = "/merchant/comment")
	public ModelAndView Comment(HttpServletRequest request,HttpSession session) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("parking/park/comment"); 
		return mv;
	}
























	/*****************************************************************************************************/


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



	/*****************************************************************************************************/

/*	// 后台订单页面
		@RequestMapping(value = "/feedback", method = RequestMethod.GET)
		public ModelAndView getList(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView("admin/feedback/message");
			return mv;
		}*/

		
		
		
		
		
}
