<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>
<script src="lib/echarts.min.js"></script>
</head>

<body class="layui-layout-body" style="overflow:auto">

   <section class="panel" style="width:10%;float:left;">
       <div class="panel-heading">营业总收入</div>
       <div class="panel-body">
           <div class="echarts" id="allget" style="height:200px;"></div>
       </div>
   </section>
   
   <section class="panel" style="width:10%;float:left;margin-left:2%">
       <div class="panel-heading">营业利润</div>
       <div class="panel-body">
           <div class="echarts" id="fleft" style="height:200px;"></div>
       </div>
   </section>
   
   
   <section class="panel" style="width:10%;float:left;margin-left:2%">
       <div class="panel-heading">净利润</div>
       <div class="panel-body">
           <div class="echarts" id="realleft" style="height:200px;"></div>
       </div>
   </section>
   
   <section class="panel" style="width:10%;float:left;margin-left:2%">
       <div class="panel-heading">净资产收益率</div>
       <div class="panel-body">
           <div class="echarts" id="returnassets" style="height:200px;"></div>
       </div>
   </section>
   
   
   <section class="panel" style="width:10%;float:left;margin-left:2%">
       <div class="panel-heading">营业现金流</div>
       <div class="panel-body">
           <div class="echarts" id="realcash" style="height:200px;"></div>
       </div>
   </section>
   
   
   <section class="panel" style="width:10%;float:left;margin-left:2%">
       <div class="panel-heading">投资现金流</div>
       <div class="panel-body">
           <div class="echarts" id="postcash" style="height:200px;"></div>
       </div>
   </section>
   
   <section class="panel" style="width:10%;float:left;margin-left:2%">
       <div class="panel-heading">融资现金流</div>
       <div class="panel-body">
           <div class="echarts" id="getcash" style="height:200px;"></div>
       </div>
   </section>
    
       
   

    
   
	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('allget'));
        // 指定图表的配置项和数据
        var option = {tooltip: {trigger: 'axis'},
            legend: {data:['营业总收入']},
            grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
            toolbox: {feature: {saveAsImage: {}}},
            xAxis: {type: 'category',boundaryGap: false,data: ${baselabels}},
            yAxis: {type: 'value'},
            series: [{name:'营业总收入',type:'line',stack: '总收入',data:${allget}}]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>
	
	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('fleft'));
        // 指定图表的配置项和数据
        var option = {tooltip: {trigger: 'axis'},
            legend: {data:['营业利润']},
            grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
            toolbox: {feature: {saveAsImage: {}}},
            xAxis: {type: 'category',boundaryGap: false,data: ${baselabels}},
            yAxis: {type: 'value'},
            series: [{name:'营业利润',type:'line',stack: '利润',data:${fleft}}]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>
	
	
	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('realleft'));
        // 指定图表的配置项和数据
        var option = {tooltip: {trigger: 'axis'},
            legend: {data:['净利润']},
            grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
            toolbox: {feature: {saveAsImage: {}}},
            xAxis: {type: 'category',boundaryGap: false,data: ${baselabels}},
            yAxis: {type: 'value'},
            series: [{name:'净利润',type:'line',stack: '净利润',data:${realleft}}]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>
	
	
	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('returnassets'));
        // 指定图表的配置项和数据
        var option = {tooltip: {trigger: 'axis'},
            legend: {data:['净资产收益率']},
            grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
            toolbox: {feature: {saveAsImage: {}}},
            xAxis: {type: 'category',boundaryGap: false,data: ${baselabels}},
            yAxis: {type: 'value'},
            series: [{name:'净资产收益率',type:'line',stack: '净资产收益率',data:${returnassets}}]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>
	
	
	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('realcash'));
        // 指定图表的配置项和数据
        var option = {tooltip: {trigger: 'axis'},
            legend: {data:['营业现金流']},
            grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
            toolbox: {feature: {saveAsImage: {}}},
            xAxis: {type: 'category',boundaryGap: false,data: ${baselabels}},
            yAxis: {type: 'value'},
            series: [{name:'营业现金流',type:'line',stack: '营业现金流',data:${realcash}}]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>
	
	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('postcash'));
        // 指定图表的配置项和数据
        var option = {tooltip: {trigger: 'axis'},
            legend: {data:['投资现金流']},
            grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
            toolbox: {feature: {saveAsImage: {}}},
            xAxis: {type: 'category',boundaryGap: false,data: ${baselabels}},
            yAxis: {type: 'value'},
            series: [{name:'投资现金流',type:'line',stack: '投资现金流',data:${postcash}}]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>
	
	
	<script>
		 // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('getcash'));
        // 指定图表的配置项和数据
        var option = {tooltip: {trigger: 'axis'},
            legend: {data:['融资现金流']},
            grid: {left: '3%',right: '4%',bottom: '3%',containLabel: true},
            toolbox: {feature: {saveAsImage: {}}},
            xAxis: {type: 'category',boundaryGap: false,data: ${baselabels}},
            yAxis: {type: 'value'},
            series: [{name:'融资现金流',type:'line',stack: '融资现金流',data:${getcash}}]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
	</script>
	
	
</body>
</html>
