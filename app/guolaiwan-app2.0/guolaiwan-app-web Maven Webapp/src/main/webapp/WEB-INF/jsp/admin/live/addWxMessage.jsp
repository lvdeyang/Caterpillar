<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/image";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>直播消息推送</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
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
    <script>
   
		function pushAllUser(){
			var msgtype=$('#msgtype').val();
			var msgtype1=$('#msgtype1').val();
			if(msgtype=="已发送"||msgtype1=="已发送"){
			alert("您已经推送过了，请不要重复推送。（如未提示“已发送完毕”请不要关闭此页面）");
				return;
			}
			$('#msgtype1').val("已发送");
			var url='<%=basePath%>admin/live/pushalluser';
			var liveId=$('#liveId').val();
			var livetitle=$('#livetitle').val();
			var livetime=$('#livetime').val();
			var livecontent=$('#livecontent').val();
			var number=$('#number').val();
			$.post(url,{"liveId":liveId,"livetitle":livetitle,"livetime":livetime,"livecontent":livecontent,"number":number},function(data){
			
					if(data==1){
					$('#msgtype').val('已发送');
					alert("已发送完毕")
					}
			})
		}
		
		function mannumber(id){
			$('#number').val(id);
		}
	
	</script>
</head>

<body>
	<div class="x-body" style="height: 1800px;">
		
			<input type="text" hidden="hidden" name="liveId" id="liveId" value="${liveId}">
		
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 直播标题 </label>
				<div class="layui-input-block">
					<input type="text" name="livetitle" id="livetitle" autocomplete="off" class="layui-input" style="width: 50%" title="直播的标题" placeholder="直播的标题">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 直播时间 </label>
				<div class="layui-input-block">
					<input type="text" name="livetime" id="livetime"  autocomplete="off" class="layui-input" style="width: 50%" title="直播的时间" placeholder="例：06-18 6:00 -- 06-18 14:00">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 内容简要 </label>
				<div class="layui-input-block">
					<input type="text" name="livecontent"  id="livecontent" autocomplete="off" style="width: 50%" class="layui-input" placeholder="直播的简要内容 或 欢迎词等">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 人数选择</label>
					<input type="radio" name="number1"  id="100" onclick="mannumber(this.id)" checked="checked" >100人(预计1分钟)
					<input type="radio" name="number1"  id="300" onclick="mannumber(this.id)"  >300人(预计3-4分钟)
					<input type="radio" name="number1"  id="500" onclick="mannumber(this.id)"  >500人(预计6-8分钟)
					<input type="radio" name="number1"  id="1000" onclick="mannumber(this.id)"  >1000人(预计13-16分钟)
					<input type="radio" name="number1"  id="2000" onclick="mannumber(this.id)"  >2000人(预计25-30分钟)
					<input type="text" name="number" id="number" hidden="hidden" value="100">
			</div>
			<div class="layui-form-item">
				<label for="L_title" class="layui-form-label"> 是否发送</label>
				<div class="layui-input-block">
					 <input type="text" readonly="readonly" name="msgtype" class="layui-input" id="msgtype" value="未发送" style="width: 100px">
					 <input type="text" hidden="hidden" name="msgtype" id="msgtype1" value="未发送" style="width: 100px">
				</div>
			</div>
			
            <div class="layui-form-item">
                <button class="layui-btn" onclick="pushAllUser()"  >
				确认推送
                </button>
            </div>

	</div>
	
	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js"
		charset="utf-8">
        </script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js"
		charset="utf-8">
       </script>

</body>

</html>