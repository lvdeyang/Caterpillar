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

   <div class="layui-row" style="margin-top:10px;margin-left:10px;">
    <div class="layui-col-xs2">
      <div class="grid-demo grid-demo-bg1">
         <button id="addClasses" class="layui-btn layui-btn-primary layui-btn-sm">添加</button>
         <button class="layui-btn layui-btn-primary layui-btn-sm">删除</button>
         <ul id="classes" style="margin-top:10px;"></ul>


      </div>
    </div>
    <div class="layui-col-xs10">
      <div class="grid-demo">10/12</div>
    </div>
   
  </div>
    
   
	<script>
		//JavaScript代码区域
		layui.use(['table','tree','layer'],function(){
		   var $ = layui.jquery;
		   var layer = layui.layer;
		   $.ajax({
              url:'classes/list',
              type:'get',
              data:{},
              success:function(data){
                 layui.tree({
				  elem: '#classes' //传入元素选择器
				  ,nodes:data.list
				});
              },
              complete: function () {
                 
              },
          });
          
          $('#addClasses').on('click',function(){
             layer.open({
		        type: 2 //此处以iframe举例
		        ,title: '添加分类'
		        ,area: ['500px', '300px']
		        ,shade: 0
		        ,maxmin: true
		        ,offset: [ //为了演示，随机坐标
		         200,300
		          
		        ] 
		        ,content: ''
		        //layer.closeAll();
		        ,zIndex: layer.zIndex //
		        ,success: function(layero){
		          layer.setTop(layero); //
		        }
		      });
          
          });
		   

           
			
		});
	</script>
</body>
</html>
