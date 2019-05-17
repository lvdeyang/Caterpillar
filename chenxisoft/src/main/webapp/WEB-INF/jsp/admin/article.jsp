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
	       搜索：
	  <div class="layui-inline">
	    <input class="layui-input" name="id" id="searchtxt" autocomplete="off">
	  </div>
	  <button class="layui-btn" id="search" data-type="reload">搜索</button>
	</div>
	<table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>
    
	<script>
		//JavaScript代码区域
		layui.use('table',function(){
		   var $ = layui.jquery;
		   var table = layui.table;
  
              
              $('#search').on('click',function(){
                  table.reload('dataTable', {
			        page: {
			          curr: 1 //重新从第 1 页开始
			        }
			        ,where: {
			          key: {
			            content:$('#searchtxt').val()
			          }
			        }
			      });
              
              });
              
              
			  table.render({
			    elem: '#dataTable'
			    ,url:'/chenxisoft/article/list'
			    ,title: '文章列表'
			    ,cols: [[
			      {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'title', title:'主题', width:300, edit: 'text'}
			      ,{field:'autor', title:'作者', width:200, edit: 'text', templet: function(res){
			        return '<em>'+ res.autor +'</em>'
			      }}
			      ,{field:'updateTime', title:'生产时间', width:180, edit: 'text', sort: true}
			      ,{field:'source', title:'来源', width:200}
			      ,{fixed: 'right', title:'操作', toolbar: '#bar', width:150}
			    ]]
			    ,page: true
			  });
			  
			  
			  table.on('tool(dataTable)', function(obj){
			    var data = obj.data;
			    //console.log(obj)
			    if(obj.event === 'del'){
			      layer.confirm('真的删除行么', function(index){
			        obj.del();
			        layer.close(index);
			      });
			    } 
			    
			  });  
			
		});
	</script>
</body>
</html>
