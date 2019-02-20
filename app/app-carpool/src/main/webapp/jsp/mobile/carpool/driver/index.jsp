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

	<title>司机接单</title>

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
        
        .picker-highlight{box-shadow:0 -1px 10px rgba(0,0,0,.3);}
        .picker-highlight .toolbar, 
        .picker-highlight .picker-items{background:#fff;}
        .picker-highlight .toolbar .title{color:#000;}
        
        .weui-picker-calendar{box-shadow:0 -1px 10px rgba(0,0,0,.3) !important;}
        .weui-picker-calendar .toolbar{background:#fff !important;}
        .weui-picker-calendar .picker-calendar-week-days{background:#fff !important;}
        
        .my-popup .weui-popup__modal{background:#fbfbfb;}
        .my-popup .header{border-bottom:0;}
        .my-popup .content{padding:0; margin-top:5px;}
        .my-popup.with-poll-to-refresh .header{position:relative; z-index:1;}
        .my-popup.with-poll-to-refresh .content{position:relative; bottom:40px; z-index:0;}
        .my-popup.with-poll-to-refresh .content-bottom{margin-top:10px;}
        
        .my-popup .weui-popup__modal{background:#fbfbfb;}
    	.my-popup .go-back>span{position:relative; top:2px;}
    	.my-popup table{width:100%; font-size:14px; border-collapse:collapse; background-color:#fff;}
    	.my-popup table thead th,
    	.my-popup table tbody td{border:1px solid #f1f1f1;}
    	.my-popup table tbody td.center{text-align:center;}
    	.my-popup table tbody td.left{text-align:left;}
    	.my-popup table tbody td.right{text-align:right;}
    	.my-popup table th,
    	.my-popup table td{padding:10px 2px;}
    	.my-popup table tr:first-child>th,
        .my-popup table tr:first-child>td{border-top:0;}
        .my-popup table tr:last-child>td{border-bottom:0;}
        .my-popup table tr>th:last-child,
        .my-popup table tr>td:last-child{border-right:0;}
        
        /* 页面样式 */
        .header{height:40px; line-height:40px; background-color:#18b4ed; color:#fff; border-bottom:1px solid #bababa;}
        .header .link-left{margin-left:10px; margin-right:10px; position:relative; z-index:1;}
        .header-content{height:100%; width:100%; position:absolute; left:0; top:0; padding-left:40px; padding-right:40px; text-align:center; z-index:0;}
        
        .content{padding:5px;}
        .cards{box-shadow: 0 1px 2px rgba(0,0,0,.3); background-color:#fff;}
        .weui-btn{color:#fff !important;}
        .weui-btn:after{border-radius:0 !important;}
        
        .driver-direction{margin-bottom:5px;}
        .driver-direction>div{height:50px; line-height:45px; position:relative; padding:5px;}
        .driver-direction>div:first-child{padding-bottom:0; border-bottom:1px solid #f1f1f1;}
        .driver-direction>div:last-child{padding-top:0;}
        
        .driver-direction button{position:absolute; right:5px; top:5px;}
        
        .seat-grid-wrapper{margin-bottom:5px;}
        .seat-grid-wrapper table{width:100%; height:350px; border-collapse:collapse;}
        .seat-grid-wrapper table td{border:1px solid #f1f1f1; background-color:#f9f9f9; position:relative; text-align:center; vertical-align:middle; width:50%;}
        .seat-grid-wrapper table td:active{opacity:.6; background-color:#FAFAFA;}
        .seat-grid-wrapper table td>div{width:100%; height:100%; display:table; background-color:#fff;}
        .seat-grid-wrapper table td:active>div{background-color:transparent;}
        .seat-grid-wrapper table td>div>div{width:100%; height:100%; display:table-cell; vertical-align:middle;}
        .seat-grid-wrapper table td ul{text-align:left; list-style:none;}
        .seat-grid-wrapper table td ul li{padding-top:3px; padding:0 2px;}
        .seat-grid-wrapper table td .detail-icon{display:inline-block; width:20px; text-align:center; margin-right:5px;}
        .seat-grid-wrapper table td .detail-message{font-size:14px;}
        .seat-grid-wrapper table td>span.not-allowed{color:#FB6155;}
        .seat-grid-wrapper table tr:first-child>td{border-top:0;}
        .seat-grid-wrapper table tr:last-child>td{border-bottom:0;}
        .seat-grid-wrapper table tr>td:last-child{border-right:0;}
        
        .btn-wrapper{padding:10px 30px;}
        
        .weui-btn_warn.btn-custom:active,
        .weui-btn_warn.btn-custom{background:#FB6155 !important;}
        .weui-btn_primary.btn-custom:active,
        .weui-btn_primary.btn-custom{background-color:#18b4ed !important;}
        .btn-custom{height:40px; line-height:40px; font-size:16px; border-radius:0;}
        .weui-btn_mini.btn-custom{border-radius:0; height:40px; line-height:40px; font-size:14px; padding:0 .8em;}
    	.btn-custom:active{opacity:0.7 !important;}
    	
    	.content-bottom{border-bottom:1px solid #f1f1f1; border-top:1px solid #f1f1f1;}
    	
    	.operation{float:left; width:100%;}
    	.operation>div{display:none;}
    	.operation>div.active{display:block;}
    	.operation>div.half{width:50%; height:100%; float:left;}
    	.operation>div.half:first-child{padding-right:15px;}
    	.operation>div.half:last-child{padding-left:15px;}
    </style>
	
</head>

<body>
	<!-- data -->
	<input type="hidden" id="mobile" value="${mobile}"/>

	<!-- 主页 -->
	<div id="page">
		<div class="header">
			<div class="wrapper">
				<div class="header-content">约吧拼车</div>
			</div>
		</div>
		<div class="content">
			<div class="cards driver-direction">
				<div>
					<span>状态：<span>${carStatusName}</span>（<span id="seat-status">${seatStatus}</span>）</span> 
					<button class="weui-btn weui-btn_mini weui-btn_warn btn-custom" id="system-orders-show">可接订单</button>
				</div>
				<div>
					<span>路线：${route}</span> 
					<button class="weui-btn weui-btn_mini weui-btn_warn btn-custom" id="route-switch">切换路线</button>
				</div>
			</div>
			<div class="cards seat-grid-wrapper">
				<table>
					<tbody id="seat-grid"></tbody>
				</table>
			</div>
			<div class="cards btn-wrapper operation" id="operation">
				<div class="before-start-off active">
					<button class="weui-btn weui-btn_primary btn-custom" id="start-off">
						<form action="<%=basePath%>driver/start/off/${mobile}" method="POST"></form>
						出发
					</button>
				</div>
				<div class="after-start-off half">
					<button class="weui-btn weui-btn_primary btn-custom" id="pause">
						<form action="<%=basePath%>driver/pause/${mobile}" method="POST"></form>
						修改接单
					</button>
				</div>
				<div class="after-start-off half">
					<button class="weui-btn weui-btn_primary btn-custom" id="complete">
						<form action="<%=basePath%>driver/complete/${mobile}" method="POST"></form>
						结束行程
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- popups -->
	
	<!-- 可接订单查询 -->
	<div id="system-orders-popup" class="weui-popup__container my-popup with-poll-to-refresh">
	    <div class="weui-popup__overlay"></div>
	    <div class="weui-popup__modal">
	    	<div class="header">
				<div class="wrapper">
					<a class="link-left go-back"><span class="icon-angle-left icon-2x"></span></a>
					<div class="header-content">可接订单</div>
				</div>
			</div>
			<div class="content" id="system-orders-content">
				<div class="weui-pull-to-refresh__layer">
				    <div class='weui-pull-to-refresh__arrow'></div>
				    <div class='weui-pull-to-refresh__preloader'></div>
				    <div class="down">下拉刷新</div>
				    <div class="up">释放刷新</div>
				    <div class="refresh">正在刷新</div>
			    </div>
			    <div class="content-bottom">
			    	<table>
			    		<thead>
			    			<tr><th>出行时间</th><th style="width:50px;">加钱</th><th style="width:50px;">人数</th><th style="width:70px;">出行方式</th><th style="width:60px;"></th></tr>
			    		</thead>
			    		<tbody id="system-orders-table"></tbody>
			    	</table>
			    </div>
			</div>
	    </div>
	</div>
	
	<!-- 接单详情 -->
	<div id="order-details-popup" class="weui-popup__container my-popup">
	    <div class="weui-popup__overlay"></div>
	    <div class="weui-popup__modal">
	    	<header style="padding:20px 0;">
		        <h1 style="text-align:center; font-size:26px; color:#3cc51f; font-weight:400; margin:0 15%;">系统订单</h1>
		    </header>
	    	<div class="weui-form-preview">
				<div class="weui-form-preview__bd">
					<div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">订单编号</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				    <div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">订单金额</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				    <div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">加钱</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				    <div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">打车方式</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				    <div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">顾客电话</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				    <div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">出发时间</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				    <div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">人数</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				    <div class="weui-form-preview__item">
				        <label class="weui-form-preview__label">顾客留言</label>
				        <span class="weui-form-preview__value"></span>
				    </div>
				</div>
				<div class="weui-form-preview__ft">
				    <button class="weui-form-preview__btn weui-form-preview__btn_default go-back">关闭详情</button>
				    <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" id="order-cancel">取消接单</button>
				</div>
			</div>
	    </div>
	</div>
	
</body>

<!-- 公共脚本引入 -->
<jsp:include page="../../../../mobile/commons/jsp/script.jsp"></jsp:include>
<script type="text/javascript" src="<%=basePath %>web/lib/frame/jQuery/jquery.json.js"></script>
<script type="text/javascript">
	$(function(){
		
		//全局变量
		var mobile = $('#mobile').val();
		var toastTime = 1000;
		var _uri = '';
		var carStatus = '${carStatus}';
		
		//消息通知连接
		webSocket = new WebSocket(window.WSBASEPATH + 'driver/connect/tool');

        webSocket.onerror = function(event){
            console.log('发生错误：');
            console.log(event.data);
        };

        webSocket.onopen = function(event){
            webSocket.send($.toJSON({
                cmdname:'init',
                mobile:mobile
            }));
            console.log('建立连接');
        };

        webSocket.onmessage = function(event){
        	try{
        		var message = $.parseJSON(event.data);
        		notifyRouter[message.cmdname].apply(message, []);
        	}catch(e){
        		console.log(event.data);
        	}
        }
        
        //新订单提醒
        var newOrder = function(){
        	var tpl = '时间：' + formatTravelTime(this.travelTime) + 
        			  '<br/>加钱：¥' + this.addMoney + 
        			  '<br/>留言：' + this.remark;
    		var orderId = this.orderId;
        	$.modal({
                title:"有新订单啦！",
                text:tpl,
                buttons: [
					{text:"忽略", className:"default"},
                  	{text:"接单", onClick:function(){takingSystemOrder(orderId)}},
                  	
                ]
            });
        	$('.weui-dialog__bd').css('text-align', 'left');
        };
        
        //用户撤单
        var visitorCancelOrder = function(){
        	var message = '订单：' + this.orderUuid + '被用户取消了！'
        	$.alert(message, '', function() {
        		$.showLoading();
       		  	_uri = window.BASEPATH + 'seat/info/query/seats/' + mobile;
       		  	$.get(_uri, null, function(data){
	       		  	$.hideLoading();
					data = parseAjaxResult(data);
					if(data === -1) return;
					refreshSeatGrid(data);
       		  	});
       		});
        };
        
        //管理员派单
        var adminDistributeSystemOrder = function(){
        	var message = '系统为您派单：' + this.orderUuid;
        	$.alert(message, '', function() {
        		$.showLoading();
       		  	_uri = window.BASEPATH + 'seat/info/query/seats/' + mobile;
       		  	$.get(_uri, null, function(data){
	       		  	$.hideLoading();
					data = parseAjaxResult(data);
					if(data === -1) return;
					refreshSeatGrid(data);
       		  	});
       		});
        }
        
        //消息通知路由
        var notifyRouter = {
        	'/new/order':newOrder,
        	'/visitor/cancel/order':visitorCancelOrder,
        	'/admin/distribute/system/order':adminDistributeSystemOrder
        };
		
		//解析ajax返回
		var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
		};
		
		//格式化出行时间
		var formatTravelTime = function(travelTime){
			return travelTime.substring(5,17);
		}
		
		//绘制座位网格
		var seatInfos = $.parseJSON('${seatInfos}');
		var drawSeatsGrid = function(seatInfos){
			var $seatGrid = $('#seat-grid');
			var perHeight = (parseFloat($seatGrid[0].clientWidth) / 2) + 'px';
			
			//计算出tr个数
			var rows = 0;
			var isodd = false;
			if(seatInfos.length%2 == 0){
				rows = parseInt(seatInfos.length/2);
			}else{
				rows = parseInt(seatInfos.length/2) + 1;
				isodd = true;
			}
			//绘制元素
			for(var i=0; i<rows; i++){
				var $tr = $('<tr></tr>'); 
				for(var j=i*2; j<i*2+2; j++){
					//绘制表格
					var $td = $('<td></td>').data('serialNum', seatInfos[j].serialNum)
											.data('id', seatInfos[j].id)
											.css('height', perHeight);
					if(j===seatInfos.length-1 && isodd){
						$td.attr('colspan', 2);	
					}
					
					//填入内容
					setContent.apply($td, [seatInfos[j]]);
					
					$tr.append($td);
				}
				$seatGrid.append($tr);
			}
		};
		
		//填装表格内容
		var setContent = function(seatInfo){
			var $td = this.empty()
						  .removeClass('free')
						  .removeClass('system_order')
						  .removeClass('personal_order')
						  .addClass(seatInfo.status.toLowerCase())
						  .data('id', seatInfo.id);
							 
			//绘制表格内容
			var $content = null;
			if(seatInfo.status === 'FREE'){
				$content = '<span class="not-allowed icon-ban-circle icon-4x"></span>';
			}else if(seatInfo.status === 'SYSTEM_ORDER'){
				$content = '<div><div><ul>'+
						       '<li><span class="detail-icon icon-laptop"></span><span class="detail-message">'+seatInfo.priceType+'</span></li>'+
						       '<li><span class="detail-icon icon-mobile-phone icon-large"></span><span class="detail-message">'+seatInfo.mobile+'</span></li>'+
						       '<li><span class="detail-icon icon-calendar" style="font-size:14px;"></span><span class="detail-message">'+formatTravelTime(seatInfo.travelTime)+'</span></li>'+
						       '<li><span class="detail-icon icon-comments-alt"></span><span class="detail-message">'+seatInfo.remark+'</span></li>'+
						   '</ul></div></div>';
			}else if(seatInfo.status === 'PERSONAL_ORDER'){
				$content = '<div><div><ul>'+
						       '<li><span class="detail-icon icon-credit-card" style="font-size:14px;"></span><span class="detail-message">'+seatInfo.priceType+'</span></li>'+
						       '<li><span class="detail-icon icon-mobile-phone icon-large"></span><span class="detail-message">'+seatInfo.mobile+'</span></li>'+
						       '<li><span class="detail-icon icon-calendar" style="font-size:14px;"></span><span class="detail-message">'+formatTravelTime(seatInfo.travelTime)+'</span></li>'+
						       '<li><span class="detail-icon icon-comments-alt"></span><span class="detail-message">'+seatInfo.remark+'</span></li>'+
						   '</ul></div></div>';
			}
			$td.append($content);
		};
		
		drawSeatsGrid(seatInfos);
		
		//系统订单
		$('#system-orders-show').on('click', function(){
			if(carStatus === 'STARTOFF'){
				$.alert('您已经出发，无法再接单！');
				return;
			}
			$('#system-orders-popup').popup();
			$('#system-orders-content').pullToRefresh('triggerPullToRefresh');
		});
		
		//下拉刷新
		$('#system-orders-content').pullToRefresh().on('pull-to-refresh', function(done){
			var self = this
			_uri = window.BASEPATH + 'order/query/system/order/' + mobile;
			$.get(_uri, null, function(data){
				$(self).pullToRefreshDone();
				data = parseAjaxResult(data);
				if(data === -1) return;
				var $table = $('#system-orders-table').empty();
				if(!data || data.length<=0){
					$table.append('<tr><td colspan="4">抱歉，系统中暂无数据！</td></tr>');
				}else{
					for(var i=0; i<data.length; i++){
						var tr = '<tr>'+
								 '<td class="center">'+formatTravelTime(data[i].travelTime)+'</td>'+
								 '<td class="right">¥'+data[i].addMoney+'</td>'+
								 '<td class="right">'+data[i].passengerNum+'</td>'+
								 '<td class="center">'+data[i].priceType+'</td>'+
								 '<td class="center"><button class="weui-btn weui-btn_mini weui-btn_warn btn-custom taking-order" data-id="'+data[i].id+'">接单</button></td></tr>';
						$table.append(tr);
					}
				}
				
			}); 
		});
		
		//系统订单接单
		$('#system-orders-table').on('click', '.taking-order', function(){
			var $button = $(this);
			var orderId = $button.data('id');
			takingSystemOrder(orderId);
		});
		
		//接系统订单
		var takingSystemOrder = function(orderId){
			$.showLoading();
			_uri = window.BASEPATH + 'order/taking/system/order/' + mobile + '/' + orderId;
			$.post(_uri, null, function(data){
				$.hideLoading();
				data = parseAjaxResult(data);
				if(data === -1) return;
				refreshSeatGrid(data);
				$.closePopup();
				$.toast('接单成功');
			});
		};
		
		//更新页面接单状态
		var refreshSeatGrid = function(seatInfos){
			if(!seatInfos || seatInfos.length<=0) return;
			var $tds = $('#seat-grid td');
			for(var i=0; i<seatInfos.length; i++){
				var seatInfo = seatInfos[i];
				$tds.each(function(){
					var $td = $(this);
					if($td.data('id') == seatInfo.id){
						setContent.apply($td, [seatInfo]);
						return false;
					}
				});
			}
			var $frees = $('#seat-grid td.free');
			$('#seat-status').text(($tds.length-$frees.length) + '/' + $tds.length);
		};
		
		//切换路线
		$('#route-switch').on('click', function(){
			var $button = $(this);
			if(carStatus === 'STARTOFF'){
				$.alert('您已出发，无法切换路线！');
				return;
			}
			$.showLoading();
			_uri = window.BASEPATH + 'driver/prev/switch/route/' + mobile;
			$.get(_uri, null, function(data){
				$.hideLoading();
				data = parseAjaxResult(data);
				if(data === -1) return;
				$.confirm('确认要切换到' + data + '吗？', function() {
					$.showLoading();
					_uri = window.BASEPATH + 'driver/switch/route/' + mobile;
					$.post(_uri, null, function(data){
						$.hideLoading();
						data = parseAjaxResult(data);
						if(data === -1) return;
						$button.prev().text('路线：' + data);
						$.toast('切换成功', toastTime);
					});
			    }, function(){
			    	$.toast('取消切换', toastTime, 'cancel');
			    });
			});
		});
		
		//接单详情
		var $orderDetailsPopup = $('#order-details-popup');
		var $orderCancel = $('#order-cancel');
		var showOrderDerails = function(seatInfoId){
			$.showLoading();
			_uri = window.BASEPATH + 'seat/info/query/taked/details/' + seatInfoId;
			$.get(_uri, null, function(data){
				$.hideLoading();
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data.status === 'SYSTEM_ORDER'){
					$orderDetailsPopup.find('h1').text('系统订单');
					$orderDetailsPopup.find('.weui-form-preview__item').first().show();
				}else if(data.status === 'PERSONAL_ORDER'){
					$orderDetailsPopup.find('h1').text('私人订单');
					$orderDetailsPopup.find('.weui-form-preview__item').first().hide();
				}
				
				var $values = $orderDetailsPopup.find('.weui-form-preview__value'); 
				$($values[0]).text(data.orderUuid);
				$($values[1]).text('¥' + data.price);
				$($values[2]).text('¥' + data.addMoney);
				$($values[3]).text(data.priceType);
				$($values[4]).text(data.mobile);
				$($values[5]).text(formatTravelTime(data.travelTime));
				$($values[6]).text(data.passengerNum);
				$($values[7]).text(data.remark);
				$orderCancel.data('id', seatInfoId);
			});
		};
		
		$('#seat-grid').on('click.free.not', 'td.system_order, td.personal_order', function(){
			var $grid = $(this);
			var seatInfoId = $grid.data('id');
			showOrderDerails(seatInfoId);
			$orderDetailsPopup.popup();
		});
		
		//撤销接单
		$('#order-cancel').on('click', function(){
			if(carStatus === 'STARTOFF'){
				$.alert('您已出发，无法取消接单！');
				return;
			}
			var $button = $(this);
			var seatInfoId = $button.data('id');
			$.confirm("确认要取消该订单吗？", function() {
			    $.showLoading();
				_uri = window.BASEPATH + 'seat/info/driver/cancel/order/' + seatInfoId;
				$.post(_uri, null, function(data){
					$.hideLoading();
					$.closePopup();
					data = parseAjaxResult(data);
					if(data === -1) return;
					refreshSeatGrid(data);
				});
			});
		});
		
		//关闭popup
		$('.my-popup .go-back').on('click', function(){
			$.closePopup();
		});
		
		//出发
		$('#start-off').on('click', function(){
			var $form = $(this).find('form');
			$.confirm("您将要开始此次行程？", function() {
				$form[0].submit();
			});
		});
		
		//暂停
		$('#pause').on('click', function(){
			var $form = $(this).find('form');
			$.confirm("您将要暂停此次行程？", function() {
				$form[0].submit();
			});
		});
		
		//完成
		$('#complete').on('click', function(){
			var $form = $(this).find('form');
			$.confirm("行程结束后将无法取消订单，确认操作？", function() {
				$form[0].submit();
			});
		});
		
		//刷新司机按钮状态
		var toggleOperationStatus = function(status){
			var $divs = $('#operation div');
			if(status === 'WAITING'){
				$($divs[0]).addClass('active');
				$($divs[1]).removeClass('active');
				$($divs[2]).removeClass('active');
			}else if(status === 'STARTOFF'){
				$($divs[0]).removeClass('active');
				$($divs[1]).addClass('active');
				$($divs[2]).addClass('active');
			}
		};
		
		//刷新按钮状态
		toggleOperationStatus(carStatus);
		
	});
</script>

</html>
