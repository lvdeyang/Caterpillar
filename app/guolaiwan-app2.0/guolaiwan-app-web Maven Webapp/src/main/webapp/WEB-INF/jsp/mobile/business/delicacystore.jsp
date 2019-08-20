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

<title>美食店铺</title>

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
	
}

* {
	box-sizing: border-box;
}

.z-depth-1-bottom {
	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, .14), 0 3px 1px -2px
		rgba(0, 0, 0, .12), 0 1px 5px 0 rgba(0, 0, 0, .2) !important;
}

.z-depth-1-right {
	box-shadow: 2px 0 2px 0 rgba(0, 0, 0, .14), 3px 0 1px -2px
		rgba(0, 0, 0, .12), 1px 0 5px 0 rgba(0, 0, 0, .2) !important;
}

.wrapper {
	height: 100%;
	width: 100%;
	position: relative;
}

/* mmenu 样式覆盖 */
.mm-listview .mm-next {
	width: 90px !important;
}

.mm-navbar.mm-navbar-top {
	height: 110px !important;
}

.mm-menu.mm-hasnavbar-top .mm-panel, .mm-menu.mm-hasnavbar-top .mm-fixeddivider
	{
	top: 110px !important;
}

.picker-highlight {
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .3);
}

.picker-highlight .toolbar, .picker-highlight .picker-items {
	background: #fff;
}

.picker-highlight .toolbar .title {
	color: #000;
}

.weui-picker-calendar {
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .3) !important;
}

.weui-picker-calendar .toolbar {
	background: #fff !important;
}

.weui-picker-calendar .picker-calendar-week-days {
	background: #fff !important;
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

.side-menu {
	background-color: #fff;
}

.logout {
	color: #fff !important;
	line-height: inherit !important;
	font-size: inherit !important;
	border-radius: 0;
}

.content {
	
}

.cards {
	box-shadow: 0 1px 2px rgba(0, 0, 0, .3);
	background-color: #fff;
}

.weui-btn {
	color: #fff !important;
}

.weui-btn:after {
	border-radius: 0 !important;
}

.form-row {
	height: 81px;
	line-height: 80px;
	border-bottom: 1px solid #f1f1f1;
	position: relative;
}

.form-row.last {
	border-bottom: 0;
}

.form-row.btn-content {
	padding: 20px 10px;
}

.form-row.text-content {
	text-align: center;
}

.form-row.sm {
	height: 51px;
	line-height: 50px;
}

.form-row.lg {
	height: 111px;
	line-height: 110px;
}

.back-icon {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	z-index: 0;
	text-align: center;
}

.form-col-half {
	width: 50%;
	height: 100%;
	float: left;
	padding: 0 15px;
	text-align: center;
	position: relative;
	z-index: 1;
	line-height: 80px;
	text-align: center;
}

.form-col-half.sm {
	line-height: 50px;
}

.form-col-half.left-border {
	border-right: 1px solid #f1f1f1;
}

.form-row.sm .form-col-half {
	line-height: 50px;
}

.form-row.sm .form-col-half>input {
	padding: 10px;
}

.form-row input {
	width: 80%;
	margin: auto;
	padding: 10px;
	border: 0;
	font-size: 14px;
}

.form-row textarea {
	width: 80%;
	height: 95px;
	line-height: 35px;
	padding: 0 10px;
	position: relative;
	top: 8px;
	border: 0;
	font-size: 14px;
	font-family: Arial;
}

.btn-custom {
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	border-radius: 0;
	background-color: #18b4ed;
}

.btn-custom:active {
	opacity: 0.7 !important;
	background-color: #18b4ed !important;
}

.content-top, .content-bottom {
	line-height: 40px;
	position: relative;
}

.content-top {
	font-size: 14px;
	color: #bfbfbf;
	top: 8px;
}

.content-bottom {
	bottom: 8px;
}

.content-icon {
	color: #bfbfbf;
	position: relative;
}

.my-popup .weui-popup__modal {
	background: #fbfbfb;
}

.my-popup .go-back>span {
	position: relative;
	top: 2px;
}

.my-popup table {
	width: 100%;
	font-size: 14px;
	border-collapse: collapse;
}

.my-popup table thead th, .my-popup table tbody td {
	border: 1px solid #f1f1f1;
}

.my-popup table tbody td.center {
	text-align: center;
}

.my-popup table th, .my-popup table td {
	padding: 10px 2px;
}

.my-popup table tr:first-child>th, .my-popup table tr:first-child>td {
	border-top: 0;
}

.my-popup table tr:last-child>td {
	border-bottom: 0;
}

.my-popup table tr>th:last-child, .my-popup table tr>td:last-child {
	border-right: 0;
}

.price {
	color: #bfbfbf;
	transition: all .05s ease-in;
}

.price.selected {
	font-size: 18px;
	color: #FB6155;
	border-top: 2px solid #FB6155;
}

/* 计数器 */
.my-counter-label {
	position: relative;
	bottom: 9px;
	font-size: 14px;
}

.my-counter {
	height: 30px;
	line-height: 30px;
	width: 90px;
	border: 1px solid #bfbfbf;
	margin-top: 10px;
	border-radius: 4px;
	position: relative;
}

.my-counter, .my-counter>a, .my-counter>span {
	display: inline-block;
}

.my-counter>a, .my-counter>span {
	height: 28px;
	line-height: 28px;
	text-align: center;
	position: absolute;
}

.my-counter .btn-minus {
	border-right: 1px solid #bababa;
	width: 29px;
	color: #ababab;
	left: 0;
	top: 0;
}

.my-counter .number {
	width: 30px;
	font-size: 14px;
	left: 30px;
	top: 0;
}

.my-counter .number.warning {
	background-color: #FB6155;
	color: #fff;
}

.my-counter .btn-plus {
	border-left: 1px solid #bababa;
	width: 29px;
	color: #ababab;
	right: 0;
	top: 0;
}

/* 日期组件代理对象 */
.click-wrapper {
	position: absolute;
	z-index: 1;
	width: 100% !important;
	height: 100%;
	opacity: 0;
	left: 0;
	top: 0;
	text-align: center;
}


      .swiper-container {
        width: 100%;
        padding:0;
        margin:0;
        height:200px;
      } 

      .swiper-container img {
        display: block;
        width: 100%;
      }
    
    #distributeList{
       margin-top:10px;
       padding-left:10px;
       border-bottom:solid 2px #18b4ed;
       width:100%;height:35px;
       
    }
    
    #distributeList a{
       text-decoration:none;
       color:#CCC;
       font-size:12px;
    }
    #distributeList a.current{
       text-decoration:none;
       color:#18b4ed;
       font-size:20px;
    }
    .columnTable{
    
        width:100%;
        
    }
    .columnTable td{
	    width:10%;
	    text-align:center;
	    font-size:12px;
	 
    }
