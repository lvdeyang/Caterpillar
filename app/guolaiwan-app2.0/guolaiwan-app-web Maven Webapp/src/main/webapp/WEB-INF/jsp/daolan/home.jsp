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
<title>Document</title>
<!-- 公共样式引用 -->
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        body, html {
            width: 100%;
            height: 100%;
            background: #f8f8f8;
            overflow: hidden;
        }

        #box {
            width: 95%;
            margin-top: 2%;
            border-radius: 15px;
            margin-left: 2.5%;
        }

        #box1 {
            width: 95%;
            margin-top: 2%;
            border-radius: 15px;
            margin-left: 2.5%;
        }

        header {
            width: 100%;
            height: 7%;
            background: #FFFFFF;
            display: flex;
            align-items: center;
            justify-content: center;
            position: fixed;
            top: 0;
        }

        #audioBox {
            width: 90%;
            height: 10%;
            display: flex;
            align-items: center;
            justify-content: center;
            position: fixed;
            bottom: 10px;
            left: 5%;
        }

        .imgbox {
            margin-top: 15%;
            height: 80%;
            overflow: auto;
        }
    </style>
</head>
<!-- 公共脚本引入 -->
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="text/javascript">
    var xmlHttpRequest;
    if (window.XMLHttpRequest) {
        xmlHttpRequest = new XMLHttpRequest();
    } else {
        xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttpRequest.open("GET", "AjaxServlet", true);

    let id = window.location.href.split("?")[1].split("=")[1]
    $.ajax({
        type: "GET",
        url: `http://www.guolaiwan.net/newPhoneController/getChildProById/${id}`,
        dataType: "json",
        success: function (obj) {
            console.log(obj)
            $("#box").attr('src', obj.path + obj.vo.childPic.split(",")[0])
            $("#box1").attr('src', obj.path + obj.vo.childPic.split(",")[1])
            $("#audio1").attr('src', obj.path + obj.vo.chineseGirl)
            $("#audio2").attr('src', obj.path + obj.vo.chineseBoy)
            $("#title").html(obj.vo.childName)
        },
        error: function (jqXHR) {
            console.log("Error", id, jqXHR);
        }
    });
</script>
<body>
<header>
    <p id="title"></p>
</header>
<div class="imgbox">
    <img src="" alt="" id="box">
    <img src="" alt="" id="box1">
</div>
<p id="dt"></p>

<div id="audioBox">
    <audio controls="controls" autoplay="autoplay" src="" id="audio1" controlsList="nodownload"></audio>
</div>
</body>
</html>