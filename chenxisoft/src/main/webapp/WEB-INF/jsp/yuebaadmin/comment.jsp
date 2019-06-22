<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>
</head>

<body class="layui-layout-body">

        <script type="text/html" id="bar">
              <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	    </script>
	    
		<table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>

	<script>
		//JavaScript代码区域
		layui.use(['table','tree','layer'],function(){
			  var $ = layui.jquery;
			  var layer = layui.layer;
		      var workerId=${workerId};
	        
		  
		      var table = layui.table;
			  table.render({
			    elem: '#dataTable'
			    ,url:'/chenxisoft/comment/list?workerId='+workerId
			    ,title: '标签列表'
			    ,cols: [[
			       {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'content', title:'内容', width:400, edit: 'text'}
			      ,{field:'answer', title:'回复', width:400, edit: 'text'}
			      ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
			    ]]
			    ,page: true
			  });
			  
			  
			  table.on('tool(dataTable)', function(obj){
			    var data = obj.data;
			    //console.log(obj)
			    if(obj.event === 'del'){
			      layer.confirm('真的删除行么', function(index){
			         
			         
			         $.ajax({
			              url:'comment/del.do',
			              type:'post',
			              data:{id:data.id},
			              success:function(data){
			                 obj.del();
			        		 layer.close(index);
			              }
			          });
			      });
			    } 
			    
			  });  
			  
			  
			  
			  $('#deleteClasses').on('click',function(){
		  		  $.ajax({
		              url:'classes/del.do',
		              type:'post',
		              data:{id:$('#selClassId').val()},
		              success:function(data){
		                 window.location.reload();
		              }
		          });
			  });

          
			
		});
	</script>
</body>
</html>