/*清楚浮动*/
	.clearFix::after,.clearFix::before{
		contentt: "";
		display: block;
		clear: both;
	}
	
	
	}
	
	
	::-webkit-scrollbar {/*隐藏滚轮*/
	display: none;
	}
    .meishi img{
    width:35%;
    height:100%;
    margin:5px 1%;
    border-radius:6px;
    }
    
.zhifu{
  background: -webkit-linear-gradient(left,rgba(254,176,1,1),rgba(255,73,0,1)) !important;/* Safari 5.1 - 6 */
  color:#fff !important;
  margin:10px 0 !important;
  font-size:20px !important;
  font-weight:bold !important;
  height:40px !important;
 padding:5px 3%;
  width:35%;
  border-radius:12px !important;
  line-height:30px;
  display: inline-block;
}
/*定义选项卡内容*/
	.contentt{
		display: none;		
	}
	/*显示动态选项卡*/
	.active{
		display: block;
	}
	.tab-btn li{
		list-style: none;
		float: left;
		width: 30%;
		height: 30px;
		font-weight: bold;
		line-height: 30px;
		font-size: 16px;
		text-align: center;
		margin-left:3%;
		
	}
	.btn-active{
		/* background: orange; */
		z-index:11111;
		border-bottom:2px solid #FE4800;
		
	}
	.ping ul{
	 margin-left:2%;
	}
    .ping ul li{
     float:left;
     list-style: none;
     width:auto;
     padding:0 10px;
     height:30px;
     line-height: 30px;
     background:#F4F4F4;
     margin:1% 1%;
     border-radius:16px;
     color:black;
     font-weight:bold;
     font-size:12px;
    }
    .yonghu img{
     float:left;
     border-radius:50%;
     width:50px;
     height:50px;
     margin:15px 0 15px 5%;
    }
    .yonghu p{
      float:left;
      line-height:80px;
      margin:0 2%;
     }
  /* 美团外卖  */   
     html,body,div,p,ul,li,h1,h2,h3,h4,h5,h6,a{margin:0;padding:0;border:0;list-style:none;font-style:normal;}
