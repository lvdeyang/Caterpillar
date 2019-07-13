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
<title>添加物流地址</title>
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
	background:#E0E0E0 !important; 
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
	height: auto;
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
.fuceng{
    position: fixed;
    width:100%;
    height:100%;
    left:0;
    top: 0;
    background-color:rgba(0,0,0,0.6);
    z-index: 10000;

}
.tanchuang input{
width:96%;
padding:0 0 0 30%;
height:50px;
border:none;
outline:none;
border-bottom:1px solid #D3D3D3;

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
$(document).on('click', '.dizhi', function(){ 
  $(".fuceng").fadeIn();
   $(".tanchuang").fadeIn();
});
  $(document).on('click', '.fuceng', function(){ 
  $(".fuceng").fadeOut();
  $(".tanchuang").fadeOut();
});
$(document).on('click', '.quxiao', function(){ 
  $(".fuceng").fadeOut();
  $(".tanchuang").fadeOut();
});   
</script>
<script>
	$(function(){
		getAllAddr();
	})
	function getAllAddr(){
        var _uriAddress = window.BASEPATH + 'phoneApp/address/list?userId=${userId}';
		$.get(_uriAddress, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			    var html=[];
			if(data && data.length>0){
				for(var i=0; i<data.length; i++){
				     var chkattr='';
				     if(i==0){
				         chkattr='checked="checked"';
				     }
			        html.push('<div style="width:100%;height:auto;background: #fff;margin:5px 0;position: relative;  ">');
					html.push('<p style="width:100%;height:30px;line-height:30px;padding:0 4% 0 15%;margin:0 auto;"><span style="text-align: left;"><span>'+data[i].consigneeName+'</span></span><span style="float:right;margin-right:8%;"><span>'+data[i].consigneePhone+'</span></span></p>');
					html.push('<p style="width:100%;height:30px;line-height:30px;padding:0 4% 0 15%;margin:0 auto;"><span>'+data[i].consigneeAddress+'</span></p>');
					html.push('<input onchange="addresschange(this.id)" id="'+data[i].id+'" '+chkattr+' type="radio" name="address" value="'+data[i].id+'" style="position: absolute;top:20px;left:5%;" />');
					html.push('<span style="position: absolute;right:5%;font-size:16px;font-weight: bold;top:25px;">></span>');
					html.push('</div>  ');
					}
				$('.addressList').children().remove();
			    $('.addressList').append(html.join(''));
			}else{
					html.push('<p style="color:#4C4C4C;text-align:center;font-weight:bold;">暂无默认地址</p>');
				$('.addressList').children().remove();
			    $('.addressList').append(html.join(''));
			}
		});
     }
     
     function deleteaddress(){
     	var addressId=$("input[name='address']:checked").val();
     	var url=window.BASEPATH + 'business/deleteaddress';
     	$.post(url,{"addressId":addressId},function(data){
     		$.alert("删除地址成功");
     		getAllAddr();
     	})
     }
     
     function addresschange(id){
     var url=window.BASEPATH + 'business/changeaddress';
     	$.post(url,{"addressId":id,"userId":${userId}},function(data){
     		$.toast("更改地址成功", "text");
     	})
     }
     
     function appendaddress(){
     	var userId=${userId};
     	var username=$('.username').val();
     	var telephone=$('.telephone').val();
     	var consigneeAddress=$('.consigneeAddress').val();
     	alert(userId+"-"+username+"-"+telephone+"-"+consigneeAddress)
     	var url=window.BASEPATH + 'business/appendaddress';
     	$.post(url,{"userId":userId,"username":username,"telephone":telephone,"consigneeAddress":consigneeAddress},function(data){
     		$.alert("添加成功");
     		$(".fuceng").fadeOut();
  			$(".tanchuang").fadeOut();
     		getAllAddr();
     	})
     }
     
     function gotopickingpurchase(){
   		location.href=window.BASEPATH + 'business/gotopickingpurchase?productId=${productId}';
   }
</script>




<body>
			<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
				class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
		</div>
		<p class="dizhi" style="height:50px;margin:5px 0;line-height: 50px;padding:0 5%;background: #fff;font-size:18px;font-weight: bold;"><span>添加新地址</span><span style="float:right;">></span></p>
		
		<div class="addressList">
		</div>
		
		
		<div style="width:100%;margin:0 auto;text-align: center;position: fixed;bottom:30px;">
		 <button onclick="deleteaddress()" style="border-radius:10px;font-size:18px;width:30%;height:40px;color:#fff;background:#EB6E1E;border:none;outline:none;">删除</button>
		 <button onclick="gotopickingpurchase()" style="border-radius:10px;font-size:18px;width:30%;height:40px;color:#fff;background:#EB6E1E;border:none;outline:none;">确定</button>
		</div>
					
		
		
	<div class='fuceng' style="display: none;"></div>
	   <div class="tanchuang" style="z-index:11111;width:100%;display:none;height:auto;padding:0 0 50px 0;text-align: center;background: #fff;position: fixed;bottom:0;position: r ">
	   <p style="width:96%;margin:0 auto;height:50px;line-height: 50px;font-size: 18px;font-weight: bold;border-bottom:1px solid #D3D3D3;">编辑收货地址</p>
	   <input type="text" class="username" placeholder="请您输入收货人姓名" style="">
	   <p style="position: absolute;top:65px;left:5%;font-weight: bold;">收货人姓名</p>
	   <input type="text" class="telephone" placeholder="请您输入收货人手机号" style="">
	   <p style="position: absolute;top:115px;left:5%;font-weight: bold;">手机号</p>
	   <input type="text" class="consigneeAddress" placeholder="请您输入详细地址" style="">
	   <p style="position: absolute;top:165px;left:5%;font-weight: bold;">详细地址</p>
	   <!-- 空白 -->
	   <p style="height:50px;"></p>
	   <div style="width:100%;height:60px;position: fixed;bottom:0;background: #fff;">
        <p class="quxiao" style="line-height: 60px;color:#EB6E1E;width:50%;display: inline-block;text-align: center;font-size:16px;font-weight: bold;">取消</p>
        <p onclick="appendaddress()" style="line-height: 60px;background:#EB6E1E;width:50%;color:#fff;float:right;display: inline-block;text-align: center;font-size:16px;font-weight: bold;">保存</p>
        </div> 
	  </div>
</body>





</html>