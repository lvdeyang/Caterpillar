<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	href="<%=request.getContextPath()%>/layui/css/x-admin.css" media="all">
</head>
<body>


	
	<div class="x-body">
		<div class="layui-inline">
      		<label class="layui-form-label" style= "width:90px">日期时间范围</label>
      		<div class="layui-input-inline">
        		<input type="text" class="layui-input" id="timeframe" placeholder=" - " style= "width:300px">
      		</div>
    	</div>
		<a class="layui-btn layui-btn-small"
			style="line-height:1.6em;margin-top:3px;float:right"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="layui-icon" style="line-height:30px">ဂ</i></a>
			
		
		<xblock>
		
		<button type="button" class="layui-btn" id="testListAction">开始上传</button>
		<button class="layui-btn layui-btn-danger" onclick="delAll()">
			<i class="layui-icon">&#xe640;</i>批量删除
		</button>
		<span class="x-right" style="line-height:40px">共有数据：<span
			id="count">${count}</span> 条
		</span></xblock>
		<table class="layui-table">
			<thead>
				<tr>
					<th><input type="checkbox" id="chooseAll"
						onclick="chooseAll()"></th>
					<th>操作时间</th>
					<th>操作人</th>
					<th>操作模块</th>
					<th>操作内容</th>
					<th>参数</th>
					<th>IP</th>
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
                            <input type="checkbox" value="{{ item.uuid }}" name="selected">
                        </td>
						<td>
                            {{ item.updateTime }}
                        </td>
                        <td>
                            {{ item.operator }}
                        </td>
                        <td>
                            {{ item.menu }}
                        </td>
						<td>
                            {{ item.operatName }}
                        </td>
						<td>
                            {{ item.params }}
                        </td>
                        <td>
                            {{ item.ip }}
                        </td>
						<td>
                        	<a title="删除" href="javascript:;" onclick="pic_del(this,'{{ item.uuid }}')" 
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

	<script src="<%=request.getContextPath()%>/layui/lib/layui/layui.js"
		charset="utf-8"></script>
	<script>
		//时间格式化
		Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
	}
	var ipage ;	
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
 		
 		//日期时间范围
  		laydate.render({
    		elem: '#timeframe'
    		,type: 'datetime'
    		,range: true
  		});
  	});
	     
	//异步加载列表
	function getlist(pagecurr){
		$.ajax({
			type:"post",
			url:"logList.do",
			async:false,
            data:{'pagecurr':pagecurr},
			success:function(msg){
			console.log(msg.list);
				var data = {
           		"list":msg.list
           		
       	 		}
       	 		
				//操作模板引擎
				var getTpl = alist.innerHTML;
				laytpl(getTpl).render(data, function(html){
            		view.innerHTML = html;
        		});
			}
		})
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
            			getlist(ipage);
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
                ,count: ${allpages}*10
                ,first: '首页'
    			,last: '尾页'
    			,prev: '<em><</em>'
    			,next: '<em>></em>'
                ,jump: function(obj){
                	ipage = obj.curr;
                	getlist(obj.curr);
                   }
              });
 		}
</script>

</body>
</html>