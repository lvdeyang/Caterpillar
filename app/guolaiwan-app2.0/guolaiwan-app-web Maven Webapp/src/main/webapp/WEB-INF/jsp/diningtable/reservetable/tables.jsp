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
<title>美食-订桌</title>
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
.nav span{
margin:0 1%;
}
.pid option{
text-align: center;
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
		list();

	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//执行一个laydate实例
		laydate.render({
			elem : '#test2', //指定元素
			change : function(value, date) { //监听日期被切换
			},
			done : function() {
			  if ($(".texts").val() != "请选择" && $(".search").val() =="") {
			    list();
	          }else if($(".texts").val() != "请选择" && $(".search").val() !=""){
	            search();
	          }else{
	           $.toptip('请选择就餐时间', 'error');
	          }
			}
		});
	//时间选择器
	});

	$('.texts').on('change', function() { //就参时间
		if ($("#test2").val() != "" && $(".search").val() == "") {
			list();
		} else if ($("#test2").val() != "" && $(".search").val() != "") {//搜索时方法
            search();
		} else {
			$.toptip('请选择预订时间', 'error');
		}
	});
	$('.pid').on('change', function() { //层数
		list();
	});

	$('.pic').on('click', function() { //搜索隐藏页面
		$(".nav").hide();
		$(".serve").hide();
		$(".pid").hide();
		$(".pid_in").hide();
		$(".nav_in").append("<button class='cancel' style='margin-left:7%;border:none;outline:none;background:#fff;'>取消搜索</button>")
		if( $(".search").val() != ""){ //搜索为空 不查询
		 search();
		}else{
		    $('.hall').empty();
			$('.Room-left').empty();
			$('.Room-right').empty();
		}
		
		$('.cancel').on('click', function() {  
		$(".search").val("")
		list();
		$(".cancel").hide();
		$(".nav").show();
		$(".serve").show();
		$(".pid").show();
		$(".pid_in").show();
	    });
	});
	
	function search() {
			var _uri = window.BASEPATH + 'reservetable/search.do'; //
			var patam = {};
			patam.merchantId = 364 ; //'${merchantId}'
			patam.search = $(".search").val();
			patam.tableDate = $("#test2").val(); //时间
			if ($(".texts").val() != "请选择") {
				patam.type = $(".texts").val(); //午 晚
			} else {
				patam.type = "";
			}
			$.post(_uri, $.toJSON(patam), function(data) {
				$('.hall').empty();
				$('.Room-left').empty();
				$('.Room-right').empty();
				var sole = data.sole; //判断此层是否只有包间 或 桌
				data = data.merchant;
				boole = 0;
				for (var i = 0; i < data.length; i++) {
					if (sole == 0) { //有桌子有包间
						add(data[i], boole);
						$(".hall").css("width", "58%");
						$(".Room-left").css("width", "20%");
						$(".Room-right").css("width", "20%");
						$(".hous").css({
							"width" : "99%",
							"margin-left" : "1%"
						});
						$(".houss").css({
							"width" : "99%",
							"margin-right" : "1%"
						});
					}
					if (sole == 1) { //只有桌子
						add(data[i]);
						$(".hall").css("width", "100%");
						$(".Room-left").css("width", "0%");
						$(".Room-right").css("width", "0%");
						$(".table").css({
							"width" : "17%",
							"padding-bottom" : "17%"
						});
					}
					if (sole == 2) { //只有房
						add(data[i], 0);
						$(".hall").css("width", "0");
						$(".Room-left").css("width", "48%");
						$(".Room-right").css("width", "48%");
						$(".hous").css({
							"width" : "48%",
							"margin-left" : "1%"
						});
						$(".houss").css({
							"width" : "48%",
							"margin-right" : "1%"
						});
					}
				}
			});
	}
	
	
	
	
	/* 日期判断 */
 	$(document).on('click', '.pic', function() {
	var sDate = new Date(document.getElementById("test2").value.replace(/-T/g, "//"));
	var eDate = new Date();
    var sewData = Number(sDate)+57599999;
    var newData = Number(eDate);
			if (sewData < newData) {
				$.toast("请选择正确就餐日期", "forbidden");
				return false;
			} 
	});

		function list() {
			$('.hall').empty();
			$('.Room-left').empty();
			$('.Room-right').empty();
			var _uri = window.BASEPATH + 'reservetable/getlist.do'; //
			var patam = {};
			patam.merchantId = 364 ; //'${merchantId}'
			patam.tableDate = $("#test2").val(); //时间
			if ($(".texts").val() != "请选择") {
				patam.type = $(".texts").val(); //午 晚
			} else {
				patam.type = "";
			}
			patam.tier = $(".pid").val(); //层
			$.post(_uri, $.toJSON(patam), function(data) {
				var sole = data.sole; //判断此层是否只有包间 或 桌
				data = data.merchant;
			    boole = 0;
				for (var i = 0; i < data.length; i++) {
					if (sole == 0) { //有桌子有包间
						   add(data[i],boole);
						   $(".hall").css("width","58%");
						   $(".Room-left").css("width","20%");
				           $(".Room-right").css("width","20%");
				           $(".hous").css({"width":"99%","margin-left":"1%"});
				           $(".houss").css({"width":"99%","margin-right":"1%"});
					} if (sole == 1){  //只有桌子
					       add(data[i]);
						   $(".hall").css("width","100%");
						   $(".Room-left").css("width","0%");
				           $(".Room-right").css("width","0%");
					       $(".table").css({"width":"17%","padding-bottom":"17%"});
					} if (sole == 2){ //只有房
						   add(data[i],0);
						   $(".hall").css("width","0");
					       $(".Room-left").css("width","48%");
				           $(".Room-right").css("width","48%");
				           $(".hous").css({"width":"48%","margin-left":"1%"});
				           $(".houss").css({"width":"48%","margin-right":"1%"});
					}
				}
			});
		}
     /*   	var title = '';
       	$(".ischange").change(function(){ 
        	$("[name='key']:checked").each(function(){
        	          if($(".ischange").prop("checked")==true){
                      title += $(this).val()+',';
                     }else{
                       alert("quxiao")
                       title += $(this).val()+',';
                     }
                    });
                     alert(title); 
           });      */
             
                    
	
		function add(data,boole) { //添加信息
			if (data.room == "0") { //普通桌
				var html = [];
				if (data.tableState == 0) {
					html.push('<div  class="table" style="text-align:center;background: #BFBFBF;width:28%;height: 0;padding-bottom: 28%;border-radius:50%;margin:5px 5px;overflow: hidden;display: inline-block;line-height:30px;">');
				}
				if (data.tableState == 1) {
					html.push('<div class="table" style="text-align:center;background: #7EBE34;width:28%;height: 0;padding-bottom: 28%;border-radius:50%;margin:5px 5px;overflow: hidden;display: inline-block;line-height:30px;">');
				}
				if (data.tableState == 2) {
					html.push('<div class="table" style="text-align:center;background: #D13035;width:28%;height: 0;padding-bottom: 28%;border-radius:50%;margin:5px 5px;overflow: hidden;display: inline-block;line-height:30px;">');
				}
				html.push('<p><span>' + data.tableNo + '</span></p>');
				html.push('<p><span>' + data.size + '</span></p>');
				html.push('</div>');
				$('.hall').append(html.join(''));
			} else { //包间
				if (boole == 0) {
					var html = [];
					html.push('<div class="hous" style="text-align:center;width:100%;height:auto;display: inline-block;border:1px solid #E0E0E0;border-radius:8px;padding:5px 5px;margin:2px 0;">');
					if (data.tableState == 0) html.push('<img style="width:90%;" src="lib/images/room.png">')
					if (data.tableState == 1) html.push('<img style="width:90%;" src="lib/images/luse.png">')
					if (data.tableState == 2) html.push('<img style="width:90%;" src="lib/images/xuanzhong.png">')
					html.push('<p>' + data.tablename + '</p>');
					html.push('<p><span>' + data.size + '人间</span></p>');
					if (data.sofa == 1) { //沙发
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lusofa.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/sofa.png">');
					if (data.television == 1) { //电视
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lutelevision.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/television.png">');
					if (data.airConditioner == 1) { //空调
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/luairConditioner.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/airConditioner.png">');
					if (data.wifi == 1) { //无线
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/luwifi.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/wifi.png">');
					if (data.lavatory == 1) { //卫生间
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lulavatory.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lavatory.png">');
					if (data.karaoke == 1) { //唱歌
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lukaraoke.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/karaoke.png">');
					html.push('</div>');
					$('.Room-left').append(html.join(''));
					window.boole = 1;
				} else {
					var html = [];
					html.push('<div class="houss" style="text-align:center;width:100%;height:auto;display: inline-block;border:1px solid #E0E0E0;border-radius:8px;padding:5px 5px;margin:2px 0;">');
					if (data.tableState == 0) html.push('<img style="width:90%;" src="lib/images/room.png">')
					if (data.tableState == 1) html.push('<img style="width:90%;" src="lib/images/luse.png">')
					if (data.tableState == 2) html.push('<img style="width:90%;" src="lib/images/xuanzhong.png">')
					html.push('<p>' + data.tablename + '</p>');
					html.push('<p><span>' + data.size + '人间</span></p>');
					if (data.sofa == 1) { //沙发
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lusofa.png">');
					} else {
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/sofa.png">');
					}
					if (data.television == 1) { //电视
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lutelevision.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/television.png">');
					if (data.airConditioner == 1) { //空调
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/luairConditioner.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/airConditioner.png">');
					if (data.wifi == 1) { //无线
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/luwifi.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/wifi.png">');
					if (data.lavatory == 1) { //卫生间
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lulavatory.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lavatory.png">');
					if (data.karaoke == 1) { //唱歌
						html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/lukaraoke.png">');
					} else html.push('<img style="width:40%;display: inline-block;margin:2px 2px;" src="lib/images/karaoke.png">');
					html.push('</div>');
					$('.Room-right').append(html.join(''));
					window.boole = 0;
				}
			}
		}
	});	

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
	     
	     
	     	<p class="nav" style="height:50px;line-height: 50px;text-align: center;font-size:12px;width:100%;font-weight:bold;border-top:2px solid #CECACB;border-bottom:2px solid #CECACB;">
	       <span><span style="color:#7EBE34;">绿色</span>空闲</span>
	       <span><span style="color:#D13035;">红色</span>已预定</span>
	       <span><span style="color:#A4A2A0;">灰色</span>不可选</span>
	       <!-- <span><span style="color:#4D974B;">绿色</span>已选</span> -->
	       <span><span><img style="height:25px;width:25px;" src="lib/images/lu.png"></span>包间</span>
	       <span><span style="width:25px;height:25px;border-radius:50%;background:#7EBE34;display: inline-block;vertical-align: middle;"></span>大厅</span>
	      </p>
	    <div class="nav_in" style="line-height:50px;border-bottom: 2px solid #CECACB;width:100%;height:auto;position: relative;font-size:12px;font-weight: bold;overflow: hidden;height:50px;">
	     <p style="float:left;line-height: 50px;width:36%;">
	      <span style="float:left;">就餐日期:</span>
	      <input type="text" placeholder="选择日期"   style="line-height: 50px;height:50px;margin:0;padding:0;float:left;cursor: pointer;width:49%;border:none;outline:none;" class="layui-input" id="test2" onchange="myFunction()">
	      <span>▼</span>
	     </p> 
	      <p style="float:left;line-height: 50px;width:33%;margin-left:2%;text-align: center;">
	      <span style="float:left;margin-left:2%;">就餐时间:</span>
	      <select class="texts" style="touch-action: none;width:auto;line-height: 50px;height:50px;padding: 0 2%;border:none;outline:none;text-align: center;margin: 0; text-align-last: center;">
	       <option >请选择</option>
	        <option >午餐</option>
	         <option >晚餐</option>
	      </select> 
	      
	     </p> 
	     <select class="pid" style="line-height: 50px;touch-action: none;width:auto;height:50px;float:right;padding: 0 2%;border:none;outline:none;text-align: center;margin: 0; text-align-last: center;">
	       <option >一层</option>
	       <option >二层</option>
	       <option >三层</option>
	      </select> 
	     <span class="pid_in" style="text-align: center;color: black;line-height: 50px;float:right;">楼层：</span>
	   </div>
	    <div class="serve" style="height:50px;line-height: 50px;text-align: center;font-size:12px;width:100%;font-weight:bold;border-bottom:2px solid #CECACB;">
	       <p style="height:50px;line-height: 50px;display: inline-block;margin:0 2%;">厕所<input class="ischange" name='key' value="11" style="margin:0;vertical-align:middle;" type="checkbox"></p>
	       <p style="height:50px;line-height: 50px;display: inline-block;margin:0 2%;">WIFI<input class="ischange" name='key' value="22" style="margin:0;vertical-align:middle;" type="checkbox"></p>
	       <p style="height:50px;line-height: 50px;display: inline-block;margin:0 2%;">沙发<input class="ischange" name='key' value="33" style="margin:0;vertical-align:middle;" type="checkbox"></p>
	       <p style="height:50px;line-height: 50px;display: inline-block;margin:0 2%;">空调<input class="ischange" name='key' value="44" style="margin:0;vertical-align:middle;" type="checkbox"></p>
	       <p style="height:50px;line-height: 50px;display: inline-block;margin:0 2%;">唱歌<input class="ischange" name='key' value="55" style="margin:0;vertical-align:middle;" type="checkbox"></p>
	    </div> 
	     <!-- 搜索 -->
	     <div style="height:40px;width:100%;line-height: 40px;text-align: center;background: #fff;position: relative;margin: 20px 0;">
		   <input placeholder="输入沙发/WIFI/电视/空调等关键字" class="search" style="padding:0 10%;width:80%;height:40px;border-radius:18px;outline: none;border:none;border:1px solid #E0E0E0;text-align: center; " type="text">
		   <img class="pic" style="width:20px;height:20px;position: absolute;right:15%;top:10px;" src="lib/images/sousuo.png"/>
		 </div>
	     
	<!-- 展示 -->
	     
<div class="main" style="width:100%;height:auto;overflow: hidden;">
	      <!-- 左包间 -->
	      <div class="Room-left" style="width:20%;height:auto;float:left;overflow: hidden;margin-left:1%;">
	          
	      </div>
	      
	        <!-- 右包间 -->
	      <div class="Room-right" style="width:20%;height:auto;float:right;overflow: hidden;margin-right:1%;">
	           
	      </div>
	        
	      <!-- 大厅 -->
	       <div class="hall" style="width:58%;height:auto;overflow: hidden;padding:0 2%;"> 
	         
	      </div>
</div> 
</body>






</html>