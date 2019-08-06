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
    <style>
    
    </style>
    <body>
        <div class="x-body">
        <div  >
            <form class="layui-form layui-form-pane">
 				<div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                                                            名称
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="name" name="name" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                                                            桌号
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="tableNo" name="tableNo" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                                    层                                     
                    </label> 
                    <div class="layui-input-block">
                    <select id="title" style="" name="title" size="1" class="sel" required lay-verify="required"
                        autocomplete="off" class="layui-input">
						<option value="一层">一层</option>
						<option value="二层">二层</option>
						<option value="三层">三层</option>
						<option value="四层">四层</option>
						<option value="五层">五层</option>
						<option value="六层">六层</option>
						<option value="七层">七层</option>
						<option value="八层">八层</option>
				   </select>
				   </div>
				</div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                         人数                                                                                  
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="title" name="type" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                         订金/元                                                                                
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="bookprice" name="bookprice" required lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
 				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                      是否是包间                                                                                
                    </label>
                    <div class="layui-input-block" id="radio">
                           <input type="radio" name="radio" title="否" value="0">
                           <input type="radio" name="radio" title="是" value="1">
                    </div>
                </div>
				<div class="layui-input-inline" style="width:auto;">
				  <label for="L_title" class="layui-form-label">
                                                     详情                                                                               
                    </label>
					<p style="margin-bottom: 15px;">
						<img alt="" src=""
							id="parkingshopPic" style="width:100px;"> <input
							type="hidden" id="parkingshopImg" name="parkingshopImg"
							lay-verify="title" autocomplete="off" class="layui-input">
						<a href="javascript:openMap('上传图片','<%=request.getContextPath()%>/admin/picture/sellist?sel=parkingshopImg&img=parkingshopPic','600','400')"class="layui-btn layui-btn-xs"style="height:40px;line-height:40px;width:90px;">更换图片</a>
					</p>
					<p>
					 <label for="L_title" class="layui-form-label">
                                                    特色                                                                        
                    </label> 
                    <span>厕所</span><input class="sap"  type="checkbox" name='key' value="厕所"> 
                    <span>wifi</span><input class="sap" type="checkbox" name='key' value="wifi"> 
                    <span>唱歌</span><input class="sap" type="checkbox" name='key' value="唱歌"> 
                    <span>电视</span>
                    <input class="sap" type="checkbox" name='key' value="电视"> 
                    <span>沙发</span>
					<input class="sap" type="checkbox" name='key' value="沙发"> 
					<span>空调</span>
					<input	class="sap" type="checkbox" name='key' value="空调">
					</p>
				</div>
				<input  name="merchantId" value="${merchantId}" style="display: none;">
				<div id="view"></div> 
				<div class="layui-form-item" style="text-align: center;position: absolute;margin-left: 200px;bottom: 0">
                    <button id="btns" class="layui-btn" lay-filter="add" lay-submit>
                        	保存
                    </button>
                </div>
            </form>
            </div>
            
              
 <jsp:include page="../../../mobile/commons/jsp/scriptpubnum.jsp"></jsp:include>
<script type="text/javascript" src="lib/city-picker.js" charset="utf-8"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
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
                 var title = '';
        		//监听提交
        		form.on('submit(add)', function(data) {
        			console.log(data.field );
        			$("[name='key']:checked").each(function() {
                      title += $(this).val()+',';
                    });
                    if(title == ""){
                      data.field.key = "";
                    }else{
                      data.field.key = title;
                    }
        			if(data.field.radio == null){
        			alert("请选择是否是包间");
        			return false;
        			}
        			if(data.field.title == null){
        			alert("请选择层数");
        			return false;
        			}
	        		layer.load();
        			$.ajax({
        				type : "post",
        				url : "add.do",
        				data : data.field,
        				success : function(msg) {
        				//关闭当前frame
                           /*  parent.window.location.reload(); */
                            var index = parent.layer.getFrameIndex(window.name);  
                            parent.layer.close(index);//关闭当前页
                            parent.list(); 
        				}
        			})
        
        			return false;
        		});
        		
        
        });
        
        
         /*  alert(title); */
        //打开分类
	function openMap(title, url, w, h) {
		x_admin_show(title, url, w, h);
	}
        </script>
       
        
    </body>

</html>