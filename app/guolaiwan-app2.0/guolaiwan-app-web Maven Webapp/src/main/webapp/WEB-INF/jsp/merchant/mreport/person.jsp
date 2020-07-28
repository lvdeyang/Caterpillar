<%@page import="pub.caterpillar.weixin.constants.WXContants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String weburl=WXContants.Website;
%>

<!DOCTYPE html>
<html style="width:100%;height:100%;margin:0;padding:0;overflow-y:hidden">
<head>
<meta charset="utf-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path %>/layui/lib/layui/css/layui.css" media="all">
<style>
	*{margin:0;padding:0;}
	li{list-style: none;}
	a{color: inherit; text-decoration:none;outline:none;}
	a{text-decoration:none;cursor: pointer}
	a:hover{color: #E26B0F;text-decoration: none;outline:none}
	a.ie6:hover{zoom:1}
	a:focus{outline:none}
	a:hover,a:active{outline:none}:focus{outline:none}
	body{
		width:100%;height:100%;overflow:hidden;
	}
	
	.container{
		width:100%;height:100%;top:0;
		background:url("<%= request.getContextPath() %>/lib/images/bg.png") no-repeat;
	}
	.img{
		width:100%;height:100%;position:absolute;top:0;
	}
	.side_c{
	    width:100%;height:20%;margin-top:2%
	}
	.innerimage{
	   width:100%;height:20%;position:absolute;
	}
	.innerimage_c{
	   width:100%;height:30%;position:absolute;
	}
	.center_c{
	    width:100%;height:30%
	}
  
	

</style>
</head>
<body>
<div class="container" style="position:absolute;top:0;">
	<image class="img" src="<%= request.getContextPath() %>/lib/images/dakuang.png"/>

 
	      <div class="layui-row grid-demo" style="height:90%;margin-top:6%">
	        <div class="layui-col-md3" style="height:100%;">
	             <div class="side_c">
	                <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/tianqi_kuang.png">
	                <div style="position:absolute;width:100%;height:20%;top:0;left:0">
		                <iframe style="margin-left:5%;margin-top:15%;float:left;" width="50%" scrolling="no" height="100%" frameborder="0" allowtransparency="true" 
							src="//i.tianqi.com/index.php?c=code&id=8&color=%23FFFFFF&bgc=%23&bdc=%23&icon=1&num=1&site=19">
						</iframe>
						<div id="server_time" style="width:30%;height:5%;float:left;color:#FFF;margin-top:20%;margin-left:5%"></div>
	                </div>
	                
	             </div>
	             <div class="side_c">
	                <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/renlian_kuang.png">
	             </div>
	             <div class="side_c">
	                <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/xinbie_kuang.png">
	                <div id="xingbie" style="width:100%;height:100%;margin-top:0;left:0"></div>
	             </div>
	             <div class="side_c">
	                <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/jiudian_kuang.png">
	             </div>
	        </div>
	        <div class="layui-col-md6" style="height:100%;">
	            <div class="center_c">
	                <image class="innerimage_c" src="<%= request.getContextPath() %>/lib/images/keliuliang.png"> 
	            </div>
	            <div class="center_c">
	                <image class="innerimage_c" src="<%= request.getContextPath() %>/lib/images/jingdiantongji.png">
	            </div>
	            <div class="center_c">
	                 <image class="innerimage_c" src="<%= request.getContextPath() %>/lib/images/qitianliuliang.png">
	            </div>
	        </div>
	        <div class="layui-col-md3" style="height:100%;">
	             <div class="side_c">
	                 <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/cheliangzhuapai.png"> 
	             </div>
	             <div class="side_c">
	                 <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/chetongji.png"> 
	             </div>
	             <div class="side_c">
	                 <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/cheguishudi.png"> 
	             </div>
	             <div class="side_c">
	                 <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/jinqingfenxi.png"> 
	             </div>
	        </div>
	      </div>

 
	  
  
</div>
	
	
	
</div>

<script type="text/javascript" src="<%= request.getContextPath() %>/webtheme/theme/js/jquery.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/webtheme/theme/js/mreport/jquery.easy_slides.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/layui/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/require.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/echart/dist/echarts.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/echart/dist/echarts-all.js"></script>
<script type="text/javascript">
   $(function(){
	

		window.setInterval(function(){
		   run();
		}, 1000);

		function run() {
			var localTime = new Date();
			var time = "";
			var year = localTime.getFullYear();
			var month = localTime.getMonth() + 1;
			if(month < 10){
				month = "0" + month;
			}
			var day = localTime.getDate();
			if(day < 10){
				day = "0" + day;
			}
			var hour = localTime.getHours();
			var minute = localTime.getMinutes();
			var second = localTime.getSeconds();
			if(hour < 10){
				time += "0" + hour;
			}else{
				time += hour;
			}
			time += ":";
			if(minute < 10){
				time += "0" + minute;
			}else{
				time += minute;
			}
			time += ":";
			if(second < 10){
				time += "0" + second;
			}else{
				time += second;
			}
			$("#server_time").html(year + "-" + month + "-" + day + "  " + time);     
		}
		
		//性别统计
		var xingbieChart = echarts.init(document.getElementById('xingbie')); 
		var optionxingbie = {
		    tooltip: {
		        trigger: 'item',
		        formatter: '{a} <br/>{b}: {c} ({d}%)'
		    },
		    legend: {
		         orient: 'horizontal',                  
		         data: ['男', '女'],
		         x : '70%',
		         y : '20%',
		         textStyle: {
			        color: '#FFF'       // 图例文字颜色
			     }
		    },
		    series: [
		        {
		            name: '游客性别',
		            type: 'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                show: false,
		                position: 'right'
		            },
		            emphasis: {
		                label: {
		                    show: true,
		                    fontSize: '30',
		                    fontWeight: 'bold'
		                }
		            },
		            labelLine: {
		                show: false
		            },
		            data: [
		                {value: 835, name: '男'},
		                {value: 310, name: '女'},
		              
		            ]
		        }
		    ]
		};
		
		xingbieChart.setOption(optionxingbie)
		
		
		
	});
	
	

</script>
	
</body>
</html>