<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文件管理系统配置</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-body">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">服务器地址</label>
				<div class="layui-input-block">
					<input type="text" name="webUrl" lay-verify="required"
						autocomplete="off" placeholder="请输入服务器地址" class="layui-input" value="${sysConfig.webUrl}">
						
						</input>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文件路径</label>
				<div class="layui-input-block">
					<input type="text" name="folderUrl" lay-verify="required"
						autocomplete="off" placeholder="请输入文件路径" class="layui-input" value="${sysConfig.folderUrl}">
						
						</input>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-filter="add" lay-submit>保存</button>
				</div>
			</div>
		</form>
	</div>
	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"
		charset="utf-8">
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
           			  url:"addSys.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("保存成功", {icon: 6},function () {
                           // 获得frame索引
                          try{
                               var index = parent.layer.getFrameIndex(window.name);
                               console.log(index);
                               //关闭当前frame
                               parent.window.location.reload();
                               parent.layer.close(index);
                             }catch(exception)
                             {
                               window.location.reload();
                             }
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