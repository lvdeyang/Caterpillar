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
<title>我的标记</title>
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
 .main_in{
 width:92%;
 height:auto;
 box-shadow:1px 1px 4px 0px #7DA2C2;
 margin:5px auto 0;
 border-radius:5px;
 padding:0 3%;
 position: relative;
 background: #fff;
 font-size:90%;
 }
.weui-cells_checkbox .weui-check:checked + .weui-icon-checked:before {
color: #0E81E4!important;
}
.weui-cells{
display: none;
}
input::-webkit-input-placeholder {
 font-size:80%;
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
$(".sou").click(function(){
 $(".homes").hide();
  $(".nav_in").show();
})
$(".manage").click(function(){
 $(".weui-cells").toggle();
 $(".footer_in").toggle();
})
$(".false").click(function(){
 $(".nav_in").hide();
  $(".homes").show();
})

})

</script>
<body>
<div class="homes">
<div style="width:100%;line-height: 40px;border-bottom:1px solid #B6B6B6;z-index:1111111111;overflow: hidden;padding:0 3%;position: fixed;top:0;background: #fff;">
 <a class="link-left" href="#side-menu">
<span  class="icon-reorder icon-large"></span>
</a>
 <p style="width:auto;line-height: 40px;float:right;"><img class="sou" style="width:20px;margin:0 5px;" src="lib/images/sousuo.png"><span class="manage">管理</span></p>
