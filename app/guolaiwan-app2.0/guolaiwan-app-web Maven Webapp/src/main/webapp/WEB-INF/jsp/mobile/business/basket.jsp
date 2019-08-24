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

<title>购物车</title>

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
	padding: 0;
	margin: 0;
	height: 200px;
}

.swiper-container img {
	display: block;
	width: 100%;
}

#distributeList {
	margin-top: 10px;
	padding-left: 10px;
	border-bottom: solid 2px #18b4ed;
	width: 100%;
	height: 35px;
}

#distributeList a {
	text-decoration: none;
	color: #CCC;
	font-size: 12px;
}

#distributeList a.current {
	text-decoration: none;
	color: #18b4ed;
	font-size: 20px;
}

#columnTable {
	width: 100%;
	margin-top: 10px;
}

#columnTable td {
	width: 20%;
	text-align: center;
	font-size: 12px;
}
.weui-count .weui-count__btn:after {
    height: 11px;
    width: 1px;
    margin-top: -4.5px;
    margin-left: -0.5px;
}
.weui-count .weui-count__increase {
background: #EC6D1E !important;

}

.weui-count .weui-count__btn{
    border: 1px solid #EC6D1E !important;
    
}
.weui-count .weui-count__btn:after, .weui-count .weui-count__btn:before{
background: #666 !important;
}
.weui-cells_checkbox .weui-check:checked + .weui-icon-checked:before {
color: #EC6D1E!important;
}
.weui-navbar {
 display: none !important;
}
#pay,#selectAll{
 background: #EC6D1E !important;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>


