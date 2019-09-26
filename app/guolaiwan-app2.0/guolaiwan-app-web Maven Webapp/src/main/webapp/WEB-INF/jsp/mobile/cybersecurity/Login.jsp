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
<title>登录</title>
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
	background:#FFF !important; 
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
.nav{
color:#2969A0;
text-align: center;
font-weight: bold;
letter-spacing:2px;  
}
.nav p{
font-size:140%;
}
.main{
width:100%;
margin:10px auto 0;
text-align: center;
position: relative;
}
.main input{
padding:0 0 0 15%;
width:60%;
height:35px;
border-radius:8px;
border:none;
outline:none;
border:1px solid #BDBDBD;
margin:5px 0;
line-height: 35px;
background: #fff;
}
input::-webkit-input-placeholder {
 font-size:80% !important;
}
.main img{
position: absolute;
height:23px;
}
.main-in{
width:100%;
padding:10px 20%;
text-align: center;
margin:0 auto;
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
	

  });
  function btns(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberhome';
   	}
	function zhuce(){
	 location.href=window.BASEPATH + 'cybersecurity/cyberregister';
	}
	function mima(){
	 location.href=window.BASEPATH + 'cybersecurity/cyberpassword';
	}
</script>
<body>
<div class="nav">
 <img style="width:26%;margin:10% 0 10px;" src="lib/imgs/logo.png">
 <p>唐山市旅游流动人口</p>
 <p>物流智慧服务管理平台</p>
</div>  
<div class="main">
<input placeholder="用户名" type="text" >
<input type="password" placeholder="密码" type="text">
<img style="left:22%;top:10px;" src="lib/imgs/id.png">
<img style="left:22%;top:55px;" src="lib/imgs/mi.png">
</div>
<div class="main-in">
<p style="line-height: 20px;float:left;"><input style="vertical-align:middle;margin:0;" type="checkbox">  <span style="color:#2969A0; font-size:80%;">  记住密码</span></p>
<p style="line-height: 20px;float:right;color:#A8A8A8; font-size:80%;"> <span onclick="zhuce()" style="">注册</span> &nbsp | &nbsp <span onclick="mima()" style="">忘记密码</span></p>
<input type="button"  onclick="btns()" value="登录"   style="color:#fff;background: #024D90;border-radius:14px;width:90%;padding:5px 0;border:none;outline: none;margin-top:10px;font-weight:bold;">
</div>

</body>
</html>