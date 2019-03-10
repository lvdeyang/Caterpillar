<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>停车场管理-新增</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/layui/UEditor/third-party/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/layui/UEditor/third-party/template.min.js"></script>
</head>

<body>
	<div class="x-body">
		<form class="layui-form layui-form-pane" id="myForm" enctype="multipart/form-data">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场名称 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingName" required placeholder="请输入停车场名称" lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场地址 </label>
				<div class="layui-input-block">
					<input type="text" name="address" id="address" placeholder="请输入停车场地址" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场电话 </label>
				<div class="layui-input-block">
					<input type="text" name="phone" id="phone" placeholder="请输入停车场电话" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场图片 </label>
				<!-- <div class="layui-input-inline" style="width: 100px">
					<img alt="" src="" id="parkingImg" style=" height:100px;width:100px "> 
					<input type="hidden" name="parkingImgPic" id="parkingImgPic" autocomplete="off" class="layui-input">
				</div> -->
				<div class="layui-input-inline">
					<div class="layui-upload">
						<button type="button" class="layui-btn" id="test"> 上传图片 </button>
						<div class="layui-upload-list">
							<img class="layui-upload-img" id="demo" style="width:280px;">
							<p id="demoText"></p>
							<input type="hidden" name="parkImg">
						</div>
						
					</div>   
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 总停放位 </label>
				<div class="layui-input-block">
					<input type="text" onkeyup="numChk($(this))" placeholder="请输入总停放位（正整数）" name="commonParking" id="commonParking" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 已用车位 </label>
				<div class="layui-input-block">
					<input type="text" onkeyup="numChk($(this))" placeholder="请输入已用车位（正整数）" name="usedParking" id="usedParking" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 车位位置 </label>
				<div class="layui-input-block">
					<select name="position" id="position" required>
						<option value="">请选择</option>
						<option value="1">室内</option>
						<option value="0">室外</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 充电柱 </label>
				<div class="layui-input-block">
					<select name="chargingColumn" id="chargingColumn" required>
						<option value="">请选择</option>
						<option value="1">有</option>
						<option value="0">无</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车车层 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingLayer" id="parkingLayer" placeholder="请输入停车车层" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车区 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingDistrict" id="parkingDistrict" placeholder="请输入停车区"  autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 罚款倍数 </label>
				<div class="layui-input-block">
					<input type="text" onkeyup="numChk($(this),'f')" name="fineMultiple" id="fineMultiple"  placeholder="请输入罚款倍数" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车费用 </label>
				<div class="layui-input-block">
					<input type="text"  onkeyup="numChk($(this),'f')"  placeholder="请输入停车费用（单位：元/小时）" name="cost" id="cost" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车时间 </label>
				<div class="layui-input-block">
					<input type="text"  name="stoppingTime" id="stoppingTime" class="layui-input" placeholder="请输入停车时间">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车条例  </label>
				<div class="layui-input-block">
					<textarea id="regulations" name="regulations" style="height: 300px;resize:vertical;" autocomplete="off" class="layui-input"></textarea>
				</div>
			</div>
<%-- 
			<！-- 调取地图 -->
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 坐标 </label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" name="shopLongitude" id="shopLongitude" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" name="shopLatitude" id="shopLatitude" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-input-inline" style="width: 100px;">
					<a href="javascript:openMap('腾讯地图','<%=request.getContextPath()%>/layui/txmap.html','600','400')" class="layui-btn">打开地图</a>
				</div>
			</div> 
--%>
			<input type="hidden" name="shopAuditstates" value="" class="layui-input">
			<div class="layui-form-item" style="text-align: center;margin-top:20px;">
				<button class="layui-btn" lay-filter="add1" lay-submit> 提交 </button>
				<!-- <button class="layui-btn layui-btn-danger" lay-filter="add2" lay-submit>存为草稿</button> -->
			</div>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js" charset="utf-8"> </script>
	<script src="<%=request.getContextPath()%>/layui/js/x-layui.js" charset="utf-8"> </script>

	<script>
		layui.use(['form','layer','laydate','upload'], function(){
			$ = layui.jquery;
			var form = layui.form
			,layer = layui.layer
			,laydate = layui.laydate
			,upload = layui.upload;
			
			// 时间 
			laydate.render({
			  elem: '#stoppingTime'
			  ,type: 'time'
			  ,range: true
			  ,trigger: 'click'
			});
              
			// 监听提交
			form.on('submit(add1)', function(data){
				data.field.shopAuditstates = "D";
				var message = "提交成功！";  
				add(data,message);
				return false;
			});
			
			var uploadInst = upload.render({
	            elem: '#test'
	            ,url: 'upload'
	            ,auto: false
	            ,before: function (obj) {
	                obj.preview(function (index, file, result) {
	                    $('#demo').attr('src', result);
	                });
	            }
	            ,choose:function(obj){//选择文件的回调，obj为选中的文件
			    	//将每次选择的文件追加到文件队列
   					var files = obj.pushFile();
   					//预览选中的文件（本地文件）
   					obj.preview(function(index,file,result){
   						$('#demo').attr('src', result); 
   					});
			    }
        	});
		});
		
		function numChk($this,flag){
			var nubmer = $this.val();
			var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字  
			var reg = /^[1-9]+[0-9]*]*$/; //判断正整数
			if(!flag){
				if(!reg.test(nubmer)){
					layer.msg("请输入大于0的数字！", { icon: 5, time: 1000 });
					$this.val("");
				}
			}
			if(!re.test(nubmer)){
				layer.msg("请输入大于0的整数！", { icon: 5, time: 1000 });
				$this.val("");
			}
		}
        // 添加   
        function add(data,message) {
        	if(data.field.commonParking==""){
        		layer.msg("请输入总共停放车位！",{icon:5});
				return false;
        	}
        	if(data.field.cost==""){
        		layer.msg("请输入停车费用！",{icon:5});
				return false;
        	}
        	if(data.field.fineMultiple==""){
        		layer.msg("请输入罚款倍数！",{icon:5});
				return false;
        	}
        	if(data.field.usedParking==""){
        		layer.msg("请输入已用多少车位！",{icon:5});
				return false;
        	}
          	//不能是""
			if(data.field.position==""){
				layer.msg("请选择车位位置！",{icon:5});
				return false;
			}else{
				var position =data.field.position
				data.field.position = parseInt(position);
			}	
			if(data.field.chargingColumn==""){
				layer.msg("请选择是否有充电柱！",{icon:5});
				return false;
			}else{
				var chargingColumn =data.field.chargingColumn
				data.field.chargingColumn = parseInt(chargingColumn);
			}	
        	var formData = new FormData($("#myForm")[0]);  
			$.ajax({
				type:"post",
				url:"add.do",
				dataType:"json",
                data:formData,
                contentType: false,
                processData: false,
				success:function(data){
					if(data.code=="0"){
						layer.alert(data.message, {icon: 6},function () {
							try{
								var index = parent.layer.getFrameIndex(window.name);
								//console.log(index);
								//关闭当前frame
								parent.window.location.reload();
								parent.layer.close(index);
							} catch(exception) {
							  	window.location.reload();
							}
						});
					}else{
						layer.alert(data.message, {icon: 5},function () {
							try{
								var index = parent.layer.getFrameIndex(window.name);
								//console.log(index);
								//关闭当前frame
								parent.window.location.reload();
								parent.layer.close(index);
							} catch(exception) {
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