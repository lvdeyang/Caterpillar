<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
<title>关于我们</title>
<!-- 引入 FrozenUI -->
<link rel="stylesheet"
	href="http://i.gtimg.cn/vipstyle/frozenui/2.0.0/css/frozen.css" />
<link rel="stylesheet"
	href="lib/css/font-awesome.css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://ryanbay.cn/vipstyle/qui/2.0.0/demo/js/lib/zepto.min.js"></script>
<script src="http://i.gtimg.cn/vipstyle/frozenjs/1.0.1/frozen.js"></script>
</head>



<body>
    <h2 class="title ui-border-b" style="height:30px;padding:10px;line-height:30px;display:none">小青月嫂
       </h2><a><i class="ui-icon-personal"></i></a>
    <header class="ui-header ui-header-positive ui-border-b" style="background:#FFC0CB;color:black;border-bottom:1px solid #CCC">
         
    </header>
	<div style="margin-top:80px;width:100%;text-align:center;">Mr.黄团队作品@晨曦软件</div>
	<div style="width:100%;text-align:center;">13810728953</div>
	<div style="width:100%;height:200px;" align="center">
	    <image style="width:100px;height:100px;margin:0 auto" src="/chenxisoft/lib/images/mrhuang.jpg"/>
	</div>
	

	
	<jsp:include page="../common.jsp"></jsp:include>

</body>
</html>
