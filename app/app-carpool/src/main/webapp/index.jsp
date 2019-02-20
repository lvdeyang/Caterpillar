<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
    <head>
	    <base href="<%=basePath%>">
	    <title>注册页面</title>
	    
		<meta charset="UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">

    </head>
  
    <body>
    
    	<!-- 游客主页 -->
  	    <jsp:forward page="${basePath}/visitor/index"></jsp:forward>
  	     
  	    <!-- 司机主页 
  	    <jsp:forward page="${basePath}/driver/index"></jsp:forward>
  	    -->
    
    
    	<!-- 后台主页 
  	    <jsp:forward page="${basePath}/admin/index"></jsp:forward>
  	     -->
  	     
    </body>
  
</html>
