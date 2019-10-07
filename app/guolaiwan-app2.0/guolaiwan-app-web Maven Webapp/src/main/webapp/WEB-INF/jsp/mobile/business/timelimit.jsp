<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<html>

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
<meta name=”viewport” content=”width=device-width, initial-scale=1″ />



<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<title>秒杀专区</title>
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
	height:100%;
	background-color: #fff !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	font-size:100%;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	font-family: "微软雅黑";
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
.layui-tab-title{
text-align: center !important;
border-bottom:2px solid #AAAAAA !important;
}
.layui-tab-title li{
width:auto !important;
margin:0 15% !important;
padding:0 5px !important;
}
.layui-tab-brief>.layui-tab-title .layui-this{
color:#C7000A !important;
}
.layui-tab-brief>.layui-tab-more li.layui-this:after, .layui-tab-brief>.layui-tab-title .layui-this:after{
    border-bottom: 2px solid #C7000A !important;
}
.time span{
 background: #000000;
 color:#fff;
 margin:0 3px;
 padding:0 2px;
 border-radius:2px;
}
.layui-progress-text {
 line-height: 16px !important;
}
</style>

</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
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
 var intDiff = 500;
 window.setInterval(function () {
    var day = 0,
      hour = 0,
      minute = 0,
      second = 0; //时间默认值
    if (intDiff > 0) {
      day = Math.floor(intDiff / (60 * 60 * 24));
      hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
      minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
      second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
     if(hour<10){hour="0"+hour;}
	if(minute<10){minute="0"+minute;}
	if(second<10){second="0"+second;}
    $("#times").html(hour);
    $("#timess").html(minute);
    $("#timesss").html(second);
    intDiff--;
  }, 1000);
  
 $(function(){
 var html=[];
  for(var i=0;i<5;i++){
      html.push("<div style='width:28%;padding-bottom:28%;border-radius:6px;overflow: hidden; height:0;float:left;'>")
      html.push("<img style='width:100%;' src='lib/images/weixin.png'>")
      html.push("</div>")
      html.push("<div style='width:67%;padding-bottom:30%;overflow: hidden; height:0;float:left;border-bottom:2px solid #959595;position: relative;text-align: left;margin-left:4%;'>")
      html.push("<p style='position: absolute;top:10%;'>遵化市xxxxxxxx---/斤</p>")
      html.push("<p style='position: absolute;bottom:10%;'><span style='font-size:120%;color:#C7000A;'>￥7.00</span><span style='font-size:80%;text-decoration:line-through;margin-left:5%;'>￥8.00</span></p>")
      html.push("<button style='position: absolute;right:5%;bottom:10%;color:#fff;background: #D81E06;border:none;outline: none;border-radius:3px;font-size:80%;padding:2px 5px;'>马上抢 ></button>")
      html.push("<p style='position: absolute;top:32%;right:5%;color:#666666;font-size:70%;'>已抢<span>96</span>件</p>")
      <!-- 进度条 -->
      html.push("<div  class='layui-progress layui-progress-big' lay-showpercent='true' style='margin-top:15%;width:70%;background:#F599A0;height:15px;'>")
      html.push("<div class='layui-progress-bar' lay-percent='60%' style='background:#D81E06;height:15px;line-height: 15px;text-align: center;'></div>")
      html.push("</div>")
      html.push("</div>")
  }
   $('.main').append(html.join(''));		
  var htmls=[];
  for(var i=0;i<5;i++){
	  htmls.push("<div style='width:28%;padding-bottom:28%;border-radius:6px;overflow: hidden; height:0;float:left;'>")
      htmls.push("<img style='width:100%;' src='lib/images/weixin.png'>")
      htmls.push("</div>")
      htmls.push("<div style='width:67%;padding-bottom:30%;overflow: hidden; height:0;float:left;border-bottom:2px solid #959595;position: relative;text-align: left;margin-left:4%;'>")
      htmls.push("<p style='position: absolute;top:10%;'>遵化市xxxxxxxx---/斤</p>")
      htmls.push("<p style='position: absolute;bottom:10%;'><span style='font-size:120%;color:#C7000A;'>￥7.00</span><span style='font-size:80%;text-decoration:line-through;margin-left:5%;'>￥8.00</span></p>")
      htmls.push("<button style='position: absolute;right:5%;bottom:10%;color:#fff;background: #D81E06;border:none;outline: none;border-radius:3px;font-size:80%;padding:2px 5px;'>查看 ></button>")
      htmls.push("<p style='position: absolute;top:35%;left:0%;color:#FFA800;font-size:75%;'><span>限量20件</span>&nbsp&nbsp   | &nbsp&nbsp  <span>明天00：00开始</span></p>")
      htmls.push("</div>")
  }
   $('.main-in').append(htmls.join(''));		
 }) 
  	
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


<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="margin:0;">
  <ul class="layui-tab-title">
    <li class="layui-this">今日秒杀</li>
    <li>明日预告</li>
  </ul>
	  <div class="layui-tab-content" style="">
		    <div class="layui-tab-item layui-show" style="text-align: center;">
		     <p style="height:50px;line-height: 50px;"><img style="height:20px;margin-top:-5px;" src="lib/images/timelimit.png"><span style="margin:0 2%;color:#C7000A;font-size:120%;">正在秒杀</span></p>
		     <p class="time">距结束<span id="times">00</span>:<span id="timess">00</span>:<span id="timesss">00</span></p>
					   <div class="main" style="width:100%;height:420px;overflow-x: hidden;margin:20px 0;">
					    
					   
					    </div>
		    </div>
		    <div class="layui-tab-item" style="text-align: center;">
		      <p style="height:50px;line-height: 50px;"><img style="height:20px;margin-top:-5px;" src="lib/images/timelimits.png"><span style="margin:0 2%;color:#FFA800;font-size:120%;">明天00：00即将开抢</span></p>
		      <p class="time" style="">距开始<span id="times">00</span>:<span id="timess">00</span>:<span id="timesss">00</span></p>
				       <div class="main-in" style="width:100%;height:420px;overflow-x: hidden;margin:20px 0;">
				       
				     
				        </div>
		    </div>
	  </div> 
</div>   
</body>

</html>
