<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String weburl=WXContants.Website;
%>

<!DOCTYPE html>
<html style="width:100%;height:100%;margin:0;padding:0;overflow-y:hidden">
<head>
<meta charset="utf-8">
<title>过来玩系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/lib/layui/css/layui.css" media="all">
<style>
	*{margin:0;padding:0;}
	li{list-style: none;}
	a{color: inherit; text-decoration:none;outline:none;}
	a{text-decoration:none;cursor: pointer}
	a:hover{color: #E26B0F;text-decoration: none;outline:none}
	a.ie6:hover{zoom:1}
	a:focus{outline:none}
	a:hover,a:active{outline:none}:focus{outline:none}
	body{
		width:100%;height:100%;overflow:hidden;
	}
	#originContainer .layui-progress,#sellContainer .layui-progress{
		background-color:#1e9fff !important;
	}
	.container{
		width:100%;height:100%;
	}
	.img{
		width:100%;height:100%;
	}
	.distribute{
		width:20%;height:25%;position:absolute;left:2%;top:10%;z-index:150;
	}
	.origin{
		width:19%;height:25%;position:absolute;left:2%;top:56%;z-index:150;
	}
	.sell{
		width:19%;height:25%;position:absolute;left:2%;top:34%;z-index:150;
	}
	.parkContainer{
		width:20%;height:25%;position:absolute;left:2%;top:77%;z-index:150;
	}
	.park{
		width:100%;height:100%;
	}
	.top_title{
		box-sizing: border-box; width: 100%; height: 40px; padding-left: 16px; position:absolute; left: 0; top: 25px;
	}
	.top_title .time{
	    float: left; height: 40px; margin-left: 100px; line-height: 40px; color:rgba(0,137,255,.6); font-size: 24px;
	}
	.top_title .weather{ 
		float: right; height: 40px; margin-right: 60px; line-height: 40px;
		position: absolute;top:-14px;right:14px;
	}
	.top_title>li{
		list-style: none; margin:0; padding:0;
	}
	.align_left{ float: left; }
	.align_right{ float: right; }
	.align_center{ text-align: center; }
	.num {
		width:25%;height:25%;position:absolute;left:25%;top:13%;z-index:160;
	}
	.num>div{
		with: 50%;height: 90%;color: #FFF;margin: 10px;
	}
	.num>.num_l, .num>.num_r{
		width:45%; position: relative;
	}
	.title_div{
		position: absolute; left: -8%; top: 10px; width: 100%; text-align: center; font-size: 24px; border: 1px solid #1D8EEE;
	}
	.num_div{
		position: absolute; left: -20px; width: 100%; text-align: center; top: 80px; height: 60px; line-height: 60px; font-size: 30px; letter-spacing:5px;
	}
	.text_div{
		position: absolute; top: 160px; width: 200px; text-align: left; font-size: 14px;
	}
	.text_div>span{
		display: inline-block;
	}
	.item_txt{
	    height: 60px; line-height:60px; text-align: center; background-color: #1D8EEE; width:150px; display:-moz-inline-box; display:inline-block; border-radius:2px;
	}
	.center{
		width:41%;height:25%;position:absolute;left:20%;top:41%;z-index:160;
	}
	.center_title{
		position:absolute;left:20%;
	}
	.center_title_sp{
		color:#FFF;font-weight:bold;font-size:20px;
	}
	.age{
		width:49%;height:95%;position:absolute;left:4%;top:6%;
	}
	.gender{
		width:49%;height:95%;position:absolute;left:35%;top:6%;
	}
	.map{
		width:50%;height:50%;position:absolute;left:27%;top:14%;z-index:100;
	}
	.people{
		width:100%;height:100%;position:absolute;
	}
	.orderInfo{
		width:20%;height:25%;position:absolute;left:78%;top:40%;z-index:150;
	}
	.center_people{
		width:50%;height:25%;position:absolute;left:25%;top:72%;z-index:150;
	}
	.people_div{
		height: 90%;
		overflow: hidden;
	    margin: 10px;
	    border: 1px solid #41A0FC;
	    -webkit-border-radius: 4px;
	    -moz-border-radius: 4px;
	    border-radius: 4px;
	  	-moz-box-shadow:0 0 10px #06c;
     	-webkit-box-shadow:0 0 10px #06c;
    	box-shadow:0 0 10px #06c;
	}
	.text_sp{
		display: inline-block;
		color: skyblue;
		font-size: 16px;
    	font-weight: bold;
		margin-left: 15px;
	}
	.text_sp:before{
	    content: '';
	    position: absolute;
	    width: 438px;
	    height: 1px;
	    display: block;
	    background: #41A0FC;
	    top: 25px;
	    left: 0;
    }
	.center_people_title { 
		position: relative; display: inline-block; padding: 0; width: 35px;height: 5px;	
	} 
	.center_people_title::before { 
		content: ''; 
		position: absolute; 
		top: -8px; right: -288px; bottom: -4px; left: 288px; z-index: 0;
	 	background: #41A0FC; 
	 	background-image: linear-gradient( hsla(0,0%,100%,0), hsla(0,0%,100%,0));
	  	border: 1px solid #41A0FC; 
	  	border-bottom: none; 
	  	border-radius: 2px 2px 0 0; 
	  	box-shadow: 0 1px #41A0FC inset;
	  	transform: perspective(10px) rotateX(10deg); 
	  	transform-origin: right; 
	}
	.center_people .tit_sp{
		font-size: 12px;
		font-weight: normal;
		margin-top: 15px;
	}
	/*  */
	.newsInfo{
		width:17%;height:25%;position:absolute;left:80%;top:70%;z-index:150;
	}
	.news{
    	overflow: hidden;
	}
	.news_hd{
	    padding: 8px 0 0;
	    position: relative;
	    line-height: 13px;
	}
	.news_hd:before{
	    content: '';
	    border: 1px solid #316CAE;
	    position: absolute;
	    left: 16px;
	    width: 18px;
	    height: 0px;
	    top: 26px;
	}
	.news .news_tit{
	    display: inline-block;
	    font-size: 16px;
	    font-weight: bold;
	    margin-left: 15px;
	    color: skyblue;
		position: relative;
	}
	.news .news_tit:before{
		content:'';
	 	border: 2px solid #FFD200; 
	 	position: absolute; 
	 	height: 9px;
	    left: -10px;
	    top: 2px;
	}
	.news .news_tit:after{
		content:'';
	 	border-left: 1px solid #468FD3; 
	 	border-bottom: 1px solid #468FD3; 
	 	position: absolute; 
	    height: 16px;
	    width: 54px;
	    left: -9px;
	    top: 2px;
	    z-index: -1;
	}
	.news .news_more{
	    position: absolute;
	    right: 12px;
	    top: 8px;
	    font-size: 12px;
	    color: #999;
	}
	.news .news_list {
	    position: relative;
	    padding-top: 10px;
	    margin: 0 15px;
	    height: 88px;
	}
	.news .news_item {
		line-height: 21px;
	    max-width: 280px;
	    width: 270px;
 	    height:30px;
	    white-space: nowrap;
	    -o-text-overflow: ellipsis;
	    text-overflow: ellipsis;
	    overflow: hidden;
	    color: #FFF;
	}
	.news_tag{
	    display: inline-block;
	    position: relative;
	    font-size: 13px;
	    height: 16px;
	    line-height: 16px;
	    text-align: center;
	    vertical-align: 0;
	    -moz-border-radius: 2px;
	    border-radius: 2px;
	    color: #E26B0F;
	    margin-right: 6px;
	}
	.news_tag_tit{
		font-size: 13px;
	}
	
	
