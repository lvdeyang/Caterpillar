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

<title>商品详情</title>

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
	padding: 0;
	margin: 0;
	height: 200px;
}

.swiper-container img {
	display: block;
	width: 100%;
}

#distributeList {
	margin-top: 10px;
	padding-left: 10px;
	border-bottom: solid 2px #18b4ed;
	width: 100%;
	height: 35px;
}

#distributeList a {
	text-decoration: none;
	color: #CCC;
	font-size: 12px;
}

#distributeList a.current {
	text-decoration: none;
	color: #18b4ed;
	font-size: 20px;
}

#columnTable {
	width: 100%;
	margin-top: 10px;
}

#columnTable td {
	width: 20%;
	text-align: center;
	font-size: 12px;
}

.alert {
	width: 90%;
	text-align: center;
	color: #fff;
	margin: 10px auto;
	border-radius: 5px;
	line-height: 30px;
	cursor: pointer;
	background: #4ab819;
}

.confirm {
	background: #196fb8;
}

.open {
	background: #f88408;
}

.toast {
	background: #f80851;
}

.later {
	background: #a9a9a9;
}
#proContent img{
    width:85%;
}
#proContent image{
    width:85%;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<!-- <script src="../../../../layui/lib/alert/css/alertstyle.css"></script> -->
