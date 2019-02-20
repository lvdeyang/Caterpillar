<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html lang="zh-cmn-Hans">
<head>

    <!-- 声明文档使用的字符编码 -->
    <meta charset='utf-8'>
    <!-- 优先使用 IE 最新版本和 Chrome -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <!-- 页面描述 -->
    <meta name="description" content=""/>
    <!-- 页面关键词 -->
    <meta name="keywords" content=""/>
    <!-- 网页作者 -->
    <meta name="author" content="name, email@gmail.com"/>
    <!-- 搜索引擎抓取 -->
    <meta name="robots" content="index,follow"/>
    <!-- 为移动设备添加 viewport -->
    <meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <!-- `width=device-width` 会导致 iPhone 5 添加到主屏后以 WebApp 全屏模式打开页面时出现黑边 http://bigc.at/ios-webapp-viewport-meta.orz -->

    <!-- iOS 设备 begin -->
    <meta name="apple-mobile-web-app-title" content="标题">
    <!-- 添加到主屏后的标题（iOS 6 新增） -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <!-- 是否启用 WebApp 全屏模式，删除苹果默认的工具栏和菜单栏 -->

    <meta name="apple-itunes-app" content="app-id=myAppStoreID, affiliate-data=myAffiliateData, app-argument=myURL">
    <!-- 添加智能 App 广告条 Smart App Banner（iOS 6+ Safari） -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <!-- 设置苹果工具栏颜色 -->
    <meta name="format-detection" content="telphone=no, email=no"/>
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

	<title>订单编辑</title>

    <!-- 公共样式引用 -->
	<jsp:include page="../../../../mobile/commons/jsp/style.jsp"></jsp:include>
    
    <style type="text/css">
        a{cursor:pointer!important;}
        a, a:link, a:active, a:visited, a:hover{color:inherit; text-decoration:none;}
        
        html, body{width:100%; min-height:100%; background-color:#fff; position: relative; -webkit-text-size-adjust:none; background-color:#fbfbfb;}
        *{box-sizing:border-box;}
        .z-depth-1-bottom{box-shadow:0 2px 2px 0 rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12), 0 1px 5px 0 rgba(0,0,0,.2) !important;}
        .z-depth-1-right{box-shadow:2px 0 2px 0 rgba(0,0,0,.14), 3px 0 1px -2px rgba(0,0,0,.12), 1px 0 5px 0 rgba(0,0,0,.2) !important;}
        .wrapper{height:100%; width:100%; position:relative;}
        
        /* mmenu 样式覆盖 */
        .mm-listview .mm-next{width:90px !important;}
        
        .mm-navbar.mm-navbar-top{height:110px !important;}
        .mm-menu.mm-hasnavbar-top .mm-panel, .mm-menu.mm-hasnavbar-top .mm-fixeddivider{top:110px !important;}
        
        .picker-highlight{box-shadow:0 -1px 10px rgba(0,0,0,.3);}
        .picker-highlight .toolbar, 
        .picker-highlight .picker-items{background:#fff;}
        .picker-highlight .toolbar .title{color:#000;}
        
        .weui-picker-calendar{box-shadow:0 -1px 10px rgba(0,0,0,.3) !important;}
        .weui-picker-calendar .toolbar{background:#fff !important;}
        .weui-picker-calendar .picker-calendar-week-days{background:#fff !important;}
        
        /* 页面样式 */
        .header{height:40px; line-height:40px; background-color:#18b4ed; color:#fff; border-bottom:1px solid #bababa;}
        .header .link-left{margin-left:10px; margin-right:10px; position:relative; z-index:1;}
        .header-content{height:100%; width:100%; position:absolute; left:0; top:0; padding-left:40px; padding-right:40px; text-align:center; z-index:0;}
        
        .side-menu{background-color:#fff;}
        .logout{color:#fff !important; line-height:inherit !important; font-size:inherit !important; border-radius:0;}
        
        .content{padding:5px;}
        .cards{box-shadow: 0 1px 2px rgba(0,0,0,.3); background-color:#fff;}
        .weui-btn{color:#fff !important;}
        .weui-btn:after{border-radius:0 !important;}
        
        .form-row{height:81px; line-height:80px; border-bottom:1px solid #f1f1f1; position:relative;}
        .form-row.last{border-bottom:0;}
        .form-row.btn-content{padding:20px 10px;}
        .form-row.text-content{text-align:center;}
        .form-row.sm{height:51px; line-height:50px;}
        .form-row.lg{height:111px; line-height:110px;}
        .back-icon{position:absolute; left:0; top:0; width:100%; height:100%; z-index:0; text-align:center;}
        .form-col-half{width:50%; height:100%; float:left; padding:0 15px; text-align:center; position:relative; z-index:1; line-height:80px; text-align:center;}
    	.form-col-half.sm{line-height:50px;}
    	.form-col-half.left-border{border-right:1px solid #f1f1f1;}
    	.form-row.sm .form-col-half{line-height:50px;}
    	.form-row.sm .form-col-half>input{padding:10px;}
    	
    	.form-row input{width:80%; margin:auto; padding:10px; border:0; font-size:14px;}
    	.form-row textarea{width:80%; height:95px; line-height:35px; padding:0 10px; position:relative; top:8px; border:0; font-size:14px; font-family:Arial;}
    	
    	.btn-custom{height:40px; line-height:40px; font-size:16px; border-radius:0; background-color:#18b4ed;}
    	.btn-custom:active{opacity:0.7 !important; background-color:#18b4ed !important;}
    	
    	.content-top, 
    	.content-bottom{line-height:40px; position:relative;}
    	.content-top{font-size:14px; color:#bfbfbf; top:8px;}
    	.content-bottom{bottom:8px;}
    	.content-icon{color:#bfbfbf; position:relative;}
    	
    	.my-popup .weui-popup__modal{background:#fbfbfb;}
    	.my-popup .go-back>span{position:relative; top:2px;}
    	.my-popup table{width:100%; font-size:14px; border-collapse:collapse;}
    	.my-popup table thead th,
    	.my-popup table tbody td{border:1px solid #f1f1f1;}
    	.my-popup table tbody td.center{text-align:center;}
    	.my-popup table th,
    	.my-popup table td{padding:10px 2px;}
    	.my-popup table tr:first-child>th,
        .my-popup table tr:first-child>td{border-top:0;}
        .my-popup table tr:last-child>td{border-bottom:0;}
        .my-popup table tr>th:last-child,
        .my-popup table tr>td:last-child{border-right:0;}
    	
    	.price{color:#bfbfbf; transition:all .05s ease-in;}
    	.price.selected{font-size:18px; color:#FB6155; border-top:2px solid #FB6155;}
    	
    	/* 计数器 */
    	.my-counter-label{position:relative; bottom:9px; font-size:14px;}
    	
    	.my-counter{height:30px; line-height:30px; width:90px; border:1px solid #bfbfbf; margin-top:10px; border-radius:4px; position:relative;}
    	
    	.my-counter,
    	.my-counter>a, 
    	.my-counter>span{display:inline-block;}
    	
    	.my-counter>a, 
    	.my-counter>span{height:28px; line-height:28px; text-align:center; position:absolute;}
    	.my-counter .btn-minus{border-right:1px solid #bababa; width:29px; color:#ababab; left:0; top:0;}
    	.my-counter .number{width:30px; font-size:14px; left:30px; top:0;}
    	.my-counter .number.warning{background-color:#FB6155; color:#fff;}
    	.my-counter .btn-plus{border-left:1px solid #bababa; width:29px; color:#ababab; right:0; top:0;}
    	
    	/* 日期组件代理对象 */
    	.click-wrapper{position:absolute; z-index:1; width:100% !important; height:100%; opacity:0; left:0; top:0; text-align:center;}
    </style>
	
</head>

<body>
	<div id="page">
		<!-- 主页 -->
		<div class="header">
			<div class="wrapper">
				<a class="link-left" href="#side-menu"><span class="icon-reorder icon-large"></span></a>
				<div class="header-content">约吧拼车</div>
			</div>
		</div>
		<div class="content">
			<div class="cards">
				<div class="form-row">
					<div class="form-col-half" id="departure">始发站</div><input type="hidden" value="-1"/>
					<div class="back-icon"><span class="icon-exchange" style="color:#18b4ed; font-size:20px;"></span></div>
					<div class="form-col-half" id="destination">终点站</div><input type="hidden" value="-1"/>
				</div>
				<div class="form-row">
					<div class="form-col-half left-border">
						<input type="text" id="departure-date" class="click-wrapper" value="-1"/>
						<div style="line-height:40px;" class="content-top">出发日期</div>
						<div style="line-height:40px;" class="content-bottom">
							<span style="font-size:14px; bottom:1px;" class="icon-calendar content-icon"></span>
							<span id="departure-date-text">00月00日</span>
						</div>
					</div>
					<div class="form-col-half">
						<input type="text" id="departure-time" class="click-wrapper" value="-1"/>
						<div class="content-top">出发时间</div>
						<div class="content-bottom">
							<span style="font-size:18px; top:1px;" class="icon-time content-icon"></span>
							<span id="departure-time-text">00:00</span>
						</div>
					</div>
				</div>
				<div class="form-row sm text-content">
					<input id="fleet" type="text" readonly placeholder="车队选择" style="text-align:center;"/>
					<input type="hidden" value="-1"/>
				</div>
				<div class="form-row">
					<div class="form-col-half left-border price no-setted" id="carpool-price">未选择</div>
					<input type="hidden" value="0" />
					<div class="form-col-half price no-setted" id="charter-price">未选择</div>
					<input type="hidden" value="1" />
				</div>
				<div class="form-row sm">
					<div class="form-col-half left-border"><input id="add-money" type="text" style="padding:10px 0; text-align:center;" placeholder="加钱（单位：元）"/></div>
					<div class="form-col-half">
			            <span class="my-counter-label">人数</span>
						<div id="passenger-num" class="my-counter" data-max="4" data-min="1" data-step="1">
			                <a class="btn-minus"><span class="icon-minus"></span></a>
			                <span class="number">1</span>
			                <a class="btn-plus"><span class="icon-plus"></span></a>
			            </div>	
              		</div>
				</div>
				<div class="form-row lg text-content">
					<textarea id="remark" style="width:84%;" placeholder="备注：可注明接送地点或其他要求"></textarea>
				</div>
				<div class="form-row btn-content">
					<button class="weui-btn weui-btn_primary btn-custom" id="save-order">提交订单</button>
				</div>
			</div>
		</div>
		
		<!-- 车队popup -->
		<div id="fleet-popup" class="weui-popup__container my-popup">
		    <div class="weui-popup__overlay"></div>
		    <div class="weui-popup__modal">
		    	<div class="header">
					<div class="wrapper">
						<a class="link-left go-back" id="go-back"><span class="icon-angle-left icon-2x"></span></a>
						<div class="header-content">选择车队</div>
					</div>
				</div>
				<div class="content">
					<div class="cards">
						<table>
							<thead><tr><th style="width:40px;"></th><th>车队</th><th style="width:50px;">拼车</th><th style="width:50px;">包车</th></tr></thead>
							<tbody id="fleet-list"></tbody>
						</table>
					</div>
				</div>
		    </div>
		</div>
		
		<!-- 订单预览 -->
		<div id="order-popup" class="weui-popup__container my-popup">
			<div class="weui-popup__overlay"></div>
		    <div class="weui-popup__modal">
		    	<header style="padding:20px 0;">
			        <h1 style="text-align:center; font-size:26px; color:#3cc51f; font-weight:400; margin:0 15%;">订单确认</h1>
			    </header>
		    	<div class="weui-form-preview">
				    <div class="weui-form-preview__hd">
					    <label class="weui-form-preview__label">付款金额</label>
					    <em class="weui-form-preview__value"></em>
				    </div>
				    <div class="weui-form-preview__bd">
					    <div class="weui-form-preview__item">
					        <label class="weui-form-preview__label">路线</label>
					        <span class="weui-form-preview__value"></span>
					    </div>
					    <div class="weui-form-preview__item">
					        <label class="weui-form-preview__label">出发时间</label>
					        <span class="weui-form-preview__value"></span>
					    </div>
					    <div class="weui-form-preview__item">
					        <label class="weui-form-preview__label">打车方式</label>
					        <span class="weui-form-preview__value"></span>
					    </div>
					    <div class="weui-form-preview__item">
					        <label class="weui-form-preview__label">人数</label>
					        <span class="weui-form-preview__value"></span>
					    </div>
					    <div class="weui-form-preview__item">
					        <label class="weui-form-preview__label">加钱</label>
					        <span class="weui-form-preview__value"></span>
					    </div>
					    <div class="weui-form-preview__item">
					        <label class="weui-form-preview__label">备注</label>
					        <span class="weui-form-preview__value"></span>
					    </div>
					</div>
					<div class="weui-form-preview__ft">
					    <button class="weui-form-preview__btn weui-form-preview__btn_default" id="order-cancel">取消</button>
					    <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" id="order-commit">确认</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 侧边栏菜单 -->
		<nav id="side-menu" class="z-depth-1-right side-menu">
			<ul>
				<li><a href="http://www.baidu.com"><span class="icon-list-alt"></span>&nbsp;订单</a></li>
				<!-- <li><a href="#about">About us</a>
					<ul>
						<li><a href="#about/history">History</a></li>
						<li><a href="#about/team">The team</a>
							<ul>
								<li><a href="#about/team/management">Management</a></li>
								<li><a href="#about/team/sales">Sales</a></li>
								<li><a href="#about/team/development">Development</a></li>
							</ul>
						</li>
						<li><a href="#about/address">Our address</a></li>
					</ul>
				</li>
				<li><a href="#contact">Contact</a></li> -->
			</ul>
		</nav>
	</div>
	
	<!-- 订单提交表单 -->
	<form id="order-form" action="<%=basePath %>order/add" method="POST" style="display:none;">
		<input name="departureId" type="hidden" />
		<input name="departureName" type="hidden" />
		<input name="destinationId" type="hidden" />
		<input name="destinationName" type="hidden" />
		<input name="date" type="hidden" />
		<input name="time" type="hidden" />
		<input name="fleetId" type="hidden" />
		<input name="fleetName" type="hidden" />
		<input name="routeId" type="hidden" />
		<input name="routeUuid" type="hidden" />
		<input name="passengerNum" type="hidden" />
		<input name="priceType" type="hidden" />
		<input name="price" type="hidden" />
		<input name="addMoney" type="hidden" />
		<input name="remark" type="hidden" />
	</form>
</body>

<!-- 公共脚本引入 -->
<jsp:include page="../../../../mobile/commons/jsp/script.jsp"></jsp:include>

<script type="text/javascript">
	$(function(){
		var name = '${user.name}';
		var headImgUrl = '${user.headImgUrl}';
		
		//初始化侧边栏菜单
		$('nav#side-menu').mmenu({
			extensions:[ 'effect-slide-menu', 'pageshadow' ],
			searchfield:false,
			counters:true,
			navbars:[
				{
					position:'top',
					//content:'<div class="user-info"><span class="icon-user"></span>&nbsp;18744014748<div>'
					content:'<div>'+
								'<div style="padding-top:10px; padding-bottom:10px;">'+
									'<div style="display:inline-block; width:60px; height:60px; border-radius:100%; overflow: hidden;">'+
										'<img style="width:100%; height:100%;" src="'+headImgUrl+'">'+
									'</div>'+
								'</div>'+
								'<div>'+name+'</div>'+
							'</div>'
				},{
					position:'bottom',
					content:[ '<a class="weui-btn weui-btn_warn logout">注销登录</a>' ]
				}
			]
		});
		
		//解析ajax返回
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
		};
		
		//计数器
		var $counterBtns = $('#passenger-num a');
		$counterBtns.on('mousedown', function(){
			var $btn = $(this);
			$btn.css({opacity:.6});
		});
		
		$(document).on('mouseup.counter.btn', '#passenger-num a', function(){
			var $btn = $(this);
			$btn.css({opacity:1});
		});
		
		$counterBtns.on('click', function(){
			var $counter = $('#passenger-num');
			var min = parseInt($counter.data('min'));
			var max = parseInt($counter.data('max'));
			var step = parseInt($counter.data('step'));
			var $number = $counter.find('.number');
			var number = parseInt($number.text());
			var $btn = $(this);
			if($btn.is('.btn-minus')){
				//减法
				if(number <= min){
					$.toptip('当前已经是最小人数', 'warning');
					return;
				} 
				if(number < (number-step)){
					$.toptip('当前计数器数据定义有误！', 'error');
				}
				if((number-step) <= 4) $number.removeClass('warning');
				$number.text(number - step);
			}else if($btn.is('.btn-plus')){
				//加法
				if(number >= max){
					$.toptip('当前已经是最大人数', 'warning');
					return;
				} 
				if(number > (number+step)){
					$.toptip('当前计数器数据定义有误！', 'error');
				}
				if((number+step) > 4) $number.addClass('warning');
				$number.text(number + step);
			}
		});
		
		var $departure = $('#departure');
		var $departureHidden = $departure.next();
		var changeDepatureName = '始发站';
		var changeDepatureId = -1;
		var $destination = $('#destination');
		var $destinationHidden = $destination.next();
		var changeDestinationName = '终点站';
		var changeDestinationId = -1;
		
		//初始化始发站
		var _uri = window.BASEPATH + 'site/query/depatures';
		$.get(_uri, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			var depatureNames = [];
			var depatureIds = [];
			if(data && data.length>0){
				for(var i=0; i<data.length; i++){
					depatureNames.push(data[i].name);
					depatureIds.push(data[i].id);
				}
			}
			$departure.picker({
				cssClass:'picker-highlight',
		        title: "请选择始发站",
		        cols:[{
		            textAlign:'center',
		            values:depatureIds,
		            displayValues:depatureNames
		        }],
		        onChange:function(p, v, dv) {
		            changeDepatureId = v;
		            changeDepatureName = dv;
		        },
		        onClose:function() {
		        	if($departureHidden.val() != changeDepatureId){
		        		$departure.text(changeDepatureName);
		            	$departureHidden.val(changeDepatureId);
		            	resetDestination(changeDepatureId);
		            	resetFleet();
		            	resetPrice();
		        	}
		        }
	        });
		});
		
		//联动终点站
		var resetDestination = function(departureId){
			_uri = window.BASEPATH + 'site/query/destinations/' + departureId;
			changeDestinationName = '终点站';
			changeDestinationId = -1;
			$destination.text('终点站');
           	$destinationHidden.val(-1);
			$.get(_uri, null, function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				var destinationNames = [];
				var destinationIds = [];
				if(data && data.length>0){
					for(var i=0; i<data.length; i++){
						destinationNames.push(data[i].name);
						destinationIds.push(data[i].id);
					}
				}
				if($destination.data('picker')){
					$destination.picker('destroy');
				}
				$destination.picker({
					cssClass:'picker-highlight',
			        title: "请选择终点站",
			        cols:[{
			            textAlign:'center',
			            values:destinationIds,
			            displayValues:destinationNames
			        }],
			        onChange:function(p, v, dv) {
			            changeDestinationId = v;
			            changeDestinationName = dv;
			        },
			        onClose:function() {
			        	if($destinationHidden.val() != changeDestinationId){
			        		$destination.text(changeDestinationName);
			            	$destinationHidden.val(changeDestinationId);
			            	selectFleet();
			        	}
			        }
		        });
			});
		};
		
		//选择车队
		var $fleetPopup = $('#fleet-popup');
		var $fleet = $('#fleet');
		var $fleetHidden = $fleet.next();
		var $carpoolPrice = $('#carpool-price');
		var $charterPrice = $('#charter-price');
		var selectFleet = function(){
			
			var departureId = $departureHidden.val();
			var destinationId = $destinationHidden.val();
			
			//合法性校验
			if(departureId == -1){
				$.toptip('请先选择始发站！', 'warning');
				return;
			}else if(destinationId == -1){
				$.toptip('请先选择终点站！', 'warning');
				return;
			}
			
			$.showLoading();
			$fleetPopup.popup();
        	_uri = window.BASEPATH + 'route/query/route/details/' + departureId + '/' + destinationId;
        	$.get(_uri, null, function(data){
        		$.hideLoading();
				data = parseAjaxResult(data);
				if(data === -1) return;
				var $fleetList = $('#fleet-list').empty();
				if(data && data.length>0){
					for(var i=0; i<data.length; i++){
						var $tr = $('<tr><td class="center"><input name="fleet-id" type="radio" value="'+data[i].id+'"/></td><td>'+data[i].fleetName+'</td><td class="center">'+data[i].carpoolPrice+'</td><td class="center">'+data[i].charterPrice+'</td></tr>').data('route', data[i]);
						$fleetList.append($tr);
						if($fleetHidden.val() == data[i].id){
							$tr.find('input[type=radio]')[0].checked = true;
						}
					}
					$fleetList.find('input[type=radio]').iCheck({radioClass:'iradio_flat-blue'});
				}else{
					$fleetList.append('<tr><td colspan="4">抱歉，系统中暂无数据！</td></tr>');
				}
			});
		};
		
		//清除车队
		var resetFleet = function(){
			$fleet.data('route', null).val('');
			$fleetHidden.val(-1);
		};
		
		//选择车队
		$('#fleet-list').on('click.tr.click', 'tr', function(){
			var $tr = $(this);
			var route = $tr.data('route');
			
			//选择车队
			$fleet.data('route', route).val('车队：' + route.fleetName);
			$fleetHidden.val(route.id);
			
			//设置价格
			$carpoolPrice.text('拼车：' + route.carpoolPrice + '元').removeClass('no-setted').addClass('selected');
			$charterPrice.text('包车：' + route.charterPrice + '元').removeClass('no-setted').removeClass('selected');
			
			//关闭popup
			$.closePopup();
		});
		
		//清除价格
		var resetPrice = function(){
			$carpoolPrice.text('未设置').removeClass('selected').addClass('no-setted');
			$charterPrice.text('未设置').removeClass('selected').addClass('no-setted');
		};
		
		//选择出行模式（拼车[|包车]）
		$('.price').on('click', function(){
			var $price = $(this);
			if($price.is('.no-setted')) return;
			if($price.is('#carpool-price')){
				$charterPrice.removeClass('selected');
			}else if($price.is('#charter-price')){
				$carpoolPrice.removeClass('selected');
			}
			$price.addClass('selected');
		});
		
		//选择车队
		$('#fleet').on('click', function(){
			selectFleet();
		});
		
		//关闭车队选择
		$('#fleet-popup #go-back').on('click', function(){
			$.closePopup();
		});
		
		//重置日期
		var $departureDate = $('#departure-date');
		var $departureDateText = $('#departure-date-text');
		var resetDate = function(dateStr){
			$departureDate.val(dateStr);
			$departureDateText.text(dateStr.split('年')[1]);
		};  
		
		//日期格式
		var dateFormat = 'yyyy年MM月dd日';
		var calendarValueFormat = 'yyyy-MM-dd';
		var today = new Date();
		var dateEarly_1 = today.dateAdd(Date.prototype.D, -1);
		var dateLater_7 = today.dateAdd(Date.prototype.D, 7);
		var value = today.format(calendarValueFormat);
		var minDate = dateEarly_1.format(calendarValueFormat);
		var maxDate = dateLater_7.format(calendarValueFormat);
		
		//初始化日期
		resetDate(today.format(dateFormat));
		
		//出发日期
		$('#departure-date').calendar({
			inputReadOnly:true,
			value:[value],
        	dateFormat:'yyyy年mm月dd日',
        	minDate:minDate,
        	maxDate:maxDate,
        	onChange:function(p, values, displayValues){
        		resetDate(values[0]);
        	}
		});
		
		//重置时间
		var $departureTime = $('#departure-time');
		var $departureTimeText = $('#departure-time-text');
		var resetTime = function(h, m){
			var time = h + ':' + m;
			$departureTime.val(time);
			$departureTimeText.text(time);
		};  
		
		var hours = Date.prototype.formatNumber(today.getHours());
		var minutes = Date.prototype.formatNumber(today.getMinutes());
		
		//初始化时间
		resetTime(hours, minutes);
		
		//出发时间
		var generateTime = function (max) {
			var timeArr = [];
			for(var i=0; i<max; i++){
				timeArr.push(Date.prototype.formatNumber(i));
			}
			return timeArr;
	    }
		
		$('#departure-time').picker({
			cssClass:'picker-highlight',
	        title: "请选择出行时间",
	        cols:[
		    	{values:generateTime(24)}, 
		    	{content:":", divider:true},
		    	{values:generateTime(60)}
		    ],
		    value:[hours, minutes],
	        onClose:function(p) {
	        	resetTime(p.value[0], p.value[1]);
	        }
		});
		
		//提交订单
		var $orderPopup = $('#order-popup');
		var $orderCommit = $('#order-commit');
		
		$('#save-order').on('click', function(){
			
			var route = $fleet.data('route');
			
			if(!route){
				$.toptip('您还没有选择路线！', 'warning');
				return;
			}
			
			//始发站
			var departureName = route.departureName;
			var departureId = route.destinationId;
			
			//终点站
			var destinationName = route.destinationName;
			var destinationId = route.destinationId;
			
			//出发日期
			var date = $departureDate.val();
			var time = $departureTime.val();
			
			//车队
			var fleetId = route.fleetId;
			var fleetName = route.fleetName;
			
			//路线
			var routeId = route.id;
			var routeUuid = route.uuid;
			
			//人数
			var passengerNum = parseInt($('#passenger-num>.number').text());
			
			//价格
			var priceType = $('.price.selected').next().val();
			var price = 0;
			if(priceType == 0){
				//拼车
				priceType = '拼车';
				price = parseInt(route.carpoolPrice) * passengerNum;
			}else if(priceType == 1){
				//包车
				priceType = '包车';
				price = parseInt(route.charterPrice);
			}
			
			//加钱
			var addMoney = parseInt($('#add-money').val());
			addMoney = isNaN(addMoney)?0:addMoney;
			
			//备注
			var remark = $('#remark').val();
			
			//创建订单数据
			var order = {
				departureId:departureId,
				departureName:departureName,
				destinationId:destinationId,
				destinationName:destinationName,
				date:date,
				time:time,
				fleetId:fleetId,
				fleetName:fleetName,
				routeId:routeId,
				routeUuid:routeUuid,
				passengerNum:passengerNum,
				priceType:priceType,
				price:price,
				addMoney:addMoney,
				remark:remark
			};
			
			//设置订单详情
			setOrderDetails(order);
			
			//弹出订单详情
			$orderPopup.popup();
			
		});
		
		//设置订单详情
		var setOrderDetails = function(order){
			//绑定数据
			$orderCommit.data('order', order);
			
			var $values = $orderPopup.find('.weui-form-preview__value');
			$($values[0]).text('¥' + order.price);
			$($values[1]).text(order.departureName + ' - ' + order.destinationName);
			$($values[2]).text(order.date + ' ' + order.time);
			$($values[3]).text(order.priceType);
			$($values[4]).text(order.passengerNum);
			$($values[5]).text('¥' + order.addMoney);
			$($values[6]).text(order.remark);
		};
		
		//清除订单详情
		var resetOrderDetails = function(){
			//绑定数据
			$orderCommit.data('order', null);
			
			var $values = $orderPopup.find('.weui-form-preview__value');
			$($values[0]).text('');
			$($values[1]).text('');
			$($values[2]).text('');
			$($values[3]).text('');
			$($values[4]).text('');
			$($values[5]).text('');
			$($values[6]).text('');
		}
		
		//关闭订单详情确认
		$('#order-cancel').on('click', function(){
			resetOrderDetails();
			$.closePopup();
		});
		
		//提交订单
		$('#order-commit').on('click', function(){
			var $button = $(this);
			var order = $button.data('order');
			
			var $form = $('#order-form');
			$form.find('input').each(function(){
				var $input = $(this);
				$input.val(order[$input.attr('name')]);
			});
			
			$form[0].submit();
		});
		
	});
</script>

</html>