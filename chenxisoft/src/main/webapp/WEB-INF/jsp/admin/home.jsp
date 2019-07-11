<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>首页</title>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">后台管理系统</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <!-- <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul> -->
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">人员管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" class="menu" data="worker/index">人员列表</a></dd>
          </dl>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" class="menu" data="label/index">标签管理</a></dd>
          </dl>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" class="menu" data="user/index">终端用户</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">订单中心</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;"  class="menu" data="order/index">订单查询</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">定价管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;"  class="menu" data="combo/index">定价列表</a></dd>
          </dl>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" class="menu" data="regions/index">地区管理</a></dd>
          </dl>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;"  class="menu" data="days/index">天数管理</a></dd>
          </dl>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;"  class="menu" data="level/index">分级管理</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">发布管理</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;"  class="menu" data="article/index">文章列表</a></dd>
          </dl>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;"  class="menu" data="product/index">商品列表</a></dd>
          </dl>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" class="menu" data="classes/index">分类上架</a></dd>
          </dl>
        </li>
       
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
        <iframe frameborder="0" src="worker/index" class="x-iframe" style="width:100%;height:100%"></iframe>
    </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © 小青月嫂 2019-2020
  </div>
</div>

<script>
//JavaScript代码区域
layui.use('element', function(){
   var element = layui.element;
   var $ = layui.jquery;
   $('.menu').on('click',function(){
       $('iframe').attr('src',$(this).attr('data'));
   });
});
</script>
</body>
</html>
