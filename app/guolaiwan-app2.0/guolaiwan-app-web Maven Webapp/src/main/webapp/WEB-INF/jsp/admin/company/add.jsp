<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            添加分公司
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
        <div style="margin: 0 auto;width:400px" >
            <form class="layui-form layui-form-pane">
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="comName" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            域名
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="domain" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
				<div class="layui-form-item" >
                    <label for="L_title" class="layui-form-label">
                                                            所在城市
                    </label>
                    <div class="layui-input-block">
                    <select  name="cityCode" id="city">
                    	<option value="">请选择</option>
                    	<c:forEach items="${citys}" var="city">
                    	<option value="${city.cityCode}">${city.cityName}</option>
                    	</c:forEach>
                    </select>
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
        	layui.use([ 'form', 'layer' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer;
        
        		//监听提交
        		form.on('submit(add)', function(data) {
        		data.field.cityName= $('#city').find("option:selected").text();
        			layer.load();
        			$.ajax({
        				type : "post",
        				url : "add.do",
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
        							parent.getCompanyList();
        						});
        					}
        				}
        			})
        
        			return false;
        		});
        	});
        </script>
        
    </body>

</html>