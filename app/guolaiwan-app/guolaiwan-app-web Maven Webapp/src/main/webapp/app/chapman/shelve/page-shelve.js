/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-shelve-body',
    'text!html-shelve-table',
    'juicer',
    'context',
    'page',
    'admin-lte',
    'commons'

], function(header, body, table, juicer, context, page, $, commons){

    var PAGENAME = 'shelve';

    var prepare_table = juicer(table);
    var prepare_body = juicer(body);

    var _init = function(){

        var $content = $('<div style="width:100%; min-height:978px;"></div>');
        context.setContent($content);

        $content.append(juicer(header).render({
            level_1:'货架管理工具',
            level_2:'1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'货架',
                }
            ]
        }));

        $content.append(prepare_body.render({}));

        var $menu_tree = $('#menu-tree');
        var $menu_list = $('#menu-list');
        var $menu_name = $('#menu-name');

        var currMenuId = 'root';

        var _uri = 'chapman/shelve/tree/get/' + context.model.chapman.id + '/' + currMenuId;

        commons.ajax.get(_uri, null, function(data){

            var $tree = $menu_tree.tree('create', {
                data:data.nodes,
                event:{
                    click:function(param, complete){

                        param = param.split('@@');
                        var nodeType = param[0];

                        loadShelveProduct({
                            id:param[1],
                            name:param[2]
                        });

                        complete();

                    },

                    open:function(param, complete){
                        var $node = $(this);
                        if($node.tree('is', 'empty')){

                            var _uri = 'chapman/shelve/tree/get/' + context.model.chapman.id + '/' + param.split('@@')[1];

                            commons.ajax.get(_uri, null, function(data){

                                if(!data.nodes || data.nodes.length<=0){
                                    complete();
                                    return;
                                }

                                for(var i= 0, len=data.nodes.length-1; i<=len; i++){
                                    $node.tree('append', data.nodes[i]);
                                }

                                complete();

                            });

                        }else{
                            complete();
                        }
                    }
                }
            });

            //选中第一个节点
            $tree.find('li').first().tree('click');

        });

        //获取栏目下的商品
        var loadShelveProduct = function(menu){

            $menu_name.text(menu.name);

            var _uri = 'chapman/shelve/product/get/' + context.model.chapman.id + '/' + menu.id;

            commons.ajax.get(_uri, null, function(data){

                var $table = $(prepare_table.render({products:data.products}));

                $menu_list.empty().append($table);

                var _table_product = $table.DataTable({
                    "order":[
                        [2, 'desc']
                    ],
                    columnDefs:[{
                        bSortable:false,
                        aTargets:[3]
                    }],
                    paging:false
                });

                $table.on('click.off.shelve', '.off-shelve', function(){

                    var $off_shelve = $(this);
                    var $tr = $off_shelve.closest('tr');

                    var $modal = commons.modal.createAndShow({
                        type:'confirm',
                        height:'0',
                        title:'是否要将此商品下架？',
                        close:'关闭',
                        save:'确定'
                    });

                    $modal.on('click.save', '.btn-save', function(){

                        var productId = $tr.find('input.product-id').val();

                        _uri = 'chapman/shelve/off/' + context.model.chapman.id + '/' + productId;

                        commons.ajax.delete(_uri, null, function(data){

                            $tr.addClass('selected');
                            _table_product.rows('.selected').remove().draw(false);

                            commons.modal.close($modal);

                        });

                    });

                });

            });

        }

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
