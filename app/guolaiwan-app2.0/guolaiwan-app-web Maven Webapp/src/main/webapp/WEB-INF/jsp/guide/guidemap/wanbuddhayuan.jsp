<!--
<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
%>-->
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<html>
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
<meta name=”viewport” content=”width=device-width, initial-scale=1″ />

<!-- 公共样式引用 -->
<jsp:include page="../../../mobile/commons/jsp/style.jsp"></jsp:include>
<title></title>
<style>
html, body {
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	color: #FFF;
}

#container {
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	color: #FFF;
}

.amap-logo {
	pointer-events: none;
	filter: alpha(opacity = 50);
	/*IE滤镜，透明度50%*/
	-moz-opacity: 0.5;
	/*Firefox私有，透明度50%*/
	opacity: 0;
	/*其他，透明度50%*/
}

* {
	margin: 0px;
	padding: 0px;
	list-style: none;
	border: 0;
}

.lof-slidecontent {
	height: 100px;
	position: relative;
	overflow: hidden;
	margin: 0 auto;
	z-index: 100;
	float: right;
	right: -1%;
}

.lof-slidecontent .sliders-wrapper {
	position: relative;
	height: 100%;
	overflow: hidden;
}

.lof-slidecontent ul.sliders-wrap-inner {
	overflow: hidden;
	/*background: transparent url(../images/load-indicator.gif) no-repeat scroll 50% 50%;*/
	padding: 0px;
	margin: 0;
	position: absolute;
	overflow: hidden;
	display: none;
}

.lof-slidecontent ul.sliders-wrap-inner li {
	overflow: hidden;
	padding: 0px;
	margin: 0px;
	float: left;
	position: relative;
}

.lof-slidecontent .lof-opacity li {
	position: absolute;
	top: 0;
	left: 0;
}

.lof-slidecontent .navigator-content {
	position: absolute;
	/* background: none repeat scroll 0 0; */
	border-radius: 5px 5px 5px 5px;
	bottom: 10%;
	right: 30%;
}

.lof-slidecontent .navigator-wrapper {
	position: relative;
	color: #FFF;
}

ul.sliders-wrap-inner li img {
	padding: 0px;
	width: 20%;
	height: 20%;
	float: right;
}

.lof-slidecontent .slider-description a.readmore {
	color: #58B1EA;
	font-size: 95%;
}

.lof-slidecontent .slider-description a {
	color: #FFF;
}

.lof-slidecontent .slider-description {
	z-index: 100;
	position: absolute;
	bottom: 50px;
	left: 0px;
	width: 350px;
	background: url(../images/bg_trans.png);
	height: 100px;
	padding: 10px;
	color: #FFF;
}

.lof-slidecontent .slider-description h4 {
	font-size: 14px;
	margin: 10px 0;
	padding: 0;
}

.lof-slidecontent .slider-description .slider-meta a {
	margin: 0;
	background: #C01F25;
	font-size: 75%;
	padding: 2px 3px;
	font-family: "Trebuchet MS", Trebuchet, Arial, Verdana, sans-serif;
	text-transform: uppercase;
	text-decoration: none
}

.lof-slidecontent .item-meta a:hover {
	text-decoration: underline;
}

.lof-slidecontent .item-meta i {
	font-size: 70%;
}

.lof-slidecontent ul.navigator-wrap-inner li {
	cursor: hand;
	cursor: pointer;
	list-style: none;
	padding: 0;
	margin-left: 0px;
	overflow: hidden;
	display: block;
	text-align: center;
}

.lof-slidecontent ul.navigator-wrap-inner li span {
	display: block;
	padding: 10px;
	position: relative;
	line-height: 0.5em
}

.lof-slidecontent ul.navigator-wrap-inner li.active, .lof-slidecontent ul.navigator-wrap-inner li:hover
	{
	background: #FFF;
	color: #000;
	display: block;
	-moz-transition: background-color 0.8s
}

.lof-slidecontent .button-next, .lof-slidecontent .button-previous {
	display: block;
	width: 20px;
	color: #FFF;
	cursor: pointer;
	position: absolute;
	height: 100%;
	z-index: 40;
	top: 0;
	text-indent: -999px;
}

.lof-slidecontent .button-control {
	height: 27px;
	width: 30px;
	cursor: pointer;
	display: none;
}

.lof-slidecontent .action-start {
	background: url(lib/images/play.png) no-repeat center center #FFF;
	z-index: 10000;
}

.lof-slidecontent .action-stop {
	background: url(lib/images/pause.png) no-repeat center center #FFF;
	z-index: 10000;
}

.tishi ul {
	position: fixed;
	right: -18%;
	top: 30%;
}

ul li {
	list-style-type: none;
}

.audio-container {
	width: 50%;
	height: 20%;
	margin: auto;
	background-color: rgba(255, 255, 255, 0.5);
	opacity: 0.7;
	box-shadow: 0px 1px 1px #FFF;
	position: fixed;
	bottom: 4%;
	left: 50%;
	margin-left: -25%;
	z-index: 111;
}

.audio-view {
	position: relative;
	height: 100%;
	width: 100%;
}

.audio-body {
	width: 100%;
}

.audio-title {
	font-size: 12px;
	position: absolute;
	top: 20%;
	left: 38%;
	color: #3C94F9;
	overflow: hidden;
	text-align: center;
	margin: 0 auto;
}

.audio-setbacks {
	width: 100%;
	height: 1px;
	border-radius: 3px;
	background-color: dodgerblue;
}

.audio-setbacks {
	position: absolute;
	top: 50%;
}

.audio-cache-setbacks, .audio-this-setbacks {
	width: 1%;
	height: 100%;
	position: absolute;
	left: 0%;
	top: 0;
	border-radius: 3px;
}

.audio-cache-setbacks {
	background-color: dodgerblue;
	z-index: 1;
	cursor: pointer;
}

.audio-this-setbacks {
	background-color: dodgerblue;
	z-index: 2;
}

.audio-backs-btn {
	position: absolute;
	width: 14px;
	height: 14px;
	margin-top: -7px;
	top: 50%;
	right: -2px;
	background-color: dodgerblue;
	border-radius: 50%;
	cursor: pointer;
}

.audio-btn {
	height: 20px;
	width: 20px;
	position: absolute;
	top: 70%;
	right: 8%;
}


.audio-select>div {
	width: 20px;
	height: 20px;
	background-size: 100% 100%;
	background-position: center center;
	background-repeat: no-repeat;
	float: left;
	cursor: pointer;
	/* margin-right:15%; */
}

.audio-select>div+div {
	margin-left: 2px;
}


.audio-play {
	background-image: url('lib/images/stop.png');
}

.audio-stop {
	background-image: url('lib/images/plays.png');
}

.audio-list {
	position: absolute;
	right: 0;
	color: #000;
	background-color: #fff;
	bottom: 100px;
	border: 1px solid #ccc;
	width: 240px;
	border-radius: 5px;
	padding-top: 10px;
	display: none;
}

.audio-list-head {
	position: relative;
	padding-bottom: 10px;
}

.audio-list-head p {
	padding-left: 8px;
	font-size: 18px;
}

.audio-list-head span {
	position: absolute;
	right: 8px;
	top: 0;
	font-size: 12px;
	display: block;
	width: 35px;
	line-height: 25px;
	border: 1px solid #ccc;
	border-radius: 3px;
	text-align: center;
	cursor: pointer;
}

.audio-inline {
	height: 165px;
	overflow-y: scroll;
}

.audio-inline li {
	font-size: 16px;
	line-height: 2;
	padding-left: 8px;
	padding-right: 8px;
	border-top: 1px solid #ccc;
}

.audio-inline a {
	font-size: inherit;
	color: inherit;
	text-decoration: none;
	height: 32px;
	overflow: hidden;
	display: block;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.menu-show, .audio-show-volume {
	display: block !important;
}

.audio-set-volume {
	background-color: #000;
	background-color: rgba(0, 0, 0, 0.5);
	position: absolute;
	bottom: 100px;
	width: 30px;
	height: 120px;
	right: 5px;
	z-index: 999;
	display: none;
}

.volume-box {
	width: 5px;
	margin: auto;
	display: block;
	position: relative;
	background-color: #000;
	border-radius: 2.5px;
	height: 100px;
	bottom: -10px;
}

.volume-box i {
	position: absolute;
	width: 100%;
	border-radius: 2.5px;
	background-color: #c70c0c;
	height: 0;
	display: block;
	bottom: 0;
}

#ren img {
	padding: 0px;
	width: 75px;
	height: 111.47px;
	float: right;
	position: fixed;
	right: -1%;
	z-index: 1111111;
}

