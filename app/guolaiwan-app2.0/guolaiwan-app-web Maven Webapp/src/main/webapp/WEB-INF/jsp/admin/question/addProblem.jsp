<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            问题管理
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
                    	<label for="productName" class="layui-form-label">
                    		问题名称：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="name" name="name" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>					
 				</div>
                		
				 <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">绑定板块</label>
						<div class="layui-input-inline">
							<input lay-filter="modularName"  type="text" id="L_title" name="modularName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly" value="${modularName}">
							<input type="text" id="L_title" name="modularCode" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none" value="${modularClass}">							
						</div>
					</div>				
				</div> 
                                                                          
                <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 操作图片 </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="shopPic" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
					<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#shopImg','#shopPic')">删除图片</button>					
				</div>
			</div>		
                 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            是否显示
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="modularIsv" value="1">
                    </div>
                </div> 
                                                                       
               <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productSort" class="layui-form-label">
						排序：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="Sort" name="Sort"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                        
 				<div class="layui-form-item">
                    <label for="productIntroduce" class="layui-form-label">
 						问题介绍：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="productIntroduce"  placeholder="请输入内容" class="layui-input" id="productIntroduce" style="height:300px"></textarea>
                    </div>
                </div>
                              
            	<input type="hidden" name="productAuditstates" value="" class="layui-input">
                <div class="layui-form-item">               	
                    <button class="layui-btn" lay-filter="add" lay-submit style="margin-left:100px;" >
						提交
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
    		var um = UM.getEditor('productIntroduce');
    		UM.getEditor('costMessage');
    		UM.getEditor('notes');
    		UM.getEditor('remarks');
    		
            
        
            layui.use(['form','layer','laydate','jquery'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
   
           
           function renderForm(){
			  layui.use('form', function(){
			   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			   form.render();
			  });
			 }
          			           
              //监听提交
              form.on('submit(add)', function(data){
              	layer.load();
          
                $.ajax({
                	  type:"post",
           			  url:"problem.do?",
                      data:data.field,
                      success:function(msg){
                      	layer.closeAll("loading");
                        if(msg=="success"){
                          layer.msg("增加成功",{icon:6,time:500} ,function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.getClassList();
                           parent.layer.close(index);
                           });
                        }
                       }
                })              
                return false;
              });
             
           });
            
           //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
                   
            //删除图片 
           function delpic(img,inp){
           		$(img).removeAttr("src");
           		$(inp).val("");           	
           }          
           
            function add(data,message){
 				//多图字段          
           		var proMpic="";
           		var strPic;
           		for(var i=1;i<6;i++){
           			var str = "#img"+i;
           			if($(str).val()!=''){
           				if(proMpic==''){
           					strPic = $(str).val();
           				}else{
           					strPic = ","+$(str).val();
           				}
           				proMpic = proMpic+strPic;
           			}
           		}
           		data.field.shopMpic= proMpic; 
			
				//排序
				var Sort =$("#productSort").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(Sort))&&Sort!=""){
						layer.msg("排序为大于0的数字！",{time: 5000, icon:5});
						return false;
				}                      
              }
        </script>
    </body>

</html>