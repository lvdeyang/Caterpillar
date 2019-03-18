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
<style>
html, body {
	width: 100%;
	height: 100%;
	background-color: #FFFFF0;
}

.li_a {
	list-style: none;
	border-radius: 3px;
	background: #D5D5D5;
	margin: 10px auto;
}

/*成功*/
.li_a:hover, .li_a:focus, .li_a:active, .li_a.active {
	color: #fff;
	background-color: #74B72C;
}
</style>

</head>
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
 $(function() {
		 var _ur = window.BASEPATH + 'vice/selePay';
		 var para = {};
		 para.uid = ${param.uid};
		 $.post(_ur, $.toJSON(para), function(data) {
			data = parseAjaxResult(data);
            	var htm = [];
				htm.push('<span">'+data.parkingName+'</span>');
				$('#name').append(htm.join(''));  
				
		    	var html = [];
				html.push('<span style="color:#F99161;">'+data.parkingLayer+' '+data.parkingDistrict+' '+data.parkingNumber+'</span>');
				$('#car').append(html.join(''));  
	
		  	 	var html1 = [];
				html1.push('<span style="color:#F99161;">'+data.stoppingTime+'</span> 小时 ');
				$('#time').append(html1.join(''));  
	
		   		var html3 = [];
				html3.push('<span style="color:#F99161;">'+data.parkingCost+'</span> ');
				$('#cost').append(html3.join(''));  
	
		   	
		 });
 
		   $('li').click(function(){
				$(this).addClass("li_a").siblings("li");
			});

 	(function($) {        
    $.alerts = {         
       		alert: function(title, message, callback) {  
            if( title == null ) title = 'Alert';  
	            $.alerts._show(title, message, null, 'alert', function(result) {  
	                if( callback ) callback(result);  
	            });  
        	},  
           
      		confirm: function(title, message, callback) {  
	            if( title == null ) title = 'Confirm';  
	            $.alerts._show(title, message, null, 'confirm', function(result) {  
	                if( callback ) callback(result);  
	            });  
       	  },  
              
          
        _show: function(title, msg, value, type, callback) {  
                    var _html = "";  
                    _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + title + '</span>';  
                    _html += '<div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';  
                      if (type == "alert") {  
                      _html += '<input id="mb_btn_ok" type="button" value="确定" />';  
                    }  

                    _html += '</div></div>';  
                    //必须先将_html添加到body，再设置Css样式  
                    $("body").append(_html); GenerateCss();  
            switch( type ) {  
                case 'alert':  
                    $("#mb_btn_ok").click( function() {  
                        $.alerts._hide();  
                        callback(true);  
                    });  
                    $("#mb_btn_ok").focus().keypress( function(e) {  
                        if( e.keyCode == 13 || e.keyCode == 27 ) $("#mb_btn_ok").trigger('click');  
                    });  
                break;  
                case 'confirm':  
                    $("#mb_btn_ok").click( function() {  
                        $.alerts._hide();  
                        if( callback ) callback(true);  
                    });  
                    $("#mb_btn_no").click( function() {  
                        $.alerts._hide();  
                        if( callback ) callback(false);  
                    });  
                    $("#mb_btn_no").focus();  
                    $("#mb_btn_ok, #mb_btn_no").keypress( function(e) {  
                        if( e.keyCode == 13 ) $("#mb_btn_ok").trigger('click');  
                        if( e.keyCode == 27 ) $("#mb_btn_no").trigger('click');  
                    });  
                break;
            }  
        },  
        _hide: function() {  
             $("#mb_box,#mb_con").remove();  
        }  
    }  
    // Shortuct functions  
    myAlert = function(title, message, callback) {  
        $.alerts.alert(title, message, callback);  
    }  
       
    myConfirm = function(title, message, callback) {  
        $.alerts.confirm(title, message, callback);  
    };  
           
   
      
      //生成Css  
  	var GenerateCss = function () {  
    $("#mb_box").css({ width: '100%', height: '100%', zIndex: '99999', position: 'fixed',  
      filter: 'Alpha(opacity=60)', backgroundColor: 'black', top: '0', left: '0', opacity: '0.6'  
    });  
    $("#mb_con").css({ zIndex: '999999', width: '270px',height:'200px', position: 'fixed', 
      backgroundColor: 'White',  
    });  
    $("#mb_tit").css({ display: 'block', fontSize: '14px', color: '#444', padding: '10px 15px',  
      backgroundColor: '#fff', borderRadius: '15px 15px 0 0',  
      fontWeight: 'bold'  
    });  
    $("#mb_msg").css({ padding: '20px', lineHeight: '30px', textAlign:'center', 
      fontSize: '12px' ,color:'#4c4c4c' 
    });  
    $("#mb_ico").css({ display: 'block', position: 'absolute', right: '10px', top: '9px',  
      border: '1px solid Gray', width: '18px', height: '18px', textAlign: 'center',  
      lineHeight: '16px', cursor: 'pointer', borderRadius: '12px', fontFamily: '微软雅黑'  
    });  
    $("#mb_btnbox").css({ margin: '5px 0px 10px 0', textAlign: 'center' });  
    $("#mb_btn_ok").css({ width: '80px', height: '40px', color: 'white', border: 'none', borderRadius:'4px'});  
    $("#mb_btn_ok").css({ backgroundColor: '#FF8500' });  
 
   
   
    //右上角关闭按钮hover样式  
    $("#mb_ico").hover(function () {  
      $(this).css({ backgroundColor: 'Red', color: 'White' });  
    }, function () {  
      $(this).css({ backgroundColor: '#DDD', color: 'black' });  
    });  
    var _widht = document.documentElement.clientWidth; //屏幕宽  
    var _height = document.documentElement.clientHeight; //屏幕高  
    var boxWidth = $("#mb_con").width();  
    var boxHeight = $("#mb_con").height();  
    //让提示框居中  
    $("#mb_con").css({ top: (_height - boxHeight) / 2 + "px", left: (_widht - boxWidth) / 2 + "px" });  
  }  
   
  
})(jQuery);
$("#btn2").bind("click", function () {   
        myAlert('系统提示','退款申请提交成功，我们会在5个工作日内将款项退回至您的付款账户，请注意查收',function(){  
        var  a =   $(".li_a").attr("id")
        var text  =    $("#"+a+"").text(); 
		var _uriYd = window.BASEPATH + 'vice/refund';
		var parm = {};
		parm.ref= text;
		parm.uid=${param.uid};
		$.post(_uriYd, $.toJSON(parm), function(data) {
		});
            //要回调的方法  
         window.location.href="quit/merchant/smartparking";
        });  
    });
  $("#btn1").bind("click", function () {   
      window.location.href="vice/merchant/order?uid="+${param.uid};
        });  
    
       

 });
    
   
   
