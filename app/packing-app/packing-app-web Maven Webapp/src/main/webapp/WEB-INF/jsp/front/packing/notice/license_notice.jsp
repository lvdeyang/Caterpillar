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
		.ui-tips i{position:relative; top:1px;}
		.ui-btn-lg{border-radius:0px !important; border-top:0px !important;}
		.p-form-title{padding:10px; font-size:16px; color:#7CAE23;}
	</style>
</head>
<body>
	<!-- 页头 -->
	<jsp:include page="../../../comm/frozenui/head.jsp"></jsp:include>
	
	<!-- 主体 -->
	<section class="p-body">
	
		<!-- logo -->
		<jsp:include page="../../../comm/frozenui/logo.jsp"></jsp:include>
		
        <div class="ui-form ui-border-t">
        	<form action="<%=basePath %>notice/do/license/notice" method="post" class="p-form">
        		<div class="ui-form-item ui-form-item-pure ui-border-b">
                	<input 
                		id="region"
                		type="text" 
                		name="license" 
                		placeholder="请输入车牌号码" 
                		data-vehicle-number="" data-vehicle-number-message="请输入正确的车牌号码！">
               		<a class="ui-icon-close"></a>
                </div>
				<button class="ui-btn-lg" style="color:#800CAF; font-weight:900; font-size:22px;">通知车主</button>
				<div class="ui-tips ui-tips-info">
	                <i></i><span>通知车主挪车，2分钟内无法重复通知</span>
	            </div>
			</form>
        </div>
		<p class="p-form-title">推广服务</p>
        <div class="ui-form ui-border-t">
        	<a class="ui-btn-lg" href="<%=basePath%>register/index/${entrance}">服务注册</a>
        	<a class="ui-btn-lg" href="http://sjxx.tslmpt.com/wap">商家信息</a>
			<a class="ui-btn-lg" href="http://kcgj.tslmpt.com/wap">酷车网站</a>
			<a class="ui-btn-lg" href="http://kcsc.tslmpt.com/wap">酷车商城</a>
        </div>
        <a href="http://jmd.tslmpt.com/wap" style="position:relative; top:3px;">
			<img src="<%=basePath%>mobile/images/link/jumengdao.jpg" style="width:100%;">
		</a>
	</section>
	
	<!-- 页尾 -->
    <jsp:include page="../../../comm/frozenui/foot.jsp"></jsp:include>
</body>
<!-- 公共脚本引入 -->
<jsp:include page="../../../comm/frozenui/script.jsp"></jsp:include>
<script type="text/javascript">
	
</script>
</html>