<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>直播播放主页</title>

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

.demo img {
	width: 30px;
	height: 30px;
	position: absolute;
	top: 1%;
	right: 15px;
	margin-left: -15px;
	z-index: 9;
}

.btn {
	background: url(lib/images/xin7.png) no-repeat center center;
	background-size: 100% 100%;
	width: 50px;
	height: 50px;
	border: 0;
	color: #fff;
	position: absolute;
	top: 0%;
	right: 5px;
	margin-left: -25px;
	z-index: 10;
	touch-action: none;
}

.btn2 {
	background: url(lib/images/liwu.png) no-repeat center center;
	background-size: 100% 100%;
	width: 50px;
	height: 50px;
	border: 0;
	color: #fff;
	position: absolute;
	top: 0%;
	right: 65px;
	margin-left: -25px;
	z-index: 10;
	touch-action: none;
}

.btn3 {
	background: url(lib/images/huifang.png) no-repeat center center;
	background-size: 100% 100%;
	width: 50px;
	height: 50px;
	border: 0;
	color: #fff;
	position: absolute;
	top: -2%;
	right: 120px;
	margin-left: -25px;
	z-index: 10;
	touch-action: none;
}

.jishu {
	border-radius: 5px;
	position: absolute;
	top: 70%;
	right: 5px;
	width: 100%;
	color: #FD6770;
	text-align: right;

}

.pushGift {
	width: 100%;
	height: 95px;
	display: none;
	border: 1px solid #EEEEEE;
	background: #f3faff;
	position: absolute;
	z-index: 10000;
	top: 0%;
	 overflow: hidden; 
	justify-content: center;
}

.readyBuy{
    /* position: relative;
     right:3%;
     top:100%; */
     z-index: 10000;
    display: none; 
    
   
}
</style>
<link href="lib/video.css" rel="stylesheet">
</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/video.js"></script>
<script type="text/javascript" src="lib/video-hls.js"></script>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript">
    var  valve = 1;
   
	$(function() {
	
	  var usrid='${live.userId}';
	  var isred=${live.isOpenRed};
	  
	  if(isred==1){
	     $('#redpackPic').show();
	  }
	  
	  if(usrid==100000){
	     $('#player').show();
	   
	  }else{
	 
	     $('#my-video').show();
	     
	  }
	  window.BASEPATH = '<%=basePath%>';
	  var parseAjaxResult = function(data){
			if(data.status != 200){
			  $.toast("评论失败!");
				$.toptip(data.message, 'error');
				valve = 0;
				return -1;
			}else{
			   valve = 1;
			return data.data;		
			}
	  };
	  var myPlayer = videojs('my-video');
	  videojs("my-video").ready(function(){
		var myPlayer = this;
		myPlayer.play();
	  });
	  
	  $('.my-video-dimensions').width('100%').height(200);
	  
	  
	  $(document).on('click','#save',function(){
	      if($(".pushGift").hasClass("show")){
	      
      			$('.readyBuy').fadeOut().removeClass("show");
	            // 其他
	            $('.giftNumber').val(1);
	            $(".pushGift").fadeOut().removeClass("show");
	      
	      }else{
	      		if(/^\s*$/.test($('#message').val())){
	      			$.alert("输入内容不能为空！");
	      			return;
	      		}
		        var _uriaddMsg = window.BASEPATH + 'phoneApp/addMessage';
			  	var params={};
			  	params.liveId=${live.id};
			  	params.userId=${userId};
			  	params.message=$('#message').val();
				$.post(_uriaddMsg, $.toJSON(params), function(data){
					data = parseAjaxResult(data);
				
					 if(valve == 1 ){
					   $.toast("评论成功!");
					} 
					 $('#message').val('');
					  refreshMsg();	
				});
			}
	  });
	  
	  
	  var share={};
	  var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	    window.onload=function(){
	    	var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			    $.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					    
						share=data;
						wx.config({
				            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				            appId : share.appId, // 必填，公众号的唯一标识
				            timestamp : share.timestamp, // 必填，生成签名的时间戳
				            nonceStr : share.nonceStr, // 必填，生成签名的随机串
				            signature : share.signature,// 必填，签名，见附录1
				            jsApiList : ['checkJsApi', 'onMenuShareTimeline' ,'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			        	});
	  
			  wx.ready(function() {
			
			               
			            wx.onMenuShareTimeline({
		                            title: '${live.liveName}-分享领红包', // 分享标题
		                            link: location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		                            imgUrl: 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
		                            success: function () {
		                
				                         if(isred==1){
									         var sendred= window.BASEPATH + 'pubnum/sendRedPacket?liveId=${live.id}';
										     var params={};
										     $.post(sendred, $.toJSON(params), function(data){
												$.alert(data.status);
											 }); 
									     } 
		                            }
		                        });
			            wx.onMenuShareAppMessage({
							title : '${live.liveName}', // 分享标题
							desc: '畅游华夏，尽在过来玩-联系电话:0315-6681288/6686299', // 分享描述
							link: location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		                    imgUrl: 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
							success : function() {
						
							     if(isred==1){
							         var sendred= window.BASEPATH + 'pubnum/sendRedPacket?liveId=${live.id}';
								     var params={};
								     $.post(sendred, $.toJSON(params), function(data){
										 $.alert(data.status);
									 }); 
							     }
								 
							}
						});
			            
			       });
					}
					
				});
	    }
	   
	  
	  
	  
	  refreshMsg();
	  
	  function refreshMsg(){
	  		var _uriMsg = window.BASEPATH + 'pubnum/getLiveMessage?liveId=${live.id}';
		  
			$.get(_uriMsg, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				  var html=[];
				  for(var i=0;i<data.length;i++){
				      html.push('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg product" id="pro-828">');
				      html.push('<div class="weui-media-box__hd">');
				      html.push('	<img style="width:60px;height:60px;" class="weui-media-box__thumb" src="'+data[i].headImg+'">');
				      html.push('</div>');
				      html.push('<div class="weui-media-box__bd">');
				      html.push('<h4 class="weui-media-box__title" style="font-size:12px;">'+data[i].userName+'</h4>');
				      html.push('<p class="weui-media-box__desc">'+data[i].content+'</p>');
				      html.push('</div>');
			          html.push('</a>');
				  }
				  $('#messageContent').children().remove();
				  $('#messageContent').append(html.join(''));
				  
				}
			});
	  
	  }
	  
	});
	
	
	

	
	
