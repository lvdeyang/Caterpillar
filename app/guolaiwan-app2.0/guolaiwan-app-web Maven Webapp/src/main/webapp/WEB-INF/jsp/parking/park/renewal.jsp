<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
%>
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
<style type="text/css">
html, body {
	height: 100%;
}

.header p {
	color: #ffffff;
	font-size: 14px;
}

.header_in {
	background: url(lib/images/ditu.jpg) no-repeat center;
	background-size: cover;
	width: 100%;
	height: 100%;
}

.footer_on {
	position: fixed;
	top: 91%;
	/*  z-index: -1;*/
}
</style>
</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
      leng = null;
      var time= null;
    $(function(){ 
			
        var _uti = window.BASEPATH + 'vice/theorder';
	    $.post(_uti, null, function(data) {  
	      	data = parseAjaxResult(data);
		    for(var i=0; i<data.length ; i++){
		        var html = [];
		        window.parkingName = data[0].parkingName;
				html.push('<option   style="text-align:center;">'+data[i].parkingName+'</option>');
				$('#sect').append(html.join('')); 
			}
			
			
		   var _util = window.BASEPATH + 'vice/vehiclename';
           var patam = {};
		   patam.tion = window.parkingName;
	       $.post(_util, $.toJSON(patam), function(data) {  
			  	data = parseAjaxResult(data);
			  	window.id = data.id; // 表单id
			    time = data.time; // 表单id
			    window.attid = data.uid; // 景区id
			  	
			  	$("#cost").empty();
			    var objt = document.getElementById("chao");
		        objt.innerText= 0; 
		        
		        var txt = document.getElementById("time"); //time
	            txt.innerText= time;
		        
				var obj = document.getElementById("vehicle");
	            obj.innerText= data.vehicle; 
	            var html = [];
				html.push('<span style="font-size:22px;">'+data.cost+'</span> 元');
				$('#cost').append(html.join('')); 
				
	            var _uti = window.BASEPATH + 'vice/renew';
	            var param = {};
			    param.attid =   data.uid;
			    param. Layer =   data. parkingLayer;
			    param.District =   data.parkingDistrict;
		    $.post(_uti, $.toJSON(param), function(data) {  
		      	data = parseAjaxResult(data);
		        var objt = document.getElementById("spa");
	            objt.innerText= data.cost; 
			  });	 
			  
			    var hours = window.BASEPATH + 'vice/ hours';
	            var para = {};
	            para.id = data.uid;
		     $.post(hours, $.toJSON(para), function(data) {  
		      	data = parseAjaxResult(data);
		        var objt = document.getElementById("stoppingTime");
	            objt.innerText= data.stoppingTime; 
			  });	  
			
			    var parking = window.BASEPATH + 'vice/ seleparkingMoney';
			    var objt = {};
			    objt.id =   window.id;
		     $.post(parking, $.toJSON(objt), function(data) {  
		      	data = parseAjaxResult(data);
		      	window.length =  data.leng;
		        if(data.money >0){
			        var objt = document.getElementById("chao");
		            objt.innerText= data.money; 
		            var num1 = document.getElementById('spa').innerText;
		    		var num2 = document.getElementById('sele').value;
		    		var num3 = document.getElementById("jin");
		    		var num4 = document.getElementById("chao").innerText;
		            window.money = parseFloat(num1)*parseFloat(num2)+parseFloat(num4);
		    		num3.innerText=(parseFloat(num1)*parseFloat(num2)+parseFloat(num4));
	            }
			 });	   
	     });			
	 });	 
    	
});
 
       
        
      $(document).on('click', '#btn', function() {
           if(document.getElementById("weixin").checked==false){
           $.toast("请选择支付方式", "forbidden");
           return false;
            }
         var meony  = window.money;
         var orderId  = window.id;
         var uid =  window.attid;
	     var parking = window.BASEPATH + 'vice/ seleparkingMoney';
		 var objt = {};
		 objt.id = id;
	     $.post(parking,$.toJSON(objt), function(data) {  
	    	 data = parseAjaxResult(data);
	    	 time = data.leng;
		     payPublic(orderId,meony,uid);
	
		});	  
	});                       












   function t(){
	    var num1 = document.getElementById('spa').innerText;
	    var num2 = document.getElementById('sele').value;
	    var num3 = document.getElementById("jin");
	    var num4 = document.getElementById("chao").innerText;
	    window.money = parseFloat(num1)*parseFloat(num2)+parseFloat(num4);
	    num3.innerText=(parseFloat(num1)*parseFloat(num2)+parseFloat(num4));
   }
   
   
  
      function startTime() {

                var shi = new Date(time);
				var today = new Date() 
				if(today >= shi &&shi != null ){
				var year=today.getFullYear() - shi.getFullYear()  
                var mon=today.getMonth()+1 - shi.getFullYear()
                var da=today.getDate() - shi.getFullYear()
				var h = today.getHours() - shi.getHours()
				var m = today.getMinutes() - shi.getMinutes() 
				var s = today.getSeconds() - shi.getSeconds() 
				// add a zero in front of numbers<10
				year = checkTime(year)
				mon = checkTime(mon)
				da = checkTime(da)
				h = checkTime(h)
				m = checkTime(m)
				s = checkTime(s)
				document.getElementById('times').innerHTML = h + ":" + m + ":" + s
				tt = setTimeout('startTime()', 1000)
			}
          function checkTime(i) {
                
				if(h < 0){
				   h = h + 23
				}
				if(m < 0){
				   m = m + 59
				}
				if(s < 0){
				   s = s + 60
				}
				return i
			}
   } 
   
   
    $(document).on('change', '#sect', function() {
         
         var x = document.getElementById("sele");
              x.selectedIndex = 0;
    
         var _util = window.BASEPATH + 'vice/vehiclename';
         var objt = {};
		 objt.tion =   $(this).val();
		$.post(_util, $.toJSON(objt), function(data) {  
		    $("#cost").empty();
		    var objt = document.getElementById("chao");
	        objt.innerText= 0; 
	        var num3 = document.getElementById("jin");
	        num3.innerText= 0;
	        
		  	data = parseAjaxResult(data);
		    window.id = data.id; // 表单id
		    window.trime = data.time; // 表单id
	        var txt = document.getElementById("time"); //time
	        txt.innerText= window.trime;
	        
			var obj = document.getElementById("vehicle");
            obj.innerText= data.vehicle; 
            
            var html = [];
			html.push('<span id="span" style="font-size:22px;">'+data.cost+'</span> 元');
			$('#cost').append(html.join('')); 
			
            var _uti = window.BASEPATH + 'vice/renew';
            var param = {};
		    param.attid =   data.uid;
		    param. Layer =  data. parkingLayer;
		    param.District = data.parkingDistrict;
		    
	    $.post(_uti, $.toJSON(param), function(data) {  
		      	data = parseAjaxResult(data);
		        var objt = document.getElementById("spa");
	            objt.innerText= data.cost; 
		  });	 
		  
		  
		   var hours = window.BASEPATH + 'vice/ hours';
           var para = {};
           para.id = data.uid;
	    $.post(hours, $.toJSON(para), function(data) {  
	      	data = parseAjaxResult(data);
	        var objt = document.getElementById("stoppingTime");
            objt.innerText= data.stoppingTime; 
		  });	  
		
		
		    var parking = window.BASEPATH + 'vice/ seleparkingMoney';
		    var objt = {};
		    objt.id =   window.id;
	     $.post(parking, $.toJSON(objt), function(data) {  
	      	data = parseAjaxResult(data);
	      	window.length =  data.leng;
	        if(data.money >0){
		        var objt = document.getElementById("chao");
	            objt.innerText= data.money; 
	            var num1 = document.getElementById('spa').innerText;
	    		var num2 = document.getElementById('sele').value;
	    		var num3 = document.getElementById("jin");
	    		var num4 = document.getElementById("chao").innerText;
	    		 window.money = parseFloat(num1)*parseFloat(num2)+parseFloat(num4);
	    		num3.innerText=(parseFloat(num1)*parseFloat(num2)+parseFloat(num4));
            }
		  });	   
		});	
     }); 
  		
  		var orderId=0;  //订单 用户id
  		var time;
		var meony;  // 
		var prepay_id;
		var paySign; 
		var appId;   
		var timeStamp;   
		var nonceStr;  
		var packageStr;  
		var signType; 
		var orderNo;	
		
		function payPublic(orderId,meony,uid){
		    meony =  meony*100;	
	        var site = "payreportrenew";		
		$.get(window.BASEPATH +"pubnum/prev/paypark/"+orderId+"/"+meony+"/"+uid+"/"+site, null, function(data){
				prepay_id = data.prepay_id;
		        paySign = data.paySign;
		        appId = data.appId;
		        timeStamp = data.timeStamp;
		        nonceStr = data.nonceStr;
		        packageStr = data.packageStr;
		        signType = data.signType;
		        orderNo = data.orderNo;
		        callpay();
			});
		}
		
	
		function onBridgeReady(){
		    WeixinJSBridge.invoke(
		        'getBrandWCPayRequest', {
		           "appId"     : appId,     //公众号名称，由商户传入
		           "timeStamp" : timeStamp, //时间戳，自1970年以来的秒数
		           "nonceStr"  : nonceStr , //随机串
		           "package"   : packageStr,
		           "signType"  : signType,  //微信签名方式：
		           "paySign"   : paySign    //微信签名
		        },
		        function(res){
		            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
						var _ut = window.BASEPATH + 'vice/addmoney'
						var para = {};
						if (time > 0) {
							para.length = time
						} else {
							para.length = 0;
						}
						para.sele = $("#sele").val();
						para.money = window.money;
						para.id = window.id;
						$.post(_ut, $.toJSON(para), function(data) {
							data = parseAjaxResult(data);
							alert("续费成功");
							window.location.href = "pubnum/product/index/merchant/renewall";
						});
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		             alert("交易取消");  
	                 window.location.href = "pubnum/product/index/merchant/renewall";
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert(res.err_desc); 
                     window.location.href = "pubnum/product/index/merchant/renewall";
		            }  
		        }
		    );
		}
		function callpay(){
		    if (typeof WeixinJSBridge == "undefined"){
		        if( document.addEventListener ){
		            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		        }else if (document.attachEvent){
		            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
		            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		        }
		    }else{
		        onBridgeReady();
		    }
		}
