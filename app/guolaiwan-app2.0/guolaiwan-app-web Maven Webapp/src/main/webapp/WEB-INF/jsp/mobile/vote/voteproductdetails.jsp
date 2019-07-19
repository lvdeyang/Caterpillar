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
<title>产品详情</title>
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
	list-style: none;
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
	height: auto;
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
.fuceng{
    position: fixed;
    width:100%;
    height:100%;
    left:0;
    top: 0;
    background-color:rgba(0,0,0,0.6);
    z-index: 10000;

}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath()%>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script>
$(function(){
getRecomment();
})



function getRecomment() {
		var _uriRecomment = window.BASEPATH + 'phoneApp/productInfo?productId=${product.id}&userId=${userId}';

		$.get(_uriRecomment, null, function(data) {
			data = parseAjaxResult(data);
			if (data === -1) return;
			if (data) {
				var html = [];
				var pics = data.product.productMorePic.split(',');
				for (var i = 0; i < pics.length; i++) {
					html.push('<div style="height:200px;" class="swiper-slide"><img style="height:200px" src="' + pics[i] + '" alt=""></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
					loop : true,
					autoplay : 3000
				});
			}
		});
}

$(document).on('click', '#zhifu', function(){ 
  $(".fuceng").fadeIn();
   $(".tanchuang").fadeIn();
});

$(document).on('click', '.fuceng', function(){ 
  $(".fuceng").fadeOut();
  $(".tanchuang").fadeOut();
});

$(document).on('click','#paynow',function(){
			$(".fuceng").fadeOut();
   			$(".tanchuang").fadeOut();
		    if($('#paytext').val()==''){
			   $.toast("请输入金额", "forbidden");
			   return false;
			}
			dopay(0);
	  }); 
	  
	  
	  function dopay(mailId){
	  	
 		    var _uriAdd = window.BASEPATH + 'phoneApp/goToPay';
			var params={};
			params.userId=${userId};
			params.merchantId=${product.productMerchantID};
			params.source="PUBLICADDRESS";
			params.payMoney=1;
			params.addressId=mailId;
			$.confirm("确定支付？", function() {
				$.post(_uriAdd, $.toJSON(params), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					 $.modal({
						  title: "付款方式",
						  buttons: [
						    { text: "余额支付", onClick: function(){ 
								    payByWallet(data.id);
						    } },
						    { text: "微信支付", onClick: function(){ 
								    payPublic(data.id);
						    } },
						    { text: "取消", className: "default", onClick: function(){ } },
						  ]
						}); 
				});			  
			  });	 	
		}
		 
	  
	  function deleteorder(orderId)
	  {
	  	var params={};
	  	params.orderId = orderId;
	  	$.post(window.BASEPATH +'phoneApp/deleteorder', $.toJSON(params), function(data){
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
		
			
		function payPublic(orderId){
			$.get(window.BASEPATH +"pubnum/prev/pay/"+orderId, null, function(data){
				prepay_id = data.prepay_id;
		        paySign = data.paySign;
		        appId = data.appId;
		        timeStamp = data.timeStamp;
		        nonceStr = data.nonceStr;
		        packageStr = data.packageStr;
		        signType = data.signType;
		        orderNo = data.orderNo;
		        callpay(orderId);
			});
		}
		
		function payByWallet(orderId){
			var url=window.BASEPATH+'pubnum/wallet/walletbuy';
			var userId=${userId};
			$.post(url,{'orderId':orderId,'userId':userId},function(data){
						data = parseAjaxResult(data);
				if(data==1){
						$.get(window.BASEPATH +"pubnum/order/status?orderId="+orderId, null, function(data){
						    if(data.data=="PAYSUCCESS"){				
						       location.href=window.BASEPATH + 'business/gotopayment?orderId='+orderId+'&merchantId=${merchant.id}';
						    }
						});
				}else{
					$.alert('您的余额不足！');
				}
			})
		}
	
		function onBridgeReady(orderId){
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
		            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		                $.confirm("交易成功");
		                //每五秒刷新订单状态
						
		                setInterval(function(){ 
                                $.get(window.BASEPATH +"pubnum/order/status?orderId="+orderNo, null, function(data){
								    
								    if(data.data=="TESTED"||data.data=='PAYSUCCESS'){
								       location.href=window.BASEPATH + 'business/gotopayment?orderId='+orderId+'&merchantId=${merchant.id}';
								    }
								});
                        }, 1000);
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {
					 	deleteorder(orderId);
		                alert("交易取消");  
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
					 	deleteorder(orderId);
		                alert(res.err_desc); 
		            }  
		        }
		    );
		}
		function callpay(orderId){
		    if (typeof WeixinJSBridge == "undefined"){
		        if( document.addEventListener ){
		            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		        }else if (document.attachEvent){
		            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		        }
		    }else{
		        onBridgeReady(orderId);
		    }
		}
</script>
<body>
			<!-- 主页 -->
		 <div class="header">
			<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
		</div>
		   
		 <div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
	</div>  
		       
		  <div style="width:100%;height:auto;">
		    <div style="width:90%;height:auto;background: #fff;margin: auto;padding:0 5% 20px;border-radius:10px;color:#6E7272;">
		       <p style="height:40px;line-height: 40px;font-size:16px;font-weight: bold;">菜品介绍</p>
		       ${product.productIntroduce}
		    </div>
		  </div>  
		  
		<div class='fuceng' style="display: none;">
	
		</div>
	  <div class="tanchuang" style="z-index:11111;width:100%;height:auto;display: none;padding:0 0 50px 0;text-align: center;background: #fff;position: fixed;bottom:0;border-radius:10px; ">
	   <p style="width:96%;margin:0 auto;height:50px;line-height: 50px;font-size: 18px;font-weight: bold;border-bottom:1px solid #D3D3D3;">${product.productName}</p>
	    <p style="margin:80px 0 50px 0;text-align: center;font-weight:bold;">支付金额（元）<input id="paytext" style="height:30px;border-radius:8px;padding:0 5px;border:2px solid #F25566;outline: none;"></p>
	    <button id="paynow" style="height:40px;color:#fff;background:#F25566;width:70%;border:none;outline:none;border-radius:10px;font-size:18px;">立即支付</button>
	  </div>
		
		 <button id="zhifu" style="color:#fff;background: #F25566;font-weight: bold;font-size:18px;width:70%;border-radius:18px;border:none;outline:none;position: fixed;bottom:30px;left:15%;">到点支付</button>
         <p style="height:35px;"></p>
</body>
 




</html>