<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>停车场车位管理列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> 
			<a><cite>首页</cite></a> 
			<a><cite>停车场管理</cite></a>
			<a><cite>${po.parkingName}-车位管理</cite></a>
		</span> 
	</div>
	<div class="x-body">
		<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:38px">ဂ</i></a>
		<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:left;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>
		<button class="layui-btn"  style="margin-top:3px;float:left"; id="sptj" onclick="parkposition_add('添加车位','<%=path%>/admin/parkmanagement/parkaddv','${po.id}','600','450')"><i class="layui-icon">&#xe608;</i> 添加车位 </button>
		<div>
			<table class="layui-table" id="parkPositionList"></table>
		</div>

<!-- 所属停车场 -->
<script id="parkingNameTpl" type="text/html">      
	${ po.parkingName }
</script>
<script id="addressTpl" type="text/html">      
	${ po.address }
</script>
<script id="isUsedTpl" type="text/html">      
	{{#  if(d.useCondition === 0){ }}
		{{ "无车未使用" }}
	{{#  }else if(d.useCondition == 1){ }}
		{{ "有车使用中" }}
	{{#  } else { }}
		{{ "暂无数据" }}
	{{#  } }}
</script>
<!-- 工具条-->
<script type="text/html" id="zsgc">  
	<a title="修改" href="javascript:;" onclick="parkposition_edit('修改车位','parkupdatev','{{ d.uuid }}','','510')" class="tdn">
		<i class="layui-icon">&#xe642;</i>
	</a>
	<a title="删除" href="javascript:;" onclick="parkposition_del(this,'{{ d.uuid }}')"  class="tdn">
		<i class="layui-icon">&#xe640;</i>
	</a>
</script>
	
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		var parkingId = '${po.id}';
		var has = '${has}';
		var path = '<%=path%>';
		layui.use(['element','laypage', 'layer','laytpl','form','table'], function(){
			$ = layui.jquery;//jquery
			var form = layui.form;//表单
				lement = layui.element;//面包导航
				laypage = layui.laypage;//分页
				layer = layui.layer;//弹出层
				laytpl = layui.laytpl;//模板引擎
				table = layui.table; 

			getParkPosition();
			// childISV();
		});
		
		function openMap(title,url,w,h){
	         x_admin_show(title,url,w,h); 
		}
		function parkposition_add(title,url,id,w,h){
	         x_admin_show(title,url+"?positionid="+id,w,h); 
		}
		// TODO
		function parkposition_edit (title,url,id,w,h) {
		    x_admin_show(title,url+"?uuid="+id,w,h); 
		}
		function parkposition_del(obj,uuid){
			layer.confirm('确认要删除吗？',function(index){
				layer.close(index);
				layer.load();
				//发异步删除数据
				$.ajax({
					type:"post",
					url:"parkdel.do",
					data:{"uuid":uuid},
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
		//判断是否有商品分类
		function childISV(){
			if(has==0){
				layer.msg('该停车场还没有车位！', {time: 1000 },function(){
					$("#sptj").click();
				})
			}
		}
		function getParkPosition(){
			table.render({
				elem: '#parkPositionList'
				,id: 'parkPositionList'
				,method:'post'
   				,where:{"positionid":parkingId}
   				,url:'parkpositionlist.do'
   				,page:true
   				,limits: [10, 20, 30]
   				,height:'full'
            	,limit: 10
				,cols: [[
					{field: 'id', title: 'ID', width: 80, sort: true}
					,{field: 'positionNumber', title: '车位编号', width: 120, sort: true}
					,{field: 'positionInformation', title: '车位信息'}
					,{field: 'useCondition', title: '使用情况',templet:'#isUsedTpl',sort: true}
					,{field: 'parkingName', title: '所属停车场',templet:'#parkingNameTpl'}
					,{field: 'address', title: '所属停车场地址',templet:'#addressTpl'}
					,{fixed: 'right',title: '操作',minWidth:100,templet:'#zsgc',unresize:true}
				 ]]
				 ,done:function(res, curr, count){
				 	layer.closeAll("loading");
				}
			});
		}
		
		
	</script>
</body>
</html>