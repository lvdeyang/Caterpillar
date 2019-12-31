<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


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
         <form action="<%=request.getContextPath() %>/admin/activity/modify.do" method="post" class="layui-form layui-form-pane">
            	<input type="hidden" value="${proid}" name="proid" />
               <div class="layui-inline">
                    <label for="productStock" class="layui-form-label">
						库存：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productStock" name="productStock" required lay-verify="required" placeholder="数量"
                        autocomplete="off" class="layui-input" value="${rel.productStock}">
                 	</div>
                 </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">有效开始时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productBeginDate" name="productBeginDate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${start}">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">有效结束时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEnddate" name="productEnddate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input"  value="${end}">
                    	</div>
                    </div>
                </div>
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">预定开始时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="bStart" name="bookBegin" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${bStart}">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">预定结束时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="bEnd" name="bookEnd" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input"  value="${bEnd}">
                    	</div>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">有效开始时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productBeginTime" name="productBeginTime" placeholder="HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${startTime}">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">有效结束时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEndTime" name="productEndTime" placeholder="HH:mm:ss" required lay-verify="required"
                        	class="layui-input"  value="${endTime}">
                    	</div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            设置价格
                    </label>
                    <div class="layui-input-block">
                    <input type="text" id="price" name="price" required lay-verify="required"
                        	class="layui-input"  value="${rel.price}">
                   
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            单日库存
                    </label>
                    <div class="layui-input-block">
                    <input type="text" id="dayStock" name="dayStock" required lay-verify="required"
                        	class="layui-input"  value="${rel.dayStock}">
                   
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            单日限购
                    </label>
                    <div class="layui-input-block">
                    <input type="text" id="onePerDay" name="onePerDay" required lay-verify="required"
                        	class="layui-input"  value="${rel.onePerDay}">
                   
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            集赞购买
                    </label>
                    <div class="layui-input-block">
                     <input type="text" id="surpportBuy" name="surpportBuy" required lay-verify="required"
                        	class="layui-input"  value="${rel.surpportBuy}">
                   
                    </div>
                </div>
				
				
				<div class="layui-form-item">
                    <label for="L_title" class="layui-form-label">
                                                            集赞个数
                    </label>
                    <div class="layui-input-block">
                        <input type="text" id="L_title" value="${rel.surpportCount}" name="surpportCount" required lay-verify="required"
                        class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label" style="width:250px;">
                                                           （小时） 购物车失效时间
                    </label>
                    <div class="layui-input-block" style="margin-left:270px">
                        <input type="text" id="L_title" value="${rel.expireTime}" name="expireTime" required lay-verify="required"
                        class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_title" class="layui-form-label" style="width:250px;">
                                                           商品数量
                    </label>
                    <div class="layui-input-block" style="margin-left:270px">
                        <input type="text" id="L_title" value="${rel.count}" name="count" required lay-verify="required"
                        class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <input class="layui-btn" type="submit" value="修改" />
                </div>
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
        <script>
        
         //实例化编辑器
    		var um = UM.getEditor('productIntroduce');
    	
        	var webUrl = "${sysConfig.webUrl}";
             
             layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
              
              laydate.render({
              	elem: '#bStart'
              	,type: 'datetime'
              });
              laydate.render({
              	elem: '#bEnd'
              	,type: 'datetime'
              });
              
              laydate.render({
              	elem: '#productBeginDate'
              	,type: 'datetime'
              });
              laydate.render({
              	elem: '#productEnddate'
              	,type: 'datetime'
              });
              laydate.render({
              	elem: '#productEctivedate'
              	,type: 'datetime'
              });
              laydate.render({
              	elem: '#productBeginTime'
              	,type: 'time'
              });
              laydate.render({
              	elem: '#productEndTime'
              	,type: 'time'
              });
            });
        </script>
    </body>
</html>