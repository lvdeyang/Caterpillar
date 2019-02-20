<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>模块管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
<link
	href="<%=request.getContextPath()%>/layui/UEditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/layui/UEditor/third-party/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/layui/UEditor/third-party/template.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/layui/UEditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/layui/UEditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
	<div class="x-body" style="height:1500px">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label for="productName" class="layui-form-label"> 政策计数： </label>
					<div class="layui-input-inline">
						<input type="text" id="productName" name="proCount" required
							lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<input type="hidden" name="proid" value="${d.product_id}" />
				<div class="layui-inline">
					<label for="productSubtitle" class="layui-form-label">
						政策价格： </label>
					<div class="layui-input-inline">
						<input type="text" id="productSubtitle" name="proPrice" required
							lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<button class="layui-btn" lay-filter="addPolicySubmit" lay-submit>
						提交</button>
				</div>
			</div>
		</form>
		<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"
			charset="utf-8">
        </script>
		<script src="<%=request.getContextPath()%>/layui/js/x-layui.js"
			charset="utf-8">
        </script>
		<script>
		
			layui.use([ 'form', 'layer' ], function() {
				$ = layui.jquery;
				var form = layui.form,
					layer = layui.layer;
		
				//监听提交
				form.on('submit(addPolicySubmit)', function(data) {
					var message = "增加成功";
					add(data, message);
					return false;
				});
			});
		
			function add(data, message) {
				$.ajax({
					type : "post",
					url : "addPolicySubmit.do?id=${productId}",
					data : data.field,
					success : function(msg) {
						if (msg == "success") {
							layer.alert(message, {
								icon : 6
							}, function() {
								// 获得frame索引
								try {
									var index = parent.layer.getFrameIndex(window.name);
									console.log(index);
									//关闭当前frame
									parent.window.location.reload();
									parent.layer.close(index);
								} catch (exception) {
									window.location.reload();
								}
							});
						}
					}
				});
			}
		</script>
</body>
</html>