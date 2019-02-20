define([
    'text!' + window.APPPATH + 'component/bvc2-system-nav-side/bvc2-system-nav-side.html',
    'vue',
    'element-ui'
], function(tpl, Vue){

    var bvc2SystemNavSide = 'bvc2-system-nav-side';

    Vue.component(bvc2SystemNavSide, {
        props:['active'],
        template:tpl,
        data:function(){
            return {
                isCollapse:false,
                isSlideMini:this.isCollapse,
                menus:[
                    {
                        id:'0',
                        icon:'el-icon-tickets',
                        title:'会议资源',
                        subs:[
                            {
                                id:'0-1',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'参数方案',
                                href:'#/page-avtpl'
                            },{
                                id:'0-2',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'业务角色',
                                href:'#/page-business-role'
                            },{
                                id:'0-3',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'通道别名',
                                href:'#/page-channel-name'
                            },{
                                id:'0-4',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'布局方案',
                                href:'#/page-layout'
                            },{
                                id:'0-5',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'录制方案',
                                href:'#/page-record-scheme'
                            },{
                                id:'0-6',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'会议模板',
                                href:'#/page-tpl'
                            },{
                                id:'0-7',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'资源导出'
                            },{
                                id:'0-8',
                                icon:'icon-circle-blank',
                                style:'position:relative; top:1px;',
                                title:'资源导入'
                            }
                        ]
                    },{
                        id:'1',
                        icon:'icon-th',
                        title:'大屏管理',
                        style:'font-size:17px; margin-right:11px; position:relative; left:3px; top:3px;',
                        href:'#/page-jv230'
                    },{
                        id:'2',
                        icon:'icon-bar-chart',
                        style:'font-size:17px; margin-right:11px; position:relative; left:3px; top:2px;',
                        title:'运维页面',
                        subs:[{
                            id:'2-1',
                            icon:'icon-circle-blank',
                            style:'position:relative; top:1px;',
                            title:'JV210接入配置',
                            href:'#/page-jv210-config'
                        },{
                            id:'2-2',
                            icon:'icon-circle-blank',
                            style:'position:relative; top:1px;',
                            title:'JV210接入状态',
                            href:'#/page-jv210-status'
                        }]
                    }
                ]
            }
        },
        methods: {
            toggleCollapse:function(){
                if(this.isCollapse){
                    this.isCollapse = false;
                }else{
                    this.isCollapse = true;
                }
            },
            menuClick:function(data){
                if(data.href){
                    if(data.href.indexOf('#/') === 0){
                        window.location.hash = data.href;
                    }else{
                        window.location.href = data.href;
                    }
                }
            }
        }
    });

});
