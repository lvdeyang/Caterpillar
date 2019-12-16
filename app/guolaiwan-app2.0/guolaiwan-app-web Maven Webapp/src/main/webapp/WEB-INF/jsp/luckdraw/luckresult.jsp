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
	<img class="main_back" src="../lib/luckdraw/newbackss.jpg">
	<!-- <img class="animated zoomIn img_2_1" src="../lib/luckdraw/img_1.png">
	<img class="animated bounceIn img_2_2" src="../lib/luckdraw/img_2.png"> -->
	<div class="kePublic">
	<div style="height:50px;"></div>
		<div id="result" style="font-size:13px;width:90%;margin-left:5%;color:#FFF;height:150px;">
		   
		         
		</div>
	</div>
	
	<div style="display:none;" class="record_line" id="record_line" style="margin-top:0px;color:#FFF">现场兑奖
    </div>
	<!-------------底部声明-------------->
	<!-- <img class="rule_title" src="../lib/luckdraw/rule_title.png">
	<div class="rule_text">
		</a>
	</div> -->
</div>

<div class="ml-main" id="ml-main1" style="display:none;">
	<img class="main_back" src="../lib/luckdraw/notstart.png">
	<!-- <img class="animated zoomIn img_2_1" src="../lib/luckdraw/img_1.png">
	<img class="animated bounceIn img_2_2" src="../lib/luckdraw/img_2.png"> -->
	<div class="kePublic">
	<div style="height:50px;"></div>
		<div id="result1" style="font-size:13px;width:90%;margin-left:5%;color:#FFF;height:150px;">
		   
		         
		</div>
	</div>
	
	<div style="display:none;" class="record_line" id="record_line" style="margin-top:0px;color:#FFF">现场兑奖
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
		
		var result='${result}';
		
		var ischeck='${useit}';
		var code='${uuid}';
		var type='${drawProductId}';
		if(result=='godloveu'){
		   if(ischeck==0){
		      $('#result').html('<font size=4px>奖品为：'+(type==1?'电影票':'眼镜')+'<br><br>兑奖码：<br><font size=3px>'+code+'</font><br><br><font color=yellow size=4px>注意：不要自己点兑奖按钮，现场商家使用</font></font>');
		      $('#record_line').show();
		   }else{
		      $('#result').html('奖品已领取');
		   }
		}else if(result=='today'){
		   $('#result').html('今天已经抽过奖了，明天再试试');
		}else if(result=='time'){
			$('#ml-main').hide();
			$('#ml-main1').show();
		   /* $('#result').html('<img src="../lib/luckdraw/notstart.png"  height="50" width="300">');  */
		}else if(result=='nullprize'){
			$('#result').html('今天奖品发完了，明天要抓紧参加活动哦');
		}else if(result=='over'){
			$('#result').html('活动已结束');
		}else if(result=='todayover'){
			$('#result').html('今日活动已结束，请明天再来');
		}else if(result=='notstart'){
			$('#result').html('活动还没开始哦');
		}
		
		
		$("#record_line").click(function(){
		     
     		$.ajax({
				type:"get",
				url:"<%=basePath%>/luckdraw/check?id=${id}",
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