<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<meta name="renderer" content="webkit">
<title>提交订单</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=path %>/webtheme/theme/icon/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/base.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/home.css">
<script type="text/javascript"
	src="<%=path%>/webtheme/theme/js/jquery.js"></script>
<script type="text/javascript">
</script>

</head>
<body>
	<!--- header begin-->
	<header id="pc-header">
		<div class="BHeader">
			<div class="yNavIndex">
				<ul class="BHeaderl">
					<li><a href="#">登录</a></li>
					<li class="headerul">|</li>
					<li><a href="#">订单查询</a></li>
					<li class="headerul">|</li>
					<li><a href="#">我的收藏</a></li>
					<li class="headerul">|</li>
					<li id="pc-nav" class="menu"><a href="#" class="tit">我的商城</a>
						<div class="subnav">
							<a href="#">我的山城</a> <a href="#">我的山城</a> <a href="#">我的山城</a>
						</div></li>
					<li class="headerul">|</li>
					<li><a href="#" class="M-iphone">手机悦商城</a></li>
				</ul>
			</div>
		</div>
		<div class="container clearfix">
			<div class="header-logo fl" style="width:212px;">
				<h1>
					<a href="#"><img src="<%=path %>/webtheme/theme/icon/logo.png"></a>
				</h1>
			</div>
			<div class="pc-order-titlei fl">
				<h2>填写订单</h2>
			</div>
			<div class="pc-step-title fl">
				<ul>
					<li class="cur2"><a href="#">1 . 产品订购</a></li>
					<li class="cur"><a href="#">2 . 填写核对订单</a></li>
					<li><a href="#">3 . 选择支付方式</a></li>
				</ul>
			</div>
		</div>

	</header>
	<!-- header End -->


	<!-- 订单提交成功 begin-->
	<section>
		<div class="containers">
			<div class="pc-space">
				<div class="pc-order-title clearfix">
				</div>
				<div class="pc-border1">
					<div class="pc-order-text clearfix">
						<div style="margin-left: 500px">
							<div style="display: inline;float:left"><img  weight="26px" height="26px" src="<%=basePath%>/webtheme/theme/img/pay/error.png"  ></div>
							<div style="display: inline ;margin-top: 5px;float:left"><h3>${info}</h3></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- 订单提交成功 End-->


	<!--- footer begin-->
	<c:import url="${path}/web/foot"></c:import>
	<!-- footer End -->

	<script type="text/javascript">
     	
 </script>

</body>
</html>
