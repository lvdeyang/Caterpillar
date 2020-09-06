package com.guolaiwan.app.gonghui.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.CompanyVO;
import com.guolaiwan.app.web.admin.vo.PictureVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.publicnum.util.EmojiFilter;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.ClassPO;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.gonghui.dao.ArticleDao;
import com.guolaiwan.bussiness.gonghui.dao.ClassesDao;
import com.guolaiwan.bussiness.gonghui.dao.LikeDao;
import com.guolaiwan.bussiness.gonghui.dao.OnlineClassesDao;
import com.guolaiwan.bussiness.gonghui.dao.PeopleVoteDao;
import com.guolaiwan.bussiness.gonghui.dao.RecommDao;
import com.guolaiwan.bussiness.gonghui.dao.RecordDao;
import com.guolaiwan.bussiness.gonghui.enumeration.LikeType;
import com.guolaiwan.bussiness.gonghui.enumeration.RecomType;
import com.guolaiwan.bussiness.gonghui.po.ArticlePo;
import com.guolaiwan.bussiness.gonghui.po.ClassesPo;
import com.guolaiwan.bussiness.gonghui.po.LikePo;
import com.guolaiwan.bussiness.gonghui.po.OnlineClassesPo;
import com.guolaiwan.bussiness.gonghui.po.PeopleVotePo;
import com.guolaiwan.bussiness.gonghui.po.RecommPo;
import com.guolaiwan.bussiness.gonghui.po.RecordPo;

import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.communication.http.client.HttpClient;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/pubnum/people/vote")
public class PeopleVoteContoller extends BaseController {
	@Autowired
	PeopleVoteDao conn_peoplevote;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		ModelAndView mv = new ModelAndView("mobile/vote/people", strMap);
		return mv;
	}
	@Autowired
	
	
	@ResponseBody
	@RequestMapping(value = "/getpeoples", method = RequestMethod.GET)
	public Map<String, Object> getpeoples(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PeopleVotePo> peopleVotePos=conn_peoplevote.findAll();
		return success(peopleVotePos);
	}
	
	
}
