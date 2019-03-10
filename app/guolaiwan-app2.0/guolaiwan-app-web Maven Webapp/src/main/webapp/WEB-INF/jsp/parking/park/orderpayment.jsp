<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
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

<title></title>

<!-- 公共样式引用 -->


<base href="http://localhost:8080/guolaiwan-app-web/">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/guolaiwan-app-web/mobile/lib/jQuery-weui-v1.2.0/lib/weui.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/guolaiwan-app-web/mobile/lib/jQuery-weui-v1.2.0/css/jquery-weui.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/guolaiwan-app-web/mobile/lib/jQuery.mmenu/css/jquery.mmenu.all.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:8080/guolaiwan-app-web/web/lib/ui/iCheck/all.css">
<link rel="stylesheet"
	href="http://localhost:8080/guolaiwan-app-web/mobile/lib/Font-Awesome-3.2.1/css/font-awesome.min.css" />
<!--[if IE 7]>
<link rel="stylesheet" href="http://localhost:8080/guolaiwan-app-web/mobile/lib/Font-Awesome-3.2.1/css/font-awesome-ie7.min.css">
<![endif]-->


<style type="text/css">
.header_in {
	font-size: 1rem;
	font-weight: bold;
	width: 80%;
}

.header_a {
	position: relative;
	width: 100%;
}

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
	background-color: #EEEEEE;
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
	padding: 0;
	margin: 0;
	height: 200px;
}

.swiper-container img {
	display: block;
	width: 100%;
}

#distributeList {
	margin-top: 10px;
	padding-left: 10px;
	border-bottom: solid 2px #18b4ed;
	width: 100%;
	height: 35px;
}

#distributeList a {
	text-decoration: none;
	color: #CCC;
	font-size: 12px;
}

#distributeList a.current {
	text-decoration: none;
	color: #18b4ed;
	font-size: 20px;
}

#columnTable {
	width: 100%;
	margin-top: 10px;
}

#columnTable td {
	width: 20%;
	text-align: center;
	font-size: 12px;
}

.alert {
	width: 90%;
	text-align: center;
	color: #fff;
	margin: 10px auto;
	border-radius: 5px;
	line-height: 30px;
	cursor: pointer;
	background: #4ab819;
}

.confirm {
	background: #196fb8;
}

.open {
	background: #f88408;
}

.toast {
	background: #f80851;
}

.later {
	background: #a9a9a9;
}

#proContent img {
	width: 250px;
	height: 180px;
}

#proContent image {
	width: 250px;
	height: 180px;
}

#roomList {
	width: 100%;
}

#roomList div {
	width: 80px;
	height: 80px;
	background: green;
	float: left;
	margin-left: 7px;
	margin-top: 5px;
	text-align: center;
	color: #FFF;
	font-size: 11px;
}

.footer_on {
	position: fixed;
	top: 93%;
	/*  z-index: -1;*/
}
</style>

</head>

