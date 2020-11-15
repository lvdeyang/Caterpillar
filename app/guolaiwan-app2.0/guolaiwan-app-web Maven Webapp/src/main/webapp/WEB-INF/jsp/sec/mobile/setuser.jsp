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

<title>用户管理</title>

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
    .weui-navbar__item{
      font-size:xx-small !important;
    }
    i.icon{
      display:inline-block !important;
    }

</style>

</head>
<jsp:include page="../../../mobile/commons/jsp/scriptsec.jsp"></jsp:include>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.13&key=ff4672efcfc6279cbe3b2815dd70a1ec"></script>
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
	    
	    
	    
	    initList();
	    
	    function initList(){
	       var _uri = window.BASEPATH+'/sec/phoneapp/checkUserList';
		   var param={};

		   $.post(_uri, $.toJSON(param), function(data){
		        var html=[];
		        $('#userList').children().remove();
		        for(var i=0;i<data.message.length;i++){
		            var type="";
		            var status="";

		            if(data.message[i].type=='SECURITY'){
		               type+="保安";
		            }else if(data.message[i].type=='ADMIN'){
		               type+="管理员";
		            }

		            if(data.message[i].status=='CHECKING'){
		            	status='审核中';
		            }else if(data.message[i].status=='PASSED'){
		                status='审核通过';
		            }else{
		                status='审核未通过';
		            }
		            
		        	html.push('<div class="weui-cell weui-cell_swiped" id="setuser-'+data.message[i].id+'">');
			        html.push('<div class="weui-cell__bd" style="transform: translate3d(0px, 0px, 0px);">');
			        html.push('<div class="weui-cell">');
			        html.push('<div class="weui-cell__bd">');
			        html.push('<p style="font-size:18px">'+data.message[i].name+':'+type+'('+data.message[i].phone+')'+'&nbsp;&nbsp;'+status+'</p></div>');
			        html.push('<div class="weui-cell__ft"></div></div></div>');
			        html.push('<div class="weui-cell__ft">');
			        html.push('<a class="weui-swiped-btn weui-swiped-btn_warn delete-swipeout" style="font-size:18px;" id="deny-'+data.message[i].id+'" href="javascript:">拒绝</a>');
			        html.push('<a class="weui-swiped-btn weui-swiped-btn_default close-swipeout" style="font-size:18px" id="pass-'+data.message[i].id+'"  href="javascript:">通过</a>');
			        html.push('</div></div>');
			       
		        }
		        $('#userList').append(html.join(''));
		        $('.weui-cell_swiped').swipeout();
		        $('.delete-swipeout').on('click',function(){
		            var ids=this.id.split('-');
			    	changeStatus(ids[1],'DENY');
		    	});
			    $('.close-swipeout').on('click',function(){
			        var ids=this.id.split('-');
			    	changeStatus(ids[1],'PASSED');
			    });
		        
		   });
	    }
	    
	    
	    
	    function changeStatus(id,status){
	    
	       
	    	var _uri = window.BASEPATH+'/sec/phoneapp/user/status';
		    var param={};
		    param.id=id;
		    param.status=status;
			$.post(_uri, $.toJSON(param), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
	            $('.weui-cell_swiped').swipeout('close') //关闭
	            initList();
			});
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
				<div class="header-content">打卡点管理</div>
			</div>
		</div>
		
		<div id="content" class="content">
		    <div class="weui-cells__title" style="height:30px;font-size:20px;">左滑动审核
		    </div>
            <div style="width:100%" id="userList"></div>
		</div>	
	</div>
	
	
	
</body>
</html>