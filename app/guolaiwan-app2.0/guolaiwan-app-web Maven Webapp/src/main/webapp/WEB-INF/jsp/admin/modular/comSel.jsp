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
<div class="layui-collapse" lay-accordion="">
  ${list}
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        
        <script>
layui.use(['element', 'layer','jquery'], function(){
 $ = layui.jquery;//jquery
  var element = layui.element;
  var layer = layui.layer;
  var indexparam=${index};
  //监听折叠
  element.on('collapse(test)', function(data){
    layer.msg('展开状态：'+ data.show);
  });
  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
   $(".child").click(function(){
   	var mcode=$(this).attr("data-mcode");
   	var mname=$(this).attr("data-mname");
   	var ccode=$(this).attr("data-ccode");
   	var cname=$(this).attr("data-cname");
   	  if(indexparam==0){
   	      parent.$("input[name='modularName']").val(mname);
	      parent.$("input[name='modularCode']").val(mcode);
	      parent.$("input[name='modularClass']").val(cname);
	      parent.$("input[name='modularClassId']").val(ccode);
   	  }else{
   	      parent.$("input[name='modularName"+indexparam+"']").val(mname);
	      parent.$("input[name='modularCode"+indexparam+"']").val(mcode);
	      parent.$("input[name='modularClass"+indexparam+"']").val(cname);
	      parent.$("input[name='modularClassId"+indexparam+"']").val(ccode);
   	  }
   	  
      if(mcode=='2021'){
         parent.$(".proLine").show();
      }else{
         parent.$(".proLine").hide();
      }
      
   
      
      
      
      parent.layer.close(index);
   })
});


</script>
    </body>

</html>
