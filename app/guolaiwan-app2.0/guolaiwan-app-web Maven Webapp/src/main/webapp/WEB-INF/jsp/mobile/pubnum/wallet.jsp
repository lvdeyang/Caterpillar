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

<title>个人主页</title>

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
	-webkit-text-size-adjust: none;
	background-color: #fbfbfb;
	position: fixed;
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
   .jinbi{
   		
   		width: 20%;
   		height: 20%;
   }
   
 .weui-dialog .weui-dialog__btn.default, .weui-toast .weui-dialog__btn.default {
 cursor:pointer !important;
 }   
.weui-dialog .weui-dialog__btn + .weui-dialog__btn, .weui-toast .weui-dialog__btn + .weui-dialog__btn{
 cursor:pointer !important;
}
.weui-prompt-input .weui-input{
cursor:pointer !important;
}
.weui-prompt-input{
height:2.5em !important;
cursor:pointer !important;
padding:4px 10% !important;
}
.weui-dialog__bd{
 line-height: 0 !important;
}
.default,.primary{
z-index:11111 !important;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript">

	var page=1;
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
    
	//ios弹出输入法页面元素错位问题
  　　document.body.addEventListener('focusout', () => {
    　　   window.scrollTo({ top: 0, left: 0, behavior: 'smooth' })
  　   })

		getMoney();
	getreckoning();
	
	});
	
	function getMoney(){
		  var userId=${userId};
          var _uriorder =window.BASEPATH+'phoneApp/wallet?userId='+userId;
          $.get(_uriorder, null, function(data){
			data = parseAjaxResult(data);
			var money=((data.data.wallet)/100).toFixed(2);
				$('.money').text(money);
			});
		}
	
	
	
	
		var prepay_id;
		var paySign; 
		var appId;   
		var timeStamp;   
		var nonceStr;  
		var packageStr;  
		var signType; 
		var orderNo;
	
	
	function invest(){
	 var orderurl =window.BASEPATH+'pubnum/wallet/addOrder';
	 var userId=${userId};
		$.prompt({
		  title: '请您输入充值金额：（元）',
		  input: '',
		  empty: false, // 是否允许为空
		  onOK: function (input) {
		    if(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(input)){
		    	 $.post(orderurl,{'money':input,'userId':userId,'type':1},function(data){
		    		data = parseAjaxResult(data)
		    		orderNo=data.id;
		    		$.get(window.BASEPATH+'pubnum/wallet/pay/'+orderNo+"/"+userId, null, function(data){
		        	data = parseAjaxResult(data)
					prepay_id = data.prepay_id;
			        paySign = data.paySign;
			        appId = data.appId;
			        timeStamp = data.timeStamp;
			        nonceStr = data.nonceStr;
			        packageStr = data.packageStr;
			        signType = data.signType;
			        callpay();
				});
		    		
		    	});
		    }else{
		    	$.alert('您的输入有误！')
				return;		    	
		    }
		  },
		  onCancel: function () {
		    //点击取消
		    $.alert('您已取消充值，感谢您的使用！')
		    return;
		  }
		});
	}
	
	
	function Withdraw(){
			 var orderurl =window.BASEPATH+'pubnum/wallet/addOrder';
			 var userId=${userId};
				$.prompt({
				  title: '请您输入提现金额：（元）',
				  input: '',
				  empty: false, // 是否允许为空
				  onOK: function (input) {
			 		  
				    if(/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(input)){
				    	 $.post(orderurl,{'money':input,'userId':userId,'type':2},function(data){
				    		data = parseAjaxResult(data)
				    		if(input<1){
  						$.alert('提现金额不能小于1元！')
  						  
						return;		  	
						}
				    		if(data==2){
				    		 
				    		$.alert('您的钱包余额不足！')
				    		}else{
				    		orderNo=data.id;
				    		mmpay(orderNo);
				    		}
						});
				  
				    }else{
				    	$.alert('您的输入有误！')
						return;		    	
				    }
				    
				  },
				  onCancel: function () {
				    //点击取消
				    $.alert('您已取消提现，感谢您的使用！')
				    return;
				  }
				});
			}
	
	//提现方法
	function mmpay(orderNo){
		var userId=${userId};
		var url=window.BASEPATH+'website/wxpay/mmpay?userId='+userId+'&orderNo='+orderNo;
		$.get(url,null,function(data){
				data = parseAjaxResult(data);
				 if(data==1){
				 $.alert({
				  title: '提现提醒',
				  text: '提现成功，24小时内到账！',
				  onOK: function () {
				    //点击确认
				 window.location.reload();
				  }
				});
				 }
		})
	}
	
	
	function onBridgeReady(){
		    WeixinJSBridge.invoke(
		        'getBrandWCPayRequest', {
		           "appId"     : appId,     //公众号名称，由商户传入
		           "timeStamp" : timeStamp, //时间戳，自1970年以来的秒数
		           "nonceStr"  : nonceStr , //随机串
		           "package"   : packageStr,
		           "signType"  : signType,  //微信签名方式：
		           "paySign"   : paySign    //微信签名
		        },
		        function(res){
		        		var userId=${userId};
		            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		                //每五秒刷新订单状态
		               $.post(window.BASEPATH+'pubnum/wallet/updata',{'userId':userId,'orderId':orderNo},function(data){
		               				data = parseAjaxResult(data);
		               				if(data==1){
									 $.alert({
									  title: '充值提醒',
									  text: '充值成功，感谢您使用过来玩！',
									  onOK: function () {
									    //点击确认
									 window.location.reload();
									 }
								 });
							  }
		            	}) 
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") { 
		            	 $.post(window.BASEPATH+'pubnum/wallet/deleteOrder',{'orderId':orderNo},function(){
		               		$.alert('充值失败，请您重试！')
		            	}) 
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {
		            	
		                alert(res.err_desc); 
		            }  
		        }
		    );
		}
		function callpay(){
		    if (typeof WeixinJSBridge == "undefined"){
		        if( document.addEventListener ){
		            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		        }else if (document.attachEvent){
		            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		        }
		    }else{
		        onBridgeReady();
		    }
		} 
		
	$(document).on('click','.order',function(){
			 if($(".order").html()=="钱包详情"){
				 $(".zong").hide();
		  	     $(".mingxi").show();
		  	     $(".order").html("关闭详情");
			 }else if($(".order").html()=="关闭详情"){
				 $(".zong").show();
		  	     $(".mingxi").hide();
		  	     $(".order").html("钱包详情");
			 }
  });
	    	
	  function getreckoning(){
	  	var	url=window.BASEPATH + 'admin/userinfo/getwalletlist';
	  	$.post(url,{"userId":${userId},"page":page,"limit":15},function(data){
	  		 var html=[];
	  		for(var i=0;i<data.data.length;i++){
			          html.push('<p style="height:50px;line-height: 50px;">');
			          html.push('<span style="float:left;font-size:12px">'+data.data[i].updateTime+'</span>');
			          html.push('<span style="margin-left:-53px;font-size:12px">'+data.data[i].productname+'</span>');
			          html.push('<span style="float:right;">'+data.data[i].money/100+'元</span></p>');
				  }
			$('.mingxi').append(html.join(''));
			page++;
	  	})
	  }
	
