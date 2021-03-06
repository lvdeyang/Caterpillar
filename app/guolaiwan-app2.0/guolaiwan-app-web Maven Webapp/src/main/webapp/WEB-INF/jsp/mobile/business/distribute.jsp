﻿<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
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
<title>公众号首页</title>
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
 .wenti ol li{
  margin:10px 0 10px 5%;
  font-weight: bold;
  color:black;
 }
 .wenti ol p{
  margin:10px 0 10px 5%;
  width: 90%;
  overflow:hidden;
  text-overflow:ellipsis; 
  white-space: nowrap;
 }
 .youxuan-in p{
  margin-left:3%;
 }
 #phone{
 position:absolute;
 right:60px !important;
 }
 .site{display:flex;flex-direction:column;min-height: 100vh;}
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/public.jsp"></jsp:include>
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="text/javascript">
$(function() {
	  window.BASEPATH = '<%=basePath%>';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };

	function guessyoulive(){       		 
		var url = window.BASEPATH + 'business/guessyoulive?id='+${merchantId};
		$.get(url, null, function(data){
			var html=[];
			for(var i=0;i<data.length;i++){	
				   var pingfen=(data[i].pingfen+46)/10;
				   if(pingfen>5)pingfen=5;
			       html.push("<div style='position: relative;overflow:hidden;width:90%;height:180px;line-height:180px;border:none;border-bottom:1px solid #C0C0C0;border-left:none;border-right:none;margin:0 auto;'>");
			       html.push("<img style='height:130px;width:45%;border-radius:6px;vertical-align: middle;display: inline-block;' src='"+data[i].weburl+data[i].url+"'/>");
			       html.push("<div class='youxuan-in' style='display: inline-block;'>");
			       html.push("<p style='position: absolute;top:-40px;font-size:14px;max-width:45%;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;'>"+data[i].ProductName+"</p>");
			       html.push("<p style='position: absolute;top:0px;font-size:12px;color:#C0C0C0;'><span style='color:#EC6D1E;'>"+pingfen+"分</span>   <span>"+data[i].number+"人来过</span></p>");
			      /*  html.push("<p style='position: absolute;top:40px;font-size:12px;color:#C0C0C0;'>739m</p>"); */
			       html.push("<p style='color:#EC6D1E;position: absolute;top:40px;font-size:14px;'>￥"+data[i].ProductPrice+"起</p>");
			       html.push("<a onclick='getorderinfos("+data[i].id+","+data[i].productModularCode+")'>");
			       html.push("<button style='position: absolute;right:3%;top:120px;line-height:25px;font-size:14px;width:20%;outline: none;border:none;height:25px;border-radius:6px;background:#FF4900;color:#fff;' >立即预订</button>");
			       html.push("</a>");
			       html.push("</div>");
			       html.push("</div>");     			       
			   }
			    $('.youxuan').append(html.join(''));	      			   
		});				  	    
    }	
 

   
   
</script>





<body class="site">
	<!-- 主页 -->
	<div class="header">
		<div class="wrapper">
	    <img style="width:20px;height:20px;position: absolute;left:2%;top:7.5px;z-index:11;" src="lib/images/homes.png" onclick="index()">
		<img style="width:25px;height:25px;position: absolute;right:2%;z-index:11;top:7.5px;" alt="" src="lib/images/personals.png" onclick="personal()">
	    <div class="header-content" >分销商</div>
	    </div>
	</div>
    <!-- 为你优选  -->
    <div class="youxuan"   style="width:96%;height:auto;margin:0 auto;background:#fff;position: relative;top:60px;overflow: hidden;">
        <p style="height:60px;line-height:60px;margin:0 5%;font-size:20px;border-bottom:1px solid #C0C0C0;">
        <span style="float:left;"><img style="width:30px;height:30px;" src="lib/images/youxuans.png"/>分销商列表</span> 
     
        </p>     
    </div> 
    <!-- 空 -->
    <div style="height:100px;"></div>
</body>
</html>