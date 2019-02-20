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
</head>
<body>
	<div class="x-body">
		<form action="getMerchantBySc.do" method="post">
			<input name="merchantName" type="text" value="${merName}" />
			<input type="submit" value="搜索" />
		</form>
		<xblock>
			</span> <a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
			<span class="x-right" style="line-height:40px">共有数据：<span id="count">${count}</span>
			条</span></xblock>
			<table class="layui-table">
			<thead>
				<tr>
					<th>商户名称</th>
					<th>联系电话</th>
					<th>商户地址</th>
					<th>联系人</th>
					<th>板块名称</th>
					<th>板块分类</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="view">

			</tbody>/
		</table>
		<div id="page"></div>

		<script id="alist" type="text/html">
{{#  layui.each(d.list, function(index, item){ }}
                        <tr>
                        <td>
                            {{ item.shopName }}
                        </td>
						<td>
                            {{ item.shopTel }}
                        </td>

						<td>
                            {{ item.shopAddress }}
                        </td>

						<td>
                            {{ item.shopLinkperson }}
                        </td>
						<td>
                             {{ item.modularName }}
                        </td>

						<td>
                             {{ item.modularClass }}
                        </td>
                       
                        <td class="td-manage">
                            <a title="选择" href="javascript:;" onclick="sel('{{ item.id }}','{{ item.shopName }}','{{ item.modularName }}','{{ item.modularCode }}','{{ item.modularClass }}','{{ item.modularClassId }}')" 
                           	style="text-decoration:none">
                            	<i class="layui-icon">选择</i>
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
           var path = '<%=path%>';
            layui.use(['element','laypage', 'layer','laytpl'], function(){
                $ = layui.jquery;//jquery
              lement = layui.element;//面包导航
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              laytpl = layui.laytpl;//模板引擎
              //以上模块根据需要引入
              
               laypage.render({
                elem: 'page'
                ,count:	parseInt($("#count").text())
                ,first: '首页'
                ,last: '尾页'
                ,limit: 5
                ,prev: '<em><</em>'
                ,next: '<em>></em>'
                ,jump: function(obj){
                		getpages(obj.curr);
                		}
                		
                }); 
              
        });
	         
	         
	         //加载数据
              function getpages(pagecurr)
              {
              	$.ajax({
              	   type:"post",
              	   url:"mohuList.do/${merName}",
              	   async:false,
              	   data:{'page':pagecurr,'limit':10},
              	   success:function(msg){
              	   console.log(msg.data);
              	       var data = {
                               "list":msg.data
                         }
                         console.log(data);
               			    var getTpl = alist.innerHTML;
                			laytpl(getTpl).render(data, function(html){
                              view.innerHTML = html;
                			 });
              	   }
              	})
              }
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		
 		function sel(mcid,mcname,mname,mcode,cname,ccode){
 			parent.$("#${mcuuid}").val(mcid);
 			parent.$("#${mcname}").val(mcname);
 			parent.$("input[name='modularName']").val(mname);
      		parent.$("input[name='modularCode']").val(mcode);
      		parent.$("input[name='modularClass']").val(cname);
      		parent.$("input[name='modularClassId']").val(ccode);
      		parent.$("input[name='merMName']").val("");
      		parent.$("input[name='merMId']").val("");
 			parent.$("#${mchref}").attr("href","javascript:openMap('选择分类','"+path+"/admin/merModular/comSel?merchant="+mcid+"','600','400')");
 			parent.layer.close(index);
 		}
          	
            </script>

</body>
</html>