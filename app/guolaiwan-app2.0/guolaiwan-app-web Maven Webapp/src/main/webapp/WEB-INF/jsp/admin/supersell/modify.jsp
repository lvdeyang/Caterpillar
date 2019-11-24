<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            卖场商品配置
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
         <form action="<%=request.getContextPath() %>/admin/supersell/modify.do" method="post" class="layui-form layui-form-pane">
            	<input type="hidden" value="${rel.id}" name="relId" />
               
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            设置原价
                    </label>
                    <div class="layui-input-block">
                    <input type="text" id="oldPrice" name="oldPrice" required lay-verify="required"
                        	class="layui-input"  value="${rel.oldPrice}">
                   
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            设置价格
                    </label>
                    <div class="layui-input-block">
                    <input type="text" id="price" name="price" required lay-verify="required"
                        	class="layui-input"  value="${rel.price}">
                   
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <input class="layui-btn" type="submit" value="修改" />
                </div>
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        
         //实例化编辑器
        	var webUrl = "${sysConfig.webUrl}";
             
             layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
              
             
            });
        </script>
    </body>
</html>