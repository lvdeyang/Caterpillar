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
<title>评分列表</title>
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
	min-height:auto;
	background:#fff !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	
	text-decoration: none !important;
}

 
* {
	box-sizing: border-box;
	list-style: none;
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
    
.weui-navbar{
 display: none !important;
}
  .inp::-webkit-input-placeholder{
        text-align: center;
}  
.lists tr th{
 width:20%;
 background: #C3191F;
 color:#fff;
 height:40px;
 text-align: center;
 font-size:16px;
}
.lists tr td{
 width:20%;
 background: #fff;
 color:black;
 height:40px;
 text-align: center;
 font-weight:bold;
 font-size:12px;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath()%>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script>
$(function(){
	getRecomment();
	list();
})


//获得图片
function getRecomment() {
		var _uriRecomment = window.BASEPATH + 'phoneApp/productInfo?productId=${product.id}&userId=${userId}';

		$.get(_uriRecomment, null, function(data) {
			data = parseAjaxResult(data);
			if (data === -1) return;
			if (data) {
				var html = [];
				var pics = data.product.productMorePic.split(',');
				for (var i = 0; i < pics.length; i++) {
					html.push('<div style="height:200px;" class="swiper-slide"><img style="height:200px" src="' + pics[i] + '" alt=""></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
					loop : true,
					autoplay : 3000
				});
			}
		});
}
	//时间格式化
	function fmat(time){
		var da = time;
	    da = new Date(da);
	    var year = da.getFullYear()+'年';
	    var month = da.getMonth()+1+'月';
	    var date = da.getDate()+'日';
		return year+month+date+"";
	}

	//展开数据
	function list(){
	var url=window.BASEPATH + 'admin/vote/showjudges';
		$.post(url,{"productId":${product.id}},function(data){
			var html=[];
			 html.push('<table border="1"  style="text-align: center;margin:0 auto;border-collapse:   separate;   border-spacing:3px; ">'); 
			 html.push('<tr>'); 
		     html.push('<th>日期</th>'); 
		     html.push('<th>评委姓名</th>'); 
		     html.push('<th>分数</th>'); 
		  	 html.push('</tr>'); 
	     	for(var i=0;i<data.all.length;i++){
	     	 var time=fmat(data.all[i].updateTime);
		  	 html.push('<tr>'); 
		     html.push('<td>2019年10月10日</td>'); //'+time+'
		     html.push('<td>'+data.all[i].username+'</td>'); 
		     html.push('<td>'+data.all[i].score+'</td>'); 
		     html.push('</tr>'); 
	     	}
	     	 html.push('<tr>'); 
	   		 html.push('<td>平均分</td>'); 
	  	  	 html.push('<td colspan="2">'+data.score+'</td>'); 
	         html.push('</tr>'); 
			 html.push('</table>'); 
	        $('.lists').children().remove();
	        $('.lists').append(html.join(''));
        })
	}
	  
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
		   
		 <div class="content" id="content" style="position: relative;" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
			<p style="height:30px;line-height: 30px;width:100%;color:#fff;font-weight:bold;padding:0 4%;position: absolute;bottom:0;z-index:111111111111;background: rgba(129,91,84,0.6);">${product.productName}</p>
	   </div>   
	<div style="width:100%;text-align: center;">
		 <button style="background:#C3191F;box-shadow:0 2px #BAB9BA;border-radius:8px;color:#fff;margin:25px auto;font-weight: bold;padding:5px 15px;font-size: 18px;border:none;outline:none;">${product.productName}评分列表</button>        
	</div>	
	
	<div class="lists" style="text-align: center;margin:0 auto;width:100%;padding:0 4%;">
	  
	 </div>    
</body>
 




</html>