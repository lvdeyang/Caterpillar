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
	<jsp:include page="../../../comm/frozenui/style.jsp"></jsp:include>
	<style type="text/css">
		.p-form-title{padding:10px; font-size:16px; color:#7CAE23;}
		.p-body{padding-bottom:100px;}
		.p-verify{position:absolute; top:0; right:0;}
	</style>
</head>
<body>
	<!-- 页头 -->
	<jsp:include page="../../../comm/frozenui/head.jsp"></jsp:include>
	
	<!-- 主体 -->
	<section class="p-body">
		<p class="p-form-title">欢迎来到酷车管家控制台</p>
        <div class="ui-form ui-border-t p-form">
            <form action="<%=basePath %>login/do/login" method="post">
                <div class="ui-form-item ui-form-item-pure ui-border-b">
                    <input 
                    	type="text" 
                    	name="username" 
                    	placeholder="请输入用户名" 
                    	value="${username}"
                    	data-required="true" data-required-message="用户名不能为空！">
                    <a class="ui-icon-close"></a>
                </div>
                <div class="ui-form-item ui-form-item-pure ui-border-b">
                    <input 
                    	type="password" 
                    	name="password" 
                    	placeholder="请输入密码" 
                    	value="${password}"
                    	data-required="true" data-required-message="密码不能为空！">
                    <a class="ui-icon-close"></a>
                </div>
                <div class="ui-form-item ui-form-item-r ui-border-b">
                    <input 
                    	type="text" 
                    	name="token" 
                    	placeholder="请输入验证码"
                    	data-required="true" data-required-message="验证码不能为空！">
                    <img src="<%=basePath %>verify/code/get/110/44" class="ui-border-l p-verify"/>
                    <a class="ui-icon-close"></a>
                </div>
                <button type="submit" class="ui-btn-lg" style="margin-top:44px;">登录</button>
            </form>
        </div>
	</section>
	
	<!-- 页尾 -->
    <jsp:include page="../../../comm/frozenui/foot.jsp"></jsp:include>
</body>
<!-- 公共脚本引入 -->
<jsp:include page="../../../comm/frozenui/script.jsp"></jsp:include>
<c:if test="${message != null}">
	<script type="text/javascript">
		$(function(){

			var message = '${message}';
			
			el=$.tips({
		        content:message,
		        stayTime:3000,
		        type:'info'
		    });
			
		});
	</script>
</c:if>
</html>