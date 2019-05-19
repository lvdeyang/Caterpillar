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

    <form class="layui-form" action="">
	  <div class="layui-form-item">
	    <label class="layui-form-label">单行输入框</label>
	    <div class="layui-input-block">
	      <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">验证必填项</label>
	    <div class="layui-input-block">
	      <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
	    </div>
	  </div>
   
	<script>
		//JavaScript代码区域
		layui.use(['table','tree','layer'],function(){
		   var $ = layui.jquery;
		   

           
			
		});
	</script>
</body>
</html>
