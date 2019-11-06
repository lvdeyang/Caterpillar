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
				
			</div>
		</div>
		<div class="container clearfix">
			<div class="header-logo fl" style="width:212px;">
				<h1>
					<a href="#"><img src="<%=path %>/webtheme/theme/icon/logo.png"></a>
				</h1>
			</div>
		</div>
		<hr/>
	</header>
	<!-- header End -->


	<!-- 订单提交成功 begin-->
	<section>
		<div class="containers">
			<div class="pc-space">
				<div class="pc-order-title clearfix"  style="text-align: center;">
					
					<div class="fl" style="font-size:14px;width:100%;text-align: center;margin:0 auto;">
						<ul style="text-align: left;line-height:60px;margin-left:470px;">
						<li>订单号：${order.orderNO}</li>
						<li>收款商家：${order.shopName}</li>
						<li>商品名称：${order.productName}</li>
						</ul>
						</br>
				          
					</div>
				</div>
				<div class="pc-order-text clearfix">
					<div style="height:92px;text-align: center;margin:0 auto;">
						<div style="display: inline-block;vertical-align: middle;"><img  src="<%=basePath%>/webtheme/theme/img/pay/ok.png"  ></div>
						<div style="display: inline-block;margin-top: 5px;"><h3>您的订单已支付成功,请保持电话畅通呦~~</h3></div>
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