<script src="../../../../layui/lib/alert/js/jquery-1.7.1.min.js"></script>
<script src="../../../../layui/lib/alert/js/ui.js"></script>
<link href="../../../../layui/lib/alert/css/alertstyle.css"
	rel="stylesheet" />
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
		
		
	  var productName;
	  var productPic;
	  var productUrl;
		
      var iscollect=0;
      var qq="";
      var canSelDate='';
      var phone='';
		//获取所有一级推荐
      var _uriRecomment = window.BASEPATH + 'phoneApp/productInfo?productId=${id}&userId=${userId}';
		
		$.get(_uriRecomment, null, function(data){
			data = parseAjaxResult(data);
			productName = data.product.productName + '-过来玩';
			productPic = data.product.productShowPic;
			productUrl = window.location.href;
			if(data === -1) return;
			if(data){
			    var html=[];
			    var demo = 11.50*3;
			    var demo1 = demo%1;
			    var pics=data.product.productMorePic.split(',');
				for(var i=0; i<pics.length; i++){
					html.push('<div style="height:200px;" class="swiper-slide"><img style="height:200px" src="'+pics[i]+'" alt=""></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
			    $('.header-content').html(data.product.productName);
			    $('#proName').html(data.product.productName+'￥<span id="price">'+data.product.productPrice+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="text-decoration:line-through">￥'+data.product.productOldPrice+'</span>');
			    $('#proContent').html(data.product.productIntroduce);
			    $('#total').html(data.product.productPrice);
			    if(data.product.productPrice*100==0){
			       $('#buy').hide();
			       $('#tel').show();
			    }
			    phone=data.shopTel;
			    $('#costMessage').html(data.product.costMessage);
			    $('#remarks').html(data.product.remarks);
			    $('#notes').html(data.product.notes);
			    $('#countDate').html(data.product.productDayCount);
			    canSelDate=data.product.datesOn;
			    generateComment(data.comments , data.userimgs , data.useridlist)
			    iscollect=data.product.ifcollection;
			    qq=data.product.ifcollection;
			    if(iscollect==1){
			    
			       $('#fav').html('取消收藏');
			    }
			}
			initShare();
		});
		
		$(document).on('click','#tel',function(){
	       var phones=phone.split('/');
	       location.href ='tel://' + phones[0];
	    });
		
		function generateComment(comments , userimgs , useridlist){
		   var html=[];
		   for(var i=0;i<comments.length;i++){
		      html.push('<div class="weui-media-box weui-media-box_text">');
	          html.push('  <h4 class="weui-media-box__title" style="font-size:13px;"><img style="height:20px;wight:20px;" src="'+userimgs[i]+'" />&nbsp;&nbsp;&nbsp;&nbsp;'+comments[i].userName+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:11px;">'+comments[i].userDate+'</span></h4>');
	          if(${userId} == useridlist[i]){
	          	html.push('  <p class="weui-media-box__desc">'+comments[i].content+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="javascript:deletepl('+comments[i].id+')" id="deletepl">删除</a></p>');
	          }else{
	          	html.push('  <p class="weui-media-box__desc">'+comments[i].content+'</p>');
	          }
	          html.push('</div>');
		   }
		   if(comments.length==0){
		      html.push("<div style='width:100%;margin-top:10px;font-size:12px;text-align:center'>暂无数据</div>");
		   }
		   $('#comment').append(html.join(''));
		}
		
		
		var share={};
		
	    function initShare(){
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
                            title: productName, // 分享标题
                            link: productUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: productPic, // 分享图标
                            success: function () {
                                
                            }
                        });
	            wx.onMenuShareAppMessage({
					title : productName, // 分享标题
					desc: '畅游华夏，尽在过来玩-联系电话:0315-6681288/6686299', // 分享描述
					link: productUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                    imgUrl: productPic, // 分享图标
					success : function() {
						
					}
				});
	            
	       });
        }
		
		
	    $(document).on('click','#contact',function(){
	      openqq(qq);
	    
	    });
	
	    $(document).on('click','#fav',function(){
	        
	        if(iscollect==1){
	            var param={};
		        param.proId=${id};
		        param.userId=${userId};
	            var _urifav = window.BASEPATH + 'pubnum/delCollectionPro';
				$.post(_urifav, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					$.toast("取消收藏成功");
	                $('#fav').html('收藏');
					iscollect=0;
				});
	        }else{
	           var param={};
		        param.productId=${id};
		        param.userId=${userId};
	           var _urifav = window.BASEPATH + 'phoneApp/collectionPro';
				$.post(_urifav, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					$.toast("收藏成功");
	                $('#fav').html('取消收藏');
					iscollect=1;
				});
	        
	        }

	    });
	
	
	    var MAX = 99, MIN = 1;
		$('.weui-count__decrease').click(function (e) {
		  var $input = $(e.currentTarget).parent().find('.weui-count__number');
		  var number = parseInt($input.val() || "0") - 1
		  if (number < MIN) number = MIN;
		  $input.val(number);
		  $('#total').html(($('#price').html()*number).toFixed(2));
		});
		$('.weui-count__increase').click(function (e) {
		  var $input = $(e.currentTarget).parent().find('.weui-count__number');
		  var number = parseInt($input.val() || "0") + 1
		  if (number > MAX) number = MAX;
		  $input.val(number);
		  $('#total').html(($('#price').html()*number).toFixed(2));
		});
		
		
		
		$(document).on('click','#addOrder',function(){
		
		var param={};
		param.productId=${id};
		param.productNum=$('#proCount').val();
		param.userId=${userId};
		param.paytype='WEICHAT';
		param.source="PUBLICADDRESS";
		
		
			var chkStockUrl=window.BASEPATH + 'pubnum/stock/check?proId='+${id}+'&count='+$('#proCount').val();
		    
			$.get(chkStockUrl, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
				if(data.stock==0){
				   $.toast("抱歉，库存不足", "forbidden");
				   return;
				}	
				var _uriPay = window.BASEPATH + 'phoneApp/joinBasket';
			    $.post(_uriPay, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					$.toast("已加入购物车");
				});
			});
			

		});
		
		$(document).on('click','#buy',function(){
		     $('#selDate').show();
			 $('#addressFitst').hide();
			 $("#inline-calendar").calendar({
						  container: "#inline-calendar",
						  input: "#selDateInput",
						  onOpen:function(p){
						      var dates=[];
						      if(canSelDate){
						         dates=canSelDate.split(',');
						      }
						      var dateDivs=$('.picker-calendar-day');
						      for(var i=0;i<dateDivs.length;i++){
						         $(dateDivs[i]).css('color','#CCC');
						         for(var j=0;j<dates.length;j++){
						            if(dates[j]==$(dateDivs[i]).attr('data-day')){
						               $(dateDivs[i]).css('background-color','#d9e5f4');
						               $(dateDivs[i]).css('color','black');
						               $(dateDivs[i]).attr('cansel','1');
						               break;
						            }
						         }
						      
						      }
						  },
						  onDayClick:function(p, dayContainer, year, month, day){
						      var cansel=$(dayContainer).attr('cansel');
						      if(cansel==1){
						         
						         $('#bookDate').val(year+"-"+(parseInt(month)+1)+'-'+day);
						      }else{
						         $.toast("不可选", "forbidden");
						         return false;
						      }
						      
						  
						  }
						  
						  
	        });      
         	$('#selAddress').popup(); 
        
        });
        
        $(document).on('click','#confirmDate',function(){
             if($('#bookDate').val()==''){
                 $.toast("请选择使用日期", "forbidden");
				 return false;
             }
             $('#selDate').hide();
			 $('#addressFitst').show();
        });
        
		$(document).on('click','#cancelAddress',function(){
	   		$.closePopup();
	  
	    });
		
		
		$(document).on('click','.mailAddress',function(){
		    $.closePopup();
		    var ids=this.id.split('-');
		    var param={};
			param.productId=${id};
			param.productNum=$('#proCount').val();
			param.userId=${userId};
			param.paytype='WEICHAT';
			param.source="PUBLICADDRESS";
			param.addressId=ids[1];
			param.orderBookDate=$('#bookDate').val();
			var chkStockUrl=window.BASEPATH + 'pubnum/stock/check?proId='+${id}+'&count='+$('#proCount').val();
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
					
					
					$.confirm("确定支付？", function() {
					    payPublic(data.orderId);
					  }, function() {
					    deleteOrder(data.orderId);
					  });

				});
					

			});
			


		});
		
		
		function deleteOrder(orderId){
		var chkStockUrl=window.BASEPATH + 'pubnum/change/ordercount?orderId='+orderId+'&count=-1';
			         $.get(chkStockUrl, null, function(data){
		        var param={};
		        param.orderId=orderId;
		        var _urideleteOrder = window.BASEPATH + 'phoneApp/deleteorder';
				$.post(_urideleteOrder, $.toJSON(param), function(data){
				     
			         });
				
				
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
								       location.href=window.BASEPATH +"pubnum/order/info?orderId="+orderNo;
								    }
								});

                        }, 1000);

		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		                deleteOrder(orderNo);
	
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
		
		
		//编辑地址
		$(document).on('click','#addAddress',function(){
		    $('#addressFitst').hide();
		    $('#addressSecond').show();
		
		});
		$(document).on('click','#save',function(){
		    if($('#address').val()==''){
			   $.toast("请选择地址", "forbidden");
			   return false;
			}
			if($('#addressphone').val()==''){
			   $.toast("请输入手机号", "forbidden");
			   return false;
			}
			if(!(/^1[34578]\d{9}$/.test($('#addressphone').val()))){ 
		       $.toast("手机号码有误，请重填", "forbidden");  
		       return false; 
		    } 
			if($('#name').val()==''){
			   $.toast("请输入姓名", "forbidden");
			   return false;
			}
		    var _uriAdd = window.BASEPATH + 'phoneApp/address/add';
			var params={};
			params.userId=${userId};
			var addresses=$('#address').val().split(' ');
			params.province=addresses[0];
			params.city=addresses[1];
			params.district=addresses[2];
			params.consigneeAddress=$('#moreAddress').val();
			params.addressphone=$('#addressphone').val();
			params.consigneeName=$('#name').val();
			$.post(_uriAdd, $.toJSON(params), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				$('#addressFitst').show();
		        $('#addressSecond').hide();
                getAllAddr();
			});
	  });

    
	  
	
      $("#address").cityPicker({
        title: "选择地址",
        onChange: function (picker, values, displayValues) {
          console.log(values, displayValues);
        }
     });
     
     getAllAddr();
     function getAllAddr(){
        var _uriAddress = window.BASEPATH + 'phoneApp/address/list?userId=${userId}';
		
		$.get(_uriAddress, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data && data.length>0){
			    var html=[];
				for(var i=0; i<data.length; i++){
				   
					 html.push('<div class="weui-media-box weui-media-box_text mailAddress" id="mailadd-'+data[i].id+'">');
			         html.push('<h4 class="weui-media-box__title">'+data[i].consigneeName+'（'+data[i].consigneePhone+'）<span style="font-size:12px;color:red" class=" icon-share-alt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击支付</span></h4>');
			         html.push('<p class="weui-media-box__desc">'+data[i].province+data[i].city+data[i].district+data[i].consigneeAddress+'</p>');
			         html.push('</div>');
				}
				$('#addressList').children().remove();
			    $('#addressList').append(html.join(''));
			}
			
		});
     
     
     }
		
	 
	 
	 
	
	
	});
