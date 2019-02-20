<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            模块管理
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend>选择板块分类</legend>
</fieldset>
  ${list}
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        
        <script>
layui.use(['element', 'layer','jquery'], function(){
 $ = layui.jquery;//jquery
  var element = layui.element;
  var layer = layui.layer;
  
  //监听折叠
  element.on('collapse(test)', function(data){
    layer.msg('展开状态：'+ data.show);
  });
  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
   $(".xuan").click(function(){
   	var merMId=$(this).attr("merMId");
   	var merMName=$(this).attr("merMName");
   	console.log(merMId);
   	  parent.$("input[name='merMId']").val(merMId);
   	  parent.$("input[name='merMName']").val(merMName);
      parent.layer.close(index);
   })
});


</script>
    </body>

</html>
