<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>分销商管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
<link rel="stylesheet" href="<%=path %>/webtheme/theme/css/admin/distributor/list.css">	

</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>分销商管理</cite></a>
		</span> <a class="layui-btn layui-btn-small merflash"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">

		<xblock>
		<!-- <button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<button class="layui-btn" 
			onclick="distributor_add('添加权限','addv','900','750')">
			<i class="layui-icon">&#xe608;</i>添加
		</button> -->
		<span class="x-right lheight4">共有${allcount}
			个分销商</span></xblock>
	<table class="layui-table" id="distributorList" lay-filter="distributorList"></table>
		

	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
	  function distributor_check(title,url,id,w,h) {
                 x_admin_show(title,url+"?id="+id,w,h); 
              }
               
     
            layui.use(['element','laypage', 'layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;
              //以上模块根据需要引入
              
            
               
              getdistributorList();


              //工具
               //分类删除
              table.on('tool(distributorList)',function(obj){                                  
                  var data = obj.data;
                  if(obj.event === 'detail'){
                     
                  } else if(obj.event === 'del'){
                        /*$.ajax({
                          type:"post",
                          url:"del.do",
                          data:{"uuid":obj.data.uuid},
                          success:function(msg){
                            layer.closeAll("loading");
                            if(msg=="success"){
                              layer.msg("删除成功！");
                              obj.del();
                            }
                          }
                        })*/

                  } else if(obj.event === 'edit'){
                      
                  } 
                }) 
              });
	         

       //获取商家列表
       function getdistributorList(){
        table.render({                  /*3.引入table*/
         elem:'#distributorList'
         ,method:'post'
         ,url:'list.do'
         ,page:true
         ,limits: [10,30,50,100]
         ,limit: 10
         ,height:'full'
         ,cols: [[
          {type: 'checkbox'}
          ,{field: 'id', title: 'ID',sort: true,width:60} 
          ,{field: 'legalPerson', title: '法人', sort: true}  
          ,{field: 'bankNo', title: '银行卡号',sort: true}       
          ,{field: 'address', title: '地址',sort: true, width:80}
          ,{field: 'phone', title: '手机',width:160,sort: true} 
          ,{field: 'type', title: '类型',width:180,sort: true} 
          ,{field: 'checkReason', title: '审核意见',width:180,sort: true} 
          ,{field: 'regionName', title: '地区',width:180,sort: true} 
          ,{field: 'status', title: '状态',width:180,sort: true} 
          ,{fixed: '',align:'center', title: '操作',width:120,minWidth:100,templet:'#handle',unresize:true}
         ]]
        });
       }

            </script>
<script type="text/html" id="handle">  
 
 <a title="审核" href="javascript:;" onclick="distributor_check('审核','check','{{ d.id }}','','510')"
                            class="ml-5" style="text-decoration:none">
     <i class="layui-icon">审核</i>
 </a>
 

</script> 
</body>
</html>