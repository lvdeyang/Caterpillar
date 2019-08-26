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
<title>采摘商品购买</title>
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

/*定义选项卡内容*/
	.contentt{
		display: none;		
	}
	/*显示动态选项卡*/
	.active{
		display: block;
	}
	.tab-btn li{
		list-style: none;
		float: left;
		width: 30%;
		text-align: center;
		margin-left:5%;
		border-radius:10px;
		
		
	}
	.tab-btn li p{
	  margin:0;
	  font-size:16px;

	}
	.btn-active{
		/* background: orange; */
		z-index:11111;
		color:#fff;
		background:#EB6E1E;
	
	} 
	select{
	-webkit-appearance: none;
	width:auto;
	 text-align: center;
    text-align-last: center;
    border:none;
    outline:none;
    background: #fff;

}
	option{
    text-align: center;
    text-align-last: center;
 
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
	var logisticsId=1;
	
	$(document).on('click', '#logistics1', function(){
		logisticsId=1;
		$('.contentt-box').show();
	});
	
	$(document).on('click', '#logistics2', function(){
		logisticsId=2;
		$('.contentt-box').hide();
	});
	
	<!--选项卡  -->
	$(document).on('click', '.tab-btn li', function(){
	//为按钮添加样式
	$(this).addClass("btn-active")
	.siblings()
	.removeClass("btn-active");
				
	//获取点击按钮的索引
	var index = $(this).index();
				
	/*通过索引找到对应的content,并显示:其他content隐藏*/
	var jq = $(".contentt-box .contentt").eq(index)
	.addClass("active").siblings().removeClass("active")

	});
	$(document).on('click', '.ling', function(){
	$(".riqi").show();
	});
	$(document).on('click', '.wuliu', function(){
	$(".riqi").hide();
	});
	
		layui.use('laydate', function(){
			  //禁止软键盘弹出
	   $("#bookDate").focus(function(){
        document.activeElement.blur();
    });
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#bookDate' //指定元素
	  });
	});
	
	var MAX = ${product.productLimitNum eq "0"? "99":product.productLimitNum}, MIN = ${productRestrictNumber};
	$(document).on('click','.p1',function(){
	  var value=parseInt($('.zhi').val())-1;
	  if($('.zhi').val()<=MIN){
	  return false;
	  }
	  $('.zhi').val(value);
	  $('.price').html("￥"+(${product.productPrice}*value).toFixed(2));
	  $('.productprice').html("￥"+(${product.productPrice}*value).toFixed(2));
	 })
	    
	 $(document).on('click','.p2',function(){
	   var value=parseInt($('.zhi').val())+1;
	   if($('.zhi').val()>=MAX){
	   return false;
	   }
	   $('.zhi').val(value);
	   $('.price').html("￥"+(${product.productPrice}*value).toFixed(2));
	   $('.productprice').html("￥"+(${product.productPrice}*value).toFixed(2));
	  })
	  
	  function gotologistics(){
   		location.href=window.BASEPATH + 'business/gotologistics?productId=${product.id}';
   		}
   		
	 function gotopickinglist(){
		location.href=window.BASEPATH + 'business/gotocomdlist?merchantId=${product.productMerchantID}';
	 }
</script>


<script>

$(function(){
	getaddress();
	initCombos();
	$('.productprice').html("￥${product.productPrice}");
})


	
	//获取地址
	function getaddress(){
		var _uriAddress = window.BASEPATH + 'phoneApp/address/list?userId=${userId}';
		$.get(_uriAddress, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			    var html=[];
			if(data && data.length>0){
				for(var i=0; i<data.length; i++){
					if(data[i].defaultAddress==1||data.length==1){
						html.push('<div class="contentt active" style="">');
						html.push('<div style="width:100%;height:auto;background: #fff;margin:5px 0;position: relative; ">');
						html.push('<p style="width:100%;height:30px;line-height:30px;padding:0 4% 0 15%;margin:0 auto;"><span style="text-align: left;">收件人：<span>'+data[i].consigneeName+'</span></span>');
						html.push('<span style="float:right;">联系电话：<span>'+data[i].consigneePhone+'</span></span></p>');
						html.push('<p style="width:100%;height:auto;padding:0 7% 0 15%;margin:0 auto;">收货地址：<span>'+data[i].consigneeAddress+'</span></p>');
						html.push('<img style="height:30px;width:30px;position: absolute;top:15px;left:5%;" src="lib/images/zidongdaolan.png">');
						html.push('<span onclick="gotologistics()" style="position: absolute;right:5%;font-size:16px;font-weight: bold;top:25px;">></span>');
				        html.push('<input type="text" hidden="hidden" class="addressId" value="'+data[i].id+'">');
				        html.push('</div>');
				        }
				}
			    		$('.contentt-box').append(html.join(''));
			}else{
						html.push(' <p onclick="gotologistics()" style="width:100%;height:30px;line-height:30px;text-align:center;background: #fff;margin:5px 0;font-weight: bold;"><span>请添加地址</span><span style="float:right;margin-right:10px;">></span></p>');
			    		$('.contentt-box').append(html.join(''));
			}
		});
	}
	
	
	function initCombos(){
		   var html=[];
		   var url=window.BASEPATH + 'business/getallcombo';
		   $.post(url,{"productId":${product.id}},function(data){
		   
		   if(data.length==0){
		   		html.push('<option value="0">标准(￥${product.productPrice}) ▼</option>');
		   }else{
		        $('#total').html((data[0].comboprice/100).toFixed(2));
		   }
		   
		   for(var i=0;i<data.length;i++){
		      html.push('<option value="'+data[i].id+'-'+(data[i].comboprice/100).toFixed(2)+'">'+data[i].combo+'(￥'+(data[i].comboprice/100).toFixed(2)+')</option>');
		   }
		   $('.comboList').append(html.join(''));
		   
		   })
		}

