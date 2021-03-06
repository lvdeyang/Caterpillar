<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl=WXContants.Website;
%>
<!DOCTYPE HTML>
<html lang="zh-cmn-Hans">
<head>
<!-- 声明文档使用的字符编码 -->
<meta charset='utf-8'>
<!-- 优先使用 IE 最新版本和 Chrome -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<!-- 页面描述 -->
<meta name="description" content="" />
<!-- 页面关键词 -->
<meta name="keywords" content="" />
<!-- 网页作者 -->
<meta name="author" content="name, email@gmail.com" />
<!-- 搜索引擎抓取 -->
<meta name="robots" content="index,follow" />
<!-- 为移动设备添加 viewport -->
<meta name="viewport"
	content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
<!-- `width=device-width` 会导致 iPhone 5 添加到主屏后以 WebApp 全屏模式打开页面时出现黑边 http://bigc.at/ios-webapp-viewport-meta.orz -->
<!-- iOS 设备 begin -->
<meta name="apple-mobile-web-app-title" content="标题">
<!-- 添加到主屏后的标题（iOS 6 新增） -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->

<meta name="apple-itunes-app"
	content="app-id=myAppStoreID, affiliate-data=myAffiliateData, app-argument=myURL">
<!-- 添加智能 App 广告条 Smart App Banner（iOS 6+ Safari） -->
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<!-- 设置苹果工具栏颜色 -->
<meta name="format-detection" content="telphone=no, email=no" />
<!-- 忽略页面中的数字识别为电话，忽略email识别 -->
<!-- 启用360浏览器的极速模式(webkit) -->
<meta name="renderer" content="webkit">
<!-- 避免IE使用兼容模式 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 不让百度转码 -->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- 针对手持设备优化，主要是针对一些老的不识别viewport的浏览器，比如黑莓 -->
<meta name="HandheldFriendly" content="true">
<!-- 微软的老式浏览器 -->
<meta name="MobileOptimized" content="320">
<!-- uc强制竖屏 -->
<meta name="screen-orientation" content="portrait">
<!-- QQ强制竖屏 -->
<meta name="x5-orientation" content="portrait">
<!-- UC强制全屏 -->
<meta name="full-screen" content="yes">
<!-- QQ强制全屏 -->
<meta name="x5-fullscreen" content="true">
<!-- UC应用模式 -->
<meta name="browsermode" content="application">
<!-- QQ应用模式 -->
<meta name="x5-page-mode" content="app">
<!-- windows phone 点击无高光 -->
<meta name="msapplication-tap-highlight" content="no">
<title>账号与安全</title>
<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<style type="text/css">
a {
	cursor: pointer !important;
}

a, a:link, a:active, a:visited, a:hover {
	color: inherit;
	text-decoration: none;
}

html, body {
	width: 100%;
	min-height:100%;
	background:#EFEFEF !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	 font-size:100%;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
}
 .nav ul li{
 width:100%;
 background: #fff;
 overflow: hidden;
 line-height: 35px;
 padding:0 5%;
 border-bottom:1px solid #EFEFEF;
 font-size:80%;
 }
  .nav ul li p span{
  float:right;
  width:auto;
  font-size:120%;
  color:#B5B5B7;
  }
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"charset="utf-8"></script>
 <script src="<%=request.getContextPath() %>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript">
$(function(){
})
function pid(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberrevise';
   	}
function mima(){
 location.href=window.BASEPATH + 'cybersecurity/cybercipher';
}
function shebei(){
 location.href=window.BASEPATH + 'cybersecurity/cyberfacilities';
}
</script>
<body>
<p style="height:40px;line-height: 40px;margin-left:5%;">
<a class="link-left" href="#side-menu" >
<span  class="icon-reorder icon-large"></span>
</a>
</p>
 <div class="nav" style="width:100%;height:auto;padding:0px 2%;border-radius:6px;margin:5px 0;">
  <ul>
   <li><p style="width:100%;">警员号<span>xxxxx</span></p></li>
   <li><p style="width:100%;">手机号<span onclick="pid()" class="pid">18731578693  ></span></p></li>
   <li><p style="width:100%;">修改密码<span onclick="mima()">></span></p></li>
   <li><p style="width:100%;">登录设备管理<span onclick="shebei()">></span></p></li>
  </ul>
 </div>
 <div class="fu" style="width:100%;height:100%;background: rgba(7,7,7,0.3);z-index:1111;position: fixed;top:0;display: none;">
   	 <div style="width:50%;height:100px;background: #fff;position: absolute;top:20%;left:25%;border-radius:6px;padding:5px 10px; ">
     <p style="border-bottom:1px solid #BABABA;line-height: 30px;font-weight: bold;">选择性别</p>
     <div style="width:auto;height:auto;overflow: hidden;line-height: 30px;">
     <span style="">男</span>
     <input style="margin:0;vertical-align: middle;float:right;margin-top:8.5px;" name="as" type="radio">
	 </div>
	 <div style="width:auto;height:auto;overflow: hidden;line-height: 30px;">
     <span style="">女</span>
     <input style="margin:0;vertical-align: middle;float:right;margin-top:8.5px;"  name="as" type="radio">
	 </div>
	 </div>
  </div>
</body>
</html>