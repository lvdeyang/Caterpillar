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
<title>活动</title>
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
	min-height:auto;
	position: relative;
	-webkit-text-size-adjust: none;
	background-color:#fff !important;
	text-decoration: none !important;
    font-size:100%;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
}
/* 页面样式 */
.header {
	height: 40px;
	line-height: 40px;
	background-color: #18b4ed;
	color: #fff;
	border-bottom: 1px solid #bababa;
}

.header .link-left {
	margin-left: 10px;
	margin-right: 10px;
	position: relative;
	z-index: 1;
}

.header-content {
	height: auto;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
}
.main ul li{
width:85%;
height:100px;
margin:15px auto;
border-radius:8px;
position: relative;

}
.main-in{
height:60px;
width:60px;
border-radius:50%;
background: #fff;
line-height: 60px;
position: absolute;
top:20px;
left:3%;
}
.main-on{
position: absolute;
line-height: 25px;
width:auto;
left:25%;
text-align: left;
font-size:90%;
color:#fff;
top:10px;
}
.main-on p{
margin:0 !important;
}
.main-on p:first-child{
font-weight: bold;
font-size:120%;
}
.main-on p:last-child{
width:auto;
background: #fff;
display: inline-block;
border-radius:4px;
height:20px;
line-height: 20px;
padding:0 4px;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="text/javascript">
 function manjian(){
       location.href=window.BASEPATH + '/product/activity/Public';
   }
 function lingquan(){
     location.href=window.BASEPATH + '/product/activity/Shotgun';
 }
 function zhekou(){
     location.href=window.BASEPATH + '/product/activity/Discount';
 }
 function miaosha(){
     location.href=window.BASEPATH + '/product/activity/Timelimit';
 }
</script>
<body>
			<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
	   </div>
  <div class="nav" style="width:100%;overflow: hidden;">
	   <img style="width:100%;" src="lib/images/activts.jpg"
	</div>
	<div class="main" style="height:auto;width:100%;">
	  <ul style="height:auto;width:100%;text-align: center;">
	    <li style="background: linear-gradient(to right,#FD761F,#FF446E);">
	      <div class="main-in">
	        <img style="width:60%;" src="lib/images/Less.png">
	      </div>
	      <div class="main-on">
	        <p>超值满减</p>
	        <p>下单满500元减100元，满1000减200元</p>
	        <p onclick="manjian()"style="color:#FD761F;">立即参与 >></p>
	      </div>
	    </li>
	    <li style="background: linear-gradient(to right,#8060FA,#4C76FF);">
	     <div class="main-in">
	        <img style="width:60%;" src="lib/images/Shotgun.png">
	      </div>
	      <div class="main-on">
	        <p>领券专区</p>
	        <p>超值优惠券等你来拿，下单更优惠</p>
	        <p onclick="lingquan()"style="color:#8060FA;">立即领取 >></p>
	      </div>
	    </li>
	    <li style="background: linear-gradient(to right,#FF43F2,#FD5D20);">
	     <div class="main-in">
	        <img style="width:60%;" src="lib/images/discount.png">
	      </div>
        <div class="main-on">
        <p>折扣专区</p>
        <p>1件8折，2件7折，最低5折起</p>
        <p onclick="zhekou()"style="color:#FF43F2;">立即参与 >></p>
        </div>
	    </li>
	    <li style="background: linear-gradient(to right,#4770FE,#21D2B4);">
	       <div class="main-in">
	        <img style="width:60%;" src="lib/images/Voucher.png">
	      </div>
	       <div class="main-on">
	       <p>秒杀专区</p>
	       <p>限时秒杀，优惠享不停</p>
	       <p onclick="miaosha()"style="color:#4770FE;">立即抢购 >></p>
	       </div>
	    </li>
	  </ul>
	</div>
</body>




</html>