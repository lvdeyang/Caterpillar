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
<title>支付成功</title>
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
	min-height: auto;
	background: #fff !important;
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
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
	height: auto;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
}

.gotop {
	position: fixed;
	right: 20px;
	bottom: 50px;
	display: block;
	width: 50px;
	height: 50px;
	opacity: 0.8;
	z-index: 111111;
}

.header-in {
	background: linear-gradient(to right, rgba(254, 210, 119, 1),
		rgba(236, 112, 33, 1));
}

.dingdan li {
	border-bottom: 1px solid #D6D6D6;
}

.dingdan li p {
	/* float:left; */
	display: inline-block;
	text-align: left;
	width: 50%;
}

.dingdan li span {
	font-size: 12px;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

<script type="text/javascript">
	$(function() {
		$(window).scroll(function() {
			var aa = $(window).scrollTop(); //当前滚动条滚动的距离
			var bb = $(window).height(); //浏览器当前窗口可视区域高度
			var cc = $(document).height(); //浏览器当前窗口文档的高度 

			if (cc <= aa + bb) {
			}
		})
	})
	/*返回顶部  */
	$(function() {
		getAllProduct();
		$(window).scroll(function() {
			if ($(window).scrollTop() > 100) {
				$(".gotop").fadeIn(400);
			} else {
				$(".gotop").fadeOut(400);
			}
		});
		$(".gotop").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				'scrollTop' : 0
			}, 500);
			return false;
		});
	});

	function getAllProduct() {
		var url = "<%=basePath%>business/getproductbytype";
		$.post(url, {
			"type" : "${type}"
		}, function(data) {
			var html = [];
			for (var i = 0; i < 4; i++) {
				if (data[i].productAuditstatus == "审核通过") {
					if (data[i].productClassCode == "006") {
						html.push('<a onclick="gotodetailspage(' + data[i].id + ')"><div class="zhifu"  style="width:48%;border-radius:6px;height:auto;float:left;margin:10px 1%;background:#fff;position: relative;overflow: hidden;">');
					}
					html.push('<div class="chenggong" style="position: relative;width:100%;height:auto;border:none;border-left:none;border-right:none;margin:0 auto;">');
					html.push('<img style="height:150px;width:100%;border-radius:6px;vertical-align: middle;display: inline-block;" src="http://www.guolaiwan.net/file' + data[i].productShowPic + '"/>');
					html.push('<div class="zhifu-in">');
					html.push('<p style="font-size:16px;margin:10px 0 0 3%;font-weight:bold;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:180px;">' + data[i].productName + '</p>');
					html.push('<p style="font-size:12px;color:#C0C0C0;"><span style="color:#EC6D1E;font-size:16px;float:left;margin:10px 0 0 3%;">￥' + data[i].productPrice + '</span></p>');
					html.push('</div></div></div></a>');
				}
			}
			$('.tuijian').append(html.join(''));
		})
	}

	function getorderinfo(id) {
		location.href = window.BASEPATH + 'business/getdetermineorder?id=' + id;
	}

	function gotodetailspage(id) {
		location.href = window.BASEPATH + 'business/gotodetailspage?productId=' + id;
	}
</script>


<script type="text/javascript">

	$(function() {

		window.BASEPATH = '<%=basePath%>';
		var parseAjaxResult = function(data) {
			if (data.status !== 200) {
				$.toptip('data.message', 'error');
				return -1;
			} else {
				return data.data;
			}
		};


		var _uriRecomment = window.BASEPATH + 'phoneApp/orderInfo?orderId=${orderId}';

		$.get(_uriRecomment, null, function(data) {
			data = parseAjaxResult(data);
			if (data === -1) return;


			if ("积分" == data.order.payMode) { //判断是否是积分订单
				if (data.order.productPic.indexOf('null') == -1) {
					$('#proPic').attr('src', data.order.productPic);
				}
				$('#proName').html(data.order.productName);
				$('#proPrice').html(data.order.productPrice * 100);
				$('#amount').html(data.order.orderAllMoney * 100);
				$('#payAmount').html(data.order.payMoney * 100);
				$('#ydImage').attr('src', data.order.ydNO);
				$('#largeYd').attr('src', data.order.ydNO);
				$('#orderNo').html(data.order.id);
				$('#orderDate').html(data.order.payDate);
				$('#combo').html(data.order.comboName);
				$('#logistics').html(data.order.logisticsName);
				$('#bookspan').html(data.order.orderBookDate);
				$('#startspan').html(data.order.orderBookDate);
				$('#endspan').html(data.order.endBookDate);
				if (data.address == null) {
					$('#cuserName').html("");
					$('#cphone').html("");
					$('#caddress').html("");
				} else {
					$('#cuserName').html(data.address.consigneeName);
					$('#cphone').html(data.address.consigneePhone);
					$('#caddress').html(data.address.consigneeAddress);
				}

				if (data.order.bkCode == '0002') {
					$('#startDate').show();
					$('#endDate').show();
				} else if (data.order.bkCode == '0003' || data.order.bkCode == '0001') {
					$('#bookDate').show();
				}
				return;
			}

			if (data) {
				if (data.order.productPic.indexOf('null') == -1) {
					$('#proPic').attr('src', data.order.productPic);
				}


				if (data.order.productName == "") {
					$('#proName').html('到店支付');
					$('#proPrice').html('￥' + data.order.productPrice);
					$('#proCount').html('x' + data.order.productNum);
				} else {
					$('#proName').html(data.order.productName);
					$('#proPrice').html('￥' + data.order.productPrice);
					$('#proCount').html('x' + data.order.productNum);
				}
				$('#amount').html('￥' + data.order.orderAllMoney);
				$('#payAmount').html('￥' + data.order.payMoney);
				var str = data.order.ydNO;
			    var reg = RegExp(/票号/);
				if(str.match(reg)){
			 		$("#ydDiv").hide(); 
			 		$(".yd").text(str);
				}else{
					$('#ydImage').attr('src',data.order.ydNO);
				}
				$('#largeYd').attr('src', data.order.ydNO);
				$('#orderNo').html(data.order.id);
				$('#orderDate').html(data.order.payDate);
				$('#combo').html(data.order.comboName);
				$('#logistics').html(data.order.logisticsName);
				$('#bookspan').html(data.order.orderBookDate);
				$('#startspan').html(data.order.orderBookDate);
				$('#endspan').html(data.order.endBookDate);
				if (data.address == null) {
					$('#cuserName').html("");
					$('#cphone').html("");
					$('#caddress').html("");
				} else {
					$('#cuserName').html(data.address.consigneeName);
					$('#cphone').html(data.address.consigneePhone);
					$('#caddress').html(data.address.consigneeAddress);
				}
				if (data.order.orderState == "已发货") {
					var html = "";

					html = html + "<div class='weui-cell__hd'><label class='weui-label'>快递单号:</label></div>";
					html = html + " <div class='weui-cell__bd'><span style='font-size:12px;' id='caddress'>" + data.order.trackingnumber + "</span></div>"
					$("#weui-cell-1").html(html);

				}
				if (data.order.payDate == null || data.order.payDate == "") {
					$('#paytime').hide();
				}
				if (data.order.bkCode == '0002') {
					$('#startDate').show();
					$('#endDate').show();
				} else if (data.order.bkCode == '0003' || data.order.bkCode == '0001') {
					$('#bookDate').show();
				}
			}
			
			if (data.order.productId == 2469) {
				document.getElementById("nhDiv").style.display = "block";
				document.getElementById("ydDiv").style.display = "none";
			}
		});

		$(document).on('click', '#ydImage', function() {
			$("#large").popup();

		});

		function changeStatus() {
			setTimeout(function() {
				$.get(window.BASEPATH + "pubnum/order/status?orderId=${orderId}", null, function(data) {

					if (data.data == "TESTED") {
						$.toast("已验单");
					} else {
						changeStatus();
					}
				});
			}, 1000);
		}
		changeStatus();
	});