/*************/
.warp {
	width:20%;height:25%;position:absolute;left:78%;top:0%;z-index:150;
}
.slider_circle_10 {
    position: relative;
    width: 330px;
    /* 640px; */
    height: 360px;
    margin: 0 auto;
    user-select: none;
    top:60px;
    left:10px;
}
.slider_circle_10 > * {
    transition: ease all 1s;
}
.slider_circle_10 > div,
.slider_circle_10 .hidden {
    opacity: 0;
    position: absolute;
    top: 20%;
    left: 50%;
    width: 1px;
    height: 1px;
    z-index: 0;
    overflow: hidden;
}
.slider_circle_10 .active {
    opacity: 1;
    position: absolute;
    top: 30%;
    left: 50%;
    margin-left: -25%;
    width: 56%;
    height: 75%;
    z-index: 10;
}
.slider_circle_10 .prev1,
.slider_circle_10 .next1 {
    opacity: 0.9;
    position: absolute;
    top: 60%;
    left: 48%;
    margin-left: -45%;
    width: 25%;
    height: 25%;
    z-index: 9;
}
.slider_circle_10 .next1 {
    margin-left: 15%;
}
.slider_circle_10 .prev2,
.slider_circle_10 .next2 {
    opacity: 0.7;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -50%;
    width: 15%;
    height: 15%;
    z-index: 8;
}
.slider_circle_10 .next2 {
    margin-left: 35%;
}
.slider_circle_10 .prev3,
.slider_circle_10 .next3 {
    opacity: 0.5;
    position: absolute;
    top: 35%;
    left: 50%;
    margin-left: -50%;
    width: 7%;
    height: 7%;
    z-index: 7;
}
.slider_circle_10 .next3 {
    margin-left: 43%;
}
.slider_circle_10 .prev4,
.slider_circle_10 .next4 {
    opacity: 0.3;
    position: absolute;
    top: 25%;
    left: 50%;
    margin-left: -40%;
    width: 3%;
    height: 3%;
    z-index: 6;
}
.slider_circle_10 .next4 {
    margin-left: 37%;
}
.slider_circle_10 .prev5,
.slider_circle_10 .next5 {
    opacity: 0.1;
    position: absolute;
    top: 20%;
    left: 50%;
    margin-left: -15%;
    width: 2%;
    height: 2%;
    z-index: 5;
}
.slider_circle_10 .next5 {
    margin-left: 13%;
}
.next_button,
.prev_button {
    position: absolute;
    left: 100% !important;
    top: 50% !important;
    margin: -15px 0 0 -25px !important;
    width: 50px !important;
    height: 50px !important;
    border: 1px solid #000 !important;
    background: #fff !important;
    opacity: 0.5 !important;
    z-index: 5000 !important;
    cursor: pointer;
    text-align: center;
    line-height: 30px;
    border-radius: 50%;
    
}

.prev_button {
    position: absolute;
    left: 0% !important;
}

.next_button:hover,
.prev_button:hover {
    opacity: 1 !important;
}
.next_button:after,
.prev_button:after {
    content: "";
    position: absolute;
    top: 50%;
    left: 50%;
    width: 1px;
    height: 1px;
    margin-top: -10px;
}

.next_button:after {
    border-left: 15px solid #000;
    border-right: 15px solid transparent;
    border-bottom: 10px solid transparent;
    border-top: 10px solid transparent;
    margin-left: -5px;
}

