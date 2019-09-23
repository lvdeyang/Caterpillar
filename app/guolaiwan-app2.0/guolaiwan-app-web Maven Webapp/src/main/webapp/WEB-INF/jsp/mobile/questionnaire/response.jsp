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
<title>答题</title>
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
	height: 100%;
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
	font-family: "微软雅黑" !important;
	
}
body{
background:url("lib/images/htnlbei.jpg");
background-size: 100%;
}
* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
}
/* 页面样式 */
.header {
	height: 100px;
	line-height: 100px;
	background: rgba(0, 0, 0, 0) !important;
}

.header .link-left {
	margin-left: 20px;
	margin-right: 10px;
	position: relative;
	z-index: 1;
	color: #fff;
	font-size: 20px;
}

.main {
	height: 100%;
	width: 80%;
	background: #FFFFF5;
	border-radius: 6px;
	margin: 0 auto;
	padding: 50px 5% 20px 5%;
	font-size: 20px;
	overflow: hidden;
}

.main-in {
	height: 450px;
	width: 85%;
	background: #fff;
	border-radius: 6px;
	margin: 0 auto;
	padding: 50px 5% 0 5%;
	font-size: 20px;
	overflow: hidden;
	z-index: 11111;
	text-align: center;
	position: fixed;
	top: 80px;
	left: 7.5%;
	display: none;
}

.lists li {
	box-shadow: 2px 2px 5px #DBDBDB;
	margin: 10px auto;
	width: 100%;
	height:auto;
	line-height: 20px;
	padding: 7px 5% 7px 10%;
	text-align: left;
	border: none;
	outline: none;
	border-radius: 8px;
	border-bottom: 1px solid #DBDBDB;
	border-right: 1px solid #DBDBDB;
	font-size:18px;
}

.game_time {
	width: 30px;
	height: 50px;
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -25px 0 0 -25px;
	text-align: center;
}
/* time scroll*/
.pie {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	position: absolute;
}

.pie1 {
	clip: rect(0px, 50px, 50px, 25px);
	-o-transform: rotate(0deg);
	-moz-transform: rotate(0deg);
	-webkit-transform: rotate(0deg);
	background-color: #D9DBDA;
}

.pie2 {
	clip: rect(0px, 25px, 50px, 0px);
	-o-transform: rotate(0deg);
	-moz-transform: rotate(0deg);
	-webkit-transform: rotate(0deg);
	background-color: #D9DBDA;
}

.hold {
	width: 50px;
	height: 50px;
	position: absolute;
	z-index: 1;
}

.bg {
	width: 50px;
	height: 50px;
	border-radius: 50px;
	position: absolute;
	background-color: #F21819;
}

.time {
	width: 40px;
	height: 40px;
	margin: 5px 0 0 5px;
	background-color: #fff;
	border-radius: 80px;
	position: absolute;
	z-index: 1;
	text-align: center;
	line-height: 40px;
	font-size: 25px;
	color: black;
}

.add {
	background: #317AE7;
	color: #fff;
}

.homes-on {
	list-style: none;
	width: auto;
	height: auto;
	overflow: hidden;
}
.homes-on
:not
 
(
:first-child
 
){
display
:
 
none
;


}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css" />
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"
	charset="utf-8"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
