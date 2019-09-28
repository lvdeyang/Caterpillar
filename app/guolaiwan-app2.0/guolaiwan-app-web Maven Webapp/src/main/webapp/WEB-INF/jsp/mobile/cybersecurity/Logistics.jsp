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
<title>物流管理</title>
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
 background-image: -webkit-linear-gradient(top,#063D8D,#0E83E7 );
 color:#fff;
 margin:0 auto;
 text-align: center;
  font-size:90%;
  }
  .layui-input::-webkit-input-placeholder {
  color: #fff;
}
#nav_s::-webkit-input-placeholder {
  color: #fff;
}
 .main_in{
 width:92%;
 height:auto;
 box-shadow:1px 1px 4px 0px #7DA2C2;
 margin:5px auto 0;
 border-radius:5px;
 overflow: hidden;
 padding:0 3%;
 position: relative;
 background: #fff;
 font-size:90%;
 }

 .layui-laydate-main{
 width:100% !important;
 }
 .layui-laydate {
  text-align: center !important;
  margin:0 auto !important;
  left:50% !important;
  margin-left:-136px !important;
 }
 .layui-input{
  width:20% !important;
  text-align: center !important;
  padding:0 !important;
  display: inline-block !important;
  height:25px !important;
  margin-top:20px; 
  border-radius:4px !important;
 }
 
 .layui-laypage span{
padding:0 5px !important;
 border-radius:4px !important;
 color:#fff;
  border:none !important;
   margin:0 2px !important;
   
}
.layui-laypage a{
color:#fff;
 padding:0 5px !important;
 border-radius:4px !important;
 border:none !important;
 margin:0 2px !important;
 height:23px !important;
 line-height: 23px !important;
}
.layui-laypage .layui-laypage-curr .layui-laypage-em{
background: #024D8F !important;
height:20px !important;
width:20px !important;
border-radius:50% !important;
text-align: center !important;
top:4px !important;
left:-3px !important;
}
.layui-laypage-next em, .layui-laypage-prev em{
 font-size:90% !important;
}
.layui-laypage-last,.layui-laypage-next,.layui-laypage-prev,.layui-laypage-first{
color:#fff !important;
background: #024D8F !important;
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

layui.use('laydate', function(){
  var laydate = layui.laydate;
    //年选择器
  laydate.render({
    elem: '#test2'
  
  });
  
  });
  layui.use(['laypage', 'layer'], function(){
		  var laypage = layui.laypage
		  ,layer = layui.layer;
		   window.BASEPATH = '<%=basePath%>';
		  laypage.render({
		    elem: 'footer'
		   /*  ,count: ${count} */
		    ,groups:3
		    ,first: '首页'
		    ,last: '尾页'
		    ,prev: '<em>上一页</em>'
		    ,next: '<em>下一页</em>'
		     ,jump: function(obj, first){
		    //obj包含了当前分页的所有参数，比如：
		    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
		    console.log(obj.limit); //得到每页显示的条数
		    //getModularClassInfo(obj.curr,obj.limit);
		    //首次不执行
		    if(!first){
		       layer.msg('第'+ obj.curr +'页', {offset: 'b'});
		    }
		  }
		  }); 
		  }); 
  });
</script>
<body>
<a class="link-left" href="#side-menu" style="position: fixed;color:#fff;top:20px;left:5%;">
<span  class="icon-reorder icon-large"></span>
</a>
<div class="nav">
    日期： <input style="background-color: rgba(0,0,0,0.0)" type="text" class="layui-input" id="test2" placeholder="请选择日期">
     物流公司：
      <select style="background-color: rgba(0,0,0,0.0);display: inline-block;border:none;">
       <option>韵达</option>
       <option>顺丰</option>
      </select>
   <div style="height:70px;width:88%;display: inline-block;padding:0 2%;vertical-align:middle;position: relative; font-size:90%;">
     <input id="nav_s" placeholder="人名" style="width:60%;height:30px;padding:0 0 0 6%;text-align:center;margin-top:10px;border-radius:6px;border:none;outline:none;border:1px solid #fff;background-color: rgba(0,0,0,0.0);">
      <img style="height:20px;position: absolute;left:23%;top:15px;" src="lib/images/sousuo.png">
    
   </div>
</div>
<div class="main" style="width:100%;height:auto;margin-top:-50px;">
 
  <div class="main_in" style="">
   <p style="text-align: center;line-height: 35px;font-size:150%;">快递公司：<span>韵达快递</span></p>
   <div style="width:20%;display: inline-block;float: left;">
   <img style="width:60px;height:60px;border-radius:6px;margin:0 5%;" src="lib/imgs/zhengjian.png">
   </div>
   <div style="width:78%;height:auto;display: inline-block;">
    <ul style="line-height: 25px;font-size:80%;">
     <li><p>快递单号：<span>xxxxxxxxx</span></p></li>
     <li><p>寄件人姓名：<span>xxx</span></p></li>
     <li><p>寄件人地址：<span>河北省xxxxx</span></p></li>
     <li><p>联系电话：<span>187xxxxxxxx</span></p></li>
     <li><p>收件人姓名：<span>xxx</span></p></li>
     <li><p>收件人地址：<span>河北省xxxxx</span></p></li>
     <li><p>联系电话：<span>187xxxxxxxx</span></p></li>
     <li><p>所寄物品照片：<span><img style="width:35px;" src="lib/imgs/captcha.png"></span><span><img style="width:35px;" src="lib/imgs/captcha.png"></span></p></li>
     <li><p>位置：<span>河北省xxxxx</span></p></li>
    </ul>
   </div>
 </div>
    <div class="main_in" style="">
   <p style="text-align: center;line-height: 35px;font-size:150%;">快递公司：<span>韵达快递</span></p>
   <div style="width:20%;display: inline-block;float: left;">
   <img style="width:60px;height:60px;border-radius:6px;margin:0 5%;" src="lib/imgs/zhengjian.png">
   </div>
   <div style="width:78%;height:auto;display: inline-block;">
    <ul style="line-height: 25px;font-size:80%;">
     <li><p>快递单号：<span>xxxxxxxxx</span></p></li>
     <li><p>寄件人姓名：<span>xxx</span></p></li>
     <li><p>寄件人地址：<span>河北省xxxxx</span></p></li>
     <li><p>联系电话：<span>187xxxxxxxx</span></p></li>
     <li><p>收件人姓名：<span>xxx</span></p></li>
     <li><p>收件人地址：<span>河北省xxxxx</span></p></li>
     <li><p>联系电话：<span>187xxxxxxxx</span></p></li>
     <li><p>所寄物品照片：<span><img style="width:35px;" src="lib/imgs/captcha.png"></span><span><img style="width:35px;" src="lib/imgs/captcha.png"></span></p></li>
     <li><p>位置：<span>河北省xxxxx</span></p></li>
    </ul>
   </div>
 </div>
</div>
<!-- 分页 -->
  <div id="footer" style="width:100%;margin:0 auto;text-align: center;position: fixed;bottom:50px;"></div>  
   <p style="height:50px;"></p>
   
</body>
</html>