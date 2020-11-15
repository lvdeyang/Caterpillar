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

<title>用户首页</title>

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

</style>

</head>
<jsp:include page="../../../mobile/commons/jsp/scriptsec.jsp"></jsp:include>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.4.13&key=ff4672efcfc6279cbe3b2815dd70a1ec"></script>
<!-- 公共脚本引入 -->
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">

	$(function() {
	    var pointId=0;
	    var x='';
	    var y='';
	    var type='';
	    var name='';
	    var curx='';
	    var cury='';
	    var distance=0;
	    var marker=null;
	    var circle=null;
	    
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;
			}

	    };
	    
	    
	    var map = new AMap.Map('container', {
		        zoom:13,//级别
		        viewMode:'3D'//使用3D视图
	    });
	    
	
	    $('#selectPoint').on('click',function(){
	       $("#selPoint").popup();
	    });
	    
	    $('.selPoint').on('click',function(){
	       initPoint(this);
	       $.closePopup();
	    });
	    
	    initPoint($('.selPoint')[0]);
	    
	    function initPoint(obj){
	       if(!obj){
	          $.toptip('没有配置打卡点', 'error');
	          return false;
	       }
	       var ids=$(obj).attr('id').split('-');
	       pointId=ids[1];
	       var datas=$(obj).attr('data').split('-');
	       y=datas[1];
	       x=datas[0];
	       type=datas[2];
	       name=datas[3];
	       distance=datas[4];
	       $('#pointName').html(name);
	       setPointMap(y,x);
	    }
	    
	   
	    
	
	    function setPointMap(lng,lat){
	        
	        // 传入经纬度，设置地图中心点
			var position = new AMap.LngLat(lng, lat);  // 标准写法
			// 简写 var position = [116, 39]; 
			map.setCenter(position); 
		    
		    if(circle!=null){
		       map.remove(circle);
		    }
		    
			// 构造矢量圆形
			circle = new AMap.Circle({
			    center: new AMap.LngLat(lng, lat), // 圆心位置
			    radius: distance,  //半径
			    strokeColor: "#F33",  //线颜色
			    strokeOpacity: 1,  //线透明度
			    strokeWeight: 3,  //线粗细度
			    fillColor: "#ee2200",  //填充颜色
			    fillOpacity: 0.35 //填充透明度
			});
			
			// add方法可以传入一个覆盖物数组，将点标记和矢量圆同时添加到地图上
			map.add(circle);
			
			
			getloca();
			//高德定位
			/*AMap.plugin('AMap.Geolocation', function() {
		        var geolocation = new AMap.Geolocation({
		            enableHighAccuracy: true,//是否使用高精度定位，默认:true
		            timeout: 10000,          //超过10秒后停止定位，默认：5s
		            buttonPosition:'RB',    //定位按钮的停靠位置
		            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
		            zoomToAccuracy: true,   //定位成功后是否自动调整地图视野到定位点
		
		        });
		        map.addControl(geolocation);
		        geolocation.getCurrentPosition(function(status,result){
		            if(status=='complete'){
		                onComplete(result)
		            }else{
		                onError(result)
		            }
		        });
		    });*/
	    }
		
		
		
	    //解析定位结果
	    function onComplete(data) {
	       
	        var str = [];
	        str.push('定位结果：' + data.position);
	        curx=data.position.lat;
	        cury=data.position.lng;
	        str.push('定位类别：' + data.location_type);
	        if(data.accuracy){
	             str.push('精度：' + data.accuracy + ' 米');
	        }//如为IP精确定位结果则没有精度信息
	        str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
	        initPointMessage();
	    }
	    //解析定位错误信息
	    function onError(data) {
	        alert(data.message);
	    }
		
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
		                    var speed = res.speed; // 速度，以米/每秒计  
		                    var accuracy = res.accuracy; // 位置精度  
		                    curx=latitude;
	                        cury=longitude;
	                        
	                        if(marker!=null){
	                        	map.remove(marker);
	                        }
	                        
	                        marker = new AMap.Marker({
							    icon: "https://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
							    position: [longitude, latitude]
							});
							map.add(marker);
							
							// 传入经纬度，设置地图中心点
							var position = new AMap.LngLat(longitude, latitude);  // 标准写法
							// 简写 var position = [116, 39]; 
							map.setCenter(position); 
		                    initPointMessage();
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
		
		function initPointMessage(){
		    if(curx&&cury){
		       var curdis=getdistance(x,y,curx,cury);
		       if(curdis>distance){
		          $('#message').html('不在打卡范围内');
		          $('#setPoint').hide();
		       }else{
		            var _uri = window.BASEPATH+'/sec/phoneapp/checkPointTime';
				    var param={};
				    param.pointId=pointId;
					$.post(_uri, $.toJSON(param), function(data){
						data = parseAjaxResult(data);
						if(data === -1) return;
				        if(data.count==0){
				           $('#message').html('不在打卡时间');
				           $('#setPoint').hide();
				        }else{
				           $('#message-time').html(data.time);
				           $('#setPoint').show();
				           if(type=='ONWORK'){
				           	  $('#message').html('上班打卡');
				           }else if(type=='OFFWORK'){
				              $('#message').html('下班打卡');
				           }else{
				              $('#message').html('巡逻打卡');
				           }
				        }
			
					});
		          
		       }
		    }
		}
		//返回米
	   function getdistance(lat1, lng1, lat2, lng2){
			var radLat1 = lat1*Math.PI / 180.0;
			var radLat2 = lat2*Math.PI / 180.0;
			var a = radLat1 - radLat2;
			var b = lng1*Math.PI / 180.0 - lng2*Math.PI / 180.0;
			var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
			Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
			s = s *6378.137 ;// EARTH_RADIUS;
			s = Math.round(s * 10000) / 10;
			return Math.abs(s);
		}
		
		
		$('#setPoint').on('click',function(){
			var _uri = window.BASEPATH+'/sec/phoneapp/setTime';
		    var param={};
		    param.pointId=pointId;
			$.post(_uri, $.toJSON(param), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
	            $.toast("打卡完成");
	            location.href=window.BASEPATH+'/sec/phoneapp/personal/index?userId=${userId}';
			});
		
		}); 
		
		$('#personalHome').on('click',function(){
			
			location.href=window.BASEPATH+'/sec/phoneapp/personal/index?userId=${userId}';
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
				<div class="header-content">用户首页</div>
			</div>
		</div>
		
		<div id="content" class="content">
		  <div style="width:100%;">
			  <image id="selectPoint" style="width:50px;height:50px;float:left;" src="lib/images/caidan.png"/>
			  <div style="float:left;line-height:50px;width:200px;font-size:20px" id="pointName"></div>
			  <div style="float:right;margin-top:10px;margin-right:5px"><a id="personalHome" href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" style="">个人中心</a></div>
		  </div>
		  
		  <div id="container" style="width:100%;height:400px;"></div>
		  <div id="message" style="text-align:center;margin-top:30px;font-size:20px"></div>
		  <div id="message-time" style="text-align:center;margin-top:10px;font-size:20px;"></div> 
		  <div id="selPoint" class="weui-popup__container popup-bottom">
			  <div class="weui-popup__overlay"></div>
			  <div class="weui-popup__modal">
			      <div class="weui-grids">
			              <c:forEach items="${points}" var="item" varStatus="status">
			                  <a href="javascript:void(0)" id="selPoint-${item.id}" data="${item.x}-${item.y}-${item.type}-${item.name}-${item.distance}" class="weui-grid js_grid selPoint">
							    <div class="weui-grid__icon">
							      <img src="lib/images/8.png" alt="">
							    </div>
							    <p class="weui-grid__label" style="font-size:20px;">
							      ${item.name}
							    </p>
							  </a>
			              </c:forEach>
					</div>
			  </div>
			</div>
			
			
		</div>	
	</div>
	
    <a id="setPoint" style="position:fixed;bottom:0px;width:100%;font-size:20px;display:none;" href="javascript:;" class="weui-btn weui-btn_primary">立刻打卡</a>
	
	
</body>
</html>