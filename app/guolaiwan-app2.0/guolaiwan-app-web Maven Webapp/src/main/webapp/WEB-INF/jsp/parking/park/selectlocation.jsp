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
* {
	margin: 0px;
	padding: 0px;
	font-size: 1rem;
}

.header {
	width: 100%;
	margin: 0 auto;
	/* background-color: orange; */
}

.header_in {
	/* background-color: orange; */
}

.main {
	position: relative;
	top: 20px;
}

.main_on {
	position: absolute;
	height: 22px;
	top: 30%;
	left: 40%;
	overflow: hidden;
}

.main_in {
	position: absolute;
	height: 22px;
	top: 30%;
	left: 15%;
	overflow: hidden;
}

.main_cn {
	position: absolute;
	height: 22px;
	top: 30%;
	left: 65%;
	overflow: hidden;
}

.main_aa, .main_cc, .main_bb {
	font-size: 0.6rem;
	position: relative;
	left: 1px;
	top: -50%;
	overflow: hidden;
}

#container {
	width: 100%;
	height: 430PX;
	margin-bottom: 2px;
	margin-top: 2px;
}

.amap-mcode {
	display: none;
}

.chewei {
	background-color: #919191;
	width: 8%;
	height: 15%;
}

.car {
	background-color: #06B02B;
	width: 8%;
	height: 15%;
}

.red {
	background-color: red;
	width: 8%;
	height: 15%;
}
#btn{
position: fixed;
top:90%;
}


