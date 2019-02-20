<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>语言管理</title>
<meta name="renderer" content="webkit">
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
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>语言管理</cite></a>
		</span> <a class="layui-btn layui-btn-small merflash"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">

		<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<button class="layui-btn" onclick="lan_add('添加语言','addv','900','750')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right lheight4">共有 ${alllan} 种语言</span></xblock>
		<table class="layui-table" id="lanList" lay-filter="lanList"></table>

	</div>
	<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		function distributor_check(title, url, id, w, h) {
			x_admin_show(title, url + "?id=" + id, w, h);
		}
	
		layui.use([ 'element', 'laypage', 'layer', 'laytpl', 'table' ], function() {
			$ = layui.jquery; //jquery
			lement = layui.element; //面包导航
			laypage = layui.laypage; //分页
			layer = layui.layer; //弹出层
			laytpl = layui.laytpl; //模板引擎
			table = layui.table;
			//以上模块根据需要引入
	
			getLanList();
	
		});
	
		function lan_add(title, url, w, h) {
			x_admin_show(title, url, w, h);
		}
	
		function lan_update(url,id) {
			x_admin_show('修改',url+"/"+id, '900', '750');
		}
	
		//获取商家列表
		function getLanList() {
			table.render({ /*3.引入table*/
				elem : '#lanList',
				method : 'post',
				url : 'getLanList.do',
				page : true,
				limits : [ 10, 30, 50, 100 ],
				limit : 10,
				height : 'full',
				cols : [ [
					{
						type : 'checkbox'
					}
					, {
						field : 'id',
						title : 'ID',
						sort : true,
						width : 60
					}
					, {
						field : 'name',
						title : '语种',
						sort : true
					}
					, {
						fixed : '',
						align : 'center',
						title : '操作',
						width : 120,
						minWidth : 100,
						templet : '#handle',
						unresize : true
					}
				] ]
			});
		}
	</script>
	<script type="text/html" id="handle"> 
	<a title="修改" href="javascript:;" onclick="lan_update('updateLan','{{d.id}}')" style="text-decoration:none"><i class="layui-icon">&#xe642;</i></a>
    <a title="删除" href="javascript:;" onclick="pro_del(this,'{{ d.uuid }}')" style="text-decoration:none"><i class="layui-icon">&#xe640;</i></a>
</script>
</body>
</html>