.left-menu::-webkit-scrollbar {width: 0px;}
.main{overflow:hidden;}
.left-menu{width:25%;float:left;background-color:#fff;position:relative;overflow-y:scroll;height: 500px;}
.left-menu ul li{border:none;outline: none;line-height:60px;font-weight:bold;width:100%;text-align:center;font-size:14px;padding:3px 0;color:#333;background-color:#fff;}  
.left-menu ul li.active{color:#f34b3f !important;background-color:#eee;}  
.right-con{display:none;width:75%;float:left;overflow-y:scroll;background:#fff;position:relative;height:500px;}
.con .con-active{display:block;}
.right-con li{position:relative;height:75px;border-bottom:1px solid #e7eaeb;border-top:1px solid #fff;padding-bottom:8px;margin-bottom:2px;}  
.right-con li .menu-img{position:absolute;margin-left:10px;top:15px;border-radius:3px;cursor:pointer;}  
.right-con li .menu-img img{height:55px;width:55px;vertical-align:middle;border:0;}  
.right-con li .menu-txt{margin:15px 15px 15px 75px;}
.right-con li .menu-txt h3{font-size:14px;margin-bottom:10px;margin-top:8px;} 
.right-con li .menu-txt p{font-style:normal;line-height:20px;font-size:12px;vertical-align:bottom;}  
.right-con li .menu-txt p.list2 b{font-size:14px;color:#f00;}  
.right-con li .btn{background-color:transparent;position:absolute;right:0px;top:45%;cursor:pointer;padding:3px;height:38px;border:none;outline: none;}  
.right-con li .btn button.minus{margin-right:-10px;display:none;}  
.right-con li .btn button{width:40px;height:40px;border:none;background:transparent;padding:0;outline:none;}  
.right-con li .btn button strong{padding:5px 10px;font-size:15px;display:inline-block;text-indent:-100px;padding:5px 11px;height:22px;}  
.right-con li .btn button.minus strong{background:url(lib/images/down.png) no-repeat;background-size:22px 22px;}  
.right-con li .btn i{display:none;width:22px;text-align:center;font-style:normal;vertical-align:top;margin-top:11px;line-height:18px;}
.right-con li .btn button.add{margin-left:-10px;}  
.right-con li .btn button.add strong{background:url(lib/images/up.png) no-repeat;background-size:22px 22px;}  
.right-con li .btn .price{display:none;} 
.footer{display:block;position:fixed;width:100%;z-index:3;height:50px;font-weight:bold;bottom:0px;color:#f03c03;background:#fff;line-height:40px;font-size:15px;border-top:1px solid #e2e2e2;}  
.footer .left{float:left;margin:5px 10px;}  
.footer .right{float:right;}  
.footer .right .disable{background:#dbdbdb;}  
.footer .xhlbtn{display:block;text-align:center;line-height:50px;background-color:#F03C03;padding:0 30px;color:#fff;font-weight:bold;}  
.menu-txt p,h4{
 margin:0 !important;
}
.olderss{
text-align: center;
}
.olderss p input{
border:none;outline:none;border-bottom:1px solid #E0E0E0;
width:70%;
text-align: center;
}
.olderss p{
width:100%;margin-top:20px;
line-height: 30px;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css"/>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"charset="utf-8"></script>
<%--  <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">  --%>
<script type="text/javascript">
      layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#useDate' //指定元素
	  });
	    });
	$(function() {
   	 image(); //顶部图片 营业时间 特色
   	 two();
  
	var iscollect;
	  window.BASEPATH = '<%=basePath%>';
	  var comCode='${comCode}';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
	
	  if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		    getloca();
	  } else {
		    if(comCode=='0000'){
			    comCode='0001';
			}
			getCom();
            getRecomment();
			initSharewx();
	  }
	  var loca={};
	  function getloca(){
	      
		  var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
	
		  var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
		    $.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
					loca=data;
					
				}
		  });
	  }
	  
	  
	 
	  
	  function getCity(la,lo){
	  
	     $.ajax({  
            url: 'http://api.map.baidu.com/geocoder/v2/?ak=yPjZB3eElPXn7zXRjcfqGCze6LCPlkmn&callback=renderReverse&location=' + la + ',' + lo + '&output=json&pois=1',  
            type: "get",  
            dataType: "jsonp",  
            jsonp: "callback",  
            success: function (data) {  
                console.log(data);  
                var province = data.result.addressComponent.province;  
                var cityname = (data.result.addressComponent.city);  
                var district = data.result.addressComponent.district;  
                var street = data.result.addressComponent.street;  
                var street_number = data.result.addressComponent.street_number;  
                var formatted_address = data.result.formatted_address;  
                if(comCode=='0000'){
				    if(district.indexOf('平谷')!=-1){
					    comCode='1003';
					    $('#headerName').html('全域休闲');
					    $('#phone').html('010-89991991');
					}else{
					    comCode='0001';
					}
				}
				getCom();
                getRecomment();
				initSharewx();
            }  
        });  
	  
	  }
	  
	  function getCom(){
	     var _uricoms = window.BASEPATH + 'pubnum/getComs';
		
		 $.get(_uricoms, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data && data.length>0){
				    var html=[];
				   
					for(var i=0; i<data.length; i++){
					    if(data[i].comCode==comCode){
					        $('#selCom').html(data[i].comName);
					    }
					    html.push('<li><a data="'+data[i].comCode+'" href="javascript:void(0)" class="comSel">'+data[i].comName+'</a></li>');
					}
					$('#com').append(html.join(''));
				}
		  });
	  
	  }
	  

      /**/
		
	  function getRecomment(){
	     var _uriRecomment = window.BASEPATH + 'phoneApp/getRecommend?comCode='+comCode;
		
		 $.get(_uriRecomment, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data && data.length>0){
				    var html=[];
					for(var i=0; i<data.length; i++){
						html.push('<div style="height:200px;" id="sw-'+data[i].id+'" class="swiper-slide"><img class="topmod" id="top-'+data[i].productId+'-'+data[i].classify+'" style="height:200px;" src="'+data[i].slidepic+'" alt="">');
						html.push('<div style="font-size:12px;position:absolute;padding-left:5px;bottom:0px;color:#FFF">'+data[i].name+'</div></div>');
					}
					$('#headerWrapper').append(html.join(''));
					$("#headerSwiper").swiper({
				        loop: true,
				        autoplay: 3000,
				        autoplayDisableOnInteraction : false
				      });
				}
		 });
	  }
		
      
		
	  
		
	 var  orderId ;	
	$(document).on('click','#zhifu',function(){
  	 
		    location.href=window.BASEPATH + 'business/merchant/nsAndView?merchantId=198&comCode=0001'; 
    });
	$(document).on('click','#tables',function(){
  	 
		    location.href=window.BASEPATH + 'reservetable/tables/home?merchantId=${merchantId}'; 
    });
	$(document).on('click','#payment',function(){
	    if($("#username").val() == "" || $("#username").val() == null){
	     $.toast("请输入名称!", "cancel");
	     return ;
	    }
	    if($("#userPhone").val() == "" || $("#userPhone").val() == null){
	     $.toast("请输入手机号!", "cancel");
	        return ;
	    }
	    if($("#useDate").val() == "" || $("#useDate").val() == null){
	     $.toast("请选择时间!", "cancel");
	        return ;
	    }
	    if($("#sele").val() == "" || $("#sele").val() == null){
	     $.toast("请选择就餐时间!", "cancel");
	        return ;
	    }
  	    newTableStatus();
    });
	$(document).on('click','#btnselect',function(){
  	     if( $("#totalcountshow").html()  >0 ){ //支付
	  	      orderId = '${orderId}';
	  	     if(orderId !=null && orderId!=""){ //已订房
	  	         newTableStatus();
	  	     }else{ //未定
	  	     $(".nav").hide();
	  	     $(".olderss").show();
	  	     } 
  	     }
    });
	    
  function newTableStatus(){ //创建订单 
    var _uri = window.BASEPATH + 'cate/newTableStatus'; //
	var patam = {};
	patam.merchantId = '${merchantId}' ; //240 '${merchantId}'
	patam.orderId = '${orderId}';
	patam.dishMoney = $("#totalpriceshow").html(); //钱
	patam.userName = $("#username").val(); //用户名
	patam.userPhone = $("#userPhone").val(); //用户手机
	patam.tableDate = $("#useDate").val(); //时间
	patam.type = $("#sele").val(); //就餐时间
	$.post(_uri, $.toJSON(patam), function(data) {
	   payPublic(data.orderId,$("#totalpriceshow").html());
	    $(".olderss").hide();
	  	$(".nav").show();
	  	orderId = data.orderId;
        /*  */
    });
  }
	    
    var share={};
    function initSharewx(){
        var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
  
    	var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
		    $.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				    
					share=data;
					
				}
		});
    }
	    
	
