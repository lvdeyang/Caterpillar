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
<title>订单支付</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=path %>/webtheme/theme/icon/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/base.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/home.css">
<script type="text/javascript"
	src="<%=path%>/webtheme/theme/js/jquery.js"></script>
<script type="text/javascript">
var basePath = '<%=basePath%>';
 
	$(function(){
		console.log(basePath);
		
	})
		
			
			
		
		

		/* //支付宝支付
		function aliPay(){
			console.log($($("form")[0]));
			$("#orderUuid").val(orderUuid);
			$("form")[0].submit();
        } */
        
        
        
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
					<li class="cur2"><a href="#">2 . 填写核对订单</a></li>
					<li class="cur"><a href="#">3 . 订单支付</a></li>
				</ul>
			</div>
		</div>
	
	</header>
	<!-- header End -->

	<!-- 订单提交成功 begin-->
	<section style="background-color: #E8ECF0">
		<div class="containers" style="padding-top: 20px;background-color: #E8ECF0">
		<div style="background-color: white;padding:20px 20px 20px 20px">
			<div class="pc-space" >
				<div class="pc-order-title clearfix">
					<div>
						<div style="display: inline;float:left"><img  src="<%=basePath%>/webtheme/theme/img/pay/ok.jpg"  ></div>
						<div style="display: inline ;margin-top: 5px;float:left"><h3>您的订单已提交，请支付。</h3></div>
						<div style="display: inline ;margin-top: 5px;float:right"><h3>待支付总金额：<font color="#EB5252" id="total-money">￥</font></h3></div>
					</div>
				</div>
				<div class="pc-border1" style="padding-top:20px">
					<c:forEach items="${orders}" var="order">
						<div class="pc-order-text clearfix order-info" style="background-color:#EEEEEE; padding-bottom:0; border-bottom:1px solid #e0e0e0;" data-money="${order.payMoney} data-uuid="${order.uuid}">
							<p>订单编号：${order.orderNO} &nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>user/order/list/1" >查看>></a></p>
							<p>产品信息：${order.productName}(数量:${order.productNum})</p>
						</div>
					</c:forEach>
				</div>
			</div>
		
			<div class="pc-space">
				<div class="pc-order-title clearfix">
					<div>
						<div style="display: inline;float:left"><img src="<%=basePath%>/webtheme/theme/img/pay/coin.jpg"></div>
						<div style="display: inline ;margin-top: 0px;float:left"><h3>请选择支付方式：</h3></div>
					</div>
				</div>
				<div class="pc-border" >
					<div class="pc-order-text clearfix">
						<div class="paydiv"  id="WEICHAT">
							<form action="<%=basePath%>website/wxpay/pay/batch" method="post" target="_blank">
								<c:forEach items="${orders}" var="order">
									<input type="hidden" name="orderUuids[]" value="${order.uuid}"/>
								</c:forEach>
								<button class="button2" type="submit"> 微信支付</button>
							</form>
						</div>
						<div class="paydiv">
							<form action="<%=basePath%>website/alipay/pay/batch" method="post"  target="_blank">
								<c:forEach items="${orders}" var="order">
									<input type="hidden" name="orderUuids[]" value="${order.uuid}"/>
								</c:forEach>
								<button class="button1" type="submit" id="alipaysub" >支付宝支付</button>
							</form>
						</div>
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
<script type="text/javascript">
	$(function(){
		var count_money = function(){
			var total_money = 0.00;
			
			$('.order-info').each(function(){
				total_money += parseFloat($(this).data('money'));
			});
			
			$('#total-money').text('￥'+total_money);
			return total_money;
		}
		
		count_money();
	});
</script>
</html>
