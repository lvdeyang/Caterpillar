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
<title>商户主页</title>
<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<style>
/* 对话框样式 */
input, button {
	outline: none;
}

.wenwen-footer {
	width: 100%;
	position: fixed;
	bottom: -5px;
	left: 0;
	background: #fff;
	padding: 3%;
	border-top: solid 1px #ddd;
	box-sizing: border-box;
}

.wenwen_btn, .wenwen_help {
	width: 15%;
	text-align: center;
}

.wenwen_btn img, .wenwen_help img {
	height: 40px;
}

.wenwen_text {
	height: 40px;
	border-radius: 5px;
	border: solid 1px #636162;
	box-sizing: border-box;
	width: 80%;
	text-align: center;
	overflow: hidden;
	margin-left: 2%;
}

.circle-button {
	padding: 0 5px;
}

.wenwen_text .circle-button {
	font-size: 14px;
	color: #666;
	line-height: 38px;
}

.write_box {
	background: #fff;
	width: 100%;
	height: 40px;
	line-height: 40px;
}

.write_box input {
	height: 40px;
	padding: 0 5px;
	line-height: 40px;
	width: 100%;
	box-sizing: border-box;
	border: 0;
}

.wenwen_help button {
	width: 100%;
	background: #42929d;
	color: #fff;
	border-radius: 5px;
	border: 0;
	height: 40px;
}

#wenwen {
	height: 100%;
}

.speak_window {
	overflow-y: scroll;
	height: 100%;
	width: 100%;
	top: 0;
	left: 0;
}

.speak_box {
	margin-bottom: 70px;
	padding: 10px;
}

.question, .answer {
	margin-bottom: 1rem;
}

.question {
	text-align: right;
	margin-top: 50px;
}

.question>div {
	display: inline-block;
}

.left {
	float: left;
	cursor:pointer
}

.right {
	float: right;
	cursor:pointer
}

.clear {
	clear: both;
}

.heard_img {
	height: 40px;
	width: 40px;
	border-radius: 5px;
	overflow: hidden;
	background: #ddd;
	margin-top: 10px
}

.heard_img img {
	width: 100%;
	height: 100%
}

.question_text, .answer_text {
	box-sizing: border-box;
	position: relative;
	display: table-cell;
	min-height: 60px;
}

.question_text {
	padding-right: 20px;
}

.answer_text {
	padding-left: 20px;
}

.question_text p, .answer_text p {
	border-radius: 6px;
	padding: .3rem;
	margin: 0;
	font-size: 14px;
	line-height: 40px;
	box-sizing: border-box;
	vertical-align: middle;
	display: table-cell;
	height: 40px;
	word-wrap: break-word;
}

.answer_text p {
	background: #fff;
}

.question_text p {
	background: #94EB68;
	color: #fff;
	text-align: left;
}

.question_text i, .answer_text i {
	width: 0;
	height: 0;
	border-top: 5px solid transparent;
	border-bottom: 5px solid transparent;
	position: absolute;
	top: 25px;
}

.answer_text i {
	border-right: 10px solid #fff;
	left: 10px;
}

.question_text i {
	border-left: 10px solid #94EB68;
	right: 10px;
}

.answer_text p a {
	color: #42929d;
	display: inline-block;
}

audio {
	display: none;
}

.saying {
	position: fixed;
	bottom: 30%;
	left: 50%;
	width: 120px;
	margin-left: -60px;
	display: none;
}

.saying img {
	width: 100%;
}

.chatline {
	background:#fff;
	margin-top: 50px;
	height:auto;
	border-radius: 6px;
	width:100%;
	position: fixed;
	top:0;
	z-index: 111111111;
	
}

.chatline img {
	width: 60px;
	height: 60px;
	margin-left:5%;
	z-index: 1111111111111;
}


	/*显示动态选项卡*/
		
			.tab-btn li{
				list-style: none;
			
				width: 100%;
				height:60px;
				margin-left:5%;
				font-weight: bold;
				font-size: 16px;
				margin: 5px 0px;
				z-index:1111;
				position: relative;
			}
			.btn-active{
				/* background: orange; */				
				background:#F2F2F2;
			}
			.ming{
			  width:250px;
			  height:60px;
			  line-height:60px;
			  display: inline-block;
			  position: absolute;
			  left:25%;
		      overflow: hidden;
			  text-overflow: ellipsis;
			  white-space: nowrap;
			}