</script>


<body>
	<!-- 主页 -->
	<div class="header">
		<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
				class="icon-reorder icon-large"></span></a>
			<div class="header-content">商户</div>
		</div>
	</div>
	<div class="header-in"
		style="width:100%;height:130px;border-radius:0 0 100px 100px;text-align: center;">
		<img style="width:40px;height:40px;margin:20px auto 0;"
			src="lib/images/xiaolian.png" />
		<p style="color:#fff;font-weight: bold;">支付成功</p>
	</div>
	<div style="width:100%;height:auto;margin-top:-40px;z-index:11;">
		<div
			style="width:94%;height:70px;border-radius: 10px;margin:0 auto;border:1px solid #EFEFEF;background:#fff;overflow: hidden;box-shadow: #EFEFEF 0px 5px 5px;position: relative;">
			<img id="proPic"
				style="height:60px;width:60px;border-radius: 10px;margin:5px 0 0  5px;"
				src="lib/images/2.jpg" />
			<p id="proName"
				style="position: absolute;top:10px;font-size:12px;left:30%">丛林穿越</p>
			<p style="position: absolute;bottom:10px;font-size:12px;left:30%">
				<span id="proPrice">￥10</span><span id="proCount"></span>
			</p>
		</div>
		<ul class="dingdan"
			style="width:100%;padding:0 5% 20px 8%;line-height:50px;height:auto;">
			<li><p>订单总额</p> <span id="amount"></span></li>
			<li><p>实际总额</p> <span id="payAmount"></span></li>
			<li><p>订单号</p> <span id="orderNo"></span></li>
			<li id="startDate" hidden="hidden"><p>入住日期</p> <span
				id="startspan"></span></li>
			<li id="endDate" hidden="hidden"><p>离店日期</p> <span id="endspan"></span></li>
			<li><p>套餐</p> <span id="combo"></span></li>
			<li><p>快递</p> <span id="logistics"></span></li>
			<li><p>联系人</p> <span id="cuserName"></span></li>
			<li><p>手机号</p> <span id="cphone"></span></li>
			<li><p>详细地址</p> <span id="caddress"></span></li>
			<li id="paytime"><p>支付日期</p> <span id="orderDate"></span></li>
		</ul>
		<div
			style="width:100%;height:auto;margin:10px auto;text-align: center;"
			id="ydDiv">
			<img id="ydImage" style="height:80px;width:80px;"
				src="lib/images/logo.png" />
			<p style="font-size:12px;">扫码验单</p>
		</div>
 		<div class="weui-media-box__bd" style="display:inline-block;padding:10px 15px;">
		    <p class="yd" style="color: #999999;font-size: 13px;line-height: 1.2;"></p>
 		</div>
		<div
			style="width:100%;height:auto;margin:10px auto;text-align: center; display:none"
			id="nhDiv">
			<p id="NhMessage" style="color:red">订单信息已通过短信形式发送到您的手机，如有问题请联系客服。</p>

		</div>
	</div>

	<p
		style="color:#EE7826;font-weight: bold;text-align: center;margin:10px 5px;">
		——<span style="margin:10px 5px;">您可能还喜欢</span>——
	</p>
	<div class="tuijian"></div>

	<!-- 置顶 -->
	<div>
		<a href="javascript:;" class="gotop" style="display:none;"><img
			style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a>
	</div>
</body>

</html>
