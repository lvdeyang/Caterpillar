<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>

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
   		  #submit,.member-head{ 
       background: -ms-linear-gradient(top, #D6002A, #B40F02) !important;       /* IE 10 */  
       background:-moz-linear-gradient(top,#D6002A,#B40F02) !important;/*火狐*/   
       background:-webkit-gradient(linear, 0% 0%, 0%100%,from(#D6002A), to(#B40F02)) !important;/*谷歌*/   
       background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#D6002A), to(#B40F02)) !important;      /* Safari 4-5, Chrome 1-9*/
       background: -webkit-linear-gradient(top, #D6002A, #B40F02) !important;  /*Safari5.1 Chrome 10+*/  
       background: -o-linear-gradient(top, #D6002A, #B40F02) !important; /*Opera 11.10+*/  
   		} 
   		   		.search-text{
height:36px !important;
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
    <meta content="过来玩, 旅游, 购物, 美食" name="keywords">
    <meta content="吃住行游购娱一站式服务。" name="description">
	<title>会员中心</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/member.css">
   <style type="text/css">
    .member-head{ 
       background: -ms-linear-gradient(top, #FF5F00, red);       /* IE 10 */  
       background:-moz-linear-gradient(top,#FF5F00,red);/*火狐*/   
       background:-webkit-gradient(linear, 0% 0%, 0%100%,from(#FF5F00), to(red));/*谷歌*/   
       background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#FF5F00), to(red));      /* Safari 4-5, Chrome 1-9*/
       background: -webkit-linear-gradient(top, #FF5F00, red);  /*Safari5.1 Chrome 10+*/  
       background: -o-linear-gradient(top, #FF5F00, red); /*Opera 11.10+*/  
   }
   </style>
   
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

<div class="containers"><div class="pc-nav-item"><a href="#">首页</a> &gt; <a href="#">会员中心 </a> &gt; <a href="#">商城快讯</a></div></div>

<!-- 商城快讯 begin -->
<section id="member">
    <div class="member-center clearfix">
<!-- 左侧菜单 b -->
        <c:import url="${path}/user/memberLeft"></c:import>
<!-- 左侧菜单 e --> 
        <div class="member-right fr">
            <div class="member-head">
                <div class="member-heels fl"><h2>我的账户</h2></div>
            </div>
            <div class="member-border">
               <div class="member-secure clearfix" style="width:1200px;">
                   <div class="member-extent fl" style="margin-left:25px;">
                       <h2 class="fl">安全级别</h2>
                       <ul class="fl">
                           <li class="on"></li>
                           <li class="on"></li>
                           <li class="on"></li>
                           <li class="on"></li>
                           <li class="on"></li>
                           <li class="on"></li>
                           <li class="on"></li>
                           <li class="on1"><a href="#"></a></li>
                           <li class="on2"><a href="#"></a></li>
                           <li class="on3"><a href="#"></a></li>
                       </ul>
                       <span class="fl">较高</span>
                   </div>
                   <div class="fr reds"><p> * 建议您开启全部安全设置，以保障您的账户及资金安全</p></div>
                   <div class="member-caution clearfix" style="float:left;">
                   <ul>
                       
                       <li class="clearfix">
                           <div class="warn1"></div>
                           <div class="warn2">购物车</div>
                           <div class="warn3">结算未支付订单以及查看历史订单内容  </div>
                           <div class="warn4"><a href="<%=path %>/user/order/list/1">查看</a> </div>
                       </li>
                       
                       <li class="clearfix">
                           <div class="warn1"></div>
                           <div class="warn2">登录密码</div>
                           <div class="warn3">互联网账号存在被盗风险，建议您定期更改密码以保护账户安全。</div>
                           <div class="warn4"><a href="<%=path %>/user/mypwd">修改</a> </div>
                       </li>
                      <!--  <li class="clearfix">
                           <div class="warn1"></div>
                           <div class="warn2">手机验证</div>
                           <div class="warn3">您验证的手机： <i class="reds">134*****693</i>   若已丢失或停用，请立即更换，<i class="reds">避免账户被盗</i></div>
                           <div class="warn5"><p>解绑请咨询搜小悦官方客服 <i>souyue@zhongsou.com  </i></p></div>
                       </li> -->
                      <!--  <li class="clearfix">
                           <div class="warn6"></div>
                           <div class="warn2">支付密码</div>
                           <div class="warn3">安全程度：  建议您设置更高强度的密码。</div>
                           <div class="warn5"><a href="#">支付密码管理</a></div>
                       </li> -->
                   </ul>
                   <!-- <div class="member-prompt">
                       <p>安全提示：</p>
                       <p>您当前IP地址是：<i class="reds">110.106.0.01</i>  北京市          上次登录的TP： 2015-09-16  <i class="reds">110.106.0.02 </i> 天津市</p>
                       <p>1. 注意防范进入钓鱼网站，不要轻信各种即时通讯工具发送的商品或支付链接，谨防网购诈骗。</p>
                       <p>2. 建议您安装杀毒软件，并定期更新操作系统等软件补丁，确保账户及交易安全。      </p>
                   </div> -->
               </div>
               </div>
               
            </div>
        </div>
    </div>
</section>
<!-- 商城快讯 End -->

<!--- footer begin-->
<c:import url="${path}/web/foot"></c:import>
<!-- footer End -->
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/head.js"></script>
</body>
</html>