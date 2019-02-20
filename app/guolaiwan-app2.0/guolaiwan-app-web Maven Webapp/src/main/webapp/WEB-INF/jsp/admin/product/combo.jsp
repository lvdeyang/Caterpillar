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
    <a><cite>产品管理</cite></a>
    <a><cite>套餐列表</cite></a>
  </span> <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>

<!--  -->
<div class="x-body">
	<xblock>
		<button class="layui-btn" onclick="combo_add('添加套餐','addcomv','900','600')"><i class="layui-icon">&#xe608;</i>添加</button>
		<span class="x-right" style="line-height:40px">系统中共有：<span id="count">${count}</span>个套餐</span>
	</xblock>
	<table id="comboList" lay-filter="comboList"></table>
</div>



<!-- 操作引擎模板 -->
<script type="text/html" id="procaozuoTpl">
 <a title="删除" href="javascript:;" onclick="combo_del(this,'{{ d.id }}')" style="text-decoration:none"><i class="layui-icon">&#xe640;</i></a>
</script>

<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
<script>

  layui.use(['element','layer','laytpl','upload','laydate','laypage','form','table'], function(){
  $ = layui.jquery;//jquery
  lement = layui.element;//面包导航
  layer = layui.layer;//弹出层
  laytpl = layui.laytpl;//模板引擎
  laypage = layui.laypage;//分页
  upload = layui.upload;
  laydate = layui.laydate;
  form = layui.form;
  table = layui.table;

  //获取产品列表
  getComList();
  
	
});



  function combo_add(title,url,w,h){
    full_show(title,url,w,h);
  }

 
  
  
  
  
  
  
  /*删除*/
            function combo_del(obj,id,name){
                layer.confirm('确认要删除吗？',function(index){
                  layer.close(index);
                  layer.load();
                  //发异步删除数据
                  $.ajax({
                    type:"post",
                    url:"combodel.do",
                    data:{"id":id},
                    success:function(msg){
                  	 if(msg=="success"){
                        layer.closeAll("loading");
                  		  $(obj).parents("tr").remove();
                  		  layer.msg('已删除!',{icon:1,time:1000});
                  	 }
                    }
                  }) 
                });
            }
  
  
  
  
  


  

  function full_show(title,url,id,w,h) {
   var index = layer.open({
     type: 2,
     area: [w+'px', h +'px'],
	  fix: false, //不固定
	  maxmin: true,
	  shadeClose: true,
	  shade:0.4,
	  title: title,
	  content: url+"?productId=${productId}"
	}); 
   layer.full(index);
 }
 

function getComList(){
  table.render({               
    elem:'#comboList'
    ,method:'post'
    ,url:'comboList.do'+"?productId=${productId}"
    ,page:true
    ,limits: [10,30,50,100]
    ,limit: 10
    ,skin:'row'
    ,even:true
    ,cols: [[
    {type:'checkbox'}
    ,{field: 'id', title: 'ID',sort: true,width:60} 
    ,{field: 'combo', title: '套餐名称',sort: true}  
    ,{field: 'comboprice', title: '套餐价格',sort: true} 
    ,{fixed: 'right',title: '操作',width:250,templet:'#procaozuoTpl',unresize:true} 
    ]]
  });
}


</script>


</body>
</html>