<script type="text/javascript">

	$(function() {
	  window.BASEPATH = '<%=basePath%>';
	  var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
	  };
		var roomId = "";
	    var inRoomDate = "";
	    var outRoomDate = "";
          //查询未支付订单       
          var _uriorder = window.BASEPATH + 'business/backet/get?userId=${userId}&ifpay=false&merchantId=${merchantId}';
          $.get(_uriorder, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			var html=[];
			if(data && data.length>0){
			   
				for(var i=0; i<data.length; i++){
				    var countStyle='';
				    if(data[i].activityId!=0){
				       countStyle='visibility:hidden;';
				    }
				    html.push('<div class="orderAll" style="width:100%;height:200px;border-bottom:1px solid #CCC" id="or-'+data[i].id+'">');
				    html.push('   <div style="width:50px;float:left" class="weui-cells weui-cells_checkbox">');
				    html.push('     <label class="weui-cell weui-check__label" for="s12-'+data[i].id+'" style="background-color:#fbfbfb;">');
					html.push('	    <div class="weui-cell__hd" style="background-color:#fbfbfb">');
					html.push('	      <input type="checkbox" name="checkbox-'+data[i].id+'" class="weui-check" id="s12-'+data[i].id+'">');
					html.push('	      <i class="weui-icon-checked"></i>');
					html.push('	    </div>');
					html.push('	    <div class="weui-cell__bd">');
					html.push('	      <p></p>');
					html.push('	    </div>');
					html.push('	  </label>');
					html.push('	</div>');
					html.push('    <a style="" href="javascript:void(0);"  id="pro-'+data[i].id+'-'+data[i].productId+'-'+data[i].activityId+'-'+data[i].bkCode+'-'+data[i].shopId+'" class="weui-media-box weui-media-box_appmsg orderproduct">');
					html.push('      <div class="weui-media-box__hd">');
					html.push('       <img style="width:60px;height:60px;" class="weui-media-box__thumb" src="'+data[i].productPic+'">');
					html.push('      </div>');
					html.push('      <div class="weui-media-box__bd">');
					html.push('        <h4 class="weui-media-box__title" style="font-size:12px;">'+data[i].productName+'</h4>');
					html.push('        <p class="weui-media-box__desc">￥'+data[i].productPrice+'</p>');
					html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">'+data[i].createDate+'</p>');
					html.push('      </div>');
					html.push('    </a>');
					html.push('<p class="weui-media-box__desc startDate" style="margin-left:75px;font-size:12px;">'+'套餐：');
					html.push('		<a style="width:65%;" placeholder="">'+data[i].comboName+'</a>');
					html.push('</p>');
					html.push('<p class="weui-media-box__desc startDate" style="margin-left:75px;font-size:12px;">'+'快递：');
					html.push('		<a style="width:65%;" placeholder="">'+data[i].logisticsName+'</a>');
					html.push('</p>');
					if(data[i].bkCode=='0002'){
					     roomId = data[i].roomId;
					     var inRoomdate = data[i].orderBookDate.split(" ");	  
					     inRoomDate = inRoomdate[0].replace('年','-').replace('月','-').replace('日','');			    
					     var outRoomdate = data[i].endBookDate.split(" ");
					     outRoomDate = outRoomdate[0].replace('年','-').replace('月','-').replace('日','');					      
						html.push('<p class="weui-media-box__desc startDate" style="margin-left:75px;font-size:12px;">'+'入住时间：');
						html.push('		<a class="startdateText"  id="startdateText-'+data[i].id+'" style="width:65%;" placeholder="">'+data[i].orderBookDate+'</a>');
						/* html.push('		<a href="javascript:;" class="updatestartDate" style="background:#18b4ed;" id="updstart-'+data[i].id+'">修改</a>');  */
						html.push('</p>');
						html.push('<p class="weui-media-box__desc endDate" style="margin-left:75px;margin-top:10px;font-size:12px;">'+'离店时间：');
						html.push('		<a class="enddateText" id="enddateText-'+data[i].id+'" style="width:65%;" placeholder="">'+data[i].endBookDate+'</a>');
						/* html.push('		<a href="javascript:;" class="updateendDate" style="background:#18b4ed;" id="updend-'+data[i].id+'">修改</a>');  */
						html.push('</p>');
					}else if(data[i].bkCode=='0003'||data[i].bkCode=='0001'){
						html.push('<p class="weui-media-box__desc bookDate" style="margin-left:75px;font-size:12px;">'+'预定时间：');
						html.push('		<a class="bookdateText" id="bookdateText-'+data[i].id+'" style="width:65%;" placeholder="">'+data[i].orderBookDate+'</a>');
						/* html.push('		<a href="javascript:;" class="updatebookDate" style="background:#18b4ed;" id="updbook-'+data[i].id+'">修改</a>');  */
						html.push('</p>');
					}
					html.push(' <div class="weui-count" style="font-size:12px;margin-top:15px;float:right;margin-right:10px;">');
		            html.push('     <a style="'+countStyle+'" class="weui-count__btn weui-count__decrease" id="de-'+data[i].id+'"></a>');
		            html.push('     <input  data="'+data[i].productPrice+'" class="weui-count__number" disabled="disabled" id="s13-'+data[i].id+'" type="number" value="'+data[i].productNum+'">');
		            html.push('     <input id="productRestrictNumber" class="productRestrictNumber" name="productRestrictNumber" type="text" hidden="hidden" value="'+data[i].productRestrictNumber+'">');
		            html.push('     <a style="'+countStyle+'" class="weui-count__btn weui-count__increase" id="in-'+data[i].id+'"></a>');
		            html.push('   </div>');
					html.push('<a href="javascript:;" class="icon-trash delOrder" id="del-'+data[i].id+'" style="margin-left:25px;float:left;font-size:12px;margin-top:17px;width:60px;line-height:20px;height:20px">&nbsp;&nbsp;删除</a>');
			    	html.push('</div>');
				}

			}else{
			
			    html.push("<div style='width:100%;margin-top:10px;font-size:12px;text-align:center'>暂无数据</div>");
			}
			
			$('#orderList').children().remove();
			$('#orderList').append(html.join(''));
			initTotal();
			
		});
	
	
	
	 $(document).on('click','.weui-check',function(){
	      //$('.weui-check').prop('checked',false);
	      //$(this).prop('checked',true);
	      initTotal();
	   });
	   
	   $(document).on('click','#selectAll',function(){
	   		var chks=$('.weui-check');
	   	    for(var i=0;i<chks.length;i++){
		        var ids=chks[i].id.split('-');
		        if($(chks[i]).prop('checked')==true){
		        	continue;
		        }else{
	        		$('.weui-check').prop('checked',true);
	        			$('#cancel').html('取消');
	        			initTotal();
	        		  return false;
	        	}
	        }
	        $('#cancel').html('');
	        $('.weui-check').prop('checked',false);  
	        initTotal();
	   
	   });
	   
	   
	   
	
	   function deleteOrder(orderId){
	      $.confirm("是否删除此订单？", function() {

			        var param={};
			        param.orderId=orderId;
			        param.userId=${userId};
					$.post(window.BASEPATH +"phoneApp/order/delbyID", $.toJSON(param), function(data){
						$('#or-'+orderId).remove();
						initTotal();
					});

			  
			  }, function() {
			  //点击取消后的回调函数
			  });
	   
	   
	   }
	   
