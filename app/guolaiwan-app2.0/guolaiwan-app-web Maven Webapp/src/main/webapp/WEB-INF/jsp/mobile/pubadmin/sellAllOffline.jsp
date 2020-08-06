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

<title>商品列表</title>

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

	font-size:14px;
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
    #columnTable{
    
        width:100%;
        margin-top:10px;
        
    }
    #columnTable td{
	    width:20%;
	    text-align:center;
	    font-size:12px;
    }
    
    .listbox-2 li input {
	width: 100%;
	height: 60px;
	margin: 5px auto;
	text-align: center;
	border-radius: 6px;
	border: none;
	background: #fff;
	outline: none;
	-webkit-appearance:none;
	border: 1px solid rgb(230, 230, 230);
	cursor:pointer;
}
  	select {
        -webkit-appearance: none;
    }
    .listbox-2 li select {
	width: 100%;
	height: 60px;
	margin: 5px auto;
	padding:0 10% 0 32%;
	text-align: center;
	border-radius: 6px;
	border: none;
	background: #fff;
	outline: none;
	border: 1px solid rgb(230, 230, 230);
}
</style>

</head>

<!-- 公共脚本引入 -->
<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/script.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/jss/calendar-pro.css" />
<script src="<%=request.getContextPath()%>/jss/calendar-pro.js"></script>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript">

	$(function() {
	  window.BASEPATH = '<%=basePath%>';
		//获取所有商品
      var _uriProduct = window.BASEPATH + 'product/package/getMerchantPro';
		$.get(_uriProduct, null, function(data){
			/* data = parseAjaxResult(data); */
			if(data === -1) return;
			if(data && data.length>0){
			    var pros=data;
			    for (var i=0;i<pros.length;i++) {
						$('.selectList').append('<option value="'+pros[i].id+'">'+pros[i].productName+'</option>')
					}
			}
			
		});
		   $(document).on('click','#ticket',function(){

	    var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+location.href.split('#')[0];

	    $.get(_uri, null, function(data){

			data = parseAjaxResult(data);

			if(data === -1) return;

			if(data){

				paras=data;
				doScan();
			}

		});

	   });
		//点击扫描二维码/一维码
		/* $(document).on('click','#ticket',function(){ */
		  var paras={};
		    function doScan(){

			           wx.config({
						
			            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			
			            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			
			            appId : paras.appId, // 必填，公众号的唯一标识
			
			            timestamp : paras.timestamp, // 必填，生成签名的时间戳
			
			            nonceStr : paras.nonceStr, // 必填，生成签名的随机串
			
			            signature : paras.signature,// 必填，签名，见附录1
			
			            jsApiList : ['checkJsApi', 'scanQRCode'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			
			       	});

			        wx.ready(function() {
			
			               wx.scanQRCode({ 
			
				                needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
			
				                scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
			
				                success: function (res) {
			
				                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
			                        var values = result.split(',')
				                 	$('#_ticketid').val(values[1])
			
				                }
				            });
			        });
	       
	    };
	 
	/*   });	 */
		
		
		//点击识别身份证
		$(document).on('click','#face',function(){
	       discern();
	    });
	 
	    
	    function  discern() {
	           //人脸采集部分
		    var reqUrl=location.href.split('#')[0].replace(/&/g,"FISH");
            var _uri = window.BASEPATH + 'pubnum/prev/scan?url='+reqUrl;
			$.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				    
					share=data;
					wx.config({
			            debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            //                                debug : true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			            appId : share.appId, // 必填，公众号的唯一标识
			            timestamp : share.timestamp, // 必填，生成签名的时间戳
			            nonceStr : share.nonceStr, // 必填，生成签名的随机串
			            signature : share.signature,// 必填，签名，见附录1
			            jsApiList : ['chooseImage',
		                        'previewImage',
		                        'uploadImage',
		                        'downloadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	       	        });
                }
            });                        
             wx.ready(function () {
                wx.checkJsApi({
                    jsApiList: [
                        'chooseImage',
                        'previewImage',
                        'uploadImage',
                        'downloadImage'
                    ],
                    success: function (res) {
                      
                        if (res.checkResult.getLocation == false) {
                            alert('你的微信版本太低，不支持微信JS接口，请升级到最新的微信版本！');
                            return;
                        }else{
                            choosePicone(this.id);
                        }
                    }
                });
            });
            wx.error(function(res){
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                alert("验证失败，请重试！");
                wx.closeWindow();
            });
	    }
		
		
		function choosePicone(id) {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    $.toast("照片处理中...", "loading");
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    getLocalDataone(localIds[0]);
                }
            });
        }
		
        function getLocalDataone(localid) {

			//获取本地图片资源
            wx.getLocalImgData({
                localId: localid, // 图片的localID
                success: function (res) {
                    var localData = res.localData; // localData是图片的base64数据，可以用img标签显示    
                
                    var _uri = window.BASEPATH + 'pubnum/IdentityCard';
                    var params = {};
                   var str=new String();
                    var arr=new Array();
                    str=localData ;
　               				var sear=new RegExp(',');
                     if(sear.test(str)) {
                          arr=str.split(',');//注split可以用字符或字符串分割
                          params.localData=arr[1];
                       }else{
                            params.localData=localData; 
                         }
                     
                                 
                      
		$.post(_uri, params, function(data){                                                  
			           if(data.msg==0){
						   $("#_idcard").val(data.sfz)
						   $("#_name").val(data.name) 	
						}			   
					     if(data.msg==1){
						     alert("识别失败,请继续上传或者手动输入");
						 }			        
				    });
                }
            });
        }
		
				/* 提交 */
				$(document).on('click','.btn',function(){
			    if($('#_count').val()==''){
				alert('请输入数量')
				return;
				}
				
				    	
					var param={};
			  			param.id=$('.selectList').val();
			 			param.count=$('#_count').val();
			  			param.sex=$('#_sex').val();
			     		param.age=$('#_age').val(); 
			            param.type=$('#_type').val(); 
			        	var _uriPay = window.BASEPATH + 'product/package/order/sellAllOffline';
			  			$.post(_uriPay, $.toJSON(param), function(data){
			  			if(data.status ==200){
				  			alert("下单成功")
				  			window.location.reload();
				  			}else{
				  			alert(data.message)
				  			return;
				  			}
			   		
					});
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
				<div class="header-content">商品</div>
			</div>
		</div>
		<div class="content">
					    <ul class="listbox-2" style="color:black;position: relative;">
					    <li>
					    	<select class="selectList"><option value=""></option></select>
					    	<p style="position: absolute;top:23.5px;left:10%;">选择商品</p>
					    </li>
						<li>
							<input id="_count" type="text" placeholder="数量"
							minlength="4" maxlength="4" style="">
							<p style="position: absolute;top:93px;left:10%;">游客数量</p>
						</li>
						<li>
							<select id="_age">
								<option value="0-15">幼年</option>
								<option value="15-64">青年-中年</option>
								<option value="65">老年</option>
							</select>
							<p style="position: absolute;top:163px;left:10%;">游客年龄</p>
						</li>
						<li>
							<select id="_sex">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
							<p style="position: absolute;top:233px;left:10%;">游客性别</p>
						</li>
						<li>
					    	<select id="_type">
		                    	<option value="0">线下</option>
								<option value="1">旅行社</option>
								<option value="2">三方OTA</option>
                    	    </select>
					    	<p style="position: absolute;top:304px;left:10%;">选择类型</p>
					    </li>
						
						
						</ul>
					    
					    <button class="btn" style="width:100%;border-radius: 6px;text-align: center;height:50px;font-size:18px;background:#18b4ed;position: fixed;bottom:10%;color:#fff;border:none;outline: none; ">购买</button>
		
		<div>
	</div>
</body>


</html>