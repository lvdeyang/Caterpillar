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
<title>购票商品详情</title>
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
	background:#EEEEEE !important; 
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
	height: 100%;
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
.xiangqing img{
text-align: center;
margin:3px 5%;
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
	    getparticulars();				
   });
//轮播图以及下面的图片   	
function getRecomment(){
     var _uriRecomment = window.BASEPATH + 'phoneApp/productInfo?productId=${productId}&userId=${userId}';
     $.get(_uriRecomment, null, function(data){
	   data = parseAjaxResult(data);
	 if(data === -1) return;
	   if(data){
	       var html=[];
	       var html1=[];
	       var pics=data.product.productMorePic.split(',');
	    for(var i=0; i<pics.length; i++){
	       html.push('<div style="height:200px;" class="swiper-slide"><img style="height:200px" src="'+pics[i]+'" alt=""/></div>');
	       if(i<2){	      
	           html1.push('<img style="height:200px" src="'+pics[i+1]+'"/></div>');
	       }	   
	  }
	   $('.swiper-wrapper').append(html.join(''));
	   $('#img1').append(html1.join(''));
	   $(".swiper-container").swiper({
	           loop: true,
	           autoplay: 3000
	      });
      }
  });
}
function getparticulars(){
     var _uricoms = window.BASEPATH + '/business/getparticulars?productId='+${productId};	
     $.get(_uricoms, null, function(data){
         var html=[];
		 html.push("<p style='font-weight:bold;margin:0;font-size:20px;color:black;width:85%;height:80px;line-height: 80px;margin:0 auto;text-align: center;'><span style='float:left;'>"+data.ProductName+"</span><span style='color:#E17421;float:right'>￥"+data.ProductPrice+"起</span></p>");
		 html.push("<p style='margin:0;font-size:12px;border-bottom:1px solid #EEEEEE;color:#E17421;width:92%;height:60px;line-height: 60px;margin:0 auto;text-align: center;'>");
		 html.push("<button style='border:none;outline:none;width:33%;height:30px;margin-left:10%;background:#fff;line-height:0px;vertical-align:middle ;float:left;border:1px solid #E17421;border-radius:6px;'>景点介绍</button>");
		 html.push("<button style='border:none;outline:none;width:33%;height:30px;margin-right:10%;background:#fff;line-height:0px;vertical-align:middle ;float:right;border:1px solid #E17421;border-radius:6px;'>购买须知</button>");
		 html.push("</p>");
		 html.push(" <p style='color:#838383;width:88%;margin:0 auto;line-height: 30px;overflow: hidden;'>"+data.ProductName+"。</p>");
		 $('#xiangqing1').append(html.join(''));
		 var html1=[];	 
		 html1.push("<p style='height:60px;line-height:60px;margin:0 5%;font-size:20px;border-bottom:1px solid #EEEEEE;'><span style='float:left;'><img style='width:30px;height:30px;' src='lib/images/goupiaoss.png'/>票种选择</span> </p>");
		 html1.push("<p class='piaowu' style='height:70px;line-height:70px;color:black;margin:0;font-size:14px;border-top:1px solid #EEEEEE;border-bottom:1px solid #EEEEEE;position: relative;'>");
		 html1.push("<span style='font-weight:bold;margin-left:5%;'>"+data.ProductName+"</span>");
		 html1.push("<span style='position: absolute;color:#E17421;font-size: 16px;top:-10px;right:40px;font-weight: bold;'>￥"+data.ProductPrice+"</span>");
		 html1.push("<span style='position: absolute;font-size: 14px;top:10px;right:40px;color:#838383;'>月销"+data.number+"</span>");
		 html1.push("<span class='xianshi' style='position: absolute;font-size: 26px;right:15px;color:#838383;font-weight: bold;'>∨</span>"); 
		 html1.push("<div class='piaowus' style='height:100px;background:#F4F8F9;margin:0;display: none;'>");
		 html1.push("<p style='height:30px;line-height: 30px;margin:0;'><span style='margin-left:5%;line-height: 30px;'>提前1天预订，出票后即可立即入园</span><span style='float:right;color:#E17421;font-size: 16px;font-weight: bold;margin-right:20px;'>￥"+data.ProductPrice+"</span></p> "); 
		 html1.push("<button style='border:none;outline:none;width:60px;color:#E17421;height:25px;margin-left:7%;background:#fff;line-height:0px;vertical-align:middle ;float:left;border:1px solid #E17421;border-radius:6px;'>无条件退</button>");  
		 html1.push("<button style='border:none;outline:none;width:80px;color:#fff;height:25px;margin-left:45%;background:#FF4900;line-height:0px;vertical-align:middle ;float:left;border:1px solid #E17421;border-radius:6px;'>立即预订</button>");  
		 html1.push("<p style='height:30px;line-height: 30px;margin:0;color:#838383;float:left;width:100%;'><span style='margin-left:5%;line-height: 30px;'>月售笔"+data.number+"</span> | <span style=''>预订须知></span></p>  ");  
		 html1.push("</div>");
		 $('.wenti').append(html1.join(''));  
	 });
}
</script>
<script>
$(document).on('click','.piaowu',function(){
    if($(".xianshi").html()=='∨'){	
		$(".piaowus").fadeIn();
		$(".xianshi").html("∧");
	  }else if($(".xianshi").html()=='∧'){		
			$(".piaowus").fadeOut();
			$(".xianshi").html("∨");
		}
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
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
	</div>	
	<!-- 商品详情 -->
    <div class="xiangqing" id="xiangqing1"  style="width:96%;height:230px;margin:0 auto;background:#fff;position: relative;top:12px;    border-top-left-radius: 6px; border-top-right-radius: 6px;overflow: hidden;">
     </div>
      <!-- 票种选择  -->
	<div class="wenti"  style="width:92%;height:auto;margin:0 auto;background:#fff;position: relative;top:24px;overflow: hidden;">
                     
     </div> 
  <button style="background:#fff;margin:36px 4% 12px;text-align:center;color:#838383;width:92%;height:50px;border:none;outline: none;">查看更多票型</button>            
      <!-- 商品详情 -->
     <div class=""  style="width:92%;height:50px;text-align: center;margin:0 auto;background:#fff;position: relative;overflow: hidden;">
         <p style="width:100%;line-height: 50px;font-size:20px;font-weight: bold;"><span style="float:left;padding-left:10%;">商品详情</span><span style="color:#EEEEEE;">|</span><span style="float:right;margin-right:10%;">游客点评</span> </p>
     </div> 
     <div class="xiangqing"  style="width:92%;height:auto;margin:0 auto;background:#fff;position: relative;top:5px;overflow: hidden;">
         <ul style="margin:20px 0 20px 5%;line-height:30px;color:#838383;">
           <li>【使用时间】：<span>指定游玩日期内1日有效</span></li>
           <li>【预订规则】：<span>提前预订</span></li>
           <li>【退改说明】：<span>允许退单</span></li>
         </ul>
         <div id="img1">
	        
         </div>
     </div> 
</body>





</html>