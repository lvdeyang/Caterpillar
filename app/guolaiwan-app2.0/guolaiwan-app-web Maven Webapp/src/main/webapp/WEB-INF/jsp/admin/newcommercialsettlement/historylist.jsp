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
            历史结算
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
              
              historyList();
             return false;
            });
        	function historyList(){
                 table.render({
                    elem:"#List"
                    ,method:"post"
                    ,url:"history.do/${id}"
                    ,page:true
                    ,limits: [10,30,50,100]
                    ,limit: 10
                    ,cols: [[{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
                        ,{field: 'merchantName', align:'center',title: '名称',sort: true} 
                        ,{field: 'bankNo',align:'center', title: '银行卡号',sort: true,width:320} 
                        ,{field: 'amount',align:'center', title: '结算金额',sort: true,width:280} 
                        ]]
                })       
        	}

                
        </script>
  </body>
</html>
