<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>
body{
width:100%;height:100%;
background: url("../webtheme/theme/img/1.jpg") no-repeat !important;
background-size:100% 100%; 
z-index:11111111;
}
#submit:hover{
 text-decoration:none;
background:rgba(43,173,74,0.5) !important;
transition: all .3s;
}
</style>
<!doctype html>
<html>
 <head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> 
	<meta name="renderer" content="webkit">
	<title>用户注册</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/login.css">
 </head>
 <body>

<!--- header begin-->
<%-- <header id="pc-header">
    <div class="login-header" style="padding-bottom:0">
        <div><h1><a href="<%=path %>/index"><img src="<%=path %>/webtheme/theme/icon/logo.png"></a></h1></div>
    </div>
</header> --%>
<!-- header End -->



<section id="login-content" style="width:auto;height:auto;">
    <div class="login-centre">
      <div style="padding:20px 120px;text-align:left;line-height: 40px;position: relative;">
          <h1>欢迎注册我们的网站</h1>
          <h3>请您完善相关信息进行注册</h3>
          <img style="position: absolute;top:25px;right:140px;" src="<%=path %>/webtheme/theme/img/anquan.png">
      </div>    
        <div class="login-back" style="width:auto;height:auto;">
            <div class="H-over">
                <form action="" class="layui-form">
                <div class="login-input">
                        <label><i class="heart">*</i>手机号：</label>
                        <input type="text" class="list-input" id="userPhone" name="userPhone" lay-verType="tips" lay-verify="required|phone|number" placeholder="请输入手机号" >
                    </div>
                    <div class="login-input">
                        <label><i class="heart">*</i>请设置密码：</label>
                        <input type="password" class="list-input" id="userPassword" name="userPassword" lay-verType="tips" lay-verify="required|userPassword" placeholder="请设置密码" autocomplete="off">
                    </div>
                    <div class="login-input">
                        <label><i class="heart">*</i>请确认密码：</label>
                        <input type="password" class="list-input" id="repsword" name="repsword" lay-verType="tips" lay-verify="required|repsword" placeholder="请确认密码">
                    </div>
                    
                    <div class="login-input">
                        <label><i class="heart">*</i>图形码：</label>
                        <input type="text" class="list-notes" id="code" name="code" lay-verify="required" lay-verType="tips" placeholder="请输入图形码">&nbsp;&nbsp;&nbsp;&nbsp;
                        <a onclick="changeImg()"><img style="margin-left:-105px;" id="codeImage" src="<%=path %>/verify/code/get/150/38" border:1px solid #DBDBDB;"/></a> 
                    </div>
                    <!-- 
                    <div class="login-input">
                        <label><i class="heart">*</i>短信验证码：</label>
                        <input type="text" class="list-notes" id="message" name="message" placeholder="">
                        <a href="#" class="obtain">获取短信验证码</a>
                    </div>
                     -->
                    <div class="item-ifo">
                        <input type="checkbox" onClick="agreeonProtocol();" id="readme" checked="checked" class="checkbox">
                        <label for="protocol">我已阅读并同意<a id="protocol" class="blue" href="#">《过来玩网站用户协议》</a></label>
                        <span class="clr"></span>
                    </div>
                    <div class="login-button" >
                        <a id="submit" style="background: #2BAD4A;" href="javascript:void(0)" lay-filter="add" lay-submit>立即注册</a>
                    </div>
                </form>
                       
            </div>
            	<div class="login-switch clearfix" style="width:auto;color:#333;margin:20px 0;">
          		 	 <p class="fr">我已经注册，现在就 <a href="login.html">登录</a></p>
        			</div>	   	
        </div>
                   
    </div>
    	
</section>

<!--- footer begin-->
<footer id="footer">
    <div class="containers">
        <div class="w" style="padding-top:30px">
            <div id="footer-2013">
                <!-- <div class="links">
                    <a href="">关于我们</a>
                    |
                    <a href="">联系我们</a>
                    |
                    <a href="">人才招聘</a>
                    |
                    <a href="">商家入驻</a>
                    |
                    <a href="">广告服务</a>
                    |
                    <a href="">手机京东</a>
                    |
                    <a href="">友情链接</a>
                    |
                    <a href="">销售联盟</a>
                    |
                    <a href="">English site</a>
                </div> -->
                <!-- <div style="padding-left:10px">
                    <p style="padding-top:10px; padding-bottom:10px; color:#999">网络文化经营许可证：浙网文[2013]0268-027号| 增值电信业务经营许可证：浙B2-20080224-1</p>
                    <p style="padding-bottom:10px; color:#999">信息网络传播视听节目许可证：1109364号 | 互联网违法和不良信息举报电话:0571-81683755</p>
                </div> -->
            </div>
        </div>

    </div>
</footer>
<!-- footer End -->
<script src="<%=path %>/webtheme/theme/js/jquery.js"></script>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
 <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
<script type="text/javascript">
//切换验证码
function changeImg(){
var date=new Date();
	$("#codeImage").attr("src","<%=path%>/verify/code/get/150/38?"+date);
}

	layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form
              ,layer = layui.layer;
			    
			  
			  form.verify({
						  userPassword: function(value){ //value：表单的值、item：表单的DOM对象
						   
						    if(/^\d+\d+\d$/.test(value)){
						      return '密码不能全为数字';
						    }
						    if(/^[\S]{3,6}$/.test(value)){
						      return '密码必须6到12位，且不能出现空格'
						    }
						  }
						  ,repsword: function(value){ //value：表单的值、item：表单的DOM对象
						   
						    if(/^\d+\d+\d$/.test(value)){
						      return '密码不能全为数字';
						    }
						    if(/^[\S]{3,6}$/.test(value)){
						      return '密码必须6到12位，且不能出现空格'
						    }
						    
						    var pass = $('#userPassword').val();
						    
						    if(value!=pass){
						   		 return '两次密码不一致'
						    }
						  }
						  
				});    
			  
              //监听提交
              form.on('submit(add)', function(data){
              
              
              
              
                $.ajax({
                	  type:"post",
           			  url:"doRegist",
                      data:data.field,
                      success:function(msg){
                        if(msg=="success"){
                          layer.alert("注册成功！跳转到登录页面...", {icon: 6},function () {
                           // 获得frame索引
                           var index = parent.layer.getFrameIndex(window.name);
                           //关闭当前frame
                           parent.window.location.reload();
                           parent.layer.close(index);
						   doReload()
                           });
                        }else if(msg=="perror") {
                            layer.alert("两次密码输入不一致！");
                        }else if(msg=="cerror") {
                            layer.alert("验证码不正确！");
                        }else {
                            layer.alert("注册失败！");
                        }
                       }
                }) 
              
                return false;
              });
            });
	
</script>
<script type="text/javascript">
function doReload() {
	location.href="login";
}
</script>
</body>
</html>