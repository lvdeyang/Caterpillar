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
<title>订桌管理</title>
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

.xinxi p {
		height: 50px;
		line-height: 50px;
		font-size: 20px;
	}
	
	.left, .right ,.putaway,.below,.quit,.delete{
		border: 1px solid #898B92;
		padding: 2px 2px;
		margin:1%;
		width:30%;
		display: inline-block;
		font-size:14px;
	}
	.toast {
	background: #f80851;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
<script type="text/javascript">
   $(function() {
    list();

      var overall = null;
      var tier = null;
		function list(date, type) {
			var _uti = window.BASEPATH + '/admin/tablelist/getlist.do';
			var param = {};
			param.TableDate = date;
			param.merchantId = '${merchantId}';
			param.type = type;
			$.post(_uti, param, function(data) {
				$('#div1').empty();
				/* data = parseAjaxResult(data); */
				if(data.date != "" && data.repast != ""){ //根据时间添加就餐日期  就餐时间
				  $("#test1").val(""+data.date+"");
				  if(data.repast == "LUNCH"){ //午餐
				   $("#times").val("午餐");
				  }else{ //晚餐
				   $("#times").val("晚餐");
				  }
			    }
				data = data.po;
				overall = data;
				var html = [];
				if (data.length > 0) {
					for (var i = 0; i < data.length; i++) {
						if (tier == null || tier != data[i].tier) {
							tier = data[i].tier;
							html.push('<p style=" background-color:#f2f2f2; font-size:18px;font-weight:bold;height:80px;line-height: 80px;">' + data[i].tier + '</p> ');
						}
						html.push('<div class="xuanzhong" id="' + data[i].id + '" style="width:auto;height:auto;text-align: center;margin:20px;display: inline-block;overflow: hidden;z-index:111111;">');
						if (data[i].room == 1) {
							if (data[i].tableState == 0) html.push('<img  style="height:80px;width:80px;" src="lib/images/homes.png">');
							if (data[i].tableState == 1) html.push('<img  style="height:80px;width:80px;" src="lib/images/luse.png">');
							if (data[i].tableState == 2) html.push('<img  style="height:80px;width:80px;" src="lib/images/xuanzhon.png">');
						} else {
							if (data[i].tableState == 0) html.push('<img  style="height:80px;width:80px;" src="lib/images/huizuo.png">');
							if (data[i].tableState == 1) html.push('<img  style="height:80px;width:80px;" src="lib/images/luzuo.png">');
							if (data[i].tableState == 2) html.push('<img  style="height:80px;width:80px;" src="lib/images/hongzuo.png">');
						}
						html.push('<p style="font-size:18px;font-weight:bold;height:50px;line-height:50px;">' + data[i].tablename + '</p>');
						html.push('</div>');
					}
				} else {
					html.push('<p style=" text-align:center; font-size:18px;font-weight:bold;height:50px;line-height:50px;">暂无房间</p>');
				}
				$('#div1').append(html.join(''));
			});
		}

		$(document).on('click', '#save', function() { //预订按钮
			var userPhone = $("#shenfen").val(); //手机
			var userName = $("#personName").val(); //用户名
			var tableId = $("#inpu").val(); //房间表id
			var tableStatusId = $("#tableStatusId").val(); //中间表id
			var tableMenu = $("#price").val(); //菜品
			var bookprice = $("#bookprice").val(); //订金
			var date = $("#time").val();
			var type = $("#midday").val();
			var img = $("#parkingshopImg").val();
			if (userPhone == "") {
				alert("请输入手机号");
				return false;
			}
			if (userName == "") {
				alert("请输入入住人姓名");
				return false;
			}
			if (bookprice == "") {
				alert("请输入房间价格");
				return false;
			}
			if (date == "") {
				alert("请选择时间");
				return false;
			}
			if ($("#tablelist").val() == "") {
				alert("发生未知错误 没有商户ID");
				return false;
			}
			
			var _uti = window.BASEPATH + '/admin/tablelist/addData.do';
			var param = {};
			param.merchantId = '${merchantId}';
			param.userPhone = userPhone;
			param.type = type;
			param.userName = userName;
			param.tableId = tableId;
			param.tableStatusId = tableStatusId;
			param.tableMenu = tableMenu;
			param.bookprice = bookprice;
			param.date = date;
			$.post(_uti, param, function(data) {
				$(".xinxi").fadeOut();
				list($("#test1").val(), $("#times").val());
			})
		});
		$(document).on('click', '.xuanzhong', function() {//详情页面
			$("#shenfen").val(""); //手机
			$("#personName").val(""); //用户名
			$(".mig").text(""); //房间名称
			$("#time").text(""); //房间名称
			$("#inpu").val(""); //房间表id
			$("#price").val(""); //菜品
			$("#tableStatusId").val(""); //中间表id
			$("#bookprice").val(""); //订金
			for (var i = 0; i < overall.length; i++) {
				if (this.id == overall[i].id) {
					$(".ming").text(overall[i].tablename);
					$("#inpu").val(overall[i].id);
					$("#bookprice").val(parseInt(overall[i].bookprice) / 100);
					if ( /* overall[i].tableMenu != null && */ overall[i].userName != null && overall[i].userPhone != null && overall[i].menuTime != null && overall[i].type != null) {
						$("#time").val(overall[i].menuTime);
						if (overall[i].type == "LUNCH") {
							$("#midday").val(0);
						} else {
							$("#midday").val(1);
						}
						$("#personName").val(overall[i].userName);
						$("#price").val(overall[i].tableMenu);
						$("#shenfen").val(overall[i].userPhone);
						$("#tableStatusId").val(overall[i].tableStatusId);
					}
				}
			}
			$(".xinxi").fadeIn();
		});

		$(document).on('click', '#cancel', function() { //取消预订
			var _uti = window.BASEPATH + '/admin/tablelist/cancel.do';
			var param = {};
			param.tableStatusid = $("#tableStatusId").val();
			param.tableid = $("#inpu").val();
			$.post(_uti, param, function(data) {
				if (data.code == 1) {
					return alert("此房间没有预订");
				}
				$(".xinxi").fadeOut();
				list($("#test1").val(), $("#times").val());
			})
		});

		$(document).on('click', '.delete', function() { //删除此房间
			var _uti = window.BASEPATH + '/admin/tablelist/delete.do';
			var param = {};
			param.tableid = $("#inpu").val();
			$.post(_uti, param, function(data) {
				$(".xinxi").fadeOut();
				list($("#test1").val(), $("#times").val());
			})
		});

		$(document).on('click', '.putaway', function() { //上架
			if ($("#tableStatusId").val() != null && $("#tableStatusId").val() != "") {
				alert("已预订餐桌 请先下架 清空信息");
			}
			var _uti = window.BASEPATH + '/admin/tablelist/amend.do';
			var param = {};
			param.state = 1;
			param.tableStatusid = $("#tableStatusId").val();
			param.tableid = $("#inpu").val();
			$.post(_uti, param, function(data) {
					$(".xinxi").fadeOut();
					list($("#test1").val(), $("#times").val());
			})
		});
		
		
		$(document).on('click', '.below', function() { //下架
			var _uti = window.BASEPATH + '/admin/tablelist/amend.do';
			var param = {};
			param.state = 0;
			param.tableStatusid = $("#tableStatusId").val();
			param.tableid = $("#inpu").val();
			$.post(_uti, param, function(data) {
				$(".xinxi").fadeOut();
				list($("#test1").val(), $("#times").val());
			})
		});


		$(document).on('click', '.right', function() { //关闭窗口
			$(".xinxi").fadeOut();
		});
		
		$(document).on('click', '#btns', function() { //根据对应时间搜索
			if ($("#test1").val() == null || $("#test1").val() == "") {
				alert("请选择时间");
				return false;
			}
			if ($("#times").val() == null) {
				alert("请选择中午晚上");
				return false;
			}
			list($("#test1").val(), $("#times").val()); 
		});

	});
	
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//常规用法
		laydate.render({
			elem : '#test1'
		});
	});
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		//常规用法
		laydate.render({
			elem : '#time'
		});
	});
