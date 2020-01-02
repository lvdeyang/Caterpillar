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
<title>停车登录</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.header-pic {
		
	width: 100%;
	text-align: center;
	
}

.header-pic img {
	width: 70px;
	height: 70px;
	border-radius: 50%;
	margin: 3% auto 1%;
}

.conformity {
	height: 40px;
	margin: 0 auto;
	
}



.phone p {
	font-size: 16px;
	font-weight: bold;
}

.phone {
	/* float: right; */
	
	line-height: 40px;
	text-align: center;
	
	
}


.main select {
	font-weight: bold;
	border: 1px solid #CDCDCD;
}

.main input {
	border: 1px solid #CDCDCD;
}

/* .banner img {
   
} */
.img-responsive{
 
   height: 55%;
    width: 100%;
    position:fixed;
    bottom:0;
	object-fit:cover;
}
.btn {
	color: white;
	border-radius: 5px;
	border:none;
	outline:none;
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

html,body{
	height: 100%;
	background-color:#E6E6E6 ;
	width: 100%;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
}
a {
	cursor: pointer !important;
}

a, a:link, a:active, a:visited, a:hover {
	color: inherit;
	text-decoration: none;
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


</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"charset="utf-8"></script>
<script type="text/javascript">
 
	$(function() {
		$(document).on('click', '#indexs', function() {
		     location.href=window.BASEPATH + '/pubnum/index';
		   });
   
	var _uri = window.BASEPATH + 'quit/query';
	var sal = ${param.sal}
		$.post(_uri, null, function(data) {
			data = parseAjaxResult(data);
          if(data.userHeadimg && data.userHeadimg != null &&  sal != 1){
          window.location.href="quit/merchant/smartparking?merchantId=${merchantId}";
          }
		});
	
		var _uriYd = window.BASEPATH + '/smart/usere';
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


		$(document).on('click', '.btn', function() {
			var _uriYd = window.BASEPATH + 'smart/setVehicle';
			var params = {};
			params.parking = $(".input1").val();
			params.parNumber = $(".s").val();
			var re = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
			if ($(".input1").val().search(re)) {
			       alert("请输入正确车牌");
				return false;
			} else {
				$.post(_uriYd, $.toJSON(params), function(data) {
				  var _uriY = window.BASEPATH + 'smart/setVeh';
			      var param = {};
					param.parking = $(".input1").val();
			      $.post(_uriY, $.toJSON(param), function(data) {
			      	data = parseAjaxResult(data);
 			        window.location.href="quit/merchant/smartparking?merchantId=${merchantId}";
 			      	$(".btn").css("box-shadow","5px 5px 10px #8E8F8F");
 			      });
				});
			}
		});
	});
   var h=$(window).height();
   $(window).resize(function(){
      if($(window).height()<h){
        $(".img-responsive").hide();
      }
      if($(window).height()>=h){
        $(".img-responsive").show();
      }
   
   })
    
</script>


<body>
		<div class="header">
			<div class="wrapper" >
			<a id="indexs" class="link-left"  href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
	   </div>
	<div class='header-pic'>
		<div id="img" style="height:80px;"></div>
		<div class="conformity">
			
			<div class="phone"></div>
		</div>
		<div class="main">
			<div class="main1" style="border-bottom:1px solid #CCCCCC;">
				<img class="img4"
					style="float: left;width: 30px;height: 30px;margin: 0 auto;"
					src=" lib/images/4.png" /> <input class="input1"
					style="width:200px;height:30px;line-height: 30px;border:none;outline:none;font-weight: bold;font-size:16px;background-color:#E6E6E6 ;"
					type="text" name="test" id="" value="" placeholder="请输入车牌号"
					maxlength="7" />
			</div>

			<div class="main2" style="border-bottom:1px solid #CCCCCC">
				<img class="img3"
					style="float: left;width: 30px;height: 30px;margin: 0 auto;"
					src="lib/images/che.png" /> <select class="s"
					style="height:30px;line-height: 30px;width: 202px;border: 0;outline: none;font-weight: bold;font-size:16px;background-color:#E6E6E6 ;">
					<option style="width:200px ">小型车</option>
					<option style="width:200px ">中型车</option>
					<option style="width:200px ">大型车</option>
                  </select>
                   
				</div>
				 <button class="btn"style="z-index:222;width: 202px;height: 35px;margin: 5% auto;background-color: #02A1E9";>保存
					</button>
			</div>
		</div>

		<!-- <div class="banner img">
			
		</div> -->
		<img class="img-responsive" src="lib/images/chundi.png" />
</body>

</html>
