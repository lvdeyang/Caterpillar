/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-product-scenery-body',
    'text!html-product-scenery-detail-body',
    'text!html-product-scenery-source-body',
    'juicer',
    'context',
    'page',
    'admin-lte',
    'commons',
    'tree'

], function(header, body_scenery, body_detail, body_source, juicer, context, page, $, commons){

    var PAGENAME = 'product-scenery';

    var prepare_body_scenery = juicer(body_scenery);
    var prepare_body_detail = juicer(body_detail);
    var prepare_body_source = juicer(body_source);

    //初始化景点
    var _init_scenery = function($content){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的景点，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'chapman/product/scenery/show/' + context.model.chapman.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_body_scenery.render(data));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_scenery = $table.DataTable({
                "order":[
                    [5, 'desc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 6]
                }],
                paging:true
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=scenery]').each(function(){
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
                selector:'input[name=scenery]',
                context:$table,
                doCheckall:true
            });

            //绑定商品属性列表
            $table.find('tr>td>a.properties').dropdown();

            //绑定选中事件
            $table.on('ifChecked', 'input[name=scenery]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=scenery]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //商品上架
            $table.on('click.on.shelve', '.on-shelve', function(){

                var $shelve = $(this);

                var currMenuId = 'root';

                var select_menu = null;

                var _uri = 'chapman/shelve/tree/get/' + context.model.chapman.id + '/' + currMenuId;

                var $modal = commons.modal.createAndShow({
                    title:'关联栏目'
                });

                var $modal_content = commons.modal.getContent($modal);

                commons.ajax.get(_uri, null, function(data){

                    $modal_content.tree('create', {
                        data:data.nodes,
                        event:{
                            click:function(param, complete){

                                param = param.split('@@');
                                var nodeType = param[0];

                                select_menu = {
                                    id:param[1],
                                    name:param[2]
                                };

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

                    $modal.on('click.save', '.btn-save', function(){
                        if(!select_menu){
                            commons.messanger.info('您还没选择任何类目！');
                            return;
                        };

                        var $tr = $shelve.closest('tr');
                        var productId = $tr.find('[name=scenery]').val();

                        _uri = 'chapman/shelve/on/' + context.model.chapman.id + '/' + productId;

                        commons.ajax.post(_uri, {

                            menuId:select_menu.id

                        }, function(data){

                            $tr.addClass('selected');
                            _table_scenery.rows('.selected').remove().draw(false);

                            commons.modal.close($modal);

                        });
                    });

                });

            });

            //商品扩展信息
            $table.on('click.info', '.info', function(){
                var $tr = $(this).closest('tr');
                var $cells = $tr.find('td');
                var $checkbox = $($cells[0]);
                var $name = $($cells[1]);

                _init_scenery_detail($content, {
                    id:$checkbox.find('[name=scenery]').val(),
                    name:$name.find('a').text()
                });

            });

            //绑定资源列表
            $table.on('click.source', '.source', function(){

            });

            //图文详情
            $table.on('click.gd', '.graphic-details', function(){

            });

            //进度图（流程图）
            $table.on('click.timeline', '.timeline', function(){

            });

            //排期表
            $table.on('click.schedule', '.schedule', function(){

            });

            //绑定地图事件
            $table.on('click.show_map', '.map-show', function(){
                commons.messanger.info('功能暂未开放，敬请期待！');
            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _unsaved = true;

                var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                _table_scenery.row.add([
                    '-',
                    '<input type="text" class="form-control scenery-name" placeholder="景点名称" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control scenery-introduction" placeholder="景点描述" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control scenery-position" placeholder="景点位置" required style="width:100%; border-radius:4px;"/>',
                    '<input type="text" class="form-control scenery-price" placeholder="价格（元）" required style="width:100%; border-radius:4px;"/>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_scenery.rows()[0].length-1;

                var $tr = $(_table_scenery.row(_last_index).node());

                var $cells = $tr.find('td');

                var $operation = $($cells[6]);

                $($cells[0]).addClass('center');

                $($cells[0]).addClass('5');

                $operation.addClass('operation').addClass('center');

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    var $save = $(this);

                    var _uri = 'chapman/product/scenery/add/' + context.model.chapman.id;

                    commons.ajax.post(_uri, {

                        name:$tr.find('.scenery-name').val(),
                        introduction:$tr.find('.scenery-introduction').val(),
                        position:$tr.find('.scenery-position').val(),
                        price:$tr.find('.scenery-price').val()

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_scenery.rows()[0].length-1;

                        //删除编辑行
                        _table_scenery.rows(_last_index).remove();

                        //新增数据行
                        _table_scenery.row.add([
                            '<input type="checkbox" name="scenery" class="flat-red" value="'+data.scenery.id+'"/>',
                            '<a class="sub-product-show" data-toggle="tooltip" data-placement="bottom" title="子商品">' + data.scenery.name +'</a>',
                            data.scenery.introduction,
                            '<a class="map-show">'+data.scenery.position+'</a>',
                            data.scenery.price,
                            data.scenery.updateTime,
                            '<a class="on-shelve" data-placement="bottom" title="商品上架"><i class="fa fa-level-up"></i></a>'+
                            '<a class="properties" data-placement="bottom" title="商品属性" data-toggle="dropdown"><i class="fa fa-list"></i></a>' +
                            '<ul class="dropdown-menu" role="menu">'+
                                '<li><a data-hash="#/product-detail/' +data.scenery.id + '">商品扩展信息</a></li>'+
                                '<li><a data-hash="#/product-source/' +data.scenery.id + '">资源列表</a></li>'+
                                '<li><a data-hash="#/product-gd/' +data.scenery.id + '">图文详情</a></li>'+
                                '<li><a data-hash="#/product-timeline/' +data.scenery.id + '">进度图（流程图）</a></li>'+
                                '<li><a data-hash="#/product-schedule/' +data.scenery.id + '">排期表</a></li>'+
                            '</ul>'+
                            '<a class="edit" data-placement="bottom" title="编辑景点"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-placement="bottom" title="删除景点"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_scenery.rows()[0].length-1;

                        var $tr = $(_table_scenery.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        var $options = $($cells[6]);

                        //初始化复选框
                        commons.checkbox.init({
                            selector:'[name=scenery]',
                            content:$checkbox,
                            doCheckall:true
                        });

                        $($cells[4]).addClass('center');

                        $($cells[5]).addClass('center');

                        $options.addClass('operation').addClass('center');

                        $options.find('a.properties').dropdown();

                        $tr.find('.operation>a').tooltip();

                    }, 403);

                });

                //取消按钮
                $tr.on('click.cancel', '.cancel', function(){
                    $(this).tooltip('hide');
                    _table_scenery.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _sceneryIds = [];
                $table.find('input[name=scenery]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _sceneryIds.push($checkbox.val());
                    }
                });

                var _uri = 'chapman/product/scenery/remove/' + context.model.chapman.id;

                commons.ajax.delete(_uri, {

                    sceneryIds:_sceneryIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_sceneryIds.length+'条数据。');

                    _table_scenery.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        }, 200);

    };

    //初始化扩展信息
    var _init_scenery_detail = function($content, scenery){

        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的景点明细，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'chapman/product/scenery/show/details/' + context.model.chapman.id + '/' + scenery.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_body_detail.render({
                scenery:scenery.name,
                details:data.details
            }));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_scenery_detail = $table.DataTable({
                "order":[
                    [4, 'desc'],
                    [3, 'asc'],
                    [1, 'asc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 5]
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
            $table.on('click.sources', 'td .source', function(){
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
            });

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
                    '<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control detail-name" placeholder="名称" required style="width:100%; border-radius:4px;"/></div>',
                    '<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control detail-introduction" placeholder="简介" required style="width:100%; border-radius:4px;"/></div>',
                    '<input type="text" class="form-control detail-price" placeholder="价格（元）" required style="width:100%; border-radius:4px;"/>',
                    '<select class="form-control detail-type" style="border-radius:4px; width:100%;"><option value="PRODUCT">产品描述</option><option value="INFO">产品详情</option><option value="TIMELINE">时刻表</option><option value="SCHEDULE">排期表</option><option value="GTDETAILS">图文详情</option></select>',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_scenery_detail.rows()[0].length-1;

                var $tr = $(_table_scenery_detail.row(_last_index).node());

                var $cells = $tr.find('td');

                $($cells[0]).addClass('center');

                $($cells[4]).addClass('center');

                $($cells[5]).addClass('center');

                $($cells[6]).addClass('operation').addClass('center');

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

    //初始化资源列表
    var _init_scenery_detail_source = function($content, scenery, detail){
        var _unsaved = false;

        var doable = function(){
            if(_unsaved === true){
                commons.messanger.info('当前有未保存的景点明细，请先保存!');
                return false;
            }
            return true;
        }

        var _uri = 'chapman/product/scenery/show/detail/sources/' + context.model.chapman.id + '/' + scenery.id + '/' + detail.id;

        commons.ajax.get(_uri, null, function(data){

            var $body = $(prepare_body_source.render({
                scenery:scenery.name,
                detail:detail.name,
                sources:data.sources
            }));
            $content.empty().append($body);

            var $button_remove = $body.find('.button-remove');

            var $table = $body.find('table');

            var _table_scenery_detail_source = $table.DataTable({
                "order":[
                    [6, 'desc']
                ],
                columnDefs:[{
                    bSortable:false,
                    aTargets:[0, 7]
                }],
                paging:true
            });

            var toggle_button = function(){
                var hasSelected = false;
                $table.find('input[name=scenery-detail-source]').each(function(){
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
                selector:'input[name=scenery-detail-source]',
                context:$table,
                doCheckall:true
            });

            //绑定选中事件
            $table.on('ifChecked', 'input[name=scenery-detail-source]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').addClass('selected');
                }
                toggle_button();
            });

            $table.on('ifUnchecked', 'input[name=scenery-detail-source]', function(){
                var $checkbox = $(this);
                if(!$checkbox.is('checkall')){
                    $checkbox.closest('tr').removeClass('selected');
                }
                toggle_button();
            });

            //初始化tooltip
            var $tooltips = $table.find('.operation>a');
            $tooltips.tooltip();

            //新建按钮
            var $button_add = $body.find('.button-add');
            $button_add.on('click', function(){

                _unsaved = true;

                var date = new Date().format('yyyy-MM-dd hh:mm:ss');

                _table_scenery_detail_source.row.add([
                    '-',
                    '<button class="btn btn-info select-picture">选择图片</button><span class="picture-name" style="padding-left:10px;"></span>',
                    '<select class="form-control source-map-type" style="border-radius:4px; width:100%;"><option value="HOMEPAGE">首页图片</option><option value="THUMBNAIL">缩略图</option><option value="INFO">详情图</option></select>',
                    '<select class="form-control source-type" style="border-radius:4px; width:100%;"><option value="图片">图片</option></select>',
                    '-',
                    '-',
                    date,
                    '<a class="save" data-toggle="tooltip" data-placement="bottom" title="保存"><i class="fa fa-save"></i></a>' +
                    '<a class="cancel last" data-toggle="tooltip" data-placement="bottom" title="取消"><i class="fa fa-remove"></i></a>'
                ]).draw();

                var _last_index = _table_scenery_detail_source.rows()[0].length-1;

                var $tr = $(_table_scenery_detail_source.row(_last_index).node());

                var $cells = $tr.find('td');

                $($cells[0]).addClass('center');

                var $name = $($cells[1]);

                var $sourceMap = $($cells[2]);

                var $size = $($cells[4]);

                var $foldername = $($cells[5]);

                $($cells[6]).addClass('center');

                $($cells[7]).addClass('operation').addClass('center');

                var picture_info = null;

                //选择图片
                $tr.on('click.select-picture', '.select-picture', function(){

                    //初始化选中的图片
                    picture_info = null;

                    var select_complete = function(){
                        if(!picture_info) return;

                        $name.find('.picture-name').text(picture_info.name);

                        $size.text(picture_info.size);

                        $foldername.text(picture_info.foldername);
                    }

                    var $modal = commons.modal.createAndShow({
                        title:'选择图片<a class="preview btn btn-link btn-xs pull-right" data-toggle="tooltip" data-placement="bottom" title="预览" style="margin-left: 20px;position: relative;bottom: 2px;" disabled><i class="fa fa-eye"></i></a>'
                    });

                    $modal.on('hidden.bs.modal', function(){
                        select_complete();
                    });

                    var $preview = $modal.find('.preview');

                    $preview.tooltip();

                    var toggle_preview = function(){
                        if(!picture_info){
                            $preview.attr('disabled', true);
                        }else{
                            $preview.removeAttr('disabled');
                        }
                    }

                    $preview.on('click', function(){

                        commons.picbox.init({
                            name:picture_info.name
                        });

                        var _uri = 'chapman/source/picture/show/picture/' + context.model.chapman.id + '/' + picture_info.id;

                        commons.ajax.get(_uri, null, function(data){

                            commons.picbox.set({
                                name:picture_info.name,
                                img:data.file.img
                            });

                        });

                    });

                    var $modal_content = commons.modal.getContent($modal);

                    var _uri = 'chapman/source/picture/show/picture/tree/' + context.model.chapman.id + '/root';

                    commons.ajax.get(_uri, null, function(data){

                        $modal_content.tree('create', {
                            data:data.nodes,
                            event:{
                                click:function(param, complete){

                                    param = param.split('@@');
                                    var nodeType = param[0];
                                    if(nodeType === 'folder'){
                                        return;
                                    }else{
                                        picture_info = {
                                            id:param[1],
                                            name:param[2],
                                            size:param[3],
                                            folderId:param[4],
                                            foldername:param[5]
                                        };

                                        toggle_preview();

                                        complete();
                                    }

                                },

                                open:function(param, complete){
                                    var $node = $(this);
                                    if($node.tree('is', 'empty')){

                                        var _uri = 'chapman/source/picture/show/picture/tree/' + context.model.chapman.id + '/' + param.split('@@')[1];

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

                    }, 200, $modal_content);

                    //保存按钮
                    $modal.on('click.save', '.btn-save', function(){

                        commons.modal.close($modal);
                        select_complete();
                        if(!picture_info){
                            return;
                        }

                    });

                });

                //保存按钮
                $tr.on('click.save', '.save', function(){

                    if(!picture_info){
                        commons.messanger.info('您还没有选择图片。');
                        return;
                    }

                    var $save = $(this);

                    var _uri = 'chapman/product/scenery/add/detail/source/' + context.model.chapman.id + '/' + scenery.id + '/' + detail.id;

                    commons.ajax.post(_uri, {

                        sourceId:picture_info.id,
                        mapType:$sourceMap.find('select').val()

                    }, function(data, status){

                        if(status === 403) {
                            return;
                        }

                        _unsaved = false;

                        $save.tooltip('hide');

                        var _last_index = _table_scenery_detail_source.rows()[0].length-1;

                        //删除编辑行
                        _table_scenery_detail_source.rows(_last_index).remove();

                        //新增数据行
                        _table_scenery_detail_source.row.add([
                            '<input type="checkbox" name="scenery-detail-source" class="flat-red" value="'+data.source.id+'"/>',
                            data.source.name,
                            data.source.mapType,
                            data.source.sourceType,
                            data.source.size,
                            data.source.foldername,
                            data.source.updateTime,
                            '<a class="edit" data-toggle="tooltip" data-placement="bottom" title="编辑资源"><i class="fa fa-edit"></i></a>'+
                            '<a class="last remove" data-toggle="tooltip" data-placement="bottom" title="删除资源"><i class="fa fa-trash-o"></i></a>'
                        ]).draw();

                        _last_index = _table_scenery_detail_source.rows()[0].length-1;

                        var $tr = $(_table_scenery_detail_source.row(_last_index).node());

                        var $cells = $tr.find('td');

                        var $checkbox = $($cells[0]);

                        //初始化复选框
                        commons.checkbox.init({
                            selector:'[name=scenery-detail-source]',
                            content:$checkbox,
                            doCheckall:true
                        });

                        $($cells[6]).addClass('center');

                        $($cells[7]).addClass('operation').addClass('center');

                    }, 403);

                });

                //取消按钮
                $tr.on('click.cancel', '.cancel', function(){
                    $(this).tooltip('hide');
                    _table_scenery_detail_source.rows(_last_index).remove().draw();
                    _unsaved = false;
                });

            });

            //删除按钮
            $button_remove.on('click', function(){

                if(!doable()) return;

                //获取选中项目
                var _sourceMapIds = [];
                $table.find('input[name=scenery-detail-source]').each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _sourceMapIds.push($checkbox.val());
                    }
                });

                var _uri = 'chapman/product/scenery/remove/detail/source/' + context.model.chapman.id + '/' + scenery.id + '/' + detail.id;

                commons.ajax.delete(_uri, {

                    sourceMapIds:_sourceMapIds

                }, function(data){

                    commons.messanger.success('操作成功，当前删除：'+_sourceMapIds.length+'条数据。');

                    _table_scenery_detail_source.rows('.selected').remove().draw(false);

                    toggle_button();

                    $table.find('input.checkall').iCheck('uncheck');

                });

            });

        }, 200);

    };

    //初始化图文详情

    //初始化进度图（流程图）

    //排期表

    var _init = function(){

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
                }
            ]
        }));

        var $wrapper = $('<div></div>');
        $content.append($wrapper);

        //初始化景点列表
        _init_scenery($wrapper);

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