.fugai {
	position: absolute;
	z-index: 1000000000000;
	width: 100%;
	height: 100%;
	background-color: #FFFFFF;
	opacity: 0.01;
}

input[type="radio"]+label::before {
	content: "\a0"; /*不换行空格*/
	display: inline-block;
	vertical-align: middle;
	font-size: 18px;
	width: 0.5em;
	height: 0.5em;
	margin-right: .4em;
	border-radius: 50%;
	border: 2px solid #787878;
	text-indent: .15em;
	line-height: 1;
	padding: 0.1em;
}

input[type="radio"]:checked+label::before {
	background-color: #029687;
	background-clip: content-box;
}

input[type="radio"] {
	/*position: absolute;
			    clip: rect(0, 0, 0, 0);*/
	opacity: 0;
}
</style>
</head>
<!-- 公共脚本引入 -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script charset="utf-8"
	src="https://map.qq.com/api/js?v=2.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77"></script>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.14&key=b4020d9668380974bf11cdbf0b3eae4c"></script>
<script type="text/javascript">
    var json ="";
    var map;
    var str = []; //存入id 修改颜色已浏览
	$(function() {
		var _uril = window.BASEPATH + 'guide/getMessage'; //获取图片 xy 初始位置
		var param = {};
		param.chantId = ${chantId};
		$.post(_uril, $.toJSON(param), function(data) {
			data = parseAjaxResult(data);
            var imageLayer = new AMap.ImageLayer({ // 贴图
			url : data.pictures,
				bounds : new AMap.Bounds(
					[ data.Xlongitude,data.Xlatitude    ],	 
					[ data.Ylongitude,data.Ylatitude    ]	 
				),
				zooms : [ 10, 20 ]
			});
			map = new AMap.Map('container', { // 初始显示
				resizeEnable : true,
				showLabel : false, //不显示地图文字标记
				center : [  data.longitude,data.latitude ],
				zoom : 16,
				layers : [
					new AMap.TileLayer(),
					imageLayer
				],
				mapStyle: 'amap://styles/f6f2ac2f09ad3fbcefb26fbec14ac08f', 
				features : []
			});
	      	 lockMapBounds(); 
	      	 getMark();  //设置所有的坐标点
	      	 
	    AMap.plugin('AMap.Weather', function() { // 查询天气
        var weather = new AMap.Weather();
        //查询实时天气信息, 查询的城市到行政级别的城市，如朝阳区、杭州市
        weather.getLive('遵化', function(err, data) {
            if (!err) {
                var str = [];
                str.push('<h4 style="padding:2px;">实时天气' + '</h4><hr>');
                str.push('<p style="padding:2px;">城市/区：' + data.city + '</p>');
                str.push('<p style="padding:2px;">天气：' + data.weather + '</p>');
                str.push('<p style="padding:2px;">温度：' + data.temperature + '℃</p>');
                str.push('<p style="padding:2px;">风向：' + data.windDirection + '</p>');
                str.push('<p style="padding:2px;">风力：' + data.windPower + ' 级</p>');
                str.push('<p style="padding:2px;">空气湿度：' + data.humidity + '</p>');
                str.push('<p style="width:250px;padding:1px;">发布时间：' + data.reportTime + '</p>');
                var marker = new AMap.Marker({ position: map.getCenter()});
                var infoWin = new AMap.InfoWindow({
                    content: '<div  class="info" style=" overflow-x: hidden;width:100%;height:35%;box-shadow: 0px 3px 5px #FFF;border-radius: 8px;color: #3C94F9;font-size:14px;width:auto;position: fixed;top:50px;left:3%;border:1px solid #3C94F9;background-color:rgba(255,255,255,0.5);">'+str.join('')+'</div><div class="sharp" ></div>',
                    isCustom:true,
                    offset: new AMap.Pixel(0, -37)
                });
                infoWin.open(map, marker.getPosition());
                marker.on('mouseover', function() {
                    infoWin.open(map, marker.getPosition());
                });
            }
          });
    	 });  
        });
        
         //限制地图显示范围
		function lockMapBounds() {
		   var bounds = map.getBounds();
		   map.setLimitBounds(bounds);
	     };  
	
	});
	
	
	setTimeout(function() {
	 $('.info').hide(0);
	 ref = setInterval(function(){// 循环
		 consoleLog();
	 },5000);
	}, 24000); //延迟2000毫米
	
	 
    var Longitude;
    var Latitude;
    var id;
    var name;
    function selectName(e){ // 点击查询对应坐标 更换颜色
    var cha = 0; 
      if(Longitude != null && Latitude !=null){ //搜索时把上一个表 更换为红点
         for(var i=0; i<str.length; i++){
		   if(id == str[i] ){   // 拆分  数组  遍历
			  var marker = new AMap.Marker({
			    size: new AMap.Size(20, 20), 
				icon : new AMap.Icon({            
	            image: "lib/images/huang.png",
	            imageSize: new AMap.Size(16,16)
	            }),   
				position : [ Longitude , Latitude ]
		      });
		       marker.proName = name;
		       marker.id= id;
			   marker.on('click', function(e,id){
					showInfoM(e.target.proName,e.target.id);
		       }); //增加点击事件; 
		       marker.setMap(map);
		      cha = 1;
           }
         }
         if(cha == 0){
	         var marker = new AMap.Marker({
			  size: new AMap.Size(20, 20), 
			  icon : new AMap.Icon({            
	          image: "lib/images/hong.png",
	          imageSize: new AMap.Size(16,16)
	          }),   
			  position : [ Longitude , Latitude ]
		   });
		    marker.proName= name;
		    marker.id= id;
			marker.on('click', function(e,id){
					showInfoM(e.target.proName,e.target.id);
		    }); //增加点击事件; 
		    marker.setMap(map);
         } 
	   }
	   
	   var leng = -1 ;
	   if(e != null){
	     for (var i = 0; i < json.length; i++) { //搜索切换紫标
		   if(json[i].childName == e){
		      var marker = new AMap.Marker({
			    size: new AMap.Size(20, 20), 
				icon : new AMap.Icon({            
	            image: "lib/images/zi.png",
	            imageSize: new AMap.Size(16,16)
	            }),   
				position : [ json[i].wxChildLongitude , json[i].wxChildLatitude ]
			  });
			   marker.proName= json[i].childName;
			   marker.id= json[i].id;
			   marker.on('click', function(e,id){
					showInfoM(e.target.proName,e.target.id);
		      }); //增加点击事件; 
		      marker.setMap(map);
			  leng= 0;
			  Longitude =  json[i].wxChildLongitude;
			  Latitude  =  json[i].wxChildLatitude ; 
			  id  = json[i].id;
			  name = json[i].childName;
			  $("#container").show();
			  $(".div_txt").hide();
		  }
	    } 
	  }   
	  if(leng == -1){
	     $.toast("没有此景点", "forbidden");
	  }
	}
	
	$(document).on('click','#but',function(){
	  var text = $('#txt1').val();
	  if(text != ""){
	    selectName(text);
	  }else{
	   $.toast("请输入景点名称", "forbidden");
	  }
	});
	
	 
	 function getMark(){
        map.clearMap();  
		var _uri = window.BASEPATH + 'guide/getChildByPro'; //获取所有坐标点
		var params = {};
		params.merchantId = ${chantId};
		$.post(_uri, $.toJSON(params), function(data) {
			data = parseAjaxResult(data);
			var info = data.volist;
			json = data.result;
            data =  json; 
			var html = [];
			if(info.childId != null){
			// 遍历拆分 存入数组
			 str  = info.childId.split(",");
		    }
		   for (var i = 0; i < data.length; i++) {
			    var cildId = 0;
			    if(str.length != 0){
			     //根据数组长度 循环  j
			     for(var v = 0; v < str.length; v++){
				   // 判断 j ==  data[i].id
				   if(data[i].id == str[v]){
				     // 更换颜色
			         var  marker = new AMap.Marker({
			            size: new AMap.Size(20, 20), 
					    icon : new AMap.Icon({            
			            image: "lib/images/huang.png",
			            imageSize: new AMap.Size(16,16)
		                }),   
				       position : [ data[i].wxChildLongitude , data[i].wxChildLatitude ]
			         });
					 marker.proName= data[i].childName;
					 marker.id= data[i].id;
					 marker.on('click', function(e,id){
					 showInfoM(e.target.proName,e.target.id);
	                 }); //增加点击事件; 
					 map.add(marker);
					 cildId = data[i].id;
				     break; 
				  } 
			     }
			    } 
			   if(cildId　==　0){ // 默认为红色
				     marker = new AMap.Marker({
				     size: new AMap.Size(20, 20), 
					 icon : new AMap.Icon({            
		             image: "lib/images/hong.png",
		             imageSize: new AMap.Size(16,16)
	            	 }),   
				    position : [ data[i].wxChildLongitude , data[i].wxChildLatitude ]
			        });
					marker.proName= data[i].childName;
					marker.id= data[i].id;
					marker.on('click', function(e,id){
					showInfoM(e.target.proName,e.target.id);
	                }); //增加点击事件; 
					map.add(marker);
			    }
				/* 增加搜索景点搜索 景点名称 */
				html.push('<div style="color: black;height: 44px;">');
				html.push('<p  data="'+data[i].childName+'" class="searchResult" style="padding-left: 5%;line-height: 44px;font-size: 14px;">'+data[i].childName+'</p>');
				html.push('</div>');
				html.push('<div style="border-bottom: solid 1px #E0E0E0;width:100%;"></div>'); 
			   } 
			  $('.div_txt').append(html.join(''));
			  $(document).on('click','.searchResult',function(){
			     var data=$(this).attr('data');
			     selectName(data);
			  });
			  /* getItude("孝经"); */  
	  	 }); 
  	   }	
	  	   
	  	  function setaddChild(d,navigation){
	  	    if(navigation == 0){
		  	    var _uril = window.BASEPATH + 'guide/addChildByUidCid';                   //保存浏览点id
				var param = {};
				param.ChildId = d;
				$.post(_uril, $.toJSON(param), function(data) {
					getMark();
			        ref = setInterval(function(){
		 			   consoleLog();
					},5000);
		        });
	  	    }else{
	  	    var  relevance;
	  	       var _uril = window.BASEPATH + 'guide/addChildByUidCid';                   //保存浏览点id
				var param = {};
				param.ChildId = d;
				$.post(_uril, $.toJSON(param), function(data) {
					for (var i = 0; i < json.length; i++){
		  	            if(d  ==  json[i].id ){
		  	              relevance =   json[i].relevance;  
		  	            }
		  	        }
		  	        if(relevance != null){
		  	          for (var i = 0; i < json.length; i++){
		  	             if(relevance == json[i].id){
			  	             var song = [{
									'src': 'http://www.guolaiwan.net/file/'+json[i].chineseGirl,
									'title': ''+json[i].childName+''
							 }];
						      $('#pid').html(json[i].chineseContent);
			  	              MP3(song,d); // 播放音乐
					          clearInterval(ref);  //停止循环 定位
					          red(json[i].id);
				        }
				      }
		  	       }
		        });  
	  	    }
	  	  }
	  	  
	    function clickHandler(){
	      map.clearMap(); 
	      var _uril = window.BASEPATH + 'guide/delectchildId';                   //清楚路线
		  $.post(_uril, null, function(data) {
		      getMark();
		      ref = setInterval(function(){
		 			   consoleLog();
			  },5000);
	      }); 
	   }