.prev_button:after {
    border-right: 15px solid #000;
    border-left: 15px solid transparent;
    border-bottom: 10px solid transparent;
    border-top: 10px solid transparent;
    margin-left: -25px;
}


div.slider > div:not(.nav_indicators):not(.next_button):not(.prev_button) {
    box-shadow: 0px 1px 1px #FFF;
    background: #20277F;
    box-sizing: border-box;
    -webkit-border-radius: 2px;
   	-moz-border-radius: 2px;
  	 border-radius: 2px;
 	-moz-box-shadow:0 0 10px #06c;
   	-webkit-box-shadow:0 0 10px #06c;
  	box-shadow:0 0 10px #06c;
    /* text-align: center;
    border: 1px solid #20277F;
    font-size: 150px; */
}
.warp_con{
	width: 100%;
	height: 100%;
	overflow: hidden;
	color: #FFF;
}
.warp .warp_con h4{
	text-align: left;
	margin: 10px 0 15px 10px;
	font-weight: normal;
}
.warp_con .warp_sp{
	display: block;
	margin: 3px;
    margin-left: 18px;
	font-size: 14px;
}

div.warp_con:not(.active),
div.warp_con:not(.active)>h4,
div.warp_con:not(.active)>span{
	font-size: 6px !important;
	opacity: 0.5 !important;
	white-space: nowrap;
	overflow: hidden;
}

.warp .news_tit{
    display: inline-block;
    font-size: 16px;
    margin-left: 15px;
    color: skyblue;
    position: absolute;
    top: 40%;
    left: 2%;
    z-index: 200;
    font-weight: bold;
}
/*
.warp .news_tit:before{
	content:'';
 	border: 2px solid #FFD200; 
 	position: absolute; 
 	height: 9px;
    left: -10px;
    top: 7px;
}
.warp .news_tit:after{
	content:'';
 	border-left: 1px solid #468FD3; 
 	border-bottom: 1px solid #468FD3; 
 	position: absolute; 
    height: 16px;
    width: 54px;
    left: -9px;
    top: 7px;
    z-index: -1;
}
*/	
.origin_tit{
	color: skyblue;
	font-size: 16px;
	font-weight: bold;
	margin-left: -10px;
}
.tit_origin{
	color: #CCC;
	font-size: 14px;
	margin:10px auto;
}
.origin .origin_data > div{
	color: #FFF;
	height: 30px;
    line-height: 25px;
	border-bottom:1px solid #CCC;
}

</style>

