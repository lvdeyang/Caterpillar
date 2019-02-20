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
	<title>管理员</title>
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
		html,body{height:100% !important; position:relative;}
		.p-body{position:absolute; top:45px; left:0; bottom:56px; right:0; overflow-y:auto;}
		.p-form-title{padding:10px; font-size:16px; color:#7CAE23;}
		.p-table tr{line-height:2.4;}
		.p-verify{position:absolute; top:0; right:0;}
	</style>
</head>
<body>
	<!-- 设置类型 -->
	<c:set var="pageType" value="2" scope="request"></c:set>

	<!-- 页头 -->
	<jsp:include page="../../../comm/frozenui/head-fixed.jsp"></jsp:include>
	
	<!-- 主体 -->
	<section class="p-body">
		<p class="p-form-title">当前管理员账户</p>
		<table class="ui-table ui-border-tb p-table" id="table-user">
            <tbody>
            	<tr>
            		<td>用户名</td>
            		<td>${admin.username}</td>
            	</tr>
            	<tr>
            		<td>密码</td>
            		<td>${admin.password}</td>
            	</tr>
            </tbody>
        </table>
        <button type="button" class="ui-btn-lg" id="change-passowrd" style="margin-top:44px;">修改密码</button>
        
        <!-- 对话框 -->
		<div class="ui-dialog" id="dialog-passowrd">
		    <form action="<%=basePath%>admin/change/password/${admin.id}" method="post" class="p-form ui-dialog-cnt">
		        <div class="ui-dialog-bd">
		            <div>
			            <p class="p-form-title">修改管理员密码</p>
			            
			            <!-- 旧密码 -->
			            <div class="ui-form-item ui-form-item-pure ui-border-b">
		                    <input 
		                    	type="password" 
		                    	name="oldPass" 
		                    	placeholder="旧密码"
		                    	data-required="true" data-required-message="旧密码不能为空！"
		                    	<c:if test="${oldPass!=null}">value="${oldPass}"</c:if>
		                    	>
		                    <a class="ui-icon-close"></a>
		                </div>
		                
		                <!-- 新密码 -->
		                <div class="ui-form-item ui-form-item-pure ui-border-b">
		                    <input 
		                    	id="newPass"
		                    	type="password" 
		                    	name="newPass" 
		                    	placeholder="新密码"
		                    	data-required="true" data-required-message="新密码不能为空！"
		                    	<c:if test="${newPass!=null}">value="${newPass}"</c:if>>
		                    <a class="ui-icon-close"></a>
		                </div>
		                
		                <!-- 重复密码 -->
		                <div class="ui-form-item ui-form-item-pure ui-border-b">
		                    <input 
		                    	type="password" 
		                    	name="repeat" 
		                    	placeholder="重复新密码"
		                    	data-required="true" data-required-message="重复新密码不能为空！"
		                    	data-equal-to="#newPass" data-equal-to-message="确认密码与密码不匹配"
		                    	<c:if test="${repeat!=null}">value="${repeat}"</c:if>>
		                    <a class="ui-icon-close"></a>
		                </div>
		                
		                <!-- 验证码 -->
		                <div class="ui-form-item ui-form-item-r ui-border-b">
		                    <input 
		                    	type="text" 
		                    	name="token" 
		                    	placeholder="请输入验证码" 
		                    	style="padding-right:110px !important;"
		                    	data-required="true" data-required-message="验证码不能为空！">
		                    <img src="<%=basePath %>verify/code/get/110/43" class="ui-border-l p-verify"/>
		                </div>
		            </div>
		        </div>
		        <div class="ui-dialog-ft ui-btn-group">
		        	<button type="submit" class="select" id="commit">提交</button>
		            <button type="button" class="select" id="cancel">取消</button> 
		        </div>
		    </form>        
		</div>
        
	</section>
	
	<!-- 页尾 -->
	<jsp:include page="../../../comm/frozenui/foot-btn.jsp"></jsp:include>
</body>
<!-- 公共脚本引入 -->
<jsp:include page="../../../comm/frozenui/script.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$('#change-passowrd').on('click', function(){
			var dia = $('#dialog-passowrd').dialog('show');
		});
		
		$('#cancel').on('click', function(){
			$('#dialog-passowrd').dialog('hide');
		});
	});
</script>
<c:if test="${message != null}">
	<script type="text/javascript">
		$(function(){

			$('#dialog-passowrd').dialog('show');
			
			var message = '${message}';
			
			el=$.tips({
		        content:message,
		        stayTime:3000,
		        type:'info'
		    });
			
		});
	</script>
</c:if>
<c:if test="${success != null}">
	<script type="text/javascript">
		$(function(){

			var success = '${success}';
			
			el=$.tips({
		        content:success,
		        stayTime:300000,
		        type:'success'
		    });
			
		});
	</script>
</c:if>
</html>