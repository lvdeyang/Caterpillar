<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/image";
%>
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
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/layui/css/x-admin.css" media="all">
<style type="text/css">
.layui-table img{     max-width: 1000px;}/* 照片的最大宽度  */
</style>
</head>
<body>
	<div class="x-nav">
		<span class="layui-breadcrumb"> <a><cite>首页</cite></a> <a><cite>分公司管理</cite></a>
	</div>
	<div class="x-body">
	<xblock>
		<a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right"  href="javascript:getCompanyList();" title="刷新"><i class="layui-icon" style="line-height:30px">ဂ</i></a>
		
		<button type="button" class="layui-btn layui-btn-normal layui-btn-sm" onclick="addBiao('添加','add','500','600')">添加分公司</button>
		</xblock>
		<table id="companyList" lay-filter="companyList"></table>
	</div>
	
	

	<script src="<%=request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
	<script src="<%=request.getContextPath() %>/layui/js/x-layui.js" charset="utf-8"></script>
	<script>
	
	layui.use(['element','layer','laytpl','upload','laydate','form','table'], function(){
    	$ = layui.jquery;//jquery
        lement = layui.element;//面包导航
        layer = layui.layer;//弹出层
        laytpl = layui.laytpl;//模板引擎
         form = layui.form;//分页 *//***/
        upload = layui.upload;
        laydate = layui.laydate;
        table = layui.table;

        getCompanyList();
        //编辑
        table.on('edit(companyList)',function(obj){
        	layer.load();
        	var data = obj.data
        	,field = obj.field
        	,value = obj.value;
			console.log(value);
        	$.ajax({
        		type:'post',
        		url:'edit.do',
        		data:{'id':data.id,'field':field,'value':value},
        		success:function(msg){
        		console.log(msg);
        			layer.closeAll("loading");
        			if(msg=="success"){
        				layer.msg("修改成功！");
        			}else if(msg=='hasComCode'){
        				layer.msg("公司编号存在！",{icon:5,time:1500});
        				getCompanyList();
        			}
        		}
        	})
        })
        
        //是否显示
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
                           
                        }else{
                           layer.msg("系统错误！",{icon:5,time:1000}); 
                        }
                    }

                })

                layer.tips(this.value+' : '+str,obj.othis);
              });
		
		$(".citySelect").change(function(){
		
			console.log("ce");
		})
      
});


	//获取列表
	function getCompanyList(){
		table.render({
			elem:'#companyList'
   			,method:'post'
   			,url:'list.do'
   			,page:true
      		,limits: [10,30,50,100]
      		,limit: 10
   			,cols: [[
        		{type:'checkbox'}
        		,{field: 'id',title: 'ID',align:  'center',sort: true,width:60}
        		,{field: 'comCode', align:'center',title: '编号',sort: true,width:80} 
        		,{title: '级别',align:'center', width:80,sort: true,templet:"#levelTpl"}    
   				,{field: 'comName',align:'center', title: '名称',sort: true,edit:'text'} 
   				,{field: 'address',align:'center', title: '公司地址',sort: true,edit:'text'} 
      			/* ,{align:'center', title: '所在城市',width:120,templet:'#selectTpl'} */
      			,{align:'center', title: '所在城市',width:120,templet:'#cityTpl'}
      			,{align:'center', title: '分公司图片',width:120,templet:'#picTpl'}
      			,{align:'center', title: '是否启用',width:120,templet:'#switchTpl'}
      			,{fixed:'right',width:200,title:'操作',unresize:true,align:'center',templet:"#zsgc"}
   			]]
		})
	}
	//添加
	function addBiao(title,url,w,h){
		$.ajax({
			url:'add.do',
			type:'post',
			success:function(msg){
				console.log(msg);
				layer.closeAll('loading');
				if(msg=='success'){
					getCompanyList();
				}	
			
			}
		
		})
		return false;
		
		
	}
	function open_win(title,url,w,h){
		x_admin_show(title,url,w,h)
	}

		

	//删除
	function del(uuid,cType){
		layer.confirm("真的要删除么？",function(index){
			layer.close(index);
			if(cType=='总公司'){
				layer.msg("总公司不能删除！",{icon:5});
				return false;
			}
			layer.load();
			$.ajax({
				url:'del.do',
				type:'post',
				data:{'id':uuid},
				success:function(msg){
					console.log(msg);
					layer.closeAll('loading');
					if(msg=='success'){
						layer.msg("删除成功！");
						getCompanyList();
					}	
				}
			})
		})
		
	}
	
	function show_pic(id){
		idn = "#"+id;
        $(idn).css('width','600px').css('height','400px');
        layer.open({
  			type: 1,
  			title: false,
  			closeBtn: 0,
  			area:'600',
  			
  			skin: 'layui-layer-nobg', //没有背景色
  			shadeClose: true,
  			content: $(idn),
  			end: function(){
  				$(idn).css("height","35px");
				$(idn).css("width","35px");
  			}
		})
    }
    
    
    function  changeCity(obj,comId){
    	var value = $(obj).find("option:selected").val();
    	if(value=="-1"){
    		return false;
    	}
    	layer.load(2); 
    	var text = $(obj).find("option:selected").text();	
    	$.ajax({
    		type:'post',
    		url:'updateCity.do',
    		data:{"id":comId,"cityCode":value,"cityName":text},
    		success:function(msg){
    			if(msg=="success"){
    				layer.closeAll("loading");
    				$("#cnSpan"+comId).show();
    				$("#citySpan"+comId).hide();
    				$("#cn"+comId).text(text);
    				layer.msg("绑定成功！",{icon:1});
    				
    			}
    		}
    	})
    	
    }
    function editCity(id){
    	var cnSpan = "#cnSpan"+id;
    	var citySpan = "#citySpan"+id;
    	console.log("1");
    	$(cnSpan).hide();
    	$(citySpan).show();    
    
    }
    

  
	         

