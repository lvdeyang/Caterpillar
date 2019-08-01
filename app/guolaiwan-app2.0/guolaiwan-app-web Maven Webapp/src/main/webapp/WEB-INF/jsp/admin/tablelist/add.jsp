<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            添加活动
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
                    <label for="title" class="layui-form-label">
                                                            名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="name" name="name" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                                                            桌号
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="tableNo" name="tableNo" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                                    层                                     
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="title" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                         人数                                                                                  
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="title" name="type" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                         订金                                                                                 
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="bookprice" name="bookprice" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                      是否是包间                                                                                
                    </label>
                    <div class="layui-input-block" id="radio">
                           <input type="radio" name="radio" title="否" value="0">
                           <input type="radio" name="radio" title="是" value="1">
                    </div>
                </div>
                <input  name="merchantId" value="${merchantId}" style="display: none;">
				<div id="view"></div> 
				<div class="layui-form-item" style="text-align: center;position: absolute;margin-left: 200px;bottom: 0">
                    <button class="layui-btn" lay-filter="add" lay-submit>
                        	保存
                    </button>
                </div>
            </form>
            </div>
            
              
 <jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">     
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        	layui.use([ 'form', 'layer','laytpl' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer,
        			laytpl = layui.laytpl;
        
        		//监听提交
        		form.on('submit(add)', function(data) {
        			console.log(data.field);
        			alert(${merchantId});
	        		layer.load();
        			$.ajax({
        				type : "post",
        				url : "add.do",
        				data : data.field,
        				success : function(msg) {
        				//关闭当前frame
                           /*  parent.window.location.reload(); */
                            var index = parent.layer.getFrameIndex(window.name);  
                            parent.layer.close(index);//关闭当前页
                            parent.list(); 
        				}
        			})
        
        			return false;
        		});
        		
        
        });
        </script>
       
        
    </body>

</html>