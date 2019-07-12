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
				<label for="L_title" class="layui-form-label"> 车位名称</label>
				<div class="layui-input-block">
					<input type="text" name="parkingname" id="parkingname" placeholder="请输入车场ID" autocomplete="off" class="layui-input" required lay-verify="required">
				</div>
				<div class="layui-inline">
				 		<div class="layui-input-inline" style="width: 100px;">
				  			<a href="javascript:openMap('查看车场名称','<%=request.getContextPath() %>/admin/parkmanagement/list?mcname=parkingLayer','600','400')" class="layui-btn " >查看车场ID</a>
						</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 车位型</label>
				<div class="layui-input-block">
					<input type="text" name="parkingLayer" id="parkingLayer" placeholder="请输入大型车,小型车,中型车" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 停车费用 </label>
				<div class="layui-input-block">
					<input type="text" name="parkingDistrict" id="parkingDistrict" placeholder="请输入停车费用 请输入整数"  autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 区域 </label>
				<div class="layui-input-block">
					<input type="text"  name="fineMultiple" id="fineMultiple"  placeholder="请输入区域 如:A区 B区" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 层域 </label>
				<div class="layui-input-block">
					<input type="text"   placeholder="请输入层域 如 一层 二层"  name="cost" id="cost" autocomplete="off" class="layui-input">
				</div>
			</div>
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
			var reg = /^[1-9]+[0-9]*]*$/; //判断正整数
			if(!flag){
				if(!reg.test(nubmer)){
					layer.msg("请输入大于0的整数！", { icon: 5, time: 1000 });
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
        	if(data.field.parkingname==""){
        		layer.msg("请输入车场名称！",{icon:5});
				return false;
        	}
        	if(data.field.parkingLayer==""){
        		layer.msg("请输入车型！",{icon:5});
				return false;
        	}
        	if(data.field.parkingDistrict==""){
        		layer.msg("请输入停车费用",{icon:5});
				return false;
        	}
        	if(data.field.fineMultiple==""){
        		layer.msg("请输入区域",{icon:5});
				return false;
        	}
          	//不能是""
			if(data.field.cost==""){
				layer.msg("请选择层数",{icon:5});
				return false;
			}	
        	var formData = new FormData($("#myForm")[0]);  
			$.ajax({
				type:"post",
				url:"addParking.do",
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