<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/image";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>过来玩管理平台系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
<style type="text/css">
.layui-table img {
	max-width: 1000px;
} /* 照片的最大宽度  */
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>评论管理</cite></a>
	</div>
	<div class="x-body">
		<xblock> <a class="layui-btn layui-btn-sm"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:getMessageList();" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a> </xblock>
		<table id="liveList" lay-filter="liveList"></table>
	</div>

	<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"
		charset="utf-8"></script>
	<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"
		charset="utf-8"></script>

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
	
			getMessageList();
		});
	
		//获取列表
		function getMessageList() {
			var a = '${liveId}';
			table.render({
				elem : '#liveList',
				method : 'post',
				url : 'message.do?liveId=' + a,
				page : false,
				limits : [ 10, 30, 50, 100 ],
				limit : 10,
				cols : [ [
					{
						type : 'checkbox'
					}
					, {
						field : 'id',
						title : '评论Id',
						align : 'center',
						sort : true,
						width : 250,
						edit : 'text'
					}
					, {
						field : 'liveId',
						title : '直播Id',
						align : 'center',
						sort : true,
						width : 250,
						edit : 'text'
					}
					, {
						field : 'userId',
						title : '用户Id',
						align : 'center',
						sort : true,
						width : 250,
						edit : 'text'
					}
					, {
						field : 'userName',
						title : '用户名',
						align : 'center',
						sort : true,
						width : 250
					}
					, {
						field : 'message',
						title : '评论内容',
						align : 'center',
						sort : true,
						width : 250
					}
					, {
						field : 'deleteMessage',
						title : '删除',
						align : 'center',
						sort : true,
						width : 120,
						templet : '#delect'
					}
				] ]
			})
		}
	
		//删除
		function del(id) {
			layer.confirm("真的要删除么？", function(index) {
				layer.close(index);
				layer.load();
				$.ajax({
					url : 'delMessage.do',
					type : 'post',
					data : {
						'Id' : id
					},
					success : function(msg) {
						console.log(msg);
						layer.closeAll('loading');
						if (msg == 'success') {
							layer.msg("删除成功！");
							getMessageList();
						}
					}
				})
			})
		}
	</script>

	<script type="text/html" id="delect">
	<a class='layui-btn layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
</script>



</body>
</html>