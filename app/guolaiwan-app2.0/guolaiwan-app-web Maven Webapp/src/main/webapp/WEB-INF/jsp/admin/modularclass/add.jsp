<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            子模块管理
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
            <form class="layui-form layui-form-pane">
                <!-- <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             子模块标识
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="classCode" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div> -->
                   <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            子模块名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="className" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                   <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            绑定模块
                        </label>
                        <div class="layui-input-block">
                            <select lay-verify="required" name="classmodularCode">
                            <c:forEach  items= "${list}" var="alist">
                            <option value="${alist.modularCode}">${alist.modularName}</option>
                             </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
 
 
                
               <!-- <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            是否显示
                    </label>
                    <div class="layui-input-block">
                       <input type="checkbox"name="classIsv" value="1">
                    </div>
                </div> -->
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            排序
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="classSort" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 
                <div class="layui-form-item" style="text-align: center;position: absolute;margin-left: 200px;bottom: 0">
                    <button class="layui-btn" lay-filter="add" lay-submit>
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
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;

              //监听提交
              form.on('submit(add)', function(data){
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"add.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("增加成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
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