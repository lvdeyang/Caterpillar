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
              <!--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="subOrder">结算订单</a>-->
	   </script>
	  
		<table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>

   

    
   
	<script>
		//JavaScript代码区域
		layui.use(['table','tree','layer'],function(){
			  var $ = layui.jquery;
			  var layer = layui.layer;
		  
	          $('#add').on('click',function(){
	             layer.open({
			        type: 2 //此处以iframe举例
			        ,title: '添加标签'
			        ,area: ['500px', '300px']
			        ,shade: 0
			        ,maxmin: true
			        ,offset: [ //为了演示，随机坐标
			         200,300
			          
			        ] 
			        ,content: 'label/add'
			        ,zIndex: layer.zIndex //
			        ,success: function(layero){
			          layer.setTop(layero); //
			        }
			      });
	          
	          });
			   
		  
		      var table = layui.table;
			  table.render({
			    elem: '#dataTable'
			    ,url:'/chenxisoft/order/list'
			    ,title: '标签列表'
			    ,cols: [[
			       {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'workName', title:'月嫂', width:200, edit: 'text'}
			      ,{field:'userName', title:'用户', width:200, edit: 'text'}
			      ,{field:'userPhone', title:'用户电话', width:200, edit: 'text'}
			      ,{field:'days', title:'天数', width:200, edit: 'text'}
			      ,{field:'region', title:'地区', width:200, edit: 'text'}
			      ,{field:'fromDate', title:'开始时间', width:200, edit: 'text'}
			      ,{field:'price', title:'佣金', width:200, edit: 'text'}
			      ,{field:'mleft', title:'提成', width:200, edit: 'text'}
			      ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
			    ]]
			    ,page: true
			  });
			  
			  
			  table.on('tool(dataTable)', function(obj){
			    var data = obj.data;
			    //console.log(obj)
			    if(obj.event === 'subOrder'){
			      
			    } 
			    
			  });  
			  
			  
			  
			
          
			
		});
	</script>
</body>
</html>
