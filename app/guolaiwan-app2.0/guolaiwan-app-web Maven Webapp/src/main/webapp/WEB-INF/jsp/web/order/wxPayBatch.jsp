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
<title>微信支付</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<%=path %>/webtheme/theme/icon/favicon.ico">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/base.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/webtheme/theme/css/home.css">
<script type="text/javascript"
	src="<%=path%>/webtheme/theme/js/jquery.js"></script>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var orderId = '${orderId}';
 	 $(function(){
 	console.log(orderId);
		//每5秒发送一个请求 持续60秒
		var timecount2=60;
		
		var interval = setInterval(function(){
			getState(orderId, function(stat){
				if(stat === true){
					clearInterval(interval);
					window.location.href=basePath+"user/order/payed/batch?order="+orderId; 
				}
			});
			//状态是PAYSUCCESS
			/* if($("#timeSate").val()=="OK"){
				clearInterval(interval);
				window.location.href=basePath+"user/order/payed?order="+orderUuid; 
			} */
		},5000);
		
		var interval2 = setInterval(function(){
			timecount2--;
			if(timecount2>=0){
				$("#sandy").html(timecount2);
			}
			console.log(timecount2);
			if(timecount2<0){
				clearInterval(interval);
				clearInterval(interval2);
				$("#wxpayImg").html("<div style='margin-left: 450px'><font color='red'>微信二维码失效，请回订单支付页面，重新选择支付方式。</font></div>");
				/* $.post(
					basePath+"user/order/payError",
					{"error":"timeover"},
					function(msg){
						console.log(msg)
						
					
					}
				
				) */
			/* 	wxpayImg */
			}
			
		},1000);
})   

	//微信post订单状态
    function getState(order, success){
    	$.ajax({
    		async: false,//同步请求
    		type:"post",
    		data:{"orderId":order},
    		url:basePath+"user/order/batch/state.do",
    		success:function(msg){
    			Ostate = msg.state;
				console.log(Ostate)	;
				//如果支付成功状态改成OK
				if(Ostate === true){
					$("#timeSate").val("OK");
				}
				if(typeof success==='function') success(Ostate);
			} 
    	});
    }

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
					<c:forEach items="${orders}" var="order">
						<div style="font-size:14px">
							<p>订单号：${order.orderNO}</p></br>
							<p>收款商家：${order.shopName}&nbsp;&nbsp;&nbsp;<font style="font-weight: bold;font-size: 16px">${order.productName}</font></p></br>
						</div>
					</c:forEach>
				</div>
				<div class="pc-border1">
					<div class="pc-order-text clearfix"  id="wxpayImg">
						<div id="payCodeImg" class="ac"><img align="center"width='250px' height='250px' src='${imagePath}'>
						<br>
						<img  align="center" width='150px' src='<%=basePath%>webtheme/theme/img/pay/wechat-qr.jpg'>
						<br>
						<br>
						<span align="center" style='font-size:20px'>￥${PayMoney}</span>
						</div>
						<div class="fr">
							<p>二维码有效时间</p>
							<p align="center" style="font-size:20px;color:red" id="sandy"></p>
						</div>
					</div>
				
						<input type="hidden" id="timeSate">
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
