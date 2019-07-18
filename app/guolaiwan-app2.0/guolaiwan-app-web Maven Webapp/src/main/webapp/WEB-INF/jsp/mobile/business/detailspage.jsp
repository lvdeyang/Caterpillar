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

<title>商品详情页</title>

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
	 


	  function getCom(){
	     var _uricoms = window.BASEPATH + 'pubnum/getComs';
		
		 $.get(_uricoms, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data && data.length>0){
				    var html=[];
				   
					for(var i=0; i<data.length; i++){
					    if(data[i].comCode==comCode){
					        $('#selCom').html(data[i].comName);
					    }
					    	
					    html.push('<li><a data="'+data[i].comCode+'" href="javascript:void(0)" class="comSel">'+data[i].comName+'</a></li>');
					    
					}
					$('#com').append(html.join(''));
				}				
		  });	  
	 }
      /**/	
	  function getRecomment(){
	    var _uriMerchantInfo = window.BASEPATH + 'phoneApp/merchantInfo?merchantID=198&userId=${userId}';
		
		 $.get(_uriMerchantInfo, null, function(data){
				data = parseAjaxResult(data);
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
					}					}
					$('#headerWrapper').append(html.join(''));
					$("#headerSwiper").swiper({
				        loop: true,
				        autoplay: 3000,
				        autoplayDisableOnInteraction : false
				      });
				}
		 });
	}
		 });
	  
	//加入购物车
function joinBasket(){
		    var param={};
			param.productId=${product.id};
			param.productNum=1;
			param.userId=${userId};
			/* param.productRestrictNumber=${productRestrictNumber}; */
			param.payMoney=1;
			param.paytype='WEICHAT';
			param.source="PUBLICADDRESS";
			/* param.bookDate=$('#bookDate').val(); */
            
				var _uriPay = window.BASEPATH + 'phoneApp/joinBasket';
			    $.post(_uriPay, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					$.toast("已加入购物车");
				});
			}
	

	function gotopickingpurchase(){
   		location.href=window.BASEPATH + 'business/gotopickingpurchase?productId=${product.id}';
    }

function getRecomment(){
	var _uriRecomment = window.BASEPATH + 'phoneApp/productInfo?productId=${product.id}&userId=${userId}';
		$.get(_uriRecomment, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data){
			    var html=[];
			    var pics=data.product.productMorePic.split(',');
				for(var i=0; i<pics.length; i++){
					html.push('<div style="height:200px;" class="swiper-slide"><img style="height:200px" src="'+pics[i]+'" alt=""></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
			}
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
		<div class="content" id="content" >
				<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
				  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
				  </div>
				</div>
		</div>
	   
	   <div style="width:100%;height:auto;padding:0 5%;background: #fff;margin:5px 0;">
	     <p style="line-height: 40px;height:40px;margin:0;"><span style="font-size:18px;color:#EF8C52;">￥${product.productPrice}</span><span style="text-decoration:line-through;">￥${product.productOldPrice}</span></p>
	     <p style="line-height: 40px;height:40px;margin:0;"><span>${product.productName}</span><span style="float:right;">已售100+</span></p>
	   </div>
	   <!-- 优惠卷 -->
	   <!--  <div style="width:100%;height:auto;padding:0 5%;background: #fff;margin:5px 0;">
	     <p style="line-height: 40px;height:40px;margin:0;border-bottom:1px solid #E1E1E1;"><span>优惠卷</span><span style="float:right;">领取优惠卷</span></p>
	     <img style="width:100%;height:50px;margin:5px 0;" src="lib/images/2.jpg">
	   </div> -->
	   <p style="font-szie:12px;background: #EFEFEF;width:100%;height:30px;line-height:30px;margin:0;text-align: center;">商品详情</p>
	   <div style="padding:10px 10% 0;text-indent:2em;height:auto;background: #fff;">
	    ${product.productIntroduce}
	   </div>
	   <div style="width:100%;height:60px;position: fixed;bottom:0;background: #fff;">
        <p onclick="joinBasket()" style="line-height: 60px;color:#EB6E1E;width:50%;display: inline-block;text-align: center;font-size:16px;font-weight: bold;">加入购物车</p>
        <p onclick="gotopickingpurchase()" style="line-height: 60px;background:#EB6E1E;width:50%;color:#fff;float:right;display: inline-block;text-align: center;font-size:16px;font-weight: bold;">立即购买</p>
        </div> 
        <!-- 空白 -->
         <div style="height:60px;"> </div>
	</body>
</html>