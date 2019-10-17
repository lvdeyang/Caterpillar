<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style> 
  #submit,.member-head{ 
       background: -ms-linear-gradient(top, #D6002A, #B40F02) !important;       /* IE 10 */  
       background:-moz-linear-gradient(top,#D6002A,#B40F02) !important;/*火狐*/   
       background:-webkit-gradient(linear, 0% 0%, 0%100%,from(#D6002A), to(#B40F02)) !important;/*谷歌*/   
       background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#D6002A), to(#B40F02)) !important;      /* Safari 4-5, Chrome 1-9*/
       background: -webkit-linear-gradient(top, #D6002A, #B40F02) !important;  /*Safari5.1 Chrome 10+*/  
       background: -o-linear-gradient(top, #D6002A, #B40F02) !important; /*Opera 11.10+*/  
   		}   
		.member-left{
   		 margin-top:53px !important;
   		}
   		.member-apart{
   		  padding:0 !important;
   		  height:50px !important;
   		  line-height: 25px !important; 
   		  width:208px !important;
   		  background: #fff !important;
   		}
   		.member-apart img{
   		  width:35px !important;
   		  height:35px !important;;
   		  margin:7.5px 0 0 20px !important;
   		}
   		.login-input{
   		width:auto !important;
   		padding-left:90px !important;
   		margin:25px auto !important;
   		text-align: center;
   		
   		}
   		input[type=password]:focus {
   		border: 1px solid #C40000 !important;
   		}
   		.login-button a{
   		 width:250px !important;
   		}
   		.search-text{
height:36px !important;
}
.pullDownList{
 display: none !important;
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
    <meta content="歪秀购物, 购物, 大家电, 手机" name="keywords">
    <meta content="歪秀购物，购物商城。" name="description">
	<title>会员中心</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/member.css">
     <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/login.css">
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/jquery.js"></script>
    <script type="text/javascript">
         (function(a){
             a.fn.hoverClass=function(b){
                 var a=this;
                 a.each(function(c){
                     a.eq(c).hover(function(){
                         $(this).addClass(b)
                     },function(){
                         $(this).removeClass(b)
                     })
                 });
                 return a
             };
         })(jQuery);

         $(function(){
             $("#pc-nav").hoverClass("current");
         });
     </script>

 </head>
 <body>

<!--- header begin-->
<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<div class="containers"><div class="pc-nav-item"><a href="#">首页</a> &gt; <a href="#">会员中心 </a> &gt; <a href="#">账户安全</a></div></div>

<!-- 商城快讯 begin -->
<section id="member">
    <div class="member-center clearfix">
<!-- 左侧菜单 b -->
        <c:import url="${path}/user/memberLeft"></c:import>
<!-- 左侧菜单 e --> 
        <div class="member-right fr">
            <div class="member-head">
                <div class="member-heels fl"><h2>密码修改</h2></div>
            </div>

                 <div class="login-back">
            <div class="H-over">
                <form action="" class="layui-form">
                    <div class="login-input">
                        <label><i class="heart">*</i>原密码：</label>
                        <input type="password" class="list-input1" id="userPassword" name="userPassword" placeholder="请您输入原密码">
                    </div>
                    <div class="login-input">
                        <label><i class="heart">*</i>请设置密码：</label>
                        <input type="password" class="list-input" id="newPassword" name="newPassword" lay-verType="tips" lay-verify="required|newPassword" placeholder="请设置新密码">
                    </div>
                    <div class="login-input">
                        <label><i class="heart">*</i>请确认密码：</label>
                        <input type="password" class="list-input" id="repsNewPwd" name="repsNewPwd" lay-verType="tips" lay-verify="required|repsNewPwd" placeholder="请确认密码">
                    </div>
                    <!-- 
                    <div class="login-input">
                        <label><i class="heart">*</i>手机号：</label>
                        <input type="text" class="list-iphone" id="iphone" name="info[password]" placeholder="">
                        <a href="#" class="obtain">获取短信验证码</a>
                    </div>
                    <div class="login-input">
                        <label><i class="heart">*</i>短信验证码：</label>
                        <input type="text" class="list-notes" id="message" name="info[password]" placeholder="">
                    </div>
                     -->
                    <div class="login-button">
                        <a id="submit" href="javascript:void(0)" lay-filter="add" lay-submit>立即修改</a>
                    </div>
                </form>
            </div>
                    
        </div>
        
      </div>
</section>
<!-- 商城快讯 End -->

<!--- footer begin-->
<c:import url="${path}/web/foot"></c:import>
<!-- footer End -->
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>
<script src="<%=path %>/webtheme/theme/js/jquery.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
layui.use(['form','layer'], function(){
    $ = layui.jquery;
    var form = layui.form
    ,layer = layui.layer;

    // 表单验证
    form.verify({
    newPassword: function(value){ //value：表单的值、item：表单的DOM对象

        if(/^\d+\d+\d$/.test(value)){
            return '密码不能全为数字';
        }
        if(/^[\S]{6,12}$/.test(value)){
            return '密码必须6到12位，且不能出现空格'
        }
    }
    ,repsNewPwd: function(value){ //value：表单的值、item：表单的DOM对象

        if(/^\d+\d+\d$/.test(value)){
            return '密码不能全为数字';
        }
        if(/^[\S]{6,12}$/.test(value)){
            return '密码必须6到12位，且不能出现空格'
        }

        var pass = $('#newPassword').val();

        if(value!=pass){
            return '两次密码不一致'
        }
    }

    });

    //监听提交
    form.on('submit(add)', function(data){
        $.ajax({
            type:"post",
            url:"modifyPwd",
            data:data.field,
            success:function(msg){
                if(msg=="success"){
                    layer.alert("密码修改成功！", {icon: 6},function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.window.location.reload();
                    parent.layer.close(index);
                    });
                }else if(msg=="perror") {
                    layer.alert("两次密码输入不一致！");
                }else if(msg=="cerror") {
                    layer.alert("验证码不正确！");
                }else {
                    layer.alert("密码修改失败！");
                }
            }
        })

        return false;
    });
});
</script>
</html>