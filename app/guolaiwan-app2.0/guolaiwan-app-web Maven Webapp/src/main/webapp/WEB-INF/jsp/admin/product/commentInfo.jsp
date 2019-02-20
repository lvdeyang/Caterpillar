<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>阳光成单系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
<link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body>
	<div class="x-body" >
		<form class="layui-form layui-form-pane">
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 用户评论 </label>
				<div class="layui-input-block">
				<textarea readonly class="layui-textarea">${comment.content}</textarea>
				</div>
			</div>
				<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 评论图片 </label>
				<div class="layui-input-inline">
				<table>
					<tr>
					<td align="center">
					<img alt="" src="${sysConfig.webUrl}${img1}" style=" height:100px;width:100px " id="imgurl1">
					<input type="hidden" id="img1" name="imgs" >
					</td>
					<td>
					<img alt="" src="${sysConfig.webUrl}${img2}" style=" height:100px;width:100px " id="imgurl2">
					<input type="hidden" id="img2" name="imgs" >
					</td>
					<td>
					<img alt="" src="${sysConfig.webUrl}${img3}" style=" height:100px;width:100px " id="imgurl3">
					<input type="hidden" id="img3" name="imgs" >
					</td>
					<td>
					<img alt="" src="${sysConfig.webUrl}${img4}" style=" height:100px;width:100px " id="imgurl4">
					<input type="hidden" id="img4" name="imgs" >
					</td>
					<td>
					<img alt="" src="${sysConfig.webUrl}${img5}" style=" height:100px;width:100px " id="imgurl5">
					<input type="hidden" id="img5" name="imgs" >
					</td>
					<td>
					<img alt="" src="${sysConfig.webUrl}${img6}" style=" height:100px;width:100px " id="imgurl6">
					<input type="hidden" id="img6" name="imgs" >
					</td>
					</tr>
				</table>
				</div>
			</div>
						
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商家回评 </label>
				<div class="layui-input-block">
				<textarea readonly class="layui-textarea">${comment.merContent}</textarea>
				</div>
			</div>
		</form>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>

</body>

</html>

