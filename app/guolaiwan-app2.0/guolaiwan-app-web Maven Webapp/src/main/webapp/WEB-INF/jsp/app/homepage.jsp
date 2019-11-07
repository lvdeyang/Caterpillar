<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl=WXContants.Website;
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

<title>公众号首页</title>

<!-- 公共样式引用 -->

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}


 .fuceng{
   position: fixed;
   width: 100%;
   height:100%;
   top:0;
   background-color:black;
   opacity:0.5;
   z-index: 10000;    
   text-align: center;
    
} 
.tishi img{
   position: fixed;
   	top:10%;
   	left:5%;
	width:90%;
	opacity:1;
	z-index: 10001;    
}
</style>

</head>
<link rel="stylesheet" type="text/css" href="<%=basePath%>mobile/lib/jQuery-weui-v1.2.0/lib/weui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>mobile/lib/jQuery-weui-v1.2.0/css/jquery-weui.css">
<!-- 公共脚本引入 -->
<script type="text/javascript" src="<%=basePath %>mobile/lib/jQuery-weui-v1.2.0/lib/jquery-2.1.4.js"></script>
<script type="text/javascript" src="../lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>

<script type="text/javascript">
    $(function() {
      //此操作会调起app并阻止接下来的js执行，进入已安装的app  ?userId=${userId}&merchantId=15&longitude=117.625103&latitude=40.188278&merchantName=万佛园
       $('body').append("<iframe src='app://media_resource_system.guolaiwan/mainActivity' style='display:none' target='' ></iframe>");
        //没有安装应用会执行下面的语句
        setTimeout(function(){window.location='http://www.guolaiwan.net/download/guolaiwan.apk'},10000);  
    });
    </script>





<body>
		<div style="margin: 30% auto;text-align: center;">
          
          </div>
	<div class="weui-loadmore" style="position: fixed;top:50%;left:50%;font-size:18px;font-weight:bold;margin-left:-135px;">
		<i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载</span>
	</div>
	<div class="fuceng">			
		</div>
		<div class="tishi">			
		  <img src="../lib/images/tishi.png"/>
		</div>
</body>


</html>