</script>

<body>
  <div class="x-body" style="text-align: center;padding:20px 10px;">
	<input type="text" style="width:auto;height:38px;"
		class="layui-input" id="test1" placeholder="yyyy-MM-dd">
	<select id="times" style="width:auto;height:38px;">
		<option value="午餐">午餐</option>
		<option value="晚餐">晚餐</option>
	</select>
	<button id="btns"
		style="width:auto;height:38px;padding:0 10px;border:none;outline: none; background-color: #009688;color:#fff;">搜索</button>
	</div>
	<div id="div1"></div>
	<!-- 主页 -->

    <!-- 信息登记 -->
	<div class="xinxi" style="width:100%;height:100%;border:5px solid #393D49;padding:10px 2%;position: fixed;top:0;left:0;z-index:11;background:#fff;display: none;overflow: hidden;overflow-y: auto">
		<p style="">
			房间名称：<span class="ming" value="111">1层102</span>
			<input style="display:none;" id="inpu" value="">	
			<input style="display:none;" id="tableStatusId" value="">	
		</p>
		<p>
			用户姓名：<input style="height:30px;width:60%;"  id = "personName" >
		</p>
		<p style="">
			手机号 &nbsp;&nbsp; ：<input id="shenfen" style="height:30px;width:60%;" value="">
		</p>
		<p style="">
			订金 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ：<input id="bookprice" style="height:30px;width:60%;" value="">
		</p>
		<p style="">
			预订菜品：<input style="height:30px;width:60%;" id = "price" >
		</p>
		<p style="">
			预订时间：<input type="text" class="layui-input"  style="height:30px;width:60%;display: inline-block;" id = "time" placeholder="yyyy-MM-dd">
		</p>
		<p style="">
			午晚 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ：<!-- <input style="height:30px;width:600px;" id = "midday" > -->
			  <select id="midday" style="height:30px;width:60%;">
              <option value="0">午餐</option>
              <option value="1">晚餐</option>
              
             </select>
		</p>
		<!-- <p>
			订桌时间：<input style="height:30px;width:600px;"id = "phone"  >
		</p>
		<p>
			起始时间：<input style="height:30px;width:300px;"id = "startingTime" >
		</p>
		<p style="">
			结束时间：<input style="height:30px;width:300px;"id = "stopTime" >
		</p> -->
		<p style="text-align: center;margin:30px auto 0;">
			<span class="left" style=""  id="save">预订</span><span class="left" style=""  id="cancel">取消预订</span><span class="putaway">上架</span><span class="below">下架</span><span class="delete">删除此房间</span><span class="right">关闭窗口</span>
		</p> 
	</div>

</body>





</html>