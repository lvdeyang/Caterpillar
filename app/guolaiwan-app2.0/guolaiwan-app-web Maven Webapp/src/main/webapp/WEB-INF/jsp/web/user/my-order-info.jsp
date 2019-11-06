<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
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
  select{
   height:35px;
   line-height: 35px;
  }
</style>
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
    <meta content="歪秀购物, 购物, 大家电, 手机" name="keywords">
    <meta content="歪秀购物，购物商城。" name="description">
	<title>会员系统我的订单</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/member.css">
	<script type="text/javascript" src="<%=path %>/webtheme/theme/js/jquery.js"></script>
     <script>
         $(function(){

             $("#H-table li").each(function(i){
                 $(this).click((function(k){
                     var _index = k;
                     return function(){
                         $(this).addClass("cur").siblings().removeClass("cur");
                         $(".H-over").hide();
                         $(".H-over:eq(" + _index + ")").show();
                     }
                 })(i));
             });
             $("#H-table1 li").each(function(i){
                 $(this).click((function(k){
                     var _index = k;
                     return function(){
                         $(this).addClass("cur").siblings().removeClass("cur");
                         $(".H-over1").hide();
                         $(".H-over1:eq(" + _index + ")").show();
                     }
                 })(i));
             });
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
                <div class="member-heels fl"><h2>订单号：${order.orderNO}</h2></div>
                <!-- <div class="member-backs fr"><a href="#">返回订单首页</a></div> -->
            </div>
            <div class="member-border">
               <div class="member-order">
                  
                   <dl class="member-custom clearfix ">
                       <dt>订单信息</dt>
                       <dd>订单编号：${order.orderNO}</dd>
                       <dd>订单金额：￥${order.orderAllMoney}</dd>
                       <dd>付款/订单时间：${order.createDate}</dd>
                   </dl>
               </div>
               <div class="member-serial">
                   <ul>
                       <li class="clearfix">
                           <div class="No1" style="width:250px;">商户名称</div>
                           <div class="No2" style="width:250px;">商品详情</div>
                           <div class="No3">数量</div>
                           <div class="No4">单价</div>
                           <div class="No5">小计</div>
                       </li>
                       <li class="clearfix">
                           <div class="No1" style="width:250px;">${order.shopName}</div>
                           <div class="No2" style="width:250px;"><a href="#">${order.productName}</a> </div>
                           <div class="No3">${order.productNum}</div>
                           <div class="No4">￥${order.productPrice}</div>
                           <div class="No5">￥${order.payMoney}</div>
                       </li>
                      
                   </ul>
               </div>
            </div>
            <c:if test="${order.orderState != '未付款'}">
                 <div style="position: absolute;right:345px;top:380px;text-align: center;">
	            <image style="margin:0 auto;width:200px;height:200px;" src="<%=path%>/file/${order.ydNO}"/>
	            <p style="margin:10px 0;color:red;font-weight: bold;">亲，需要截图或拍照上方二维码现场验单呦~~</p>
	            </div>
            </c:if>
            
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