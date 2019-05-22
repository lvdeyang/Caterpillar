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
	  <div class="layui-inline" style="margin-left:20px;">
	    <input class="layui-input" name="id" id="searchtxt" autocomplete="off">
	  </div>
	  <button class="layui-btn" id="search" data-type="reload">搜索</button>
	  <button class="layui-btn" id="online" lay-event="online">上架</button>
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
              
              
              $('#online').click(function(){
                   var checkStatus = table.checkStatus('dataTable');
                   var data = checkStatus.data;
                   var datas=[];
                   for(var i=0;i<data.length;i++){
                       datas.push(data[i].id);
                   }
                   if(datas.length==0){
                        var index=layer.alert("请选择一个文章。", {icon: 2},function () {
		        
		            
		                    layer.close(index);
				        
				        });
                        return false;
                   }
                   var classId=$("#selClassId",window.parent.document).val();
                   $.ajax({
                	  type:"post",
           			  url:"article/addonline.do",
                      data:{ids:datas.join(','),classId:classId},
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("增加成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.layui.table.reload('dataTable');
                           parent.layer.close(index);
                           });
                        }
                        //提示
                        else{
                        
                        }
                       }
                  });
              })
              
              
              
              
              
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
