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


			    


	  <div class="layui-form-item">
	    <label class="layui-form-label">YEAR</label>
	    <div class="layui-input-block">
	      <input type="text" name="year" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">营业收入</label>
	    <div class="layui-input-block">
	      <input type="text" name="allget" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">营业利润</label>
	    <div class="layui-input-block">
	      <input type="text" name="fleft" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">净利润</label>
	    <div class="layui-input-block">
	      <input type="text" name="realLeft" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>

	  <div class="layui-form-item">
	    <label class="layui-form-label">净资产收益率</label>
	    <div class="layui-input-block">
	      <input type="text" name="returnassets" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">营业现金</label>
	    <div class="layui-input-block">
	      <input type="text" name="realcash" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">投资现金</label>
	    <div class="layui-input-block">
	      <input type="text" name="postcash" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">融资现金</label>
	    <div class="layui-input-block">
	      <input type="text" name="getcash" lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
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
           			  url:"stock/finance/add.do?stockId=${stockId}",
                      data:data.field,
                      success:function(msg){
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
                        
                        }
                       }
                }) 
              
                return false;
            });
 
           
			
		});
	</script>
</body>
</html>
