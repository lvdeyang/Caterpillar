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
        	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right;margin-left:0" href="javascript:history.go(-1)" title="返回"><i class="layui-icon" style="line-height:38px">&#xe65c;</i></a>
        </div>
        <div class="x-body">
            
            <xblock>           
                <button class="layui-btn" onclick="merchant_childent()"><i class="layui-icon">&#xe608;</i>添加用户</button>
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

              getUserList();

              active = {
                reload: function(){
                    var userPhone = $('#userPhone');
                    var nickname = $('#nickname');
                    var openId = $('#openId');
                    table.reload('userListTable', {
                        page: {
                               curr: 1 //重新从第 1 页开始
                              }
                        ,where: {
                            userPhone: userPhone.val(),
                            nickname:nickname.val(),
                            openId:openId.val()
                        }
                    });
                }
              };
              return false;
             
            });

            function getUserList(){
              table.render({
                    elem:"#userList"
                    ,method:"post"
                    ,url:"list.do?optionId=${optionId}"
                    ,page:true
                    ,limits: [10, 20, 30]
                    ,limit: 10
                    ,cols: [[
                        {type:"checkbox"}
                        ,{field:"id",title:"Id",sort:true}
                        ,{field:"userPhone",title:"手机号"}
                        ,{field:"userIntegral",title:"积分"}
                        ,{field:"userHeadimg",title:"头像"}
                        ,{field:"userNickname",title:"昵称"}                       
                        ,{field:"wallet",title:"钱包余额（单位：分）",sort:true}
                        ,{field:"updateTime",title:"创建时间"}
                         ,{fixed: 'right',width:120,minWidth:100,templet:'#zsgc',unresize:true}
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
            
            
               function  merchant_childent(){
	                 var title="title"
	                 var url="skip.do?optionId=${optionId}"
	                 var w=""
	                 var h="610"
	                 x_admin_show(title,url,w,h);
               }
               
      function sel(id,shopName) {		
			$.ajax({
	                    type:"post",
	                    url:"deletejudges.do",
	                    data:{"id":id                                             
	                    },
	                    success:function(msg){
	                    if(msg == "msg"){                                   
	                    layer.msg("用户已是评委！");
	                   
	                    }
	                   if(msg == "false"){
	                    
	                    layer.msg("添加失败！");
	                    }
	                   if(msg == "true"){
	                    
	                    layer.msg("操作成功！");
	                     getUserList();
	                    }
	                    }
	            }) 	
		}  
</script>
<script type="text/html" id="zsgc"> 
{{# }} 
		<a title="删除" href="javascript:;" onclick="sel('{{ d.id }}','{{ d.shopName }}')" 
                           	style="text-decoration:none">
                            	<i>删除</i>
                        	</a>
{{# }} 
</script>  
            
    </body>
</html>