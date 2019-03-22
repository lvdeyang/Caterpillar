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
    background:url(lib/images/beijing.jpg)no-repeat center ;
        background-size:cover;
        width:100%;
        height:100%;
   } 
   
   .header_in{
    background:url(lib/images/beijing2.png)no-repeat center ;
        background-size:cover;
        width:100%;
        height:100%;
   }
   .header_in p{
   font-size:14px;
    font-weight: bold;
   }
   .btn{
		    	position:fixed;
                 top:88%;
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
    // 更改车位 颜色
		 var  _take = window.BASEPATH + 'quit/query';
		 $.post(_take,null, function(data) {
			data = parseAjaxResult(data);
			var userHeadimg = data.userHeadimg;
		   var  _take = window.BASEPATH + 'vice/allmessage';
		  var para = {};
		  para.Headimg = userHeadimg;
		 $.post(_take,$.toJSON(para), function(data) {
		 data = parseAjaxResult(data);  
			for(var i=0;i<data.length/3;i++){     
			var htm = [];
			htm.push('<div class="header" style="height:30%;">');
			htm.push('<div class="header_in" style="width:100%;height:auto;padding-top:8%;">');
			htm.push('<img class="img4"style="width: 8%;height:8%;display: inline-block;margin-left:11%; vertical-align: middle;"src=" lib/images/che1.png" />');
			htm.push('<p style="display: inline-block;margin-left:1px;color:#ffffff;">'+userHeadimg+'</p>');
			htm.push('<a href="https://apis.map.qq.com/uri/v1/routeplan?type=drive&to='+data[i*3]+'&tocoord='+data[i*3+1]+'&policy=1&referer=2FNBZ-52HR4-OHEUW-XT2S7-ZJABQ-OJFIJ"><img class="img" style="float:right;width: 16%;height:8%;margin-right:10%;"src=" lib/images/daohang1.png"/> </a>');
			htm.push('<p style="margin:10% 0 0 20%;color:#ffffff;">'+data[i*3]+'</p>');
			htm.push('<img class="img4"style="width: 6%;height:6%;display: inline-block;margin-left:12%; vertical-align: middle;"src=" lib/images/dizhi.png" />');
			htm.push('<p style="padding:0 0 10% 2%;font-size:12px;color:#ffffff;display: inline-block;">地址：<span>'+data[i*3+2]+'</span></p>');
			htm.push('</div> ');
			htm.push('<div class="header_on" style="border-bottom: solid 2px #6D82EB;width:100%;"></div>');
			htm.push('</div>');	
			$("body").append(htm.join(''));   
			}	
		});
    }); 

	
    });  
 </script>
  <body>
  
   
   
  <!--  
    <div class="main">
   <div class="header_in" style="width:100%;height:auto;padding-top:8%;">
   <img class="img4"style="width: 8%;height:8%;display: inline-block;margin-left:11%; vertical-align: middle;"src=" lib/images/che.png" />
  
   <p style="display: inline-block;margin-left:1px;">冀 B5555</p>
   <img class="img" style="float:right;width: 16%;height:8%;margin-right:10%;"src=" lib/images/hang.png" />
   <p style="margin-left:20%;">清东陵停车场</p>
   <p style="font-size:12px;color:#B6B6B6;margin-left:20%;">地址：<span>遵化市石门镇六盘营村西</span></p>
   </div>  
   <div class="header_on" style="border-bottom: solid 10px #DCDCDC;width:100%;margin:10px auto 0;"></div>   
   </div>
     -->
   
  
  </body>
</html>