</head>
<body>
<div class="container">
	<image class="img" src="<%= request.getContextPath() %>/lib/images/backdata.jpg"/>
	
	<div class="top_title">
		<li id="server_time" class="time"></li>
		<li id="weather" class="weather" >
        	<!-- <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=51&color=%23FFFF00" class="weatherIframe" id="weatherIframe"
        		allowtransparency="true" width="225" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no">
        	</iframe> -->
			<iframe width="300" scrolling="no" height="60" frameborder="0" allowtransparency="true" 
				src="//i.tianqi.com/index.php?c=code&id=8&color=%23FFFFFF&bgc=%23&bdc=%23&icon=1&num=1&site=19">
			</iframe>
		</li>
	</div>
	<div style="z-index:190;width:300px;height:100px;">
		 <div id="m-weather" class="m-weather f14 fr">
              <a id="weaher-info" href="https://tianqi.qq.com/" target="_blank">
                <div id="ipWeather" class="w-city fl"></div>
                <div id="weatherIcon" class="w-icon fl"></div>
                <div id="weatherTemperature" class="w-du fl"></div>
              </a>
		</div>
	</div>
	
	<!-- l -->
	<div id="distribute" class="distribute" ></div>

	<div id="sell" class="sell" >
	    <legend class="origin_tit">分销</legend>
		<div class="layui-row">
		    <div class="layui-col-xs3 tit_origin"> 经销商</div>
		    <div class="layui-col-xs3 tit_origin"> 销售量</div>
		    <div class="layui-col-xs6 tit_origin"> 占比</div>
		</div>
		<div id="sellContainer">
			<!-- ajax -->
		</div>
	</div>
	
	<div id="origin" class="origin" >
	    <legend class="origin_tit">客源地统计</legend>
		<div class="layui-row">
		    <div class="layui-col-xs3 tit_origin"> 省份 </div>
		    <div class="layui-col-xs3 tit_origin"> 人数 </div>
		    <div class="layui-col-xs6 tit_origin"> 占比 </div>
		</div>
		<div id="originContainer">
			<!-- ajax -->
		</div>
	</div>
      
	<div class="parkContainer">
		<span id="park_sp"></span>
		<div id="park" class="park"></div>
	</div>
	
	<!-- c -->
	<div  class="num" >
		<div class="num_l align_left">
			<div class="title_div">今日访问量</div>
			<div class="num_div">
				<span class="item_txt" id="countTodayRecord"></span>
			</div>
			<div class="text_div">
				<div>
					<span>昨日访问量：</span>
					<span id="countYesterdayRecord" style="display:block"></span>
				</div>
				<div>
					<span>历史访问总量：</span>
					<span id="countRecord" style="display:block"></span>
				</div>
			</div>
		</div>
		<div class="num_r align_right">
			<div class="title_div">今日新增粉丝量</div>
			<div class="num_div">
				<span class="item_txt" id="todayCount"></span>
			</div>
			<div class="text_div">
				<div>
					<span>昨日粉丝量：</span>
					<span id="yesterdayCount" style="display:block"></span>
				</div>
				<div>
					<span>粉丝总量：</span>
					<span id="userCount" style="display:block"></span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="center" >
		<div class="center_title">
			<span class="center_title_sp">人群画像</span>
		</div>
		<div id="age" class="age"></div>
		<div id="gender" class="gender"></div>
	</div>
	
	<div id="map" class="map" ></div>
	
	<div class="center_people" >
		<span class="text_sp">游客趋势分析</span>
		<a class="center_people_title"></a>
		<div class="people_div">
			<span class="text_sp align_right tit_sp" style="margin-right: 100px;" id="yearData"></span>
			<span class="text_sp align_right tit_sp">日期/人数</span>
			<div id="people" class="people"></div>
		</div>
	</div>

	<!-- r -->
	<div class="warp" id="warp">
		<div class="news_tit">交易量</div>
	    <div class="slider slider_circle_10" id="warpContainer">
		<!-- 
			<div class="warp_con">
				<h4>分公司</h4>
				<span class="warp_sp">11张</span>
				<span class="warp_sp">22张</span>
				<span class="warp_sp">33张</span>
			</div>
	        <div class="next_button"></div>
	        <div class="prev_button"></div> 
	    -->
	    </div>
	</div>

	<div id="orderInfo" class="orderInfo"></div>

	<div id="newsInfo" class="newsInfo" >
		<div id="news" class="news">
			<div class="news_hd">
				<h5 class="news_tit">过来玩头条</h5>
				<a href="" target="_blank" class="news_more">更多</a>
			</div>
			<ul class="news_list" id="newsCurrent">
				<li class="news_item">
					<a href="http://www.guolaiwan.net/web/videoPic/vpInfo/373" target="_blank" class="news_link" title="过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展">
						<span class="news_tag">【头条】</span><span class="news_tag_tit">过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展</span>
					</a>
				</li>
				<li class="news_item">
					<a href="http://www.guolaiwan.net/web/videoPic/vpInfo/373" target="_blank" class="news_link" title="过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展">
						<span class="news_tag">【头条】</span><span class="news_tag_tit">过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展</span>
					</a>
				</li>
				<li class="news_item">
					<a href="http://www.guolaiwan.net/web/videoPic/vpInfo/373" target="_blank" class="news_link" title="过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展">
						<span class="news_tag">【头条】</span><span class="news_tag_tit">过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展</span>
					</a>
				</li>
			</ul>
		</div>
		<div id="news" class="news">
			<div class="news_hd">
				<h5 class="news_tit">攻略</h5>
				<a href="" target="_blank" class="news_more">更多</a>
			</div>
			<ul class="news_list" id="public">
				<li class="news_item">
					<a href="http://www.guolaiwan.net/web/videoPic/vpInfo/373" target="_blank" class="news_link" title="过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展">
						<span class="news_tag">【活动】</span><span class="news_tag_tit">过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展</span>
					</a>
				</li>
				<li class="news_item">
					<a href="http://www.guolaiwan.net/web/videoPic/vpInfo/373" target="_blank" class="news_link" title="过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展">
						<span class="news_tag">【活动】</span><span class="news_tag_tit">过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展</span>
					</a>
				</li>
				<li class="news_item">
					<a href="http://www.guolaiwan.net/web/videoPic/vpInfo/373" target="_blank" class="news_link" title="过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展">
						<span class="news_tag">【活动】</span><span class="news_tag_tit">过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展</span>
					</a>
				</li>
			</ul>
		</div>
	</div>

<script type="text/javascript" src="<%= request.getContextPath() %>/webtheme/theme/js/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/webtheme/theme/js/mreport/jquery.easy_slides.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/require.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/echart/dist/echarts.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/echart/dist/echarts-all.js"></script>

<!-- 时间 -->
<script type="text/javascript">
	var timeDiff = null;
	
	var distributeTitles = new Array();
	var distributeCount = new Array();
	
	var ageStage = new Array();
	var genderStage = new Array();

	var monthList = new Array();
	var countList = new Array();
	
	var monthArr = new Array();
	var nameOrderArr = new Array();
	var dataArr = new Array();
	
	var parkArr = new Array();
	var parkNameArr = new Array();
	
	var mapArr = new Array();
	
	$(function(){
	
		// 获取时间
		var server = getServerTime();
		var local = new Date().getTime();
		timeDiff = parseInt((local - server) / 1000);
		window.setInterval("run();", 1000);

		// 获取渠道售票订单统计
		getDistribute();
		// 获取客源地统计
		getOrigin();
		// 获取停车信息
		getParking();
		
		// 获取用户数量等
		getUserInfo();
		// 获取人物画像-age
		getAge();
		getGender();
		// 获取地图
		getMap();
		// 获取游客趋势
		getPeople();
		
		// 获取交易量
		getDealInfo();
		// 交易量Echart
		getOrderInfo();
		// TODO 获取实时事件或新闻
		getNews();

	});

// 获取时间
	function run() {
		var localTime = new Date();
		localTime.setSeconds(localTime.getSeconds() - timeDiff);
		var time = "";
		var year = localTime.getFullYear();
		var month = localTime.getMonth() + 1;
		if(month < 10){
			month = "0" + month;
		}
		var day = localTime.getDate();
		if(day < 10){
			day = "0" + day;
		}
		var hour = localTime.getHours();
		var minute = localTime.getMinutes();
		var second = localTime.getSeconds();
		if(hour < 10){
			time += "0" + hour;
		}else{
			time += hour;
		}
		time += ":";
		if(minute < 10){
			time += "0" + minute;
		}else{
			time += minute;
		}
		time += ":";
		if(second < 10){
			time += "0" + second;
		}else{
			time += second;
		}
		$("#server_time").html(year + "-" + month + "-" + day + "  " + time);     
	}
	function getServerTime(){
		var serverTime = new Date().getTime();
		$.ajax({
			url : "getsystemtime",
			type : "get",
			data : {"id": 2},
			async : false,// 是否异步执行
			cache : false,
			beforeSend : function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success : function(result) {
				if (result.status == 200) {
					serverTime = result.data.serverTime;
				}
			}
		});
		return serverTime;
	}