/* 	   	$(document).on('click','.startdateText',function(){
	   		var orderIds=this.id.split('-');	   
		    $("#startdateText-"+orderIds[1]).datetimePicker({
		        title: '请选择入住时间',
		        yearSplit: '年',
		        monthSplit: '月',
		        dateSplit: '日',
		        times: function () {
		            return [  // 自定义的时间
		                {
		                    values: (function () {
		                        var hours = [];
		                        for (var i=0; i<24; i++) hours.push(i > 9 ? i : '0'+i);
		                        return hours;
		                    })()
		                },
		                {
		                    divider: true,  // 这是一个分隔符
		                    content: ':'
		                },
		                {
		                    values: (function () {
		                        var minutes = [];
		                        for (var i=0; i<60; i++) minutes.push(i > 9 ? i : '0'+i);
		                        return minutes;
		                    })()
		                }
		            ];
		        },
		        onChange: function (picker, values, displayValues) {
		        console.log(values);
		            var param={};
			        param.orderId=orderIds[1];
			        param.time=values;
					$.post(window.BASEPATH +"phoneApp/order/changetime", $.toJSON(param), function(data){}); 
		        }
		    });		       
               
	   });

	   $(document).on('click','.enddateText',function(){
	   		var orderIds=this.id.split('-');
		    $("#enddateText-"+orderIds[1]).datetimePicker({
		        title: '请选择入住时间',
		        yearSplit: '年',
		        monthSplit: '月',
		        dateSplit: '日',
		        times: function () {
		            return [  // 自定义的时间
		                {
		                    values: (function () {
		                        var hours = [];
		                        for (var i=0; i<24; i++) hours.push(i > 9 ? i : '0'+i);
		                        return hours;
		                    })()
		                },
		                {
		                    divider: true,  // 这是一个分隔符
		                    content: ':'
		                },
		                {
		                    values: (function () {
		                        var minutes = [];
		                        for (var i=0; i<60; i++) minutes.push(i > 9 ? i : '0'+i);
		                        return minutes;
		                    })()
		                }
		            ];
		        },
		        onChange: function (picker, values, displayValues) {
		            var param={};
			        param.orderId=orderIds[1];
			        param.time=values;
			        param.end="true";
					$.post(window.BASEPATH +"phoneApp/order/changetime", $.toJSON(param), function(data){}); 
		        }
		    });		       
               
	   });
	   
	   $(document).on('click','.bookdateText',function(){
	   		var orderIds=this.id.split('-');
		    $("#bookdateText-"+orderIds[1]).datetimePicker({
		        title: '请选择入住时间',
		        yearSplit: '年',
		        monthSplit: '月',
		        dateSplit: '日',
		        times: function () {
		            return [  // 自定义的时间
		                {
		                    values: (function () {
		                        var hours = [];
		                        for (var i=0; i<24; i++) hours.push(i > 9 ? i : '0'+i);
		                        return hours;
		                    })()
		                },
		                {
		                    divider: true,  // 这是一个分隔符
		                    content: ':'
		                },
		                {
		                    values: (function () {
		                        var minutes = [];
		                        for (var i=0; i<60; i++) minutes.push(i > 9 ? i : '0'+i);
		                        return minutes;
		                    })()
		                }
		            ];
		        },
		        onChange: function (picker, values, displayValues) {
		            var param={};
			        param.orderId=orderIds[1];
			        param.time=values;
					$.post(window.BASEPATH +"phoneApp/order/changetime", $.toJSON(param), function(data){}); 
		        }
		    });		       
               
	   });
	    */
	   	   	   
	   $(document).on('click','.delOrder',function(){
	       
               var ids=this.id.split('-');
               deleteOrder(ids[1]);
	   });
	
	
	
	   var MAX = 99, MIN = 0;
		$(document).on('click','.weui-count__decrease',function (e) {
		 
		  var $productRestrictNumber=$(e.currentTarget).parent().find('.productRestrictNumber');
		  var orderIds=this.id.split('-');
		  var $input = $(e.currentTarget).parent().find('.weui-count__number');
		  var number = parseInt($input.val() || "0") - 1;
		  
		  var chkStockUrl=window.BASEPATH + 'pubnum/change/ordercount?orderId='+orderIds[1]+'&count=-1';
			$.get(chkStockUrl, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
				if(data.stock==0){
				   $.toast("抱歉，库存不足", "forbidden");
				   return;
				}else if(data.stock==-1){
				   $.toast("今天的名额已经使用，明天再来吧", "forbidden");
				   return;
				}
				
				if(number==parseInt($productRestrictNumber.val())-1){
				     deleteOrder(orderIds[1]);
				  }else{
				  
				   $input.val(number);
			       initTotal();
				  }
				
		    });
		  
		 
	      
		});
		$(document).on('click','.weui-count__increase',function (e) {
		  var orderIds=this.id.split('-');
		  var $input = $(e.currentTarget).parent().find('.weui-count__number');
		  var number = parseInt($input.val() || "0") + 1
		  
		  var chkStockUrl=window.BASEPATH + 'pubnum/change/ordercount?orderId='+orderIds[1]+'&count=1';
			$.get(chkStockUrl, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
				if(data.stock==0){
				   $.toast("抱歉，库存不足", "forbidden");
				   return;
				}else if(data.stock==-1){
				   $.toast("今天的名额已经使用，明天再来吧", "forbidden");
				   return;
				}
		        $input.val(number);
		        initTotal();
		  });
		 
		});
	
	  function initTotal(){
	   
	       var total=0;
	       var chks=$('.weui-check');
	       for(var i=0;i<chks.length;i++){
	          var ids=chks[i].id.split('-');
	          
	          var startDate=$('#startdateText-'+ids[1]).val();
	          var endDate=$('#enddateText-'+ids[1]).val();
	          var daycount=1;
			  if(startDate&&endDate){
			        startDate = Date.parse(startDate);
			        endDate = Date.parse(endDate);
			        var dateSpan = endDate - startDate;
			        iDays = Math.ceil(dateSpan / (24 * 3600 * 1000));
			        if(iDays>0){
			          daycount=iDays;
			        }
			  }
	          if($(chks[i]).prop('checked')==true){
	              total+=$('#s13-'+ids[1]).attr('data')*$('#s13-'+ids[1]).val()*daycount;
	          }
	          
	       }
	       
	       $('#total').html(total);
	   }
	   function initTotal1(){
	   
	       var total=0;
	       var chks=$('.weui-check');
	       
	       for(var i=0;i<chks.length;i++){
	          if($(chks[i]).prop('checked')==true){
	              var ids=$(chks[i]).attr('id').split('-');   
	              total+=parseInt(ids[2])*$('#'+ids[0]+'-'+ids[1]).val();
	          }
	       
	       }
	       
	       $('#total').html(total);
	   }
	      
	   
	    $(document).on('click','#pay',function(){
	    
	       var total=0;
	       var chks=$('.weui-check');
	       
	       for(var i=0;i<chks.length;i++){
	          if($(chks[i]).prop('checked')==true){
	              var ids=$(chks[i]).attr('id').split('-');   
	              total+=parseInt(ids[2])*$('#'+ids[0]+'-'+ids[1]).val();
	          }
	       
	       }
	       if(total != 0){
	    		$('#selAddress').popup();
	       }else{
	       		$.toast("请选择商品", "forbidden");
	       }
	       
	    
	    });
	    
	    $(document).on('click','#buynow',function(){
		    var addressIds=$('input[type^=radio]:checked').attr('id').split('-');
		    $.closePopup();
		   
	        var ids=[];
	        var nums=[];
	        var chks=$('.weui-check');
	        for(var i=0;i<chks.length;i++){
	          var chkids=chks[i].id.split('-');
	          if($(chks[i]).prop('checked')==true){	        
	             ids.push(chkids[1]);	            
	             nums.push($('#s13-'+chkids[1]).val());
	          }
	        }
	        
	        $.get(window.BASEPATH +"pubnum/changeOrderAddress?orderStr="+ids.join('A')+"&addressId="+addressIds[1], null, function(data){
					 $.modal({
						  title: "付款方式",
						  buttons: [
						    { text: "余额支付", onClick: function(){ 
						    	$.confirm("确定支付？", function() {
								    payByWallet(ids.join('A'),nums.join('A'));
								  }, function() {});
						    } },
						    { text: "微信支付", onClick: function(){						    
						       $.confirm("确定支付？", function() {
								    payPublic(ids.join('A'),nums.join('A'));
								  }, function() {}); 						        			   					  								 								
						    } },
						    { text: "取消", className: "default", onClick: function(){ } },
						  ]
						})  															    										  							
				});	
		});
	    
	    $(document).on('click','.orderproduct',function(){	      
	       var ids=this.id.split('-');
	       //景点  普通票
	       if(ids[4]== "0001" && ids[3] == "0"){
	        location.href=window.BASEPATH + '/product/package/commodity/jump?merchantId='+ids[5]+'&proId='+id+'&choice=0';
	       }
	       //景点  活动票
	       if(ids[4]== "0001" && ids[2] == "0"){
	       location.href=window.BASEPATH + '/product/package/commodity/jump?merchantId='+ids[5]+'&proId='+id+'&choice=1';	       
	       }
	       //住宿
	       if(ids[4]== "0002"){
	        location.href=window.BASEPATH + '/business/gotoroomdetails?roomId='+roomId+'&inRoomDate='+inRoomDate+'&outRoomDate='+outRoomDate;
	       }
	       //采摘
	       if(ids[4]== "2126"){
	        location.href=window.BASEPATH +'/business/gotodetailspage?productId='+ids[2];
	       }
	     
	    });
	    
	    
	    //钱包支付
	    function payByWallet(orderId,numStr){
			var url=window.BASEPATH +"pubnum/prev/paywalletbasket/"+orderId+"/"+numStr+"/";		
			$.post(url,null,function(data){
						data = parseAjaxResult(data);
				if(data==1){										
						$.confirm("交易成功");						
		                location.href=window.BASEPATH +"business/order/list";
				}else{
					$.alert('您的余额不足！');
				}                   
			})
		}
	   	
	    var prepay_id;
		var paySign;
		var appId;
		var timeStamp;
		var nonceStr;
		var packageStr;
		var signType;
		var orderNo;	
		

		function payPublic(orderId,numStr){
			$.get(window.BASEPATH +"pubnum/prev/paybasket/"+orderId+"/"+numStr+"/", null, function(data){
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
		                $.confirm("交易成功");
		                //每五秒刷新订单状态
						
		                location.href=window.BASEPATH +"business/order/list";

		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		                alert("交易取消");  
	                    location.href=location.href;
		            }  
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert(res.err_desc); 
                        location.href=location.href;
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
	
	
	
	
	 //编辑地址
		$(document).on('click','#addAddress',function(){
		    $('#addressFitst').hide();
		    $('#addressSecond').show();
		
		});
		$(document).on('click','#save',function(){
		    if($('#address').val()==''){
			   $.toast("请选择地址", "forbidden");
			   return false;
			}
			if($('#addressphone').val()==''){
			   $.toast("请输入手机号", "forbidden");
			   return false;
			}
			if(!(/^1[34578]\d{9}$/.test($('#addressphone').val()))){ 
		       $.toast("手机号码有误，请重填", "forbidden");  
		       return false; 
		    } 
			if($('#name').val()==''){
			   $.toast("请输入姓名", "forbidden");
			   return false;
			}
		
		
		    var _uriAdd = window.BASEPATH + 'phoneApp/address/add';
			var params={};
			params.userId=${userId};
			var addresses=$('#address').val().split(' ');
			params.province=addresses[0];
			params.city=addresses[1];
			params.district=addresses[2];
			params.consigneeAddress=$('#moreAddress').val();
			params.addressphone=$('#addressphone').val();
			params.consigneeName=$('#name').val();
			$.post(_uriAdd, $.toJSON(params), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				$('#addressFitst').show();
		        $('#addressSecond').hide();
                getAllAddr();
			});
	  });

    $(document).on('click','#cancelAddress',function(){
	   		$.closePopup();
	  
	    });
	  
	
      $("#address").cityPicker({
        title: "选择地址",
        onChange: function (picker, values, displayValues) {
          console.log(values, displayValues);
        }
     });
     
     getAllAddr();
     function getAllAddr(){
        var _uriAddress = window.BASEPATH + 'phoneApp/address/list?userId=${userId}';
		
		$.get(_uriAddress, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data && data.length>0){
			    var html=[];
				for(var i=0; i<data.length; i++){
				     var chkattr='';
				     if(i==0){
				         chkattr='checked="checked"';
				     }
					 html.push('<div class="weui-media-box weui-media-box_text mailAddress" id="mailadd-'+data[i].id+'">');
					 html.push('<input style="float:left;height:27px;width:20px"  type="radio" name="radio1" class="" id="radio-'+data[i].id+'" '+chkattr+'>');
			         html.push('<h4 style="width:80%;margin-left:35px;" class="weui-media-box__title">'+data[i].consigneeName+'（'+data[i].consigneePhone+'）</h4>');
			         //html.push('<p class="weui-media-box__desc">身份证'+(data[i].idNum?data[i].idNum:'-')+'</p>');
			         html.push('<p class="weui-media-box__desc">'+data[i].province+data[i].city+data[i].district+data[i].consigneeAddress+'</p>');
			         html.push('</div>');
				}
				$('#addressList').children().remove();
			    $('#addressList').append(html.join(''));
			}
			
		});
		}
	
	
	});
