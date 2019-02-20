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
                    <label for="L_title" class="layui-form-label">
                                                            活动名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" name="name" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            活动类型
                    </label>
                    <div class="layui-input-block">
                    <select id="type" name="type" lay-filter="type" required lay-verify="required">
                    	<option value="">请选择折扣类型</option>
                    	<option value="MANJIAN">满减</option>
						<option value="DAZHE">折扣</option>
						<option value="JIFEN">积分兑换</option>
						<option value="FIXEDPRICE">固定价格</option>
                    </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            活动规则
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_rule" name="rule" required lay-verify="required"
                        autocomplete="off" class="layui-input">
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
        							parent.getactivityList();
        						});
        					}else{
        						layer.msg("系统错误！",{icon:2,time:3000});
        					}
        				}
        			})
        
        			return false;
        		});
        		
        	form.on("select(type)",function(){
        		var type = $("#type").find("option:selected").val();
        		
        		var data = {
        			"type":type
        		}
        		var getTpl = activityTpl.innerHTML
        		,view = document.getElementById("view");
        		laytpl(getTpl).render(data, function(html){
  					view.innerHTML = html;
				});
        		
        	})	
        });
        </script>
        <script type="text/html" id="activityTpl">
			{{# if (d.type=="MANJIAN"){ }}
				<div class="layui-form-item" id="mj">
                   	<div class="layui-inline">
                   		<label for="L_title" class="layui-form-label" style="width:60px">满</label>
                    	<div class="layui-input-inline" style="width:100px">
                      		<input type="text" name="ceil" class="layui-input" placeholder="￥" required lay-verify="required">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label for="L_title" class="layui-form-label" style="width:60px"> 减</label>
                    	<div class="layui-input-inline" style="width:100px">
                        	<input type="text" name="cut" class="layui-input" placeholder="￥" required lay-verify="required">
                    	</div>
                   	</div>
                </div>
			{{# }else if (d.type=="DAZHE"){ }}
				 <div class="layui-form-item" id="zk">
                    <label for="L_title" class="layui-form-label">
                                                          折扣
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="discount" name="discount" placeholder="几折" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
			{{# }else if (d.type=="JIFEN"){ }}
				<div class="layui-form-item" id="jf">
                    <label for="L_title" class="layui-form-label">
                                                            比率
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="ratio" name="ratio" placeholder="百分之几" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
			{{# }else if (d.type=="FIXEDPRICE"){ }}
				<div class="layui-form-item" id="fPrice">
                    <label for="L_title" class="layui-form-label">
                                                            价格
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="fixedPrice" name="fixedPrice" placeholder="￥" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
			{{# }else{ }}

			{{# } }}


			
		</script>
        
    </body>

</html>