</script>



<body>
	<script type="text/javascript">
		function deletepl(data){
			var paran={};
			paran.proId=data;
			paran.userId=${userId};
			var _urifav = window.BASEPATH + 'phoneApp/deletepl.do';
			$.post(_urifav , $.toJSON(paran) , function(daat){
				if(daat === "success"){
					$.toast("删除评论成功");
					window.location.reload();
				}else{
					$.toast("该评论不是您的哦");
				}
			});
		}
	</script>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商品详情</div>
			</div>
		</div>
		<div class="content">

			<div class="swiper-container" data-space-between='10'
				data-pagination='.swiper-pagination' data-autoplay="1000">
				<div class="swiper-wrapper"></div>
			</div>
			<div style="width:100%">
				<div
					style="font-size:12px;margin-left:14px;margin-top:15px;width:50%;float:left;"
					id="proName"></div>
				<div
					style="font-size:12px;margin-right:14px;margin-top:15px;width:30%;float:right;text-align:right;margin-right:40px;">
					<i id="fav" class="icon-star">收藏</i>
				</div>
			</div>
			<div
				style="display:none;width:100%;margin-top:15px;margin-left:14px;font-size:12px;float:left;">
				<a id="contact" href="javascript:void(0);" class=" icon-user">联系客服</a>
			</div>
			
			<div
				style="width:90%;margin-top:15px;margin-left:14px;font-size:12px;float:left;">
				团期&nbsp;&nbsp;<span id="countDate"></span>
			</div>
			
			<div
				style="font-size:12px;margin-left:14px;margin-top:15px;width:50%;float:left;height:50px;line-height:50px;">购买数量</div>
			<div
				style="font-size:12px;margin-right:14px;margin-top:15px;width:30%;float:right;">
				<div class="weui-cell">
					<div class="weui-cell__ft">
						<div class="weui-count">
							<a class="weui-count__btn weui-count__decrease"></a> <input
								disabled="disabled" class="weui-count__number" id="proCount"
								type="number" value="1"> <a
								class="weui-count__btn weui-count__increase"></a>
						</div>
					</div>
				</div>
			</div>



			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">商品详情</div>
			<div style="font-size:12px;padding:12px;float:left;width:100%;overflow-x:scroll" id="proContent"></div>
			
			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">费用包含</div>
			<div style="width:95%;font-size:12px;padding:12px;float:left;width:100%;overflow-x:scroll" id="costMessage"></div>
            <div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">备注</div>
			<div style="width:95%;font-size:12px;padding:12px;float:left;width:100%;overflow-x:scroll" id="remarks"></div>
			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">预定须知</div>
			<div style="width:95%;font-size:12px;padding:12px;float:left;width:100%;overflow-x:scroll" id="notes"></div>

			<div
				style="width:90%;font-size:14px;font-weight:bold;margin-left:12px;float:left;margin-top:15px;">评论列表</div>
			<div id="comment" class="weui-panel__bd"
				style="font-size:12px;width:100%;float:left;padding-bottom:40px;">

			</div>

			<div style="width:100%;height:40px;position:fixed;bottom:2px">
				<!-- <a id="addOrder"
					style="width:46%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;"
					href="javascript:;" class="weui-btn weui-btn_primary">加入购物车</a> --> <a
					id="buy"
					style="width:96%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
					href="javascript:;" class="weui-btn weui-btn_primary">立即购买（￥<span
					id="total">0</span>）
				</a>
				<a
					id="tel"
					style="display:none;width:96%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
					href="javascript:;" class="weui-btn weui-btn_primary">电话咨询
				</a>
			</div>

			<div id="selAddress" class="weui-popup__container"
				style="padding-bottom:50px;">
				<div class="weui-popup__overlay"></div>
				<div class="weui-popup__modal">
				    <div id="selDate">
				       
				      
				          <input class="weui-input" id="selDateInput" type="text" readonly="">
				    
				        <div style="width:100%;height:200px;" id="inline-calendar"></div>
				         <div class="weui-cell" style="margin-top:180px">
				          <div class="weui-cell__hd"><label class="weui-label">预定日期</label></div>
						    <div class="weui-cell__bd">
						      <input id="bookDate" style="border:1px solid #CCC;width:150px" class="weui-input" type="text" placeholder="">
						    </div>
						   </div>
						   <a id="confirmDate"
							style="width:96%;position:fixed;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary">确定</a>
				    </div>
					<div id="addressFitst" style="display:none;">

						<div class="weui-cells__title" style="color:red;font-weight:bold">点击地址选择或添加新联系人</div>
						<div class="weui-panel__bd" id="addressList"
							style="padding-bottom:40px;"></div>

						<!-- 						<a id="addAddress"
							style="width:96%;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 添加地址</a> <a
							id="cancelAddress"
							style="width:96%;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 取消购买</a> -->

						<div style="width:100%;height:40px;">
							<a id="cancelAddress"
								style="width:46%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;"
								href="javascript:;" class="weui-btn weui-btn_primary">取消购买</a> <a
								id="addAddress"
								style="width:46%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
								href="javascript:;" class="weui-btn weui-btn_primary">添加地址</a>
						</div>
					</div>

					<div id="addressSecond" style="display:none;">

						<div class="weui-cells weui-cells_form">
							<div class="weui-cells__title">添加收货地址</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">姓名</label>
								</div>
								<div class="weui-cell__bd">
									<input id="name" class="weui-input" type="text" placeholder="">
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">手机号</label>
								</div>
								<div class="weui-cell__bd">
									<input id="addressphone" class="weui-input" type="text"
										placeholder="">
								</div>
							</div>

							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label for="name" class="weui-label">地址选择</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" id="address" type="text" value=""
										readonly="" data-code="420106"
										data-codes="420000,420100,420106">
								</div>
							</div>


							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">详细地址</label>
								</div>
								<div class="weui-cell__bd">
									<input id="moreAddress" class="weui-input" type="text"
										placeholder="">
								</div>
							</div>
						</div>
						<a id="save"
							style="width:96%;position:fixed;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 保存</a>

					</div>

				</div>



			</div>
		</div>
</body>


</html>