<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>板块管理</title>
<meta name="rende rer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/x-admin.css"
	media="all">
<style type="text/css">
.layui-table-cell {
	height: 50px;
	line-height: 50px;
}
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>系统管理</cite></a>
			<a><cite>板块管理</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<button class="layui-btn"
			onclick="modular_add('添加模块','addv','','510')">
			<i class="layui-icon">&#xe608;</i>添加模块
		</button>
		<span class="x-right" style="line-height:40px">共有数据：${allcount}
			条</span></xblock>
		<table id="modularList" lay-filter="modularList"></table>
	</div>


	<script type="text/html" id="mktpTpl">
    <div><img width="50px" height="50px" alt="" src= "{{ d.modularPic}}"></div>    
</script>

	<script type="text/html" id="zsgc">
    <div style="cursor: pointer;">
    <a title="编辑" lay-event ="edit"><i class="layui-icon">&#xe642;</i></a> 
    <a title="删除" lay-event ="del"><i class="layui-icon">&#xe640;</i></a> 
    </div>
</script>
	<script type="text/html" id="switchTpl">
  <input type="checkbox" name="modularIsv" id='{{d.id}}' value="{{d.modularName}}" lay-skin="switch" lay-text="是|否" lay-filter="modularIsv" {{ d.modularIsv == 1 ? 'checked' : '' }}>
</script>
	<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
	
		layui.use([ 'element', 'laypage', 'layer', 'laytpl', 'table', 'form' ], function() {
			$ = layui.jquery; //jquery
			lement = layui.element; //面包导航
			layer = layui.layer; //弹出层
			laypage = layui.laypage; //分页
			laytpl = layui.laytpl; //模板引擎
			table = layui.table;
			form = layui.form;
	
			//模块列表
			getmodularList();
	
			form.on('switch(modularIsv)', function(obj) {
	
				layer.load();
	
	
	
	
				var str;
				var bl = obj.elem.checked;
				var val;
				if (bl) {
					str = "显示";
					val = 1;
				} else {
					str = "不显示";
					val = 0;
				}
				$.ajax({
					type : 'post',
					url : 'changeIsv.do',
					data : {
						'id' : this.id,
						'val' : val
					},
					success : function(msg) {
						layer.closeAll("loading");
						if (msg == 'success') {
	
						} else {
							layer.msg("系统错误！", {
								icon : 5,
								time : 1000
							});
						}
					}
				})
	
				layer.tips(this.value + ' : ' + str, obj.othis);
			});
	
	
	
			table.on('tool(modularList)', function(obj) {
				var data = obj.data;
				if (obj.event === 'del') {
					layer.confirm('确认要删除' + data.modularName + '吗？', function(index) {
						layer.load();
						//发异步删除数据
						$.ajax({
							type : "post",
							url : "del.do",
							data : {
								"uuid" : data.uuid
							},
							success : function(msg) {
								layer.closeAll("loading");
								if (msg == "success") {
									layer.msg("删除成功！", {
										icon : 6,
										time : 500
									}, function() {
										layer.close(index);
										getmodularList();
	
									});
								}
							}
						})
					});
				} else if (obj.event === 'edit') {
					modular_edit('编辑', 'updatev', data.uuid, '', '510');
				}
			})
	
		});
	
		function full_show(title, url, id, w, h) {
			var index = layer.open({
				type : 2,
				area : [ w + 'px', h + 'px' ],
				fix : false, //不固定
				maxmin : true,
				shadeClose : true,
				shade : 0.4,
				title : title,
				content : url + "?uuid=" + id
			});
			layer.full(index);
		}
	
	
		/*添加*/
		function modular_add(title, url, w, h) {
			x_admin_show(title, url, w, h);
		}
	
		//编辑
		function modular_edit(title, url, id, w, h) {
			x_admin_show(title, url + "?uuid=" + id, w, h);
		}
	
	
		function getmodularList() {
			table.render({
				elem : '#modularList',
				method : 'post',
				url : 'list.do',
				page : true,
				limits : [ 5, 10, 30 ],
				limit : 5,
				style : 'padding:0;margin:0',
				cols : [ [
					{
						type : 'checkbox'
					}
					, {
						field : 'modularCode',
						title : '模块标识',
						align : 'center',
						sort : true,
					}
					, {
						field : 'modularName',
						title : '模块名称',
						align : 'center',
						sort : true,
					}
					, {
						title : '模块图片',
						align : 'center',
						templet : '#mktpTpl'
					}
					, {
						field : 'modularIsv',
						title : '显示',
						align : 'center',
						templet : '#switchTpl',
						unresize : true
					}
					, {
						field : 'sortindex',
						title : '排序',
						align : 'center',
						sort : true
					}
					, {
						field : 'updateTime',
						title : '修改时间',
						align : 'center',
						sort : true
					}
					, {
						fix : 'right',
						toolbar : '#zsgc',
						width : 80,
						unresize : true
					}
				] ]
			})
		}
	</script>



</body>
</html>