<!--选项卡  -->
	$(".tab-btn li").click(function(){
	//为按钮添加样式
	$(this).addClass("btn-active")
	.siblings()
	.removeClass("btn-active");
				
	//获取点击按钮的索引
	var index = $(this).index();
				
	/*通过索引找到对应的content,并显示:其他content隐藏*/
	var jq = $(".contentt-box .contentt").eq(index)
	.addClass("active").siblings().removeClass("active")
			})						  							
	});
	
		var orderId=0; 
		var text; 
		var prepay_id;
		var paySign; 
		var appId;   
		var timeStamp;   
		var nonceStr;  
		var packageStr;  
		var signType; 
		var orderNo;	
		
		function payPublic(orderId,text){
			text =  (text*100).toFixed(0);	
		$.get(window.BASEPATH +"cate/buydish/port/"+orderId+"/"+text, null, function(data){
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
		             alert("交易成功");  
		             window.location.href = "reservetable/diningtable/tableSuccess?orderId="+orderId+"&merchantId=${merchantId}"; 
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		             alert("交易取消");  
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert(res.err_desc); 
		            }  
		        }
		      }
		    )
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
		
		
	function image(){ //商家图片 特色 营业时间
	     var url = window.BASEPATH + 'product/delicacystore/info.do?merchantId='+${merchantId};
	  	 $.get(url,null,function(msg){
		  	 var ht=[];
		  	 ht.push(' <p style="font-weight: bold;color:black;margin:10px 10%;">'+msg.merch.shopName+'</p>'); //顶部商户名称
		     $("#merInfo1").append(ht.join(''));
		  	 var mpi = msg.mpic;
		  	 var shopNam = msg.shopName;
		  	 var htm = [];
		  	 htm.push('<p style="margin:10px 0 0 2%;font-size:16px;font-weight:bold;">'+shopNam+'</p>');
		  	 $('._name').append(htm.join(''));
		  	 var html=[]; //首页图片
		  	 for(var i=0;i<mpi.length;i++){
		      html.push('<img  style="" alt="" src="http://www.guolaiwan.net/file'+mpi[i]+'">');
		     }
	         $('.meishi').append(html.join(''));
	    
		    var array = [];  //商户特色 营业时间
		    var feature = msg.merch.feature;
		    if(feature != null && feature != "" && msg.merch.businessDate != null){ //商家特色
		      var split  =   feature.split(',');
		      array.push('<p style="font-size:18px;height:50px;line-height:50px;font-weight:bold;margin-left:7%;">营业中     <span>|</span> <span style="">周一---周日</span> <span style="font-size:12px;"> '+msg.merch.businessDate+'</span></p>');
		      array.push('<div style="position: absolute;top:55px;width:100%;height:auto;">');
		      for(var j=0;j<split.length ;j++){
		        array.push('<button style="margin-left:2%;border-radius:6px;padding:0 3px;line-height:25px;font-size:12px;width:auto;outline: none;border:none;border:1px solid #757575;height:25px;color:#757575;background:#fff;">'+split[j]+'</button>');		       	        
		      }
		      array.push('</div>');
		    }else{
		      array.push('<p style="font-size:18px;height:50px;line-height:50px;font-weight:bold;margin-left:7%;">营业中     <span>|</span> <span style="">周一---周日</span> </p>');
		    }	     
		    $('#feature').append(array.join(''));	         
   	 });
	}
	
	
	
	
	
	
	var storage =[];//存储菜品分类
	var  list =null; //保存数据
	var  boole = 1;
	function two(){ //左边列表
	     storage =[];
	     var _uricoms = window.BASEPATH + '/cate/order/list?merchantId='+${merchantId};	
	     $.get(_uricoms, null, function(data){  
	      /*  data = parseAjaxResult(data); */
	      /*  htm.push(' <li class="active" id="first"  onclick="hotsell(this.id)">热销<span class="num-price"></span></li>');
	           $('#meau').append(htm.join(''));   */
	       list = data.name;
	       var html=[];        
	       for(var i=0;i<data.name.length;i++){ //左边列表名称
	            if(storage.length <= 0 ){
	                storage.push(data.name[i].productClassName);
	                html.push('<li id="'+data.name[i].productClassCode+'" onclick="sel(this.id)">'+data.name[i].productClassName+'</li>');
	            } else{
	              for(var j=0; j<storage.length;j++){
	                if(storage[j] == data.name[i].productClassName ){
	                  boole = 0;
	                  break ;
	                }
	              }
	              if(boole == 1){
	               storage.push(data.name[i].productClassName);
	               html.push('<li id="'+data.name[i].productClassCode+'" onclick="sel(this.id)">'+data.name[i].productClassName+'</li>');
	               boole = 0;
	              }
	            }
		   }
		   $('#meau').append(html.join(''));	  
		   select();//显示菜品   	
		 });
   }
 function select(){      // 加载codeid 加载菜品
     $('.con').empty()
	 for(var j = 0; j<storage.length; j++){
		 var html=[];
		 html.push('<div class="right-con con-active">');   
		 html.push('<ul>'); 
		 for(var i = 0; i<list.length; i++){
		    if(list[i].productClassName == storage[j]){
	            html.push('<li>');           
		        html.push('<div class="menu-img"><img class="men_img" src=http://www.guolaiwan.net/file'+list[i].productShowPic+' width="55" height="55" /></div>');
		        html.push('<div class="menu-txt" >');
		        html.push('<font>'+list[i].productName+'</font>');
		        html.push('<p class="list1"  >月销<span>120</span></p>');
		        html.push('<p class="list2">');
		        html.push('<b>￥'+list[i].productPrice+'</b>');
		        html.push('<div class="btn">');
		        if(list[i].mealAmount >0){
		       	  html.push(' <button class="minus" style="display:block;display: inline-block;"  id ="minus'+list[i].uuid+'"  onclick="minus(this.id,'+list[i].id+')" >  <strong class="minuss" ></strong>   </button>');
		          html.push(' <i style="display:block;display: inline-block;">'+list[i].mealAmount+'</i> ');
		          html.push('<button class="add" id ="add'+list[i].uuid+'" onclick="add(this.id,'+list[i].id+')">  <strong class="adds"></strong>  </button> ');
		          html.push('<i class="price">'+list[i].productPrice+'</i>');
		          html.push(' </div> ');
		       	  html.push('</p>');
		       	  html.push('</div> ');
		       	  html.push('</li>');	
		          calculate(list[i].productPrice,list[i].mealAmount);
		        }else{
		          html.push(' <button class="minus"  id ="minus'+list[i].uuid+'"  onclick="minus(this.id,'+list[i].id+')" >  <strong class="minuss"></strong>   </button>');
		          html.push(' <i >0</i> ')
		          html.push('<button class="add" id ="add'+list[i].uuid+'" onclick="add(this.id,'+list[i].id+')">  <strong class="adds"></strong>  </button> ');
		          html.push('<i class="price">'+list[i].productPrice+'</i>');
		          html.push(' </div> ');
		       	  html.push('</p>');
		       	  html.push('</div> ');
		       	  html.push('</li>');	
		        }
		    }
		    	
		}
		html.push('</ul>');   	
		html.push('</div>');
	
	    $('.con').append(html.join(''));
	    $(".con>div").hide();  //隐藏别的菜品 只显示初始
		$(".con>div:eq(0)").show();
  	  }
	}
	  
 function  sel(code){ //切换菜品类型
    $("#"+code).addClass("active").siblings().removeClass("active");
	var n = $(".left-menu li").index("#"+code);
	$(".left-menu li").index("#"+code);
	$(".con>div").hide();
	$(".con>div:eq("+n+")").show();  
 }
	
