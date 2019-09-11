<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            添加新问题
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
    <style>
	.layui-form-label{
    	width: 150px !important;
    }
    .layui-input{
    	width: 700px !important;
    }
    </style>
    </head>
    <body>
        <div class="x-body">
        <div  >
            <form class="layui-form layui-form-pane">
 				<div class="layui-form-item">
                    <label for="questiontitle" class="layui-form-label">
                                                            题目
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="questiontitle" name="questiontitle" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
				    <label class="layui-form-label">问题类型 </label>
				    <div class="layui-input-block">
				      <input type="radio" name="questiontype" value="1" title="单选" checked="">
				      <input type="radio" name="questiontype" value="2" title="多选">
				      <input type="radio" name="questiontype" value="3" title="判断" >
				    </div>
				</div>
                <div class="layui-form-item">
                    <label for="options1" class="layui-form-label">
                                                           A(对)
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="options1" name="options1" required 
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="options2" class="layui-form-label">
                                                           B(错)
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="options2" name="options2" required 
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="options3" class="layui-form-label">
                                                           C
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="options3" name="options3" required 
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="options4" class="layui-form-label">
                                                          D
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="options4" name="options4" required 
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="options5" class="layui-form-label">
                                                           E
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="options5" name="options5" required 
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="answer" class="layui-form-label">
                                                           答案(ABCDE/对错)
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="answer" name="answer" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <input type="text" id="questionnaireId" name="questionnaireId" hidden="hidden" value="${questionnaireId}">
				<div id="view"></div> 
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
        	layui.use([ 'form', 'layer','laytpl' ], function() {
        		$ = layui.jquery;
        		var form = layui.form,
        			layer = layui.layer,
        			laytpl = layui.laytpl;
        
        	form.on('submit(add)', function(data){
              	var message = "增加成功"
              	add(data,message);
              	return false;
             });
        		
        	function add(data,message){
        	
              	var questiontitle =$("#questiontitle").val();
				if(questiontitle==null||questiontitle==""){
						layer.msg("题目不能为空",{time: 5000, icon:5});
						return false;
				}
				
				var answer =$("#answer").val();
				if(answer==null||answer==""){
						layer.msg("答案不能为空",{time: 5000, icon:5});
						return false;
				} 

				var options3 =$("#options3").val();
				if(questiontitle==null||questiontitle==""){
					data.field.options3="";
				}
				
				var options4 =$("#options4").val();
				if(options4==null||options4==""){
					data.field.options4="";
				}
				
				var options5 =$("#options5").val();
				if(options5==null||options5==""){
					data.field.options5="";
				}
								
				console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"insertquestion",
                      data:data.field,
                      success:function(msg){
                        if (msg == "success") {
        						layer.msg("增加成功", {
        							icon : 6,
        							time : 500
        						}, function() {
        							// 获得frame索引
        							var index = parent.layer.getFrameIndex(window.name);
        							//关闭当前frame
        							parent.layer.close(index);
        							parent.getquestionbankList();
        						});
        					}else{
        						layer.msg("系统错误！",{icon:2,time:3000});
        					}
                       }
                }) 
              }	
        		
        		
        		
        		
        		
        });
        </script>
        
    </body>

</html>