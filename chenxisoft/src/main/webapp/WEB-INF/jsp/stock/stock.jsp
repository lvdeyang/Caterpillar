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
              <a class="layui-btn layui-btn-xs" lay-event="finance">FINANCE MANAGE</a>
	   </script>
	    <div>

		  <button style="margin-left:10px;" class="layui-btn" id="add">添加</button>

		</div>
		<table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>

   

    
   
	<script>
		//JavaScript代码区域
		layui.use(['table','tree','layer'],function(){
			  var $ = layui.jquery;
			  var layer = layui.layer;
		  
	          $('#add').on('click',function(){
	             layer.open({
			        type: 2 //此处以iframe举例
			        ,title: 'ADDSTOCK'
			        ,area: ['500px', '300px']
			        ,shade: 0
			        ,maxmin: true
			        ,offset: [ 
			         200,300
			          
			        ] 
			        ,content: 'stock/add'
			        ,zIndex: layer.zIndex //
			        ,success: function(layero){
			          layer.setTop(layero); //
			        }
			      });
	          
	          });
			   
		  
		      var table = layui.table;
			  table.render({
			    elem: '#dataTable'
			    ,url:'/chenxisoft/stock/list'
			    ,title: 'STOCK'
			    ,cols: [[
			       {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'code', title:'CODE', width:100, edit: 'text'}
			      ,{field:'name', title:'NAME', width:200, edit: 'text'}
			      ,{fixed:'right', title:'操作', toolbar: '#bar', width:300}
			    ]]
			    ,page: true
			  });
			  
			  
			  table.on('tool(dataTable)', function(obj){
			    var data = obj.data;
			    //console.log(obj)
			    if(obj.event === 'del'){
			      layer.confirm('真的删除行么', function(index){
			         
			         
			         $.ajax({
			              url:'stock/del.do',
			              type:'post',
			              data:{id:data.id},
			              success:function(data){
			                 obj.del();
			        		 layer.close(index);
			              }
			          });
			      });
			    } 
			    else if(obj.event === 'finance'){
			      layer.open({
			        type: 2 //此处以iframe举例
			        ,title: 'FINANCEMANAGE'
			        ,area: ['98%', '98%']
			        ,shade: 0
			        ,maxmin: true
			        ,offset: [ 
			         0,0
			          
			        ] 
			        ,content: 'stock/finance/index?stockId='+data.id
			        ,zIndex: layer.zIndex //
			        ,success: function(layero){
			          layer.setTop(layero); //
			        }
			      });
			    } 
			    
			  });  
			  
			  
			  
			  

          
			
		});
	</script>
</body>
</html>
