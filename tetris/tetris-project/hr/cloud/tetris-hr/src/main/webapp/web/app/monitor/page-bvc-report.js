/**
 * Created by mr on 2020/4/29 0024.
 */
define([
    'text!' + window.APPPATH + 'monitor/page-bvc-report.html',
    'config',
    'jquery',
    'restfull',
    'context',
    'commons',
    'echarts',
    'vue',
    'element-ui',
    'date',
    'mi-frame',
    'css!' + window.APPPATH + 'monitor/page-bvc-report.css'
], function(tpl, config, $, ajax, context, commons, echarts,Vue){

    var pageId = 'page-bvc-report';

    var init = function(){

        //设置标题
        commons.setTitle(pageId);

        var $page = document.getElementById(pageId);
        $page.innerHTML = tpl;
        new Vue({
            el: '#' + pageId + '-wrapper',
            data: {
                menus: context.getProp('menus'),
                user: context.getProp('user'),
                groups: context.getProp('groups')
               
            },
            computed:{

            },
            watch:{

            },
            methods:{
                
            },
            mounted:function(){
                var self = this;
                
                self.$nextTick(function(){
                	var myChart = echarts.init(document.getElementById('main'));
                    
                    var option = {
                	    title: {
                	        text: '设备拓扑'
                	    },
                	    tooltip: {},
                	    animationDurationUpdate: 1500,
                	    animationEasingUpdate: 'quinticInOut',
                	    series: [
                	        {
                	            type: 'graph',
                	            layout: 'none',
                	            symbolSize: 50,
                	            color:'#409EFF',
                	            roam: true,
                	            label: {
                	                show: true,
                	                color:'black',
                	                fontSize:18
                	            },
                	            edgeSymbol: ['circle', 'arrow'],
                	            edgeSymbolSize: [4, 10],
                	            edgeLabel: {
                	                fontSize: 20
                	            },
                	            data: [{
                	                name: '编码器：192.165.56.64',
                	                x: 300,
                	                y: 300
 
                	            }, {
                	                name: '解码器：192.165.56.71',
                	                x: 1000,
                	                y: 300
                	            }, {
                	                name: '接入：192.165.56.81',
                	                x: 550,
                	                y: 300
                	            }, {
                	                name: '接入：192.165.56.82',
                	                x: 800,
                	                y: 300
                	            }],
                	            links: [{
                	                source: 0,
                	                target: 2,
                	                symbolSize: [5, 20],
                	                label: {
                	                    show: true,
                	                    formatter:"【视频】800kbps\r\n【音频】60kbps"
                	                },
                	                lineStyle: {
                	                    width: 1,
                                        color: '#67C23A'
                	                    
                	                }
                	            }, {
                	                source: 2,
                	                target: 3,
                	                label: {
                	                    show: true,
                	                    formatter:"【视频】10kbps\r\n【音频】0kbps"
                	                },
                	                lineStyle: {
                                        color: '#F56C6C'
                	                }
                	            }, {
                	                source: 3,
                	                target: 1,
                	                label: {
                	                    show: true,
                	                    formatter:"【视频】10kbps\r\n【音频】0kbps"
                	                },
                	                lineStyle: {
                                        color: '#F56C6C'
                	                }
                	            }],
                	            lineStyle: {
                	                opacity: 0.9,
                	                width: 2,
                	                curveness: 0
                	            }
                	        }
                	    ]
                	};
                    
                    myChart.setOption(option);
                });
                
            }
        });

    };

    var destroy = function(){

    };

    var currpage = {
        path:'/' + pageId,
        component:{
            template:'<div id="' + pageId + '" class="page-wrapper"></div>'
        },
        init:init,
        destroy:destroy
    };

    return currpage;

});