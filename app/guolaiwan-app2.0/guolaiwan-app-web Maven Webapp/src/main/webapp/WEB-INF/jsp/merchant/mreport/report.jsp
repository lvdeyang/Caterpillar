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
<title>过来玩系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

</head>
<body style="width:100%;height:100%;margin:0;padding:0">
<div style="width:100%;height:100%;margin:0;padding:0"><image style="width:100%;height:100%;margin:0;padding:0" src="<%= request.getContextPath() %>/lib/images/backdata.jpg"/>

<div id="distribute" style="width:20%;height:25%;position:absolute;left:2%;top:10%;z-index:150"></div>
<div style="width:20%;height:25%;position:absolute;left:2%;top:40%;z-index:150"></div>
<div id="park" style="width:20%;height:25%;position:absolute;left:2%;top:70%;z-index:150"></div>

<div style="width:50%;height:50%;position:absolute;left:25%;top:15%;z-index:150"></div>
<div id="people" style="width:50%;height:25%;position:absolute;left:25%;top:70%;z-index:150"></div>

<div style="width:20%;height:25%;position:absolute;left:78%;top:10%;z-index:150"></div>
<div style="width:20%;height:25%;position:absolute;left:78%;top:40%;z-index:150"></div>
<div style="width:20%;height:25%;position:absolute;left:78%;top:70%;z-index:150"></div>





</div>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/require.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/lib/echart/dist/echarts.js"></script>
<script type="text/javascript">

 require.config({
     paths: {
         echarts: '<%= request.getContextPath() %>/lib/echart/dist'
     }
 });
 
 // 使用
 require(
     [
         'echarts',
         'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
         'echarts/chart/pie'
     ],
     function (ec) {
         // 基于准备好的dom，初始化echarts图表
         var myChart = ec.init(document.getElementById('distribute')); 
         
         var option = {
             tooltip: {
                 show: true
             },
             grid :{ 
                 borderWidth :'0px' 
             },
             legend: {
                 data:['销量']
             },
             xAxis : [
                 {
                     type : 'category',
                     data : ["美团","途牛","旅行社","过来玩","淘宝","京东"],
                     splitLine:{show: false},
                     axisLabel: {textStyle: {
                        color: '#fff'
                     }}
                 }
             ],
             yAxis : [
                 {
                     type : 'value',
                     splitLine:{show: false},
                     axisLabel : {
                         formatter: '{value}',
                         textStyle: {
                             color: '#fff'
                         }
                     }
                 }
             ],
             series : [
                 {
                     "name":"销量",
                     "type":"bar",
                     "barWidth":10,
                     "data":[5, 20, 40, 10, 10, 20]
                 }
             ],
              legend: {
                textStyle:{
                     color: '#FFF'//字体颜色
                },
                data: ['销量']
            }
             
         };
 
         // 为echarts对象加载数据 
         myChart.setOption(option); 
         
         
         //第二个
         var myChart3 = ec.init(document.getElementById('park')); 
         option3 = {
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data:['新能源电动车','小型车','中型车','大型车'], 
			        textStyle:{
                       color: '#FFF'//字体颜色
                    }
			    },
			    toolbox: {
			        show : false,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {
			                show: true, 
			                type: ['pie', 'funnel'],
			                option: {
			                    funnel: {
			                        x: '25%',
			                        width: '50%',
			                        funnelAlign: 'center',
			                        max: 1548
			                    }
			                }
			            },
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : false,
			    series : [
			        {
			            name:'访问来源',
			            type:'pie',
			            radius : ['50%', '70%'],
			            itemStyle : {
			                normal : {
			                    label : {
			                        show : false
			                    },
			                    labelLine : {
			                        show : false
			                    }
			                },
			                emphasis : {
			                    label : {
			                        show : true,
			                        position : 'center',
			                        textStyle : {
			                            fontSize : '30',
			                            fontWeight : 'bold'
			                        }
			                    }
			                }
			            },
			            data:[
			                {value:335, name:'新能源电动车'},
			                {value:310, name:'小型车'},
			                {value:234, name:'中型车'},
			                {value:135, name:'大型车'}
			            ]
			        }
			    ]
			};
         myChart3.setOption(option3); 
     }
 );

</script>

</body>
</html>