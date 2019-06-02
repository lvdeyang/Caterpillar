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

<title>商品详情</title>

<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
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
	padding: 0;
	margin: 0;
	height: 200px;
}

.swiper-container img {
	display: block;
	width: 100%;
}

#distributeList {
	margin-top: 10px;
	padding-left: 10px;
	border-bottom: solid 2px #18b4ed;
	width: 100%;
	height: 35px;
}

#distributeList a {
	text-decoration: none;
	color: #CCC;
	font-size: 12px;
}

#distributeList a.current {
	text-decoration: none;
	color: #18b4ed;
	font-size: 20px;
}

#columnTable {
	width: 100%;
	margin-top: 10px;
}

#columnTable td {
	width: 20%;
	text-align: center;
	font-size: 12px;
}

.alert {
	width: 90%;
	text-align: center;
	color: #fff;
	margin: 10px auto;
	border-radius: 5px;
	line-height: 30px;
	cursor: pointer;
	background: #4ab819;
}

.confirm {
	background: #196fb8;
}

.open {
	background: #f88408;
}

.toast {
	background: #f80851;
}

.later {
	background: #a9a9a9;
}


#proContent img{
    width:250px;
    height:180px;
}
#proContent image{
    width:250px;
    height:180px;
}
 #roomList{
       width:100%;
    }
#roomList div{
   width:80px;
   height:80px;
   background:green;
   float:left;
   margin-left:7px;
   margin-top:5px;
   text-align:center;
   color:#FFF;
   font-size:11px;
}

input[type="datetime-local"]:before{
    content: attr(placeholder);
    color:red;
}
::-webkit-input-placeholder {
    color:red;
}
.names::-webkit-input-placeholder {
    color:#18b4ed;
    font-size:14px;
     text-align: center;
     margin:0 auto;
}
.idnums::-webkit-input-placeholder {
    color:#18b4ed;
     font-size:14px;
     text-align: center;
}
.weui-cell {
    padding: 20px 15px;
     top:30px;
}

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<!-- <script src="../../../../layui/lib/alert/css/alertstyle.css"></script> -->
<script src="../../../../layui/lib/alert/js/jquery-1.7.1.min.js"></script>
<script src="../../../../layui/lib/alert/js/ui.js"></script>
<link href="../../../../layui/lib/alert/css/alertstyle.css"
	rel="stylesheet" />
