<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            审核
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
        <div  >
            <form class="layui-form layui-form-pane">
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                           审核意见
                    </label>
                    <div class="layui-input-block">
                        <input id="checkreason"  type="text" id="L_title" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                
               
				
				<div class="layui-form-item" style="text-align: center;position: absolute;margin-left: 200px;bottom: 0">
                    <button class="layui-btn" lay-filter="pass" lay-submit>
                        	通过
                    </button>
                    <button class="layui-btn" lay-filter="deny" lay-submit>
                        	拒绝
                    </button>
                </div>
            </form>
            </div>
            
              
 
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">     
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        var id=${id};
        	layui.use([ 'form', 'layer','laytpl','laydate' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer,
        			laydate = layui.laydate,
        			laytpl = layui.laytpl;
        			
            
        		//监听提交
        		form.on('submit(pass)', function(data) {
        			changestatus('PASSED');
        		});
        		
        		
        		form.on('submit(deny)', function(data) {
        			changestatus('DENY');
        		});
        		
        		
        		function changestatus(status){
	        		
        		    $.ajax({
						url:'changestate',
						type:'post',
						data:{'id':id,'status':status,'checkreason':$('#checkreason').val()},
						success:function(msg){
							var index = parent.layer.getFrameIndex(window.name);
    						parent.layer.close(index);
    						parent.getuserList();	
						}
					})
        		
        		}
        		
        	  
        });
        
         function open_win(title,url,w,h){
	         	x_admin_show(title,url,w,h);
	         }
        
        </script>
       
    </body>

</html>