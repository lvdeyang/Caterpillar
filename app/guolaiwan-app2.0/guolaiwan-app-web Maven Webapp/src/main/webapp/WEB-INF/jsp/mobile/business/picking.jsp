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
<title>采摘</title>
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

 .youxuan-in p{
  margin-left:3%;
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
.fenlei img{
 width:50px;
 height:50px;
 margin:0 15%;
}
.fenlei p span{

 margin:0 15%;
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
       getAllProduct();
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
	  
	  function getAllProduct(){
		var url="<%=basePath%>business/getallproduct";
            $.post(url,{"merchantId":${merchantId},"type":"006"},function(data){
            	var html=[];
            	if(data.length==0){
            			html.push('<p style="text-align: center;bottom:5px;left:50%;color:#858585;">暂无推荐商品</p>');
            	}else{
					for(var i=0; i<data.length; i++){
						html.push('<a onclick="gotodetailspage('+data[i].id+')"> <div style="width:48%;height:auto;overflow: hidden;border-radius:10px;text-align:center;float:left;margin:5px 0 0 1.5%;">');
					    html.push('<img style="width:100%;height:120px;border-radius:10px;" src="http://www.guolaiwan.net/file'+data[i].productShowPic+'"/>');
					    html.push('<p style="margin:0 ;height:30px;line-height: 30px;text-align:left;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:85%;">'+data[i].productName+'</p>');
					    html.push('<p style="margin:0 ;height:20px;line-height: 20px;color:#EA6C1B;text-align:left;">￥<span>'+data[i].productPrice+'</span><span style="text-decoration: line-

through;color:#787878;margin-left:10px;font-size:12px;">￥'+data[i].productOldPrice+'</span></p>');
					    html.push('<button style="border-radius:10px;font-size:12px;color:#fff;background:#EA6C1B;padding:0px 25px;border:none;outline:none;margin:0 auto;">立即购买</button>');
					    html.push('</div></a>');
						}
				}
		    	$('.main').append(html.join(''));
            })
		}
</script>
<script>

 /*返回顶部  */
  $(function(){
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

   function gotorecommend(){
   		location.href=window.BASEPATH + 'business/gotorecommend?merchantId=${merchantId}';
   }
  
   function gotopickinglist(){
   		location.href=window.BASEPATH + 'business/gotopickinglist?merchantId=${merchantId}';
   }
   
   function gotodetailspage(id){
   		location.href=window.BASEPATH + 'business/gotodetailspage?productId='+id;
   }
    
   function searchproduct(){
   		var name=$('.search').val();
   		var url=window.BASEPATH + 'business/searchproduct'
   		$.post(url,{"name":name,"type":"006"},function(data){
   				$('.main').empty();
   				var html=[];
            	if(data.length==0){
            			html.push('<p style="text-align: center;bottom:5px;left:50%;color:#858585;">暂无推荐商品</p>');
            	}else{
					for(var i=0; i<data.length; i++){
						html.push('<a onclick="gotodetailspage('+data[i].id+')"> <div style="width:48%;height:auto;overflow: hidden;border-radius:10px;text-align:center;float:left;margin:5px 0 0 1.5%;">');
					    html.push('<img style="width:100%;height:120px;border-radius:10px;" src="http://www.guolaiwan.net/file'+data[i].productShowPic+'"/>');
					    html.push('<p style="margin:0 ;height:30px;line-height: 30px;text-align:left;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:85%;">'+data[i].productName+'</p>');
					    html.push('<p style="margin:0 ;height:20px;line-height: 20px;color:#EA6C1B;text-align:left;">￥<span>'+data[i].productPrice+'</span><span style="text-decoration: line-

through;color:#787878;margin-left:10px;font-size:12px;">￥'+data[i].productOldPrice+'</span></p>');
					    html.push('<button style="border-radius:10px;font-size:12px;color:#fff;background:#EA6C1B;padding:0px 25px;border:none;outline:none;margin:0 auto;">立即购买</button>');
					    html.push('</div></a>');
						}
				}
		    	$('.main').append(html.join(''));
   		})
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
		<!-- 搜索  -->
		  <div style="height:40px;width:100%;line-height: 40px;text-align: center;background: #fff;position: relative;">
		   <input placeholder="搜索" class="search" style="padding:0 15%;width:80%;height:30px;border-radius:18px;outline: none;border:none;background:#E0E0E0;text-align: center; " type="text">
		   <img style="width:20px;height:20px;position: absolute;right:20%;top:10px;" onclick="searchproduct()" src="lib/images/sousuo.png"/>
		  </div>
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
	</div>
	
	<!-- 采摘-->
	<div class="fenlei" style="width:100%;height:80px; text-align: center;margin:0 auto;background:#fff;">
	   <img onclick="gotorecommend()" src="lib/images/caizhaiss.png"/>
	   <img onclick="gotopickinglist()" src="lib/images/shuguos.png"/>
	  <p style=""><span style="color:#ED8036;" onclick="gotorecommend()">采摘活动</span> <span style="color:#6CA640;" onclick="gotopickinglist()">蔬果采摘</span></p>
	</div>
	 <p style="text-align: center;color:#EC7218;font-size:16px;height:35px;line-height:35px;margin:0;font-weight: bold;">——<span style="margin:0 3%;">您可能选择</span>——</p>
    <div class="main">
	</div>	
   <!-- 置顶 -->
    <div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
</body>





</html>