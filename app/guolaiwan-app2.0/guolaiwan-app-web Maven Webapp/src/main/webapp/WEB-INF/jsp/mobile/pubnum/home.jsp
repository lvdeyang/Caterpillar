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

<title>公众号首页</title>

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
	min-height: 100%;
	background-color: #fff;
	position: relative;
	-webkit-text-size-adjust: none;
	background-color: #fbfbfb;
	text-decoration: none !important;
}

* {
	box-sizing: border-box;
}

.z-depth-1-bottom {
	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .14), 0 3px 1px -2px
		rgba(0, 0, 0, .12), 0 1px 5px 0 rgba(0, 0, 0, .2) !important;
}

.z-depth-1-right {
	box-shadow: 2px 0 2px 0 rgba(0, 0, 0, .14), 3px 0 1px -2px
		rgba(0, 0, 0, .12), 1px 0 5px 0 rgba(0, 0, 0, .2) !important;
}

.wrapper {
	height: 100%;
	width: 100%;
	position: relative;
}

/* mmenu 样式覆盖 */
.mm-listview .mm-next {
	width: 90px !important;
}

.mm-navbar.mm-navbar-top {
	height: 110px !important;
}

.mm-menu.mm-hasnavbar-top .mm-panel, .mm-menu.mm-hasnavbar-top .mm-fixeddivider
	{
	top: 110px !important;
}

.picker-highlight {
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .3);
}

.picker-highlight .toolbar, .picker-highlight .picker-items {
	background: #fff;
}

.picker-highlight .toolbar .title {
	color: #000;
}

.weui-picker-calendar {
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .3) !important;
}

.weui-picker-calendar .toolbar {
	background: #fff !important;
}

