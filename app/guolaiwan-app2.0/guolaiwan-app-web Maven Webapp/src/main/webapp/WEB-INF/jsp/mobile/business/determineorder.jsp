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
<title>确认订单</title>
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
	background:#fff !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	
	text-decoration: none !important;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
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

  .swiper-container {
    width: 100%;
    padding:0;
    margin:0;
    height:200px;
  } 

  .swiper-container img {
    display: block;
    width: 100%;
  }
    
.weui-navbar{
 display: none !important;
}
  .inp::-webkit-input-placeholder{
        text-align: center;
}  

.liebiao li input{
    width: 100%;
    height: 60px;
    margin: 10px auto;
    text-align: right;
    margin-left: 2%;
    padding: 0 10%;
    border:none;
    background:#fff;
    outline: none;
    border:1px solid  rgb(230, 230, 230);
}
.fukuan{
  background: -webkit-linear-gradient(left,rgba(255,142,4,1),rgba(255,63,74,1)); /* Safari 5.1 - 6 */
}
</style>

</head>

<!-- 公共脚本引入 -->
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
   
	$(function() {
	    affirmorder();
	});
	
	function affirmorder(){
	 var url = window.BASEPATH + 'business/affirmorder?productid='+${productid};
	 $.get(url, null, function(data){
	     var html=[];
		 html.push("<div style='position: relative;width:100%;height:150px;line-height:150px;border:none;border-left:none;border-right:none;margin:0 auto;'>");
		 html.push("<img style='height:110px;width:40%;vertical-align: middle;display: inline-block;' src='http://www.guolaiwan.net/file"+data.url+"'/>");
		 html.push("<div class='huodong' style='display: inline-block;'>");
		 html.push("<p style='position: absolute;top:-43px;font-size:16px;margin-left:10px;'>"+data.ProductName+"</p>");
		 html.push("<p style='position: absolute;top:43px;color:#EC6D1E;font-size:16px;'>&nbsp￥<span id='spans'>"+data.ProductPrice+"</span><span style='text-decoration:line-through;color:#666666;margin-left:5%;'>￥"+data.productOldPrice+"</span></p>");
		 html.push("</div>");
		 html.push("</div>");
		 $('#order').append(html.join(''));	
	 });	 
	   
}
	
</script>
<script>
	layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#test1' //指定元素
	  });
	});
	   
     /*控制数量  */
	$(document).on('click','.p1',function(){
	 var value=parseInt($('.zhi').val())-1;
	 if($('.zhi').val()<=1){
	 return false;
	 }
	 $('.zhi').val(value);
	 var num1 = document.getElementById('spans').innerText;
	 var num2 = document.getElementById('shuliang').value;
	 //总额
	 var num3 = document.getElementById("zong");
	 num3.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
	})
    
	$(document).on('click','.p2',function(){
		 var value=parseInt($('.zhi').val())+1;
		 if($('.zhi').val()>=99){
		 return false;
		 }
		 $('.zhi').val(value);
		 var num1 = document.getElementById('spans').innerText;
		 var num2 = document.getElementById('shuliang').value;
		 //总额
		 var num3 = document.getElementById("zong");
		 num3.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
	})
	
	 /*手机号正则  */
	$(document).on('click', '.fukuan', function() {	
	var re = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
				if ($(".phone").val().search(re)) {
				       alert("请输入正确的手机号码");
					return false;
				}else{
				 alert("通过");
				}
				
	
	
	});	
</script>
<body>
	<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<div class="header-content">商户</div>
			</div>
		</div>	
	 <!-- 订单信息  -->
	  	<div id="order"  style="width:96%;height:auto;margin:0 auto;background:#fff;position: relative;overflow: hidden;">
	     	
	    </div>
	  
	  
	  
      <div class="layui-inline" style="width:96%;height:auto;position: relative;"> <!-- 注意：这一层元素并不是必须的 -->
  			<ul class="liebiao" style="color:black;">
  			<li>
  			<input type="text" placeholder="请您选择游玩日期"   style="cursor: pointer;width:100%;height:60px;margin:10px auto;text-align: right;margin-left:2%;padding:0 10%;" class="layui-input" id="test1">
	        <p style="position: absolute;top:30px;left:10%;">游玩日期</p>
	        <p style="position: absolute;top:30px;right:5%;">❯</p>
		    </li>
		    <li>
		    <input type="button">
		    <p style="position: absolute;top:110px;left:10%;">数量</p>
		    <p class="p1" style="position: absolute;top:110px;right:22%;font-size:14px;font-weight:bold;line-height:25px;display:inline-block;width:25px;height:25px;border-radius:50%;border:1px solid #666666;text-align: center;">—</p>
		    <input type="text" readonly="true"  class="zhi" id="shuliang" value="1"  style="padding:0;border:none;outline: none;width:20px;height:20px;position: absolute;right:14%;margin:0;top:112px;font-size:14px;font-weight:bold;text-align: center;">
		    <p class="p2" style="position: absolute;top:110px;right:4%;font-size:22px;color:#fff;background:#EC6D1E;display:inline-block;width:25px;height:25px;border-radius:50%;border:1px solid;text-align: center;">+</p>
		    </li>
		    <li>
		    <input type="button">
		    <img style="width:40px;height:40px;position: absolute;top:180px;left:9%;" src="lib/images/renlians.png"/>
	        <p style="position: absolute;top:183px;left:23%;font-weight:bold;">人脸识别</p>
	        <p style="position: absolute;top:203px;left:23%;font-size:12px;">请保持正脸</p>
		    <p style="position: absolute;top:190px;right:4%;font-size:22px;color:#fff;background:#0FB2FF;display:inline-block;width:25px;height:25px;border-radius:50%;border:1px solid;text-align: center;">+</p>
		    </li>
		    <li>
		    <input type="text" placeholder="请输入您的姓名" minlength="4" maxlength="4" style="padding:0 7%" >
		    <p style="position: absolute;top:270px;left:10%;">姓名</p>
		    </li>
		    <li>
		     <input class="phone" type="text" placeholder="请输入正确的手机号码" minlength="11" maxlength="11" style="padding:0 7%" >
		    <p style="position: absolute;top:350px;left:10%;">联系电话</p>
		    </li>
		    <li>
		    <input type="button">
		    <p style="position: absolute;top:430px;left:10%;">优惠卷</p>
		    <p style="position: absolute;top:430px;right:7%;"><span>0</span>张可用</p>
		    </li>
		    <li>
		    <input type="text" placeholder="备注留言" style="text-align:left;padding:0 7%;">
		    </li>
		    </ul>	 
	  </div>
	 
	     
	 <div style="background:#fff;height:60px;width:100%;border-bottom:1px solid  rgb(230, 230, 230);border-top:1px solid  rgb(230, 230, 230);position: fixed;bottom:0;">
	     	<p style="height:100%;float:left;text-align:center;width:55%;line-height: 60px;color:#EC6D1E;font-size:20px;font-weight:bold;display: inline-block;">￥<span id="zong">39.9</span></p>
	        <p class="fukuan" style="height:100%;float:right;text-align:center;width:45%;line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;">去付款</p>
	 </div>
     <div style="height:60px;width:100%;">
          
     </div> 
</body>






</html>