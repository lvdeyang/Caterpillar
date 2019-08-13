<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()

			+ path + "/";

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



<title>商户主页</title>



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

    

    .circle{

    width:50px;

    height:50px;

    border-radius:50%;

    font-size:25px;

    color:#000;

    line-height: 50px;

    text-align:center;

    background:#fff

}





</style>



</head>



<!-- 公共脚本引入 -->

<jsp:include page="../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script src='https://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>

<script type="text/javascript">



	$(function() {
	
		pushroomoption();

	  window.BASEPATH = '<%=basePath%>';

	  var parseAjaxResult = function(data){

			if(data.status !== 200){

				$.toptip(data.message, 'error');

				return -1;

			}else{

				return data.data;		

			}

	  };

		

		

     var _uriModal = window.BASEPATH + 'phoneApp/getModulars?comCode=0001';

		

		$.get(_uriModal, null, function(data){

			data = parseAjaxResult(data);

			if(data === -1) return;

			var modals=data.modulars;

			if(modals && modals.length>0){

			    var html=[];

				for(var i=0; i<modals.length; i++){

				   

				   if(i%5==0){

				     html.push('<tr>');

				   }

				   html.push('<td>');

		           html.push('<image class="modal" id="modal-'+modals[i].modularCode+'" style="width:80%;height:55px" src="'+modals[i].modularPic+'"/>');

		           html.push('<div>'+modals[i].modularName+'(<span id="merchant-'+modals[i].modularCode+'">0</span>)</div>');

		           html.push('</td>');

		           if(i%5==4){

				     html.push('<tr>');

				   }

				  myProductCount(modals[i].modularCode);

				   

				}

				 $('#columnTable').append(html.join(''));

				

			}

			

		});

		

		$(document).on('click','.modal',function(){

		    var ids=this.id.split('-');

		    location.href=window.BASEPATH + 'pubnum/admin/product?code='+ids[1];

		});

		

		function myProductCount(id){

		   var _uriMyProductCount = window.BASEPATH + 'pubnum/getMerchantProCount?code='+id;

		

			$.get(_uriMyProductCount, null, function(data){

				data = parseAjaxResult(data);

				if(data === -1) return;

				

				if(data){

				    $('#merchant-'+id).html(data);

				}else{

				    $('#merchant-'+id).html(0);

				}

			});

		}

		

		function getOrderCount(type){

		

		    var _uriMyProductCount = window.BASEPATH + 'pubnum/getOrderCount?type='+type;

		

			$.get(_uriMyProductCount, null, function(data){

				data = parseAjaxResult(data);

				if(data === -1) return;

				 

				if(data){

				    if(type=='NOTPAY'){

						$('#nopay').html('<font color=green>'+data+'</font>'); 

				    }

				    else if(type=='PAYSUCCESS'){

				       $('#payed').html('<font color=green>'+data+'</font>');

				    }

				    else if(type=='TESTED'){

				       $('#yd').html('<font color=green>'+data+'</font>');

				    }

				    else if(type=='DELIVER'){

				       $('#deliver').html('<font color=green>'+data+'</font>');

				    }

				    else if(type=='REFUNDING'){

				       $('#refunding').html('<font color=green>'+data+'</font>');

				    }

				    else if(type=='REFUNDED'){

				       $('#refunded').html('<font color=green>'+data+'</font>');

				    }

				    else if(type=='RECEIPT'){

				       $('#receipt').html('<font color=green>'+data+'</font>');

				    }else if(type=='COMMENTED'){

				       $('#comment').html('<font color=green>'+data+'</font>');

				    }

				   

				}

			});

		}

		

		setInterval(function(){ 



            getOrderCount("NOTPAY");

			getOrderCount("PAYSUCCESS");

			getOrderCount("TESTED");

			getOrderCount("DELIVER");

			getOrderCount("REFUNDING");

			getOrderCount("REFUNDED");

			getOrderCount("RECEIPT");

			getOrderCount("COMMENTED");

            

        }, 1000);

		

	

	    

	    var paras={};

	    

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

		                    ydNow(result);

		                  

		                }

		            });

	            

	        });

       }

	

	    function ydNow(orderNo){

	       var _uriYd = window.BASEPATH + 'phoneApp/order/ydNow';

		   var params={};

		   params.userId=${muserId};

		   params.orderNo=orderNo;

			$.post(_uriYd, $.toJSON(params), function(data){

				data = parseAjaxResult(data);

				if(data === -1) return;

				

				if(data){

				    if(data=='TESTED'){

				       $.toast("验单通过");

				       setTimeout(function(){

				   

					       location.href=window.BASEPATH + "pubnum/admin/orderinfo?orderId="+orderNo;

					   

					   },1000);

				    }else{

				       $.toast("验单未通过");

				    }

				}

			});

	    }

	

	

	   

	   $(document).on('click','#scan',function(){

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

	   

 	  $(document).on('click','#closeadmin',function(){

	  

		$.confirm("确定退出？", function() {

			location.href=window.BASEPATH + 'pubnum/admin/logout';

		});

			

	  }); 

	   

	   function crtTimeFtt(val) {

		    if (val != null) {

		            var date = new Date(val);

		            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();

		        }

		}

	   

	   var _uriBalance = window.BASEPATH + 'pubnum/admin/balance';

	    $.get(_uriBalance, null, function(data){

			data = parseAjaxResult(data);

			if(data === -1) return;

			if(data){

			    

				$('#menu').html(data.total);

				var html=[];

				for(var i=0;i<data.balances.length;i++){

				        html.push('<a class="weui-cell weui-cell_access" href="javascript:;">');

					    html.push('<div class="weui-cell__bd">');

					    /* crtTimeFtt(new Date(parseInt())) */

					    html.push('  <p>'+ data.balances[i].settleDate+'</p>');

					    html.push('</div>');

					    html.push('<div class="weui-cell__ft">￥'+data.balances[i].amount);

					    html.push('</div>');

					    html.push('</a>');

				}

				$('#balanceContent').children().remove();

				$('#balanceContent').append(html.join(''));

				

			}

			

		});

		

		

	

	});

	

	

	$(document).on('click','#uploadImages',function(){	  
         var myName="${merchantId}"; 
		 location.href=window.BASEPATH + 'face/recognition?merchantid='+myName;

	});
	$(document).on('click','#manage',function(){	  
         var myName="${merchantId}"; 
		 location.href=window.BASEPATH + 'reservetable/mobile/tables?merchantId='+myName;

	});
	
	$(document).on('click','#roomoption',function(){	  
		 location.href=window.BASEPATH + 'admin/room/gotoroomoption?merchantId=${merchantId}';
	});

	//判断有没有房间管理
	function pushroomoption(){
		var url=window.BASEPATH + 'admin/room/isroomoption';
		$.post(url,{"merchantId":${merchantId}},function(msg){
			if(msg=="success"){
				var html=[];
				 html.push('<a id="roomoption" class="weui-cell weui-cell_access" href="javascript:void(0); >');
				 html.push('   <div class="weui-cell__bd">');
				 html.push('     <p>房间管理</p>');
				 html.push('   </div>');
				 html.push('   <div class="weui-cell__ft">');
				 html.push('   </div>');
				 html.push(' </a>');
				 $('#operation').append(html.join(''));
			}
		})
	}

	

		

	

	

	

	



