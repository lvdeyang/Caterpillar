<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
    <head>
		<base href="<%=basePath%>">

		<title>guolaiwan</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<!-- iCheck -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/iCheck/all.css" />

		<!-- google font -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/travel/fonts/google/google-font.css" />

		<!-- Animate.css -->
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/animate.css">

		<!-- Icomoon Icon Fonts-->
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/icomoon.css">

		<!-- Bootstrap  -->
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/bootstrap.css">

		<!-- Superfish -->
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/superfish.css">

		<!-- Magnific Popup -->
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/magnific-popup.css">

		<!-- Date Picker -->
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/bootstrap-datepicker.min.css">

		<!-- CS Select -->
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/cs-select.css">
		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/cs-skin-border.css">

		<link rel="stylesheet" href="<%=basePath %>lib/travel/css/style.css">

		<!-- Modernizr JS -->
		<script src="<%=basePath %>lib/travel/js/modernizr-2.6.2.min.js"></script>
		<!-- FOR IE9 below -->
		<!--[if lt IE 9]>
		<script src="<%=basePath %>lib/travel/js/respond.min.js"></script>
		<![endif]-->

		<script type="text/javascript">
			window.BASEPATH = '<%=basePath %>';
		</script>

		<style type="text/css">
			[data-hash]{cursor:pointer;}
		</style>
    </head>
  
    <body class="fh5co-section-gray">
		<div id="fh5co-wrapper">
			<div id="fh5co-page">

			</div>
		</div>
    </body>

	<script type="text/javascript" src="<%=basePath %>lib/requireJS/require.min.js"  defer async="true" data-main="<%=basePath %>app/website/main"></script>
</html>
