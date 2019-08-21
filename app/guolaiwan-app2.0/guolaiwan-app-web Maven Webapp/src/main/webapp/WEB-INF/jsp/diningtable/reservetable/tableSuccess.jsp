 <%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<html>

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
<meta name=”viewport” content=”width=device-width, initial-scale=1″ />



<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<title>订桌-支付成功</title>
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
	background:#E8E8E8 !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	
	text-decoration: none !important;
}

* {
	box-sizing: border-box;
	
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
	height: auto;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
	
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
.header-in{
 background: linear-gradient(to right, rgba(254,210,119,1), rgba(236,112,33,1)); 
  
}
.main ul li p{
 border-bottom:1px solid #C2C4C3;
 height:30px;
 line-height: 30px;
}
.tuijian{
    width: 96%;
    height:200px;
    border-radius: 10px;
    margin: 10px auto;
    text-align: center;
    background: #fff;
    padding: 10px 10px;
  
    overflow: hidden;
    overflow:scroll;
   display: none;
}
.tuijian ul li{
width:100%;
height:50px;
list-style-type:none;
border-bottom:1px solid #eeeeee;

line-height: 50px;

}

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

<script type="text/javascript">
  $(function() {
		var _uri = window.BASEPATH + 'reservetable/getOrder.do'; //
		var patam = {};
		patam.orderId = '${orderId}' ; //'${merchantId}'
		alert(${orderId});
		$.post(_uri, $.toJSON(patam), function(data) {
		if(data.state != 0){
			$("#pic").attr("src",'http://www.guolaiwan.net/file/'+data.table.detailsImg+'');
			$("#name").append('<span>'+data.table.tablename+'</span>'); //名称
			$("#money").append(data.table.bookprice/100); //钱
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">订单编号</span><span>'+data.tables.id+'</span></p></li>');
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">就餐时间</span><span>'+data.tables.tableDate+'  '+data.type +'</span></p></li>');
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">预订人</span><span>'+data.tables.userName+'</span></p></li>');
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">预订电话</span><span>'+data.tables.userPhone+'</span></p></li>');
			$("#ydImage").attr("src",'http://www.guolaiwan.net/file/'+data.tables.ydNO+'');
			if(data.mealList != null && data.mealList.length >0){
			  $(".tuijian").show();
              for(var i=0; i<data.mealList.length; i++){
				  var array = [];  
			      array.push('<li style="position:relative;">');
			      array.push('<img style="height:49px;width:49px;float:left;margin-left:5%;" src="http://www.guolaiwan.net/file/'+data.mealList[i].picture+'">');
			      array.push('<p style="position: absolute;top:0px;left:25%;">'+data.mealList[i].mealName+'</p>');
			      array.push('<p style="position: absolute;top:17px;left:25%;">x'+data.mealList[i].mealAmount+'</p>');
			      array.push('<p style="position: absolute;top:7px;right:5%;">￥'+data.mealList[i].money+'</p>');
			      array.push('</li>');
				  $('#mealList').append(array.join(''));	       
			  }
			}
		}else{
		    $(".tuijian").show();
		    $("#pic").attr("src",'lib/images/undecideds.png');
			$("#name").append('<span>未订桌</span>'); //名称
			$("#money").append(0.00); //钱
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">订单编号</span><span>'+data.tables.id+'</span></p></li>');
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">就餐时间</span><span>'+data.tables.tableDate+'  '+data.type +'</span></p></li>');
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">预订人</span><span>'+data.tables.userName+'</span></p></li>');
			$("#message").append(' <li><p><span style="text-align: left;width:30%;display: inline-block;">预订电话</span><span>'+data.tables.userPhone+'</span></p></li>');
			$("#ydImage").attr("src",'http://www.guolaiwan.net/file/'+data.tables.ydNO+'');
			for(var i=0; i<data.mealList.length; i++){
			  var array = [];  
		      array.push('<li style="position:relative;">');
		      array.push('<img style="height:49px;width:49px;float:left;margin-left:5%;" src="http://www.guolaiwan.net/file/'+data.mealList[i].picture+'">');
		      array.push('<p style="position: absolute;top:0px;left:25%;">'+data.mealList[i].mealName+'</p>');
		      array.push('<p style="position: absolute;top:17px;left:25%;">x'+data.mealList[i].mealAmount+'</p>');
		      array.push('<p style="position: absolute;top:7px;right:5%;">￥'+data.mealList[i].money+'</p>');
		      array.push('</li>');
			  $('#mealList').append(array.join(''));	       
			}
		}
		});
		
			
	$(document).on('click','#clic',function(){
		 location.href=window.BASEPATH + 'business/gotodelicacystore?merchantId=${merchantId}&orderId=${orderId}'; 
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
		<div class="header-in" style="width:100%;height:130px;border-radius:0 0 100px 100px;text-align: center;">
          <img style="width:40px;height:40px;margin:20px auto 0;" src="lib/images/xiaolian.png"/>
          <p style="color:#fff;font-weight: bold;">支付成功</p>
		</div>
		 <div style="width:100%;height:auto;margin-top:-40px;z-index:11;">
		  <div style="width:96%;height:120px;border-radius: 10px;padding:10px 10px;margin:0 auto;border:1px solid #EFEFEF;background:#fff;overflow: hidden;position: relative;">
		   <img id="pic" style="width:30%;height:100%;border-radius: 10px;" src="lib/images/2.jpg"/>
		   <p  style="position: absolute;top:20px;font-size:16px;left:35%" id="name"></p>
		    <p style="position: absolute;bottom:20px;margin:0;font-size:12px;color:#9A9A9A;left:35%">预订金<span style="color:#EA712C;font-size:18px;" id="money">￥</span> </p>
		  </div>
	
		  <div class="main" style="width:96%;height:auto;border-radius: 10px;margin:10px auto;text-align: center;background: #fff;padding:10px 5%;font-size:12px;text-indent:5px">
		  <ul style="text-align: left;list-style: none;" id = "message">
		  
		  </ul>
		   <p style="text-align: left;font-size:12px;">注：<span>所有包间的预订金再结帐时作为餐费抵消；</span></p>
		   <p style="text-align: left;font-size:12px;padding:0 5%;"><span>如果您超出预订时间没有就餐，订单将会自动取消，所交预订金不退还。</span></p>
		  <img id="ydImage" style="height:170px;width:170px;" src="lib/images/logo.png"/> 
		  <p style="font-size:14px;line-height: 30px;">扫码验单</p>
		  <p id = "clic" style="width:25%;color:#fff;background: #FC6100;height:30px;line-height: 30px;margin: 0 auto;border-radius:10px;">点餐</p>
		  </div>

		

		<!-- 推荐 -->
		<p style="color:#EE7826;font-weight: bold;text-align: center;margin:10px 5px;">——<span style="margin:10px 5px;">您的菜单</span>——</p>
     		<div class="tuijian">
     		   <ul id="mealList" >
     		   <!--  <li >
     		    <img style="height:49px;width:49px;float:left;margin-left:5%;" src="lib/images/logo.png">
     		    <p style="position: absolute;top:0px;left:25%;">麻辣鸡丝</p>
     		    <p style="position: absolute;top:17px;left:25%;">x1</p>
     		    <p style="position: absolute;top:7px;right:5%;">$20</p>
     		    </li>
     		    <li></li>
     		    <li></li>
     		    <li></li> -->
     		   </ul>
			</div>
   
   <!-- 置顶 -->
    <div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
</body>

</html>
