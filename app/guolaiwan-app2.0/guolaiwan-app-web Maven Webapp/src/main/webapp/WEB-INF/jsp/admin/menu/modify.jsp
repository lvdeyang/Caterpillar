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
</head>

<body>
	<div class="x-body">
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="uuid" value="${list.uuid}">
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 权限名称 </label>
				<div class="layui-input-block">
					<input type="text" id="L_title" name="name" required
						lay-verify="title" value="${list.name}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 地址 </label>
				<div class="layui-input-block">
					<input type="text" id="L_title" name="url" required
						lay-verify="title" value="${list.url}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 是否显示 </label>
				<div class="layui-input-block">
					<input type="text" id="L_title" name="showis" required
						lay-verify="title" value="${list.showis}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 排序 </label>
				<div class="layui-input-block">
					<input type="text" id="L_title" name="sort" required
						lay-verify="title" value="${list.sort}" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 父类 </label>
				<div class="layui-input-block">
					<input type="text" id="L_title" name="parentId" required
						lay-verify="title" value="${list.parentId}" autocomplete="off"
						class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<button class="layui-btn" lay-filter="add" lay-submit>保存</button>
			</div>
		</form>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>
	<script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
               
              //监听提交
              form.on('submit(add)', function(data){
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"update.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.layer.close(index);
                           });
                        }
                       }
                }) 
                
               
                return false;
              });
            });
             
        </script>

</body>

</html>
