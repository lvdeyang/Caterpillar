<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String status = request.getParameter("status");
String message = request.getParameter("message");
if(message!=null) message = URLDecoder.decode(message, "utf-8");
%>

<!-- 设置el表达式支持 -->
<c:set var="status" value="<%=status %>"></c:set>
<c:set var="message" value="<%=message %>"></c:set>

<!DOCTYPE html>
<html>
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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>web/lib/ui/bootstrap/css/bootstrap.min.css" />
    
    <!-- font awesome -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>web/lib/icon/font-awesome/css/font-awesome.min.css" />
    
    <!-- ionicicons -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>web/lib/icon/ionicons/css/ionicons.min.css" />
    
    <!-- Theme style -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>web/lib/template/admin-lte/css/AdminLTE.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>web/lib/template/admin-lte/css/skins/_all-skins.min.css" />

    <!-- iCheck -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>web/lib/ui/iCheck/all.css" />

    <!--[if lt IE 9]>
    <script src="<%=basePath%>web/lib/ui/html5-lt-ie9/html5shiv.min.js"></script>
    <script src="<%=basePath%>web/ui/html5-lt-ie9/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
        window.BASEPATH = '<%=basePath%>';
    </script>
    
</head>
<body class="hold-transition login-page">
	<div class="login-box">
	    <!-- <div class="login-logo">库车管家</div> -->
	    <div class="login-box-body">
		    <p class="login-box-msg">用户登录</p>
		
		      <form action="<%=basePath %>console/login/do/login" method="post">
			      <div class="form-group has-feedback">
			          <input type="text" name="username" class="form-control" placeholder="用户名">
			          <span class="glyphicon glyphicon-user form-control-feedback"></span>
			      </div>
			      <div class="form-group has-feedback">
			          <input type="password" name="password" class="form-control" placeholder="密码">
			          <span class="glyphicon glyphicon-lock form-control-feedback"></span>
			      </div>
			      <div class="form-group has-feedback">
			          <div style="postion:relative; height:34px;">
			              <div style="position:absolute; left:0; top:0; right:110px; bottom:0;">
			              	  <input type="text" name="token" class="form-control" placeholder="验证码" style="padding-right:12px;">
			              </div>
			              <span style="position:absolute; right:0; top:0; border:1px solid #d2d6de; border-left:0;">
			              	  <img src="<%=basePath %>verify/code/get/109/32" class="commons-verify"/>
			              </span>
			          </div>
			      </div>
			      <c:if test="${status!=null && status!=200}">
				      <div style="font-size:14px; color:#d33724">
				      	  <i class="fa fa-exclamation-triangle" style="margin-right:10px; position:relative; top:1px; left:5px;"></i>
				      	  <span>${message}</span>
				      </div>
			      </c:if>
			      <div class="row">
			          <div class="col-xs-8">
				          <div class="checkbox icheck">
				              <label><input type="checkbox"> <span style="position:relative; top:1px;">记住密码</span></label>
				          </div>
			          </div>
			          <div class="col-xs-4">
			          	  <button type="submit" class="btn btn-primary btn-block btn-flat" style="margin-top:4.5px;">登录</button>
			          </div>
			      </div>
		      </form>
		
		      <!-- <div class="social-auth-links text-center">
			      <p>- 友情链接 -</p>
			      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
			      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
		      </div> -->
		      
		      <div class="row">
				  <div class="col-xs-12">
				      <div style="text-align:center;">
			          	<a href="#">忘记了密码？</a>
			          </div>
				  </div>
			  </div>
		  </div>
	  </div>
	
</body>

<script type="text/javascript" src="<%=basePath%>web/lib/frame/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>web/lib/ui/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>web/lib/ui/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="<%=basePath%>web/app/commons/commons.js"></script>
<script type="text/javascript">
	$(function(){
		$('input').iCheck({
	        checkboxClass:'icheckbox_square-blue',
	        radioClass:'iradio_square-blue',
	        increaseArea:'20%'
	    });
	});
</script>

</html>

