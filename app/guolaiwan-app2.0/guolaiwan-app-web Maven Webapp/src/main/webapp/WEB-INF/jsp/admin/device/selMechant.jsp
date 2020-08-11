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
<title>商户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css"
	media="all">
<link rel="stylesheet" href="<%=path %>/webtheme/theme/css/admin/merchant/list.css">	

</head>
<body>
	<div class="x-nav">
		<a class="layui-btn layui-btn-small merflash"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
	</div>
	<div class="x-body">

		<xblock>
		<div class="layui-inline">
  			商家：
  			<div class="layui-inline">
    			<input class="layui-input" name="mName" id="mName" autocomplete="off">
  			</div>
  			<button class="layui-btn" data-type="reload" onclick="select(this)" >搜索</button>
	    </div>
			
			
			</xblock>
	<table class="layui-table" id="merchantList" lay-filter="merchantList"></table>
<script type="text/html" id="zsgc">
    {{#  { }}
              <a title="选择" href="javascript:;" onclick="sel('{{ d.id }}')"
                           	style="text-decoration:none">
                            	<i>选择</i>
                        	</a>
                {{#  } }}
</script>
	</div>
	<script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
           
            layui.use(['element','laypage', 'layer','laytpl','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              table = layui.table;
              //以上模块根据需要引入
           
               
              getMerchantList();

			  
			  //检索table表重新加载
			  active = {
        		reload: function(){
            		var mName = $('#mName');
            		table.reload('merList', {
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
			  
		});
              //工具
            
	         
	         
	         function sel(data){
	         	$.ajax({
	         		type:"post",
	         		url:"selMechant.do?merchantId="+data+"&id=${deviceId}",
	         		success:function(msg){
	         			if(msg == "success"){
	         				layer.msg('设置成功', {icon: 1});
	         				parent.getdeviceList();
	         			}else if(msg == "err"){
	         				
	         			}
	         		}
	         	});
	         }
			
           
           

		       //获取商家列表
		       function getMerchantList(){
		        table.render({                  /*3.引入table*/
		         elem:'#merchantList'
		         ,method:'post'
		         ,url:'merchantlist.do'
		         ,page:true
		         ,limits: [10,30,50,100]
		         ,limit: 10
		         ,height:'full'
		         ,id:'merList'
		         ,cols: [[
		          {type: 'checkbox'}
		          ,{field: 'id', title: 'ID',sort: true,width:60} 
		          ,{field: 'shopName', title: '商家名称',sort: true,templet:'#shopNameTpl'}
		          ,{field: 'shopLinkperson', title: '联系人',width:80,sort: true}
		          ,{field: 'shopTel', title: '联系电话',width:160,sort: true}
		          ,{fixed: 'right',width:120,minWidth:100,templet:'#zsgc',unresize:true}
		         ]]
		         ,done:function(res, curr, count){
		         	layer.closeAll("loading");
		         }
		        });
		       }
				
				//检索
				function select(obj){
					var type = $(obj).data('type');
		    		active[type] ? active[type].call(this) : '';
				}
      
            </script>
</body>
</html>