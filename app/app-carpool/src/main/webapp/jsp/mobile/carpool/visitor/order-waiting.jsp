<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="zh-cmn-Hans">
<head>

    <!-- 声明文档使用的字符编码 -->
    <meta charset='utf-8'>
    <!-- 优先使用 IE 最新版本和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!-- 页面描述 -->
    <meta name="description" content=""/>
    <!-- 页面关键词 -->
    <meta name="keywords" content=""/>
    <!-- 网页作者 -->
    <meta name="author" content="name, email@gmail.com"/>
    <!-- 搜索引擎抓取 -->
    <meta name="robots" content="index,follow"/>
    <!-- 为移动设备添加 viewport -->
    <meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <!-- `width=device-width` 会导致 iPhone 5 添加到主屏后以 WebApp 全屏模式打开页面时出现黑边 http://bigc.at/ios-webapp-viewport-meta.orz -->

    <!-- iOS 设备 begin -->
    <meta name="apple-mobile-web-app-title" content="标题">
    <!-- 添加到主屏后的标题（iOS 6 新增） -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->

    <meta name="apple-itunes-app" content="app-id=myAppStoreID, affiliate-data=myAffiliateData, app-argument=myURL">
    <!-- 添加智能 App 广告条 Smart App Banner（iOS 6+ Safari） -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <!-- 设置苹果工具栏颜色 -->
    <meta name="format-detection" content="telphone=no, email=no"/>
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

	<title>等待接单</title>

    <!-- 公共样式引用 -->
	<jsp:include page="../../../../mobile/commons/jsp/style.jsp"></jsp:include>
    
    <style type="text/css">
        a{cursor:pointer!important;}
        a, a:link, a:active, a:visited, a:hover{color:inherit; text-decoration:none;}
        
        html, body{width:100%; height:100%; position:relative; background-color:#000;}
        *{box-sizing:border-box;}
        .z-depth-1-bottom{box-shadow:0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12), 0 1px 5px 0 rgba(0,0,0,.2) !important;}
        .z-depth-1-right{box-shadow:2px 0 2px 0 rgba(0,0,0,.14), 3px 0 1px -2px rgba(0,0,0,.12), 1px 0 5px 0 rgba(0,0,0,.2) !important;}
        .wrapper{height:100%; width:100%; position:relative;}
        
        .header{height:40px; line-height:40px; background-color:#18b4ed; color:#fff; border-bottom:1px solid #bababa;}
        
        .content-wrapper{width:100%; padding:30px 0; text-align:center;}
        .content{display:inline-block; width:160px; height:160px; position:relative;}
        .content-time{position:absolute; left:0; top:0; right:0; bottom:0;}
        
        .notice{padding:20px 20px; color:#fff; font-size:14px;}
        
        .foot{padding:20px 20px; position:absolute; bottom:0; width:100%;}
        .btn-custom{height:40px; line-height:40px; font-size:16px; border-radius:0; background-color:#18b4ed;}
    	.btn-custom:active{opacity:0.7 !important; background-color:#18b4ed !important;}
    </style>
	
</head>

<body>
	<!-- 数据信息 -->
	<input type="hidden" value="${orderId}" id="order-id" />
	<input type="hidden" value="${mobile}" id="mobile" />

	<div class="header" style="text-align:center;">订单调度中</div>
	<!-- <header style="padding:20px 0;">
        <h1 style="text-align:center; font-size:20px; color:#fff; font-weight:400; margin:0 15%;">订单调度中</h1>
    </header> -->
    <div class="content-wrapper">
    	<div class="content" id="content-sonic">
    		<div class="content-time">
    			<div class="wrapper">
    				<div style="width:100%; height:100%; display:table;">
    					<div id="content-time" style="width:100%; height:100%; display:table-cell; text-align:center; vertical-align:middle; color:#fff; font-size:18px;">00分00秒</div>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
    <div class="notice">提示：如果30分钟后没有司机接单，系统将会为您自动派单</div>
    <div class="foot">
    	<form action="<%=basePath %>order/visitor/cancel/order/${orderId}" method="POST" id="cancel-order-form"></form>
	    <button class="weui-btn weui-btn_primary btn-custom" id="cancel-order">取消订单</button>
    </div>
</body>

<!-- 公共脚本引入 -->
<jsp:include page="../../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script src="<%=basePath %>mobile/lib/canvas.sonic-v0.2/canvas.sonic.js"></script>

<script type="text/javascript">
	$(function(){
		var orderId = $('#order-id').val();
		var mobile = $('#mobile').val();
		var _uri = '';
		
		//解析ajax返回
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
		};
		
		var conf = {
			width:160,
			height:160,
			stepsPerFrame: 3,
			trailLength: 1,
			pointDistance:.01,
			fps:30,
			step:'fader',
			strokeColor:'#05E2FF', //'#D4FF00',
			setup: function() {
				this._.lineWidth = 6;
			},
			path:[
				['arc', 80, 80, 75, 0, 360]
			]
		};
		
		var sonic = new Sonic(conf);
		$('#content-sonic').append($(sonic.canvas));
		sonic.play();
		
		//等待计时
		var $time = $('#content-time');
		var timeGo = function(minutes, seconds){
			
			//构造一个开始时间
			var begin = new Date();
			begin.setMinutes(minutes);
			begin.setSeconds(seconds);
			
			setTime(begin);
			
			setInterval(function(){
				begin = begin.dateAdd(Date.prototype.S, 1);
				setTime(begin);
			}, 1000);
			
		};
		
		var setTime = function(time){
			$time.text(Date.prototype.formatNumber(time.getMinutes()) + '分' + Date.prototype.formatNumber(time.getSeconds()) + '秒');
		};
		
		var beginMinutes = '${minutes}';
		beginMinutes = beginMinutes?beginMinutes:0;
		var beginSeconds = '${seconds}';
		beginSeconds = beginSeconds?beginSeconds:0;
		
		timeGo(beginMinutes, beginSeconds);
		
		$('#cancel-order').on('click', function(){
			$.confirm('确认要取消当前订单吗？', function(){
				$('#cancel-order-form')[0].submit();
			});
		});
		
		//每五秒刷新订单状态
		var interval = setInterval(function(){
			_uri = window.BASEPATH + 'order/query/taking/status/' + orderId;
			$.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data == -1) return;
				if(data === 'TAKED'){
					clearInterval(interval);
					window.location = window.BASEPATH + 'visitor/index/' + mobile;
				}
			});
		}, 5000);
		
	});
</script>

</html>