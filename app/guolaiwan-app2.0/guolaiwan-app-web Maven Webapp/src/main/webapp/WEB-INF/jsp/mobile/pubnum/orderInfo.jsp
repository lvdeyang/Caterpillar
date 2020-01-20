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

<title>订单详情</title>

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
    
    

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>

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
		

      var _uriRecomment = window.BASEPATH + 'phoneApp/orderInfo?orderId=${orderId}';
		
		$.get(_uriRecomment, null, function(data){ 
			data = parseAjaxResult(data);
			if(data === -1) return;
			
			if("积分"==data.order.payMode){ //判断是否是积分订单
			    if(data.order.productPic.indexOf('null')==-1){
			       $('#proPic').attr('src',data.order.productPic);
			    }
			    $('#proName').html(data.order.productName);
			    $('#proPrice').html(data.order.productPrice*100);
			    $('#amount').val(data.order.orderAllMoney*100);
			    $('#payAmount').val(data.order.payMoney*100);
			    $('#ydImage').attr('src',data.order.ydNO);
			    $('#largeYd').attr('src',data.order.ydNO);
			    $('#orderNo').html(data.order.id);
			    $('#orderDate').html(data.order.payDate);
			    $('#combo').html(data.order.comboName);
			    $('#logistics').html(data.order.logisticsName);
			    $('#cuserName').html(data.address.consigneeName);
			    $('#cphone').html(data.address.consigneePhone);
			    $('#bookspan').html(data.order.orderBookDate);
			    $('#startspan').html(data.order.orderBookDate);
			    $('#endspan').html(data.order.endBookDate);
			    $('#caddress').html(data.address.province+data.address.city+data.address.district+'&nbsp;&nbsp;&nbsp;&nbsp;'+data.address.consigneeAddress);
			
			    
			    
 			    if(data.order.bkCode=='0002'){
			    	$('#startDate').show();
			    	$('#endDate').show();
			    }else if(data.order.bkCode=='0003'||data.order.bkCode=='0001'){
			    	$('#bookDate').show();
			    } 
			     return;
			}
			
			if(data){
			    if(data.order.productPic.indexOf('null')==-1){
			       $('#proPic').attr('src',data.order.productPic);
			    }
			    
			    
			    if(data.order.productName == "")
			    {	
			    	$('#proName').html('到店支付');
			    }else{
			    	$('#proName').html(data.order.productName);
			    	$('#proPrice').html('￥'+data.order.productPrice);
			    	$('#proCount').html('x'+data.order.productNum);
			    }
			    $('#amount').val('￥'+data.order.orderAllMoney);
			    $('#payAmount').val('￥'+data.order.payMoney);
			    var str = data.order.ydNO;
			    var reg = RegExp(/票号/);
				if(str.match(reg)){
			 		$("#ydDiv").hide(); 
			 		$("#combo").html(str);
				}else{
					$('#ydImage').attr('src',data.order.ydNO);
					$('#combo').html(data.order.comboName);
				}
			    $('#largeYd').attr('src',data.order.ydNO);
			    $('#orderNo').html(data.order.id);
			    $('#orderDate').html(data.order.orderBookDate);
			    $('#logistics').html(data.order.logisticsName);
			    $('#cuserName').html(data.address.consigneeName);
			    $('#cphone').html(data.address.consigneePhone);
			    $('#bookspan').html(data.order.orderBookDate);
			    $('#startspan').html(data.order.orderBookDate);
			    $('#endspan').html(data.order.endBookDate);
			    $('#caddress').html(data.address.province+data.address.city+data.address.district+'&nbsp;&nbsp;&nbsp;&nbsp;'+data.address.consigneeAddress);				        
			   if( data.order.orderState=="已发货"){
			   var html="";
	          
			    html=html+"<div class='weui-cell__hd'><label class='weui-label'>快递单号:</label></div>"; 
			    html=html+" <div class='weui-cell__bd'><span style='font-size:12px;' id='caddress'>"+data.order.trackingnumber+"</span></div>"
			  	$("#weui-cell-1").html(html);
         
			}
 			    if(data.order.bkCode=='0002'){
			    	$('#startDate').show();
			    	$('#endDate').show();
			    }else if(data.order.bkCode=='0003'||data.order.bkCode=='0001'){
			    	$('#bookDate').show();
			    } 
			}
			
		});
	
	   $(document).on('click','#ydImage',function(){
	      $("#large").popup();
	   
	   });
	   
       function changeStatus(){
       
         setTimeout(function(){ 
	          $.get(window.BASEPATH +"pubnum/order/status?orderId=${orderId}", null, function(data){
		
			    if(data.data=="TESTED"){
			       $.toast("已验单");
			    }else{
			      changeStatus();
			    }
		
	         });
          }, 1000);
       }
   
   
	    changeStatus();
          
	      

      
	   
	
	
	});
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">订单详情</div>
			</div>
		</div>
		<div class="content">
			
			<div class="weui-panel__bd" style="padding-bottom:50px;">
				    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
				      <div class="weui-media-box__hd">
				        <img id="proPic" style="width:60px;height:60px;" class="weui-media-box__thumb" src="">
				      </div>
				      <div class="weui-media-box__bd">
				        <h4 class="weui-media-box__title" id="proName"></h4>
				        <p class="weui-media-box__desc"><span id="proPrice"></span><span id="proCount"></span></p>
				      </div>
				    </a>
				    
				    <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">订单总额</label></div>
					    <div class="weui-cell__bd">
					      <input id="amount"  class="weui-input" value="" disabled type="text" placeholder="">
					    </div>
					  </div>
					 <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">实付总额</label></div>
					    <div class="weui-cell__bd">
					      <input id="payAmount" class="weui-input" value="" disabled type="text" placeholder="">
					    </div>
					  </div>
					  <div class="weui-media-box__bd" style="display:inline-block;padding:10px 15px;">
					   <p class="yd" style="color: #999999;font-size: 13px;line-height: 1.2;"></p>
					  </div>
					  <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg" style="height:100px">
				      <div id="ydDiv" class="weui-media-box__hd" style="width:100px;height:100px;">
				        <img style="width:100px;height:100px;" class="weui-media-box__thumb open-popup" id="ydImage" src="">
				      </div>
				      
				      <div class="weui-media-box__bd">
				        <!-- <h4 class="weui-media-box__title">订单号:<span id="orderNo"></span></h4> -->
				        <p class="weui-media-box__desc">支付日期:<span id="orderDate"></span></p>
				      </div>
				    </a>
				    
				    
				    <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">订单号:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="orderNo"></span>
					    </div>
					  </div>
					  
					  <div class="weui-cell" style="display:none;" id="bookDate">
					    <div class="weui-cell__hd"><label class="weui-label">使用日期:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="bookspan"></span>
					    </div>
					  </div>
					  
					  <div class="weui-cell" style="display:none;" id="startDate">
					    <div class="weui-cell__hd"><label class="weui-label">入住日期:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="startspan"></span>
					    </div>
					  </div>
					  
					  <div class="weui-cell" style="display:none;" id="endDate">
					    <div class="weui-cell__hd"><label class="weui-label">离店日期:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="endspan"></span>
					    </div>
					  </div>
					  
					  <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">套餐:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="combo"></span>
					    </div>
					  </div>
					  
					  <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">快递:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="logistics"></span>
					    </div>
					  </div>
					  
					  <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">联系人:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="cuserName"></span>
					    </div>
					  </div>
					  <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">手机号:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="cphone"></span>
					    </div>
					  </div>
					  <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">详细地址:</label></div>
					    <div class="weui-cell__bd">
					         <span style="font-size:12px;" id="caddress"></span>
					    </div>
					  </div>
					  <div class="weui-cell" id="weui-cell-1">
					 
					  </div>
				    
		     </div>
		     
			
			
		</div>
	</div>
	
	<div id="large" class="weui-popup__container" style="padding-bottom:50px;">
	  <div class="weui-popup__overlay"></div>
	  <div class="weui-popup__modal">
	      <image id="largeYd" style="width:100%;height:350px;margin-top:10px;"/>
	      <a style="width:96%;margin-top:50px;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary close-popup">
	      关闭</a>
	</div>
</div>
	
</body>


</html>