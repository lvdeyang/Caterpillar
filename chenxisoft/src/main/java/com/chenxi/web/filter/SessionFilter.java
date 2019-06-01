package com.chenxi.web.filter;

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

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.weixin.constants.WXContants;

public class SessionFilter implements Filter {

	public FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);

		String userAgent = "";
		if (hrequest.getHeader("user-agent") != null) {
			userAgent = hrequest.getHeader("user-agent").toLowerCase();
		}
		if (userAgent.indexOf("micromessenger") > -1) {
			if (hrequest.getRequestURI().contains("index1") || hrequest.getRequestURI().contains("index2")) {
				chain.doFilter(request, response);
			} else {
				wrapper.sendRedirect("http://www.yueba.net.cn/mobile/index1?rUrl="
						+ hrequest.getRequestURL().toString() + "?" + hrequest.getQueryString());
			}
			return;
		}

		chain.doFilter(request, response);
		return;

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

}
