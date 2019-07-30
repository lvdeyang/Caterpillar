<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            添加房间
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
        <div class="x-body">
        <div  >
            <form class="layui-form layui-form-pane">
            	<input type="text" name="merchantId" value="${merchantId}" hidden="hidden"/>
           	 	<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                                层                                     
                    </label>
                    <div class="layui-input-block">
                        <input type="radio" name="tier" value="1" title="一层" checked/>
						<input type="radio" name="tier" value="2" title="二层"/>
						<input type="radio" name="tier" value="3" title="三层"/>
						<input type="radio" name="tier" value="4" title="四层"/>
						<input type="radio" name="tier" value="5" title="五层"/>
						<input type="radio" name="tier" value="6" title="六层"/>
						<input type="radio" name="tier" value="7" title="七层"/>
						<input type="radio" name="tier" value="8" title="八层"/>
                    </div>
                </div>
                
 				<div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                                                            名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="title" name="name" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                         价钱                                     
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="price" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                     房间类型                                                                                  
                    </label>
                    <div class="layui-input" >
                        <input type="radio" name="identity" value="标准间" title="标准间" checked/>
						<input type="radio" name="identity" value="豪华间" title="豪华间"/>
						<input type="radio" name="identity" value="三人间" title="三人间"/>
						<input type="radio" name="identity" value="五人间" title="五人间"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="voterule" class="layui-form-label">
 					房间详情
                    </label>
                    <div class="layui-input-block">
                        <textarea name="roomdetails"  placeholder="请输入内容" class="layui-input" id="roomdetails" style="height:300px"></textarea>
                    </div>
                </div>
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
        	var um = UM.getEditor('roomdetails');
        
        	layui.use([ 'form', 'layer','laytpl' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer,
        			laytpl = layui.laytpl;
        
        		//监听提交
        		form.on('submit(add)', function(data) {
        			console.log(data.field);
	        		layer.load();
        			$.ajax({
        				type : "post",
        				url : "add.do",
        				data : data.field,
        				success : function(msg) {
        					if(msg=="success"){
        						layer.alert("添加成功",{icon:2,time:1000});
        					}
        					//关闭当前frame
                            parent.window.location.reload();
                            parent.layer.close(index);
        				}
        			})
        
        			return false;
        		});
        		
        
        });
        </script>
       
        
    </body>

</html>