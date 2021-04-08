<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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

<title>上传作品</title>

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

.z-depth-1-bottom {
	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .14), 0 3px 1px -2px
		rgba(0, 0, 0, .12), 0 1px 5px 0 rgba(0, 0, 0, .2) !important;
}

.z-depth-1-right {
	box-shadow: 2px 0 2px 0 rgba(0, 0, 0, .14), 3px 0 1px -2px
		rgba(0, 0, 0, .12), 1px 0 5px 0 rgba(0, 0, 0, .2) !important;
}

.wrapper {
	height: 100%;
	width: 100%;
	position: relative;
}

/* mmenu 样式覆盖 */
.mm-listview .mm-next {
	width: 90px !important;
}

.mm-navbar.mm-navbar-top {
	height: 110px !important;
}

.mm-menu.mm-hasnavbar-top .mm-panel, .mm-menu.mm-hasnavbar-top .mm-fixeddivider
	{
	top: 110px !important;
}

.picker-highlight {
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .3);
}

.picker-highlight .toolbar, .picker-highlight .picker-items {
	background: #fff;
}

.picker-highlight .toolbar .title {
	color: #000;
}

.weui-picker-calendar {
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .3) !important;
}

.weui-picker-calendar .toolbar {
	background: #fff !important;
}

.weui-picker-calendar .picker-calendar-week-days {
	background: #fff !important;
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

.side-menu {
	background-color: #fff;
}

.logout {
	color: #fff !important;
	line-height: inherit !important;
	font-size: inherit !important;
	border-radius: 0;
}

.content {
	padding: 5px;
}

.cards {
	box-shadow: 0 1px 2px rgba(0, 0, 0, .3);
	background-color: #fff;
}

.weui-btn {
	color: #fff !important;
}

.weui-btn:after {
	border-radius: 0 !important;
}

.form-row {
	height: 81px;
	line-height: 80px;
	border-bottom: 1px solid #f1f1f1;
	position: relative;
}

.form-row.last {
	border-bottom: 0;
}

.form-row.btn-content {
	padding: 20px 10px;
}

.form-row.text-content {
	text-align: center;
}

.form-row.sm {
	height: 51px;
	line-height: 50px;
}

.form-row.lg {
	height: 111px;
	line-height: 110px;
}

.back-icon {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	z-index: 0;
	text-align: center;
}

.form-col-half {
	width: 50%;
	height: 100%;
	float: left;
	padding: 0 15px;
	text-align: center;
	position: relative;
	z-index: 1;
	line-height: 80px;
	text-align: center;
}

.form-col-half.sm {
	line-height: 50px;
}

.form-col-half.left-border {
	border-right: 1px solid #f1f1f1;
}

.form-row.sm .form-col-half {
	line-height: 50px;
}

.form-row.sm .form-col-half>input {
	padding: 10px;
}

.form-row input {
	width: 80%;
	margin: auto;
	padding: 10px;
	border: 0;
	font-size: 14px;
}

.form-row textarea {
	width: 80%;
	height: 95px;
	line-height: 35px;
	padding: 0 10px;
	position: relative;
	top: 8px;
	border: 0;
	font-size: 14px;
	font-family: Arial;
}

.btn-custom {
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	border-radius: 0;
	background-color: #18b4ed;
}

.btn-custom:active {
	opacity: 0.7 !important;
	background-color: #18b4ed !important;
}

.content-top, .content-bottom {
	line-height: 40px;
	position: relative;
}

.content-top {
	font-size: 14px;
	color: #bfbfbf;
	top: 8px;
}

.content-bottom {
	bottom: 8px;
}

.content-icon {
	color: #bfbfbf;
	position: relative;
}

.my-popup .weui-popup__modal {
	background: #fbfbfb;
}

.my-popup .go-back>span {
	position: relative;
	top: 2px;
}

.my-popup table {
	width: 100%;
	font-size: 14px;
	border-collapse: collapse;
}

.my-popup table thead th, .my-popup table tbody td {
	border: 1px solid #f1f1f1;
}

.my-popup table tbody td.center {
	text-align: center;
}

.my-popup table th, .my-popup table td {
	padding: 10px 2px;
}

.my-popup table tr:first-child>th, .my-popup table tr:first-child>td {
	border-top: 0;
}

.my-popup table tr:last-child>td {
	border-bottom: 0;
}

.my-popup table tr>th:last-child, .my-popup table tr>td:last-child {
	border-right: 0;
}

.price {
	color: #bfbfbf;
	transition: all .05s ease-in;
}

.price.selected {
	font-size: 18px;
	color: #FB6155;
	border-top: 2px solid #FB6155;
}

/* 计数器 */
.my-counter-label {
	position: relative;
	bottom: 9px;
	font-size: 14px;
}

.my-counter {
	height: 30px;
	line-height: 30px;
	width: 90px;
	border: 1px solid #bfbfbf;
	margin-top: 10px;
	border-radius: 4px;
	position: relative;
}

.my-counter, .my-counter>a, .my-counter>span {
	display: inline-block;
}

.my-counter>a, .my-counter>span {
	height: 28px;
	line-height: 28px;
	text-align: center;
	position: absolute;
}

.my-counter .btn-minus {
	border-right: 1px solid #bababa;
	width: 29px;
	color: #ababab;
	left: 0;
	top: 0;
}

.my-counter .number {
	width: 30px;
	font-size: 14px;
	left: 30px;
	top: 0;
}

.my-counter .number.warning {
	background-color: #FB6155;
	color: #fff;
}

.my-counter .btn-plus {
	border-left: 1px solid #bababa;
	width: 29px;
	color: #ababab;
	right: 0;
	top: 0;
}

/* 日期组件代理对象 */
.click-wrapper {
	position: absolute;
	z-index: 1;
	width: 100% !important;
	height: 100%;
	opacity: 0;
	left: 0;
	top: 0;
	text-align: center;
}
.weui-cells{
    background:none;
}
</style>

</head>

<body>
<!-- enctype="multipart/form-data" -->
<form id="video-form" action="<%=basePath%>gonghui/upload.do" method="POST">
        <image src="lib/images/videoback.jpg" 
		    style="position:absolute;width:100%;height:800px;"/>
		<div class="content" style="height:800px">
		    
			<!-- <div class="weui-cells__title">上传作品</div> -->
			
		   
			
			<div class="weui-cells weui-cells_form">
			    <div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">所属工会</label>
					</div>
					<div class="weui-cell__bd">
						<select class="weui-select" name="companyType" id="companyType">
				            <option value="未选择">未选择</option> 
				            <option value="遵化市总工会">遵化市总工会</option> 
				            <option value="乡镇（街道）总工会">乡镇（街道）总工会</option>
				            <option value="系统工会">系统工会</option>
				            <option value="对口单位工会">对口单位工会</option>
				            <option value="其他">其他</option>       
		            	</select>	
					</div>
				</div>
			    <div class="weui-cell" id="selCompanyCell">
					<div class="weui-cell__hd">
						<label class="weui-label">所属单位</label>
					</div>
					<div class="weui-cell__bd">
						<select class="weui-select" name="selCompany" id="selCompany">
				           
		            	</select>	
					</div>
				</div>
			
			
			
				<div class="weui-cell" id="companyCell" style="display:none;">
					<div class="weui-cell__hd">
						<label class="weui-label">所属单位</label>
					</div>
					<div class="weui-cell__bd">
						<input name="company" id="company" class="weui-input" type="text" placeholder="">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">姓名</label>
					</div>
					<div class="weui-cell__bd">
						<input name="name" id="name" class="weui-input" type="text"
							placeholder="">
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">电话</label>
					</div>
					<div class="weui-cell__bd">
						<input name="phone" id="phone" class="weui-input" type="number" pattern="[0-9]*"
							placeholder="">
					</div>
				</div>
				
		       <div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">作品名称</label>
					</div>
					<div class="weui-cell__bd">
						<input name="videoName" id="videoName" class="weui-input" type="text"
							placeholder="">
					</div>
				</div>
				
				
				<div class="weui-cells__title">上传视频封面(不上传则使用视频第一帧图像):<input type="hidden" name="coverUrl" id="selCover" /></div>
				<div style="width:100%;height:100px;">
					<div style="margin-left:15px;width:100px;height:100px;"
						class="weui-uploader__input-box">
						<input name="cover" id="cover" class="weui-uploader__input" type="file"
							accept="image/*" multiple>
					</div>
					
					<image class="myImage" id="showCover" style="margin-left:15px;width:100px;height:100px;"/>
				</div>
				
				
				<div class="weui-cells__title">上传视频:<input type="hidden" id="selPlay" name="playUrl"/></div>
				<div style="width:100%;height:100px;">
					<div style="margin-left:15px;width:100px;height:100px;"
						class="weui-uploader__input-box">
						<input name="play" id="play" class="weui-uploader__input" type="file"
							multiple>
					</div>
					
					<video autoplay  controls="controls" x5-playsinline=""  webkit-playsinline="true" x-webkit-airplay="true" x5-video-player-type="h5" x5-video-player-fullscreen="" x5-video-orientation="portraint" id="showPlay" style="margin-left:15px;width:100px;height:100px;"/>
				</div>
				</div>
				
				<a id="upload" style="width:96%;margin-top:50px;margin-left:2%;background-color:#FF2B33;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">提交</a>
			
			</div>
			

	<!-- 订单提交表单 -->
</form>
</body>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptgonghui.jsp"></jsp:include>

<script type="text/javascript">
	$(function() {
	   var msg='${msg}';
	   if(msg){
	       alert(msg);
	   }
	
	    $(document).on('click','#upload',function(){
	        $.showLoading();
	    	var _urigetuploadStatus = window.BASEPATH + 'gonghui/getUploadStatus';
		
			$.get(_urigetuploadStatus, null, function(data){
			    if(data.data.data=='error'){
				    if($('#company').val() == ''){
					     $.hideLoading();	
						 $.toast("请填所属单位", "forbidden"); 
						 return false; 
					}
			        if($('#name').val() == ''){
			        	 $.hideLoading();	
						 $.toast("请填写姓名", "forbidden"); 
						 return false; 
					}			
			        if($('#phone').val() == ''){
			        	 $.hideLoading();	
						 $.toast("请填写手机号", "forbidden"); 
						 return false; 
					}			
			        if($('#videoName').val() == ''){
			        	 $.hideLoading();	
						 $.toast("请填作品名称", "forbidden"); 
						 return false; 
					}
				
					if($('#selPlay').val() == ''){
					     $.hideLoading();	 
						 $.toast("请上传视频", "forbidden");  
						 
						 return false; 
					}	
					$('#video-form').submit(); 
			    }else{
			    	$.hideLoading();
			    	alert(data.data.data)
			    	
			    }
				
			});
	        
	        
			
	    });
	
	    $(document).on('change','#cover',function(){
	         var _urigetuploadStatus = window.BASEPATH + 'gonghui/getUploadStatus';
		
			 $.get(_urigetuploadStatus, null, function(data){
			    if(data.data.data=='error'){
			        $.showLoading();
				    setTimeout(function(){
				        uploadFiles($('#cover')[0],$('#selCover'),$('#showCover'),"image");
				    },1000);
			    }else{
			    	alert(data.data.data)
			    }
				
			 });
	       
	      
	

	    });
	    
	   
	    
	    $(document).on('change','#play',function(){
	       
	       var _urigetuploadStatus = window.BASEPATH + 'gonghui/getUploadStatus';
		
			 $.get(_urigetuploadStatus, null, function(data){
			    if(data.data.data=='error'){
			           $.showLoading();
				       setTimeout(function(){
				           uploadFiles($('#play')[0],$('#selPlay'),$('#showPlay'),"video");
				       
				       },1000);
			    }else{
			    	alert(data.data.data)
			    }
				
			 });
	       
	      
	    });
	    
	    
	    function uploadFiles(file,submitObj,showObj,type){
	        
	        var fileType = file.files[0].name.substring(file.files[0].name.lastIndexOf(".") + 1).toLowerCase();
			if(type=="video"&&fileType != "mp4"){
              	alert("请选择mp4格式视频");	
              	$.hideLoading();					
				return false;
			}
			//判断文件大小
			var size = file.files[0].size;
			if (size>104857600) {
				alert("上传文件不能大于100M");
				$.hideLoading();		
				return false;				
			}

	        var percent=0;
	        var interId=setInterval(function(){ 
                 var _urigetpercent = window.BASEPATH + 'gonghui/getUploadPercent?fileName='+file.files[0].name;
		
				 $.get(_urigetpercent, null, function(data){
				    if(data.data.data=='error'){
				        if(percent<30){
				           percent++;
				        }
				        $.showLoading("正在上传:"+percent+"%");	
				    }else{
				    	$.showLoading("正在上传:"+data.data.data.uploadPercent+"%");	
				    }
					
				 });
	        }, 2000);
		    var uploadFile = new FormData();
		    uploadFile.append(file.files[0].name, file.files[0]);
		    var _upQuery = window.BASEPATH + 'gonghui/upload';
		    if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
		    		$.ajax({			
			    		url:_upQuery,		
			    		type:'POST',	
			    		data:uploadFile,		
			    		//async: false,  			
			    		cache: false, 			
			    		contentType: false, //不设置内容类型			
			    		processData: false, //不处理数据			
			    		success:function(data){	
			    		    $.hideLoading();						
				    		showObj.attr('src',data.data.webPath);
				    		submitObj.val(data.data.url);
				    		alert("上传成功，待审核");
				    		clearInterval(interId);
				    				
			    		},			
		    			error:function(jqXHR, textStatus, errorThrown){
		    			    $.hideLoading();
		    				alert("上传失败！");
		    				alert(jqXHR.responseText);
		    				alert(jqXHR.status);
				            alert(jqXHR.readyState);
				            alert(jqXHR.statusText);
				            alert(textStatus);
				            alert(errorThrown);
		    				clearInterval(interId);					
		    			}		
		    		});
		    	
              }else {	
                    $.hideLoading();
                    clearInterval(interId);		
		    		alert("选择的文件无效！请重新选择");
		      }
		  }   

        $(document).on('error','.myImage',function(){
            $(this).removeAttr('src');
        });
        
        initSelCompany();
        function initSelCompany(){
            $('#selCompany').children().remove();
            $('#company').val('');
            var comType=$('#companyType').val();
            var html=[];
            if(comType=='遵化市总工会'){
               html.push('<option value="未选择">未选择</option>');
               html.push('<option value="遵化市总工会">遵化市总工会</option>');
               $('#selCompanyCell').show();
               $('#companyCell').hide();
            }else if(comType=='乡镇（街道）总工会'){
               $('#selCompanyCell').show();
               $('#companyCell').hide();
               html.push('<option value="未选择">未选择</option>');
               html.push('<option value="遵化镇总工会">遵化镇总工会</option>');
               html.push('<option value="西留村乡总工会">西留村乡总工会</option>');
               html.push('<option value="崔家庄乡总工会">崔家庄乡总工会</option>');
               html.push('<option value="西三里乡总工会">西三里乡总工会</option>');
               html.push('<option value="堡子店镇总工会">堡子店镇总工会</option>');
               html.push('<option value="汤泉乡总工会">汤泉乡总工会</option>');
               html.push('<option value="西下营乡总工会">西下营乡总工会</option>');
               html.push('<option value="兴旺寨乡总工会">兴旺寨乡总工会</option>');
               html.push('<option value="马兰峪镇总工会">马兰峪镇总工会</option>');
               html.push('<option value="东陵乡总工会">东陵乡总工会</option>');
               html.push('<option value="石门镇总工会">石门镇总工会</option>');
               html.push('<option value="平安城镇总工会">平安城镇总工会</option>');
               html.push('<option value="东新庄镇总工会">东新庄镇总工会</option>');
               html.push('<option value="刘备寨乡总工会">刘备寨乡总工会</option>');
               html.push('<option value="新店子镇总工会">新店子镇总工会</option>');
               html.push('<option value="团瓢庄乡总工会">团瓢庄乡总工会</option>');
               html.push('<option value="党峪镇总工会">党峪镇总工会</option>');
               html.push('<option value="地北头镇总工会">地北头镇总工会</option>');
               html.push('<option value="娘娘庄乡总工会">娘娘庄乡总工会</option>');
               html.push('<option value="东旧寨镇总工会">东旧寨镇总工会</option>');
               html.push('<option value="铁厂镇总工会">铁厂镇总工会</option>');
               html.push('<option value="苏家洼镇总工会">苏家洼镇总工会</option>');
               html.push('<option value="侯家寨乡总工会">侯家寨乡总工会</option>');
               html.push('<option value="建明镇总工会">建明镇总工会</option>');
               html.push('<option value="小厂乡总工会">小厂乡总工会</option>');
               html.push('<option value="华明路街道办总工会">华明路街道办总工会</option>');
               html.push('<option value="文化路街道办总工会">文化路街道办总工会</option>');
               
            }else if(comType=='系统工会'){
               $('#selCompanyCell').show();
               $('#companyCell').hide();
               html.push('<option value="未选择">未选择</option>');
               html.push('<option value="教育局系统工会">教育局系统工会</option>');
               html.push('<option value="卫健局系统工会">卫健局系统工会</option>');
               html.push('<option value="工信局系统工会">工信局系统工会</option>');
               html.push('<option value="交通局系统工会">交通局系统工会</option>');
               html.push('<option value="水利局系统工会">水利局系统工会</option>');
               html.push('<option value="住建局系统工会">住建局系统工会</option>');
               html.push('<option value="民政局系统工会">民政局系统工会</option>');
               html.push('<option value="税务局系统工会">税务局系统工会</option>');
               html.push('<option value="公安局系统工会">公安局系统工会</option>');
               html.push('<option value="发展改革局系统工会">发展改革局系统工会</option>');
               html.push('<option value="农业农村局系统工会">农业农村局系统工会</option>');
               html.push('<option value="市直机关工会">市直机关工会</option>');
               html.push('<option value="经济开发区工会">经济开发区工会</option>');
               html.push('<option value="手工业联社工会">手工业联社工会</option>');
               html.push('<option value="市供销社工会">市供销社工会</option>');
               html.push('<option value="商业资产管理处工会">商业资产管理处工会</option>');
            }else if(comType=='对口单位工会'){
               $('#selCompanyCell').show();
               $('#companyCell').hide();
               html.push('<option value="未选择">未选择</option>');
               html.push('<option value=" 应急局工会"> 应急局工会</option>');
               html.push('<option value="医保局工会">医保局工会</option>');
               html.push('<option value="市场监督管理局工会">市场监督管理局工会</option>');
               html.push('<option value="城管执法局工会">城管执法局工会</option>');
               html.push('<option value="财政局工会">财政局工会</option>');
               html.push('<option value="邮政局工会">邮政局工会</option>');
               html.push('<option value="电力局工会">电力局工会</option>');
               html.push('<option value="广播电视台工会">广播电视台工会</option>');
               html.push('<option value="广电网络工会">广电网络工会</option>');
               html.push('<option value="清东陵文管处工会">清东陵文管处工会</option>');
               html.push('<option value="国际饭店工会">国际饭店工会</option>');
               html.push('<option value="人民保险公司工会">人民保险公司工会</option>');
               html.push('<option value="烟草公司工会">烟草公司工会</option>');
               html.push('<option value="联通公司工会">联通公司工会</option>');
               html.push('<option value="人寿保险公司工会">人寿保险公司工会</option>');
               html.push('<option value="人民银行工会">人民银行工会</option>');
               html.push('<option value="建设银行工会">建设银行工会</option>');
               html.push('<option value="中国银行工会">中国银行工会</option>');
               html.push('<option value="发展银行工会">发展银行工会</option>');
               html.push('<option value="农村信用社工会">农村信用社工会</option>');
               html.push('<option value="工商银行工会">工商银行工会</option>');
               html.push('<option value="农业银行工会">农业银行工会</option>');
               html.push('<option value="栗源公司工会">栗源公司工会</option>');
               html.push('<option value="港陆公司工会">港陆公司工会</option>');
               html.push('<option value="广野公司工会">广野公司工会</option>');
               html.push('<option value="尚禾源工会">尚禾源工会</option>');
               html.push('<option value="唐百工会">唐百工会</option>');
               html.push('<option value="建投热电厂工会">建投热电厂工会</option>');
               html.push('<option value="亚太工会">亚太工会</option>');
               html.push('<option value="永兴建安工会">永兴建安工会</option>');
               html.push('<option value="金卓工会">金卓工会</option>');
               html.push('<option value="宝兑通工会">宝兑通工会</option>');
               html.push('<option value="圣龙水泥厂工会">圣龙水泥厂工会</option>');
               html.push('<option value="德嘉铝业工会">德嘉铝业工会</option>');
               html.push('<option value="亿通团建工会">亿通团建工会</option>');
               html.push('<option value="勇辉健身工会">勇辉健身工会</option>');
               html.push('<option value="龙宇建筑工会">龙宇建筑工会</option>');
               
            }else{
              $('#selCompanyCell').hide();
              $('#companyCell').show();
            }
            $('#selCompany').append(html.join(''));
            if(comType!='其他'&&$('#selCompany').val()!='未选择'){
               $('#company').val($('#selCompany').val());
            }
            
        }
        $(document).on('change','#companyType',function(){
           initSelCompany();
        });
        $(document).on('change','#selCompany',function(){
            if($('#selCompany').val()!='未选择'){
                $('#company').val($('#selCompany').val());
            }
            
        });
	});
</script>
</html>