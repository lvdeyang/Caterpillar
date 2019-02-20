/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-product-scenery-detail-body',
    'juicer',
    'context',
    'page',
    'admin-lte',
    'commons',
    'tree'

], function(header, body_detail, juicer, context, page, $, commons){

    var PAGENAME = 'product-detail';

    var prepare_body_detail = juicer(body_detail);

    var _init = function(productId){

        var $content = $('<div style="width:100%; min-height:876px;"></div>');
        context.setContent($content);

        //渲染页头
        $content.append(juicer(header).render({
            level_1:'景点管理工具',
            level_2:'版本：1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'商品',
                },{
                    name:'景点'
                },{
                    name:'商品扩展信息'
                }
            ]
        }));

        var $wrapper = $('<div></div>');
        $content.append($wrapper);

        _init_scenery_detail($content, productId);

    }

    //初始化扩展信息
    var _init_scenery_detail = function($content, sceneryId){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的景点明细，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'chapman/product/scenery/show/details/' + context.model.chapman.id + '/' + sceneryId;

        commons.ajax.get(_uri, null, function(data){

            var scenery = data.scenery;

            var $body = $(prepare_body_detail.render({
                scenery:scenery.name,
                details:data.details
            }));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_scenery_detail = $table.DataTable({
                "order":[
                    [6, 'desc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 7]
                }],
                paging:true
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=scenery-detail]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        hasSelected = true;
                        return false;
                    }
                });
                if(hasSelected){
                    $button_remove.removeAttr('disabled');
                }else{
                    $button_remove.attr('disabled', 'true');
                }
            }

            //初始化checkbox
            commons.checkbox.init({
                selector:'input[name=scenery-detail]',
                context:$table,
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=scenery-detail]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=scenery-detail]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //资源列表
            /*$table.on('click.sources', 'td .source', function(){
                var $source = $(this);
                $source.tooltip('hide');
                var $tr = $source.closest('tr');
                var $cells = $tr.find('td');
                var $checkbox = $($cells[0]);
                var $name = $($cells[1]);
                _init_scenery_detail_source($content, scenery, {
                    id:$checkbox.find('[name=scenery-detail]').val(),
                    name:$name.text()
                })
            });*/

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _unsaved = true;

                var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                _table_scenery_detail.row.add([
                    '-',
                    '<input type="text" class="form-control detail-name" placeholder="标题" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control detail-introduction" placeholder="描述" required style="width:100%; border-radius:4px;"/>',
                    '<select class="form-control detail-type" style="border-radius:4px; width:100%;"><option value="HOMEPAGE">首页扩展描述</option><option value="INFO">详情页描述</option></select>',
                    '<select class="form-control detail-style" style="border-radius:4px; width:100%;"><option value="DELETELINE" style="text-decoration:line-through;">删除线</option><option value="PRICEHIGHLIGHT" style="font-size:18px; color:#d33724;">价格高亮</option></select>',
                    '<input type="text" class="form-control detail-index" placeholder="渲染顺序" required style="width:100%; border-radius:4px;"/>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_scenery_detail.rows()[0].length-1;

                var $tr = $(_table_scenery_detail.row(_last_index).node());

                var $cells = $tr.find('td');

                $($cells[0]).addClass('center');

                $($cells[5]).addClass('right');

                $($cells[6]).addClass('center');

                $($cells[7]).addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'chapman/product/scenery/add/detail/' + context.model.chapman.id + '/' + scenery.id;

                    commons.ajax.post(_uri, {

                        name:$tr.find('.detail-name').val(),
                        introduction:$tr.find('.detail-introduction').val(),
                        price:$tr.find('.detail-price').val(),
                        type:$tr.find('.detail-type').val()

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_scenery_detail.rows()[0].length-1;

                        //删除编辑行
                        _table_scenery_detail.rows(_last_index).remove();

                        //新增数据行
                        _table_scenery_detail.row.add([
                            '<input type="checkbox" name="scenery-detail" class="flat-red" value="'+data.detail.id+'"/>',
                            data.detail.name,
                            data.detail.introduction,
                            data.detail.price,
                            data.detail.type,
                            data.detail.updateTime,
                            '<a class="source" data-toggle="tooltip" data-placement="bottom" title="资源列表"><i class="fa fa-folder-o"></i></a>'+
                            '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑明细"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除明细"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_scenery_detail.rows()[0].length-1;

                        var $tr = $(_table_scenery_detail.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        //初始化复选框
                        commons.checkbox.init({
                            selector:'[name=scenery-detail]',
                            content:$checkbox,
                            doCheckall:true
                        });

                        $($cells[3]).addClass('right');

                        $($cells[4]).addClass('center');

                        $($cells[5]).addClass('center');

                        $($cells[6]).addClass('operation').addClass('center');

                    }, 403);

                });

                //取消按钮
                $tr.on('click.cancel', '.cancel', function(){
                    $(this).tooltip('hide');
                    _table_scenery_detail.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _detailIds = [];
                $table.find('input[name=scenery-detail]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _detailIds.push($checkbox.val());
                    }
                });

                var _uri = 'chapman/product/scenery/remove/details/' + context.model.chapman.id + '/' + scenery.id;

                commons.ajax.delete(_uri, {

                    detailIds:_detailIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_detailIds.length+'条数据。');

                    _table_scenery_detail.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        }, 200);
    };

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
