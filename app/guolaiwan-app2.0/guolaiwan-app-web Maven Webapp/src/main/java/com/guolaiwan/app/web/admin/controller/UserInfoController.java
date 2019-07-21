package com.guolaiwan.app.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.InvestWalletVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.bussiness.admin.dao.InvestWalletDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.InvestWalletPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.orm.hql.CountHql;

@Controller
@RequestMapping("/admin/userinfo")
public class UserInfoController extends BaseController{

	@Autowired
	private UserInfoDAO conn_UserInfo;
	@Autowired
	private InvestWalletDAO conn_investWallet;
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getuserinfos(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_UserInfo.countAll();
		strMap.put("allCount", count);
		ModelAndView mv = new ModelAndView("admin/userinfo/list",strMap);
		return mv;
	}
	//添加页面
	@RequestMapping("/addv")
	public ModelAndView addUserInfo(){
		ModelAndView mv = new ModelAndView("admin/userinfo/add");
		return mv;
	}
	
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(HttpServletRequest request,int page,int limit) throws Exception {
		String userPhone = request.getParameter("userPhone");
		String nickname = request.getParameter("nickname");
		String openId = request.getParameter("openId");
		List<UserInfoPO> userinfo=conn_UserInfo.GetListbyPage(page, limit,userPhone,nickname,openId);
		List<UserInfoVO> _userinfo = UserInfoVO.getConverter(UserInfoVO.class).convert(userinfo, UserInfoVO.class);
		int count = conn_UserInfo.countByPhone(userPhone);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", _userinfo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}

	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		UserInfoPO userinfo = conn_UserInfo.get(uuid);
		conn_UserInfo.delete(userinfo);
		return "success";
	}
	
	
	//充值记录页面跳转 张羽
	@RequestMapping("/investwalletlist")
	public ModelAndView InvestWalletList(){
		ModelAndView mv = new ModelAndView("admin/userinfo/investWalletList");
		return mv;
	}
	
	//异步读取充值列表分页 张羽
	@ResponseBody
	@RequestMapping(value="/walletlist.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetWalletList(HttpServletRequest request,int page,int limit) throws Exception {
		String userId = request.getParameter("userId");
		String username = request.getParameter("username");
		List<InvestWalletPO> wallet=conn_investWallet.GetListbyPage(page, limit,userId,username);
		List<InvestWalletVO> walletvo = InvestWalletVO.getConverter(InvestWalletVO.class).convert(wallet, InvestWalletVO.class);
		int count = conn_investWallet.countByUserId(userId);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", walletvo);
		map.put("code", "0");
		map.put("msg", "");
		map.put("count", count);
		return map;
	}
	
	//异步读取充值列表分页 张羽
	@ResponseBody
	@RequestMapping(value="/getwalletlist", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> WalletList(HttpServletRequest request,int page,int limit) throws Exception {
		long userId = Long.parseLong(request.getParameter("userId"));
		List<InvestWalletPO> wallet=conn_investWallet.GetListbyPage(page, limit,userId);
		List<InvestWalletVO> walletvo = InvestWalletVO.getConverter(InvestWalletVO.class).convert(wallet, InvestWalletVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("data", walletvo);
		return map;
	}
}

