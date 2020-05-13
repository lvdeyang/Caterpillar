<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
   <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
   <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
   <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
</head>

<body class="layui-layout-body">

   <script type="text/html" id="bar">
       <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>

	
    <div>
	  <div class="layui-inline" style="margin-left:20px;">
	    <input class="layui-input" name="id" id="searchtxt" autocomplete="off">
	  </div>
	  <button class="layui-btn" id="search" data-type="reload">搜索</button>
	  <button class="layui-btn" id="addRecomm" lay-event="addRecomm">添加推荐</button>
	</div>
	<table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>
    
   
	<script>
		//JavaScript代码区域
		layui.use(['table','tree','layer'],function(){
		   var $ = layui.jquery;
		   var layer = layui.layer;
		   
          
          $('#addRecomm').on('click',function(){
             addRecomm('recomm/article/index');
          });
		   
		   
		   
		   function addRecomm(url){
		       
	             layer.open({
			        type: 2 //此处以iframe举例
			        ,title: '添加推荐'
			        ,area: ['1200px', '600px']
			        ,shade: 0
			        ,maxmin: true
			        ,offset: [ //为了演示，随机坐标
			         50,300
			          
			        ] 
			        ,content: url
			        //layer.closeAll();
			        ,zIndex: layer.zIndex //
			        ,success: function(layero){
			          layer.setTop(layero); //
			        }
			      });
		   
		   }
		   
		   
		   
		   
		   var table = layui.table;
		    $('#search').on('click',function(){
                  
              
              });
              
              
			  table.render({
			    elem: '#dataTable'
			    ,url:'recomm/list'
			    ,title: '推荐列表'
			    ,cols: [[
			      {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'contentName', title:'内容名称', width:500, edit: 'text'}
			      ,{field:'moudular', title:'所属板块', width:200, edit: 'text'}
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
			              url:'recomm/del.do',
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