</script>
<!-- <a class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:open_win('选择城市','chooseC','500','500')">城市</a> -->
<script type="text/html" id="zsgc">
<div class="layui-btn-group">
	<a class='layui-btn layui-btn-primary layui-btn-xs' href="javascript:open_win('选择图片','<%=path%>/admin/picture/addlist?&sImg=comImg{{d.id}}&sId={{ d.id }}&source=company','600','500')">选择图片</a>
	<a class='layui-btn layui-btn-danger layui-btn-xs' href='javascript:del("{{ d.id }}","{{ d.cType }}")'>删除</a>
</div>
</script>
<script type="text/html" id="switchTpl">
	<input type="checkbox" name="enable" id='{{d.id}}' value='{{ d.comName }}'  lay-skin="switch" lay-text="是|否" lay-filter="enable" {{ d.enable == 1 ? 'checked' : '' }} {{ d.cType == '总公司' ? 'disabled' : '' }}>
</script>
<script type="text/html" id="picTpl">
	<a href="javascript:show_pic('comImg{{d.id}}')"><img src='${sysConfigPO.webUrl}{{ d.pic }}' id='comImg{{d.id}}' style="width:35px;height:35px" alt='' ></a></div>
</script>
<script type="text/html" id="levelTpl">
	 {{#  if(d.cType === '总公司'){ }}
     	<span stayle="color: #F581B1;">{{ d.cType }}</span>
  	 {{#  } else { }}
     {{ d.cType }}
     {{#  } }}
</script>
<script type="text/html" id ="cityTpl">
	<span id="cnSpan{{d.id}}"><span id="cn{{d.id}}">{{d.cityName}}</span>&nbsp;<a href="javascript:editCity({{d.id}})" style="text-decoration:underline">编辑</a></span>
	<span id="citySpan{{d.id}}" hidden="hidden">
	<select lay-ignore  style="border:1px solid #5c5c5c;" id='city{{d.id}}'  onchange="changeCity(this,{{d.id}})">
		<option value="-1">请选择</option>
		<c:forEach items="${citys}" var="city">
			<option value="${city.cityCode}" {{ d.cityCode=='${city.cityCode}'?'selected':''}}>${city.cityName}</option>
		</c:forEach>
	</select>
	</span>
</script>


</body>
</html>