<script type="text/javascript">

    var base;	
	$(function() {
	  $(document).on('focus','.mydate',function(){
	      $(this).removeAttr('placeholder');
	  });
	  $(document).on('blur','.mydate',function(){
	      if($(this).val()==''){
	        $(this).addAttr('placeholder');
	      }
	  });
	
	
	  window.BASEPATH = '<%=basePath%>';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
		
		
	  var productName;
	  var productPic;
	  var productUrl;
	  var phone='';
      var iscollect=0;
      var qq="";
      var productModular=0;
      var photos={};
      var ifFace=0;
      var product;
		//获取所有一级推荐
      var _uriRecomment = window.BASEPATH + 'phoneApp/productInfo?productId=${id}&userId=${userId}';
		
		$.get(_uriRecomment, null, function(data){
			data = parseAjaxResult(data);
			productName = data.product.productName + '-过来玩';
			productPic = data.product.productShowPic;
			productUrl = window.location.href;
			product =  data.product.productModularCode;
			if(data === -1) return;
			if(data){
			    var html=[];
			    var demo = 11.50*3;
			    var demo1 = demo%1;
			    var pics=data.product.productMorePic.split(',');
			    productModular=data.product.productModularCode;
				for(var i=0; i<pics.length; i++){
					html.push('<div style="height:200px;" class="swiper-slide"><img style="height:200px" src="'+pics[i]+'" alt=""></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
			    
			    if(data.product.productPrice*100==0){
			       $('#buy').hide();
			       $('#addOrder').hide();
			       $('#tel').show();
			    }
			    if(data.product.productModularCode=='0002')
			    {
			    	$('#choosediv').show();
			    	$('#startdiv').show();
			    	$('#enddiv').show();
			    	$('#selRoomDiv').show();
			    	$('#logisticDiv').hide();
			    }else if(data.product.productModularCode=='0003'||data.product.productModularCode=='0001')
			    {
			    	$('#choosediv').show();
			    	$('#bookdiv').show();
			    }
			    
			    phone=data.shopTel;
			    
			    $('.header-content').html(data.product.productName);
			    $('#proName').html(data.product.productName+'￥<span id="price">'+data.product.productPrice+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="text-decoration:line-through">￥'+data.product.productOldPrice+'</span>');
			    $('#proContent').html(data.product.productIntroduce);
			    $('#total').html((data.product.productPrice*${productRestrictNumber}).toFixed(2));
			    $('#proShowNum').html('销量'+data.product.productShowNum);
			    $('#proStock').html('库存'+data.product.productStock);
			    $('#address1').html('<a href="https://apis.map.qq.com/uri/v1/routeplan?type=drive&to='+data.merchant.shopAddress+'&tocoord='+data.merchant.shopLongitude+','+data.merchant.shopLatitude+'&policy=1&referer=2FNBZ-52HR4-OHEUW-XT2S7-ZJABQ-OJFIJ"><i class="icon-map-marker"></i>&nbsp;&nbsp;&nbsp;&nbsp;'+data.merchant.shopAddress+'</a>');
				$('#addressphone1').html('<span class="icon-mobile-phone"></span>&nbsp;&nbsp;&nbsp;&nbsp;'+data.merchant.shopTel);
			    $('#addressphone1').data('phone',data.merchant.shopTel);
			    generateComment(data.comments , data.userimgs , data.useridlist)
			    iscollect=data.product.ifcollection;
			    qq=data.product.ifcollection;
			    ifFace=data.product.ifFace;
			    
			   
				
			    var t = new Date();
				var t_s = t.getTime();
				t.setTime(t_s + 2000 * 60);
				
				 //获取第二天十二点的时间  4/25修改 为了让离店时间变成第二天12点
			    const start = new Date(new Date(new Date().toLocaleDateString()).getTime()+36*60*60*1000);
				/* var t1 = new Date();
				var t1_s = t1.getTime();
				t1.setTime(t1_s + 2000 * 60+60*60*1000*24); */
				var bookdiv =document.getElementById("bookdiv").style.display;       
				var startdiv =document.getElementById("startdiv").style.display;  
				if(bookdiv!='none'){
				    $("#bookDate").val(dateFtt('yyyy-MM-dd hh:mm',t));
	  			    
				}else{
				    $("#startDate").val(dateFtt('yyyy-MM-dd hh:mm',t));
	  			    $("#endDate").val(dateFtt('yyyy-MM-dd hh:mm',start));
				}
			   
	  			
			    $("#bookDate").datetimePicker();
	            $("#startDate").datetimePicker();
	            $("#endDate").datetimePicker();
			    if(iscollect==1){
			    
			       $('#fav').html('取消收藏');
			    }
			    
			    initCombos(data.combos,data.product.productPrice);
			    initLogistics(data.logistics);
			}
			initShare();
			
		});
		
		
		function dateFtt(fmt,date)   
		{ //author: meizz   
		  var o = {   
		    "M+" : date.getMonth()+1,                 //月份   
		    "d+" : date.getDate(),                    //日   
		    "h+" : date.getHours(),                   //小时   
		    "m+" : date.getMinutes(),                 //分   
		    "s+" : date.getSeconds(),                 //秒   
		    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
		    "S"  : date.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		} 
		
		
		function initLogistics(data){
		   var html=[];
		   for(var i=0;i<data.length;i++){
		      html.push('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		   }
		   $('#logisticsList').append(html.join(''));
		}
		
		function initCombos(data,price){
		   var html=[];
		   if(data.length==0){
		   		html.push('<option value="0">标准(￥'+price+')</option>');
		   }else{
		        $('#total').html((data[0].comboprice/100).toFixed(2));
		   }
		   
		   for(var i=0;i<data.length;i++){
		      html.push('<option value="'+data[i].id+'-'+(data[i].comboprice/100).toFixed(2)+'">'+data[i].combo+'(￥'+(data[i].comboprice/100).toFixed(2)+')</option>');
		   }
		   $('#comboList').append(html.join(''));
		}
		
		
		
		
		$(document).on('click','#tel',function(){
	       var phones=phone.split('/');
	       location.href ='tel://' + phones[0];
	    });
		
		$(document).on('click','#selRoom',function(){
		    
				var startdiv =document.getElementById("startdiv").style.display;  
				var sDate = new Date(document.getElementById("startDate").value.replace(/-T/g, "/")).getTime();
		   		var eDate = new Date(document.getElementById("endDate").value.replace(/-T/g, "/")).getTime();		
		   		if(sDate >= eDate)
			    {
				     $.toast("离店时间不能小于开始时间", "forbidden");
				     return false;
			    }
			    if(sDate < new Date)
			    {
			    	$.toast("入住时间不能小于当前时间", "forbidden");
				     return false;
			    }
			  
			 if(startdiv!="none")
			  {
			  	if($("#startDate").val()=='')	{
			  		$.toast("请选择入住日期", "forbidden");
			  		return false;
			  	}else if($("#endDate").val()==''){
			  		$.toast("请选择离店日期", "forbidden");
			  		return false;
			  	}     
			  }
		 /* ************************************************************************************************************************ */
		    $('#selAddress').popup(); 
	    	var _uri = window.BASEPATH + 'pubnum/stock/userRooms?productId=${id}&start='+
	    	$("#startDate").val()+'&end='+$("#endDate").val();
		    $.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				       var html=[];
				       if(data.length==0){
				          html.push('<div class="room1" style="background:green">默认</div>');
				       }
					   for(var i=0;i<data.length;i++){
					      var color="green";
					      if(data[i].status==1){
					         color="red"
					      }
					      html.push('<div class="room" id="room-'+data[i].id+'-'+data[i].name+'-'+data[i].status+'" style="background:'+color+'">'+data[i].name+'</div>')
					   
					   }
					   $('#roomList').children().remove();
					   $('#roomList').append(html.join(''));
					   $('.modDiv').hide();
					   $('#roomList').show();
					   
					
				}
			});
		});
		
		
		$(document).on('click','.room',function(){
		      var ids=this.id.split('-');
		      if(ids[3]==1){
		         $.toast("该房间暂时不可选", "forbidden");
		         return false;
		      }
		      $.closePopup();
		      
		      $('#selRoom').val(ids[2]);
		      $('#selRoomId').val(ids[1]);
		});
		$(document).on('click','.room1',function(){
		      
		      $.closePopup();
		      
		      $('#selRoom').val("自动选择");
		      $('#selRoomId').val(0);
		});
		
		
		
		function generateComment(comments , userimgs , useridlist){
		   var html=[];
		   for(var i=0;i<comments.length;i++){
		      html.push('<div class="weui-media-box weui-media-box_text">');
	          html.push('  <h4 class="weui-media-box__title" style="font-size:13px;"><img style="height:20px;wight:20px;" src="'+userimgs[i]+'" />&nbsp;&nbsp;&nbsp;&nbsp;'+comments[i].userName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:11px;">'+comments[i].userDate+'</span></h4>');
	          if(${userId} == useridlist[i]){
	          	html.push('  <p class="weui-media-box__desc">'+comments[i].content+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="javascript:deletepl('+comments[i].id+')" id="deletepl">删除</a></p>');
	          }else{
	          	html.push('  <p class="weui-media-box__desc">'+comments[i].content+'</p>');
	          }
	          html.push('</div>');
		   }
		   if(comments.length==0){
		      html.push("<div style='width:100%;margin-top:10px;font-size:12px;text-align:center'>暂无数据</div>");
		   }
		   $('#comment').append(html.join(''));
		}
		
		
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
                            title: productName, // 分享标题
                            link: productUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: productPic, // 分享图标
                            success: function () {
                                
                            }
                        });
	            wx.onMenuShareAppMessage({
					title : productName, // 分享标题
					desc: '畅游华夏，尽在过来玩-联系电话:0315-6681288/6686299', // 分享描述
					link: productUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                    imgUrl: productPic, // 分享图标
					success : function() {
						
					}
				});
	            
	       });
        }
		function initDatePrice(){
		      var number = parseInt($('#proCount').val());
			  /* var startDate=$('#startDate').val(); */
			  var startDate = new Date(new Date(new Date().toLocaleDateString()).getTime()+36*60*60*1000);
			  var endDate=$('#endDate').val();
			  var daycount=1;
			  if(startDate&&endDate){
			        startDate = Date.parse(startDate);
			        endDate = Date.parse(endDate);
			        if(endDate!=startDate){
			        var dateSpan = endDate - startDate;
			        var iDays = Math.ceil(dateSpan / (24 * 3600 * 1000));
			        }
			        if(iDays>0){
			          daycount+=iDays;
			        }
			         $("#payMoney").val(daycount);
			  }
			  if($('#comboList').val()==0){
			  	 $('#total').html(($('#price').html()*number*daycount).toFixed(2));
			  }else{
			     var ids=$('#comboList').val().split('-');
			     $('#total').html((ids[1]*number*daycount).toFixed(2));
			  }
		}
		
		$(document).on('change','#startDate',function(){
			  initDatePrice();
			
		});
		
		$(document).on('change','#endDate',function(){
		
		      initDatePrice();
		});
		
		
		
		
		
	    $(document).on('click','#contact',function(){
	      openqq(qq);
	    
	    });
		$(document).on('click','#contact1',function(){
	      openqq(qq); 
	      location.href="http://wpa.qq.com/msgrd?v=3&uin="+qq+"&site=qq&menu=yes";
	    });
	    $(document).on('click','#addressphone1',function(){
	       var phones=$('#addressphone1').data('phone').split('/');
	       location.href = 'tel://' + phones[0];
	    });
	    //进入店铺
	    $(document).on('click','#gotoshop',function(){
	    	location.href=window.BASEPATH + 'pubnum/gotoshop?productId=${id}';
	    });	    
	    
	    $(document).on('click','#fav',function(){
	        
	        if(iscollect==1){
	            var param={};
		        param.proId=${id};
		        param.userId=${userId};
		        param.activityproductId=0;
	            var _urifav = window.BASEPATH + 'pubnum/delCollectionPro';
				$.post(_urifav, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					$.toast("取消收藏成功");
	                $('#fav').html('收藏');
					iscollect=0;
				});
	        }else{
	           var param={};
		        param.productId=${id};
		        param.userId=${userId};
		        param.activityproductId=0;
	           var _urifav = window.BASEPATH + 'phoneApp/collectionPro';
				$.post(_urifav, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					$.toast("收藏成功");
	                $('#fav').html('取消收藏');
					iscollect=1;
				});
	        
	        }

	    });
	
	
	    var MAX = 99, MIN = ${productRestrictNumber};
		$('.weui-count__decrease').click(function (e) {
		  var $input = $(e.currentTarget).parent().find('.weui-count__number');
		  var number = parseInt($input.val() || "0") - 1
		  if (number < MIN) number = MIN;
		  $input.val(number);
		      /* var startDate=$('#startDate').val(); */
		      var startDate = new Date(new Date(new Date().toLocaleDateString()).getTime()+36*60*60*1000);
			  var endDate=$('#endDate').val();
			  var daycount=1;
			  if(startDate&&endDate){
			        startDate = Date.parse(startDate);
			        endDate = Date.parse(endDate);
			        var dateSpan = endDate - startDate;
			        iDays = Math.ceil(dateSpan / (24 * 3600 * 1000));
			        if(iDays>0){
			          daycount=iDays+1;
			        }
			         $("#payMoney").val(daycount);
			  }
		  if($('#comboList').val()==0){
		  	 $('#total').html(($('#price').html()*number*daycount).toFixed(2));
		  }else{
		     var ids=$('#comboList').val().split('-');
		     $('#total').html((ids[1]*number*daycount).toFixed(2));
		  }
		  initpeopleList();
		});
		$('.weui-count__increase').click(function (e) {
		  var $input = $(e.currentTarget).parent().find('.weui-count__number');
		  var number = parseInt($input.val() || "0") + 1
		  if (number > MAX) number = MAX;
		  $input.val(number);
		  
		  /* var startDate=$('#startDate').val(); */
		   //获取第二天十二点的时间 
		  var startDate = new Date(new Date(new Date().toLocaleDateString()).getTime()+36*60*60*1000);
		  var endDate=$('#endDate').val();
		  var daycount=1;
		  if(startDate&&endDate){
		        startDate = Date.parse(startDate);
		        endDate = Date.parse(endDate);
		        var dateSpan = endDate - startDate;
		        var iDays = Math.ceil(dateSpan / (24 * 3600 * 1000));
		        if(iDays>0){
		          daycount+=iDays;
		        }
		         $("#payMoney").val(daycount);
		  }
		  
		  if($('#comboList').val()==0){
		  	 $('#total').html(($('#price').html()*number*daycount).toFixed(2));
		  }else{
		     var ids=$('#comboList').val().split('-');
		     $('#total').html((ids[1]*number*daycount).toFixed(2));
		  }
		  initpeopleList();
		  
		});
		
		$(document).on('change','#comboList',function(){
		    var number = parseInt($('#proCount').val());
		      /* var startDate=$('#startDate').val(); */
			  var endDate=$('#endDate').val();
			  //获取第二天十二点的时间 
			  var startDate = new Date(new Date(new Date().toLocaleDateString()).getTime()+36*60*60*1000);
			  var daycount=1;
			  if(startDate&&endDate){
			        startDate = Date.parse(startDate);
			        endDate = Date.parse(endDate);
			        if(endDate!=startDate){
			        	var dateSpan = endDate - startDate;
			        	var iDays = Math.ceil(dateSpan / (24 * 3600 * 1000));
			        }
			        if(iDays>0){
			          daycount+=iDays;
			        }
			        $("#payMoney").val(daycount);
			  }
			if($('#comboList').val()==0){
			  	 $('#total').html(($('#price').html()*number*daycount).toFixed(2));
			}else{
			     var ids=$('#comboList').val().split('-');
			     $('#total').html((ids[1]*number*daycount).toFixed(2));
			}
		});
		
		var buyOrbasketFlg=0;//0:buy,1:basket;
		
		$(document).on('click','#addOrder',function(){
		
				var bookdiv =document.getElementById("bookdiv").style.display;       
				var startdiv =document.getElementById("startdiv").style.display;  
				var bDate = new Date(document.getElementById("bookDate").value.replace(/-T/g, "/")).getTime();
				var sDate = new Date(document.getElementById("startDate").value.replace(/-T/g, "/")).getTime();
		   		var eDate = new Date(document.getElementById("endDate").value.replace(/-T/g, "/")).getTime();		
		   		if(sDate >= eDate)
			    {
				     $.toast("离店时间不能小于开始时间", "forbidden");
				     return false;
			    }
			    if(sDate < new Date)
			    {
			    	$.toast("入住时间不能早于当前时间", "forbidden");
				     return false;
			    }
			    if(bDate < new Date)
			    {
			    	$.toast("预定时间不能早于当前时间", "forbidden");
				     return false;
			    }
			  if(bookdiv!="none")  
			  {
				  if($("#bookDate").val()==''){
				  	$.toast("请选择预定日期", "forbidden");
				  	return false;
				  }
			  }else if(startdiv!="none")
			  {
			  	if($("#startDate").val()=='')	{
			  		$.toast("请选择入住日期", "forbidden");
			  		return false;
			  	}else if($("#endDate").val()==''){
			  		$.toast("请选择离店日期", "forbidden");
			  		return false;
			  	}     
			  }
		
		
		      if(ifFace==0||productModular!='0001'){
		         joinBasket();
		      }else{
		         $('#selAddress').popup(); 
		         $('.modDiv').hide();
		         $('#cameraDiv').show();
		         buyOrbasketFlg=1;
		      }
		

		});
		
		function joinBasket(){
		    var param={};
			param.productId=${id};
			param.productNum=$('#proCount').val();
			param.userId=${userId};
			param.productRestrictNumber=${productRestrictNumber};
			param.payMoney=$('#payMoney').val();
			param.paytype='WEICHAT';
			param.source="PUBLICADDRESS";
			param.bookDate=$('#bookDate').val();
			param.startDate=$('#startDate').val();
			param.endDate=$('#endDate').val();
			param.source="PUBLICADDRESS";
			param.roomId=$('#selRoomId').val();
			param.roomName=$('#selRoom').val();
			param.logisticsId=$('#logisticsList').val();
			param.comboId=$('#comboList').val().split('-')[0];
			//param.photo=encodeURIComponent(photo);
			//param.idNum=$('#orderIdNum').val();
			
			
			if(ifFace==1){
               var retP;
               if(retP=getIdNums()){
                  param.idnums=retP;
               }
               else{
               
                  return false;
               }
            }
			$.closePopup();
				var chkStockUrl=window.BASEPATH + 'pubnum/stock/check?proId='+${id}+'&count='+$('#proCount').val();
			    
				$.get(chkStockUrl, null, function(data){
						data = parseAjaxResult(data);
						if(data === -1) return;
					if(data.stock==0){
					   $.toast("抱歉，库存不足", "forbidden");
					   return;
					}	
					var _uriPay = window.BASEPATH + 'phoneApp/joinBasket';
				    $.post(_uriPay, $.toJSON(param), function(data){
						data = parseAjaxResult(data);
						if(data === -1) return;
						$.toast("已加入购物车");
					});
				});
		}
		
		
		function initpeopleList(){
		   $('#cameraContent').children().remove();
		   var count=$('#proCount').val();
		   var html=[];
		   for(var i=0;i<count;i++){
		        html.push('<h1 style="font-size:16px;background:#18b4ed;height:50px;line-height:50px;text-align:center;width:100%;color:#fff" class="demos-title">信息采集</h1>');   /* <span>'+(i+1)+'</span> */
		        html.push('<div style="text-align:center;width:100%;" class="weui-cell">');
			    html.push('	 	<div class="weui-cell__hd">');
			   /*  html.push('			<label class="weui-label">姓名</label>'); */
				html.push('	</div>');
				html.push('	<div class="weui-cell__bd">');
				html.push('		<input placeholder="点击上传真实姓名" style="text-align:center;font-weight: bold;border-radius:6px;font-size:14px;outline: none;border:1px solid #E0DABA;width:70%;height:40px;line-height:40px;" class="names" id="name-'+i+'" class="weui-input" type="text"');
				html.push('			placeholder="">');
				html.push('	</div>');
				html.push('</div>');
		        
		        
		        html.push('<div style="text-align:center;width:100%;" class="weui-cell">');
			    html.push('	 	<div class="weui-cell__hd">');
			 /*    html.push('			<label class="weui-label">身份证</label>'); */
				html.push('	</div>');
				html.push('	<div class="weui-cell__bd">');
				html.push('		<input placeholder="点击上传真实身份证信息" style="font-weight: bold;text-align:center;border-radius:6px;font-size:16px;outline: none;border:1px solid #E0DABA;width:70%;height:40px;line-height:40px;" class="idnums" id="orderIdNum-'+i+'" class="weui-input" type="text"');
				html.push('			placeholder="">');
				html.push('	</div>');
				html.push('</div>');
		        html.push('<div style="text-align:center;width:100%;" class="weui-cell">');
			    /* html.push('	<div style="position:absolute;color:#fff;left:50%;margin-left:-52.5px;top:75.5px" class="weui-cell__hd">');
			 	html.push('		<label style="z-index:-2" class="weui-label">上传照片</label>');
				html.push('	</div>'); */
				html.push('	<div class="weui-cell__bd">');
				html.push('<image style="width:60%;height:250px;border-radius:6px;z-index:1111;opacity: 0.7 ;" class="uploadImages" id="uploadImage-'+i+'" src="<%=basePath%>/lib/fishimages/asdasd.jpg"></image>');
				html.push('	</div>');
				html.push('</div>');
			    html.push('</div>');		   
		   }
		   $('#cameraContent').append(html.join(''));
		}
		initpeopleList();
		
		
		//点击输入框识别图片
		$(document).on('click','.idnums',function(){
	       discern();
	     /*  discern(); */
	    });
	    //点击输入框识别图片
		$(document).on('click','.names',function(){
	        discern();	    
	    });
	    
	    function  discern() {
	           //人脸采集部分
		    var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
            var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			$.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				    
					share=data;
					wx.config({
			            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            appId : share.appId, // 必填，公众号的唯一标识
			            timestamp : share.timestamp, // 必填，生成签名的时间戳
			            nonceStr : share.nonceStr, // 必填，生成签名的随机串
			            signature : share.signature,// 必填，签名，见附录1
			            jsApiList : ['chooseImage',
		                        'previewImage',
		                        'uploadImage',
		                        'downloadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	       	        });
                }
            });                        
             wx.ready(function () {
                wx.checkJsApi({
                    jsApiList: [
                        'chooseImage',
                        'previewImage',
                        'uploadImage',
                        'downloadImage'
                    ],
                    success: function (res) {
                      
                        if (res.checkResult.getLocation == false) {
                            alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                            return;
                        }else{
                            choosePicone(this.id);
                        }
                    }
                });
            });
            wx.error(function(res){
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                alert("验证失败，请重试！");
                wx.closeWindow();
            });
	    }
		
		
		function choosePicone(id) {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    $.toast("照片处理中...", "loading");
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    getLocalDataone(localIds[0]);
                }
            });
        }
		
        function getLocalDataone(localid) {
			//获取本地图片资源
            wx.getLocalImgData({
                localId: localid, // 图片的localID
                success: function (res) {
                    var localData = res.localData; // localData是图片的base64数据，可以用img标签显示               
                    var _uri = window.BASEPATH + 'pubnum/IdentityCard';
                    var params = {};
                    params.localData=localData;
			        $.post(_uri, params, function(data){
			           if(data.msg==0){
						   $(".idnums").val(data.name)
						   $(".names").val(data.sfz) 	
						}			   
					     if(data.msg==1){
							  alert("解析失败请重新上传照片")	 		
						 }			        
				    });
                }
            });
 
        }
		
		/* function dsy(){
		   alert(111);      
                    var _uri = window.BASEPATH + 'pubnum/IdentityCard';
                    var params = {};
                    params.localData="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";
			        $.post(_uri, params, function(data){
			           if(data.msg==0){
						   $(".idnums").val(data.name)
						   $(".names").val(data.sfz) 	
						}			   
					     if(data.msg==1){
							  alert("解析失败请重新上传照片")	 		
						 }			        
				    });
		}
		 */
		    function addmessage() {
		            var _uri = window.BASEPATH + 'pubnum/addmessage';
                    var params = {};
                    params.localData=base;
                    params.idnums= $(".idnums").val() ;
                    params.name= $(".names").val() ;
		          $.post(_uri, params, function(data){
			           if(data.msg==0){
						  alert("保存成功");
						}			   
					     if(data.msg==1){
							 alert("保存失败");
						 }			        
				 });       
		    }
		
		
		
		
		$(document).on('click','#buy',function(){
			/* mizhu.alert('', '这是alert效果');  */   
					
			var bookdiv =document.getElementById("bookdiv").style.display;       
			var startdiv =document.getElementById("startdiv").style.display;  
			
			var bDate = new Date(document.getElementById("bookDate").value.replace(/-T/g, "/")).getTime();
			var sDate = new Date(document.getElementById("startDate").value.replace(/-T/g, "/")).getTime();
    		var eDate = new Date(document.getElementById("endDate").value.replace(/-T/g, "/")).getTime();
    		if(sDate >= eDate)
		    {
			     $.toast("离店时间不能小于开始时间", "forbidden");
			     return false;
		    }
		    if(sDate < new Date)
		    {
		    	$.toast("入住时间不能早于当前时间", "forbidden");
			     return false;
		    }
		    if(bDate < new Date)
		    {
		    	$.toast("预定时间不能早于当前时间", "forbidden");
			     return false;
		    }
			  if(bookdiv!="none")  
			  {
				  if($("#bookDate").val()==''){
				  	$.toast("请选择预定日期", "forbidden");
				  	return false;
				  }else{
         			$('#selAddress').popup(); 
         			$('.modDiv').hide();
         			$('#addressFitst').show();
					
				  }
			  }else if(startdiv!="none")
			  {
			  	if($("#startDate").val()=='')	{
			  		$.toast("请选择入住日期", "forbidden");
			  		return false;
			  	}else if($("#endDate").val()==''){
			  		$.toast("请选择离店日期", "forbidden");
			  		return false;
			  	}else{
         			$('#selAddress').popup(); 
         			$('.modDiv').hide();
         			$('#addressFitst').show();
					
			  	}      
			  }else{
			  	$('#selAddress').popup(); 
			  	$('.modDiv').hide();
			  	$('#addressFitst').show();
				
			  }
              
        });
		$(document).on('click','#cancelAddress',function(){
	   		$.closePopup();
	  
	    });
		
			$(document).on('click','.uploadImages',function(){
		    //人脸采集部分
		    var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
            var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			$.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				    
					share=data;
					wx.config({
			            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            appId : share.appId, // 必填，公众号的唯一标识
			            timestamp : share.timestamp, // 必填，生成签名的时间戳
			            nonceStr : share.nonceStr, // 必填，生成签名的随机串
			            signature : share.signature,// 必填，签名，见附录1
			            jsApiList : ['chooseImage',
		                        'previewImage',
		                        'uploadImage',
		                        'downloadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	       	        });
                }
            }); 
            
            
             wx.ready(function () {
                wx.checkJsApi({
                    jsApiList: [
                        'chooseImage',
                        'previewImage',
                        'uploadImage',
                        'downloadImage'
                    ],
                    success: function (res) {
                      
                        if (res.checkResult.getLocation == false) {
                            alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                            return;
                        }else{
                            choosePic(this.id);
                        }
                    }
                });
            });
            wx.error(function(res){
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                alert("验证失败，请重试！");
                wx.closeWindow();
            });
		
		});
		
		function choosePic(id) {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    $.toast("照片处理中...", "loading");
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    getLocalData(localIds[0]);
                }
            });
        }
		
        function getLocalData(localid) {
			//获取本地图片资源
            wx.getLocalImgData({
                localId: localid, // 图片的localID
                success: function (res) {
                    var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
                    base=localData;
                    $('.uploadImages').attr('src','data:image/png;base64,'+localData);
                    photos[id]=localData;
                }
            });
 
        }
		
		//$(document).on('click','.mailAddress',function(){
		$(document).on('click','#buynow',function(){
		    if(ifFace==0||productModular!='0001'){
		    	dobuy();
		    	return;
		    }else{
		       $('.modDiv').hide();
		       $('#cameraDiv').show();
		       buyOrbasketFlg=0;
		    }
		});
		$(document).on('click','#cancelPhoto',function(){
		    $.closePopup();
		});
		$(document).on('click','#confirmPhoto',function(){
		    
		    if(buyOrbasketFlg==1){
		       joinBasket();
		    }else{
		       dobuy();
		    }
		    
		    
		});
		
		function getIdNums(){
		    var idnums=$('.idnums');
		    var ret=[];
		    for(var i=0;i<idnums.length;i++){
		       var idnumobj={};
		      
		       if($(idnums[i]).val()==''){
		            $.toast("身份证号不能为空", "forbidden");
		            return false;
		       }
		      var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
			
		       idnumobj.idNum=$(idnums[i]).val();
		       var ids=$(idnums[i]).attr('id').split('-');
		       
		       if($('#name-'+ids[1]).val()==''){
		            $.toast("姓名不能为空", "forbidden");
		            return false;
		       }
		       
		       
		       idnumobj.photo=encodeURIComponent(photos['uploadImage-'+i]?photos['uploadImage-'+i]:'');
		       idnumobj.name=$('#name-'+ids[1]).val();
		       ret.push(idnumobj);
		    }
		   return ret;
		}
		
		
		function dobuy(){
		    
		    var ids=$('input[type^=radio]:checked').attr('id').split('-');
		    var param={};
			param.productId=${id};
			param.productNum=$('#proCount').val();
			param.payMoney=$('#payMoney').val();
			param.userId=${userId};
			param.paytype='WEICHAT';
			param.source="PUBLICADDRESS";
			param.addressId=ids[1];
			param.bookDate=$('#bookDate').val();
			param.startDate=$('#startDate').val();
			param.endDate=$('#endDate').val();
			param.roomId=$('#selRoomId').val();
			param.roomName=$('#selRoom').val();
			param.logisticsId=$('#logisticsList').val();
		    param.comboId=$('#comboList').val().split('-')[0];
		    //param.photo=encodeURIComponent(photo);
		    //param.idNum=$('#orderIdNum').val();
		    if(ifFace==1){
               var retP;
               if(retP=getIdNums()){
                  param.idnums=retP;
               }else{
                  return false;
               }
            }
            
            
            
            addmessage();
            $.closePopup();
			var chkStockUrl=window.BASEPATH + 'pubnum/stock/check?proId='+${id}+'&count='+$('#proCount').val();
			$.post(chkStockUrl, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
				if(data.stock==0){
				   $.toast("抱歉，库存不足", "forbidden");
				   return;
				}	
				var _uriPay = window.BASEPATH + 'phoneApp/order/add';
				$.post(_uriPay, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					
					$.modal({
						  title: "付款方式",
						  buttons: [
						    { text: "余额支付", onClick: function(){ 
						    	$.confirm("确定支付？", function() {
								    payByWallet(data.orderId);
								  }, function() {});
						    } },
						    { text: "微信支付", onClick: function(){ 
							    $.confirm("确定支付？", function() {
								    payPublic(data.orderId);
								  }, function() {});
						    } },
						    { text: "取消", className: "default", onClick: function(){ } },
						  ]
						});
					

				});
					

			});
		
		}
		
		
		
		function payByWallet(orderId){
			var url=window.BASEPATH+'pubnum/wallet/walletbuy';
			var userId=${userId};
			$.post(url,{'orderId':orderId,'userId':userId},function(data){
						data = parseAjaxResult(data);
				if(data==1){
						$.get(window.BASEPATH +"pubnum/order/status?orderId="+orderId, null, function(data){
						    if(data.data=="PAYSUCCESS"){
						       location.href=window.BASEPATH +"pubnum/order/info?orderId="+orderId;
						    }
						});
				}else{
					$.alert('您的余额不足！');
				}
                   
			})
		}
		
		var prepay_id;
		var paySign;
		var appId;
		var timeStamp;
		var nonceStr;
		var packageStr;
		var signType;
		var orderNo;	
		
			
		function payPublic(orderId){
			$.get(window.BASEPATH +"pubnum/prev/pay/"+orderId, null, function(data){
				prepay_id = data.prepay_id;
		        paySign = data.paySign;
		        appId = data.appId;
		        timeStamp = data.timeStamp;
		        nonceStr = data.nonceStr;
		        packageStr = data.packageStr;
		        signType = data.signType;
		        orderNo = data.orderNo;
		        callpay();
			});
		}
		
	
		function onBridgeReady(){
		    WeixinJSBridge.invoke(
		        'getBrandWCPayRequest', {
		           "appId"     : appId,     //公众号名称，由商户传入
		           "timeStamp" : timeStamp, //时间戳，自1970年以来的秒数
		           "nonceStr"  : nonceStr , //随机串
		           "package"   : packageStr,
		           "signType"  : signType,  //微信签名方式：
		           "paySign"   : paySign    //微信签名
		        },
		        function(res){
		            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		                $.confirm("交易成功");
		                //每五秒刷新订单状态
						
		                setInterval(function(){ 
                                $.get(window.BASEPATH +"pubnum/order/status?orderId="+orderNo, null, function(data){
								    
								    if(data.data=="PAYSUCCESS"){
								       location.href=window.BASEPATH +"pubnum/order/info?orderId="+orderNo;
								    }
								});

                        }, 1000);

		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		                alert("交易取消");  
	
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert(res.err_desc); 

		            }  
		        }
		    );
		}
		function callpay(){
		    if (typeof WeixinJSBridge == "undefined"){
		        if( document.addEventListener ){
		            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		        }else if (document.attachEvent){
		            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		        }
		    }else{
		        onBridgeReady();
		    }
		}
		//编辑地址
		$(document).on('click','#addAddress',function(){
		   if(product=="0001"||product=="0002"){
		     $('#weui,#weui-cell').hide();
		   };
		    $('.modDiv').hide();
		    $('#addressSecond').show();
		});
		$(document).on('click','#save',function(){
		    if($('#address').val()==''&&product!="0001"&&product!="0002"){
			   $.toast("请选择地址", "forbidden");
			   return false;
			}
			if($('#addressphone').val()==''){
			   $.toast("请输入手机号", "forbidden");
			   return false;
			}
			if(!(/^1[34578]\d{9}$/.test($('#addressphone').val()))){ 
		       $.toast("手机号码有误，请重填", "forbidden");  
		       return false; 
		    } 
			if($('#name').val()==''){
			   $.toast("请输入姓名", "forbidden");
			   return false;
			}
		    var _uriAdd = window.BASEPATH + 'phoneApp/address/add';
			var params={};
			params.userId=${userId};
			var addresses=$('#address').val().split(' ');
			params.province=addresses[0];
			params.city=addresses[1];
			params.district=addresses[2];
			params.idNum=$('#idNum').val();
			params.consigneeAddress=$('#moreAddress').val();
			params.addressphone=$('#addressphone').val();
			params.consigneeName=$('#name').val();
			$.post(_uriAdd, $.toJSON(params), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				$('.modDiv').hide();
				$('#addressFitst').show();
		        
                getAllAddr();
			});
	  });
	
      $("#address").cityPicker({
        title: "选择地址",
        onChange: function (picker, values, displayValues) {
          console.log(values, displayValues);
        }
     });
     
     getAllAddr();
     function getAllAddr(){
        var _uriAddress = window.BASEPATH + 'phoneApp/address/list?userId=${userId}';
		
		$.get(_uriAddress, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data && data.length>0){
			    var html=[];
				for(var i=0; i<data.length; i++){
				     var chkattr='';
				     if(i==0){
				         chkattr='checked="checked"';
				     }
					 html.push('<div class="weui-media-box weui-media-box_text mailAddress" id="mailadd-'+data[i].id+'">');
					 html.push('<input style="float:left;height:27px;width:20px"  type="radio" name="radio1" class="" id="radio-'+data[i].id+'" '+chkattr+'>');
			         html.push('<h4 style="width:80%;margin-left:35px;" class="weui-media-box__title">'+data[i].consigneeName+'（'+data[i].consigneePhone+'）</h4>');
			         //html.push('<p class="weui-media-box__desc">身份证'+(data[i].idNum?data[i].idNum:'-')+'</p>');
			         if(data[i].province!=null&&data[i].city!=null&&data[i].district!=null)html.push('<p class="weui-media-box__desc">'+data[i].province+data[i].city+data[i].district+data[i].consigneeAddress+'</p>');
			         html.push('</div>');
				}
				$('#addressList').children().remove();
			    $('#addressList').append(html.join(''));
			}
		});
     }
		
	 
	
	
	});
