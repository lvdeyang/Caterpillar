<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>停车场车位管理-修改</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/layui/UEditor/third-party/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/layui/UEditor/third-party/template.min.js"></script>
</head>

<body>
	<div class="x-body">
		<form class="layui-form layui-form-pane" id="myForm">
			<input type="hidden" name="uuid" value="${po.uuid}">
			<input type="hidden" name="id" value="${po.id}">
			<input type="hidden" name="positionId" value="${po.positionId}">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 车位编号 </label>
				<div class="layui-input-block">
					<input type="text" name="positionNumber" value="${po.positionNumber}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 车位信息 </label>
				<div class="layui-input-block">
					<select name="positionInformation" id="positionInformation" required>
						<option value="">请选择车位信息</option>
						<option value="大型车车位" <c:if test='${po.positionInformation == "大型车车位"}'>selected="selected"</c:if> >大型车车位</option>
						<option value="中型车车位" <c:if test='${po.positionInformation == "中型车车位"}'>selected="selected"</c:if> >中型车车位</option>
						<option value="小型车车位" <c:if test='${po.positionInformation == "小型车车位"}'>selected="selected"</c:if> >小型车车位</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 使用情况 </label>
				<div class="layui-input-block">
					<select name="useCondition" id="useCondition" required>
						<option value="">请选择使用情况</option>
						<option value="1" <c:if test='${po.useCondition == 1}'>selected="selected"</c:if> >有车</option>
						<option value="0" <c:if test='${po.useCondition == 0}'>selected="selected"</c:if> >无车</option>
					</select>				
				</div>
			</div>
			<div class="layui-form-item" style="text-align: center;margin-top:20px;">
				<button class="layui-btn" lay-filter="add1" lay-submit> 保存修改 </button>
			</div>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js" charset="utf-8"> </script>
	<script src="<%=request.getContextPath()%>/layui/js/x-layui.js" charset="utf-8"> </script>

	<script>
		layui.use(['form','layer'], function(){
			$ = layui.jquery;
			var form = layui.form
			,layer = layui.layer
			// 监听提交
			form.on('submit(add1)', function(data){
				data.field.shopAuditstates = "D";
				var message = "提交成功！";  
				update(data,message);
				return false;
			});
		});
		
		function numChk($this){
			var nubmer = $this.val();
			var reg = /^[1-9]+[0-9]*]*$/; //判断正整数
			if(!reg.test(nubmer)){
				layer.msg("请输入大于0的数字！", { icon: 5, time: 1000 });
				$this.val("");
				return false;
			}
		}
		// 验证商户登录名称是否重复
		$("#positionNumber").blur(function() {
			var url = "verifypositionnumber.do";
			var param = {"positionNumber":$(this).val(),"positionId":$("#positionid").val()};
			$.post(url,param,function(result) {
			 	if (result=="has") {
			 		layer.msg("车位编号已存在，请更换！", {icon: 5,time:1000});
			 		$("#positionNumber").val("");
			 	}
			});
		});
        // 添加   
        function update(data,message) {
        		//不能是""
			if(data.field.positionInformation==""){
				layer.msg("请选择车位信息！",{icon:5});
				return false;
			}
			if(data.field.useCondition==""){
				layer.msg("请选择使用情况！",{icon:5});
				return false;
			}else{
				var useCondition =data.field.useCondition
				data.field.useCondition = parseInt(useCondition);
			}	
			$.ajax({
				type: "post",
				url: "parkupdate.do",
                data: data.field,
				success:function(data){
					if(data=="success"){
						layer.alert(message, {icon: 6},function () {
							try{
								var index = parent.layer.getFrameIndex(window.name);
								//关闭当前frame
								parent.window.location.reload();
								parent.layer.close(index);
							} catch(exception) {
							  	window.location.reload();
							}
						});
					}else{
						layer.alert("修改失败", {icon: 5},function () {
							try{
								var index = parent.layer.getFrameIndex(window.name);
								//关闭当前frame
								parent.window.location.reload();
								parent.layer.close(index);
							} catch(exception) {
							  	window.location.reload();
							}
						});
					}
				}
            })
        }
            
	</script>
</body>

</html>