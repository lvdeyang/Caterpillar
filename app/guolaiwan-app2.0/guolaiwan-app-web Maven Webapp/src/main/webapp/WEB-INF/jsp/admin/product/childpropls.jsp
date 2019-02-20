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
    <a><cite>评论列表</cite></a>
  </span> <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<!--  -->
<div class="x-body">
	<table id="commentList" lay-filter="commentList"></table>
</div>
<!-- 操作引擎模板 -->
<script type="text/html" id="procaozuoTpl">
     <a title="删除" href="javascript:;" onclick="delComment(this,'{{ d.uuid }}')" style="text-decoration:none">
         <i class="layui-icon">&#xe640;</i>
     </a>	
     <a title="详情" href="javascript:;" onclick="comment_info('评论详情','infocomment','{{d.uuid}}','','')" class="tdn">
          <i class="layui-icon">&#xe62d;</i>
	 </a>
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
  getCommentList(${productID});
});
function getCommentList(data){
  table.render({               
    elem:'#commentList'
    ,method:'post'
    ,url:'getChildPL.do/'+data
    ,page:true
    ,limits: [10,30,50,100]
    ,limit: 10
    ,skin:'row'
    ,even:true
    ,cols: [[
    {type:'checkbox'}
    ,{field: 'id', title: 'ID',sort: true,width:60} 
    ,{field: 'content', title: '用户评论',sort: true}
    ,{field: 'userDate', title: '评论时间',sort: true}
    ,{field: 'merContent', title: '商家回评',sort: true}
    ,{field: 'merDate', title: '回评时间',sort: true}
    ,{fixed: 'right',title: '操作',width:160,templet:'#procaozuoTpl',unresize:true} 
    ]]
  });
}

function delComment(obj,uuid){
	layer.confirm('确认要删除吗？',function(index){
	    //发异步删除数据
	    $.ajax({
	    type:"post",
	    url:"delcom.do/"+uuid,
	    success:function(msg){
	    	if(msg=="success"){
	    		$(obj).parents("tr").remove();
	    		layer.msg('已删除!',{icon:1,time:1000});
	    	}
	    }
	    })
	    
	});
	
}

       //产品详情
function comment_info(title,url,id,w,h) {
	x_admin_show(title,url+"/"+id,w,h); 
}

</script>
</body>
</html>