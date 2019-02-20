<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String weburl=WXContants.Website;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>过来玩系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%= request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header header header-demo">
    <div class="layui-main">
   
    <div class="admin-logo-box">
				<a class="layui-logo" >过来玩管理平台</a>
				<div class="larry-side-menu">
					<i class="fa fa-th-large" aria-hidden="true"></i>
				</div>
			</div>
            <ul class="layui-nav layui-layout-left layui-ygyd-menu" style="position:absolute; left:250px;">
      <li class="layui-nav-item">
        <a href="javascript:;">订单管理</a>
        <dl class="layui-nav-child">
          <dd><a href="">角色管理</a></dd>
          <dd><a href="">权限设置</a></dd>
          <dd><a href="">日志管理</a></dd>
        </dl>
      </li>
    </ul>
    
      <ul class="layui-nav" lay-filter="">
      <!-- <li class="layui-nav-item">
                        <a href="" title="消息">
                            <i class="layui-icon" style="top: 1px;">&#xe63a;</i>
                        </a>
                        </li> -->
        <li class="layui-nav-item"> <a href="javascript:;">${merchant}</a>
          <dl class="layui-nav-child">
            <!-- 二级菜单 -->
            <dd><a href="">个人信息</a></dd>
            <dd><a href="">切换帐号</a></dd>
            <dd><a href="<%= request.getContextPath() %>/logout/merchant"">退出</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item x-index"><a href="http://<%=weburl%>">前台首页</a></li>
      </ul>
    </div>
  </div>
  <div class="layui-side layui-bg-black x-side" style="left:-200px;">
    <div class="layui-side-scroll">
      <ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="side">
        ${menulist}
      </ul>
    </div>
  </div>
  <div class="layui-tab layui-tab-card site-demo-title x-main" lay-filter="x-tab" lay-allowclose="true" style="left: 0px;border-left: solid 2px #2299ee;">
    <ul class="layui-tab-title">
      <li class="layui-this"> 我的桌面 <i class="layui-icon layui-unselect layui-tab-close"></i> </li>
    </ul>
    <div class="layui-tab-content site-demo site-demo-body">
      <div class="layui-tab-item layui-show">
        <iframe frameborder="0" src="welcome" class="x-iframe"></iframe>
      </div>
    </div>
  </div>
  <div class="site-mobile-shade"> </div>
</div>
<script src="<%= request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script> 
<script src="<%= request.getContextPath() %>/layui/js/x-admin.js"></script> 
</body>
</html>