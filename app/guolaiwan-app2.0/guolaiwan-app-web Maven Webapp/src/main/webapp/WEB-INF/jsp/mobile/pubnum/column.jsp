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

<title>栏目首页</title>

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
    #columnTable{
    
        width:100%;
        margin-top:10px;
        
    }
    #columnTable td{
	    width:20%;
	    text-align:center;
	    font-size:12px;
    }
    
    .weui-panel__bd{
        padding-bottom:0px !important;
    }

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">

	$(function() {
	  window.BASEPATH = '<%=basePath%>';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
		
		
	  var pageNumber=2;
	  var columnName;
	  var columnPic;
	  var columnUrl;
	  
      var _uriRecomment = window.BASEPATH + 'phoneApp/getRecommendByModu?modularCode=${modularCode}';
		
		$.get(_uriRecomment, null, function(data){
			data = parseAjaxResult(data);
			columnName = data[0].modularName + '-过来玩';
			columnPic = 'http://<%=weburl%>/lib/images/logo.jpg';
			columnUrl = window.location.href;
			if(data === -1) return;
			if(data && data.length>0){
			    var html=[];
				for(var i=0; i<data.length; i++){
				    html.push('<div style="height:200px;" id="sw-'+data[i].id+'" class="swiper-slide"><img class="topmod" id="top-'+data[i].id+'" style="height:200px;" src="'+data[i].shopPic+'" alt="">');
					html.push('<div style="font-size:12px;position:absolute;padding-left:5px;bottom:0px;color:#FFF">'+data[i].shopName+'</div></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			      });
			}
			initShare();
		});
		
		var share={};
		function initShare(){
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
                            title: columnName, // 分享标题
                            link: columnUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: columnPic, // 分享图标
                            success: function () {
                                
                            }
                        });
	            wx.onMenuShareAppMessage({
					title : columnName, // 分享标题
					desc: '畅游华夏，尽在过来玩-联系电话:0315-6681288/6686299', // 分享描述
					link: columnUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                    imgUrl: columnPic, // 分享图标
					success : function() {
						
					}
				});
	            
	       });
        }
		
		$(document).on('click','.topmod',function(){
	       var codes=this.id.split('-');
	       location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	    
	    });
		
		
		var _uriClasses = window.BASEPATH + 'phoneApp/getRetrievalByModu?modularCode=${modularCode}';
		
		
		
		$.get(_uriClasses, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data){
			   var html=[];
			   html.push('<option value="0">全部</option>');
			   var classes=data[0].children;
			   var items=[];
			   var item1={};
		       item1.title='全部';
		       item1.value=0;
		       items.push(item1);
			   for(var i=0;i<classes.length;i++){
			      var item={};
			      item.title=classes[i].className;
			      item.value=classes[i].classCode;
			      items.push(item);
			      html.push('<option value="'+classes[i].classCode+'">'+classes[i].className+'</option>')
			   }
			   $("#classes").append(html.join(''));
			    /*$("#classes").select({
				  title: "选择分类",
				  items: items,
				  onClose:function(e){
				     getMerchants(e.data.values);
				  }
				});*/
				
				getMerchants(0);
				
			}
			
		});
		
		$(document).on('change','#classes',function(){
			$('.weui-panel__bd').remove();
		   getMerchants($(this).val());
		   $('.chengjie').val($(this).val());
		   $('.flag').val(0);
		   pageNumber=2;
		});
		
		
		function getMerchants(classId){
		        var _uriMechants = window.BASEPATH + 'phoneApp/getMerchantsByModu';
		
		
				var params={};
				params.modularCode='${modularCode}';
				params.page=1;
				if(classId!=0){
					params.retrievals=[];
					params.retrievals.push({"type":"modularClass","value":classId});
				}
				
				$.post(_uriMechants, $.toJSON(params), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					   generateProduct(data);
					}
				});
		}
		
		$(window).scroll(function(){
　　			//判断是否滑动到页面底部
		     if($(window).scrollTop() === $(document).height() - $(window).height()){
				var flag=$('.flag').val();
				if(flag==2){
				   $('.weui-loadmores').fadeIn().addClass("show")
				   setTimeout(function(){$('.weui-loadmores').fadeOut().addClass("show")},2000);
				 
				return;
				}
				if(flag==3)return;
				
		           // TODO 滑动到底部时可请求下一页的数据并加载，加载可使用append方法
				var classId=$('.chengjie').val();
				var page=pageNumber;
				var params={};
				if(classId!=0){
					params.retrievals=[];
					params.retrievals.push({"type":"modularClass","value":classId});
				}
				params.modularCode='${modularCode}';
				params.page=page;
				$.post('<%=basePath%>phoneApp/getMerchantsByModu', $.toJSON(params), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					  getLine(data);
					 $('.weui-loadmore').fadeIn().addClass("show")
					setTimeout(function(){$('.weui-loadmore').fadeOut().addClass("show")},3000);
					pageNumber+=1;
						}
					});
			     }
			});


		
		
		$(document).on('click','.merchant',function(){
	       var codes=this.id.split('-');
	       location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	       $('.flag').val(1);
	    });
	    
	    $(document).on('click','.merchant1',function(){
	       var codes=this.id.split('-');
	       location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	       $('.flag').val(1);
	    });
		
		function getLine(modal){
	        var html=[];
           var merchants=modal.merchants;
           if(merchants.length==0){
           		$('.flag').val(2);
              return;
           }
	       //后面的
	       html.push('<div style="margin-top:5px;padding-bottom:50px;" class="weui-panel__bd" class="columnRecomments">');
		   for(var j=0;j<modal.merchants.length;j++){
		   	   html.push('<div class="weui-panel__bd">');
						   html.push('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg merchant" id="merchant-'+merchants[j].id+'">');
						   html.push('<div class="weui-media-box__hd">');
						   html.push('<img style="width:100%;height:100%" class="weui-media-box__thumb" src="'+merchants[j].shopHeading+'">');
						   html.push('</div>');
						   html.push('<div class="weui-media-box__bd">');
						   html.push('<h4 class="weui-media-box__title">'+merchants[j].shopName+'</h4>');
						   html.push('<p class="weui-media-box__desc">'+merchants[j].shopAddress+'</p>');
						   html.push('<div style="font-size:12px;height:20px"></div>');
						   html.push('</div>');
						   html.push('</a>');
					       html.push('</div>');
		   }
		   html.push('</div>');

	        $('#content').append(html.join(''));
	    }
		
		function generateProduct(modal){
	        var html=[];
	        $('.allMer').remove();
           var merchants=modal.merchants;
           if(merchants.length==0){
              return;
           }
           html.push('<div style="width:100%;margin-top:30px;" class="allMer">');
	       html.push('<div style="width:100%;font-weight:bold;padding-left:12px;height:45px;">'+merchants[0].shopName+'</div>');
	       html.push('<div style="width:90%;float:left;font-size:12px;padding-left:12px;">'+merchants[0].shopAddress+'</div>');
	       html.push('<div style="width:10%;float:right;font-size:12px;padding-right:12px;"></div>');
              html.push('<div style="width;100%;margin-left:12px;margin-top:15px;" class="merchant1" id="merchant1-'+merchants[0].id+'">');
              var morePics=merchants[0].shopMpic.split(',');
              html.push('<image style="width:32%;height:70px;float:left;" src="'+morePics[0]+'"/>');
	       html.push('<image style="width:32%;height:70px;float:left;margin-left:1%" src="'+morePics[1]+'"/>');
	       html.push('<image style="width:32%;height:70px;float:left;margin-left:1%" src="'+morePics[2]+'"/>');
	       html.push('</div>');
	       html.push('</div>');
	       //后面的
	       html.push('<div style="margin-top:5px;padding-bottom:50px;" class="weui-panel__bd allMer" class="columnRecomments">');
		   for(var j=1;j<modal.merchants.length;j++){
					       html.push('<div class="weui-panel__bd">');
						   html.push('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg merchant" id="merchant-'+merchants[j].id+'">');
						   html.push('<div class="weui-media-box__hd">');
						   html.push('<img style="width:100%;height:100%" class="weui-media-box__thumb" src="'+merchants[j].shopHeading+'">');
						   html.push('</div>');
						   html.push('<div class="weui-media-box__bd">');
						   html.push('<h4 class="weui-media-box__title">'+merchants[j].shopName+'</h4>');
						   html.push('<p class="weui-media-box__desc">'+merchants[j].shopAddress+'</p>');
						   html.push('<div style="font-size:12px;height:20px"></div>');
						   html.push('</div>');
						   html.push('</a>');
					       html.push('</div>');
		   }
		   html.push('</div>');

	        $('#content').append(html.join(''));
	    
	    }
		
	
	    $(document).on('click','.weui-search-bar__box',function(){
	   
	       location.href=window.BASEPATH + 'pubnum/search?content='+$('#searchInput').val();
	   
	   });
	});
	
		
