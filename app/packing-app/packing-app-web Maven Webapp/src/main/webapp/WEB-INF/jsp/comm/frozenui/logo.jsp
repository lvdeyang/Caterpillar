<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<style type="text/css">
		.p-logo{text-align:center !important; margin-top:22px !important; margin-bottom:-22px !important;}
	</style>
</head>
<body>
	<p class="p-logo">
		<img src="<%=basePath%>mobile/images/logo/logo-kucheguanjia.png" style="width:70%;"/>
	</p>
</body>
</html>