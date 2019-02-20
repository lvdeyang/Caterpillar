<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<link rel="stylesheet" type="text/css" href="<%=basePath%>mobile/lib/frozenui/css/frozen.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>mobile/lib/Font-Awesome-3.2.1/css/font-awesome.min.css">
<script type="text/javascript">
	window.basePath = '<%=basePath%>';
</script>

