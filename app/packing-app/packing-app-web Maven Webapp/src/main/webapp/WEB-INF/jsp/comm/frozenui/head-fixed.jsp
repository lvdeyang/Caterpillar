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
		*{-webkit-backface-visibility: hidden;}
		body,html{margin:0; padding:0; width:100%; height:auto%; background-color:#fff;}
		div{box-sizing:border-box;}
		
		.margin-bottom-20px{margin-bottom:20px !important;}
		.z-depth-1{
		    webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
		    -moz-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
		    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
		}
		.is-text-white{color:#fff !important;}
		.is-border{border-bottom:1px solid #e0e0e0;}
		.is-border-box{box-sizing:border-box;}
		
		header.p-header>.p-header-action, header.p-header>.p-header-home{position:absolute; padding-left:20px; padding-right:20px; display:inline-block; height:45px; line-height:45px; transition:background-color 0.05s ease-in;}
		header.p-header>.p-header-action{left:0;}
		header.p-header>.p-header-home{right:0; top:0;}
		header.p-header>.p-header-action:active, header.p-header>.p-header-home:active{background-color:rgba(0,0,0,.1);}
		header.p-header>.p-header-action>i{position:relative; right:2px;}
		
		.p-error:not(.ui-tips){box-sizing:content-box; border:1px solid #f75549;}
		
		.ui-poptips{z-index:10000 ！important;}
	</style>
	<script type="text/javascript">
		window.basePath = '<%=basePath%>';
	</script>
</head>
<body>
	<header class="ui-header ui-header-positive ui-border-b p-header">
		<c:if test="${goBack != null}">
			<i class="ui-icon-return" id="goBack"></i>
		</c:if>
    	<h1>酷车管家</h1>
    </header>
</body>
</html>