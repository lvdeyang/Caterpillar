<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>过来玩管理平台</title>
  <link rel="stylesheet" href="<%=path %>/layui/css/login.css">
  <script type="text/javascript" src="<%=path %>/layui/js/jquery.min.js"></script>
<%--   <script type="text/javascript" src="<%=path %>/layui/js/index.js"></script> --%>
  <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
</head>
<body class="login-bg">
    <canvas id="fullstarbg">你的浏览器不支持canvas标签</canvas>
    <div class="login">
        <div class="message">过来玩管理平台登录</div>
        <div id="darkbannerwrap"></div>   
        <form method="post" class="layui-form" >
            <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>
    <script>
            layui.use(['form','layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;
				//浏览器判断
				 var userAgent = navigator.userAgent;
				var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    			var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    			var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    			var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器//谷歌
				var liu;
				if (isIE) {
        			var IE5 = IE55 = IE6 = IE7 = IE8 = false;
        			var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        			reIE.test(userAgent);
        			var fIEVersion = parseFloat(RegExp["$1"]);
        			liu = "IE"+fIEVersion;
    			}
    			if(isOpera){
    				liu = "欧朋";
    			}
    			if(isFF){
    				liu = "火狐";
    			}
    			if(isSafari){
    				liu = "google";
    			}
    			if(!(isFF||isSafari)){
					alert("您的浏览器版本过低，请更换chrome浏览器、360浏览器、火狐浏览器。\n您的浏览器内核是"+liu+"。");
				}





                //监听提交
                form.on('submit(login)',
                function(data) {
                    console.log(data);
                    $.ajax({
                    	type:"post", 
                    	url:"../login/login.do",
                    	data:data.field,
                    	success:function(msg){
                    		if(msg == "error") {
                    			layer.alert("账号或密码错误！");
                    		} else {
                    			 location.href = "../admin/my";
                    		}
                    	}
                    
                    })
                   
                    return false;
                });

            });

        </script>

    
    <!-- 底部结束 -->
    
</body>
</html>