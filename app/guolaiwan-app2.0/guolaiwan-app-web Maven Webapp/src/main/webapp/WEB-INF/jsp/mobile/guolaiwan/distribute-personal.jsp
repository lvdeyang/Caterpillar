<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String regionCode=request.getParameter("regionCode");
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

<title>个人中心</title>

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
        height:120px;
      } 

      .swiper-container img {
        display: block;
        width: 100%;
      }
    
 
    
    

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script type="text/javascript">

	$(function() {

		$(document).on('click','#applyDistributor',function(){
		   location.href=window.BASEPATH + 'distributor/apply/index?disId=0';
		});
		$(document).on('click','#modDis',function(){
		   location.href=window.BASEPATH + 'distributor/apply/index?disId=${distributorId}';
		});
        $(document).on('click','#delDis',function(){
           location.href=window.BASEPATH + 'distributor/delete?disId=${distributorId}';
        });
	
	});
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">分销</div>
			</div>
		</div>
		<div class="content">
		    
		
		
		   <c:if test="${status=='null'}">
		    <c:if test="${distributorId=='0'}"> 
		    	<a id="applyDistributor" style="margin-top:10px;width:96%;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">申请分销商</a>
		    
			</c:if>
			</c:if>
			<c:if test="${status=='CHECKED'||status=='NOTPASSED'}">
			    <div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">法人</label>
					</div>
					<div class="weui-cell__bd">
						<input name="legalPerson"  disabled="disabled" value="${distributor.legalPerson }"  class="weui-input" type="text" placeholder="">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">开户行账号</label>
					</div>
					<div class="weui-cell__bd">
						<input name="bankNo" disabled="disabled" value="${distributor.bankNo }"  class="weui-input" type="number" pattern="[0-9]*"
							placeholder="">
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">地址</label>
					</div>
					<div class="weui-cell__bd">
						<input name="address" disabled="disabled" value="${distributor.address }" class="weui-input" type="text"
							placeholder="">
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">电话</label>
					</div>
					<div class="weui-cell__bd">
						<input name="phone" disabled="disabled" value="${distributor.phone }" class="weui-input" type="number" pattern="[0-9]*"
							placeholder="">
					</div>
				</div>
				
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">分销地区</label>
					</div>
					<div class="weui-cell__bd">
						${region}
					</div>
				</div>
			
			
		    
		        <div style="margin-top:20px;width:100%;height:100px;background:#18b4ed;color:#FFF;text-align:center">
				  
				  <c:if test="${status=='CHECKED'}">
				         
				  审核中
				  </c:if>
				  <c:if test="${status=='NOTPASSED'}">
				 审核未通过        
				  
				  </c:if>
	
				
				
				       <br><br><span>${reason}</span>
	
				
				</div>
				
				<a id="modDis" style="margin-top:10px;width:46%;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">修改</a>
				<a id="delDis" style="margin-top:10px;width:46%;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">取消申请</a>
		    </c:if>
		   
			<c:if test="${status=='PASSED'}">
			<c:if test="${distributorId!='0'}"> 
			        <div style="width:100%;height:100px;">
					    <a style="margin-top:20px;" href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
					      <div class="weui-media-box__hd">
					        <img style="border-radius:50%;width:60px;height:60px;" class="weui-media-box__thumb" src="${user.userHeadimg}">
					      </div>
					      <div class="weui-media-box__bd">
					        <h4 class="weui-media-box__title" style="font-size:12px;">${user.userNickname}(${region})</h4>
					        <p class="weui-media-box__desc" style="font-size:12px;">${distributor.address }</p>
					      </div>
		    		    </a>
					</div>
			
			    	<div class="weui-grids">
					  <a href="<%=basePath%>/distributor/my/index" class="weui-grid js_grid">
					    <div class="weui-grid__icon">
					      <img src="lib/images/icon_nav_msg.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      商品管理
					    </p>
					  </a>
					  <a href="<%=basePath%>/distributor/order/index" class="weui-grid js_grid">
					    <div class="weui-grid__icon">
					      <img src="lib/images/icon_nav_msg.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      采购订单
					    </p>
					  </a>
					  <a href="<%=basePath%>/distributor/sellorder/index" class="weui-grid js_grid">
					    <div class="weui-grid__icon">
					      <img src="lib/images/icon_nav_msg.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      卖出订单
					    </p>
					  </a>
					    <!-- <a href="<%=basePath%>/distributor/distribute/index/0/0" class="weui-grid js_grid">
					    <div class="weui-grid__icon">
					      <img src="lib/images/icon_nav_msg.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      分销首页
					    </p>
					  </a> -->
					   <!-- <a href="javascript:void(0)" class="weui-grid js_grid">
					    <div class="weui-grid__icon">
					      <img src="lib/images/icon_nav_msg.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					     个人信息
					    </p>
					  </a> -->
					</div>
			</c:if>
			</c:if>
		
		    
		</div>
	</div>
</body>


</html>