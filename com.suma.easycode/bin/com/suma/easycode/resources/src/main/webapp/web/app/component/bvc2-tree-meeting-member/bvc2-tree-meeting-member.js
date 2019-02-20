define([
    'text!' + window.APPPATH + 'component/bvc2-tree-meeting-member/bvc2-tree-meeting-member.html',
    'restfull',
    'vue',
    'element-ui'
], function(tpl, ajax, Vue){

    var bvc2TreeMeetingMember = 'bvc2-tree-meeting-member';

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
                    label:'name'
                },
                defaultExpanded:[]
            }
        },
        methods:{
            renderContent:function(h, target){
                var node = target.node
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
            }
        },
        //渲染开始之前
        created:function(){

        }

    });

});