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
<title>拼团列表</title>
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
	background-color: #EEEEEE !important; 
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
 
  .gotop {
	    position: fixed;
	    right: 20px;
	    bottom: 50px;
	    display: block;
	    width: 50px;
	    height: 50px;
	    opacity: 0.8;
	    z-index:111111;
	}
	.pin{
	  font-size: 40px;
	  font-family: "Just Another Hand",cursive;
	  font-weight:800;
	  color:#fff;
	  text-transform: uppercase;
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
	
	  if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		    getloca();
	  } else {
		    if(comCode=='0000'){
			    comCode='0001';
			}
			getCom();
		    getModal();
			getActivityBundle();
			initSharewx();
	  }
	
	  
	  
		
	 
	
	  var loca={};
	  function getloca(){
	      
		  var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	
		  var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
		    $.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
					loca=data;
					getLoation();
				}
				
		  });
	  
	  }
	  
	  
	  function getLoation(){
	       wx.config({
	            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            appId : loca.appId, // 必填，公众号的唯一标识
	            timestamp : loca.timestamp, // 必填，生成签名的时间戳
	            nonceStr : loca.nonceStr, // 必填，生成签名的随机串
	            signature : loca.signature,// 必填，签名，见附录1
	            jsApiList : ['checkJsApi','getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
	        wx.ready(function() {
	            wx.getLocation({  
	                success: function (res) {  
	                    var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90  
	                    var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。  
	                    var speed = res.speed; // 速度，以米/每秒计  
	                    var accuracy = res.accuracy; // 位置精度  
	                    getCity(latitude,longitude);
	                    
	                },  
	                cancel: function (e) {  
	                        //这个地方是用户拒绝获取地理位置  
	                        if(comCode=='0000'){
							    comCode='0001';
							}
							getCom();
						    getModal();
							getActivityBundle();
							initSharewx();
					}
	                	
	             });     
		         wx.error(function (res) {  
		                if(comCode=='0000'){
						    comCode='0001';
						}
						getCom();
					    getModal();
						getActivityBundle();
						initSharewx();
				
		         });     
	            
	         });
	  
	  
	  }
	  
	  function getCity(la,lo){
	  
	     $.ajax({  
            url: 'http://api.map.baidu.com/geocoder/v2/?ak=yPjZB3eElPXn7zXRjcfqGCze6LCPlkmn&callback=renderReverse&location=' + la + ',' + lo + '&output=json&pois=1',  
            type: "get",  
            dataType: "jsonp",  
            jsonp: "callback",  
            success: function (data) {  
                console.log(data);  
                var province = data.result.addressComponent.province;  
                var cityname = (data.result.addressComponent.city);  
                var district = data.result.addressComponent.district;  
                var street = data.result.addressComponent.street;  
                var street_number = data.result.addressComponent.street_number;  
                var formatted_address = data.result.formatted_address;  
                if(comCode=='0000'){
				    if(district.indexOf('平谷')!=-1){
					    comCode='1003';
					    $('#headerName').html('全域休闲');
					    $('#phone').html('010-89991991');
					}else{
					    comCode='0001';
					}
				}
				getCom();
			    getModal();
				getActivityBundle();
				initSharewx();
            }  
        });  
	  
	  }
	  
	  function getCom(){
	     var _uricoms = window.BASEPATH + 'pubnum/getComs';
		
		 $.get(_uricoms, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data && data.length>0){
				    var html=[];
				   
					for(var i=0; i<data.length; i++){
					    if(data[i].comCode==comCode){
					        $('#selCom').html(data[i].comName);
					    }
					    	
					    html.push('<li><a data="'+data[i].comCode+'" href="javascript:void(0)" class="comSel">'+data[i].comName+'</a></li>');
					    
					}
					$('#com').append(html.join(''));
				}
				
		  });
	  
	  }
	  

	    
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
                            title: '畅游华夏，尽在过来玩', // 分享标题
                            link: 'http://<%=weburl%>/guolaiwan/pubnum/index', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: 'http://<%=weburl%>/lib/images/logo.jpg', // 分享图标
                            success: function () {
                               	
                            }
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
	});
</script>
<script>
$(function(){
	getgroupproduct();


    $(window).scroll(function(){
        var aa = $(window).scrollTop(); //当前滚动条滚动的距离
        var bb = $(window).height();//浏览器当前窗口可视区域高度
        var cc = $(document).height(); //浏览器当前窗口文档的高度 
      
        if(cc <= aa+bb){
            $(".youxuan").append($(".goupiao").clone()); 
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
</script>
<script>
function getgroupproduct(){
     var _uricoms = window.BASEPATH + '/business/getgroupproduct?merchantId=${merchantId}';	
     $.get(_uricoms, null, function(data){
         var html=[];
         for(var i=0;i<data.length;i++){
         		if(data[i].isgroup==1){
		         	html.push('<div class="goupiao" style="position: relative;background:#fff;width:100%;height:180px;line-height:180px;border:none;border-left:none;border-right:none;margin:0 0 10px 0;">');
			        html.push('	<img style="height:130px;width:45%;vertical-align: middle;margin-left:2%;display: inline-block;" src="http://www.guolaiwan.net/file'+data[i].productShowPic+'"/>');
			        html.push('	<div class="youxuan-in" style="display: inline-block;">');
			        html.push('  	<p style="position: absolute;top:-50px;font-weight:bold;font-size:14px;color:black;">'+data[i].productName+'</p>');
			        html.push('  	<p style="position: absolute;top:30px;font-size:14px;color:#EC6D1E;"><span style="">拼团价：</span>   <span>￥'+data[i].groupprice+'</span></p>');
			        html.push('  	<p style="position: absolute;top:50px;font-size:12px;color:black;"><span style="">原商品价：</span>   <span style="text-decoration:line-through;">￥'+data[i].productPrice+'</span></p>');
			        html.push(' 	<p style="color:black;position: absolute;top:0px;right:5%;font-size:14px">'+data[i].groupnum+'人团</p>');
			        html.push(' 	<button id="'+data[i].id+'" style="position: absolute;right:3%;top:113px;line-height:35px;font-size:14px;width:15%;outline: none;border:none;height:35px;border-radius:6px;background:#F56938;color:#fff;" onclick="grouping(this.id)">去开团</button>');
			        html.push('</div>');
			     	html.push('</div>');
	     		}
	     	}
		 $('.youxuan').append(html.join(''));
	 });
}

function grouping(id){
	location.href=window.BASEPATH + 'business/grouping?productId='+id+'&userId=${userId}';
}

</script>



<body>
			<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<div class="header-content">商户</div>
			</div>
		</div>
	   

         <!-- 拼团列表  -->
         <div style="height:120px;width:100%;background:#FF5742;text-align: center;line-height: 120px;">
          <p class="pin" style="">拼团优惠</p>
         </div>
	  	<div class="youxuan"  style="width:100%;height:auto;margin:0 auto;position: relative;overflow: hidden;">
         
        </div> 
          <!-- 置顶 -->
			<div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/hometop.png"></a></div>
</body>




</html>