<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>乐视云视频导出</title>
<meta name="rende rer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/x-admin.css"
	media="all">

<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
<script>

	layui.use([ 'element', 'layer', 'laytpl', 'upload', 'laydate', 'form', 'table' ], function() {
		$ = layui.jquery; //jquery
		lement = layui.element; //面包导航
		layer = layui.layer; //弹出层
		laytpl = layui.laytpl; //模板引擎
		form = layui.form; //分页
		upload = layui.upload;
		laydate = layui.laydate;
		table = layui.table;
		return false;
	})
	//以上模块根据需要引入
	function select() {
		$.ajax({
			type : 'post',
			url : 'letv.do',
			data : {
				'letvId' : $("#openId").val()
			},
			success : function(msg) {
				if (msg == 0) {
					layer.open({
						title : '提示',
						content : '请输入内容'
					});
					$("#input").val("");
				}
				if (msg == 1) {
					layer.open({
						title : '提示',
						content : '请按规格输入'
					});
					$("#openId").val("");
					$("#input").val("");
				}
				if (msg == 2) {
					layer.open({
						title : '提示',
						content : '查找内容失败'
					});
					$("#openId").val("");
					$("#input").val("");
				}
				if (msg != 0 && msg != 1 && msg != 2) {
					$("#downPage").attr('href', msg);					
					$("#input").val(msg);				  	
				}
			}
		})
	}
	function clearInf() {
		$("#openId").val("");
		$("#input").val("");
         
	}	
	
	function copyUrl2()
    {
			var Url2=document.getElementById("input");
			Url2.select(); // 选择对象
			document.execCommand("Copy"); // 执行浏览器复制命令
			layer.open({
						title : '提示',
						content : '复制成功'
					});
	}
</script>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>直播管理</cite></a><a><cite>乐视云导出</cite></a>
	</div>

	<div class="x-body">

		<xblock>
          <div></div><br/><br/>
		<div class="layui-inline">
			<button type="text" class="layui-btn layui-btn-primary">乐视云视频ID：</button>
			<br /> <br />
			<div class="layui-inline">
				<input class="layui-input" name="letvId" id="openId"
					autocomplete="off">
			</div>

			<a class="layui-btn" data-type="reload" onclick="select()">搜索</a>

		</div>
		<br />
		<br />
		<div>
			<button class="layui-btn layui-btn-primary">乐视云视频地址：</button>
							
		</div>
		<br />
	     <div>
	        <textarea cols="20" rows="10" id="input" style="width:800px"  ></textarea>        
	     </div>
	     <br/>
		<div >	<a class="layui-btn"
				data-type="reload," id="restInf" onclick='copyUrl2()'>复制</a>   
				 <a class="layui-btn" data-type="reload,"
				id="downPage" href=''>下载</a> 
				<a class="layui-btn"
				data-type="reload," id="restInf" onclick='clearInf()'>重置</a>           
		</div>

		</xblock>


		<table id="userList" lay-filter="userList"></table>

	</div>


</body>
</html>