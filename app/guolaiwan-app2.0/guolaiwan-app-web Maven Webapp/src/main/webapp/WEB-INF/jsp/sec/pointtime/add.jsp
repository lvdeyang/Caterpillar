<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            添加打卡点时间
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
                                                           打卡开始时间
                    </label>
                    <div class="layui-input-inline">
                        	<input type="text" id="setStartTime" name="setStartTime" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" >
                    	</div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                           打卡时间
                    </label>
                    
                    <div class="layui-input-inline">
                        	<input type="text" id="setTime" name="setTime" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" >
                    	</div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                           打卡结束时间
                    </label>
                    <div class="layui-input-inline">
                        	<input type="text" id="setEndTime" name="setEndTime" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" >
                    	</div>
                </div>
                
                
                
				
				<div class="layui-form-item" style="text-align: center;position: absolute;margin-left: 200px;bottom: 0">
                    <button class="layui-btn" lay-filter="add" lay-submit>
                        	保存
                    </button>
                </div>
            </form>
            </div>
            
              
 
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">     
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        var pointId=${pointId};
        	layui.use([ 'form', 'layer','laytpl','laydate' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer,
        			laydate = layui.laydate,
        			laytpl = layui.laytpl;
        			
            
        		//监听提交
        		form.on('submit(add)', function(data) {
        			console.log(data.field);
	        		layer.load();
        			$.ajax({
        				type : "post",
        				url : "add.do?pointId="+pointId,
        				data : data.field,
        				success : function(msg) {
        					layer.closeAll("loading");
        					if (msg == "success") {
        						layer.msg("增加成功", {
        							icon : 6,
        							time : 500
        						}, function() {
        							// 获得frame索引
        							var index = parent.layer.getFrameIndex(window.name);
        							//关闭当前frame
        							parent.layer.close(index);
        							parent.getpointtimeList();
        						});
        					}else{
        						layer.msg("系统错误！",{icon:2,time:3000});
        					}
        				}
        			})
        
        			return false;
        		});
        		
        	  //时间选择器
			  laydate.render({
			    elem: '#setTime'
			    ,type: 'time'
			  });
			  laydate.render({
			    elem: '#setEndTime'
			    ,type: 'time'
			  });
			  laydate.render({
			    elem: '#setStartTime'
			    ,type: 'time'
			  });
        });
        
         function open_win(title,url,w,h){
	         	x_admin_show(title,url,w,h);
	         }
        
        </script>
       
    </body>

</html>