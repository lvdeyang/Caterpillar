<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>网点列表</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
</head>
<body>
<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>物流管理</cite></a>
			<a><cite>网点列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
	<div class="x-body">
		<xblock>
		<button class="layui-btn"
			onclick="full_show('添加网点','pointaddv?logId=${logId}','1000','800')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		
		<!-- <button class="layui-btn layui-btn-danger" onclick="">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button> -->
		<span class="x-right" style="line-height:40px">共有数据：<span id="count">${count}</span>
			条</span>
			</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" id="chooseAll" onclick="chooseAll()" ></th>
					<th>名称</th>
					<th>经度</th>
					<th>纬度</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="view">

			</tbody>
		</table>
		<div id="page"></div>


		<script id="alist" type="text/html">
{{#  layui.each(d.list, function(index, item){ }}
						<tr>
                        <td>
                            <input type="checkbox" value="{{ item.uuid }}" name="selected" >
                        </td>
						<td>
                            {{ item.name }}
                        </td>
						
						<td>
                            {{ item.longitude }}
                        </td>
						<td>
                            {{ item.latitude }}
                        </td>
                       
                        <td class="td-manage">
						 <a title="修改" href="javascript:;" onclick="point_edit('编辑','pointupdatev','{{ item.id }}','','510')" 
                            style="text-decoration:none">
                                <i class="layui-icon">&#xe642;</i>
                            </a>     
                       <a title="删除" href="javascript:;" onclick="point_del(this,'{{ item.id }}')" 
                            style="text-decoration:none">
                                <i class="layui-icon">&#xe640;</i>
                            </a>
                        <a class="layui-btn layui-btn-primary layui-btn-xs" href="javascript:;" onclick="full_showpeo('快递员列表','peoplelist','{{ item.id }}','400','600')">快递员管理</a>

                        </td>               
                       </tr>
{{#  }); }}

{{#  if(d.list.length === 0){ }}
  没有相关信息
{{#  } }} 
</script>
	</div>

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
	var logId = '${logId}';	
	var ipage ;	
	var ilimit;
	var doneCount = 0;
	layui.use(['element','layer','laytpl','upload','laydate','laypage','form','table'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        laypage = layui.laypage;//分页
        upload = layui.upload;
        laydate = layui.laydate;
     	var form = layui.form;
     	var table = layui.table;
     	
  		
 	
	 	
	 	getlist();
	 	
	 	
	 	
});
	 
	
	
	function point_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
    
    function full_show(title,url,w,h) {
    	var index = layer.open({
			type: 2,
			area: [w+'px', h +'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose: true,
			shade:0.4,
			title: title,
			content: url
		}); 
		layer.full(index);
    }
	
	function full_showpeo(title,url,id,w,h) {
    	var index = layer.open({
			type: 2,
			area: [w+'px', h +'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose: true,
			shade:0.4,
			title: title,
			content: url+"?uuid="+id
		}); 
		layer.full(index);
    }
	
	//异步加载列表
	function getlist(){
		console.log("llak");
		$.ajax({
			type:"post",
			url:"pointList.do",
			async:false,
            data:{'logId':logId},
			success:function(msg){
			
				var data = {
           		"list":msg.pList
       	 		}
       	 		console.log(data);
				//操作模板引擎
				var getTpl = alist.innerHTML;
				laytpl(getTpl).render(data, function(html){
            		view.innerHTML = html;
        		});
			}
		})
	}
	
	
	//异步删除列表 
	function point_del(obj,id){
    	layer.confirm('确认要删除吗？',function(index){
   		//发异步删除数据
    		$.ajax({
        		type:"post",
        		url:"pointdel.do",
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
	
	
	//全选和全不选（**）
	function chooseAll(){
		var list = $("input[name='selected']")
		if($("#chooseAll").prop("checked")){
			$("input[name='selected']").prop("checked", "checked");
		}else{
			$("input[name='selected']").removeAttr("checked");			
		}
	}
	
	function delAll(){
		var list =$("input[name='selected']:checked");
		var uuids = new Array(list.length);
		for(var i=0;i<list.length;i++){
			uuids[i] = $(list[i]).val();
		}
		if(uuids.length == 0){
			layer.msg("请选择删除项！");
		}else{
		layer.confirm('确认要删除吗？',function(){
   		
    		$.ajax({
        		type:"post",
        		url:"delAll.do",
        		data:{'uuids':uuids},
       			success:function(msg){
        			if(msg=="success"){
            			/* $("input[name='selected']:checked").parents("tr").remove();  */
            			getlist(ipage,ilimit);
            			if($("#chooseAll").prop("checked")){
            				$("#chooseAll").removeAttr("checked");
            			} 
            			$("#count").html($("#count").text() - list.length);
            			layer.msg('已删除'+list.length+'条数据!',{icon:1,time:1000});
            			
            		}
            		
            	}
       		})
       		 
   		});
   		}
	}
	
	function fenye(){
 			laypage.render({
                elem: 'page'
                ,count: parseInt($("#count").text())
                ,first: '首页'
    			,last: '尾页'
    			,limit: 10
    			,prev: '<em><</em>'
    			,next: '<em>></em>'
    			,layout: ['page', 'skip']
                ,jump: function(obj){
                	getlist(obj.curr);
                	ipage=obj.curr;
                   }
              });
 		}
 		
    //讲解点编辑
    function point_edit(title,url,id,w,h){
    	x_admin_show(title,url+"?logId="+logId+"&uuid="+id,w,h); 
    }
    
   
    
   
	
</script>

</body>
</html>