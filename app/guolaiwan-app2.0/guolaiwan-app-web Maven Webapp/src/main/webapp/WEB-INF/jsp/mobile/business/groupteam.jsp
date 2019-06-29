<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>发起拼团</title>
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
	background-color: #fff !important; 
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

 .youxuan-in p{
  margin-left:3%;
 }
 
.renshu ul li{
float:left;
margin:10px 10px;
width:40px;
height:40px;
border-radius:50%;
overflow: hidden;
}
.renshu ul li img{
width:40px;
height:40px;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="text/javascript">



   
	$(function() {
	var iscollect;
	  window.BASEPATH = '<%=basePath%>';
	  var comCode='${comCode}';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
	
	    var share={};
	    function initSharewx(){
	        var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	  
	    	var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			    $.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					    
						share=data;
						doScanShare();
					}
					
			});
	    
	    }
	    
	    
	    
	    function doScanShare(){
            wx.config({
	            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            appId : share.appId, // 必填，公众号的唯一标识
	            timestamp : share.timestamp, // 必填，生成签名的时间戳
	            nonceStr : share.nonceStr, // 必填，生成签名的随机串
	            signature : share.signature,// 必填，签名，见附录1
	            jsApiList : ['checkJsApi', 'onMenuShareTimeline' , 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
	        wx.ready(function() {
	
	               
	            wx.onMenuShareTimeline({
                            title: '快来啊，就差几个人了，外人我不告诉他~', // 分享标题
                            link: 'http://<%=weburl%>/business/groupteam?teamId=${team.id}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
                            success: function () {
                               	
                            }
                        });
	            wx.onMenuShareAppMessage({
					title : '快来啊，就差几个人了，外人我不告诉他~', // 分享标题
					desc : '<%=weburl%>，联系电话:0315-6681288/6686299', // 分享描述
					link : 'http://<%=weburl%>/business/groupteam?teamId=${team.id}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
					success : function() {}
				});
	            
	       });
        }
	    
	    function doScan(){
            wx.config({
	            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            appId : paras.appId, // 必填，公众号的唯一标识
	            timestamp : paras.timestamp, // 必填，生成签名的时间戳
	            nonceStr : paras.nonceStr, // 必填，生成签名的随机串
	            signature : paras.signature,// 必填，签名，见附录1
	            jsApiList : ['checkJsApi', 'scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
	        wx.ready(function() {
	
	               wx.scanQRCode({ 
		                needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		                scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		                success: function (res) {

		                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		                    location.href=window.BASEPATH + 'pubnum/product/index/payinshop/'+result;
		                  
		                }
		            });
	            
	            
	            
	       });
        }
	   
timer();
getAllTeamMan();   
	

	
	});
</script>
<script>
function timer() {
	var begintime=new Date("${team.updateTime}").getTime();
	var grouptime=${groupBuyPO.grouptime}*60*60*1000-1000;
	var newtime=new Date().getTime();
	var intDiff =parseInt((grouptime-(newtime-begintime))/1000);
   /*倒计时  */
  window.setInterval(function () {
    var day = 0,
      hour = 0,
      minute = 0,
      second = 0; //时间默认值
    if (intDiff > 0) {
      day = Math.floor(intDiff / (60 * 60 * 24));
      hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
      minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
      second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
     if(hour<10){hour="0"+hour;}
	if(minute<10){minute="0"+minute;}
	if(second<10){second="0"+second;}
    $("#times").html(hour + ":" + minute + ":" + second);
    intDiff--;
  }, 1000);
}

function dobuy(){
		    var productId=${product.id};
		    var userId=${userId};
            $.closePopup();
			var chkStockUrl=window.BASEPATH + 'pubnum/stock/check?proId='+productId+'&count=1';
			$.get(chkStockUrl, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
				if(data.stock==0){
				   $.toast("抱歉，库存不足", "forbidden");
				   return;
				}	
				var _uriPay = window.BASEPATH + 'phoneApp/grouporder/add';
				$.post(_uriPay,{"productId":productId,"userId":userId}, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					$.modal({
						  title: "付款方式",
						  buttons: [
						    { text: "余额支付", onClick: function(){ 
						    	$.confirm("确定支付？", function() {
								    payByWallet(data.orderId);
								  }, function() {});
						    } },
						    { text: "微信支付", onClick: function(){ 
							    $.confirm("确定支付？", function() {
								    payPublic(data.orderId);
								  }, function() {});
						    } },
						    { text: "取消", className: "default", onClick: function(){ } },
						  ]
						});
				});
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
						    $.post(window.BASEPATH+"phoneApp/groupteam",{"orderId":orderNo,"userId":${userId},"type":2},function(){
						       location.href=window.BASEPATH +"pubnum/order/info?orderId="+orderId;
		                		})
						    }
						});
				}else{
					$.alert('您的余额不足！');
				}
                   
			})
		}
		
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
		        callpay(orderNo);
			});
		}
		
	
		function onBridgeReady(orderNo){
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
		                
		                setInterval(function(){ 
                                $.get(window.BASEPATH +"pubnum/order/status?orderId="+orderNo, null, function(data){
								    if(data.data=="PAYSUCCESS"){
				                		$.post(window.BASEPATH+"phoneApp/groupteam",{"orderId":orderNo,"userId":${userId},"type":2},function(){
									       location.href=window.BASEPATH +"pubnum/order/info?orderId="+orderNo;
				                		})
								    }
								});
                        }, 1000);
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		                alert("交易取消");  
	
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert(res.err_desc); 

		            }  
		        }
		    );
		}
		function callpay(orderNo){
		    if (typeof WeixinJSBridge == "undefined"){
		        if( document.addEventListener ){
		            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		        }else if (document.attachEvent){
		            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		        }
		    }else{
		        onBridgeReady(orderNo);
		    }
		}
		
		function getAllTeamMan(){
        var url = window.BASEPATH + 'business/getteamman';
		var captain=${team.userid};
		var teamId=${team.id};
		$.post(url, {"captain":captain,"teamId":teamId}, function(data){
			if(data === -1) return;
			if(data && data.length>0){
			    var html=[];
				for(var i=0; i<data.length; i++){
					 html.push('<li style="border:1px solid #989898;"><img src="'+data[i].userheadimg+'"/></li>');
				}
			    $('.teamman').append(html.join(''));
			}
		});
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
	   

         <!-- 发起拼团  -->
         <div style="width:100%;height:20px;background:#EEEEEE;"></div> 
         <div style="height:100px;weight:100%;text-align:center;vertical-align:middle;position: relative;">
          <img style="width:60px;height:60px;border-radius:50%;float:left;margin:20px 0 20px 8%;display: inline-block;" src="${team.userheadimg}"/>
          <span style="font-weight:bold;position: absolute;top:30px;left:30%">${team.usernickname}</span>
          <p style="color:#949494;font-size:12px;position: absolute;top:60px;left:30%">发起了<span>${groupBuyPO.groupnum}</span>人团</p>
         </div>
          <div style="width:92%;height:180px;border:1px solid #CECECE;margin:0 auto;border-radius:6px;position: relative;">
            <img style="height:140px;margin:20px 0px 20px 10px; width:55%;" src="http://www.guolaiwan.net/file/${product.productShowPic}"/>
            <p style="position: absolute;font-weight:bold;color:black;top:30px;font-size:12px;right:7%;">${product.productName}</p>
            <p style="position: absolute;font-weight:bold;color:#E50012;top:110px;font-size:12px;right:7%;"><span>${groupBuyPO.groupnum}</span>人拼购价：￥<span><fmt:formatNumber type="number" value="${groupBuyPO.groupprice/100}" maxFractionDigits="2"/></span></p>
            <p style="position: absolute;font-weight:bold;color:#989898;top:140px;font-size:12px;right:7%;">单买价：￥<span><fmt:formatNumber type="number" value="${product.productPrice/100}" maxFractionDigits="2"/></span></p>
          </div>
          <div style="height:90px;width:100%;line-height: 90px;position:relative;">
             <p style="width:100%;text-align: center;font-weight:bold;font-size:14px;letter-spacing:2px">还差<span style="color:#FF4900;font-size:18px;">${groupBuyPO.groupnum-team.teamnum}人</span>拼团成功，剩余时间<span id="times" style="color:#fff;letter-spacing:7px;background:#FF4900;padding:10px 0;border-radius:6px;font-size:16px;text-align: center;margin-left:10px;">00:00:00</span></p>
          </div>
          <div class="renshu" style="width:100%;height:90px;position: relative;text-align: center;">
          <ul class="teamman" style="margin:0 auto;  display: inline-block;overflow: auto;position: relative;width:80%;">
           <button style="color:#fff;padding:0 6px;top:0px;left:10px;background:#FF4900;border:none;outline:none;width:auto;height:20px;position: absolute;border-radius:12px;">团长</button>
           <li style="border:1px solid #FF4900;"><img src="${team.userheadimg}"/></li>
          </ul>
           <p style="color:#FD7C13;margin:0;">邀请好友&nbsp&nbsp&nbsp&nbsp>></p>
            
          </div>
          <div style="margin:0 auto;text-align: center;width:100%;position: fixed;bottom:10px;">
          <button onclick="dobuy()" style="width:90%;height:40px;color:#fff;background:#FF4900;border:none;outline:none;border-radius:12px;">确定</button>
          </div>
          <div style="height:40px;width:100%;"></div>
</body>




</html>