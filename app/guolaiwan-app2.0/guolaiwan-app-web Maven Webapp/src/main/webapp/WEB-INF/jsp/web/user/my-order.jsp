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
    <meta content="购物, 大家电, 手机" name="keywords">
    <meta content="购物商城。" name="description">
	<title>我的订单</title>
        <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/base.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/home.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/member.css">
    <script type="text/javascript" src="<%=path %>/webtheme/theme/js/jquery.js"></script>
     <script>
     
     	var basePath = "<%=basePath%>";
     	
         $(function(){
           
             $("#H-table li").each(function(i){
             
                 $(this).click((function(k){
                     var _index = k;
                     return function(){
                        
                         $(".H-over").hide();
                         $(".H-over:eq(" + _index + ")").show();
                         //根据index获取类型，根据类型取对应数据，重新刷新页面
                         location.href="<%=path%>/user/order/list/"+_index; 
                         	
                     }
                 })(i));
             });

            var index = '${index}';
            
            $("#stab${state}").attr("class","cur")

         });
         function delOrder(order){
         	if(confirm("确定要删除此订单吗？")){
         		$.ajax({
         			type:"post",
         			data:{"order":order},
         			url:basePath+"user/order/delete",
         			success:function(msg){
						if(msg=='success'){
							alert("删除成功！");
							window.location.reload();
						
						}
         			}
         		});
         	}
         }
     </script>
     <style type="text/css">
     	.member-circle .ci0{width:20px; float:left; height:168px; padding-top:78px; border-right:1px solid #e0e0e0; box-sizing:border-box; text-align:center;}
     	.member-circle .ci1.with-checkbox{width:370px !important;}
     	
     	/* 结算全部 */
     	.count-all-info{height:42px; line-height:42px; text-align:right; border:1px solid #e0e0e0; font-size:16px;}
     	.count-all-info label, 
     	.count-all-info #total-price{margin-right:50px;}
     	.count-all-info #count-all{height:100%; width:100px; display:inline-block; background-color:#ea4949; text-align:center; color:#fff; cursor:pointer; border:0;}
    
       #count-all,.member-head{ 
       background: -ms-linear-gradient(top, #D6002A, #B40F02) !important;       /* IE 10 */  
       background:-moz-linear-gradient(top,#D6002A,#B40F02) !important;/*火狐*/   
       background:-webkit-gradient(linear, 0% 0%, 0%100%,from(#D6002A), to(#B40F02)) !important;/*谷歌*/   
       background: -webkit-gradient(linear, 0% 0%, 0% 100%,from(#D6002A), to(#B40F02)) !important;      /* Safari 4-5, Chrome 1-9*/
       background: -webkit-linear-gradient(top, #D6002A, #B40F02) !important;  /*Safari5.1 Chrome 10+*/  
       background: -o-linear-gradient(top, #D6002A, #B40F02) !important; /*Opera 11.10+*/  
   		}   
   		.member-whole ul .cur a{
   		  color:#FF4401;
   		  font-weight: bold;
         
   		}
   		
   		.member-circle .ci7 .gr3{
   		padding-top:5px;
   		}
   		.member-whole,.member-whole ul li a{
   		background:#DADADA;
   		}
   		.member-left{
   		 margin-top:53px;
   		}
   		.member-apart{
   		  padding:0;
   		  height:50px;
   		  line-height: 25px;
   		  width:208px;
   		  background: #fff;
   		}
   		.member-apart img{
   		  width:35px;
   		  height:35px;
   		  margin:7.5px 0 0 20px;
   		}
   		.pc-search-g .pc-search-u:hover{
   		  background: #C40000 !important;
   		  border: 1px solid #C40000 !important;
   		}
     </style>
 </head>
 <body>

<!--- header begin-->
<c:import url="${path}/web/top"></c:import>
<!-- header End -->

<div class="containers"><div class="pc-nav-item"><a href="#">首页</a> &gt; <a href="#">会员中心 </a> &gt; <a href="#">我的订单</a></div></div>

<!-- 商城快讯 begin -->
<section id="member">
    <div class="member-center clearfix">
        <!-- 左侧菜单 b -->
        	<c:import url="${path}/user/memberLeft"></c:import>
<!-- 左侧菜单 e --> 
        <div class="member-right fr">
            <div class="member-head">
                <div class="member-heels fl"><h2>我的购物车</h2></div>
                <!-- <div class="member-backs member-icons fr"><a href="#">搜索</a></div>
                <div class="member-about fr"><input type="text" placeholder="商品名称/商品编号/订单编号"></div> -->
            </div>
            <div class="member-whole clearfix">
                <ul id="H-table" class="H-table">
                    <li id="stab0"><a href="#">全部订单</a></li>
                    <li id="stab1"><a href="#">未付款</a></li>
                    <li id="stab2"><a href="#">已付款</a></li>
                    <li id="stab3"><a href="#">已验单</a></li>
                    <li id="stab4"><a href="#">申请退款</a></li>
                    <li id="stab5"><a href="#">退款失败</a></li>
                    <li id="stab6"><a href="#">退款成功</a></li>
                </ul>
            </div>
            <div class="member-border">
               <div class="member-return H-over">
                   <div class="member-cancel clearfix">
                       <span class="be1">订单信息</span>
                       <span class="be2">收货人</span>
                       <span class="be2">订单金额</span>
                       <span class="be2">订单时间</span>
                       <span class="be2">订单状态</span>
                       <span class="be2">订单操作</span>
                   </div>
                   <div class="member-sheet clearfix">
                       <ul>
                           <c:forEach items="${orders}" var="order">
                           <li>
                               <div class="member-minute clearfix">
                                   <span>${order.createDate}</span>
                                   <span>订单号：<em>${order.orderNO}</em></span>
                                  
                                   <span><a href="#">商家名称:${order.shopName}</a></span>
                                   <span class="member-custom">支付方式：<em>${order.payMode}</em></span>
                               </div>
                               <div class="member-circle clearfix">
                               	   <c:if test="${state == 1}">
                                   		<div class="ci0"><input type="checkbox" checked="checked" data-money="${order.orderAllMoney}" data-uuid="${order.uuid}"/></div>
                                   		<div class="ci1 with-checkbox">
                                   </c:if>
                                   <c:if test="${state != 1}">
                                   	<div class="ci1">
                                   </c:if>
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="${order.productPic}" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">产品名称:${order.productName}</a></span>
                                           <span class="gr3">X${order.productNum}</span>
                                       </div>
                                   </div>
                                   <div class="ci2" >${order.userInfo}</div>
                                   <div class="ci3"><b>￥${order.orderAllMoney}</b><p>${order.payMode}</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>${order.createDate}</p></div>
                                   <div class="ci5"><p>${order.orderState}</p> <!-- <p><a href="#">物流跟踪</a></p> --> <%-- <p><a href="<%=path %>/user/order/info?orderNO=${order.orderNO}">订单详情</a></p> --%></div>
                                   <div class="ci5 ci8"><!-- <p>剩余15时20分</p> --> <p><a href="<%=path %>/user/order/submit?order=${order.uuid}" class="member-touch">立即支付</a> </p> <p><a href="javascript:delOrder('${order.uuid}')">取消订单</a> </p></div>
                               </div>
                           </li>
                          </c:forEach>
                       </ul>
                   </div>
               </div>
               <%-- <div class="member-return H-over" style="display:none;">
                   <div class="member-cancel clearfix">
                       <span class="be1">订单信息</span>
                       <span class="be2">收货人</span>
                       <span class="be2">订单金额</span>
                       <span class="be2">订单时间</span>
                       <span class="be2">订单状态</span>
                       <span class="be2">订单操作</span>
                   </div>
                   <div class="member-sheet clearfix">
                       <ul>
                           <c:forEach items="${orders}" var="order">
                           <li>
                               <div class="member-minute clearfix">
                                   <span>${order.createDate}</span>
                                   <span>订单号：<em>${order.orderNO}</em></span>
                                   <span>验单号：<em>${order.ydNO}</em></span>
                                   <span><a href="#">商家名称:${order.shopName}</a></span>
                                   <span class="member-custom">支付方式：<em>${order.payMode}</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="${order.productPic}" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">产品名称:${order.productName}</a></span>
                                           <span class="gr3">X${order.productNum}</span>
                                       </div>
                                       
                                   </div>
                                   <div class="ci2" >${order.userInfo}</div>
                                   <div class="ci3"><b>￥${order.orderAllMoney}</b><p>${order.payMode}</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>${order.createDate}</p></div>
                                   <div class="ci5"><p>${order.orderState}</p> <!-- <p><a href="#">物流跟踪</a></p> --> <p><a href="<%=path %>/user/order/info?orderNO=${order.orderNO}">订单详情</a></p></div>
                                   <div class="ci5 ci8"><!-- <p>剩余15时20分</p> --> <p><a href="<%=path %>/user/order/submit?order=${order.uuid}" class="member-touch">立即支付</a> </p> <p><a href="#">取消订单</a> </p></div>
                               </div>
                           </li>
                          </c:forEach>
                       </ul>
                   </div>
               </div>
               <div class="H-over member-over" style="display:none;">
               		
               		<div class="member-cancel clearfix">
                       <span class="be1">订单信息</span>
                       <span class="be2">收货人</span>
                       <span class="be2">订单金额</span>
                       <span class="be2">订单时间</span>
                       <span class="be2">订单状态</span>
                       <span class="be2">订单操作</span>
                   </div>
                   <div class="member-sheet clearfix">
                       <ul>
                           <c:forEach items="${orders}" var="order">
                           <li>
                               <div class="member-minute clearfix">
                                   <span>${order.createDate}</span>
                                   <span>订单号：<em>${order.orderNO}</em></span>
                                   <span>验单号：<em>${order.ydNO}</em></span>
                                   <span><a href="#">商家名称:${order.shopName}</a></span>
                                   <span class="member-custom">支付方式：<em>${order.payMode}</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="${order.productPic}" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">产品名称:${order.productName}</a></span>
                                           <span class="gr3">X${order.productNum}</span>
                                       </div>
                                       
                                   </div>
                                   <div class="ci2" >${order.userInfo}</div>
                                   <div class="ci3"><b>￥${order.orderAllMoney}</b><p>${order.payMode}</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>${order.createDate}</p></div>
                                   <div class="ci5"><p>${order.orderState}</p> <p><a href="#">物流跟踪</a></p> <p><a href="<%=path %>/user/order/info?orderNO=${order.orderNO}">订单详情</a></p></div>
                                   <div class="ci5 ci8"><p>剩余15时20分</p> <p><a href="<%=path %>/user/order/submit?order=${order.uuid}" class="member-touch">立即支付</a> </p> <p><a href="#">取消订单</a> </p></div>
                               </div>
                           </li>
                          </c:forEach>
                       </ul>
                   </div>
               </div>
               <div class="H-over member-over" style="display:none;">
               		
               		<div class="member-cancel clearfix">
                       <span class="be1">订单信息</span>
                       <span class="be2">收货人</span>
                       <span class="be2">订单金额</span>
                       <span class="be2">订单时间</span>
                       <span class="be2">订单状态</span>
                       <span class="be2">订单操作</span>
                   </div>
                   <div class="member-sheet clearfix">
                       <ul>
                           <c:forEach items="${orders}" var="order">
                           <li>
                               <div class="member-minute clearfix">
                                   <span>${order.createDate}</span>
                                   <span>订单号：<em>${order.orderNO}</em></span>
                                   <span>验单号：<em>${order.ydNO}</em></span>
                                   <span><a href="#">商家名称:${order.shopName}</a></span>
                                   <span class="member-custom">支付方式：<em>${order.payMode}</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="${order.productPic}" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">产品名称:${order.productName}</a></span>
                                           <span class="gr3">X${order.productNum}</span>
                                       </div>
                                       
                                   </div>
                                   <div class="ci2" >${order.userInfo}</div>
                                   <div class="ci3"><b>￥${order.orderAllMoney}</b><p>${order.payMode}</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>${order.createDate}</p></div>
                                   <div class="ci5"><p>${order.orderState}</p> <p><a href="#">物流跟踪</a></p> <p><a href="<%=path %>/user/order/info?orderNO=${order.orderNO}">订单详情</a></p></div>
                                   <div class="ci5 ci8"><p>剩余15时20分</p> <p><a href="<%=path %>/user/order/submit?order=${order.uuid}" class="member-touch">立即支付</a> </p> <p><a href="#">取消订单</a> </p></div>
                               </div>
                           </li>
                          </c:forEach>
                       </ul>
                   </div>
               </div>
               <div class="H-over member-over" style="display:none;">
               	
               		<div class="member-cancel clearfix">
                       <span class="be1">订单信息</span>
                       <span class="be2">收货人</span>
                       <span class="be2">订单金额</span>
                       <span class="be2">订单时间</span>
                       <span class="be2">订单状态</span>
                       <span class="be2">订单操作</span>
                   </div>
                   <div class="member-sheet clearfix">
                       <ul>
                           <c:forEach items="${orders}" var="order">
                           <li>
                               <div class="member-minute clearfix">
                                   <span>${order.createDate}</span>
                                   <span>订单号：<em>${order.orderNO}</em></span>
                                   <span>验单号：<em>${order.ydNO}</em></span>
                                   <span><a href="#">商家名称:${order.shopName}</a></span>
                                   <span class="member-custom">支付方式：<em>${order.payMode}</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="${order.productPic}" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">产品名称:${order.productName}</a></span>
                                           <span class="gr3">X${order.productNum}</span>
                                       </div>
                                       
                                   </div>
                                   <div class="ci2" >${order.userInfo}</div>
                                   <div class="ci3"><b>￥${order.orderAllMoney}</b><p>${order.payMode}</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>${order.createDate}</p></div>
                                   <div class="ci5"><p>${order.orderState}</p> <p><a href="#">物流跟踪</a></p> <p><a href="<%=path %>/user/order/info?orderNO=${order.orderNO}">订单详情</a></p></div>
                                   <div class="ci5 ci8"><p>剩余15时20分</p> <p><a href="<%=path %>/user/order/submit?order=${order.uuid}" class="member-touch">立即支付</a> </p> <p><a href="#">取消订单</a> </p></div>
                               </div>
                           </li>
                          </c:forEach>
                       </ul>
                   </div>
               </div>
               <div class="H-over member-over" style="display:none;">
               		
               		<div class="member-cancel clearfix">
                       <span class="be1">订单信息</span>
                       <span class="be2">收货人</span>
                       <span class="be2">订单金额</span>
                       <span class="be2">订单时间</span>
                       <span class="be2">订单状态</span>
                       <span class="be2">订单操作</span>
                   </div>
                   <div class="member-sheet clearfix">
                       <ul>
                           <c:forEach items="${orders}" var="order">
                           <li>
                               <div class="member-minute clearfix">
                                   <span>${order.createDate}</span>
                                   <span>订单号：<em>${order.orderNO}</em></span>
                                   <span>验单号：<em>${order.ydNO}</em></span>
                                   <span><a href="#">商家名称:${order.shopName}</a></span>
                                   <span class="member-custom">支付方式：<em>${order.payMode}</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="${order.productPic}" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">产品名称:${order.productName}</a></span>
                                           <span class="gr3">X${order.productNum}</span>
                                       </div>
                                       
                                   </div>
                                   <div class="ci2" >${order.userInfo}</div>
                                   <div class="ci3"><b>￥${order.orderAllMoney}</b><p>${order.payMode}</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>${order.createDate}</p></div>
                                   <div class="ci5"><p>${order.orderState}</p> <p><a href="#">物流跟踪</a></p> <p><a href="<%=path %>/user/order/info?orderNO=${order.orderNO}">订单详情</a></p></div>
                                   <div class="ci5 ci8"><p>剩余15时20分</p> <p><a href="<%=path %>/user/order/submit?order=${order.uuid}" class="member-touch">立即支付</a> </p> <p><a href="#">取消订单</a> </p></div>
                               </div>
                           </li>
                          </c:forEach>
                       </ul>
                   </div>
               </div>
               <div class="H-over member-over" style="display:none;">
               		
               		
               </div>
               <div class="H-over member-over" style="display:none;">
               	
               		<li>
                               <div class="member-minute clearfix">
                                   <span>2015-09-22 18:22:33</span>
                                   <span>订单号：<em>98653056821</em></span>
                                   <span><a href="#">以纯甲醇旗舰店</a></span>
                                   <span class="member-custom">客服电话：<em>010-6544-0986</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="theme/img/pd/m1.png" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">红米Note2 标准版 白色 移动4G手机 双卡双待</a></span>
                                           <span class="gr3">X1</span>
                                       </div>
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="theme/img/pd/m2.png" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">AXON天机mini NBA限量版</a></span>
                                           <span class="gr3">X9</span>
                                       </div>
                                   </div>
                                   <div class="ci2" >张子琪</div>
                                   <div class="ci3"><b>￥120.00</b><p>货到付款</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>2015-09-22</p></div>
                                   <div class="ci5"><p>已申请退货</p> <p><a href="#">退货日志</a></p></div>
                                   <div class="ci6"><p><a href="#">取消退货</a> </p></div>
                               </div>
                           </li>
                           <li>
                               <div class="member-minute clearfix">
                                   <span>2015-09-22 18:22:33</span>
                                   <span>订单号：<em>98653056821</em></span>
                                   <span><a href="#">以纯甲醇旗舰店</a></span>
                                   <span class="member-custom">客服电话：<em>010-6544-0986</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="theme/img/pd/m1.png" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">红米Note2 标准版 白色 移动4G手机 双卡双待</a></span>
                                           <span class="gr3">X1</span>
                                       </div>
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="theme/img/pd/m2.png" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">AXON天机mini NBA限量版</a></span>
                                           <span class="gr3">X9</span>
                                       </div>
                                   </div>
                                   <div class="ci2" >张子琪</div>
                                   <div class="ci3"><b>￥120.00</b><p>货到付款</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>2015-09-22</p></div>
                                   <div class="ci5"><p>已完成</p> <p><a href="#">订单详情</a></p></div>
                                   <div class="ci6"><p><a href="#">取消退货</a> </p></div>
                               </div>
                           </li>
                           <li>
                               <div class="member-minute clearfix">
                                   <span>2015-09-22 18:22:33</span>
                                   <span>订单号：<em>98653056821</em></span>
                                   <span><a href="#">以纯甲醇旗舰店</a></span>
                                   <span class="member-custom">客服电话：<em>010-6544-0986</em></span>
                               </div>
                               <div class="member-circle clearfix">
                                   <div class="ci1">
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="theme/img/pd/m1.png" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">红米Note2 标准版 白色 移动4G手机 双卡双待</a></span>
                                           <span class="gr3">X1</span>
                                       </div>
                                       <div class="ci7 clearfix">
                                           <span class="gr1"><a href="#"><img src="theme/img/pd/m2.png" width="60" height="60" title="" about=""></a></span>
                                           <span class="gr2"><a href="#">AXON天机mini NBA限量版</a></span>
                                           <span class="gr3">X9</span>
                                       </div>
                                   </div>
                                   <div class="ci2" >张子琪</div>
                                   <div class="ci3"><b>￥120.00</b><p>货到付款</p><p class="iphone">手机订单</p></div>
                                   <div class="ci4"><p>2015-09-22</p></div>
                                   <div class="ci5"><p>已完成</p> <p><a href="#">订单详情</a></p></div>
                                   <div class="ci6"><p><a href="#">取消退货</a> </p></div>
                               </div>
                           </li>
               </div> --%>

                <div class="clearfix" style="padding:30px 20px;">
                	${pages}
                    <!-- <div class="fr pc-search-g pc-search-gs">
                        <a style="display:none" class="fl " href="#">上一页</a>
                        <a href="#" class="current">1</a>
                        <a href="javascript:;">2</a>
                        <a href="javascript:;">3</a>
                        <a href="javascript:;">4</a>
                        <a href="javascript:;">5</a>
                        <a href="javascript:;">6</a>
                        <a href="javascript:;">7</a>
                        <span class="pc-search-di">…</span>
                        <a href="javascript:;">1088</a>
                        <a title="使用方向键右键也可翻到下一页哦！" class="" href="javascript:;">下一页</a>
                    </div> -->
                </div>
            </div>
            <c:if test="${state==1}">
				<div class="count-all-info">
					<label for="check-all"><input id="check-all" type="checkbox" /> 全选</label>
					<span>合计：￥</span><span id="total-price"></span>
					<button id="count-all">去结算</button>
					<form id="count-all-form" action="<%=path %>/user/order/submit/batch" method="POST"></form>
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
<script type="text/javascript">
	$(function(){
		var count_result = [];
		
		var count_all = function(doCheckAll){
			var $form = $('#count-all-form').empty();
			var checkall = true;
			var total = 0.00;
			var totalInfo = [];
			$('.ci0').each(function(){
				var $checkbox = $(this).find('input');
				if($checkbox.is(':checked')){
					var money = parseFloat($checkbox.data('money'))
					total += money;
					totalInfo.push({
						uuid:$checkbox.data('uuid'),
						money:money
					});
				}else{
					checkall = false;
				}
			});
			
			if(doCheckAll !== false){
				if(checkall){
					$('#check-all')[0].checked = true;
				}else{
					$('#check-all')[0].checked = false;
				}
			}
			
			$('#total-price').text(total);
			
			//加入隐藏域
			for(var i=0; i<totalInfo.length; i++){
				$form.append('<input type="hidden" name="orderUuids[]" value="'+totalInfo[i].uuid+'" />');
			}
			
			return totalInfo;
		}; 
		
		//初始化总账
		count_result = count_all();
		
		//绑定事件
		$('.ci0 input').on('change', function(){
			count_result = count_all();
		});
		
		$('#check-all').on('change', function(){
			var $checkbox = $(this);
			if($checkbox.is(':checked')){
				$('.ci0 input').each(function(){this.checked = true;});
			}else{
				$('.ci0 input').each(function(){this.checked = false;});
			}
			count_result = count_all(false);
		});
		
		//结算
		$('#count-all').on('click', function(){
			if(!count_result || count_result.length<=0){
				alert('没有内容可以结算！');
			}else{
				$('#count-all-form')[0].submit();
			}
		});
	});
</script>
</body>
</html>