</div>
<p style="height:40px;"></p>
<div style="width:100%;height:100%;background: #EFEFEF;padding:10px 3%;overflow-y: auto;"> 
 <div class="main_in" style="">
   <p style="text-align: center;line-height: 35px;font-size:150%;">客运公司：<span>遵化汽车站</span></p>
   <div style="width:20%;display: inline-block;float: left;">
   <img style="width:60px;height:60px;border-radius:6px;margin:0 5%;" src="lib/imgs/zhengjian.png">
   </div>
   <div style="width:78%;height:auto;display: inline-block;">
    <ul style="line-height: 25px;">
     <li><p>乘车人姓名：<span>xxx</span></p></li>
     <li><p>乘车人身份证号：<span>130281xxxxxxxxxx</span></p></li>
     <li><p>购票时间：<span>2019年9月20日，10：00</span></p></li>
     <li><p>发车时间：<span>2019年9月20日，10：20</span></p></li>
     <li><p>起点：<span>遵化</span>终点：<span>唐山</span></p></li>
     <li><p>是否携带物品：<span><input style="vertical-align: middle;margin:0;" class="btn_true" checked="checked" disabled="disabled" type="checkbox">是</span><span style="margin-left:10px;"><input style="vertical-align: middle;margin:0;" disabled="disabled"  type="checkbox">否</span></p></li>
     <li><p>携带物品照片：<span><img style="width:35px;" src="lib/imgs/captcha.png"></span><span><img style="width:35px;" src="lib/imgs/captcha.png"></span></p></li>
     <li><p>位置：<span>河北省xxxxx</span></p></li>
    </ul>
   </div>
   <img style="position: absolute;width:25px;top:5px;right:2%;" src="lib/imgs/yujing_red.gif">
		  <div  style="position: absolute;left:-8.7%;top:50%;margin-top:-11.2px;background: rgba(0,0,0,0);z-index:1111;"
		class="weui-cells weui-cells_checkbox">
		<label class="weui-cell weui-check__label" style="padding:0;margin:0;width:auto;"
			<div class="weui-cell__hd" >
				<input type="checkbox" 
					class="weui-check" > <i
					class="weui-icon-checked"></i>
			</div>
			<div class="weui-cell__bd">
				<p></p>
			</div>
		</label>
		   </div>
		    <div class="main_in" style="">
   <p style="text-align: center;line-height: 35px;font-size:150%;">客运公司：<span>遵化汽车站</span></p>
   <div style="width:20%;display: inline-block;float: left;">
   <img style="width:60px;height:60px;border-radius:6px;margin:0 5%;" src="lib/imgs/zhengjian.png">
   </div>
   <div style="width:78%;height:auto;display: inline-block;">
    <ul style="line-height: 25px;">
     <li><p>乘车人姓名：<span>xxx</span></p></li>
     <li><p>乘车人身份证号：<span>130281xxxxxxxxxx</span></p></li>
     <li><p>购票时间：<span>2019年9月20日，10：00</span></p></li>
     <li><p>发车时间：<span>2019年9月20日，10：20</span></p></li>
     <li><p>起点：<span>遵化</span>终点：<span>唐山</span></p></li>
     <li><p>是否携带物品：<span><input style="vertical-align: middle;margin:0;" class="btn_true" checked="checked" disabled="disabled" type="checkbox">是</span><span style="margin-left:10px;"><input style="vertical-align: middle;margin:0;" disabled="disabled"  type="checkbox">否</span></p></li>
     <li><p>携带物品照片：<span><img style="width:35px;" src="lib/imgs/captcha.png"></span><span><img style="width:35px;" src="lib/imgs/captcha.png"></span></p></li>
     <li><p>位置：<span>河北省xxxxx</span></p></li>
    </ul>
   </div>
   <img style="position: absolute;width:25px;top:5px;right:2%;" src="lib/imgs/yujing_red.gif">
		  <div  style="position: absolute;left:-8.7%;top:50%;margin-top:-11.2px;background: rgba(0,0,0,0);z-index:1111;"
		class="weui-cells weui-cells_checkbox">
		<label class="weui-cell weui-check__label" style="padding:0;margin:0;width:auto;"
			<div class="weui-cell__hd" >
				<input type="checkbox" 
					class="weui-check" > <i
					class="weui-icon-checked"></i>
			</div>
			<div class="weui-cell__bd">
				<p></p>
			</div>
		</label>
		   </div>
		    <div class="main_in" style="">
   <p style="text-align: center;line-height: 35px;font-size:150%;">客运公司：<span>遵化汽车站</span></p>
   <div style="width:20%;display: inline-block;float: left;">
   <img style="width:60px;height:60px;border-radius:6px;margin:0 5%;" src="lib/imgs/zhengjian.png">
   </div>
   <div style="width:78%;height:auto;display: inline-block;">
    <ul style="line-height: 25px;">
     <li><p>乘车人姓名：<span>xxx</span></p></li>
     <li><p>乘车人身份证号：<span>130281xxxxxxxxxx</span></p></li>
     <li><p>购票时间：<span>2019年9月20日，10：00</span></p></li>
     <li><p>发车时间：<span>2019年9月20日，10：20</span></p></li>
     <li><p>起点：<span>遵化</span>终点：<span>唐山</span></p></li>
     <li><p>是否携带物品：<span><input style="vertical-align: middle;margin:0;" class="btn_true" checked="checked" disabled="disabled" type="checkbox">是</span><span style="margin-left:10px;"><input style="vertical-align: middle;margin:0;" disabled="disabled"  type="checkbox">否</span></p></li>
     <li><p>携带物品照片：<span><img style="width:35px;" src="lib/imgs/captcha.png"></span><span><img style="width:35px;" src="lib/imgs/captcha.png"></span></p></li>
     <li><p>位置：<span>河北省xxxxx</span></p></li>
    </ul>
   </div>
   <img style="position: absolute;width:25px;top:5px;right:2%;" src="lib/imgs/yujing_red.gif">
		  <div  style="position: absolute;left:-8.7%;top:50%;margin-top:-11.2px;background: rgba(0,0,0,0);z-index:1111;"
		class="weui-cells weui-cells_checkbox">
		<label class="weui-cell weui-check__label" style="padding:0;margin:0;width:auto;"
			<div class="weui-cell__hd" >
				<input type="checkbox" 
					class="weui-check" > <i
					class="weui-icon-checked"></i>
			</div>
			<div class="weui-cell__bd">
				<p></p>
			</div>
		</label>
		   </div>
		   
		   
			
  </div>
  	   <div class="footer_in" style="height:50px; width:100%;background: #fff;position: fixed;bottom:0;display: none;z-index:1111">
		       <div  style="background: rgba(0,0,0,0);z-index:1111;margin-top:13.5px;"
				class="weui-cells weui-cells_checkbox">
				<label class="weui-cell weui-check__label" style="padding:0;margin:0;width:auto;border:none;outline:none;"
					<div class="weui-cell__hd" >
						<input type="checkbox" 
							class="weui-check" > <i
							class="weui-icon-checked"></i>
							<span style="color:#B5B5B7;font-size:80%;">全选<span>
					</div>
					<div class="weui-cell__bd">
						<p></p>
					</div>
				</label>
				<button style="z-index:11111;background-image: -webkit-linear-gradient(left,#0E83E7,#063D8D );float:right;margin:-22px 20px 0 0;color:#fff;font-size:80%;border:none;outline:none;border-radius:18px;padding:2px 20px ">删除</button>   
			  </div>
</div> 
  <p style="height:50px;"></p>
  
  <div class="nav_in" style="width:100%;height:50px;position: fixed;top:0;background: #fff;padding:0 5%;line-height: 50px;display: none;">
    <input placeholder="人名/景区/住宿/物流/客运/集会" style="float:left;margin-top:10px;width:88%;height:30px;border-radius:8px;outline:none;border:none;border:1px solid #B5B5B7;padding:0 0 0 10%;line-height: 30px;">
    <span class="false" style="color:#B5B5B7;float:right;">取消</span>
    <img style="height:20px;position: absolute;left:7%;top:15px;" src="lib/images/sousuo.png">
  </div>
</body>
</html>