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
<title>采摘商家列表</title>
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

.gotop {
   position: fixed;
   right: 20px;
   bottom: 50px;
   display: block;
   width: 50px;
   height: 50px;
   opacity: 0.8;
   z-index:111111;
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
	$(function() {
	   
		   getRecomment();
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
</script>
<script>
$(function(){
    $(window).scroll(function(){
        var aa = $(window).scrollTop(); //当前滚动条滚动的距离
        var bb = $(window).height();//浏览器当前窗口可视区域高度
        var cc = $(document).height(); //浏览器当前窗口文档的高度 
      
        if(cc <= aa+bb){
            $(".huodong").append($(".huodongs").clone()); 
        }
    })
  })
 /*返回顶部  */
  $(function(){
  getAllMerchant();

	$(window).scroll(function(){
		if($(window).scrollTop()>100){
			$(".gotop").fadeIn(400);	
		}
		else{
			$(".gotop").fadeOut(400);
		}
	});
	$(".gotop").click(function(event){
        event.preventDefault();
		$('html,body').animate({'scrollTop':0},500);
        return false;
	});
}); 


	function getAllMerchant(){
			var url="<%=basePath%>business/getmerchant1";
           $.post(url,{"merchantId":${merchantId},"code":"采摘"},function(data){
           	var html=[];
           	  
           	if(data.merlist.length==0){
           		html.push('<p style="text-align: center;position: fixed;bottom:5px;left:50%;margin-left:-28px;color:#858585;">暂无数据</p>');
           	}else{
				for(var i=0; i<data.merlist.length; i++){
					var pics=data.merlist[i].shopMpic.split(',');
			  		 var pingfen=(data.pingfens[i]+46)/10;
				  		 if(pingfen>5)pingfen=5;
					   html.push('<a href="javascript:void(0);" onclick="gotocomdlist('+data.merlist[i].id+')"><div class="main" style="width:95%;height:auto;border-radius:10px;padding:10px 1%;background:#fff; margin:10px auto;overflow: hidden;">');
					   html.push('<img style="width:48%;height:160px;float:left;border-radius:8px;" src="http://www.guolaiwan.net/file'+data.merlist[i].shopHeading+'"/>');
					   html.push('<div style="width:48%;height:160px;float: right;">');
					   html.push('<img style="width:100%;height:80px;border-radius:8px;" src="http://www.guolaiwan.net/file'+pics[0]+'"/>');
					   html.push('<img style="width:100%;height:80px;border-radius:8px;" src="http://www.guolaiwan.net/file'+pics[1]+'"/>');
				       html.push('</div>');
				       html.push('<p style="height:20px;line-height: 20px;clear: both;">'+data.merlist[i].shopName+'</p>');
				       html.push('<p style="height:20px;line-height: 20px;clear: both;"><span>现摘现吃，健康营养</span><span style="float:right;font-size:12px;"><span style="color:#EA6E1E;">'+pingfen+'分</span>好评率99%</span></p>');
					   html.push('</div>');
					   html.push('</div></a>');
					}
			}
			
	    	$('.huodong').append(html.join(''));
	    		
           })
           setTimeout(function() {
            	if($(".huodong").height() == 0){
				$("body").append('<p style="text-align: center;position: fixed;top:270px;left:50%;margin-left:-28px;color:#858585;">暂无商品</p>');
				  } 	
           }, 1000)
         
	}
	
  function gotocomdlist(id){
        location.href=window.BASEPATH + 'business/gotocomdlist?merchantId='+id;
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
	
	<!-- 采摘商户列表-->
   	<div class="huodong">
   	</div>
   <!-- 置顶 -->
    <div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
</body>





</html>