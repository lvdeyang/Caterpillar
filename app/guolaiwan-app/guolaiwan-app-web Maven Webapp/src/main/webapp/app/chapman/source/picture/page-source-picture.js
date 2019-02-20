/**
 * Created by lvdeyang on 2017/6/20.
 */
define([

    'text!html-content-header',
    'text!html-source-picture-body',
    'text!html-source-picture-table',
    'text!html-source-picture-task',
    'juicer',
    'context',
    'page',
    'admin-lte',
    'commons',

], function(header, body, table, task, juicer, context, page, $, commons){

    var messanger = commons.messanger;

    var PAGENAME = 'source-picture';

    var _table_picture = null;

    var tableReady = function(){
        if(!_table_picture){
            messanger.info('当前表格还未加载完成!');
            return false;
        }
        return true;
    }

    //是否有未保存的新建文件夹
    var hasUnsaved = false;

    var doable = function(){
        if(hasUnsaved) {
            messanger.warning('当前有未保存的文件夹，请先保存!');
            return false;
        }
        return true;
    }

    //当前工作目录
    var _currSourceFolder = null,
        _uri = null;


    var _init = function(){

        //初始化工作目录
        _currSourceFolder = context.model.root;

        hasUnsaved = false;

        var $content = $('<div style="width:100%; min-height:876px;"></div>');
        context.setContent($content);

        //渲染页头
        $content.append(juicer(header).render({
            level_1:'图片素材管理工具',
            level_2:'版本：1.0.0',
            history:[
                {
                    name:'首页',
                    icon:'fa-home',
                    hash:'#/home'
                },{
                    name:'素材',
                },{
                    name:'图片'
                }
            ]
        }));

        var $body = $(body);
        $content.append($body);

        //功能按钮
        var $button_download = $body.find('.button-download'),
            $button_remove = $body.find('.button-remove'),
            $button_rename = $body.find('.button-rename'),
            $button_move = $body.find('.button-move'),
            $task_list = $body.find('#task-list>.box'),
            $task_num = $task_list.find('.task-num'),
            $task_table = $task_list.find('table>tbody');

        //统计checkbox状态
        var statistics_checkbox = function($checkboxList){

            var pictures = [];

            var folders = [];

            $checkboxList.each(function(){
                var $checkbox = $(this);
                if($checkbox.is('.checkall')) return;
                if($checkbox.is(':checked')){
                    var $type = $checkbox.closest('td').siblings('.type');
                    if($type.is('.folder')){
                        folders.push($checkbox.val());
                    }else if($type.is('.picture')){
                        pictures.push($checkbox.val());
                    }
                }
            });

            return {
                pictures:pictures,
                folders:folders
            }
        };

        //展示任务列表
        var task_show = function(){
            if($task_list.is(':hidden')){
                $task_list.show();
            }
        };

        //获取文件列表
        var get_files = function(){
            var files = [];
            $task_table.find('tr').each(function(){
                files.push($(this).data('file'));
            });
            return files;
        }

        //设置tooltip
        $body.find('[data-toggle=tooltip]').tooltip();

        var $pictureList = $body.find('#picture-list').first();

        $pictureList.on('click', 'td>a', function(){
            var $a = $(this),
                $tr = $a.closest('tr'),
                $cells = $tr.find('td'),
                $id = $($cells[0]),
                $type = $($cells[1]),
                $name = $($cells[2]);

            if($type.is('.folder')){
                //进入文件夹
                _currSourceFolder = {
                    id:$id.find('input').val(),
                    name:$name.find('a').text()
                }

                //进入文件夹
                _getFolderSource(_currSourceFolder);
            }else if($type.is('.picture')){

                commons.picbox.init();

                //获取图片
                var uri = 'chapman/source/picture/show/picture/' + context.model.chapman.id + '/' + $id.find('input').val();

                commons.ajax.get(uri, null, function(data){

                    commons.picbox.set({
                        img:data.file.img,
                        name:$name.text()
                    })

                });

            }
        });

        var _getFolderSource = function(folder){

            //销毁datatable实例
            if(_table_picture) _table_picture.destroy();

            $pictureList.empty();

            //加载默认文件夹
            _uri = 'chapman/source/picture/show/folder/' + context.model.chapman.id + '/' + folder.id;
            commons.ajax.get(_uri, null, function(data, status){

                $pictureList.empty();

                var $table = $(juicer(table).render(data));

                $pictureList.append($table);

                //创建图片表格
                _table_picture = $table.find('table').DataTable({
                    "order":[
                        [3, 'asc'],
                        [4, 'desc'],
                        [2, 'asc']
                    ],
                    columnDefs:[{
                        bSortable:false,
                        aTargets:[0, 1]
                    }],
                    paging:false
                });

                //初始化表格复选框
                commons.checkbox.init({
                    selector:'input[type=checkbox]',
                    context:$table,
                    doCheckall:true
                });

                //检查当前复选框的选中状态
                var toggle_button = function(){

                    var result = statistics_checkbox($pictureList.find('input[name=picture]'));

                    //选中图片数量
                    var num_picture = result.pictures.length;

                    //选中文件夹数量
                    var num_folder = result.folders.length;

                    //只有一张图片下载
                    if(num_picture===1 && num_folder===0){
                        $button_download.removeAttr('disabled');
                    }else{
                        $button_download.attr('disabled', '');
                    }

                    //有选中就能删除
                    if(num_picture>0 || num_folder>0){
                        $button_remove.removeAttr('disabled');
                    }else{
                        $button_remove.attr('disabled', '');
                    }

                    //只有一张图片[|文件夹]重命名[|移动]
                    if(num_picture===1 || num_folder===1){
                        $button_rename.removeAttr('disabled');
                        $button_move.removeAttr('disabled');
                    }else{
                        $button_rename.attr('disabled', '');
                        $button_move.attr('disabled', '');
                    }
                };

                toggle_button();

                //绑定复选框事件
                $pictureList.on('ifChecked', 'input[name=picture]', function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('checkall')){
                        $checkbox.closest('tr').addClass('selected');
                    }
                    toggle_button();
                });

                $pictureList.on('ifUnchecked', 'input[name=picture]', function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('checkall')){
                        $checkbox.closest('tr').removeClass('selected');
                    }
                    toggle_button();
                });

            });

        };

        //初始化表格
        _getFolderSource(_currSourceFolder);

        //任务列表按钮事件
        var $button_task = $body.find('.button-task');
        $button_task.on('click', function(){
            task_show();
        });

        //文件上传
        var $button_upload = $body.find('.button-upload');
        $button_upload.on('click', function(){
            $body.find('#target-picture').click();
        });

        //文件选择事件
        var $file_picture = $body.find('#target-picture');
        var prepare_task = juicer(task);
        $file_picture.on('change', function(){

            var files = $(this)[0].files;

            var fileExist = get_files(),
                task_no = $task_num.data('num') || 0,
                uploadList = [];

            //展示任务列表
            task_show();

            var illegal_type = false;

            var upload_list = {length:0};
            var $upload_list = [];

            for(var i= 0, len=files.length-1; i<=len; i++){
                var _newFile = files[i];

                //文件类型校验
                var _type = _newFile.type;
                if(_type!=='image/jpeg' && _type!=='image/png'){
                    illegal_type = true;
                    continue;
                }

                task_no += 1
                var $task = $(prepare_task.render({
                    no:task_no,
                    name:_newFile.name,
                    process:0,
                    folder:_currSourceFolder.name
                }));
                $task_table.append($task);
                $upload_list.push($task);

                upload_list[task_no] = _newFile;
                upload_list.length += 1;
            }
            $task_num.data('num', task_no);
            $task_num.text(task_no);

            //有非法类型给出提示
            if(illegal_type) commons.messanger.warning('当前系统只支持jpg和png类型，其他类型将被自动过滤。');

            //上传
            var _formData = new FormData();
            for(var i in upload_list){
                _formData.append(upload_list[i].name, upload_list[i]);
            }

            var _uri = 'chapman/source/picture/upload/' + context.model.chapman.id + '/' + _currSourceFolder.id
            commons.ajax.upload(_uri, _formData, function(data, status){

                //进度条设置成完成状态
                for(var i in $upload_list){
                    var $task = $upload_list[i];
                    $task.find('.progress-bar').css('width', '100%').removeClass('progress-bar-info').addClass('progress-bar-success');
                    $task.find('.badge').text('100%').removeClass('bg-aqua').addClass('bg-green');
                }

                var _pictures = data.pictures;
                for(var i= 0, len=_pictures.length-1; i<=len; i++){
                    _table_picture.row.add([
                        '<input type="checkbox" name="picture" class="flat-red" value="'+_pictures[i].id+'" />',
                        '<i class="fa fa-file-picture-o" class="target-type-picture" />',
                        '<a>' + _pictures[i].name + '</a>',
                        _pictures[i].size,
                        _pictures[i].updateTime
                    ]);
                }
                _table_picture.draw();

                var endIndex = _table_picture.rows()[0].length - 1;
                var beginIndex = _table_picture.rows()[0].length - _pictures.length - 1;
                for(i=(beginIndex<0?0:beginIndex); i<=endIndex; i++){
                    var $row =  $(_table_picture.row(i).node());

                    var $cells = $row.find('td');

                    //初始化checkbox
                    commons.checkbox.init({
                        selector:'input[type=checkbox]',
                        context:$row,
                        doCheckall:false
                    })

                    //初始化文件夹图标
                    $($cells[1]).addClass('type picture');

                }

            });
        });

        //新建文件夹
        var $button_add = $body.find('.button-add');
        $button_add.on('click', function(){

            if(!tableReady()) return;

            if(!doable()) return;

            //加一行
            hasUnsaved = true;

            var _oSettings = _table_picture.settings()[0];

            var oData = $.extend( true, {}, $.fn.dataTable.models.oRow, {
                src: 'data'
            });

            var date = new Date().format('yyyy-MM-dd hh:mm:ss');

            oData._aData = [
                '<button class="btn btn-link" data-toggle="tooltip" data-placement="bottom" title="取消编辑" style="padding:0 10px; font-size:22px; color:#fff; cursor:pointer;"><i class="fa fa-remove" /></button>',
                '<i class="fa fa-folder" class="target-type-folder" />',
                '<div class="col-lg-4 col-md-6 col-sm-8 col-xs-12" style="padding:0; margin:0; position:relative;"><input type="text" class="form-control" value="新建文件夹（'+date+'）"  style="width:100%; border-radius:4px;"/></div>',
                '-',
                date
            ];

            //加入第0位
            var exists = _oSettings.aoData;
            _oSettings.aoData = [];
            _oSettings.aoData.push(oData);
            for(var i=0, iLen=exists.length; i<iLen; i++){
                _oSettings.aoData.push(exists[i]);
            }

            var columns = _oSettings.aoColumns;
            for ( var i=0, iLen=columns.length ; i<iLen ; i++ ){
                columns[i].sType = null;
            }

            _oSettings.aiDisplayMaster.push(_oSettings.aiDisplayMaster.length);

            $.fn.dataTable.ext.internal._fnCreateTr(_oSettings, 0);

            _table_picture.draw();

            var $row = $(_table_picture.row(0).node()).addClass('bg-teal');

            var $cells = $row.find('td');

            //取消编辑按钮
            var $cancel = $($cells[0]).find('button');
            $cancel.tooltip();
            $cancel.on('focus', function(){
                _table_picture.rows(0).remove().draw();
                hasUnsaved = false;
            });

            //初始化文件夹图标
            $($cells[1]).addClass('type folder');

            //输入框获得焦点
            var $input = $($cells[2]).find('input');
            $input[0].focus();
            $input[0].select();

            $input.on('blur', function(event){

                var $this = $(this),
                    _foldname = $this.val();

                //做一个小小的延迟
                setTimeout(function(){

                    if($input.is(':hidden')) return;

                    var _uri = 'chapman/source/picture/add/folder/' + context.model.chapman.id + '/' + _currSourceFolder.id;

                    commons.ajax.post(_uri, {
                        foldername:_foldname
                    }, function(data, status){

                        if(status === 409){

                            $this[0].focus();
                            $this[0].select();

                        }else{

                            //修改未保存标记
                            hasUnsaved = false;

                            //删除一行
                            _table_picture.rows(0).remove().draw();

                            var _folder = data.folder;

                            //加一行
                            _table_picture.row.add([
                                '<input type="checkbox" name="picture" class="flat-red" value="'+_folder.id+'" />',
                                '<i class="fa fa-folder" class="target-type-folder" />',
                                '<a>' + _folder.name + '</a>',
                                '-',
                                _folder.updateTime
                            ]).draw();

                            var $row = $(_table_picture.row(_table_picture.rows()[0].length-1).node());

                            var $cells = $row.find('td');

                            //初始化checkbox
                            commons.checkbox.init({
                                selector:'input[type=checkbox]',
                                context:$row,
                                doCheckall:false
                            })

                            //初始化文件夹图标
                            $($cells[1]).addClass('type folder');

                        }

                    }, 409);

                },10);

            });

        });

        //删除
        $button_remove.on('click', function(){

            var $pictureList = $body.find('#picture-list').first();

            var result = statistics_checkbox($pictureList.find('input[name=picture]'));

            if(result.pictures.length===0 && result.folders.length===0) return;

            var _uri = 'chapman/source/picture/remove/' + context.model.chapman.id + '/' +_currSourceFolder.id;

            commons.ajax.delete(_uri, result, function(data, status){

                //删除选中的行
                _table_picture.rows('.selected').remove().draw(false);

                //取消checkall状态
                $body.find('.checkall[name=picture]').iCheck('uncheck');

            });

        });

    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
