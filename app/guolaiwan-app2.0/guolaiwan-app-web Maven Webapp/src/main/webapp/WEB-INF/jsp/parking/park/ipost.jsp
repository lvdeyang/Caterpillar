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
	background-color: #F3F3F3;
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
      	var _uri = window.BASEPATH + 'phoneApp/getVideoPics';
      	var sName = {};
			sName.page = 0;
			sName.pageSize = 100;
			sName.userId = <%= request.getSession().getAttribute("userId")%>;
			sName.vType = "PICTURE";
		 $.get(_uri, sName, function(data) { 
			data = parseAjaxResult(data);
          for(var i = 0;i < data.size;i++) {
				htm.push('<div class="main" style="width:47%;height:50%;background-color: #ffffff; margin:1% 1% 3% 1%;float:left;border:1px solid #F6F6F6;">');
				htm.push('<div class="main_in" style="padding:10%;height:100%;width:80%;">');
				htm.push('<div class="main_on" style="height:20%;width:100%;position: relative;">');
				htm.push('<img class="pic" style="width:50px;height:50px;border-radius: 50%;position: absolute; top: 50%;left:20%;transform: translate(-50%, -50%);" src="'+data.userHeadimg+'" / >');
				htm.push('<span class="pid" style="position: absolute; top: 50%;left: 54%;transform: translate(-50%, -50%);color:#586F83;font-size:16px;">"'+data.userNickname+'"</span>');
				htm.push('</div>');
				htm.push('<img class="pic_a" style="width:100%;height:40%;margin:5% auto" src="'+data.headPic+'" / >');
				htm.push('<p class="pid_a" style="font-weight: bold;font-size:16px;overflow:hidden;width:9em;text-overflow:ellipsis;white-space:nowrap;">"'+data.name +'"</p>');
				htm.push('<p class="pid_b" style="color:#949494;font-size:14px;padding:2% auto;overflow:hidden;width:10em;text-overflow:ellipsis;white-space:nowrap;">"'+data.content +'"</p>');
				htm.push('<p style="color:#949494;font-size:12px;margin:10% auto;"><span>4周前</span><span style="float:right;">点赞("'+data.praiseCount+'")</span><span style="float:right;">评论("'+data.commentCount+'")</span></p>');
				htm.push('</div>');
				htm.push('</div>');
				}
         $('.nav').append(htm.join('')); 
      });
    });
 </script>
<body>
	<div class="nav" style="width:100%;height:100%;">

		<!--  <div class="main" style="width:47%;height:50%;background-color: #ffffff; margin:1% 2% 5% 2%;float:left;border:1px solid #F6F6F6;">
            <div class="main_in" style="padding:10%;height:100%;width:80%;">
                  <div class="main_on" style="height:20%;position: relative;">
                  <img class="pic" style="width:50px;height:50px;border-radius: 50%;position: absolute; top: 50%;left:20%;transform: translate(-50%, -50%);" src="lib/images/2.jpg" / >
                  <span class="pid" style="position: absolute; top: 50%;left: 54%;transform: translate(-50%, -50%);color:#586F83;font-size:16px;">兔子</span>
                  </div>
                  <img class="pic_a" style="width:100%;height:40%;margin:5% auto" src="lib/images/2.jpg" / >
                  <p class="pid_a" style="font-weight: bold;font-size:16px;overflow:hidden;width:10em;text-overflow:ellipsis;white-space:nowrap;">模拟</p>
                  <p class="pid_b" style="color:#949494;font-size:14px;padding:2% auto;overflow:hidden;width:10em;text-overflow:ellipsis;white-space:nowrap;">模拟数据,模拟数据</p>
                  <p style="color:#949494;font-size:12px;margin:10% auto;"><span>4周前</span><span style="float:right;">评论(1)</span><span style="float:right;">点赞(1)</span></p>
            </div>
      </div>
 -->

	</div>

</body>
</html>
