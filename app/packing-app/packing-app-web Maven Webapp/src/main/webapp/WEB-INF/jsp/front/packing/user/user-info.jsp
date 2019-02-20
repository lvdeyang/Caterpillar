<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath %>">
	<title>管理员</title>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<!-- 公共样式引用 -->
	<jsp:include page="../../../comm/frozenui/style.jsp"></jsp:include>
	<style type="text/css">
		html,body{height:100% !important; position:relative;}
		.p-body{position:absolute; top:45px; left:0; bottom:56px; right:0; overflow-y:auto;}
		.p-form-title{padding:10px; font-size:16px; color:#7CAE23;}
		.p-table tr{line-height:2.4;}
	</style>
</head>
<body>
	<!-- 设置类型 -->
	<c:set var="pageType" value="0" scope="request"></c:set>

	<!-- 设置回退 -->
	<c:set var="goBack" value="true" scope="request"></c:set>

	<!-- 页头 -->
	<jsp:include page="../../../comm/frozenui/head-fixed.jsp"></jsp:include>
	
	<!-- 主体 -->
	<section class="p-body">
		<p class="p-form-title">用户信息</p>
		<table class="ui-table ui-border-tb p-table" id="table-user">
            <tbody>
            	<tr>
            		<td>姓名</td>
            		<td>${user.name}</td>
            	</tr>
            	<tr>
            		<td>手机号</td>
            		<td>${user.mobile}</td>
            	</tr>
            	<tr>
            		<td>车牌号</td>
            		<td>${user.license}</td>
            	</tr>
            	<tr>
            		<td>车型</td>
            		<td>${user.type}</td>
            	</tr>
            	<tr>
            		<td>二维码</td>
            		<td><img src="<%=basePath %>${user.qrCode}" /></td>
            	</tr>
            </tbody>
        </table>
	</section>
	
	<!-- 页尾 -->
	<jsp:include page="../../../comm/frozenui/foot-btn.jsp"></jsp:include>
</body>
<!-- 公共脚本引入 -->
<jsp:include page="../../../comm/frozenui/script.jsp"></jsp:include>
<script type="text/javascript">
	
</script>
</html>