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
           宣传部活动
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
              <a><cite>宣传部活动</cite></a>
              <a><cite>列表管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            
            <xblock><button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button><button class="layui-btn" onclick="article_add('添加文章','addv','900','500')"><i class="layui-icon">&#xe608;</i>添加文章</button>
        
            
             <div class="layui-inline">
                	标题：
                    <div class="layui-input-inline">
                        <input class="layui-input" name="sName" id="sName" autocomplete="off">
                    </div>
                   	 类型：
                   
                    
                            <select  name="ptype" id="ptype">
                             <option value="">请选择类型</option>
                               <option value="PICTURE">图片</option>
                             
                               <option value="LITTLEVEDIO">小视频</option>
                             
                            </select>
                   
                    
                    
                    <button class="layui-btn" data-type="reload" onclick="getlist(1)" >搜索</button>
               </div>
           
            
            <span class="x-right" style="line-height:40px">共有数据：${allcount} 条</span></xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" name="" value="">
                        </th>
                        <th>
                            ID
                        </th>
                        <th>
                            标题
                        </th>
                      
                        
                        <th>
                            用户ID
                        </th>
                        
                         <th>
                      创建时间
                         </th>
                         
                          <th>
                         操作
                         </th>
                         
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
                            {{ item.name	 }}
                        </td>
                       
                       
                       <td>
                            {{item.userId}}
                         </td>


<td>
                            {{item.updateTime}}
                         </td>
                        <td class="td-manage">
                            <a title="编辑" href="javascript:;" onclick="article_edit('编辑','updatev','{{ item.uuid }}','','510')"
                            class="ml-5" style="text-decoration:none">
                                <i class="layui-icon">&#xe642;</i>
                            </a>
                            <a title="删除" href="javascript:;" onclick="article_del(this,{{ item.id }})" 
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
                ,limit: 10
                ,jump: function(obj){
                getlist(obj.curr);
          
                   }
              });
               
            });
	         
		
            //异步加载列表
			function getlist(c){
			
			var sName=$("#sName").val();
			var vType=$("#ptype").val();
				$.ajax({
				 type:"post",
				 url:"list.do",
				 data:{"page":c,"sName":sName,"vType":vType},
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
            function article_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

            //编辑
            function article_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            /*删除*/
            function article_del(obj,id){
            console.log(id);
                layer.confirm('确认要删除吗？',function(index){
                    //发异步删除数据
                    $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"vpid":id},
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