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

<title>商户主页</title>

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
	  
		function getProduct(){
		    var _uriproduct = window.BASEPATH + 'phoneApp/getCollection?userId=${userId}';
			
			$.get(_uriproduct, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				   var pros=data;
				   var html=[];
				   for(var i=0;i<pros.length;i++){
				   	   var price = formatDigit(pros[i].productPrice);
				       if(i%2==0){
				         	 html.push('<tr>');
				       }
	                     	html.push('<td id="tdtd" style="padding:10px;width:50%">');
		                 	html.push('<input class="inputt" type="checkbox" onclick="return false;" name="checkbox" id="inputt" value="'+pros[i].id+'" style="display:none;float:left;margin-top:50px;background-color:red;width:7%;" />');
		                 if(pros[i].productAuditstatus=='审核通过'){
		                   
		                 	html.push('<image style=" width:92%;height:100px;" src="'+pros[i].productShowPic+'" class="product" id="pro-'+pros[i].id+'-'+pros[i].isactivityproduct+'-'+pros[i].activityReId+'"/>');
			                html.push('<p style="font-size:12px;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;">'+pros[i].productName+'</p>');
			             }else{
			            
			             	html.push('<image style=" width:92%;height:100px;" src="'+pros[i].productShowPic+'"/>');
		                 	html.push('<p style="font-size:12px;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;">已失效</p>');
			             }
			             	html.push('<p style="font-size:12px;">￥'+price+'&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="icon-trash del" id="del-'+pros[i].id+'"></a></p>');
			            	html.push('</td>');
	                     if(pros.length==1){
	                       html.push('<td style="padding:10px;"><div  style="width:100%;height:100px;"></div></td>');
	                     }
	                  if(i%2==1){
				          html.push('</tr>');
				      }
				   
				   }
				   
				   $('#product_table').append(html.join(''));
				   
				   
				}
			});
		
		}
		getProduct();
		//保留两位小数方法
		function formatDigit(digit){
		   if(/^\d+$/.test(digit)){//整数的直接返回
		     return digit;
		   }else if(/^\d+\.\d+$/.test(digit)){//有小数部分的格式化保留两位小数
		     return parseFloat(digit).toFixed(2);
		   }else{//非数字的参数不正确
		     throw "参数不正确";
		   }
		}	
		$(document).on('click','.product',function(){
	       var codes=this.id.split('-');
	       if(codes[2]=='0')
	       {
	       		location.href=window.BASEPATH + 'pubnum/product/index?id='+codes[1];
	       }else{
	       		location.href=window.BASEPATH + 'pubnum/product/index?id='+codes[1]+'&activityproId='+codes[3];
	       }
	    
	    });
	    $(document).on('click','.del',function(){
	    
	       var codes=this.id.split('-');
	       
	       var param={};
	       param.proId=codes[1];
	       param.userId=${userId};
	       param.activityproductId=0;
	       
	       var _uriDelCollect = window.BASEPATH + 'pubnum/delCollectionPro';
		
			$.post(_uriDelCollect, $.toJSON(param), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				 
				   $.toast("已取消");
				   setTimeout(function(){
				   
				   location.href=location.href;
				   
				   },1000);
				   
			});
	    
	    });	  
	    
	    
	     
	    
	    	function getMerchant(){
		    var _uriproduct = window.BASEPATH + 'phoneApp/getmerCollection?userId=${userId}';
		
			$.get(_uriproduct, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				   var pros=data;
				   var html=[];
				   for(var i=0;i<pros.length;i++){
				       if(i%2==0){
				          	html.push('<tr>');
				       }
	                   		html.push('<td  style="padding:10px;width:50%">');
	                     	html.push('<input class="inputt" type="checkbox" onclick="return false;" name="checkboxs" id="inputt" value="'+pros[i].id+'" style="display:none;float:left;margin-top:50px;background-color:red;width:7%;" />');
				       if(pros[i].shopAuditState=='审核通过'){
				       		html.push('<image style=" width:92%;height:100px;" src="'+pros[i].shopPic+'" class="merchant" id="pro-'+pros[i].id+'"/>');
		                 	html.push('<p style="font-size:12px;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;">'+pros[i].shopName+'</p>');
				       }else{
				       		html.push('<image style=" width:92%;height:100px;" src="'+pros[i].shopPic+'"/>');
		                 	html.push('<p style="font-size:12px;-webkit-line-clamp: 1;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;">已失效</p>');
				       }
		               
		               html.push('<p style=""><a href="javascript:void(0)" class="icon-trash delmer" id="delmer-'+pros[i].id+'"></a></p>');  
		               html.push('</td>');
	                   if(pros.length==1){
	                       	html.push('<td style="padding:10px;"><div style="width:100%;height:100px;"></div></td>');
	                   }
	                   if(i%2==1){
				          	html.push('</tr>');
				       }
				   
				   }
				   
				   $('#merchant_table').append(html.join(''));
				   
				   
				}
			});
		
		}
		getMerchant();
		$(document).on('click','.merchant',function(){
	       var codes=this.id.split('-');
	       location.href=window.BASEPATH + 'pubnum/merchant/index?merchantId='+codes[1];
	    });
	    $(document).on('click','.delmer',function(){
	       var codes=this.id.split('-');
	       var param={};
	       param.merId=codes[1];
	       param.userId=${userId};   
	       var _uriDelCollect = window.BASEPATH + 'phoneApp/delmerCollectionPro';
			$.post(_uriDelCollect, $.toJSON(param), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				   $.toast("已取消");
				   setTimeout(function(){
				   location.href=location.href;
				   },1000); 
			});
	    });
     
	
       
    	$(".btn2").click(function(){  //商品     
           	$("input[name='checkbox']").each(function(){ 
			if($(this).prop("checked") ) 
			{ 
			$(this).removeAttr("checked");  
			} 
			else 
			{ 
			$(this).prop("checked","true"); 
			}  	
		}) 
  		 	var obj = document.getElementsByName("checkbox");
			var str=null;
    		for(k in obj){
       			if(obj[k].checked){
           		if(str == null){str = obj[k].value;}else{str += "-"+obj[k].value;}	
        		}
  				}
	       	var param={};
	       	param.productId=str;
	       	param.userId=${userId};
	      	var _uriDelCollect = window.BASEPATH + 'phoneApp/alldelete';
			$.post(_uriDelCollect, $.toJSON(param), function(data){
				    $.toast("取消成功");
				    window.location.reload()
		});
       	}); 
 
  		$(".btn22").click(function(){//商家   
  		     	$("input[name='checkboxs']").each(function(){ 
			if($(this).prop("checked") ) 
			{ 
			$(this).removeAttr("checked");  
			} 
			else 
			{ 
			$(this).prop("checked","true"); 
			}  	
		})
			 var objj = document.getElementsByName("checkboxs");
				var strr=null;
    				for(k in objj){
       			 	if(objj[k].checked){
            		if(strr == null){strr = objj[k].value;}else{strr += "-"+objj[k].value;}	
            	  	}
  					}
	       	var paramm={};
	     	paramm.merId=strr;
	      	paramm.userId=${userId};
	       	var _uriDelCollect = window.BASEPATH + 'phoneApp/merdelete';
			$.post(_uriDelCollect, $.toJSON(paramm), function(data){
				    $.toast("取消成功");
				    window.location.reload()
		});
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
			
			<div class="weui-tab">
			  <div class="weui-navbar">
			    <a id="tab-1" onclick="return false" class="weui-navbar__item weui-bar__item--on" href="#tab1">
			      商品
			    </a>
			    <a id="tab-2" onclick="return false" class="weui-navbar__item" href="#tab2">
			      商家
			    </a>

			  </div>

             <div class="weui-tab__bd"  style="padding-bottom:50px">
			    <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
			       <table id="product_table" style="margin-top:15px;">
                 
                   </table> 
                     <div id="btns" style="width:100%;text-align: center;">
		           <button class="btn2"  style="width:80%;height:40px;background:#18B4ED;color:#ffffff;border:none;outline:none;border-radius:25px;font-size:14px;">取消全部</button>
		           </div> 
			    </div>
			    <div id="tab2" class="weui-tab__bd-item">
			       <table id="merchant_table" style="margin-top:15px;">
                  
                   </table>
                    <div class="btnss" style="width:100%;text-align: center;">
		           <button class="btn22"  style="width:80%;height:40px;background:#18B4ED;color:#ffffff;border:none;outline:none;border-radius:25px;font-size:14px;">取消全部</button>
		            </div> 
			    </div>
			 	</div>
		</div>
	</div>
	       
</body>


</html>