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
<title>美食大赛</title>
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

	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
}
.zong{
	background:url("lib/images/meishibeijing.png") no-repeat;
	background-size:cover;
    overflow: hidden;
}
 
* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	touch-action: none;
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
.xia button{
background:#FF1A48;
color:#FFF03B;
font-weight: bpld;
padding:0 8px;
border:none;
outline:none;
border-radius:6px;
box-shadow:0 3px #D50931;
font-size:12px;
font-weight:bold;
margin:0 6%;
}
.di{

}
.di::before{
  content:"";
  position:absolute;
  left:0;
  bottom:-40px;
  width:100%;
  height:30px;
  box-sizing:border-box;
  border-bottom:1px solid deeppink;
  transform-origin:bottom center;
  transform:rotateZ(140deg) scale(1.414);
  /* animation:slash 5s infinite ease; */
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
.lists img{
width:30px;
height:30px;
margin:0 5px;
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
			
			/*定义选项卡内容*/
			.contentt{
				display: none;
				width:auto;
				height:auto;
			}
			/*显示动态选项卡*/
			.active{
				display: block;
			}
			.tab-btn li{
				list-style: none;
				float: left;
				width: 17%;
				height: 30px;
				font-weight: bold;
				line-height: 20px;
				font-size: 14px;
				text-align: center;
				margin:0 0 20px 3%;
				color:#fff;
				
			}
			.tab-btn li img{
			height:25px;
			width:25px;
			}
			.btn-active{
				/* background: orange; */
				z-index:11111;
				color:#FFEF39 !important;
				
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
<script>
var base;

/* $(document).on('click', '.vote', function(){ 
  $(".fuceng").fadeIn();
  $(".tanchuang").fadeIn();
}); */
  $(document).on('click', '.return', function(){ 
  $(".fuceng").fadeOut();
  $(".tanchuang").fadeOut();
});
  $(document).on('click', '.guize-in', function(){ 
  $(".fuceng").fadeOut();
  $(".guize").fadeOut();
});
$(document).on('click', '.btns', function(){ 
  $(".fuceng").fadeIn();
  $(".guize").fadeIn();
});
 $(document).on('click', '.dianping', function(){ 
   $(".fuceng").fadeIn();
  $(".lists").fadeIn();
});
 $(document).on('click', '.fuceng', function(){ 
  $(".fuceng").fadeOut();
  $(".lists").fadeOut();
});

$(function() {
	<!--选项卡  -->
	$(".tab-btn li").click(function(){
	//为按钮添加样式
	$(this).addClass("btn-active")
	.siblings()
	.removeClass("btn-active");
				
	//获取点击按钮的索引
	var index = $(this).index();
				
	/*通过索引找到对应的content,并显示:其他content隐藏*/
	var jq = $(".contentt-box .contentt").eq(index)
	.addClass("active").siblings().removeClass("active")
			})
	getvotemodular();
	});
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

//获取选项卡标签
function getvotemodular(){
 var _uriRecomment = window.BASEPATH + 'judges/getvotemodular';
   $.post(_uriRecomment,null,function(data){
   
     getvoteproduct(data[0].id)
      var html=[];
      for(var i=0;i<data.length;i++){
          if(i==0){
              html.push('<li  onclick="getvoteproduct('+data[0].id+')"><img src="http://www.guolaiwan.net/file'+data[0].slidepic+'"><p id="b'+data[0].id+'">'+data[0].modularName+'</p></li>');
          }else{
              html.push('<li  onclick="getvoteproduct('+data[i].id+')"><img src="http://www.guolaiwan.net/file'+data[i].slidepic+'"><p id="b'+data[i].id+'">'+data[i].modularName+'</p></li>');
          }          
      }
         $('#menu').append(html.join(''));
         $("#b"+data[0].id).css("color","#FFF03A");
   });
}

//获取对应的商品
function getvoteproduct(id){
$("#b"+base).css("color","#fff");
 base=id;
 $("#b"+id).css("color","#FFF03A");
    var _uriRecomment = window.BASEPATH + 'judges/getvotemodular?id='+id;
    $.get(_uriRecomment,null,function(data){
      var html=[];
      for(var i=0;i<data.length;i++){
            html.push('<div style="width:47%;margin:10px 1%;height:auto;float:left;position: relative;">');
            if(i==0){
              html.push('<img style="width:30px;height:30px;position: absolute;z-index:111;top:-15px;left:-15px;transform:rotate(-45deg);" src="lib/images/king.png">');
            }
            html.push('<div style="z-index:11;width:100%;height:200px;background:#fff;border-radius:12px;float:left;position: relative;overflow: hidden;border: 2px solid black;">');
            html.push('<div style="width:100%;height:60%;position: relative;">');
            if(i==0){
              html.push('<span style="color:#fff;background: #FF1A48;padding:3px 8px;font-size:18px;font-weight: bold;position: absolute;sss">NO'+1+'</span>');
            }
            if(i==1){
              html.push('<span style="color:#fff;background: #FF1A48;padding:3px 8px;font-size:18px;font-weight: bold;position: absolute;sss">NO'+2+'</span>');
            }
            if(i==2){
              html.push('<span style="color:#fff;background: #FF1A48;padding:3px 8px;font-size:18px;font-weight: bold;position: absolute;sss">NO'+3+'</span>');
            }
            html.push('<img style="width:100%;height:100%;" src="http://www.guolaiwan.net/file'+data[i].image+'">');
            html.push('<P style="height:20px;line-height: 20px;font-size:12px;width:100%;color:#fff;background:#000000;opacity:0.6;position: absolute;bottom:0;"><span style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:60%;display: inline-block;">'+data[i].productname+'</span><span style="float:right;">已售<span>'+data[i].OutOfPrint+'</span>+</span></P>');
            html.push('</div>');
            html.push('<div class="xia" style="width:100%;height:40%;position: relative;background: #FFF03B;padding:0 3% 10px;color:#FF1A48;text-align: center;font-weight:bold;">');
            html.push('<P style="height:20px;line-height: 20px;font-size:12px;width:100%;"><span style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:50%;display: inline-block;float:right;">'+data[i].productname+'</span><span style="float:left;">总票：<span>'+data[i].OutOfPrint+'</span>票</span></P>');
            html.push('<p style="height:25px;line-height: 25px;text-align: left;"><span><img style="width:12px;height:12px;" src="lib/images/toupiao.png">'+data[i].ticketnumber+'票</span><span class="dianping" style="float:right;"><img style="width:12px;height:12px;" src="lib/images/dianzanss.png">评委点评</span></p>');
            html.push('<button onclick="votepoll('+data[i].productId+')"  class="vote">投票</button>');
            html.push('<button onclick="gotovoteproductdetails('+data[i].productId+')">购买</button>');
            html.push('</div>');
            html.push('</div>');
            html.push('<div class="di" style="margin:5px 0 0 3%;width:100%;height:200px;background:#fff;border-radius:12px;position: absolute;overflow: hidden;border: 2px solid black;">');
            html.push('</div>');
            html.push('</div>');         
            
      }
       $('.contentt-box').children().remove();
       $('.contentt-box').append(html.join(''));    
    });
} 
	
	function votepoll(id){
		var url=window.BASEPATH + 'judges/votepoll?userId=${userId}&productId='+id;
		$.get(url,null,function(data){
			if(data.msg=="1"){
				$(".fuceng").fadeIn();
	  			$(".tanchuang").fadeIn();
			}else if(data.msg=="0"){
				$.toast('5票/商品/人', 'text');
			}
		})
	}

	function gotovoteproductdetails(id){
   		location.href=window.BASEPATH + 'admin/vote/gotovoteproductdetails?productId='+id;
    }

</script>


<body>
			<!-- 主页 -->
		<!-- <div class="header">
			<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
		</div> -->
		    <div class="content" id="content" >
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
		    </div>
		  
		   
		
		   
		   
		<div class="zong" style="width:100%;height:auto;margin:0 auto;overflow: hidden;position: relative;padding:0 10%;">
		    
		       	 <!-- 选项卡 -->
		     
		  <div class="tab">
				<ul class="tab-btn active" id="menu">
					
				</ul>
					
		<div class="contentt-box" >
				<div class="contentt active" style="text-align: center;">
				     <!-- 循环  -->

				</div>
				
		<div class="contentt" style="text-align: center;">
				   <!-- 循环  -->
		
		</div>
				
				
		</div>
		 
		     
		     
		     
			</div>
		</div>
	</div>
		    
		    
		   
	
		  </div>
		 
	 
		 
		 
		 
		 
		<!-- 规则 --> 
		<button class="btns" style="color:#fff;background: #F25566;width:45px;border-radius:18px;position: absolute;top:15px;right:2%;border:none;outline:none;z-index:111;">规则</button>
		<div class="guize" style="z-index:11111;width:90%;height:300px;padding:0 3% 20px 3%;overflow-x: hidden;text-align: center;background: #8DD0FB;position: fixed;top:10%;border-radius: 10px;margin:0 5% -150px;">
	     <p class="guize-in" style="float: right;background: #fff;border-radius:50%;width:20px;height:20px;font-weight: bold;margin-top:10px;">x</p>
	      <p style="margin-top:40px;color:#F44F60;font-weight: bold;font-size:20px;">投票规则/说明</p>
	      <ul style="color:#fff;line-height: 20px;font-size: 12px;text-align: left;">	       
	       <li>一:投票时间：2019年7月20日起至10月20日止</li>
	       <li>二:投票说明：2019年7月20日起至10月20日止</li>
	       <li>三:投票规则：2019年7月20日起至10月20日止</li>
	      </ul>
	    </div>  
		 
	 <div class='fuceng' style=""></div>
	 <!-- 投票 -->
     <div class="tanchuang" style="display: none;z-index:11111;width:80%;height:300px;padding:0 2% 50px 2%;overflow-x: hidden;text-align: center;background: #8DD0FB;position: fixed;bottom:50%;border-radius: 20px;margin:0 10% -150px;">
	    <img style="width:150px;margin-top:30px;" src="lib/images/true.png">
	    <p style="color:#fff;height:40px;line-height: 40px;font-size:18px;font-weight: bold;letter-spacing:3px;  ">投票成功</p>
	    <p style="color:#fff;height:40px;line-height: 40px;">投票剩余<span>2</span>次</p>
	    <button class="return" style="border-radius:8px;color:#fff;background: #FED44C;font-size:18px;font-weight: bold;letter-spacing:3px;width:60%;height:35px;border:none;outline:none;position: absolute;bottom:20px;left:50%;margin-left:-30%;">返回</button>
	  </div> 
		  
		 <!--评委列表 -->
     <div class="lists" style="display: none;z-index:11111;width:80%;height:300px;padding:0 0 30px ;overflow-x: hidden;text-align: center;background:#fff;position: fixed;bottom:50%;border-radius: 10px;margin:0 10% -150px;">
	     <p style="height:70px;line-height: 70px;background: #8DD0FB;"><img src="lib/images/xingxing.png"><img src="lib/images/xingxing.png"><img src="lib/images/xingxing.png"></p>
	     <p style="height:30px;line-height: 30px;background: #8DD0FB;color:#fff;font-size:18px;font-weight:bold;">评分列表</p>
	     
	     <p style="color:#FD775C;font-size:20px;font-weight:bold;height:40px;line-height: 40px;"><span style="margin:0 8%">姓名</span><span style="margin:0 8%">分数</span></p>
	     <ul>
	      <li> <p style="color:#FD775C;font-size:16px;font-weight:bold;height:30px;line-height:30px;"><span style="margin:0 8%">姓名</span><span style="margin:0 8%">分数</span></p></li>
	      <li> <p style="color:#FD775C;font-size:16px;font-weight:bold;height:30px;line-height:30px;"><span style="margin:0 8%">姓名</span><span style="margin:0 8%">分数</span></p></li>
	      <li> <p style="color:#FD775C;font-size:16px;font-weight:bold;height:30px;line-height:30px;"><span style="margin:0 8%">姓名</span><span style="margin:0 8%">分数</span></p></li>
	      <li> <p style="color:#FD775C;font-size:16px;font-weight:bold;height:30px;line-height:30px;"><span style="margin:0 8%">平均</span><span style="margin:0 8%">分数</span></p></li>
	     </ul>
	 </div>  
	 
	 
	 
	    <!-- 置顶 -->
		<div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
</body>
 




</html>