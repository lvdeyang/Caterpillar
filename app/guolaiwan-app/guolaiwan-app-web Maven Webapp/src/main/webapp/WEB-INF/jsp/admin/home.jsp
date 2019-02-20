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

		<!-- tree -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/tree/css/tree.css" />

		<!-- bootstrap -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/bootstrap/css/bootstrap.min.css" />

		<!-- datatables -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/datatables/dataTables.bootstrap.css" />

		<!-- iCheck -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/iCheck/all.css" />

		<!-- font awesome -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/font-awesome/css/font-awesome.min.css" />

		<!-- ionicicons -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/ionicons/css/ionicons.min.css" />

		<!-- Theme style -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/admin-lte/css/AdminLTE.min.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>lib/admin-lte/css/skins/_all-skins.min.css" />

		<!--[if lt IE 9]>
		<script src="<%=basePath %>lib/html5-lt-ie9/html5shiv.min.js"></script>
		<script src="<%=basePath %>lib/html5-lt-ie9/respond.min.js"></script>
		<![endif]-->

		<script type="text/javascript">
			window.BASEPATH = '<%=basePath %>';
		</script>

		<style type="text/css">
			[data-hash]{cursor:pointer;}
		</style>
	</head>

	<body class="hold-transition skin-yellow-light sidebar-mini">
		<div class="wrapper" style="position:relative; z-index:0;">
			<!-- 单页面应用 -->
		</div>
	</body>

	<script type="text/javascript" src="<%=basePath %>lib/requireJS/require.min.js"  defer async="true" data-main="<%=basePath %>app/admin/main"></script>

</html>
