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
<title>路线管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>

<body>
	<div class="x-body">
		<div style="margin: 0 auto;width:500px">
		<form class="layui-form layui-form-pane">
			<input type="hidden" value="${route}" name="route">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 发车时间 </label>
				<div class="layui-input-inline">
        			<input type="text" class="layui-input" id="goTime" lay-verify="required" name="goTime">
      			</div>
			</div>
			
		
			
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
						lay-verify="required" autocomplete="off" class="layui-input" placeholder="￥">
				</div>
			</div>
			
			
			<div class="layui-form-item" style="text-align:center;	position: absolute;margin-left: 200px;bottom: 0">
				<button class="layui-btn" lay-filter="add" lay-submit>保存</button>
			</div>
		</form>
		</div>
	</div>
	

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"
		charset="utf-8">
        </script>
	<script>
            layui.use(['form','layer','upload','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,upload = layui.upload
              ,laydate =layui.laydate;
				
			
			
			laydate.render({
    			elem: '#goTime'
    			,type: 'time'
  			});  	
				
				
				
              //监听提交
              form.on('submit(add)', function(data){
              
              //价钱
              var price = parseFloat(data.field.price);
              data.field.price = price.toFixed(2);
              data.field.flightType = "CARTIME";
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"add.do",
                      data:data.field,
                      success:function(msg){
                      	console.log(msg);
                        if(msg=="success"){
                          layer.alert("增加成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
                           });
                        }
                        //提示
                        else{
                        	layer.alert("标识已存在",{icon:6});
                        }
                       }
                }) 
              
                return false;
              });
              });
              
              
              
              
           
           
        </script>

</body>
</html>