html, body {
	height: 100%;
	background-color: #FFFCED;
}
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.13&key=ff4672efcfc6279cbe3b2815dd70a1ec"></script>
<script
	src="https://webapi.amap.com/maps?v=1.4.13&key=ff4672efcfc6279cbe3b2815dd70a1ec"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
 	
 
 	$(function() {
        var tak = null;
        var stall = null;
 	    var qu =null;	
        var char = null;
        var id = null;
 		var _uri = window.BASEPATH + 'vice/Information';
 		var params = {};
		params.uid = ${param.useid};
		 $.post(_uri, $.toJSON(params), function(data) {
			data = parseAjaxResult(data);
		   	var htm = [];
			for (var i = 0; i < data.length; i++) {
				htm.push('<option class="value" value="'+data[i]+'">'+data[i]+'</option>');
			    tak = data[0];
				}
				$('.lou').append(htm.join('')); 
			
		var _ur = window.BASEPATH + 'vice/tier';
 		var param = {};
		param.uid = ${param.useid};
		param.input = tak;
		 $.post(_ur, $.toJSON(param), function(data) {
			data = parseAjaxResult(data);
		   	var htm = [];
			for (var i = 0; i < data.length; i++) {
				htm.push('	<option class="opt" style="text-align:center;">'+data[i]+'</option>');
				qu = data[0];
				}
				$('.qu').append(htm.join(''));  
						
		 // 根据  层数 查询车位图
		var _utli = window.BASEPATH + 'vice/Informap';
		 var parm = {};
		 parm.uid = ${param.useid};
		 parm.ceng =  tak;
		 parm.qu = qu;
		 $.post(_utli,$.toJSON(parm), function(data) {
			data = parseAjaxResult(data);
			id = data.id;
		   	var htm = []; 
	       	htm.push(''+data.long+'');
		   $('.banner').append(htm.join(''));  
		   
		   
		// 更改车位 颜色
		 var  _take = window.BASEPATH + 'vice/truck';
		 var  parame= {};
		 parame.uid = id;
		 $.post(_take,$.toJSON(parame), function(data) {
			data = parseAjaxResult(data);
		  for (var i = 0; i < data.length; i++) {
			if(data[i].positionInformation == char){
			 $('#'+data[i].positionNumber+'').removeClass('chewei').addClass('car');
			}
			if(data[i].useCondition == "1"){
			 $("#"+data[i].positionNumber+'').removeClass('car').addClass('red');
			} 	
		   } 
		}); 
		});
		});
		    $(document).on('click', '.car', function() {
		      $("#"+lu+"").css({
			  "background-color":"#06B02B",
			  });
			    stall =  $(this).attr("id");
			   lu = stall;
			  $("#"+stall+"").css({
			  "background-color":"#18b4ed",
			  });
		  }); 
	 	}); 
	
		 var _ur = window.BASEPATH + 'quit/query';
		 $.post(_ur, null, function(data) {
			data = parseAjaxResult(data);
			char = data.userNickname;
		   	var htm = [];
		    htm.push('<span style="font-size: 0.7rem;">'+data.userHeadimg+'车主您好，已经为您筛选出'+data.userNickname+'车位，请直接点击车位预约</span>');
			$('#car').append(htm.join(''));  
		});
	
	
		 var _ur = window.BASEPATH + 'vice/hours';
		 var par = {};
		 par.id = ${param.useid};
		 $.post(_ur,$.toJSON(par), function(data) {
			data = parseAjaxResult(data);
		   	var htm = []; 
	       	htm.push('<p  style="text-align:center;font-size: 0.3rem;">停车场时间：<span id ="time" style="font-size: 0.3rem;">'+data.stoppingTime+'</span></p>');
	       	htm.push('<p style="text-align:center;font-size: 0.3rem;">热线服务：<span style="font-size: 0.3rem;">'+data.phone+'</span></p>');
		   $('.footer_in').append(htm.join(''));  
		});


      $(document).on('change', '.lou', function() {
        var _ur = window.BASEPATH + 'vice/tier';
 		var param = {};
		param.uid = ${param.useid};
		param.input = $(".lou").val();
		 $.post(_ur, $.toJSON(param), function(data) {
		 $(".qu").find("option").remove();
			data = parseAjaxResult(data);
		   	var htm = [];
			for (var i = 0; i < data.length; i++) {
				htm.push('<option  style="text-align:center;">'+data[i]+'</option>');
				}
				$('.qu').append(htm.join(''));  
		});
     }); 
     
     
     
    var lu =null;
	var carid = null;
    $(document).on('change', '.lou', function() {
    lu = null;
    $(".banner").empty();
	// 根据  层数 查询车位图
	var _utli = window.BASEPATH + 'vice/Informap';
		 var parm = {};
		 parm.uid = ${param.useid};
		 parm.ceng =  $(".lou").val();
		 parm.qu =  "A区";
		 $.post(_utli,$.toJSON(parm), function(data) {
			data = parseAjaxResult(data);
			carid = data.id;
		   	var htm = []; 
	       	htm.push(''+data.long+'');
		   $('.banner').append(htm.join(''));  
		   test();
	    
		});
     }); 
     
    var lu =null;
	var carid = null;
    $(document).on('change', '#qu1', function() {
    lu = null;
    $(".banner").empty();
	// 根据  层数 查询车位图
	var _utli = window.BASEPATH + 'vice/Informap';
		 var parm = {};
		 parm.uid = ${param.useid};
		 parm.ceng =  $(".lou").val();
		 parm.qu =   $("#qu1").val();
		 $.post(_utli,$.toJSON(parm), function(data) {
			data = parseAjaxResult(data);
			carid = data.id;
		   	var htm = []; 
	       	htm.push(''+data.long+'');
		   $('.banner').append(htm.join(''));  
		   test();
	    
		});
		
     }); 
 	 
 	 
 	 
 	 
 	  function test(){
 	  	   
		// 更改车位 颜色
		 var  _take = window.BASEPATH + 'vice/truck';
		 var  parame= {};
		 parame.uid = carid;
		 $.post(_take,$.toJSON(parame), function(data) {
			data = parseAjaxResult(data);
			 for (var i = 0; i < data.length; i++) {
			if(data[i].positionInformation == char){
			 $('#'+data[i].positionNumber+'').removeClass('chewei').addClass('car');
			}
			if(data[i].useCondition == "1"){
			 $("#"+data[i].positionNumber+'').removeClass('car').addClass('red');
			} 	
		   } 
		}); 
 	  
     // 更改车位  点击 颜色
		    $(document).on('click', '.car', function() {
		      $("#"+lu+"").css({
			  "background-color":"#06B02B",
			  });
			   stall =  $(this).attr("id");
			   lu = stall;
			  $("#"+stall+"").css({
			  "background-color":"#18b4ed",
			  });
		  }); 
 }
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 
 	 $(document).on('click', '.btn', function() {
 	if(stall != null){
 	    var _ur = window.BASEPATH + 'vice/save';
 		var param = {};
		param.uid = ${param.useid};
		param.ceng = $(".lou").val();
		param.qu = $(".qu").val();
		// 区
		param.time = $("#time").val();
		param.stall = stall;
		 $.post(_ur, $.toJSON(param), function(data) {
		 data = parseAjaxResult(data);
		      window.location.href=window.BASEPATH +"pubnum/product/index/merchant/payment?uid="+${param.useid}+"&orderid="+data.uid;
		      	$(".btn").css("box-shadow","5px 5px 10px #8E8F8F");
		 });
	} else{
	alert("请选择车位!");
	}
 	});
});
       
     
         
       
       

 
 </script>
