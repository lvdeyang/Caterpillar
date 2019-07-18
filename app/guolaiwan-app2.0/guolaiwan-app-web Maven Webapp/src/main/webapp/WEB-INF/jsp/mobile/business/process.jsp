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

<title>拼团流程</title>

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
	background-color: #EEEEEE !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	background-color: #fbfbfb;
	text-decoration: none !important;
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
	height:auto;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
}

  .swiper-container {
    width: 100%;
    padding:0;
    margin:0;
    height:200px;
  } 

  .swiper-container img {
    display: block;
    width: 100%;
  }
    
.weui-navbar{
 display: none !important;
}
  .inp::-webkit-input-placeholder{
        text-align: center;
}  
 .header_in ul li img{
   width:8%;
   height:30px;
   float:left;
   margin:0 6%;

 }
 .header_in ul li span{
 font-size:14px;
 	width:10%;
   float:left;
   margin:10px 5% 25px 5%;
   
 }
 .header_in ul li{
  text-align: center;
 }
  .header_on p{
    line-height: 50px;
    display: inline-block;
    font-size:12px;
    color:#C0C0C0;
    
 }
  .header_on img{
   height:20px;
 }
 .header_on span{
  margin-left:5px;
 }
.layui-timeline-axis{
    background: #E96108 !important;
    color:#fff !important;

}
.layui-timeline-title{
 margin:0 !important;
 font-size:16px !important;
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
<script>

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
	<p style="height:40px;line-height: 40px;color:#E96108;font-weight: bold;padding:0 5%;">拼团流程</p>
    <div>
    <ul class="layui-timeline">
  <li class="layui-timeline-item">
    <i class="layui-timeline-axis">1</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">选中心仪的商品</h3>
      <div style="width:90%;height:150px;background: #fff;">
       
      </div>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-timeline-axis">2</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">选中开团或拼团，确认支付</h3>
      <div style="width:90%;height:150px;background: #fff;">
       
      </div>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-timeline-axis">3</i>
    <div class="layui-timeline-content layui-text">
      <h3 class="layui-timeline-title">达到人数拼团成功</h3>
      <div style="width:90%;height:150px;background: #fff;">
       
      </div>
      <p style="width:90%;">拼团成功：从开团规定的<span>x</span>小时内找到相应的人数的好友参团，即拼团成功，等待卖家发货。</p>
       <div style="width:90%;height:150px;background: #fff;">
       
      </div>
      <p style="width:90%;">拼团失败：<br>A:超过拼团所限制的<span>x</span>小时后，未达到相应拼团认识的团，则该团失败。</p>
      <p style="width:90%;">B:在拼团有效期<span>x</span>小时内，商品已经提前售罄，但拼团人数还未到达相应要求，则该团失败。</p>
    </div>
  </li>
  <li class="layui-timeline-item">
  
  </li>
</ul>  
    </div>
    
   <div style="width:100%;height:auto;background: #F2C81A;">
   <p style="height:40px;line-height: 40px;background: #fff;font-weight: bold;font-size:20px;text-align: center;">拼团规则</p> 
   <ol style="width:100%;height:auto;padding:10px 5%;font-size:16px;font-weight: bold;border:10px solid black;color:black;">
    <li>1. 活动满<span>2</span>人起生效，自动发起活动1小时内未达到参团人数的，该团自动取消</li>
     <li>2. 对于未成团，但已付款的用户，会通过微信转账方式，退回该笔支付款项</li>
      <li>3. 对于以不正当方式参与活动的用户，包括但不限恶意套现，恶意下单，利用程序	漏洞等，“过来玩”有权在不事先通知的情况下取消其参与活动的资格</li>
       <li>4. 在法律允许的范围内，“过来玩”可能对活动的规则/条款做出适当的修改/调整，最终解释权归“过来玩”所有</li>
   </ol>
   </div>
</body>
</html>