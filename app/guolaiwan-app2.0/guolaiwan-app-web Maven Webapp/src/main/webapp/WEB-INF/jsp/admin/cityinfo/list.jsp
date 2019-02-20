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
            城市管理
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
              <a><cite>城市信息</cite></a>
              <a><cite>城市管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
            
            <xblock>
                <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button>
                <button class="layui-btn" onclick="cityinfo_add('添加城市','addv','900','500')"><i class="layui-icon">&#xe608;</i>添加城市</button>
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
              
              form.on('switch(enable)', function(obj){
                layer.load();
                var str;
                var bl = obj.elem.checked;
                var val;
                if(bl){
                    str = "启用";
                    val = 1;
                }else{
                    str = "不启用";
                    val = 0;
                }
                $.ajax({
                    type:'post',
                    url:'changeIsv.do',
                    data:{'id':this.id,'value':val},
                    success:function(msg){
                        layer.closeAll("loading"); 
                        if(msg=='success'){
                           layer.msg("修改成功！",{icon:1});
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }

                })

                layer.tips(this.value+' : '+str,obj.othis);
              });

             return false;
               
            });
	         

            function getcityList(){
                table.render({
                    elem:"#cityList"
                    ,method:"post"
                    ,url:"list.do"
                    ,page:true
                    ,limits: [10,30,50,100]
                    ,limit: 10
                    ,cols: [[
                        {type:'checkbox'}
                        ,{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
                        ,{field: 'cityName', align:'center',title: '城市名称',sort: true,width:160} 
                        ,{field: 'cityDomain',align:'center', title: '域名',sort: true} 
                        ,{field: 'cityCode',align:'center', title: '城市编号',sort: true} 
                        ,{align:'center', title: '是否启用',width:120,templet:'#switchTpl'}
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#zsgc"}
                        ]]
                })
           }
		
         
		
             /*添加*/
            function cityinfo_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }

            //编辑
            function cityinfo_edit (title,url,id,w,h) {
                x_admin_show(title,url+"?uuid="+id,w,h); 
            }
            /*删除*/
            function cityinfo_del(obj,id){
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
<script type="text/html" id="switchTpl">
	<input type="checkbox" name="enable" id='{{d.id}}' value='{{ d.cityName }}'  lay-skin="switch" lay-text="是|否" lay-filter="enable" {{ d.enable == 1 ? 'checked' : '' }} >
</script>
<script type="text/html" id="zsgc">
	 <a title="编辑" href="javascript:;" onclick="cityinfo_edit('编辑','updatev','{{ d.uuid }}','','510')" class="ml-5" style="text-decoration:none">
         <i class="layui-icon">&#xe642;</i>
     </a>
     <a title="删除" href="javascript:;" onclick="cityinfo_del(this,'{{ d.uuid }}')" style="text-decoration:none">
         <i class="layui-icon">&#xe640;</i>
     </a>		
</script>
          	
    </body>
</html>