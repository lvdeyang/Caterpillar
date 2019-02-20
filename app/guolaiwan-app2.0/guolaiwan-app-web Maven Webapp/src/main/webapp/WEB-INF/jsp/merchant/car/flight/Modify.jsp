<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
       <meta charset="utf-8">
        <title>
            阳光成单系统
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
		<div style="margin: 0 auto;width:500px">
		<form class="layui-form layui-form-pane">
			<input name="uuid" type="hidden" value="${list.uuid}">
			<c:if test="${list.flightType == '固定时间'}">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 发车时间 </label>
				<div class="layui-input-inline">
        			<input type="text" class="layui-input" id="goTime" name="goTime" lay-verify="required" value="${list.goTime}">
      			</div>
			</div>
			</c:if>
			
		
			<div class="layui-form-item">
    			<label class="layui-form-label">选择司机：</label>
    			<div class="layui-input-block">
    			  <select lay-verify="required" name="driverId">
    				<c:forEach items="${drivers}" var="driver">
      				
      				      <option value="${driver.id}">  ${driver.name}</option>
      				</c:forEach>
      				   </select>
    			</div>
  			</div>
			
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 价钱 </label>
				<div class="layui-input-block" style="width: 300px">
					<input type="text" id="L_title" name="price" required
						lay-verify="required" autocomplete="off" class="layui-input" placeholder="￥" value="${list.price}">
				</div>
			</div>
			
			
			<div class="layui-form-item" style="text-align:center;	position: absolute;margin-left: 200px;bottom: 0">
				<button class="layui-btn" lay-filter="add" lay-submit>保存</button>
			</div>
		</form>
		</div>
	</div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
               var form = layui.form
              ,layer = layui.layer
              ,laydate =layui.laydate;
              
              laydate.render({
    			elem: '#goTime'
    			,type: 'time'
  			}); 
               
              //监听提交
              form.on('submit(add)', function(data){
             
              
              
              if(data.field.driverId==undefined){
              		console.log("1");
              		layer.msg("您还没有选司机！",{icon:5});
              		return false;
              }
              //价钱
              var price = parseFloat(data.field.price);
              data.field.price = price.toFixed(2);
              
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
            
                       //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
        </script>
        
    </body>

</html>
