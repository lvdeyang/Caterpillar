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
<title>支付成功</title>
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
				<h2>收银台</h2>
			</div>
		</div>
		<hr/>
	</header>
	<!-- header End -->


	<!-- 订单提交成功 begin-->
	<section>
		<div class="containers">
			<div class="pc-space">
				<div class="pc-order-title clearfix">
					
					<div class="fl" style="font-size:14px">
						<p>订单号：${order.orderNO}</p></br>
						<p>收款商家：${order.shopName}&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;font-size: 16px">${order.productName}</font></p></br>
					</div>
				</div>
				<div class="pc-order-text clearfix">
					<div style="margin-left: 450px">
						<div style="display: inline;float:left"><img  src="<%=basePath%>/webtheme/theme/img/pay/ok.jpg"  ></div>
						<div style="display: inline ;margin-top: 5px;float:left"><h3>您的订单已支付成功,请保持电话畅通。。。。</h3></div>
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
