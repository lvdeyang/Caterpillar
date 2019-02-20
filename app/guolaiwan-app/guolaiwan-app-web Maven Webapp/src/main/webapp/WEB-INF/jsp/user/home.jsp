<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>

    <base href="<%=basePath%>">
    <title>guolaiwan</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="<%=basePath %>lib/jQueryUI/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>lib/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>lib/user/css/style.css" />

    <script type="text/javascript">
        window.BASEPATH = '<%=basePath %>';
    </script>

    <style type="text/css">
        [data-hash]{cursor:pointer;}
        html, body{width:100%; height:100%; padding:0; margin:0;}
    </style>

</head>

<body>

</body>

<script type="text/javascript" src="<%=basePath %>lib/requireJS/require.min.js"  defer async="true" data-main="<%=basePath %>app/user/main"></script>

</html>