<link
	href="<%=request.getContextPath()%>/layui/UEditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript">
	var questions,index=0,answer,retn = true,type=1;
	/* 回答错误 */
	function falses(){
      $(".time").css({"color":"#fff","background":"#F21819","font-size":"30px"});
      $(".pie1").css("background","#F21819");
      $(".pie2").css("background","#F21819");
      $(".bg").css("background-color","#F21819");
	  $(".time").html("✖");
	  $(".btn-next").hide();
	  retn = false;
     }	
          
     /* 回答正确 */
  function trues(){
    $(".time").css({"color":"#fff","background":"#37AD3B","font-size":"30px"});
    $(".bg").css("background-color","#37AD3B");
    $(".pie1").css("background","#37AD3B");
    $(".pie2").css("background","#37AD3B");
	$(".time").html("✔");
	 retn = false;
  }	
  /* 默认状态 */
  function retns(){
    $(".time").css({"color":"black","background":"#fff","font-size":"25px"});
    $(".bg").css("background-color","#F21819");
    $(".pie1").css("background","#D9DBDA");
    $(".pie2").css("background","#D9DBDA");
  }
  
  	
    
  $(function() {
  	//领红包
 	$(document).on('click','.open',function(){
       $(".fu").fadeOut()
      location.href=window.BASEPATH + 'admin/questionnaire/sendRedPacket?questionnaireId=${questionnaireId}';
    });
    
    if($('.lists li').attr("class") != "add"){
                $(".btn-next").attr("disabled",true);
		  }
     $(".lists").on("click","li",function(){
       $('.btn-next').attr("disabled",false);
        if(type==1||type==3){
	        $(".lists li").removeClass("add");
	        $(this).addClass("add");
        }else{
        	$(this).toggleClass("add");
        }
    });
  		
	var li=document.getElementById('homes').getElementsByClassName("homes-on");
    var num=0;
    var len=li.length;

  $(document).on('click','.btn-next',function(){
     $(".time").css("color","#FFF");
  		var answers="";
  		index+=1;
  		$('.lists').find('li').each(function() {
                if($(this).attr("class") == "add"){
                	answers+=($(this).index()+"");
                	
                }
            })
  		var str=answer.replace("A","0").replace("B","1").replace("C","2").replace("D","3").replace("E","4").replace("对","0").replace("错","1");
  		if(answers !=str){
  		
  		}
  		
  		if(answers==str){
  			$('.lists li').css({"color":"#fff","background":"#F21819"});
  			$('.add').css("background","#37AD3B");
  			trues();
  		}else{
  			$('.lists').find('li').each(function() {
                if(str.indexOf($(this).index().toString())!=-1){
                	$(this).css({"color":"#fff","background":"#37AD3B"});
                }else{
                	$(this).css({"color":"#fff","background":"#F21819"});
                }
            })
  			 falses();
  			 setTimeout(function(){
  			  $(".main-in").slideDown();
			}, 1500);
  			
  		}
  		 if(index==${questionnum}){
  		 
  			if(answers==str)$('.fu').fadeIn();
  			 retn = false;
  			 gameTime = 100000;
  			clearInterval(s);
			clearInterval(t1);
			countDown();
  			return;
  		}else{
  		 setTimeout(function(){
      		clearInterval(s);
			clearInterval(t1);
			countDown();
  			pushquestion();
  			retn = true;
  			retns()
  		}, 2000);
  		} 
  
        /* li[num].style.display="none";
        num=++num==len?0:num;
        li[num].style.display="inline-block"; */
   });
   
   getquestion();

   });
			i = 0;
			j = 0;
			count = 0;
			MM = 0;
		var	SS = 60; // 秒 90s
			MS = 0;
			totle = (MM + 1) * 600;
			d = 180 * (MM + 1);
			MM = "0" + MM;
			var gameTime = parseInt("${timelimit}");
		
			//count down
			var showTime = function() {
				totle = totle - 1;
				if(totle == 0) {
					clearInterval(s);
					clearInterval(t1);
					/*  clearInterval(t2);  */
					$(".pie2").css("-o-transform", "rotate(" + d + "deg)");
					$(".pie2").css("-moz-transform", "rotate(" + d + "deg)");
					$(".pie2").css("-webkit-transform", "rotate(" + d + "deg)");
				} else {
					if(totle > 0 && MS > 0) {
						MS = MS - 1;
						if(MS < 10) {
							MS = "0" + MS
						};
					};
					if(MS == 0 && SS > 0) {
						MS = 10;
						SS = SS - 1;
						if(SS < 10) {
							SS = "0" + SS
						};
					};
					if(SS == 0 && MM > 0) {
						SS = 60;
						MM = MM - 1;
						
						if(MM < 10) {
							MM = "0" + MM
						};
					};
					
				};
				
				if(retn)$(".time").html(SS);
                    
			};
            	 	
			var start1 = function() {
				//i = i + 0.6;
				i = i + 360 / ((gameTime) * 10); //旋转的角度  90s 为 0.4  60s为0.6
				count = count + 1;
				if(count <= (gameTime / 2 * 10)) { // 一半的角度  90s 为 450
				    
					$(".pie1").css("-o-transform", "rotate(" + i + "deg)");
					$(".pie1").css("-moz-transform", "rotate(" + i + "deg)");
					$(".pie1").css("-webkit-transform", "rotate(" + i + "deg)");
				} else {
				 	$(".pie2").css("background", "#F21819");
					$(".pie2").css("-o-transform", "rotate(" + i + "deg)");
					$(".pie2").css("-moz-transform", "rotate(" + i + "deg)");
					$(".pie2").css("-webkit-transform", "rotate(" + i + "deg)");
				}
				
			    
				/* 倒计时结束 */
				  if(SS == 0){
				   $(".main-in").slideDown() 
			      falses()
						} 
					
			};
            
			var start2 = function() {
				j = j + 0.6;
				count = count + 1;
				if(count == 300) {
					count = 0;
					 clearInterval(t2);
					t1 = setInterval("start1()", 100);
				}
				$(".pie2").css("-o-transform", "rotate(" + j + "deg)");
				$(".pie2").css("-moz-transform", "rotate(" + j + "deg)");
				$(".pie2").css("-webkit-transform", "rotate(" + j + "deg)");
			}

			var countDown = function() {
				//80*80px 时间进度条
				i = 0;
				j = 0;
				count = 0;
				MM = 0;
				SS = gameTime;
				MS = 0;
				totle = (MM + 1) * gameTime * 10;
				d = 180 * (MM + 1);
				MM = "0" + MM;
               
				showTime();
             
				s = setInterval("showTime()", 100);
				start1();
				//start2();
				t1 = setInterval("start1()", 100);
					
			}
	
						countDown();
				 
		</script>
