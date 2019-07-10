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
	  <input type="hidden" name="workerId" value="${workerId}"/>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">入户日期</label>
	    <div class="layui-input-block">
	        <input type="text" name="fromDate" id="fromDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" lay-key="1">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">地区选择</label>
	    <div class="layui-input-block">
	      <select name="region" lay-filter="region">
	         <c:forEach items="${regionList}" var="region">
                <option value="${region.regionName}">${region.regionName}</option>
             </c:forEach>
	        
	      </select>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">天数选择</label>
	    <div class="layui-input-block">
	      <select name="days" lay-filter="days">
	        <c:forEach items="${daysList}" var="days">
                <option value="${days.days}">${days.days}</option>
             </c:forEach>
	        
	      </select>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">佣金</label>
	    <div class="layui-input-block">
	      <input type="text" name="price" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">提成</label>
	    <div class="layui-input-block">
	      <input type="text" name="left" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户名称</label>
	    <div class="layui-input-block">
	      <input type="text" name="userName" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户电话</label>
	    <div class="layui-input-block">
	      <input type="text" name="userPhone" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
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
		layui.use(['form','layer','laydate'],function(){
		   var $ = layui.jquery;
		   var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;


               laydate.render({
				    elem: '#fromDate',
				    min:'${minDate}',
			        max:'2029-09-28'
			   });
               
              //监听提交
              form.on('submit(add)', function(data){
              console.log(data.field);
          
                $.ajax({
                	  type:"post",
           			  url:"order/add.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("增加成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           //parent.window.location.reload();
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
