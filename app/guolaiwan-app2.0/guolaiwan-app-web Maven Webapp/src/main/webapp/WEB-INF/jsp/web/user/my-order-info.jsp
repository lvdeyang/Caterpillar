<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <div class="member-backs fr"><a href="#">返回订单首页</a></div>
            </div>
            <div class="member-border">
               <div class="member-order">
                  
                   <dl class="member-custom clearfix ">
                       <dt>订单信息</dt>
                       <dd>订单编号：${order.orderNO}</dd>
                       <dd>订单金额：￥${order.orderAllMoney}</dd>
                       <dd>付款/订单？时间：${order.createDate}</dd>
                       <dd>发货时间？：2015-09-22 08：22</dd>
                   </dl>
                   
                   <dl>
                       <dt>商品信息</dt>
                       <dd class="member-seller">本订单是由 “以纯甲醇旗舰店” 发货并且提高售后服务，商品在下单后会尽快给您发货。 </dd>
                   </dl>
               </div>
               <div class="member-serial">
                   <ul>
                       <li class="clearfix">
                           <div class="No1">商品编号</div>
                           <div class="No2">商品详情</div>
                           <div class="No3">数量</div>
                           <div class="No4">单价</div>
                           <div class="No5">小计</div>
                       </li>
                       <li class="clearfix">
                           <div class="No1"></div>
                           <div class="No2"><a href="#">${order.productName}</a> </div>
                           <div class="No3">${order.productNum}</div>
                           <div class="No4">￥${order.productPrice}</div>
                           <div class="No5">￥???</div>
                       </li>
                       <li class="clearfix">
                           <div class="No1">908756</div>
                           <div class="No2"><a href="#">现货包邮小米手环充电线 小米手环充电器 小米手环数据线 有腕带</a> </div>
                           <div class="No3">2</div>
                           <div class="No4">￥78.00</div>
                           <div class="No5">￥99.00</div>
                       </li>
                   </ul>
               </div>
            </div>
            <div class="member-settle clearfix">
                <div class="fr">
                    <div><span>商品金额：</span><em>￥${orderAllMoney}</em></div>
                    <div><span>运费：</span><em>￥270.00</em></div>
                    <div class="member-line"></div>
                    <div><span>共需支付：</span><em>￥280.00</em></div>
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