</script>



<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span
					class="icon-reorder icon-large"></span></a>
				<div class="header-content">购物车</div>
			</div>
		</div>
		<div class="content">

			<div id="orderList" class="weui-panel__bd"
				style="padding-bottom:50px;"></div>
			<!-- <a id="pay" style="position:fixed;bottom:0px;width:96%;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">
                                             支付(￥<span id="total">0</span>)
              </a> -->

			<div style="width:100%;height:40px;position:fixed;bottom:2px">
				<a id="selectAll"
					style="width:46%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;"
					href="javascript:;" class="weui-btn weui-btn_primary"><span
					id="cancel"></span>全选</a> <a id="pay"
					style="width:46%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
					href="javascript:;" class="weui-btn weui-btn_primary">支付（￥<span
					id="total">0</span>）
				</a>

			</div>




			<div id="selAddress" class="weui-popup__container"
				style="padding-bottom:50px;">
				<div class="weui-popup__overlay"></div>
				<div class="weui-popup__modal">
					<div id="addressFitst">

						<div class="weui-cells__title" style="color:red;font-weight:bold">点击地址选择或添加新联系人</div>
						<a id="addAddress"
								style="width:96%;font-size:14px;margin-left:2%;background-color:#18b4ed;height:30px;line-height:30px;margin-top:0"
								href="javascript:;" class="weui-btn weui-btn_primary">添加地址</a>
						<div class="weui-panel__bd" id="addressList"
							style="padding-bottom:40px;"></div>

						<div style="width:100%;height:40px;">
							<a id="cancelAddress"
								style="width:47%;font-size:14px;margin-left:2%;float:left;height:40px;line-height:40px;"
								href="javascript:;" class="weui-btn weui-btn_warn">取消购买</a> <a
								id="buynow"
								style="width:47%;font-size:14px;margin-left:2%;float:left;background-color:#18b4ed;height:40px;line-height:40px;margin-top:0"
								href="javascript:;" class="weui-btn weui-btn_primary">立即购买</a>
						</div>

					</div>


					<div id="addressSecond" style="display:none;">

						<div class="weui-cells weui-cells_form">
							<div class="weui-cells__title">添加收货地址</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">姓名</label>
								</div>
								<div class="weui-cell__bd">
									<input id="name" class="weui-input" type="text" placeholder="">
								</div>
							</div>
							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">手机号</label>
								</div>
								<div class="weui-cell__bd">
									<input id="addressphone" class="weui-input" type="text"
										placeholder="">
								</div>
							</div>

							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label for="name" class="weui-label">地址选择</label>
								</div>
								<div class="weui-cell__bd">
									<input class="weui-input" id="address" type="text" value=""
										readonly="" data-code="420106"
										data-codes="420000,420100,420106">
								</div>
							</div>


							<div class="weui-cell">
								<div class="weui-cell__hd">
									<label class="weui-label">详细地址</label>
								</div>
								<div class="weui-cell__bd">
									<input id="moreAddress" class="weui-input" type="text"
										placeholder="">
								</div>
							</div>
						</div>
						<a id="save"
							style="width:96%;position:fixed;bottom:0;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;"
							href="javascript:;" class="weui-btn weui-btn_primary"> 保存</a>

					</div>

				</div>



			</div>
		</div>
</body>


</html>