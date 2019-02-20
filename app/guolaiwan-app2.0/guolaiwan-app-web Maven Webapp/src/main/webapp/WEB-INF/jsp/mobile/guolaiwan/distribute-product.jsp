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

<title>分销商品</title>

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
.message{
  font-size:14px;
  height:30px;
  line-height:30px;
  margin-left:20px;
  margin-top:10px;

}
#regionBar{
 width:100%;
 height:30px;
 background:#18b4ed;
 color:#FFF;
 line-height:30px;
 padding-left:10px;
 font-size:14px;

}



</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script type="text/javascript">
    
	$(function() {
	
	    var id='${product.id}';
	
	    $(document).on('change','#policy',function(){
	        var count=$(this).val();
	        var _uriGetpolicyPrice = window.BASEPATH + 'distributor/policy/price/'+id+'/'+count;
				
			$.get(_uriGetpolicyPrice, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
			    if(data && data.length>0){
			       $('#product_price').val(data.price);
			    }
			    
			});
	    
	    });
	    
	    $(document).on('click','#applyProduct',function(){
	    
	        if($('#selContractPic').val() == ''){
				$.toast("请上传合同", "forbidden");
				return false;
			}
	        if($('#selContract').val() == ''){
				$.toast("请上传合同视频", "forbidden");
				return false;
			}else{
		   	    var _uriApplyProduct = window.BASEPATH + 'distributor/apply/product';
			    var params={};
			    params.count=$('#policy').val();
			    params.region=$('#region').val();
			    params.price=$('#product_price').html();
			    params.contractPic=$('#selContractPic').val();
			    params.contractVideo=$('#selContract').val();
			    params.productId=id;
			    params.orderId=${orderId};
				$.post(_uriApplyProduct, $.toJSON(params), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
				    if(data){
					$.confirm("确定提交？", function() {
						location.href=window.BASEPATH + 'distributor/order/info/'+data;
					  }, function() {
					  });
				    }
				    
				});				
			}
				    
	    });
	    
	    
	    $(document).on('change','#contract',function(){
	       
	       $.showLoading();
	       setTimeout(function(){
	           uploadFiles($('#contract')[0],$('#selContract'),$('#showContract'));
	       
	       },1000);
	    });
	    
	    $(document).on('change','#contractPic',function(){
	     
	       
	       $.showLoading();
	       setTimeout(function(){
	           uploadFiles($('#contractPic')[0],$('#selContractPic'),$('#showContractPic'));
	       
	       },1000);
	    });
	    
	    
	    function uploadFiles(file,submitObj,showObj){  
	        
		    var uploadFile = new FormData();
		    uploadFile.append(file.files[0].name, file.files[0]);
		    var _upQuery = window.BASEPATH + 'distributor/upload';
		    if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
		    		$.ajax({			
			    		url:_upQuery,		
			    		type:'POST',	
			    		data:uploadFile,		
			    		async: false,  			
			    		cache: false, 			
			    		contentType: false, //不设置内容类型			
			    		processData: false, //不处理数据			
			    		success:function(data){							
				    		showObj.attr('src',data.data.webPath);
				    		submitObj.val(data.data.url);	
				    		$.hideLoading();	
			    		},			
		    			error:function(){
		    				alert("上传失败！");
		    									
		    			}		
		    		});
		    	
              }else {		
		    		alert("选择的文件无效！请重新选择");
		      }
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
				<div class="header-content">分销</div>
			</div>
		</div>
		<div class="content">
		    <image style="width:100%;height:150px;" src="${product.pic}"/>
			<div class="message">景区地址：${product.address}</div>
			<div class="message">开放时间：${product.openTime}</div>
			<div class="message">客服电话：${product.tel}</div>
			<div id="regionBar">购买须知</div>
			<div class="message">
				
				    分销地区：${myregion.name}<input type="hidden" id="region" value="${myregion.id }">
				
 
            </div>
            
            <div class="message" style="font-size:16px;text-align:left;width:80px;">分销政策</div>
            <div class="message" style="margin-top:0">
				<div style="width:120px;float:left;height:44px;line-height:44px;">选择购票数量：👇</div>
				<div style="width:100px;;float:left;height:30px;">
					<select class="weui-select" id="policy" name="policy">
			            <c:forEach var="poli" items="${product.policys}">
			                <option value="${poli.count}">${poli.count}</option>
			            </c:forEach>
		            </select>
            	</div>
            	<div style="width:100px;float:left;height:44px;line-height:44px;">单价：<span id="product_price">120</span>元</div>
            </div>
            
              <div class="weui-cells__title"><br>上传合同:<input type="hidden" name="contractPicUrl" id="selContractPic" /></div>
				<div style="width:100%;height:100px;">
					<div style="margin-left:15px;width:100px;height:100px;"
						class="weui-uploader__input-box">
						<input name="contractPic" id="contractPic" class="weui-uploader__input" type="file"
							accept="image/*" multiple>
					</div>
					
					<image id="showContractPic" style="margin-left:15px;width:100px;height:100px;"/>
						
					
					
				</div>
				
				<div class="weui-cells__title">上传合同视频:<input type="hidden" id="selContract" name="contractUrl"/></div>
				<div style="width:100%;height:100px;">
					<div style="margin-left:15px;width:100px;height:100px;"
						class="weui-uploader__input-box">
						<input name="contract" id="contract" class="weui-uploader__input" type="file"
							multiple>
					</div>
					
					<video autoplay controls="controls" id="showContract" style="margin-left:15px;width:100px;height:100px;"/>
				</div>
            
            
            
		</div>
		
	
		<a id="applyProduct" style="width:96%;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;z-index:1000" href="javascript:;" class="weui-btn weui-btn_primary">申请分销</a>
	</div>
	<!-- 订单提交表单 -->
	
</body>


</html>