</script>



<body onload="startTime()" >
	<div class="nav" style="height:40px;width:100%;background:black;text-align:center;">

     <a class="layui-btn layui-btn-small" style="float:left;height:100%;color:#ffffff;font-size:15px;line-height:40px;font-weight: bold;" href="quit/merchant/smartparking" title="返回"> <返回首页 </a>
	  <span style="color:#ffffff;line-height:40px;font-size:18px;margin-left:-72px;">续费页面</span>
	</div> 
	<div class="header" style="width:100%;height:50%;">

		<div class="header_in" style="margin: 0 auto;overflow: auto;" />
		<p style="padding:5% 0 0 6%; display: inline-block;">总金额</p>
		<p id="vehicle"
			style=" padding:5% 8% 0 0 ;float:right;display: inline-block;"></p>


		<p id="cost"
			style='display: inline-block;position: absolute;top:17%;left:46%;margin-left:-25px'>

		</p>



		<div class="header_on" style="margin:47% 0 0 0;">

			<p style="padding:5% 0 0 6%; display: inline-block;">进场时间</p>
			<p style=" padding:5% 8% 0 0 ;float:right;display: inline-block;" >停车时长</p>
		</div>
		<div class="header_cn">
			<p id="time"   style="padding:1% 0 0 6%;display: inline-block;">2019-3-11
				09:02:11</p>
			<p id="times"  style="padding:1% 8% 0 0 ;float:right;display: inline-block;">00:00:00</p>
		</div>
	</div>
	</div>
	<div class="main" style="width:100%;height:auto;">
		<div class="main_in"
			style="width:100%;height:50px;line-height:50px;font-weight: bold;text-align:center;">
			<select id="sect" style= " webkit-appearance: none;    padding: 0 0 0 5%;text-align:center; height:30px;line-height: 30px;width:50%;border: 0;outline: none;font-weight: bold;font-size:14spx;border-radius:5px;z-index: 999;">
				
			</select>
		</div>
		<div class="main_on" style="width:100%;">
			<button
				style="width:50%;height:40px;background:#ffffff;border:none;outline:none;border:1px solid #F39801;border-left:none;font-weight: bold;">
				<p>
					收费标准 : <span id="spa">5</span>元/小时
				</p>
			</button>
			<button
				style="width:50%;height:40px;float:right;background:#ffffff;outline:none;border:1px solid #F39801;border-right:none;font-weight: bold;">
				<p>
					开放时间 : <span id="stoppingTime">8：00-18：00</span>
				</p>
			</button>
		</div>

		<div class="main_cn"
			style="widht:100%;height:auto;overflow: hidden;margin:10px 10%;">
			<p style="display: inline-block;font-weight: bold;font-size:18px;">续费时长：</p>
			<select id="sele" onchange="t()"
				style="text-align:center; height:30px;line-height: 30px;width:20%;border: 0;outline: none;font-weight: bold;font-size:14spx;background:#E5E5E5;border-radius:5px;z-index: 999;">
				<option value="0";>0</option>
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
			</select><span>小时</span>
			<p style="font-weight: bold;font-size:18px;color:red;margin:2% auto;">
				超时停车费用：<span id="chao">0</span>元
			</p>
		</div>

	</div>
	<div class="footer" style="width:100%;">
		<div class="footer_in" style="margin:0 10% ;height:100%;">
		<div>
			<img alt="" src="lib/images/weixin.png"
				style="width:30px;height:30px; vertical-align:middle;" /> <span
				style="display:inline-block;font-size:12px;line-height:35px;">微信支付</span>
			<input type="checkbox" name="" id="weixin" value=""
				style="float:right;margin-top:10px;background-color:red;" />
		</div>
		<div>
			<img alt="" src="lib/images/zhifu.png"
				style="width:30px;height:30px; vertical-align:middle;" /> <span
				style="display:inline-block;font-size:12px;line-height:35px;">支付宝支付</span>
			<input type="checkbox" name="" id="weixin" disabled="value"
				style="float:right;margin-top:10px;background-color:red;" />
		</div>	
			 
		</div>
		<div style="width:100%;height:60px;">
			
		</div>	
		<div class="footer_on" style="width:100%;">
			<button
				style="width:60%;height:50px;background:#ffffff;border:none;outline:none;border:1px solid #A0A0A0;border-left:none;color:#CE8805;">
				<p>
					总额 : ￥<span id="jin" style="font-size:20px;font-weight: bold;">0</span>
				</p>
			</button>
			<button id="btn"
				style="width:40%;height:50px;float:right;background:#CE8805;outline:none;border:none;color:#ffffff;font-size:16px;">
				<p>立即续费</p>
			</button>
		</div>
	</div>
</body>
</html>