<!-- 公共脚本引入 -->
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/mobile/lib/jQuery-weui-v1.2.0/lib/jquery-2.1.4.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/mobile/lib/jQuery-weui-v1.2.0/lib/fastclick.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/mobile/lib/jQuery-weui-v1.2.0/js/jquery-weui.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/mobile/lib/jQuery.mmenu/js/jquery.mmenu.min.all.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/web/lib/ui/iCheck/icheck.min.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/web/commons/date/date.ext.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/mobile/lib/swiper.js"></script>
<script type="text/javascript"
	src="http://localhost:8080/guolaiwan-app-web/mobile/lib/jquery_json.js"></script>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">

	$(function() {
	  
		window.BASEPATH = 'http://localhost:8080/guolaiwan-app-web/';
		var parseAjaxResult = function(data) {
			if (data.status !== 200) {
				$.toptip('data.message', 'error');
				return -1;
			} else {
				return data.data;
			}
		};


	
	
		$(".swiper-container").swiper({
			loop : true,
			autoplay : 3000
		});

		$('#choosediv').show();
		$('#startdiv').show();
		$('#enddiv').show();
		$('#selRoomDiv').show();





		$(document).on('click', '#btn', function() {

			var startdiv = document.getElementById("startdiv").style.display;
			var sDate = new Date(document.getElementById("startDate").value.replace(/-T/g, "//"));
			var eDate = new Date(document.getElementById("endDate").value.replace(/-T/g, "//"));
			if (sDate >= eDate) {
				$.toast("离场时间不能小于进场时间", "forbidden");
				return false;
			}
			if (sDate < new Date) {
				$.toast("请输入正确的入场时间", "forbidden");
				return false;
			}

			if (startdiv != "none") {
				if ($("#startDate").val() == '') {
					$.toast("请选择入场日期", "forbidden");
					return false;
				} else if ($("#endDate").val() == '') {
					$.toast("请选择离场日期", "forbidden");
					return false;
				}

			}
			//预计停车小时
			var demoP = document.getElementById("spa");
			demoP.innerHTML = (parseInt(eDate - sDate) / 1000 / 60 / 60).toFixed(2);
			var num1 = document.getElementById('spa').innerText;
			var num2 = document.getElementById('qian').innerText;
			//应付金额
			var num3 = document.getElementById("jin");
			num3.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
			//总额
			var num4 = document.getElementById("zong");
			num4.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);

		});




		var prepay_id;
		var paySign;
		var appId;
		var timeStamp;
		var nonceStr;
		var packageStr;
		var signType;
		var orderNo;

		function onBridgeReady() {
			WeixinJSBridge.invoke(
				'getBrandWCPayRequest', {
					"appId" : appId, //公众号名称，由商户传入
					"timeStamp" : timeStamp, //时间戳，自1970年以来的秒数
					"nonceStr" : nonceStr,  //随机串
					"package" : packageStr,
					"signType" : signType, //微信签名方式：
					"paySign" : paySign //微信签名
				}
			);
		}



		$("#wu").click(function() {
			$.toast("抱歉，暂不支持其他支付方式", "forbidden");

		});

    



 
	   var uri = window.BASEPATH + 'vice/seleorder';
         var para = {};
		  para.uid = ${param.id};
		 $.post(uri, $.toJSON(para), function(data) {
			data = parseAjaxResult(data);
			var html = [];
			html.push('<span>'+data.cheng+'</span> <span>'+data.qu+'</span> <span>'+data.number+' 号车位</span>');
			$('.header_in').append(html.join('')); 
		  
			var htm = [];
			htm.push('<span>'+data.time+'</span>');
			$('.header_aa').append(htm.join('')); 
		  
			var ht = [];
			ht.push('<span>'+data.name+'</span>');
			$('.header_bb').append(ht.join('')); 
		    });
		
		
		 var _uri = window.BASEPATH + 'quit/query';
		 $.post(_uri, null, function(data) {
			data = parseAjaxResult(data);
		   var html = [];
		   	html.push('<span style="color:#F4984C">'+data.userHeadimg+'</span>'); 
			$('#head').append(html.join(''));
		   var htm = [];
			htm.push('<p id="nkname" style="float:right;overflow:auto;line-height:40px;;margin-right:15px"">'+data.userNickname+'</p>'); 
			$('.header_an').append(htm.join(''));
		
		})	/* ;	 */
		
		
		 var _util = window.BASEPATH + 'vice/selecost';
		  var param = {};
		  param.uid = ${param.id};
		 $.post(_util, $.toJSON(param), function(data) {
			data = parseAjaxResult(data);
		   var html = [];
		   	html.push('<span  style="color:#E80003;"><span id="qian">'+data.cost+'</span>元/小时 ></span>'); 
			$('#nkname').append(html.join(''));
		})	;	








	});
</script>



