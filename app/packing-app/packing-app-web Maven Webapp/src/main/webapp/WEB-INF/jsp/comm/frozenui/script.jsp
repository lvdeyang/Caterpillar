<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath %>mobile/lib/zepto/js/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/frozenui/js/frozen.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/lib/zValidate/js/zValidate.js"></script>
<script type="text/javascript" src="<%=basePath %>mobile/app/common/common.js"></script>
