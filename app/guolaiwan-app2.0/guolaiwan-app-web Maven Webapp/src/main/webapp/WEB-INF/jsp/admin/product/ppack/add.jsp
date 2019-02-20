<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            模块管理
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
        <div class="x-body" style="height:1500px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="childName" class="layui-form-label">
                    		名称：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="name" name="name" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
 				</div>
 				
 				
				
				
				
				
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 选择图片</label>
				<div class="layui-input-inline" style="width: 100px">
				    <img id="img" src="" alt="" height="100px" width="100px">
					<input type="hidden" id="pic" name="pic" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('选择图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=pic&img=img','600','400')" class="layui-btn " >图片素材</a>
				</div>
				</div>
 				
 				
 				
 				<div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="childName" class="layui-form-label">
                    		原价：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="oldPrice" name="oldPrice" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
 				</div>
 				
 				
 				<div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="childName" class="layui-form-label">
                    		现价：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="price" name="price" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
 				</div>
 				
 				
               	
                <div class="layui-form-item">
                	<button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
						保存套餐
                    </button>
                </div>
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
       		var productID = '${productID}';	
            layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;

             //监听提交
              form.on('submit(add)', function(data){
                data.field.productID = productID;
              	var message = "保存套餐成功！";
              	add(data,message);
              	return false;
            	});
            	
            	
            });
            
           //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
           
      
           
           
           function add(data,message){
           		//校验
              	//原价
              	var OldPrice =$("#oldPrice").val();
				if(!( /^\d+(\.\d+)?$/).test(OldPrice)){
						layer.msg("原价为数字！",{time: 5000, icon:5});
						return false;
				}   
				
				//现价
				var Price =$("#price").val();
				if(!( /^\d+(\.\d+)?$/).test(Price)){
						layer.msg("现价为数字！",{time: 5000, icon:5});
						return false;
				}
				
				var oldPrice = parseFloat(data.field.oldPrice);
				var price = parseFloat(data.field.price);
           		data.field.oldPrice = oldPrice.toFixed(2);
           		data.field.price = price.toFixed(2);
				
				
				console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"add.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert(message, {icon: 6},function () {
                           // 获得frame索引
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
                               console.log(index);
                               //关闭当前frame
                               parent.window.location.reload();
                               parent.layer.close(index);
                             }catch(exception)
                             {
                               window.location.reload();
                             }
                           });
                        }
                       }
                }) 
              
               
              }
           
        </script>
        
    </body>

</html>