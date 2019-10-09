<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
        <title>
            地区管理
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
		<link rel="stylesheet" href="<%=path %>/webtheme/theme/css/admin/distributecity/list.css">	
  </head>
  
  <body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a> <a><cite>分销商地区管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            
            <xblock>
                <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button>
                <button class="layui-btn" onclick="cityinfo_add('添加地区','addv','900','500')"><i class="layui-icon">&#xe608;</i>添加地区</button>
                <span class="x-right" style="line-height:40px">共有数据：${allcount} 条</span>
            </xblock>
            <table class="layui-table" id="cityList" lay-filter="cityList"></table> 
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
            layui.use(['element','laypage','layer','laytpl','table','form'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;
              form = layui.form;
              
              getcityList();
             
             return false;
               
            });
	         
            function getcityList(){
                table.render({
                    elem:"#cityList"
                    ,method:"post"
                    ,url:"citylist.do"
                    ,page:true
                    ,limits: [10,30,50,100]
                    ,limit: 10
                    ,cols: [[
                        {type:'checkbox'}
                        ,{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
                        ,{field: 'name', align:'center',title: '地区名称',sort: true} 
                        ,{field: 'code',align:'center', title: '地区编号',sort: true,width:120} 
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#class"}
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#zsgc"}
                        ]]
                })
           }
			/*上一级*/
			function lastclass(parentId){
			
				table.render({
                    elem:"#cityList"
                    ,method:"post"
                    ,url:"lastclass.do/"+parentId
                    ,page:true
                    ,limits: [10,30,50,100]
                    ,limit: 10
                    ,cols: [[
                        {type:'checkbox'}
                        ,{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
                        ,{field: 'name', align:'center',title: '地区名称',sort: true} 
                        ,{field: 'code',align:'center', title: '地区编号',sort: true,width:120} 
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#class"}
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#zsgc"}
                        ]]
                })
	                    
           }           
           
           
			/*下一级*/
			function nextclass(id){
				table.render({
                    elem:"#cityList"
                    ,method:"post"
                    ,url:"nextclass.do/"+id
                    ,page:true
                    ,limits: [10,30,50,100]
                    ,limit: 10
                    ,cols: [[
                        {type:'checkbox'}
                        ,{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
                        ,{field: 'name', align:'center',title: '地区名称',sort: true} 
                        ,{field: 'code',align:'center', title: '地区编号',sort: true,width:120} 
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#class"}
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#zsgc"}
                        ]]
                })
	                    
           }
         
		
             /*添加*/
            function cityinfo_add(title,url,id,w,h){
                x_admin_show(title,url,w,h);
            }
            //编辑
            function cityinfo_edit (title,url,id,parentId,w,h) {
                x_admin_show(title,url+"/"+id+"/"+parentId,w,h); 
            }
            /*删除*/
            function cityinfo_del(obj,id,uuid){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"id":id,"uuid":uuid},
                    success:function(msg){
                    	if(msg=="success"){
                    		$(obj).parents("tr").remove();
                    		layer.msg('已删除!',{icon:1,time:1000});
                    	}
                    }
                    })
                    
                });
            }
            
            //批量删除提交
             function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             
            </script>
            
<script type="text/html" id="switchTpl">
	<input type="checkbox" name="enable" id='{{d.id}}' value='{{ d.cityName }}'  lay-skin="switch" lay-text="是|否" lay-filter="enable" {{ d.enable == 1 ? 'checked' : '' }} >
</script>
<script type="text/html" id="zsgc">
	 <a title="编辑" href="javascript:;" onclick="cityinfo_edit('编辑','updatev','{{ d.uuid }}','{{ d.parentId }}','510')" class="ml-5" style="text-decoration:none">
         <i class="layui-icon">&#xe642;</i>
     </a>
     <a title="删除" href="javascript:;" onclick="cityinfo_del(this,'{{ d.id }}','{{ d.uuid }}')" style="text-decoration:none">
         <i class="layui-icon">&#xe640;</i>
     </a>		
</script>

<script type="text/html" id="class">
	 <a title="上一级" href="javascript:;" onclick="lastclass('{{d.parentId}}')" class="ml-5" style="text-decoration:none">
         <i class="layui-icon">上一级</i>
     </a>
     <a title="下一级" href="javascript:;" onclick="nextclass('{{ d.id }}')" style="text-decoration:none">
         <i class="layui-icon">下一级</i>
     </a>		
</script>
          	
    </body>
</html>