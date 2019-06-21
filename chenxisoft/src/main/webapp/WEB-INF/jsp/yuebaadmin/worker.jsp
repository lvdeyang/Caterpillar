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
              <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
              <a class="layui-btn layui-btn-xs" lay-event="comment">查看评论</a>
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
			    ,url:'/chenxisoft/worker/list'
			    ,title: '标签列表'
			    ,cols: [[
			       {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'realName', title:'姓名', width:100, edit: 'text'}
			      ,{field:'idCard', title:'身份证号', width:200, edit: 'text'}
			      ,{field:'phone', title:'电话', width:150, edit: 'text'}
			      ,{field:'address', title:'地址', width:250, edit: 'text'}
			      ,{field:'age', title:'年龄', width:100, edit: 'text'}
			      ,{field:'price', title:'价格', width:100, edit: 'text'}
			      ,{field:'status', title:'状态', width:200, edit: 'text'}
			      ,{fixed: 'right', title:'操作', toolbar: '#bar', width:200}
			    ]]
			    ,page: true
			  });
			  
			  
			  table.on('tool(dataTable)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				         $.ajax({
				              url:'worker/del.do',
				              type:'post',
				              data:{id:data.id},
				              success:function(data){
				                 obj.del();
				        		 layer.close(index);
				              }
				          });
				      });
				    } 
				    else if(obj.event==='check'){
				        layer.open({
					        type: 2 //此处以iframe举例
					        ,title: '审核'
					        ,area: ['500px', '300px']
					        ,shade: 0
					        ,maxmin: true
					        ,offset: [ //为了演示，随机坐标
					         200,300
					          
					        ] 
					        ,content: 'worker/check?id='+data.id
					        //layer.closeAll();
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
