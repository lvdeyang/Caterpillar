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
<title>住宿-选房</title>
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
	height:auto;
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

.nav p{
margin:0 1%;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>

<script type="text/javascript">
          
  $(function() {
  	roomlist(1,"");
	});
	
	
	
	function roomlist(tier,identity){
		$('.main').children().remove();
	 var url=window.BASEPATH + 'business/getallroom'; 
		$.post(url,{"merchantId":${merchantId},"tier":tier,"identity":identity},function(data){
				var html= [];
				for(var i =0;i<data.length;i++){
					html.push('<div onclick="gotoroomdetails(this.id)" id="'+data[i].id+'" style="background: #fff;width:30%;height: 0;padding-bottom: 30%;border-radius:50%;position: relative;margin:5px 5px;overflow: hidden;display: inline-block;">');
					if(data[i].state=="1"){
						html.push('<img style="width:40%;height:40%;position: absolute;left:50%;margin-left:-20%;" src="lib/images/weixuan.png">');
					}else{
						html.push('<img style="width:40%;height:40%;position: absolute;left:50%;margin-left:-20%;" src="lib/images/xuanzhong.png">');
					}
					html.push('<p style="width:80%;text-align: center;position: absolute;left:50%;top:45%;margin:0 0 0 -40%;"><span>'+data[i].name+'</span>   <span>'+data[i].identity+'</span></p>');
					html.push('<p style="width:80%;text-align: center;position: absolute;left:50%;top:68%;margin:0 0 0 -40%;color:#FB9361;"><span>￥'+(data[i].price/100)+'</span></p>');    
				    html.push('</div>');   
				}
				$('.main').append(html.join(''));
		})
	}
	
	function changetier(){
		var tier=$("#tier").val();
		var identity=$("#identity").val();
		alert(tier+""+identity)
		roomlist(tier,identity);
	}
	
	function changeidentity(){
		var tier=$("#tier").val();
		var identity=$("#identity").val();
		alert(tier+""+identity)
		roomlist(tier,identity);
	}
	
	function gotoroomdetails(id){
	    location.href=window.BASEPATH + 'business/gotoroomdetails?roomId='+id;
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
       
         <div class="nav" style="background:#fff;height:50px;line-height:50px;text-align: center;font-size:12px;width:100%;font-weight:bold;border-top:2px solid #CECACB;border-bottom:2px solid #CECACB;">
	       <p style="display: inline-block;"><span style="color:#A4A2A0;">灰色</span>空闲</p>
	       <p style="display: inline-block;"><span style="color:#D13035;">红色</span>已预定</p>
	       <span>楼层：</span>
	       <select class="tier" id="tier" onchange="changetier()"  style="touch-action: none;width:auto;height:30px;padding: 0 1%;border:none;outline:none;text-align: center;margin: 0; text-align-last: center;">
		       <option value="1">1层</option>
		       <option value="2">2层</option>
		       <option value="3">3层</option>
		       <option value="4">4层</option>
		       <option value="5">5层</option>
		       <option value="6">6层</option>
		       <option value="7">7层</option>
		       <option value="8">8层</option>
	       </select> 
	       <span>房型：</span>
	       <select class="identity" id="identity" onchange="changeidentity()" style="touch-action: none;width:auto;height:30px;padding: 0 1%;border:none;outline:none;text-align: center;margin: 0; text-align-last: center;">
		       <option value="标准间">标准间</option>
		       <option value="豪华间">豪华间</option>
		       <option value="三人间">三人间</option>
		       <option value="五人间">五人间</option>
	      </select> 
	      </div>
	      
	      <!-- 房间选择 -->
	      <div class="main" style="width:100%;height:auto;padding:0 5%;">
	       
	        
	      </div>
</body>





</html>