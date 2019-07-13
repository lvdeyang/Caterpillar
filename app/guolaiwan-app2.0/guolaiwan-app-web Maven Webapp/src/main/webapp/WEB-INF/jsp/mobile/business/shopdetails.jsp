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
<title>店铺详情</title>
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
	background:#E0E0E0 !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	
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
.xiangqing ul li{
   height:30px;
   line-height: 30px;
   font-weight:bold;
}
.xiangqing ul li p{
 margin:0;
}
.fangxing p{
 margin:0;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath()%>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script>

</script>
<script type="text/javascript">
	$(function() {
	getRecomment();
	getProduct();
	var pingfen=${pingfen}+46/10;
	if(pingfen>5)pingfen=5;
    $('.pingfen').html(pingfen+"分") ; 
	});


	function getRecomment(){
	      var _uriMerchantInfo = window.BASEPATH+'phoneApp/merchantInfo?merchantID=${merchantId}&userId=${userId}';
		
		$.get(_uriMerchantInfo, null, function(data){
			data = parseAjaxResult(data);
			merchantName = data.shopName + '-过来玩';
			merchantPic = 'http://<%=weburl%>/file/' + data.shopHeading;
			merchantUrl = window.location.href;
			if(data === -1) return;
			if(data){
			    var html=[];
			    var pics=data.shopMpic.split(',');
				for(var i=0; i<pics.length; i++){
					var str = pics[i].split('.');
					if(str[3]!="mp4"&&str[3]!="MP4"){ 
					html.push('<div class="swiper-slide" style="height:200px;"><img class="exampleImg" style="height:200px;" id="imgTest" src="'+pics[i]+'" alt=""></div>');
					}else{
					html.push('<div class="swiper-slide" style="height:200px;"><video class="exampleImg" style="height:200px;width:100%;" src="'+pics[i]+'" controls="controls" ></div>');
					}
				}
			    $('.header-content').html(data.shopName);
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
			    }
			    });
	  }
	  
	function getProduct(){
	    var _uricoms = window.BASEPATH + '/business/getproduct';	
	     $.get(_uricoms, {"merchantId":${merchantId}}, function(data){
	         var html=[];
	         for(var i=0;i<data.length;i++){
	         			if(data[i].productClassCode=="1"||data[i].productClassCode=="0014"){
				     	html.push('<div style="width:90%;height:auto;background:#BCBCBC;margin:10px auto;border-radius:10px;">');
				        html.push('<p style="height:40px;line-height: 40px;"><span style="float:left;margin-left:5px;">'+data[i].productName+'</span>');
				        html.push('<span style="float:right;margin-right:5px;color:#EB6E1E;font-size:18px;font-weight:bold;">￥'+data[i].productPrice+'</span></p>');
				    	html.push('<p style="height:40px;line-height: 40px;"><span style="float:left;margin-left:5px;">月销100+</span>');
				    	html.push('<span onclick="buyproduct(this.id)" id="'+data[i].id+'" style="float:right;margin-right:5px;color:#fff;font-size:18px;font-weight:bold;background:#EB6E1E;height:30px;line-height:30px;border-radius:10px;padding:0px 10px;">预订</span></p>');
					    html.push('</div>');
					    }
		     		}
			 $('.productlist').append(html.join(''));
		     	})
		 }
		 
	function buyproduct(id){
		location.href=window.BASEPATH + 'business/buyproduct?productId='+id;
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
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
	</div>

	  <div class="xiangqing" style="width:94%;height:auto;background:#fff;margin:10px auto;border-radius:10px;overflow: hidden;position: relative;top:-60px;z-index:111;">
	   <ul style="margin:0 0 20px 6%;">
	    <li><p>${merchant.shopName}</p></li>
	    <li><p><span class="pingfen" style="color:#EB6E1E;font-size:16px;"></span><span style="font-size:12px;margin-left:10px;">超棒</span></p></li>
	    <li><p>距离您<span>1000</span>千米</p></li>
	    <li><p>联系电话：<span>${merchant.shopTel}</span></p></li>
	   </ul> 
	  </div>
	  <div class="fangxing" style="width:100%;height:auto;background:#fff;border-radius:10px;position: relative;top:-60px;padding:0 0 30px 0;">
	    <p style="font-size:18px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;border-bottom:1px solid #BCBCBC;"><img style="width:30px;height:30px;" alt="" src="lib/images/goupiaoss.png">房型选择</p>
	    <div class="productlist">
	    </div>
	     
	    

	  </div> 
	  <div class="fangxing" style="width:100%;height:auto;background:#fff;border-radius:10px;position: relative;top:-50px;padding:0 0 30px 0;">
	    <p style="font-size:18px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;border-bottom:1px solid #BCBCBC;"><img style="width:30px;height:30px;" alt="" src="lib/images/goupiaoss.png">订房必读</p>
	     
	     <p style="display: inline-block;height:50px;line-height: 50px;font-size:12px;margin-left:9%;">
	     <img style="width:25px;height:25px;" alt="" src="lib/images/shizhong.png">
	     <span >离店时间：12：00以前</span>
	     </p>
	     <p style="font-size:12px;margin-left:10%;">预订电话：${merchant.shopTel}</p>
	   
	  </div>
	   
	   <div class="dianping" style="width:100%;height:auto;background:#fff;border-radius:10px;position: relative;top:-40px;padding:0 0 30px 0;">
	    <p style="font-size:18px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;border-bottom:1px solid #BCBCBC;"><img style="width:30px;height:30px;" alt="" src="lib/images/goupiaoss.png">酒店点评</p>
	    <p style="height:50px;width:90%;margin:0 auto;line-height: 50px;border-bottom:1px solid #BCBCBC;"><span class="pingfen" style="font-size:16px;font-weight:bold;color:#EB6E1E;height:50px;"></span><span style="padding:0 10px;color:#E0E0E0;">|</span><span style="color:black;background:#F3EA29;border-radius:12px;padding:0 5px;">服务周到</span><span style="color:black;background:#F3EA29;border-radius:12px;padding:0 5px;">性价比高</span></p>
	     <div style="height:auto;">
	      <p style="font-size:14px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;">
	      <img style="width:35px;height:35px;border-radius:50%;" src="lib/images/logo.png">
	      <span>想念</span><span style="padding:0 10px;color:#E0E0E0;">|</span><span style="color:#fff;background:#FAB526;border-radius:12px;padding:0 5px;">老用户</span>
	      </p>
          <p style="padding:0 8%;">离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间</p>
	     </div>
	      <div style="height:auto;">
	      <p style="font-size:14px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;">
	      <img style="width:35px;height:35px;border-radius:50%;" src="lib/images/logo.png">
	      <span>想念</span><span style="padding:0 10px;color:#E0E0E0;">|</span><span style="color:#fff;background:#FAB526;border-radius:12px;padding:0 5px;">老用户</span>
	      </p>
          <p style="padding:0 8%;">离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间离店时间</p>
	     </div>
	      
	  </div>



</body>
 




</html>