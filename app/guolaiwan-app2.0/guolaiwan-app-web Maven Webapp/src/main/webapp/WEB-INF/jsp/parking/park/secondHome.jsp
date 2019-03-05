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
<title></title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.header-pic {
	height: 80px;
	width: 100%;
	text-align: center;
	line-height: 10px;
}

.header-pic img {
	width: 75px;
	height: 75px;
	border-radius: 50%;
	margin: 30px 0px 10px;
}

.conformity {
	height: 50px;
	margin: 0 auto;
	width: 144px;
}

.pid {
	float: left;
	text-align: center;
	line-height: 50px;
	margin-left: 20px;
}

.pid p, .phone p {
	font-size: 16px;
	font-weight: bold;
}

.phone {
	/* float: right; */
	float: left;
	line-height: 50px;
	text-align: center;
}

.main {
	height: 100px;
}

.main input {
	border: 1px solid #CDCDCD;
}

.banner {
	width: 100%;
	position: absolute;
	top: 300px;
}

.banner img {
	width: 100%;
	height: 50%;
}

.btn {
	border-radius: 5%;
}

.btn a {
	color: white;
}

.main1 {
	margin: 0 auto;
	width: 244px;
	overflow: auto;
}

.main2 {
	margin: 5px auto 0px;
	width: 244px;
	overflow: auto;
}

#img_car {
	position: absolute;
	top: 15%;
	right: 40%;
}

#img_navigation {
	position: absolute;
	top: 40%;
	right: 65%;
}

#img_order {
	position: absolute;
	top: 40%;
	right: 15%;
}

#img_parking {
	position: absolute;
	top: 69%;
	right: 40%;
}

#img_Renewal {
	position: absolute;
	top: 42%;
	right: 40%;
}

html {
	height: 100%;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

<script type="text/javascript">

	$(function() {
	
	 
		var _uriYd = window.BASEPATH + 'smart/usere';
		$.post(_uriYd, null, function(data) {
			data = parseAjaxResult(data);
			/* data.userNickname  */
			var htm = [];
			htm.push('<img src="' + data.userHeadimg + '"/>');
			$('#img').append(htm.join(''));

			var html = [];
			html.push('<p id="username">' + data.userNickname + '</p>');
			$('.phone').append(html.join(''));
		});
		
		 var _uri = window.BASEPATH + 'quit/query';
		 $.post(_uri, null, function(data) {
			data = parseAjaxResult(data);
		   var html = [];
		   	html.push('<p class="input1" style="width:200px;height:30px;line-height:30px;border:none;outline:none;">' + data.userHeadimg + '</p>'); 
	        /*  html.push('<input class="input1" style="width:200px;height:30px;line-height:30px;border:none;outline:none;" type="text" name="test"  value="'+data.userHeadimg+'" />');  */
			$('.main1').append(html.join(''));
			
			var htm = [];
			htm.push('<p class="input2" style="width:200px;height:30px;line-height:30px;border:none;outline:none;">' + data.userNickname + '</p>'); 
			$('.main2').append(htm.join(''));
			
		})	;	



		var _uri = window.BASEPATH + 'quit/order';
		$.post(_uri, null, function(data) {
			 data = parseAjaxResult(data);
			if (data.orderStatus == "未使用") {
				var navigation = document.getElementById('img_navigation');
				navigation.onclick = function() {
					var Navigation = document.getElementById('test1');
					Navigation.src = 'lib/images/daohang.png';
					alert("我是导航页面")
				};
				var order = document.getElementById('img_order');
			   	order.onclick = function() {
					var Order = document.getElementById('test1');
					Order.src = 'lib/images/dingdan.png';
					alert("我是订单页面")
			};  
				var parking = document.getElementById('img_parking');
				parking.onclick = function() {
					var Parking = document.getElementById('test1');
					Parking.src = 'lib/images/tingche.png';
					alert("我是停车页面")
				};
			}
			
			//进入导航界面
			if (data.orderStatus == "正在使用") {
				var navigation = document.getElementById('img_navigation');
				navigation.onclick = function() {
					var Navigation = document.getElementById('test1');
					Navigation.src = 'lib/images/daohang.png';
					alert("我是导航页面")
				};
				 //进入停车界面
				var parking = document.getElementById('img_parking');
				parking.onclick = function() {
					var Parking = document.getElementById('test1');
					Parking.src = 'lib/images/tingche.png';
					alert("我是停车页面")
				};
                //进入续费界面
				var renew = document.getElementById('img_Renewal');
				renew.onclick = function() {
					var Renew = document.getElementById('test1');
					Renew.src = 'lib/images/xufei.png';
					alert("我是续费页面")
				};
				 var order = document.getElementById('img_order');
			   	order.onclick = function() {
					var Order = document.getElementById('test1');
					Order.src = 'lib/images/dingdan.png';
					alert("我是订单页面")
			};  
				
			};
				

			
        //进入订单界面
        if(data.orderStatus == "已使用"){
            var order = document.getElementById('img_order');
			   	order.onclick = function() {
					var Order = document.getElementById('test1');
					Order.src = 'lib/images/dingdan.png';
					alert("我是订单页面")
			}
        };
	
	
		//进入找车位界面
		var parkingspace = document.getElementById('img_car');
		parkingspace.onclick = function() {
			var Parkingspace = document.getElementById('test1');
			Parkingspace.src = 'lib/images/chewei.png';
			alert("我是找车位页面")
			window.location.href="vice/merchant/scenic";
		};
		 
	});
	
});
</script>


<body>

	<div class='header-pic'>
		<div id="img"></div>
		<div class="conformity">
			<div class="pid">
				<p>用户名：</p>
			</div>
			<div class="phone"></div>
		</div>
		<div class="main">
			<div class="main1" style="border-bottom:1px solid #CCCCCC;">
				<img class="img4"
					style="float: left;width: 30px;height: 30px;margin: 0 auto;"
					src=" lib/images/4.png" />

			</div>

			<div class="main2" style="border-bottom:1px solid #CCCCCC">
				<img class="img3"
					style="float: left;width: 30px;height: 30px;margin: 0 auto;"
					src="lib/images/che.png" />




			</div>
			<button class="btn"
				style="width: 202px;height: 35px;margin: 15px auto;background-color: #02A1E9";>
				<a href="smart/merchant/parking">退出</a>
			</button>
		</div>
	</div>

	<div class="banner ">
		<img src="lib/images/zhuanpan.png " class="img-responsive1 "
			id="test1" alt=" ">
		<div id="img_car" style="width:20%;height: 20%;border-radius:50%"
			onclick="data()"></div>
		<!--我要找车位-->
		<div id="img_navigation"
			style="width:20%;height: 20%;border-radius:50%;"></div>
		<!--导航-->
		<div id="img_order" style="width:20%;height: 20%;border-radius:50%;"></div>
		<!--订单详情-->
		<div id="img_parking" style="width:20%;height: 20%;border-radius:50%;"></div>
		<!--正在停车-->
		<div id="img_Renewal" style="width:20%;height: 20%;border-radius:50%;"></div>
		<!--续费-->
	</div>
</body>

</html>

