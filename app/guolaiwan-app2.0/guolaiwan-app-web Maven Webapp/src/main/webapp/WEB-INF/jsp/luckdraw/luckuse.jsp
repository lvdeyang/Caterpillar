<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>已兑奖名单</title>
<meta name="rende rer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/x-admin.css"
	media="all">
</head>

<body>
	<div class="x-body">

		
		<table class="layui-table" id="List" lay-filter="List"></table>
	</div>
	<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		layui.use([ 'element', 'laypage', 'layer', 'laytpl', 'table', 'form', 'laydate' ], function() {
			$ = layui.jquery; //jquery
			lement = layui.element; //面包导航
			layer = layui.layer; //弹出层
			laypage = layui.laypage; //分页
			laytpl = layui.laytpl; //模板引擎
			table = layui.table;
			form = layui.form;
			laydate = layui.laydate;
	        
			getuseitList();
			
			return false;
	
		});
	
	
		function getuseitList() { //加载
			var index = layer.load(1);
			table.render({
				elem : "#List",
				method : "post",
				url : "list1.do",
				page : true,
				loading : true,
				limits : [ 10, 30, 50, 100 ],
				limit : 10,
				id : 'mList',
				cols : [ [ {
					field : 'id',
					title : 'ID',
					align : 'center',
					sort : true
				}
					, {
						field : 'updateTime',
						align : 'center',
						title : '中奖时间',
						sort : true
					}
					, {
						field : 'uuid',
						align : 'center',
						title : '兑换码',
						sort : true
					}
					, {
						field : 'drawProductId',
						align : 'center',
						title : '奖品',
						sort : true
					}
					, {
						field : 'phone',
						align : 'center',
						title : '手机号',
						sort : true
					}
					, {
						field : 'userName',
						align : 'center',
						title : '姓名',
						sort : true
					} 
				] ],
				done : function(res) { //返回数据执行回调函数
					layer.close(index); //返回数据关闭loading
	
				}
			})
		}
	
		
	</script>
</body>
</html>
