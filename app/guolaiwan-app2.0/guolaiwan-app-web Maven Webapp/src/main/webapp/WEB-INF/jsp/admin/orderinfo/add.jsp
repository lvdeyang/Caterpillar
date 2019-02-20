<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
        <div class="x-body">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="orderNO" class="layui-form-label">
                    		orderNO：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="orderNO" name="orderNO" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 					<div class="layui-inline">
 						<label for="bkName" class="layui-form-label">
							bkName：
                   		</label>
                    	<div class="layui-input-inline">
                       		<input type="text" id="bkName" name="bkName" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 				</div>
                
				
				
				
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">shopName</label>
						<div class="layui-input-inline">
							<input type="text" id="shopName" name="shopName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="shopId" name="shopId" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a href="javascript:openMap('选择商家','<%=request.getContextPath() %>/admin/merchant/sellist?mcname=MerchantName&mcuuid=MerchantID&mchref=merclass','600','400')" class="layui-btn " >选择商家</a>
						</div>
					</div>
				</div>
				
				
				
				
                <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">productName</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="productName" 
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="L_title" name="productId" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a id="merclass" href="javascript:openMap('选择产品','<%=request.getContextPath() %>/admin/merModular/comSel','600','400')" class="layui-btn " >选择产品</a>
						</div>
					</div>
				</div>

                
				<div class="layui-form-item">
 					<div class="layui-inline">
						<label class="layui-form-label">productNum</label>
						<div class="layui-input-inline">
							<input type="text" id="productNum" name="productNum" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">productPrice</label>
						<div class="layui-input-inline">
							<input type="text" id="productPrice" name="productPrice" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
						</div>
					</div>               
				</div>
                
               <!-- <div class="layui-form-item">
					<div class = "layui-inline">
    					<label class="layui-form-label">出售方式：</label>
    					<div class="layui-input-block">
      						<input type="radio" name="productCommissionCode" value="1" title="佣金" id="Commission"  lay-filter="filter" checked="">
      						<input type="radio" name="productCommissionCode" value="0" title="比例" id="Proportion"  lay-filter="filter">
    					</div>
    				</div> -->
                
                <div class="layui-form-item">
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
                    		payMode：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="payMode" name="payMode"  
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
                 
                 <div class="layui-inline">
                    <label for="productStock" class="layui-form-label">
						payMoney：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="payMoney" name="payMoney" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">createDate：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productBeginDate" name="productBeginDate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">orderBookDate：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEnddate" name="productEnddate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                </div>
                		
			<div class="layui-form-item">

  			</div>
                
                <div class="layui-form-item">
				<div class = "layui-inline">
    				<label class="layui-form-label">orderType：</label>
    				<div class="layui-input-block">
      					<input type="text" id="orderType" name="orderType" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
    				</div>
    			</div>                
                	<div class="layui-inline">
                    	<label class="layui-form-label">
 							orderState：
                    	</label>
                    	<div class="layui-input-inline" onclick="changeNum()">
                        	<input type="text" id="orderState" name="orderState" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                        	</div>
                    	</div>
                    </div>
				
                    <div class="layui-inline">
                    	<label class="layui-form-label">source：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="source" name="source" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                    </div>
                 <div class="layui-inline">
                    <label for="productPrice" class="layui-form-label">
						userTel：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="userTel" name="userTel" required lay-verify="required" "
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>                    
                </div>
                
                <div class="layui-form-item">
                    <button class="layui-btn" lay-filter="add" lay-submit >
						确定出票
                    </button>
                </div>
                </div>
                
            </form>
        </div>
        <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8">
        </script>
                <script>
        
         //实例化编辑器
    
        
            layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
              
             
              //监听提交
              form.on('submit(add)', function(data){
              	data.field.productAuditstates = '待审核';
              	var message = "增加成功"
              	add(data,message);
              	return false;
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
            });
        
        </script>
  </body>
</html>
