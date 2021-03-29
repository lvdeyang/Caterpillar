<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%  String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频列表</title>
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
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
	
<body style="margin:0">

<div class="content" style="padding-bottom:50px">
	<image src="<%=basePath%>lib/images/header.jpg" style="width:100%;"/>
	<div  style="width:100%;height:50px;margin-top:30px">
	    <a id="upload" style="width:50%;margin-left:25%;background-color:#FF2B33;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">上传作品</a> 
	</div>
	<div class="weui-loadmore" hidden="hidden" style="position:fixed;bottom: 5%;left:18%;z-index: 10000">
			  <i class="weui-loading"></i>
			  <span class="weui-loadmore__tips">正在加载</span>
	</div>
	<div class="weui-loadmores" hidden="hidden" style="position:fixed;bottom: 7%;left:50%;margin-left:-40px;z-index: 10000">
			  <span class="weui-loadmore__tips">没有内容了</span>
	</div>		
	
</div>
</head>
</body>
	<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptgonghui.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
	   var msg='${msg}';
	   if(msg){
	       alert(msg);
	   }
	   window.BASEPATH = '<%=basePath%>';
	   var pageNumber=1;
	   getVideos();
	
	   $(document).on('click','.acount',function(){
	       var ids=this.id.split('-');
	       var _uriAcount = window.BASEPATH + 'gonghui/acount?id='+ids[1];
		
		   $.post(_uriAcount, null, function(data){
				data = parseAjaxResult(data);
				if(data.msg){
				   $.toast(data.msg);
				   return false;
				}else{
				   $.toast("点赞完成");
				   $('#acounttext-'+ids[1]).html(data.aCount+'赞');
				}
			    
		   });
	   });
	   $('#upload').on('click',function(){
		    location.href=window.BASEPATH +"gonghui/video/upload/index";
	   });
	   
	   var loadingData=false;
	   
	   $(window).scroll(function(){
　　			 //判断是否滑动到页面底部
	         /*if($(window).scrollTop() === $(document).height() - $(window).height()){ */
		     if($(window).scrollTop() + $(window).height() +10>= $(document).height()){
				if(loadingData) return false;
		        // TODO 滑动到底部时可请求下一页的数据并加载，加载可使用append方法
		        $('.weui-loadmore').fadeIn().addClass("show");
				getVideos();
				
			 }		
			
		});
		
		function getVideos(){
		 loadingData=true;
	     var _uriVideoList = window.BASEPATH + 'gonghui/videoList?page='+pageNumber;
		
		 $.get(_uriVideoList, null, function(data){
				data = parseAjaxResult(data);
				if(!data.list||data.list.length==0){
				    $('.weui-loadmore').fadeOut().addClass("show")
				    return;
				}
			    var html=[];
			    if(data.list.length>0){
			       
			        pageNumber+=1;
			    }
			    for(var i=0; i<data.list.length; i++){
					html.push('<div style="width:100%;font-size:small;float:left"><span style="margin-left:10px">编号:'+data.list[i].id+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+data.list[i].videoName+'</span></div>');
					html.push('<div ><video poster="'+data.list[i].realCoverUrl+'" src="'+data.list[i].realPlayUrl+'" x5-video-player-type="h5" controls="controls" width=100% height="200"></video></div>');
					//html.push('<div ><video poster="https://glw-old-file.oss-cn-beijing.aliyuncs.com/file/gonghui/6A42CCC8-3976-4DE6-9AA3-FB7CBFC00058.png" src="https://glw-old-file.oss-cn-beijing.aliyuncs.com/file/gonghui/IMG_3391.MP4" x5-video-player-type="h5" controls="controls" width=100% height="200"></video></div>');
					
					
					html.push('<div style="font-size:small;line-height:30px;"><span style="margin-left:10px;">'+data.list[i].name+'</span>');
					html.push('<div style="font-size:small;line-height:30px;float:right"><span id="acounttext-'+data.list[i].id+'" style="margin-right:10px;">'+data.list[i].aCount+'赞</span>');
					html.push('<a id="acount-'+data.list[i].id+'" style="margin-right:10px;width:80px;float:right;background-color:#FF2B33;height:30px;line-height:30px;" href="javascript:;" class="weui-btn weui-btn_primary acount">点赞</a></div>');
					html.push('<div style="font-size:small;width:50%;line-height:30px;float:left"><span style="margin-left:10px">'+data.list[i].company+'</span></div>');
				} 
				$('.content').append(html.join(''));
				$('.weui-loadmore').fadeOut().addClass("show");
				loadingData=false;
		 });
		 
	  }
	   
	   
	})
	
	
</script>



</html>