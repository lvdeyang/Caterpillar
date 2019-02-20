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
           文章管理
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
            <form class="layui-form layui-form-pane">
                
                <div class="layui-form-item">
                  <div class="layui-inline">
                    <label  class="layui-form-label">
                                                             绑定向导
                    </label>
                    <div class="layui-input-inline">
                        
                        <select lay-verify="required" name="classUUid" id="classUUid" lay-filter="selclass">
                    <option value=""></option>
                     <c:forEach  items="${list}" var="alist">
                        <option value="${alist.uuid} ">${alist.className}</option>
                        </c:forEach>
                        </select>
                           <input type="hidden" name="className" id="className">
                      </div>
                    </div>
                </div>
 
 
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            文章名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="articleName" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
            
                 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            排序
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="articleSort" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
               <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            是否启用
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="articleEnable" value="1">
                    </div>
                </div>
 <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                           文章日期
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="articleDate" name="articleDate" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                 <div class="layui-form-item">
                    <label for="articleIntroduce" class="layui-form-label">
 						文章内容：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="articleCon"  placeholder="请输入内容" class="layui-input" id="articleIntroduce" style="height:300px"></textarea>
                    </div>
                    
                </div>
                <div class="layui-form-item">
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
           
           
         //实例化编辑器
    		var um = UM.getEditor('articleIntroduce');
            layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
               ,laydate = layui.laydate;
                 
              laydate.render({
              	elem: '#articleDate'
              	,type: 'datetime'
              });


             //获取向导名称
             form.on('select(selclass)', function(data){
               $("#className").val($('#classUUid option:selected').text());
             });
              
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