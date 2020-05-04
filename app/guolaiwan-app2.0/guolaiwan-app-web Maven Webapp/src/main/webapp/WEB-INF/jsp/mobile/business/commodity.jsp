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
<title>购票商品详情</title>
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
	height:auto;
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
	.jieshao ul li p{
	 margin:0;
	 font-weight: bold;
	}

	.jieshao ul li{
	 line-height: 40px;
	 border-bottom:1px solid #DFDFDF;
	}
.fangxing p{
 margin:0;
}
#proContent img{
    width:100%;
  
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
<script src="https://cdn.bootcss.com/vConsole/3.2.0/vconsole.min.js"></script>
<script>

</script>
<script type="text/javascript">
    var share={};
    var proName='';
    var proPic='';
    $(function(){
    //隐藏头部电话
$("#phone").hide();
	  getallteam(); 
	  getComment();
	  //var vConsole = new VConsole();
  })
	 //全局获取商品内容 
	 function getallteam(){
     var _uricoms = window.BASEPATH + 'product/package/commodity/info?merchantId=${merchantId}&proId=${proId}&choice=0';	
     $.get(_uricoms, null, function(msg){   
     console.log(msg) 
       var html = []; 
       	merchantPic = msg.webUrl;
        var pics=msg.product.productMorePic.split(',');
	 	for(var i=0; i<pics.length; i++){
		var str = pics[i].split('.');
		if(str[3]!="mp4"&&str[3]!="MP4"){ 
		html.push('<div class="swiper-slide" style="height:200px;"><img class="exampleImg" style="height:200px;" id="imgTest" src="'+merchantPic+''+pics[i]+'" alt=""></div>');
		proPic=merchantPic+''+pics[i];
		}else{
		html.push('<div class="swiper-slide" style="height:200px;"><video class="exampleImg" style="height:200px;width:100%;" src="'+merchantPic+''+pics[i]+'" controls="controls" ></div>');
		}
	}
		$('.swiper-wrapper').append(html.join(''));
		$(".swiper-container").swiper({
	        loop: true,
	        autoplay: 3000
	    });	
       //商品信息
       var merchantMessage =  msg.merinfo;
       var prouctMessage =  msg.proinfo;
       var ticketOrderNumber = msg.ticketOrderNumber;
       productPic = msg.proinfo[0].productIntroduce;
       $('#proContent').html(productPic);
       var html = []; 
       
       
     	proName=prouctMessage[0].productName;
     	
       html.push('<ul>');
       html.push('<li><p style="font-size:18px;">'+prouctMessage[0].productName+'</p></li>');
       html.push('<li><p><span style="font-size:18px;color:#EA6B1F;">'+msg.grade+'分</span><span style="margin:0 5px;color:#DFDFDF;">|</span><span>好评率'+msg.feedback+'%</span></p></li>');
       html.push('<li><p>开放时间：'+msg.beginDate+'至'+msg.endDate+'</p></li>'); 
       html.push('<li><p><img style="width:25px;height:25px;" src="lib/images/dingweis.png">地址：<a href="https://apis.map.qq.com/uri/v1/routeplan?type=drive&to='+merchantMessage[0].shopAddress+'&tocoord='+merchantMessage.shopLongitude+','+merchantMessage[0].shopLatitude+'&policy=1&referer=2FNBZ-52HR4-OHEUW-XT2S7-ZJABQ-OJFIJ">'+merchantMessage[0].shopAddress+'</a></p></li>');
       html.push('<ul>');
       $(".jieshao").append(html.join(""));
       
       //票种选择
       var htm = [];
       htm.push('<div style="width:90%;height:auto;background:#E9EBEA;margin:10px auto;border-radius:10px;">');
       htm.push('<p style="height:40px;line-height: 40px;">');
       htm.push('<span style="float:left;margin-left:5px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:65%;">'+prouctMessage[0].productName+'</span>');
       htm.push('<span style="float:right;margin-right:5px;color:#EB6E1E;font-size:18px;font-weight:bold;">￥'+prouctMessage[0].productPrice+'</span>');
       htm.push('</p>');
       htm.push('<p style="margin-left:5px;font-size:12px;"><span style="color:#81D4FD;">提前两小时订票 </span><span style="color:#EB6E1E;">出票后可立即入园</span></p>');
       htm.push('<p style="height:40px;line-height: 40px;"><span style="float:left;margin-left:5px;">月销'+ticketOrderNumber+'+</span>');
       htm.push('<span onclick="reserve('+prouctMessage[0].id+',0)" style="float:right;margin-right:5px;color:#fff;font-size:14px;font-weight:bold;background:#EB6E1E;line-height:30px;border-radius:10px;padding:0px 20px;">立即预订</span></p>');
       htm.push('</div>');
        $(".productlist").append(htm.join(""));
              
       //套餐票展示
       var prc =  msg.priceList;       
       var  ticket_type= msg.pComboPOs;
       var orNum = msg.oderNumList;
       for(var i = 0;i<ticket_type.length;i++){
       var htm = [];
       htm.push('<div style="width:90%;height:auto;background:#E9EBEA;margin:10px auto;border-radius:10px;">');
       htm.push('<p style="height:40px;line-height: 40px;">');
       htm.push('<span style="float:left;margin-left:5px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:65%;">'+ticket_type[i].combo+'</span>');
       htm.push('<span style="float:right;margin-right:5px;color:#EB6E1E;font-size:18px;font-weight:bold;">￥'+prc[i]+'</span>');
       htm.push('</p>');
       htm.push('<p style="margin-left:5px;font-size:12px;"><span style="color:#81D4FD;">提前两小时订票 </span><span style="color:#EB6E1E;">出票后可立即入园</span></p>');
       htm.push('<p style="height:40px;line-height: 40px;"><span style="float:left;margin-left:5px;">月销'+orNum[i]+'+</span>');
       htm.push('<span onclick="reserve('+ticket_type[i].id+',1)" style="float:right;margin-right:5px;color:#fff;font-size:14px;font-weight:bold;background:#EB6E1E;line-height:30px;border-radius:10px;padding:0px 20px;">立即预订</span></p>');
       htm.push('</div>');
        $(".productlist").append(htm.join(""));
       }      
	 });
}
	function getComment(){	
	var _uri = window.BASEPATH + 'product/package/comment?proId=${proId}';	
	$.get(_uri,null,function(msg){
	   var comment = msg.comm;
	   if(comment.length==0){ 
	    var htlr = [];
	    htlr.push('<div><p style="text-align:center;line-height:40px;height:40px;">暂无评论</p></div>');
	    $(".dianping").append(htlr.join(""));	
	   }
	  for(var i =0 ;i< comment.length;i++){
	  //商品评论 
	  var htl = [];
	  htl.push('');
	  htl.push('<div style="height:auto;">');
	  htl.push('<p style="font-size:14px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;">');
	  htl.push('<img style="width:35px;height:35px;border-radius:50%;" src="'+comment[i].userHeadimg+'">');
	  htl.push('<span>'+comment[i].userName+'</span>');
	  htl.push('</p>');
	  htl.push(' <p style="padding:0 8%;">'+comment[i].content+'</p>');
	  htl.push('</div>');
	  $(".dianping").append(htl.join(""));	
	  }
	  initShare();
	})	
 }  
  //立即预定
    function reserve(id,isCombo){    
    //套餐票
    if(isCombo==1){
     location.href=window.BASEPATH + 'product/package/payment/jump?merchantId=${merchantId}&proId=${proId}&choice=0&comboId='+id+'&isCombo='+isCombo;
    }
    //原票
    else{    
     location.href=window.BASEPATH + 'product/package/payment/jump?merchantId=${merchantId}&proId='+id+'&choice=0&isCombo='+isCombo; 
    }          
    }
    
    
        
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
                            title: proName, // 分享标题
                            link: location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: proPic, // 分享图标
                            success: function () {
                                
                            }
                        });
	            wx.onMenuShareAppMessage({
					title : proName, // 分享标题
					desc: '畅游华夏，尽在过来玩-联系电话:0315-6681288/6686299', // 分享描述
					link: location.href, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                    imgUrl: proPic, // 分享图标
					success : function() {
						
					}
				});
	            
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
		<div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		</div>
	</div>
           <div class="jieshao" style="height:auto;width:100%;padding:0 5%;background: #fff;border-radius:10px;overflow: hidden;margin-top:10px;z-index:111;">        
	     </div>
	  
	  <div class="fangxing" style="width:100%;height:auto;background:#fff;border-radius:10px;margin:10px 0;padding:0 0 30px 0;">
	    <p style="font-size:18px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;border-bottom:1px solid #BCBCBC;"><img style="width:30px;height:30px;" alt="" src="lib/images/goupiaoss.png">票种选择</p>
	    <div class="productlist">	              		          
	    </div>	     	  
	  </div> 
	 <!-- 商品详情 -->
	  <div style="width:100%;height:auto;background:#fff;border-radius:10px;padding:0 0 30px 0;overflow: hidden;margin:10px 0;">
	    <p style="font-size:18px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;border-bottom:1px solid #BCBCBC;">商品详情</p>
	    <div style="font-size:12px;padding:20px;float:left;width:100%;height:auto;margin:0 auto;overflow-x:scroll" id="proContent"></div>

	  </div> 
	   <!-- 点评 -->
	   <div class="dianping" style="width:100%;height:auto;background:#fff;border-radius:10px;padding:0 0 30px 0;margin-top:15px;">
	    <p style="font-size:18px;font-weight:bold;width:90%;margin:0 auto;height:50px;line-height: 50px;border-bottom:1px solid #BCBCBC;">用户评价</p>	  	      
	  </div>



</body>
 




</html>