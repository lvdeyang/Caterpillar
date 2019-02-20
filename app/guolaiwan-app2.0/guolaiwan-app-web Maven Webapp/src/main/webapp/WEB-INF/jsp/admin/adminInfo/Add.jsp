<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             账号
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="adminName" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                             密码
                    </label>
                    <div class="layui-input-block">
                        <input type="password" id="L_title" name="adminpwd" required lay-verify="title"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            角色
                        </label>
                        <div class="layui-input-block">
                            <select lay-verify="required" name="roleid" id="roleid" lay-verify="required">
                            <option value="">请选择角色</option>
                            <c:forEach  items= "${list}" var="alist">
                            
                            <option value="${alist.id}">${alist.roleName}</option>
                             </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                           选择城市
                        </label>
                        <div class="layui-input-block">
                            <select lay-verify="required" name="cityCode" id="cityCode" lay-verify="required" lay-filter="selcity">
                             <option value="">请选择城市</option>
                            <c:forEach  items= "${cityList}" var="clist">
                           
                            <option value="${clist.cityCode}">${clist.cityName}</option>
                             </c:forEach>
                            </select>
                            <input type="hidden" name="cityName" id="cityName">
                        </div>
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                           选择公司
                        </label>
                        <div class="layui-input-block">
                            <select lay-verify="required" name="comId" id="comId" lay-verify="required" lay-filter="selcity">
                             <option value="">请选择公司</option>
                            <c:forEach  items= "${comList}" var="company">
                           
                            <option value="${company.id}">${company.comName}</option>
                             </c:forEach>
                            </select>
                          <!--   <input type="hidden" name="cityName" id="cityName"> -->
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
            layui.use(['form','layer','jquery'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
			
			
			 //获取城市名称
             form.on('select(selcity)', function(data){
               $("#cityName").val($('#cityCode option:selected').text());
             });

         
              //监听提交
              form.on('submit(add)', function(data){
              
          
          	  data.field.comName = $("#comId option:selected").text();
          	  data.field.roleName = $("#roleid option:selected").text();
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
                        } else {
                        	layer.alert("用户名已存在！");
                        }
                       }
                }) 

                return false;
              });
              
            });
            
               
          
        </script>
        
    </body>

</html>