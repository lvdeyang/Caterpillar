<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String weburl = WXContants.Website;
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
<title>活动购票确认订单</title>
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
	min-height: auto;
	background: #E1E1E1 !important;
	position: relative;
	-webkit-text-size-adjust: none;
	text-decoration: none !important;
}

* {
	box-sizing: border-box;
	list-style: none;
	text-decoration: none;
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
	height: auto;
	width: 100%;
	position: absolute;
	left: 0;
	top: 0;
	padding-left: 40px;
	padding-right: 40px;
	text-align: center;
	z-index: 0;
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

.weui-navbar {
	display: none !important;
}

.inp::-webkit-input-placeholder {
	text-align: center;
}

.listbox li input {
	width: 100%;
	height: 60px;
	margin: 5px auto;
	text-align: right;
	margin-left: 2%;
	padding: 0 10%;
	border-radius: 6px;
	border: none;
	background: #fff;
	outline: none;
	border: 1px solid rgb(230, 230, 230);
}

.fukuan {
	background: -webkit-linear-gradient(left, rgba(254, 187, 56, 1),
		rgba(254, 104, 33, 1)); /* Safari 5.1 - 6 */
}

.homepage_add span {
	border-radius: 10px;
	border: 1px solid #FCB735;
	padding: 3px 20px;
	margin: 20px 0 0 10px;
	display: inline-block;
	color: #FCB735;
}

.homepage_add span:hover {
	background: #FCB735;
	color: #fff;
}

.fuceng {
	position: fixed;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	background-color: rgba(0, 0, 0, 0.6);
	z-index: 10000;
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
	border: 1px solid rgb(230, 230, 230);
}
.demo-box{
	width: 100%;
	height:100%;
	overflow: hidden;
	background: #E1E1E1;
	/* border: 1px solid #E6E8EB; */

}
/*******单独组件样式********/
.calendar-box *{
	box-sizing: border-box;
}
.ht-rili-head{
	overflow: hidden;
}
.ht-rili-querybox{
	overflow: hidden;
	height:80px;
	padding-top:20px;
}
.ht-rili-title{
	padding: 10px;
	display: inline-block;
	max-width: 200px;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	vertical-align: middle;
	margin-left:5%;
	font-weight: bold;
}
.ht-rili-datebox{
	float: right;
	display: inline-block;
	vertical-align: middle;
	padding: 10px;
}
.ht-rili-date{
	text-align: center;
	display: inline-block;
	padding:0 5px;
	font-weight: bold;
	height: 19px;
	line-height: 19px;
	vertical-align: middle;
}
.ht-rili-leftarr{
	display: inline-block;
	width:25px;
	height: 19px;
	background: url("lib/images/left-arr.png") left center no-repeat;
	background-size: contain;
	vertical-align: middle;
	cursor: pointer;
}
.ht-rili-rightarr{
	display: inline-block;
	width:25px;
	height: 19px;
	background: url("lib/images/right-arr.png") right center no-repeat;
	background-size: contain;
	vertical-align: middle;
	cursor: pointer;
}
.ht-rili-th{
	width: 14.25%;
	float: left;
	text-align: center;
	height: 60px;
	line-height: 60px;
	/*background: #E66B14;*/
	color: #000;
}
.ht-rili-td{
	width: 14.25%;
	float: left;
	text-align: center;
	height:60px;
	/*line-height: 50px;*/
	background: #E1E1E1;
	padding-top: 5px;
	/*border-bottom: 1px solid #ddd;
	border-right: 1px solid #ddd;*/
	cursor: pointer;
}
.ht-rili-body{
	overflow: hidden;
}
.ht-rili-day{
	font-family:Arial;
	font-size: 18px;
	font-weight: bold;
	display: inline-block;
	width: 100%;
}
.ht-rili-money{
	font-family:Arial;
	display: inline-block;
	width: 100%;
	font-size: 12px;
	color: #D4585A
}
.ht-rili-td-disabled{
	color: #BFC4CA;
}
.ht-rili-td-active{
	border-radius: 4px;
	background: #80B3E8;
}
</style>

</head>

<!-- 公共脚本引入 -->
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/bootstrap.js" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="lib/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/jss/calendar-pro.css" />
<script src="<%=request.getContextPath()%>/jss/calendar-pro.js"></script>
<script src='https://res.wx.qq.com/open/js/jweixin-1.2.0.js'></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/layui/js/x-layui.js" charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
<link href="<%=request.getContextPath()%>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
	<script>
	(function(){
	
	var calendarDate = {};
	var riliHtml = '';
	calendarDate.today = new Date();
	calendarDate.year = calendarDate.today.getFullYear();//当前年
	calendarDate.month = calendarDate.today.getMonth()+1;//当前月
	calendarDate.date = calendarDate.today.getDate();//当前日
	calendarDate.day = calendarDate.today.getDay();//当前周几
	
	
	//绘制
	function getIndexDay(){
		isLeapYear();
    	getDays();
    	riliHtml = '';
    	//本月一号周几
    	calendarDate.monthStart = new Date(calendarDate.year+"/"+calendarDate.month+"/1").getDay();
		//上个月所占空格数
		console.log(calendarDate)
		if( calendarDate.monthStart == 0 ){//独占一行
			calendarDate.monthStart = 7;
		}
		//上月数据
		for( var i = calendarDate.monthStart;i>0;i-- ){
			var dataDateStr = calendarDate.lastYear + "-" + calendarDate.lastMonth + "-" + (calendarDate.lastDays - i + 1);
			riliHtml += '<div class="ht-rili-td ht-rili-td-disabled" data-date="'+ dataDateStr +'"><span class="ht-rili-day">'+ (calendarDate.lastDays - i + 1) +'</span><span class="ht-rili-money"></span></div>'
		}
		//本月数据
		for( var k = 0;k<calendarDate.days;k++ ){
			var flag
			var dataDateStr = calendarDate.year + "-" + calendarDate.month + "-" + ( k + 1  );
			for( var d in calendarDate.opt.data ){
				var nowDate = dataDateStr;
				var dataDate = calendarDate.opt.data[d].date; 
				flag = checkDate(nowDate,dataDate);
				if( flag ){
					riliHtml += '<div class="ht-rili-td ht-rili-onclick" data-date="'+ dataDateStr +'"><span class="ht-rili-day">'+ (k + 1) +'</span><span class="ht-rili-money" data-money="'+ calendarDate.opt.data[d].data +'">（'+ calendarDate.opt.data[d].data +'）</span></div>';
					break;
				}
			}
			if( !flag ){
				riliHtml += '<div class="ht-rili-td ht-rili-td-disabled" data-date="'+ dataDateStr +'"><span class="ht-rili-day">'+ (k + 1) +'</span><span class="ht-rili-money"></span></div>';	
			}
		}
		//下月数据
		for( var j = 0;j<(42 - calendarDate.days - calendarDate.monthStart);j++ ){//42-已占用表格数=剩余表格数
			var dataDateStr = calendarDate.nextYear + "-" + calendarDate.nextMonth + "-" + (j + 1);
			riliHtml += '<div class="ht-rili-td ht-rili-td-disabled" data-date="'+ dataDateStr +'"><span class="ht-rili-day">'+ (j + 1) +'</span><span class="ht-rili-money"></span></div>';
		}
		$('.ht-rili-body').append(riliHtml);
		$('.ht-rili-onclick').on('click',function(){
			dateClick(this);
		})
		
	}
	//是否是闰年
	function isLeapYear(){
		if( (calendarDate.year % 4 == 0) && (calendarDate.year % 100 != 0 || calendarDate.year % 400 == 0) ){
			calendarDate.isLeapYear = true;
		}else{
			calendarDate.isLeapYear = false;
		}
	}
	//日期点击事件
	function dateClick(obj){
		$(obj).siblings().each(function(){
			$(this).removeClass('ht-rili-td-active');
		});
		$(obj).addClass('ht-rili-td-active');
	}
	//获取上个月份，本月，下个月份信息
	function getDays(){
		//上月天数
        if(  parseInt(calendarDate.month) == 1 ){
        	calendarDate.lastDays = new Date(calendarDate.year-1,12, 0).getDate();
        	calendarDate.lastMonth = new Date(calendarDate.year-1,12, 0).getMonth()+1;
        	calendarDate.lastYear = new Date(calendarDate.year-1,12, 0).getFullYear();
        }else{
            calendarDate.lastDays = new Date(calendarDate.year,calendarDate.month-1, 0).getDate();
            calendarDate.lastMonth = new Date(calendarDate.year,calendarDate.month-1, 0).getMonth()+1;
            calendarDate.lastYear = new Date(calendarDate.year,calendarDate.month-1, 0).getFullYear();
        }
        //下个月天数
        if( parseInt(calendarDate.month) == 12 ){
            calendarDate.nextDays  = new Date(calendarDate.year+1,1, 0).getDate();
            calendarDate.nextMonth  = new Date(calendarDate.year+1,1, 0).getMonth()+1;
            calendarDate.nextYear  = new Date(calendarDate.year+1,1, 0).getFullYear();
        }else{
            calendarDate.nextDays  = new Date(calendarDate.year,calendarDate.month+1, 0).getDate();
            calendarDate.nextMonth  = new Date(calendarDate.year,calendarDate.month+1, 0).getMonth()+1;
            calendarDate.nextYear  = new Date(calendarDate.year,calendarDate.month+1, 0).getFullYear();
        }
        //本月天数
		calendarDate.days = new Date(calendarDate.year,calendarDate.month, 0).getDate();
	}
	//检测时间是否一致
	function checkDate( dateStr1, dateStr2 ){
		var date1 = dateStr1.split("-"); //[0]year,[1]month,[2]date;
		var date2 = dateStr2.split("-"); //[0]year,[1]month,[2]date;
		if( date1[1] < 10 && date1[1].length < 2){
			date1[1] = "0"+date1[1];
		}
		if( date1[2] < 10 && date1[2].length < 2){
			date1[2] = "0"+date1[2];
		}
		if( date2[1] < 10 && date2[1].length < 2){
			date2[1] = "0"+date2[1];
		}
		if( date2[2] < 10 && date2[2].length < 2){
			date2[2] = "0"+date2[2];
		}
		date1 = date1.join("-");
		date2 = date2.join("-");
		return date1 == date2;
	}
	
	$.fn.extend({
	    calendar:function(opt){
	        if( (opt.beginDate != undefined && opt.endDate != undefined )||( opt.data.length > 0 ) ){
	        	var beginDate = opt.data[0].date;
	        	var endDate = opt.data[opt.data.length-1].date;
	        	calendarDate.beginYear = parseInt(beginDate.split('-')[0]);//起始年
				calendarDate.beginMonth = parseInt(beginDate.split('-')[1]);//起始月
				calendarDate.beginDate = parseInt(beginDate.split('-')[2]);//起始日
	        	
	        	calendarDate.endYear = parseInt(endDate.split('-')[0]);//结束年
				calendarDate.endMonth = parseInt(endDate.split('-')[1]);//结束月
				calendarDate.endDate = parseInt(endDate.split('-')[2]);//结束日
				
				calendarDate.year = parseInt(beginDate.split('-')[0]);//设置起始日期为当前日期
				calendarDate.month = parseInt(beginDate.split('-')[1]);//设置起始日期为当前日期
				calendarDate.date = parseInt(beginDate.split('-')[2]);//设置起始日期为当前日期
	        	calendarDate.opt = opt;
	        	
	        }else{
	        	console.log('未传入beginDate或endDate！');
	        }
	        //加载容器
	    	calendarDate.container = '<div class="ht-rili-querybox"><strong class="ht-rili-title">'+ opt.title +'</strong><div class="ht-rili-datebox"><span class="ht-rili-leftarr"></span><span class="ht-rili-date"></span><span class="ht-rili-rightarr"></span></div></div><div class="ht-rili-head"><div class="ht-rili-th">周日</div><div class="ht-rili-th">周一</div><div class="ht-rili-th">周二</div><div class="ht-rili-th">周三</div><div class="ht-rili-th">周四</div><div class="ht-rili-th">周五</div><div class="ht-rili-th">周六</div></div><div class="ht-rili-body"><!--<div class="ht-rili-td"><span class="ht-rili-day">1</span><span class="ht-rili-money">&yen;100</span></div>--></div>'
	    	$(opt.ele).append(calendarDate.container);
	        $('.ht-rili-date').html(calendarDate.year+'年 '+calendarDate.month+'月');
	        
	    	getIndexDay();
	    	
 	    	$('.ht-rili-leftarr').on('click',function(){
    		/* $('.ht-rili-body').html(''); */
    		 $('.demo-box').html('');
    		if( calendarDate.month == 1 ){
    			calendarDate.year -= 1;
    			calendarDate.month = 12;
    		}else{
    			calendarDate.month -=1;
    		}
	  		var nextmonth = calendarDate.month+1;
	  		var start = calendarDate.year + "-" + calendarDate.month + "-01";
	  		var end = calendarDate.year + "-" + nextmonth + "-01";
    		var url =  window.BASEPATH + 'product/package/getNumTicketsByDay';
 			var date = {"id":${proId},"choice":${choice},"start":start , "end": end}
			$.post(url,date,function(data){
				if(data.length==0){
				 $('.Calendar').hide();
				alert('抱歉,当月暂无可预订门票')
				 return; 
				}
				$('.calendar-box').calendar({
				ele : '.demo-box', //依附
				title : '请选择游览日期',
				beginDate : start,
				endDate : end, 
				data : data
				});
			
			})	
    		$('.ht-rili-date').html(calendarDate.year+'年 '+calendarDate.month+'月');
    		/*  getIndexDay();  */
    		})
    		
	    	$('.ht-rili-rightarr').on('click',function(){
	    		/* $('.ht-rili-body').html(''); */
	    		 $('.demo-box').html('');
	    		if( calendarDate.month == 12 ){
	    			calendarDate.year += 1;
	    			calendarDate.month = 1;
	    		}else{
	    			calendarDate.month +=1;
	    		}
   			var nextmonth = calendarDate.month+1;
	  		var start = calendarDate.year + "-" + calendarDate.month + "-01";
	  		var end = calendarDate.year + "-" + nextmonth + "-01";
    		var url =  window.BASEPATH + 'product/package/getNumTicketsByDay';
 			var date = {"id":${proId},"choice":${choice},"start":start , "end": end}
			$.post(url,date,function(data){
				if(data.length==0){
				$('.Calendar').hide();
				alert('抱歉,当月暂无可预订门票')
				 return; 
				}
				$('.calendar-box').calendar({
				ele : '.demo-box', //依附
				title : '请选择游览日期',
				beginDate : start,
				endDate : end, 
				data : data
				});
			
			})	
	    		
	    		$('.ht-rili-date').html(calendarDate.year+'年 '+calendarDate.month+'月');
	    		/* getIndexDay(); */
	    	})
	   
	    	
	    },
	    calendarGetActive: function(){//获取当前选中日期的值
	    	//未选中时返回undefined
	    	var activeEle = $(this).find(".ht-rili-td-active");
	    	var date = activeEle.attr("data-date");
	    	var money = activeEle.children(".ht-rili-money").attr("data-money");
	    	return data = {
	    		date : date,
	    		money : money
	    	}
	    }
	});
})(jQuery)
	</script>
<script>	 
$(function() {
//隐藏头部电话
$("#phone").hide();
});
  		var limt=0;
		var ifFace=0;
     	var resNum=${resNum};
	layui.use('laydate', function(){
	  //禁止软键盘弹出
	   $("#useDate").focus(function(){
        document.activeElement.blur();
    });

	var url = window.BASEPATH + 'product/package/getNumTicketsByDayType?id='+${proId};
	$.get(url,null,function(msg){
	 let msgs = msg.numTicketsByDayType;
	    //判断是否显示预约	  
	  if(msgs==1){
	    //显示自制日历
	     $("#useDate").click(function(){
	  		/* 请求票数 */
	  		var myDate = new Date();
	  		var year = myDate.getFullYear();
	  		var month = myDate.getMonth()+1;
	  		var nextmonth = myDate.getMonth()+2;
	  		var start = year + "-" + month + "-01";
	  		var end = year + "-" + nextmonth + "-01";
	    	
			var url =  window.BASEPATH + 'product/package/getNumTicketsByDay';
 			var date = {"id":${proId},"choice":${choice},"start": start, "end": end}
			$.post(url,date,function(data){
				console.log(data)
				if(data.length==0){
				alert('抱歉暂无可预订门票')
				 return; 
				}
		    	$('.calendar-box').calendar({
					ele : '.demo-box', //依附
					title : '请选择游览日期',
					beginDate : start,
					endDate : end, 
					data : data
				});
				$('.Calendar').show();
			})		
			
    	});
    		//隐藏日历  获取日子 票数
   		$("#getActive").click(function(){
			var data = $('.calendar-box').calendarGetActive();
			$('.zhi').val(1);
			    limt=data.money
				 if(limt==0){
			    alert('该日期门票已售罄')
		      	$('.Calendar').hide();
			    return; 
				 }
	 		/* 	alert(data.date+"--¥"+data.money);  */
			 $('#useDate').val(data.date);
			 $('.Calendar').hide();
       		 $('.demo-box').html('');
		});
	$(document).on('click','.p2',function(){
		 var value=parseInt($('.zhi').val())+1;
		  var values=parseInt(limt);
		 if($('.zhi').val()>=99){
		alert("抱歉，最多同时购买99张")
		 return false;
		 }
		if($('.zhi').val()>=values){
		alert("抱歉，库存不足")
		$('.zhi').val(values)
		 return false;
		 }
		 $('.zhi').val(value);
		 var num1 = pice;
		 var num2 = document.getElementById('shuliang').value;
		 //总额
		 var num3 = document.getElementById("zong");
		 num3.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
	})
	  }else{
	  
	  var laydate = layui.laydate;
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#useDate' //指定元素
	  });
	  $(document).on('click','.p2',function(){
		 var value=parseInt($('.zhi').val())+1;
		 if($('.zhi').val()>=99){
		alert("抱歉，最多同时购买99张")
		 return false;
		 }
		 $('.zhi').val(value);
		 var num1 = pice;
		 var num2 = document.getElementById('shuliang').value;
		 //总额
		 var num3 = document.getElementById("zong");
		 num3.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
	})
	  }
	  
	}) 


	  
	  var today = new Date();
      var tmonth=today.getMonth()+1+'';
      tmonth=(tmonth.length==1?(0+''+tmonth):tmonth);
      var tdate=today.getDate()+'';
      tdate=(tdate.length==1?(0+''+tdate):tdate);
      var todayStr = today.getFullYear()+"-" +tmonth+ "-" + tdate;
      $('#useDate').val(todayStr);
	  
	  
	   $(function() {
	    if(${choice}== 0){
	     $("title").html("购票确认订单"); 
	  
	    }
	   
	    getUserInfo(true);	   	   	   	   
	    getordersInfo();
	     window.BASEPATH = '<%=basePath%>';
	     var parseAjaxResult = function(data){
			if(data.status !== 200){
				$.toptip('data.message', 'error');
				return -1;
			}else{
				return data.data;		
			}
			
	  };	 
	    }); 	 	  	 
     var pice = ""; 
 
	 function  getordersInfo(){
	 if(${isCombo} == 1){	 
	 var order_path = window.BASEPATH + 'product/package/orders/info?comboId=${comboId}&isCombo=${isCombo}';
	 }else{
	  var order_path = window.BASEPATH + 'product/package/orders/info?proId=${proId}&choice=${choice}&isCombo=${isCombo}';
	 }	 
	 //南湖 不显示游玩日期
	if(${proId}==2469){
		document.getElementById("travelDate").style.display = "none"
		document.getElementById("travelDates").style.display = "block"
	}else{
		document.getElementById("travelDate").style.display = "block"
		document.getElementById("travelDates").style.display = "none"
	}
	 $.get(order_path,null,function(msg){
	   var prc = msg.ticketPrice;
	   ifFace=msg.ifFace;
		     
	  $("#zong").text(prc*${resNum});
	   pice=prc;
	 })
	}
	 /*控制数量  */
	$(document).on('click','.p1',function(){
	 var value=parseInt($('.zhi').val())-1;
	 if($('.zhi').val()<=resNum){
	 return false;
	 }
	 $('.zhi').val(value);
	 var num1 = pice;	
	 var num2 = document.getElementById('shuliang').value; 
	 //总额
	 var num3 = document.getElementById("zong");
	 num3.innerText = (parseFloat(num1).toFixed(2) * parseFloat(num2).toFixed(2)).toFixed(2);
	})
   

			$(document).on('click', '.change', function(){ 															
		    $(".window-1").fadeIn();
		  });
		    $(document).on('click', '.close-window-1', function(){ 
		    $(".window-1").fadeOut();
		}); 		
	}); 
	//添加信息
	function addInfo(){
	
	   $("#_name").val(""),
       $("#_phone").val(""),
       $("#_idcard").val(""),
	     
	  $(".window-2").fadeIn();
	  $(".homepage").fadeOut();
	  $(".window-1").fadeOut();
	}			
	//-------------------------------------------------------------------------
		
	    var prepay_id;

		var paySign;

		var appId;

		var timeStamp;

		var nonceStr;

		var packageStr;

		var signType;

		var orderNo;
	   			  	   	  	
	//购买条件判断	
	var nobuy = "";
	var choice = ${choice}; 
	var userId = ${userId};
	var number =0;
	var clientNumber = []; //所选的用户ID
	$(document).on('click','#nowbuy',function(){	
         //日期判断
        	//南湖id 2469 日期不判断
    	 if($("#useDate").val()==''&&${proId}!=2469){
		     $.toast("预定时间不能为空", "forbidden");
		      return false;
		    }
		//票的数量与所选人数是否一样   
          if((ifFace==1&&$('.zhi').val() != number)||(ifFace==0&&number==0)){
             $.toast("请在填写身份信息处确认用户信息数量", "forbidden");
		      return false;         
          }    

		    var buyDate = new Date($("#useDate").val().replace(/-/g,"/"))
		    var time = new Date();
		    var nowDate = new Date(formatDate(time));
		    
		    if(buyDate>=nowDate){
		  	 
 				 var url =  window.BASEPATH + 'product/package/productDate';
 				 var date = {"id":${proId},"choice":${choice},"buyDate":$("#useDate").val()}
 				 if(${proId}==2469){
 				 //如果是南湖的票 不用定义日期 又得让我传 。。我觉得你是在难为我。我选择传明天
 				 var nhDateTime = new Date();
 				 var d = nhDateTime.getFullYear() + "-" + (nhDateTime.getMonth() + 1) + "-" + (nhDateTime.getDate()+1);
			   		 date={"id":${proId},"choice":${choice},"buyDate":d}
		   		 }
				  $.post(url,date,function(msg){
				  if(msg.result == 1){		
				     nobuy="1";
				    return true;
				  }else{		  
				     $.toast("该票日期已到", "forbidden");
				      nobuy="0";
				     return false;		    
		        }		   
		  }) 			
            }
            
            else{
             nobuy="0";
             $.toast("预订日期不能小于当前日期", "forbidden");
             return false;		 
            }
                 	         	
      var url = window.BASEPATH + 'product/package/commonticket?proId=${proId}&choice=${choice}&ticketnumber='+$('.zhi').val();	 
	  $.get(url,null,function(msg){
	
	  var sign = msg.state;
	    if($.isEmptyObject(msg) == true){
	　　　		sign=4;
	　　}
	 //普通票判断
	  if(choice == 0){		   	    
	  /*  $(document).attr("title","购票确认订单"); */	        	    	     
	     if(sign == 0){ 
	         $.toast("票以售空", "forbidden");
			 return false;
	     }
	     if(sign == 1){ 
	        $.toast("票量不足,剩余"+msg.Stock, "forbidden");	        
			 return false;
	     }
	     if(sign == 2){ 
	       $.toast("最低限购数量为:"+msg.limitNum, "forbidden");
			 return false;
	     }
	     if(sign == 3){	
	      if(nobuy ==  "1" ){
	       dobuy();
	      }   	     	       	        	     	     	        	       
	       }	     
	     }
	   //活动票判断  	  
	   else{
	     if(sign == 0){
	      $.toast("票以售空", "forbidden");
			 return false;
	     }
		if(sign == 1){
		  $.toast("每日限购1张", "forbidden");
			 return false;
		}	
		if(sign == 2){
		    $.toast("票量不足,剩余"+msg.Stock, "forbidden");	        
			 return false;
		}
		if(sign == 3){
			$.toast("最低限购数量为:"+msg.limitNum, "forbidden");
			 return false;		
		}
		if(sign == 4){
		  if(nobuy ==  "1" ){
	       dobuy();
	      } 	     
		}	          
	  }
	  })	   	
	}) 
	
	 //设置时间转换格式
	  function formatDate(date){
	   var y = date.getFullYear();//获取年
	   var m = date.getMonth()+1;//获取月
	   m = m < 10?'0'+m:m; //判断月是否大于10
	   var d = date.getDate();//获取日
	   d=d<10?('0'+d):d;//判断日期是否大于10
	   return y+'/'+m+'/'+d;//返回时间格式 
	  }
	  				 
	 function dobuy(){
		    //勾选的购买人的身份信息
		var youP = [];
		var myP = document.getElementsByClassName("psss");
		for(let i=0;i<myP.length;i++){
			let a =myP[i]
			youP.push(a.getAttribute("name"));
		}
		    var param={};
			param.id=${proId};
			param.productNum=$('.zhi').val();        
			param.userId=${userId};
			param.paytype='WEICHAT';
			param.source='PUBLICADDRESS';
			param.isCombo = ${isCombo};	
			param.choice=${choice};
			param.bookDate=$("#useDate").val();
			if(${isCombo} == 1){	
			param.ComboId = ${comboId};
			}
			param.messageUserIds=youP;
		   		   	                   
	        var _uriPay = window.BASEPATH + 'product/package/order/add';
				$.post(_uriPay, $.toJSON(param), function(data){
					data = parseAjaxResult(data);
					if(data === -1) return;

					//refreshActivity();
					$.modal({
						  title: "付款方式",
						  buttons: [
						    { text: "余额支付", onClick: function(){ 

						    	$.confirm("确定支付？", function() {
									addMessageOrderId(data.orderId,0);										
								  }, function() {});

						    } },						    
						    { text: "微信支付", onClick: function(){ 
							    $.confirm("确定支付？", function() {
							       addMessageOrderId(data.orderId,1);							      							      						       													    
								  }, function() {});
						    } },
						    { text: "取消", className: "default", onClick: function(){ } },
						  ]
						});
				});	 	 	 
	 }	
	 
	    //添加下单客户 orderID 
	  function addMessageOrderId(orderId,paytipe){
	    if(clientNumber.length > 0){	   
	        var gather = {};
	        gather.oderId= orderId;
	        gather.clientList = clientNumber;
	        gather.proId =${proId};
	        gather.userId = ${userId};
	        gather.merchantId =${merchantId};
		    var addMessage_url = window.BASEPATH+'product/package/addMessage';		    
		     $.post(addMessage_url,$.toJSON(gather),function(msg){
		       	if(msg == 'success'){
    	            if(paytipe == '0'){
    	             payByWallet(orderId);
    	            }else{
    	             payPublic(orderId);	
    	            }
		       	}else{
		       	 $.toast("用户信息保存失败", "forbidden");
		       	}	     
		     })		    
		    }				  	  
	  }		  
	 
	 
	 /* 钱包购买方法 */
		function payByWallet(orderId){
			var url=window.BASEPATH+'pubnum/wallet/walletbuy';
			var userId=${userId};
			$.post(url,{'orderId':orderId,'userId':userId},function(data){
						data = parseAjaxResult(data);
				if(data==1){
				$("body").append("<div class='yue' style='z-index:11111111111;margin:0 0 -60px -25%;border-radius:10px;height:120px;width:50%;background: #fff;position: fixed;bottom:50%;left:50%;font-size:16px;font-weight: bold;text-align: center;overflow: hidden;'><img style='width:40%;' src='lib/images/8.png'><p  style='line-height: 45px;color:#1afa29;'>支付中</p><div>");
						 setTimeout(function() {
						 	$('.yue').hide()
						 }, 1500)
						$.get(window.BASEPATH +"pubnum/order/status?orderId="+orderId, null, function(data){
						    if(data.data=="PAYSUCCESS"){						    	
					           updatemessage(orderId);					            
						      location.href=window.BASEPATH +"business/gotopayment?merchantId=${merchantId}&orderId="+orderId;
						    }
						});
				}else{
					$.alert('您的余额不足！');
				}
			})			
		}
		
		//支付成功后修改身份采集表里的状态 
		function updatemessage(orderId){
		    var _uri = window.BASEPATH + 'product/package/updatemessage';
		          var params = {};		        
		          params.oderId=orderId;		 
			$.post(_uri, params, function(data){			s		   
					if(data.msg==1){
						alert("保存失败");
					}			        
				});       	    
		}
	 
	 function payPublic(orderId){
			$.get(window.BASEPATH +"pubnum/prev/pay/"+orderId, null, function(data){
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
		            $("body").append("<div class='oks' style='z-index:11111111111;margin:0 0 -50px -25%;border-radius:6px;height:100px;width:50%;background:#FAFBFB;position: fixed;bottom:50%;left:50%;font-size:16px;font-weight: bold;text-align: center;overflow: hidden;border:1px solid'><p  style='line-height: 100px;'>交易成功</p><div>");	
		            setTimeout(function() {
		               $('.oks').hide()
		            }, 1500)
		                /* $.confirm("交易成功"); */
		                //每五秒刷新订单状态					
		                var myVar = setInterval(function(){ 
                                $.get(window.BASEPATH +"pubnum/order/status?orderId="+orderNo, null, function(data){								    
								    if(data.data=="PAYSUCCESS"){	
								    	clearInterval(myVar);							      							    
								       location.href=window.BASEPATH +"business/gotopayment?merchantId=${merchantId}&orderId="+orderNo;
								    }
								     
								});
                        }, 1000);
		            }
		            if (res.err_msg == "get_brand_wcpay_request:cancel") {  
		                alert("交易取消");  
		            } 
		            if (res.err_msg == "get_brand_wcpay_request:fail") {  
		                alert(res.err_desc); 
		            }  
		        }
		    );
		} 
			 	  		
  	//----------------------------------------------------------------------
     	var base="";
       function  getPicture(){                                                       
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
                            choosePic(this.id);
                        }
                    }
                });
            });
            wx.error(function(res){
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                alert("验证失败，请重试！");
                wx.closeWindow();
            });		
		};

		function choosePic(id) {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    $.toast("照片处理中...", "loading");
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    getLocalData(localIds[0]);
                }
            });
        }

        function getLocalData(localid) {       
			//获取本地图片资源
            wx.getLocalImgData({
                localId: localid, // 图片的localID
                success: function (res) {
                    var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
                    var str=new String();
                    var arr=new Array();
                    str=localData ;
                    var sear=new RegExp(',');
                      if(sear.test(str)) {
                        arr=str.split(',');//注split可以用字符或字符串分割
                      
                        $('#uploadImages').attr('src','data:image/png;base64,'+arr[1]);
                      						
						base=arr[1];
                       }else{                     
                         $('#uploadImages').attr('src','data:image/png;base64,'+localData);                                               
                          base=localData;
                       }                                                                                                                                                                                  
                }
            });
        }
        
        	
		//点击输入框识别图片
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
        
      //----------------------------------------------------------------------  
      //区分 保存 还是更新 
      var state ="";   
      function  save(){       
        if($('#_phone').val()==''){
			   $.toast("请输入手机号", "forbidden");
			   return false;
			}
			if(!(/^1[3456789]\d{9}$/.test($('#_phone').val()))){ 
		       $.toast("手机号码有误，请重填", "forbidden");  
		       return false; 
		    } 
			if($('#_name').val()==''){
			   $.toast("请输入姓名", "forbidden");
			   return false;
			} 
			if($('#_idcard').val()==''){
			   $.toast("请输入身份证号", "forbidden");
			   return false;
			} 
			 var re = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
                      
		    if(re.test($('#_idcard').val())==false){
		       $.toast("身份证号码有误，请重填", "forbidden");  
		       return false;  		    
		    }	
		    
		     /*  if(base==""){
              $.toast("照片获取失败", "forbidden");
			  return false;
             }   */  	     
                $(".window-2").fadeOut();
                $(".window-1").fadeOut();
			    $(".homepage").fadeIn();
			                
              var url = window.BASEPATH + 'product/package/add/info';
               
              if(state == ""){                                  
              var date = {"name":$("#_name").val(),
                          "phone":$("#_phone").val(),
                          "idcard":$("#_idcard").val(),
                          "facedate":base,
                          "state": 0
                      }
               }else{
                 var date = {"name":$("#_name").val(),
                          "phone":$("#_phone").val(),
                          "idcard":$("#_idcard").val(),                         
                          "facedate":base,
                          "state": state           
              }  
              }            
	          $.post(url, date,function(msg){ 
	          	if(msg == 'success') {
	          	    $(".homepage_add").children().remove();
	          	    $("#window-1-message").children().remove();          	    
	          	    clientNumber.splice(0,clientNumber.length);
	          	    number = 0;
	           		getUserInfo(false);
	           		
          		     $.alert("保存成功");  
	           	}
	         })                                                                                                       
     }	
       	 
	//删除用户  	 
	 function deleteClientMessage(id){
	     var merssageId = id.split("-");
	      var url =  window.BASEPATH + 'product/package/deleteClientMessage?merssageId='+merssageId[1]; 
	     $.get(url,null,function(msg){   
	        if(msg=="success"){
	                $(".homepage_add").children().remove();
	          	    $("#window-1-message").children().remove();          	    
	          	    clientNumber.splice(0,clientNumber.length);
	          	     number = 0;
	           		getUserInfo(false);
	               alert("操作成功")
	        }
	     })	 	 	    
	 } 
       
	     //查询用户信息
	     var clientInfo = " "; 	    
	  	 function  getUserInfo(first){
	  	  var url_ = window.BASEPATH + 'product/package/user/list';
	  	  $.get(url_,null,function(msg){
	  	  var mesage = msg.message;
	  	  clientInfo = msg.message; 	  
	  	  if(mesage.length==0){return;}
	      var firstAddr=0;
	  	  for(var i = 0; i<mesage.length;i++){	  	    	  	     
	  	     var html = [];  	  	             
             html.push('<div  style="width:100%;height:auto;background: #fff;margin:5px 0;position: relative;border-bottom:1px solid #A6A6A6;text-align: left;padding:10px 10%;">');
             if(first&&i==0){
                html.push('<input checked="checked" id="input-'+i+'"  onclick="clientMessage(this.id)"  type="checkbox" name="sex" value="1" style="position: absolute;top:30px;left:5%;" />');
                firstAddr='input-'+i;
                
             }else{
                html.push('<input id="input-'+i+'"  onclick="clientMessage(this.id)"  type="checkbox" name="sex" value="1" style="position: absolute;top:30px;left:5%;" />');
             }
             html.push('<P>姓名：'+mesage[i].name+'</P>');
             html.push('<P>手机号：'+mesage[i].phone+'</P>');
             html.push('<P>身份证号：'+mesage[i].number+'</P>');
             html.push('<img id="img1-'+mesage[i].id+'" onclick="deleteClientMessage(this.id)" style="width:28px;height:28px;position: absolute;top:26px;right:20%;border-radius:50%;" src="lib/images/trashss.png">');
             html.push('<img id="'+mesage[i].id+'" onclick="update(this.id)"  style="width:20px;height:20px;position: absolute;top:30px;right:10%; cursor:pointer " src="lib/images/xiugai.png">');
			 html.push('</div>');
             $("#window-1-message").append(html.join(''));
			 
	  	  }
clientMessage(firstAddr);
	  	  	})  	 	  	 
	  	 }
	
	  	 	  	 
	 //更新用户信息 	 	  	 	
 	function update(id){       
	  	state = id;
	  	 var url = window.BASEPATH + 'product/package/update/message?id='+id;
	  	 $.get(url,null,function(msg){
	  	  var mesage = msg.mes;	  	  
	  	  $("#_name").val(mesage.name),
          $("#_phone").val(mesage.phone),
          $("#_idcard").val(mesage.number),
          $(".homepage").fadeOut();
          $(".window-1").fadeOut();
          $(".window-2").fadeIn();  	
	  	}) 
   }           		
   function shutdown(){
    $(".window-2").fadeOut();
    $(".homepage").fadeIn();
    $(".window-1").fadeIn();  
   }    
   
  function clientMessage(id){
    var i = id.split("-");

   if(clientInfo != " "){
			//多选框要用prop 来获取判断
            if($("#"+id).prop("checked")){
            //添加勾选的信息
             number+=1;
	
             clientNumber[i[1]] = clientInfo[i[1]].id;	
			 var htm = [];	
			
             htm.push('<p class="psss" name="'+clientInfo[i[1]].id+'" style="width:20%;padding:2px 5px;background:#FF9C00;color:#fff;border-radius:12px;margin:2px 5px;display: inline-block;text-align:center;" id="homepage-'+i[1]+'">'+clientInfo[i[1]].name+'</p>');            
             $(".homepage_add").append(htm.join(''));         
            } 
            if(clientNumber.length==$('#shuliang').val()){
                $(".window-1").fadeOut();
            }
            //取消勾选删除
            if(!($("#"+id).prop("checked"))){
               $("#homepage-"+i[1]).remove();               
               clientNumber.splice(i[1],1);
     			number-=1;
            }                           
     }         
  }         					
