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
<title>主页</title>
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
	min-height:auto;
	background:#FFF !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	 font-size:100%;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
}

 .nav{
 height:150px;
 width:100%;
 background-image: -webkit-linear-gradient(top, #0E83E7, #063D8D);
 color:#fff;
 margin:0 auto;
 font-size:90%;
  }
  #nav_s::-webkit-input-placeholder {
  color: #fff;
}
.header{
width:85%;
height:180px;
padding:10px 3%;
border-radius:6px;
background: #fff;
margin:-70px auto 0;
box-shadow:1px 2px 5px #7DA2C2;
overflow: hidden;
font-size:90%;
}
.header ul li{
width:24%;
display: inline-block;
text-align: center;
margin-top:5px;
}
.header ul li img{
width:50%;
margin:10px 0;
}
.header ul li span{
display: inline-block;
}

 .main_in{
 width:92%;
 height:140px;
 box-shadow:1px 1px 4px 0px #7DA2C2;
 margin:5px auto 0;
 border-radius:5px;
 overflow: hidden;
 padding:0 3%;
 position: relative;
 font-size:90%;
 }
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/aublic.jsp"></jsp:include>
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"charset="utf-8"></script>
 <script src="<%=request.getContextPath() %>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"> 
<script type="text/javascript">
$(function(){
    $("#img_a").attr("src","lib/imgs/homes.gif");
    $("#index p").css({"color":"#0B6FCD"});
})
	function yujing(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberwarning';
   	}
   	function cha(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberInformation';
   	}
 	function zhu(){
   	 location.href=window.BASEPATH + 'cybersecurity/cybermanagement';
   	}
   	function jing(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberScenic';
   	}
    function ke(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberpassenger';
   	}
   	function wu(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberLogistics';
   	}	
   	function ji(){
   	 location.href=window.BASEPATH + 'cybersecurity/cyberrally';
   	}		
</script>	
<body>
<div class="nav">
   <div style="height:70px;width:20%;display: inline-block;line-height: 70px;padding:0 0 0 2%;">
     <img style="height:20px;" src="lib/imgs/dingwei.png">
     <span>河北 ></span>
   </div>
   <div style="height:70px;width:78%;display: inline-block;padding:0 2%;vertical-align:middle;position: relative;font-size:90%;">
     <input id="nav_s" placeholder="请输入关键字/词" style="width:100%;height:30px;padding:0 15%;text-align:center;margin-top:20px;border-radius:6px;border:none;outline:none;border:1px solid #fff;background-color: rgba(0,0,0,0.0)">
     <div style="border-top-right-radius: 6px;border-bottom-right-radius: 6px;height:30px;width:15%;background: #fff;text-align: center;line-height: 30px;position: absolute;right:2%;top:20px;">
      <img style="height:20px;" src="lib/imgs/sousuo.png">
     </div>
     <select style="background-color: rgba(0,0,0,0.0);border:none;outline:none;position: absolute;left:3%;top:20px;line-height: 30px;height:30px;">
      <option>景区</option>
      <option>酒店</option>
     </select>
   </div>
</div>
<div class="header" style="">
 <ul>
 <li onclick="yujing()"><img src="lib/imgs/yujing_red.gif"/><span>预警信息</span></li>
 <li onclick="cha()"><img src="lib/imgs/cha.png"/><span>信息查询</span></li>
 <li onclick="zhu()"><img src="lib/imgs/zhu.png"/><span>住宿管理</span></li>
 <li onclick="jing()"><img src="lib/imgs/jing.png"/><span>景区管理</span></li>
 <li onclick="ke()"><img src="lib/imgs/ke.png"/><span>客运管理</span></li>
 <li onclick="wu()"><img src="lib/imgs/wu.png"/><span>物流管理</span></li>
 <li onclick="ji()"><img src="lib/imgs/ji.png"/><span>集会管理</span></li>
 </ul>
</div>
<div class="main" style="width:100%;height:auto;">
 <div class="main_in" style="">
   <p style="text-align: center;line-height: 35px;font-size:150%;">景区名称：<span style="">清东陵</span></p>
   <div style="width:20%;display: inline-block;float: left;">
   <img style="width:60px;height:60px;border-radius:6px;margin:0 5%;" src="lib/imgs/zhengjian.png">
   </div>
   <div style="width:78%;height:auto;display: inline-block;">
    <ul style="line-height: 25px;font-size:80%;">
     <li><p>姓名：<span>xxx</span></p></li>
     <li><p>身份证号：<span>130281xxxxxxxxxxxx</span></p></li>
     <li><p>进入景区时间：<span>2019年9月10日</span><span>10：02：00</span></p></li>
     <li><p>购买票数：<span>2张</span></p></li>
    </ul>
   </div>
   <img style="width:25px;position: absolute;right:5%;" src="lib/imgs/yujing.gif">
 </div> 
</div>

<p style="height:50px;"></p>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
</body>
</html>