</script>







<body>

	<div id="page">

		<!-- 主页 -->

		<div class="header">

			<div class="wrapper">

				<a class="link-left" href="#side-menu"><span

					class="icon-reorder icon-large"></span></a>

				<div class="header-content">商户</div>

			</div>

		</div>

		<div class="content">

			<div style="width:100%;height:100px;">

			    <a style="margin-top:20px;" href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">

			      <div class="weui-media-box__hd">

			        <img style="border-radius:50%;width:60px;height:60px;" class="weui-media-box__thumb" src="${merchant.shopPic}">

			      </div>

			      <div class="weui-media-box__bd">

			        <h4 class="weui-media-box__title" style="font-size:12px;">${merchant.shopName}</h4>

			        <p class="weui-media-box__desc" style="font-size:12px;">待结账金额:￥<span id="menu">0</span></p>

			      </div>

    		    </a>

			</div>

			

			<div class="weui-cells__title">用户操作</div>

			<div class="weui-cells" id="operation">

			  <a id="scan" class="weui-cell weui-cell_access" href="javascript:void(0);">

			    <div class="weui-cell__bd">

			      <p>扫码验单</p>

			    </div>

			    <div class="weui-cell__ft">

			    </div>

			  </a>

			  

			  <a id="scan" class="weui-cell weui-cell_access" href="pubnum/admin/olchat">

			    <div class="weui-cell__bd">

			      <p>在线咨询</p>

			    </div>

			    <div class="weui-cell__ft">

			    </div>

			  </a>



			  <a id="numchecklist" class="weui-cell weui-cell_access" href="pubnum/admin/numchecklist/${muserId}" >

			    <div class="weui-cell__bd">

			      <p>订单号验单</p>

			    </div>

			    <div class="weui-cell__ft">

			    

			    </div>

			  </a>

			  

			  <a class="weui-cell weui-cell_access">

			    <div class="weui-cell__bd">

			      <p id="closeadmin">退出登录</p>

			    </div>

			    <div class="weui-cell__ft">

			    </div>

			  </a>

			  

			  <a class="weui-cell weui-cell_access" href="distributor/selladminorder/index">

			    <div class="weui-cell__bd">

			      <p>分销订单</p>

			    </div>

			    <div class="weui-cell__ft">

			    </div>

			  </a>

			  

			 <a id="uploadImages" class="weui-cell weui-cell_access" href="javascript:void(0);">

			    <div class="weui-cell__bd">

			      <p>人脸识别</p>

			    </div>

			    <div class="weui-cell__ft">

			    </div>

			  </a>

			  

			</div>

			<div class="weui-cells__title">商品管理</div>

			<table id="columnTable">

		 

		    </table>

			<div class="weui-cells__title">订单管理</div>

			<div class="weui-cells">

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/NOTPAY">

			    <div class="weui-cell__bd">

			      <p>未支付</p>

			    </div>

			    <div id="nopay" class="weui-cell__ft"><span class="circle">0</span>

			    </div>

			  </a>

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/PAYSUCCESS">

			    <div class="weui-cell__bd">

			      <p>已支付</p>

			    </div>

			    <div id="payed" class="weui-cell__ft"><span class="circle">0</span>

			    </div>

			  </a>

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/DELIVER">

			    <div class="weui-cell__bd">

			      <p>已发货</p>

			    </div>

			    <div id="deliver" class="weui-cell__ft"><span class="circle">0</span>

			    </div>

			  </a>

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/REFUNDING">

			    <div class="weui-cell__bd">

			      <p>申请退款</p>

			    </div>

			    <div id="refunding" class="weui-cell__ft"><span class="circle">0</span>

			    </div>

			  </a>

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/REFUNDED">

			    <div class="weui-cell__bd">

			      <p>已退款</p>

			    </div>

			    <div id="refunded" class="weui-cell__ft"><span class="circle">0</span>

			    </div>

			  </a>

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/TESTED">

			    <div class="weui-cell__bd">

			      <p>已验单</p>

			    </div>

			    <div class="weui-cell__ft" id="yd"><span class="circle">0</span>

			    </div>

			  </a>

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/RECEIPT">

			    <div class="weui-cell__bd">

			      <p>已收货</p>

			    </div>

			    <div class="weui-cell__ft" id="receipt"><span class="circle">0</span>

			    </div>

			  </a>

			  <a class="weui-cell weui-cell_access" href="pubnum/admin/order/COMMENTED">

			    <div class="weui-cell__bd">

			      <p>已评论</p>

			    </div>

			    <div class="weui-cell__ft" id="comment"><span class="circle">0</span>

			    </div>

			  </a>
			  
			  <a class="weui-cell weui-cell_access"  id="manage">

			    <div class="weui-cell__bd">

			      <p>订桌管理</p>

			    </div>

			    <div class="weui-cell__ft" id="comment">

			    </div>

			  </a>

			</div>

			<div class="weui-cells__title">打款记录</div>

			<div class="weui-cells" id="balanceContent">

			 

			</div>

			

			

		</div>

	</div>

</body>





</html>