// 获取时间--------END
// 获取用户数量等
	function getUserInfo(){
		$.ajax({
			url : "getuser",
			type : "get",
			async : false,// 是否异步执行
			cache : false,
			beforeSend : function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success : function(result) {
				if(result.status == 200){
					var data = result.data;
					/* $("#todayCount").html(data.todayCount);
					$("#yesterdayCount").html(data.yesterdayCount);
					$("#userCount").html(data.userCount);
					$("#countTodayRecord").html(data.countTodayRecord);
					$("#countYesterdayRecord").html(data.countYesterdayRecord);
					$("#countRecord").html(data.countRecord); */
					 $("#todayCount").html("56");
					$("#yesterdayCount").html("150");
					$("#userCount").html("23854");
					$("#countTodayRecord").html("2216");
					setInterval(function(){
					    var randomNum=Math.floor(Math.random()*10);
					    var count=parseInt($("#countTodayRecord").html())+randomNum;
						$("#countTodayRecord").html(count);
						
						var num1=parseInt($("#todayCount").html())+1;
						$("#todayCount").html(num1);
						
					},2000)
					$("#countYesterdayRecord").html("5630");
					$("#countRecord").html("4151911");
				}
			}
		});
	}
// 获取用户数量等------END
// TODO 获取实时事件等
	function getNews(){
		$.ajax({
			url : "getnews"+"?id=2",
			type : "get",
			async : false,// 是否异步执行
			cache : false,
			beforeSend : function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success : function(result) {
				var html ="";
				for(var i = 0; i < 4; i++){
					html += '<li class="news_item">'
						+		'<a href="http://www.guolaiwan.net/web/videoPic/vpInfo/373" target="_blank" class="news_link" title="过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展">'
						+			'<span class="news_tag">【精彩活动】</span><span class="news_tag_tit">过来玩杯——全民讲遵化，我是金牌讲解员”获奖作品展</span>'
						+		'</a>'
						+	'</li>';
				}
				//$("#newsCurrent").html(html);
				//$("#public").html(html);
				/* if(result.status == 200){
					var data = result.data;
				} */
			}
		});
	}
// 获取实时事件等------END	
// 获取渠道售票统计
	function getDistribute(){
		$.ajax({
			url : "getdistribute",
			type : "get",
			async : false,// 是否异步执行
			cache : false,
			beforeSend : function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success : function(result) {
				distributeTitles = result.distributeTitles;
				distributeCount = result.distributeCount;
			}
		});
	}
// 获取渠道售票统计------END	
// 获取客源地统计
	function getOrigin(){
		$.ajax({
			url: "getorigin"+"?id=2",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				var html = "";
				if(result.origin){
					// var d = result.origin;
					var d= [{"originCount":14,"originRadio":"77.78%","originName":"北京市"},{"originCount":4,"originRadio":"22.22%","originName":"河北省"}]
					if(Array.isArray(d)){
						for ( var i = 0; i < d.length; i++){
						    html += '<div class="layui-row origin_data">'
						 		 +  	'<div class="layui-col-xs3 tit_origin">'+d[i].originName+'</div>'
					             +  	'<div class="layui-col-xs3 tit_origin">'+d[i].originCount+'</div>'
					             +  	'<div class="layui-col-xs6 tit_origin">'
					             +			'<div class="layui-progress layui-progress-big" lay-showpercent="true">'
							     +		    	'<div class="layui-progress-bar layui-bg-red" lay-percent="'+d[i].originRadio+'"></div>'
							     +      	'</div>'
							     +   	'</div>'
							     +  '</div>';
						 }
					}else{
						html += '<div class="layui-row origin_data">'
							 +  	'<div class="layui-col-xs3 tit_origin">'+d.originName+'</div>'
					         +  	'<div class="layui-col-xs3 tit_origin">'+d.originCount+'</div>'
					         +  	'<div class="layui-col-xs6 tit_origin">'
					         +			'<div class="layui-progress layui-progress-big" lay-showpercent="true">'
							 +		    	'<div class="layui-progress-bar layui-bg-red" lay-percent="'+d.originRadio+'"></div>'
							 +      	'</div>'
							 +   	'</div>'
							 +  '</div>';
					}
				}
				// console.log(html)
				html = '<div class="layui-row origin_data"><div class="layui-col-xs3 tit_origin">河北省</div><div class="layui-col-xs3 tit_origin">849</div><div class="layui-col-xs6 tit_origin"><div class="layui-progress layui-progress-big" lay-showpercent="true"><div class="layui-progress-bar layui-bg-red" lay-percent="66.27%"></div></div></div></div><div class="layui-row origin_data"><div class="layui-col-xs3 tit_origin">北京市</div><div class="layui-col-xs3 tit_origin">340</div><div class="layui-col-xs6 tit_origin"><div class="layui-progress layui-progress-big" lay-showpercent="true"><div class="layui-progress-bar layui-bg-red" lay-percent="26.54%"></div></div></div></div><div class="layui-row origin_data"><div class="layui-col-xs3 tit_origin">天津市</div><div class="layui-col-xs3 tit_origin">92</div><div class="layui-col-xs6 tit_origin"><div class="layui-progress layui-progress-big" lay-showpercent="true"><div class="layui-progress-bar layui-bg-red" lay-percent="7%"></div></div></div></div>'
				$("#originContainer").html(html)
				sell_html = '<div class="layui-row origin_data"><div class="layui-col-xs3 tit_origin">白云</div><div class="layui-col-xs3 tit_origin">849</div><div class="layui-col-xs6 tit_origin"><div class="layui-progress layui-progress-big" lay-showpercent="true"><div class="layui-progress-bar layui-bg-red" lay-percent="66.27%"></div></div></div></div><div class="layui-row origin_data"><div class="layui-col-xs3 tit_origin">金招</div><div class="layui-col-xs3 tit_origin">340</div><div class="layui-col-xs6 tit_origin"><div class="layui-progress layui-progress-big" lay-showpercent="true"><div class="layui-progress-bar layui-bg-red" lay-percent="26.54%"></div></div></div></div><div class="layui-row origin_data"><div class="layui-col-xs3 tit_origin">茗苏</div><div class="layui-col-xs3 tit_origin">92</div><div class="layui-col-xs6 tit_origin"><div class="layui-progress layui-progress-big" lay-showpercent="true"><div class="layui-progress-bar layui-bg-red" lay-percent="7%"></div></div></div></div>'
				$("#sellContainer").html(sell_html)
			}
		});
	}
