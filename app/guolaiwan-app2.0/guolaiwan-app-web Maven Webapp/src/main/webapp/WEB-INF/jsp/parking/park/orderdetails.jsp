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
<title></title>
<style type="text/css">
html, body {
	height: 100%;
}
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
   $(function() {
   var vehicle = null;
   var state = null; //订单状态
    	var _uriYd = window.BASEPATH + '/smart/usere';
		$.post(_uriYd, null, function(data) {
			data = parseAjaxResult(data);
			var html = [];
			html.push('	<span>'+data.userNickname+'</span>');
			if(data.userPhone != null){
			html.push('<span>'+data.userPhone+'</span>');
			}
			$('.pic').append(html.join(''));
		});
		 var _url = window.BASEPATH + 'quit/query';
		 $.post(_url, null, function(data) {
			data = parseAjaxResult(data);
		   	var htm = [];
		   	vehicle = data.userHeadimg;
		    htm.push('<span>'+data.userHeadimg+'</span>     <span>'+data.userNickname+'</span> ');
			$('.pid').append(htm.join(''));  
		   
		var _ur = window.BASEPATH + 'vice/selePay';
	    var  parame= {};
		parame.uid = ${param.uid}; 
		parame.vehicle = vehicle; 
		 $.post(_ur, $.toJSON(parame), function(data) {
			data = parseAjaxResult(data);
			state = data.orderStatus;
		   	var htm = [];
				htm.push('<span">'+data.parkingName+'</span>');
				$('#marg').append(htm.join(''));  
				
		   	var html = [];
				html.push('<span style="color:#F99161;">'+data.parkingLayer+' '+data.parkingDistrict+' '+data.parkingNumber+'</span>');
				$('#mag1').append(html.join(''));  
	
		   	var html1 = [];
				html1.push('<span style="color:#F99161;">'+data.stoppingTime+' </span> 小时');
				$('#mag2').append(html1.join(''));  
	
		   	var html3 = [];
				html3.push('<span style="color:#F99161;">'+data.parkingCost+'</span> ');
				$('#mag3').append(html3.join(''));  
	
		   	var html4 = [];
				html4.push('<span>'+data.bookingTime+'</span> ');
				$('#mag4').append(html4.join(''));  
	
		   	var html5 = [];
				html5.push('<span>'+data.dueTime+'</span> ');
			   $('#mag5').append(html5.join(''));  
			var ht = '<img alt="" src="http://www.guolaiwan.net/file/'+data.img+'" style="width:60%;height:100%;">';
		    $('.img').html(ht);  
		    document.getElementById("span").innerHTML = data.number;//编号
		});
		   
	 });
    
	
	
		/*  var _url = window.BASEPATH + 'vice/long';
		 var  param= {};
		 param.attid =  ${param.uid};
		 $.post(_url, $.toJSON(param), function(data) {
		 data = parseAjaxResult(data);
		 var ht = '<img alt="" src="'+data.img+'" style="width:60%;height:100%;">';
		 $('.img').html(ht);  
		 
		 document.getElementById("span").innerHTML = data.number;//编号
		}); */
     
    
    
    
	   $(".btn").bind("click", function () {   
		  if(state!= null && state == 'PAYSUCCESS' ){
		      window.location.href="vice/merchant/cance?uid="+${param.uid}; 
		  }else{
		      $.toast("您的订单已不能退款哦!", "cancel");
		  }
		});  
   });  
  </script>
<body>

<div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="quit/merchant/smartparking" title="返回"> <返回首页 </a>
	  <span style="color:#ffffff;line-height:40px;font-size:18px;margin-left:-72px;">订单详情</span>
	</div> 
	<div class="header" style="width:100%;">
		<p class="pic" style="padding:20px 0 10px 15px;font-weight: bold;">
		</p>
		<p class="pid" style="margin:0px 0 10px 15px;font-weight: bold;">
		</p>
	</div>
	<div class="header_in"
		style="background-color: #EEEEEE;width:100%;height:auto;overflow: hidden;border-radius:10px;">

		<p id="marg" style="margin:0 0 10px 15px;font-size:14px;"></p>
		<p id="mag1" style="margin-left:15px;font-size:12px;color:#777777;">停车位：</p>
		<p id="mag2"
			style="margin-left:15px;font-size:12px;color:#777777;display: inline-block;">购买时长：
			</p>
		<p id="mag3"
			style="display: inline-block;font-size:14px;float:right;margin-right:20px;">总费用
			￥</p>
		<div class="header_on"
			style="border-bottom: solid 1px #959595;width:95%;margin:7px auto;"></div>
		<p id="mag4" style="font-size:12px;float:left;margin-left:15px">入场：</p>
		<p id="mag5" style="font-size:12px;float:right;margin-right:15px;;">离场：</p>
		<div class="img"
			style="text-align:center;height:40%;clear: both;padding-top:15px;">

		</div>
		<p style="text-align:center;font-size:14px;">
			订单编号：<span id="span">123456789632</span>
		</p>
		<p style="color:#777777;text-align:center;font-size:10px;">扫描二维码即可进入停车场</p>
		<button class="btn" id="refund"
			style="width:100%;height:35px;background-color:#D5D5D5;color:red;border:none;outline:none;">取消订单</button>
	</div>
	<div class="footer" style="padding-top:10%;">
		<p>温馨提示：</p>
		<ol style="font-size:12px;margin: 20px 15px;">
			<li>预订成功后<span style="color:red;">15 分钟内可免费取消，</span>约定入场时间内未入场，订单将自动计费。
			</li>
			<li>超出车费预定时间前30分钟，系统给车主推送提示离场信息。若车主超时间，则按照三倍费用自动计算。</li>
			<li>停车费以出停车场费用为标准。</li>
		</ol>
	</div>
	<p style="height:20px;"></p>
</body>
</html>
