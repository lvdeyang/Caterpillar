<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>停车场详情</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/UEditor/third-party/template.min.js"></script>
</head>

<body>
	<div class="x-body" >
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="uuid" value="${po.uuid}">
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场名称 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingName" value="${po.parkingName}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场地址 </label>
				<div class="layui-input-block">
					<input type="text" name="address" value="${po.address}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场电话 </label>
				<div class="layui-input-block">
					<input type="text" name="phone" value="${po.phone}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场图片 </label>
				<div class="layui-input-inline">
					<div class="layui-upload">
						<!-- <button type="button" class="layui-btn" id="test"> 上传图片 </button> -->
						<div class="layui-upload-list">
						<!-- TODO -->
							<img class="layui-upload-img" src="<%=basePath %>${po.parkingImg}" id="demo" style="width:280px;">
<%-- 							<img class="layui-upload-img" src="${sysConfig.webUrl}${po.parkingImg}" id="demo" style="width:280px;"> --%>
						</div>
						
					</div>   
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 总停放位 </label>
				<div class="layui-input-block">
					<input type="text" name="commonParking" value="${po.commonParking}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 已用车位 </label>
				<div class="layui-input-block">
					<input type="text" name="usedParking" value="${po.usedParking}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 车位位置 </label>
				<div class="layui-input-block">
					<input type="text" id="positionType" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 充电柱 </label>
				<div class="layui-input-block">
					<input type="text" id="chargingColumnType"  readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车车层 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingLayer" value="${po.parkingLayer}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车区 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingDistrict" value="${po.parkingDistrict}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 罚款倍数 </label>
				<div class="layui-input-block">
					<input type="text" name="fineMultiple" value="${po.fineMultiple}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车费用 </label>
				<div class="layui-input-block">
					<input type="text" name="cost" value="${po.cost}" readonly="readonly" autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车时间 </label>
				<div class="layui-input-block">
					<input type="text"  name="stoppingTime" value="${po.stoppingTime}" class="layui-input" placeholder="-">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车条例  </label>
				<div class="layui-input-block">
					<textarea name="regulations" autocomplete="off" style="height: 300px;resize:vertical;" readonly="readonly" class="layui-input">${po.regulations}</textarea>
				</div>
			</div>
			
			
			<%-- <div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 头像图片地址 </label>
				<div class="layui-input-inline" style="width: 100px">
					<img alt="" src="${sysConfig.webUrl}${po.shopHeading}" id="shopHeadingPic" style=" height:100px;width:100px ">
					<input type="hidden" name="shopHeading" id="shopHeading"
						value="${po.shopHeading}" autocomplete="off"
						class="layui-input">
				</div>
				
			</div>
			
 --%>
		</form>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
<script>
	$(function(){
		var position = ${po.position};
		if(position == 1 ){
			$("#positionType").val("室内");
		}else if(position == 0){
			$("#positionType").val("室外");
		}else{
			$("#positionType").val("暂无数据");
		}
		var chargingColumn = ${po.chargingColumn};
		if(chargingColumn == 1){
			$("#chargingColumnType").val("有");
		}else if(chargingColumn == 0){
			$("#chargingColumnType").val("无");
		}else{
			$("#pchargingColumnType").val("暂无数据");
		}
	})
	
</script>
</body>

</html>

