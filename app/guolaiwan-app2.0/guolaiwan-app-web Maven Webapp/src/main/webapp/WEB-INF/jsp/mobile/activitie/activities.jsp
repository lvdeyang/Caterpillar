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
<title>年货节活动</title>
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
	background:#FEF8D3 !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	font-szie:100%;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	moz-user-select: -moz-none;
	-o-user-select:none;
	-khtml-user-select:none;
	-webkit-user-select:none;
	-ms-user-select:none;
	user-select:none;
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
/* .title_a{
color:#FCF0CD;
position: absolute;
left:15%;
z-index:3;
font-size:80%;
font-weight: bold;
width:70%;
top:7.5px;
text-align: center;
letter-spacing:1px;
font-family: STSong;
} */
.title_on{
font-size:110%;
color:#D9201D;
}
.title_header{
text-align: center;
width:100%;
height:40px;
line-height: 40px;
margin:0 0 20px 0;
}
.title_header img{
 height:20px;
}

ol li{
list-style-type:decimal;

list-style-position:inside;
height:35px;
line-height:35px;
color:#D9201D;
font-size:90%;
font-weight: bold;
}
.main_ol{
text-align: left;
text-indent: 4px;
}
.main_in{
width:100%;
height:150px;
background: url("lib/images/mainss.png") no-repeat;
background-size: 100% 100%;
position: relative;
}
.main_in img{
height:55%;
position:absolute;
left:7%;
top:22.5%;
}
.main_in ul{
position: absolute;
text-align:left;
right:5%;
top:15%;
}
.main_in ul li{
 color:#fff;
 line-height:25px;
}
.main_in ul li button{
font-size:80%;
padding:0 10px;
background: #FFF8D3;
color:#CE1723;
border:none;
outline: none;
border-radius:4px;
height:20px;
line-height: 20px;
cursor:pointer
}
.main_a_on,.main_c_on{
width:49%;
height:auto;
background:url("lib/images/main_a.png")no-repeat;
background-size:100% 100%;
float:left;
margin:0 0.5% 20px 0.5%;
padding:30px 5% 20px 5%;
}
.main_a_on ul li,.main_c_on ul li{
text-align:left;
color:#D9201D;
line-height:24px;
}
.main_a_on ul li button,.main_c_on ul li button{
font-size:80%;
padding:0 10px;
background: #D9201D;
color:#FFF8D3;
border:none;
outline: none;
border-radius:4px;
height:20px;
line-height: 20px;
cursor:pointer
}
.main_b_on,.main_d_on{
width:49%;
height:auto;
background:url("lib/images/main_b.png")no-repeat;
background-size:100% 100%;
float:left;
margin:0 0.5% 20px 0.5%;
padding:30px 5% 20px 5%;
}
.main_b_on ul li,.main_d_on ul li{
text-align:left;
color:#FAEACA;
line-height:24px;
}
.main_b_on ul li button,.main_d_on ul li button{
font-size:80%;
padding:0 10px;
background:#FFF8D3;
color:#D9201D;
border:none;
outline: none;
border-radius:4px;
height:20px;
line-height: 20px;
cursor:pointer
}
.main_ol img{
width:100%;

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
  // 初始化
    
      var _uriActivitys = window.BASEPATH + 'pubnum/activity/getProducts?comCode=0001'; 
		/* var _uriActivitys = 'http://www.guolaiwan.net/pubnum/activity/getProducts?comCode=0001' */
     $.get(_uriActivitys, null, function(data){

	data = parseAjaxResult(data);
	if(data === -1) return;
	if(data){
	var activitys=data.activity;
		var htmlss=[];
      for(var i = 0;i<activitys.length;i++){
       var ps =data[activitys[i].id]
      
       if(activitys[i].id == 22){
       for(var i = 0;i<ps.length;i++){
        htmlss.push('<p class="title_header"><img src="lib/images/title_lefts.png"><span class="title_on">礼包一</span><img src="lib/images/title_rights.png"></p>'); 
	    htmlss.push('<img style="width:100%;" src="'+data.url+ps[i].productShowPic+'">'); 
	    htmlss.push('<img style="width:60%;" src="lib/images/title_only.png">'); 
	    htmlss.push('<div class="main_ol" style="height:auto;width:100%;">'+ps[i].productIntroduce+'</div>'); 
      	htmlss.push('<div class="main_in">');
      	htmlss.push('<img src="'+data.url+ps[i].productShowPic+'">');
      	htmlss.push('<ul>');
        htmlss.push('<li><span style="font-size:110%;">'+ps[i].productClassName+'</span></li>');
        htmlss.push('<li><span style="font-size:80%;text-decoration: line-through;">原价:'+ps[i].productOldPrice+'</span></li>');
        htmlss.push('<li><span style="font-size:90%;">年货价:￥</span><span style="font-size:140%;">'+ps[i].productPricesStr+'</span></li>'); 
        htmlss.push('<li><button  onclick="btn_a('+ps[i].id+')">点击抢购</button></li>');
	    htmlss.push(' </ul>');
	    htmlss.push('</div>'); 
   }
   $('.main').append(htmlss.join('')); 
	  } 
	}
	
	  /* 黄底 红框 */
       var html=[];
       for(var i = 0;i<activitys.length;i++){
       var ps =data[activitys[i].id]
       if(activitys[i].id == 23){
      for(var i = 0;i<ps.length;i++){
	   html.push('<div class="main_a_on">');
	   html.push('<img style="width:100%;margin-bottom:10px;" src="'+data.url+ps[i].productShowPic+'">');
	   html.push('<ul>');
	   html.push('<li style="height:90px;"><span style="font-size:120%;font-weight:bold;">'+ps[i].productName+'</span></li>');
	   html.push('<li><span style="font-size:70%;text-decoration: line-through;">原价:'+ps[i].productOldPrice+'</span></li>');
	   html.push('<li><span style="font-size:80%;">年货价:￥</span><span style="font-size:120%;font-weight:bold;">'+ps[i].productPricesStr+'</span></li>');
	   html.push('<li><button onclick="btn_a('+ps[i].id+')">点击抢购</button></li>');
	   html.push('</ul>');
	   html.push('</div>');
	   }
	   $('.main_a_in').append(html.join('')); 
	  } 
	}
       /* 红底 黄框 */
	   var htmls=[];
       for(var i = 0;i<activitys.length;i++){
       var ps =data[activitys[i].id]
       if(activitys[i].id == 24){
	   for(var i = 0;i<ps.length;i++){
	   htmls.push('<div class="main_b_on">');
	   htmls.push('<img style="width:100%;margin-bottom:10px;" src="'+data.url+ps[i].productShowPic+'">');
	   htmls.push('<ul>');
	   htmls.push('<li style="height:90px;"><span style="font-size:110%;font-weight:bold;">'+ps[i].productName+'</span></li>');
	   htmls.push('<li><span style="font-size:70%;text-decoration: line-through;">原价:'+ps[i].productOldPrice+'</span></li>');
	   htmls.push('<li><span style="font-size:80%;">年货价:￥</span><span style="font-size:120%;font-weight:bold;">'+ps[i].productPricesStr+'</span></li>');
	   htmls.push('<li><button onclick="btn_a('+ps[i].id+')">点击抢购</button></li>');
	   htmls.push('</ul>');
	   htmls.push('</div>');
	   }
	   $('.main_b_in').append(htmls.join('')); 
	  } 
	}
	
	
	  /* 黄底 红框 */
       var html=[];
       for(var i = 0;i<activitys.length;i++){
       var ps =data[activitys[i].id]
       if(activitys[i].id == 25){
      for(var i = 0;i<ps.length;i++){
	   html.push('<div class="main_c_on">');
	   html.push('<img style="width:100%;margin-bottom:10px;" src="'+data.url+ps[i].productShowPic+'">');
	   html.push('<ul>');
	   html.push('<li style="height:90px;"><span style="font-size:120%;font-weight:bold;">'+ps[i].productName+'</span></li>');
	   html.push('<li><span style="font-size:70%;text-decoration: line-through;">原价:'+ps[i].productOldPrice+'</span></li>');
	   html.push('<li><span style="font-size:80%;">年货价:￥</span><span style="font-size:120%;font-weight:bold;">'+ps[i].productPricesStr+'</span></li>');
	   html.push('<li><button onclick="btn_a('+ps[i].id+')">点击抢购</button></li>');
	   html.push('</ul>');
	   html.push('</div>');
	   }
	   $('.main_c_in').append(html.join('')); 
	  } 
	}
       /* 红底 黄框 */
	   var htmls=[];
       for(var i = 0;i<activitys.length;i++){
       var ps =data[activitys[i].id]
       if(activitys[i].id == 26){
	   for(var i = 0;i<ps.length;i++){
	   htmls.push('<div class="main_d_on">');
	   htmls.push('<img style="width:100%;margin-bottom:10px;" src="'+data.url+ps[i].productShowPic+'">');
	   htmls.push('<ul>');
	   htmls.push('<li style="height:90px;"><span style="font-size:110%;font-weight:bold;">'+ps[i].productName+'</span></li>');
	   htmls.push('<li><span style="font-size:70%;text-decoration: line-through;">原价:'+ps[i].productOldPrice+'</span></li>');
	   htmls.push('<li><span style="font-size:80%;">年货价:￥</span><span style="font-size:120%;font-weight:bold;">'+ps[i].productPricesStr+'</span></li>');
	   htmls.push('<li><button onclick="btn_a('+ps[i].id+')">点击抢购</button></li>');
	   htmls.push('</ul>');
	   htmls.push('</div>');
	   }
	   $('.main_d_in').append(htmls.join('')); 
	  } 
	}
	}			
     });
   }) 
   
	/*购买 */
   function btn_a(id){
/*    location.href=window.BASEPATH + 'pubnum/product/index?id; */
   location.href=window.BASEPATH + 'pubnum/product/index?id='+id;
   }