</script>
<script type="text/javascript">

  /* 美团外卖  */
       function calculate (danjia,mealAmount){
        /* var danjia = $("#"+cls).next().text();//获取单价   */
		var a = $("#totalpriceshow").html();//获取当前所选总价    
		$("#totalpriceshow").html((a * 1 + danjia * mealAmount).toFixed(2));//计算当前所选总价  
		var nm = $("#totalcountshow").html();//获取数量  
		$("#totalcountshow").html(nm*1+mealAmount);  
		jss();
       }
	 //加的效果  
       function add(cls,id){
		$("#"+cls).prevAll().css("display", "inline-block");  
		var n = $("#"+cls).prev().text();  
		var num = parseInt(n) + 1;  
		if (num == 0) { return; } 
		var  aggregate =  num; 
		$("#"+cls).prev().text(num);  
		var danjia = $("#"+cls).next().text();//获取单价  
		var a = $("#totalpriceshow").html();//获取当前所选总价    
		$("#totalpriceshow").html((a * 1 + danjia * 1).toFixed(2));//计算当前所选总价  
		var nm = $("#totalcountshow").html();//获取数量  
		$("#totalcountshow").html(nm*1+1);  
		jss();//<span style='font-family: Arial, Helvetica, sans-serif;'></span>   改变按钮样式
		var _uril = window.BASEPATH + '/cate//order/setmeal';
		var param = {};
	 	param.merchantId = '${merchantId}';
		param.tableId = '${tableId}';
		param.productId = id;
		param.mealAmount = aggregate;
		$.post(_uril, $.toJSON(param), function(data) {
	     
	    }); 
	}  
	//减的效果  
	  function minus(cls,id){
		var n = $("#"+cls).next().text();  
		var num = parseInt(n) - 1;  
		var  aggregate =  num; 
		$("#"+cls).next().text(num);//减1  
		var danjia = $("#"+cls).nextAll(".price").text();//获取单价  
		var a = $("#totalpriceshow").html();//获取当前所选总价  
		$("#totalpriceshow").html((a * 1 - danjia * 1).toFixed(2));//计算当前所选总价  
		 
		var nm = $("#totalcountshow").html();//获取数量  
		$("#totalcountshow").html(nm * 1 - 1);  
		var _uril = window.BASEPATH + '/cate//order/setmeal';
		var param = {};
	 	param.merchantId = '${merchantId}';
		param.tableId = '${tableId}';
		param.productId = id;
		param.mealAmount = aggregate;
		$.post(_uril, $.toJSON(param), function(data) {
	     
	    });
		//如果数量小于或等于0则隐藏减号和数量  
		if (num <= 0) {  
			$("#"+cls).next().css("display", "none");  
			$("#"+cls).css("display", "none");  
			jss();//改变按钮样式  
			 return  
		}  
	} 
		
	function jss() {  
		var m = $("#totalcountshow").html();  
		if (m > 0) {  
			$(".right").find("a").removeClass("disable");  
		} else {  
		   $(".right").find("a").addClass("disable");  
		}  
	};
	