<body>
	<div>
		<div class="header_in" style="margin:20px 30px;">
			<p>
				<span style="float:right;font-size:0.5rem;margin-top:2%;">车场详情
					></span>
			</p>
		</div>
		<div class="header_a">
			<p class="header_aa" style="margin:5px 0 10px 28px">
				<span
					style="display:inline-block;color:#FFFFFF; background-color: #EB6100;height:25px;width:25px;text-align: center;">时</span>

			</p>
			<p class="header_bb" style="margin:5px 0 10px  28px">
				<span
					style="display:inline-block;color:#FFFFFF; background-color: #8FC320;height:25px;width:25px;text-align: center;">地</span>

			</p>
		</div>
	</div>
	<div class="header_on"
		style="background-color:#DCDCDC;width:100%;height:50px;">
		<p id="head"
			style="line-height:50px;margin-left:27px;font-size:1rem;font-weight: bold;">
			车牌号：</p>
	</div>
	<div class="header_an" style="width:100%;margin:0 auto;height:40px;">
		<p style="float:left;overflow:auto;margin-left:27px;line-height:40px;">费用说明:</p>
		<p id="nkname"
			style="float:right;overflow:auto;line-height:40px;;margin-right:15px"">

		</p>
	</div>

	<div class="main"
		style="width:100%;height:200px;background-color:#ffffff;">
		<div id="choosediv"
			style="display:none;width:95%;font-size:14px;font-weight:bold;margin-left:15px;float:left;margin-top:15px;color:#787878;">请选择预订时间</div>
		<div id="bookdiv"
			style="display:none;font-size:12px;float:left;width:100%;overflow-x:scroll">
			<div class="weui-cell">
				<div class="weui-cell__hd" style="width:20%;float:left;">
					<label class="weui-label">预定日期</label>
				</div>
				<div class="weui-cell__bd" style="width:80%;border:1px solid #CCC">
					<input id="bookDate" class="weui-input" type="datetime-local"
						placeholder="">
				</div>

			</div>
		</div>

		<div id="startdiv"
			style="display:none;font-size:12px;float:left;width:100%;">
			<div class="weui-cell">
				<div class="weui-cell__hd" style="width:20%;float:left;">
					<label class="weui-label">进场日期 :</label>
				</div>
				<div class="weui-cell__bd" style="width:80%;border:1px solid #CCC">
					<input id="startDate" class="weui-input" type="datetime-local"
						placeholder="">

				</div>
				<div class="weui-cell__bd"></div>
			</div>
		</div>

		<div id="enddiv"
			style="display:none;font-size:12px;float:left;width:100%;">
			<div class="weui-cell">
				<div class="weui-cell__hd" style="width:20%;float:left;">
					<label class="weui-label">离场日期 :</label>
				</div>
				<div class="weui-cell__bd" style="width:80%;border:1px solid #CCC">
					<input id="endDate" class="weui-input" type="datetime-local"
						onchange="test()" placeholder="">
					<script type="text/javascript">
						function test() {
							var startdiv = document.getElementById("startdiv").style.display;
							var sDate = new Date(document.getElementById("startDate").value.replace(/-T/g, "//"));
							var eDate = new Date(document.getElementById("endDate").value.replace(/-T/g, "//"));
							if (sDate >= eDate) {
								$.toast("离场时间不能小于进场时间", "forbidden");
								return false;
							}
							if (sDate < new Date) {
								$.toast("请输入正确的入场时间", "forbidden");
								return false;
							}
					
							if (startdiv != "none") {
								if ($("#startDate").val() == '') {
									$.toast("请选择入场日期", "forbidden");
									return false;
								} else if ($("#endDate").val() == '') {
									$.toast("请选择离场日期", "forbidden");
									return false;
								}
								$("#btn").click(function() {
									window.location.href = "vice/merchant/order";
					
								});
					
							}
					
							var demoP = document.getElementById("spa");
							demoP.innerHTML = (parseInt(eDate - sDate) / 1000 / 60 / 60).toFixed(2);
							var num1 = document.getElementById('spa').innerText;
							var num2 = document.getElementById('qian').innerText;
							//应付金额
							var num3 = document.getElementById("jin");
							num3.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
							//总额
							var num4 = document.getElementById("zong");
							num4.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
						}
					</script>
				</div>
				<div class="weui-cell__bd"></div>
			</div>
			<p style="margin:10px 0 10px 15px;font-size:12px;">
				预计停车：<span id="spa"
					style="display:inline-block;margin-left:10px;width:60px;background-color:#E5E5E5;text-align: center;color:red;">0</span>小时
			</p>
			<p style="margin-left:15px;font-size:12px;color:#919191;">
				应付金额￥<span id="jin" style="color:red;"> </span>请选择以下支付方式支付
			</p>
		</div>
	</div>

	</div>
	<div class="footer" style="width:100%;">
		<div class="footer_in" style="margin:20px 20px;height:100%;">
			<img alt="" src="lib/images/zhifu.png"
				style="width:40px;height:40px; vertical-align:middle;" /> <span
				style="display:inline-block;font-size:12px;line-height:35px;">微信支付</span>
			<input type="checkbox" name="" id="" value=""
				style="float:right;margin-top:10px;background-color:red;" />
			<p id="wu" style="text-align:center;color:#747474;margin:10px auto;">其他支付方式
				∨</p>
		</div>

		<div class="footer_on"
			style="width:100%;height:10%;background-color:#FFFFFF;font-size:12px;">
			<p
				style="display:inline-block;color:#FE923A;line-height:50px;font-weight: bold;margin-left:30px;">
				总额：￥<span id="zong"
					style="font-weight: bold;color:#FF7D13;font-size:18px;">0</span>
			</p>
			<button id="btn"
				style="height:50px;width:40%;color:#ffffff;background-color:#FF7D13;border:none;outline:none;float:right;font-size:18px;">去支付</button>
		</div>
	</div>
</body>


</html>