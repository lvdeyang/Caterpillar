<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
        <title>
           订单详情
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">

  </head>
  
  <body>
  <div class="x-body">
    <table class="layui-table" id="List" lay-filter="List"></table> 
  </div>
  <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['element','laypage','layer','laytpl','table','form'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;
              form = layui.form;
              
              orderList();
             return false;
            });
        	function orderList(){
                 table.render({
                    elem:"#List"
                    ,method:"post"
                    ,url:"order.do/${id}"
                    ,page:true
                    ,limits: [10,30,50,100]
                    ,limit: 10
                    ,cols: [[{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
                        ,{field: 'shopName', align:'center',title: '商店名称',sort: true} 
                        ,{field: 'productName',align:'center', title: '产品名称',sort: true,width:320} 
                        ,{field: 'payMoney',align:'center', title: '结算金额',sort: true,width:280} 
                        ]]
                })       
        	}
   
        </script>
  </body>
</html>
