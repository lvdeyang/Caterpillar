/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-statistics-order-table',
    'text!html-statistics-order-chart',
    'juicer',
    'context',
    'page',
    'chart',
    'admin-lte',
    'commons'

], function(header, table, chart, juicer, context, page, Chart, $, commons){

    var PAGENAME = 'statistics-order';

    var prepare_headr = juicer(header);

    var prepare_table = juicer(table);

    var _init = function(){

        var $content = $('<div style="width:100%; min-height:876px;"></div>');
        context.setContent($content);

        //渲染页头
        $content.append(prepare_headr.render({
            level_1:'订单统计工具',
            level_2:'版本：1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'统计',
                },{
                    name:'订单统计'
                }
            ]
        }));

        var $body = $('<div class="content"></div>');

        var $wrapper = $('<div></div>');

        $content.append($body.append($wrapper));

        var _uri = 'chapman/statistics/order/result/table/' + context.model.chapman.id;

        commons.ajax.get(_uri, null, function(data){

            var $table = $(prepare_table.render({
                title:'订单统计表',
                statistics:data.statistics
            }));

            $wrapper.empty().append($table);

            $table.find('table').DataTable({
                "order":[
                    [2, 'asc'],
                    [7, 'desc']
                ],
                paging:true
            });

        });

        $body.append(chart);

        _uri = 'chapman/statistics/order/result/pie/' + context.model.chapman.id;

        //饼图
        commons.ajax.get(_uri, null, function(data){

            var _pieData = [];
            var _colors = ['#f56954', '#00a65a'];
            var _hightlights = ['#f56954', '#00a65a'];
            var _statistics = data.statistics;
            for(var i= 0, len=_statistics.length-1; i<= len; i++){
                _pieData.push({
                    value:_statistics[i].count,
                    color:_colors[i],
                    highlight:_hightlights[i],
                    label:_statistics[i].type
                });
            }

            var pieChartCanvas = $('#statistics-pie').get(0).getContext('2d');
            var pieChart = new Chart(pieChartCanvas);

            var pieOptions = {
                //Boolean - Whether we should show a stroke on each segment
                segmentShowStroke: true,
                //String - The colour of each segment stroke
                segmentStrokeColor: '#fff',
                //Number - The width of each segment stroke
                segmentStrokeWidth: 2,
                //Number - The percentage of the chart that we cut out of the middle
                percentageInnerCutout: 50, // This is 0 for Pie charts
                //Number - Amount of animation steps
                animationSteps: 100,
                //String - Animation easing effect
                animationEasing: 'easeOutBounce',
                //Boolean - Whether we animate the rotation of the Doughnut
                animateRotate: true,
                //Boolean - Whether we animate scaling the Doughnut from the centre
                animateScale: false,
                //Boolean - whether to make the chart responsive to window resizing
                responsive: true,
                // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
                maintainAspectRatio: true,
                //String - A legend template
                legendTemplate: '<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<segments.length; i++){%><li><span style=\'background-color:<%=segments[i].fillColor%>\'></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>'
            };
            //Create pie or douhnut chart
            // You can switch between pie and douhnut using the method below.
            pieChart.Doughnut(_pieData, pieOptions);

        });

        _uri = 'chapman/statistics/order/result/bar/' + context.model.chapman.id;

        commons.ajax.get(_uri, null, function(data){

            //条形图
            var areaChartData = {
                labels: [],
                datasets: [
                    {
                        label: '景点',
                        fillColor: 'rgba(210, 214, 222, 1)',
                        strokeColor: 'rgba(210, 214, 222, 1)',
                        pointColor: 'rgba(210, 214, 222, 1)',
                        pointStrokeColor: '#c1c7d1',
                        pointHighlightFill: '#fff',
                        pointHighlightStroke: 'rgba(220,220,220,1)',
                        data: []
                    },
                    {
                        label: '餐饮',
                        fillColor: 'rgba(60,141,188,0.9)',
                        strokeColor: 'rgba(60,141,188,0.8)',
                        pointColor: '#3b8bba',
                        pointStrokeColor: 'rgba(60,141,188,1)',
                        pointHighlightFill: '#fff',
                        pointHighlightStroke: 'rgba(60,141,188,1)',
                        data: []
                    }
                ]
            };

            var _statistics = data.statistics;

            for(var i= 0, len=_statistics.length-1; i<=len; i++){
                var bar = _statistics[i];
                var orderMonth = bar.orderMonth;
                var productType = bar.productType;
                var count = bar.count;

                if(areaChartData.labels.length === 0){
                    areaChartData.labels.push(orderMonth);
                }else{
                    for(var j= 0; j<areaChartData.labels.length; j++){
                        if(areaChartData.labels[j] === orderMonth){
                            break;
                        }else if(j === areaChartData.labels.length-1){
                            areaChartData.labels.push(orderMonth);
                        }
                    }
                }

                for(var j=0; j<areaChartData.datasets.length; j++){
                    if(areaChartData.datasets[j].label === productType){
                        areaChartData.datasets[j].data.push(count);
                    }
                }
            }

            var barChartCanvas = $("#statistics-bar").get(0).getContext("2d");
            var barChart = new Chart(barChartCanvas);
            var barChartData = areaChartData;
            barChartData.datasets[1].fillColor = '#00a65a';
            barChartData.datasets[1].strokeColor = '#00a65a';
            barChartData.datasets[1].pointColor = '#00a65a';
            var barChartOptions = {
                //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
                scaleBeginAtZero: true,
                //Boolean - Whether grid lines are shown across the chart
                scaleShowGridLines: true,
                //String - Colour of the grid lines
                scaleGridLineColor: 'rgba(0,0,0,.05)',
                //Number - Width of the grid lines
                scaleGridLineWidth: 1,
                //Boolean - Whether to show horizontal lines (except X axis)
                scaleShowHorizontalLines: true,
                //Boolean - Whether to show vertical lines (except Y axis)
                scaleShowVerticalLines: true,
                //Boolean - If there is a stroke on each bar
                barShowStroke: true,
                //Number - Pixel width of the bar stroke
                barStrokeWidth: 2,
                //Number - Spacing between each of the X value sets
                barValueSpacing: 5,
                //Number - Spacing between data sets within X values
                barDatasetSpacing: 1,
                //String - A legend template
                legendTemplate: '<ul class=\'<%=name.toLowerCase()%>-legend\'><% for (var i=0; i<datasets.length; i++){%><li><span style=\'background-color:<%=datasets[i].fillColor%>\'></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
                //Boolean - whether to make the chart responsive
                responsive: true,
                maintainAspectRatio: true
            };

            barChartOptions.datasetFill = false;
            barChart.Bar(barChartData, barChartOptions);

        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
