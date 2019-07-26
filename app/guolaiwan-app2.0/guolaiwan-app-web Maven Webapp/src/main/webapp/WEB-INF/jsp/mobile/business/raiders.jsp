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
<title>攻略</title>
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
	background:#E1E1E1 !important; 
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
.gotop {
   position: fixed;
   right: 20px;
   bottom: 50px;
   display: block;
   width: 50px;
   height: 50px;
   opacity: 0.8;
   z-index:111111;
	}
.gonglue ul{
 float:right;
 height:50px;
 line-height: 50px;

} 
.gonglue ul li{
 float:left;
 margin:0 5px;

}
.dianzan:hover{
@keyframes anim{  
	 0%{ 
	 transform:scale(1); 
	} 
	 50%{ 
	 transform:scale(1.5); 
	}   
	100%{ 
	 transform:scale(1); 
	}   
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
  /*返回顶部  */
  $(function(){
  getVideoPics();
	$(window).scroll(function(){
		if($(window).scrollTop()>100){
			$(".gotop").fadeIn(400);	
		}
		else{
			$(".gotop").fadeOut(400);
		}
	});
	$(".gotop").click(function(event){
        event.preventDefault();
		$('html,body').animate({'scrollTop':0},500);
        return false;
	});
	});
	
	// 南山攻略需要的数据
  function getVideoPics(){       
	var url = window.BASEPATH + 'business/getVideoPics?merchantId=${merchantId}';
	$.get(url, null, function(data){
	    var html=[];
	   	for(var i=0;i<data.length;i++){
	   		  var time=parseInt(((new Date().getTime())-(new Date(data[i].updatetime).getTime()))/(1000*60*60*24));
	   	      html.push('<a href="javascript:void(0);" onclick="gotoraidersdetails('+data[i].id+')"><div class="main" style="width:100%;height:auto;background:#fff;padding-top:10px;border-radius:10px;margin:5px 0;">');
		      html.push('<div class="gonglue" style="width:96%;height:auto;margin:0 auto;overflow: hidden;">');
		      html.push('<img style="width:100%;height:200px" src="'+data[i].textimg+'">');
		      html.push('<p style="font-size:18px;font-weight:bold;margin:10px auto; ">"'+data[i].textname+'"</p>');
		      html.push('<p style="font-size:16px;margin:10px 0;overflow : hidden;text-overflow: ellipsis;white-space:nowrap;width:250px;">'+data[i].frist+'</p>');
		      html.push('</a>');
		      html.push('<ul>');
	          html.push('<li><p>'+time+'天前</p></li>');
	         /*  html.push('<li><p><img  style="width:20px;height:20px;" src="lib/images/xiaoxis.png"/>(<span>'+data[i].pcomment+'</span>)</p></li>'); */
	         if(data[i].picture == 1 ){
	         html.push('<li><p onclick="xiaoxisChlick('+data[i].id+')"><img class="dianzan" style="width:20px;height:20px;z-index:11111;animation:anim 2s linear 0.5s; " src="lib/images/dianzanss.png"/>(<span>'+data[i].videoPic+'</span>)</p></li>');
	         }else{
	         html.push('<li><p onclick="xiaoxisChlick('+data[i].id+')"><img class="dianzan" style="width:20px;height:20px;z-index:11111;animation:anim 2s linear 0.5s; " src="lib/images/huixin.png"/>(<span>'+data[i].videoPic+'</span>)</p></li>');
	         }
	         /*  html.push('<li><p><img style="width:25px;height:25px;" src="lib/images/zhuanfas.png"/>(<span>111</span>)</p></li>'); */
		      html.push('</ul>');
		      html.push('</div>');
		   	  html.push('</div></a>');
		   }		
	     $('.gl').append(html.join(''));
	    })
	} 
	
	function gotoraidersdetails(id){
        location.href=window.BASEPATH + 'business/gotoraidersdetails?id='+id;
   }
	function xiaoxisChlick(id){ //增加点赞
	  var userId="<%=session.getAttribute("userId")%>"; 
      var url = window.BASEPATH + 'newPhoneController/vpPraise.do';
      var params = {};
	  params.vpId = id;
	  params.userId = userId;
      $.post(url, $.toJSON(params), function(data){
	      data = parseAjaxResult(data);
	      alert(data.msg);
	      $('.gl').empty();
	      getVideoPics();
	 });  
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
	   
	   <!-- <div class="main" style="width:100%;height:auto;background:#fff;padding-top:10px;border-radius:10px;margin:5px 0;">
	     <div class="gonglue" style="width:96%;height:auto;margin:0 auto;overflow: hidden;">
	       <img style="width:100%;height:200px" src="lib/images/1.jpg">
	       <p style="font-size:18px;font-weight:bold;margin:10px auto; ">南山长乐谷</p>
	       <p style="font-size:16px;margin:10px 0;overflow : hidden;text-overflow: ellipsis;white-space:nowrap;width:250px;">南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷南山长乐谷</p>
	       <ul>
	        <li><p>1天前</p></li>
	        <li><p><img style="width:20px;height:20px;" src="lib/images/xiaoxis.png"/>(<span>111</span>)</p></li>
	        <li><p><img style="width:20px;height:20px;" src="lib/images/dianzanss.png"/>(<span>111</span>)</p></li>
	        <li><p><img style="width:25px;height:25px;" src="lib/images/zhuanfas.png"/>(<span>111</span>)</p></li>
	       </ul>
	     </div>
	   </div> -->
	   <div class="gl"></div>
	    

	    <!-- 置顶 -->
    <div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
</body>






</html>