<script>

	function getquestion() {
		var url = window.BASEPATH + 'admin/questionnaire/getquestion';
		$.post(url, {
			"questionnaireId" : "${questionnaireId}"
		}, function(data) {
			questions = data;
			pushquestion();
		})
	}

	function pushquestion() {
		var options = questions[index].options.split("#");
		type=questions[index].questiontype;
		answer = questions[index].answer;
		$('.btn-next').attr("disabled",true);
		if(questions[index].questiontype==1)$('.type').text('单选题');
		if(questions[index].questiontype==2)$('.type').text('多选题');
		if(questions[index].questiontype==3)$('.type').text('判断题');
		$('.questiontitle').html(questions[index].topic);
		var html = [];
		for (var i = 0; i < options.length; i++) {
			html.push('<li id="' + i + '">' + options[i] + '</li>');
		}
		$('.lists').children().remove();
		$('.lists').append(html.join(''));
	}
	
</script>
<body>
	<!-- 主页 -->
	<div class="header">
		<a class="link-left" href="#side-menu"><span
			class="icon-reorder icon-large"></span></a>
	</div>

	<div id="homes">
		<ul id="homes-in">
			<li class="homes-on">
				<div class="main" style="">
				 <p class="type" style="float:left;margin-top:-35px;font-size: 16px"></p>
					<div
						style="height:60px;width: 60px;border-radius: 50%;background:#fff;position:absolute;top:75px;left:50%;margin-left:-30px;">
						<div class="game_time">
							<div class="hold">
								<div class="pie pie1"></div>
							</div>
							<div class="hold">
								<div class="pie pie2"></div>
							</div>
							<div class="bg"></div>
							<div class="time"></div>
						</div>
					</div>
					<p class="questiontitle"
						style="width:100%;height:auto;word-break:break-all;line-height: 30px;"></p>
					<ul class="lists">
					</ul>
						<button  class="btn-next"
		style="width:20%;height:35px;padding:0 2px;border-radius:6px;line-height:35px;background:#F61C1D;text-align: center;color:#fff;font-size:14px;border:none;outline:none;float:right;margin-top:30px;">下一题
		></button>
				</div>
			</li>
		</ul>
		
	</div>

</body>
<div class="fu" style="width:100%;height:100%;background: rgba(0,0,0,0.2);position: fixed;top:0;z-index:1111;text-align: center;display: none;">
	<img class="open" style="width:90%;position: absolute;top:40%;left:50%;margin:-50% 0 0 -45%;" src="lib/images/hongbao.png"> 
	<a onclick="javascript :history.back(-1);"><img  style="width:25%;position: absolute;top:72%;left:50%;margin-left:-12.5%;" src="lib/images/close.png"></a>
</div>

<div class="main-in" style="">
	<img style="width:50%;" src="lib/images/wrong.png">
	<p style="width:100%;font-size:24px;font-weight:400;margin-top:10%;text-align: center; ">很遗憾，请再接再厉!</p>
	<button
		style="font-weight:500;font-size:18px;margin-top:10%;color:#fff;background: #F61C1D;width:60%;border-radius:16px;padding:4px 0;border:none;outline:none;"
		onclick="javascript:location.reload();">再答一次</button>
</div>

</html>