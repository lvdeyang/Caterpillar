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

	<title>绑定手机</title>

    <!-- 公共样式引用 -->
	<jsp:include page="../../../../mobile/commons/jsp/style.jsp"></jsp:include>
    
    <style type="text/css">
        a{cursor:pointer!important;}
        a, a:link, a:active, a:visited, a:hover{color:inherit; text-decoration:none;}
        
        html, body{width:100%; min-height:100%; background-color:#fff; position: relative; -webkit-text-size-adjust:none; background-color:#fbfbfb;}
        *{box-sizing:border-box;}
        .z-depth-1-bottom{box-shadow:0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12), 0 1px 5px 0 rgba(0,0,0,.2) !important;}
        .z-depth-1-right{box-shadow:2px 0 2px 0 rgba(0,0,0,.14), 3px 0 1px -2px rgba(0,0,0,.12), 1px 0 5px 0 rgba(0,0,0,.2) !important;}
        .wrapper{height:100%; width:100%; position:relative;}
      
      	/* 页面样式 */
        .header{height:40px; line-height:40px; background-color:#18b4ed; color:#fff; border-bottom:1px solid #bababa;}
        .header .link-left{margin-left:10px; margin-right:10px; position:relative; z-index:1;}
        .header-content{height:100%; width:100%; position:absolute; left:0; top:0; padding-left:40px; padding-right:40px; text-align:center; z-index:0;}
      
      	.center{text-align:center !important;}
      	.img-wrapper{display:inline-block; width:100px; height:100px; border-radius:100%; overflow:hidden;}
    	.img-wrapper>img{width:100%; height:100%;}
    </style>
	
</head>

<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<div class="header-content">约吧拼车</div>
			</div>
		</div>
		<div class="content">
			
			<div class="weui-form-preview">
			    <div class="weui-form-preview__hd">
				    <div class="center">
					    <div class="img-wrapper">
					    	<!-- <img src="http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLuLyoiaXxa02F7vmD6tMUWnu0LVu10tY2mCjfZDeLy927CNjKib5DYIHhsVEnibGOMwKSTDn2t4ysVw/132" /> -->
							<img src="${user.headImgUrl}" />
						</div>
					</div>
					<div class="center">${user.name}</div>
			    </div>
			</div>
			
			<div class="weui-cells weui-cells_form" style="margin-top:0;">
				<form action="<%=basePath %>bind/mobile" method="POST" id="user-info">
					<div class="weui-cell weui-cell_vcode">
					    <div class="weui-cell__hd">
					        <label class="weui-label">手机号</label>
					    </div>
					    <div class="weui-cell__bd">
					        <input class="weui-input" name="mobile" placeholder="请输入手机号">
					    </div>
					    <div class="weui-cell__ft">
					        <button type="button" class="weui-vcode-btn">获取验证码</button>
					    </div>
					</div>
					<div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
					    <div class="weui-cell__bd">
					      <input class="weui-input" name="token" placeholder="请输入验证码" value="111">
					    </div>
					</div>
				</form>
			</div>
			
			<div class="weui-form-preview">
				<div class="weui-form-preview__ft">
				    <button type="button" id="save-info" class="weui-form-preview__btn weui-form-preview__btn_primary">保存信息</button>
			    </div>
			</div>
		</div>
	</div>
		
</body>

<!-- 公共脚本引入 -->
<jsp:include page="../../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script type="text/javascript">
	$(function(){
		
		$('#save-info').on('click', function(){
			$('#user-info')[0].submit();
		});
	});
</script>

</html>
