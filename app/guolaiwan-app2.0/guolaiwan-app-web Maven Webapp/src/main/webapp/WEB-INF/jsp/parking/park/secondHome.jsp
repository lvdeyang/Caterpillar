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
	width: 40%;
}

/* .pid {
	float: left;
	text-align: center;
	line-height: 50px;
	margin-left: 20%;
} */

.phone p {
	font-size: 16px;
	font-weight: bold;
}

.phone {
	/* float: right; */
	float: left;
	line-height: 50px;
	text-align: center;
	margin-left:15%;
}

.main {
	height: 100px;
}

.main input {
	border: 1px solid #CDCDCD;
}

.banner {
	height:50%;
	width: 100%;
	position: absolute;
	bottom:-10%;
	margin:0;
	padding:0;
}

.banner img {
    width: 100%;
	height:100%;
	margin:0;
	padding:0;
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

html,body{
	height:100%;
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
			html.push('<p id="username">用户名: ' + data.userNickname + '</p>');
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


        var _ur = window.BASEPATH + 'vice/vehicleorder'; //
		$.post(_ur,null, function(data) {
			data = parseAjaxResult(data);
			console.log(data.length);
			var html = [];
			for (var i = 0; i < data.length; i++) { 
				html.push('<span id="alert_a" style="margin:0 0 0 2%;">' + data[i].vehicle + ' </span>' + '的')
				html.push('<span id="alert_b">' + data[i].type+ '</span>' + '，在')
				html.push('<span id="alert_c">' + data[i].parking + '</span>' + '，')
				html.push('<span id="alert_d">' + data[i].district + '</span>')
				html.push('<span id="alert_e">' + data[i].number + '</span>  </br>') 
				var shi = new Date(data[i].dueTime);   /* '2019-03-22 14:00' */
				var today = new Date();
				var tan = ((shi - today) / 1000 / 60).toFixed(2);
			} 
				if (tan < 0) {
					myAlert('系统提示:', '您已超时，当前系统正在按停车场三倍停车费用为您计时，请您及时续费。');
				} else if (tan < 30  ) {
					myAlert('系统提示:', '<p id="alertss" style="font-weight:bold;color:red;margin:5px auto;height:auto;line-height:30px;"><span id="alert_a"></span><span id="alert_b"></span><span id="alert_c"></span><span id="alert_d"></span><span id="alert_e"></span></p> <p style="margin:0 2%;">的停车时间将于30分钟后结束。如果您需要续费，请在预订时间结束前30分钟内续费。如果您预订的车位超出预订时间，未能驶离停车场，系统将按照三倍每小时费用自动计算。</p>');
				} 
			$('#alertss').append(html.join(''));
		})	;
 



		var _uri = window.BASEPATH + 'quit/order';
		$.post(_uri, null, function(data) {
			data = parseAjaxResult(data);
			for (var i = 0; i < data.length; i++) {
				if ( data[i] == "NOTPAY" ) { //未支付
					var order = document.getElementById('img_order');
					order.onclick = function() {
						var Order = document.getElementById('test1');
						Order.src = 'lib/images/dingdan.png';
						
						setTimeout(function(){
						window.location.href = "vice/merchant/information";
						 },300);
					};
				}
				if ( data[i]=="PARKING" ) {//停车
					var navigation = document.getElementById('img_navigation');
					navigation.onclick = function() {
						var Navigation = document.getElementById('test1');
						Navigation.src = 'lib/images/daohang.png';
						setTimeout(function(){
						window.location.href = "vice/merchant/navigation";
						 },300);
					};
					//进入停车界面
					var parking = document.getElementById('img_parking');
					parking.onclick = function() {
						var Parking = document.getElementById('test1');
						Parking.src = 'lib/images/tingche.png';
							setTimeout(function(){
						window.location.href = "vice/merchant/parkings";
						 },300);
					};
					//进入续费界面
					var renew = document.getElementById('img_Renewal');
					renew.onclick = function() {
						var Renew = document.getElementById('test1');
						Renew.src = 'lib/images/xufei.png';
						setTimeout(function(){
						window.location.href = "pubnum/product/index/merchant/renewall";
							 },300);
					};
					var order = document.getElementById('img_order');
					order.onclick = function() {
						var Order = document.getElementById('test1');
						Order.src = 'lib/images/dingdan.png';
						setTimeout(function(){
						window.location.href = "vice/merchant/information";
							 },300);
					};

				}

				//进入导航界面
				if ( data[i] == "PAYSUCCESS" ) { //已支付
					var navigation = document.getElementById('img_navigation');
					navigation.onclick = function() {
						var Navigation = document.getElementById('test1');
						Navigation.src = 'lib/images/daohang.png';
						setTimeout(function(){
						window.location.href = "vice/merchant/navigation";
						 },300);

					};
					//进入续费界面
					var renew = document.getElementById('img_Renewal');
					renew.onclick = function() {
						var Renew = document.getElementById('test1');
						Renew.src = 'lib/images/xufei.png';
						setTimeout(function(){
						window.location.href = "pubnum/product/index/merchant/renewall";
						 },300);
					};
					var order = document.getElementById('img_order');
					order.onclick = function() {
						var Order = document.getElementById('test1');
						Order.src = 'lib/images/dingdan.png';
						setTimeout(function(){
						window.location.href = "vice/merchant/information";
						},300);
					};
				}
				;



				//进入订单界面
				if ( data[i] == "PAST") { //过期
					var order = document.getElementById('img_order');
					order.onclick = function() {
						var Order = document.getElementById('test1');
						Order.src = 'lib/images/dingdan.png';
						setTimeout(function(){
						window.location.href = "vice/merchant/information";
						},300);
					}
				}
				;
			}

			//进入找车位界面
			var parkingspace = document.getElementById('img_car');
			parkingspace.onclick = function() {
				var Parkingspace = document.getElementById('test1');
				Parkingspace.src = 'lib/images/chewei.png';
				setTimeout(function(){
				window.location.href = "vice/merchant/scenic";
				},300);
			};

		});

	});
