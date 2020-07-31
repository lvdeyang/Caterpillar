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
	                <div style="width:100%;height:100%;margin-top:0;left:0">
	                   <image src="<%= request.getContextPath() %>/lib/images/icon_renlian.png" 
	                   style="width:12%;height:38%;margin-top:12%;margin-left:15%;float:left;">
	                   <div style="float:left;margin-top:14%;margin-left:5%;text-align:center">
	                      <span style="color:#FFF">人脸抓拍总数</span><br><span style="color:#00FF26;line-height:50px;font-size:20px">&nbsp;&nbsp;24000</span>
	                   </div>
	                </div>
	                
	             </div>
	             <div class="side_c">
	                <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/xinbie_kuang.png">
	                <div id="xingbie" style="width:100%;height:100%;padding-top:2%;left:0"></div>
	             </div>
	             <div class="side_c" style="height:30%">
	                <image style="height:30%" class="innerimage" src="<%= request.getContextPath() %>/lib/images/jiudian_kuang.png">
	                <div id="jiudian" style="width:100%;height:100%;margin-top:0;left:0"></div>
	             </div>
	        </div>
	        <div class="layui-col-md6" style="height:100%;">
	            <div class="center_c">
	                <image class="innerimage_c" src="<%= request.getContextPath() %>/lib/images/keliuliang.png"> 
	                 <div id="jinrikeliu" style="width:100%;height:100%;margin-top:0;left:0"></div>
	            </div>
	            <div class="center_c">
	                <image class="innerimage_c" src="<%= request.getContextPath() %>/lib/images/jingdiantongji.png">
	                <div id="jingqu" style="width:100%;height:100%;margin-top:0;left:0"></div>
	            </div>
	            <div class="center_c" style="height:32%">
	                 <image style="height:32%" class="innerimage_c" src="<%= request.getContextPath() %>/lib/images/qitianliuliang.png">
	                 <div id="qitiantongji" style="width:100%;height:100%;margin-top:0;left:0"></div>
	            </div>
	        </div>
	        <div class="layui-col-md3" style="height:100%;">
	             <div class="side_c">
	                 <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/cheliangzhuapai.png"> 
	                 <div style="width:100%;height:100%;margin-top:0;left:0">
	                   <image src="<%= request.getContextPath() %>/lib/images/icon_qiche.png" 
	                   style="width:12%;height:34%;margin-top:14%;margin-left:15%;float:left;">
	                   <div style="float:left;margin-top:14%;margin-left:5%;text-align:center">
	                      <span style="color:#FFF">车辆抓拍总数</span><br><span style="color:#00FF26;line-height:50px;font-size:20px">24000</span>
	                   </div>
	                </div>
	             </div>
	             <div class="side_c">
	                 <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/chetongji.png"> 
	                 <div id="cheliang" style="width:100%;height:100%;padding-top:2%;left:0"></div>
	             </div>
	             <div class="side_c">
	                 <image class="innerimage" src="<%= request.getContextPath() %>/lib/images/cheguishudi.png"> 
	                 <div id="guishu" style="width:100%;height:120%;margin-top:0;left:0"></div>
	             </div>
	             <div class="side_c" style="height:30%">
	                 <image style="height:30%" class="innerimage" src="<%= request.getContextPath() %>/lib/images/jinqingfenxi.png"> 
	                 <div id="jingqing" style="width:100%;height:100%;margin-top:0;left:0"></div>
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
		              
		            ],
		            itemStyle: {
                          emphasis: {
                              shadowBlur: 10,
                              shadowOffsetX: 0,
                              shadowColor: 'rgba(0, 0, 0, 0.5)'
                           },
                          normal:{
                              color:function(params) {
                              //自定义颜色
                              var colorList = [          
                                      '#04AFFF','#FF63CD'
                                  ];
                                  return colorList[params.dataIndex]
                               }
                          }
                    }
		        }
		    ]
		};
		
		xingbieChart.setOption(optionxingbie)
		
		//酒店人流
		var jiudianeChart = echarts.init(document.getElementById('jiudian')); 
	
		var jiudianoption = {
		    grid:{
		       x:50,
		       y:50,
		       x1:50,
		       y1:50,
		       borderWidth:0
		    },
		    xAxis: {
		        splitLine:{show: false},
		        type: 'category',
		        data: ['汤泉宫', '建国', '国际', '天元'],
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    yAxis: {
		        splitLine:{show: false},
		        type: 'value',
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    series: [{
		        data: [520, 700, 650, 580],
		        type: 'bar',
		        showBackground: true,
		        backgroundStyle: {
		            color: 'rgba(220, 220, 220, 0.8)'
		        },
		        itemStyle: {
		            normal: {
		                color: function(params) {
		                    var colorList = ['#2360FF','#2360FF','#2360FF','#2360FF','#2360FF','#2360FF'];
		                    return colorList[params.dataIndex]
		                }
		            }
		        }
		    },{
		        data: [120, 200, 150, 80],
		        type: 'bar',
		        showBackground: true,
		        backgroundStyle: {
		            color: 'rgba(220, 220, 220, 0.8)'
		        },
		        itemStyle: {
		            normal: {
		                color: function(params) {
		                    var colorList = ['#0FC9FF','#0FC9FF','#0FC9FF','#0FC9FF','#0FC9FF','#0FC9FF','#0FC9FF'];
		                    return colorList[params.dataIndex]
		                }
		            }
		        }
		    }]
		};
		jiudianeChart.setOption(jiudianoption);
		
		
		//今日客流
		var jinrikeliueChart = echarts.init(document.getElementById('jinrikeliu')); 
		var jinrikeliuoption = {
		    grid:{
		       borderWidth:0
		    },
		    xAxis: {
		        splitLine:{show: false},
		        type: 'category',
		        boundaryGap: false,
		        data: ['1', '2', '3', '4', '5', '6', '7','1', '2', '3', '4', '5', '6', '7'],
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    yAxis: {
		        splitLine:{show: false},
		        type: 'value',
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    series: [{
		        data: [820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934, 1290, 1330, 1320],
		        type: 'line',
		        areaStyle: {
					
				}
		    }]
		};
		jinrikeliueChart.setOption(jinrikeliuoption);
		
		
		//景区人流
		var jingqueChart = echarts.init(document.getElementById('jingqu')); 
	
		var jingquoption = {
		    grid:{
		       borderWidth:0
		    },
		    xAxis: {
		        splitLine:{show: false},
		        type: 'category',
		        data: ['清东陵', '万佛园', '禅林寺', '南山常乐谷','独乐寺','南湖','清东陵', '万佛园', '禅林寺', '南山常乐谷','独乐寺','南湖'],
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    yAxis: {
		        splitLine:{show: false},
		        type: 'value',
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    series: [{
		        data: [520, 700, 650, 580,990,285,520, 700, 650, 580,990,285],
		        type: 'bar',
		        showBackground: true,
		        backgroundStyle: {
		            color: 'rgba(220, 220, 220, 0.8)'
		        },
		        itemStyle: {
		            normal: {
		                color: function(params) {
		                    var colorList = ['#FD6760','#FD6760','#FD6760','#FD6760','#FD6760','#FD6760','#FD6760','#FD6760','#FD6760','#FD6760','#FD6760'];
		                    return colorList[params.dataIndex]
		                }
		            }
		        }
		    },{
		        data: [120, 200, 150, 80,25,87,120, 200, 150, 80,25,87],
		        type: 'bar',
		        showBackground: true,
		        backgroundStyle: {
		            color: 'rgba(220, 220, 220, 0.8)'
		        },
		        itemStyle: {
		            normal: {
		                color: function(params) {
		                    var colorList = ['#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF','#0FEBFF'];
		                    return colorList[params.dataIndex]
		                }
		            }
		        }
		    }]
		};
		jingqueChart.setOption(jingquoption);
		
		
		//七天统计
		var qitiantongjieChart = echarts.init(document.getElementById('qitiantongji')); 
		var qitiantongjioption = {
		    grid:{
		       borderWidth:0
		    },
		    xAxis: {
		        splitLine:{show: false},
		        type: 'category',
		        boundaryGap: false,
		        data: ['7.1', '7.2', '7.3', '7.4', '7.5', '7.6', '7.7'],
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    yAxis: {
		        splitLine:{show: false},
		        type: 'value',
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    series: [{
		        data: [820, 932, 901, 934, 1290, 1330, 1320],
		        type: 'line',
		        areaStyle: {},
		        
		        itemStyle: {
		            normal: {
		                lineStyle: {
							color: '#00FFFF' //改变折线颜色
						}
		            }
		        }
		    },{
		        data: [120, 132, 201, 334, 590, 1530, 720],
		        type: 'line',
		        areaStyle: {},
		        itemStyle: {
		            normal: {
			            lineStyle: {
							color: '#FFF600' //改变折线颜色
						}
		            }
		        }
		    }]
		};
		qitiantongjieChart.setOption(qitiantongjioption);
		
		
		
		//车辆统计
		var cheliangChart = echarts.init(document.getElementById('cheliang')); 
		var optioncheliang = {
		    tooltip: {
		        trigger: 'item',
		        formatter: '{a} <br/>{b}: {c} ({d}%)'
		    },
		    legend: {
		         orient: 'horizontal',                  
		         data: ['大型车', '小型车'],
		         x : '70%',
		         y : '30%',
		         textStyle: {
			        color: '#FFF'       // 图例文字颜色
			     }
		    },
		    series: [
		        {
		            name: '车辆类型',
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
		                {value: 835, name: '大型车'},
		                {value: 310, name: '小型车'}
		            ],
		            itemStyle: {
                          emphasis: {
                              shadowBlur: 10,
                              shadowOffsetX: 0,
                              shadowColor: 'rgba(0, 0, 0, 0.5)'
                           },
                          normal:{
                              color:function(params) {
                              //自定义颜色
                              var colorList = [          
                                      '#FA903C','#EAEA3C','#F66465','#30A7FB'
                                  ];
                                  return colorList[params.dataIndex]
                               }
                          }
                    }
		        }
		    ]
		};
		cheliangChart.setOption(optioncheliang)
		
		//警情统计
		var jingqingChart = echarts.init(document.getElementById('jingqing')); 
		var optionjingqing = {
		    tooltip: {
		        trigger: 'item',
		        formatter: '{a} <br/>{b}: {c} ({d}%)'
		    },
		    legend: {
		         orient: 'horizontal',                  
		         data: ['清东陵', '禅林寺', '南湖', '飞机场', '客运站'],
		         x : '70%',
		         y : '30%',
		         textStyle: {
			        color: '#FFF'       // 图例文字颜色
			     }
		    },
		    series: [
		        {
		            name: '车辆类型',
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
		                {value: 835, name: '清东陵'},
		                {value: 310, name: '禅林寺'},
		                {value: 210, name: '南湖'},
		                {value: 110, name: '飞机场'},
		                {value: 710, name: '客运站'}
		            ],
		            itemStyle: {
                          emphasis: {
                              shadowBlur: 10,
                              shadowOffsetX: 0,
                              shadowColor: 'rgba(0, 0, 0, 0.5)'
                           },
                          normal:{
                              color:function(params) {
                              //自定义颜色
                              var colorList = [          
                                      '#73FE94','#FF4C68','#D8FE70','#FE71D5','#70D9FE','#C987FF','#FF9758'
                                  ];
                                  return colorList[params.dataIndex]
                               }
                          }
                    }
		        }
		    ]
		};
		
		jingqingChart.setOption(optionjingqing)
		
		//车辆归属地
		var guishuChart = echarts.init(document.getElementById('guishu')); 
		var guishuoption = {
		    title: {
		        text: '',
		        subtext: ''
		    },

		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true,
		        borderWidth:0
		    },
		    xAxis: {
	            splitLine:{show: false},	    
		        type: 'value',
		        boundaryGap: [0, 0.01],
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    yAxis: {
		        splitLine:{show: false},
		        type: 'category',
		        data: [ '北京', '天津', '河南', '浙江', '辽宁'],
		        axisLabel: {
                     interval:0,
					 textStyle: {
	                     color: '#fff'
	                 }
		        }
		    },
		    series: [
		        {
		          
		            type: 'bar',
		            data: [ 23489, 29034, 104970, 131744, 630230],
			        itemStyle: {
			            normal: {
			                color: function(params) {
			                    var colorList = ['#00E67D','#FA903C','#EAEA3C','#F66465','#31A7FB'];
			                    return colorList[params.dataIndex]
			                }
			            }
			        }
		        }
		    ]
		};
		guishuChart.setOption(guishuoption)
		
	});
	
	

</script>
	
</body>
</html>