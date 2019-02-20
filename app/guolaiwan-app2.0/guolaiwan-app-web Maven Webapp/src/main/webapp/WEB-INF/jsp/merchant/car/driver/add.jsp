<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
             司机管理
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
        <div style="margin: 0 auto;width:500px" >
            <form class="layui-form layui-form-pane" >
            	
            
            <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 
				 正面照片 
				</label>
				<div class="layui-input-inline" style="width: 150px">
  					<img class="layui-upload-img" style="width:150px;height:180px" id="peopic" style="display:inline">
					<input type="hidden" id="photo" name="photo" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<button type="button" class="layui-btn"  id="uppeopic">上传照片</button>
				</div>
			</div>
 
 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                          真实姓名
                    </label>
                    <div class="layui-input-block" style="width: 300px" >
                        <input type="text" id="L_title" name="name" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                          身份证号
                    </label>
                    <div class="layui-input-block" style="width: 300px">
                        <input type="text" id="L_title" name="IDnumber" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                
                
                <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 
				 行驶本正面照 
				</label>
				<div class="layui-input-inline" style="width: 180px">
  					<img class="layui-upload-img" style="width:180px;height:150px" id="bookpic" style="display:inline">
					<input type="hidden" id="drivingBook" name="drivingBook" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<button type="button" class="layui-btn"  id="upbookpic">上传照片</button>
				</div>
			</div>
               
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                           农行卡号
                    </label>
                    <div class="layui-input-block" style="width: 300px">
                        <input type="text" id="L_title" name="bankNumber" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                  车牌号码
                    </label>
                    <div class="layui-input-block" style="width: 300px">
                        <input type="text" id="L_title" name="carNumber" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                  <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                  车辆型号
                    </label>
                    <div class="layui-input-block" style="width: 300px">
                        <input type="text" id="L_title" name="carModel" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                           载客数量
                    </label>
                    <div class="layui-input-block" style="width: 300px">
                        <input type="text" id="L_title" name="passenger" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
               
                <div class="layui-form-item" style="text-align: center">
                    <button class="layui-btn layui-btn-normal"  lay-filter="add" lay-submit>
                        保存
                    </button>
                </div>
            </form>
            </div>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer','upload'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,upload = layui.upload;

              //监听提交
              form.on('submit(add)', function(data){
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
                        
                        else if(msg=="iDnumberHas"){
                          layer.alert("身份证已被注册！", {icon: 5});
                        }
                        
                        else if(msg=="norool"){
                          layer.alert("未获取到商家！", {icon: 5});
                        }
                        //提示
                        else{
                        	layer.alert("系统错误！",{icon:5});
                        }
                       }
                }) 
              
                return false;
              });
              
            //上传正面照
  			var uploadInst = upload.render({
    			elem: '#uppeopic'
    			,size: 1024
    			,url: 'peopic.do'
    			,before: function(obj){
		      		obj.preview(function(index, file, result){
        				$('#peopic').attr('src', result); //图片链接（base64）
      				});
    			}
    			,done: function(res){
    				console.log(res);
		      		if(res.code > 0){
        				return layer.msg('上传失败',{icon:5});
      				}
      				$("#photo").val(res.path);
      				return layer.msg('上传成功',{icon:6});
    			}
    			,error: function(){
      				//演示失败状态，并实现重传
      				return layer.msg('上传失败',{icon:5});
    			}
  			});
  			
  			//上传行车照片
  			var uploadInst = upload.render({
    			elem: '#upbookpic'
    			,url: 'bookpic.do'
    			,size: 1024
    			,before: function(obj){
		      		obj.preview(function(index, file, result){
        				$('#bookpic').attr('src', result); //图片链接（base64）
      				});
    			}
    			,done: function(res){
		      		if(res.code > 0){
        				return layer.msg('上传失败',{icon:5});
      				}
      				$("#drivingBook").val(res.path);
      				return layer.msg('上传成功',{icon:6});
    			}
    			,error: function(){
      				//演示失败状态，并实现重传
      				return layer.msg('上传失败',{icon:5});
    			}
  			});
         });
             //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
           
        </script>
        
    </body>

</html>