</script>


<script type="text/javascript">
			$(function() {
			window.BASEPATH = '<%=basePath%>';
			
			$('.url').val(window.BASEPATH)
			
			 $("#btn1").click(function() {
				var x = 100;
				var y = 900;
				var num = Math.floor(Math.random() * 3 + 1);
				var index = $('.demo').children('img').length;
				var rand = parseInt(Math.random() * (x - y + 1) + y);

				$(".demo").append("<img src=''>");
				$('.demo img:eq(' + index + ')').attr('src', 'lib/images/' + num + '.png')
				$(".demo img").animate({
					top: "-500px",
					opacity: "0",
				}, 3000)
				
			  var giveLikeUrl = window.BASEPATH + 'phoneApp/giveLike';
			  var params={};
			  params.liveId=${live.id};
			　　$.post(giveLikeUrl, $.toJSON(params), function(data){
					data = parseAjaxResult(data);
					$('.jishu').html(data.giveLike); 
				});
				/*  //计数
				var num = $('.jishu').text();
				num++;
				$('.jishu').text(num); */ 
			})
			
			$('#redpackPic').click(function(){
			    $.alert("请分享到朋友圈或分享给好友领取红包");
			    $(this).hide();
			});
			
			
			$('#btn2').click(function(){
				if( $(".pushGift").hasClass("show") ){
		            // 执行隐藏
		            $(".pushGift").fadeOut().removeClass("show");
		            // 其他
		        }else{
		            // 显示
		            $(".pushGift").fadeIn().addClass("show");
		        }
		        	 if( $('.readyBuy').hasClass("show") ){
		            // 执行隐藏
		            $('.readyBuy').fadeOut().removeClass("show");
		            // 其他
		            $('.giftNumber').val(1);
		        }  
						 
			})
			
			var max=99,min=1
			$('.numAdd').click(function(){
				var chengjie=$('.chengjie').val();
				var number=$('.giftNumber').val();
				if(number<max){
					number++;
				}else{
					number=max;
				}
				$('.giftNumber').val(number);
				$('.Allprice').text('共计：'+(chengjie*number).toFixed(2)+'元');
				
			})
			
			$('.numCut').click(function(){
				var chengjie=$('.chengjie').val();
				var number=$('.giftNumber').val();
				if(number>min){
					number--;
				}else{
					number=min;
				}
				$('.giftNumber').val(number);
				$('.Allprice').text('共计：'+(chengjie*number).toFixed(2)+'元');
			})
			
			
			
			
			
		getAdvertisement();
		pushGift();
			
				
		})
		
		function getAdvertisement(){
			     var _uriRecomment = window.BASEPATH + 'phoneApp/getAdvertisement';
				
				 $.get(_uriRecomment, null, function(data){
						data = parseAjaxResult(data);
						if(data === -1) return;
						if(data && data.length>0){
						    var html=[];
							for(var i=0; i<data.length; i++){
								html.push('<div style="height:70px;width:100%;" id="sw-'+data[i].id+'" class="swiper-slide"><img class="topmod" id="top-'+data[i].productId+'-'+data[i].classify+'" style="height:100%;width:100%;" src="'+data[i].slidepic+'" alt="">');
								html.push('<div style="font-size:12px;position:absolute;padding-left:5px;bottom:0px;color:#FFF">'+data[i].name+'</div></div>');
							}
							$('#headerWrapper').append(html.join(''));
							$("#headerSwiper").swiper({
						        loop: true,
						        autoplay: 5000
						      });
						}
				 });
			  }
			  
		
		function pushGift(){
			     var _uripushGift = window.BASEPATH + 'phoneApp/pushGift';
				
					$.post(_uripushGift, null, function(data){
						data = parseAjaxResult(data); 
						    var html=[]; 
						    /* */
							for(var i=0; i<data.length; i++){
								html.push('<div class="gift"  onclick="readyBuy('+data[i].id+','+data[i].price+')"  style="width:20%;height: 100%;background:#f3faff;display: inline-block;margin:0 auto;padding:0px;text-align: center;border: 1px solid #EEEEEE;">');
								html.push('<img src="http://www.guolaiwan.net/file/'+data[i].slidepic+'" style="width:40px;height:40px;margin-top:10px;" />');
								html.push('<p style="font-size:12px; ">'+data[i].name+'</P>');
								html.push('<p style="font-size:12px;">'+(data[i].price/100).toFixed(2)+'元</P>'); 
								html.push('</div>');
							}
							$('.pushGift').append(html.join(''));
				   }); 
			  }
		   
		   function readyBuy(giftId,giftPrice){
		 	if( $('.readyBuy').hasClass("show") ){
		            // 执行隐藏
		            $('.readyBuy').fadeOut().removeClass("show");
		            // 其他
		            $('.giftNumber').val(1);
		            $('.Allprice').text('共计：'+(giftPrice/100).toFixed(2)+'元');
		        }else{
		            // 显示
		            $('.readyBuy').fadeIn().addClass("show");
		            $('.chengjie').val((giftPrice/100).toFixed(2));
		            $('.Allprice').text('共计：'+(giftPrice/100).toFixed(2)+'元');
		            
		        }
     		      $('.readyBuy').attr('id',giftId);
		      
		 }  
		 
		 
		 	
		
		var prepay_id;
		var paySign; 
		var appId;   
		var timeStamp;   
		var nonceStr;  
		var packageStr;  
		var signType; 
		var orderNo; 
		
		function toPay(){
		
			var liveId=${live.id}
			var giftId=$('.readyBuy').attr('id')
			var num=$('.giftNumber').val()
			var userId=${userId};
			$.get(window.BASEPATH+'pubnum/gift/addOrder/'+liveId+"/"+giftId+"/"+num+"/"+userId, null, function(data){
		          orderNo = data.id
				$('.dingdanhao').val(orderNo);
		        $.get(window.BASEPATH+'pubnum/gift/pay/'+orderNo+"/"+userId, null, function(data){
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
		            	var orderId=$('.dingdanhao').val();
		            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		                //每五秒刷新订单状态
		                alert("感谢老板的打赏~~");
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") { 
		            	$.post(window.BASEPATH+'admin/live/deltipgift.do',{'orderId':orderId},function(){
		            	}) 
		                alert("交易取消");  
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {
		            	$.post(window.BASEPATH+'admin/live/deltipgift.do',{'orderId':orderId},function(){
		            	})
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
		
		
		$(document).on('click','.topmod',function(){
	       var codes=this.id.split('-');
	       if(codes[2]==''){return;}
	       if(codes[1]==0){
		       location.href='http://www.guolaiwan.net/guolaiwan/pubnum/index';
		       return;
	       }
	       location.href=window.BASEPATH + 'pubnum/merchant/index1?code='+codes[1]+'&classify='+codes[2];
	    });
		</script>

<body>
<input class="chengjie" hidden="hidden" type="number" value=""/>
<input class="dingdanhao" value="" hidden="hidden">
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">直播播放</div>
			</div>
		</div>
		<div class="content" style="">
             <div style="position: absolute;top:40px;right:5%;color:#ffffff;z-index:111111;font-size:12px;"><img style="width:15px;height:15px;display: inline-block;" alt="" src="lib/images/renshu2.png"><p style="display: inline-block;">57812</p></div>
			<video style="display: none;" id="my-video" class="video-js" width="100%" height="100%"  controls="controls" autoplay="autoplay"   x-webkit-airplay="true" x5-video-player-fullscreen="true"  preload="auto" playsinline="true" webkit-playsinline  x5-video-player-typ="h5">
		    <source type="application/x-mpegURL" src="${url}">		
			</video>

			<div id="player" style="width:100%;height:250px;display:none;">
				<script type="text/javascript" charset="utf-8"
					src="http://yuntv.letv.com/player/live/blive.js"></script>
				<script>
					var leshiyunId = '${live.leshiyunId}';
					var player = new CloudLivePlayer();
					player.init({
						activityId : leshiyunId
					});
				</script>
			</div>

			<div class="swiper-container" id="headerSwiper"
				data-space-between='10' data-pagination='.swiper-pagination'
				data-autoplay="1000">
				<div class="swiper-wrapper" id="headerWrapper" style="height:70px;">
				
				</div>
			
			</div>
			
			<div style="margin-top:10px;margin-left:10px">${live.liveName}
			
			</div>
			
			<div class="weui-cells__title" style="width:100%;height:10%;"><p >请输入评论</p>
				<div class="readyBuy" style="line-height:20px;font-size:16px;" >
				<button class="numCut" style="width:20px;height:20px;background:#ffffff;border-radius:50%;color:#18b4ed;border:1px solid #18b4ed;outline: none;">-</button>
				<input disabled="disabled" class="giftNumber" id="giftNumber"
					type="number" value="1" style="width:5%;border: none;outline: none;background:#FBFBFB;text-align: center; ">
				<button class="numAdd" style="width:20px;height:20px;background:#18b4ed;border-radius:50%;color:#ffffff;border: none;outline: none;">+</button>
				<span class="Allprice" style="position: absolute;right:20%;"></span>
				<button class="toPay" onclick="toPay()" style="position: absolute;right:2%;width:12%;height:20px;background:#18b4ed;color:#ffffff;border: none;outline: none;border-radius:12px;">打赏</button>
			</div>
			</div>
			
			<div class="weui-cells weui-cells_form">
				<div class="pushGift"></div>
				<div class="weui-cell" >
					<div class="weui-cell__bd">
						<textarea id="message" class="weui-textarea" rows="3" ></textarea>

					</div>
				</div>
			</div>
			<div style="width:100%;height:70px;position:relative;">
				<a id="save" href="javascript:void(0);"
					class="weui-btn_mini weui-btn_default"
					style="margin-top:5px;margin-left:10px;background:#18b4ed">发表评论</a>
				<div id="zong">
					<div class="demo"></div>
					<div id="btn1" class="btn"></div>
					<div class="jishu"><p style="width:100%;text-align: right;">${live.giveLike}</p></div>
					<!-- 礼物 -->
					<div id="btn2" class="btn2"></div>
					<!-- 回放 -->
					<a href="<%=basePath%>admin/live/rebroadcastslist?liveId=${live.id}"><div
							id="btn3" class="btn3"></div></a>
				</div>
			</div>
			<div class="weui-cells__title">评论列表</div>
			<div id="messageContent" style="padding-bottom:50px;"></div>



		</div>
	</div>
	<image id="redpackPic" src="https://glw-old-file.oss-cn-beijing.aliyuncs.com/file${live.redCover}" 
	style="display:none;width:80%;height:400px;position:absolute;left:10%;top:80px;z-index:2500;margin:0 auto">
</body>


</html>