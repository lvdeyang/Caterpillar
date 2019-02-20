<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            订单详情
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    </head>
    <body>
    <div style="margin:22px">
    	<div style="margin-bottom:60px">
    		<div style="font-size:20px">订单详情</div>
    		<hr >
    		订单号：${order.orderNO}<br>
    		下单时间：${order.createDate}<br>
    		商品：${order.productName} X ${order.productNum}<br>
    		单价：${order.productPrice}<br>
    		总金额：${order.orderAllMoney}<br>
    		订单状态：${order.orderState}<br>
    		验单时间：${order.ydDate}
    	</div>
    	<div style="margin-bottom:60px">
    		<div style="font-size:20px">用户详情</div>
    		<hr >
    		订单号：${order.orderNO}
    		
    	</div>
    	<div style="margin-bottom:60px">
    		<div style="font-size:20px">商品详情</div>
    		<hr >
    		订单号：${order.orderNO}<br>
    		下单时间：${order.createDate}<br>
    		
    		
    	</div>
    	<div style="margin-bottom:60px">
    		<div style="font-size:20px">商家详情</div>
    		<hr >
    		订单号：${order.orderNO}
    		
    	</div>
    	
    	<div>
    	${product.productName}
    	</div>
    	<div>
    	${user.userPhone}
    	${merchant.shopName}
    	</div>
    	<div>
    	
    	</div>
    	
    </div>
    
    </body>
</html>