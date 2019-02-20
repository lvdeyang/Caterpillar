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
		#send-token[disabled]{color:#ccc; background:#e9ebec;}
	</style>
</head>
<body>
	<!-- 页头 -->
	<jsp:include page="../../../comm/frozenui/head.jsp"></jsp:include>
	
	<!-- 主体 -->
	<section class="p-body" style="padding-bottom:30px;">
	
		<!-- logo -->
		<jsp:include page="../../../comm/frozenui/logo.jsp"></jsp:include>
	
		<p class="p-form-title">请输入注册信息</p>
        <div class="ui-form ui-border-t">
            <form action="<%=basePath %>register/do/register/${identification}" method="post" class="p-form">
            
            	<!-- 车牌号 -->
                <div class="ui-form-item ui-form-item-pure ui-border-b">
                	<input 
                		id="region"
                		type="text" 
                		name="region" 
                		placeholder="车牌地区如：京N" 
                		style="padding-right:200px;"
                		data-vehicle-number="#license" data-vehicle-number-message="请输入正确的车牌号码！">
                	<div class="ui-form-item ui-form-item-pure ui-border-l" style="width:200px; position:absolute; top:0; right:0;">
                		<input id="license" type="text" name="license" placeholder="车牌尾号如：56789">
                		<a class="ui-icon-close"></a>
                	</div>
                </div>
                
                <!-- 车型 
                <div class="ui-form-item ui-form-item-pure ui-border-b">
                    <input 
                    	type="text" 
                    	name="type" 
                    	placeholder="请输入车型"
                    	data-required="true" data-required-message="车型不能为空！">
                    <a class="ui-icon-close"></a>
                </div>-->
                
                <!-- 姓名
                <div class="ui-form-item ui-form-item-pure ui-border-b">
                    <input 
                    	type="text" 
                    	name="name" 
                    	placeholder="请输入姓名"
                    	data-required="true" data-required-message="姓名不能为空！">
                    <a class="ui-icon-close"></a>
                </div> -->
                
                <!-- 手机号 -->
                <div class="ui-form-item ui-form-item-r ui-border-b">
                    <input 
                    	type="text" 
                    	name="mobile" 
                    	placeholder="请输入手机号"
                    	data-required="true" data-required-message="手机号不能为空！"
                    	data-mobile-number="true" data-mobile-number-message="请输入正确的手机号！">
                    <a class="ui-icon-close"></a>
                    <!-- 若按钮不可点击则添加 disabled 类 -->
                    <button type="button" class="ui-border-l" id="send-token">获取验证码</button>
                </div>
                
                <!-- 验证码 -->
                <div class="ui-form-item ui-form-item-pure ui-border-b">
                    <input 
                    	type="text" 
                    	name="token" 
                    	placeholder="请输入验证码"
                    	data-required="true" data-required-message="验证码不能为空！">
                    <a class="ui-icon-close"></a>
                </div>
                
                <!-- 提示信息 -->
                <div class="ui-tips ui-tips-info">
	                <i style="position:relative; top:1px;"></i><span>酷车管家需要您提供真实信息。</span>
	            </div>
	            
                <button type="submit" class="ui-btn-lg">提交</button>
            </form>
        </div>
        
        <div class="ui-border-t" style="padding-top:30px;">
        	<p style="text-align: center;">
        		<a class="ui-txt-warning" href="http://www.51qcgj.com/index.php/wap/lqdz.html" style="font-size:36px; font-weight:900;">领取车贴</a>
        	</p>
        </div>
        
	</section>
	
	<!-- 页尾 -->
    <jsp:include page="../../../comm/frozenui/foot.jsp"></jsp:include>
</body>
<!-- 公共脚本引入 -->
<jsp:include page="../../../comm/frozenui/script.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		
		var validate = window.validate;
		
		$('#send-token').on('click', function(){
			var $button = $(this);
			var text = $button.text();
			var $mobile = $button.siblings('input');
			
			//手机号验证
			var result = validate.check($mobile);
			if(!result) return;
			
			//生成二维码
			var url = window.basePath + 'token/generate?' + new Date().getTime();
			$.post(url, {
				mobile:$mobile.val()
			},function(data){
				console.log(data);
			});
			
			//60s内不可用
			var disableLast = 60;
			$button.attr('disabled', true).text(disableLast+'s');
			var interval = setInterval(function(){
				disableLast = disableLast - 1;
				if(disableLast <= 0){
					clearInterval(interval);
					$button.removeAttr('disabled').text(text);
				}else{
					$button.text(disableLast+'s');
				}
			}, 1000);
			
		});
	});
</script>
</html>