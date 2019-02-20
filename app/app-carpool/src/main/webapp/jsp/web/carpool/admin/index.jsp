<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String host = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String schema = request.getContextPath() + "/";
	String basePath = host + schema;
	String webBasic = "web/";
	String commonsPath = webBasic + "commons/";
	String libPath = webBasic + "lib/";
	String appPath = webBasic + "app/carpool/";
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

    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>ui/bootstrap/css/bootstrap.min.css" />

    <!-- datatables -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>ui/datatables/dataTables.bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>ui/datatables/extensions/FixedColumns/css/dataTables.fixedColumns.css" />

    <!-- iCheck -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>ui/iCheck/all.css" />

    <!-- font awesome -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>icon/font-awesome/css/font-awesome.min.css" />

    <!-- ionicicons -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>icon/ionicons/css/ionicons.min.css" />

    <!-- Theme style -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>template/admin-lte/css/AdminLTE.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=libPath %>template/admin-lte/css/skins/_all-skins.min.css" />

	<!-- commons -->
    <link rel="stylesheet" type="text/css" href="<%=basePath %><%=commonsPath %>commons.css" />

    <!--[if lt IE 9]>
    <script src="<%=basePath %><%=libPath %>ui/html5-lt-ie9/html5shiv.min.js"></script>
    <script src="<%=basePath %><%=libPath %>ui/html5-lt-ie9/respond.min.js"></script>
    <![endif]-->
    
    <title>库车管家控制台</title>

    <style type="text/css">
        a{cursor:pointer!important;}
        html, body{width:100%; min-height:100%}
    </style>

	<script type="text/javascript" >
		window.HOST = '<%=host %>';
		window.SCHEMA = '<%=schema %>';
	    window.BASEPATH = '<%=basePath %>';
	    window.WEBBASIC = '<%=webBasic %>';
	    window.COMMONSPATH = '<%=commonsPath %>';
	    window.LIBPATH = '<%=libPath %>';
	    window.APPPATH = '<%=appPath %>';
	</script>
	
</head>

<body class="hold-transition skin-yellow-light sidebar-mini">

<div class="wrapper">
    <!-- 单页面应用1 -->
</div>

</body>

<script type="text/javascript" src="<%=basePath %><%=libPath %>frame/requireJS/require.js"  defer async="true" data-main="<%=basePath %><%=commonsPath %>main"></script>

</html>
