<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>阳光成单系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>

	

	<div class="x-body" style="height:1500px">
	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
		<xblock>
		<button type="button" class="layui-btn layui-btn-normal" onclick="addBiao('添加','add','500','600')">添加表</button>
		</xblock>
		<table id="biaoList" lay-filter="biaoList">
		
		</table>

	
	</div>

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
		
	layui.use(['element','layer','laytpl','upload','laydate','laypage','table'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        laypage = layui.laypage;//分页
        upload = layui.upload;
        laydate = layui.laydate;
        table = layui.table;

        getBiaoList();
        table.on('edit(biaoList)',function(obj){
        	layer.load();
        	var data = obj.data
        	,field = obj.field
        	,value = obj.value;
			console.log(value);
        	$.ajax({
        		type:'post',
        		url:'edit.do',
        		data:{'id':data.id,'field':field,'value':value},
        		success:function(msg){
        		console.log(msg);
        			layer.closeAll("loading");
        			if(msg=="success"){
        				layer.msg("修改成功！");
        			}
        		}
        	})
        })

      
});


	function getBiaoList(){
		table.render({
			elem:'#biaoList'
   			,method:'post'
   			,url:'list.do'
   			,page:true
      		,limits: [10,30,50,100]
      		,limit: 10
   			,cols: [[
        		{type:'checkbox'}
        		,{field: 'id', align:'center',title: 'ID',align:  'center',sort: true,width:60}  
   				,{field: 'name',align:'center', title: '表名',sort: true,edit:'text'}  
   				,{field: 'enName',align:'center', title: '中文名',sort: true,edit:'text'}       
      			,{field: 'modular',align:'center', title: '分类',sort: true, edit:'text'}
      			,{fixed:'right',width:200,title:'操作',unresize:true,templet:"#zsgc"}
   			]]
		})
	}
	function addBiao(title,url,w,h){
		x_admin_show(title,url,w,h);
	}
	function del(uuid){
		layer.load();
		$.ajax({
			url:'del.do',
			type:'post',
			data:{'uuid':uuid},
			success:function(msg){
				console.log(msg);
				layer.closeAll('loading');
				if(msg=='success'){
					layer.msg("删除成功！");
					getBiaoList();
				}	
			
			}
		
		})
		console.log(uuid);
	}

  
	         

</script>
<script type="text/html" id="zsgc">
	<a class='layui-btn layui-btn-primary layui-btn-xs' href=''>查看字段</a>
	<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.uuid }}")'>删除</a>
</script>

</body>
</html>