// 获取客源地统计------END
// 获取停车场
	function getParking(){
		$.ajax({
			url: "getpark"+"?id=1",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				if(result){
					parkArr = result.list;
					parkNameArr.push("总停车位：" + result.total);
					for(var i=0;i<parkArr.length; i++){
						parkNameArr.push(parkArr[i].name);
					}
				}
			}
		});
	}
// 获取停车场统计------END
// 获取人物画像统计
	function getAge(){
		$.ajax({
			url: "getagestage"+"?id=2",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				if(result){
					// ageStage = result.ageList;
					ageStage = [{"name":"10岁以下","value":356},{"name":"11~17岁","value":4555},{"name":"18~30岁","value":8695},{"name":"31~60岁","value":13240}]
				}
			}
		});
	}
	function getGender(){
		$.ajax({
			url: "getgender"+"?id=2",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				if(result){
					// genderStage = result.genderList;
					genderStage = [{"name":"女","value":10536},{"name":"男","value":20154}];
				}
			}
		});
	}
// 获取人物画像统计------END
// 获取游客趋势
	function getPeople(){
		$.ajax({
			url: "getpeopel"+"?id=2",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				if(result){
					var htm = "";
					var yearList = result.yearList;
					monthList = result.monthList;
					countList = result.countList;
					var hash=[];
				    for (var i = 0; i < yearList.length; i++) {
				    	for (var j = i+1; j < yearList.length; j++) {
				        	if(yearList[i]===yearList[j]){
				        		++i;
				      		}
				    	}
				      	hash.push(yearList[i]);
				  	}
				  	if(hash.length>1){
				  		htm = "【 " + hash[0] + " ~ " + hash[1] + " 】";
				  	}else{
				  		htm = "【 " + hash[0] + " 】";
				  	}
				  	// $("#yearData").html(htm);
				  	$("#yearData").html("【 2018 ~ 2019 】");
				}
			}
		});
	}
// 获取游客趋势-------END	
// 获取交易量
	function getDealInfo(){
		$.ajax({
			url: "getdealinfo"+"?id=2",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				if(result){
					var arr = result.deals;
					var nameArr = result.nameSet;
					var htm = '';
					for(var j = 0; j < nameArr.length; j++){
						htm += '<div class="warp_con">';
						htm += '<h4>' + nameArr[j] + '</h4>';
						for (var i = 0; i < arr.length; i++) {
							if(nameArr[j]==arr[i].name){
						   		htm += '<span class="warp_sp">'+arr[i].source+'：'+arr[i].count+'张</span>'
							}
					  	}
					  	htm +='</div>'
					}
					htm = '<div class="warp_con"><h4>遵化公司</h4><span class="warp_sp">APP：439张</span><span class="warp_sp">分销：123张</span><span class="warp_sp">线下：233张</span><span class="warp_sp">网页：454张</span><span class="warp_sp">直播：147张</span><span class="warp_sp">公众号：534张</span></div><div class="warp_con"><h4>平谷公司</h4><span class="warp_sp">APP：9张</span><span class="warp_sp">分销：3张</span><span class="warp_sp">线下：3张</span><span class="warp_sp">网页：1张</span><span class="warp_sp">直播：1张</span><span class="warp_sp">公众号：1张</span></div><div class="warp_con"><h4>迁西公司</h4><span class="warp_sp">APP：9张</span><span class="warp_sp">分销：3张</span><span class="warp_sp">线下：3张</span><span class="warp_sp">网页：1张</span><span class="warp_sp">直播：1张</span><span class="warp_sp">公众号：1张</span></div>'
				  	$("#warpContainer").html(htm);
				}
			}
		});
		// 
		$('.slider_circle_10').EasySlides({
	        'autoplay': true,
	        'show': 13	
	    })
	}
