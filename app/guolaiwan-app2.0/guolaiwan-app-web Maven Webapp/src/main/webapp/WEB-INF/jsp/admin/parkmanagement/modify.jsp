<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>停车场管理-修改</title>
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
	<div class="x-body" style="height: 1800px;">
		<form class="layui-form layui-form-pane" id="myForm" enctype="multipart/form-data">
			<input type="hidden" name="uuid" value="${po.uuid}">
			<input type="hidden" name="id" value="${po.id}">
			<input type="hidden" name="imgTemp" value="${po.parkingImg}">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场名称 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingName" value="${po.parkingName}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场地址 </label>
				<div class="layui-input-block">
					<input type="text" name="address" value="${po.address}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场电话 </label>
				<div class="layui-input-block">
					<input type="text" name="phone" value="${po.phone}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场图片 </label>
				<div class="layui-input-inline">
					<div class="layui-upload">
						<button type="button" class="layui-btn" id="test"> 上传图片 </button>
						<div class="layui-upload-list">
							<img class="layui-upload-img" src="<%=basePath %>${po.parkingImg}" id="demo" style="width:280px;">
						</div>
					</div>   
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 总停放位 </label>
				<div class="layui-input-block">
					<input type="text" name="commonParking" onkeyup="numChk($(this))" value="${po.commonParking}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 已用车位 </label>
				<div class="layui-input-block">
					<input type="text" name="usedParking" onkeyup="numChkZero($(this))" value="${po.usedParking}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 车位位置 </label>
				<div class="layui-input-block">
					<select name="position" id="position" required>
						<option value="">请选择</option>
						<option <c:if test='${po.position == 1}'>selected="selected"</c:if>value="1">室内</option>
						<option <c:if test='${po.position == 0}'>selected="selected"</c:if>value="0">室外</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 充电柱 </label>
				<div class="layui-input-block">
					<select name="chargingColumn" id="chargingColumn" required>
						<option value="">请选择</option>
		                <option <c:if test='${po.chargingColumn == 1}'>selected="selected"</c:if> value="1">有</option>
						<option <c:if test='${po.chargingColumn == 0}'>selected="selected"</c:if> value="0">无</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车车层 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingLayer" value="${po.parkingLayer}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车区 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingDistrict" value="${po.parkingDistrict}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 罚款倍数 </label>
				<div class="layui-input-block">
					<input type="text" name="fineMultiple" value="${po.fineMultiple}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车费用 </label>
				<div class="layui-input-block">
					<input type="text" name="cost" value="${po.cost}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车时间 </label>
				<div class="layui-input-block">
					<input type="text"  id="stoppingTime" name="stoppingTime" value="${po.stoppingTime}" class="layui-input" placeholder="-">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车条例  </label>
				<div class="layui-input-block">
					<textarea name="regulations" autocomplete="off" style="height: 300px;resize:vertical;"  class="layui-input">${po.regulations}</textarea>
				</div>
			</div>
			
			<input type="hidden" name="shopAuditstates" value="" class="layui-input">
			<div class="layui-form-item" style="text-align: center;margin-top:20px;">
				<button class="layui-btn" lay-filter="add1" lay-submit> 修改保存 </button>
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
				update(data,message);
				return false;
			});
			/* // 监听提交
			form.on('submit(add2)', function(data){
				 data.field.shopAuditstates = "C";
				 var message = "保存草稿成功！";  
				 add(data,message);
				 return false;
			}); */
			
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
		function numChkZero($this){
			var nubmer = $this.val();
			var reg = /^[0-9]+[0-9]*]*$/; //判断正整数
			if(!reg.test(nubmer)){
				layer.msg("请输入整数数字！", { icon: 5, time: 1000 });
				$this.val("");
			}
		}
		function numChk($this,flag){
			var nubmer = $this.val();
			var re = /^[0-9]+.?[0-9]*$/; //判断字符串是否为数字  
			var reg = /^[0-9]+[0-9]*]*$/; //判断正整数
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
        function update(data,message) {
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
				url:"update.do",
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
	<script>
		$(function(){
			var stoppingTime = ${po.stoppingTime};
			if(stoppingTime){
				laydate.render({
				  elem: '#stoppingTime'
				  ,value: stoppingTime
				  ,isInitValue: true
				});
			}
			
			var chargingColumn = ${po.chargingColumn};
    		$("#chargingColumn option[value='"+chargingColumn+"']").attr("selected","selected"); 
		})
		
	</script>
</body>

</html>