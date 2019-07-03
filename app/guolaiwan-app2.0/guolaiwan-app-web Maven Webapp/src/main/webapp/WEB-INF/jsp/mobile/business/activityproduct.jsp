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
<title>活动产品</title>
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
	timer();
	getRecomment();
	var iscollect;
	  window.BASEPATH = '<%=basePath%>';
	  var comCode='${comCode}';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
	
	  if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		    getloca();
	  } else {
		    if(comCode=='0000'){
			    comCode='0001';
			}
			getCom();
            getRecomment();
			initSharewx();
	  }
	
	  
	  
		
	 
	
	  var loca={};
	  function getloca(){
	      
		  var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	
		  var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
		    $.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
					loca=data;
					
				}
				
		  });
	  
	  }
	  
	  
	 
	  
	  function getCity(la,lo){
	  
	     $.ajax({  
            url: 'http://api.map.baidu.com/geocoder/v2/?ak=yPjZB3eElPXn7zXRjcfqGCze6LCPlkmn&callback=renderReverse&location=' + la + ',' + lo + '&output=json&pois=1',  
            type: "get",  
            dataType: "jsonp",  
            jsonp: "callback",  
            success: function (data) {  
                console.log(data);  
                var province = data.result.addressComponent.province;  
                var cityname = (data.result.addressComponent.city);  
                var district = data.result.addressComponent.district;  
                var street = data.result.addressComponent.street;  
                var street_number = data.result.addressComponent.street_number;  
                var formatted_address = data.result.formatted_address;  
                if(comCode=='0000'){
				    if(district.indexOf('平谷')!=-1){
					    comCode='1003';
					    $('#headerName').html('全域休闲');
					    $('#phone').html('010-89991991');
					}else{
					    comCode='0001';
					}
				}
				
				
				getCom();
                getRecomment();
				initSharewx();
            }  
        });  
	  
	  }
	  
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
	      var _uriMerchantInfo = window.BASEPATH + 'phoneApp/merchantInfo?merchantID=${productMerchantID}&userId=${userId}';
		
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
		
      
		
	  
		
		
	    
	    
	  
	   

	    
	    var share={};
	    function initSharewx(){
	        var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	  
	    	var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			    $.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					    
						share=data;
						
					}
					
			});
	    
	    }
	    
	  

	
	});
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
    
    function timer() {
		var endDate=new Date("${end}").getTime();
		var newtime=new Date().getTime();
		var intDiff =parseInt((endDate-newtime)/1000);
		alert(intDiff)
	   /*倒计时  */
	  window.setInterval(function () {
	    var day = 0,
	      hour = 0,
	      minute = 0,
	      second = 0; //时间默认值
	    if (intDiff > 0) {
	      day = Math.floor(intDiff / (60 * 60 * 24));
	      hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
	      minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
	      second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
	    }
	     if(hour<10){hour="0"+hour;}
		if(minute<10){minute="0"+minute;}
		if(second<10){second="0"+second;}
	    $("#times").html(day+"天"+hour + "时" + minute + "分" + second+"秒");
	    intDiff--;
	  }, 1000);
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
	
	<!-- 活动产品 -->
       <div style="height:80px;width:100%;background:#fff;">
           <p style="font-size:16px;font-weight:bold;line-height: 50px;margin:0 0 0 5%;">【活动】${activityRelPO.productName}</p>
           <p style="line-height: 5px;margin:0 0 0 5%;color:#A1A1A1;">&nbsp&nbsp月销：<span>10笔</span><span style="margin-left:5%;">预订须知 ></span></p>
       </div>
        <div style="height:60px;width:100%;line-height: 60px">
         <p style="font-size:18px;font-weight: bold;margin-left:5%;display: inline-block;">&nbsp&nbsp活动价<span style="color:#FF4900;">￥${price}</span></p>
          <button style="color:#fff;font-size:18px;border:none;outline:none;height:60px;width:30%;background:#FF4900;float:right;display: inline-block">限时抢购</button>
       </div>
       <div style="height:40px;width:100%;line-height: 40px;background: #fff;text-align: center;border-bottom:1px solid #DDDDDD;">
        <p style="color:#A1A1A1;">剩余<span id="times">00:00:00</span></p>
       </div>
      <!-- 商品详情 -->
       <div class=""  style="width:100%;height:50px;text-align: center;margin:0 auto;background:#fff;position: relative;overflow: hidden;">
         <p style="width:100%;line-height: 50px;font-size:20px;font-weight: bold;"><span style="float:left;padding-left:15%;">商品详情</span><span style="color:#EEEEEE;">|</span><span style="float:right;margin-right:15%;">游客点评</span> </p>
       </div> 
       <div class="xiangqing"  style="width:100%;text-align: center;height:auto;margin:0 auto;background:#fff;position: relative;top:5px;overflow: hidden;">
         <ul style="margin:20px 0 20px 5%;line-height:30px;color:#838383;text-align: left;">
          <li>【抢购时间】：<span>${beginTime}~${endTime}</span></li>
           <li>【使用时间】：<span>指定游玩日期内1日有效</span></li>
            <li>【预订规则】：<span>提前预订</span></li>
             <li>【退改说明】：<span>允许退单</span></li>
         </ul>
       </div>
 
</body>





</html>