<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
	<meta name="renderer" content="webkit">
	<title>立即登录</title>
    <link rel="stylesheet" href="<%=path %>/webtheme/theme/css/base.css">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/login.css">
    <script src="<%=path %>/webtheme/theme/js/jquery-3.1.1.min.js"></script>
    <script src="<%=path %>/webtheme/theme/js/checkcode.js"></script>
</head>
<style>
.coagent .regist-link a{
color:#333;

}
.login-wrap .login-banner{
  height:690px;
 background-size:100%  !important;
}
.login-wrap .login-form{
top:120px;
margin-right:-60px;
}
.login-form{
background: rgba(157,157,159,0.6);
}
.login-form p,a{
color:#fff !important;
}
#logo{
 height:90px;
}
</style>
<body style="background: #E5E5E5;">
<div class="w">
	<div id="logo">
		<a href="<%=path %>/index">
			<img src="<%=path %>/webtheme/theme/icon/logo.png" alt="">
		</a>
		<b></b>
	</div>

	<!-- <a href="#" class="q_link fr">
		<b class="fl"></b>
		登录页面，改进意见
	</a> -->
</div>
<div id="content">
  <div class="login-wrap">
  	<div class="w">
  		<div class="login-form" >
  			<!-- <div class="login-tab login-tab-l">
  				<a href="javascript:;">扫码登录</a>
  			</div> -->
  			<div class="login-tab login-tab-r"  style="width:100%;border-bottom: 1px solid #fff4f4;"> 
  				<a style="border:none;" href="javascript:;">账号登录</a>
  			</div>
  			<div class="login-box" style="visibility: visible; display:block">
  			  <div class="mt tab-h"></div>
  			  <div class="msg-wrap"></div>
  			  <div class="mc">
  			  	<div class="form">
  			  		<form action="" class="layui-form">
  			  			<div class="item item-fore1 item-error">
  			  				<label for="loginname" class="login-label name-label"></label>
  			  				<input type="text" name="userPhone" id="userPhone" class="itxt" tabindex="1" autocomplete="off" lay-verType="tips" lay-verify="required|phone|number" placeholder="请输入手机号">
  			  				<span class="clear-btn" style="display:inline;"></span>
  			  			</div>
  			  			<!-- 密码输入框fore2 -->
  			  			<div id="entry" class="item item-fore2" style="visibility: visible">
  			  				<label class="login-label pwd-label" for="nloginpwd"></label>
  			  				<input type="password" name="userPassword" id="userPassword" class="itxt itxt-error" tabindex="2" autocomplete="off" placeholder="请输入密码">
  			  				<span class="clear-btn" style="display: inline;"></span>
  			  				<span class="capslock" style="display: none;">
  			  					<b></b>
  			  					大小写锁定已打开
  			  				</span>
  			  			</div>
  			  			<!-- 图片验证码开始 fore3-->
                        <div id="o-authcode" class="item item-vcode item-fore3 ">
                        	<input type="text" name="code" id="code" class="itxt itxt02" name="authcode" tabindex="3">
                        	<a onclick="changeImg()"><img id="codeImage" src="<%=path %>/verify/code/get/150/38" border:1px solid #DBDBDB;" class="verify-code"/></a>
                        </div>
                        <!-- 自动登录开始fore4 -->
                        <div class="item item-fore4">
                        	<div class="safe">
                        		<span>
                        			<input type="checkbox" name="chkRememberMe" id="autologin" tabindex="3">
                        			<label for>自动登录</label>
                        		</span>
                        		<span class="forget-pw-safe">
                        			<a href="">忘记密码</a>
                        		</span>
                        	</div>
                        </div>
                        <!-- 登录按钮开始 -->
                        <div class="item item-fore5">
                        	<div class="login-btn">
                        	<a style="background: #9D9D9F;box-shadow:5px 5px 10px #8E8F8F;" id="submit" href="javascript:void(0)" class="btn-img btn-entry" lay-filter="add" lay-submit>登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
                        		
                        	</div>
                        </div>
  			  		</form>
  			  	</div>
  			  </div>
  			</div>
  			<div class="qrcode-login">
  				<div class="mc">
  					<div class="qrcode-error-2016">
  						<div class="qrcode-error-mask"></div>
  						<p class="err-cont">服务器出错</p>
  						<a href="javascript:void(0)" class="refresh-btn">刷新</a>
  					</div>
  					<div class="qrcode-main">

  						<div class="qrcode-img" style="">
  							<img src="<%=path %>/webtheme/theme/login/code.png" alt="">
  							<div class="qrcode-error-02 hide" id="J-qrcodeerror" style="display: none;">
  								<a href="#none">
  									<span class="error-icon"></span>
  									<div class="txt">
  									   网络开小差咯
  									   <span class="ml10">刷新二维码</span>
  									 </div>
  								</a>
  							</div>
  						</div>

  						<div class="qrcode-help" style="display: none;"></div>
  					</div>

  					<div class="qrcode-panel">
  						<ul>
  							<li class="fore1">
  								<span>打开</span>
  								<a href="">
  									<span class="red">过来玩平台</span>
  								</a>
  							</li>
  							<li>扫一扫登录</li>
  						</ul>
  					</div>
  				</div>
  			</div>

  			<div class="coagent" id="kbCoagent">
  				<ul>
  					<li class="extra-r">
  					   <div class="regist-link">
  						<a href="register.html" class="">
  							<b></b>立即注册
  						</a>
  					   </div>
  					</li>
  				</ul>
  			</div>
  		</div>
  	</div>
  	<div class="login-banner" style="background: url(<%=path %>/webtheme/theme/login/pchome.jpg) no-repeat;">
  		<div class="w">
  			<div id="banner-bg" class="i-inner" ></div>
  		</div>
  	</div>
  </div>
</div>
<div class="w">
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

</body>
<script src="<%=path %>/webtheme/theme/js/jquery.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
 <script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8">
        </script>
<script type="text/javascript">
var rul="${rul}";
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
						    if(/^[\S]{6,12}$/.test(value)){
						      return '密码必须6到12位，且不能出现空格'
						    }
						  }
						  
						  
				});    
			  
              //监听提交
              form.on('submit(add)', function(data){
              
              
              
              
                $.ajax({
                	  type:"post",
           			  url:"doLogin",
                      data:data.field,
                      success:function(msg){
                      	console.log(msg);
                        if(msg=="userSuccess"){
                        	layer.alert("登录成功！",function(){
                        		if(rul==""){
	                        		window.location.href="myuser";
	                         	}else{
	                         		window.location.href=rul;
	                         	}
                        	});
                        }else if(msg=="merSuccess"){
                        	layer.alert("商户登录成功！",function(){
                        		if(rul==""){
	                        		window.location.href="myuser";
	                         	}else{
	                         		window.location.href=rul;
	                         	}
                        	});
                        	
                        }else if(msg=="codeerror") {
                            layer.alert("验证码错误！");
                            changeImg()
                        }else if(msg=="phoneerror") {
                            layer.alert("手机号或密码错误！");
                            changeImg()
                        }else{
                            layer.alert("手机号或密码错误！");
                            changeImg()
                        }
                       }
                }) 
              
                return false;
              });
            });
	
</script>
</html>
