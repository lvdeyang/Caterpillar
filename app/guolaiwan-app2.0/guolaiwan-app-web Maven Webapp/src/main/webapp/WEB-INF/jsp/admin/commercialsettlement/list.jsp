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
            商家结算
        </title>
        <meta name="rende rer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
  </head>
  
<body>
        <div class="x-nav">
            <span class="layui-breadcrumb">
              <a><cite>首页</cite></a> <a><cite>商家结算</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        
        <div class="x-body">
            
            <xblock>
            <button type="button" class="layui-btn" id="settle" onclick="settle()">结算</button>
            	<div class="layui-inline">
					<div class="layui-inline">
						<input class="layui-input" name="sName" id="sName"
								autocomplete="off">
					</div>
					<button class="layui-btn" data-type="reload" onclick="select(this)">搜索</button>
				</div>
            </xblock>
            <table class="layui-table" id="List" lay-filter="List"></table> 
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
              

              getCommercialList();
			 active = {
        		reload: function(){
					var index = layer.load(1); 
            		var sName = $('#sName');
            		table.reload('mList', {
                		where: {
                    		sName: sName.val(),
                			},
					    done:function (res) {   //返回数据执行回调函数
					    	layer.close(index);    //返回数据关闭loading
					    	
					    }
            		});
        	  	}
    		  };
             return false;
               
            });
	         

            function getCommercialList(){//加载
				var index = layer.load(1); 
                table.render({
                    elem:"#List"
                    ,method:"post"
                    ,url:"list.do"
                    ,page:true
                    ,loading: true
                    ,limits: [10,30,50,100]
                    ,limit: 10
                    ,id:'mList'
                    ,cols: [[{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
                        ,{field: 'shopName', align:'center',title: '名称',sort: true} 
                        ,{field: 'shopBankId',align:'center', title: '银行卡号',sort: true,width:320} 
                        ,{field: 'price',align:'center', title: '结算金额',sort: true,width:280} 
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#history"}
                        ,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#order"}
                        ]],
					    done:function (res) {   //返回数据执行回调函数
					    	layer.close(index);    //返回数据关闭loading
					    	
					    }
                })
           }       
           
           function settle()
           {
                 layer.confirm('确认要结算吗？',function(index){
                    var url = "/guolaiwan-app-web/admin/commercialsettlement/settle.do";
                    window.open(url);
                    window.location.reload();
                    layer.close(index);
                });          
           }  
           
			function settlehistory(title,url,w,h,id)
            {    
            	x_admin_show(title,url+"/"+id,w,h);
            }        
            
            function orderlist(title,url,w,h,id)
            {    
            	x_admin_show(title,url+"/"+id,w,h);
            }  
                          
            function select(obj){
				var type = $(obj).data('type');
    			active[type] ? active[type].call(this) : '';
			} 
            </script>
 <script type="text/html" id="history">
	 <a title="结算历史" href="javascript:;" onclick="settlehistory('结算历史','history','1000','600','{{ d.id }}')" class="ml-5" style="text-decoration:none">
         <i class="layui-btn layui-btn-xs">结算历史</i>
     </a>	
</script>
 <script type="text/html" id="order">

 <a title="详情" href="javascript:;" onclick="orderlist('订单详情','order','1000','600','{{d.id}}')" style="text-decoration:none"><i class="layui-icon">&#xe62d;</i></a>
</script>           
    </body>
</html>
