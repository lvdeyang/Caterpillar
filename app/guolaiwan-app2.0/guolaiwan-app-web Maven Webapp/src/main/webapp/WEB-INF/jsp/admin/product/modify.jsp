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
                        	<input type="text" id="productName" name="productName" required lay-verify="required" value="${product.productName}"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 					<div class="layui-inline">
 						<label for="productSubtitle" class="layui-form-label">
							副标题：
                   		</label>
                    	<div class="layui-input-inline">
                       		<input type="text" id="productSubtitle" name="productSubtitle" required lay-verify="required" value="${product.productSubtitle}"
                        	autocomplete="off" class="layui-input">
                    	</div>
                	</div>
 				</div>
                
				
				
				
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商家名称</label>
						<div class="layui-input-inline">
							<input type="text" id="MerchantName" name="productMerchantName" required lay-verify="required" value="${product.productMerchantName}"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="MerchantID" name="productMerchantID"  value="${product.productMerchantID}"
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a href="javascript:openMap('修改商家','<%=request.getContextPath() %>/admin/merchant/sellist?mcname=MerchantName&mcuuid=MerchantID','600','400')" class="layui-btn " >修改商家</a>
						</div>
					</div>
				</div>
                <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">板块名称</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="modularName" required lay-verify="required"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly" value="${product.productModularCodeName}">
							<input type="text" id="L_title" name="modularCode" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none" value="${product.productModularCode}">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">分类名称</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="modularClass" required lay-verify="required" value="${product.productClassName}"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly">
							<input type="text" id="L_title" name="modularClassId" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none" value="${product.productClassCode}">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a href="javascript:openMap('修改分类','<%=request.getContextPath() %>/admin/modular/comSel','600','400')" class="layui-btn " >修改分类</a>
						</div>
					</div>
				</div>
                <div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商家分类</label>
						<div class="layui-input-inline">
							<input type="text" id="L_title" name="merMName" placeholder="选填"
							lay-verify="title" autocomplete="off" class="layui-input" readonly="readonly" value="${product.merMName}">
							<input type="text" id="L_title" name="merMId" 
							lay-verify="title" autocomplete="off" class="layui-input" style="display: none" value="${product.merMId}">
						</div>
					</div>
					<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a id="merclass" href="javascript:openMap('选择分类','<%=request.getContextPath() %>/admin/merModular/comSel?merchant=${product.productMerchantID}','600','400')" class="layui-btn " >选择分类</a>
						</div>
					</div>
				</div>
                
                
               <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productOldPrice" class="layui-form-label">
						原价：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productOldPrice" name="productOldPrice" required lay-verify="required" placeholder="（单位：元）"
                        autocomplete="off" class="layui-input" value="${product.productOldPrice}">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productPrice" class="layui-form-label">
						现价：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productPrice" name="productPrice" required lay-verify="required" placeholder="（单位：元）"
                        autocomplete="off" class="layui-input" value="${product.productPrice}">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productStock" class="layui-form-label">
						库存(房间个数)：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productStock" name="productStock" required lay-verify="required" placeholder="数量"
                        autocomplete="off" class="layui-input" value="${product.productStock}">
                 	</div>
                 </div>
                 
                  <!--  5/1 张羽 新增 最低购买数量限制 -->
                   <div class="layui-inline">
                    <label for="productRestrictNumber" class="layui-form-label">
						最低购买量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productRestrictNumber" name="productRestrictNumber" required lay-verify="required" placeholder="最低购买数量限制"
                        autocomplete="off" class="layui-input" value="${product.productRestrictNumber}">
                 	</div>
                 </div>
                 
                </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                    	<label class="layui-form-label">开始时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productBeginDate" name="productBeginDate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${beginDate}">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">结束时间：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEnddate" name="productEnddate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${endDate}">
                    	</div>
                    </div>
                    <div class="layui-inline">
                    	<label class="layui-form-label">有效期：</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="productEctivedate" name="productEctivedate" placeholder="yyyy-MM-dd HH:mm:ss" required lay-verify="required"
                        	class="layui-input" value="${ectiveDate}">
                    	</div>
                    </div>
                </div>
                
                <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 图片 </label>
				<div class="layui-input-inline" style="width: 100px">
				    <img alt="" src="${sysConfig.webUrl}${product.productShowPic}" id="shopImg" style=" height:100px;width:100px ">
					<input type="hidden" id="shopPic" name="shopPic" 
						lay-verify="title" autocomplete="off"  class="layui-input" value="${product.productShowPic}">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=shopPic&img=shopImg','600','400')" class="layui-btn " >更换图片</a>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label">详情视频 </label>
				<div class="layui-input-inline" style="width: 100px">
				    <video src="${sysConfig.webUrl}${product.videourl}" id="provideo" style=" height:100px;width:100px "/>
					<input type="text" id="videourl" name="videourl" 
						lay-verify="title" autocomplete="off"  class="layui-input" value="${product.videourl}">
				</div>
				<div class="layui-input-inline">
					<a href="javascript:openMap('更换视频','<%=request.getContextPath() %>/admin/picture/sellist?sel=videourl&img=provideo','600','400')" class="layui-btn " >更换视频</a>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 轮播图 </label>
				<div class="layui-input-inline">
				<table>
					<tr>
					<td >
						<img alt="" src="${sysConfig.webUrl}${img1}" style=" height:100px;width:100px " id="imgurl1" name="imgurl" >
						<input type="hidden" id="img1" name="img" value='${img1}'>
						<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img1&img=imgurl1','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl1','#img1')">删除图片</button>
					</td>		
					<td>
						<img alt="" src="${sysConfig.webUrl}${img2}" style=" height:100px;width:100px " id="imgurl2" name="imgurl">
						<input type="hidden" id="img2" name="img" value='${img2}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img2&img=imgurl2','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl2','#img2')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img3}" style=" height:100px;width:100px " id="imgurl3" name="imgurl">
						<input type="hidden" id="img3" name="img"" value='${img3}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img3&img=imgurl3','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl3','#img3')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img4}" style=" height:100px;width:100px " id="imgurl4" name="imgurl">
						<input type="hidden" id="img4" name="img" value='${img4}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img4&img=imgurl4','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl4','#img4')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img5}" style=" height:100px;width:100px " id="imgurl5" name="imgurl">
						<input type="hidden" id="img5" name="img" value='${img5}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img5&img=imgurl5','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl5','#img5')">删除图片</button>
					</td>
					<td>
						<img alt="" src="${sysConfig.webUrl}${img6}" style=" height:100px;width:100px " id="imgurl6" name="imgurl">
						<input type="hidden" id="img6" name="img" value='${img6}'>
						<a href="javascript:openMap('更换图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=img6&img=imgurl6','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px">更换图片</a>
						<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgurl6','#img6')">删除图片</button>
					</td>
					</tr>
				</table>
					<input type="hidden" id="shopMpic" name="shopMpic" 
						lay-verify="title" autocomplete="off" class="layui-input">
		        </div>
			</div>
			
			<div class="layui-form-item">
				<div class = "layui-inline">
    				<label class="layui-form-label">提成：</label>
    				<div class="layui-input-block">
      					<input type="radio" name="productCommissionCode" value="1" title="佣金" id="Commission"  lay-filter="filter">
      					<input type="radio" name="productCommissionCode" value="0" title="比例" id="Proportion"  lay-filter="filter">
    				</div>
    			</div>
    			
    			<div class = "layui-inline">
    				<label class="layui-form-label" id ="commissionPrice">金额：</label>
    				<div class="layui-input-block">
      					<input type="text" name="productCommissionPrice" id="productCommissionPrice" class="layui-input" placeholder="（单位：元）"
      					required lay-verify="required" value="${product.productCommissionPrice}">
    				</div>
    			</div>
  			</div>
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						显示：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="productIsShow" lay-skin="switch" id="productIsShow" lay-text="ON|OFF" lay-filter="switchTest" value="${product.productIsShow}">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch" >
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
                        <input type="checkbox" name="productIsRefund" lay-skin="switch" id="productIsRefund" lay-text="ON|OFF" lay-filter="switchTest" value="${product.productIsRefund}">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch">
                        	<em>OFF</em>
                        	<i></i>
                        </div>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                	<div class="layui-inline">
                	
		               	<label class="layui-form-label">
								是否拼团：
		                   </label>
		                   <div class="layui-input-block" onclick="changeGroup()">
		                       <input type="checkbox" name="group" lay-skin="switch" id="group" lay-text="ON|OFF" 
		                       lay-filter="switchTest" value="${product.isgroup}">
		                       <div class="layui-unselect layui-form-switch" lay-skin="_switch" >
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
                        	autocomplete="off" class="layui-input" disabled="disabled" value="${groupbuypo.groupnum}">
                    	</div>
                    </div>
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
 							<font id="LimitNum2" style="color:#757575">拼团价格：</font>
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="groupprice" name="groupprice"  placeholder="价格"
                        	autocomplete="off" class="layui-input" disabled="disabled" value="${groupbuypo.groupprice}">
                    	</div>
                    </div>
                    <div class="layui-inline" >
                    	<label class="layui-form-label" >
 							<font id="LimitNum3" style="color:#757575">拼团时限：</font>
                    	</label>
                    	<div class="layui-input-inline">
                        	<input type="text" id="grouptime" name="grouptime"  placeholder="时间限制（小时）"
                        	autocomplete="off" class="layui-input" disabled="disabled" value="${groupbuypo.grouptime}">
                    	</div>
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						是否人脸：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="ifFace" lay-skin="switch" id="ifFace" lay-text="ON|OFF" lay-filter="switchTest" value="${product.ifFace}">
                        <div class="layui-unselect	layui-form-switch" lay-skin="_switch" >
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
                        	<input type="checkbox" name="productLimitType" lay-skin="switch" id="productLimitType" lay-text="ON|OFF" lay-filter="switchTest" value="${product.productLimitType}">
                        	<div class="layui-unselect	layui-form-switch" lay-skin="_switch">
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
                        	autocomplete="off" class="layui-input" disabled="disabled" value="${product.productLimitNum}">
                    	</div>
                    </div>
                </div>
          
                     <div class="layui-form-item">
		               <div class="layui-inline">
		                    	<label class="layui-form-label" style="width:200px !important;">
		 							是否限制每天预约门票数:
		                    	</label>
		                    	<div class="layui-input-inline" onclick="changeNums()">
		                        	<input type="checkbox" name="numTicketsByDayType" lay-skin="switch" id="numTicketsByDayType" lay-text="ON|OFF" lay-filter="switchTest" value="${product.numTicketsByDayType}" >
		                        	<div class="layui-unselect	layui-form-switch" lay-skin="_switch" >
		                        	<em>OFF</em>
		                        	<i></i>
		                        	</div>
		                    	</div>
		                    </div>
               
		                <div class="layui-inline" >
		                    <label class="layui-form-label" style="width:200px !important;">
		 						每天可预约门票数量:
		                    </label>
		                    <div class="layui-input-inline">
		                        	<input type="text" id="numTicketsByDay" name="numTicketsByDay"  placeholder="个数"
		                        	autocomplete="off" class="layui-input" disabled="disabled" value="${product.numTicketsByDay}">
		                    </div>
		                </div>
             	</div>  
                
                <div class="layui-form-item">
                    <label class="layui-form-label">
 						推荐：
                    </label>
                    <div class="layui-input-block">
                        <input type="checkbox" name="like[productIndexRecommend]" value="${product.productIndexRecommend}" id="productIndexRecommend" title="首页推荐">
                    	<input type="checkbox" name="like[productListRecommend]" value="${product.productListRecommend}" id="productListRecommend" title="列表推荐">
                    </div>
                </div>
                
                <div class="layui-form-item">
               	<div class="layui-inline">
                    <label for="productSort" class="layui-form-label">
						排序：
                    </label>
                    <div class="layui-input-inline">
                    	<input type="text" id="productSort" name="productSort"
                        autocomplete="off" class="layui-input" value="${product.productSort}">
                 	</div>
                 </div>
                
                 <div class="layui-inline">
                    <label for="productShowNum" class="layui-form-label">
						浏览量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productShowNum" name="productShowNum" 
                        autocomplete="off" class="layui-input" value="${product.productShowNum}">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="productSaleNum" class="layui-form-label">
						销量：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productSaleNum" name="productSaleNum"
                        autocomplete="off" class="layui-input" value="${product.productSaleNum}">
                 	</div>
                 </div>
                 <div class="layui-inline">
                    <label for="productntegral" class="layui-form-label">
						积分：
                    </label>
                    <div class="layui-input-inline">
                    <input type="text" id="productntegral" name="productntegral"
                        autocomplete="off" class="layui-input" value="${product.productntegral}">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="sent" class="layui-form-label">
						分销ID：
                    </label>
                    <div class="layui-input-inline">
                   		 <input type="text" id="distributeId" name="distributeId" value="${product.distributeId}"
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 <div class="layui-inline">
                    <label for="sent" class="layui-form-label">
						积分商品：
                    </label>
                    <div class="layui-input-inline">
                   		 <input type="text" id="Integra" value="0" name="Integra" 
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 
                 <div class="layui-inline">
                    <label for="sent" class="layui-form-label">
						坐标X：
                    </label>
                    <div class="layui-input-inline">
                   		 <input type="text" id="x" name="x"  value="${product.x}"
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 <div class="layui-inline">
                    <label for="sent" class="layui-form-label">
						坐标Y：
                    </label>
                    <div class="layui-input-inline">
                   		 <input type="text" id="y" name="y" value="${product.y}"
                        	autocomplete="off" class="layui-input">
                 	</div>
                 </div>
                 
                 <div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 导览地图 </label>
					<div class="layui-input-inline">
						<table>
							<tr>
							<td align="center">
							<img alt="" src="${sysConfig.webUrl}${product.mapUrl}" style=" height:100px;width:100px " id="imgmap">
							<input type="hidden" id="mapUrl" name="mapUrl" value="${product.mapUrl}">
							<a href="javascript:openMap('上传图片','<%=request.getContextPath() %>/admin/picture/sellist?sel=mapUrl&img=imgmap','600','400')" class="layui-btn layui-btn-xs" style="width:90px;margin-left:5px;margin-right:5px" >更换图片</a>
							<button class="layui-btn layui-btn-xs" type="button" style="width:90px;margin-left:5px;margin-right:5px" onclick="delpic('#imgmap','#mapUrl')">删除图片</button>						
							</td>
							</tr>
						</table>
					</div>
				</div>
                <div class="layui-form-item">
					<label for="L_title" class="layui-form-label"> 选择声音</label>
					<div class="layui-input-inline" style="width: 100px">
					    <input id="voiceUrl"  hidden="hidden"  value="${product.voiceUrl}"><!-- 这个没啥用，暂时不删了 -->
						<input type="text" id="voice" name="voice" value="${product.voiceUrl}"
							lay-verify="title" autocomplete="off"   class="layui-input">
					</div>
					<div class="layui-input-inline">
						<a href="javascript:openMap('选择声音','<%=request.getContextPath() %>/admin/Voice/sellist?sel=voice&img=voiceUrl','600','400')" class="layui-btn " >声音素材</a>
					</div>
				</div>
                <div class="layui-inline">
                    <label for="sent" class="layui-form-label">
						是否室内：
                    </label>
                    <div class="layui-input-inline">
                         <div class="layui-input-inline">
	                    	  <select name="isInner" lay-verify="required" value="${product.isInner}">
						        <option selected="selected" value="0">室外</option>
						       	<option value="1">室内</option>
						      </select>
	                   	
	                 	</div>
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
                </div>
               
                
                <div class="layui-form-item proLine">
                <div class="layui-inline">
                    <label for="traffic" class="layui-form-label">
						交通：
                    </label>
                    <div class="layui-input-inline">
                         <select value="${product.traffic}" id="traffic" name="traffic" lay-verify="required" lay-search="">
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
                    <input type="text" id="productDayCount" value="${product.productDayCount}" name="productDayCount"
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
                        autocomplete="off" class="layui-input" value="${product.datesOn }">
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
                        <textarea name="productIntroduce"   placeholder="请输入内容" class="layui-input" id="productIntroduce" style="height:300px">${product.productIntroduce}</textarea>
                    </div>
                    <input type="hidden" name="uuid" value="${product.uuid}">
                </div>
                
                <div class="layui-form-item proLine">
                    <label for="costMessage" class="layui-form-label">
 						费用包含：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="costMessage" value=""  placeholder="请输入内容" class="layui-input" id="costMessage" style="height:300px">${product.costMessage}</textarea>
                    </div>
                </div>
                
                
                 <div class="layui-form-item proLine">
                    <label for="remarks" class="layui-form-label">
 						备注：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="remarks" value=""  placeholder="请输入内容" class="layui-input" id="remarks" style="height:300px">${product.remarks}</textarea>
                    </div>
                </div>
                
                <div class="layui-form-item proLine">
                    <label for="notes" class="layui-form-label">
 						预定须知：
                    </label>
                    <div class="layui-input-block">
                        <textarea name="notes" value=""  placeholder="请输入内容" class="layui-input" id="notes" style="height:300px">${product.notes}</textarea>
                    </div>
                </div>
                
                
                
                
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-danger" lay-filter="add1" lay-submit>
						保存草稿
                    </button>
                     <button class="layui-btn" lay-filter="add2" lay-submit>
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
        	var webUrl = "${sysConfig.webUrl}";
             
             layui.use(['form','layer','laydate'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer
              ,laydate = layui.laydate;
              
              
              var nameStrs=$('#datesOn').val().split(',');
              for(var i=0;i<nameStrs.length;i++){
                 var chk=$('input[name="'+nameStrs[i]+'"]');
                 if(chk){
                    $(chk).attr('checked',true);
                 }
                 
              }
              
              var modCode='${product.productModularCode}';
              if(modCode=='2021'){
                $('.proLine').show();
              }else{
                $('.proLine').hide();
              }
              
              
            //是否限购
            if($("#productLimitType").val()=="1"){
             		$("#productLimitType").prop("checked",true);
             		$("#productLimitNum").removeAttr("disabled");
           			$("#LimitNum").removeAttr("style");
            }
               if($("#productLimitType").val()=="0"){
             		$("#productLimitNum").val("");
            }
              //是否预购
               console.log($("#numTicketsByDayType").val());
            if($("#numTicketsByDayType").val()=="1"){
             		$("#numTicketsByDayType").prop("checked",true);
             		$("#numTicketsByDay").removeAttr("disabled");
            }else{
             		$("#numTicketsByDay").val("0");
             		$("#numTicketsByDayType").val("0");
            }
             //是否拼团
            if($("#group").val()=="1"){
             		$("#group").prop("checked",true);
            }
           
         
           
            //是否显示
            if($("#productIsShow").val()=="1"){
             		$("#productIsShow").prop("checked",true);
            }
            if($("#ifFace").val()=="1"){
             		$("#ifFace").prop("checked",true);
            }
            if($("#productIsRefund").val()=="1"){
             		$("#productIsRefund").prop("checked",true);
            }
            
            //是否前端推荐
            if($("#productListRecommend").val()=="1"){
             		$("#productListRecommend").prop("checked",true);
            }
            //是否页面推荐
            if($("#productIndexRecommend").val()=="1"){
             		$("#productIndexRecommend").prop("checked",true);
            }
            if(${product.productCommissionCode}=='1'){
            		$("#Commission").attr("checked","checked")
  					$("#commissionPrice").html("金额：");
  					}
  			if(${product.productCommissionCode}=='0'){
  					$("#Proportion").attr("checked","checked")
  					$("#commissionPrice").html("百分之：");
  			}
  			
  			if($("#productSort").val()=="0"){
  				$("#productSort").val("");
  			}
  			if($("#productShowNum").val()=="0"){
  				$("#productShowNum").val("");
  			}
  			if($("#productSaleNum").val()=="0"){
  				$("#productSaleNum").val("");
  			}
  			if($("#productntegral").val()=="0"){
  				$("#productntegral").val("");
  			}
            //渲染
            layui.form.render();
             
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
              	var message = "成功保存草稿！"
              	data.field.productAuditstates="C";
              	console.log(data.field);
              	add(data,message)
              	   return false;
              });
              	//监听提交
              form.on('submit(add2)', function(data){
              	var message = "提交待审核成功！"
              	data.field.productAuditstates="D";
              	console.log(data.field);
              	add(data,message)
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
            function changeNums(){
         		if($("#numTicketsByDayType").prop("checked")){
         		console.log("修改");
         			$("#numTicketsByDay").removeAttr("disabled");
         			$("#Stocks").removeAttr("style");
         		}else{
         			$("#numTicketsByDay").prop("disabled", "disabled");
         			$("#Stocks").prop("style", "color:#757575");
					$("#numTicketsByDay").val("0");
					$("#numTicketsByDayType").val("0");
			
         		}
            }
         
           function changeGroup(){
           		if($("#group").prop("checked")){
           			$("#group").val(1);
           			$("#groupnum").removeAttr("disabled");
           			$("#groupprice").removeAttr("disabled");
           			$("#grouptime").removeAttr("disabled");
           			$("#LimitNum1").removeAttr("style");
           			$("#LimitNum2").removeAttr("style");
           		}else{
           			$("#groupnum").prop("disabled", "disabled");
           			$("#groupprice").prop("disabled", "disabled");
           			$("#grouptime").prop("disabled", "disabled");
           			$("#LimitNum1").prop("style", "color:#757575");
           			$("#LimitNum2").prop("style", "color:#757575");
					$("#groupnum").val("");
					$("#groupprice").val("");
					$("#grouptime").val("");
           		}
           }
           
           function add(data,message){
           		//多图字段          
           		var proMpic="";
           		var strPic;
           		for(var i=1;i<7;i++){
           			var str = "#img"+i;
           			if($(str).val()!=''){
           				if(proMpic==''){
           					strPic = $(str).val();
           				}else{
           					strPic = $(str).val()+",";
           				}
           				proMpic = strPic+proMpic;
           				console.log()
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
				if(!(/^([1-9]\d*|[0]{1,1})$/).test(Stock)){
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
				
				//积分
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
							//可预约数量
				var Stocks =$("#numTicketsByDay").val();
			if(!(/^[0-9]*[1-9][0-9]*$/).test(Stocks)&&$("#numTicketsByDayType").prop("checked")){
		/* 		if(!(/^([1-9]\d*|[0]{1,1})$/).test(Stocks)){ */
						layer.msg("预约数量为数字！",{time: 5000, icon:5});
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
           		
           		//提成
           		var cprice = parseFloat(data.field.productCommissionPrice);
				if(data.field.productCommissionCode==0){
					cprice = cprice/100
				}
				data.field.productCommissionPrice = cprice.toFixed(2);
		/* 		if(data.field.numTicketsByDayType==undefined){
					data.field.numTicketsByDayType='0'
				} */
				if(data.field.numTicketsByDay==""){
					data.field.numTicketsByDay=0
				}
				console.log(data.field);
                $.ajax({
                	  type:"post",
           			  url:"modify.do",
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
           
           function delpic(img,inp){
           		$(img).removeAttr("src");
           		$(inp).val("");           	
           }
              
             
           
        </script>
        
    </body>

</html>