<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
 <head>
	<meta charset="UTF-8">
	<meta name="Generator" content="s">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> 
	<meta name="renderer" content="webkit">
	<title>过来玩租车</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/webtheme/theme/js/jquery.js"></script>
    <script src="<%=path%>/layui/lib/bootstrap/js/bootstrap.min.js"></script>
    <link href="<%=path%>/layui/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <script src="<%=path%>/layui/lib/bootstrapvalidate/js/bootstrapValidator.min.js"></script>
    <link href="<%=path%>/layui/lib/bootstrapvalidate/css/bootstrapValidator.min.css" rel="stylesheet" />
	
    <link rel="shortcut icon" type="image/x-icon" href="<%=path %>/webtheme/theme/icon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="<%=path %>/webtheme/theme/css/footerfull.css">
 </head>
 <body>
 <div id="d3">
 <p id="p1">1</p>
 <p>1</p>
 </div>
<c:import url="${path}/web/ltop"></c:import>
<div class="page-wrap">
		<div style="width: 200px;height: 200px;top:40%;left:50%;margin-left:-100px;margin-top:-100px;position: absolute;" >
		<div style="margin-left:-100px">
		<div style="padding-bottom: 20px"><h3>请选择路线:</h3></div>		
		<form action="<%=path%>/car/index" method="get" id="car">
            <div class="form-group">
                <label>出发地</label>
                <input type="text" class="form-control" name="fromArea" />
            </div>
            <div class="form-group">
                <label>目的地</label>
                <input type="text" class="form-control" name="goArea" />
            </div>
            <div class="form-group">
                <button   class="btn btn-sm btn-primary" onclick="sub()">提交</button>
                <div style="float:right">
                	<button class="btn btn-sm btn-link" onclick="sub()">我要包车</button>
                </div>
            </div>
        </form>
        </div>
	</div>
</div>
</div>
<div class="site-footer">
	<c:import url="${path}/web/foot"></c:import>
</div>
<div>
<svg>
</svg>
</div>
<div id="d3h">
</div>
<script type="text/javascript">
	var basePath = '<%=basePath%>';
	$(function(){
	
	 $('form').bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　		feedbackIcons: {
                　　	valid: 'glyphicon glyphicon-ok',
                　　　	invalid: 'glyphicon glyphicon-remove',
                　　　	validating: 'glyphicon glyphicon-refresh'
            　　　　　　},
            fields: {
                fromArea: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '出发地不能为空'
                        }
                    }
                },
                goArea: {
                    validators: {
                        notEmpty: {
                            message: '目的地不能为空'
                        }
                    }
                }
            }
        });
	
	
	});
	
	function sub(){
		console.log("dfd");
		$('form').submit();	
	}
	/* layui.use(['layer'], function(){
                $ = layui.jquery;
              var layer = layui.layer;
              });
	 */
	/* function car(type){
		//1.非空校验
		var fromArea = $("#fromArea").val();
		var goArea = $("#goArea").val();
		if(fromArea==""){
			layer.msg("请选择出发城市！",{cion:5});
			return false;
		}
		if(goArea==""){
			layer.msg("请选择目的城市！",{cion:5});
			return false;
		}
		
		
		
		if(type=="cartime"){
			window.location.href=basePath+"car/reseat/flight?fromArea="+fromArea+"&goArea="+goArea;
		}else if(type=="contract"){
			window.location.href=basePath+"car/contract/flight?fromArea="+fromArea+"&goArea="+goArea;
		}
	} */

</script>
</body>
<!-- d3 -->
<script src="<%=path%>/layui/lib/d3/d3.js" charset="utf-8"></script>
<script>
		d3.select("body").selectAll("p").text("d3helloWord").style("color","red");
		
		//datum
		var p = d3.select("body").selectAll("p");//获取所有p的选择集
		var str = "apple";
		p.datum(str);   							//绑定数据
		p.text(function(d,i){
			return "第"+i+"个元素绑定的是"+d;
		})                                      //改变内容
		
		//绑定数组
		var shuzu =["i like dogs","i like you"];
		p.data(shuzu).text(function(d,i){
			return d;
		});
		
		//
		var div1 = d3.select("#d3");
		div1.append("p").text("插到后面了");
		div1.insert("p","#p1").text("插到前面了");
		//删除第一个p元素
		var p1=d3.select("p");
		p1.remove();
		
		
	/* 	//给body添加一个画布
		width="200px";
		height="300px";
		var svg = d3.select("#d3").append("svg").attr("height",height).attr("width",width); */
		
		//可视化
		var dataset = [ 250 , 210 , 170 , 130 , 90 ];
		var rectHeight = 25;   //每个矩形所占的像素高度(包括空白)
		var svg = d3.select("svg");
		var rects = svg.selectAll("rect").data(dataset).enter().append("rect")
		
		rects.attr("y",function(d,i){
			return i*rectHeight;
		
		}).attr("x","20").attr("width",function(d){
			return d;
		}).attr("height",rectHeight-2).attr("fill","steelblue");



	var svg2 = d3.select("#d3h").append("svg").attr("width","300px").attr("height","200px");
	svg2.select("rect").data(dataset).enter.append("rect").attr("x","20").attr("y",function(d,i){
				
	})




</script>
</html>
