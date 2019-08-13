<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    <style type="text/css">
	.layui-table img {
		max-width: 1000px;
	} /* 照片的最大宽度  */
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
		background: #fff !important;
		position: relative;
		-webkit-text-size-adjust: none;
		text-decoration: none !important;
	}
	
	* {
		box-sizing: border-box;
		list-style: none;
		text-decoration: none;
	}
	
	.xinxi p {
		height: 50px;
		line-height: 50px;
		font-size: 20px;
	}
	
	.left, .right ,.putaway,.below,.quit{
		border: 1px solid #898B92;
		padding: 10px 10px;
		margin: 10px;
	}
</style>
    </head>
      <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
	<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script>
	var overall ;
    var personName = ""; //人名
	var card = "";	 //身份证
	var phone= "";	 //联系电话
	var startingTime= ""; //初始时间
	var stopTime= "";	 //结束时间
	var price= "";	 //价钱
        	layui.use(['element','laypage','layer','laytpl','table'], function(){
              $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;
              table = layui.table; //模板引擎
			//以上模块根据需要引入
	        var html = [];
	        var tier = null;
			$.ajax({
				type : "post",
				url : "getlist.do",
				success : function(msg) {
				msg = msg.po;
				overall = msg;
				for(var i=0; i<msg.length;i++) {
					if(tier == null  || tier != msg[i].tier ){
					  tier = msg[i].tier;
			    	  html.push('<p style=" background-color:#f2f2f2; font-size:18px;font-weight:bold;height:80px;line-height: 80px;">'+msg[i].tier+'</p> ');
					} 
					html.push('<div class="xuanzhong" id="'+msg[i].id+'" style="width:auto;height:auto;text-align: center;margin:20px;display: inline-block;overflow: hidden;z-index:111111;">');
					if(msg[i].state == 0)html.push('<img  style="height:80px;width:80px;" src="../../lib/images/weixuan.png">');
					if(msg[i].state == 1)html.push('<img  style="height:80px;width:80px;" src="../../lib/images/lu.png">');
					if(msg[i].state == 2)html.push('<img  style="height:80px;width:80px;" src="../../lib/images/xuanzhong.png">');
					html.push('<p style="font-size:18px;font-weight:bold;height:50px;line-height:50px;">'+msg[i].name+'</p>');
					html.push('</div>');
				 }
				   $('body').append(html.join('')); 
				}
			})
		});
	
	
	
	
		function open_win(title, url, w, h) {
			x_admin_show(title, url, w, h);
		}
	
		$(function() {
			$(document).on('click', '.xuanzhong', function() {
			        $("#shenfen").val("");
			        $("#personName").val("");
			        $("#phone").val("");
			        $("#startingTime").val("");
			        $("#stopTime").val("");
			        $("#price").val("");
			        $(".ming").text("");
				for(var i=0; i<overall.length;i++) {
				  if(this.id == overall[i].id){
				   $(".ming").text(overall[i].name);
				   $("#inpu").val(overall[i].id);
				    if( overall[i].personName != null && overall[i].card!=null &&  overall[i].phone!=null  &&overall[i].startingTime!=null && overall[i].stopTime!=null	&& overall[i].price != null){
					     $("#shenfen").val(overall[i].card);
					     $("#personName").val(overall[i].personName);
					     $("#phone").val(overall[i].phone);
					     $("#startingTime").val(overall[i].startingTime);
					     $("#stopTime").val(overall[i].stopTime);
					     $("#price").val(overall[i].price/100);
				    }
				  }/* else if(this.id == overall[i].id && overall[i].standard == 1){
				   $('.xinxi').empty();
				   var html = [];
				    html.push('<p style="">房间名称：<span class="ming" >'+overall[i].name+'</span></p>');
				    html.push('<input style="display:none;" id="inpu" value="'+overall[i].id+'">');
				   for(var i=0 ;i<3;i++){
				    html.push('<p style="float:right;">身份证号：<input id="shenfen'+i+'" style="height:30px;width:250px;" value=""></p>');
				    html.push('<p>入住人姓名：<input style="height:30px;width:200px;"  id = "personName'+i+'" ></p>');
				    html.push('<p>入住人联系电话：<input style="height:30px;width:200px;"id = "phone'+i+'"  ></p>');
				    $('.xinxi').append(html.join('')); 
				    html =[];
				   }
			        var html = [];
			        html.push('<p style="float:right;">入住结束时间：<input style="height:30px;width:220px;"id = "stopTime" ></p>');
			        html.push('<p>入住起始时间：<input style="height:30px;width:180px;"id = "startingTime" ></p>');
				    html.push('<p style="">房间价格：<input style="height:30px;width:220px;" id = "price" ></p>');
				    html.push('<p style="text-align: center;margin:20px auto;"><span class="left" style=""  id="save">入住</span><span class="putaway">上架</span><span class="below">下架</span><!-- <span class="quit">退房</span> --><span class="right">关闭窗口</span></p>');
				    $('.xinxi').append(html.join(''));  
				  } */
				}
				$(".xinxi").fadeIn();
					layui.use('laydate', function(){
			  var laydate = layui.laydate;
			 //时间选择器
			laydate.render({
			  elem: '#stopTime'
			  ,type: 'datetime'
			});
				 //时间选择器
			laydate.render({
			  elem: '#startingTime'
			  ,type: 'datetime'
			});
			});
			});
			$(document).on('click', '.right', function() {
				$(".xinxi").fadeOut();
			});
			
		
			
			
			$(document).on('click', '#save', function() {
			     var shenfen =   $("#shenfen").val();////身份证
			     var personName =   $("#personName").val(); //人名
			     var phone =   $("#phone").val();//联系电话
			     var startingTime =   $("#startingTime").val();//初始时间
			     var stopTime =   $("#stopTime").val();//结束时间
			     var price =   $("#price").val();//价钱
			    
			   /*  while(flag){
				    if($('#shenfen'+i).length>0){
				    	shenfen += $('#shenfen'+i).val();
				    }else{
				    	flag=false;
				    }
				    i++;
			    } */
			      if(shenfen ==""){
			       alert("请输入身份证");
			       return false;
			     }
			     if(personName ==""){
			       alert("请输入入住人姓名");
			       return false;
			     }
			     if(price ==""){
			       alert("请输入房间价格");
			       return false;
			     }
			     if(phone ==""){
			       alert("请输入联系电话");
			       return false;
			     }
			     if(stopTime ==""){
			       alert("请输入入住结束时间");
			       return false;
			     }
			     if(startingTime ==""){
			       alert("请输入入住起始时间");
			       return false;
			     }
				 $.ajax({
					type : "post",
					url : "addData.do",
					data : {"a":shenfen,"b":personName,"c":phone,"d":startingTime,"e":stopTime,"f":price,"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				})  
			});
			
			$(document).on('click', '.putaway', function() {
			  $.ajax({
					type : "post",
					url : "amend.do",
					data : {"state":1,"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				}) 
			});
			$(document).on('click', '.below', function() {
			   $.ajax({
					type : "post",
					url : "amend.do",
					data : {"state":0,"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				}) 
			});
			$(document).on('click', '.quit', function() {
			   $.ajax({
					type : "post",
					url : "amend.do",
					data : {"state":1,"id":$("#inpu").val()},
					success : function(data) {
					$("#sty i").trigger('click');
					}	
				}) 
			});
			
		});
	</script>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">`
              <a><cite>首页</cite></a>
              <a><cite>房间管理</cite></a>
              <a><cite>添加房间</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" id="sty"title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加房间','addv','600','500')"><i class="layui-icon">&#xe608;</i>添加房间</button><span class="x-right" style="line-height:40px"></xblock>
         	<table id="activityList" lay-filter="activityList"></table>
        </div>
      
       
    
  
   
 <!--   <p style="font-size:18px;font-weight:bold;height:80px;line-height: 80px;">1层</p>
     <div style="width:auto;height:auto;text-align: center;float:left;margin:20px;">
       <img class="xuanzhong" style="height:80px;width:80px;" src="../../lib/images/lu.png">
       <p style='font-size:18px;font-weight:bold;height:50px;line-height:50px;'>101</p>
     </div>
	 
	 
     <div style="width:auto;height:auto;text-align: center;float:left;margin:20px;">
       <img class="xuanzhong" style="height:80px;width:80px;" src="../../lib/images/lu.png">
       <p style='font-size:18px;font-weight:bold;height:50px;line-height:50px;'>102</p>
     </div> -->
     
 

	<!-- 信息登记 -->
	<div class="xinxi" style="width:50%;height:430px;border:5px solid #393D49;padding:30px 2%;position: fixed;top:50%;left:50%;margin:-180px 0 0 -25%;z-index:11;background:#fff;display: none;overflow: hidden;overflow-y: auto">
		<p style="">
			房间名称：<span class="ming" value="111">1层102</span>
			<input style="display:none;" id="inpu" value="">	
		</p>
		<p>
			入住姓名：<input style="height:30px;width:600px;"  id = "personName" >
		</p>
		<p style="">
			身份证号：<input id="shenfen" style="height:30px;width:600px;" value="">
		</p>
		<p style="">
			房间价格：<input style="height:30px;width:600px;" id = "price" >
		</p>
		<p>
			联系电话：<input style="height:30px;width:600px;"id = "phone"  >
		</p>
		<p>
			起始时间：<input style="height:30px;width:300px;"id = "startingTime" >
		</p>
		<p style="">
			结束时间：<input style="height:30px;width:300px;"id = "stopTime" >
		</p>
		<p style="text-align: center;margin:10px auto;">
			<span class="left" style=""  id="save">入住</span><span class="putaway">上架</span><span class="below">下架</span><span class="quit">退房</span><span class="right">关闭窗口</span>
		</p> 
	</div>
</body>
</html>