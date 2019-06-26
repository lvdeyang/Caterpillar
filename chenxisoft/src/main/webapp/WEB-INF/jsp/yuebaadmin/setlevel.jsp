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
	  <input type="hidden" value="${id}" name="id"/>
	  <div class="layui-form-item">
	    <label class="layui-form-label">级别选择</label>
	    <div class="layui-input-block">
	      <select name="level" lay-filter="level">
	        <c:forEach items="${levelList}" var="level">
                <option value="${level.name}">${level.name}</option>
             </c:forEach>
	        
	      </select>
	    </div>
	  </div>
      <div class="layui-form-item" style="margin-left:50px;">
           <button class="layui-btn" lay-filter="add" lay-submit>
                                                              保存
           </button>
      </div>
</form>
</div>
	<script>
		//JavaScript代码区域
		layui.use(['form','layer'],function(){
		   var $ = layui.jquery;
		   var form = layui.form
              ,layer = layui.layer;

              //监听提交
              form.on('submit(add)', function(data){
              console.log(data.field);
          
                $.ajax({
                	  type:"post",
           			  url:"worker/setlevel.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("设置成功", {icon: 6},function () {
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
