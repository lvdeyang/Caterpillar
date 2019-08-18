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
<title>订桌详情</title>
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
	height:auto;
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
.facilities li{
float:left;
width:33%;
margin-bottom:20px;
text-align: center;
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
		$(".goshopping").click(function() {
			$(".zong").fadeOut();
			$(".modDiv").fadeIn();
		});
		$(".weui-btn_primary").click(function() {
			$(".modDiv").fadeOut();
			$(".zong").fadeIn();
			$(".shopping").fadeIn();
			setTimeout(function() {
				$(".shopping").fadeOut();
			}, 2000);
		});

        var _uri = window.BASEPATH + 'reservetable/particulars.do'; 
			var patam = {};
			patam.tableId = '${param.tablesId}'; //时间
			$.post(_uri, $.toJSON(patam), function(data) {
		      $("#meony").text(data.table.bookprice/100);
		      $("#room").append('<span>'+data.table.tablename+'</span>');
		      $("#headerWrapper").append('<img  src="'+data.table.detailsImg+'" />');
		      if(data.table.sofa == 1){
		       $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/lusofa.png"><span>沙发</span></li>');
		      } else $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/sofa.png"><span>沙发</span></li>');
		      if(data.table.television == 1){
		       $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/lutelevision.png"><span>电视</span></li>');
		      } else $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/television.png"><span>电视</span></li>');
		      if(data.table.airConditioner == 1){
		       $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/luairConditioner.png"><span>空调</span></li>');
		      } else $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/airConditioner.png"><span>空调</span></li>');
		      if(data.table.wifi == 1){
		       $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/luwifi.png"><span>无线</span></li>');
		      } else $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/wifi.png"><span>无线</span></li>');
		      if(data.table.lavatory == 1){
		       $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/lulavatory.png"><span>卫生间</span></li>');
		      } else $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/lavatory.png"><span>卫生间</span></li>');
		      if(data.table.lavatory == 1){
		       $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/lukaraoke.png"><span>唱歌</span></li>');
		      } else $("#facil").append('<li><img style="width:25px;height:25px;" src="lib/images/karaoke.png"><span>唱歌</span></li>');
             
			});

	});
	
	
	
	window.orderId;
	$(document).on('click', '#save', function() {
			var _uri = window.BASEPATH + 'reservetable/addition.do'; 
			var patam = {};
			patam.userName = $("#name").val(); //用户名
			patam.userPhone = $("#addressphone").val(); //用户手机号
			patam.merchantId = '${merchantId}'; 
			patam.tableDate = '${tableDate}'; //用户手机号
			patam.repast = '${repast}'; //用户手机号
			if ($("#addressphone").val() == "") {
				alert("请输入手机号");
				return false;
			}
			if ($("#idNum").val() == "") {
				alert("请输入用户名");
				return false;
			}
			patam.tablesId = '${param.tablesId}' ;
			$.post(_uri, $.toJSON(patam), function(data) {
		         payPublic(data.code, $("#meony").text());  
		         window.orderId = data.code;
			});
	});
	
	
	
	
	
	
	
  		var time;
  		var attid;
		var meony;  // 
		var prepay_id;
		var paySign; 
		var appId;   
		var timeStamp;   
		var nonceStr;  
		var packageStr;  
		var signType; 
		var orderNo;	
		function payPublic(orderId,meony){
		    meony =  meony*100;	
		$.get(window.BASEPATH +"reservetable/prev/table/"+orderId+"/"+meony, null, function(data){
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
						window.location.href = "reservetable/diningtable/tableSuccess?orderId="+window.orderId; 
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		             alert("交易取消");  
	                 window.location.href = "reservetable/tables/home";
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		             alert(res.err_desc); 
                     window.location.href = "reservetable/tables/home";
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
	
	<%-- function getRecomment(){
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
	  } --%>
          
        
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
<div class="zong">
		
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
	   
        <div style="width:96%;margin:5px auto;text-align: center;height:auto;background: #fff;border-radius:8px;font-weight: bold;">
          <p style="width:100%;height:50px;line-height: 50px;padding:0 5%;margin:0;text-align:left;color:black;" id="room"></p>
        </div>
        
         <div style="width:96%;margin:5px auto;height:auto;background: #fff;border-radius:8px;font-weight: bold;">
          <p style="padding:0 5%;height:40px;line-height: 40px;font-size:16px;"><img style="width:20px;height:20px;" src="lib/images/sheshi.png">设施</p>
             <ul class="facilities" id="facil" style="width:100%;padding:0 8%;overflow: hidden;list-style: none;">
             
             </ul>
        </div>
        
        
        <div style="width:96%;margin:5px auto;height:auto;background: #fff;border-radius:8px;">
          <p style="padding:0 5%;height:40px;line-height: 40px;font-size:16px;margin:0;font-weight: bold;"><img style="width:20px;height:20px;" src="lib/images/refund.png">退款规则</p>
          <p style="width:100%;height:auto;line-height: 30px;padding:0 5%;margin:0;text-align:left;color:black;"><span style="color:red;">不可取消</span>  <span>目前通过预付费方式购买的轻量应用服务器，支持 5 天（自然日）内无理由全额退款，相关限制条件如下：

提出退款申请的时间需要在购买成功之后 5 个自然日以内。
每个用户最多退款 1 次，最多累积可退 1 个实例。
在使用过程中已产生的后付费费用（如后付费流量费用）不退还。
仅退还实付金额，已使用的代金券不退还。
如用户在使用产品过程中，违反了相关法律法规或违反了所购产品服务条款的规定，将不予退款。</span></p>
        </div>
        
          <div style="width:96%;margin:5px auto;height:auto;background: #fff;border-radius:8px;">
          <p style="padding:0 5%;height:40px;line-height: 40px;font-size:16px;margin:0;font-weight: bold;"><img style="width:20px;height:20px;" src="lib/images/rule.png">使用规则</p>
          <ul style="width:100%;padding:0 8%;overflow: hidden;height:auto;">
           <li><span>我们可能将在向您提供服务的过程之中所收集的信息用作下列用途：</span></li>
           <li><span>向您提供服务;</span></li>
           <li><span>在我们提供服务时，用于身份验证、客户服务、安全防范、诈骗监测、存档和备份用途，确保我们向您提供的产品和服务的安全性;</span></li>
          </ul>
         </div>
         
         
         <div style="height:50px;width:100%;background: #fff;color:#fff;position: fixed;bottom:0;font-size:16px;font-weight: bold;">
          <p style="width:50%;line-height: 50px;color:#EC6C1F;text-align: center;display: inline-block;font-size:20px;"><span id="meony">￥50</span></p>
          <p class="goshopping" style="width:24%;line-height: 50px;text-align: center;background: #FF6501;float:right;">立即预订</p>
          <p style="width:24.5%;line-height: 50px;text-align: center;display: inline-block;background: #FD9E06;float:right;">加入购物车</p>
         </div>
         
      <!--   <div class="shopping" style="z-index:11111111111;display:none;margin:0 0 -60px -25%;border-radius:10px;height:120px;width:50%;background: #E6E6E6;color:#797778;position: fixed;bottom:50%;left:50%;font-size:16px;font-weight: bold;text-align: center;overflow: hidden;">
        <img style="width:30%;position: absolute;left:50%;margin-left:-15%;top:20px;" src="lib/images/trues.png">
        <p  style="line-height: 190px;">成功加入购物车</p>
        </div> 
          -->
</div>

	<div class="modDiv" id="addressSecond" style="display:none;">

		<div class="weui-cells weui-cells_form" style="margin:0;">

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">预订人</label>
				</div>
				<div class="weui-cell__bd">
					<input id="name" class="weui-input" type="text" placeholder="">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">联系电话</label>
				</div>
				<div class="weui-cell__bd">
					<input id="addressphone" class="weui-input" type="text"
						placeholder="">
				</div>
			</div>
		</div>
		<a id="save"
			style="width:96%;position:fixed;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
			href="javascript:;" class="weui-btn weui-btn_primary"> 立即购买</a>

	</div>
<!-- 	<div class="modDiv" id="cameraDiv" style="display:none;">
		<div id="cameraContent"></div> -->

		<div>
			<!--   <a id="cancelPhoto"
							style="width:47%;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 取消</a>
                          <a id="confirmPhoto"
							style="width:47%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 保存</a> -->
		</div>
	</div>
	</div>

	<!-- 空白 -->
         <p style="height:50px;"></p>
</body>





</html>