</script>

<script>
 var latitid;      //当前景点id
 var navigation = 0;      //导游模式
	function consoleLog(){
	    getloca();
	    var loca = {};
		function getloca() {
			var reqUrl = location.href.split('#')[0].replace(/&/g, "FISH");
			var _uri = window.BASEPATH + 'pubnum/prev/scan?url=' + reqUrl;
			$.get(_uri, null, function(data) {
				data = parseAjaxResult(data);
				if (data === -1) return;
				if (data) {
					loca = data;
					getLoation();
				}
			});
		}
	
		function getLoation(){
	       wx.config({
	            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	            appId : loca.appId, // 必填，公众号的唯一标识
	            timestamp : loca.timestamp, // 必填，生成签名的时间戳
	            nonceStr : loca.nonceStr, // 必填，生成签名的随机串
	            signature : loca.signature,// 必填，签名，见附录1
	            jsApiList : ['checkJsApi','getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
	        wx.ready(function() {
	            wx.getLocation({  
                        type: 'gcj02',
	                success: function (res) {  
	                    var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90  
	                    var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。  
	                    var speed = res.speed; // 速度，以米/每秒计  
	                    var accuracy = res.accuracy; // 位置精度  
                         getCity(latitude, longitude);
	                },  
	                cancel: function (e) {  
	                        //这个地方是用户拒绝获取地理位置  
	                        alert("请打开GPS定位,");
					}
	             });     
		         wx.error(function (res) {  
		         });     
	         });
	  }
		
	    function getCity(latitude, longitude) { //通过经纬度   获取高德位置
	     latitude =   ( parseFloat(latitude) ).toFixed(5);  //保留经纬度后5位
	     longitude =  ( parseFloat(longitude) ).toFixed(5);
	     //   计算两点的距离
	     var name;
	     var length;  //米
	     var Longitude; //经度
	     var Latitude; //纬度
	     var mp3;  //音频
	     var text; //音频文本
	     var scope;
	     var child;
		 for (var i = 0; i < json.length; i++) {
	        var p1 = [longitude,latitude];
		    var p2 = [json[i].wxChildLongitude,json[i].wxChildLatitude];
		   // 返回 p1 到 p2 间的地面距离，单位：米
			var dis = AMap.GeometryUtil.distance(p1, p2);
            if(length == null || dis < length){
                length = dis; // 保存距离
                latitid =  json[i].id;
                name = json[i].childName; // 景点名称
                Longitude = json[i].wxChildLongitude; // 经度
                Latitude = json[i].wxChildLatitude; // 纬度
                mp3 = json[i].chineseGirl; //音频
                text = json[i].chineseContent; // 文本
                scope = json[i].scope; //距离
	        }			
		 }
		 alert(length+" :米"+", "+name);  
		 if(str != "" ){  // 判断是否有已浏览点
		   for(var i = 0; i < str.length; i++){
			 if( latitid != str[i]){   // 拆分  数组  遍历
			  if( length <= scope ){  // 判断是否小于讲解范围
				  $('#pid').html(text);
				   var song = [{
							'src': 'http://www.guolaiwan.net/file/'+mp3,
							'title': ''+name+''
						}
				   ];
			      MP3(song,latitid);
			      clearInterval(ref);  //停止循环 
			      red(latitid);
			   }
			 } 
	       }
		 }else{  // 没有已预览点 则直接判断播放距离
		    if( length <= scope ){  // 判断是否小于讲解范围
				  $('#pid').html(text);
				   var song = [{
							'src': 'http://www.guolaiwan.net/file/'+mp3,
							'title': ''+name+''
						}
				   ];
			    MP3(song,latitid); // 播放音乐
			    clearInterval(ref);  //停止循环 定位
			    red(latitid);
			}
		 }
	  } 
	}
	
	

	
	$(function() {
	/*   var song = [{
					'src': 'lib/images/huanying.mp3',
					'title': '孝经'
				}
			];
	  MP3(song); */
	 
	});
   function red(n){
    map.clearMap(); 
    for (var i = 0; i < json.length; i++){
       var cha = 0;
		 for(var j = 0 ; j < str.length; j++){
		   if(json[i].id == str[j] ){   // 拆分  数组  遍历
			  var marker = new AMap.Marker({
			    size: new AMap.Size(20, 20), 
				icon : new AMap.Icon({            
	            image: "lib/images/huang.png",
	            imageSize: new AMap.Size(16,16)
	            }),   
				position : [ json[i].wxChildLongitude , json[i].wxChildLatitude ]
		      });
		      cha = 1;
           }
         }
          if(cha == 0){
	       var marker = new AMap.Marker({
			  size: new AMap.Size(20, 20), 
			  icon : new AMap.Icon({            
	          image: "lib/images/hong.png",
	          imageSize: new AMap.Size(16,16)
	          }),   
			  position : [ json[i].wxChildLongitude , json[i].wxChildLatitude ]
		   });
         }  
            marker.proName= json[i].childName;
		    marker.id= json[i].id;
		    if(json[i].id == n) marker.setAnimation('AMAP_ANIMATION_BOUNCE'); //弹跳点 
			marker.on('click', function(e,id){
				showInfoM(e.target.proName,e.target.id);
		    }); //增加点击事件;  
		    marker.setMap(map);
		} 
   }
   
   function showInfoM(e,id) { // 点击 坐标点
	  $.modal({
	  text: e,
	  buttons: [
	    { text: "取消", onClick: function(){  } },
	    { text: "导航", onClick: function(){  
		      var _uril = window.BASEPATH + 'phoneApp/getRoad'; //获取图片 xy 初始位置
			  var param = {};
			  param.startId = latitid;  // 用户点位
			  param.childId = id;  // 用户当id
			  $.post(_uril, $.toJSON(param), function(data) {
				  data = parseAjaxResult(data);
				  var lines = [];
				  for(var i=0;i<data.length;i++){
					 lines.push([data[i].wxChildLongitude , data[i].wxChildLatitude]);
				  }
				  var polyLine = new AMap.Polyline({
				  //折线的节点坐标数组
				  path:lines,
				  map:map,
				  strokeColor: "#0C2EF0",//线颜色
				  strokeOpacity: 0.5,//线透明度
                  strokeWeight: 5,//线宽
				  }); 
		     }); 
	    }},
	      { text: "导游模式", onClick: function(){ 
		     for (var i = 0; i < json.length; i++){
		       if(id ==  json[i].id){
		           $('#pid').html(json[i].chineseContent);
				   var song = [{
							'src': 'http://www.guolaiwan.net/file/'+json[i].chineseGirl,
							'title': ''+json[i].childName+''
						}
				   ];
				  navigation = 1;
				  latitid = json[i].id
			      MP3(song,latitid); // 播放音乐
			      clearInterval(ref);  //停止循环 定位
			      red(latitid);
		       }
 			} 
	     }},
	  ]
	}); 
   }
</script>

<script type="text/javascript">
		$(document).ready(function() {
			var buttons = {
				previous: $('#jslidernews1 .button-previous'),
				next: $('#jslidernews1 .button-next')
			};
			$obj = $('#jslidernews1').lofJSidernews({
				interval: 100,
				easing: 'easeInOutQuad',
				duration: 0,
				auto: false,
				maxItemDisplay: 3,
				startItem: 0,
				navPosition: 'horizontal',
				navigatorHeight: null,
				navigatorWidth: null,
				mainWidth: 375,
				buttons: buttons
			});
		});
		(function($) { // Compliant with jquery.noConflict()
			$.fn.jCarouselLite = function(o) {
				o = $.extend({
					btnPrev: null,
					btnNext: null,
					btnGo: null,
					mouseWheel: false,
					auto: null,

					speed: 200,
					easing: null,

					vertical: false,
					circular: true,
					visible: 3,
					start: 0,
					scroll: 1,

					beforeStart: null,
					afterEnd: null
				}, o || {});

				return this.each(function() { // Returns the element collection. Chainable.

					var running = false,
						animCss = o.vertical ? "top" : "left",
						sizeCss = o.vertical ? "height" : "width";
					var div = $(this),
						ul = $("ul", div),
						tLi = $("li", ul),
						tl = tLi.size(),
						v = o.visible;

					if(o.circular) {
						ul.prepend(tLi.slice(tl - v - 1 + 1).clone())
							.append(tLi.slice(0, v).clone());
						o.start += v;
					}

					var li = $("li", ul),
						itemLength = li.size(),
						curr = o.start;

					div.css("visibility", "visible");
					li.css({
						overflow: "hidden",
						float: o.vertical ? "none" : "left"
					});
					ul.css({
						margin: "0",
						padding: "0",
						position: "relative",
						"list-style-type": "none",
						"z-index": "1"
					});
					div.css({
						overflow: "hidden",
						position: "relative",
						"z-index": "2",
						left: "0px"
					});
					var liSize = o.vertical ? height(li) : width(li); // Full li size(incl margin)-Used for animation
					var ulSize = liSize * itemLength; // size of full ul(total length, not just for the visible items)
					var divSize = liSize * v; // size of entire div(total length for just the visible items)

					li.css({
						width: li.width(),
						height: li.height()
					});
					ul.css(sizeCss, ulSize + "px").css(animCss, -(curr * liSize));

					div.css(sizeCss, divSize + "px"); // Width of the DIV. length of visible images

					if(o.btnPrev)
						$(o.btnPrev).click(function() {
							return go(curr - o.scroll);
						});

					if(o.btnNext)
						$(o.btnNext).click(function() {
							return go(curr + o.scroll);
						});

					if(o.btnGo)
						$.each(o.btnGo, function(i, val) {
							$(val).click(function() {
								return go(o.circular ? o.visible + i : i);
							});
						});

					if(o.mouseWheel && div.mousewheel)
						div.mousewheel(function(e, d) {
							return d > 0 ? go(curr - o.scroll) : go(curr + o.scroll);
						});

					if(o.auto)
						setInterval(function() {
							go(curr + o.scroll);
						}, o.auto + o.speed);

					function vis() {
						return li.slice(curr).slice(0, v);
					};

					function go(to) {
						if(!running) {

							if(o.beforeStart)
								o.beforeStart.call(this, vis());

							if(o.circular) { // If circular we are in first or last, then goto the other end
								if(to <= o.start - v - 1) { // If first, then goto last
									ul.css(animCss, -((itemLength - (v * 2)) * liSize) + "px");
									// If "scroll" > 1, then the "to" might not be equal to the condition; it can be lesser depending on the number of elements.
									curr = to == o.start - v - 1 ? itemLength - (v * 2) - 1 : itemLength - (v * 2) - o.scroll;
								} else if(to >= itemLength - v + 1) { // If last, then goto first
									ul.css(animCss, -((v) * liSize) + "px");
									// If "scroll" > 1, then the "to" might not be equal to the condition; it can be greater depending on the number of elements.
									curr = to == itemLength - v + 1 ? v + 1 : v + o.scroll;
								} else curr = to;
							} else { // If non-circular and to points to first or last, we just return.
								if(to < 0 || to > itemLength - v) return;
								else curr = to;
							} // If neither overrides it, the curr will still be "to" and we can proceed.

							running = true;

							ul.animate(
								animCss == "left" ? {
									left: -(curr * liSize)
								} : {
									top: -(curr * liSize)
								}, o.speed, o.easing,
								function() {
									if(o.afterEnd)
										o.afterEnd.call(this, vis());
									running = false;
								}
							);
							// Disable buttons when the carousel reaches the last/first, and enable when not
							if(!o.circular) {
								$(o.btnPrev + "," + o.btnNext).removeClass("disabled");
								$((curr - o.scroll < 0 && o.btnPrev) ||
									(curr + o.scroll > itemLength - v && o.btnNext) ||
									[]
								).addClass("disabled");
							}

						}
						return false;
					};
				});
			};

			function css(el, prop) {
				return parseInt($.css(el[0], prop)) || 0;
			};

			function width(el) {
				return el[0].offsetWidth + css(el, 'marginLeft') + css(el, 'marginRight');
			};

			function height(el) {
				return el[0].offsetHeight + css(el, 'marginTop') + css(el, 'marginBottom');
			};

		})(jQuery);

		(function($) {

			var types = ['DOMMouseScroll', 'mousewheel'];

			if($.event.fixHooks) {
				for(var i = types.length; i;) {
					$.event.fixHooks[types[--i]] = $.event.mouseHooks;
				}
			}

			$.event.special.mousewheel = {
				setup: function() {
					if(this.addEventListener) {
						for(var i = types.length; i;) {
							this.addEventListener(types[--i], handler, false);
						}
					} else {
						this.onmousewheel = handler;
					}
				},

				teardown: function() {
					if(this.removeEventListener) {
						for(var i = types.length; i;) {
							this.removeEventListener(types[--i], handler, false);
						}
					} else {
						this.onmousewheel = null;
					}
				}
			};

			$.fn.extend({
				mousewheel: function(fn) {
					return fn ? this.bind("mousewheel", fn) : this.trigger("mousewheel");
				},

				unmousewheel: function(fn) {
					return this.unbind("mousewheel", fn);
				}
			});

			function handler(event) {
				var orgEvent = event || window.event,
					args = [].slice.call(arguments, 1),
					delta = 0,
					returnValue = true,
					deltaX = 0,
					deltaY = 0;
				event = $.event.fix(orgEvent);
				event.type = "mousewheel";

				// Old school scrollwheel delta
				if(orgEvent.wheelDelta) {
					delta = orgEvent.wheelDelta / 120;
				}
				if(orgEvent.detail) {
					delta = -orgEvent.detail / 3;
				}

				// New school multidimensional scroll (touchpads) deltas
				deltaY = delta;

				// Gecko
				if(orgEvent.axis !== undefined && orgEvent.axis === orgEvent.HORIZONTAL_AXIS) {
					deltaY = 0;
					deltaX = -1 * delta;
				}

				// Webkit
				if(orgEvent.wheelDeltaY !== undefined) {
					deltaY = orgEvent.wheelDeltaY / 120;
				}
				if(orgEvent.wheelDeltaX !== undefined) {
					deltaX = -1 * orgEvent.wheelDeltaX / 120;
				}

				// Add event and delta to the front of the arguments
				args.unshift(event, delta, deltaX, deltaY);

				return($.event.dispatch || $.event.handle).apply(this, args);
			}

		})(jQuery);

		(function($) {
			$.fn.lofJSidernews = function(settings) {
				return this.each(function() {
					// get instance of the lofSiderNew.
					new $.lofSidernews(this, settings);
				});
			}
			$.lofSidernews = function(obj, settings) {
				this.settings = {
					direction: '',
					mainItemSelector: 'li',
					navInnerSelector: 'ul',
					navSelector: 'li',
					navigatorEvent: 'click' /* click|mouseenter */ ,
					wapperSelector: '.sliders-wrap-inner',
					interval: 5000,
					auto: false, // whether to automatic play the slideshow
					maxItemDisplay: 3,
					startItem: 0,
					navPosition: 'vertical',
					/* values: horizontal|vertical*/
					navigatorHeight: 100,
					navigatorWidth: 310,
					duration: 600,
					navItemsSelector: '.navigator-wrap-inner li',
					navOuterSelector: '.navigator-wrapper',
					isPreloaded: true,
					easing: 'easeInOutQuad',
					onPlaySlider: function(obj, slider) {},
					onComplete: function(slider, index) {}
				}
				$.extend(this.settings, settings || {});
				this.nextNo = null;
				this.previousNo = null;
				this.maxWidth = this.settings.mainWidth || 684;

				this.wrapper = $(obj).find(this.settings.wapperSelector);
				var wrapOuter = $('<div class="sliders-wrapper"></div>').width(this.maxWidth);
				this.wrapper.wrap(wrapOuter);

				this.slides = this.wrapper.find(this.settings.mainItemSelector);
				if(!this.wrapper.length || !this.slides.length) return;
				// set width of wapper
				if(this.settings.maxItemDisplay > this.slides.length) {
					this.settings.maxItemDisplay = this.slides.length;
				}
				this.currentNo = isNaN(this.settings.startItem) || this.settings.startItem > this.slides.length ? 0 : this.settings.startItem;
				this.navigatorOuter = $(obj).find(this.settings.navOuterSelector);
				this.navigatorItems = $(obj).find(this.settings.navItemsSelector);
				this.navigatorInner = this.navigatorOuter.find(this.settings.navInnerSelector);
				// if use automactic calculate width of navigator

				if(this.settings.navigatorHeight == null || this.settings.navigatorWidth == null) {
					this.settings.navigatorHeight = this.navigatorItems.eq(0).outerWidth(true);
					this.settings.navigatorWidth = this.navigatorItems.eq(0).outerHeight(true);

				}
				if(this.settings.navPosition == 'horizontal') {
					this.navigatorInner.width(this.slides.length * this.settings.navigatorWidth);
					this.navigatorOuter.width(this.settings.maxItemDisplay * this.settings.navigatorWidth);
					this.navigatorOuter.height(this.settings.navigatorHeight);

				} else {
					this.navigatorInner.height(this.slides.length * this.settings.navigatorHeight);

					this.navigatorOuter.height(this.settings.maxItemDisplay * this.settings.navigatorHeight);
					this.navigatorOuter.width(this.settings.navigatorWidth);
				}
				this.slides.width(this.settings.mainWidth);
				this.navigratorStep = this.__getPositionMode(this.settings.navPosition);
				this.directionMode = this.__getDirectionMode();

				if(this.settings.direction == 'opacity') {
					this.wrapper.addClass('lof-opacity');
					$(this.slides).css({
						'opacity': 0,
						'z-index': 1
					}).eq(this.currentNo).css({
						'opacity': 1,
						'z-index': 3
					});
				} else {
					this.wrapper.css({
						'left': '-' + this.currentNo * this.maxSize + 'px',
						'width': (this.maxWidth) * this.slides.length
					});
				}

				if(this.settings.isPreloaded) {
					this.preLoadImage(this.onComplete);
				} else {
					this.onComplete();
				}

				$buttonControl = $(".audio-select");
				if(this.settings.auto) {
					$buttonControl.addClass("action-stop");
				} else {
					$buttonControl.addClass("action-start");
				}
				var self = this;

				$buttonControl.click(function() {
					if($buttonControl.hasClass("action-start")) {
						self.settings.auto = true;
						self.play(self.settings.interval, 'next', true);
						$buttonControl.removeClass("action-start").addClass("action-stop");
					} else {
						self.settings.auto = false;
						self.stop();
						$buttonControl.addClass("action-start").removeClass("action-stop");
					}
				});
			}
			$.lofSidernews.fn = $.lofSidernews.prototype;
			$.lofSidernews.fn.extend = $.lofSidernews.extend = $.extend;

			$.lofSidernews.fn.extend({

				startUp: function(obj, wrapper) {
					seft = this;

					this.navigatorItems.each(function(index, item) {
						$(item).bind(seft.settings.navigatorEvent, (function() {
							seft.jumping(index, true);
							seft.setNavActive(index, item);
						}));
						$(item).css({
							'height': seft.settings.navigatorHeight,
							'width': seft.settings.navigatorWidth
						});
					})
					this.registerWheelHandler(this.navigatorOuter, this);
					this.setNavActive(this.currentNo);
					this.settings.onComplete(this.slides.eq(this.currentNo), this.currentNo);
					if(this.settings.buttons && typeof(this.settings.buttons) == "object") {
						this.registerButtonsControl('click', this.settings.buttons, this);

					}
					if(this.settings.auto)
						this.play(this.settings.interval, 'next', true);

					return this; 
				},
				onComplete: function() {
					setTimeout(function() {
						$('.preload').fadeOut(900, function() {
							$('.preload').remove();
						});
					}, 400);
					this.startUp();
				},
				preLoadImage: function(callback) {
					var self = this;
					var images = this.wrapper.find('img');

					var count = 0;
					images.each(function(index, image) {
						if(!image.complete) {
							image.onload = function() {
								count++;
								if(count >= images.length) {
									self.onComplete();
								}
							}
							image.onerror = function() {
								count++;
								if(count >= images.length) {
									self.onComplete();
								}
							}
						} else {
							count++;
							if(count >= images.length) {
								self.onComplete();
							}
						}
					});
				},
				navivationAnimate: function(currentIndex) {
					if(currentIndex <= this.settings.startItem ||
						currentIndex - this.settings.startItem >= this.settings.maxItemDisplay - 1) {
						this.settings.startItem = currentIndex - this.settings.maxItemDisplay + 2;
						if(this.settings.startItem < 0) this.settings.startItem = 0;
						if(this.settings.startItem > this.slides.length - this.settings.maxItemDisplay) {
							this.settings.startItem = this.slides.length - this.settings.maxItemDisplay;
						}
					}

					this.navigatorInner.stop().animate(eval('({' + this.navigratorStep[0] + ':-' + this.settings.startItem * this.navigratorStep[1] + '})'), {
						duration: 500,
						easing: 'easeInOutQuad'
					});
				},
				setNavActive: function(index, item) {
					if((this.navigatorItems)) {
						this.navigatorItems.removeClass('active');
						$(this.navigatorItems.get(index)).addClass('active');
						this.navivationAnimate(this.currentNo);
					}
				},
				__getPositionMode: function(position) {
					if(position == 'horizontal') {
						return ['left', this.settings.navigatorWidth];
					}
					return ['top', this.settings.navigatorHeight];
				},
				__getDirectionMode: function() {
					switch(this.settings.direction) {
						case 'opacity':
							this.maxSize = 0;
							return ['opacity', 'opacity'];
						default:
							this.maxSize = this.maxWidth;
							return ['left', 'width'];
					}
				},
				registerWheelHandler: function(element, obj) {
					element.bind('mousewheel', function(event, delta) {
						var dir = delta > 0 ? 'Up' : 'Down',
							vel = Math.abs(delta);
						if(delta > 0) {
							obj.previous(true);
						} else {
							obj.next(true);
						}
						return false;
					});
				},
				registerButtonsControl: function(eventHandler, objects, self) {
					for(var action in objects) {
						switch(action.toString()) {
							case 'next':
								objects[action].click(function() {
									self.next(true)
								});
								break;
							case 'previous':
								objects[action].click(function() {
									self.previous(true)
								});
								break;
						}
					}
					return this;
				},
				onProcessing: function(manual, start, end) {
					this.previousNo = this.currentNo + (this.currentNo > 0 ? -1 : this.slides.length - 1);
					this.nextNo = this.currentNo + (this.currentNo < this.slides.length - 1 ? 1 : 1 - this.slides.length);
					return this;
				},
				finishFx: function(manual) {
					if(manual) this.stop();
					if(manual && this.settings.auto) {
						this.play(this.settings.interval, 'next', true);
					}
					this.setNavActive(this.currentNo);
					this.settings.onPlaySlider(this, $(this.slides).eq(this.currentNo));
				},
				getObjectDirection: function(start, end) {
					return eval("({'" + this.directionMode[0] + "':-" + (this.currentNo * start) + "})");
				},
				fxStart: function(index, obj, currentObj) {
					var s = this;
					if(this.settings.direction == 'opacity') {

						$(this.slides).stop().animate({
							opacity: 0
						}, {
							duration: this.settings.duration,
							easing: this.settings.easing,
							complete: function() {
								s.slides.css("z-index", "1")
								s.slides.eq(index).css("z-index", "3");
							}
						});
						$(this.slides).eq(index).stop().animate({
							opacity: 1
						}, {
							duration: this.settings.duration,
							easing: this.settings.easing,
							complete: function() {
								s.settings.onComplete($(s.slides).eq(index), index);
							}
						});
					} else {
						this.wrapper.stop().animate(obj, {
							duration: this.settings.duration,
							easing: this.settings.easing,
							complete: function() {
								s.settings.onComplete($(s.slides).eq(index), index)
							}
						});
					}
					return this;
				},
				jumping: function(no, manual) {
					this.stop();
					if(this.currentNo == no) return;
					var obj = eval("({'" + this.directionMode[0] + "':-" + (this.maxSize * no) + "})");
					this.onProcessing(null, manual, 0, this.maxSize)
						.fxStart(no, obj, this)
						.finishFx(manual);
					this.currentNo = no;
				},
				next: function(manual, item) {

					this.currentNo += (this.currentNo < this.slides.length - 1) ? 1 : (1 - this.slides.length);
					this.onProcessing(item, manual, 0, this.maxSize)
						.fxStart(this.currentNo, this.getObjectDirection(this.maxSize), this)
						.finishFx(manual);
				},
				previous: function(manual, item) {
					this.currentNo += this.currentNo > 0 ? -1 : this.slides.length - 1;
					this.onProcessing(item, manual)
						.fxStart(this.currentNo, this.getObjectDirection(this.maxSize), this)
						.finishFx(manual);
				},
				play: function(delay, direction, wait) {
					this.stop();
					if(!wait) {
						this[direction](false);
					}
					var self = this;
					this.isRun = setTimeout(function() {
						self[direction](true);
					}, delay);
				},
				stop: function() {
					if(this.isRun == null) return;
					clearTimeout(this.isRun);
					this.isRun = null;
				}
			})
		})(jQuery)
	</script>
<script language="javascript"> 
		
		var interval2 = window.setInterval("test()", 100);
		var array = new Array();
		var index = 0;
		var array = new Array("lib/images/man_1.png", "lib/images/man_2.png", "lib/images/man_3.png", "lib/images/man_4.png", "lib/images/man_5.png", "lib/images/man_6.png", "lib/images/man_7.png", "lib/images/man_8.png", "lib/images/man_9.png", "lib/images/man_10.png", "lib/images/man_11.png", "lib/images/man_12.png", "lib/images/man_13.png", "lib/images/man_14.png", "lib/images/man_15.png", "lib/images/man_16.png", "lib/images/man_17.png", "lib/images/man_18.png", "lib/images/man_19.png", "lib/images/man_20.png", "lib/images/man_21.png");

		function test() {
			var myimg = document.getElementById("imgs");
			if(index == array.length - 1) {
				index = 0;
			} else {
				index++;
			}
			myimg.src = array[index];
		}		
		setTimeout(function() {
			window.clearInterval(interval2);
		}, 2000);
		$(document).ready(function(){
			$('#ren').delay(2000).hide(0);
			$('.fugai').delay(24000).hide(0);
		 	$('.info').delay(24000).hide(0); 
		});
		setTimeout(function() {
			$('.sliders-wrap-inner').show();
		}, 2000); //延迟2000毫米
		
		setTimeout(function() {
		$('.audio-container').show();
		$('#zhanshi').show();
		}, 24000); //延迟2000毫米
		
	</script>


<body>
	 <div class="fugai"></div> 
	<audio style="display: none;" controls="" autoplay="" name="media">
		<source src="horse.ogg" type="audio/ogg">
		<source src="lib/images/huanying.mp3" type="audio/mpeg">
	</audio>
	<div class="yuyan"
		style="display:none;z-index:11111111111111111;color:black;font-size:14px;width: 50%;height: 35%;background: #FFFFFF;position: fixed;top:29%;left: 25%;border-radius:8px;">
		<p style="text-align:center;margin: 8% 0;">选择语言</p>
		<ul>
			<li style="margin: 5% 0;"><input type="radio" name="sex"
				id="male" value="male" checked="checked" /><label for="中文男声">中文男声</label></li>
			<li style="margin: 5% 0;"><input type="radio" name="sex"
				id="male" value="male" /><label for="中文女声">中文女声</label></li>
			<li style="margin: 5% 0;"><input type="radio" name="sex"
				id="male" value="male" /><label for="唐山方言">唐山方言</label></li>
			<li style="margin: 5% 0;"><input type="radio" name="sex"
				id="male" value="male" /><label for="四川方言">四川方言</label></li>
		</ul>
		<div id=""
			style="background:#FFFFFF;width:100%;margin:0px;padding:0px;text-align: center;position: absolute;bottom: 0;border-radius:8px;">
			<button class="quxiao"
				style="color:#6B7477;background:#FFFFFF;border:1px solid #F0F0F0;width: 50%;height: 42px;margin:0;padding:0;float: left;outline: none;border-bottom-left-radius: 8px;border-left:none ;border-bottom:none ;">取消</button>
			<button class="queding"
				style="color:#6B7477;background:#FFFFFF;border:1px solid #F0F0F0; width: 50%;height: 42px;margin:0;padding:0;float: right;outline: none;border-bottom-right-radius: 8px;border-right:none ;border-bottom:none ;">确定</button>
		</div>

	</div>
	<div id="container" style="">
		<div class="nav"
			style="width:100%;height:40px;background:#ffffff;text-align: center;line-height: 40px;position:relative;z-index:10;">
			<span
				style="color:black;font-weight: bold;float:left;line-height: 40px;font-size:20px;margin-left:3%;">
				< </span> 
				<img
				style="width:5%;height:40%;position: absolute;left: 40%;top:32%;"
				src="lib/images/sousuo.png" />
				<input class="sousuo"
				placeholder="&nbsp景点搜索"
				style="width:25%;height:25px;text-indent: 25px;margin-left:-3%;border-radius: 25px;border:solid 1px #BDBDBD;outline:none;padding: 0px 3px; "></input> 
		</div>
		<div id="zhanshi"
			style="position:fixed;width:50%;z-index:10;display: inline-block;display: none;">
			<ul
				style="color:black;font-size:14px;margin:5% 5%;list-style:none;display: inline-block; width:50%;line-height:26px;">
				<li><img style="width:20px;height:20px;vertical-align: middle;"
					src="lib/images/hong.png" />未游览</li>
				<li><img style="width:20px;height:20px;vertical-align: middle;"
					src="lib/images/huang.png" />已游览</li>
				<li><img style="width:20px;height:20px;vertical-align: middle;"
					src="lib/images/zi.png" />搜索结果</li>    
			</ul>
		</div>
		<div id="ren" style="width: 100%;">
			<img id="imgs" style="" src="lib/images/man_1.png" />
		</div>

		<div id="jslidernews1" class="lof-slidecontent">
			<ul class="sliders-wrap-inner">
				<li><img src="lib/images/man_20.png" /></li>
				<li><img src="lib/images/man_21.png" /></li>
				<li><img src="lib/images/man_22.png" /></li>
				<li><img src="lib/images/man_23.png" /></li>
				<li><img src="lib/images/man_24.png" /></li>
			</ul>

			<div class="navigator-content">
				<div class="button-control"></div>
			</div>

			<div class="tishi" style="z-index:1000;">
				<ul>

					<li><img id="imgss" style="width:40%;height:65%;"
						src="lib/images/yuyan.png" /></li>
					<li><img class="img2" style="width:40%;height:65%;"
						src="lib/images/luxian.png" /></li>
					<li><img onclick="clickHandler()" class="img3" style="width:40%;height:65%;"
						src="lib/images/jilu.png" /></li>
					<li><img class="img4" style="width:40%;height:65%;"
						src="lib/images/zhishu.png" /></li>
				</ul>
			</div>
		</div>

		<div class="audio-container"
			style="border:2px solid #3C94F9;border-radius: 12px;display:none ;">
			<div class="audio-view">
				<P style="color:#3C94F9;font-size: 12px;margin: 4px 3px;">前方即将到达</P>
				<!--	<div class="audio-cover" ></div>-->
				<div class="audio-body">
					<h3 class="audio-title">未知歌曲</h3>
					<div class="audio-backs">

						<div class="audio-btn">
							<div class="audio-select">
								<div class="audio-play"></div>
							</div>
						</div>
						<div class="audio-setbacks">
							<i class="audio-this-setbacks"> <span class="audio-backs-btn"></span>
							</i> <span class="audio-cache-setbacks"> </span>

						</div>

					</div>
					<p id="xianshi"
						style="font-size:12px;color:#3C94F9;position:absolute;left:30%;top:70%;">显示详情</p>
				</div>

			</div>

		</div>
		<div id="xiangqing"
			style="width: 65%;height: 35%;display:none;z-index: 100;box-shadow: 0px 3px 5px #FFF; overflow-x: hidden;word-break:break-all;word-wrap:break-word;border-radius: 12px;color: #3C94F9;font-size:13.5px;position: fixed;top:50px;left:3%;border:1px solid #3C94F9;background-color:rgba(255,255,255,0.5);">
			<p  id = "pid" style="padding:2px;">进了山门,眼前呈现出一座长16米,高4.5米的汉白玉孝经影壁,上面刻着儒家十三经中最著名的《孝经》，宣扬了中华名族的孝道文明。经文的大意是：君王、臣子、百官、庶民，都要遵从百善孝为先的思想，为国尽忠，为父母尽孝是做人的根本，它的精神应为华夏子孙去继承和光大。为什么在迎门设置影壁呢？因为影壁在风水学上称为玄关，他避开了一箭穿心的弊端。通过弯曲的道路把人引入，一个别有洞天的境界，在两侧有两个耳碑，上面是左青龙右白虎，有阻挡煞气的作用。</p>
		</div>
	</div>

	<div class="div_txt" style="width: 100%;height:100%;display: none;">
		<div id=""
			style="width: 100%;height:44px;position: fixed;background: #fff;line-height: 44px;">
			<p id="quxiao"
				style="color:black;display: inline-block;font-size: 14px;margin-left: 5%;">取消</p>
			<input type="text" id="txt1" placeholder="搜索"
				style="height:34px;border:none;outline:none;display: inline-block;width: 60%;margin: 0 auto;background-color: #FBFBFB;">
			<button id="but"
				style="width:15%;margin-right: 5%;height:34px;margin-top:5px;border:none;outline:none;display: inline-block;float: right;background-color: #00C79D;color: #fff;">搜索</button>
		</div>
		<div
			style="border-bottom: solid 1px #E0E0E0;width:100%;position: fixed;top:44px;"></div>
		<div style="color: black;height: 44px;"></div>

	</div>
</body>
<script type="text/javascript">
var id;
		function MP3(e,m){  
		    id =  m;//  是景点id
			$(".sousuo").click(function() {
				$("#container").hide();
				$(".div_txt").show();
				$(".yuyan").hide();
			});

			$("#quxiao").click(function() {
				$("#container").show();
				$(".div_txt").hide();
			});
			
			$("#imgss").click(function() {
				$(".yuyan").show();
			});
			$(".quxiao").click(function() {
				$(".yuyan").hide();
			});
			$(".queding").click(function() {
				$(".yuyan").hide();
			});
			/*  if($(".audio-this-setbacks").style.width = 100%){
     	_this.audio.currentTime = 0;
        _this.audio.pause();
        };*/

			/* window.song = [{

					'src': 'lib/images/huanying.mp3',
					'title': '孝经'
				}
			]; */

			var audioFn = audioPlay({
				song: e,
				autoPlay: true //是否立即播放第一首，autoPlay为true且song为空，会alert文本提示并退出
			});

			/* 向歌单中添加新曲目，第二个参数true为新增后立即播放该曲目，false则不播放 */
			/*	audioFn.newSong({
					'cover' : 'images/cover5.jpg',
					'src' : 'https://amazingaudioplayer.com/wp-content/uploads/amazingaudioplayer/5/audios/Soaring%20Spirit.mp3',
					'title' : 'B.A.A.B'
				},false);*/

			/* 暂停播放 */
			//audioFn.stopAudio();

			/* 开启播放 */
			  audioFn.playAudio();

			/* 选择歌单中索引为3的曲目(索引是从0开始的)，第二个参数true立即播放该曲目，false则不播放 */
			//audioFn.selectMenu(3,true);

			/* 查看歌单中的曲目 */
			//console.log(audioFn.song);

			/* 当前播放曲目的对象 */
			//console.log(audioFn.audio);

		};
             $("#xianshi").click(function() {
				if($("#xianshi").html()=='显示详情'){	
				    $("#xiangqing").show();
				   	$("#zhanshi").hide();
				    $("#xianshi").html("隐藏详情");
				}else if($("#xianshi").html()=='隐藏详情'){		
					$("#xiangqing").hide();
					$("#zhanshi").show();
				    $("#xianshi").html("显示详情");
				}
			});
		(function($) {

			var fnName = 'audioPlay';
			var config = {

				view: ".audio-view",

				title: ".audio-title",

				/*cover : ".audio-cover",*/

				autoPlay: false,

				volume: {

					volumeView: ".audio-set-volume",
					volumeBox: ".volume-box",
				},

				timeView: {

					thisTime: ".audio-this-time",

					countTime: '.audio-count-time',
				},

				setbacks: {

					setbacks: '.audio-setbacks',

					thisSetbacks: '.audio-this-setbacks',

					cacheSetbacks: ".audio-cache-setbacks",

					volumeSetbacks: ".volume-box > i",

					volumeCircular: ".volume-box > i span"
				},

				button: {

					backs: ".audio-backs-btn",

					play: ".audio-play",

					menuClose: ".menu-close"
				},

				menu: {

					menuView: '.audio-list',

					colse: '.close',

					list: '.audio-inline'
				},

				song: null
			};

			var songEq = 0,
				volumeSize = 0.7;

			window[fnName] = function(setConfig) {

				//设置属性值
				if(typeof(setConfig) == "object") {

					for(var n in setConfig) {

						config[n] = setConfig[n];
					}
				}

				var _this = config,
					playDate;

				/*cover = $(_this.cover),*/
				var title = $(_this.title),
					thisTime = $(_this.timeView.thisTime),
					countTime = $(_this.timeView.countTime),
					thisSetbacks = $(_this.setbacks.thisSetbacks),
					cacheSetbacks = $(_this.setbacks.cacheSetbacks),
					setbacks = $(_this.setbacks.setbacks),
					volumeCircular = $(_this.setbacks.volumeCircular),
					volumeSetbacks = $(_this.setbacks.volumeSetbacks),
					volumeBox = $(_this.volume.volumeBox),
					play = $(_this.button.play);
					/* prev = $(_this.button.prev),
					next = $(_this.button.next),
					menuBtn = $(_this.button.menu),
					volume = $(_this.button.volume),
					menuClose = $(_this.button.menuClose),
					backs = $(_this.button.backs); */

				_this.createAudio = function() {

					if(!_this.audio) {

						_this.audio = new Audio();
					}

					var song = config.song;
					/* if(!song) {
						alert('当前没有音乐!!!');
						return false;
					} */

					_this.stopAudio();
					_this.audio.src = song[songEq].src;

					_this.volumeSet();

					title.text(song[songEq].title || '未知音乐');
					/*cover.css({
						'backgroundImage' : 'url('+(song[songEq].cover || '')+')'
					});*/

					function setDuration() {

						if(isNaN(_this.audio.duration)) {

							setTimeout(setDuration, 50);
						} else {

							countTime.text(_this.conversion(_this.audio.duration));
						}
					}
					setDuration(_this.audio.duration);

					thisTime.text(_this.conversion(_this.audio.currentTime));

					_this.audio.onended = function() {

						setTimeout(function() {

						++songEq;
						songEq = (songEq < _this.song.length) ? songEq : 0;
						_this.selectMenu(songEq, false);
						}, 500);
						/* _this.audio.currentTime = 0;
       					 _this.audio.pause();
       					 _this.playAudio();
       					 self.stop();
       					$(".audio-stop").css("background-image","url(lib/images/stop.png)"); */
       					_this.audio.currentTime = 0;
			             _this.audio.pause();
			             _this.stopAudio();
       				     setaddChild(latitid,navigation); // 存用户已浏览点
       					$(".audio-play").click(function() {
       					if($buttonControl.hasClass("action-start")){
       					  	$(".audio-play").css("background-image","url(lib/images/plays.png)"); 
       					  	}else{
       					  	$(".audio-stop").css("background-image","url(lib/images/stop.png)");
       					  	}
						}); 
					}

				}

				var timeAudio;
				_this.playAudio = function() {
					if(_this.audio) {
						if(!playDate || (Date.now() - playDate) > 100) {

							playDate = Date.now();

							(!_this.audio.paused) || _this.audio.pause();

							_this.audio.play();
							play.addClass('audio-stop').one('click', function() {
								_this.stopAudio();
								$(this).removeClass('audio-stop').one('click', function() {
									_this.playAudio();
								});
							});

							timeAudio = setInterval(function() {

								if(_this.audio.readyState == 4) {

									cacheSetbacks.css({
										'width': (_this.audio.buffered.end(0) / _this.audio.duration) * 100 + "%"
									});
								}

								thisSetbacks.css({
									'width': (_this.audio.currentTime / _this.audio.duration) * 100 + "%"
								});

								thisTime.text(_this.conversion(_this.audio.currentTime));
							}, 500);
						} else {

							setTimeout(function() {

								_this.playAudio();
							}, 50);
						}
					}
				}

				_this.stopAudio = function() {

					if(!playDate || (Date.now() - playDate) > 100) {

						playDate = Date.now();
						_this.audio.pause();
						clearInterval(timeAudio);
					} else {

						setTimeout(function() {

							_this.stopAudio();
						}, 50);
					}
				}

				_this.conversion = function(num) {

					function changInt(num) {

						return(num < 10) ? '0' + num : num;
					}

					return changInt(parseInt(num / 60)) + ":" + changInt(Math.floor(num % 60));
				}

				_this.upMenu = function() {

					var song = _this.song,
						inline = $(_this.menu.list).empty();

					/* for(var i in song) {

						inline.append("<li><a href='javascript:;'>" + (song[i].title || '52player.cn提示：未知歌曲') + "</a></li>");
					}
					 inline.find(">li").unbind('click').on('click', function() {

						_this.selectMenu($(this).index(), true);
					}); */
				}

				_this.selectMenu = function(num, _bool) {

					songEq = num;
					_this.createAudio();
					(_bool) && _this.playAudio();
				}

				_this.volumeSet = function() {

					_this.audio.volume = volumeSize;
					volumeSetbacks.css({
						'height': volumeSize * 100 + "%"
					});
				}

				/* _this.newSong = function(_new, _bool) {

					if(typeof(_new) == 'object') {

						if(_new.src) {

							if(_this.song) {

								_this.song.push(_new);
							} else {

								_this.song = [_new];
							}

							_this.upMenu();
							(_bool) && _this.selectMenu(_this.song.length - 1, true);
						} else {

							alert('提示：对象缺省src属性');
						}
					} else {

						alert('提示：这不是一个对象');
					}
				} */

				var volumeTime;
				volumeBox.on('mousedown', function() {

					if(_this.audio) {
						var Y, EndY = parseInt(volumeBox.css('height')),
							goY;
						volumeBox.on('mousemove click', function(e) {

							clearTimeout(volumeTime);

							Y = (e.clientY - (volumeBox.offset().top - $(document).scrollTop()));
							Y = (Y > 0) ? (Y > EndY) ? EndY : Y : 0;

							goY = Y / EndY;

							volumeSize = 1 - goY;

							_this.volumeSet();
						});

						volumeBox.one('mouseup', function() {

							volumeBox.unbind('mousemove');
						}).on('mouseout', function() {

							volumeTime = setTimeout(function() {

								volumeBox.unbind('mousemove');
							}, 500);
						});
					}
				});

				setbacks.on('mousedown', function() {

					if(_this.audio) {
						var X, EndX = parseInt(setbacks.css('width')),
							goX, mouseTime;
						setbacks.on('mousemove click', function(e) {

							_this.stopAudio();
							clearTimeout(mouseTime);

							X = (e.clientX - setbacks.offset().left);
							X = (X > 0) ? (X > EndX) ? EndX : X : 0;

							goX = X / EndX;
							thisSetbacks.css({
								'width': goX * 100 + "%"
							});

							_this.audio.currentTime = parseInt(goX * _this.audio.duration);
							thisTime.text(_this.conversion(_this.audio.currentTime));
						});

						setbacks.one('mouseup', function() {

							_this.playAudio();
							setbacks.unbind('mousemove');
						}).on('mouseout', function() {

							mouseTime = setTimeout(function() {

								_this.playAudio();
								setbacks.unbind('mousemove');
							}, 500);
						});
					}
				});

				play.one('click', function() {

					_this.playAudio();
				});

			/* 	menuBtn.on('click', function() {

					$(_this.menu.menuView).toggleClass('menu-show');
				}); 

				prev.on('click', function() {

					--songEq;
					songEq = (songEq >= 0) ? songEq : _this.song.length - 1;
					_this.selectMenu(songEq, true);
				});

				next.on('click', function() {

					++songEq;
					songEq = (songEq < _this.song.length) ? songEq : 0;
					_this.selectMenu(songEq, true);
				});

				menuClose.on('click', function() {

					$(_this.menu.menuView).removeClass('menu-show');
				});

				volume.on('click', function() {

					$(_this.volume.volumeView).toggleClass('audio-show-volume');
				}); */

				_this.upMenu();

				_this.selectMenu(songEq, _this.autoPlay);

				return _this;
			}
		})(jQuery)
	</script>

</html>



