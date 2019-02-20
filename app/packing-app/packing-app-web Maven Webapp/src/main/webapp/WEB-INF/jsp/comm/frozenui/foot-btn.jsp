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
		.ui-footer .ui-tiled{border-top:0;}
		.ui-footer li{border-top:1px solid #e0e0e0;}
		.ui-footer li.active{background:#f9f9f9;}
		.ui-footer li:not(:last-child).active{border-right:1px solid #e0e0e0;}
	</style>
</head>
<body>
	<footer class="ui-footer ui-footer-btn" id="p-footer">
        <ul class="ui-tiled ui-border-t">
        	<c:if test="${pageType == 0}">
        		<li data-href="<%=basePath %>user/list" class="ui-border-r active"><div>绑定用户</div></li>
        	</c:if>
            <c:if test="${pageType != 0}">
        		<li data-href="<%=basePath %>user/list" class="ui-border-r"><div>绑定用户</div></li>
        	</c:if>
        	<c:if test="${pageType == 1}">
        		<li data-href="<%=basePath %>qr/code/list" class="ui-border-r active"><div>二维码</div></li>
        	</c:if>
        	<c:if test="${pageType != 1}">
        		<li data-href="<%=basePath %>qr/code/list" class="ui-border-r"><div>二维码</div></li>
        	</c:if>
        	<c:if test="${pageType == 2}">
        		<li data-href="<%=basePath %>admin/list" class="active"><div>管理员</div></li>
        	</c:if>
        	<c:if test="${pageType != 2}">
        		<li data-href="<%=basePath %>admin/list"><div>管理员</div></li>
        	</c:if>
        </ul>
    </footer>
</body>
</html>