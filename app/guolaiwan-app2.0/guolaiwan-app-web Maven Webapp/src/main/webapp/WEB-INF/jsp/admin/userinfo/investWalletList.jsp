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
            板块管理
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
    </head>
    <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a>
              <a><cite>用户管理</cite></a>
              <a><cite>用户列表</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            
            <xblock>
                <div class="layui-inline">
                   	 用户昵称：
                    <div class="layui-inline">
                        <input class="layui-input" name="username" id="username" autocomplete="off">
                    </div>
                    
                    <button class="layui-btn" data-type="reload" onclick="select(this)" >搜索</button>
                    
                </div>
            </xblock>
            

            <table id="userList" lay-filter="userList"></table>

        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
           
            layui.use(['element','laypage','layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;//table
              //以上模块根据需要引入

              getWalletList();
              active = {
                reload: function(){
                    var userid = $('#userid');
                    var username = $('#username');
                    table.reload('userListTable', {
                        page: {
                               curr: 1 //重新从第 1 页开始
                              }
                        ,where: {
                            userid: userid.val(),
                            username:username.val(),
                        }
                    });
                }
              };
              return false;
             
            });

            function getWalletList(){
              table.render({
                    elem:"#userList"
                    ,method:"post"
                    ,url:"walletlist.do"
                    ,page:true
                    ,limits: [10, 20, 30]
                    ,limit: 10
                    ,cols: [[
                        {type:"checkbox"}
                        ,{field:"id",title:"Id",sort:true}
                        ,{field:"userid",title:"用户Id"}
                        ,{field:"username",title:"用户昵称"} 
                        ,{field:"money",title:"充值金额（单位：分）",sort:true}
                        ,{field:"updateTime",title:"充值时间"}
                    ]]
                    ,done:function(res, curr, count){
                    }
                    ,id:"userListTable"
              })
            }

            function select(obj){
                var type = $(obj).data('type');
                active[type] ? active[type].call(this) : '';
            }
            
            
            
            </script>
            
    </body>
</html>