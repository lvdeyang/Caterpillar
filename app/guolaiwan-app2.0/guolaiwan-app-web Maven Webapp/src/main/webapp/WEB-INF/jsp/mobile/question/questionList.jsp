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
<title>常见问题列表</title>
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
	font-family: "微软雅黑" !important;
	height:auto;
	background:#fff !important; 
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	
}
*{
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;	
}
.main li{
width:100%;
height:40px;
line-height: 40px;
border-bottom:1.5px solid #CFCFCF;
}
.main li p{
padding:0 5px;
display: inline-block;
width:80%;
overflow: hidden;
text-overflow: ellipsis;
white-space: nowrap;
}
.main li span{
float:right;
font-weight: bold;
margin-right:5px;
}
.layui-laypage-next em, .layui-laypage-prev em{
  font-size:12px !important;
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
 padding:0 10px !important;
 border-radius:4px !important;
 border:none !important;
 margin:0 2px !important;
 height:23px !important;
 line-height: 23px !important;
}
.layui-laypage .layui-laypage-curr .layui-laypage-em{
background: #4BB259 !important;
height:20px !important;
width:20px !important;
border-radius:50% !important;
text-align: center !important;
top:4px !important;
left:-3px !important;
}
.layui-laypage-last,.layui-laypage-next,.layui-laypage-prev,.layui-laypage-first{
color:#fff !important;
background: #4BB259 !important;
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
<script>       	
		layui.use(['laypage', 'layer'], function(){
		  var laypage = layui.laypage
		  ,layer = layui.layer;
		   window.BASEPATH = '<%=basePath%>';
		  laypage.render({
		    elem: 'footer'
		    ,count: ${count}
		    ,groups:3
		    ,first: '首页'
		    ,last: '尾页'
		    ,prev: '<em>上一页</em>'
		    ,next: '<em>下一页</em>'
		     ,jump: function(obj, first){
		    //obj包含了当前分页的所有参数，比如：
		    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
		    console.log(obj.limit); //得到每页显示的条数
		    getModularClassInfo(obj.curr,obj.limit);
		    //首次不执行
		    if(!first){
		       layer.msg('第'+ obj.curr +'页', {offset: 'b'});
		    }
		  }
		  }); 
		  //分页查询问题列表
	       function getModularClassInfo(page,limit){
	         var url =  window.BASEPATH + 'questionbase/getProblemMessage?page='+page+'&limit='+limit+'&id=${classModularId}';
	         $.get(url,null,function(msg){
	           var msgs = msg.cProblemPOs;
	           if(msgs.length == 0)return;
	            var html=[];
		        for(var i=0;i<msgs.length;i++){
			    html.push('<li id="'+msgs[i].id+'" onclick="getProblemPage(this.id)"><p>问题'+(i+1)+'</p><span>'+msgs[i].problemName+'</span></li>');		 
		         }
		         $('.main').children().remove();
		         $('.main').append(html.join(''));	         
	         })
	       } 		  		 		  
		  });
		  //跳转问题详情页
		  function getProblemPage(id){		  
		   location.href = window.BASEPATH +'questionbase/nextComProMessage?id='+id;
		  }
		  			  
</script>
<body>
   <div style=" text-align: center"><h4 style=>${className}</h4></div>
 <div style="width:100%;height:auto;padding:0 4%;">
  <ul class="main" style="width:100%;">
  </ul>
 </div>
 <!-- 分页 -->
  <div id="footer" style="width:100%;margin:0 auto;text-align: center;position: fixed;bottom:0;"></div>  
  <div style="height:55px;"></div>
</body>

</html>