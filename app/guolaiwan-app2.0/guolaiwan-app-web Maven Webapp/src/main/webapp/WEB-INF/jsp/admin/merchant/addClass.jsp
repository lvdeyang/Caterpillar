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
			
				<c:choose>
					<c:when test="${has ==0}">
					<div class="layui-form-item">
						<label for="L_title" class="layui-form-label"> 上一级 </label>
						<div class="layui-input-block" style="width: 300px">
							<input type="hidden" value="0" name="pId">
							<input class="layui-input" style="border: 0"readonly="readonly" lay-verify="required" value="${merchant.shopName}&nbsp;&nbsp;&nbsp;(商家)">
						</div>
					</div>
					</c:when>
					<c:otherwise>
					<div class="layui-form-item">
						<label for="L_title" class="layui-form-label"> 上一级 </label>
						<div class="layui-input-block" style="width: 300px">
						<select lay-verify="required" name="pId" id="pId">
							<option value="0">${merchant.shopName}(商家)</option>
							<c:forEach  items="${merModulars}" var="merModular"><!-- ${merModular.id}${merModular.name}${merModular.level} -->
								<option value="${merModular.id}">${merModular.parentName}---${merModular.name}(第${merModular.level}级)</option>
							</c:forEach>
      					</select>
						</div>
					</div>
					</c:otherwise>
					
				</c:choose>	
				
			<input type="hidden" name="merchant" value="${merchant.uuid}">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 分类名称 </label>
				<div class="layui-input-block" style="width: 300px">
					<input type="text" id="L_title" name="mmName" id="mmName" required
						lay-verify="required" autocomplete="off" class="layui-input">
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
				
				
			
				
              //监听提交
              form.on('submit(add)', function(data){
              layer.load();
              var mmName = data.field.mmName;
              console.log(mmName);
              //价钱
              console.log(data.field);
              var pname = $("#pId").find("option:selected").text();
              pname = pname.split("(")[0];
                $.ajax({
                	  type:"post",
           			  url:"addClass.do",
                      data:data.field,
                      success:function(msg){
                      	console.log(msg);
                      	layer.closeAll("loading"); 
                        if(msg=="success"){
                          layer.msg("增加成功", {icon: 6,time:500},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
                           });
                        }else if(msg=="has"){
                        	layer.msg(pname+'下已有"'+mmName+'"分类！',{icon:5});
                        	
                        }
                        //提示
                        else{
                        	layer.msg("系统异常！",{icon:5});
                        	parent.layer.close(index);
                        }
                       }
                }) 
              
                return false;
              });
              });
        </script>

</body>
</html>