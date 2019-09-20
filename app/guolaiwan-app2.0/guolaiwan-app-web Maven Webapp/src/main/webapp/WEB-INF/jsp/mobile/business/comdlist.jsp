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
<title>采摘商品列表</title>
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

.gotop {
   position: fixed;
   right: 20px;
   bottom: 50px;
   display: block;
   width: 50px;
   height: 50px;
   opacity: 0.8;
   z-index:1111;
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

<script type="text/javascript">
	$(function() {
	consoleLog();
	  getRecomment();
	  getAllProduct();
	  window.BASEPATH = '<%=basePath%>';
	   
	});
	 //获取手机当前的经纬度
     function consoleLog(){
	    getloca();
	    var loca = {};
		function getloca() {
			var reqUrl = location.href.split('#')[0].replace(/&/g, "FISH");
			var _uri = window.BASEPATH + 'pubnum/prev/scan?url=' + reqUrl;
			$.get(_uri, null, function(data) {
				data = parseAjaxResult(data);
				if (data === -1) return;
				if (data) {
					loca = data;
					getLoation();
				}
			});
		}
    
 	function getLoation() {
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				//                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : loca.appId, // 必填，公众号的唯一标识
				timestamp : loca.timestamp, // 必填，生成签名的时间戳
				nonceStr : loca.nonceStr, // 必填，生成签名的随机串
				signature : loca.signature, // 必填，签名，见附录1
				jsApiList : [ 'checkJsApi', 'getLocation' ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
			wx.ready(function() {
				wx.getLocation({
					type : 'gcj02',
					success : function(res) {
						var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90  
						var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。  
						var speed = res.speed; // 速度，以米/每秒计  
						var accuracy = res.accuracy; // 位置精度  
						getCity(latitude, longitude);
						
					},
					cancel : function(e) {
						//这个地方是用户拒绝获取地理位置  
						alert("请打开GPS定位,");
					}
				});
				wx.error(function(res) {});
			});
		}
		
		  function getCity(latitude, longitude) { //通过经纬度   获取高德位置
			latitudes = (parseFloat(latitude)).toFixed(5); //保留经纬度后5位
			longitudes = (parseFloat(longitude)).toFixed(5);
			getInfo();
		}    

} 
		function getInfo(){
	    var url_s =  window.BASEPATH + 'business/gotodistance?merchantId=${merchantId}&latitude='+latitudes+'&longitude='+longitudes;
	    $.get(url_s,null,function(msg){
	    $("#dintance").text("距离"+msg+"km");
	    })
	}
	function getRecomment(){
	      var _uriMerchantInfo = window.BASEPATH+'phoneApp/merchantInfo?merchantID=${merchantId}&userId=${userId}';
		$.get(_uriMerchantInfo, null, function(data){
			data = parseAjaxResult(data);
			merchantName = data.shopName + '-过来玩';
			merchantPic = 'http://<%=weburl%>/file/' + data.shopHeading;
			merchantUrl = window.location.href;
			if(data === -1) return;
			if(data){
			    var html=[];
			    var pics=data.shopMpic.split(',');
				for(var i=0; i<pics.length; i++){
					var str = pics[i].split('.');
					if(str[3]!="mp4"&&str[3]!="MP4"){ 
					html.push('<div class="swiper-slide" style="height:200px;"><img class="exampleImg" style="height:200px;" id="imgTest" src="'+pics[i]+'" alt=""></div>');
					}else{
					html.push('<div class="swiper-slide" style="height:200px;"><video class="exampleImg" style="height:200px;width:100%;" src="'+pics[i]+'" controls="controls" ></div>');
					}
				}
			    $('.header-content').html(data.shopName);
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
			    }
			    });
	  }
	
	
	
	function getAllProduct(){
			var url="<%=basePath%>business/getproduct";
           $.post(url,{"merchantId":${merchantId}},function(data){
           	var html=[];
           	if(data.length==0){
           		html.push('<p style="text-align: center;position: fixed;bottom:5px;left:50%;margin-left:-28px;color:#858585;">暂无数据</p>');
           	}else{
				for(var i=0; i<data.length; i++){
					    html.push('<a onclick="gotodetailspage('+data[i].id+')"><div style="width:48%;height:auto;overflow: hidden;border-radius:10px;text-align:center;float:left;margin:5px 0 0 1.5%;">');
					    html.push('<img style="width:100%;border-radius:10px;" src="http://www.guolaiwan.net/file'+data[i].productShowPic+'"/>');
					    html.push('<p style="margin:0 ;height:30px;line-height: 30px;text-align:left;width:90%;white-space: nowrap;overflow:hidden;text-overflow:ellipsis; ">'+data[i].productName+'</p>');
					    html.push('<p style="margin:0 ;height:20px;line-height: 20px;color:#EA6C1B;text-align:left;">￥<span>'+data[i].productPrice+'</span><span style="text-decoration: line-through;color:#787878;margin-left:10px;font-size:12px;">￥'+data[i].productOldPrice+'</span></p>');
					    html.push('<button style="border-radius:10px;font-size:12px;color:#fff;background:#EA6C1B;padding:0px 25px;border:none;outline:none;margin:0 auto;">立即购买</button>');
					    html.push('</div></a>');
					}
			}
	    	$('.productlist').append(html.join(''));
           })
	}

	
   function gotobusinessdetails(){
   		location.href=window.BASEPATH + 'business/gotobusinessdetails?merchantId=${merchant.id}';
   }
   
  function gotodetailspage(id){
   		location.href=window.BASEPATH + 'business/gotodetailspage?productId='+id;
   }
</script>
<script>
$(function(){
	
	var pingfen=(${pingfen}+46)/10;
	if(pingfen>5)pingfen=5;
	$('.pingfen').html(pingfen+"分");
    $(window).scroll(function(){
        var aa = $(window).scrollTop(); //当前滚动条滚动的距离
        var bb = $(window).height();//浏览器当前窗口可视区域高度
        var cc = $(document).height(); //浏览器当前窗口文档的高度 
      
        if(cc <= aa+bb){
        }
    })
  })
 /*返回顶部  */
  $(function(){
	$(window).scroll(function(){
		if($(window).scrollTop()>100){
			$(".gotop").fadeIn(400);	
		}
		else{
			$(".gotop").fadeOut(400);
		}
	});
	$(".gotop").click(function(event){
        event.preventDefault();
		$('html,body').animate({'scrollTop':0},500);
        return false;
	});
}); 


$(document).on('click', '#zhifu', function(){ 
  $(".fuceng").fadeIn();
   $(".tanchuang").fadeIn();
});
  $(document).on('click', '.fuceng', function(){ 
  $(".fuceng").fadeOut();
  $(".tanchuang").fadeOut();
}); 
       
</script>

<script>
$(function() {
	  
	  var parseAjaxResult = function(data){
	  
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
		
      var _uriRecomment = window.BASEPATH + 'phoneApp/getmerchantid?merchantId=${merchantId}';
		
		$.get(_uriRecomment, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data){
			    $('.header-content').html(data.shopName);
			}
		});		
		
		$('.header-content').html(${shopName});	
		$("#paytext").keyup(function(){
		    $(this).val($(this).val().replace( /[^0-9.]/g,''));
			}).bind("paste",function(){
	    $(this).val($(this).val().replace( /[^0-9.]/g,''));
	})
			
		$(document).on('click','#paynow',function(){
			$(".fuceng").fadeOut();
   			$(".tanchuang").fadeOut();
		    if($('#paytext').val()==''){
			   $.toast("请输入金额", "forbidden");
			   return false;
			}
			 if($("#paytext").val() <= 0.01){
			 $("#paytext").val("0.01");
			/*  alert($("#paytext").val()) */
			/*   return false; */
			 }
			dopay(0);
	  });  
		
		function dopay(mailId){
 		    var _uriAdd = window.BASEPATH + 'phoneApp/goToPay';
			var params={};
			params.userId=${userId};
			params.merchantId=${merchantId};
			params.source="PUBLICADDRESS";
			params.payMoney=$('#paytext').val();
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
						       location.href=window.BASEPATH + 'business/gotopayment?orderId='+orderId;
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
								       location.href=window.BASEPATH + 'business/gotopayment?orderId='+orderId;
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
		})
				
</script>



<body>
			<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
			<a class="link-left" href="#side-menu">
			<span class="icon-reorder icon-large"></span>
			</a>
				<div class="header-content">商户</div>
			</div>
		</div>
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
	</div>
	
	
	<div style="width:96%;height:auto;background:#fff;text-align:center;margin:10px auto;padding:0 3% 20px 5%;border-radius:10px;">
	 <p style="height:30px;line-height: 30px;font-size:12px;margin:0;text-align:left;">
	 <span style="font-size:16px;font-weight:bold;">${merchant.shopName}</span>
	 <span style="float:right;"><span class="pingfen" style="color:#E65903;"></span>超棒</span>
	 <p style="text-align: left;margin:0;height:25px;line-height:25px;">联系电话：<a href="tel://${merchant.shopTel}" style="text-decoration:none;">${merchant.shopTel}</a>
	 </p>
	 <p style="height:30px;line-height: 30px;font-size:12px;margin:0;text-align:left;">
	 <img style="height:20px;width:20px;" src="lib/images/dingweis.png;"/>
	 <span id="dintance"></span>
	 <span onclick="gotobusinessdetails()" style="color:#E65903;float:right;">商家详情</span>
	 </p>
	 <button id="zhifu"  style="color:#fff;background: #E65903;width:85%;height:40px;border:none;outline:none;border-radius:10px; font-size:12px;">到店支付</button>
	</div>
	<!-- 采摘商品列表-->
	<div class="productlist" style="width:95%;border-radius:10px;padding:10px 0;height:auto;background:#fff; margin:10px auto;overflow: hidden;">
	</div>
	
	
	<div class='fuceng' style="display: none;">
	
	</div>
	  <div class="tanchuang" style="z-index:11111;width:100%;height:auto;display: none;padding:0 0 50px 0;text-align: center;background: #fff;position: fixed;bottom:0;border-radius:10px; ">
	   <p style="width:96%;margin:0 auto;height:50px;line-height: 50px;font-size: 18px;font-weight: bold;border-bottom:1px solid #D3D3D3;">${merchant.shopName}</p>
	    <p style="margin:80px 0 50px 0;text-align: center;font-weight:bold;">支付金额（元）<input id="paytext" style="height:30px;border-radius:8px;padding:0 5px;border:2px solid #EB6E1E;outline: none;"></p>
	    <button id="paynow" style="height:40px;color:#fff;background:#EB6E1E;width:70%;border:none;outline:none;border-radius:10px;font-size:18px;">立即支付</button>
	  </div>
   <!-- 置顶 -->
    <div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
</body>





</html>