// 获取交易量-------END
// 获取OrderInfoEchart 交易e
	function getOrderInfo(){
		$.ajax({
			url: "getorderinfo"+"?id=2",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				if(result){
					nameOrderArr = result.name;
					monthArr = result.month;
					dataArr= result.order;
					nameOrderArr = ["遵化公司", "迁西公司","平谷公司"]
					monthArr = ["2018-04", "2018-05", "2018-06", "2018-07", "2018-08", "2018-09", "2018-10", "2018-11", "2018-12", "2019-01", "2019-02", "2019-03"]
					dataArr = [{data:[64540,86900,93243,44890,22380,52340,51420,34380,33410,33440,41320,24208],name: "遵化公司",type: "bar"},{data:[34540,46900,23243,34890,12380,42340,41420,24380,23410,23440,31320,14208],name: "遵化公司",type: "bar"},{data:[20223,42131,35620,22140,11328,13524,12524,2318,5644,25264,32124,9128],name: "迁西公司",type: "bar"}]
				}
			}
		});
	}
// 获取OrderInfoEchart 交易e-----------END
// 获取map
	function getMap(){
		$.ajax({
			url: "getmap"+"?id=2",
			type: "get",
			async: false,// 是否异步执行
			cache: false,
			beforeSend: function(xmlHttp) {
				xmlHttp.setRequestHeader("If-Modified-Since", "0");
				xmlHttp.setRequestHeader("Cache-Control", "no-cache");
			},
			success: function(result) {
				if(result){
					mapArr = result.mapData;
				}
			}
		});
	}
// 获取map------------END
</script>




