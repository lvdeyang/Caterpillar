<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String weburl=WXContants.Website;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>关于我们</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">

    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>
	<style>
		
/* 		.photo{width:333px; height:80px;float:right;margin-right:400px;} */
        
   		.search-text{
   		   height: 36px !important;
   		}
   		html,body{
   		width:100%;
   		height:auto;
   		background: #E5E5E5;
   		}
   		
   		.biao,.biao1{
	    color: #fff;
	    font-weight: 300;
	    font-size: 170px;
	    line-height: 60px;
	    font-family: "Just Another Hand",cursive;
	    text-transform: uppercase;
	    position: absolute;
	    left:50%;
	    top:45%;
	    margin-left:-340px;
		}
   		.beijing{
   		background-position: center;
		background-repeat: no-repeat;
   		background: url("lib/images/real2.jpg") no-repeat;
   		background-size:100% 100%;
   		background-attachment: fixed;
   		}
   		.beijing1{
   		background-position: center;
		background-repeat: no-repeat;
   		background: url("lib/images/slider_2.jpg") no-repeat;
   		background-size:100% 100%;
   		background-attachment: fixed;
   		}
   		.button:hover{
   		color:#2BAD00 !important;
   		background:#fff !important;
   		cursor:pointer !important;
   		}
       .gotop {
			    position: fixed;
			    right: 20px;
			    bottom: 50px;
			    display: block;
			    width: 80px;
			    height: 80px;
			    opacity: 0.8;
			    z-index:111111;
			}
		.liaojie:hover{
		   color:#2BAD00 !important;
		}	
			 
 </style>

  </head>
  
  <body>
  
	
 
    <!--- header begin-->
    <c:import url="${path}/web/top"></c:import>
    <!-- 
    <div class="header" style="height:100px;width:100%;background:#fff;color:#30AD00;font-size:24px;line-height: 100px;">
	  <h2 style="margin-left:14%;font-family: 'Lobster', cursive;font-weight:bold !important;text-shadow: 5px 5px 5px black, 0px 0px 2px #CDCDCD;">过来玩。</h2>
	</div> -->
	<div class="beijing" style="width:100%;height:800px;position: relative;top:0;">
	 <font class="biao" style="vertical-align: inherit;">关于我们</font>
	 <div class="button" style="z-index:11;position:absolute;left:50%; text-align:center;background: 0 0;margin-left:-90px; top:65%;color:#fff;font-size:22px;width:180px;height:60px;line-height:60px;border:2px solid #fff;outline: none;border-radius:30px;">欢迎</div>
	</div>
	<div class="main" style="text-align:center;margin:60px 0;position: relative;display:none;">
	    <p style="font-size:12px;color:black;margin:30px 0">关 于</p>
	    <p style="color:black;font-size:36px;margin:40px 0;font-family: "Just Another Hand",cursive;">我们的公司简介</p>
	    <img class="hs" style="width:40%;height:400px;border-radius:10px;" src="lib/images/gongsi.jpg" />
	    
	     <div style="font-size:16px;width:38%;height:auto;float:right;padding-right:10%;text-align: left;display: inline-block;line-height:50px;">
	      <p style=" text-indent:2em;">遵化市博客旅游集散中心“过来玩”网站成立于2012年6月，注册资金1000万元。2015年5月，适应京津冀旅游协同发展和开发全域智慧旅游的形势需求。“过来玩”网站致力于智慧化景区、社区以及智慧化城市规划设计，旅游产品策划研发，旅游大数据，旅游活动组织和会展服务，是覆盖全国、具有浓郁地方特色，pc端、手机APP、微官网、小程序四网合一，集“吃、住、行、游、购、娱”为一体的智慧旅游网站。</p>
	      <p style=" text-indent:2em;">业务主要涵盖景区门票、智慧导览、分销、智慧住宿、智慧餐厅、地方特产、智慧停车、多机位直播、生活服务、大数据平台、自由行个性定制、进出口跨境网上商城交易的线上线下交易平台。</p>
	     </div>
	</div>
	
	 <div class="beijing1" style="width:100%;height:400px;position: relative;overflow: hidden;display:none;">
	 <font class="biao1" style="vertical-align: inherit;margin-left:-425px;">我们的荣誉</font>
	 </div>
	 <div style="width:100%;margin:40px auto;text-align: center;">
	    <div style="width:25%;height:300px;background:#E9E9E5;margin:20 20px;border-radius:10px;display: inline-block;"><img style="width:100%;height:100%;border-radius:10px;" src="lib/images/rong1.jpg"/></div>
 		<div style="width:25%;height:300px;background:#E9E9E5;margin:20 20px;border-radius:10px;display: inline-block;"><img style="width:100%;height:100%;border-radius:10px;" src="lib/images/rong2.jpg"/></div>
 		<div style="width:25%;height:300px;background:#E9E9E5;margin:20 20px;border-radius:10px;display: inline-block;"><img style="width:100%;height:100%;border-radius:10px;" src="lib/images/rong3.jpg"/></div>
 		<div style="width:25%;height:300px;background:#E9E9E5;margin:20 20px;border-radius:10px;display: inline-block;"><img style="width:100%;height:100%;border-radius:10px;" src="lib/images/rong6.jpg"/></div>
 		<div style="width:25%;height:300px;background:#E9E9E5;margin:20 20px;border-radius:10px;display: inline-block;"><img style="width:100%;height:100%;border-radius:10px;" src="lib/images/rong5.jpg"/></div>
 		<div style="width:25%;height:300px;background:#E9E9E5;margin:20 20px;border-radius:10px;display: inline-block;"><img style="width:100%;height:100%;border-radius:10px;" src="lib/images/rong1.jpg"/></div>
	 </div>
	 <div class="hehuo" style="text-align:center;margin:60px 0;">
	  <p style="font-size:12px;color:black;margin:30px 0">关 于</p>
	    <p style="color:black;font-size:36px;margin:40px 0;font-family: "Just Another Hand",cursive;">我们的合伙人</p>
	     <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	     <img style="width:100%;height:200px;" src="lib/images/he11.jpg"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">“过来玩”多机位直播</h1>
	       <p style="font-size:14px;line-height:30px;">在线多机位直播，可录制直播，直播回放，直播视频下载</p>
	     </div>
	     <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	       <img style="width:100%;height:200px;" src="lib/images/he2.jpg"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">北京平谷区智慧旅游</h1>
	       <p style="font-size:14px;line-height:30px;"> 全域休闲，畅游平谷。</p>
	     </div>
	      <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	       <img style="width:100%;height:200px;" src="lib/images/he3.jpg"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">国际饭店</h1>
	       <p style="font-size:14px;line-height:30px;"> 遵化国际饭店隶属遵化市政府，是集餐饮、住宿、购物、娱乐为一体的三星级旅游涉外饭店。</p>
	     </div>
	      <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	       <img style="width:100%;height:200px;" src="lib/images/he4.jpg"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">花乡果巷景区</h1>
	       <p style="font-size:14px;line-height:30px;"> 花乡果巷，花海趣圈。</p>
	     </div>
	     <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	     <img style="width:100%;height:200px;" src="lib/images/he5.jpg"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">金融街•汤泉宫温泉旅游度假村</h1>
	       <p style="font-size:14px;line-height:30px;">以皇家温泉养生文化为核心，深入挖掘皇家温泉养生之道，注重汤泉温泉生态构建，弘扬健康温泉养生理念，精心打造中国皇家温泉养生文化旅游高端品牌</p>
	     </div>
	     <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	       <img style="width:100%;height:200px;" src="lib/images/he6.jpg"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">任记农产品•非物质文化遗产</h1>
	       <p style="font-size:14px;line-height:30px;"> 非物质文化遗产。</p>
	     </div>
	      <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	       <img style="width:100%;height:200px;" src="lib/images/he7.png"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">万佛园4A级景区智慧导览</h1>
	       <p style="font-size:14px;line-height:30px;"> 展示手绘地图，集语音、服务、景点介绍、线路一体的智慧化导游功能。</p>
	     </div>
	      <div style="width:18%;height:400px;background:#fff;margin:20 20px;border-radius:10px;display: inline-block;overflow: hidden;">
	       <img style="width:100%;height:200px;" src="lib/images/he8.jpg"/>
	       <h1 style="font-size:20px;color:black;line-height:50px;">兴隆山景区</h1>
	       <p style="font-size:14px;line-height:30px;"> 完整的自然生态系统、良好的自然生态气候，兴隆山是观光旅游，休闲旅游，生态旅游的极佳场所。</p>
	     </div>
	 </div>
	 <div style="text-align:center;margin:60px 0 0 0;">
	 <p style="font-size:12px;color:black;margin:30px 0">欢 迎</p>
	  <p style="color:black;font-size:36px;margin:40px 0;font-family: "Just Another Hand",cursive;">加入我们</p>
	   <div style="width:40%;display: inline-block;margin-top:50px;">
	   
	    <h1 style="color:black;font-size:24px;">招聘信息</h1>
	   	<ul style="font-size:16px;line-height:50px;">
	    <li><span>找工作来这里</span></li>
	    <li><span>过来玩团队等待您的加入</span></li>
	    <li><img style=" vertical-align: middle;width:30px;height:30px;" src="lib/images/zhi1.png"/><span style="margin:0 10px;font-weight: bold;"><a style="color:red;" href="https://mp.weixin.qq.com/s/fQfDxZILdrZACvmTRXawmA">查看详情</a></span><img style=" vertical-align: middle;width:30px;height:30px;" src="lib/images/zhi2.png"/></li>
	   	</ul>
	   </div>
	   <div style="width:40%;display: inline-block;">
	    <h1 style="color:black;font-size:24px;">联系信息</h1>
	   	<ul style="font-size:16px;line-height:50px;">
	    <li>联系电话：<span>0315-6686299</span> / <span>0315-6681288</span></li>
	    <li></li>
	    <li>邮箱：<span>guolaiwan99@163.com</span></li>
	    <li>地址：<span>中国·遵化万乘晟象大厦12层</span></li>
	   	</ul>
	   </div>
	   
	     <p class="liaojie" style="text-align:right;font-size:14px;margin:50px 290px 0 0;font-weight: bold;">想了解更多关于我们的信息 可以扫描下方二维码哟 <span style="color:red;font-weight: bold;">⬇⬇⬇ </span></p>
	     
	 </div>
	 
	  <!-- 置顶 -->
			<div><a href="javascript:;" class="gotop" style="display:none;"><img style="width:100%;height:100%;" alt="" src="lib/images/tophome.png"></a></div>
	<c:import url="${path}/web/foot"></c:import>
    <!-- header End -->
  <!--   <label>房间名</label>
