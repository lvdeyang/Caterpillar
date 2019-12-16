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
	<img class="main_back" src="../lib/luckdraw/newbacks.jpg">
	<!-- <img class="animated zoomIn img_2_1" src="../lib/luckdraw/img_1.png">
	<img class="animated bounceIn img_2_2" src="../lib/luckdraw/img_2.png"> -->
	<div class="kePublic">
	<div style="height:50px;"></div>
		<div style="width:220px;color:#FFF;height:150px;margin:0 auto">
		    <table style="height:100px;">
		       <tr>
		       <td style="width:60px;line-height:60px">手机号</td>
		       <td><input style="width:150px;color:black" type="text" id="phone"/></td>
		       </tr>
		       <tr>
		       <td style="line-height:60px;">姓名</td>
		       <td><input style="width:150px;color:black" type="text" id="name"/></td>
		       </tr>
		    
		    </table>
		
		</div>
	</div>
	
	<div class="record_line" id="record_line" style="margin-top:-30px;color:#FFF;font-size:14px;">点击进入抽奖
    </div>
	<!-------------底部声明-------------->
	<!-- <img class="rule_title" src="../lib/luckdraw/rule_title.png">
	<div class="rule_text">
		</a>
	</div> -->
</div>

<!-------------中奖弹窗页面-------------->
<div class="zj-main" id="zj-main">
		<div class="txzl">
			<div class="zj_text">
				<span id="errormsg"></span>
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
<!--<script type="text/javascript" src="../lib/luckdraw/awardRotate.js"></script>-->
<!--<script type="text/javascript" src="js/main.js"></script>-->
<!--<script type="text/javascript" src="../lib/luckdraw/award.js"></script>-->

<script type="text/javascript">
	$(function() {
		$("img").on("click", function() {
			return false;
		});
		document.addEventListener("WeixinJSBridgeReady", function() {
			WeixinJSBridge.call('hideOptionMenu');
		});
		
		$("#record_line").click(function(){
		     if($('#phone').val()==''||$('#name').val()==''){
			     $("#errormsg").text("请输入姓名和手机号！");
			     $('#zj-main').fadeIn();
			     return false;
		     }
     		$.ajax({
				type:"get",
				url:"<%=basePath%>/luckdraw/saveMsg?phone="+$('#phone').val()+"&name="+$('#name').val(),
				data:"",
				dataType:"json",
				success:function(data){
				    location.href=location.href;
				},
				error:function(data){
					return;
				}
			});
		});
		$('.close_zj').click(function(){
			$('#zj-main').fadeOut();
		});
	});
</script>


</body>

</html>