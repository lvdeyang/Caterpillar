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

<title>打卡点管理</title>

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
	min-height: 100%;
	background-color: #fff;
	position: relative;
	-webkit-text-size-adjust: none;
	background-color: #fbfbfb;
}

* {
	box-sizing: border-box;
}

.wrapper {
	height: 100%;
	width: 100%;
	position: relative;
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
	height: 100%;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
}


.content {
	
}


.weui-btn {
	color: #fff !important;
}

.weui-btn:after {
	border-radius: 0 !important;
}

    .weui-panel__bd{
        padding-bottom:0px !important;
    }
    .weui-cells_checkbox .weui-icon-checked:before{
       font-size:12px !important;
    }
    .weui-navbar__item{
      font-size:xx-small !important;
    }
    i.icon{
      display:inline-block !important;
    }
.weui-label{
font-size:20px !important;
}
.weui-input{
font-size:20px !important;
}
.weui-select{
font-size:20px !important;
}
.weui-btn{
font-size:20px !important;
}

</style>

</head>
<jsp:include page="../../../mobile/commons/jsp/scriptsec.jsp"></jsp:include>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.13&key=ff4672efcfc6279cbe3b2815dd70a1ec"></script>
<!-- 公共脚本引入 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">

	$(function() {
	    
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;
			}

	    };
	    
	    
	    
	    initList();
	    
	    function initList(){
	       var _uri = window.BASEPATH+'/sec/phoneapp/getPointList';
		   var param={};

		   $.post(_uri, $.toJSON(param), function(data){
		        var html=[];
		        $('#pointList').children().remove();
		        for(var i=0;i<data.message.length;i++){
		            var title=data.message[i].name;
		            if(data.message[i].type=='ONWORK'){
		               title+="(上班打卡点)";
		            }else if(data.message[i].type=='OFFWORK'){
		               title+="(下班打卡点)";
		            }else if(data.message[i].type=='CRUISE'){
		               title+="(巡逻点)";
		            }
		        
		        	html.push('<div class="weui-cell weui-cell_swiped setpoint" id="setpoint-'+data.message[i].id+'">');
			        html.push('<div class="weui-cell__bd" style="transform: translate3d(0px, 0px, 0px);">');
			        html.push('<div class="weui-cell">');
			        html.push('<div class="weui-cell__bd">');
			        html.push('<p style="font-size:18px">'+title+'</p></div>');
			        html.push('<div class="weui-cell__ft"></div></div></div>');
			        html.push('<div class="weui-cell__ft">');
			        html.push('<a class="weui-swiped-btn weui-swiped-btn_warn delete-swipeout" style="font-size:18px" id="delete-'+data.message[i].id+'" href="javascript:">删除</a>');
			        html.push('<a class="weui-swiped-btn weui-swiped-btn_default close-swipeout" style="font-size:18px" id="schedule-'+data.message[i].id+'"  href="javascript:">排期</a>');
			        html.push('</div></div>');
			       
		        }
		        $('#pointList').append(html.join(''));
		        $('.weui-cell_swiped').swipeout();
		        $('.delete-swipeout').on('click',function(){
			    	var ids=this.id.split('-');
			    	var _uri = window.BASEPATH+'/sec/phoneapp/delPoint';
				    var param={};
				    param.id=ids[1];
					$.post(_uri, $.toJSON(param), function(data){
						data = parseAjaxResult(data);
						if(data === -1) return;
			            $('.weui-cell_swiped').swipeout('close') //关闭
			            initList();
					});
		    	});
			    $('.close-swipeout').on('click',function(){
			    	var ids=this.id.split('-');
			        location.href=window.BASEPATH+'/sec/phoneapp/setpointtime/index?pointId='+ids[1];
			    });
			    
		   });
	    }
	    
	    
	    var x,y;
		var marker=null;
	    var map = new AMap.Map('container', {
		    zoom:13,//级别
		    viewMode:'3D'//使用3D视图
	    });
	    map.on('click', function(e) {
             $('#position').html('经度:'+e.lnglat.getLng() + '&nbsp;&nbsp;' +'纬度:'+ e.lnglat.getLat());
             x=e.lnglat.getLat();
             y=e.lnglat.getLng();
             
             if(marker!=null){
             	map.remove(marker);
             }
             marker = new AMap.Marker({
			    icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			    position: [y, x]
			});
			map.add(marker);
    	});
    	
    	
    	//微信定位
		var loca={};
		function getloca(){
		      
			  var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
		
			  var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			  $.get(_uri, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
						loca=data;
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
		                success: function (res) {  
		                    var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90  
		                    var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。  
		                    $('#position').html('经度:'+res.longitude + '&nbsp;&nbsp;' +'纬度:'+ res.latitude);
             				x=res.latitude;
             				y=res.longitude;
		                    var speed = res.speed; // 速度，以米/每秒计  
		                    var accuracy = res.accuracy; // 位置精度  
	                        if(marker!=null){
	                        	map.remove(marker);
	                        }
	                        marker = new AMap.Marker({
							    icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
							    position: [longitude, latitude]
							});
							map.add(marker);
							var position = new AMap.LngLat(longitude, latitude);  // 标准写法
							// 简写 var position = [116, 39]; 
							map.setCenter(position); 
		                },  
		                cancel: function (e) {  
		                        //这个地方是用户拒绝获取地理位置  
		                       console.log(e)
						}
		                	
		             });     
			         wx.error(function (res) {  
			             console.log(res)
					
			         });     
		            
		       });
		  
		  
		}
    	
	    
	    $('#add').on('click',function(){
	    	$('#editDialog').popup();
			getloca();
	    });
	    
	    $('#save').on('click',function(){
	        var _uri = window.BASEPATH+'/sec/phoneapp/addPoint';
		    var param={};
		    param.type=$('#type').val();
		    param.x=x;
		    param.y=y;
		    param.name=$('#name').val();
		    param.distance=$('#distance').val();
			$.post(_uri, $.toJSON(param), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
	            $.closePopup();
	            initList();
			});
	    	
	    });
	    
	    $('#cancel').on('click',function(){
	    	$.closePopup();
	    });

	    
	});
		
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">打卡点管理</div>
			</div>
		</div>
		
		<div id="content" class="content">
		    <div class="weui-cells__title" style="height:30px;font-size:20px">左滑动编辑打卡点
		    <a style="float:right;height:30px;line-height:30px;" id="add" href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary">添加</a></div>
            <div style="width:100%" id="pointList"></div>
            <div id="editDialog" class="weui-popup__container">
			  <div class="weui-popup__overlay"></div>
			  <div class="weui-popup__modal">
			    	<div id="container" style="width:100%;height:400px;"></div>
					<div class="weui-cell weui-cell_vcode" style="margin-top:15px;">
					    <div class="weui-cell__hd">
					      <label class="weui-label">打卡位置</label>
					    </div>
					    <div class="weui-cell__bd"><span id="position"></span></div>
		  			</div>
		  			<div class="weui-cell weui-cell_vcode" style="margin-top:15px;">
					    <div class="weui-cell__hd">
					      <label class="weui-label">打卡点名称</label>
					    </div>
					    <div class="weui-cell__bd">
					      <input style="height:40px" id="name" class="weui-input" type="text" placeholder="请输入名称">
					    </div>
		  			</div>
		  			<div class="weui-cell weui-cell_vcode" style="margin-top:10px;">
					    <div class="weui-cell__hd">
					      <label class="weui-label">打卡距离</label>
					    </div>
					    <div class="weui-cell__bd">
					      <input style="height:40px" id="distance" class="weui-input" type="text" placeholder="请输入距离">
					    </div>
		  			</div>
				    <div class="weui-cell weui-cell_select weui-cell_select-after"  style="margin-top:10px;">
				        <div class="weui-cell__hd">
				          <label for="" class="weui-label">打卡点类型</label>
				        </div>
				        <div class="weui-cell__bd">
				          <select class="weui-select" name="type" id="type">
				            <option value="ONWORK">上班</option>
				            <option value="OFFWORK">下班</option>
				            <option value="CRUISE">巡逻</option>
				          </select>
				        </div>
				    </div>
				    <div style="width:200px;margin:0 auto"> <a style="" id="save" href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary">保存</a>
				    <a style="" id="cancel" href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary">关闭</a></div>
				   
			  </div>
			</div>
		
		
		</div>	
	</div>
	
	
	
</body>
</html>