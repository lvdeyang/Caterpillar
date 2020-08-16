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

<title>搜索主页</title>

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
.weui-navbar__item.weui-bar__item--on{
 color:red;
 background:#f1f1f1;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>

<script type="text/javascript">

	$(function() {
	  window.BASEPATH = '<%=basePath%>';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
	  
 		if($("#searchInput").val()!=''){
        	getMerchant(1); 
        	getProduct(1);
        }
        if($('#selType').val()==2){
        
            $('#tab-2').click();
        }else{
            $('#tab-1').click();
        }
		//获取所有一级推荐
	    $(document).on('click','#tab-1',function(){
	        $('#selType').val(1);
		});	 	
 		$(document).on('click','#tab-2',function(){
 		    $('#selType').val(2);
		});	 	
        
	
		function getProduct(page){
		    var _uriproduct = window.BASEPATH + 'phoneApp/search';
		    var params={};
		    params.page=page;
		    params.comId="001";
		    params.type="PRODUCT";
		    params.name=$('#searchInput').val();
			$.post(_uriproduct, $.toJSON(params), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				   $('#product_table').children().remove();
				   var pros=data.products;
				   var html=[];
				   for(var i=0;i<pros.length;i++){
				       if(i%2==0){
				          html.push('<tr>');
				       }
	                     html.push('<td style="padding:10px;width:50%">');
		                 html.push('<image style=" width:100%;height:100px;"  relData="'+pros[i].activityReId+"-"+ 
	                     pros[i].isSurpport+'" data="'+pros[i].productClassCode+'" modularData="'+pros[i].productModularCode+'" src="'+pros[i].productShowPic+'" class="product" id="pro-'+pros[i].id+'-'+pros[i].productMerchantID+'"/>');
		                 html.push('<p style="font-size:12px">'+pros[i].productName+'￥'+pros[i].productPrice+'&nbsp;&nbsp;&nbsp;&nbsp;<span style="text-decoration:line-through">￥'+pros[i].productOldPrice+'</span></p>');
		                 html.push('</td>');
	                     if(pros.length==1){
	                       html.push('<td style="padding:10px;"><div style="width:100%;height:100px;"></div></td>');
	                     }
	                  if(i%2==1){
				          html.push('</tr>');
				      }
				   
				   }
				   if(pros.length==0){
				     	html.push("<tr style='width:100%'><td style='width:100%;font-size:12px;text-align:center'>暂无数据</td></tr>");
				   }
				   $('#product_table').append(html.join(''));
				   
				   
				}
			});

		}
		
		
		function getMerchant(page){
		    var _uriproduct = window.BASEPATH + 'phoneApp/search';
		
		    var params={};
		    params.page=page;
		    params.type="MERCHANT";
		    params.name=$('#searchInput').val();
			$.post(_uriproduct, $.toJSON(params), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				   $('#merchant_table').children().remove()
				   var pros=data.merchants;
				   var html=[];
				   for(var i=0;i<pros.length;i++){
				       if(i%2==0){
				          html.push('<tr>');
				       }
	                     html.push('<td style="padding:10px;width:50%">');
		                 html.push('<image style=" width:100%;height:100px;" src="'+pros[i].shopPic+'" class="merchant" id="pro-'+pros[i].id+'-'+pros[i].modularCode+'"/>');
		                 html.push('<p style="font-size:12px">'+pros[i].shopName+'</p>');
		                 html.push('</td>');
	                     if(pros.length==1){
	                       html.push('<td style="padding:10px;"><div style="width:100%;height:100px;"></div></td>');
	                     }
	                  if(i%2==1){
				          html.push('</tr>');
				      }
				   
				   }
				   if(pros.length==0){
				     	html.push("<tr style='width:100%'><td style='width:100%;font-size:12px;text-align:center'>暂无数据</td></tr>");
				   }
				   $('#merchant_table').append(html.join(''));
				   
				   
				}
			});

		}
		
	

	   $(document).on('click','.product',function(){
	   	   
	       var codes=this.id.split('-');
	       var data=$(this).attr('data');
	       var relData=$(this).attr('relData');
	       var modularData=$(this).attr('modularData');
	       var relDatas=relData.split('-');	       
	       if(relDatas[0]==0&&relDatas[1]==0){	        
		       if(data=='2021'){
		          location.href=window.BASEPATH + 'pubnum/product/index/line?id='+codes[1];
		       }else if(modularData == '0003'){
		          location.href=window.BASEPATH + 'business/gotodelicacystore?merchantId='+codes[2];	     
		       }else if(modularData=='0001'||modularData=='10001'||modularData=='30001'){      
			       //普通票
			       location.href=window.BASEPATH + '/product/package/commodity/jump?merchantId='+codes[2]+'&proId='+codes[1]+'&choice=0';
		       }		       
		       else{		          	       		           
		          location.href=window.BASEPATH + 'pubnum/product/index?id='+codes[1];
		       }
	       
	       }else{	       	          	          
	          if(relDatas[1]==0){
		           if(modularData=='0001'||modularData=='10001'||modularData=='30001'){    
			           //普通票
			           location.href=window.BASEPATH + '/product/package/commodity/jump?merchantId='+codes[2]+'&proId='+codes[1]+'&choice=1';
			       }
			       else if(modularData == '0003'){
			           location.href=window.BASEPATH + 'business/gotodelicacystore?merchantId='+codes[2];	     
			       }else{
			           location.href=window.BASEPATH + 'pubnum/product/index/'+codes[1];
			       }
			   }
	           else{	          
	               location.href=window.BASEPATH + 'pubnum/product/index/surpport/'+relDatas[0]+'/0';
	           }
	       } 
	   });
	   $(document).on('click','.merchant',function(){
	      var ids=this.id.split('-');
     
	       if(ids[2]=='0001'||ids[2]=='30001'){
	           location.href=window.BASEPATH + 'business/merchant/nsAndView?merchantId='+ids[1]+'&comCode=0001';
	       }else if(ids[2]=='10001'){
	           location.href=window.BASEPATH + 'business/merchant/nsAndView?merchantId='+ids[1]+'&comCode=10001';
	       }else if(ids[2] == '0003'){
		       location.href=window.BASEPATH + 'business/gotodelicacystore?merchantId='+ids[1];	     
		   }else if(ids[1]==387){
		       location.href='http://www.yueba.net.cn/chenxisoft/home/mobile/index';
	       }else{
	           location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+ids[1]; 
	       }  
	  
	   });
	});
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
		</div>
		<div class="content">
			
			<div style="height:60px;" class="weui-search-bar" id="searchBar">
			  <form class="weui-search-bar__form" action="pubnum/search/post" method="post">
			    <input type="hidden" name="type" id="selType" value="${type}"/>
			    <div class="weui-search-bar__box">
			      <i  id="search" class="weui-icon-search" style="line-height:50px;"></i>
			      <input style="height:40px" type="search" name="searchContent" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="" value="${content }">
			      <a href="javascript:" class="weui-icon-clear" style="line-height: 44px;" id="searchClear"></a>
			    </div>
			    <label class="weui-search-bar__label" id="searchText">
			      <i class="weui-icon-search"></i>
			      <span style="line-height: 45px;font-weight: bold;">搜索</span>
			    </label>
			  </form>
			  <a style="line-height:40px;" href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
			</div>
			
			
			<div class="weui-tab">
			  <div class="weui-navbar">
			    <a id="tab-1" onclick="return false" class="weui-navbar__item weui-bar__item--on" href="#tab1">
			      商品
			    </a>
			    <a id="tab-2" onclick="return false"  class="weui-navbar__item" href="#tab2">
			      商家
			    </a>

			  </div>

             <div class="weui-tab__bd" style="padding-bottom:50px">
			    <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
			       <table id="product_table" style="margin-top:15px;width:100%">
                  
                    </table>
				   
			    </div>
			    <div id="tab2" class="weui-tab__bd-item">
			       <table id="merchant_table" style="margin-top:15px;width:100%">
                  
                    </table>
				   
			    </div>			
		
			  </div>
		   
		    
		    
		   
		    
		    
		    
		   
		    
		</div>
	</div>
</body>


</html>