<body>
	<div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="quit/merchant/smartparking" title="返回"> <返回首页 </a>
	  <span style="color:#ffffff;line-height:40px;font-size:18px;margin-left:-72px;">选车位</span>
	</div> 
	<div class="header">
		<div class="header_in"
			style="width:100%; margin: 0 auto;text-align: center;background:#DDDDDD;">
			<span style="text-align: center;color: black;">楼层：</span> <select
				class="lou" id="test"
				style="touch-action: none;height:40px;text-align: center; line-height:30%;width:auto;border: 0;outline: none;text-align-last: center;">

			</select> <span style="margin: 0 10%;color: black; overflow:hidden;">|</span>
			<span style="text-align: center;color: black;overflow:hidden; ">区域：</span>
			<select class="qu" id="qu1"
				style="touch-action: none;height:40px;text-align: center; line-height: 30px;width:auto;border: 0;outline: none;text-align-last: center;">

			</select>
		</div>

	</div>
	<div class="main" style="width: 100%;height: 60px;">
		<div class="main_in">
			<div class="main_a"
				style="height:20px;width:20px; background-color: #06B02B;float: left;overflow:hidden;border-radius:2px ;"></div>
			<span class="main_aa">可预订</span>
		</div>
		<div class="main_on">
			<div class="main_b"
				style="height:20px;width:20px; background-color: red;float: left;overflow:hidden;border-radius:2px ;"></div>
			<span class="main_bb">已预订</span>
		</div>
		<div class="main_cn">
			<div class="main_c"
				style="height:20px;width:20px; background-color: #919191;float: left;overflow:hidden;border-radius:2px ;"></div>
			<span class="main_cc">不可预订</span>
		</div>
	</div>
	<div class="banner" id="banner"
		style="width: 99%;height:45%;margin: 15px auto 50px;overflow:hidden;position: relative; border:1px #F39801 dashed;">


	</div>
	<div class="footer">
		<p id="car"
			style="margin: 0 15px;font-size: 0.7rem;text-align: center;font-weight: bold;"></p>
		<div class="footer_in" style="margin: 20px auto;"></div>

	</div>
	<div style="width:100%;height:50px;"></div>
	<div id="btn" style="margin: 0 auto;text-align: center;width:100%;">
		<button class="btn"
			style="background-color: #F39801;color: white;width: 80%;height: 50px;border-radius: 5px;border: none;outline: none;font-size: 0.9rem;">立即预定</button>
	</div>


</body>

</html>