</script>
<script>
		    
	function dobuy(){
		    var param={};
			param.productId=${product.id};
			param.productNum=$('.zhi').val();
			param.payMoney=1;
			param.userId=${userId};
			param.paytype='WEICHAT';
			param.source="PUBLICADDRESS";
			param.addressId=$('.addressId').val();
			param.bookDate=$('#bookDate').val();
			param.logisticsId=logisticsId;
		    param.comboId=$('.comboList').val().split('-')[0];
			var chkStockUrl=window.BASEPATH + 'pubnum/stock/check?proId='+${product.id}+'&count='+$('.zhi').val();
			$.get(chkStockUrl, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
				if(data.stock==0){
				   $.toast("抱歉，库存不足", "forbidden");
				   return;
				}	
				var _uriPay = window.BASEPATH + 'phoneApp/order/add';
				$.post(_uriPay, $.toJSON(param), function(data){
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
						       location.href=window.BASEPATH + 'business/gotopayment?orderId='+orderId+'&merchantId=${product.productMerchantID}';
						    }
						});
				}else{
					$.alert('您的余额不足！');
				}
                   
			})
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
		                $.confirm("交易成功");
		                //每五秒刷新订单状态
						
		                setInterval(function(){ 
                                $.get(window.BASEPATH +"pubnum/order/status?orderId="+orderNo, null, function(data){
								    
								    if(data.data=="PAYSUCCESS"){
								       location.href=window.BASEPATH + 'business/gotopayment?orderId='+orderNo+'&merchantId=${product.productMerchantID}';
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
		  <!-- 选项卡 -->
			<div class="tab" style="">
			  <p style="float:left;font-size:16px;margin:0 0 0 40px;line-height:50px;">收货方式</p>
					<ul class="tab-btn active" style="padding:0 5%;margin:0;height:50px;line-height: 50px;background: #fff;">
					<li id="logistics1" class="btn-active"><p class="wuliu">过来玩物流</p></li>
					<li id="logistics2" class="ling"><p>到店领取</p></li>				
			        </ul>	  
			    <div class="contentt-box" >
			     <!--过来玩物流  -->
				
				</div>
				<!--到店取货  -->
				<div class="contentt" style="text-align: center;">
				</div>
			  </div>			
            </div>
        <div style="height:120px;width:100%;background: #fff;margin:5px 0;overflow: hidden;position: relative;">
          <img style="width:35%;height:94%;border-radius:10px;margin:0.5% 5% 0.5% 6%;display:inline-block;" src="http://www.guolaiwan.net/file${product.productShowPic}">
          <p style="display: inline-block;margin:0;position: absolute;font-size:16px;top:10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:50%;">${product.productName}</p>
          <p style="display: inline-block;margin:0;position: absolute;font-size:12px;top:40px;">型号：1斤装</p>
          <p class="price" style="display: inline-block;margin:0;position: absolute;font-size:18px;bottom:10px;color:#EB6E1E;">￥${product.productPrice}</p>
          <button onclick="gotopickinglist()" style="color:#fff;background:#EB6E1E;padding:2px 5px;border-radius:12px;border:none;outline:none;position: absolute;right:2%;top:40px; ">进入店铺</button>
          <p class="p1" style="margin:0;position: absolute;bottom:10px;right:22%;font-size:14px;font-weight:bold;line-height:25px;display:inline-block;width:25px;height:25px;border-radius:50%;border:1px solid #666666;text-align: center;">—</p>
		  <input type="text" readonly="true"  class="zhi" id="shuliang" value="${productRestrictNumber}"  style="padding:0;border:none;outline: none;width:20px;height:20px;position: absolute;right:14%;margin:0;bottom:10px;font-size:14px;font-weight:bold;text-align: center;">
		  <p class="p2" style="margin:0;position: absolute;bottom:10px;right:4%;font-size:22px;color:#fff;background:#EC6D1E;display:inline-block;width:25px;height:25px;border-radius:50%;border:1px solid;text-align: center;">+</p>
        </div>
        <div class="riqi" style="position: relative;display: none; ">
        <input class="layui-input" id="bookDate"  type="text" placeholder="请您选择采摘日期"   style="cursor: pointer;width:100%;height:60px;margin:5px auto;padding:0 10%;text-align: center;" >
         <p style="position: absolute;font-size: 16px;font-weight: bold;top:19px;left:5%;">预订日期</p>
        </div>
        <div style="position: relative;background: #fff;margin: 0 auto;text-align: center;">
        <select class="comboList" style=" border-radius:10px;cursor: pointer;height:60px;" >
        </select>
       
        <p style="position: absolute;font-size: 16px;font-weight: bold;top:19px;left:5%;">套餐选择</p>
        </div>
        <div style="width:100%;height:60px;position: fixed;bottom:0;background: #fff;">
        <p  style="line-height: 60px;color:#EB6E1E;width:50%;display: inline-block;text-align: center;font-size:20px;font-weight: bold;"><span class="productprice"></span></p>
        <p onclick="dobuy()" style="line-height: 60px;background:#EB6E1E;width:50%;color:#fff;float:right;display: inline-block;text-align: center;font-size:16px;font-weight: bold;">立即购买</p>
        </div> 
</body>





</html>