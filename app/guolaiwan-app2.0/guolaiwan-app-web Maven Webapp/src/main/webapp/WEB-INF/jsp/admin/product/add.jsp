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
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label for="productName" class="layui-form-label">
                    		商品名称：
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productName" name="productName" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 					<div class="layui-inline">
 						<label for="productSubtitle" class="layui-form-label">
							副标题：
                   		</label>
                    	<div class="layui-input-inline">
                       		<input type="text" id="productSubtitle" name="productSubtitle" required lay-verify="required"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 				</div>
                
				
				
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商家名称</label>
						<div class="layui-input-inline">
							<input type="text" id="MerchantName" name="productMerchantName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="MerchantID" name="productMerchantID" 
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
						<label class="layui-form-label">板块名称</label>
						<div class="layui-input-inline">
							<input lay-filter="modularName"  type="text" id="L_title" name="modularName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="L_title" name="modularCode" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">分类名称</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="modularClass" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="L_title" name="modularClassId" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a href="javascript:openMap('选择板块','<%=request.getContextPath() %>/admin/modular/comSel','600','400')" class="layui-btn " >选择板块</a>
						</div>
					</div>
				</div>
                
                
                <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商家分类</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="merMName" placeholder="选填"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="L_title" name="merMId" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a id="merclass" href="javascript:openMap('选择分类','<%=request.getContextPath() %>/admin/merModular/comSel','600','400')" class="layui-btn " >选择分类</a>
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
			    <label class="layui-form-label">地区选择</label>
			    <div class="layui-input-inline">
			      <select name="regionFirst" id="regionFirst">
			        
			      </select>
			    </div>
			    <div style="display:none;" class="layui-input-inline">
			      <select name="regionSecond" id="regionSecond">
			        
			      </select>
			    </div>
			    <div style="display:none;" class="layui-input-inline">
			      <select name="regionThird" id="regionThird">
			       
			      </select>
			    </div>
			    
			</div>
            
            
            
 				                
               <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productOldPrice" class="layui-form-label">
						原价：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productOldPrice" name="productOldPrice" required lay-verify="required" placeholder="（单位：元）"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productPrice" class="layui-form-label">
						现价：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productPrice" name="productPrice" required lay-verify="required" placeholder="（单位：元）"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productStock" class="layui-form-label">
						库存(房间个数)：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productStock" name="productStock" required lay-verify="required" placeholder="数量"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                <!--  5/1 张羽 新增 最低购买数量限制 -->
	                 <div class = "layui-inline">
	    				<label class="layui-form-label" id ="restrictNumber">最低购买量：</label>
	    				<div class="layui-input-block">
	      					<input type="text" name="productRestrictNumber" id="productRestrictNumber" class="layui-input" placeholder="最低购买数量限制"
	      					required lay-verify="required">
	    				</div>
	    			 </div>
                </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">开始时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productBeginDate" name="productBeginDate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">结束时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEnddate" name="productEnddate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">有效期：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEctivedate" name="productEctivedate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input">
                    	</div>
                    </div>
                </div>
                
                <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商品主图 </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="shopPic" 
						lay-verify="title" autocomplete="off"   class="layui-input">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
					<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#shopImg','#shopPic')">删除图片</button>					
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 商品详情图 </label>
				<div class="layui-input-inline">
				<table>
					<tr>
					<td align="center">
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl1">
					<input type="hidden" id="img1" name="img1" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img1&img=imgurl1','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
					<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl1','#img1')">删除图片</button>						
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl2">
					<input type="hidden" id="img2" name="img2" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img2&img=imgurl2','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
					<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl2','#img2')">删除图片</button>	
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl3">
					<input type="hidden" id="img3" name="img3"" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img3&img=imgurl3','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
					<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl3','#img3')">删除图片</button>	
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl4">
					<input type="hidden" id="img4" name="img4" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img4&img=imgurl4','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
					<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl4','#img4')">删除图片</button>	
					</td>
					<td>
					<img alt="" src="" style=" height:100px;width:100px " id="imgurl5">
					<input type="hidden" id="img5" name="img5" >
					<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img5&img=imgurl5','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
					<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl5','#img5')">删除图片</button>	
					</td>
					</tr>
				</table>
				
					<input type="hidden" id="shopMpic" name="shopMpic" required
						lay-verify="title" autocomplete="off" class="layui-input" >
		        </div>
			</div>
			
			<div class="layui-form-item">
				<div class = "layui-inline">
    				<label class="layui-form-label">提成：</label>
    				<div class="layui-input-block">
      					<input type="radio" name="productCommissionCode" value="1" title="佣金" id="Commission"  lay-filter="filter" >
      					<input type="radio" name="productCommissionCode" value="0" title="比例" id="Proportion"  lay-filter="filter">
    				</div>
    			</div>
    			
    			<div class = "layui-inline">
    				<label class="layui-form-label" id ="commissionPrice">百分之：</label>
    				<div class="layui-input-block">
      					<input type="text" name="productCommissionPrice" id="productCommissionPrice" class="layui-input" placeholder="整数"
      					required lay-verify="required">
    				</div>
    			</div>
  			</div>
  			
  			
  			<div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">
 							是否拼团：
                    	</label>
                    	<div class="layui-input-inline" onclick="changeGroup()">
                        	<input type="checkbox" name="group" lay-skin="switch" id="group" lay-text="ON|OFF" lay-filter="switchTest" value="1" >
                        	<div class="layui-unselect	layui-form-switch" lay-skin="_switch" >
                        	<em>OFF</em>
                        	<i></i>
                        	</div>
                    	</div>
                    </div>
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
 							<font id="LimitNum1" style="color:#757575">拼团人数：</font>
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="groupnum" name="groupnum"  placeholder="人数"
                        	autocomplete="off" class="layui-input" disabled="disabled">
                    	</div>
                    </div>
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
 							<font id="LimitNum2" style="color:#757575">拼团价格：</font>
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="groupprice" name="groupprice"  placeholder="价格"
                        	autocomplete="off" class="layui-input" disabled="disabled">
                    	</div>
                    </div>
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
 							<font id="LimitNum3" style="color:#757575">拼团时限：</font>
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="grouptime" name="grouptime"  placeholder="时间限制（小时）"
                        	autocomplete="off" class="layui-input" disabled="disabled">
                    	</div>
                    </div>
                </div>
  			
  			
  			
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						显示：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="productIsShow" checked="checked" lay-skin="switch" id="productIsShow" lay-text="ON|OFF" lay-filter="switchTest" value="0">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch">
                        	<em>OFF</em>
                        	<i></i>
                        </div>
                    </div>
                </div>
                
                 <!-- 4/28 张羽新增退款限制 -->
                 <div class="layui-form-item">
                    <label class="layui-form-label">
 						退款限制：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="productIsRefund" lay-skin="switch" id="productIsRefund" lay-text="ON|OFF" lay-filter="switchTest" value="1">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch">
                        	<em>OFF</em>
                        	<i></i>
                        </div>
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						是否人脸：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="ifFace" lay-skin="switch" id="ifFace" lay-text="ON|OFF" lay-filter="switchTest" value="1">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch">
                        	<em>OFF</em>
                        	<i></i>
                        </div>
                    </div>
                </div>
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">
 							限购：
                    	</label>
                    	<div class="layui-input-inline" onclick="changeNum()">
                        	<input type="checkbox" name="productLimitType" lay-skin="switch" id="productLimitType" lay-text="ON|OFF" lay-filter="switchTest" value="1" >
                        	<div class="layui-unselect	layui-form-switch" lay-skin="_switch" >
                        	<em>OFF</em>
                        	<i></i>
                        	</div>
                    	</div>
                    </div>
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
 							<font id="LimitNum" style="color:#757575">限购数量：</font>
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productLimitNum" name="productLimitNum"  placeholder="个数"
                        	autocomplete="off" class="layui-input" disabled="disabled">
                    	</div>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						推荐：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="like[productIndexRecommend]" checked="checked" value="1" id="productIndexRecommend" title="首页推荐">
                    	<input type="checkbox" name="like[productListRecommend]" checked="checked" value="1" id="productListRecommend" title="列表推荐">
                    	<input type="checkbox" lay-skin="primary" name="psType" value="GOLD" id="psType" title="金币商城" lay-filter="psType">
                    	<div class="layui-inline"  id="goldNumDiv" >
                    	<input type="text"  class="layui-input" name="goldNum" id="goldNum" disabled="" placeholder="金币数">
                    	</div>
                    </div>
                </div>
                
                <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productSort" class="layui-form-label">
						排序：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productSort" name="productSort"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productShowNum" class="layui-form-label">
						浏览量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productShowNum" name="productShowNum" 
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productSaleNum" class="layui-form-label">
						销量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productSaleNum" name="productSaleNum"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 <div class="layui-inline">
                    <label for="productntegral" class="layui-form-label">
						积分：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productntegral" name="productntegral"
                        autocomplete="off" class="layui-input">
                 	</div>
                 	
                 </div>
                 <div class="layui-inline">
                    <label for="Integra" class="layui-form-label">
						积分商品：
                    </label>
                    <div class="layui-input-inline">
                   		 <input type="text" id="Integra" value="0" name="Integra" 
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 <div class="layui-inline">
                    <label for="sent" class="layui-form-label">
						领取地址：
                    </label>
                    <div class="layui-input-inline">
                   		 <input type="text" id="site" value="遵化市法院对面万乘晟象12楼" name="site" 
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="sent" class="layui-form-label">
						快递费：
                    </label>
                    <div class="layui-input-inline">
                   		 <input type="text" id="sent" name="sent"
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                </div>
                <div class="layui-form-item proLine">
                <div class="layui-inline">
                    <label for="traffic" class="layui-form-label">
						交通：
                    </label>
                    <div class="layui-input-inline">
                         <select id="traffic" name="traffic" lay-verify="required" lay-search="">
				          <option value="汽车">汽车</option>
				          <option value="火车">火车</option>
				          <option value="飞机">飞机</option>
				        </select>
                 	</div>
                 </div>
                 </div>
                
                <div class="layui-form-item proLine">
                <div class="layui-inline">
                    <label for="productDayCount" class="layui-form-label">
						团期：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productDayCount" name="productDayCount"
                        autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 </div>
                 <div class="layui-form-item proLine">
                 <div class="layui-inline">
                    <label for="datesOn" class="layui-form-label">
						有效日：
                    </label>
                    <div class="layui-input-inline" style="width:600px;">
                         <input type="hidden" id="datesOn" name="datesOn"
                        autocomplete="off" class="layui-input">
                          <input lay-filter="selOn" type="checkbox" name="1" title="01">
					      <input lay-filter="selOn" type="checkbox" name="2" title="02">
					      <input lay-filter="selOn" type="checkbox" name="3" title="03">
					      <input lay-filter="selOn" type="checkbox" name="4" title="04">
					      <input lay-filter="selOn" type="checkbox" name="5" title="05">
					      <input lay-filter="selOn" type="checkbox" name="6" title="06">
					      <input lay-filter="selOn" type="checkbox" name="7" title="07">
					      <input lay-filter="selOn" type="checkbox" name="8" title="08">
					      <input lay-filter="selOn" type="checkbox" name="9" title="09">
					      <input lay-filter="selOn" type="checkbox" name="10" title="10">
					      <input lay-filter="selOn" type="checkbox" name="11" title="11">
					      <input lay-filter="selOn" type="checkbox" name="12" title="12">
					      <input lay-filter="selOn" type="checkbox" name="13" title="13">
					      <input lay-filter="selOn" type="checkbox" name="14" title="14">
					      <input lay-filter="selOn" type="checkbox" name="15" title="15">
					      <input lay-filter="selOn" type="checkbox" name="16" title="16">
					      <input lay-filter="selOn" type="checkbox" name="17" title="17">
					      <input lay-filter="selOn" type="checkbox" name="18" title="18">
					      <input lay-filter="selOn" type="checkbox" name="19" title="19">
					      <input lay-filter="selOn" type="checkbox" name="20" title="20">
					      <input lay-filter="selOn" type="checkbox" name="21" title="21">
					      <input lay-filter="selOn" type="checkbox" name="22" title="22">
					      <input lay-filter="selOn" type="checkbox" name="23" title="23">
					      <input lay-filter="selOn" type="checkbox" name="24" title="24">
					      <input lay-filter="selOn" type="checkbox" name="25" title="25">
					      <input lay-filter="selOn" type="checkbox" name="26" title="26">
					      <input lay-filter="selOn" type="checkbox" name="27" title="27">
					      <input lay-filter="selOn" type="checkbox" name="28" title="28">
					      <input lay-filter="selOn" type="checkbox" name="29" title="29">
					      <input lay-filter="selOn" type="checkbox" name="30" title="30">
					      <input lay-filter="selOn" type="checkbox" name="31" title="31">
                 	</div>
                 </div>
                </div>
 				<div class="layui-form-item">
                    <label for="productIntroduce" class="layui-form-label">
 						产品介绍：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="productIntroduce"  placeholder="请输入内容" class="layui-input" id="productIntroduce" style="height:300px"></textarea>
                    </div>
                </div>
                
                
                <div class="layui-form-item proLine">
                    <label for="costMessage" class="layui-form-label">
 						费用包含：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="costMessage"  placeholder="请输入内容" class="layui-input" id="costMessage" style="height:300px"></textarea>
                    </div>
                </div>
                
                
                 <div class="layui-form-item proLine">
                    <label for="remarks" class="layui-form-label">
 						备注：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="remarks"  placeholder="请输入内容" class="layui-input" id="remarks" style="height:300px"></textarea>
                    </div>
                </div>
                
                <div class="layui-form-item proLine">
                    <label for="notes" class="layui-form-label">
 						预定须知：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="notes"  placeholder="请输入内容" class="layui-input" id="notes" style="height:300px"></textarea>
                    </div>
                </div>
                
                
                
            	<input type="hidden" name="productAuditstates" value="" class="layui-input">
                <div class="layui-form-item">
                	<button class="layui-btn layui-btn-danger" lay-filter="add1" lay-submit>
						存为草稿
                    </button>
                    <button class="layui-btn" lay-filter="add2" lay-submit >
						提交待审核
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
    		var um = UM.getEditor('productIntroduce');
    		UM.getEditor('costMessage');
    		UM.getEditor('notes');
    		UM.getEditor('remarks');
    		
            
        
            layui.use(['form','layer','laydate','jquery'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
   
           
           function renderForm(){
			  layui.use('form', function(){
			   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			   form.render();
			  });
			 }
           
           function getRegions(level){
                var parid=0;
                if(level==2){
                  parid=$('#regionFirst').val();
                  $('#regionSecond').children().remove();
                  $('#regionThird').children().remove();
                }else if(level==3){
                  parid=$('#regionSecond').val();
                  $('#regionThird').children().remove();
                }else if(level==1){
                    $('#regionFirst').children().remove();
                    $('#regionSecond').children().remove();
                    $('#regionThird').children().remove();
                }
                $.ajax({
              	  type:"get",
         			  url:"regions?parentId="+parid,
                    data:{},
                    success:function(data){
                       var html=[];
                       html.push('<option value="0">请选择</option>')
                       for(var i=0;i<data.data.length;i++){
                          html.push('<option value="'+data.data[i].id+'">'+data.data[i].name+'</option>');
                       }
                       if(level==1){
                          $('#regionFirst').append(html.join(''));
                       }else if(level==2){
                          $('#regionSecond').append(html.join(''));
                       }else{
                          $('#regionThird').append(html.join(''));
                       }
                       renderForm();
                    }
                    
                }); 
           }
               
           getRegions(1);
           
           $('#regionFirst').change(function(){
           
              getRegions(2);
           });
           $('#regionSecond').change(function(){
           
              getRegions(3);
           });
           
              
           form.on('checkbox(selOn)', function (data) {
                var strs=[];
                if($('#datesOn').val()!=''){
                   strs=$('#datesOn').val().split(',');
                }
                
                if(data.elem.checked){
                   strs.push(this.name);
                }else{
                
                    var index = strs.indexOf(this.name);
					if (index > -1) {
					    strs.splice(index, 1);
					}
                }
                $('#datesOn').val(strs.join(','));

           });


              
              
            form.on('radio(filter)', function(data){
  				if(data.value==1){
  					$("#commissionPrice").html("金额：");
  					$("#productCommissionPrice").attr("placeholder","（单位：元）");
  				}else{
  					$("#commissionPrice").html("百分之：");
  					$("#productCommissionPrice").attr("placeholder","整数");
  				}
  				
			}); 
			
             
             //监听提交
              form.on('submit(add1)', function(data){
              	data.field.productAuditstates = '草稿';
              	var message = "保存草稿成功！"
              	console.log(data.field);
              	add(data,message);
              	return false;
            	});
              //监听提交
              form.on('submit(add2)', function(data){
              	data.field.productAuditstates = '待审核';
              	var message = "增加成功"
              	add(data,message);
              	return false;
             });
             
             form.on("checkbox(psType)",function(data){
             	layer.tips('勾选后！请填写金币数<br/>商品将会在金币商城中显示！', data.othis, {
  						tips: [1, '#3595CC']
				})
             	//选中
             	if($("#psType").prop("checked")){
             		$("#goldNum").removeAttr("disabled");
             	}else{
             		$("#goldNum").attr("disabled","disabled");
             	}
             
             })
              
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
            
           //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
           
           function changeNum(){
           		if($("#productLimitType").prop("checked")){
           		console.log("进来了");
           			$("#productLimitNum").removeAttr("disabled");
           			$("#LimitNum").removeAttr("style");
           		}else{
           			$("#productLimitNum").prop("disabled", "disabled");
           			$("#LimitNum").prop("style", "color:#757575");
					$("#productLimitNum").val("");
					
           		}
           }
           
           function changeGroup(){
           		if($("#group").prop("checked")){
           		console.log("进来了");
           			$("#groupnum").removeAttr("disabled");
           			$("#groupprice").removeAttr("disabled");
           			$("#grouptime").removeAttr("disabled");
           			$("#LimitNum1").removeAttr("style");
           			$("#LimitNum2").removeAttr("style");
           			$("#LimitNum3").removeAttr("style");
           		}else{
           			$("#groupnum").prop("disabled", "disabled");
           			$("#groupprice").prop("disabled", "disabled");
           			$("#grouptime").prop("disabled", "disabled");
           			$("#LimitNum1").prop("style", "color:#757575");
           			$("#LimitNum2").prop("style", "color:#757575");
           			$("#LimitNum3").prop("style", "color:#757575");
					$("#groupnum").val("");
					$("#groupprice").val("");
           		}
           }
 
            //删除图片 
           function delpic(img,inp){
           		$(img).removeAttr("src");
           		$(inp).val("");           	
           }          
           
           function add(data,message){
 				//多图字段          
           		var proMpic="";
           		var strPic;
           		for(var i=1;i<6;i++){
           			var str = "#img"+i;
           			if($(str).val()!=''){
           				if(proMpic==''){
           					strPic = $(str).val();
           				}else{
           					strPic = ","+$(str).val();
           				}
           				proMpic = proMpic+strPic;
           			}
           		}
           		data.field.shopMpic= proMpic;
           		
              	//校验
				
              	//原价
              	var OldPrice =$("#productOldPrice").val();
				if(!( /^\d+(\.\d+)?$/).test(OldPrice)){
						layer.msg("原价为数字！",{time: 5000, icon:5});
						return false;
				}   
				
				//现价
				var Price =$("#productPrice").val();
				if(!( /^\d+(\.\d+)?$/).test(Price)){
						layer.msg("现价为数字！",{time: 5000, icon:5});
						return false;
				}
				
				//库存
				var Stock =$("#productStock").val();
				if(!(/^[1-9]\d*$/).test(Stock)){
						layer.msg("库存为整数字！",{time: 5000, icon:5});
						return false;
				}
				
				//最低购买数量限制 5/1 张羽 新增
				var restrict =$("#productRestrictNumber").val();
				if(!(/^([1-9]\d*|[0]{1,1})$/).test(restrict)){
						layer.msg("购买限制为整数字！",{time: 5000, icon:5});
						return false;
				}
				
				//排序
				var Sort =$("#productSort").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(Sort))&&Sort!=""){
						layer.msg("排序为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
				//浏览量
				var ShowNum =$("#productShowNum").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(ShowNum))&&ShowNum!=""){
						layer.msg("浏览量为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
				//销量
				var SaleNum =$("#productSaleNum").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(SaleNum))&&SaleNum!=""){
						layer.msg("销量为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
				//销量
				var ntegral =$("#productntegral").val();
				if((!(/^[0-9]*[1-9][0-9]*$/).test(ntegral))&&ntegral!=""){
						layer.msg("积分为大于0的数字！",{time: 5000, icon:5});
						return false;
				}
				
              	//限购数量（正整数）
          		var LimitNum =$("#productLimitNum").val();
				if(!(/^[0-9]*[1-9][0-9]*$/).test(LimitNum)&&$("#productLimitType").prop("checked")){
						layer.msg("限购数量为大于0的数字！",{time: 5000, icon:5});
						return false;
				} 
				
				//提成
				var CommissionPrice =$("#productCommissionPrice").val();
				if(!(/^\d+(\.\d+)?$/).test(CommissionPrice)){
						layer.msg("提成为数字！",{time: 5000, icon:5});
						return false;
				} 
				//积分商品
				var IntegralGoods =$("#Integra").val();
				if( IntegralGoods!=0 && IntegralGoods !=1){
						layer.msg("是否是积分商品0不是1是",{time: 5000, icon:5});
						return false;
				} 
				
				
				//原价小数
				var oldPrice = parseFloat(data.field.productOldPrice);
				var price = parseFloat(data.field.productPrice);
				
           		data.field.productOldPrice = oldPrice.toFixed(2);
           		data.field.productPrice = price.toFixed(2);
           		
           		//快递费
           		var sent = data.field.sent;
           		if(sent==""||sent==NaN){
           			data.field.sent = "";
           		}else{
           			sent = parseFloat(sent);
           			data.field.sent = sent.toFixed(2)*100;
           		}
           		
           		
           		
           		
           		//提成
           		var cprice = parseFloat(data.field.productCommissionPrice);
				if(data.field.productCommissionCode==0){
					cprice = cprice/100
				}
				data.field.productCommissionPrice = cprice.toFixed(2);
				console.log(data.field);
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
        </script>
    </body>

</html>