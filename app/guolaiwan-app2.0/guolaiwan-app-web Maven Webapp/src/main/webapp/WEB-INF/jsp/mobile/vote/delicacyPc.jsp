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
<title>美食大赛pc</title>
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
.zong{
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
background:#C3181E;
color:#fff;
font-weight: bpld;
padding:0 15px;
border:none;
outline:none;
border-radius:6px;
box-shadow:0 3px #D50931;
font-size:12px;
font-weight:bold;
margin:5px 5%;
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
    right:2%;
    bottom: 50px;
    display: block;
    width: 6em;
    height:6em;
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
				width: 14%;
				height:auto;
				font-weight: bold;
			/* 	line-height: 2em; */
				font-size: 2em;
				text-align: center;
				margin:0 0 10px 2.5%;
				color:black;
				
			}
			.tab-btn li img{
			 width:30%;
			}
			.btn-active{
				/* background: orange; */
				z-index:11111;
				color:#FFEF39 !important;
				
			}
.nav{
   height:15em;
   width:100%;
   background-image: url('lib/images/dasaibeijing.jpg');
  background-size:100% 100%;
  position: relative;
}
.logo-in{
    color: #C3181E;
    font-weight: bold;
    font-size:4.5em;
    font-family: "Just Another Hand",cursive;
    text-transform: uppercase;
}

.paiming{
     -webkit-clip-path: polygon(50% 0, 100% 50%, 50% 100%, 0 50%);
     -moz-clip-path: polygon(50% 0, 100% 50%, 50% 100%, 0 50%);
     clip-path: polygon(50% 0, 100% 50%, 50% 100%, 0 50%);
     transition: 1s clip-path;
     border:0.2em solid #CDAD5D;
     height:3em;
     width:3em;
     background: #060606;
     line-height: 3em;
     color:#fff;
     font-weight:bold;

}
 .other::-webkit-scrollbar {
        display: none;
    }
  #remainTime{
  font-weight: bold;
  text-transform: uppercase;
  font-size:1.2em;
  height:2em;
  color:#C3181E;
  line-height: 2em;
  position:absolute;
  right:2%;
  top:20px;
  }   
 #remainTime span{
	  display: inline-block;
	  background: #C3181E;
	  color:#fff;
	  width:2em;
	  height:2em;
	  border-radius: 8px;
	  text-align: center;
	  margin: 0 5px;
	  
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
var allmodular=[];
var latermodular=1;
var flag=0;
  setTimeout(function(){

 caolilailai();

 },2000);
 
function caolilailai(){
  var scrollHeight = $(document).height();
    setTimeout(function(){
   $('.other').animate({'scrollTop':scrollHeight},5000); 
   },4000);
   if(flag!=0)home();flag=1;
 } 
function home(){
	getvoteproduct(allmodular[latermodular]);
 	latermodular+=1;
    if(latermodular==allmodular.length)latermodular=0;
}
/*  $(window).scroll(function() {
  	 if ($(document).scrollTop()<=0){
          setTimeout(function(){
 		  caolilailai();
 		  },2000);
     }
     if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
     	$('.other').animate({'scrollTop':0},2000);
     }
})  */

