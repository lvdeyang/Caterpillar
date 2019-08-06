<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>阳光成单系统</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>
  <div class="x-nav">
   <span class="layui-breadcrumb">
    <a><cite>首页</cite></a>
    <a><cite>投票大赛</cite></a>
    <a><cite>评委评分</cite></a>
  </span> 
 		
 <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:getProductList()" title="刷新"><i class="layui-icon" style="line-height:38px">ဂ</i></a>
 <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>


</div>

<div class="x-body">
	<table id="productList" lay-filter="productList"></table>
</div>



<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
<script>
	var id = '${productId}';
  layui.use(['element','layer','laytpl','upload','laypage','table'], function(){
  $ = layui.jquery;//jquery
  lement = layui.element;//面包导航
  layer = layui.layer;//弹出层
  laytpl = layui.laytpl;//模板引擎
  laypage = layui.laypage;//分页
  table = layui.table;

  //获取产品列表
  getProductList();
  
  table.on("edit(productList)",function(obj){
  		//加载
 		layer.msg('加载中', { icon: 16 ,shade: 0.01 })
         		var data = obj.data,
         		value = obj.value,
         		field = obj.field;
         		$.ajax({
         			type:"post",
         			url:"updetescore",
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


function getProductList(){
  table.render({               
    elem:'#productList'
    ,method:'post'
    ,url:'getalljudges'
    ,where:{"productId":id}
    ,page:true
    ,limits: [10,30,50,100]
    ,limit: 10
    ,cols: [[
    {type:'checkbox'}
    ,{field: 'username', title: '评委姓名',edit:"text",align:'center',width:800} 
    ,{field: 'score', title: '评委评分',sort: true,edit:"text",align:'center',width:800} 
    ]]
    ,done:function(res, curr, count){
    	$("#account").text(count);
    }
    
  });
}

</script>


</body>
</html>