</script>
<script>
$(function(){
    $(window).scroll(function(){
        var aa = $(window).scrollTop(); //当前滚动条滚动的距离
        var bb = $(window).height();//浏览器当前窗口可视区域高度
        var cc = $(document).height(); //浏览器当前窗口文档的高度 
      
        if(cc <= aa+bb){
            getreckoning();
        }
    })
  })
</script>


<body>
	<!-- 主页 -->
	<div class="header">
		<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
				class="icon-reorder icon-large"></span></a>
			<div class="header-content">个人</div>
		</div>
	</div> 
	<p class="order" style="padding:0 5%;height:40px;line-height: 40px;float:right;">钱包详情</p>
	 <div class="mingxi" style="width:100%;height:600px;overflow:hidden;overflow-y:auto;text-align: center;padding:0 5% 0 5%;display: none;">
	    <p style="width:90%;height:50px;line-height: 50px;border-bottom:1px solid black;position: absolute;background:#fbfbfb ">
	    <span style="float:left;">时间</span>
	    <span style="">商品</span>
	    <span style="float:right;">金额</span>
	    </p> 
	    <p style="height:50px;"></p>
	 </div>
	
	
	<div class="zong">
		<div id="page" style="text-align:center;margin-top:30%;">
		
		<img class="jinbi" alt="" src="lib/images/jinbi.png">
		<p class="lingqian">我的零钱</p>
		<h1 class="money"></h1>
		<a href="javascript:;" onclick="invest()" class="weui-btn weui-btn_primary" style="width: 60%"><input class="btns" style="z-index:111;height:100%;width:100%;left:0;border:none;outline: none;position: absolute;background: rgba(0,0,0,0);">充值</a>
		<a href="javascript:;" onclick="Withdraw()" class="weui-btn weui-btn_warn" style="width: 60%"><input class="btns" style="z-index:111;height:100%;width:100%;left:0;border:none;outline: none;position: absolute;background: rgba(0,0,0,0);">提现</a>
		
		
	</div>
</div>	
</body>


</html>