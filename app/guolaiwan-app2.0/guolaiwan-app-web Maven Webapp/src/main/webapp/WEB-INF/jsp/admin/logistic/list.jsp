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
<title>物流管理</title>
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
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>物流管理</cite></a>
		</span> <a class="layui-btn layui-btn-small merflash"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">

		<xblock>
		<!-- <button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button> -->
		<button class="layui-btn" onclick="logistic_add('添加物流','addv','400','400')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right lheight4">共有 ${all} 条数据</span></xblock>
		<table class="layui-table" id="logisticList" lay-filter="logisitcList"></table>

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
	
			getLogisticList();
	
		});
	
	
	      function full_show(title,url,id,w,h) {
		   var index = layer.open({
		     type: 2,
		     area: [w+'px', h +'px'],
			  fix: false, //不固定
			  maxmin: true,
			  shadeClose: true,
			  shade:0.4,
			  title: title,
			  content: url+"?uuid="+id
			}); 
		   layer.full(index);
		 }
 
		function logistic_add(title, url, w, h) {
			x_admin_show(title, url, w, h);
		}
	
		function logistic_update(url,id) {
			x_admin_show('修改',url+"/"+id, '400', '400');
		}
	
		//获取商家列表
		function getLogisticList() {
			table.render({ /*3.引入table*/
				elem : '#logisticList',
				method : 'post',
				url : 'getLogisticList.do',
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
						title : '名称',
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
		
		
		function logistic_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                  layer.close(index);
                  layer.load();
                  //发异步删除数据
                  $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"id":id},
                    success:function(msg){
                  	 if(msg=="success"){
                        layer.closeAll("loading");
                  		  $(obj).parents("tr").remove();
                  		  layer.msg('已删除!',{icon:1,time:1000});
                  	 }
                    }
                  }) 
                });
            }
		
	</script>
	<script type="text/html" id="handle"> 
	<a title="修改" href="javascript:;" onclick="logistic_update('updateLogistic','{{d.id}}')" style="text-decoration:none"><i class="layui-icon">&#xe642;</i></a>
    <a title="删除" href="javascript:;" onclick="logistic_del(this,'{{ d.id }}')" style="text-decoration:none"><i class="layui-icon">&#xe640;</i></a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" href="javascript:;" onclick="full_show('网点列表','pointlist','{{ d.id }}','400','600')">网点管理</a>
</script>
</body>
</html>