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
       html,body{
       height:100%;
       }
      a:hover{
	   color:red;
}
       
    </style>
  </head>
  <!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.13&key=ff4672efcfc6279cbe3b2815dd70a1ec"></script> 
<script>
$(function() {
  var _uriYd = window.BASEPATH + '/smart/usere';
  $.post(_uriYd, null, function(data) {
      data = parseAjaxResult(data);
      var htm = [];
      htm.push('	<span>'+data.userNickname+'</span>');
	  if(data.userPhone != null){
	  htm.push('<span>'+data.userPhone+'</span>');
	  }
	    $('.pic').append(htm.join(''));
  });
   var _url = window.BASEPATH + 'quit/query';
		 $.post(_url, null, function(data) {
			data = parseAjaxResult(data);
		   	var htm = [];
		   	vehicle = data.userHeadimg;
		    htm.push('<span style="color:#FD923E;">'+data.userHeadimg+'</span>');
			$('.pid').append(htm.join(''));  
		
    });
    	var _ur = window.BASEPATH + 'vice/theorder';
		 $.post(_ur,null, function(data) {
			data = parseAjaxResult(data);
			var htm = [];
			for (var i = 0; i < data.length; i++) {
		   htm.push('<div class="main" style="width:100%;height:50%;background:#D5D5D5;position:relative">');
		   htm.push('<div class="main_in" style="width:95%;height:80%;background:#ffffff;position:absolute;overflow: hidden;margin:auto;left:0;right:0;top:0;bottom:0;border-radius:5px;">')
           htm.push('<p style="font-size:12px;color:#FD923E;margin:5% 0 2% 5%;position:absolute;top:-6px;right:0%;transform:rotate(45deg);width:auto;border-bottom:3px solid red;">'+data[i].orderStatus+'</p>')
           htm.push('<p style="font-size:14px;font-weight:bold;margin:5% 0 2% 5%;">' + data[i].parkingName + '</p>')
           htm.push('<p style="font-size:20px;font-weight:bold;margin:0 0 2% 5%;display: inline-block;"><span>'+data[i].parkingDistrict+'</span> <span>'+data[i].parkingNumber+'</span></p>')
           htm.push('<p  style="font-size:14px;font-weight:bold;padding-left:43%;display: inline-block;"><a class="xiangqing" style="color:#FD923E;" href="vice/merchant/order?uid='+data[i].id+'">订单详情 ></a></p>')
           htm.push('<div style="border-bottom: solid 1px #D5D5D5;width:90%;margin:0 auto;"></div>')
           htm.push('<p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;">进场时间：' + data[i].bookingTime + '</p>')
           htm.push('<p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;">离场时间：' + data[i].dueTime + '</p>')
           htm.push('<p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;display: inline-block;">停车时间：<span>' + data[i].stoppingTime + '</span> 小时</p>')
           htm.push('<p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;display: inline-block;">超出时长：<span> '+data[i].overTime+'</span> 小时</p>')
           htm.push('<p style="display: inline-block;margin-left:4%;">￥<span>' + data[i].parkingCost + '</span></p>')
           htm.push('</div>') 
		   htm.push('</div>');
			}
			$('body').append(htm.join('')); 
		}); 
         
       $(document).on('click', '.xiangqing', function() {   
		      $('a').removeAttr('style');
            $(this).attr('style','color:red');
		     
		});  
});


 </script>

  <body>
     	<div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="quit/merchant/smartparking" title="返回"> <返回首页 </a>
	  <span style="color:#ffffff;line-height:40px;font-size:18px;margin-left:-72px;">订单信息</span>
	</div> 
   <div class="header" style="width:100%;">
     <p class="pic" style="padding:20px 0 10px 7%;font-weight: bold;"></p>
     <p class="pid" style="margin:0px 0 10px 7%;font-weight: bold;">车牌号：</p>
    </div>
    
    <!--    xunhuan  -->
    <!--  <div class="main" style="width:100%;height:40%;background:#D5D5D5;position:relative">
     <div class="main_in" style="width:95%;height:80%;background:#ffffff;position:absolute;margin:auto;left:0;right:0;top:0;bottom:0;border-radius:5px;">
          <p style="font-size:14px;font-weight:bold;margin:5% 0 2% 5%;">清东陵停车场</p>
          <p style="font-size:20px;font-weight:bold;margin:0 0 2% 5%;display: inline-block;"><span>A 区</span> <span>007</span></p>
          <p id="" style="font-size:14px;font-weight:bold;padding-left:43%;display: inline-block;">订单详情 ></p>
          <div style="border-bottom: solid 1px #D5D5D5;width:90%;margin:0 auto;"></div>
          <p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;">进场时间：2019-03-13 08:30</p>
          <p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;">离场时间：2019-03-13 12:30</p>
          <p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;display: inline-block;">停车时间：<span>20</span> 小时</p>
          <p style="font-size:12px;margin:2% 0 2% 5%;color:#A3A3A3;display: inline-block;">超出时长：<span>0</span> 小时</p>
          <p style="display: inline-block;margin-left:10%;">￥<span>60</span></p>
       </div> -->
    </div> 
   
  </body>
</html>
