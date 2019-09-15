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
<title>答题首页</title>
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
	height:100%;
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	background: #fff;
}
* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
}
/* 页面样式 */
.header {
	height: 120px;
	line-height:50px;
	background:url("lib/images/navs.png") !important;
	background-size: 100% 120px!important;
}

.header .link-left {
	margin-left: 20px;
	margin-right: 10px;
	position: relative;
	z-index: 1;
	font-size:20px;
}
.main ul{
width:100%;
}
.main ul li{
box-shadow:2px 2px 5px #DBDBDB;
margin:10px auto;
width:100%;
height:auto;
text-align: left;
border:none;
outline: none;
background: #fff;
border-radius:8px;
border-bottom:1px solid #DBDBDB;
border-right:1px solid #DBDBDB;
border-left:1px solid #DBDBDB;
overflow: hidden;
padding:20px 5%;
line-height: 20px;
}
.main ul li img{
width:15%;
display: inline-block;
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
<body>
			<!-- 主页 -->
		<div class="header">
			<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
		</div>
	<div style="width:100%;height:auto;margin-top:-50px;">
		<div style="height:0;padding:7%;text-align: center;width:12%;line-height: 10%;margin:0 auto;overflow: hidden;position: relative;">
		 <!-- <p style="width:100%;position: absolute;left:50%;margin-left:-50%;font-size:20px;color:#000000;font-family:STKaiti;margin-top: 4px"><b>有奖问答</b></p > -->
		</div>
	</div>
	<div class="main" style="width:100%;height:auto;padding:0 5%;">
	  <ul class="alllist">
	   
	   
	  </ul>
	</div>	
	
	<div id="ruleshow" class="weui-popup__container popup-bottom">
		<div class="weui-popup__overlay"></div>
		<div  class="weui-popup__modal" >
			<div id="rules" style="padding: 20px;overflow-y:auto;text-indent: 2em;height:400px;">
			
			</div>
			<div  style="background:#FF3D00;height:60px;width:100%;border-bottom:1px solid  rgb(230, 230, 230);
			border-top:1px solid  rgb(230, 230, 230);position: fixed;bottom:0;" >
			<p  style="background:#F56938;height:100%;float:left;text-align:center;width:50%;
			line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;"  onclick="closewin()">
			<span style="font-size:14px;margin-left:5%;">取消答题</span>
			</p>
			<p  style="height:100%;float:right;text-align:center;width:50%;line-height: 60px;
			color:#fff;font-size:20px;font-weight:bold;display: inline-block;" onclick="gotoanswer()">
			<span style="font-size:14px;margin-left:5%;">开始答题</span>
			</p>
			</div>
			<p style="height:65px;"></p>
		</div>
	</div>	
	<p style="height:65px;"></p>
 </body>
<script>
var id;
$(function(){
	var url=window.BASEPATH+'admin/questionnaire/findallquestionnaire';
	$.post(url,null,function(data){
		 var html=[];
      for(var i=0;i<data.length;i++){
      	   html.push('<li id="'+data[i].id+'" onclick="getrole(this.id)">');
		   html.push('<img src="lib/images/responsess.png">');
		   html.push('<div style="display: inline-block;width:80%;vertical-align:middle;margin-left:5%;">');
		   html.push('<p style="word-break:break-word;font-size: 14px;">'+data[i].title+'asdlkashkd</p>');
		   html.push('<p style="word-break:break-word;font-size: 12px;color:#A4A4A4;">'+data[i].onthertitle+'</p>');
		   html.push('</div>');
		   html.push('</li>');
      }   
         $('.alllist').append(html.join(''));
	})
})

	function getrole(questionnaireId){
		id=questionnaireId;
		var url=window.BASEPATH+'admin/questionnaire/getquestionrole';
		$.post(url,{"questionnaireId":questionnaireId},function(data){
			$('#rules').html(data.questionnairerole);
			
			$("#ruleshow").popup();
		})
	}

	function gotoanswer(){
		$.closePopup();
		location.href=window.BASEPATH + 'admin/questionnaire/gotoanswer?id='+id;
	}
	
	function closewin(){
		$.closePopup();	
	}
</script>




</html>