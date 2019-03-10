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

<div id="map" style="width:50%;height:50%;position:absolute;left:25%;top:15%;z-index:150"></div>
<div id="people" style="width:50%;height:25%;position:absolute;left:25%;top:70%;z-index:150"></div>

<div style="width:20%;height:25%;position:absolute;left:78%;top:10%;z-index:150"></div>
<div id="orderInfo" style="width:20%;height:25%;position:absolute;left:78%;top:40%;z-index:150"></div>
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
         'echarts/chart/pie',
         'echarts/chart/line'
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
         
         var myChart5 = ec.init(document.getElementById('people')); 
         option5 = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : false,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['5月','6月','7月','8月','9月','10月','11月'],
		            axisLabel: {textStyle: {
                        color: '#fff'
                     }}
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
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
		            name:'游客数量',
		            type:'line',
		            stack: '总量',
		            data:[120, 132, 101, 134, 90, 230, 210]
		        }
		    ]
		};
         
        myChart5.setOption(option5); 
        
        
        
        var myChart7 = ec.init(document.getElementById('orderInfo')); 
        option7 = {
		    title : {
		        text: '',
		        subtext: ''
		    },
             grid :{ 
                 borderWidth :'0px' 
             },
		    
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['总公司','分公司'],
		        textStyle:{
                     color: '#FFF'//字体颜色
                }
		    },
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : false,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
		            splitLine:{
					　　　show:false
					}
		        }
		        
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            splitLine:{
					　　　show:false
					}
		        }
		    ],
		    series : [
		        {
		            name:'总公司',
		            type:'bar',
		            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        },
		        {
		            name:'分公司',
		            type:'bar',
		            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
		            markPoint : {
		                data : [
		                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
		                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name : '平均值'}
		                ]
		            }
		        }
		    ]
		};
        myChart7.setOption(option7);
        
        
        
        
        
        
        
        
        var myChart4 = ec.init(document.getElementById('map')); 
        option4 = {
		    title : {
		        text: '',
		        subtext: ''
		    },
		    legend: {
		        x:'right',
		        selectedMode:false,
		        data:['北京','上海','广东']
		    },
		    dataRange: {
		        orient: 'horizontal',
		        min: 0,
		        max: 55000,
		        text:['高','低'],           // 文本，默认为数值文本
		        splitNumber:0
		    },
		    toolbox: {
		        show : false,
		        orient: 'vertical',
		        x:'right',
		        y:'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false}
		        }
		    },
		    series : [
		        {
		            name: '2011全国GDP分布',
		            type: 'map',
		            mapType: 'china',
		            mapLocation: {
		                x: 'left'
		            },
		            selectedMode : 'multiple',
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data:[
		                {name:'西藏', value:605.83},
		                {name:'青海', value:1670.44},
		                {name:'宁夏', value:2102.21},
		                {name:'海南', value:2522.66},
		                {name:'甘肃', value:5020.37},
		                {name:'贵州', value:5701.84},
		                {name:'新疆', value:6610.05},
		                {name:'云南', value:8893.12},
		                {name:'重庆', value:10011.37},
		                {name:'吉林', value:10568.83},
		                {name:'山西', value:11237.55},
		                {name:'天津', value:11307.28},
		                {name:'江西', value:11702.82},
		                {name:'广西', value:11720.87},
		                {name:'陕西', value:12512.3},
		                {name:'黑龙江', value:12582},
		                {name:'内蒙古', value:14359.88},
		                {name:'安徽', value:15300.65},
		                {name:'北京', value:16251.93, selected:true},
		                {name:'福建', value:17560.18},
		                {name:'上海', value:19195.69, selected:true},
		                {name:'湖北', value:19632.26},
		                {name:'湖南', value:19669.56},
		                {name:'四川', value:21026.68},
		                {name:'辽宁', value:22226.7},
		                {name:'河北', value:24515.76},
		                {name:'河南', value:26931.03},
		                {name:'浙江', value:32318.85},
		                {name:'山东', value:45361.85},
		                {name:'江苏', value:49110.27},
		                {name:'广东', value:53210.28, selected:true}
		            ]
		        },
		        {
		            name:'2011全国GDP对比',
		            type:'pie',
		            roseType : 'area',
		            tooltip: {
		                trigger: 'item',
		                formatter: "{a} <br/>{b} : {c} ({d}%)"
		            },
		            center: [document.getElementById('map').offsetWidth - 250, 225],
		            radius: [30, 120],
		            data:[
		                {name: '北京', value: 16251.93},
		                {name: '上海', value: 19195.69},
		                {name: '广东', value: 53210.28}
		            ]
		        }
		    ],
		    animation: false
		};
       
         myChart4.setOption(option4);
     }
 );

</script>

</body>
</html>