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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
<title></title>

<link href="../lib/luckdraw/mui.min.css" rel="stylesheet">
<link href="../lib/luckdraw/component.css" rel="stylesheet" type="text/css">
<link href="../lib/luckdraw/award.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../lib/luckdraw/animate.min.css">


</head>



<body>
	
<body style="">
	<input type="hidden" name="gamed" id="gamed" value="{{gamed}}">
<input type="hidden" name="gameState" id="gameState" value="{{gameState}}">
<input type="hidden" name="cardCode" id="cardCode" value="{{cardCode}}">
<input type="hidden" name="mId" id="mId" value="{{mId}}">
<!-------------抽奖页面-------------->
<div class="ml-main" id="ml-main">
	<img class="main_back" src="../lib/luckdraw/newback.jpg">
	<!-- <img class="animated zoomIn img_2_1" src="../lib/luckdraw/img_1.png">
	<img class="animated bounceIn img_2_2" src="../lib/luckdraw/img_2.png"> -->
	<div class="kePublic">
		<!--转盘效果开始-->
		<div style="margin:0 auto">
			<div class="banner">
				<div class="turnplate" style="background-image:url(img/turnplate-bg_2.png);background-size:100% 100%;font-size:24px !important;">
					<canvas class="item" id="wheelcanvas" width="516" height="516"></canvas>
					<img id="tupBtn" class="pointer" src="../lib/luckdraw/turnplate-pointer_2.png">
				</div>
			</div>
		</div>
		<!--转盘效果结束-->
		<div class="clear"></div>
	</div>
	<img class="bottom_shadow" src="../lib/luckdraw/bottom_shadow.png">
	<img class="animated zoomIn kePublic_back" src="../lib/luckdraw/back1.png">
	
	<!--------------滚动中奖纪录---------------->
	<div class="record_line" id="Marquee">
		
	
		
	<div id="">
			
		</div><div id="">
			
		</div></div>
	<!-------------底部声明-------------->
	<div class="rule_text">
		</a>
	</div>
</div>

<!-------------中奖弹窗页面-------------->
<div class="zj-main" id="zj-main">
		<div class="txzl">
			<div class="zj_text">
				中奖啦<br>恭喜获得<span id="jiangpin"></span>一份<br>
			</div>
			<div class="close_zj">关闭</div>
		</div>
</div>

<!-------------谢谢参与弹窗-------------->
<div class="xxcy-main" id="xxcy-main">
	<div class="xxcy">
		<div class="xxcy_text">
			很遗憾<br>没有抽中礼品
		</div>   
		<div class="close_xxcy">关闭</div>
	</div>
</div>

<script type="text/javascript" src="../lib/luckdraw/jquery.min.js"></script>
<script type="text/javascript" src="../lib/luckdraw/mui.min.js"></script>
<script type="text/javascript" src="../lib/luckdraw/awardRotate.js"></script>
<!--<script type="text/javascript" src="js/main.js"></script>-->
<script type="text/javascript" src="../lib/luckdraw/award.js"></script>

<script type="text/javascript">
	$(function() {
		$("img").on("click", function() {
			return false;
		});
		document.addEventListener("WeixinJSBridgeReady", function() {
			WeixinJSBridge.call('hideOptionMenu');
		});
	});
</script>


</body>

</html>