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
    			  <c:forEach items="${comSonList}" var="comSon">
    			  <c:choose>
    			  <c:when test="${comSon.check ==1}">
    			  <div class="layui-input-block">
      					<input type="checkbox" name="comSonIds"  title="${comSon.comName}"  value="${comSon.id}" checked >
      			  </div>
      			  </c:when>
      			  <c:otherwise>
      			  <div class="layui-input-block">
      			  		<input type="checkbox" name="comSonIds"  title="${comSon.comName}"  value="${comSon.id}">
      			   </div>
      			  </c:otherwise>
      			  </c:choose>
				  </c:forEach>
				  <div class="layui-form-item" style="text-align: center;position: absolute;margin-left: 150px;bottom: 0">
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
        
        var source = '${source}';
        var sourceId = '${sourceId}';
        layui.use(['form', 'layedit', 'laydate'], function(){
  			$ = layui.jquery;
  			var form = layui.form
  			,layer = layui.layer
  			,layedit = layui.layedit
  			,laydate = layui.laydate;
  				
  			
  				
  			form.on("submit(add)",function(data){
  				layer.load();
  				comSonIds = [];
  				$("input:checkbox[name='comSonIds']:checked").each(function(){
  					comSonIds.push($(this).val());
  				})
  				data.field.comSonIds = comSonIds.toString();
  				data.field.source = source;
  				data.field.sourceId =sourceId;
  				console.log(data.field);
  				$.ajax({
  					type:"post",
  					data:data.field,
  					url:"reComSon.do",
  					success:function(msg){
  						layer.closeAll("loading");
  						if(msg.msg=="success"){
  						   var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.layer.close(index);
                           parent.layer.msg("推荐成功！",{icon:1,time:1000});
                           parent.$("#reCount"+sourceId).text(msg.count);
                           
  						}else{
  						   parent.layer.msg("系统错误！",{icon:2,time:1000});
  						}
  					
  					}
  				
  				
  				})
  				return false;
  			
  			})
  			});
        </script>
             
    </body>
</html>