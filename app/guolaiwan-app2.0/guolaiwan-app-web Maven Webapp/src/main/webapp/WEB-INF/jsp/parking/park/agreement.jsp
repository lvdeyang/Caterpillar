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
<title></title>
    <style type="text/css">
			* {
				margin: 0px;
				padding: 0px;
				
			}
			
			.header {
				width: 100%;
			
				border-bottom:solid 1px #959595;
			}
			.main li{
				list-style: none;
				list-style-type:decimal;
                list-style-position:inside;
                font-size: 14px;
                font-weight: bold;
                
			}
			
			html {
				height: 100%;
			}
			
		    .btn{
		    	position:fixed;
                 top:93%;
               /*  z-index: -1;*/         
		    }
			
		</style>
  </head>
  <!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
  $(function() {
      var uri = window.BASEPATH + 'vice/details';
         var para = {};
		  para.uid = ${param.useid};
		 $.post(uri, $.toJSON(para), function(data) {
			data = parseAjaxResult(data);
			for(var i = 0; i<data.length; i++){
			 if("PAYSUCCESS"==data[i] ){
		     window.location.href="vice/merchant/order?uid="+${param.useid};
		    }
			 if("PARKING"==data[i] ){
		     window.location.href="vice/merchant/parkings";
		    }
		    
		    
			}
		   
	     });
  
  
  
      	var _uri = window.BASEPATH + 'vice/regu';
      	   var params = {};
		  params.uid = ${param.useid};
		 $.post(_uri, $.toJSON(params), function(data) {
			data = parseAjaxResult(data);
			var htm = [];
				htm.push('<li>'+data.regulations+'</li>');
			$('#ol').append(htm.join('')); 
			var html = [];
				html.push('<p style="font-size: 16px;font-weight: bold; padding: 8% 0px 20px 15px;">'+data.parkingName+'管理条例</p>');
			$('.header').append(html.join('')); 
			  });
			
			
		   $(document).on('click','.btn1', function() {
     window.location.href="vice/merchant/scenic?car=";
      });  	
		   $(document).on('click','.btn2', function() {
     window.location.href="vice/merchant/parking?useid="+${param.useid};
      });  	
			
      });
  
</script>
  <body>
		<div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="quit/merchant/smartparking" title="返回"> <返回首页 </a>
	  <span style="color:#ffffff;line-height:40px;font-size:18px;margin-left:-72px;">管理条例</span>
	</div> 
		
		<div class="header">
		</div>

		
		<div class="main">
		<ol id ="ol"; style="margin: 20px 15px;">
			
		</ol>
		 <div id="kong"style="height:40px;width: 100%;">
	    	
	    </div>
		</div>	
	    <div class="btn" style="text-align: center;width: 100%;">
    <button class="btn1" style="background-color: white;width: 45%;height: 40px;border-radius: 25px;color: #F5820B;border-color: #F5820A;outline:none;font-size: 18px;">拒绝</button>
     <button class="btn2" style="background-color: #F5820B;width: 45%;height: 40px;border-radius: 25px;color:white;border:none;outline:none;font-size: 18px;">同意</button>
	    </div>
  </body>
</html>
