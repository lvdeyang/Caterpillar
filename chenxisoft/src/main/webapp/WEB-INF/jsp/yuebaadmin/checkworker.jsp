<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>
</head>

<body class="layui-layout-body">
<div class="x-body">
    <form class="layui-form" action="">
	  <input type="hidden" name="id" value="${id}"/>
	  <div class="layui-form-item">
	    <label class="layui-form-label">身份证照片</label>
	    <div class="layui-input-block">
	      <c:if test="${!empty worker.idCardPhoto}">
	           <image src="/${worker.idCardPhoto}" style="width:300px;height:200px;"/>
	      </c:if>
	      
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">健康证明</label>
	    <div class="layui-input-block">
	      <c:if test="${!empty worker.healthPhoto}">
	           <image src="/${worker.healthPhoto}" style="width:300px;height:200px;"/>
	      </c:if>
	   
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">专业照片</label>
	    <div class="layui-input-block">
	      <c:if test="${!empty worker.healthPhoto}">
	          <image src="/${worker.expertPhoto}" style="width:300px;height:200px;"/>
	      </c:if>
	      
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">回复</label>
	    <div class="layui-input-block">
	      <input type="text" name="checkMsg" lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	  </div>
      <div class="layui-form-item" style="margin-left:50px;">
           <button class="layui-btn" lay-filter="agree" lay-submit>
                                                同意
           </button>
           <button class="layui-btn" lay-filter="refuse" lay-submit>
                                                 拒绝
           </button>
      </div>
</form>
</div>
	<script>
		//JavaScript代码区域
		layui.use(['form','layer'],function(){
			  var $ = layui.jquery;
			  var form = layui.form,layer = layui.layer;

              //监听提交
              form.on('submit(agree)', function(data){
          
                $.ajax({
                	  type:"post",
           			  url:"worker/agree.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                           layer.alert("审核已通过", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
                           });
                        }
                        //提示
                        else{
                        
                        }
                       }
                }) 
              
                return false;
            });
            
            
            //监听提交
              form.on('submit(refuse)', function(data){
          
                $.ajax({
                	  type:"post",
           			  url:"worker/refuse.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                           layer.alert("审核已拒绝", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
                           });
                        }
                        //提示
                        else{
                        
                        }
                       }
                }) 
              
                return false;
            });
 
           
			
		});
	</script>
</body>
</html>