</script>



<body>
<input type="text" class="chengjie" value="" hidden="hidden"> 
<input type="text" class="flag" value="" hidden="hidden">
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">栏目</div>
			</div>
		</div>
		
		<div id="content" class="content">
			<div class="swiper-container" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" style="height:200px;">
			  </div>
			</div>
			<div class="weui-cell" style="font-size:14px;">
		        <div class="weui-cell__hd"><label for="name" class="weui-label">选择分类</label></div>
		        <div class="weui-cell__bd">
		          <!-- <input class="weui-input" style="width: 100px" id="classes" type="text" value="全部" readonly="" data-values="0"> -->
		          <select id="classes" style="width:100px;height:30px;line-height:30px"></select>
		        </div>
	       </div>
	       
		</div>
	</div>
	
	<div class="weui-loadmore" hidden="hidden" style="position:fixed;bottom: 5%;left:18%;z-index: 10000">
			  <i class="weui-loading"></i>
			  <span class="weui-loadmore__tips">正在加载</span>
	</div>
	<div class="weui-loadmores" hidden="hidden" style="position:fixed;bottom: 7%;left:50%;margin-left:-40px;z-index: 10000">
			  <span class="weui-loadmore__tips">没有内容了</span>
	</div>		
	<div style="height:50px;width:100%;"></div>	
</body>
</html>