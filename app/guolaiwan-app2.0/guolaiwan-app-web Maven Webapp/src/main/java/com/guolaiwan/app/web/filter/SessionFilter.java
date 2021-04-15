package com.guolaiwan.app.web.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.URLEditor;

import com.guolaiwan.bussiness.operation.dao.WebsiteRecordDAO;
import com.guolaiwan.bussiness.operation.po.WebsiteRecord;

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.weixin.constants.WXContants;

public class SessionFilter implements Filter {

	WebsiteRecordDAO conn_websiterecord = SpringContext.getBean("com.guolaiwan.bussiness.admin.dao.WebsiteRecordDAO");;

	private boolean istest = WXContants.istest;
	public FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	public static boolean isContains(String container, String[] regx) {
		boolean result = false;

		for (int i = 0; i < regx.length; i++) {
			if (container.indexOf(regx[i]) != -1) {
				return true;
			}
		}
		return result;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
		
		//System.out.println("******************"+hrequest.getRemoteAddr());
		//System.out.println("******************"+hrequest.getSession().getId());
		//System.out.println(DateUtil.format(new Date())+"*****************"+hrequest.getRequestURI());
		
		String userAgent = "";
		if (hrequest.getHeader("user-agent") != null) {
			userAgent = hrequest.getHeader("user-agent").toLowerCase();
		}
		
		if (hrequest.getRequestURI().contains("pubnum") || hrequest.getRequestURI().contains("phoneApp")) {
			/*WebsiteRecord record = new WebsiteRecord();
			record.setUrl(hrequest.getRequestURI());
			record.setUpdateTime(new Date());
			conn_websiterecord.save(record);*/
		}

		Object type = hrequest.getSession().getAttribute("type");// 判断用户是否登录
		boolean condition = true;
		if (!istest) {
			condition = (type == null && userAgent.indexOf("micromessenger") > -1);
		} else {
			condition = (type == null);
		}

		if (condition) {
			if (hrequest.getRequestURI().contains("index1") || hrequest.getRequestURI().contains("index2")
					|| hrequest.getRequestURI().contains("index3")|| hrequest.getRequestURI().contains("layui/UEditor")
					|| hrequest.getRequestURI().contains("recordapp")) {
				
				chain.doFilter(request, response);
			} else {
				// 测试
				
				if (!istest) {
					
					wrapper.sendRedirect(
							"http://"+WXContants.Website+"/pubnum/index1?rUrl=" + hrequest.getRequestURL().toString()+"?"+hrequest.getQueryString());
				} else {
					wrapper.sendRedirect("../pubnum/index1?rUrl=" + hrequest.getRequestURL().toString());
				}

			}
			return;
		}
		if ("APP".equals(type)) {
			chain.doFilter(request, response);
			return;
		}
		if ("PHONENUM".equals(type)) {
			chain.doFilter(request, response);
			return;
		}
		/****************** 分割，上面是MRhuang写的 ********************/
		String logonStrings = config.getInitParameter("logonStrings"); // 登录登陆页面
		String includeStrings = config.getInitParameter("includeStrings"); // 过滤资源后缀参数
		String redirectPath = hrequest.getContextPath() + config.getInitParameter("redirectPath");// 没有登陆转向页面
		String disabletestfilter = config.getInitParameter("disabletestfilter");// 过滤器是否有效

		if (disabletestfilter.toUpperCase().equals("Y")) { // 过滤无效
			chain.doFilter(request, response);
			return;
		}
		String[] logonList = logonStrings.split(";");
		String[] includeList = includeStrings.split(";");

		if (!this.isContains(hrequest.getRequestURI(), includeList)) {// 只对指定过滤参数后缀进行过滤
			chain.doFilter(request, response);
			return;
		}

		if (this.isContains(hrequest.getRequestURI(), logonList)) {// 对登录页面不进行过滤
			chain.doFilter(request, response);
			return;
		}

		Object ouser = hrequest.getSession().getAttribute("userInfo");// 判断用户是否登录
		if (ouser == null) {
			String str = hrequest.getQueryString().replace("&", "!");
			wrapper.sendRedirect(redirectPath + "?rul=" + hrequest.getRequestURI() + "?" + str);
			return;
		} else {
			chain.doFilter(request, response);
			return;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

}
