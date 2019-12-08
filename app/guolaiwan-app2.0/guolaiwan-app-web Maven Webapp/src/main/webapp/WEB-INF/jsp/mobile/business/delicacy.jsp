<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
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
<title>美食</title>
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
	background-color:#fff !important;
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
$(function(){
  consoleLog();
   getRecomment();
   //getCate();
});
 
//轮播图以及下面的图片   	
function getRecomment(){
    var _uriRecomment = window.BASEPATH + 'cate/getRecommendByModu?merchantId=${merchantId}';
		$.get(_uriRecomment, null, function(data){
			if(data === -1) return;
			if(data && data.length>0){
			    var html=[];
				for(var i=0; i<data.length; i++){
				    html.push('<div style="height:200px;" id="sw-'+data[i].id+'" class="swiper-slide"><img class="topmod" id="top-'+data[i].id+'" style="height:200px;" src="http://www.guolaiwan.net/file'+data[i].shopPic+'" alt="">');
					html.push('<div style="font-size:12px;position:absolute;padding-left:5px;bottom:0px;color:#0D0D0D">'+data[i].shopName+'</div></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			      });
			}
		});
}


        var latitudes= 40.18654;
		var longitudes= 117.35987;
    //获取手机当前的经纬度
     function consoleLog(){
	    getloca();
	    var loca = {};
		function getloca() {
			var reqUrl = location.href.split('#')[0].replace(/&/g, "FISH");
			var _uri = window.BASEPATH + 'pubnum/prev/scan?url=' + reqUrl;
			$.get(_uri, null, function(data) {
				data = parseAjaxResult(data);
				if (data === -1) return;
				if (data) {
					loca = data;
					getLoation();
				}
			});
		}
    
 	function getLoation() {
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				//                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : loca.appId, // 必填，公众号的唯一标识
				timestamp : loca.timestamp, // 必填，生成签名的时间戳
				nonceStr : loca.nonceStr, // 必填，生成签名的随机串
				signature : loca.signature, // 必填，签名，见附录1
				jsApiList : [ 'checkJsApi', 'getLocation' ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function() {
				wx.getLocation({
					type : 'gcj02',
					success : function(res) {
						var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90  
						var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。  
						var speed = res.speed; // 速度，以米/每秒计  
						var accuracy = res.accuracy; // 位置精度  
						getCity(latitude, longitude);
						
					},
					cancel : function(e) {
						//这个地方是用户拒绝获取地理位置  
						alert("请打开GPS定位,");
					}
				});
				wx.error(function(res) {});
			});
		}
		
		  function getCity(latitude, longitude) { //通过经纬度   获取高德位置
			latitudes = (parseFloat(latitude)).toFixed(5); //保留经纬度后5位
			longitudes = (parseFloat(longitude)).toFixed(5);
			 getCate();
		}    

}
   function getCate(){
      var _uriRecomment = window.BASEPATH + 'business/getCate?merchantId=${merchantId}&latitude='+latitudes+'&longitude='+longitudes;
		$.get(_uriRecomment, null, function(data){
		    var html=[];
		    for(var i=0;i<data.length;i++){
		         html.push('<a onclick="gotodelicacystore('+data[i].merchantId+')"><div class="goupiao" style="position: relative;width:90%;height:180px;line-height:180px;border:none;border-bottom:1px solid #C0C0C0;border-left:none;border-right:none;margin:0 auto;">');
		         html.push('<img style="height:130px;width:45%;vertical-align: middle;display: inline-block;" src="'+data[i].ShopPic+'"/>');  
		         html.push('<div class="youxuan-in" style="display: inline-block;">');  
		         html.push('<p style="position: absolute;top:-40px;font-size:16px;font-weight: bold;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:180px;">'+data[i].ShopName+'</p>');
		         html.push('<p style="position: absolute;top:0px;font-size:12px;color:#757575;"><span style="">'+data[i].ModularClass+'</span></p>');
		         if(data[i].Date != null)html.push('<p style="position: absolute;top:25px;font-size:12px;color:#757575;">'+data[i].Date+'</p>'); 
		         if(data[i].average < 100){
		         html.push('<p style="color:#757575;position: absolute;top:0px;right:1%;font-size:14px">人均<span>'+data[i].average+'</span>元</p>'); 
		         }else{
		         html.push('<p style="color:#757575;position: absolute;top:0px;right:1%;font-size:14px">人均<span>30</span>元</p>');
		         }
		         var feature = data[i].feature;
		         if(feature !=null && feature!=""){
		            var split  =   feature.split(',');
		            html.push('<div style="position: absolute;top:55px;width:auto;height:auto;">');
			        for(var j=0;j<split.length&&j<2 ;j++){
			            
			            html.push('<button style="padding:0 3px;margin:0 5px;line-height:25px;font-size:12px;outline: none;border:none;border:1px solid #757575;height:25px;color:#757575;background:#fff;">'+split[j]+'</button>');
			        }
			        html.push('</div>');
		         }
		         html.push('<p style="position: absolute;top:25px;font-size:12px;color:#757575;">距离您'+data[i].distance+'km</p>');
		         html.push('</div>');
		         html.push('</div></a>');  		        
		    }
		     $('.youxuan').append(html.join(''));
		});
}


	function getAllMerchant(){
			var name=$('.search').val();
			var url="<%=basePath%>cate/search";
	            $.post(url,{"merchantId":${merchantId},"name":name,"type":"0003","latitude":latitudes,"longitude":longitudes},function(data){
	            	$('.youxuan').empty();
	            	var average = data.average;
	            	var merlist = data.merlist;
	            	var distance = data.distance;
	            	var html=[];
	            	if(merlist.length==0){
	            		html.push('<p style="text-align: center;position: fixed;bottom:5px;left:50%;margin-left:-28px;color:#858585;">暂无数据</p>');
	            	}else{
						for(var i=0; i<merlist.length; i++){
							 html.push('<a onclick="gotodelicacystore('+merlist[i].id+')"><div class="goupiao" style="position: relative;width:90%;height:180px;line-height:180px;border:none;border-bottom:1px solid #C0C0C0;border-left:none;border-right:none;margin:0 auto;">');
					         html.push('<img style="height:130px;width:45%;vertical-align: middle;display: inline-block;" src="http://www.guolaiwan.net/file'+merlist[i].shopHeading+'"/>');  
					         html.push('<div class="youxuan-in" style="display: inline-block;">');  
					         html.push('<p style="position: absolute;top:-40px;font-size:16px;font-weight: bold;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:180px;">'+merlist[i].shopName+'</p>');
					         html.push('<p style="position: absolute;top:0px;font-size:12px;color:#757575;"><span style="">'+merlist[i].modularClass+'</span></p>');
					         if(data.average[0] < 100){
		                     html.push('<p style="color:#757575;position: absolute;top:0px;right:1%;font-size:14px">人均<span>'+data.average[0]+'</span>元</p>'); 
		                     }else{
		                      html.push('<p style="color:#757575;position: absolute;top:0px;right:1%;font-size:14px">人均<span>30</span>元</p>');
		                     }
					         var feature = merlist[i].feature;
					         if(feature !=null && feature!=""){ //商家特色
					            var split  =   feature.split(',');
					            html.push('<div style="position: absolute;top:55px;width:auto;height:auto;">');
						        for(var j=0;j<split.length&&j<2 ;j++){
						            html.push('<button style="padding:0 3px;margin:0 5px;line-height:25px;font-size:12px;outline: none;border:none;border:1px solid #757575;height:25px;color:#757575;background:#fff;">'+split[j]+'</button>');
						        }
						        html.push('</div>');
					         }
					         html.push('<p style="position: absolute;top:25px;font-size:12px;color:#757575;">距离您'+distance[i]+'km</p>');
					         html.push('</div>');
					         html.push('</div></a>');
							
							}
					}
			    	$('.youxuan').append(html.join(''));
	            })
	}

	function gotodelicacystore(id){
        location.href=window.BASEPATH + 'business/gotodelicacystore?merchantId='+id;
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
		   <input placeholder="搜索" class="search" style="position: absolute;line-height: 30px;top:5px;margin-left:-40%;padding:0 15%;width:80%;height:30px;border-radius:18px;outline: none;border:none;background:#E0E0E0;text-align: center; " type="text">
		   <img style="width:20px;height:20px;position: absolute;right:20%;top:10px;" onclick="getAllMerchant()" src="lib/images/sousuo.png"/>
		  </div>
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
	</div>

         <!-- 美食  -->
	  	<div class="youxuan"  style="width:96%;height:auto;margin:0 auto;background:#fff;position: relative;overflow: hidden;">
         
           
       </div> 
          <!-- 置顶 -->
			<div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/hometop.png"></a></div>
</body>




</html>