<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>站点管理</title>
<meta name="rende rer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a
			href="<%=path%>/merchant/route/list"><cite>路线</cite></a> <a><cite>站点</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">

		<xblock>
		<a class="layui-btn layui-btn-danger" href="<%=path%>/merchant/route/list">
			<i class="layui-icon">&#xe65c;</i>返回上一级
		</a>
		<button class="layui-btn"
			onclick="site_add('添加站点','addv','${route}','600','500')">
			<i class="layui-icon">&#xe608;</i>添加站点
		</button>
		<span class="x-right" style="line-height:40px">共有数据：${allcount}
			条</span></xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" name="" value=""></th>
					<th>ID</th>

					<th>站点名称</th>
					<th>价格</th>


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
                            <input type="checkbox" value="1" name="">
                        </td>
<td>
                             {{ item.id }}
                        </td>
                        <td>
                             {{ item.name }}
                        </td>
                        <td>
                            {{ item.price }}
                        </td>
                       

						



 
 <td class="td-manage">
                            <a title="编辑" href="javascript:;" onclick="driver_edit('编辑司机','updatev','{{ item.uuid }}','600','500')"
                            class="ml-5" style="text-decoration:none">
                                <i class="layui-icon">&#xe642;</i>
                            </a>
                            <a title="删除" href="javascript:;" onclick="driver_del(this,'{{ item.uuid }}')" 
                            style="text-decoration:none">
                                <i class="layui-icon">&#xe640;</i>
                            </a>
                        </td>
                    </tr>
{{#  }); }}

{{#  if(d.list.length === 0){ }}
  没有相关信息
{{#  } }} 
</script>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
           	var route = '${route}';
            layui.use(['element','laypage','layer','laytpl'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
               //以上模块根据需要引入
              laypage.render({
                elem: 'page'
                ,count:${allcount}
                ,first: false
                ,last: false
                ,limit: 5
                ,jump: function(obj){
                getlist(obj.curr);
          
                   }
              });
               
            });
	         
		
            //异步加载列表
			function getlist(c){
				
				$.ajax({
				 type:"post",
				 url:"list.do",
				 data:{"pagecurr":c,"route":route},
				 success:function(msg)
				 {
				 console.log(msg);
				 var data = {
                               "list":msg.alist
                         }
				   //操作模板引擎
				   var getTpl = alist.innerHTML;
				   laytpl(getTpl).render(data, function(html){
                              view.innerHTML = html;
                    });
				 }
				})
			}
			
            //批量删除提交
            function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             /*添加*/
            function site_add(title,url,r,w,h){
                x_admin_show(title,url+"?route="+r,w,h);
            }

            //编辑
            function driver_edit (title,url,id,w,h) {
            console.log(url+"?uuid="+id);
           
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            /*删除*/
            function driver_del(obj,id){
            	console.log(id);
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
           
            </script>

</body>
</html>