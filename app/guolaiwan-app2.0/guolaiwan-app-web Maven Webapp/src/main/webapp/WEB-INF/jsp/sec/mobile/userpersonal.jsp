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

<title>用户个人</title>

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
	min-height: 100%;
	background-color: #fff;
	position: relative;
	-webkit-text-size-adjust: none;
	background-color: #fbfbfb;
}

* {
	box-sizing: border-box;
}

.wrapper {
	height: 100%;
	width: 100%;
	position: relative;
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


.content {
	
}


.weui-btn {
	color: #fff !important;
}

.weui-btn:after {
	border-radius: 0 !important;
}

    .weui-panel__bd{
        padding-bottom:0px !important;
    }
    .weui-cells_checkbox .weui-icon-checked:before{
       font-size:12px !important;
    }
   
    i.icon{
      display:inline-block !important;
    }
.weui-label{
font-size:20px !important;
}
.weui-input{
font-size:20px !important;
}
.weui-select{
font-size:20px !important;
}
.weui-btn{
font-size:20px !important;
}


</style>

</head>
<jsp:include page="../../../mobile/commons/jsp/scriptsec.jsp"></jsp:include>
<!-- 公共脚本引入 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">

	$(function() {
	    
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;
			}

	    };
	    $("#inline-calendar").calendar({
		  container: "#inline-calendar",
		  input: "#date3",
		  dateFormat:'yyyy-mm-dd',
		  onChange:function(p, values, displayValues){
		     $('#date3').val(values[0]);
		     initDatePoint();
		  }
		});
		
		var pointList=[];
		
		function initDatePoint(){
			var _uri = window.BASEPATH+'/sec/phoneapp/getPointVo';
		    var param={};
		    param.userId=${userId};
		    param.comId=${comId};
		    param.date=$('#date3').val();
			$.post(_uri, $.toJSON(param), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
		        pointList=data.message;
		        
	            initPointHtml();
			});
		}
		
		var type='work';
		
		$('.weui-navbar__item').on('click',function(){
		    $('.weui-navbar__item').removeClass('weui_bar__item_on');
		    $(this).addClass('weui_bar__item_on');
		    type=$(this).attr('id');
		    initPointHtml();
		});
		
		function initPointHtml(){
		    $('#pointList').empty();
		    var types=[];
		    if(type=='work'){
		       types.push('ONWORK');
		       types.push('OFFWORK');
		    }else{
		       types.push('CRUISE');
		    }
		    var html=[];
		    for(var i=0;i<pointList.length;i++){
		        if(types.indexOf(pointList[i].type)!=-1){
		             
		             
		             
				     html.push('<div style="width:100%;font-size:20px;color:#666666;margin-top:10px;">');
			         html.push('<div style="margin-left:30px;">'+pointList[i].name+'</div>');
			         html.push('<div style="margin-left:30px;">');
			         for(var j=0;j<pointList[i].secUserPointVos.length;j++){
			             var secstatus="";
			             if(pointList[i].secUserPointVos[j].status=='BEFORE'){
				           	secstatus='早退';
				         }else if(pointList[i].secUserPointVos[j].status=='LATE'){
				            secstatus='迟到';
				         }else if(pointList[i].secUserPointVos[j].status=='NORMAL'){
				            secstatus='打卡正常';
				         }else{
				            secstatus='漏打卡';
				         }
				         html.push('<div class="weui-cells"><div class="weui-cell"><div class="weui-cell__bd" style="font-size:18px;">');
						 html.push('<p>'+pointList[i].secUserPointVos[j].setTimeStr+'</p>');
						 html.push('</div><div class="weui-cell__ft"  style="font-size:18px;">'+secstatus+'</div></div></div>');
			         }
					 html.push('</div></div>');
		        }
		    }
		    $('#pointList').append(html.join(''));
		}
	    
	});
		
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">用户个人</div>
			</div>
		</div>
		
		<div id="content" class="content">
            <div style="width:80px;height:100px;margin:0 auto">
				<img id="userHead"
					style="border-radius:50%;width:80px;height:80px;margin-top:10px"
					class="weui-media-box__thumb" src="lib/images/logo.png">
			</div>
			<div style="text-align:center;margin:0 auto;font-size:20px;">
			    ${userName}
			</div>
			<div style="text-align:center;margin:0 auto;font-size:18px;margin-top:10px;color:#666666">
			    ${comName}
			</div>
			<div class="weui-flex" style="margin-top:20px;">
		      <div class="weui-flex__item"><div style="font-weight:bold;text-align:center;font-size:20px">${notCount}</div></div>
		      <div class="weui-flex__item"><div style="font-weight:bold;text-align:center;font-size:20px">${lateCount}</div></div>
		      <div class="weui-flex__item"><div style="font-weight:bold;text-align:center;font-size:20px;">${beforeCount}</div></div>
            </div>
		    <div class="weui-flex">
		      <div class="weui-flex__item"><div style="font-size:18px;color:#666666;text-align:center">未打卡</div></div>
		      <div class="weui-flex__item"><div style="font-size:18px;color:#666666;text-align:center">迟到</div></div>
		      <div class="weui-flex__item"><div style="font-size:18px;color:#666666;text-align:center">早退</div></div>
		    </div>
            
            <div class="weui-navbar" style="position:relative !important;margin-top:10px;">
			  <div class="weui-navbar__item weui_bar__item_on" id="work" style="font-size:20px">
			     打卡记录
			  </div>
			  <div class="weui-navbar__item" id="cruize" style="font-size:20px">
			    巡逻记录
			  </div>
			</div>
            <div class="weui-cell">
		        <div class="weui-cell__hd"><label for="date3" class="weui-label">日期</label></div>
		        <div class="weui-cell__bd">
		          <input class="weui-input" id="date3" type="text" readonly="">
		        </div>
		     </div>
            <div id="inline-calendar"></div>
            
            <div id="pointList">
               
              
            </div>
		</div>	
	</div>
	
	
	
</body>
</html>