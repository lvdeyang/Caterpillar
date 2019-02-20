define([
    'text!' + window.APPPATH + 'component/bvc2-tree-source-list/bvc2-tree-source-list.html',
    'restfull',
    'vue',
    'element-ui'
], function(tpl, ajax, Vue){

    var bvc2TreeMeetingMember = 'bvc2-tree-source-list';

    ajax.get('/device/group/query/all/source/tree', null, function (data) {

        Vue.component(bvc2TreeMeetingMember, {
            props:['checkedmembers'],
            template:tpl,
            data:function(){
                return {
                    data:data,
                    checked:this.checkedmembers,
                    defaultExpandAll:true,
                    defaultProps:{
                        children:'children',
                        label:'name'
                    }
                }
            },
            methods:{
                renderContent:function(h, target){
                    var node = target.node,
                        data = target.data;
                     //过滤Channel
                    if(data.type === 'CHANNEL'){
                        node.visible = false;
                    }else if(data.type === 'BUNDLE'){
                        node.isLeaf = true;
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
                }
            },
            //渲染开始之前
            created:function(){

            }
        });
    });
});