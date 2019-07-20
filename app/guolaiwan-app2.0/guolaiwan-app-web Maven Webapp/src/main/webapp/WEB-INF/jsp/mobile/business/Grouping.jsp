<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
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
<title>拼团</title>
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
	min-height: auto;
	background-color: #EEEEEE !important;
	position: relative;
	-webkit-text-size-adjust: none;
	background-color: #fff !important;
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
	padding: 0;
	margin: 0;
	height: 200px;
}

.swiper-container img {
	display: block;
	width: 100%;
}

.weui-navbar {
	display: none !important;
}

.inp::-webkit-input-placeholder {
	text-align: center;
}

.jieshao p span {
	font-size: 14px;
}

.fukuan {
	background: #FF3D00;
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
.tanchuang input{
width:96%;
padding:0 0 0 30%;
height:50px;
border:none;
outline:none;
border-bottom:1px solid #D3D3D3;

}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css" />
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="text/javascript">
	$(function() {
		var iscollect;
		window.BASEPATH = '<%=basePath%>';
		var comCode = '${comCode}';
		var parseAjaxResult = function(data) {
			if (data.status !== 200) {
				$.toptip('data.message', 'error');
				return -1;
			} else {
				return data.data;
			}
		};

		getRecomment()

		var share = {};
		function initSharewx() {
			var reqUrl = location.href.split('#')[0].replace(/&/g, "FISH");

			var _uri = window.BASEPATH + 'pubnum/prev/scan?url=' + reqUrl;
			$.get(_uri, null, function(data) {
				data = parseAjaxResult(data);
				if (data === -1) return;
				if (data) {

					share = data;
					doScanShare();
				}
			});
		}

		function doScanShare() {
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				//                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : share.appId, // 必填，公众号的唯一标识
				timestamp : share.timestamp, // 必填，生成签名的时间戳
				nonceStr : share.nonceStr, // 必填，生成签名的随机串
				signature : share.signature, // 必填，签名，见附录1
				jsApiList : [ 'checkJsApi', 'onMenuShareTimeline', 'onMenuShareAppMessage' ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function() {


				wx.onMenuShareTimeline({
					title : '畅游华夏，尽在过来玩', // 分享标题
					link : 'http://<%=weburl%>/guolaiwan/pubnum/index', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
					success : function() {}
				});
				wx.onMenuShareAppMessage({
					title : '畅游华夏，尽在过来玩', // 分享标题
					desc : '<%=weburl%>，联系电话:0315-6681288/6686299', // 分享描述
					link : 'http://<%=weburl%>/guolaiwan/pubnum/index', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
					imgUrl : 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
					success : function() {}
				});

			});
		}

		function doScan() {
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				//                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : paras.appId, // 必填，公众号的唯一标识
				timestamp : paras.timestamp, // 必填，生成签名的时间戳
				nonceStr : paras.nonceStr, // 必填，生成签名的随机串
				signature : paras.signature, // 必填，签名，见附录1
				jsApiList : [ 'checkJsApi', 'scanQRCode' ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function() {

				wx.scanQRCode({
					needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
					scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
					success : function(res) {

						var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
						location.href = window.BASEPATH + 'pubnum/product/index/payinshop/' + result;

					}
				});
			});
		}

		$(document).on('click', '.fukuan', function() {
			$("#startteam").popup();
		})

		$(document).on('click', '.cancel', function() {
			$.closePopup();
		})

		$(document).on('click', '.pay', function() {
			$.closePopup();
		})

		$(document).on('click', '#addAddress', function() {
			$.closePopup();
			$("#address").popup();
		})
		
		$(document).on('click', '.close', function() {
			$.closePopup();
			$("#startteam").popup();
		})
		
		$(document).on('click', '.dizhi', function(){ 
		  $(".fuceng").fadeIn();
		   $(".tanchuang").fadeIn();
		});
		
	  	$(document).on('click', '.fuceng', function(){ 
		  $(".fuceng").fadeOut();
		  $(".tanchuang").fadeOut();
		});
		
		$(document).on('click', '.quxiao', function(){ 
		  $(".fuceng").fadeOut();
		  $(".tanchuang").fadeOut();
		});
		
	});



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
</script>
<script>

function timer(times,intDiff) {
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
    $("#"+times).html(hour + ":" + minute + ":" + second);
    intDiff--;
  }, 1000);
}
 
 $(function () {
  getallteam();
  getAllAddr();
});

 function getallteam(){
 	var groupnum=${groupBuyPO.groupnum};
     var _uricoms = window.BASEPATH + '/business/getallteam?productId=${product.id}';	
     $.get(_uricoms, null, function(data){
         for(var i=0;i<data.length;i++){
         		var teamnum=groupnum-data[i].teamnum;
  				var times='times'+data[i].id;
	 			var begintime=data[i].updateTime;
	 			var grouptime=data[i].grouptime*60*60*1000-1000;
	 			var newtime=new Date().getTime();
	 			var intDiff =parseInt((grouptime-(newtime-begintime))/1000);
         		var html=[];
         		if(data[i].productid==${product.id}&&data[i].iscaptain==1&&intDiff>0&&data[i].teamnum<${groupBuyPO.groupnum}){
		           html.push('<div style="height:100px;weight:100%;text-align:center;border-bottom:1px solid #A6A6A6;vertical-align:middle;position: relative;">');
		           html.push(' <img style="width:60px;height:60px;border-radius:50%;float:left;margin:20px 0 20px 8%;display: inline-block;" src="'+data[i].userheadimg+'"/>');
		           html.push(' <span style="margin-left:10px;font-weight:bold;line-height: 100px;float:left;white-space: nowrap;text-overflow:ellipsis;overflow: hidden;width:70px;">'+data[i].usernickname+'</span>');
		           html.push(' <span style="font-weight:bold;margin-left:-10px;line-height: 100px;">还差<span style="color:#F46837;">'+teamnum+'人</span>拼团成功</span>');
		           html.push(' <button id="'+data[i].id+'" style="background:#FF4900;width:50px;height:30px;margin:35px 5% 35px 0;border:none;outline: none;border-radius:6px;color:#fff;float:right;" onclick="gototeam(this.id)">去拼单</button>');
		           html.push('<p style="color:#949494;font-size:12px;position: absolute;top:60px;left:55%;margin-left:-30px">剩余时间<span id="times'+data[i].id+'">00：00：00</span></p>');
		           html.push('	</div>');
		           }
		 		$('.allteam').append(html.join(''));
		 			
		 			timer(times,intDiff);
	     	}
	 });
}

	function getAllAddr(){
        var _uriAddress = window.BASEPATH + 'phoneApp/address/list?userId=${userId}';
		$.get(_uriAddress, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			    var html=[];
			if(data && data.length>0){
				for(var i=0; i<data.length; i++){
				     var chkattr='';
				     if(data[i].defaultAddress==1){
				         chkattr='checked="checked"';
				     }
			        html.push('<div style="width:100%;height:auto;background: #fff;margin:5px 0;position: relative;  ">');
					html.push('<p style="width:100%;height:30px;line-height:30px;padding:0 4% 0 15%;margin:0 auto;"><span style="text-align: left;"><span>'+data[i].consigneeName+'</span></span><span style="float:right;margin-right:8%;"><span>'+data[i].consigneePhone+'</span></span></p>');
					html.push('<p style="width:100%;height:30px;line-height:30px;padding:0 4% 0 15%;margin:0 auto;"><span>'+data[i].consigneeAddress+'</span></p>');
					html.push('<input onchange="addresschange(this.id)" id="'+data[i].id+'" '+chkattr+' type="radio" name="address" value="'+data[i].id+'" style="position: absolute;top:20px;left:5%;" />');
					html.push('<span onclick="deleteaddress(this.id)" id="'+data[i].id+'" style="position: absolute;right:5%;font-size:16px;font-weight: bold;top:25px;"><i class="weui-icon-cancel"></i></span>');
					html.push('</div>  ');
					}
				$('#addressList').children().remove();
			    $('#addressList').append(html.join(''));
			}else{
					html.push('<p style="color:#4C4C4C;text-align:center;font-weight:bold;">暂无默认地址</p>');
				$('#addressList').children().remove();
			    $('#addressList').append(html.join(''));
			}
		});
     }
     
     function deleteaddress(id){
     	var url=window.BASEPATH + 'business/deleteaddress';
     	$.post(url,{"addressId":id},function(data){
     		$.alert("删除地址成功");
     		getAllAddr();
     	})
     }

	function addresschange(id){
     var url=window.BASEPATH + 'business/changeaddress';
     	$.post(url,{"addressId":id,"userId":${userId}},function(data){
     		$.toast("更改地址成功", "text");
     	})
     }

     function appendaddress(){
     	var userId=${userId};
     	var username=$('.username').val();
     	var telephone=$('.telephone').val();
     	var consigneeAddress=$('.consigneeAddress').val();
     	var url=window.BASEPATH + 'business/appendaddress';
	      var re = /^[1][3,4,5,7,8][0-9]{9}$/;
	       if($(".username").val()==""||$(".consigneeAddress").val()==""){  
				  alert("请完善信息");
				  return false;
				}
			if ($(".telephone").val().search(re)) {
				       alert("请输入正确手机号");
					return false;
				}
				
			
     	$.post(url,{"userId":userId,"username":username,"telephone":telephone,"consigneeAddress":consigneeAddress},function(data){
     	    $.alert("添加成功");
     		$(".fuceng").fadeOut();
  			$(".tanchuang").fadeOut();
     		getAllAddr();
     	})
     }

		function dobuy(){
		    var productId=${product.id};
		    var userId=${userId};
		    var logisticsId=$("input[name='address']:checked").val();
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
				$.post(_uriPay,{"productId":productId,"userId":userId,"logisticsId":logisticsId}, function(data){
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
						    $.post(window.BASEPATH+"phoneApp/groupteam",{"orderId":orderId,"userId":${userId},"type":1},function(){
						       location.reload();
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
		                				$.post(window.BASEPATH+"phoneApp/groupteam",{"orderId":orderNo,"userId":${userId},"type":1},function(){
								      		 /* location.href=window.BASEPATH +"pubnum/order/info?orderId="+orderNo; */
								      		 location.reload();
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


 function gototeam(id){
 		location.href=window.BASEPATH + 'business/groupteam?teamId='+id;
 }
 
 function gotoproduct(id){
	    location.href=window.BASEPATH + 'business/getdetermineorder?id='+id;
}

function gotoprocess(){
	    location.href=window.BASEPATH + 'business/gotoprocess';
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
	<div class="content" id="content">
		<div class="swiper-container" id="headerSwiper"
			data-space-between='10' data-pagination='.swiper-pagination'
			data-autoplay="1000">
			<div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			</div>
		</div>
	</div>
	</div>

	<!--拼团  -->
	<div class=""
		style="height:100px;width:100%;border-bottom:1px solid #A6A6A6;">
		<p
			style="color:#F46837;font-size:18px;height:50px;line-height:50px;font-weight:bold;margin-left:10%;">
			￥<fmt:formatNumber type="number" value="${groupBuyPO.groupprice/100}" maxFractionDigits="2"/><span
				style="text-decoration:line-through;font-size:14px;color:#C7C7C7;" >￥<fmt:formatNumber type="number" value="${product.productPrice/100}" maxFractionDigits="2"/></span>
		</p>
		<p
			style="color:black;font-size:18px;height:50px;line-height:25px;margin-left:10%;">${product.productName}</p>
	</div>
	<div class="jieshao"
		style="height:100px;width:100%;border-bottom:1px solid #A6A6A6;position: relative;">
		<p
			style="line-height:100px;margin:0;font-weight:bold;text-align:center;">
			<span style="float:left;padding-left:10%;">①开团/参团
				&nbsp&nbsp&nbsp❯</span><span>②好友分享 &nbsp&nbsp&nbsp❯</span><span
				style="float:right;margin-right:2%;">③满员发货</span>
		</p>
		<p style="width:100%;position: absolute;top:10px;">
			<span style="margin-left:10%;">拼购玩法</span><span onclick="gotoprocess()"
				style="float:right;margin-right:2%;">详细规则❯</span>
		</p>
		<p
			style="font-size:12px;position: absolute;bottom:10px;right:0%;color:#949494;margin:0;">（不满自动退款）</p>
	</div>
	<!-- 后面动态写进来各个团 -->
	<div class="allteam"></div>

	<div
		style="background:#F56938;height:60px;width:100%;border-bottom:1px solid  rgb(230, 230, 230);border-top:1px solid  rgb(230, 230, 230);position: fixed;bottom:0;">
		<p style="height:100%;float:left;text-align:center;width:50%;line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;">
			￥<span id="zong"><fmt:formatNumber type="number" value="${product.productPrice/100}" maxFractionDigits="2"/></span>
			<span style="font-size:14px;margin-left:5%;" onclick="gotoproduct(${product.id})">原价购买</span>
		</p>
		<p class="fukuan" style="height:100%;float:right;text-align:center;width:50%;line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;">
			￥<span id="zong"><fmt:formatNumber type="number" value="${groupBuyPO.groupprice/100}" maxFractionDigits="2"/></span>
			<span style="font-size:14px;margin-left:5%;">我要开团</span>
		</p>
	</div>


	<div id="startteam" class="weui-popup__container">
		<div class="weui-popup__overlay"></div>
		<div class="weui-popup__modal">
				<p class="dizhi" style="height:50px;margin:5px 0;line-height: 50px;padding:0 5%;background: #fff;font-size:18px;font-weight: bold;"><span>添加新地址</span><span style="float:right;">></span></p>
				<div class="weui-panel__bd" id="addressList"
					style="padding-bottom:40px;"></div>

				
					<div style="background:#FF3D00;height:60px;width:100%;border-bottom:1px solid  rgb(230, 230, 230);border-top:1px solid  rgb(230, 230, 230);position: fixed;bottom:0;">
						<p
							style="background:#F56938;height:100%;float:left;text-align:center;width:50%;line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;"
							class="cancel">
							<span style="font-size:14px;margin-left:5%;">取消开团</span>
						</p>
						<p class="pay" onclick="dobuy()"
							style="height:100%;float:right;text-align:center;width:50%;line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;">
							<span style="font-size:14px;margin-left:5%;">开团支付</span>
						</p>
				</div>

		  </div>
	</div>


	<div class='fuceng' style="display: none;"></div>
	   <div class="tanchuang" style="z-index:11111;width:100%;display:none;height:auto;padding:0 0 50px 0;text-align: center;background: #fff;position: fixed;bottom:0; ">
	   <p style="width:96%;margin:0 auto;height:50px;line-height: 50px;font-size: 18px;font-weight: bold;border-bottom:1px solid #D3D3D3;">编辑收货地址</p>
	   <input type="text" class="username" placeholder="请您输入收货人姓名" style="">
	   <p style="position: absolute;top:65px;left:5%;font-weight: bold;">收货人姓名</p>
	   <input type="text" class="telephone" placeholder="请您输入收货人手机号" style="">
	   <p style="position: absolute;top:115px;left:5%;font-weight: bold;">手机号</p>
	   <input type="text" class="consigneeAddress" placeholder="请您输入详细地址" style="">
	   <p style="position: absolute;top:165px;left:5%;font-weight: bold;">详细地址</p>
	   <!-- 空白 -->
	   <p style="height:50px;"></p>
	   <div style="width:100%;height:60px;position: fixed;bottom:0;background: #fff;">
        <p class="quxiao" style="line-height: 60px;color:#EB6E1E;width:50%;display: inline-block;text-align: center;font-size:16px;font-weight: bold;">取消</p>
        <p onclick="appendaddress()" style="line-height: 60px;background:#EB6E1E;width:50%;color:#fff;float:right;display: inline-block;text-align: center;font-size:16px;font-weight: bold;">保存</p>
        </div>
	
	<div style="height:60px;width:100%;"></div>
	
</body>




</html>