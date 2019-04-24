package com.guolaiwan.app.web.publicnum.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.guolaiwan.app.web.admin.Utils.ExportExcelSeedBack;
import com.guolaiwan.app.web.admin.vo.LuckDrawRecordVO;
import com.guolaiwan.app.web.publicnum.util.CommonUtil;
import com.guolaiwan.app.web.website.controller.WebBaseControll;
import com.guolaiwan.bussiness.admin.dao.LuckDrawContactDao;
import com.guolaiwan.bussiness.admin.dao.LuckDrawDao;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.po.LuckDrawContact;
import com.guolaiwan.bussiness.admin.po.LuckDrawRecord;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.weixin.constants.WXContants;
//portal
@Controller
@RequestMapping("/map")
public class MapController extends WebBaseControll {

	
	@RequestMapping(value = "/index")
	public ModelAndView luckdraw(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		
    		mv = new ModelAndView("mobile/pubnum/map");
    		
		return mv;
	}
	
	
	
	
}
