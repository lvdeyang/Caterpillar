<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="zh-cmn-Hans">
<head>

    <!-- 声明文档使用的字符编码 -->
    <meta charset='utf-8'>
    <!-- 优先使用 IE 最新版本和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!-- 页面描述 -->
    <meta name="description" content=""/>
    <!-- 页面关键词 -->
    <meta name="keywords" content=""/>
    <!-- 网页作者 -->
    <meta name="author" content="name, email@gmail.com"/>
    <!-- 搜索引擎抓取 -->
    <meta name="robots" content="index,follow"/>
    <!-- 为移动设备添加 viewport -->
    <meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <!-- `width=device-width` 会导致 iPhone 5 添加到主屏后以 WebApp 全屏模式打开页面时出现黑边 http://bigc.at/ios-webapp-viewport-meta.orz -->

    <!-- iOS 设备 begin -->
    <meta name="apple-mobile-web-app-title" content="标题">
    <!-- 添加到主屏后的标题（iOS 6 新增） -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->

    <meta name="apple-itunes-app" content="app-id=myAppStoreID, affiliate-data=myAffiliateData, app-argument=myURL">
    <!-- 添加智能 App 广告条 Smart App Banner（iOS 6+ Safari） -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <!-- 设置苹果工具栏颜色 -->
    <meta name="format-detection" content="telphone=no, email=no"/>
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

	<title>等待接单</title>

    <!-- 公共样式引用 -->
	<jsp:include page="../../../../mobile/commons/jsp/style.jsp"></jsp:include>
    
    <style type="text/css">
        a{cursor:pointer!important;}
        a, a:link, a:active, a:visited, a:hover{color:inherit; text-decoration:none;}
        
        html, body{width:100%; min-height:100%; background-color:#fff; position: relative; -webkit-text-size-adjust:none; background-color:#fbfbfb;}
        *{box-sizing:border-box;}
        .z-depth-1-bottom{box-shadow:0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12), 0 1px 5px 0 rgba(0,0,0,.2) !important;}
        .z-depth-1-right{box-shadow:2px 0 2px 0 rgba(0,0,0,.14), 3px 0 1px -2px rgba(0,0,0,.12), 1px 0 5px 0 rgba(0,0,0,.2) !important;}
        .wrapper{height:100%; width:100%; position:relative;}
    </style>
	
</head>

<body>
	<!-- 数据信息 -->
	<input type="hidden" value="${order.id}" id="order-id" />
	<input type="hidden" value="${mobile}" id="mobile" />
	
	<!-- 主页 -->
	<div id="page">
		<header style="padding:20px 0;">
	        <h1 style="text-align:center; font-size:26px; color:#3cc51f; font-weight:400; margin:0 15%;">当前订单</h1>
	    </header>
    	<div class="weui-form-preview">
		    <div class="weui-form-preview__hd">
			    <label class="weui-form-preview__label">约车金额</label>
			    <em class="weui-form-preview__value">¥${order.price}</em>
		    </div>
		    <div class="weui-form-preview__bd">
		    	<div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">加钱</label>
			        <span class="weui-form-preview__value">¥${order.addMoney}</span>
			    </div>
			    <div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">路线</label>
			        <span class="weui-form-preview__value">${order.departureName} - ${order.destinationName}</span>
			    </div>
			    <div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">出发时间</label>
			        <span class="weui-form-preview__value">${order.travelTime}</span>
			    </div>
			    <div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">打车方式</label>
			        <span class="weui-form-preview__value">${order.priceType}</span>
			    </div>
			    <div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">人数</label>
			        <span class="weui-form-preview__value">${order.passengerNum}</span>
			    </div>
			    <div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">支付状态</label>
			        <span class="weui-form-preview__value">${order.payStatus}</span>
			    </div>
			    <div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">司机电话</label>
			        <span class="weui-form-preview__value">${driverMobile}</span>
			    </div>
			    <div class="weui-form-preview__item">
			        <label class="weui-form-preview__label">留言</label>
			        <span class="weui-form-preview__value">${order.remark}</span>
			    </div>
			</div>
			<div class="weui-form-preview__ft">
				<form action="<%=basePath %>order/visitor/cancel/order/${order.id}" method="POST" id="cancel-order-form"></form>
			    <button class="weui-form-preview__btn weui-form-preview__btn_default" style="display:none" id="order-cancel">撤销</button>
			    <button class="weui-form-preview__btn weui-form-preview__btn_primary" style="display:none" id="order-pay">支付</button>
			</div>
		</div>
	</div>
	
</body>

<!-- 公共脚本引入 -->
<jsp:include page="../../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script src="<%=basePath %>mobile/lib/canvas.sonic-v0.2/canvas.sonic.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

<script type="text/javascript">
	$(function(){
	    var path='<%=basePath %>'; 
		var orderId = $('#order-id').val();
		var _uri = '';
		var takeStatus='${order.takingStatus}';
		if(takeStatus=='SUCCESS'){
		   $('#order-pay').show();
		}
		function getDriveStatus(){
			_uri = window.BASEPATH + 'order/query/taking/status/' + orderId;
				$.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data == -1) return;
					if(data === 'SUCCESS'){
						clearInterval(interval1);
						$('#order-pay').show();
						$('#order-cancel').hide();
					}else{
					    $('#order-pay').hide();
						$('#order-cancel').show();
					}
			});
		
		}
		getDriveStatus();
		var interval1 = setInterval(function(){
			getDriveStatus();
		}, 5000);
		
		var prepay_id;
		var paySign;
		var appId;
		var timeStamp;
		var nonceStr;
		var packageStr;
		var signType;
		var orderNo;	
		
			
		function payPublic(){
			$.get(path+"order/prev/pay/"+orderId, null, function(data){
				prepay_id = data.prepay_id;
		        paySign = data.paySign;
		        appId = data.appId;
		        timeStamp = data.timeStamp;
		        nonceStr = data.nonceStr;
		        packageStr = data.packageStr;
		        signType = data.signType;
		        orderNo = data.orderNo;
		        callpay();
			});
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
		            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		                alert("交易成功");
		                //每五秒刷新订单状态
						var interval = setInterval(function(){
							_uri = window.BASEPATH + 'order/query/pay/status/' + orderId;
							$.get(_uri, null, function(data){
								data = parseAjaxResult(data);
								if(data == -1) return;
								if(data === 'PAYED'){
									clearInterval(interval);
									window.location = window.BASEPATH + 'visitor/index/' + mobile;
								}
							});
						}, 2000);
		                
		                
		                //window.location.href="${base}/test/paySuccess";
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		                alert("交易取消");  
		                //window.location.href="${base}/test/cancel";
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert("支付失败"); 
		                //window.location.href="${base}/test/fail";
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
		
		
		
		//解析ajax返回
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
		};
		
		$('#order-cancel').on('click', function(){
			$.confirm('是否要撤销当前订单？', function(){
				$('#cancel-order-form')[0].submit();
			});
		});
		
		$('#order-pay').on('click', function(){
			
			payPublic();
			
		});

	});
</script>

</html>