/* 	function  hotsell(codes){
	    $("#"+codes).addClass("active").siblings().removeClass("active");	    	   
		var n = $(".left-menu li").index("#"+codes);
		$(".left-menu li").index("#"+codes);
		$(".con>div").hide();
		$(".con>div:eq("+n+")").show(); 
	 }
	 	  function refreshMsg(){
	  		var _uriMsg = window.BASEPATH + '/product/delicacystore/getMerchantMessage?merchantId='+${merchantId};
		  
			$.get(_uriMsg, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				  var html=[];
				  for(var i=0;i<data.length;i++){
				      html.push('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg product" id="pro-828">');
				      html.push('<div class="weui-media-box__hd">');
				      html.push('	<img style="width:60px;height:60px;" class="weui-media-box__thumb" src="'+data[i].headImg+'">');
				      html.push('</div>');
				      html.push('<div class="weui-media-box__bd">');
				      html.push('<h4 style="font-size:12px;text-align:left;">'+data[i].userName+'</h4>');
				      html.push('<p style="font-size:12px;text-align:left;">'+data[i].content+'</p>');
				      html.push('</div>');
			          html.push('</a>');
				  }
				  $('#messageContent').children().remove();
				  $('#messageContent').append(html.join(''));
				}
			});	  
	  }	 */
	  
	  function payMany(){	   
	     var url = window.BASEPATH + 'pubnum/product/index/payinshop/'+${merchantId};	
	     $.get(url,null,function(msg){})   
	  }	
	  
