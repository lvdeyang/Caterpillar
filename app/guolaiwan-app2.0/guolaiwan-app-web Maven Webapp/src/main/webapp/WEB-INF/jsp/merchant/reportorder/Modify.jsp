<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            修改大数据订单
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
            <input type="hidden" name="id" value="${order.id}">
                 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                                      姓名
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="name" required lay-verify="required"
                        autocomplete="off" class="layui-input" value="${order.name}">
                    </div>
                </div>
 
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                           身份证号
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="idCard" required lay-verify="required"
                        autocomplete="off" class="layui-input"  value="${order.idCard}">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                           选择商品
                    </label>
                    <div class="layui-input-block">
                        <select id="productId" name="productId" lay-filter="productId" required lay-verify="required" value="${order.productId}">
	                    	<c:forEach items="${pros}" var="pro" varStatus="prosta">
								<option value="${pro.id}">${pro.productName}</option>
							</c:forEach>
                    	</select>
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                          价格(分)
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="price" required lay-verify="required"
                        autocomplete="off" class="layui-input" value="${order.price}">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                          数量
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="count" required lay-verify="required"
                        autocomplete="off" class="layui-input" value="${order.count}">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                           选择类型
                    </label>
                    <div class="layui-input-block">
                        <select id="type" name="type" lay-filter="type" required lay-verify="required" value="${order.type}">
	                    	<option value="0">线下</option>
							<option value="1">旅行社</option>
							<option value="2">三方OTA</option>
                    	</select>
                    </div>
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
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"edit.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           parent.window.location.reload();
                           //关闭当前frame
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