</script>
<!-- ios 软键盘兼容问题 -->
<!-- <script>
	FastClick.prototype.focus = function(targetElement) {
		var length;
		//兼容处理:在iOS7中，有一些元素（如date、datetime、month等）在setSelectionRange会出现TypeError
		//这是因为这些元素并没有selectionStart和selectionEnd的整型数字属性，所以一旦引用就会报错，因此排除这些属性才使用setSelectionRange方法
		if (deviceIsIOS && targetElement.setSelectionRange && targetElement.type.indexOf('date') !== 0 && targetElement.type !== 'time' && targetElement.type !== 'month' && targetElement.type !== 'email') {
			length = targetElement.value.length;
			targetElement.setSelectionRange(length, length);
			/*修复bug ios 11.3不弹出键盘，这里加上聚焦代码，让其强制聚焦弹出键盘*/
			targetElement.focus();
		} else {
			targetElement.focus();
		}
	};
	const str = navigator.userAgent.toLowerCase()
	const ver = str.match(/cpu iphone os (.*?) like mac os/)

	if (!ver || parseInt(ver[1]) < 11) { //非IOS系统或者系统版本小于11
		// 引入fastclick 做相关处理
	}
</script> -->
<body>
	<!-- 主页 -->
	<div class="header">
		<div class="wrapper">
			<a class="link-left" href="#side-menu"><span
				class="icon-reorder icon-large"></span></a>
			<div class="header-content">商户</div>
		</div>
	</div>

	<div class="homepage" style="z-index:11111111;">
		<!-- 订单信息  -->
		<div id="order"
			style="width:96%;height:auto;margin:0 auto;background:#fff;position: relative;overflow: hidden;">

		</div>



		<div class="layui-inline"
			style="width:96%;height:auto;position: relative;">
			<!-- 注意：这一层元素并不是必须的 -->
			<ul class="listbox" style="color:black;">
				<li id="travelDate">
				<input type="text" placeholder="请您选择游玩日期" id="useDate" class="layui-input"
				style="cursor: pointer;width:100%;height:60px;margin:10px auto;text-align: right;margin-left:2%;padding:0 10%;">
					<p style="position: absolute;top:30px;left:10%;">游玩日期</p>
					<p style="position: absolute;top:30px;right:5%;">❯</p></li>

				<li id="travelDates">
					<!-- position: absolute;top:30px;left:10%; --> <!-- style="position: absolute;top:30px;right:5%;" -->
					<!-- 					<p style="color:red;position: absolute;top:30px;left:10%;">活动规则:2020年1月17日-2020年1月24日(1月24日闭园),过期作废。</p>
 --> <p 
					style="cursor: pointer;width:100%;height:60px;line-height:60px;margin:10px auto;text-align: center;margin-left:2%;font-size:80%"
					class="layui-input" id="useDate" >活动日期:2020-1-17至2020-2-24 (1月24日闭园),过期作废。</p>

				</li>




				<li><input type="button">
					<p style="position: absolute;top:105px;left:10%;">购买数量</p>
					<p class="p1"
						style="position: absolute;top:105px;right:22%;font-size:14px;font-weight:bold;line-height:25px;display:inline-block;width:25px;height:25px;border-radius:50%;border:1px solid #666666;text-align: center;">—</p>
					<input type="text" readonly="true" class="zhi" id="shuliang"
					value="${resNum}"
					style="padding:0;border:none;outline: none;width:20px;height:20px;position: absolute;right:14%;margin:0;top:108px;font-size:14px;font-weight:bold;text-align: center;">
					<p class="p2"
						style="position: absolute;top:105px;right:4%;font-size:22px;color:#fff;background:#EC6D1E;display:inline-block;width:25px;height:25px;border-radius:50%;border:1px solid;text-align: center;line-height:25px;">+</p>
				</li>
				<li><input type="button">
					<p style="position: absolute;top:175px;left:10%;">优惠券</p>
					<p style="position: absolute;top:175px;right:7%;">
						<span>0</span>张可用
					</p></li>
				<li><input type="button">
					<p style="position: absolute;top:245px;left:10%;">填写身份信息</p>
					<p class="change"
						style="position: absolute;top:245px;right:7%;color:#FCB735;">添加/修改▲</p>
					<div class="homepage_add"
						style="height:auto;width:85%;background: #fff;margin:-20px auto 0;padding:20px 0;border-radius:10px;">
					</div></li>

			</ul>
		</div>
		<!-- 付款  -->
		<div
			style="background:#fff;height:60px;width:100%;border-bottom:1px solid  rgb(230, 230, 230);border-top:1px solid  rgb(230, 230, 230);position: fixed;bottom:0;">
			<p
				style="height:100%;float:left;text-align:center;width:55%;line-height: 60px;color:#EC6D1E;font-size:20px;font-weight:bold;display: inline-block;">
				￥<span id="zong"></span>
			</p>
			<p id="nowbuy"
				style="height:100%;float:right;text-align:center;width:45%;line-height: 60px;color:#fff;font-size:20px;font-weight:bold;display: inline-block;background:#EC6D1E; ">立即购买</p>
		</div>
		<div style="height:90px;width:100%;"></div>

	</div>


	<div class="window-1"
		style="z-index:11111;width:100%;display: none;height:500px;padding:0 0 50px 0;overflow-x: hidden;text-align: center;background: #fff;position: fixed;bottom:0; ">
		<p
			style="text-align:center;width:100%;margin:0 auto;height:40px;line-height: 40px;font-size: 14px;border-bottom:1px solid #D3D3D3;background:#CFCFCF">
			添加/修改信息<span class="close-window-1"
				style="float:right;color:#fff;margin-right:5%;font-weight:bold;">关闭</span>
		</p>
		<button onclick="addInfo()"
			style="width:50%;height:30px;color:#FFA940;border:none;outline:none;background: #fff;border:1px solid #FFA940;margin:20px 0;">添加信息</button>
		<div id="window-1-message"></div>
	</div>



	<!-- 添加信息 -->
	<div class="window-2" style="z-index:111111;display: none;">
		<ul class="listbox-2" style="color:black;">
			<li><input id="_name" type="text" placeholder="请输入您的姓名"
				minlength="4" maxlength="4" style="">
				<p style="position: absolute;top:65px;left:10%;">用户姓名</p></li>
			<li><input id="_phone" class="phone" type="text"
				placeholder="请输入正确的手机号码" minlength="11" maxlength="11" style="">
				<p style="position: absolute;top:135px;left:10%;">联系电话</p></li>
			<li><input id="_idcard" class="pid" type="text"
				placeholder="请输入身份证号码" minlength="18" maxlength="18" style="">
				<img id="face"
				style="height:20px;height:20px;position: absolute;top:205px;right:10%;"
				src="lib/images/zhaoxiang.png">
				<p style="position: absolute;top:205px;left:10%;">身份证号码</p></li>
		</ul>

		<div
			style="background:#fff;height:370px;width:96%;border-radius:6px;margin:0 auto;position: relative;top:10px;">
			<img id="uploadImages"
				style="width:140px;height:171px;position: absolute;left:50%;margin:40px 0 0 -70px;"
				alt="" src="lib/images/renliansss.png">
			<p
				style="text-align: center;position: absolute;top:250px;left:50%;margin-left:-126px;">请保持正脸，平视屏幕，面部足够清晰。</p>
			<button onclick="getPicture()"
				style="position: absolute;left:50%;margin-left:-50px;top:300px;font-size:18px;width:100px;height:35px;color:#fff;font-weight:bold;background:#FFC138;border:none;outline:none;border-radius:10px;">开始拍摄</button>
			<button onclick="save()"
				style="position: absolute;left:14%;top:300px;font-size:18px;width:70px;height:35px;color:#fff;font-weight:bold;background:#FFC138;border:none;outline:none;border-radius:10px;">保存</button>
			<button onclick="shutdown()"
				style="position: absolute;right:14%;top:300px;font-size:18px;width:70px;height:35px;color:#fff;font-weight:bold;background:#FFC138;border:none;outline:none;border-radius:10px;">关闭</button>
		</div>
	</div>
	
	<!--日历  -->
<div class='Calendar' style="display: none;position: fixed;top:0;width:100%;z-index:111;">
<div class="calendar-box demo-box"></div>
<p id="getActive" style="width:100%;text-align: center;height:70px;line-height: 70px;font-size:20px;background:#434343;position: fixed;bottom:0;color:#fff; ">确定</p>
</div>
</body>






</html>