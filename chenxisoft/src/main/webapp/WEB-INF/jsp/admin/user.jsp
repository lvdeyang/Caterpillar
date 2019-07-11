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
              {{#
                 if(d.adminFlg==0){
              }}
              <a class="layui-btn layui-btn-xs" lay-event="setAdmin">设置管理员</a>
              {{#
                 } else{
              }}
              
              <a class="layui-btn layui-btn-xs" lay-event="cancelAdmin">取消管理员</a>
              {{#
                 }
              }}
	   </script>
	   <table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>

   

    
   
	<script>
		//JavaScript代码区域
		layui.use(['table','tree','layer'],function(){
			  var $ = layui.jquery;
			  var layer = layui.layer;
		  

		      var table = layui.table;
			  table.render({
			    elem: '#dataTable'
			    ,url:'/chenxisoft/user/list'
			    ,title: '用户列表'
			    ,cols: [[
			       {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'nickName', title:'姓名', width:100, edit: 'text'}
			      ,{field:'openId', title:'openId', width:200, edit: 'text'}
			      ,{field:'phone', title:'电话', width:150, edit: 'text'}
			      ,{field:'address', title:'地址', width:250, edit: 'text'}
			      ,{fixed: 'right', title:'操作', toolbar: '#bar', width:330}
			    ]]
			    ,page: true
			  });
			  
			  
			  table.on('tool(dataTable)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'setAdmin'){
				      layer.confirm('是否设置为管理员', function(index){
				         $.ajax({
				              url:'user/setAdmin.do',
				              type:'post',
				              data:{userId:data.id},
				              success:function(data){
				                 location.reload();
				        		 layer.close(index);
				              }
				          });
				      });
				    } else if(obj.event === 'cancelAdmin'){
				      layer.confirm('是否取消管理员', function(index){
				         $.ajax({
				              url:'user/cancelAdmin.do',
				              type:'post',
				              data:{userId:data.id},
				              success:function(data){
				                 location.reload();
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
