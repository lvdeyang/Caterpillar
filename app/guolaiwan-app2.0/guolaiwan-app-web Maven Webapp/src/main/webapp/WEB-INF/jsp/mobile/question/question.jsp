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
<title>常见问题</title>
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
	font-family: "微软雅黑" !important;
	height:auto;
	background:#F5F5F5 !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	
}
*{
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;	
}
.layui-tab-title{
 text-align: center;
 background: #fff;
 margin-top:-10px;
}
.layui-tab-bar{
display: none;
}
.layui-tab-title li{

margin:0 8%;

}
.header_on ul li{
width:98%;
border-bottom:1px solid #F5F5F5;
height:41px;
line-height: 41px;
}
.header_on ul li p{
width:85%;
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap;
text-indent: 1em;
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
layui.use('element', function(){
  var $ = layui.jquery
  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

});
$(function(){
  $(".footer").click(function(){
    $(".footer_in").fadeIn();
    $(".footer_on").fadeIn();
  });
   $(".footer_in").click(function(){
    $(".footer_in").fadeOut();
    $(".footer_on").fadeOut();
  });
})


</script>
<body>
 <div class="nav" style="height:130px;width:100%;background: #4BB259;line-height:130px;">
     <img style="height:75px;width:75px;display: inline-block; border-radius:8px;vertical-align: middle;margin-left:7%;" src="lib/images/logo.png">
     <h3 style="color:#fff;display: inline-block;margin:0 0 -5px 6%;width:50%;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">H i, <span>念</span></h3>
 </div>
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		  <ul class="layui-tab-title">
		    <li class="layui-this">商户</li>
		    <li>用户</li>
		    <li>交易</li> 
		  </ul>
		  <div class="layui-tab-content" style="height:auto;margin:5px 10px">
		  
		    <div class="layui-tab-item layui-show" >
		    <!--商户循环体 -->
			   <div class="header" style="margin:10px 0;height:120px;width:100%;overflow: hidden;background: #fff;border-radius:8px;">
				        <div class="header_in"  style="height:100%;width:30%;float:left;text-align: center;">
				         <img style="width:50px;height:50px;display:inline-block;margin-top:25px;"src="lib/images/logo.png">
				         <p style="height:25px;line-height: 25px;">订单查询<span style="color:#4BB259;">  ▶</span></p>
				        </div> 
				        <div class="header_on" style="height:100%;width:70%;float:right;overflow: hidden;">
				          <ul>
				           <li><p>查询不到订单怎么半</p></li>
				            <li><p>查询不到订单怎么半</p></li>
				             <li><p>查询不到订单怎么半</p></li>
				          </ul>
				        </div> 
			    </div>
			      
		    </div>
			    <!--用户循环体 -->
			    <div class="layui-tab-item">
			                内容2
			    </div>
			    <!--交易循环体 -->
			    <div class="layui-tab-item">
			              内容3
			    </div>
		  </div>
     </div>
     
  <div class="footer" style="z-index:11;text-align:left;width:100px;height:30px;background:#4BB259;border-radius:18px;padding:0px 5px;position:fixed;bottom:30px;right:-10px; ">
   <img style="height:15px;width:15px;margin:0 4px;" src="lib/images/telephone.png"><p style="color:#fff;line-height: 30px;display: inline-block;">电话咨询</p>
  </div> 
  
  <!-- 联系电话 -->
	<div class="footer_in" style="display:none;background-color:rgba(0,0,0,0.7);height:100%;width:100%;z-index:111111;position: fixed;top:0;left:0;">
	</div>  
  <div class="footer_on" style="display:none;z-index:111111111;height:120px;width:170px;padding:5px 5px;background: #fff;border-radius:4px;position: fixed;top:50%;left:50%;margin:-60px 0 0 -85px;">
  <p style="height:40px;width:100%;line-height: 40px;text-align: center;">电话咨询</p>
   <p style="color:#fff;width:75%;margin:5px 0;height:25px;border-radius:18px;background: #4BB259;line-height:25px;text-align: center;display: inline-block;"><a href="tel://0315-6681288">0315-6681288</a></p>
   <img style="width:10%;margin-left:5%;" src="lib/images/telephonelv.png">
   <p style="color:#fff;width:75%;margin:5px 0;height:25px;border-radius:18px;background: #4BB259;line-height:25px;text-align: center;display: inline-block;"><a href="tel://0315-6686299">0315-6686299</a></p>
   <img style="width:10%;margin-left:5%;" src="lib/images/telephonelv.png">
  </div>  
    
</body>

</html>