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
<title>匠心非遗  寻味遵化  赶快为喜欢的遵化美食投票吧！</title>
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
    background:#fff;
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	
}


 
* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
	/* font-size:1rem; */
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
background:#C3181E;
color:#fff;
font-weight: bold;
padding:0 2px;
border:none;
outline:none;
border-radius:6px;
/* box-shadow:0 3px #D50931; */
font-size:12px;
line-height:24px;
height:24px;
margin:5px 4% 5px 0;
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
				margin:0 0 20px 2.5%;
				color:black;
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
			.flase,.true{
			color:#fff;
			background: #C4191F;
			padding:0 10%;
			display: inline-block;
			height:32px;
			margin:0 5%;
			border-radius:10px;
			border:none;
			outline:none;
			font-weight: bold;
			}
			.Judges{
			width:80%;
			height:150px;
			background: #fff;
			margin:0 auto;
			border-radius:12px;
			border:1px solid #AEAEAE;
			text-align: center;
			font-size:14px;
			overflow: hidden;
			position: fixed;
			top:50%;
			left:50%;
			margin:-75px 0 0 -40%;
			display: none;
			z-index:11111;
			}
		#votetitle{
		/*  color: #C3181E;
	    font-weight: bold;
	    font-size:18px;
	    font-family: "Just Another Hand",cursive;
	    text-transform: uppercase; */
	    width:60%;
	    position: absolute;
	    z-index:11;
	    top:25px;
	    left:10%;
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
var base,page=1,nodata=0,isclick=true;
var votenum=${pollnum};
var buynum=${buynum};
var productId;
var ShareUrl=window.location.href;
var SharePic;
$(function() {
initShare();
    if(${logoshow=='SHOW'}){
		$('#logo').show();
	}
    if(${titleshow=='SHOW'}){
		$('#votetitle').show();
	}
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
	$(document).on('click', '.flase', function() {
 		$(".Judges").fadeOut();
 	});
 	$(".Judgess").keyup(function(){
    $(this).val($(this).val().replace( /[^0-9]/g,''));
	}).bind("paste",function(){
	    $(this).val($(this).val().replace( /[^0-9]/g,''));
	})
	getRecomment();
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
function getRecomment(){
	      var _uriMerchantInfo = window.BASEPATH+'admin/vote/allpics?optionId=${optionId}';
		$.get(_uriMerchantInfo, null, function(data){
			    	var html=[];
//这是分享出去的图片
			    	SharePic="http://www.guolaiwan.net/file"+data[0].slidepic;
				for(var i=0; i<data.length; i++){
					html.push('<div onclick="gotopicdetails(this.id)" id="'+data[i].id+'" class="swiper-slide" style="height:200px;"><img class="exampleImg" style="height:200px;" id="imgTest" src="http://www.guolaiwan.net/file'+data[i].slidepic+'" alt=""></div>');
				}
				$('.swiper-wrapper').append(html.join(''));
				$(".swiper-container").swiper({
			        loop: true,
			        autoplay: 3000
			    });
		    });
}
//获取选项卡标签
function getvotemodular(){
 var _uriRecomment = window.BASEPATH + 'judges/getvotemodular';
   $.post(_uriRecomment,{"optionId":${optionId}},function(data){
     getvoteproduct(data[0].id,page)
      var html=[];
      for(var i=0;i<data.length;i++){
          if(i==0){
              html.push('<li class="clickmodular" onclick="getvoteproduct('+data[0].id+',1)"><img src="http://www.guolaiwan.net/file'+data[0].slidepic+'"><p id="b'+data[0].id+'">'+data[0].modularName+'</p></li>');
          }else{
              html.push('<li class="clickmodular" onclick="getvoteproduct('+data[i].id+',1)"><img src="http://www.guolaiwan.net/file'+data[i].slidepic+'"><p id="b'+data[i].id+'">'+data[i].modularName+'</p></li>');
          }          
      }   
         $('#menu').append(html.join(''));
           if($("#menu").find("li").length < 2){
		      $("#menu").css("display", "none");
		    }
         $("#b"+data[0].id).css("color","#F92828");
         base=data[0].id;
   });
}
$(document).on('click','.clickmodular',function(){
	page=1;
});
//获取对应的商品
function getvoteproduct(id,page){
$("#b"+base).css("color","black");
 base=id;
 $("#b"+id).css("color","#F92828");
 	//计算总票数 用于排序
 	var url=window.BASEPATH + 'judges/sortproduct?id='+id+'&userId=${userId}&optionId=${optionId}'
/*  	$.post(url,{"id":id,"optionId":"${optionId}"},function(data){ */
 		//按照总票数查询投票商品
	    var _uriRecomment = window.BASEPATH + 'judges/getvoteproduct?id='+id+'&userId=${userId}&optionId=${optionId}&page='+page;
	    $.get(_uriRecomment,null,function(data){
	      list(data);   
	    });
 	/* }) */
} 
	//搜索商品
 	function search(){
    	if($('.search').val()==""){
    		$.toast('请输入关键字', 'text');
    		return;
    	}
    	var name=$('.search').val();
    	var url = window.BASEPATH + 'judges/getproductbyname';
    	$.post(url,{"userId":${userId},"name":name,"optionId":"${optionId}"},function(data){
    				$("#b"+base).css("color","black");
    				list(data);
    	})
    }
	//投票方法
	function votepoll(id){
		if(isclick==false)return;
		if(isclick==true)isclick=false;
		var url=window.BASEPATH + 'judges/votepoll?userId=${userId}&productId='+id+'&optionId=${optionId}';
		$.get(url,null,function(data){
			var count=parseInt(data.count)+1;
			var pollnum=parseInt(data.pollnum)-1;
			var votes=parseInt($('#votes'+id).html())+1;
			if(data.msg=="1"){
				$(".tanchuang"+id).show(300).delay(1000).hide(300); 
				$('#polled'+id).html(count);
				$('#pollnum'+id).html(pollnum);
				$('#votes'+id).html(votes);
				  setTimeout(function(){
				  isclick=true;
				  getvoteproduct(base,page);	
				    },1000);
	           	
			}else if(data.msg=="0"){
				$.toast(votenum+'票/商品/人/天', 'text');
				isclick=true;
				getvoteproduct(base,page);
			}
		})
	}
	//购买方法
	function gotobuy(id){
		var url=window.BASEPATH + 'judges/buypoll';
		$.post(url,{"usreId":${userId},"productId":id,"optionId":"${optionId}"},function(data){
			if(data.msg=="1"){
	   			location.href=window.BASEPATH + 'pubnum/product/index?id='+id+'&vote=YES';
			}else if(data.msg=="0"){
				$.toast(buynum+'次/商品/人', 'text');
			}else if(data.msg=="2"){
				$.toast('哎呀，出了点小问题！', 'text');
			}
		})
    }
    //评分弹窗
    function mark(id){
    	$(".Judges").fadeIn();
 		$(".Judgess").val("");
 		productId=id;
 		
    }
    //评分方法
    function score(){
    	var mark=$(".Judgess").val();
	    if(mark >= 100) mark=100;
		if(mark<= 1) mark=1;
    	var url=window.BASEPATH + 'judges/makescore';
    	$.post(url,{"userId":"${userId}","score":mark,"productId":productId},function(data){
    		if(data=="success"){
    			$(".Judges").fadeOut();
    			$.toast('感谢您，评分成功！', 'text');
    			getvoteproduct(base,page);
    		}
    	})
    }
    function gotopicdetails(id){
    	location.href=window.BASEPATH + 'admin/vote/gotovotepicdetails?picId='+id;
    }
    function productdetails(id){
    	location.href=window.BASEPATH + 'admin/vote/gotovoteproductdetails?productId='+id;
    }
	//展开数据
	function list(data){
		var html=[];
		if(data.length==0){
			nodata=1;
			html.push('<p style="height:100px;line-height:70px;text-align:center;width:100%;color:#DADADA;">没有搜索到相关的商品</p>');
			 //开始的时候这里放开 
			  $('.contentt-box').children().remove();
   			 $('.contentt-box').append(html.join('')); 
   			 return;
		}
		if(data.length>0){
		  $(".weui-loadmore").hide();
		}
     for(var i=0;i<data.length;i++){
     var productvotes=parseInt(data[i].productvotes);
           html.push('<div style="width:47%;margin:10px 1%;height:auto;float:left;position: relative;min-height:auto;">');
           if(i==0){
             //html.push('<img style="width:30px;height:30px;position: absolute;z-index:111;top:-15px;left:-15px;transform:rotate(-45deg);" src="lib/images/king.png">');
           }
           html.push('<div style="z-index:11;width:100%;height:auto;background:#fff;border-radius:12px;float:left;position: relative;overflow: hidden;border: 2px solid #F92828;">');
           html.push('<div style="width:100%;height:60%;position: relative;">');
          /*  if(i==0){
             html.push('<span style="color:#fff;background: #FF1A48;padding:3px 8px;font-size:18px;font-weight: bold;position: absolute;sss">NO'+1+'</span>');
           }
           if(i==1){
             html.push('<span style="color:#fff;background: #FF1A48;padding:3px 8px;font-size:18px;font-weight: bold;position: absolute;sss">NO'+2+'</span>');
           }
           if(i==2){
             html.push('<span style="color:#fff;background: #FF1A48;padding:3px 8px;font-size:18px;font-weight: bold;position: absolute;sss">NO'+3+'</span>');
           } */
           html.push('<img onclick="gotoproductdetails(this.id)" id="'+data[i].productId+'" style="width:100%;height:100%;z-index:111;" src="http://www.guolaiwan.net/file'+data[i].image+'"> ');
           //html.push('<p style="position:absolute;right:0;bottom:0;background: #C3181E;color:#fff;font-size:12px;padding:0 5px;height:20px;line-height:20px;">已售：<span>'+data[i].OutOfPrint+'</span>+</p>');
           html.push('</div>');
           html.push('<div class="xia" style="width:100%;height:auto;position: relative;padding:5px 7.5% 3px 7.5%;color:black;text-align: center;">');
           html.push('<P style="height:20px;line-height: 20px;width:100%;color:black;text-align:left;"><span style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:95%;display: inline-block;">'+data[i].productname+'</span></P>');
            //html.push('<P style="height:20px;line-height: 20px;width:100%;"><span style="float:left;">投票数：<span id="votes'+data[i].productId+'">'+data[i].manvotes+'</span></span></P>');
           html.push('<p style="text-align: left;height:20px;line-height: 20px;">总投票量：<span class="add">'+data[i].allvotes+'</span></p>');
           html.push('<p style="text-align: left;height:20px;line-height: 20px;">排名：<span>'+data[i].ranking+'</span></p>');
           //html.push('<P style="height:20px;line-height: 20px;width:100%;text-align:left;">评委评分：<span>'+data[i].avg+'</span></p>');
          /*  if(${isjudges==1}){
		    html.push('<button id="'+data[i].productId+'" onclick="mark(this.id)" style="width:42%;float:left;" class="vote">评分</button>');           
           }else{ */
           html.push('<button id="'+data[i].productId+'" onclick="votepoll(this.id)" style="width:100%;text-align:center;" class="vote">投票</button>');
          /*  } */
           //html.push('<button id="'+data[i].productId+'" onclick="productdetails(this.id)" style="width:42%;float:left;">查看评分</button>');
           //html.push('<button style="width:88%;float:left;margin:0 0 10px 0;" onclick="gotobuy('+data[i].productId+')">购买</button>');
           html.push('</div>');
           html.push('</div>');
           html.push('<div class="tanchuang'+data[i].productId+'" style="text-indent:2em;display: none;z-index:111111;width:90%;height:auto;font-weight:bold;background:#fff;bottom:5px;left:5%;border:2px solid #C3181E;border-radius:12px;padding:20px 10%;position: absolute;">');
	    html.push('<P>您已经成功投票<span id="polled'+data[i].productId+'"></span>次，还有<span id="pollnum'+data[i].productId+'"></span>次投票机会。</p>');
	    html.push('</div>');
           html.push('</div>');         
     }
     	if(${downpic!=""}){
       	html.push('<img id="downpic" style="width:100%;margin:10px 0px;display:none;" src="${downpic}">');
         }  
         //开始的时候这里放开 
         nodata=0;
        $('.contentt-box').children().remove();
        $('.contentt-box').append(html.join(''));  
        if(${downpicshow=='SHOW'}){
		$('#downpic').show();
	}
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
                            title: '匠心非遗  寻味遵化  赶快为喜欢的遵化美食投票吧！', // 分享标题
                            link: ShareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                            imgUrl: SharePic, // 分享图标
                            success: function () {
                            }
                        });
	            wx.onMenuShareAppMessage({
					title : '匠心非遗  寻味遵化  赶快为喜欢的遵化美食投票吧！', // 分享标题
					desc: '  ', // 分享描述
					link: ShareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                    imgUrl: SharePic, // 分享图标
					success : function() {
						
					}
				});
	       });
        }
	function gotoproductdetails(id){
		location.href=window.BASEPATH + 'admin/vote/getoproductdetails?productId='+id;
	}
	function changepage(srt){
	 $(".weui-loadmore").show();
	 $(".weui-loadmore").css("bottom","-10px");
		if(srt=="1"){
			page=1;
			getvoteproduct(base,page);
		}else if(srt=="2"){
			if(page==1){
				getvoteproduct(base,page);
			}else{
				page-=1;
				getvoteproduct(base,page);
			}
		}else if(srt=="3"){
			if(nodata==0){
				page+=1;
				getvoteproduct(base,page);
			}else{
				$.toast('没有下一页了~', 'text');
			}
		}
	}
