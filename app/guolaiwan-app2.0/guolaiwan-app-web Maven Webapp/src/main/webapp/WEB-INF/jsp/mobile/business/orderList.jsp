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

<title>订单管理</title>

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
     #saveReject:hover{
    box-shadow:5px 5px 10px #8E8F8F;
    
    }


</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<jsp:include page="../../../mobile/commons/jsp/public.jsp"></jsp:include>

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
		
	  $(document).on('click','.product',function(){
	       var codes=this.id.split('-');
	       location.href=window.BASEPATH +"business/gotopayment?merchantId=${merchantId}&orderId="+codes[1];
	  });
	  
	    $(document).on('click','.table',function(){
	       var codes=this.id.split('-');  	      
	      location.href=window.BASEPATH +"reservetable/diningtable/tableSuccess?merchantId="+codes[2]+"&orderId="+codes[1];
	  });
	   getOrder(2);
	  	  
     function getOrder(type){
          
          var _uriorder = window.BASEPATH + 'business/order/get?userId=${userId}&type='+type+'&uType=USER&ifpay=true&merchantId=${merchantId}';
          $.get(_uriorder, null, function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			var html=[];
			
			html.push('<div class="weui-panel__bd">');
			if(data && data.length>0){
			    
				for(var i=0; i<data.length; i++){
				   
				    html.push('<div class="weui-cells__title">'+data[i].payDate);
				    var addStr="";
				    if(data[i].isBundle==1){
				       addStr="bundle-";
				    }
				    
				    
				    for(var j=0;j<data[i].orderList.length;j++){
				        if(j==0){
				            var bookDate=data[i].orderList[j].orderBookDate.replace('年','/').replace('月','/').replace('日','');
					        if('2019/04/15 00:00:00'<=bookDate&&bookDate<='2019/04/19 23:59:59'){
					            //这里加各种各样的奇葩限制。
					        }else{
					           if(type==2){
								    
							    //添加退款限制理由 张羽 4/28
							    if(data[i].orderList[j].productIsRefund!="1"){
							    html.push('<a style="color:black;font-size:12px;margin-left:15px" id="relay-'+addStr+data[i].orderId+'" class="icon-reply" href="javascript:void(0)">&nbsp;&nbsp;申请退款</a>')
							    }else{
							    	html.push('<a style="font-size:12px;margin-left:15px" href="javascript:void(0)"></a>')
							    }
							   }
					        }
				        }
				        
				        html.push('</div>');
				        html.push('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg product" id="pro-'+data[i].orderList[j].id+'">');
					    html.push('<div class="weui-media-box__hd">');
					    html.push('<img style="width:60px;height:60px;" class="weui-media-box__thumb" src="'+data[i].orderList[j].productPic+'">');
					    html.push('</div>');
					    html.push('<div class="weui-media-box__bd">');
					    html.push('<h4 class="weui-media-box__title" style="font-size:12px;">'+data[i].orderList[j].productName+'&nbsp;&nbsp;&nbsp;&nbsp;￥'+data[i].orderList[j].productPrice+'x'+data[i].orderList[j].productNum+'</h4>');
					    html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">下单时间'+data[i].orderList[j].createDate+'</p>');
					    html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">验单时间'+data[i].orderList[j].ydDate+'</p>');
					    html.push('</div>');
					    //退款限制 张羽 4/28
					    if(data[i].orderList[j].productIsRefund=="1"){
					    	html.push('<a style="font-size:12px;margin-left:15px" href="javascript:void(0)">活动商品不支持退款，谢谢您的支持</a>')
					    }
					    if(type==3){
						    html.push('<a style="color:black;font-size:12px;margin-left:45px" id="ok-'+data[i].orderList[j].id+'" class="icon-ok" href="javascript:void(0)">&nbsp;&nbsp;确认收货</a>')
						    html.push('<a style="color:black;font-size:12px;margin-left:95px" id="ok-'+data[i].orderList[j].id+'" class="icon-ok-logistics" href="javascript:void(0)">&nbsp;&nbsp;查看物流</a>')
						 
						}
					    if(type==5){
					       html.push('<a style="font-size:12px;margin-left:15px" href="javascript:void(0)">七个工作日内到帐，注意查收</a>')
					    }
					    //4.24 新增退款原因订单中展示
					    if(type==9){
					       html.push('<a style="font-size:12px;margin-left:15px" href="javascript:void(0)">未退款原因：'+data[i].orderList[j].justification+'</a>')
					    }
					    if(type==6||type==8){
					    	if(data[i].orderList[j].productId!=0){
					    		html.push('<a style="font-size:12px;margin-left:15px" id="edit-'+data[i].orderList[j].id+'" class="icon-edit" href="javascript:void(0)">&nbsp;&nbsp;添加评论</a>')
					    	}
					    }
					    if(type==7)
					    {
					       html.push('<a style="font-size:12px;margin-left:15px">&nbsp;&nbsp;已评论</a>')
					    }
					    html.push('</a>');
				    } 
				}
			}else{
			 	html.push("<div style='width:100%;margin-top:10px;font-size:12px;text-align:center'>暂无数据</div>");
			}
			html.push('</div>');
			$('#tab'+type).children().remove();
			$('#tab'+type).append(html.join(''));
			getTableOrder(2);	
		});
      }
      
      	  // 美食订单
	    function getTableOrder(type){	
	   	  
          var _uriorder = window.BASEPATH + 'business/tableOrder/get?userId=${userId}&type='+type+'&uType=USER&ifpay=true&merchantId=${merchantId}';
          $.get(_uriorder, null, function(data){
		    var tableslist = data.tableslist;
					
			if(tableslist.length>0){
			     var html=[];
				//订桌订单 			   			    
			    html.push('<div class="weui-panel__bd">');
				for(var i=0; i<tableslist.length; i++){					      					     				      						 
					 for(var j = 0; j<tableslist[i].table_order.length; j++ ){						 			       					   
				        var  table_order = tableslist[i].table_order;
				        var  table = tableslist[i].table;
				        html.push('<div class="weui-cells__title">'+tableslist[i].merchant.shopName);	
				      		    
	                    if(type==2){
						html.push('<a style="color:black;font-size:12px;margin-left:15px" id="relay-'+table_order[i].orderId+'" class="icon-reply" href="javascript:void(0)">&nbsp;&nbsp;申请退款</a>')	   
						html.push('<a style="font-size:12px;margin-left:15px" href="javascript:void(0)"></a>')							    
						 } 
					    html.push('</div>');
				        html.push('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg table" id="pro-'+table_order[j].id+'-'+table_order[j].merchantId+'">');
					    html.push('<div class="weui-media-box__hd">');
					    html.push('<img style="width:60px;height:60px;" class="weui-media-box__thumb" src="http://www.guolaiwan.net/file/'+table[j].detailsImg+'">');
					    html.push('</div>');
					    html.push('<div class="weui-media-box__bd">');
					    html.push('<h4 class="weui-media-box__title" style="font-size:12px;">'+table[j].tablename+'&nbsp;&nbsp;&nbsp;&nbsp;￥'+parseInt(table_order[j].dishMoney)/100+'</h4>');
					    html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">下单时间'+table_order[j].tableDate.replace('-','年').replace('-','月')+"日"+'</p>');
					    html.push('</div>');				  
					    html.push('</a>');				    
				    }
				}				
				html.push('</div>');	
					   
			   $('#tab'+type).append(html.join(''));
			}			
			//未订桌订单展示
			var disList = data.disList;
	
		   if(disList.length>0){
			     var html=[];
					   			    
			    html.push('<div class="weui-panel__bd">');
				for(var i=0; i<disList.length; i++){					      					     				      						 					 			          					   
				        var  table_orders = disList[i].table_orders;				     
				        html.push('<div class="weui-cells__title">'+disList[i].merchant.shopName);	
	                    if(type==2){
						html.push('<a style="color:black;font-size:12px;margin-left:15px" id="relay-'+table_orders[0].orderId+'" class="icon-reply" href="javascript:void(0)">&nbsp;&nbsp;申请退款</a>')	   
						html.push('<a style="font-size:12px;margin-left:15px" href="javascript:void(0)"></a>')							    
						 } 
					    html.push('</div>');
				        html.push('<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg table" id="pro-'+table_orders[0].id+'-'+table_orders[0].merchantId+'">');
					    html.push('<div class="weui-media-box__hd">');
					    html.push('<img style="width:60px;height:60px;" class="weui-media-box__thumb" src="http://www.guolaiwan.net/file/'+tableslist[i].merchant.shopPic+'">');
					    html.push('</div>');
					    html.push('<div class="weui-media-box__bd">');
					    html.push('<h4 class="weui-media-box__title" style="font-size:12px;">未订桌</h4>');
					    html.push('<p class="weui-media-box__desc" style="margin-top:4px;font-size:12px;">下单时间'+table_orders[0].tableDate.replace('-','年').replace('-','月')+"日"+'</p>');
					    html.push('</div>');				  
					    html.push('</a>');				    
				    
				}
				html.push('</div>');
			   
			   $('#tab'+type).append(html.join(''));
			} 		
		});
		   
	   }
      
      
      $(document).on('click','.icon-reply',function(){
          var ids=this.id.split('-');
          var newIds=[];
          for(var i=1;i<ids.length;i++){
             newIds.push(ids[i]);
          }
          commOrderId=newIds.join('-');
          $('#rejectContent').popup();
          $('.weui-tab').hide();
          
      });
      
      $(document).on('click','#saveReject',function(){
          if($('#reasonContent').val()==''){
              $.toast("请填写退款原因", "forbidden");
              return false;
          }
          var _urichangeorder = window.BASEPATH + 'pubnum/applyReject';
          var params={};
          params.orderId=commOrderId;
          params.reason=$('#reasonContent').val();
          $.post(_urichangeorder, $.toJSON(params), function(data){
			data = parseAjaxResult(data);
			if(data === -1) return;
			if(data){
			   $.toast("申请退款中，等待商家确认");
			   $('#reasonContent').val('');
			   $('.weui-tab').show();
               $.closePopup();
			   getOrder(2);
			}
		  });
      });
      
      
      
      //查询物流的jqurey
      $(document).on('click','.icon-ok-logistics',function(){
	       var ids=this.id.split('-');
	       location.href=window.BASEPATH + 'pubnum/logistics/particulars?orderId='+ids[1];
	    
	  });
      
      
      
      $(document).on('click','.icon-ok',function(){
          var ids=this.id.split('-');
          changeOrderStatus(ids[1],'RECEIPT',3);
      });
      
      
      var commOrderId=0;
      $(document).on('click','.icon-edit',function(){
          var ids=this.id.split('-');
          commOrderId=ids[1];
          $('#commentContent').popup();
          $('.weui-tab').hide();
      });
      
	    $(document).on('click','#save',function(){
		    var content=$('#comContent').val();
		    var params={};
		    params.userId=${userId};
		    params.orderId=commOrderId;
		    params.content=content;
		    var _uriaddComment = window.BASEPATH + 'pubnum/addComment';
	          $.post(_uriaddComment, $.toJSON(params), function(data){
				data = parseAjaxResult(data);
				if(data === -1) return;
				if(data){
				   if(data.status==403){
				      $.toast("您已经评论过了","forbidden");
				      return;
				   }
				   $.toast("评论成功");
				   $('#comContent').val('');
                   $('.weui-tab').show();
                   $.closePopup();
				}
				
				
			});
		});
		
	  $(document).on('click','.weui-navbar__item',function(){
	      var ids=this.id.split('-');
	      getOrder(ids[1]);
	  });
		
	
	
	
			function changeOrderStatus(orderId,status,type){
			   var _urichangeorder = window.BASEPATH + 'pubnum/changeOrderStatus?orderId='+orderId+'&status='+status;
		          $.get(_urichangeorder, null, function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;
					if(data){
					   getOrder(type);
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
				<div class="header-content">订单管理</div>
			</div>
		</div>
		<div class="content">
			<!-- 1.未支付;2.已支付;3.已发货;4.待退款;5.已退款;6.已收货;7.已评价 -->
			<div class="weui-tab">
			  <div class="weui-navbar">
			    <!-- <a id="tab-1" onclick="return false" class="weui-navbar__item weui-bar__item--on" href="#tab1">
			      未支付
			    </a> -->
			    <a id="tab-2" onclick="return false" class="weui-navbar__item weui-bar__item--on" href="#tab2">
			      已支付
			    </a>
			    <a id="tab-3" onclick="return false" class="weui-navbar__item" href="#tab3">
			      已发货
			    </a>
			     <a id="tab-4" onclick="return false" class="weui-navbar__item" href="#tab4">
			      待退款
			    </a>
			    <a id="tab-5" onclick="return false" class="weui-navbar__item" href="#tab5">
			      已退款
			    </a>
			    <a id="tab-6" onclick="return false" class="weui-navbar__item" href="#tab6">
			      已收货
			    </a>
			    <a id="tab-8" onclick="return false" class="weui-navbar__item" href="#tab8">
			      已验单
			    </a>
			    <a id="tab-9" onclick="return false" class="weui-navbar__item" href="#tab9">
			      未退款 
			    </a> 
			   
			  </div>
			  <div class="weui-tab__bd" style="padding-bottom:50px">
			    <div id="tab1" class="weui-tab__bd-item ">
			      
				   
			    </div>
			    <div id="tab2" class="weui-tab__bd-item weui-tab__bd-item--active">
			      
			    </div>
			    <div id="tab3" class="weui-tab__bd-item">
			      
			    </div>
			    <div id="tab4" class="weui-tab__bd-item">
			      
			    </div>
			    <div id="tab5" class="weui-tab__bd-item">
			      
			    </div>
			    <div id="tab6" class="weui-tab__bd-item">
			      
			    </div>
			    <div id="tab8" class="weui-tab__bd-item">
			      
			    </div>
			    <div id="tab9" class="weui-tab__bd-item">
			      
			    </div>
		
			  </div>
			</div>
			
			<div id="commentContent" class="weui-popup__container">
			  <div class="weui-popup__overlay"></div>
			  <div class="weui-popup__modal">
				<div class="weui-cells__title">请输入评论内容</div>
				<div class="weui-cells">
				  <div class="weui-cell">
				    <div class="weui-cell__bd">
				       <textarea id="comContent" class="weui-textarea" placeholder="" rows="3"></textarea>
				    </div>
				  </div>
				</div>
				<a id="save" style="width:96%;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">保存</a>
			  </div>
			  
			</div>
			
			<div id="rejectContent" class="weui-popup__container">
			  <div class="weui-popup__overlay"></div>
			  <div class="weui-popup__modal">
				<div class="weui-cells__title">请输入退款理由</div>
				<div class="weui-cells">
				  <div class="weui-cell">
				    <div class="weui-cell__bd">
				       <textarea id="reasonContent" style="border:1px solid #fbfbfb;" class="weui-textarea" placeholder="" rows="3"></textarea>
				    </div>
				  </div>
				</div>
				<a id="saveReject" style="width:96%;margin-left:2%;background-color:#18b4ed;height:40px;line-height:40px;" href="javascript:;" class="weui-btn weui-btn_primary">保存</a>
			  </div>
			  
			</div>
			
			
		</div>
	</div>
</body>


</html>