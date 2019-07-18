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
        <div class="x-body">
            
            <xblock>           
                <div class="layui-inline">
                	用户手机号：
                    <div class="layui-inline">
                        <input class="layui-input" name="userPhone" id="userPhone" autocomplete="off">
                    </div>
                   	 昵称：
                    <div class="layui-inline">
                        <input class="layui-input" name="nickname" id="nickname" autocomplete="off">
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
                    ,url:"userlist"
                    ,page:true
                    ,limits: [10, 20, 30]
                    ,limit: 10
                    ,cols: [[
                        {type:"checkbox"}
                        ,{field:"id",title:"Id",sort:true}                                 
                        ,{field:"userNickname",title:"昵称"} 
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
            
            
         function sel(id,shopName) {		
			$.ajax({
	                    type:"post",
	                    url:"addjudges.do",
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
	                     window.parent.location.reload();
	                    }
	                    }
	            }) 	
		}  
               
            
            
            
</script>
<script type="text/html" id="zsgc"> 
{{# }} 
		<a title="添加" href="javascript:;" onclick="sel('{{ d.id }}','{{ d.shopName }}')" 
                           	style="text-decoration:none">
                            	<i>添加</i>
                        	</a>
{{# }} 
</script>    
    </body>
</html>