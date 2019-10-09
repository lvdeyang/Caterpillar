<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <meta charset="utf-8">
        <title>
           地区分级系统
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    </head>
  
    <body>
        <div class="x-body">
            <form class="layui-form layui-form-pane">
            <input type="hidden" name="uuid" value="${list.uuid}">
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             地区名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="name" required lay-verify="title" value="${list.name}"
                        autocomplete="off" class="layui-input">
                    </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                     
                    <button class="layui-btn" lay-filter="add" lay-submit>
                        保存
                    </button>
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
                $.ajax({
                	  type:"post",
           			  url:"update.do/${parentId}",
           			  data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
						   
                           parent.layer.close(index);
                           });
                        }
                       }
                }) 
                //发异步，把数据提交给php
                return false;
              });
            });
             
        </script>
        
    </body>
</html>