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
	<title>酷车管家</title>
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<!-- 公共样式引用 -->
	<jsp:include page="../comm/frozenui/style.jsp"></jsp:include>
	<style type="text/css">
		.p-message{margin-top:80px; margin-bottom:230px; text-align:center;}
		.p-message i{color:#f76249; font-size:160px; line-height:0.88;}
		.p-message p{font-size:18px; color:#777;}
		.p-message p span{font-size:26px; font-style:italic; position:relative; top:3px;}
	</style>
</head>
<body>
	<!-- 页头 -->
	<jsp:include page="../comm/frozenui/head.jsp"></jsp:include>
	
	<!-- 主体 -->
	<section class="p-message">
        <i class="ui-icon-warn-block"></i>
        <br/>
        <p><span>404</span>：${message}</p>
    </section>
	
	<!-- 页尾 -->
    <jsp:include page="../comm/frozenui/foot.jsp"></jsp:include>
</body>
<!-- 公共脚本引入 -->
<jsp:include page="../comm/frozenui/script.jsp"></jsp:include>
<script type="text/javascript">
	
</script>
</html>