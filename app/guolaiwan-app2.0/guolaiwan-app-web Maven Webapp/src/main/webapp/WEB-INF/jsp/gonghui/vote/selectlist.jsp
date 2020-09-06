<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<div class="x-body">
	<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:location.replace(location.href);" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
		<xblock>
		<span class="x-right" style="line-height:40px">共有数据：<span id="count">${count}</span>条</span>
	    </xblock>
		<table class="layui-table">
			<thead>
				<tr>
					
					<th>文件名</th>
					<th>上传时间</th>
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
                            {{ item.oldName }}
                        </td>
                       
						<td>
                            {{ item.update }}
                        </td>
                      
						<td>
                        	<a title="选择" href="javascript:;" onclick="sel('{{ item.folde }}','{{ item.newName }}')" 
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

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script>
	
	var ipage ;	
	var ilimit;
	var webUrl = "{sysConfig.webUrl}";
	var doneCount = 0;
	layui.use(['element','layer','laytpl','upload','laydate','laypage'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
        laypage = layui.laypage;//分页
        upload = layui.upload;
        laydate = layui.laydate;
       
 		//以上模块根据需要引入
 		fenye();
});
	         
	//异步加载列表
	function getlist(pagecurr,ilimit){
		$.ajax({
			type:"post",
			url:"voiceList.do",
			async:false,
            data:{'pagecurr':pagecurr,'ilimit':ilimit},
			success:function(msg){
			
				var data = {
           		"list":msg.list
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
	
	function fenye(){
 			laypage.render({
                elem: 'page'
                ,count: parseInt($("#count").text())
                ,first: '首页'
    			,last: '尾页'
    			,prev: '<em><</em>'
    			,next: '<em>></em>'
    			,layout: ['limit', 'page', 'skip']
                ,jump: function(obj){
                	ilimit =obj.limit;
                	ipage = obj.curr;
                	console.log(obj.count);
                	getlist(obj.curr,obj.limit);
                   }
              });
 		}
 		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
 		function sel(folde,url)
 		{
 		  //parent.$("#${sel}").val("/"+folde+"/"+url);
 		 
 		  console.log("${sel}");
 		  $.ajax({
				url:'voice.do?id=${id}&voice='+url,
				type:'post',
				success:function(msg){
					console.log(msg);
					layer.closeAll('loading');
					if(msg=='success'){
						layer.msg("删除成功！");
						parent.getactivityList();
					}	
				}
			});
 		  parent.layer.close(index);
 		}
</script>

</body>
</html>