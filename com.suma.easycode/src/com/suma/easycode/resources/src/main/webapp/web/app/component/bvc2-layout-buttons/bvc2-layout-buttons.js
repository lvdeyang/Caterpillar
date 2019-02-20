define([
    'text!' + window.APPPATH + 'component/bvc2-layout-buttons/bvc2-layout-buttons.html',
    'restfull',
    'jquery.layout.auto',
    'vue',
    'element-ui'
], function(tpl, ajax, $, Vue){

    //组件名称
    var bvc2LayoutButtons = 'bvc2-layout-buttons';

    Vue.component(bvc2LayoutButtons, {
        props: ['group'],
        template:tpl,
        data:function(){
            return {
                buttons:[]
            }
        },
        methods:{
            layoutChange:function(e){
                var $target = e.target;
                while(true){
                    if($target.className !== 'bvc2-layout-button'){
                        $target = $target.parentElement;
                    }else{
                        break;
                    }
                }
                var index = parseInt($target.getAttribute('data-index'));
                var layout_button_instance = this;
                var button = layout_button_instance.buttons[index];
                layout_button_instance.$emit('layout-change', button);
            }
        },
        mounted:function(){
            var layout_instance = this;
            ajax.get('/device/group/screen/layout/query/all/' + layout_instance.group.id, null, function(data){
                if(data && data.length>0){
                    for(var i=0; i<data.length; i++){
                        var layout = data[i];
                        var websiteDraw = $.parseJSON(layout.websiteDraw);
                        layout_instance.buttons.push({
                            text:layout.name,
                            layout:websiteDraw
                        });
                    }
                    //渲染jquery组件
                    setTimeout(function(){
                        var buttons = layout_instance.buttons;
                        var icons = $('.bvc2-layout-button-icon');
                        for(var i=0; i<buttons.length; i++){
                            var button = buttons[i];
                            $(icons[i])['layout-auto']('create', {
                                cell:button.layout.basic,
                                cellspan:button.layout.cellspan,
                                editable:false,
                                theme:'gray'
                            });
                        }
                    }, 0);
                }
            });
        }
    });

});