</script>

<body>
       	<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">商户</div>
			</div>
		</div>
	   <!-- 美食店铺  -->
	   <div class="nav">
		   	  <span class='_name'></span><!-- <p style="margin:10px 0 0 2%;font-size:16px;font-weight:bold;">临街小馆</p>   -->
		     <div class="meishi" style="width:100%;height:120px;white-space:nowrap;overflow-x:auto;overflow-y:hidden;padding:10px 0;position: relative;">
		     <!--  <img  style="" alt="" src="lib/images/1.jpg">
		      <img  style="" alt="" src="lib/images/1.jpg">
		      <img  style="" alt="" src="lib/images/1.jpg">
		      <img  style="" alt="" src="lib/images/1.jpg"> -->
		      <button style="position: absolute;bottom:15px;left:15px;background-color:rgba(44,17,6,0.5);border-radius:12px;outline:none;border:none;color:#fff;padding:0 8px;font-size:12px;"></button>
		     </div>
		     <div id="feature" class="" style="height:100px;width:100%;border-bottom:1px solid #EFEFEF;position: relative;">
            
             </div>
             
            <div style="width:100%;height:80px;text-align: center;border-bottom:10px solid #F4F4F4;">
              <a style="" href="pubnum/product/index/payinshop/${merchantId}" class="zhifu" id="zhifu">到店支付</a>
              <a style=""　href="reservetable/tables/home?merchantId=${merchantId}" class="zhifu" id="tables">订桌</a> 
            </div>
            
             <!-- 选项卡 -->
			<div class="tab" style="">
					<ul class="tab-btn active" >
					<li class="btn-active">招牌菜</li>
					<li>评价</li>
					<li>商家信息</li>
					</ul>
					
					<div class="contentt-box" >
					   <!-- 点菜-->
						<div class="contentt active" style="">
                         <!-- 美团外卖-->
					    <div class="main" style="width:100%;height:auto;">
						<div class="left-menu">
							<ul id="meau">													            					           							   
					        </ul> 
						</div>
	        <div class="con">	</div>
	<div class="footer">  
		<div class="left">已选：
		<span id="cartN">
			<span id="totalcountshow">0</span>份　总计：￥<span id="totalpriceshow">0</span></span>元  
		</div>  
		<div class="right">  
			<a id="btnselect" class="xhlbtn  disable" href="javascript:void(0)">去结算</a>  
		</div>   
	</div>
