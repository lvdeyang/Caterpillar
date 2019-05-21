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
       <script type="text/html" id="bar">
              <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	   </script>
	    <div>
		      搜索：
		  <div class="layui-inline">
		    <input class="layui-input" name="id" id="searchtxt" autocomplete="off">
		  </div>
		  <button class="layui-btn" id="search" data-type="reload">搜索</button>
		  <label class="layui-form-label" id="selClassName">未选择分类</label>
		  <input type="hidden" id="selClassId" value="0"/>
		</div>
		<table class="layui-hide" id="dataTable" lay-filter="dataTable"></table>
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
				  ,click: function(node){
                    
				    console.log(node) //node即为当前点击的节点数据
				    $('#selClassName').html(node.name);
				    $('#selClassId').val(node.id);
				    
				    table.reload('dataTable', {
				        page: {
				          curr: 1 //重新从第 1 页开始
				        }
				        ,where: {
				          key: {
				            classId:$('#selClassId').val()
				          }
				        }
				    });
				    
				    
				  }  
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
		        ,content: 'classes/add'
		        //layer.closeAll();
		        ,zIndex: layer.zIndex //
		        ,success: function(layero){
		          layer.setTop(layero); //
		        }
		      });
          
          });
		   
		   
		   var table = layui.table;
		    $('#search').on('click',function(){
                  
              
              });
              
              
			  table.render({
			    elem: '#dataTable'
			    ,url:'/chenxisoft/onlineclass/list?classId='+$('#selClassId').val()
			    ,title: '上架列表'
			    ,cols: [[
			      {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
			      ,{field:'contentName', title:'内容名称', width:500, edit: 'text'}
			      ,{field:'contentMouduler', title:'所属板块', width:200, edit: 'text'}
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
