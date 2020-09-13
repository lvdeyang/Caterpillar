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

<title>投票大赛</title>

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
        height:120px;
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
    
    /*#address{
        -webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;
    }
    #phone{
        -webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;
    }*/

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptgonghui.jsp"></jsp:include>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

<script type="text/javascript">

	$(function() {
		function initPage(){
			$('.peopleDiv').remove();
			var urlpeoplelist=window.BASEPATH + "/people/vote/getpeoples";
		    $.get(urlpeoplelist,null,function(data){
				var html=[];
				for(var i=0;i<data.data.length;i++){
					html.push('<div class="peopleDiv" style="width:90%;margin-top:10px;margin-left:5%;height:240px;border:1.5px solid #CE6271">');
				    html.push('<image src="lib/images/ooo.png" style="position:absolute;margin-top:0px;margin-left:0px"/>');
				    html.push('<div style="position:absolute;margin-top:2px;margin-left:9px;font-weight:bold;color:#FFF">'+(i+1)+'</div>');
				
				    html.push('<image src="${webpath}'+data.data[i].headerimg+'"'); 
				    html.push('style="margin-top:10px;margin-left:10px;width:140px;height:140px;float:left;"/>');
				    html.push('<div style="font-size:9pt;width:160px;height:140px;margin-top:10px;margin-left:10px;float:left;position: relative;">');
				    html.push('   <p style="color:#4F5971">诵读者:'+data.data[i].name+'</p>');
				    html.push('   <p style="color:#4F5971">诵读作品:'+data.data[i].article+'</p>');
				    html.push('   <p style="color:#4F5971">推荐单位:'+data.data[i].org+'</p>');
				    html.push('   <p style="color:#4F5971">作品来源:'+data.data[i].articleFrom+'</p>');
				    html.push('   <div style="width:100%;padding-top:5px;font-size:14px"><span style="color:#F8324D" id="peopleCount">'+(data.data[i].count==null?0:data.data[i].count)+'</span>票');
				    html.push('      <image class="toupiao" style="width:60%;float:right;margin-right:10px" id="toupiao-'+data.data[i].id+'" src="lib/images/vote.png" style="float:right;"/>');
				    html.push('   </div>');
				    html.push('</div>');
				    html.push('<div><audio class="paudio" id="paudio-'+(i+1)+'" preload="auto" controls id="audio" style="background:#FFF;width:96%;margin-top:30px">');
					html.push('  <source src="${webpath}'+'/'+data.data[i].voice+'" />');
					html.push('</audio></div>');
				    html.push('</div>');
				}
		        
			    $('#peopleContent').append(html.join(''));
			    initAudio();
		   });
		}
	       
		   initPage();
		   
		   var flg=false;
		   $(document).on('click','.toupiao',function(){
		        if(flg==true){
		           alert('操作太频繁了');
		           return;
		        }
		        flg=true;
		        var ids=this.id.split('-');
		        var urlvote=window.BASEPATH + "/people/vote/set?vpId="+ids[1];
		   		$.post(urlvote,null,function(data){
	                flg=false;
		   		    if(data=="failed"){
		   		       alert('每个微信号一天最多投5票');
		   		    }else{
		   		       //alert('今天还有'+(4-parseInt(data.usecount))+"次投票机会");
		   		       //$('#peopleCount').text(data.allcount);
					   initPage();
		   		    }
		   		});
		   });
		   
		   function initAudio(){
		       var audios=document.getElementsByClassName("paudio");
		       for(var i=0;i<audios.length;i++){
		          audios[i].addEventListener("play", function () {   //开始播放时触发
		              var ids=this.id.split('-');
			          for(var j=0;j<audios.length;j++){
			             if(ids[1]!=(j+1)){
			                audios[j].pause();
			             }
			          }
			      });
			      
			      
			      audios[i].addEventListener("ended", function () {   //当播放完一首歌曲时也会触发
				      var ids=this.id.split('-');
			          for(var j=0;j<audios.length;j++){
			             if(ids[1]==(j+1)){
			                if(audios[j+1]){
			                	audios[j+1].play();
			                }
			             }
			          }
				  });
			      
			      
			      
		       }
		   }
		   
		   
		   
	});
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header" style="background:#333333">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">诵读投票评选</div>
			</div>
		</div>
		<div class="content" style="height:6000px;width:100%;">
			<image src="lib/images/bgs1.png" style="width:100%;height:6000px;position:absolute;margin-top:0px;margin-left:0px;">
			<image src="lib/images/biaoti2.png" style="width:100%;height:5700px;position:absolute;margin-top:265px;margin-left:0px;z-index:5000">
			<div id="peopleContent" style="width:100%;height:5000px;position:absolute;margin-top:325px;margin-left:0px">
			
				<p style="width:93%;font-size:18px;margin:0 auto;text-align-last:justify;text-align:justify;text-justify:distribute-all-lines;">&nbsp;&nbsp;&nbsp;&nbsp;市总工会"弘扬传统文化&nbsp;领悟中华经典"职工诵读活动自3月份开展以来,历时近5个月,受到了全市各级工会和职工朋友的热情响应。截至8月底,共收到400多名职工的诵读作品800余篇,期间经过初评持续对300余篇诵读作品进行了展播。经过专业评审,20名优秀职工朗读者脱颖而出。市总工会将根据评审意见并结合网络投票情况,评选出一、二、三等奖。
　　<br>&nbsp;&nbsp;&nbsp;&nbsp;谁是你心中的“人气王”？动动手指,快来投票吧！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			    <image src="lib/images/Tips.png" style="width:50%;margin-left:25%;margin-top:20px;"/>
			    <p style="width:93%;font-size:18px;margin:0 auto;;text-align-last:justify;text-align:justify;text-justify:distribute-all-lines;">
			    	   &nbsp;&nbsp;&nbsp;&nbsp;1、投票时间：9月　上午9：00至9月　日21:00止结束。&nbsp;&nbsp;&nbsp;&nbsp;
					　　<br>&nbsp;&nbsp;&nbsp;&nbsp;2、投票方式：网络投票，每个微信账号每天有一次投票机会，每次可投5票。(本投票严禁刷票，如有发现一律取消评选资格。)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    
			    </p>
			
			    <image src="lib/images/player.png" style="width:50%;margin-left:25%;margin-top:20px;"/>
			
			</div>
		</div>
	</div>
	
</body>


</html>