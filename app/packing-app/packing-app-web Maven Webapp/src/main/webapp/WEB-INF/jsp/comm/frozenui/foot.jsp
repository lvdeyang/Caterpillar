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
		.p-footer{background-color:#18b4ed; padding-top:20px; position:static; padding:0 !important;}
		.p-footer-item{margin-bottom:5px;}
		.p-footer-copyright{height:50px; line-height:50px; background-color:rgba(0,0,0,0.1); width:100%;}
	</style>
</head>
<body>
    <footer class="p-footer ui-row">
	    <div class="p-footer-copyright ui-flex ui-flex-pack-center">
	        <div class="container is-text-white">
		        © 2017 Copyright <a class="is-text-white" href="javascript:void(0);">河北奥尼克斯科技有限公司</a>
	        </div>
	    </div>
	</footer>
</body>
</html>