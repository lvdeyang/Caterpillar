<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            轮播图详情
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
     	<div class="x-nav">
			   <span class="layui-breadcrumb">
			    <a><cite>首页</cite></a>
			    <a><cite>投票大赛</cite></a>
			    <a><cite>轮播图详情</cite></a>
			   </span> 
		</div>
        <div class="x-body" style="height:1500px">
            <form class="layui-form layui-form-pane">
            	  <div class="layui-form-item">
            	  
            	  	<label for="title" class="layui-form-label">
                                                           标题
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="title" required lay-verify="required" value="${title}"
                        autocomplete="off" class="layui-input">
                    </div>
            	  
                    <label for="shopaddress" class="layui-form-label">
                                                           商家地址
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="shopaddress" required lay-verify="required" value="${shopaddress}"
                        autocomplete="off" class="layui-input">
                    </div>
                    
                    
                    <label for="shopphone" class="layui-form-label">
                                                           商家电话
                    </label>
                    <div class="layui-input-block">
                        <input type="number" id="L_title" name="shopphone" required lay-verify="required" value="${shopphone}"
                        autocomplete="off" class="layui-input">
                    </div>
                    
                    
                    
                  </div>
                  
            	
 				  <div class="layui-form-item">
                    <label for="picdetails" class="layui-form-label">
 						详情：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="picdetails"  placeholder="请输入内容" class="layui-input" id="picdetails" style="height:300px">${picdetails}</textarea>
                    </div>
                </div>
                
            	<input type="hidden" name="picId" value="${picId}" class="layui-input">
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add2" lay-submit >
						提交详情
                    </button>
                </div>
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        
         //实例化编辑器
    		var um = UM.getEditor('picdetails');
    		
            
        
            layui.use(['form','layer','laydate','jquery'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
   
   			//监听提交
              form.on('submit(add2)', function(data){
              	var message = "增加成功"
              	add(data,message);
              	return false;
             });
   
            });
            
 			
 
 
           function add(data,message){
				console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"addpicdetails",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert(message, {icon: 6},function () {
                           // 获得frame索引
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
                               console.log(index);
                               //关闭当前frame
                               parent.layer.close(index);
                             }catch(exception)
                             {
                              layer.msg("系统错误！",{icon:2,time:3000});
                             }
                           });
                        }
                       }
                }) 
              }
              
              
        </script>
    </body>

</html>