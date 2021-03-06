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
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">

<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<title></title>
<style>
* {
	margin: 0px;
	padding: 0px;
}

#div_txt {
	position: relative;
	width: 90%;
	margin: 0 auto;
}

#txt1 {
	width: 99%;
	height: 34px;
	margin: 15px auto;
	border-radius: 25px;
	text-indent: 50px;
	outline: none;
	border: none;
}

#div_items {
	position: absolute;
	border: 1px;
	width: 90%;
	display: none;
	margin: 0 auto;
	z-index: 999;
	background: #eee;
}

.div_item {
	width: 100%;
	height: 30px;
	font-size: 13px;
	line-height: 30px;
	margin: 5px 20px;
	/*   background: rgba(255,204,51, 0.5); */
}

.sou {
	position: absolute;
	top: 29%;
	left: 5%;
}

.btn {
	position: absolute;
	top: 24%;
	left: 83%;
	border-bottom-right-radius: 25px;
	border-top-right-radius: 25px;
	background-color: #00A0EA;
	color: white;
	border: none;
}

.main {
	position: relative;
	width: 100%;
	height: 30%;
	border-bottom: solid 2px #BDBDBD;
	overflow: hidden
}

.main_pid {
	position: absolute;
	height: 80%;
	bottom: 10%;
	left: 47.5%;
}

.daohang {
	position: absolute;
	display: inline-block;
}

html {
	height: 100%;
	background-color: #EEEEEE;
}
</style>

</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.15&key=b4020d9668380974bf11cdbf0b3eae4c"></script>
<script type="text/javascript">

	$(function() {
	        var loca = {};
		    var reqUrl = location.href.split('#')[0].replace(/&/g, "FISH");
			var _uri = window.BASEPATH + 'pubnum/prev/scan?url=' + reqUrl;
			$.get(_uri, null, function(data) {
				data = parseAjaxResult(data);
				if (data === -1) return;
				if (data) {
				    loca = data; 
					getLoation();
				}
			});

		function getLoation() {
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				//                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : loca.appId, // 必填，公众号的唯一标识
				timestamp : loca.timestamp, // 必填，生成签名的时间戳
				nonceStr : loca.nonceStr, // 必填，生成签名的随机串
				signature : loca.signature, // 必填，签名，见附录1
				jsApiList : [ 'checkJsApi', 'getLocation' ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function() {
				wx.getLocation({
					type : 'gcj02',
					success : function(res) {
						var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90  
						var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。  
						var speed = res.speed; // 速度，以米/每秒计  
						var accuracy = res.accuracy; // 位置精度  
						getCity(latitude, longitude);
					},
					cancel : function(e) {
						//这个地方是用户拒绝获取地理位置  
						alert("请打开GPS定位,");
					}
				});
				wx.error(function(res) {});
			});
		}
		var latitude=null;
		var longitude=null;
		function getCity(latitude, longitude) { //通过经纬度   获取高德位置
			latitude = (parseFloat(latitude)).toFixed(5); //保留经纬度后5位
			longitude = (parseFloat(longitude)).toFixed(5);
		}

	        var _uri = window.BASEPATH + 'vice/infor';
	        var para = {};
		    para.latitude = latitude;
		    para.longitude = longitude;
		    para.merchantId = '${merchantId}';
		    $.post(_uri, $.toJSON(para), function(data) {
			data = parseAjaxResult(data);
			var htm = [];
			for (var i = 0; i < data.length; i++) {
				htm.push('<div class="main">');
				htm.push('<a href="vice/merchant/agreemen?useid='+data[i].id+'"><img class="main_pic" id="main_pic"style="width:40%;height:130px; margin:20px 10px 20px;" src="'+data[i].parkingImg+'" /></a>');
				htm.push('<div class="main_pid style="width:100%;height:50%;float:right;overflow:hidden;">');
				htm.push('<a href="vice/merchant/agreemen?useid='+data[i].id+'"><p style="font-weight: bold;font-size:0.8rem; color:#333;">' + data[i].parkingName + '</p></a>');
				htm.push('<p style="font-size:0.7rem;margin:0 0 5px;overflow:hidden;">总车位：' + data[i].commonParking + '<span></span>剩余车位：<span>' + data[i].usedParking + '</span></p>');
				htm.push('<p style="font-size:0.7rem;lor:#BDBDBD; ">' + data[i].position + '  <span> 充电柱: ' + data[i].chargingColumn + '</span></p>');
				htm.push('<p style="font-size:0.7rem;color:#BDBDBD; "> 地址 ：<span>' + data[i].address + '</span></p>');
				if(data[i].distance!=null)htm.push('<p style="font-size:0.7rem;color:#BDBDBD; ">距离您 ：<span>'+ Math.floor(data[i].distance/1000)+'km</span><img class="daohang"style="width:18%;margin:3px;" src="lib/images/hang.png" /></p>');
				htm.push('<a href="vice/merchant/agreemen?useid='+data[i].id+'"><p id="yu"name="1" style="float:right;overflow:hidden;color:#FB7A32;font-size:0.65rem;">点击预约 ></p></a>');
				htm.push('</div>');
				htm.push('</div>');
				
			}
			$('body').append(htm.join('')); 
		}); 
		//隐藏列表框
		$("body").click(function() {
			$("#div_items").css('display', 'none');
		});

		//移入移出效果
		$(".div_item").hover(function() {
			$(this).css('background-color', '#1C86EE').css('color', 'white');
		}, function() {
			$(this).css('background-color', 'white').css('color', 'black');
		});

		//文本框输入
		$("#txt1").keyup(function() {
			if ($("#txt1").val() != "") {
				$("#div_items").css('display', 'block'); //只要输入就显示列表框
				var _uriYd = window.BASEPATH + 'vice/query';
				var params = {};
				params.input = $("#txt1").val();
				$.post(_uriYd, $.toJSON(params), function(data) {
					data = parseAjaxResult(data);
					$("#div_items").find("div").remove();
					var html = [];
					for (var i = 0; i < data.length; i++) {
						html.push('<div class="div_item">' + data[i].parkingName + '</div>');
					}
					$('#div_items').append(html.join(''));
				});
			}
			$(document).on('click', '.div_item', function() {
				$("#txt1").val($(this).text());
			});


			if ($("#txt1").val().length <= 0) {
				$(".div_item").css('display', 'none'); //如果什么都没填，保持全部隐藏状态
				return;
			}

			$(".div_item").css('display', 'none'); //如果填了，先将所有的选项隐藏

			for (var i = 0; i < $(".div_item").length; i++) {
				//模糊匹配，将所有匹配项显示
				if ($(".div_item").eq(i).text().substr(0, $("#txt1").val().length) == $("#txt1").val()) {
					$(".div_item").eq(i).css('display', 'block');

				}
			}

		});

		//项点击


	});
		
		$(document).on('click',".btn",function() {	
	     	$("body.main").remove();
		    $(".main").remove();
		    var uri = window.BASEPATH + 'vice/query';
		     var params = {};
		    params.input = $("#txt1").val();
	     	$.post(uri, $.toJSON(params), function(data) {
			data = parseAjaxResult(data);
			if(data.length == 0 ){
			alert("无此景点");
			}
			var ht = [];
			for (var i = 0; i < data.length; i++) {
				ht.push('<div class="main">');
				ht.push('<a href="vice/merchant/agreemen?useid='+data[i].id+'"><img class="main_pic" id="main_pic"style="width:40%;height:130px; margin:20px 10px 20px;" src="'+data[i].parkingImg+'" /></a>');
				ht.push('<div class="main_pid style="width:100%;height:50%;float:right;overflow:hidden;">');
				ht.push('<a href="vice/merchant/agreemen?useid='+data[i].id+'"><p style="font-weight: bold;font-size:0.8rem;color:#333;">' + data[i].parkingName + '</p></a>');
				ht.push('<p style="font-size:0.7rem;margin:0 0 5px;overflow:hidden;">总车位：' + data[i].commonParking + '<span> </span>剩余车位：<span>' + data[i].usedParking + '</span></p>');
				ht.push('<p style="font-size:0.7rem;lor:#BDBDBD; ">' + data[i].position + '  <span> 充电柱: ' + data[i].chargingColumn + '</span></p>');
				ht.push('<p style="font-size:0.7rem;color:#BDBDBD; "> 地址 ：<span>' + data[i].address + '</span></p>');
				ht.push('<p style="font-size:0.7rem;color:#BDBDBD; ">距离您 ：<span>2.6km</span><img class="daohang"style="width:18%;margin:3px;" src="lib/images/hang.png" /></p>');
			    ht.push('<a href="vice/merchant/agreemen?useid='+data[i].id+'"><p id="yu"name="1" style="float:right;overflow:hidden;color:#FB7A32;font-size:0.65rem;">点击预约 ></p></a>');
				ht.push('</div>');
				ht.push('</div>');
			}
			$('body').append(ht.join(''));
				return false;
			});
			
	
			
		
		});
	
	
