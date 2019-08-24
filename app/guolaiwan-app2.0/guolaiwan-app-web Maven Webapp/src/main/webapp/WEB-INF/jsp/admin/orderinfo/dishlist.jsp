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
            菜品订单
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
              <a><cite>订单中心</cite></a>
              <a><cite>餐桌订单</cite></a>
              <a><cite>菜品订单</cite></a>
            </span>
			<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>        	
        	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>
        </div>
        <div class="x-body">
         	<table id="dishList" lay-filter="dishList"></table>
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
              
              
              getdishList();
              
            });
	         
	         function getdishList(){
	         	table.render({
            		elem:"#dishList"
            		,method:'post'
   					,url:'getdishorder?tableId=${tableId}&userId=${userId}&merchantId=${merchantId}'
   					/* ,data:{"tableId":"${tableId}","userId":"${userId}","merchantId":"${merchantId}"} */
   					,page:true
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
   						{type:"checkbox"}
   						,{field: 'id', title: 'ID',sort: true,width:100} 
					    ,{field: 'productName', title: '商品名称',width:300}  
					    ,{field: 'productMerchantName', title: '商家',width:300} 
					    ,{field: 'productOldPrice', title: '原价',width:100,sort: true} 
					    ,{field: 'productPrice', title: '现价',width:100,sort: true} 
					    ,{field: 'productCityName', title: '城市',sort: true, width:100}
					    ,{field: 'comName', title: '所属公司',width:300} 
					    ,{field: 'updateTime', title: '上传时间',width:200,sort: true} 
   						]]
   					 ,done:function(res, curr, count){
   						$("#allcount").text(count); 
   					}
            	})
	         }
	         
	         
            </script>
          
    </body>
</html>