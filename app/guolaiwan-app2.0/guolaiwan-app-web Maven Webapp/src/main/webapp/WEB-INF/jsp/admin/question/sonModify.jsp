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
            阳光成单系统
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
        <div style="margin: 0 auto;width:500px" >
            <form class="layui-form layui-form-pane">
            <input type="hidden" name="uuid" value="${list.uuid}">
               <%--  <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             分类标识
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="classCode" required lay-verify="title" value="${list.classCode}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div> --%>
               
 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             分类名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="className" required lay-verify="title"value="${list.className}"
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
                            <c:forEach  items= "${modularss}" var="modular">
                            <option value="${modular.modularCode}">${modular.modularName}</option>
                             </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                 <%-- <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            是否显示
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" id="L_title" name="classIsv" required lay-verify="title" value="${list.classIsv}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>  --%>
                 <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 
				              图片 
				              </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="${weburl}${list.modularPic}" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="modularPic" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn " >图片素材</a>
				</div>
			</div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            排序
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="classSort" required lay-verify="title" value="${list.classSort}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="text-align: center;position: absolute;margin-left: 200px;bottom: 0">
                     
                    <button class="layui-btn" lay-filter="add" lay-submit >
                        保存
                    </button>
                </div>
            </form>
            </div>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8">
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
           			  url:"update2.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
                           });
                        }
                       }
                }) 
                //发异步，把数据提交给php
               
                return false;
              });
            });
             function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
        </script>
        
    </body>

</html>
