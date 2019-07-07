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

<title>公众号首页</title>

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
	background-color: #fbfbfb;
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
 .header_in ul li img{
   width:8%;
   height:30px;
   float:left;
   margin:0 6%;

 }
 .header_in ul li span{
 font-size:14px;
 	width:10%;
   float:left;
   margin:10px 5% 25px 5%;
   
 }
 .header_in ul li{
  text-align: center;
 }
  .header_on p{
    line-height: 50px;
    display: inline-block;
    font-size:12px;
    color:#C0C0C0;
    
 }
  .header_on img{
   height:20px;
 }
 .header_on span{
  margin-left:5px;
 }

 .wenti ol li{
  margin:10px 0 10px 5%;
  font-weight: bold;
  color:black;
 }
 .wenti ol p{
  margin:10px 0 10px 5%;
  width: 90%;
  overflow:hidden;
  text-overflow:ellipsis; 
  white-space: nowrap;
 }
 .youxuan-in p{
  margin-left:3%;
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
	  
	
		
 		getRecomment();
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
      /**/	
	  function getRecomment(){
	    var _uriMerchantInfo = window.BASEPATH + 'phoneApp/merchantInfo?merchantID=198&userId=${userId}';
		
		 $.get(_uriMerchantInfo, null, function(data){
				data = parseAjaxResult(data);
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
					}					}
					$('#headerWrapper').append(html.join(''));
					$("#headerSwiper").swiper({
				        loop: true,
				        autoplay: 3000,
				        autoplayDisableOnInteraction : false
				      });
				}
		 });
	  }
	  
	  //下面的方法是微信分享的方法
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
      getactivity();// 封装南山首页需要活动版块的数据
      getVideoPics();// 南山攻略需要的数据
      getProblem();// 南山常见问题的列表
      guessyoulive();//猜你喜欢
	});
	// 封装南山首页需要活动版块的数据
	 function getactivity(){       
		var url = window.BASEPATH + 'business/merchant/nsActivity?id='+${merchantId}+'&comCode=${comCode}';
		$.get(url, null, function(data){
			var html=[];
			for(var i=0;i<data.length;i++){		 
			     html.push("<a onclick='getorderinfo("+data[i].id+")'><div style='position: relative;width:90%;height:110px;line-height:110px;border:none;border-bottom:1px solid #C0C0C0;border-left:none;border-right:none;margin:0 auto;'>")
			     html.push("<img  onclick='activity("+data[i].id+")' style='height:80px;width:30%;vertical-align: middle;display: inline-block;margin-left:3%;' src='http://www.guolaiwan.net/file"+data[i].img+"'/>")
			     html.push("<div class='huodong' style='display: inline-block;'>")
			     html.push("<p onclick='activity("+data[i].id+")'  style='position: absolute;top:-15px;font-size:14px;'>【活动】 "+data[i].activityRelPO.productName+"</p>")
			     html.push("<p onclick='activity("+data[i].id+")' style='position: absolute;top:20px;color:#FF4900;font-size:16px;'>&nbsp￥"+data[i].ProductPrice+"起</p>")
			     html.push("<button style='position: absolute;right:3%;top:60px;line-height:30px;font-size:14px;width:15%;outline: none;border:none;height:30px;border-radius:16px;background:#FF4900;color:#fff;' >抢 &gt;</button>")
			     html.push("</div>")
			     html.push("</div></a>")	
			     if(i==2){
			        break;
			     }		    
			}
			 $('#hd').append(html.join(''));			
		});
	}	
	
	
	function getorderinfo(id){
	    location.href=window.BASEPATH + 'business/getdetermineorder?id='+id;
	}
	
	// 南山攻略需要的数据
	  function getVideoPics(){       
		var url = window.BASEPATH + 'business/getVideoPics?merchantId='+${merchantId};
		$.get(url, null, function(data){
		    var html=[];
		   	for(var i=0;i<data.length;i++){
		   	      html.push("<div style='float:left;display:inline-block;width:30%;height:150px;margin-left:10px;margin-top:20px;border-top-left-radius: 6px;border-top-right-radius: 6px;overflow: hidden;'>");
		   	      html.push("<img style='width:100%;height:50%;border-radius: 6px;' src='"+data[i].textimg+"'/>");
		   	      html.push("<img style='border-radius:50%;width:25px;height:25px;float:left;margin:5px 5%;' src='"+data[i].userimg+"'/>");
		   	      html.push("<p style='color:#EC6D1E;margin:5px 5px;height:25px;line-height:25px;font-size:12px;'>"+data[i].username+"</p>");
		   	      html.push("<p style='color:#C0C0C0;margin:0;font-size:12px;text-indent:2em;line-height: 20px;  width:100%; height: 58px;   text-overflow:ellipsis;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp:2;overflow: hidden;'>"+data[i].textname+"</p>");
		   	      html.push("</div>");
		   	   if(i==2){
			        break;
			   }		
		    }
		     $('#gl').append(html.join(''));
		});
	} 
	 function getProblem(){       
		var url = window.BASEPATH + 'business/getProblem?merchantId='+${merchantId};
		$.get(url, null, function(data){
		 var html=[];
		 	for(var i=0;i<data.length;i++){
		 	   html.push("<li>"+data[i].content+"</li>")
		 	   html.push("<P>答：<span>"+data[i].replycontent+"</span></P>")
		 	    if(i==2){
			        break;
			   }	
		 	 }	
		 	    $('#ol').append(html.join(''));	      
		   });
		}
		
	function guessyoulive(){       		 
		var url = window.BASEPATH + 'business/guessyoulive?id='+${merchantId};
		$.get(url, null, function(data){
			var html=[];
			for(var i=0;i<data.length;i++){	
			       html.push("<a onclick='getorderinfo("+data[i].id+")'><div style='position: relative;overflow:hidden;width:90%;height:180px;line-height:180px;border:none;border-bottom:1px solid #C0C0C0;border-left:none;border-right:none;margin:0 auto;'>");
			       html.push("<img style='height:130px;width:45%;border-radius:6px;vertical-align: middle;display: inline-block;' src='http://www.guolaiwan.net/file"+data[i].url+"'/>");
			       html.push("<div class='youxuan-in' style='display: inline-block;'>");
			       html.push("<p style='position: absolute;top:-40px;font-size:14px;max-width: 100px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;'>"+data[i].ProductName+"</p>");
			       html.push("<p style='position: absolute;top:0px;font-size:12px;color:#C0C0C0;'><span style='color:#EC6D1E;'>5.0分</span>   <span>"+data[i].number+"人来过</span></p>");
			       html.push("<p style='position: absolute;top:40px;font-size:12px;color:#C0C0C0;'>739m</p>");
			       html.push("<p style='color:#EC6D1E;position: absolute;top:-40px;right:0%;font-size:14px;'>$"+data[i].ProductPrice+"起</p>");
			       html.push("<button style='position: absolute;right:3%;top:120px;line-height:25px;font-size:14px;width:20%;outline: none;border:none;height:25px;border-radius:6px;background:#FF4900;color:#fff;' >立即预订</button>");
			       html.push("</div>");
			       html.push("</div></a>");     			       
			   }
			    $('.youxuan').append(html.join(''));	      			   
		   });				  	    
}	

   function activity(id){
        location.href=window.BASEPATH + 'business/productdetails?id='+id;
   }
    function cate(){
        location.href=window.BASEPATH + 'business/cate?modularCode=0003&merchantId=${merchantId}';
   }
    function activity(){
        location.href=window.BASEPATH + '/product/activity/jump';
   }
   function group(){
   		location.href=window.BASEPATH + 'business/group?merchantId=${merchantId}';
   }  
   function wallet(){
   		location.href=window.BASEPATH + 'pubnum/wallet';
   }
   function search(){
   		location.href=window.BASEPATH + 'pubnum/search?content=';
   }
   function preferably(){
   		location.href=window.BASEPATH + 'business/gotopreferably?merchantId=${merchantId}';
   }
   function accommodation(){
   		location.href=window.BASEPATH + 'business/gotoaccommodation?merchantId=${merchantId}';
   }
   function raiders(){
   		location.href=window.BASEPATH + 'business/gotoraiders?merchantId=${merchantId}';
   }
   function picking(){
   		location.href=window.BASEPATH + 'business/gotopickinglist?merchantId=${merchantId}';
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
	
	 <!-- 分类  -->
	  <div  style="background:#fff;width:96%;height:230px;margin:0 auto;z-index:1;border-top-left-radius: 10px;border-top-right-radius: 10px;top:12px;position: relative;overflow: hidden;">
	     <div style="height:60px;width:100%;clear:both">
	     <img style="width:30px;height:30px;display:inline-block;margin:0 0.5% 0 5%;" src="lib/images/dizhiss.png"/>
	     <p style="display:inline-block;font-size:14px;font-weight: bold;color:black;margin:0;line-height: 60px;">南山长乐谷欢迎您</p>
	     <input class="inp" placeholder="关键字/词" style="float:right;margin-top:15px;margin-right:5%;width:45%;height:30px;border:none;outline: none;padding:10px 9%;border-radius:16px;background:#EEEEEE;" onfocus="search()"></input>	     
	     <img style="width:20px;height:20px;position: absolute;right:42%;top:20px;" src="lib/images/sousuo.png"/> 
	     </div> 
	    <div class="header_in" style="clear:both">
	     <ul style="">
	     <li><img src="lib/images/goupiaos.png"/></li>
   	     <li onclick="accommodation()"><img src="lib/images/zhusus.png"/></li>
         <li><img src="lib/images/tingchess.png"/></li>
         <li><img onclick="cate()" src="lib/images/meishis.png"/></li>
         <li><img src="lib/images/fenxiaos.png"/></li>
         <li><span>购票</span></li>
   	     <li onclick="accommodation()"><span>住宿</span></li>
         <li><span>停车</span></li>
         <li onclick="cate()"><span>美食</span></li>
         <li><span>分销</span></li>
         <li onclick="activity()"><img src="lib/images/huodongs.png"/></li>
   	     <li onclick="group()"><img src="lib/images/pintuans.png"/></li>
         <li onclick="picking()"><img src="lib/images/caizhais.png"/></li>
         <li onclick="raiders()"><img src="lib/images/gongluess.png"/></li>
         <li onclick="wallet()"><img src="lib/images/qianbaos.png"/></li>
         <li onclick="activity()"><span>活动</span></li>
   	     <li onclick="group()"><span>拼团</span></li>
         <li onclick="picking()"><span>采摘</span></li>
         <li onclick="raiders()"><span>攻略</span></li>
         <li onclick="wallet()"><span>钱包</span></li>
	     </ul>
	    </div> 
	  </div>
	  
	   <!-- 导览  -->
	  <div  style="width:96%;height:230px;margin:0 auto;text-align: center;background:#fff;top:24px;position: relative;overflow: hidden;">
	    <p style="font-size:14px;height:35px;line-height:35px;font-weight: bold;color:black;margin:0;">
	    <span style="margin-left:5%;float:left;">景区导览</span>
	    <span style="margin-right:8%;float:right;color:#C0C0C0;font-size:18px">&gt;</span>
	    </p>
	   <img style="width:85%;height:60%;" alt="" src="lib/images/daolantishi.png">
	    <div class="header_on" style=" text-align:center;margin:0 auto;height:50px;width:83%;">
	      <p style="float:left;"><img src="lib/images/daohangss.png"/><span>导航</span></p>
	      <p><img src="lib/images/yuyins.png"/><span>语音讲解</span></p>
	      <p style="float:right"><img src="lib/images/zidongdaolan.png"/><span>自动导览</span></p>
	    </div>
	  </div>
	     
	   <!-- 活动  -->
	  	<div  style="width:96%;height:auto;margin:0 auto;background:#fff;position: relative;top:36px;overflow: hidden;" id="hd"">
	     <p style="height:60px;line-height:60px;margin:0 5%;font-size:20px;border-bottom:1px solid #C0C0C0;"><img style="width:30px;height:30px;" src="lib/images/huodongss.png"/>活动<span style="margin-right:3%;float:right;color:#C0C0C0;font-size:14px" onclick="activity()">查看更多&gt;</span></p>
	     	
    
	  </div>	
	  
	     <!-- 攻略  -->
	  	<div  style="width:96%;height:230px;text-align:center;margin:0 auto;background:#fff;position: relative;top:48px;overflow: hidden;" id="gl">
            <p style="height:60px;line-height:60px;margin:0 5%;font-size:20px;border-bottom:1px solid #C0C0C0;"><span style="float:left;"><img style="width:30px;height:30px;" src="lib/images/gongluess.png"/>攻略  </span> <span onclick="raiders()" style="margin-right:3%;float:right;color:#C0C0C0;font-size:14px">查看更多&gt;</span></p>  
         </div>
        
         <!-- 常见问题  -->
	  	<div class="wenti"  style="width:96%;height:260px;margin:0 auto;background:#fff;position: relative;top:60px;overflow: hidden;">
            <p style="height:60px;line-height:60px;margin:0 5%;font-size:20px;border-bottom:1px solid #C0C0C0;"><span style="float:left;"><img style="width:30px;height:30px;" src="lib/images/wentis.png"/>常见问题  </span> <span style="margin-right:3%;float:right;color:#C0C0C0;font-size:14px">查看更多&gt;</span></p>     
             <ol id="ol">
             </ol>
        </div> 
         <!-- 为你优选  -->
	  	<div class="youxuan"   style="width:96%;height:790px;margin:0 auto;background:#fff;position: relative;top:72px;overflow: hidden;">
            <p style="height:60px;line-height:60px;margin:0 5%;font-size:20px;border-bottom:1px solid #C0C0C0;">
            <span style="float:left;"><img style="width:30px;height:30px;" src="lib/images/youxuans.png"/>为你优选 </span> 
            <span onclick="preferably()" style="margin-right:3%;float:right;color:#C0C0C0;font-size:14px">查看更多&gt;</span>
            </p>     
        </div> 
</body>
</html>