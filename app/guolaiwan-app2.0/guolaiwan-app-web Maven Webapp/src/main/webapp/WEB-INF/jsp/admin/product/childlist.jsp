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
<title>阳光成单系统</title>
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
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>产品管理</cite></a>
			<a><cite>讲解点列表</cite></a>
		</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
	<div class="x-body">
		<xblock>
		<button class="layui-btn"
			onclick="full_show('添加讲解点','addv?productID=${productID}','1000','800')">
			<i class="layui-icon">&#xe608;</i>添加
		</button>
		<button class="layui-btn " onclick="full_show('景区路线','addx?productID=${productID}','1000','800')">
			<i class="layui-icon">&#xe608;</i>景区路线
		</button>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<span class="x-right" style="line-height:40px">共有数据：<span id="count">${count}</span>
			条</span>
			</xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" id="chooseAll" onclick="chooseAll()" ></th>
					<th>id</th>
					<th>子商品名称</th>
					<th>图片</th>
					<th>声音</th>
					<th>经度</th>
					<th>纬度</th>
					<th>微信经度</th>
					<th>微信纬度</th>
					<th>下个关联点</th>
					<th>语言</th>
					<th>讲解范围</th>
					<th>是否讲解</th>
					<th>描述</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="view">

			</tbody>
		</table>
		<div id="page"></div>


<!-- {{#  if(item.pics.length !== 0){ }}	
							{{# for(var i=0;i<item.pics.length;i++){ }}
								{{#  if(item.pics[i] !== null){ }}
									<a href="javascript:;"onclick="full_show('图片描述列表','<%=basePath%>admin/bewrite/list?picId={{ item.pics[i].id }}','1000','800')"><img width="40px" heigth="40px" src="{{ item.webUrl }}{{ item.pics[i].folde }}/{{ item.pics[i].newName }}"></a>
								{{#  } }}							
							{{# }; }}
							{{#  } }} -->


		<script id="alist" type="text/html">
{{#  layui.each(d.list, function(index, item){ }}
						<tr>
                        <td>
                            <input type="checkbox" value="{{ item.uuid }}" name="selected" >
                        </td>
                        <td>
                            {{ item.id }}
                        </td>
						<td>
                            {{ item.childName }}
                        </td>
						<td style="word-break:break-all;">
							
	                        {{ item.childPic }}
                        </td>
						<td>
                            {{ item.chineseGirl }}
                        </td>
						<td>
                            {{ item.childLongitude }}
                        </td>
						<td>
                            {{ item.childLatitude }}
                        </td>
						<td>
                            {{ item.wxChildLongitude }}
                        </td>
						<td>
                            {{ item.wxChildLatitude }}
                        </td>
						<td>
                            {{ item.relevance }}
                        </td>
                        <td>
                            {{ item.lanId }}
                        </td>
                        <td>
                            {{ item.scope }}
                        </td>
                        <td>
                            {{ item.isTaught }}
                        </td>
                        <td>
                            {{ item.content }}
                        </td>
                        <td class="td-manage">
						 <a title="修改" href="javascript:;" onclick="child_exit('编辑','updatev','{{ item.uuid }}','','510')" 
                            style="text-decoration:none">
                                <i class="layui-icon">&#xe642;</i>
                            </a>     
                       <a title="删除" href="javascript:;" onclick="child_del(this,'{{ item.uuid }}')" 
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

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
    <script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
	var productID = '${productID}';	
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
     	
  		
 		//以上模块根据需要引入
 		fenye();
 		/* $(function(){
	 		$("#view tr").mouseover(function(){
       			$(this).css("background-color","#009688").siblings().css("background-color", "#FFFFFF");
    		});
	 	}) */
	 	//模块
 		form.on('select(filter)',function(data){
 			$.ajax({
			type:"post",
			url:"getProBymc.do",
			async:false,
            data:{"modularCode":data.value},
			success:function(msg){
				var sbHtml="<option value=''></option>";
				 classn = msg.classn;
        		console.log(classn);
        		for(i=0;i<classn.length;i++){
        			classn[i].classCode;
        			classn[i].className;
        			sbHtml=sbHtml+"<option value='"+classn[i].classCode+"'>"+classn[i].className+"</option>"
        		}
        		//清空下拉框
        		$("#classCode").html(sbHtml);
        		 form.render();
				}
			})
 		});
	 	
	 	
	 	
	 	form.on('submit(getPro)',function(data){
	 		data.field.cityCode="${cityCode}";
	 		console.log("${cityCode}");
 			var allcount;
 			console.log(data.field);
 			$.ajax({
			type:"post",
			url:"getProductByC.do",
			async:false,
            data:data.field,
			success:function(msg){
				 allcount = msg.getcount;
				 $("#count").html(allcount);
        		console.log(allcount);
				}
			})
 			laypage.render({
                elem: 'page'
                ,count: allcount
                ,first: '首页'
    			,last: '尾页'
    			,page:false
    			,limit: 10
    			,prev: '<em><</em>'
    			,next: '<em>></em>'
    			,layout: ['page', 'skip']
                ,jump: function(obj){
                	data.field.pagecurr=obj.curr;
                	console.log(data.field);
                	getProductBy(data);
                   }
              });
              return false;
 		});
});
	 
	
	
	function product_add(title,url,w,h){
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
	
	//异步加载列表
	function getlist(pagecurr){
		console.log("llak");
		$.ajax({
			type:"post",
			url:"childList.do",
			async:false,
            data:{'pagecurr':pagecurr,'productID':productID},
			success:function(msg){
			
				var data = {
           		"list":msg.childList
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
	function child_del(obj,id){
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
    function child_exit(title,url,id,w,h){
    	x_admin_show(title,url+"?productID="+productID+"&uuid="+id,w,h); 
    }
    
    //查询列表
	function getProductBy(data){
 				$.ajax({
					type:"post",
					url:"getProductBy.do",
					async:false,
            		data:data.field,
					success:function(msg){
					var data = {
           				"list":msg.getlist
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
 			
    
    //审核意见
    function advice_show(advice){
    	  layer.open({
        	type: 1
        	,title: false //不显示标题栏
        	,closeBtn: false
        	,area: '300px;'
        	,shade: 0.8
        	,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        	,btn: ['知道啦']
        	,btnAlign: 'c'
        	,moveType: 1 //拖拽模式，0或者1
        	,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">审核意见：<br/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+advice+'</div>'
        	
      });
    }
    
   
	
</script>

</body>
</html>