</div>
						 
						</div>
						<!-- 评价 -->
						<div class="contentt" style="text-align: center;">
						 <p style="height:50px;line-height:50px;margin:0 0 0 5%;font-size:20px;font-weight:bold;float:left;">网友点评<span style="font-size:16px;color:#DCDCDC;">(324)</span></p>
						 <div class="ping" style="border-bottom:10px solid #F4F4F4;width:100%;display: inline-block;margin:5px 0 0 0; ">
						 <ul style="">
						  <li>全部</li>
						  <li>好评</li>
						  <li>差评</li>
						  <li>最新点评</li>
						  <li>味道赞（189）</li>
						  <li>服务热情（189）</li>
						  <li>分量足（189）</li>
						  <li>性价比高（189）</li>
						  <li>优雅环境（189）</li>
						  <li>回头客（189）</li>
						 </ul>
						 </div>
						 
						<div id="messageContent" style="padding-bottom:50px;"></div>
						 
						  
						</div>
						<!-- 商家信息 -->
						<div class="contentt" style="height:auto;width:100%;">
							<div id="merInfo1" style="width:100%;height:auto;border-bottom:10px solid #F4F4F4;border-top:10px solid #F4F4F4;display: inline-block;margin:20px 0 0 0; ">
								<!-- <p style="font-weight: bold;color:black;margin:10px 10%;">临街小馆</p> -->
		        		    </div>    
						    <div id="merInfo2">
						   <p style="font-weight: bold;color:#757575;margin:10px 10%;">订单有效期使用日期：7天内有效</p>
						     <p style="font-weight: bold;color:#757575;margin:10px 10%;">使用规则：无需预约，高峰时段可能需要等位</p>
						    </div>
						</div>
				   </div>
		    </div>
  </div>   
            
		       <div class="olderss" style="width:100%;height:100%;overflow: hidden;display: none;z-index:111111;padding:0 10%; ">
			            <p style="">用户姓名:<input id="username" type="text" style=""></p>
			            <p>联系电话:<input id="userPhone"  type="text"></p>
			            <p>就餐日期:<input id="useDate" type="text"></p>
			            <p>就餐时间:<select id="sele"  style="width:70%;border:none;outline:none;border-bottom:1px solid #E0E0E0;padding:0 0 0 28%;"> <option>午餐</option><option>晚餐</option>    </select></p>
			  
			     <button id="payment"  style="border:none;outiline:none;font-weight:bold;color:#fff;height:40px;width:40%;background: #F03C03;position: fixed;bottom:5%;left:50%;margin-left:-20%;border-radius:12px;">立即支付</button>
			  </div>  

</body>


</html>