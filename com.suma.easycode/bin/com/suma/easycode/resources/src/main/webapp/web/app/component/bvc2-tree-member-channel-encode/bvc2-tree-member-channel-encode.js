define([
    'text!' + window.APPPATH + 'component/bvc2-tree-member-channel-encode/bvc2-tree-member-channel-encode.html',
    'restfull',
    'jquery',
    'vue',
    'element-ui',
    'json'
], function(tpl, ajax, $, Vue){

    var bvc2TreeMeetingMember = 'bvc2-tree-member-channel-encode';

    //第二级节点
    var level_1 = [
        {
            id: 'level_1_0',
            name: '210',
            icon: 'icon-folder-close'
        },{
            id: 'level_1_1',
            name: '机顶',
            icon: 'icon-folder-close'
        },{
            id: 'level_1_2',
            name: '移动端',
            icon: 'icon-folder-close'
        }
    ];

    //第一级节点
    var level_0 = [{
        id: 'level_0',
        name: '设备列表',
        isLeaf:false,
        icon: 'icon-folder-open',
        children: level_1
    }];

    Vue.component(bvc2TreeMeetingMember, {
        props:['members'],
        template:tpl,
        data:function(){
            return {
                data:this.members,
                checked:[],
                defaultExpandAll:true,
                defaultProps:{
                    children:'children',
                    label:'name',
                    isLeaf:'isLeaf'
                }
                //defaultExpanded:['level_0']
            }
        },
        methods:{
            //loadNode:function(node, resolve){
            //    if(node.level === 0){
            //        resolve(level_0);
            //    }else if(node.level === 1 ) {
            //        resolve(level_1);
            //    }else if(node.level === 2){
            //        resolve(node.data.children);
            //    }else{
            //        resolve([]);
            //    }
            //},
            renderContent:function(h, target){
                var node = target.node,
                    data = target.data;
                if(data.type == 'CHANNEL'){
                    if(JSON.parse(data.param).channelType != 'VenusVideoIn'){
                        node.visible = false;
                    }
                }

                var c = {};
                c[node.icon] = true;
                return h('span', {
                    class: {
                        'bvc2-tree-node-custom': true
                    }
                }, [
                    h('span', null, [
                        h('span', {
                            class: c,
                            style: {
                                'font-size': '16px',
                                'position': 'relative',
                                'top': '1px',
                                'margin-right': '5px'
                            }
                        }, null),
                        node.label
                    ])
                ]);
            },
            nodeExpand:function(data, node, instance){
                if(data.icon === 'icon-folder-close'){
                    data.icon = 'icon-folder-open';
                }
            },
            nodeCollapse:function(data, node, instance){
                if(data.icon === 'icon-folder-open'){
                    data.icon = 'icon-folder-close';
                }
            },
            getCheckedNodes:function(){
                return this.$refs.tree.getCheckedNodes();
            },
            handleDragStart:function(node, e){
                //放置数据
                e.dataTransfer.setData('data', $.toJSON(node.data));
            },
            allowDrop:function(node){
                return false;
            },
            allowDrag:function(node){
                if(node.data.type === 'CHANNEL'){
                    return true;
                }
                return false;
            }
        },
        //渲染开始之前
        created:function(){

            var initData = [{
                id:'1',
                name:'文件夹1',
                type:'folder',
                icon:'icon-facetime-video',
                children:[{
                    id:'1',
                    name:''
                }]
            }];

            level_0[0].children = initData;
        }

    });

});
