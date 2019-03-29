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
            订单中心
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
              <a><cite>订单中心</cite></a>
              <a><cite>售票</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
        </div>
        <div class="x-body">
        <div style="margin-bottom:15px">
        	<div class="layui-inline">
        		<img style="width:200px;height:200px;" src="<%=path%>/webtheme/theme/img/admin/smcode.png">
        	</div>
        	<div class="layui-inline" style="margin-left:60px;margin-top:120px">
        	<div style="font-family:楷体;font-size:20px;margin-bottom: 40px" >扫描二维码或手动输入票号:</div>
        	<form class="layui-form" >
        	<!-- 隐藏、去边框、取消提示 -->
        		<div class="layui-form-item">
        			<div class="layui-inline">
        				<input type="text" id="productNum" class="layui-input" name="productNum" style="width:300px" placeholder="手动输入票号" autocapitalize="on">
        			</div>
        			<div class="layui-inline">
        				<input type="text" id="idnum" class="layui-input" name="idnum" style="width:300px;display:none" placeholder="身份证" autocapitalize="on">
        			</div>
        			<div class="layui-inline">
        				<button class='layui-btn' lay-filter="sell" lay-submit>售票<button>
        			</div>
        		</div>
        	</form>
        	</div>
        	</div>
        	
        	<table id="sellList" lay-filter="sellList" ></table>
        	
        </div>
        <div id="view" hidden="hidden"></div>
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/x-layui.js" charset="utf-8"></script>
        <script>
        
        
           
            layui.use(['element','laypage','layer','laytpl','form','table'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              layer = layui.layer;//弹出层
              laypage = layui.laypage;//分页
              laytpl = layui.laytpl;//模板引擎
              form = layui.form;
              table = layui.table;
            
			 //光标到input中            
              $("#productNum").focus();
             	
             $(document).on('change','#productNum',function(){
                 if($(this).val()==''){
                    $('#idnum').hide();
                 }else{
                    $('#idnum').show();
                 }
             });
          
             $(document).click(function(e) { 
  				var clickId = $(e.target).attr("id")    
  				if(clickId!="productNum"){
  					$("#productNum").focus();
  				}
			})
              
              
           
              
              
              form.on("submit(sell)",function(data){
              	var productNum = data.field.productNum;
              	
              	//提交前清楚表单数据
              	$("#productNum").val("");
              	
              	
              	if(productNum==''){
              		layer.alert("没有输入票号",{ skin: 'layui-layer-molv' ,closeBtn: 0},function(index){
              			$("#productNum").focus();
              			layer.close(index);
              		});
              		return false;
              	}
         		$.ajax({
         			type:"post",
         			url:"sell.do",
         			data:{"productNum":productNum},
         			success:function(msg){
         			console.log(msg.msg);
         				if(msg.msg=="success"){
							layer.msg("售票成功！",{icon:1,time:1000},function(){
								getSellList();
								$("#productNum").focus();
							
							})     						
         				}
         			
         			}
         		})
         	   
               return false;
              })
              
              
              
              getSellList();
            
            function getSellList(){
            	table.render({
            		elem:"#sellList"
            		,method:'post'
   					,url:'sellList.do'
   					,page:true
   					,skin: 'nob'
   					,even:'true'
   					,limits: [10, 20, 30]
            		,limit: 10
   					,cols: [[
      					{field: 'productName', title: '商品'} 
      					,{field: 'productNum', title: '数量',width:60} 
     					,{field: 'orderAllMoney', title: '总金额',width:120} 
      					,{field: 'comboName', title: '套餐',width:120}
      					,{field: 'updateTime', title: '售票时间',width:200}
   					]]
   					,done:function(res, curr, count){
   						$("#productNum").focus();
   					}
            	})
            }
              
              
      
            
            });
            
            
          
			
			
			
            </script>
            
       
        
       
    </body>
</html>