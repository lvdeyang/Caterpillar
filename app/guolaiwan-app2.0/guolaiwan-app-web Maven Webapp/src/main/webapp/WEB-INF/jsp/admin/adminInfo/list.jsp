<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>阳光成单系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../layui/css/x-admin.css" media="all">
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>会员管理</cite></a>
			<a><cite>管理员列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">
		<form class="layui-form x-center" action="adminInfo" style="width:80%"
			method="get">
			<div class="layui-form-pane" style="margin-top: 15px;">
				<div class="layui-form-item">
					<label class="layui-form-label">创建日期</label>

					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="LAY_demorange_s"
								placeholder="开始日">
						</div>
					</div>

					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" class="layui-input" id="LAY_demorange_e"
								placeholder="截止日">
						</div>
					</div>
					<div class="layui-input-inline">
						<input type="text" name="username" placeholder="请输入登录名"
							autocomplete="off" class="layui-input" value="${username}">
					</div>
					<div class="layui-input-inline" style="width:80px">
						<button class="layui-btn" lay-submit="" lay-filter="sreach">
							<i class="layui-icon">&#xe615;</i>
						</button>
					</div>
				</div>
			</div>
		</form>
		<xblock>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<button class="layui-btn"
			onclick="admin_add('添加用户','addv','600','700')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<span class="x-right" style="line-height:40px">共有数据：${allcount}
			条</span></xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" name="" value=""></th>
					<th>ID</th>
					<th>登录名</th>


					<th>角色</th>
					<th>城市</th>
					<th>公司</th>
					<th>加入时间</th>

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
                            {{ item.adminName }}
                        </td>
                       
                        <td >
                            {{ item.roleName }}
                        </td>

 						<td >
                            {{ item.cityName }}
                        </td>
						
						<td >
                            {{ item.comName }}
                        </td>

                        <td>
                            {{ item.createdDate }}
                        </td>
                       
                        <td class="td-manage">
                            <a title="编辑" href="javascript:;" onclick="admin_edit('编辑','updatev','{{ item.uuid }}','','700')"
                            class="ml-5" style="text-decoration:none">
                                <i class="layui-icon">&#xe642;</i>
                            </a>
                            <a title="删除" href="javascript:;" onclick="admin_del(this,'{{ item.uuid }}')" 
                            style="text-decoration:none">
                                <i class="layui-icon">&#xe640;</i>
                            </a>
                        </td>
                    </tr>
{{#  }); }}

{{#  if(d.list.length === 0){ }}
  无数据
{{#  } }} 
</script>

	</div>
	<script src="../layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="../layui/js/x-layui.js" charset="utf-8"></script>
	<script>
            layui.use(['laydate','element','laypage','layer','laytpl'], function(){
                $ = layui.jquery;//jquery
              laydate = layui.laydate;//日期插件
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
             
              //以上模块根据需要引入
              laypage.render({
                elem: 'page'
                ,count:${allcount}
                ,first: false
                ,last: false
                ,limit: 5
                ,jump: function(obj){
                getpages('liuliqiang',obj.curr);
          
                   }
              }); 
            
             
    
            laydate.render({
    elem: '#LAY_demorange_s'
  });
  
       laydate.render({
    elem: '#LAY_demorange_e'
  });
             
            }); 
              
            //批量删除提交
             function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             /*添加*/
            function admin_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

           
            //编辑
            function admin_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            /*删除*/
            function admin_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                     $.ajax({
                	  type:"post",
           			  url:"del.do",
                      data:{"uuid":id},
                      success:function(msg){
                      console.log(msg);
                        if(msg=="success"){
                           $(obj).parents("tr").remove();
                           layer.msg('已删除!',{icon:1,time:1000});
                        }else{
                        layer.msg('您没有权限!',{icon:1,time:1000});
                        }
                       }
                    }) 
                   
                });
            }
            
              //加载数据
              function getpages(username,pagecurr)
              {
              	$.ajax({
              	   type:"post",
              	   url:"list.do",
              	   async:false,
              	   data:{'pagecurr':pagecurr,'username':'${username}'},
              	   success:function(msg){
              	   if(msg.list!=null){
              	       var data = {
                               "list":msg.list
                         }
               			    var getTpl = alist.innerHTML;
                			laytpl(getTpl).render(data, function(html){
                              view.innerHTML = html;
                			 });
              	   }else{
              	     layer.msg('您没有权限或者没有数据!',{icon:1,time:5000});
              	   }
              	   }
              	})
              }
            </script>

</body>
</html>