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
            大数据订单
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    <style type="text/css">
	    .layui-table img{max-width: 1000px;}/* 照片的最大宽度  */
	</style>
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>车辆信息</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        
        <div class="x-body">
            <xblock><button class="layui-btn" onclick="car_add('添加信息','addv','600','500')"><i class="layui-icon">&#xe608;</i>添加信息</button><span class="x-right" style="line-height:40px">共有数据：<span id="allcount"></span> 条</span></xblock>
         	<table id="carList" lay-filter="carList"></table>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        
        
           
            layui.use(['element','laypage','layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;
              table = layui.table;//模板引擎
              //以上模块根据需要引入
              
              getCarList();
      
	         
	         function getCarList(){
	         	table.render({
            		elem:"#carList"
            		,method:'post'
   					,url:'list.do'
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						 {type:"checkbox"}
   						,{field:"carNum",title:"数量"}
   						,{field:"region",title:"地区"}
   						,{field:"typeString",title:"类型"}
   						,{title:"操作",templet:"#zsgc"}
   					]]
   					,done:function(res, curr, count){
   						$("#allcount").text(count);
   					}
            	})
	         }
	         
	         
	       
		})
		
		
		  /*添加*/
            function car_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

            //编辑
            function car_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?id="+id,w,h); 
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
								window.location.reload();
							}	
						}
					})
				})
				
			}
		
          </script>
          
          <script type="text/html" id="zsgc">
			<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}")'>删除</a>
		  </script>
		 
    </body>
</html>