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
			<input type="hidden" name="id" value="${po.id}">
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车场Id </label>
				<div class="layui-input-block">
					<input type="text" name="commonParking" onkeyup="numChk($(this))" value="${po.attractionsId}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 车位型 </label>
				<div class="layui-input-block">
					<input type="text" name="usedParking"  value="${po.parkingModel}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车费用  </label>
				<div class="layui-input-block">
					<input type="text" name="position"  value="${po.money}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 区域 </label>
				<div class="layui-input-block">
					<input type="text" name="chargingColumn"  value="${po.area}"  autocomplete="off" lay-verify="title" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 层 数</label>
				<div class="layui-input-block">
					<input type="text" name="parkingLayer" value="${po.tier}"  autocomplete="off" lay-verify="title" class="layui-input">
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
	 //打开分类
            function openMap (title,url,w,h) {
                x_admin_show(title,url,w,h); 
            }
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
        		layer.msg("请输入车场ID！",{icon:5});
				return false;
        	}
        	if(data.field.usedParking==""){
        		layer.msg("请输入车位车型！",{icon:5});
				return false;
        	}
        	if(data.field.position==""){
        		layer.msg("请输入费用！",{icon:5});
				return false;
        	}
          	//不能是""
			if(data.field.chargingColumn==""){
				layer.msg("请输入区！",{icon:5});
				return false;
			}
			if(data.field.parkingLayer==""){
				layer.msg("请输入层！",{icon:5});
				return false;
			}	
        	var formData = new FormData($("#myForm")[0]);  
			$.ajax({
				type:"post",
				url:"updateMoney.do",
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