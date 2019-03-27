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
 position: relative;
   height:100%;
   background: -webkit-radial-gradient(#92A2ED,#536CE3); /* Safari 5.1 - 6.0 */
    background: -o-radial-gradient(#92A2ED,#536CE3); /* Opera 11.6 - 12.0 */
    background: -moz-radial-gradient(#92A2ED,#536CE3); /* Firefox 3.6 - 15 */
    background: radial-gradient(#92A2ED,#536CE3); /* 标准的语法（必须放在最后） */
   } 
  .head{
  position: relative;
 top:10%;
  }
  .header{
width:230px;
height:230px;
margin:0 auto 10%;
border:10px solid #78B6FF;
border-top-color: #4F68CD;
border-left-color: #4F68CD;
border-radius:50%;
-webkit-animation:circle 1s infinite linear;/*匀速 循环*/
background-color: #ffffff;
}

 


@-webkit-keyframes circle{

0%{ transform:rotate(0deg); }

100%{ transform:rotate(-360deg); }

}
  .header_in{
width:210px;
height:210px;
margin:0 auto;
border:10px solid #5C7EE2;
border-radius:50%;
background-color: #ffffff;
}

#time-item{
font-size:30px;
color:#4B9DF6;
position: absolute;
top:35%;
left:50%;
margin-left: -81.8px;


}
.main_in p{

color:#ffffff;
margin:0 8%;
font-size:0.6rem;
}
.footer{
position:fixed;
 top:82%;
}


</style>
  </head>
    <!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
 <script>
    $(function() {
    var  time = null;
     var _util = window.BASEPATH + 'vice/seleparking';
		 $.post(_util, null, function(data) {
			data = parseAjaxResult(data);
			window.name= data.time;
			
	   var mis = Math.abs(window.name);
    	window.setInterval(function(){
    		var day=0,
        	hour=0,
        	minute=0,
        	second=0;//时间默认值        
    		if(window.name > 0){
        		hour = Math.floor(window.name / (60 * 60));
        		minute = Math.floor(window.name / 60) - (hour * 60);
        		second = Math.floor(window.name) - (hour * 60 * 60) - (minute * 60);
    		if (hour <= 9) hour = '0' + hour; 
    		if (minute <= 9) minute = '0' + minute;
    		if (second <= 9) second = '0' + second;
    		$('#hour_show').html('<s id="h"></s>'+hour+' :');
    		$('#minute_show').html('<s></s>'+minute+' :');
    		$('#second_show').html('<s></s>'+second);
    		window.name--;
    		}
    		if(window.name < 0){
        	hour = Math.floor(mis / (60 * 60));
        	minute = Math.floor(mis / 60) - (hour * 60);
        	second = Math.floor(mis) - (hour * 60 * 60) - (minute * 60);
    		if (hour <= 9) hour = '0' + hour; 
    		if (minute <= 9) minute = '0' + minute;
    		if (second <= 9) second = '0' + second;
    		$('#hour_show').html('<s id="h"></s>'+hour+' :');
    		$('#minute_show').html('<s></s>'+minute+' :');
    		$('#second_show').html('<s></s>'+second);
    		mis++;
    		}
    	}, 1000);   
  		

         
	 var _util = window.BASEPATH + 'vice/isparking';
		 $.post(_util, null, function(data) {
			data = parseAjaxResult(data);
			var name = [];
		   	name.push(''+data.name+''); 
			$('#position').append(name.join(''));
			var number = [];
		   	number.push(' <p style="font-size:1.2rem;font-weight: bold;">'+data.number+'</p>'); 
			$('#man').append(number.join(''));
		    var obj = document.getElementById("money");
            obj.innerText= data.cost; 
			if(data.over>0){
			 var obj = document.getElementById("cost");
              obj.innerText= data.over; 
			}
		  });	 

		});
		
   
/* $(function(){
	timer(intDiff);	
}); */
    /*   timer(intDiff); */
$(document).on('click','#btn', function() {
     	window.location.href="pubnum/product/index/merchant/renewall";
	}); 
});
</script>

  <body>
  	<div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="quit/merchant/smartparking" title="返回"> <返回首页 </a>
	  <span style="color:#ffffff;line-height:40px;font-size:18px;margin-left:-72px;">正在停车</span>
	</div> 
  <div class="head">
   <div class="header"><div class="header_in"></div></div>
   <p id="position" style="position: absolute;top:20%;left:50%;color:#6AADF7;margin-left: -38px;font-size:18px;"></p>
   
   
   <div id="time-item">
    <!-- <span id="day_show">0天</span> -->
    <strong id="hour_show"></strong>
    <strong id="minute_show"></strong>
    <strong id="second_show"></strong>
</div>



  </div>
  <div class="main" style="text-align: center;margin:40% auto 0;width:100%;">
  <div  id= "man" class="main_in" style="display: inline-block;width:30%;">
     <p >车位号</p>
    
  </div>
  <div class="main_in" style="display: inline-block;width:30%;">
     <p>停车费</p>
     <p  id="money" style="font-size:1.2rem;font-weight: bold;"></p>
  </div> 
   <div  class="main_in" style="display: inline-block;width:30%;">
     <p style="color:#F2DA00;">超时停车费</p>
     <p  id="cost" class="cost" style="color:#F2DA00;font-size:1.2rem;font-weight: bold;">0</p>
  </div>
  </div>
    <div class="footer" style="text-align: center;width:100%;height:15%;">
    <p style=" color:#ffffff;font-size:0.6rem;margin:0 auto 10%;">温馨提示：以预订时间开始，以离停车场时间结束。</p>
     <button id="btn" style="font-size:0.8rem;width:90%;height:50%;font-weight: bold;background-color:#FFFFFF ;border-radius:5px;border:none;outline:none;border:1px solid #D5D5D5;color:#6AADF7;">续费</button>
    </div>
  </body>
</html>