</script>
<body>

	<div class="header"
		style="width:95%;height:30%;margin:0 auto;padding-top:3%;">
		<div class="header_in"
			style="width:100%;height:100%;background:#D5D5D5;border-radius:5px;">
			<p style="text-align:center;color:#74B72C;font-size:1rem">亲~你确定要放弃这个车位吗？</p>
			<div class="header_on"
				style="border-bottom: solid 1px #B7B7B7;width:100%;margin:7px auto;"></div>
			<p id="name" style="margin:0 0 10px 15px;font-size:18px;"></p>
			<p id="car" style="margin-left:15px;font-size:16px;color:#777777;">停车位：</p>
			<p id="time" style="margin-left:15px;font-size:16px;color:#777777;">购买时长：</p>
			<p id="cost" style="font-size:18px;margin-left:15px;">总费用 ￥</p>
		</div>

	</div>
	<div class="main" style="width:95%;height:45%;margin:0 auto">
		<p style="margin:15px 0 15px 15px">请选择退单理由</p>
		<ul id="ul" style="">
			<li id="cl_a" class='li_a'><p
					style="margin-left:15px;line-height:30px;font-size:0.8rem;">不想去了</p></li>
			<li id="cl_b" class='li_a'><p
					style="margin-left:15px;line-height:30px;font-size:0.8rem;">换目的地了</p></li>
			<li id="cl_c" class='li_a'><p
					style="margin-left:15px;line-height:30px;font-size:0.8rem;">车坏了</p></li>
			<li id="cl_d" class='li_a'><p
					style="margin-left:15px;line-height:30px;font-size:0.8rem;">没时间，不去了</p></li>
			<li id="cl_e" class='li_a'><p
					style="margin-left:15px;line-height:30px;font-size:0.8rem;">其他原因</p></li>
		</ul>
	</div>
	<div class="footer"
		style="width:100%;height:20%;margin:0 auto;text-align:center">
		<button id="btn1"
			style="font-size:0.8rem;width:25%;height:30%;font-weight: bold;background-color:#FFFFFF ;border-radius:5px;border:none;outline:none;border:1px solid #D5D5D5">取
			消</button>
		<button id="btn2"
			style="font-size:0.8rem;width:25%;height:30%;font-weight: bold;color:#FFFFFF;background-color:#FF8500 ;border-radius:5px;border:none;outline:none;">提
			交</button>
	</div>

</body>
</html>

