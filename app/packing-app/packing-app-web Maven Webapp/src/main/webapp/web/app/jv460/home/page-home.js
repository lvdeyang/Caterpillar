/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'juicer',
    'context',
    'page',
    'select',
    'tree-select',
    'switch-select',
    'spinner'

], function(header, juicer, context, page, $){

    var PAGENAME = 'home';

    var _init = function(){

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(juicer(header).render({
            level_1:'可视化移动终端模板编辑工具',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                }
            ]
        }));

       var $div = $('<div style="width:200px; margin-top:20px; margin-left:100px;"></div>');

        $div['switch-select']('create', {
            style:'',
            data:[{value:'父节点', nodes:[{key:1, value:'子节点'}, {value:'子节点', nodes:[{key:2, value:'子节点'}, {key:3, value:'子节点'}]}, {key:4, value:'子节点'}]}, {key:5, value:'子节点'}, {key:6, value:'子节点'}]
        });

        $content.append($div);

        var $button = $('<button class="btn btn-primary">获取值</button>');

        $content.append($button);

        $button.on('click', function(){

            var data = $div['switch-select']('val');

            console.log(data);

        });

        /*var $treeSelect = $($.fn['tree-select']('create', {
            html:true,
            style:'',
            groups:[
                {key:'', value:'请选择...'},
                {key:1, value:'父节点1',
                    options:[
                        {key:1, value:'子节点1'},
                        {key:2, value:'子节点2'},
                        {key:3, value:'子节点3'}
                    ]},
                {key:2, value:'父节点2',
                    options:[
                        {key:4, value:'子节点4'},
                        {key:5, value:'子节点5'},
                        {key:6, value:'子节点6'}
                    ]}
            ]
        }));

        $div.append($treeSelect);

        $content.append($div);

        var $button = $('<button class="btn btn-primary">获取值</button>');

        $content.append($button);

        $button.on('click', function(){
            alert($treeSelect['tree-select']('val'));
            alert($treeSelect['tree-select']('text'));
        });*/

        /*var $a = $.fn.spinner('create', {style:'renderIndex', step:1, max:10000, min:0, init:0});

        $div.append($a);

        $content.append($div);

        var $button = $('<button class="btn btn-primary">获取值</button>');

        $content.append($button);

        $button.on('click', function(){
            alert($a.spinner('val'));
        });*/

    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