</style>
</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/script.jsp"></jsp:include>
<script src='https://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>
<script type="text/javascript">
	$(function() {
		//轮询任务
		window.setInterval(function () {
			//心跳
			var url=window.BASEPATH+'pubnum/admin/merchantbeat';
			var merchantId=${merchantId};
				$.post(url,{"merchantId":merchantId},function(){})
			
			var url=window.BASEPATH+'pubnum/getolchat';
			var userId=${userId};
			var merchantId=${merchantId};
				$.post(url,{"merchantId":merchantId },function(data){
					//从属于这个商户房间信息中查询未发送的信息遍历
					for(var i=0;i<data.length;i++){	
							//查找出这个房间touser是登录人的信息展示出来
							if(data[i].fromuserId!=userId&&data[i].merchantId==merchantId){
								$('.ltname').text(data[i].fromuser);
								ans  = '<div class="answer" style="margin-top:45px;">';
								ans += '<div class="heard_img left"><img src="'+data[i].userheadimg+'"></div>';
				            	ans += '<div class="answer_text"><p>'+data[i].message+'</p><i></i>';
				        		ans += '</div></div>';
				        		$('.speak_box').append(ans);
				        		
					        		if(document.getElementById(data[i].fromuserId)==null){
					        		imgs  = '<li ><a id="'+data[i].fromuserId+'" name="'+data[i].fromuser+'" onclick="changetouser(this.id,this.name)"><img  src="'+data[i].userheadimg+'"><p class="ming">'+data[i].fromuser+'</p></a></li>';
					        		$('#userlist').append(imgs);
					        		} 
								//记录消息来自谁放到三方待用
								$('.touser').val(data[i].fromuserId);
								//修改展示完成的数据flag
								$.post(window.BASEPATH+'pubnum/updateflag',{"id":data[i].id},function(){})								
							}
							
						}
					})
		},3000);
		
    })   
	            	
	 
	
	//消息发送方法
	 function SubSend(){
	 	var message="";
	 	var userId=${userId};
		var merchantId=${merchantId};
		//存数据库的路径
	 	var url=window.BASEPATH+'pubnum/pullolchat';
		//获取要发送的对象 
		var touser=$('.touser').val();
		
		
	 	//输入框判空
	 	if(document.getElementById("left").value!=""&&document.getElementById("left").value!=null){
	 		message=document.getElementById("left").value;
	 	}else{
	 		$.alert('输入不能为空！');
	 		return;
	 	}
	 		str  = '<div class="question">';
	        str += '<div class="heard_img right"><img src="${userHeadimg}"></div>';
	        str += '<div class="question_text clear"><p>'+message+'</p><i></i>';
	        str += '</div></div>';
	        $('.speak_box').append(str);
	 	$('.left').val("");
	 	//将发送的信息存入数据库 
	 	$.post(url,{"userId":userId,"merchantId":merchantId,"message":message,"touser":touser},function(data){
	 	})
	 }
	 //修改touser
	function changetouser(id,name){
		$('.touser').val(id);
		$('.ltname').text(name);
	 $(".chatline").fadeOut().removeClass("show");
	
	}
	$(document).on('click','.olline',function(){
	 		if( $(".chatline").hasClass("show") ){
		            // 执行隐藏
		            $(".chatline").fadeOut().removeClass("show");
		            // 其他
		        }else{
		            // 显示
		            $(".chatline").fadeIn().addClass("show");
		        }
		        
		        
		        	<!--选项卡  -->
	$(".tab-btn li").click(function(){
	//为按钮添加样式
	$(this).addClass("btn-active")
	.siblings()
	.removeClass("btn-active");	
	 });
	 $(".left").click(function(){
	    $(".chatline").fadeOut().removeClass("show");
	  });
	}); 

</script>

<body>
	<input type="text" class="touser" hidden="hidden" value="">
	<div class="chatline" id="chatline" style="display: none;">
	    <ul class="tab-btn active" id="userlist">
		</ul>
		<p style="font-size:14px;text-align: center;clear:both">请选择回复对象</p>
	</div>
	<div>
	<!-- 对话框 -->
	<div class="duihua" style="width:100%;height:100%;z-index:1111;">

		<div class="speak_window">
			<div style="position:fixed;top:0;width:100%;height:50px;background: #FFFFFF;z-index: 11111;float: left;line-height: 50px;">
				<p style="width:100%;">
					<a class="link-left" href="#side-menu" style="padding-left:5%;float:left;">
					<span class="icon-reorder icon-large" "></span></a>
					<span class="ltname" style="float:left;margin-left:8%;width:150px;overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"></span> <span class="olline"><a style="float: right;z-index: 111111;margin-right:5%;">聊天列表</a></span>
				</p>

			</div>
			<div class="speak_box">
				<div class="answer"></div>
			</div>
		</div>
		<div class="wenwen-footer">
			<div class="wenwen_btn left"></div>
			<div class="wenwen_text left">
				<div class="write_box">
					<input type="text" class="left" id="left" placeholder="请输入关键字" />
				</div>

			</div>
			<div class="wenwen_help right">
				<button onClick="SubSend();" class="right">发送</button>
			</div>
			<div style="opacity:0;" class="clear"></div>
		</div>
		</div>
</body>
</html>