</script>
<body>
		    <div class="content" id="content"  style="position: relative;">
			<div class="swiper-container" id="headerSwiper" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
			  <div class="swiper-wrapper" id="headerWrapper" style="height:200px;">
			  </div>
			</div>
			<div class="weui-loadmore" style="position: fixed;bottom:40%;left:50%;margin-left:-30%;">
			  <i class="weui-loading"></i>
			  <span class="weui-loadmore__tips">正在加载</span>
			</div>
			<!-- 标题 -->
			<div class="nav">
			<img id="logo" style="width:25%;float:left;margin-left:3%;display: none;" src="${logo}">
		    <div id="votetitle" style="margin-left:3%;display: none;">${title}</div>
			</div> 
			<!--  <img style="width:10%;position: absolute;z-index:111111111111;top:10px;left:55%;" src="lib/images/logoss.png"> -->
		    </div> 
		<%--  <div style="width:85%;height:auto;border:1px solid #C3181E;border-radius:12px;margin:10px auto;padding:0 3%;">
		    <P style="height:50px;line-height: 50px;color:#C3181E;font-weight: bold;font-size:26px;text-align: center;">评分规则</P>
		    ${voterule}
		  </div> --%>
		     <!-- 搜索  -->
		  <div style="height:40px;width:100%;line-height: 40px;text-align: center;background: #fff;position: relative;margin:20px 0;">
		   <input placeholder="搜索" class="search" style="padding:0 15%;width:70%;line-height:30px;position: absolute;top:5px;margin-left:-35%;height:30px;border-radius:18px;outline: none;border:none;border:1px solid #E0E0E0;background:#fff;text-align: center; " type="text">
		   <img style="width:20px;height:20px;position: absolute;right:20%;top:10px;" onclick="search()" src="lib/images/sousuo.png"/>
		  </div>
			<div class="zong" style="width:100%;height:auto;margin:0 auto;position: relative;padding:0 5%;">
			  	<div class="tab"  style="">
					<ul class="tab-btn active" id="menu" style="">
					</ul>
					<div class="contentt-box" style="">
					<!-- <p style="text-align: center;font-szie:18px;">投票尚未开始，敬请期待</p> -->
							<div class="contentt active" style="text-align: center;width:100%;overflow-y:auto; -webkit-overflow-scrolling: touch">
							<!-- 开始的时候这里去掉 -->
							<!-- <p style="text-align: center;">活动尚未开始，敬请期待！~</p>  -->
							</div>
							<div class="contentt" style="text-align: center;">
							</div>
					</div>
				</div>
			</div>
		</div>
 </div>
	 		<div class="Judges" style="">
	         <p style="height:50px;line-height: 50px;"><span style="color:#C4191F;">妈妈私厨-鸡肉</span>评委评分</p>
	         <p style="height:50px;line-height: 50px;color:#C4191F;">
	         <input class="Judgess" maxlength='3' style="width:25%;height:30px;border-radius:12px;border:1px solid #C4191F;padding:0 3%;text-align: center;margin:0 5px;" type="text" >分
	         </p>
	         <p style="height:50px;margin:0 auto;">
	         <button class="flase" style="">取消</button>
	         <button class="true" onclick="score()" style="">确定</button>
	         </p>
	      </div>	 
	    <!-- 置顶 -->
		<div><a href="javascript:;" class="gotop" style="display:none;"><img  style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
<div style="text-align:center;width:100%;overflow: hidden;">
<button type="button"  onclick="changepage('1')" class="weui_btn weui_btn_mini weui_btn_default">首页</button>
<button type="button"  onclick="changepage('2')" class="weui_btn weui_btn_mini weui_btn_default">上一页</button>
<button type="button"  onclick="changepage('3')" class="weui_btn weui_btn_mini weui_btn_default">下一页</button>
</div>
<div style="height: 40px"></div>
</body>
</html>