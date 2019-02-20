<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            添加网点
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
        <link href="<%=request.getContextPath() %>/layui/UEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/layui/UEditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/lang/zh-cn/zh-cn.js"></script>
    </head>
    
    <body>
        <div class="x-body" style="height:1500px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="childName" class="layui-form-label">
                    		名称：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="name" name="name" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
 				</div>
 			
 				
 				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 经度： </label>
					<div class="layui-input-inline" style="width: 100px;">
						<input type="text"  name="longitude" id="longitude"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid">-</div>
					<label for="L_title" class="layui-form-label"> 纬度 ：</label>
      				<div class="layui-input-inline" style="width: 100px;">
						<input type="text" name="latitude" id="latitude"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-input-inline" style="width: 100px;">
				  		<a href="javascript:openMap('腾讯地图','<%=request.getContextPath() %>/layui/txmap.html','600','400')" class="layui-btn" >打开地图</a>
					</div>
				</div>
				
			
               	
                <div class="layui-form-item">
                	<button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
						保存网点
                    </button>
                </div>
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
       		var logId = '${logId}';	
            layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;

             //监听提交
              form.on('submit(add)', function(data){
                data.field.logId = logId;
              	var message = "保存网点成功！";
              	add(data,message);
              	return false;
            	});
            	
            	
            });
            
           //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
           
      
           
           
           function add(data,message){
				
                $.ajax({
                	  type:"post",
           			  url:"pointadd.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert(message, {icon: 6},function () {
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
              
               
              }
              
            
           
        </script>
        
    </body>

</html>