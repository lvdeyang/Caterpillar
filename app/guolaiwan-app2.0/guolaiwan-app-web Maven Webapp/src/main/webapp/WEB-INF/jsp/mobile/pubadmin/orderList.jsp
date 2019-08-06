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

<title>订单管理</title>

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
    
    #distributeList{
       margin-top:10px;
       padding-left:10px;
       border-bottom:solid 2px #18b4ed;
       width:100%;height:35px;
       
    }
    
    #distributeList a{
       text-decoration:none;
       color:#CCC;
       font-size:12px;
    }
    #distributeList a.current{
       text-decoration:none;
       color:#18b4ed;
       font-size:20px;
    }
    #columnTable{
    
        width:100%;
        margin-top:10px;
        
    }
    #columnTable td{
	    width:20%;
	    text-align:center;
	    font-size:12px;
    }
    
    

</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script type="text/javascript">
var oderinfoid;
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
		

      var _uriMyOrder = window.BASEPATH + 'pubnum/getOrder?type=${status}';
			$.get(_uriMyOrder, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				 var html=[];
			    if('${status}'=='TESTED')
			    {
			    	$('#buydate').hide();
			    	$('#checkdate').show();
			    }
				if(data){
			
				   for(var i=0;i<data.length;i++){
				   if(data[i].productName==null){
				      data[i].productName='到店支付订单';
				   }
				   html.push('<a href="javascript:void(0);" id="order-'+data[i].id+'"  class="order weui-media-box weui-media-box_appmsg">');
				   html.push('   <div class="weui-media-box__hd">');
				   html.push('     <img style="width:60px;height:60px;" class="weui-media-box__thumb" src="'+data[i].productPic+'">');
				   html.push('   </div>');
				   html.push('   <div class="weui-media-box__bd">');
				   html.push('<h4 class="weui-media-box__title" style="font-size:12px;">'+data[i].productName+'&nbsp;&nbsp;&nbsp;&nbsp;￥'+data[i].productPrice+'x'+data[i].productNum+'</h4>');
				   html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">下单时间'+data[i].createDate+'</p>');
				   html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">验单时间'+data[i].ydDate+'</p>');
				   html.push('   </div>');
				   if('${status}'=='REFUNDING'){
				       html.push('<a class="weui-media-box__desc" style="margin-left:15px;font-size:12px;" href="javascript:void(0)">退款理由:&nbsp;'+data[i].refundReason+'</p>');
				       //html.push('<a style="font-size:12px;margin-left:15px" id="relay-'+data[i].id+'" class="icon-ok" href="javascript:void(0)">&nbsp;&nbsp;同意退款</a>')
				    }
				    if('${status}'=='PAYSUCCESS'){				   
					    if(data[i].logisticsId=="1"){
					         html.push('<a style="font-size:14px;position: absolute;right:0;border-radius:10px;margin:-35px 10px 0 0;color:#8FCBFF;border:1px solid #8FCBFF;text-align:center;padding:0 7px 0 0;" id="ok-'+data[i].id+'" class="check" href="javascript:void(0)" value="'+data[i].id+'">&nbsp;&nbsp;点击发货</a>')
					    }				       
				    }
				   html.push(' </a>');
				   }
				   if(data.length==0)
				   {
				   		html.push("<div style='margin-top:100px;text-align: center;'>暂无数据");
				   }
				}
				$('#orderContent').append(html.join(""));
			});
			
			
			
			var _uriMyProduct = window.BASEPATH + 'pubnum/getMerchantAllPro';
		
			$.get(_uriMyProduct, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				 var html=[];
				if(data){
				   html.push('<option value="0">全部</option>');
				   for(var i=0;i<data.length;i++){
				      html.push('<option value="'+data[i].id+'">'+data[i].productName+'</option>');
				   }
				   html.push('<option value="-1">到店支付</option>');
				}
				$('#proSel').append(html.join(""));
			});
			
			
			$(document).on('click','.order',function(){
		          var ids=this.id.split('-');
		          location.href=window.BASEPATH + 'pubnum/admin/orderinfo?orderId='+ids[1];
		      });
			
			$(document).on('click','.icon-ok',function(){
		          var ids=this.id.split('-');
		          
		          var _urirefund = window.BASEPATH + '/website/wxpay/refund?orderId='+ids[1];
			          $.get(_urirefund, null, function(data){

				        changeOrderStatus(ids[1],'REFUNDED');
				        
					});
		       
		      });
		      
		     //下拉框选择啥快递
			 $(document).on('click','.check',function(){
			 oderinfoid=$(this).attr("id");
			     $(".xianshi").fadeIn();
			     var html=[];
			     var _uriMyOrder = window.BASEPATH + 'admin/logistic/getMerchantLogistics';
				 $.get(_uriMyOrder, null, function(data){
				     for(var i=0;i<data.length;i++){
				          html.push('<option value="'+data[i].id+'">'+data[i].name+'</option>');
				     }
			         $('#xuanze').append(html.join(''));
				 });
			          
			 });
			 //取消狂框
			  $(document).on('click','.quxiao',function(){			         
			          $("#kuaidi").val("");
			          $(".xianshi").fadeOut();			       
			 });
			 
			 
	
	
	function searchOrder(){
 	$('#orderContent').children().remove();
		if('${status}'=='TESTED'){
			var _uriseaOrder = window.BASEPATH + 'pubnum/searchOrder?type=${status}&proId='+
	     		$('#proSel').val()+'&start='+$('#checkstart').val()+'&end='+$('#checkend').val();
		}else{
	     	var _uriseaOrder = window.BASEPATH + 'pubnum/searchOrder?type=${status}&proId='+
	     		$('#proSel').val()+'&start='+$('#start').val()+'&end='+$('#end').val();
		}
		
			$.get(_uriseaOrder, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				 var html=[];
				if(data){
				   for(var i=0;i<data.length;i++){
				   if(data[i].productName==null){
				      data[i].productName='到店支付订单';
				   }				   
				   html.push('<a href="javascript:void(0);" id="order-'+data[i].id+'"  class="order weui-media-box weui-media-box_appmsg">');
				   html.push('   <div class="weui-media-box__hd">');
				   html.push('     <img style="width:60px;height:60px;" class="weui-media-box__thumb" src="'+data[i].productPic+'">');
				   html.push('   </div>');
				   html.push('   <div class="weui-media-box__bd">');
				   html.push('<h4 class="weui-media-box__title" style="font-size:12px;">'+data[i].productName+'&nbsp;&nbsp;&nbsp;&nbsp;￥'+data[i].productPrice+'x'+data[i].productNum+'</h4>');
				   html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">下单时间'+data[i].createDate+'</p>');
				   html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">验单时间'+data[i].ydDate+'</p>');
				   html.push('   </div>');
				   if('${status}'=='REFUNDING'){
				       html.push('<a class="weui-media-box__desc" style="margin-left:15px;font-size:12px;" href="javascript:void(0)">退款理由:&nbsp;'+data[i].refundReason+'</p>');
				    }
				    if('${status}'=='PAYSUCCESS'){				  
				       html.push('<a style="font-size:14px;position: absolute;right:0;border-radius:10px;margin:-35px 10px 0 0;color:#8FCBFF;border:1px solid #8FCBFF;text-align:center;padding:0 7px 0 0;" id="ok-'+data[i].id+'" class="check" href="javascript:void(0)">&nbsp;&nbsp;点击发货</a>')
				    }
				   html.push(' </a>');
				   }
				   if(data.length==0)
				   {
				   		html.push("<div style='margin-top:100px;text-align: center;'>暂无数据");
				   }
				}
				$('#orderContent').append(html.join(""));
			});
	
	
	}
	
	$(document).on('change','#proSel',function(){
	searchOrder();
	
	});
	$(document).on('change','#start',function(){
	searchOrder();
	
	});
	$(document).on('change','#end',function(){
	searchOrder();
	
	});
	$(document).on('change','#checkstart',function(){
	searchOrder();
	
	});
	$(document).on('change','#checkend',function(){
	searchOrder();
	
	});
	
	});
	
	
		//点击确定以后发送请求保存快递单号和快递类型
     function addlogistics(){
        var kdname=$("#kuaidi").val();
        var id=$("#xuanze").val();
            if(!isNaN(kdname)){		              
		          }else{
		             $.alert("请重新输入正确的快递单号")		   
		            return;
		     }         
		var _urichangeorder = window.BASEPATH + 'pubnum/UpdateKd?orderId='+oderinfoid+'&kdname='+kdname+'&id='+id;
	       $.get(_urichangeorder, null, function(data){
			  if(data=="success"){
				   location.href=location.href;
			}	
			 if(data=="error"){
				   alert("保存失败");
			}							
		}); 		
	}
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">订单管理</div>
			</div>
		</div>
		<div class="content">
			    <div class="weui-cells weui-cells_form">
			       <div class="weui-cell">
				    <div class="weui-cell__hd"><label class="weui-label">选择商品</label></div>
				    <div class="weui-cell__bd">
				      <select id="proSel" class="weui-select" name="select2">
			            
			          </select>
				    </div>
				  </div>
				  <div class="weui-cell" id="buydate">
				    <div class="weui-cell__hd"><label for="" class="weui-label">支付时间</label></div>
				    <div class="weui-cell__bd">
                        <input id="start" style="width:100px;font-size:12px;background-color: #f0eff4;height:30px;" class="weui-input" type="date" value="">--<input id="end" style="width:100px;font-size:12px;background-color: #f0eff4;height:30px;" class="weui-input" type="date" value="">
				    </div>
				  </div>
				  <div class="weui-cell" style="display:none;" id="checkdate">
				    <div class="weui-cell__hd"><label for="" class="weui-label">验单日期</label></div>
				    <div class="weui-cell__bd">
                        <input id="checkstart" style="width:100px;font-size:12px;background-color: #f0eff4;height:30px;" class="weui-input" type="date" value="">--<input id="checkend" style="width:100px;font-size:12px;background-color: #f0eff4;height:30px;" class="weui-input" type="date" value="">
				    </div>
				  </div>
				 
			    </div>
		
			      <div id="orderContent" class="weui-panel__bd">
				    
				   
			     </div>

			
			
		</div>
	</div>
	 <div class="xianshi" style="width:300px;height:200px;background:#fff;overflow: hidden;text-align: center;margin:0 auto;position: fixed;top:50%;left:50%;margin:-100px 0 0 -150px;border:1px solid #D3D3D3;display: none;">
	  <p style="color:black;margin-top:10px;font-size:18px;">提示</p>
	  <p style="color:black;margin-top:10px;color:#999999;font-size:12px;">请输入快递单号并选择快递种类</p>
	  <input id="kuaidi" placeholder="请输入快递单号"  type="text" style="height:30px;width:40%;margin-top:20px;" >
	  <select id="xuanze" style="height:30px;width:40%;margin-top:20px;">
	   </select>
	   <button class="quxiao" style="position:absolute;left:0;bottom:0;width:50%;height:50px;font-size:18px;border:none;outline:none;border:1px solid #D3D3D3;background:#fff;">取消</button>
	   <button style="position:absolute;right:0;bottom:0;width:50%;height:50px;font-size:18px;border:none;outline:none;border:1px solid #D3D3D3;background:#fff;" onclick="addlogistics()" >确定</button>
	 </div>

</body>


</html>