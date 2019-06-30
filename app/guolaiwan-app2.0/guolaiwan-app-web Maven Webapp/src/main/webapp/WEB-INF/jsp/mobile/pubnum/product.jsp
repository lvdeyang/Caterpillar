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
	cursor: pointer !important;
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
    width:320px;
  
}
#proContent image{
    width:320px;
  
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
.left::-webkit-input-placeholder {
    color:#B38F80;
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
.names,.idnums{
     margin-top:30px;
}
.icon-map-marker,.icon-mobile-phone{
 font-size:18px;
}

/* 对话框样式 */   
  input,button{outline:none;}
	.wenwen-footer{width:100%;position:fixed;bottom:-5px;left:0;background:#fff;padding:3%;border-top:solid 1px #ddd;box-sizing:border-box;}
	.wenwen_btn,.wenwen_help{width:15%;text-align:center;-webkit-user-select:text !important;}
	.wenwen_btn img,.wenwen_help img{height:40px;}
	.wenwen_text{height:40px;border-radius:5px;border:solid 1px #636162;box-sizing:border-box;width:80%;text-align:center;overflow:hidden;margin-left:2%;}
	.circle-button{padding:0 5px;}
	.wenwen_text .circle-button{font-size:14px;color:#666;line-height:38px;}
	.write_box{background:#fff;width:100%;height:40px;line-height:40px;-webkit-user-select:text !important;}
	.write_box input{height:40px;padding:0 5px;line-height:40px;width:100%;box-sizing:border-box;border:0;}
	.wenwen_help button{width:100%;background:#42929d;color:#fff;border-radius:5px;border:0;height:40px;}
	#wenwen{height:100%;}
	.speak_window{overflow-y:scroll;height:100%;width:100%;position:fixed;top:0;left:0;}
	.speak_box{margin-bottom:70px;padding:10px;}
	.question,.answer{margin-bottom:1rem;}
	.question{text-align:right;margin-top:50px;}
	.question>div{display:inline-block;}
	.left{float:left;cursor:pointer}
	.right{float:right;cursor:pointer;}
	.clear{clear:both;}
	.heard_img{height:40px;width:40px;border-radius:5px;overflow:hidden;background:#ddd;margin-top:10px}
	.heard_img img{width:100%;height:100%}
	.question_text,.answer_text{box-sizing:border-box;position:relative;display:table-cell;min-height:60px;}
	.question_text{padding-right:20px;}
	.answer_text{padding-left:20px;}
	.question_text p,.answer_text p{border-radius:6px;padding:.5rem;margin:0;font-size:14px;line-height:40px;box-sizing:border-box;vertical-align:middle;display:table-cell;height:40px;word-wrap:break-word;}
	.answer_text p{background:#fff;}
	.question_text p{background:#94EB68;color:#fff;text-align:left;}
	.question_text i,.answer_text i{width:0;height:0;border-top:5px solid transparent;border-bottom:5px solid transparent;position:absolute;top:25px;}
	.answer_text i{border-right:10px solid #fff;left:10px;}
	.question_text i{border-left:10px solid #94EB68;right:10px;}
	.answer_text p a{color:#42929d;display:inline-block;}
	audio{display:none;}
	.saying{position:fixed;bottom:30%;left:50%;width:120px;margin-left:-60px;display:none;}
	.saying img{width:100%;}  
    
    


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
	    var id = '${id}';
		if( id == 34){
	   $("#guide").show();
	  }
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
			    $('#proShowNum').html('销量'+data.product.productSaleNum);
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
		     if(i==0){
		          html.push('<option value="'+data[i+1].id+'">'+data[i+1].name+'</option>');
		     }
		     if(i==1){
		          html.push('<option value="'+data[i-1].id+'">'+data[i-1].name+'</option>');
		     }
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

addmessage();
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
                   var str=new String();
                    var arr=new Array();
                    str=localData ;
　               var sear=new RegExp(',');
                     if(sear.test(str)) {
                          arr=str.split(',');//注split可以用字符或字符串分割
                          params.localData=arr[1];
                       }else{
                                      params.localData=localData; 
                         }
                     
                                 
                      
		$.post(_uri, params, function(data){
                                                   
			           if(data.msg==0){
                                                                    
                                                                          
						   $(".idnums").val(data.name)
						   $(".names").val(data.sfz) 	
						}			   
					     if(data.msg==1){
							    $(".idnums").val("130228196606080097")
						   $(".names").val("刘建江") 			
						 }			        
				    });
                }
            });
 
        }
		

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
		    if(ifFace==0){
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
            $.closePopup();
			var chkStockUrl=window.BASEPATH + 'pubnum/stock/check?proId='+${id}+'&count='+$('#proCount').val();
			$.get(chkStockUrl, null, function(data){
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

<script type="text/javascript">
	$(function() {
		//轮询任务
		window.setInterval(function () {
			var url=window.BASEPATH+'pubnum/getolchat';
			var userId=${userId};
			var merchantId=${merchantId};
				$.post(url,{"merchantId":merchantId},function(data){
					//从属于这个商户房间信息中查询未发送的信息遍历
					for(var i=0;i<data.length;i++){	
							//查找出这个房间touser是登录人的信息展示出来
							if(data[i].touserId==userId&&data[i].merchantId==merchantId){
								$('.ltname').text(data[i].fromuser);
								ans  = '<div class="answer" style="margin-top:30px;">';
								ans += '<div class="heard_img left"><img src="'+data[i].userheadimg+'"></div>';
				            	ans += '<div class="answer_text"><p>'+data[i].message+'</p><i></i>';
				        		ans += '</div></div>';
				        		$('.speak_box').append(ans);
								$('#olprompt').show();
								//修改展示完成的数据flag
								$.post(window.BASEPATH+'pubnum/updateflag',{"id":data[i].id},function(){})								
							}
							
						}
					})
		},3000);
    })   
	            	
	 
	 $(document).on('click',' #left',function(){
        setTimeout(function(){ 
            document.body.scrollTop = document.body.scrollHeight; 
        },200); 
    })
	//消息发送方法
	 function SubSend(){
	 
	   
	 
	 	var message="";
	 	var userId=${userId};
		var merchantId=${merchantId};
		//存数据库的路径
	 	var url=window.BASEPATH+'pubnum/pullolchat';
		//获取要发送的对象 用户在这里问 
		var touser="";
	 	//输入框判空
	 	if(document.getElementById("left").value!=""&&document.getElementById("left").value!=null){
	 		message=document.getElementById("left").value;
	 	}else{
	 		$.alert('输入不能为空！');
	 		return;
	 	}
	 		str  = '<div class="question">';
	        str += '<div class="heard_img right"><img src="${userHeadimg}"></div>';
	        str += '<div class="question_text clear"><p>'+message+'</p><i></i>';
	        str += '</div></div>';
	        $('.speak_box').append(str);
	 	$('.left').val("");
	 	//将发送的信息存入数据库 
	 	$.post(url,{"userId":userId,"merchantId":merchantId,"message":message,"touser":touser},function(data){
	 	})
	 }
	 
	/*显示隐藏切换  */
	 $(document).on('click',' #socket',function(){
	 		$(".zhuye").hide();
	        $(".duihua").show();
	       $("#olprompt").hide();
	 });
	/*显示隐藏切换  */
	 $(document).on('click',' #guide',function(){
	  window.location.href="guide/visitors/app?userId="+${userId}; 
			/* var _urifav = window.BASEPATH + 'guide/visitors/app';
			$.get(_urifav , null , function(dat){
		    });                              */
	  /*  // $('body').append("<a href='app://guolaiwan'>唤醒你的 APP</a>");
	 
	 	//此操作会调起app并阻止接下来的js执行，进入已安装的app
        $('body').append(" <a href='app://media_resource_system.guolaiwan/guideActivity?userId=${userId}&merchantId=15&longitude=117.625103&latitude=40.188278'></a>");
        //没有安装应用会执行下面的语句
        setTimeout(function(){window.location='http://a.app.qq.com/o/simple.jsppkgname=com.bjyijiequ.community'},5000);  */
	 });
	 
	 $(document).on('click','.tui',function(){
	 		$(".duihua").hide();
	        $(".zhuye").show();
	       
	 });
	 
	 
	  $(document).on('click','#gotogroup',function(){
	  		location.href=window.BASEPATH + 'pubnum/grouping?productId=${id}&userId=${userId}';
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
	
	
	<div class="zhuye" style="">
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
							<a class="weui-count__btn weui-count__decrease"></a> <input style="font-size:14px;width:30px;"
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
			    <c:if test="${isgroup==1}">
			    	<a href="javascript:;" style="background:#18b4ed;width:80px;font-size:12px;position: absolute;" class="weui-btn weui-btn_mini weui-btn_primary" id="gotogroup">拼团购买</a>
			    </c:if>
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
			      <input id="selRoom" readonly="readonly" class="weui-input" type="text" placeholder="" value="默认">
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
					style="width:90%;margin-top:20px;margin-left:11px;font-size:14px;">
					
					<a id="socket" href="javascript:void(0);"  class=" icon-user">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在线咨询</a>
					<a id="guide" href="javascript:void(0);" style="display: none;" class=" icon-user">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;导览</a>
					
					<a> <img id="olprompt" style="width:20px;height:20px;vertical-align: middle;margin-top:-2px;display: none;" src="lib/images/hongdian.gif"><!--这个标志是信息提醒 --></a>

				</div>	
			</div>
					
			
			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">商品详情</div>
			<div style="font-size:12px;padding:12px;float:left;width:100%;height:auto;margin:0 auto;overflow-x:scroll" id="proContent"></div>

			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">评论列表</div>
			<div id="comment" class="weui-panel__bd"
				style="font-size:12px;width:100%;float:left;padding-bottom:40px;">

			</div>

			<div style="width:100%;height:40px;position:fixed;z-index:10;bottom:2px">
				<a id="addOrder"
					style="width:47.5%;font-size:12px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;"
					href="javascript:;" class="weui-btn weui-btn_primary">加入购物车</a> <a
					id="buy"
					style="width:47.5%;font-size:12px;margin-right:2%;float:right;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
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
		</div>
		</div>
		<!-- 对话框 -->
<div class="duihua" style="width:100%;height:100%;z-index:1111;display: none;">
	
<div class="speak_window" >
<div style="position:fixed;top:0;width:100%;height:50px;background: #FFFFFF;z-index: 11111;float: left;line-height: 50px;">
	<p style="width:100%;margin-left: 5%;"><span class="tui" style="font-weight: bold;">＜</span> <span class="ltname"></span></p>
		<%-- <c:if test="${ismerchant==1}">
		<div style="float: right;z-index: 111111;" class="olline"><p>聊天列表</p></div>	
		</c:if> --%>
	</div>
	<div class="speak_box">
		<div class="answer">
		</div>
	</div>
</div>
<div class="wenwen-footer">
	<div class="wenwen_btn left" onClick="to_write()"></div>
	<div class="wenwen_text left">
	    <div class="write_box">
	        <input type="text" class="left" id="left"  placeholder="请输入关键字" />
	    </div> 
	      
	</div>
	<div class="wenwen_help right">
	    <button onClick="SubSend();" class="right">发送</button>
	</div>
	<div style="opacity:0;" class="clear"></div>
</div>


</div>
</body>


</html>