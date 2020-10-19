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

<title>管理员首页</title>

<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>

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
	min-height: 100%;
	background-color: #fff;
	position: relative;
	-webkit-text-size-adjust: none;
	background-color: #fbfbfb;
}

* {
	box-sizing: border-box;
}

.wrapper {
	height: 100%;
	width: 100%;
	position: relative;
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
	height: 100%;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
}


.content {
	
}


.weui-btn {
	color: #fff !important;
}

.weui-btn:after {
	border-radius: 0 !important;
}

    .weui-panel__bd{
        padding-bottom:0px !important;
    }
    .weui-cells_checkbox .weui-icon-checked:before{
       font-size:12px !important;
    }
    .weui-navbar__item{
      font-size:xx-small !important;
    }


</style>

</head>
<jsp:include page="../../../mobile/commons/jsp/scriptsec.jsp"></jsp:include>

<!-- 公共脚本引入 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">

	$(function() {
	    
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;
			}

	    };
	
	    $('#selectPoint').on('click',function(){
	       $("#selPoint").popup();
	    });
	    
	    $('.selPoint').on('click',function(){
	       $.closePopup();
	    });
	    
	
	});
		
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">用户状态</div>
			</div>
		</div>
		
		<div id="content" class="content">
		  <div style="width:100%">
			  <image id="selectPoint" style="width:50px;height:50px;float:left;" src="lib/images/caidan.png"/>
			  <div style="float:left;line-height:50px;width:200px;">人民银行打卡点</div>
		  </div>
		
			 <div class="weui-panel__bd" style="float:left;width:100%">
			    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			      <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="lib/images/logo.jpg">
			      </div>
			      <div class="weui-media-box__bd">
			        <h4 class="weui-media-box__title">王芃皓</h4>
			        <p class="weui-media-box__desc">遵化市人民法院</p>
			        <p class="weui-media-box__desc" style="margin-top:5px;color:green;font-size:x-small;font-weight:bold">打卡正常</p>
			        
			      </div>
			    </a>
			    <p style="width:100%;float:left"><div class="weui-cells weui-cells_checkbox" style="margin-top:0px">   
			          <label class="weui-cell weui-check__label" for="point1" style="font-size:xx-small">
				        
				        <div class="weui-cell__bd">
				          <p>今日巡逻统计</p>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				      </label>
				      
				     
				  </div></p>
			    
			    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			      <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="lib/images/logo.jpg">
			      </div>
			      <div class="weui-media-box__bd">
			        <h4 class="weui-media-box__title">于迪</h4>
			        <p class="weui-media-box__desc" >遵化市人民法院</p>
			        <p class="weui-media-box__desc" style="margin-top:5px;color:green;font-size:x-small;font-weight:bold">打卡正常</p>
			      </div>
			    </a>
			    
			    <p style="width:100%;float:left"><div class="weui-cells weui-cells_checkbox" style="margin-top:0px">   
			          <label class="weui-cell weui-check__label" for="point1" style="font-size:xx-small">
				        
				        <div class="weui-cell__bd">
				          <p>打卡记录</p>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				      </label>
				      
				     
				  </div></p>
			    
			    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
			      <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="lib/images/logo.jpg">
			      </div>
			      <div class="weui-media-box__bd">
			        <h4 class="weui-media-box__title">于迪</h4>
			        <p class="weui-media-box__desc" >遵化市人民法院</p>
			        <p class="weui-media-box__desc" style="margin-top:5px;color:green;font-size:x-small;font-weight:bold">打卡正常</p>
			      </div>
			    </a>
			    
			    <p style="width:100%;float:left"><div class="weui-cells weui-cells_checkbox" style="margin-top:0px">   
			          <label class="weui-cell weui-check__label" for="point1" style="font-size:xx-small">
				        
				        <div class="weui-cell__bd">
				          <p>打卡记录</p>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				        <div class="weui-cell__hd">
				          <input type="checkbox" class="weui-check" name="checkbox1" id="point1" checked="checked">
				          <i class="weui-icon-checked"></i>
				        </div>
				      </label>
				      
				     
				  </div></p>
			    
			 </div>
			 
			 <div id="selPoint" class="weui-popup__container popup-bottom">
			  <div class="weui-popup__overlay"></div>
			  <div class="weui-popup__modal">
			      <div class="weui-grids">
					  <a href="javascript:void(0)" class="weui-grid js_grid selPoint">
					    <div class="weui-grid__icon">
					      <img src="lib/images/8.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      人民银行打卡点
					    </p>
					  </a>
					  <a href="javascript:void(0)" class="weui-grid js_grid selPoint">
					    <div class="weui-grid__icon">
					      <img src="lib/images/8.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      人民银行打卡点
					    </p>
					  </a>
					  <a href="javascript:void(0)" class="weui-grid js_grid selPoint">
					    <div class="weui-grid__icon">
					      <img src="lib/images/8.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      人民银行打卡点
					    </p>
					  </a>
					  <a href="javascript:void(0)" class="weui-grid js_grid selPoint">
					    <div class="weui-grid__icon">
					      <img src="lib/images/8.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      人民银行打卡点
					    </p>
					  </a>
					  <a href="javascript:void(0)" class="weui-grid js_grid selPoint">
					    <div class="weui-grid__icon">
					      <img src="lib/images/8.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      人民银行打卡点
					    </p>
					  </a>
					  <a href="javascript:void(0)" class="weui-grid js_grid selPoint">
					    <div class="weui-grid__icon">
					      <img src="lib/images/8.png" alt="">
					    </div>
					    <p class="weui-grid__label">
					      人民银行打卡点
					    </p>
					  </a>
					  
					</div>
			  </div>
			</div>
		</div>
	</div>
	
	
	
</body>
</html>