</script>
<body>
	<div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="quit/merchant/smartparking" title="返回"> <返回首页 </a>
	  <span style="color:#ffffff;line-height:40px;font-size:18px;margin-left:-72px;">搜索停车场</span>
	</div> 
<div id="container"></div> 
	<div id="div_header">

		<!--表单的autocomplete="off"属性设置可以阻止浏览器默认的提示框-->
	
		<div id="div_txt">
			<img class="sou" style="width:7%;height:40%;"
				src="lib/images/sousuo.png " />
			<!--要模糊匹配的文本框-->
			<input type="text" id="txt1" placeholder="&nbsp输入停车场关键词">
			<button class="btn" style="width:20%;height:34px;border:none;outline:none;">搜索</button>
			<!--模糊匹配窗口-->
			<div id="div_items"></div>
		</div>
	
	</div>
	<div class="main">
		<!-- 	<img class="main_pic" id="main_pic"
			style="width:40%;height:80%; margin:15px 10px 15px;"
			src="lib/images/2.jpg" />
		<div class="main_pid style="width:100%;height:10%;float:right;overflow:hidden">
			<p style="font-size:12px;margin:0 0 10px;color:#BDBDBD;">
				距离您 ：<span>2.6km</span><img class="daohang"
					style="width:18%;margin:3px;" src="lib/images/hang.png" />
			</p>
			<p style="float:right;overflow:hidden;color:#FB7A32;font-size:15px;">点击预约
				></p>
		</div> -->
	</div>
  
</body>
</html>
