<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            模块管理
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
        <div class="x-body" style="height:1500px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="childName" class="layui-form-label">
                    		名称：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="childName" name="childName" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
 				</div>
 				
 				<div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="linkedPoint" class="layui-form-label">
                    		关联点(逗号分隔ID)：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="linkedPoint" name="linkedPoint" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
 				</div>
 				
 				  <div class="layui-form-item">
				    <label class="layui-form-label">语言</label>
				    <div class="layui-input-block" style="width:190px;">
				      <select name="lan" lay-verify="required">
				         <c:forEach items="${lanList}" var="lan" > 
				            <option value="${lan.id}">${lan.name}</option>
						</c:forEach> 
				      </select>
				    </div>
				  </div>
 				
 				
 				<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 显示图片</label>
				<div class="layui-input-inline">
				<table style="width:360px;">
					<tr>
					<td align="center" id='shopImgs'>
					
					</td>
					<td style="width:120px;">
						<a href="javascript:openMap('添加图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=subImage1&img=subImageUrl1','600','400')" class="layui-btn " >添加图片</a>
					    <input type="hidden" id="subImage1" class="subImage"/>
					    <image style="width:92px;height:92px;margin-top:5px" id="subImageUrl1"/>
					</td>
					<td style="width:120px;" >
						<a href="javascript:openMap('添加图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=subImage2&img=subImageUrl2','600','400')" class="layui-btn " >添加图片</a>
					    <input type="hidden" id="subImage2" class="subImage"/>
					    <image style="width:92px;height:92px;margin-top:5px" id="subImageUrl2"/>
					</td>
					<td style="width:120px;" >
						<a href="javascript:openMap('添加图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=subImage3&img=subImageUrl3','600','400')" class="layui-btn " >添加图片</a>
					    <input type="hidden" id="subImage3" class="subImage"/>
					    <image style="width:92px;height:92px;margin-top:5px" id="subImageUrl3"/>
					</td>
					</tr>
					<tr><td><div style="height:10px;"></div></td></tr>
				</table>
				</div>
				
				
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 选择声音</label>
				<div class="layui-input-inline" style="width: 100px">
				    <input id="cvoice"  hidden="hidden"><!-- 这个没啥用，暂时不删了 -->
					<input type="text" id="voice" name="voice" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('选择声音','<%=request.getContextPath() %>/admin/Voice/sellist?sel=voice&img=cvoice','600','400')" class="layui-btn " >声音素材</a>
				</div>
				</div>
 				 <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productOldPrice" class="layui-form-label">
						讲解范围：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="scope" name="scope" required lay-verify="required" 
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productPrice" class="layui-form-label">
						是否讲解：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="isTaught" name="isTaught" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
 				
 				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 经度： </label>
					<div class="layui-input-inline" style="width: 100px;">
						<input type="text"  name="childLongitude" id="shopLongitude"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid">-</div>
					<label for="L_title" class="layui-form-label"> 纬度 ：</label>
      				<div class="layui-input-inline" style="width: 100px;">
						<input type="text" name="childLatitude" id="shopLatitude"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-input-inline" style="width: 100px;">
				  		<!-- <a href="javascript:openMap('腾讯地图','<%=request.getContextPath() %>/layui/txmap.html','600','400')" class="layui-btn" >打开地图</a> -->
					</div>
				</div>
				
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 微信经度： </label>
					<div class="layui-input-inline" style="width: 100px;">
						<input type="text"  name="wxChildLongitude" id="wxChildLongitude"  autocomplete="off" class="layui-input" >
					</div>
					<div class="layui-form-mid">-</div>
					<label for="L_title" class="layui-form-label"> 微信纬度 ：</label>
      				<div class="layui-input-inline" style="width: 100px;">
						<input type="text" name="wxChildLatitude" id="wxChildLatitude"  autocomplete="off" class="layui-input" >
					</div>
					<div class="layui-input-inline" style="width: 100px;">
				  		<!-- <a href="javascript:openMap('腾讯地图','<%=request.getContextPath() %>/layui/txmap.html','600','400')" class="layui-btn" >更该坐标</a> -->
					</div>
				</div>
				
				
				<div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 下个浏览景点： </label>
					<div class="layui-input-inline" style="width: 100px;">
						<input type="text"  name="relevance" id="relevance"  autocomplete="off" class="layui-input" value="${child.relevance}">
					</div>
				</div>
				
				<div class="layui-form-item">
				    <label class="layui-form-label">景区规模</label>
				    <div class="layui-input-block" style="width:190px;">
				      <select name="scale" lay-verify="required">
				        <option selected="selected" value="小">小</option>
				       	<option value="中">中</option>
				       	<option value="大">大</option>
				      </select>
				    </div>
				  </div>
				
				 <div class="layui-form-item layui-form-text">
				    <label class="layui-form-label" style="width:110px;float:left;">描述：</label>
				    <div class="layui-input-block" style="width:400px;float:left;">
				      <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
				    </div>
				  </div>
				
               	
                <div class="layui-form-item">
                	<button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
						保存子产品
                    </button>
                </div>
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
       		var productID = '${productID}';	
            layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;

             //监听提交
              form.on('submit(add)', function(data){
                data.field.productID = productID;
              	var message = "保存子产品成功！";
              	
              	var imgids = new Array();
                //获取图片的id
                $(".subImage").each(function(index,item){
               		imgids[index]=$(this).val();
				});
				
				console.log(imgids);
              	data.field.imgids=imgids.join(',');
              	console.log(data.field);
      
              	add(data,message);
              	return false;
            	});
            	
            	
            });
            
           //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
           
      
           
           
           function add(data,message){
				
                $.ajax({
                	  type:"post",
           			  url:"add.do",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert(message, {icon: 6},function () {
                           // 获得frame索引
                           try{
                               var index = parent.layer.getFrameIndex(window.name);
                               console.log(index);
                               //关闭当前frame
                               parent.window.location.reload();
                               parent.layer.close(index);
                             }catch(exception)
                             {
                               window.location.reload();
                             }
                           });
                        }
                       }
                }) 
              
               
              }
              
               function delpic(obj){
              		console.log(obj);
              		$(obj).parent().remove();
              }
           
        </script>
        
    </body>

</html>