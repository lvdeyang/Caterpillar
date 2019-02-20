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
        <link rel="stylesheet" href="../layui/css/x-admin.css" media="all">
    </head>
    
    <body>
        <div class="x-body">
            <form class="layui-form layui-form-pane">
            <input type="hidden" name="uuid" value="${list.uuid}">
                <div class="layui-form-item">
                    <label for="adminName" class="layui-form-label">
                                                             账号
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="adminName" name="adminName" required lay-verify="title" value="${list.adminName}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 <div class="layui-form-item">
                    <label for="adminpwd" class="layui-form-label">
                                                             密码
                    </label>
                    <div class="layui-input-block">
                        <input type="password" id="adminpwd" name="adminpwd" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            角色
                        </label>
                        <div class="layui-input-block">
                             <select lay-verify="required" name="roleid" id="roleid">
                            <c:forEach  items= "${rlist}" var="alist">
                            <option value="${alist.id}">${alist.roleName}</option>
                             </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            城市
                        </label>
                        <div class="layui-input-block">
                             <select lay-verify="required" name="cityCode" id="cityCode" lay-filter="selcity">
                            <c:forEach  items= "${clist}" var="clist">
                            <option value="${clist.cityCode}">${clist.cityName}</option>
                             </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                
                  <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                           选择公司
                        </label>
                        <div class="layui-input-block">
                            <select lay-verify="required" name="comId" id="comId" lay-verify="required" lay-filter="selcom">
                             <option value="">请选择公司</option>
                            <c:forEach  items= "${comList}" var="company">
                           
                            <option value="${company.id}">${company.comName}</option>
                             </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add" lay-submit>
                        保存
                    </button>
                </div>
            </form>
        </div>
        <script src="../layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="../layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
               //加载角色选中
              $("#roleid").val("${list.roleId}");
              $("#cityCode").val("${list.cityCode}");
              $("#comId").val("${list.comId}");
              layui.form.render('select');
              var form = layui.form
              ,layer = layui.layer;
               
              //监听提交
              form.on('submit(add)', function(data){
              var comName = $("#comId").find("option:checked").text();
              var cityName = $("#cityCode").find("option:checked").text();
              var roleName = $("#roleid").find("option:checked").text();
              
              data.field.roleName = roleName;
              data.field.comName = comName;
              data.field.cityName = cityName;
              
              console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"update.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("修改成功", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           
                           parent.layer.close(index);
                           parent.getpages("",1);
                          
                           });
                        }
                       }
                }) 
                //发异步，把数据提交给php
               
                return false;
              });
            });
             
        </script>
        
    </body>

</html>
