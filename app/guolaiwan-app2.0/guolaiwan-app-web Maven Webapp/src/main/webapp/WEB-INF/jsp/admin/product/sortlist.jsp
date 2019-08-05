<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>阳光成单系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
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
 

<fieldset class="layui-elem-field site-demo-button" style="margin-top: 30px;">
<div class="layui-form-item">
       
    
     <label class="layui-form-label">商品名称</label>
    <div class="layui-input-block">
      <input type="text"  id="proName" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" value="${product.productName}">
    </div>

     <label class="layui-form-label">所属商家</label>
    <div class="layui-input-block">
      <input type="text"  id="merchantName" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" value="${merchant.shopName}">
    </div>
   
          
    <label class="layui-form-label">排序索引</label>
    <div class="layui-input-block">
      <input type="text" id="sortNumber" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" value="${nowOrder}">
    </div>
  </div>

  <div style="text-align: center;">
  
    <button type="button" class="layui-btn" onclick="upMove()" >上移</button>
    <button type="button" class="layui-btn" onclick="downMove()" >下移</button>
    <button type="button" class="layui-btn" onclick="topMove()" >置顶</button>
    
  </div>
</fieldset>

     <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"
		charset="utf-8">
       </script>

	<script> 
   	 layui.use( 'layer', function(){
                $ = layui.jquery;//jquery
                layer = layui.layer;//弹出层
              //以上模块根据需要引入     
     }) 

          //上移 下移 置顶  状态存储
            var state =null;
            function upMove(){ 
             state= 0 ;
             sortChange();             
               }
           function downMove(){
              state= 1 ;           
              sortChange(); 
           }
           function topMove(){
              state= 2 ;             
             sortChange();       
           }
         //修改排序顺序  
        function sortChange(){
          $.ajax({
                    type:"post",
                    url:"sortChange",
                    data:{"merchantId":${merchant.id},
                          "productId":${product.id},
                          "state":state                          
                         },
                    success:function(msg){
                  	 if(msg=="success"){                        
                  		layer.msg('成功!',{icon:1,time:1000});
                  		location.reload(); 
                  	 }else{
                  	    layer.msg('失败!',{icon:1,time:1000});
                  	 }
                    }
                  })                               
         }    
     </script>
</body>

</html>