$(document).on('click', '.zong', function() {
 		$(".other").pause();
 	
 	});
  $(document).ready(function (){
       var nScrollHight = 0; //滚动距离总长(注意不是滚动条的长度)
       var nScrollTop = 0;   //滚动到的当前位置
       var nDivHight = $(".other").height();
       $(".other").scroll(function(){
         nScrollHight = $(this)[0].scrollHeight;
         nScrollTop = $(this)[0].scrollTop;
         if(nScrollTop + nDivHight >= nScrollHight){//底部
         $('.other').animate({'scrollTop':0},5000);
         }
         if ($('.other').scrollTop()<=0){ //顶部
             setTimeout(function(){
 		      caolilailai();
 		    },4000);
         } 
           
         });
       $('.other').mouseenter(function() {
	  $(this).stop();
	})
	 $('.other').mouseleave(function(){
	 /* $(this).start();  */
	 var scrollHeight = $(document).height();
	$('.other').animate({'scrollTop':scrollHeight},5000); 
	  
	})   
	
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


//获取选项卡标签
function getvotemodular(){
 var _uriRecomment = window.BASEPATH + 'judges/getvotemodular';
   $.post(_uriRecomment,{"optionId":${optionId}},function(data){
   
      var html=[];
      for(var i=0;i<data.length;i++){
      		allmodular.push(data[i].id);
          if(i==0){
              html.push('<li  onclick="getvoteproduct('+data[0].id+')"><img src="http://www.guolaiwan.net/file'+data[0].slidepic+'"><p id="b'+data[0].id+'">'+data[0].modularName+'</p></li>');
          }else{
              html.push('<li  onclick="getvoteproduct('+data[i].id+')"><img src="http://www.guolaiwan.net/file'+data[i].slidepic+'"><p id="b'+data[i].id+'">'+data[i].modularName+'</p></li>');
          }          
      }
         $('#menu').append(html.join(''));
         $("#b"+data[0].id).css("color","#F92828");
     getvoteproduct(data[0].id)
   });
}

//获取对应的商品
function getvoteproduct(id){
$("#b"+base).css("color","black");
 base=id;
 $("#b"+id).css("color","#F92828");
//计算总票数 用于排序
var url=window.BASEPATH + 'judges/sortproduct?id='+id+'&userId=${userId}&optionId=${optionId}'
$.post(url,{"id":id,"optionId":"${optionId}"},function(data){
    var _uriRecomment = window.BASEPATH + 'judges/getvoteproductpc?id='+id+'&optionId=${optionId}';
    $.get(_uriRecomment,null,function(data){
      var html=[],html1=[];
      for(var i=0;i<data.length;i++){
      var productvotes=parseInt(data[i].allcount);
            <!-- 1 -->
        if(i==0){
			html.push('<div style="width:30%;height:18em;position: relative;display: inline-block;">');   
			html.push('<img style="width:10em;border-radius:50%;height:10em;z-index:10;position: absolute;left:50%;margin-left:-5em;" src="'+data[i].productpic+'">');   
			html.push('<div class="paiming" style="position: absolute;top:8.2em;left:50%;z-index:11;text-align: center;margin-left:-1.5em;">');   
			html.push('<p style="font-size:2em;">1</p>');   
			html.push('</div>');   
			html.push('<img style="width:15em;height:12em;z-index:9;position: absolute;left:50%;bottom:4em;margin-left:-7.8em;" src="lib/images/guanjun.png">');   
			html.push('<div style="width:10em;height:auto;position: absolute;text-align: center;font-weight:bold;left:50%;bottom:1em;margin-left:-5em;">');   
			html.push('<p style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:10em;border-radius:10px;color:#fff;background: #F2C148;padding:0.5em 0.2em;font-size:1em;">'+data[i].productname+'</p>');   
			html.push('<p style="padding:0.2em 0.5em;font-size:1.5em;">'+productvotes+'票</p>');   
			html.push('</div>');   
			html.push('</div>');   
		}
	   	<!-- 2 -->
	   	if(i==1){
			html.push('<div style="width:30%;height:18em;position: relative;display: inline-block;float:left;left:15%;top:4em;">');   
			html.push('<img style="width:9em;border-radius:50%;height:9em;z-index:10;position: absolute;left:50%;margin-left:-4.5em;" src="'+data[i].productpic+'">');   
			html.push('<div class="paiming" style="position: absolute;top:7em;left:50%;z-index:11;text-align: center;margin-left:-1.5em;">');   
			html.push('<p style="font-size:2em;">2</p>');   
			html.push('</div>');   
			html.push('<img style="width:15em;height:12em;z-index:9;position: absolute;left:50%;bottom:5em;margin-left:-7.8em;" src="lib/images/yajun.png">');   
			html.push('<div style="width:10em;position: absolute;text-align: center;font-weight:bold;left:50%;bottom:1em;margin-left:-5em;">');   
			html.push('<p style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:10em;border-radius:10px;color:#fff;background:#BDC9C9;padding:0.5em 0.2em;font-size:1em;display: inline-block;">'+data[i].productname+'</p>');   
			html.push('<p style="padding:0.2em 0.5em;font-size:1.5em;">'+productvotes+'票</p>');   
			html.push('</div>');   
			html.push('</div>');   
		}
		<!-- 3 -->
		if(i==2){
			html.push('<div style="width:30%;height:18em;position: relative;display: inline-block;float:right;right:15%;top:4em;">');   
			html.push('<img style="width:9em;border-radius:50%;height:9em;z-index:10;position: absolute;left:50%;margin-left:-4.5em;" src="'+data[i].productpic+'">');   
			html.push('<div class="paiming" style="position: absolute;top:7em;left:50%;z-index:11;text-align: center;margin-left:-1.5em;">');   
			html.push('<p style="font-size:2em;">3</p>');   
			html.push('</div>');   
			html.push('<img style="width:15em;height:12em;z-index:9;position: absolute;left:50%;bottom:5em;margin-left:-7.8em;" src="lib/images/jijun.png">');   
			html.push('<div style="width:10em;position: absolute;text-align: center;font-weight:bold;left:50%;bottom:1em;margin-left:-5em;">');   
			html.push('<p style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:10em;border-radius:12px;color:#fff;background:#955A38;padding:0.5em 0.2em;font-size:1em;">'+data[i].productname+'</p>');   
			html.push('<p style="padding:0.2em 0.5em;font-size:1.5em;">'+productvotes+'票</p>');   
			html.push('</div>');   
			html.push('</div>');   
		    html.push('<div style="height:3em;"></div>');   
        }  
        if(i>2&&i%2==1){
        	html1.push('<div style="width:80%;background: #B4B6B5;height:6em;margin: 0.5em auto;border-radius:1em;padding:0 2%;">');
		    html1.push('<div style="width:50%;height:100%;text-align: left;align-items: center;float:left;">');
	        html1.push('<div class="paiming" style="margin:1.5em 0;display: inline-block;text-align: center;">');
			html1.push('<p style="font-size:1.5em;">'+(i+1)+'</p>');
			html1.push('</div>');
		  	html1.push('<img style="width:4em;border-radius:50%;height:4em;align-items: center;margin:0 1em 1em 1em;" src="'+data[i].productpic+'">');
		   	html1.push('<p style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:60%;color:black;display: inline-block;font-size:1.5em;margin:0 2%;">'+data[i].productname+'</p>');
		  	html1.push('<p style="color:#fff;font-size:1.5em;display: inline-block;float:right;line-height:4em;margin-right:0.5em;">'+productvotes+'票</p>');
	      	html1.push('</div>');
	      	if(i+1==data.length)continue;
	     	html1.push('<div style="width:50%;height:100%;text-align: left;align-items: center;float:left;">');
         	html1.push('<div class="paiming" style="margin:1.5em 0;display: inline-block;text-align: center;">');
			html1.push('<p style="font-size:1.5em;">'+(i+2)+'</p>');
		 	html1.push('</div>');
		  	html1.push('<img style="width:4em;border-radius:50%;height:4em;align-items: center;margin:0 1em 1em 1em;" src="'+data[i+1].productpic+'">');
	  		html1.push('<p style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width:60%;color:black;display: inline-block;font-size:1.5em;margin:0 2%;">'+data[i+1].productname+'</p>');
		  	html1.push('<p style="color:#fff;font-size:1.5em;display: inline-block;float:right;line-height:4em;margin-right:0.5em;">'+parseInt(data[i+1].allcount)+'票</p>');
	     	html1.push('</div>');
			html1.push('</div>');
        }
         
      }
       $('.main').children().remove();
       $('.main').append(html.join('')); 
       $('.other').children().remove();
       $('.other').append(html1.join(''));   
    });
});
} 
	
	function votepoll(id){
		var url=window.BASEPATH + 'judges/votepoll?userId=${userId}&productId='+id;
		$.get(url,null,function(data){
			if(data.msg=="1"){
			$(".tanchuang").show(300).delay(3000).hide(300); 
			}else if(data.msg=="0"){
				$.toast('5票/商品/人', 'text');
			}
		})
	}

	function gotovoteproductdetails(id){
   		location.href=window.BASEPATH + 'admin/vote/gotovoteproductdetails?productId='+id;
    }

</script>
<script>
	$(function(){
		setInterval("test()",3000);
	})
     function test() {
     	var url=window.BASEPATH + 'admin/vote/selectshowproduct';
         $.post(url,{"optionId":"${optionId}"},function(data){
         	if(data.length==1){
         		location.href=window.BASEPATH + 'admin/vote/gotojudgespc?optionId=${optionId}&productId='+data[0].productId;
         	}else{
         		return;
         	}
         })
     }


</script>
<script type="text/javascript"> 
var SysSecond;
var InterValObj;
  
$(document).ready(function() {
  SysSecond = parseInt($("#remainSeconds").html()); //这里获取倒计时的起始时间
  InterValObj = window.setInterval(SetRemainTime, 1000); //间隔函数，1秒执行
});
 
//将时间减去1秒，计算天、时、分、秒
function SetRemainTime() {
  if (SysSecond > 0) {
   SysSecond = SysSecond - 1;
   var second = Math.floor(SysSecond % 60);            // 计算秒     
   var minite = Math.floor((SysSecond / 60) % 60);      //计算分 
   var hour = Math.floor((SysSecond / 3600) % 24);      //计算小时
   var day = Math.floor((SysSecond / 3600) / 24);       //计算天 
 
   var hourDiv = "<span id='hourSpan'>"+ hour +"</span>";
   var dayDiv = "<span id='daySpan'>"+ day +"</span>";
   var miniteDiv = "<span id='miniteSpan'>"+ minite +"</span>";
   var secondDiv = "<span id='secondSpan'>"+ second +"</span>";
 
    $("#remainTime").html( "距结束"+dayDiv + "天" + hourDiv +"小时"+ miniteDiv + "分" + secondDiv + "秒");

  if(second == 10 && minite == 0 && hour == 0 && day == 0){//剩余时间小于或等于0的时候，就停止间隔函数
   window.clearInterval(InterValObj);
   //这里可以添加倒计时时间为0后需要执行的事件
   	window.location.href="http://www.guolaiwan.net/guolaiwan/judges/getclosing"
   
  }
 }
}
</script>

<body>
       <div class="nav">
        <img style="width:10%;position:absolute;left:2%;top:10px; " src="lib/images/zhengfu.png">
        <p style="height:2em;line-height: 2em;width:100%;font-size:2.5em;text-align: center;color:#C3181E;">2019中国·遵化</p>
         <p class="logo-in" style="vertical-align: inherit;text-align: center;">美食节大赛评选活动</p>
         <p style="text-transform:uppercase;text-align: center;font-size:1.5em;color:#C3181E;">food festival competition selection activities</p>
       </div>
       
		 
		<div class="zong" style="width:100%;height:auto;margin:0 auto;overflow: hidden;position: relative;padding:0 5%;">  
			  <!-- 选项卡 -->    
			  <div class="tab" >
					<ul class="tab-btn active" id="menu">
						
					</ul>
				
					<div class="contentt-box" style="" >
							<div class="contentt active" style="text-align: center;">
							
							</div>
							
							<div class="contentt" style="text-align: center;">
			
							</div>
				  		
			        </div> 
	  
	          </div>
	   </div>

		  <div class="main" style="width:100%;height:auto;text-align: center;margin:0 auto;">	
			</div>	
					   
			<!-- 4--无限父级  -->
		 <div class="other" style="width:100%;height:19.5em;overflow-y:auto;font-weight:bold;">     	
	      </div>
						
						
	        </div>			
	 
		 
	    <!--倒计时  -->
		<div id="remainSeconds" style="display:none">180</div> <!-- 秒 -->
		<div id="remainTime" style="">	
		</div> 
	    
	     <!-- 置顶 -->
     <!--  <div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div> -->
</body>
 




</html>