</script>
<script type="text/javascript">
(function($) {
				$.alerts = {
					alert : function(title, message, callback) {
						if (title == null)
							title = 'Alert';
						$.alerts._show(title, message, null, 'alert', function(result) {
							if (callback) callback(result);
						});
					},

					confirm : function(title, message, callback) {
						if (title == null)
							title = 'Confirm';
						$.alerts._show(title, message, null, 'confirm', function(result) {
							if (callback) callback(result);
						});
					},


					_show : function(title, msg, value, type, callback) {

						var _html = "";

						_html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + title + '</span>';
						_html += '<div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';
						if (type == "alert") {
							_html += '<input id="mb_btn_ok" type="button" value="确定" />';
						}

						_html += '</div></div>';

						//必须先将_html添加到body，再设置Css样式  
						$("body").append(_html); GenerateCss();

						switch (type) {
						case 'alert':

							$("#mb_btn_ok").click(function() {
								$.alerts._hide();
								callback(true);
							});
							$("#mb_btn_ok").focus().keypress(function(e) {
								if (e.keyCode == 13 || e.keyCode == 27) $("#mb_btn_ok").trigger('click');
							});
							break;
						case 'confirm':

							$("#mb_btn_ok").click(function() {
								$.alerts._hide();
								if (callback) callback(true);
							});
							$("#mb_btn_no").click(function() {
								$.alerts._hide();
								if (callback) callback(false);
							});
							$("#mb_btn_no").focus();
							$("#mb_btn_ok, #mb_btn_no").keypress(function(e) {
								if (e.keyCode == 13) $("#mb_btn_ok").trigger('click');
								if (e.keyCode == 27) $("#mb_btn_no").trigger('click');
							});
							break;
						}
					},
					_hide : function() {
						$("#mb_box,#mb_con").remove();
					}
				}
				// Shortuct functions  
				myAlert = function(title, message, callback) {
					$.alerts.alert(title, message, callback);
				}

				myConfirm = function(title, message, callback) {
					$.alerts.confirm(title, message, callback);
				};



				//生成Css  
				var GenerateCss = function() {

					$("#mb_box").css({
						width : '100%',
						height : '100%',
						zIndex : '99999',
						position : 'fixed',
						filter : 'Alpha(opacity=60)',
						backgroundColor : 'black',
						top : '0',
						left : '0',
						opacity : '0.6'
					});

					$("#mb_con").css({
						zIndex : '999999',
						width : '90%',
						height : '40%',
						position : 'fixed',
						backgroundColor : 'White',
					});

					$("#mb_tit").css({
						display : 'block',
						fontSize : '14px',
						color : 'red',
						padding : '10px 15px 0',
						backgroundColor : '#fff',
						borderRadius : '15px 15px 0 0',
						fontWeight : 'bold'
					});

					$("#mb_msg").css({
						padding : '4px',
						lineHeight : '20px',
						textAlignleft : 'center',
						fontSize : '12px',
						color : '#000000'
					});

					$("#mb_ico").css({
						display : 'block',
						position : 'absolute',
						right : '10px',
						top : '9px',
						border : '1px solid Gray',
						width : '18px',
						height : '18px',
						textAlign : 'center',
						lineHeight : '16px',
						cursor : 'pointer',
						borderRadius : '12px',
						fontFamily : '微软雅黑'
					});

					$("#mb_btnbox").css({
						margin : '5px 0px 10px 0',
						textAlign : 'center'
					});
					$("#mb_btn_ok").css({
						width : '80px',
						height : '40px',
						color : 'white',
						border : 'none',
						borderRadius : '4px'
					});
					$("#mb_btn_ok").css({
						backgroundColor : '#FF8500'
					});
					var _widht = document.documentElement.clientWidth; //屏幕宽  
					var _height = document.documentElement.clientHeight; //屏幕高  

					var boxWidth = $("#mb_con").width();
					var boxHeight = $("#mb_con").height();

					//让提示框居中  
					$("#mb_con").css({
						top : (_height - boxHeight) / 1.2 + "px",
						left : (_widht - boxWidth) / 2 + "px"
					});
				}
			})(jQuery);
</script>

<body>
   <div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <!-- <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="smart/merchant/parking" title="返回"> <返回首页 </a> -->
	  <span style="color:#ffffff;line-height:40px;font-size:18px;">我的车位</span>
	</div> 
	<div class='header-pic'>
		<div id="img"></div>
		<div class="conformity">
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
				<a href="smart/merchant/parking?sal=1">退出</a>
			</button>
		</div>
	</div>

	<div class="banner">
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

