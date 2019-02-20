<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String wsBasePath = "ws"+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>">
<link rel="stylesheet" type="text/css" href="<%=basePath%>mobile/lib/jQuery-weui-v1.2.0/lib/weui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>mobile/lib/jQuery-weui-v1.2.0/css/jquery-weui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>mobile/lib/jQuery.mmenu/css/jquery.mmenu.all.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>web/lib/ui/iCheck/all.css">
<link rel="stylesheet" href="<%=basePath%>mobile/lib/Font-Awesome-3.2.1/css/font-awesome.min.css" />
<!--[if IE 7]>
<link rel="stylesheet" href="<%=basePath%>mobile/lib/Font-Awesome-3.2.1/css/font-awesome-ie7.min.css">
<![endif]-->
<script type="text/javascript">
	window.BASEPATH = '<%=basePath%>';
	window.WSBASEPATH = '<%=wsBasePath%>';
</script>