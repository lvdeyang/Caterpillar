<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/layui/css/x-admin.css"
	media="all">
</head>
<body>

	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>商家管理</cite></a>
			<a><cite>待审核商家</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>

	<!-- b1 -->
	<!-- e1 -->
	<div class="x-body">
		<xblock>
			<span id=count class="x-right" style="line-height:40px">共有数据：${allcount}条</span>
			<div class="layui-inline">
				商家：
				<div class="layui-inline">
					<input class="layui-input" name="mName" id="mName"
							autocomplete="off">
				</div>
				<button class="layui-btn" data-type="reload" onclick="select(this)">搜索</button>
			</div>
		</xblock>
		<table class="layui-table" id="checkList" lay-filter="checkList">
		</table>
	</div>
	<script src="<%=path%>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path%>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
           
            layui.use(['element','laypage', 'layer','laytpl', 'form','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              table = layui.table; 
              var form = layui.form;
              //以上模块根据需要引入
              
              getcheckList();
              
                //检索table表重新加载
			  active = {
        		reload: function(){
            		var mName = $('#mName');
            		table.reload('cList', {
                		page: {
          					curr: 1 //重新从第 1 页开始
        				}
                		,where: {
                    		mName: mName.val(),
                			}
            		});
            		layer.closeAll("loading");
        	  	}
    		  };
              return false;
			});
			
            //编辑
            function merchant_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            
            /*删除*/
            function merchant_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"uuid":id},
                    success:function(msg){
                    	if(msg=="success"){
                    		$(obj).parents("tr").remove();
                    		layer.msg('已删除!',{icon:1,time:1000});
                    	}
                    }
                    })
                    
                });
            }
            
            function getcheckList(){
           	 	layer.load(2);
       			table.render({                  /*3.引入table*/
         			elem:'#checkList'
       				,method:'post'
       				,url:'checklist.do'
       				,page:true
       				,limits: [10,30,50,100]
       				,limit: 10
       				,height:'full'
       				,id:'cList'
       				,cols: [[
        				{type: 'checkbox'}
        				,{field: 'id', title: 'ID',sort: true,width:60} 
        				,{field: 'shopName', title: '商家名称',sort: true}  
        				,{field: 'shopLoginName', title: '登录名称',sort: true} 
       		 			,{field: 'shopTel', title: '联系电话',sort: true} 
        				,{field: 'shopAddress', title: '商户地址'} 
        				,{field: 'shopLinkperson', title: '联系人',sort: true,width:100} 
        				,{field: 'shopAuditState', title: '审核状态',sort: true,width:120} 
        				,{field: 'shopAuditopinion', title: '审核意见',sort: true,width:120}
        				,{field: 'modularName', title: '板块名称',sort: true,width:120}
        				,{field: 'modularClass', title: '板块分类',sort: true,width:120}
        				,{fixed: 'right',width:120,minWidth:60,templet:'#zsgc',unresize:true}
       				]]
         			,done:function(res, curr, count){
         				layer.closeAll("loading");
         			}
        		});
            }
                  //检索
		function select(obj){
			layer.load(2);
			var type = $(obj).data('type');
    		active[type] ? active[type].call(this) : '';
		}
            </script>
<script type="text/html" id="zsgc">  
 <a title="编辑" href="javascript:;" onclick="merchant_edit('编辑','check','{{ d.uuid }}','','510')"
                            class="ml-5" style="text-decoration:none">
     <i class="layui-icon">审核</i>
 </a>
 <a title="删除" href="javascript:;" onclick="merchant_del(this,'{{ d.uuid }}')" 
                            style="text-decoration:none">
     <i class="layui-icon">&#xe640;</i>
 </a>
</script> 
</body>
</html>