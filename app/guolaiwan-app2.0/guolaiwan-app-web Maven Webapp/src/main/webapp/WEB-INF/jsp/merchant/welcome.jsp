<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            阳光成单系统
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/css/bootstrap.css" media="all">
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/js/bootstrap.min.js" media="all">
    </head>
    <body>
    <style type="text/css">
	legend { display: block; width:100px; border-bottom:0px; font-family: "Microsoft YaHei","Helvetica Neue";}
	legend a{ color:#666;} legend a:hover{ text-decoration:none;}
	.layui-table{ font-family: "Microsoft YaHei","Helvetica Neue";}
	</style>
        <div class="x-body">
            <blockquote class="layui-elem-quote">
                欢迎使用过来玩系统！<span class="f-14">v1.0</span> &nbsp;&nbsp;登录次数：
            </blockquote>
<div class="row">
            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-blue"> <i class="fa fa-address-card"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="入园总数"><i class="iconfont " data-icon=""></i>
                            <h1>10</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="入园总数"> <i class="iconfont " data-icon=""></i><span>入园总数</span></a>

                    </div>
                </section>
            </div>
            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-commred"> <i class="fa fa-handshake-o"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="出园总数"> <i class="iconfont " data-icon=""></i>
                            <h1>10</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="出园总数"> <i class="iconfont " data-icon=""></i><span>出园总数</span></a>

                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-dark-green"> <i class="fa fa-bar-chart"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="在园总数"> <i class="iconfont " data-icon=""></i>
                            <h1>10</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="在园总数"> <i class="iconfont " data-icon=""></i><span>在园总数</span></a>
                    </div>
                </section>
            </div>

            

        </div>
            
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a name="default">信息统计</a></legend>
            </fieldset>
             
        <!--相关统计-->
        <div class="row">
       
        </div>
    
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/index.js"></script>
        <script src="<%=path %>/layui/js/echarts.min.js"></script>
        <script src="<%=path %>/layui/js/echart.js"></script>
            
        </div>
        <div class="layui-footer footer footer-demo">
            <div class="layui-main">
                <p>
                    <a href="/">
                        Copyright ©2017-2019 遵化市博客旅游集散中心有限公司 All Rights Reserved.
                    </a>
                </p>
                
            </div>
        </div>
        
    </body>
</html>