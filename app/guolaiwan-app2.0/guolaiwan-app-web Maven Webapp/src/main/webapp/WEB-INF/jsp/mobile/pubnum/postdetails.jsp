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
html, body {
	height: 100%;
}
#div1 img{
    width:100%;
    height:100%;
}
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
	$(function() {
		var htm = [];  
		var _uri = window.BASEPATH + 'phoneApp/videoPicInfo?vpId='+ ${param.id}+'&UserId=0';
		$.get(_uri, null, function(data) {
				data = parseAjaxResult(data);
				var json = JSON.parse(data.videoPic.content); //转换json
                var text= json.title; //获取标题
				var reg = new RegExp("\n","g");//g,表示全部替换。
				text=text.replace(reg,"	<br>");//替换
	            var newdiv = document.getElementById("p1");//添加
                newdiv.innerHTML = text ;
                var html = [];//添加头像 用户名
                html.push('<img class="pic"style="width:50px;height:50px;border-radius: 50%;position: absolute; top: 50%;left:8%;transform: translate(-50%, -50%);" src="'+data.videoPic.user.userHeadimg+'"/ >');
				html.push('<span class="pid" style="position: absolute; top: 50%;left: 24%;transform: translate(-50%, -50%);color:#586F83;font-size:16px;"> '+data.videoPic.user.userNickname+'</span>')   
                $('.main_on').append(html.join(''));    
                  
                var htm = [];
                var newdiv =json.content;
                for(var i =0;i<newdiv.length;i++){
  				   for(var j in newdiv[i]){
  				     var   cont =   newdiv[i][j]
  				     var reg = new RegExp("\n","g");//g,表示全部替换。
					 cont = cont.toString().replace(reg,"<br>");
					 var fill =  "img" == j ? "<img  src="+cont+" />" : "type" != j ? cont :'<br/>'; //添加文本图片
                     htm.push(fill);
   				   }
			    }
                $('#div1').append(htm.join(''));    
		}); 	
	});
</script>
<body>
	<div class="nav" style="width:100%;height:100%;">

		<div class="main"
			style="width:90%;height:100%;/* background-color: #ffffff; */margin:3% auto;">
			<p class="pid_a" id="p1" style="font-weight: bold;font-size:16px;"></p>
			<div class="main_in" style="height:100%;width:100%;margin:0 auto">
				<div class="main_on" style="height:10%;position: relative;">
				</div>
				<div id="div1" style=""></div>
			
			</div>
			
		</div>
		
	</div>
</body>
</html>
