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

<title>分销商主页</title>

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
	padding: 0;
	margin: 0;
	height: 200px;
}

.swiper-container img {
	display: block;
	width: 100%;
	height: 200px;
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
#merchantContent img{
    width:280px;
 
}

/*#address{
        -webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;
    }
    #phone{
        -webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;
    }*/
#olprompt{
	width: 5%;
	height: 15%;
}   
    
    
    
 /* 对话框样式 */   
  input,button{outline:none;}
	.wenwen-footer{width:100%;position:fixed;bottom:-5px;left:0;background:#fff;padding:3%;border-top:solid 1px #ddd;box-sizing:border-box;}
	.wenwen_btn,.wenwen_help{width:15%;text-align:center;}
	.wenwen_btn img,.wenwen_help img{height:40px;}
	.wenwen_text{height:40px;border-radius:5px;border:solid 1px #636162;box-sizing:border-box;width:80%;text-align:center;overflow:hidden;margin-left:2%;}
	.circle-button{padding:0 5px;}
	.wenwen_text .circle-button{font-size:14px;color:#666;line-height:38px;}
	.write_box{background:#fff;width:100%;height:40px;line-height:40px;}
	.write_box input{height:40px;padding:0 5px;line-height:40px;width:100%;box-sizing:border-box;border:0;}
	.wenwen_help button{width:100%;background:#42929d;color:#fff;border-radius:5px;border:0;height:40px;}
	#wenwen{height:100%;}
	.speak_window{overflow-y:scroll;height:100%;width:100%;top:0;left:0;}
	.speak_box{margin-bottom:70px;padding:10px;}
	.question,.answer{margin-bottom:1rem;}
	.question{text-align:right;margin-top:50px;}
	.question>div{display:inline-block;}
	.left{float:left;cursor:pointer}
	.right{float:right;cursor:pointer}
	.clear{clear:both;}
	.heard_img{height:40px;width:40px;border-radius:5px;overflow:hidden;background:#ddd;margin-top:10px}
	.heard_img img{width:100%;height:100%}
	.question_text,.answer_text{box-sizing:border-box;position:relative;display:table-cell;min-height:60px;width:80%;}
	.question_text{padding-right:20px;}
	.answer_text{padding-left:20px;}
	.question_text p,.answer_text p{position: absolute;right:20px;top:10px;word-wrap:break-word;word-break:normal;border-radius:6px;padding:0 5px 2px 5px;margin:0;font-size:14px;line-height:40px;box-sizing:border-box;vertical-align:middle;display:table-cell;word-wrap:break-word;}
	.answer_text p{background:#fff;}
	.question_text p{background:#94EB68;color:#fff;text-align:left;}
	.question_text i,.answer_text i{width:0;height:0;border-top:5px solid transparent;border-bottom:5px solid transparent;position:absolute;top:25px;}
	.answer_text i{border-right:10px solid #fff;left:10px;}
	.question_text i{border-left:10px solid #94EB68;right:10px;}
	.answer_text p a{color:#42929d;display:inline-block;}
	audio{display:none;}
	.saying{position:fixed;bottom:30%;left:50%;width:120px;margin-left:-60px;display:none;}
	.saying img{width:100%;}  
    
    
    .chatline{
     background:#FBFBFB;
     margin-top:50px;
     width:100%;
     border-radius:6px;
     overflow: hidden;
  
    }
    .chatline img{
     width:50px;
     height:50px;
     border-radius:6px;
     margin:5px 5px;
       z-index:11111111111111111111111111111111;
    }

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
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
	  
		
		//获取所有一级推荐
      var _uriMerchantInfo = window.BASEPATH + 'pubnum/dismerchantInfo?disId=${disId}';
		
	  $.post(_uriMerchantInfo, null, function(data){
	
			if(data){
			    var html=[];
		
				html.push('<div class="swiper-slide" style="height:200px;"><img class="exampleImg" style="height:200px;" id="imgTest" src="'+data.licenseUrl+'" alt=""></div>');
			    $('.header-content').html(data.address);
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
			    $('#merchantContent').html(data.regionName);
			    $('#shopName').html(data.address);
			    $('#address').html('<a href="https://apis.map.qq.com/uri/v1/routeplan?type=drive&to='+data.address+'&tocoord='+data.shopLongitude+','+data.shopLatitude+'&policy=1&referer=2FNBZ-52HR4-OHEUW-XT2S7-ZJABQ-OJFIJ"><i class="icon-map-marker"></i>&nbsp;&nbsp;&nbsp;&nbsp;'+data.address+'</a>');
				$('#addressphone').html('<span class="icon-mobile-phone"></span>&nbsp;&nbsp;&nbsp;&nbsp;'+data.phone);
			    $('#addressphone').data('phone',data.phone);
			    getProduct();
			}
		});
		
		
		
		
		$(document).on('click','.product',function(){
	 	   var ids=this.id.split('-');
		   location.href=window.BASEPATH + 'pubnum/disproduct/index?id='+ids[1]+"&disId=${disId}";
	    });
	    $(document).on('click','#addressphone',function(){
	       var phones=$('#addressphone').data('phone').split('/');
	       location.href = 'tel://' + phones[0];
	    });

		function getProduct(){
		    var _uriproduct = window.BASEPATH + 'pubnum/getProductsBydis?disId=${disId}';
		
			$.post(_uriproduct, null, function(data){
				
				if(data){
				   var pros=data;
				   var html=[];
				   for(var i=0;i<pros.length;i++){
				       if(i%2==0){
				          html.push('<tr>');
				       }
	                     html.push('<td style="padding:10px;width:50%" relData="'+pros[i].activityReId+"-"+
	                     pros[i].isSurpport+'" data="'+pros[i].productModularCode+'" class="product" id="pro-'+pros[i].id+'">');
		                 html.push('<image style=" width:100%;height:100px;" src="'+pros[i].productShowPic+'" />');
		                 html.push('<p style="font-size:12px;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;">'+pros[i].productName+'</p>');
		                 html.push('<p style="font-size:12px;">￥'+pros[i].productPrice+'&nbsp;&nbsp;&nbsp;&nbsp;<span style="text-decoration:line-through">￥'+pros[i].productOldPrice+'</span>&nbsp;&nbsp;');
		                 html.push('</br>');
		                 html.push('<a style="width:40px;display:inline-block;color:#FFF;text-align:center;font-size:12px;background-color:#18b4ed;height:17px;line-height:17px;" href="javascript:;">购买</a></p>');
		                 html.push('</td>');
	                     if(pros.length==1){
	                       html.push('<td style="padding:10px;"><div style="width:100%;height:100px;"></div></td>');
	                     }
	                  if(i%2==1){
				          html.push('</tr>');
				      }
				   }
				   $('#product_table').append(html.join(''));
				}
			});
		}

	});

</script>

<body>

<div class="zhuye" style="">

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

			<div class="swiper-container" data-space-between='10'
				data-pagination='.swiper-pagination' data-autoplay="1000">
				
				<div class="swiper-wrapper" style="height:200px;"></div>
			</div>
				<div class="weui-tab">
				  <div class="weui-navbar">
				    <a id="tab-2" onclick="return false" class="weui-navbar__item weui-bar__item--on" href="#tab2">
				      全部商品
				    </a>
				    <a id="tab-1" onclick="return false" class="weui-navbar__item" href="#tab1">
				      店铺首页 <img id="olprompt" style="width:20px;height:20px;vertical-align: middle;margin-top:-2px;display: none;" src="lib/images/hongdian.gif"><!--这个标志是信息提醒 -->
				    </a>
				  </div>	
	             <div class="weui-tab__bd" style="padding-bottom:50px">
				    <div id="tab1" class="weui-tab__bd-item">
				       <table id="shophead" style="margin-top:15px;">
				       
						<div style="width:100%;">
							<div
								style="font-size:12px;margin-left:12px;margin-top:25px;width:50%;float:left;"
								id="shopName"></div>
							<div
								style="font-size:12px;margin-right:14px;margin-top:25px;width:30%;float:right;text-align:right;margin-right:40px;">
								
							</div>
						</div>
						</br></br>
						<div style="width:100%;height:40px;">
							<div id="address"
								style="font-size:12px;color: #FF0000;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;margin-left:12px;margin-top:15px;width:96%;"></div>
							<div id="addressphone"
								style="font-size:12px;margin-left:12px;margin-top:15px;width:90%;"></div>
						</div>
						<div style="width:90%;margin-top:25px;margin-left:11px;font-size:14px;"></div>
						
						<div style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">商家详情</div>
						<div style="font-size:12px;padding:12px;float:left;width:100%;overflow-x:scroll;" id="merchantContent"></div>
						
						
						<table id="product_table1"
							style="margin-top:15px;padding-bottom:50px;float:left">
			
						</table>	                  
	                    </table>
					   
				    </div>
				    <div id="tab2" class="weui-tab__bd-item weui-tab__bd-item--active">
				       <table id="product_table" style="margin-top:15px;">
						                  
	                   </table>
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
	<p style="width:100%;"><span class="tui" style="font-weight: bold;margin-left: 10%;">＜</span> <span class="ltname"></span>
	</p>
		
	</div>
	<div class="speak_box">
		<div class="answer">
		</div>
	</div>
</div>
<div class="wenwen-footer">
	<div class="wenwen_btn left" ></div>
	<div class="wenwen_text left">
	    <div class="write_box">
	        <input type="text" class="left" id="left" onKeyUp="keyup()" placeholder="请输入关键字" />
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