</script>
<body οncοpy='document.selection.empty()' οnselect='document.selection.empty()' >
	<!-- 主页 -->
	<div class="header">
		<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
				class="icon-reorder icon-large"></span></a>
			<div class="header-content">个人</div>
		</div>
	</div> 
	<div style="width:100%;height:auto;" class="nav">
	 <img style="width:100%;z-index:1" src="lib/images/headers.png" />
	</div>
	<div style="width:100%;position: relative;top:-90px;">
	 	  <img style="width:70%;z-index:2;position: absolute;left:15%;top:20px;" src="lib/images/title_one.png" />
	 	 <!-- <span class="title_a">年货节大礼包限量抢购</span> -->
	</div>
	 
	<div class="main" style="width:100%;height:auto;padding:0 10% 50px 10%;text-align: center;">
	</div>
	
	<div style="width: 100%;position: relative;">
	 <img style="width:100%;position:absolute;top:-80px;" src="lib/images/title_two.png">
	 <div class="main_a" style="width:100%;height:auto;overflow: hidden;padding:70px 0;">
	   <div class="main_a_in" style="width:100%;height:auto;padding:0 6%;margin-top:-10px;text-align: center;">
	   </div>
	</div>
    </div>	
    
  <div style="width: 100%;position: relative;">
	 <img style="width:100%;position:absolute;top:-80px;" src="lib/images/title_three.png">
	 <div class="main_b" style="width:100%;height:auto;overflow: hidden;background: #D10006;padding:70px 0;">
	   <div class="main_b_in" style="width:100%;height:auto;padding:0 6%;margin-top:-10px;text-align: center;">
 	   </div> 
	</div>
  </div>
  	
  	<div style="width: 100%;position: relative;">
	 <img style="width:100%;position:absolute;top:-80px;" src="lib/images/title_four.png">
	 <div class="main_c" style="width:100%;height:auto;overflow: hidden;padding:70px 0;">
	   <div class="main_c_in" style="width:100%;height:auto;padding:0 6%;margin-top:-10px;text-align: center;">
	   </div>
	</div>
    </div>	
    
  <div style="width: 100%;position: relative;">
	 <img style="width:100%;position:absolute;top:-80px;" src="lib/images/title_five.png">
	 <div class="main_d" style="width:100%;height:auto;overflow: hidden;background: #D10006;padding:70px 0;">
	   <div class="main_d_in" style="width:100%;height:auto;padding:0 6%;margin-top:-10px;text-align: center;">
 	   </div> 
	</div>
  </div>
</body>

</html>