</script>



<body>
	<script type="text/javascript">
		function deletepl(data){
			var paran={};
			paran.proId=data;
			paran.userId=${userId};
			var _urifav = window.BASEPATH + 'phoneApp/deletepl.do';
			$.post(_urifav , $.toJSON(paran) , function(daat){
				if(daat === "success"){
					$.toast("删除评论成功");
					window.location.reload();
				}else{
					$.toast("该评论不是您的哦");
				}
			});
		}
	</script>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商品详情</div>
			</div>
		</div>
		<div class="content">

			<div class="swiper-container" data-space-between='10'
				data-pagination='.swiper-pagination' data-autoplay="1000">
				<div class="swiper-wrapper"></div>
			</div>
			<div style="width:100%">
				<div
					style="font-size:12px;margin-left:14px;margin-top:15px;width:50%;float:left;"
					id="proName"></div>
				<div
					style="font-size:12px;margin-right:14px;margin-top:15px;width:30%;float:right;text-align:right;margin-right:40px;">
					<i id="fav" class="icon-star">收藏</i>
				</div>
			</div>
			<div
				style="display:none;width:100%;margin-top:15px;margin-left:14px;font-size:12px;float:left;">
				<a id="contact" href="javascript:void(0);" class=" icon-user">联系客服</a>
			</div>
			<div
				style="font-size:12px;margin-left:14px;margin-top:15px;width:50%;float:left;height:50px;line-height:50px;">购买数量</div>
			<div
				style="font-size:12px;margin-right:14px;margin-top:15px;width:30%;float:right;">
				<div class="weui-cell">
					<div class="weui-cell__ft">
						<div class="weui-count">
							<a class="weui-count__btn weui-count__decrease"></a> <input
								disabled="disabled" class="weui-count__number" id="proCount"
								type="number" value="${productRestrictNumber}"> <a
								class="weui-count__btn weui-count__increase"></a>
						</div>
					</div>
				</div>
			</div>
			<div style="width:100%;float:left;">
			<div style="font-size:12px;padding:12px;float:left;width:25%;overflow-x:scroll" id="proShowNum"></div>
			<div style="font-size:12px;padding:12px;float:left;width:25%;overflow-x:scroll" id="proStock"></div>
			<div style="font-size:12px;padding:12px;overflow-x:hidden;width:155px;padding-left:70px;margin-top:-5px;">
			    <a href="javascript:;" style="background:#18b4ed;width:80px;font-size:12px" class="weui-btn weui-btn_mini weui-btn_primary" id="gotoshop">进入店铺</a>
			</div></div>
			<div id="choosediv"
				style="display:none;width:95%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">购买设置</div>
			<div id="bookdiv" style="display:none;font-size:12px;float:left;width:100%;overflow-x:scroll">
			  <div class="weui-cell" >
			    <div class="weui-cell__hd" style="width:20%;float:left;"><label class="weui-label">预定日期</label></div>
			    <div class="weui-cell__bd" style="width:80%;border:1px solid #CCC">
			       <input id="bookDate" class="weui-input mydate" type="text" placeholder="请选择"> 
			    </div>
			    <div class="weui-cell__bd"></div>
			  </div>
			</div>
			
			<div id="startdiv" style="display:none;font-size:12px;float:left;width:100%;overflow-x:scroll">
			  <div class="weui-cell" >
			    <div class="weui-cell__hd" style="width:20%;float:left;"><label class="weui-label">入住日期</label></div>
			    <div class="weui-cell__bd" style="width:80%;border:1px solid #CCC">
			    	<input id="startDate" class="weui-input mydate" type="text" placeholder="请选择"> 
			      
			    </div>
			    <div class="weui-cell__bd"></div>
			  </div>
			</div>
			
			<div id="enddiv" style="display:none;font-size:12px;float:left;width:100%;overflow-x:scroll">
			  <div class="weui-cell" >
			    <div class="weui-cell__hd" style="width:20%;float:left;"><label class="weui-label">离店日期</label></div>
			    <div class="weui-cell__bd" style="width:80%;border:1px solid #CCC">
			    	<input id="endDate" class="weui-input mydate" type="text" placeholder="请选择"> 
			    	<input id="payMoney" class="weui-input mydate" type="text" value="1" hidden="hidden">
			    </div>
			    <div class="weui-cell__bd"></div>
			  </div>
			</div>
			
		   <div id="selRoomDiv" style="display:none;font-size:12px;float:left;width:100%;overflow-x:scroll">
			  <div class="weui-cell" >
			    <div class="weui-cell__hd" style="width:20%;float:left;"><label class="weui-label">房间选择</label></div>
			    <div class="weui-cell__bd" style="width:80%;border:1px solid #CCC">
			      <input id="selRoom" readonly="readonly" class="weui-input" type="text" placeholder="">
			      <input type="hidden" id="selRoomId"/>
			    </div>
			    <div class="weui-cell__bd"></div>
			  </div>
			</div>
			
			<div style="font-size:12px;float:left;width:100%;overflow-x:scroll">
			  <div class="weui-cell" >
			    <div class="weui-cell__hd" style="width:20%;float:left;"><label class="weui-label">套餐选择</label></div>
			    <div class="weui-cell__bd" style="width:80%;">
			      <select id="comboList" style="width:130px;height:25px;line-height:25px" name="select1">
		         
		          </select>
			    </div>
			   
			  </div>
			</div>
			
			<div id="logisticDiv"  style="font-size:12px;float:left;width:100%;overflow-x:scroll">
			  <div class="weui-cell" >
			    <div class="weui-cell__hd" style="width:20%;float:left;"><label class="weui-label">物流选择</label></div>
			    <div class="weui-cell__bd" style="width:80%;">
			      <select id="logisticsList" style="width:130px;height:25px;line-height:25px" name="select1">
		         
		          </select>
			    </div>
			   
			  </div>
			</div>
			
			
			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">商家信息</div>
			<div style="font-size:12px;padding:12px;float:left;width:100%;overflow-x:scroll">
				<div id="address1"
					style="font-size:12px;color: #FF0000;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;margin-left:12px;margin-top:15px;width:96%;"></div>
				<div id="addressphone1"
					style="font-size:12px;margin-left:12px;margin-top:15px;width:90%;"></div>
					
				<div
					style="width:90%;margin-top:20px;margin-left:11px;font-size:12px;">
					<a id="contact1" href="javascript:void(0);" class=" icon-user">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;qq客服</a>
				</div>	
			</div>
					
			
			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">商品详情</div>
			<div style="font-size:12px;padding:12px;float:left;width:100%;overflow-x:scroll" id="proContent"></div>

			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">评论列表</div>
			<div id="comment" class="weui-panel__bd"
				style="font-size:12px;width:100%;float:left;padding-bottom:40px;">

			</div>

			<div style="width:100%;height:40px;position:fixed;z-index:10;bottom:2px">
				<a id="addOrder"
					style="width:47.5%;font-size:13px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;"
					href="javascript:;" class="weui-btn weui-btn_primary">加入购物车</a> <a
					id="buy"
					style="width:47.5%;font-size:13px;margin-right:2%;float:right;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
					href="javascript:;" class="weui-btn weui-btn_primary">立即购买（￥<span
					id="total">0</span>）
				</a>
				<a
					id="tel"
					style="display:none;width:96%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
					href="javascript:;" class="weui-btn weui-btn_primary">电话咨询
				</a>
			</div>

			<div id="selAddress" class="weui-popup__container"
				style="padding-bottom:50px;">
				<div class="weui-popup__overlay"></div>
				<div class="weui-popup__modal">
				    <div class="modDiv" id="roomList">
				    
				    
				    </div>
				
				
					<div class="modDiv" id="addressFitst">

						<div class="weui-cells__title" style="color:red;font-weight:bold">点击地址选择或添加新联系人</div>
						<a id="addAddress"
								style="width:96%;font-size:14px;margin-left:2%;background-color:#18b4ed;height:30px;line-height:30px;margin-top:0"
								href="javascript:;" class="weui-btn weui-btn_primary">添加地址</a>
						<div class="weui-panel__bd" id="addressList"
							style="padding-bottom:40px;"></div>

						<div style="width:100%;height:40px;">
							<a id="cancelAddress"
								style="width:47%;font-size:14px;margin-left:2%;float:left;height:40px;line-height:40px;"
								href="javascript:;" class="weui-btn weui-btn_warn">取消购买</a> <a
								id="buynow"
								style="width:47%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
								href="javascript:;" class="weui-btn weui-btn_primary">立即购买</a>
						</div>
					</div>


					<div class="modDiv" id="addressSecond" style="display:none;">

						<div class="weui-cells weui-cells_form">
							<div class="weui-cells__title">添加收货地址</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">姓名</label>
								</div>
								<div class="weui-cell__bd">
									<input id="name" class="weui-input" type="text" placeholder="">
								</div>
							</div>
							<div class="weui-cell" style="display:none;">
							    <div class="weui-cell__hd"><label class="weui-label">身份证</label></div>
							    <div class="weui-cell__bd">
							      <input id="idNum" class="weui-input" type="text" placeholder="">
							    </div>
							    
							 </div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">手机号</label>
								</div>
								<div class="weui-cell__bd">
									<input id="addressphone" class="weui-input" type="text"
										placeholder="">
								</div>
							</div>
							<div class="weui-cell" id="weui">
								<div class="weui-cell__hd">
									<label for="name" class="weui-label">地址选择</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" id="address" type="text" value=""
										readonly="" data-code="420106"
										data-codes="420000,420100,420106">
								</div>
							</div>
							<div class="weui-cell" id="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">详细地址</label>
								</div>
								<div class="weui-cell__bd">
									<input id="moreAddress" class="weui-input" type="text"
										placeholder="">
								</div>
							</div>
						</div>
						<a id="save"
							style="width:96%;position:fixed;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 保存</a>

					</div>
                    <div class="modDiv" id="cameraDiv" style="display:none;">
                          <div id="cameraContent"></div>
							  
						  <div>
                          <a id="cancelPhoto"
							style="width:47%;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 取消</a>
                          <a id="confirmPhoto"
							style="width:47%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 保存</a></div>
                    </div>
				</div>



			</div>
		</div>
</body>


</html>