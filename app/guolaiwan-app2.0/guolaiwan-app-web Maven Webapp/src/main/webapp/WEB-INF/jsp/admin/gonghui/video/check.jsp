<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>工会视频审核</title>
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
	<div class="x-body" style="height: 1800px;">
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="id" value="${video.id}">
			
			
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label">海报图片</label>
				<div class="layui-input-block">
					<img style="width:300px;height:200px" src="${webPath}/${video.coverUrl}"/>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label">上传视频</label>
				<div class="layui-input-block">
					<video style="width:300px;height:200px" src="${webPath}/${video.playUrl}" autoplay controls="controlss"/>
				</div>
			</div>
  			
  			<div class="layui-form-item">
    			<label class="layui-form-label">审核意见</label>
    			<div class="layui-input-block">
     				<input type="text" name="reason" autocomplete="off" class="layui-input">
    			</div>
  			</div>
  			
			<div class="layui-form-item">
				<button class="layui-btn" lay-filter="agree" lay-submit>同意</button>
				<button class="layui-btn" lay-filter="reject" lay-submit>拒绝</button>
			</div>
		</form>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>
	<script>
			//实例化编辑器
		    var um = UM.getEditor('shopIntroduction');
    
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
               
              //监听提交
              form.on('submit(agree)', function(data){
                var obj=data.field;
                obj.result=1;
                $.ajax({
                	  type:"post",
           			  url:"checkResult.do",
                      data:obj,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
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
              
                form.on('submit(reject)', function(data){
                 var obj=data.field;
                obj.result=0;
                $.ajax({
                	  type:"post",
           			  url:"checkResult.do",
                      data:obj,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
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