<input id="input_roomName" size="10" maxlength="10">
<input type="button"  value="进入聊天室" onclick="initWebSocket()" />
<input type="button" value="退出聊天室" onclick="closeWs()" /><br> -->
<!-- <div class="msg_board"></div> -->
<!-- <input id="input_msg" size="43" maxlength="40">
<input type="button" value="发送" onclick="send_msg()" /> -->
	
	
	
	
	
  </body>
  
  <script>
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
  /* $('.beijing').fadeIn(1500); */
   $('.main').fadeIn(3000); 
    function getTop(){
        var top = $(document).scrollTop();
        if($(document).scrollTop()>351){
         
            $('.beijing1').fadeIn(2500);
        }
        setTimeout(getTop);
    }
     
    getTop();
</script>
  
<%--   <script type="text/javascript">
    var webSocket;
 
    function send_msg() {
        if (webSocket != null) {
            var input_msg = document.getElementById("input_msg").value.trim();
            if (input_msg == "") {
                return;
            }
            webSocket.send(input_msg);
            // 清除input框里的信息
            document.getElementById("input_msg").value = "";
        } else {
            alert("您已掉线，请重新进入聊天室...");
        }
    };
 
    function closeWs() {
        webSocket.close();
    };
 
    function initWebSocket() {
        var roomName = document.getElementById("input_roomName").value;
        // 房间名不能为空
        if (roomName == null || roomName == "") {
            alert("请输入房间名");
            return;
        }
        if ("WebSocket" in window) {
//            alert("您的浏览器支持 WebSocket!");
            if (webSocket == null) {
             /* var url = "ws://<%=weburl%>/guolaiwan/webSocket/chat/" + roomName;*/
                 var url = "ws://localhost:8080/guolaiwan-app-web/webSocket/chat/" + roomName; 
                // 打开一个 web socket
                webSocket = new WebSocket(url);
            } else {
                alert("您已进入聊天室...");
            }
 
            webSocket.onopen = function () {
                alert("已进入聊天室，畅聊吧...");
            };
 
            webSocket.onmessage = function (evt) {
                var msg_board = document.getElementsByClassName("msg_board")[0];
                var received_msg = evt.data;
                var old_msg = msg_board.innerHTML;
                msg_board.innerHTML = old_msg + received_msg + "<br>";
                // 让滚动块往下移动
                msg_board.scrollTop = msg_board.scrollTop + 40;
            };
 
            webSocket.onclose = function () {
                // 关闭 websocket，清空信息板
                alert("连接已关闭...");
                webSocket = null;
                document.getElementsByClassName("msg_board")[0].innerHTML = "";
            };
        }
        else {
            // 浏览器不支持 WebSocket
            alert("您的浏览器不支持 WebSocket!");
        }
    }
</script> --%>
</html>
