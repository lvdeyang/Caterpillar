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
	.toast {
	background: #f80851;
}
	
</style>
    </head>
<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script>
	var overall ;
    var personName = ""; //人名
	var card = "";	//结束时间
	var price= "";	 //价钱
        	layui.use(['element','laypage','layer','laytpl','table'], function(){
              $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;
              table = layui.table; //模板引擎
		});
	
	    
	//打开分类
       function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
        }
		function open_win(title, url, w, h) {
			 if($('#tablelist').val() !=null && $('#tablelist').val() != ""){
				x_admin_show(title, url +"?merchantId="+$('#tablelist').val(), w, h);
			}else{
			   alert("请选择商家");
			} 
		}
		var overall = null;
		var tier = null;
		function list(date,type){
		$('#div1').empty();
	         $.ajax({  //初始显示列表
					type : "post",
					url : "getlist.do",
					data : {"merchantId":$("#tablelist").val(),"TableDate":date,"type":type},
					success : function(data) {
					data = data.po;
					overall = data;
					var html = [];
					if(data.length>0){
						for (var i = 0; i < data.length; i++) {
							if (tier == null || tier != data[i].tier) {
								tier = data[i].tier;
								html.push('<p style=" background-color:#f2f2f2; font-size:18px;font-weight:bold;height:80px;line-height: 80px;">' + data[i].tier + '</p> ');
							}
							html.push('<div class="xuanzhong" id="' + data[i].id + '" style="width:auto;height:auto;text-align: center;margin:20px;display: inline-block;overflow: hidden;z-index:111111;">');
							if(data[i].room == 1){
							if (data[i].tableState == 0) html.push('<img  style="height:80px;width:80px;" src="../../lib/images/homes.png">');
							if (data[i].tableState == 1) html.push('<img  style="height:80px;width:80px;" src="../../lib/images/lu.png">');
							if (data[i].tableState == 2) html.push('<img  style="height:80px;width:80px;" src="../../lib/images/xuanzhong.png">');
							}
							else{
							if (data[i].tableState == 0) html.push('<img  style="height:80px;width:80px;" src="../../lib/images/huizuo.png">');
							if (data[i].tableState == 1) html.push('<img  style="height:80px;width:80px;" src="../../lib/images/luzuo.png">');
							if (data[i].tableState == 2) html.push('<img  style="height:80px;width:80px;" src="../../lib/images/hongzuo.png">');
							}
							html.push('<p style="font-size:18px;font-weight:bold;height:50px;line-height:50px;">' + data[i].tablename + '</p>');
							html.push('</div>');
						}
					}else{
					  html.push('<p style=" text-align:center; font-size:18px;font-weight:bold;height:50px;line-height:50px;">暂无房间</p>');
					}
					$('#div1').append(html.join(''));
				}
			}) 
		}	
			     
		$(function() {
			$(document).on('click', '.xuanzhong', function() {
			        $("#shenfen").val(""); //手机
			        $("#personName").val(""); //用户名
			        $(".mig").text(""); //房间名称
			        $("#inpu").val(""); //id
			      /*   $("#midday").val(""); */ //中午晚上
			      /*   $("#time").val(""); */ //预订时间
			        $("#price").val(""); //菜品
				 for(var i=0; i<overall.length;i++) {
				  if(this.id == overall[i].id){
				   $(".ming").text(overall[i].tablename);
				   $("#inpu").val(overall[i].id);
				     if( /* overall[i].tableMenu != null && */ overall[i].userName!=null &&  overall[i].userPhone!=null  &&overall[i].menuTime!=null && overall[i].type!=null	){
					     $("#time").val( overall[i].menuTime);
					     if(overall[i].type == "LUNCH"){
					       $("#midday").val(0);
					     }else{
					       $("#midday").val(1);
					     }
					     $("#personName").val(overall[i].userName);
					     $("#shenfen").val(overall[i].userPhone);
				    } 
				  }
			   } 
			   $(".xinxi").fadeIn();
			});
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
			
			
			$(document).on('click', '#btns', function() { //根据对应时间搜索
			    if($("#test1").val() == null || $("#test1").val() == ""){
			       alert("请选择时间");
        			return false;
			    }
			    if($("#times").val() == null){
			      alert("请选择中午晚上");
        			return false;
			    }
			    if($("#tablelist").val() == "" || $("#tablelist").val() == null){
			       alert("请选择商家");
        			return false;
			    }
			    list($("#test1").val(),$("#times").val());	
			});
			
			
			
			
			
			$(document).on('click', '.right', function() { //关闭窗口
				$(".xinxi").fadeOut();
			});
			
			layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  
			  //常规用法
			  laydate.render({
			    elem: '#test1'
			  });
  			});
  				layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  
			  //常规用法
			  laydate.render({
			    elem: '#time'
			  });
			  
			    		
			 	});
	</script>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">`
              <a><cite>首页</cite></a>
              <a><cite>订桌管理</cite></a>
              <a><cite>添加订桌</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margitop:3px;float:right"  href="javascript:location.replace(location.href);" id="sty"title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <xblock>  <a href="javascript:openMap('选择商家','<%=request.getContextPath() %>/admin/merchant/sellist?mcname=MerchantName&mcuuid=MerchantID&mchref=merclass','600','500')" class="layui-btn " >选择商家</a> 
             <button class="layui-btn" onclick="open_win('添加房间','addv','600','500')"><i class="layui-icon">&#xe608;</i>添加房间</button><span class="x-right" s
            ght:40px">
             <input type="text" style="float:left;width:auto;margin-left:-1500px;" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
             <select id="times" style="width:auto;height:38px;margin-left:-1300px;">
              
              <option>午餐</option>
              <option>晚餐</option>
             </select>
             <button id="btns" style="width:auto;height:38px;padding:0 10px;border:none;outline: none; background-color: #009688;color:#fff;">搜索</button>
            </xblock>
         	<table id="activityList" lay-filter="activityList" ></table>
        </div>
       <span style="font-size: 18px;margin-left:5%;">商家名称:</span>
       <input id="mcname" name="mcname"  style='border:none;outline:none;font-size: 18px;font-weight: bold; '>
       <input id="tablelist" name="tablelist" style="display: none;" >
    
  
   
 <!--   <p style="font-size:18px;font-weight:bold;height:80px;line-height: 80px;">1层</p>
     <div style="width:auto;height:auto;text-align: center;float:left;margin:20px;">
       <img class="xuanzhong" style="height:80px;width:80px;" src="../../lib/images/lu.png">
       <p style='font-size:18px;font-weight:bold;height:50px;line-height:50px;'>101</p>
     </div>
	 
	 
     <div style="width:auto;height:auto;text-align: center;float:left;margin:20px;">
       <img class="xuanzhong" style="height:80px;width:80px;" src="../../lib/images/lu.png">
       <p style='font-size:18px;font-weight:bold;height:50px;line-height:50px;'>102</p>
     </div> -->
     
    <div id ="div1">
    </div>

	<!-- 信息登记 -->
	<div class="xinxi" style="width:50%;height:430px;border:5px solid #393D49;padding:30px 2%;position: fixed;top:50%;left:50%;margin:-180px 0 0 -25%;z-index:11;background:#fff;display: none;overflow: hidden;overflow-y: auto">
		<p style="">
			房间名称：<span class="ming" value="111">1层102</span>
			<input style="display:none;" id="inpu" value="">	
		</p>
		<p>
			用户姓名：<input style="height:30px;width:600px;"  id = "personName" >
		</p>
		<p style="">
			手机号：<input id="shenfen" style="height:30px;width:600px;" value="">
		</p>
		<p style="">
			菜品：<input style="height:30px;width:600px;" id = "price" >
		</p>
		<p style="">
			预订时间：<input type="text" class="layui-input"  style="height:30px;width:600px;display: inline-block;" id = "time" placeholder="yyyy-MM-dd">
		</p>
		<p style="">
			午晚：<!-- <input style="height:30px;width:600px;" id = "midday" > -->
			  <select id="midday" style="height:30px;width:600px;">
              <option value="0">午餐</option>
              <option value="1">晚餐</option>
              
             </select>
		</p>
		<!-- <p>
			订桌时间：<input style="height:30px;width:600px;"id = "phone"  >
		</p>
		<p>
			起始时间：<input style="height:30px;width:300px;"id = "startingTime" >
		</p>
		<p style="">
			结束时间：<input style="height:30px;width:300px;"id = "stopTime" >
		</p> -->
		<p style="text-align: center;margin:10px auto;">
			<span class="left" style=""  id="save">入住</span><span class="putaway">上架</span><span class="below">下架</span><span class="quit">退房</span><span class="right">关闭窗口</span>
		</p> 
	</div>
</body>
</html>