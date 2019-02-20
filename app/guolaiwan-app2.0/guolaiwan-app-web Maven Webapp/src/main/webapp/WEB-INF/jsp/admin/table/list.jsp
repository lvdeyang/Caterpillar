<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/image";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>过来玩管理平台系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">

<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath() %>/layui/js/jquery.min.js" charset="utf-8">"></script>
<script src="<%=request.getContextPath() %>/layui/js/jQuery.print.min.js" charset="utf-8">"></script>
<style type="text/css">

.layui-table img {
	max-width: 1000px;
} /* 照片的最大宽度  */

#divtable {
 width:100%;
 overflow:hidden; 
}

#divtable .red {
	float:left;
	width:100px;
	height:100px;
	background-color:#FF5722;
	margin-left:5px;
	margin-top:5px;
	text-align:center;
}
#divtable .green {
	float:left;
	width:100px;
	height:100px;
	background-color:#5FB878;
	margin-left:5px;
	margin-top:5px;
	text-align:center;
}
#divtable .yellow {
	float:left;
	width:100px;
	height:100px;
	background-color:#FFB800;
	margin-left:5px;
	margin-top:5px;
	text-align:center;
}

.divmenu .title {
	height:50px;
	width:50px;
	line-height:50px;
	margin:0 auto;
}
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>餐桌管理</cite></a>
	</div>
	<div class="x-body">
	
	<xblock>
		
		<button class="layui-btn" 
			onclick="distributor_add('添加餐桌','addv','500','450')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		
	</xblock>
	
	
	<form class="layui-form">
		
		<div class="layui-inline">
		<label class="layui-form-label" style= "width:90px">选择日期:</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" id="chooseDate" placeholder="选择日期">
			</div>
		</div>
		
		
	    <div class="layui-inline">
			<label for="classCode" class="layui-form-label">选择餐别:</label>
		    <div class="layui-input-inline">
		      	<select name="type"  id="type">
		        	<option value="LUNCH" checked>午餐</option>
		        	<option value="DINNER">晚餐</option>
		    	</select>
		  	</div>
  		</div>
	  	<button id="query" class="layui-btn layui-btn-normal" lay-filter="getPro" lay-submit>查询</button>
	</form>
				
	<div id="divtable" style="margin-top: 20px"></div>
	
	<div id="view" hidden="hidden"></div>
	
	<script id="demo" type="text/html">
  		<div>
			<table id="menu" style="margin: 20px 20px">
  			{{#  layui.each(d.list, function(index, item){ }}
    			<tr style="text-align: center; font-size: 12px">
      				<td style="height: 20px; width: 80px">{{ item.productName }}</td>
      				<td style="height: 20px; width: 200px">{{ item.productPrice }}分</td>
    			</tr>
			
  			{{#  }); }}

				<tr style="height:40px; text-align: center; font-size: 12px"><td style="height: 20px;width: 80px">总计:</td><td style="height: 20px; width: 200px">{{d.total}}分</td></tr>
			</table>
			
  			{{#  if(d.list.length === 0){ }}
    		
  			{{#  } }} 
  		</div>
	</script>
	
</div>

	<script type="text/javascript">
		  layui.use(['element','layer','laytpl','upload','laydate','laypage','form','table'], function(){
		  $ = layui.jquery;//jquery
		  lement = layui.element;//面包导航
		  layer = layui.layer;//弹出层
		  laytpl = layui.laytpl;//模板引擎
		  laypage = layui.laypage;//分页
		  upload = layui.upload;
		  laydate = layui.laydate;
		  form = layui.form;
		  table = layui.table;
		
		$("#query").click(function() {
			getTables();
			return false;
		});
		
		
		Date.prototype.format = function(format) { 
       	var date = { 
              "M+": this.getMonth() + 1, 
              "d+": this.getDate(), 
              "h+": this.getHours(), 
              "m+": this.getMinutes(), 
              "s+": this.getSeconds(), 
              "q+": Math.floor((this.getMonth() + 3) / 3), 
              "S+": this.getMilliseconds() 
       }; 
       if (/(y+)/i.test(format)) { 
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length)); 
       } 
       for (var k in date) { 
              if (new RegExp("(" + k + ")").test(format)) { 
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length)); 
              } 
       } 
       return format; 
	   } 

	   var d = new Date().format('yyyy-MM-dd'); 
	   
	   $("#chooseDate").val(d);
		
	  
		
		//执行一个laydate实例
  		laydate.render({
    		elem: '#chooseDate' //指定元素
  		});
		
		  //获取餐桌列表
		  getTables();
		  
		});
		
		
	 /*添加*/
      function distributor_add(title,url,w,h){
          x_admin_show(title,url,w,h);
      }
      
      
     
           
      
	</script>
		
		

	

	
	
<script type="text/javascript">
	
	
	
	function getTables() {
		
		
	   
	   var checkedDate = $("#chooseDate").val();
		var currentType = $("#type").val();
		
		
		$.get("list.do?date="+checkedDate+"&atype="+currentType,function(data){
   			var tables = data.tables;
   			$("#divtable").empty();
            $.each(tables,function(i,table) {
            	var green = '<div class="green">';
            	var red = '<div class="red">';
            	var yellow = '<div class="yellow">';
            
            	
            	if(table.tableStatus == 'FREE') {
            		var color = green;
            	} else if(table.tableStatus == 'PAID') {
            		 var color = yellow;
            	} else {
            		var color = red;
            	}
            	
            	var div = '<div onclick="getTableId('+table.tableId+','+table.tableNo+')">'
            			+ 	color  
            			+	'<div style="float:right" id="table-'+table.tableId+'" onclick="deleteTable(id);return false;"><div style="color:red">X</div></div>'
            			+		'<p>桌号:'+table.tableNo+'</p>'
            			+		'<p>容量:'+table.size+'人</p>'
            			+		'<p>房间名:'+table.name+'</p>'
            			+		'<p>账单金额:'+table.bill+'元</p>'
            			+	'</div>'
            			+   '</div>'
            			+ '</div>';
            			
            	$("#divtable").append(div);
            	
            	
　　			});

			
			
       	})
	}
	
	//获取桌号以及其它信息
	function getTableId(tableId,tableNO) {
		
		var checkedDate = $("#chooseDate").val();
		var currentType = $("#type").val();
		
		$.ajax({
	        type:"post",
	        url:"showMenu.do",
	        data:{"tableId":tableId,"date":checkedDate, "type":currentType},
	        success:function(map){
	        	console.log(map);
	        	var data = { //数据
				  "list":map.tableOrders,
				  "tableId":tableId,
				  "total":map.total
				}
				var getTpl = demo.innerHTML
				,view = document.getElementById('view');
				laytpl(getTpl).render(data, function(html){
				  view.innerHTML = html;
				});
	        	
	        	layer.open({
	        		title:"桌号"+tableNO+"的菜单信息",
		 	 		type: 1,
		  			skin: 'layui-layer-molv', //样式类名
		  			area: [600+'px', 450 +'px'],
		  			anim:2,  //动画类型
		 	 		shadeClose: true, //开启遮罩关闭
		 	 		btn:["验单","打印"],
		 	 		yes:function(index){
		 	 			
		 	 			$.ajax({
		 	 				type:"post",
		 	 				url:"checkOrders.do",
		 	 				data:{"tableStatusId":map.tableStatusId},
		 	 				success:function() {
		 	 					layer.msg('验单成功!', {icon: 6}); 
		 	 					
		 	 					getTables();
		 	 				}
		 	 			
		 	 			});
		 	 		},
		 	 		btn2:function(index){
		 	 		// 此处应为商家名
		 	 		 var ht = $("#menu").html();
		 	 		
		 	 		$("#menu").print({
                        //Use Global styles
                        globalStyles : false,
                        //Add link with attrbute media=print
                        mediaPrint : false,
                        //Print in a hidden iframe
                        iframe : false,
                        //Don't print this
                        noPrintSelector : ".avoid-this",
                        //Add this at top
                        prepend : ht,
                        // 广告位
                        //Add this on bottom
                        append : "<hr/><img src='<%=request.getContextPath() %>/webtheme/theme/img/admin/smcode.png' height='200' width='200'>"
                       
                    });
	 	 				
						
		 	 		},
		  			content: $("#view")
				});

	        }
	    })
	}
	
	
	
	
	
	
	
	function deleteTable(id) {
		var o = event;
		layer.confirm('确认要删除吗？',function(index){
				layer.close(index);
                    //发异步删除数据
                    $.ajax({
                    type:"post",
                    url:"del.do",
                    data:{"tableId":id},
                    success:function(msg){
                    	if(msg=="success"){
                    		
                    		layer.msg('已删除!',{icon:1,time:1000});
                    		getTables();
                    		
                    	}
                    	
                    }
                    })
                });
             
              
                
                o.cancelBubble = true; 
		return false;
	}
</script>

</body>
</html>