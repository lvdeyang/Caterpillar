<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            子模块管理
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
              <a><cite>系统管理</cite></a>
              <a><cite>子模块管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            <xblock>
            	<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button>
            	<button class="layui-btn" onclick="modularclass_add('添加模块','addv','900','500')"><i class="layui-icon">&#xe608;</i>添加子模块</button>
            	<span class="x-right" style="line-height:40px">共有数据：${allcount} 条</span>
            	<div class="layui-inline">
                	子模块：
                    <div class="layui-inline">
                        <input class="layui-input" name="cName" id="cName" autocomplete="off">
                    </div>
                   	 模块：
                    <div class="layui-inline">
                    	<select name="mCode" id="mCode" class="layui-select">
                    		<option></option>
        					<c:forEach items="${modulars}" var="modular">
        					<option value="${modular.modularCode}">${modular.modularName}</option>  
               				</c:forEach> 
                    	</select>
                    </div>
                    <button class="layui-btn" data-type="reload" onclick="select(this)" >搜索</button>
                </div>
            </xblock>
            <table id="mClassList" lay-filter="mClassList">
            </table>

            <div id="page"></div>
            
            <script id="alist" type="text/html">

</script>
        </div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
           
            layui.use(['element','laypage','layer','laytpl','table','form'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;
              table = layui.table;
              form = layui.form;
              
              //模板引擎
              //以上模块根据需要引入
              getClassList();



              form.on('switch(classIsv)', function(obj){

                layer.load();
                



                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "显示";
                    val = 1;
                }else{
                    str = "不显示";
                    val = 0;
                }
                
                $.ajax({
                    type:'post',
                    url:'changeIsv.do',
                    data:{'id':this.id,'val':val},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           console.log("成功");
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }

                })

                layer.tips(this.value+' : '+str,obj.othis);
              });


              table.on('tool(mClassList)',function(obj){
                 var data = obj.data;
                 if(obj.event === 'del'){
                    layer.confirm('确认要删除'+data.className+'吗？',function(index){
                        layer.load();
                    //发异步删除数据
                        $.ajax({
                            type:"post",
                            url:"del.do",
                            data:{"uuid":data.uuid},
                            success:function(msg){
                                layer.closeAll("loading");
                                if(msg=="success"){
                                    layer.msg("删除成功！",{icon:6,time:500},function(){
                                        layer.close(index);
                                        getClassList();

                                    });
                                }
                            }
                        })
                    });  
                } else if(obj.event === 'edit'){
                    modularclass_edit('编辑','updatev',data.uuid,'','510');
                }
              })


			  active = {
                reload: function(){
                    var mCode = $('#mCode').find("option:selected");
                    console.log(mCode.val());
                    var cName = $('#cName');
                    table.reload('mClassTable', {
                        page: {
                               curr: 1 //重新从第 1 页开始
                              }
                        ,where: {
                            mCode: mCode.val(),
                            cName:cName.val()
                        }
                    });
                }
              };
              return false;

               
            });
	         
			
			
            //批量删除提交
            function delAll () {
                layer.confirm('确认要删除吗？',function(index){
                    //捉到所有被选中的，发异步进行删除
                    layer.msg('删除成功', {icon: 1});
                });
             }
             /*添加*/
            function modularclass_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

            //编辑
            function modularclass_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
           


            function getClassList(){
                table.render({
                    elem:'#mClassList'
                    ,method:'post'
                    ,url:'list.do'
                    ,page:true
                    ,limits: [5,10,30,100]
                    ,limit: 5
                    ,cols: [[
                        {type:'checkbox'}  
                        ,{field: 'classCode',title: '子模块标识',align:'center',templet:'#mktpTpl'}   
                        ,{field: 'className', title: '子模块名称',align:'center',sort: true}
                        ,{field: 'classmodularCode', title: '绑定模块',align:'center',sort: true,}       
                        ,{field:'classIsv', title:'显示',align:'center', templet: '#switchTpl', unresize: true}
                        ,{field:'classSort', title:'排序',align:'center'}
                        ,{field:'updateTime', title:'上传时间',align:'center'}
                        ,{fix:'right',toolbar:'#zsgc',width:80,unresize:true} 
                	]]
                	,id:"mClassTable"
                })
            }
            
            function select(obj){
                var type = $(obj).data('type');
                active[type] ? active[type].call(this) : '';
            }


            </script>
<script type="text/html" id="zsgc">
    <div style="cursor: pointer;">
    <a title="编辑" lay-event ="edit"><i class="layui-icon">&#xe642;</i></a> 
    <a title="删除" lay-event ="del"><i class="layui-icon">&#xe640;</i></a> 
    </div>
</script>
<script type="text/html" id="switchTpl">
  <input type="checkbox" name="classIsv" id='{{d.id}}' value="{{d.className}}" lay-skin="switch" lay-text="是|否" lay-filter="classIsv" {{ d.classIsv == 1 ? 'checked' : '' }}>
</script>
    </body>
</html>