.weui-picker-calendar .picker-calendar-week-days {
	background: #fff !important;
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

.side-menu {
	background-color: #fff;
}

.logout {
	color: #fff !important;
	line-height: inherit !important;
	font-size: inherit !important;
	border-radius: 0;
}

.content {
	
}

.cards {
	box-shadow: 0 1px 2px rgba(0, 0, 0, .3);
	background-color: #fff;
}

.weui-btn {
	color: #fff !important;
}

.weui-btn:after {
	border-radius: 0 !important;
}

.form-row {
	height: 81px;
	line-height: 80px;
	border-bottom: 1px solid #f1f1f1;
	position: relative;
}

.form-row.last {
	border-bottom: 0;
}

.form-row.btn-content {
	padding: 20px 10px;
}

.form-row.text-content {
	text-align: center;
}

.form-row.sm {
	height: 51px;
	line-height: 50px;
}

.form-row.lg {
	height: 111px;
	line-height: 110px;
}

.back-icon {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	z-index: 0;
	text-align: center;
}

.form-col-half {
	width: 50%;
	height: 100%;
	float: left;
	padding: 0 15px;
	text-align: center;
	position: relative;
	z-index: 1;
	line-height: 80px;
	text-align: center;
}

.form-col-half.sm {
	line-height: 50px;
}

.form-col-half.left-border {
	border-right: 1px solid #f1f1f1;
}

.form-row.sm .form-col-half {
	line-height: 50px;
}

.form-row.sm .form-col-half>input {
	padding: 10px;
}

.form-row input {
	width: 80%;
	margin: auto;
	padding: 10px;
	border: 0;
	font-size: 14px;
}

.form-row textarea {
	width: 80%;
	height: 95px;
	line-height: 35px;
	padding: 0 10px;
	position: relative;
	top: 8px;
	border: 0;
	font-size: 14px;
	font-family: Arial;
}

.btn-custom {
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	border-radius: 0;
	background-color: #18b4ed;
}

.btn-custom:active {
	opacity: 0.7 !important;
	background-color: #18b4ed !important;
}

.content-top, .content-bottom {
	line-height: 40px;
	position: relative;
}

.content-top {
	font-size: 14px;
	color: #bfbfbf;
	top: 8px;
}

.content-bottom {
	bottom: 8px;
}

.content-icon {
	color: #bfbfbf;
	position: relative;
}

.my-popup .weui-popup__modal {
	background: #fbfbfb;
}

.my-popup .go-back>span {
	position: relative;
	top: 2px;
}

.my-popup table {
	width: 100%;
	font-size: 14px;
	border-collapse: collapse;
}

.my-popup table thead th, .my-popup table tbody td {
	border: 1px solid #f1f1f1;
}

.my-popup table tbody td.center {
	text-align: center;
}

.my-popup table th, .my-popup table td {
	padding: 10px 2px;
}

.my-popup table tr:first-child>th, .my-popup table tr:first-child>td {
	border-top: 0;
}

.my-popup table tr:last-child>td {
	border-bottom: 0;
}

.my-popup table tr>th:last-child, .my-popup table tr>td:last-child {
	border-right: 0;
}

.price {
	color: #bfbfbf;
	transition: all .05s ease-in;
}

.price.selected {
	font-size: 18px;
	color: #FB6155;
	border-top: 2px solid #FB6155;
}

/* 计数器 */
.my-counter-label {
	position: relative;
	bottom: 9px;
	font-size: 14px;
}

.my-counter {
	height: 30px;
	line-height: 30px;
	width: 90px;
	border: 1px solid #bfbfbf;
	margin-top: 10px;
	border-radius: 4px;
	position: relative;
}

.my-counter, .my-counter>a, .my-counter>span {
	display: inline-block;
}

.my-counter>a, .my-counter>span {
	height: 28px;
	line-height: 28px;
	text-align: center;
	position: absolute;
}

.my-counter .btn-minus {
	border-right: 1px solid #bababa;
	width: 29px;
	color: #ababab;
	left: 0;
	top: 0;
}

.my-counter .number {
	width: 30px;
	font-size: 14px;
	left: 30px;
	top: 0;
}

.my-counter .number.warning {
	background-color: #FB6155;
	color: #fff;
}

.my-counter .btn-plus {
	border-left: 1px solid #bababa;
	width: 29px;
	color: #ababab;
	right: 0;
	top: 0;
}

/* 日期组件代理对象 */
.click-wrapper {
	position: absolute;
	z-index: 1;
	width: 100% !important;
	height: 100%;
	opacity: 0;
	left: 0;
	top: 0;
	text-align: center;
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
    
    #distributeList{
       margin-top:10px;
       padding-left:10px;
       border-bottom:solid 2px #18b4ed;
       width:100%;height:35px;
       
    }
    
    #distributeList a{
       text-decoration:none;
       color:#CCC;
       font-size:12px;
    }
    #distributeList a.current{
       text-decoration:none;
       color:#18b4ed;
       font-size:20px;
    }
    .columnTable{
    
        width:100%;
        
    }
    .columnTable td{
	    width:10%;
	    text-align:center;
	    font-size:12px;
	 
    }
    
    a:link {
	 text-decoration: none !important;
	}
	a:visited {
	 text-decoration: none !important;
	}
	a:hover {
	 text-decoration: none !important;
	}
	a:active {
	 text-decoration: none !important;
	}

  .weui-search-bar__label{
  
  border-radius:20px;
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
 
		start()
		myFunction()
		$(window).on('scroll', function(){
		 start()
		})
		function start(){
		  //.not('[data-isLoaded]')选中已加载的图片不需要重新加载
		 $('.lazy').not('[data-isLoaded]').each(function(){
		   var $node = $(this)
		   if( isShow($node) ){
		     loadImg($node)
		   }
		 })
		 }
		//判断一个元素是不是出现在窗口(视野)
		function isShow($node){
		 return $node.offset().top <= $(window).height() + $(window).scrollTop()
		 }
		//加载图片
		 function loadImg($img){
		//.attr(值)
		//.attr(属性名称,值)
		$img.attr('src', $img.attr('data-src')) //把data-src的值 赋值给src
		$img.attr('data-isLoaded', 1)//已加载的图片做标记
		}
		
/*记录历史位置  */
      $(function () {
		var str = window.location.href;
		str = str.substring(str.lastIndexOf("/") + 1);
		if ($.cookie(str)) {
		$("html,body").animate({ scrollTop: $.cookie(str) }, 1000);
		}
		});
		$(window).scroll(function () {
		var str = window.location.href;
		str = str.substring(str.lastIndexOf("/") + 1);
		var top = $(document).scrollTop();
		$.cookie(str, top, { path: '/' });
		return $.cookie(str);
	});  

	function myFunction(){
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
			initFirstPage();
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
					getLoation();
				}
				
		  });
	  
	  }
	  
	  
	  function getLoation(){
	       wx.config({
	            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            appId : loca.appId, // 必填，公众号的唯一标识
	            timestamp : loca.timestamp, // 必填，生成签名的时间戳
	            nonceStr : loca.nonceStr, // 必填，生成签名的随机串
	            signature : loca.signature,// 必填，签名，见附录1
	            jsApiList : ['checkJsApi','getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
	        wx.ready(function() {
	            wx.getLocation({  
	                success: function (res) {  
	                    var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90  
	                    var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。  
	                    var speed = res.speed; // 速度，以米/每秒计  
	                    var accuracy = res.accuracy; // 位置精度  
	                    getCity(latitude,longitude);
	                    
	                },  
	                cancel: function (e) {  
	                        //这个地方是用户拒绝获取地理位置  
	                        if(comCode=='0000'){
							    comCode='0001';
							}
							initFirstPage();
					}
	                	
	             });     
		         wx.error(function (res) {  
		                if(comCode=='0000'){
						    comCode='0001';
						}
						initFirstPage();
				
		         });     
	            
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
				
				initFirstPage();
            }  
        });  
	  
	  }
	  
	  
	  function initFirstPage(){
	        $('#postionLoading').hide();
			getCom();
            getRecomment();
		    getModal();
			getActivityBundle();
			initSharewx();
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
	  
	  $(document).on('click','.comSel',function(){
		   location.href=window.BASEPATH + 'pubnum/index?comCode='+$(this).attr('data');

	  });
	  
	  
	  //test
	    /*if(comCode=='0000'){
		    comCode='0001';
		}
		getCom();
	    getRecomment();
	    getModal();
		getActivityBundle();
		initSharewx();*/
      /**/
		
	  function getRecomment(){
	     var _uriRecomment = window.BASEPATH + 'phoneApp/getRecommend?comCode='+comCode;
		
		 $.get(_uriRecomment, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data && data.length>0){
				    var html=[];
					for(var i=0; i<data.length; i++){
						html.push('<div style="height:200px;" id="sw-'+data[i].id+'" class="swiper-slide"><img class="topmod" id="top-'+data[i].productId+'-'+data[i].classify+'" style="height:200px;" src="'+data[i].slidepic+'" alt="">');
						html.push('<div style="font-size:12px;position:absolute;padding-left:5px;bottom:0px;color:#FFF">'+data[i].name+'</div></div>');
					}
					$('#headerWrapper').append(html.join(''));
					$("#headerSwiper").swiper({
				        loop: true,
				        autoplay: 3000,
				        autoplayDisableOnInteraction : false
				      });
				}
		 });
	  }
		
      
		
	  function getModal(){
	    var _uriModal = window.BASEPATH + 'phoneApp/getOnlyModulars?comCode='+comCode;
		
		$.get(_uriModal, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			var modals=data.modulars;
			if(modals && modals.length>0){
			    var html=[];
			    var liveModal={};
			    liveModal.modularCode='0000';
			    liveModal.modularPic='lib/images/live.jpg';
			    liveModal.modularName='直播';			    
			    //按照心意改变 "直播模块位置"
			    var starp;
			    for(var i = 0; i<modals.length;i++){
			      if(modals[i].modularCode == '2022'){
			        starp =modals[i];
			        modals[i] = liveModal;
			      }			    
			    }
			    if(starp){
			       modals.push(starp);
			    }
			    
			    			    
			    if(modals.length<20){
			       for(var k=modals.length;k<20;k++){
			          modals.push({});
			       }
			    }
				for(var i=0; i<modals.length; i++){
				
				   var index=i;
				   if(modals[11].modularCode){
				       if(i==6){
					     index=10;
					   }
					   if(i==10){
					     index=6;
					   }
				   }
				   
				   if(i%10==0){
				     html.push('<div style="height:180px;" class="swiper-slide">');
				     html.push('<table class="columnTable" style="margin-top:10px;">');
				   }
				   if(i%5==0){
				     html.push('<tr style="height:75px;">');
				   }
				   if(modals[index].modularCode){
					   html.push('<td>');
			           html.push('<image class="modals" id="modal-'+modals[index].modularCode+'" style="width:46px;height:46px;margin:0 auto" src="'+modals[index].modularPic+'"/>');
			           html.push('<div>'+modals[index].modularName+'</div>');
			           html.push('</td>');
				   }else{
				       html.push('<td></td>');
				   }
				  
		           if(i%5==4){
				     html.push('<tr>');
				   }
				   if(i%10==9){
				     html.push('</table>');
				     html.push('</div>');
				   }
				   
				   generateModMerchant(modals[index].modularCode);
	sleep(50)
				}
				$('#columnWrapper').append(html.join(''));
				$("#columnSwiper").swiper({
			        loop: false,
			        autoplay: false
			    });
			}
			
			
		});
	  
	  }
	 
                 

                 function sleep(numberMillis) { 
                 
                       var now = new Date(); 
                       var exitTime = now.getTime() + numberMillis; 
                       while (true) { 
                             now = new Date(); 
                            if (now.getTime() > exitTime) {
                                 break; 
                             } 
                       }
                 }



	  function generateModMerchant(modularCode){
	        var _uriModpro = window.BASEPATH + 'phoneApp/getModularMerchants?modularCode='+modularCode;
		    
			$.get(_uriModpro, null, function(data){
			   data = parseAjaxResult(data);
			   if(data === -1) return;
			   var merchants=data.merchants;
	           if(merchants==null||merchants.length==0){
	              return;
	           }
	           var html=[];
	           
	            var addStyle='';
			    if(data.modular.modularCode=='2222'){
	               addStyle="padding-bottom:70px;";
	            }
	           
	           html.push('<div style="width:100%;height:30px;text-align:center;font-weight:bold;background-color: #f4f4f4;font-size:20px;">'+data.modular.modularName+'<div class="hrefModal" id="hmodal-'+data.modular.modularCode+'" style="font-size:14px;position:absolute;margin-top:-23px;z-index:499;color:#a6a6a6;right:10px">查看全部></div></div>');
	           html.push('<br />');
	           html.push('<div class="zong" style="width:100%;position: relative;">');
	           html.push('<button id="fav"class="collect" value="'+merchants[0].id+'" style="position: absolute;top:30%;left:80%;float:right;width:17%;height:35px;font-size:14px;border-radius: 25px;color:#F6A2A2;background:#ffffff;border:1px solid #F6A2A2 ;outline:none;margin-right:5%;z-index:10;">+ 收藏</button>');
	           html.push('<div style="width:100%;margin-top:10px;float:left;" class="merchant1" id="merchant1-'+merchants[0].id+'-'+merchants[0].modularCode+'">');
		       html.push('<image style="width:60px;height:60px;float:left;margin-left:12px;"  class="lazy" src="lib/images/loading.gif" data-src="'+merchants[0].shopHeading+'"/>');
		       html.push('<div style="width:50%;float:left;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;font-weight:bold;padding-left:12px;height:40px;">'+merchants[0].shopName+'</div>');
		    
		       html.push('</br>');
		       html.push('<div style="width:80%;float:left;font-size:12px;padding-left:12px;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;">'+merchants[0].shopAddress+'</div>');
               html.push('<div style="width;100%;margin-top:15px;height:90px;float:left;margin-left:12px">');
               var morePics=merchants[0].shopMpic.split(',');
               html.push('<image style="width:32%;height:90px;display: inline-block;" class="lazy" src="lib/images/loading.gif" data-src="'+morePics[0]+'"/>');
		       html.push('<image style="width:32%;height:90px;display: inline-block;margin-left:1%" class="lazy" src="lib/images/loading.gif" data-src="'+morePics[1]+'"/>');
		       html.push('<image style="width:32%;height:90px;display: inline-block;margin-left:1%" class="lazy" src="lib/images/loading.gif" data-src="'+morePics[2]+'"/>');
		       html.push('<div style="width:100%;height:47px;display:block;">');
	           html.push('</div>');
		       html.push('</div>');
		       html.push('</div>');
		       html.push('<div id="commnetUser-'+merchants[0].id+'" style="height:25px;width:90%;margin-left:12px;margin-top:30px;float:left;">');
		       html.push('<div style="float:left;width:60px;font-size:12px">评论用户</div>')
		       html.push('</div>');
		       html.push('</div>');
		       //后面的
		       html.push('<div style="margin-top:50px;'+addStyle+'" class="weui-panel__bd" class="columnRecomments">');
			   html.push('<div class="weui-panel__bd">'); 
			   for(var j=1;j<merchants.length;j++){
				   html.push('<a  href="javascript:void(0);"  class="weui-media-box weui-media-box_appmsg merchant" id="merchant-'+merchants[j].id+'-'+merchants[j].modularCode+'">');
				   html.push('<div class="weui-media-box__hd">');
				   html.push('<img style="width:100%;height:100%" class="weui-media-box__thumb lazy"  src="lib/images/loading.gif" data-src="'+merchants[j].shopHeading+'">');
				   html.push('</div>');
				   html.push('<div class="weui-media-box__bd">');
				   html.push('<h4 class="weui-media-box__title">'+merchants[j].shopName+'</h4>');
				   html.push('<p class="weui-media-box__desc">'+merchants[j].shopAddress+'</p>');
				   html.push('<div style="font-size:12px;height:20px"></div>');
				   html.push('</div>');
				   html.push('</a>');
			   
			   }
			   html.push('</div></div>');
			   $('#content').append(html.join(''));
               getCommentUser(merchants[0].id);
			});
	    }
	  
		
		
	    
	    
	    function getCommentUser(id){
	    
	        //
	        var _uriCommentUser = window.BASEPATH + 'pubnum/commentUser?merchantId='+id;
		
		      $.get(_uriCommentUser, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
			    if(data&&data.length>0){
			       var html=[];
			       for(var i=0;i<5;i++){
			          if(data[i]){
			           html.push('<image src="'+data[i]+'" style="border-radius:50%;width:25px;height:25px;float:left"/>');
			          }
			   
			       }
			       $('#commnetUser-'+id).append(html.join(''));
			    }else{
			        $('#commnetUser-'+id).hide();
			    }	
			
				
			  });
	    }
         	    
	    $(document).on('click','.hrefModal',function(){
	    
	      var codes=this.id.split('-');
	      location.href=window.BASEPATH + 'pubnum/column/index?modularCode='+codes[1];
	       
	    });
	    
	    $(document).on('click','.modals',function(){
	       var codes=this.id.split('-');
	       if(codes[1]=='0000'){
	          location.href=window.BASEPATH + 'pubnum/live/index';
	       }else if(codes[1]=='01112'){
	          location.href=window.BASEPATH + 'pubnum/activity/index?refActivityId=5&comCode='+comCode;
	       }else if(codes[1]=='12022'){
	          location.href=window.BASEPATH + 'pubnum/ipost';
	       }else if(codes[1]=='101112'){
	          location.href=window.BASEPATH + 'back/merchant/comment';
	       }else if(codes[1]=='2124'){
	          location.href=window.BASEPATH + 'judges/votepage';
	       }else{
	         location.href=window.BASEPATH + 'pubnum/column/index?modularCode='+codes[1];
	    
	       }
	       
	    });
	
	   $(document).on('click','.merchant',function(){
	       var codes=this.id.split('-');
	       if(codes[1]==387){
	          location.href='http://www.yueba.net.cn/chenxisoft/home/mobile/index';
	       }else{
	        //景点模块	       
	       if(codes[2].indexOf('0001')!=-1){	      
	         location.href=window.BASEPATH + 'business/merchant/nsAndView?merchantId='+codes[1]+'&comCode=${modularCode}';
	          }
	      /*  //住宿模块
	      else if(codes[2] == 0002){
	         location.href=window.BASEPATH + 'business/gotoshopdetails?merchantId='+codes[1];
	       }  */
	       //美食模块  
	      else if(codes[2] == '0003'){
	        location.href=window.BASEPATH + 'business/gotodelicacystore?merchantId='+codes[1];	     
	       }
	       //采摘板块
	      else if(codes[2] == '2126'){
	      
	        location.href=window.BASEPATH + 'business/gotopicking?merchantId='+codes[1];
	      } 
	      /* 地方特产 */
	       else if(codes[2] == '01'){
	        location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	      } 
	       else{
	        location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	       }	       	       		     	         	         
	       }
	       
	    
	    });
	    $(document).on('click','.merchant1',function(){
	       var codes=this.id.split('-');	
	       if(codes[1]==387){
	          location.href='http://www.yueba.net.cn/chenxisoft/home/mobile/index';
	       }else{
	        //景点模块	       
	       if(codes[2].indexOf('0001')!=-1){	      
	         location.href=window.BASEPATH + 'business/merchant/nsAndView?merchantId='+codes[1]+'&comCode=0001';
	          }
	       /* //住宿模块
	      else if(codes[2] == 0002){
	         location.href=window.BASEPATH + 'business/gotoshopdetails?merchantId='+codes[1];
	       }  */
	       //美食模块  
	      else if(codes[2] == '0003'){
	        location.href=window.BASEPATH + 'business/gotodelicacystore?merchantId='+codes[1];	     
	       }
	       //采摘板块
	      else if(codes[2] == '2126'){
	      
	        location.href=window.BASEPATH + 'business/gotopicking?merchantId='+codes[1];
	      } 
	      /* 地方特产 */
	        else if(codes[2] == '01'){
	        location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	      } 
	       else{
	        location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	       }	       	       		     	         	         
	       }
	       
	    
	    });
	    $(document).on('click','.topmod',function(){
	       var codes=this.id.split('-');
	       if(codes[1]==387){
	          location.href='http://www.yueba.net.cn/chenxisoft/home/mobile/index';
	       }else{
	          if(codes[2]==''){return;}
	          location.href=window.BASEPATH + 'pubnum/merchant/index1?code='+codes[1]+'&classify='+codes[2];
	       }
	       
	    });
	
	
	     
	 
	   
	   $(document).on('click','#downloadapp',function(){
	   
	     location.href="http://<%=weburl%>/download/download.html";
	     return false;
	   });
	   
	   
	  //收藏
	   $(document).on('click','#fav',function(){
	      var merchantId =  $(this).attr("value");  
	      var param={};
		  param.merId= merchantId;
	      param.userId=${userId};
	      var _urifav = window.BASEPATH + 'phoneApp/mercollectionPro';
		  $.post(_urifav, $.toJSON(param), function(data){
			data = parseAjaxResult(data);
			if(data == "收藏成功"){
			$.toast("收藏成功");
			return;
			}else{
			$.toast("已收藏");
			return;
			}
			
			iscollect=1;
		  });
	   });
	   
	   
	 
	   $(document).on('click','#searchimg',function(){
	   
	       location.href=window.BASEPATH + 'pubnum/search?content=';
	   
	   });
	   $(document).on('click','#bundle',function(){
	   if(bundleType=="VOTE"){
	   	   location.href=window.BASEPATH + 'judges/votepage';
	   }else{
	       //location.href=window.BASEPATH + 'pubnum/activity/index?refActivityId=0&comCode='+comCode;
	       location.href=window.BASEPATH + 'pubnum/activities';
	   }
	   });
	
	var bundleType;
	
	
	   function getActivityBundle(){
	    
	        //
	        var _uriBundle = window.BASEPATH + 'pubnum/getActivityBundle?comCode='+comCode;
		
		      $.get(_uriBundle, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
			    if(data.bundles.length>0){
			       $('#bundle').show();
			       $('#actTitle').html(data.bundles[0].title);
			       bundleType=data.bundles[0].type;
			       $('#activityBunlde').attr('src',data.url+data.bundles[0].pic)
			    }
				
			  });
	    }
	
	   
	    var paras={};
	    $(document).on('click','#ewmpic',function(){
	          var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+location.href.split('#')[0];
			    $.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					    
						paras=data;
						doScan();
					}
					
				});
	    
	    });
	    
	    var share={};
	    function initSharewx(){
	        var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	  
	    	var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			    $.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					    
						share=data;
						doScanShare();
					}
					
			});
	    
	    }
	    
	    
	    
	    function doScanShare(){
            wx.config({
	            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            appId : share.appId, // 必填，公众号的唯一标识
	            timestamp : share.timestamp, // 必填，生成签名的时间戳
	            nonceStr : share.nonceStr, // 必填，生成签名的随机串
	            signature : share.signature,// 必填，签名，见附录1
	            jsApiList : ['checkJsApi', 'onMenuShareTimeline' , 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
	        wx.ready(function() {
	
	               
	            wx.onMenuShareTimeline({
                            title: '畅游华夏，尽在过来玩', // 分享标题
                            link: 'http://<%=weburl%>/guolaiwan/pubnum/index', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
                            success: function () {
                               	
                            }
                        });
	            wx.onMenuShareAppMessage({
					title : '畅游华夏，尽在过来玩', // 分享标题
					desc : '<%=weburl%>，联系电话:0315-6681288/6686299', // 分享描述
					link : 'http://<%=weburl%>/guolaiwan/pubnum/index', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
					success : function() {}
				});
	            
	       });
        }
	    
	    function doScan(){
            wx.config({
	            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            appId : paras.appId, // 必填，公众号的唯一标识
	            timestamp : paras.timestamp, // 必填，生成签名的时间戳
	            nonceStr : paras.nonceStr, // 必填，生成签名的随机串
	            signature : paras.signature,// 必填，签名，见附录1
	            jsApiList : ['checkJsApi', 'scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
	        wx.ready(function() {
	
	               wx.scanQRCode({ 
		                needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		                scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		                success: function (res) {

		                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		                    location.href=window.BASEPATH + 'pubnum/product/index/payinshop/'+result;
		                  
		                }
		            });
	            
	            
	            
	       });
        }
	   

   
	

	
	};
</script>





<body>
			<!-- 主页 -->
		
       
		<div class="header">
			<div class="wrapper">
					<!-- <select style="float:left;font-size:11px;width:120px;height:40px;" class="weui-select" id="com"></select> -->
				<div class="dropdown" style="width:200px;z-index:1000;border:0">
				  <button style="border:0" class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    <span id="selCom"></span>
				    <span class="caret"></span>
				  </button>
				  <ul id="com" class="dropdown-menu" aria-labelledby="dropdownMenu1">
				    
				  </ul>
				</div>
				
				
				<div class="header-content">商户</div>
			</div>
		</div>
		<div class="content" id="content">
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
            
            <div id="searchimg" style="width:80%;margin:0 auto;opacity:0.5;height:45px;position: relative;top:10px;z-index:11111;cursor: pointer;">
              <!-- <image src="lib/images/search.jpg" style="width:100%;height:50px"/> -->
               <label style="border:1px solid #9B9B9B" class="weui-search-bar__label" id="searchText">
				      <i style="margin: auto;margin-top: 3%;" class="weui-icon-search"></i>
				      <span style="margin: auto;margin-top: 3%;">搜索</span>
				    </label>
           </div> 
            <div class="weui-loadmore" id="postionLoading">
			  <i class="weui-loading"></i>
			  <span class="weui-loadmore__tips">正在获取定位，请稍候......</span>
			</div>
		    <div class="swiper-container" style="height:160px;margin-top:5px;"  id="columnSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="columnWrapper" style="height:180px;">
			  </div>
			</div>

		    <div id="bundle" style="display:none;font-size: 20px;">
	             <div style="margin-left:15px;margin-top:10px;display:none;"><span style="font-weight:bold;font-size:15px;">过来玩活动</span>
	            &nbsp;&nbsp;<span style="font-size:12px;"  id="actTitle"></span></div>
	            <img id="activityBunlde" src="" style="width:100%;height:110px;">
	            <img style="display:none;" id="downloadapp" src="lib/images/downapp.gif" style="margin-top:-70px;margin-left:120px;z-index:500;width:130px;height:35px;">
            </div>
		</div>
	</div>
	 <p style="height:40px;"></p>
</body>


</html>