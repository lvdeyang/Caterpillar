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
	<title>二维码</title>
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
		.p-form-title button{position:absolute; right:5px; top:7px;}    
		.p-table tr{line-height:2.4;}
		.p-table tr>th.action,.p-table tr>td.action{display:none;}
		.p-verify{position:absolute; top:0; right:0;}
	</style>
</head>
<body>
	<!-- 设置类型 -->
	<c:set var="pageType" value="1" scope="request"></c:set>
	
	<!-- 页头 -->
	<jsp:include page="../../../comm/frozenui/head-fixed.jsp"></jsp:include>
	
	<!-- 主体 -->
	<section class="p-body">
		<form action="<%=basePath%>"></form>
		<p class="p-form-title">二维码列表<button class="ui-btn" id="generate-qrcode">生成二维码</button></p>
		<table class="ui-table ui-border-tb p-table" id="table-qrcode">
            <thead>
            	<tr>
            		<th>绑定id</th>
            		<th style="width:90px;">是否注册</th>
            		<th class="action"></th>
            	</tr>
            </thead>
            <tbody>
            	<c:if test="${qrcodes!=null && qrcodes.size()>0}">
            		<c:forEach items="${qrcodes}" var="qrcode">
            			<tr>
            				<td>${qrcode.identification}</td>
            				<td>${qrcode.registed}</td>
            				<td class="action"><form action="<%=basePath%>qr/code/info/${qrcode.id}" method="post"></form></td>
            			</tr>
            		</c:forEach>
            	</c:if>
            </tbody>
        </table>
	</section>
	
	<!-- 对话框 -->
	<div class="ui-dialog" id="qrcode-num">
	    <form action="<%=basePath%>qr/code/generate/200/200" method="post" class="ui-dialog-cnt p-form">
	        <div class="ui-dialog-bd">
	            <div>
		            <p class="p-form-title">欢迎来到酷车管家控制台</p>
		            <div class="ui-form-item ui-form-item-pure ui-border-b">
	                    <input type="text" name="num" placeholder="请输入0~100之间的整数" value="100">
	                    <a class="ui-icon-close"></a>
	                </div>
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
	        	<button type="submit" class="select" id="qrcode-commit">提交</button>
	            <button type="button" class="select" id="qrcode-cancel">取消</button> 
	        </div>
	    </form>        
	</div>
	
	<!-- 页尾 -->
	<jsp:include page="../../../comm/frozenui/foot-btn.jsp"></jsp:include>
</body>
<!-- 公共脚本引入 -->
<jsp:include page="../../../comm/frozenui/script.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		
		$('#table-qrcode').on('click', 'tbody>tr', function(){
			var $this = $(this),
				$form = $this.find('form');
			$form[0].submit();
		});
		
		$('#generate-qrcode').on('click', function(){
			
			var dia = $('#qrcode-num').dialog('show');
			
		});
		
		$('#qrcode-cancel').on('click', function(){
			$('#qrcode-num').dialog('hide');
		});
		
	});
</script>
<c:if test="${message != null}">
	<script type="text/javascript">
		$(function(){

			$('#qrcode-num').dialog('show');
			
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
		        stayTime:3000,
		        type:'success'
		    });
			
		});
	</script>
</c:if>
</html>