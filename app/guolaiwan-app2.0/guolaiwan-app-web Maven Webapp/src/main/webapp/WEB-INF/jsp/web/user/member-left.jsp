<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="member-left fl" style="border: 1px solid #e4eaee;margin:-2px 0 0 -258px;border-radius:2px;overflow: hidden;">
    <div class="member-apart clearfix">
        <div class="fl"><a href="#"><img style="border-radius:50%;" src="<%=path%>/lib/images/logo.png"></a></div>
        <div class="fl" style="padding-left:20px;">
            <p>手机号：</p>
            <p><a href="#">${user.userPhone}</a></p>
           <%--  <p>过来玩ID：</p>
            <p>${user.id}</p> --%>
        </div>
    </div>
    <div class="member-lists" >
        <dl>
        	<dt>我的商城</dt>
            <dd><a href="<%=path %>/user/order/list/0">我的订单</a></dd>
            <!-- <dd><a href="#">我的收藏</a></dd> -->
            <dd><a href="<%=path %>/user/mypwd">账户安全</a></dd>
            <!-- <dd><a href="#">我的评价</a></dd> -->
            <dd><a href="<%=path %>/user/address/list">地址管理</a></dd>
        </dl>
       <!--  <dl>
            <dt>客户服务</dt>
            <dd><a href="#">退货申请</a></dd>
            <dd><a href="#">退货/退款记录</a></dd>
        </dl> -->
       <!--  <dl>
            <dt>我的消息</dt>
            <dd class="cur"><a href="#">商城快讯</a></dd>
            <dd><a href="#">帮助中心</a></dd>
        </dl> -->
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/jquery.js"></script>