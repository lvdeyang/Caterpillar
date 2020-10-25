<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            打卡点管理
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    <style type="text/css">
	.layui-table img{     max-width: 1000px;}/* 照片的最大宽度  */
	</style>
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>保安公司</cite></a>
              <a><cite>打卡点列表</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="open_win('添加打卡点','addv?comId=${id}','1000','600')"><i class="layui-icon">&#xe608;</i>添加打卡点</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="pointList" lay-filter="pointList"></table>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        var id = '${id}';
        
           
            layui.use(['element','laypage','layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;
              table = layui.table;//模板引擎
              //以上模块根据需要引入
              
              getpointList();
              
              
             
              table.on("edit(pointList)",function(obj){
              		//加载
              		layer.msg('加载中', {
 						 icon: 16
  						,shade: 0.01
					})
              		var data = obj.data,
              		value = obj.value,
              		field = obj.field;
              		
              		$.ajax({
              			type:"post",
              			url:"edit.do",
              			data:{"id":data.id,"value":value,"field":field},
              			success:function(msg){
              				layer.closeAll("loading");
              				if(msg=="success"){
              					layer.msg("修改成功！",{icon:1});
              				}else{
              					layer.msg("系统错误！",{icon:2});
              				}
              			
              			}
              		})
              })
            });
	         
	         function getpointList(){
	         	table.render({
            		elem:"#pointList"
            		,method:'post'
   					,url:'list.do?comId='+id
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field:"name",title:"名称"}
   						,{field:"x",title:"经度",width:160}
   						,{field:"y",title:"纬度",width:160}
   						,{field:"distance",title:"打卡范围",width:160}
   						,{field:"type",title:"打卡类型",width:160}
   						,{title:"操作",templet:"#zsgc"}
   						]]
   					,done:function(res, curr, count){
   						$("#allcount").text(count);
   					}
            	})
	         }
	         
	         
	         function open_win(title,url,w,h){
	         	x_admin_show(title,url,w,h);
	         }
	         
	         
	        
    		
    		//删除
	function del(id){
		layer.confirm("真的要删除么？",function(index){
			layer.close(index);
			layer.load();
			$.ajax({
				url:'del.do',
				type:'post',
				data:{'id':id},
				success:function(msg){
					console.log(msg);
					layer.closeAll('loading');
					if(msg=='success'){
						layer.msg("删除成功！");
						getpointList();
					}	
				}
			})
		})
		
	}
		
            </script>
          
          <script type="text/html" id="zsgc">
			
			<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
            <a class='layui-btn layui-btn-danger layui-btn-xs' href='<%=path%>/sec/pointtime/list?id={{ d.id }}'>打卡点时间管理</a>
		  </script>
		  
    </body>
</html>