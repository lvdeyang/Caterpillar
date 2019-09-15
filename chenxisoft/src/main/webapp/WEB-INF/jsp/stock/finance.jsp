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
			        ,title: 'ADDFINANCE'
			        ,area: ['800px', '600px']
			        ,shade: 0
			        ,maxmin: true
			        ,offset: [ 
			         200,200
			          
			        ] 
			        ,content: 'stock/finance/add?stockId=${stockId}'
			        ,zIndex: layer.zIndex //
			        ,success: function(layero){
			          layer.setTop(layero); //
			        }
			      });
	          
	          });
			   
		  
		      var table = layui.table;
			  table.render({
			    elem: '#dataTable'
			    ,url:'/chenxisoft/stock/finance/list?stockId=${stockId}'
			    ,title: 'STOCK'
			    ,cols: [[
			       {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'year', title:'YEAR', width:100, edit: 'text'}
			      ,{field:'allget', title:'营收', width:200, edit: 'text'}
			      ,{field:'fleft', title:'利润', width:200, edit: 'text'}
			      ,{field:'realLeft', title:'净利', width:200, edit: 'text'}
			      ,{field:'returnassets', title:'净资产收益率', width:200, edit: 'text'}
			      ,{field:'realcash', title:'营业现金', width:200, edit: 'text'}
			      ,{field:'postcash', title:'投资现金', width:200, edit: 'text'}
			      ,{field:'getcash', title:'融资现金', width:200, edit: 'text'}
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
			              url:'stock/finance/del.do',
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
			  
			  
			  
			  

          
			
		});
	</script>
</body>
</html>
