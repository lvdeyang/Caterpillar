<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            过来玩系统
        </title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="<%=path %>/layui/css/x-admin.css" media="all">
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/css/bootstrap.css" media="all">
        <link rel="stylesheet" href="<%=path %>/layui/lib/bootstrap/js/bootstrap.min.js" media="all">
    </head>
    <body>
    <style type="text/css">
	legend { display: block; width:100px; border-bottom:0px; font-family: "Microsoft YaHei","Helvetica Neue";}
	legend a{ color:#666;} legend a:hover{ text-decoration:none;}
	.layui-table{ font-family: "Microsoft YaHei","Helvetica Neue";}
	</style>
        <div class="x-body">
            <blockquote class="layui-elem-quote">
                <a href="#" style="color:#333">${adminName}</a>，你好！欢迎来到 <a href="#" >${comName}</a>
            </blockquote>
<div class="row" id="content" style="display:none;">
            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-blue"> <i class="fa fa-address-card"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="机构总数"><i class="iconfont " data-icon=""></i>
                            <h1>${merchantCount}</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="机构总数"> <i class="iconfont " data-icon=""></i><span>商户总数</span></a>

                    </div>
                </section>
            </div>
            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-commred"> <i class="fa fa-handshake-o"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon=""></i>
                            <h1>${userCount}</h1>
                        </a>

                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon=""></i><span>用户总数</span></a>

                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-dark-green"> <i class="fa fa-bar-chart"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="订单总数"> <i class="iconfont " data-icon=""></i>
                            <h1>${orderCount}</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="订单总数"> <i class="iconfont " data-icon=""></i><span>订单总数</span></a>
                    </div>
                </section>
            </div>

            <div class="col-xs-6 col-sm-4 col-md-3">
                <section class="panel">
                    <div class="symbol bgcolor-yellow-green"> <i class="fa fa-cubes"></i>
                    </div>
                    <div class="value tab-menu" bind="1">
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon=""></i>
                            <h1>${todayrequest}</h1>
                        </a>
                        <a href="javascript:;" data-url="user-info.html" data-parent="true" data-title="今日新增"> <i class="iconfont " data-icon=""></i><span>今日访问</span></a>
                    </div>
                </section>
            </div>

        </div>
            
            <fieldset class="layui-elem-field layui-field-title site-title">
              <legend><a name="default">信息统计</a></legend>
            </fieldset>
             
        <!--相关统计-->
        <div class="row"  id="content1" style="display:none;">
        <div class="col-sm-6">
         <section class="panel">
                    <div class="panel-heading">终端访问统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="area" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        <div class="col-sm-6">
         <section class="panel">
                    <div class="panel-heading">订单统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="main" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        <div class="col-sm-6">
         <section class="panel">
                    <div class="panel-heading">商品订单统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="years" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        <div class="col-sm-6">
         <section class="panel">
                    <div class="panel-heading">商户订单统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="product" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        
        <div class="col-sm-6">
         <section class="panel">
                    <div class="panel-heading">访问量统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="record" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        <div class="col-sm-6">
         <section class="panel">
                    <div class="panel-heading">商品订单统计</div>
                    <div class="panel-body">
                        <div class="echarts" id="recordUrl" style="height:300px; height:350px"></div>
                    </div>
                </section>
        </div>
        </div>
    
        <script src="<%=path %>/layui/lib/layui/layui.js" charset="utf-8"></script>
        <script src="<%=path %>/layui/js/index.js"></script>
        <script src="<%=path %>/layui/js/echarts.min.js"></script>
        <script src="<%=path %>/layui/js/echart.js"></script>
        <script type="text/javascript">
        
         var  user='${user}';
        if(user=='Admin'||user=='admin'){
           document.getElementById('content').style.display='block';
           document.getElementById('content1').style.display='block';
        }
        
        
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['订单']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ${orderDateList}
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:'订单',
                    type:'line',
                    stack: '总量',
                    data:${orderCountList}
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 指定图表的配置项和数据
       var myChart = echarts.init(document.getElementById('area'));

        // 指定图表的配置项和数据
    var option = {
        title: {
            text: '终端访问统计',
            subtext: '',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['App', '公众号', '线下购票']
        },
        series: [{
            name: '终端访问统计',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data:  ${terReprt},
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('years'));

        // 指定图表的配置项和数据
        var option = {
            title: {
            text: '商品订单统计',
            subtext: '纯属虚构',
            x: 'center'
        },
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ${proOrderTitles},
            axisTick: {
                alignWithLabel: true
            }
        }],
        yAxis: [{
            type: 'value'
        }],
        series: [{
            name: '活跃度',
            type: 'bar',
            barWidth: '60%',
            data: ${proOrderCounts}
        }]
    };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <script type="text/javascript">
       
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('product'));

         // 指定图表的配置项和数据
        var option = {
            title: {
            text: '商户订单统计',
            subtext: '纯属虚构',
            x: 'center'
        },
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ${proOrderTitles1},
            axisTick: {
                alignWithLabel: true
            }
        }],
        yAxis: [{
            type: 'value'
        }],
        series: [{
            name: '活跃度',
            type: 'bar',
            barWidth: '60%',
            data: ${proOrderCounts1}
        }]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
            
            
            
            
            
            <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('record'));

        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['访问量']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ${recordDateList}
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:'访问量',
                    type:'line',
                    stack: '总量',
                    data:${recordCountList}
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
            
            
            
             <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('recordUrl'));

         // 指定图表的配置项和数据
        var option = {
            title: {
            text: '访问位置统计',
            subtext: '纯属虚构',
            x: 'center'
        },
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ${recordTitles},
            axisTick: {
                alignWithLabel: true
            }
        }],
        yAxis: [{
            type: 'value'
        }],
        series: [{
            name: '活跃度',
            type: 'bar',
            barWidth: '60%',
            data: ${recordCounts}
        }]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
            
            
            
        </div>
        <div class="layui-footer footer footer-demo">
            <div class="layui-main">
                <p>
                    <a href="/">
                        Copyright ©2017-2019 遵化市博客旅游集散中心有限公司 All Rights Reserved.
                    </a>
                </p>
                
            </div>
        </div>
        
    </body>
</html>