<script type="text/javascript">

 require.config({
     paths: {
         echarts: '<%= request.getContextPath() %>/lib/echart/dist'
     }
 });
 
 // 使用
 require(
     [
         'echarts',
         'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
         'echarts/chart/pie',
         'echarts/chart/line',
         'echarts/chart/map',
     ],
     function (ec) {
         // 基于准备好的dom，初始化echarts图表
         var myChart = ec.init(document.getElementById('distribute')); 
         var option = {
         	 title: {
         	 	text: '渠道售票订单统计',
         	 	textStyle:{
		        	color: '#FFF',
		        	fontWeight: "bold",
		        	fontSize: 20,
		        	color: 'skyblue'
		        },
         	 	subtext: '单位:张',	
         	 },
             tooltip: {
                 show: true
             },
             grid :{ 
                x:20,
	x2: 20,
	y2: 60,
                borderWidth :'0px' 
             },
             
             legend: {
            	 orient: 'vertical',
		         x : '80%',
		         y : '8%',
                 data:['销量'],
                 textStyle:{
                    color: '#FFF'//字体颜色
                 }
             },
             xAxis : [
                 {
                     type : 'category',
                     data : ["APP", "线下分销", "线下", "网页", "直播", "公众号"], //distributeTitles,
                     splitLine:{show: false},
                     axisLabel: {
	                     interval:0,
              	         rotate:40,
						 textStyle: {
		                     color: '#fff'
		                 }
		             }
                 }
             ],
             yAxis : [
                 {
                     type : 'value',
                     splitLine:{show: false},
                     axisLabel : {
                         formatter: '{value}',
                         textStyle: {
                             color: '#fff'
                         }
                     }
                 }
             ],
             series : [
                 {
                     "name":"销量",
                     "type":"bar",
                     "barWidth":10,
                     "data": [490, 230, 136, 218, 171, 511]// distributeCount
                 }
             ],
         };
         myChart.setOption(option); 
         
    // 车辆
         var myChart4park = ec.init(document.getElementById('park')); 
         option4park = {
         	 title:{
         	 	 text: '停车场使用情况分析',
		         textStyle:{
		        	color: '#FFF',
		        	textAlign:'center',
		        	fontWeight: "bold",
		        	fontSize: 16,
		        	color: 'skyblue'
		        },
		        x:'left',
		        y:'top',
		     },
		     tooltip : {
		         trigger: 'item',
		         formatter: "{a} <br/>{b} : {c} ({d}%)"
		     },
		     legend: {
		         orient : 'vertical',
		         x: '70%',
		         y: '25%',
		         data: ["总停车位：100","大型车车位","中型车车位", "剩余车位", "小型车车位"], //parkNameArr, 
		         textStyle:{
                    color: '#FFF'//字体颜色
                 }
		     },
		     toolbox: {
		         show : false,
		     },
		     calculable : false,
		     series : [
		         {
		             name:'停车信息',
		             type:'pie',
		             radius : ['40%', '60%'],
		             center : ['35%', "50%"],
		             itemStyle : {
		                  normal : {
		                     label : {
		                         show : false
		                     },
		                     labelLine : {
		                         show : false
		                     }
		                 },
		                 emphasis : {
		                     label : {
		                         show : true,
		                         position : 'center',
		                         textStyle : {
		                             fontSize : '18',
		                             fontWeight : 'normal'
		                         }
		                     }
		                 }
		             },
		             data:  [{"name":"大型车车位","used":1,"value":20},{"name":"中型车车位","used":1,"value":20},{"name":"剩余车位","used":0,"value":30},{"name":"小型车车位","used":1,"value":30}], //parkNameArr, 
		             // data: parkArr
		         }
		     ]
		 };
         myChart4park.setOption(option4park); 
         
    // 游客数量
         var myChart4people = ec.init(document.getElementById('people')); 
         option4people = {
         	title : {
		        text: '',
		    },
		    tooltip : {
		        trigger: 'axis',
		        formatter: function (params, ticket, callback) {
			        var showHtm="";
			        for(var i=0;i<params.length;i++){
			            //x轴名称
			            var name = params[i][1];
			            var value = params[i][2];
			            showHtm+= " "+ name + ' 月份' +'<br> 人数：'+ value
			        }
			        return showHtm;
			    }
		    },
		    toolbox: {
		        show : false
		    },
		    calculable : false,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : monthList,
		            axisLabel: {
		            	formatter:function(name){
                    		return name + "月"
                		},
		            	textStyle: {
                        	color: '#fff'
                    	}
                    }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
                         formatter: '{value}',
                         textStyle: {
                             color: '#fff'
                         }
                     }
		        }
		    ],
		    series : [
		        {
		            name:'游客数量',
		            type:'line',
		            stack: '总量',
//		            data: countList
		            data: [340,238,134,120,80,314]
		        }
		    ]
		};
        myChart4people.setOption(option4people); 
        
   // 订单信息
        var myChart4orderInfo = ec.init(document.getElementById('orderInfo')); 
        option4orderInfo = {
		    title : {
		        text: '公司交易量',
		        textStyle:{
		        	color: '#FFF',
		        	textAlign:'center',
		        	fontWeight: "bold",
		        	fontSize: 20,
		        	color: 'skyblue'
		        },
		        x:'left',
		        y:'top',
		        subtext: '单位:元',
		    },
            grid :{ 
                x2:5,
                y2:60,
                borderWidth :'0px',
            },
		    tooltip : {
		        trigger: 'axis',
		    },
		    legend: {
		        data: nameOrderArr,
		        textStyle:{
                     color: '#FFF'//字体颜色
                },
                orient: 'vertical',
		        x : '80%',
		        y : '5%',
		    },
		    toolbox: {
		        show : false,
		    },
		    calculable : false,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data :  monthArr,
		            splitLine:{
					　　　show:false
					},
					triggerEvent:true,
					axisLabel: {
                     	interval:0,
             	        formatter: function (value) {
					        return value.substring(5);
					    },
						textStyle: {
                        	color: '#fff'
                     	}
                    }
		        }
		    ],
		    yAxis : [
		        {	
		            type : 'value',
		            splitLine:{
					　　　show: false
					},
					axisLabel : {
                    	formatter: '{value}',
                        textStyle: {
                            color: '#fff'
                        }
                    },
		        }
		    ],
		    series : dataArr
		};
        myChart4orderInfo.setOption(option4orderInfo);
        
    // 地图
        var myChart4map = ec.init(document.getElementById('map')); 
        option4map = {
		    title : {
		        text: '',
		    },
		    dataRange: {
		        orient: 'horizontal',
		        min: 0,
		        max: 10000,
		        x: '48%',
		        // text:['高','低'],           // 文本，默认为数值文本
		        splitNumber:0,
		    }, 
		    toolbox: {
                show: false,
		    },
		    tooltip : {
	            trigger: 'item',
	            formatter: '{a} <br/>{b} : {c}',
	            showDelay: 0,
	            backgroundColor: 'rgba(0,0,0,0.8)',
                borderColor: 'skyblue',
                borderRadius: 5,
                borderWidth: 1,
                padding: [5,10,5,10],
            },
		    series : [
		        {
		            name: '游客分布',
		            type: 'map',
		            mapType: 'china',
		            //zoom: '0.1', 
		            mapLocation: {
		                x: 'right',y:'center',width:'70%'
		            },
		            selectedMode : 'single',
		            itemStyle:{
		                normal:{label:{show:true},areaStyle:{color:'#C9C9C9'} },
		                emphasis:{label:{show:true},areaStyle:{color:'#0000CD'} },
		            },
		            // data: mapArr,
		            data: [{"name":"北京","value":340},{"name":"河北","value":849},{"name":"天津","value":92}]
		        }
		    ],
		    animation: false
		}; 
        myChart4map.setOption(option4map);
         
    // 性别比例
        var myChart4gender = ec.init(document.getElementById('gender')); 
        option4gender = {
         	title : {
		        text: '男女比例',
		        textStyle:{
		        	color: '#FFF',
		        	textAlign:'center',
		        	fontWeight: "bold",
		        	fontSize: 20,
		        	color: 'skyblue'
		        },
		        x:'center',
		        y:'bottom',
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    toolbox: {
		        show : false,
		    },
		    calculable : false,
		    series : [
		        {
		            name:'男女比例',
		            type:'pie',
		            radius : ['15%', '25%'],
		            data: genderStage
		        }
		    ]
		};
     	myChart4gender.setOption(option4gender);
     	
     // 年龄比例
     	var myChart4age = ec.init(document.getElementById('age')); 
        option4age = {
		    title : {
		        text: '年龄比例',
		        textStyle:{
		        	color: '#FFF',
		        	textAlign:'center',
		        	fontWeight: "bold",
		        	fontSize:20,
		        	color:'skyblue'
		        },
		        x:'center',
		        y:'bottom',
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    toolbox: {
		        show : false,
		    },
		    calculable : false,
		    series : [
		        {
		            name:'年龄比例',
		            type:'pie',
		            radius : [10, 60],
		            center : ['50%', "50%"],
		            roseType : 'area',
		            x: '50%',               // for funnel
		            max: 60,                // for funnel
		            sort : 'ascending',     // for funnel
		            data: ageStage
		        }
		    ]
		};
     	myChart4age.setOption(option4age);
		
     }
 );

</script>

<script>
// 右上角轮播图
/* 	$(document).ready(function() {
	    $('.slider_circle_10').EasySlides({
	        'autoplay': false,
	        'show': 13
	    })
	}); */
</script>

<script>
//注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
layui.use('element', function(){
	var element = layui.element;
});
</script>
  
</body>
</html>