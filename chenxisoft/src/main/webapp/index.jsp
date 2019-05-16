<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <meta charset="UTF-8">
<script type="text/javascript" src="<%=path %>/webtheme/theme/js/jquery.js"></script>
<script type="text/javascript">
$(function () {
	
});

</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>loading</title>
</head>
<body>
<jsp:forward page="/index"></jsp:forward>
</body>
</html>