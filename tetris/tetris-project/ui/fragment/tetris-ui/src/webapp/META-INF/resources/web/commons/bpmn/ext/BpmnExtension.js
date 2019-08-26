define([
    'jquery',
    'web/commons/bpmn/bpmn-modeler.development',
    'web/commons/bpmn/ext/i18n/i18n',
    'text!' + window.COMMONSPATH + 'bpmn/ext/empty.bpmn',
    'text!' + window.COMMONSPATH + 'bpmn/ext/html/bpmn-extension.html',
    'css!' + window.COMMONSPATH + 'bpmn/assets/diagram-js.css',
    'css!' + window.COMMONSPATH + 'bpmn/assets/bpmn-font/css/bpmn.css',
    'css!' + window.COMMONSPATH + 'bpmn/ext/css/diagram-extension.css'
], function($, BpmnJS, i18n, emptyXml, tpl){

    /***************************
     ********dependences********
     ***************************/

    var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

    function assign(target) {
        for (var _len = arguments.length, others = Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
            others[_key - 1] = arguments[_key];
        }
        return _extends.apply(undefined, [target].concat(others));
    }

    function getOriginal(event) {
        return event.originalEvent || event.srcEvent;
    }

    function isMac() {
        return (/mac/i).test(navigator.platform);
    }

    function isPrimaryButton(event) {
        // button === 0 -> left áka primary mouse button
        return !(getOriginal(event) || event).button;
    }

    function hasPrimaryModifier(event) {
        var originalEvent = getOriginal(event) || event;

        if (!isPrimaryButton(event)) {
            return false;
        }

        // Use alt as primary modifier key for mac OS
        if (isMac()) {
            return originalEvent.metaKey;
        } else {
            return originalEvent.ctrlKey;
        }
    }

    var nativeToString = Object.prototype.toString;
    var nativeHasOwnProperty = Object.prototype.hasOwnProperty;

    function isFunction(obj) {
        return nativeToString.call(obj) === '[object Function]';
    }

    function toMatcher(matcher) {
        return isFunction(matcher) ? matcher : function (e) {
            return e === matcher;
        };
    }

    function isUndefined(obj) {
        return obj === null || obj === undefined;
    }

    function isArray(obj) {
        return nativeToString.call(obj) === '[object Array]';
    }

    function contains(arr, item){
        if(!arr || arr.length<=0) return false;
        for(var i=0; i<arr.length; i++){
            if(arr[i] === item) return true;
        }
        return false;
    }

    function toNum(arg) {
        return Number(arg);
    }

    function identity(arg) {
        return arg;
    }

    function has(target, key) {
        return nativeHasOwnProperty.call(target, key);
    }

    function forEach(collection, iterator) {
        if (isUndefined(collection)) {
            return;
        }
        var convertKey = isArray(collection) ? toNum : identity;
        for (var key in collection) {
            if (has(collection, key)) {
                var val = collection[key];
                var result = iterator(val, convertKey(key));
                if (result === false) {
                    return;
                }
            }
        }
    }

    function find(collection, matcher) {
        matcher = toMatcher(matcher);
        var match;
        forEach(collection, function (val, key) {
            if (matcher(val, key)) {
                match = val;
                return false;
            }
        });
        return match;
    }

    function some(collection, matcher) {
        return !!find(collection, matcher);
    }

    function getBusinessObject(element) {
        return (element && element.businessObject) || element;
    }

    function is$1(element, type) {
        var bo = getBusinessObject(element);
        return bo && (typeof bo.$instanceOf === 'function') && bo.$instanceOf(type);
    }

    function isAny(element, types) {
        return some(types, function(t) {
            return is$1(element, t);
        });
    }

    function is(element, type) {
        var bo = getBusinessObject(element);
        return bo && (typeof bo.$instanceOf === 'function') && bo.$instanceOf(type);
    }

    function isEventType(eventBo, type, definition) {
        var isType = eventBo.$instanceOf(type);
        var isDefinition = false;
        var definitions = eventBo.eventDefinitions || [];
        forEach(definitions, function(def) {
            if (def.$type === definition) {
                isDefinition = true;
            }
        });
        return isType && isDefinition;
    }

    function isEventSubProcess(element) {
        return element && !!getBusinessObject(element).triggeredByEvent;
    }

    var round$10 = Math.round;

    function mid$2(bounds, defaultValue) {

        if (!bounds || isNaN(bounds.x) || isNaN(bounds.y)) {
            return defaultValue;
        }

        return {
            x: round$10(bounds.x + bounds.width / 2),
            y: round$10(bounds.y + bounds.height / 2)
        };
    }

    function endWith(targetStr, endStr){
        var d = targetStr.length - endStr.length;
        return (d>=0 && targetStr.lastIndexOf(endStr)==d);
    }

    /***************************
     ************utils**********
     ***************************/

    //获取bpmn-js的模块定义
    var getModuleDefinetion = function(moduleName){
        var modules = BpmnJS.prototype.getModules();
        if(modules!=null && modules.length>0){
            for(var i=0; i<modules.length; i++){
                var module = modules[i];
                if(module[moduleName]){
                    return module[moduleName][1];
                }
            }
        }
        return null;
    };

    /***************************
     *******custom module*******
     ***************************/

    //默认的流程节点
    function FlowNode(){}

    /**
     * entries格式示例
     * {
     *      key:'approvalService.managerApproval',
     *      name:'经理通过任务',
     *      expression:'#{approvalService.managerApproval(execution, time)}',
     *      icon:''
     *  }
     */
    FlowNode.prototype.getEntries = function(){
        return [];
    };

    FlowNode.prototype.getModule = function(){
        return {
            __init__: [ 'flowNode' ],
            flowNode:['type', FlowNode]
        };
    };

    //加入节点规则
    function FlowNodeRules(bpmnRules, elementRegistry){
        bpmnRules.addRule('elements.delete', 0, function(context){
            var allowed = [];
            var elements = context.elements;
            if(elements && elements.length>0){
                for(var i=0; i<elements.length; i++){
                    var businessObject = elements[i].businessObject;
                    if(!is(businessObject, 'bpmn:StartEvent') && !is(businessObject, 'bpmn:EndEvent')){
                        if(is(businessObject, 'bpmn:ReceiveTask')){
                            var asynchronousNode = elementRegistry.filter(function(scope, svg){
                                if(scope.businessObject && (businessObject.$attrs['extension:reference']==scope.businessObject.$attrs['extension:reference'])){
                                    return true;
                                }
                            });
                            if(asynchronousNode && asynchronousNode.length>0){
                                var canDelete = false;
                                for(var j=0; j<asynchronousNode.length; j++){
                                    if(asynchronousNode[j].businessObject.$attrs['extension:removeable'] === true){
                                        canDelete = true;
                                        break;
                                    }
                                }
                                if(canDelete){
                                    for(var j=0; j<asynchronousNode.length; j++){
                                        allowed.push(asynchronousNode[j]);
                                    }
                                }
                            }
                        }else if(is(businessObject, 'bpmn:ServiceTask')){
                            if(businessObject.$attrs['extension:removeable'] === true){
                                if(businessObject.$attrs['extension:type'] === 'REMOTE_ASYNCHRONOUS'){
                                    var asynchronousNode = elementRegistry.filter(function(scope, svg){
                                        if(scope.businessObject && (businessObject.$attrs['extension:reference']==scope.businessObject.$attrs['extension:reference'])){
                                            return true;
                                        }
                                    });
                                    for(var j=0; j<asynchronousNode.length; j++){
                                        allowed.push(asynchronousNode[j]);
                                    }
                                }else{
                                    allowed.push(elements[i]);
                                }
                            }
                        }else{
                            allowed.push(elements[i]);
                        }
                    }
                }
            }
            return allowed;
        });
    }

    FlowNodeRules.prototype.getModel = function(){
        return {
            __init__:['flowNodeRules'],
            flowNodeRules:['type', FlowNodeRules]
        };
    };

    //原生ContextPadProvider
    //var NativeContextPadProvider = getModuleDefinetion('contextPadProvider');

    //ContextPadProvider模块重写
    function ContextPadProvider(
        config, injector, eventBus,
        contextPad, modeling,
        elementFactory, elementRegistry,
        connect, create, popupMenu,
        canvas, rules, translate,
        flowNode){

        config = config || {};

        contextPad.registerProvider(this);

        this._contextPad = contextPad;

        this._modeling = modeling;

        this._elementFactory = elementFactory;
        this._elementRegistry = elementRegistry;
        this._connect = connect;
        this._create = create;
        this._popupMenu = popupMenu;
        this._canvas = canvas;
        this._rules = rules;
        this._translate = translate;
        this._flowNode = flowNode;

        if (config.autoPlace !== false) {
            this._autoPlace = injector.get('autoPlace', false);
        }

        eventBus.on('create.end', 250, function(event) {
            var shape = event.context.shape;

            if (!hasPrimaryModifier(event)) {
                return;
            }

            var entries = contextPad.getEntries(shape);

            if (entries.replace) {
                entries.replace.action.click(event, shape);
            }
        });

    }

    ContextPadProvider.prototype.getContextPadEntries = function(element){

        var contextPad = this._contextPad,
            modeling = this._modeling,

            elementFactory = this._elementFactory,
            elementRegistry = this._elementRegistry,
            connect = this._connect,
            create = this._create,
            popupMenu = this._popupMenu,
            canvas = this._canvas,
            rules = this._rules,
            autoPlace = this._autoPlace,
            translate = this._translate,
            flowNode = this._flowNode;

        var actions = {};

        if (element.type === 'label') {
            return actions;
        }

        var businessObject = element.businessObject;

        function startConnect(event, element) {
            connect.start(event, element);
        }

        function removeElement(e) {
            if(element.businessObject.$attrs['extension:removeable'] !== false){
                if(element.businessObject.$attrs['extension:type'] === 'REMOTE_ASYNCHRONOUS'){
                    var asynchronousNode = elementRegistry.filter(function(scope, svg){
                        if(scope.businessObject && (element.businessObject.$attrs['extension:reference']==scope.businessObject.$attrs['extension:reference'])){
                            return true;
                        }
                    });
                    modeling.removeElements(asynchronousNode);
                }else{
                    modeling.removeElements([element]);
                }
            }
        }

        function getReplaceMenuPosition(element) {

            var Y_OFFSET = 5;

            var diagramContainer = canvas.getContainer(),
                pad = contextPad.getPad(element).html;

            var diagramRect = diagramContainer.getBoundingClientRect(),
                padRect = pad.getBoundingClientRect();

            var top = padRect.top - diagramRect.top;
            var left = padRect.left - diagramRect.left;

            var pos = {
                x: left,
                y: top + padRect.height + Y_OFFSET
            };

            return pos;
        }

        function appendAction(type, className, title, options, service, group) {

            if (typeof title !== 'string') {
                options = title;
                title = translate('Append {type}', { type: type.replace(/^bpmn:/, '') });
            }

            function appendStart(event, element) {

                var shape = elementFactory.createShape(assign({ type: type }, options));

                if(service){
                    shape.businessObject.$attrs['activiti:expression'] = service.expression;
                    shape.businessObject.name = service.name;
                    shape.businessObject.$attrs['extension:removeable'] = service.removeable;
                    shape.businessObject.$attrs['extension:type'] = service.type;
                    shape.businessObject.$attrs['extension:reference'] = new Date().getTime() + '-' + service.id;
                    modeling.updateProperties(shape, {
                        name: service.name
                    });
                }

                create.start(event, shape, element);
            }

            var append = autoPlace ? function(event, element) {

                var shape = elementFactory.createShape(assign({ type: type }, options));
                if(service){
                    shape.businessObject.$attrs['activiti:expression'] = service.expression;
                    shape.businessObject.name = service.name;
                    shape.businessObject.$attrs['extension:removeable'] = service.removeable;
                    shape.businessObject.$attrs['extension:type'] = service.type;
                    shape.businessObject.$attrs['extension:reference'] = new Date().getTime() + '-' + service.id;
                    modeling.updateProperties(shape, {
                        name: service.name
                    });
                }
                autoPlace.append(element, shape);

                if(service && service.type === 'REMOTE_ASYNCHRONOUS'){
                    var target = elementFactory.createShape(assign({type:'bpmn:ReceiveTask'}, null));
                    target.businessObject.name = shape.businessObject.name + '回调';
                    target.businessObject.$attrs['extension:removeable'] = shape.businessObject.$attrs['extension:removeable'];
                    target.businessObject.$attrs['extension:reference'] = shape.businessObject.$attrs['extension:reference'];
                    modeling.updateProperties(target, {
                        name:target.businessObject.name
                    });
                    autoPlace.append(shape, target);
                }

            } : appendStart;

            return {
                group: group || 'model',
                className: className,
                title: title,
                action: {
                    dragstart: appendStart,
                    click: append
                }
            };
        }

        var items = {};

        //流程连线
        var addConnect = function(){
            items['connect'] = {
                group: '通用节点',
                className: 'bpmn-icon-connection-multi',
                title: translate('Activate the global connect tool'),
                action: {
                    click: startConnect,
                    dragstart: startConnect
                }
            }
        };

        //节点删除
        var addDelete = function(){
            items['delete'] = {
                group: 'edit',
                className: 'bpmn-icon-trash',
                title: translate('Remove'),
                action: {
                    click: removeElement
                }
            }
        };

        //分支节点
        var addExclusiveGateway = function(){
            items['append.gateway.exclusive'] = appendAction('bpmn:ExclusiveGateway', 'bpmn-icon-gateway-xor', translate('Append ExclusiveGateway'), null, null, '通用节点');
        };

        //用户节点
        var addUserTask = function(){
            items['append.task.user'] = appendAction('bpmn:UserTask', 'feather-icon-user', translate('Append UserTask'), null, null, '通用节点');
        };

        //服务节点
        var groupEntries = flowNode.getEntries();
        var addServiceTasks = function(){
            if(groupEntries && groupEntries.length>0){
                for(var i=0; i<groupEntries.length; i++){
                    var groupEntry = groupEntries[i];
                    var entries = groupEntry.entries;
                    if(entries && entries.length>0){
                        for(var j=0; j<entries.length; j++){
                            var service = entries[j];
                            items['append.' + service.id] = appendAction('bpmn:ServiceTask',  'feather-icon-settings', '附加' + service.name, null, service, groupEntry.name);
                        }
                    }
                }
            }
        };

        //修改类型节点
        var addReplace = function(){
            items['replace'] = {
                group: 'edit',
                className: 'bpmn-icon-screw-wrench',
                title: translate('Change type'),
                action: {
                    click: function(event, element) {
                        var position = assign(getReplaceMenuPosition(element), {
                            cursor: { x: event.x, y: event.y }
                        });
                        popupMenu.open(element, 'service-popup', position);
                        var $container = $(flowNode.getContainer());
                        var $popup = $container.find('.djs-popup');
                        var groupEntries = flowNode.getEntries();
                        if(groupEntries && groupEntries.length>0){
                            var $groupBody = $popup.find('.djs-popup-body');
                            var $entries = $groupBody.children();
                            for(var i=0; i<groupEntries.length; i++){
                                var groupEntry = groupEntries[i];
                                var $innerGroup = $('<div class="group"></div>');
                                var $header = $('<div class="group-header"><span class="header-text">'+groupEntry.name+'</span><span class="feather-icon-chevron-right"></span></div>');
                                var $body = $('<div class="group-entries"></div>');
                                $groupBody.append($innerGroup.append($header).append($body));
                                var entries = groupEntry.entries;
                                if(entries && entries.length>0){
                                    for(var j=0; j<entries.length; j++){
                                        var service = entries[j];
                                        for(var k=0; k<$entries.length; k++){
                                            var $entry = $($entries[k]);
                                            if(service.id == $entry.data('id').split('.')[1]){
                                                $body.append($entry);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            $popup.show();
                        }
                    }
                }
            }
        };

        var nodeType = flowNode.getNodeType();

        if(is(businessObject, 'bpmn:StartEvent')){
            //开始节点
            if(contains(nodeType, 'user')) addUserTask();
            if(contains(nodeType, 'service')) addServiceTasks();
            if(contains(nodeType, 'gateway')) addExclusiveGateway();
            if(contains(nodeType, 'tool')) addConnect();
        }else if(is(businessObject, 'bpmn:EndEvent')){
            //结束节点
        }else if(is(businessObject, 'bpmn:ExclusiveGateway')){
            //分支节点
            if(contains(nodeType, 'user')) addUserTask();
            if(contains(nodeType, 'service')) addServiceTasks();
            if(contains(nodeType, 'gateway')) addExclusiveGateway();
            if(contains(nodeType, 'tool')) addConnect();
            addDelete();
        }else if(is(businessObject, 'bpmn:UserTask')){
            //用户节点
            if(contains(nodeType, 'user')) addUserTask();
            if(contains(nodeType, 'service')) addServiceTasks();
            if(contains(nodeType, 'gateway')) addExclusiveGateway();
            if(contains(nodeType, 'tool')) addConnect();
            addDelete();
        }else if(is(businessObject, 'bpmn:ServiceTask')){
            //服务节点
            if(businessObject.$attrs['extension:type'] === 'REMOTE_ASYNCHRONOUS'){
                if(contains(nodeType, 'tool')) addConnect();
                if(contains(nodeType, 'service')) addReplace();
                if(businessObject.$attrs['extension:removeable']===true) addDelete();
            }else{
                if(contains(nodeType, 'user')) addUserTask();
                if(contains(nodeType, 'service')) addServiceTasks();
                if(contains(nodeType, 'gateway')) addExclusiveGateway();
                if(contains(nodeType, 'service')) addReplace();
                if(contains(nodeType, 'tool')) addConnect();
                if(businessObject.$attrs['extension:removeable']===true) addDelete();
            }
        }else if(is(businessObject, 'bpmn:ReceiveTask')){
            //回调节点
            if(contains(nodeType, 'user')) addUserTask();
            if(contains(nodeType, 'service')) addServiceTasks();
            if(contains(nodeType, 'gateway')) addExclusiveGateway();
            if(contains(nodeType, 'tool')) addConnect();
        }else if(is(businessObject, 'bpmn:SequenceFlow')){
            //流程连线
            addDelete();
        }

        //删除
        assign(actions, items);

        return actions;
    };

    ContextPadProvider.prototype.getModule = function(){
        return {
            __depends__: [
                ServicePopupMenuProvider.prototype.getModule(),
                FlowNode.prototype.getModule(),
                FlowNodeRules.prototype.getModel()
            ],
            __init__: [ 'contextPadProvider' ],
            contextPadProvider:['type', ContextPadProvider]
        };
    };

    //弹出菜单
    function ServicePopupMenuProvider(
        popupMenu, modeling, moddle,
        elementFactory, elementRegistry,
        bpmnReplace, rules, translate,
        modeling, flowNode, autoPlace){

        this._popupMenu = popupMenu;
        this._modeling = modeling;
        this._moddle = moddle;
        this._elementFactory = elementFactory;
        this._elementRegistry = elementRegistry;
        this._bpmnReplace = bpmnReplace;
        this._rules = rules;
        this._translate = translate,
        this._modeling = modeling,
        this._flowNode = flowNode;
        this._autoPlace = autoPlace;

        popupMenu.registerProvider('service-popup', this);

    }

    ServicePopupMenuProvider.prototype.getEntries = function(element){
        var elementFactory = this._elementFactory;
        var elementRegistry = this._elementRegistry;
        var modeling = this._modeling;
        var flowNode = this._flowNode;
        var autoPlace = this._autoPlace;
        var businessObject = element.businessObject;
        var id = businessObject.$attrs['extension:reference'].split('-')[1];
        var items = [];
        var groupEntries = flowNode.getEntries();
        for(var i=0; i<groupEntries.length; i++){
            var groupEntry = groupEntries[i];
            var entries = groupEntry.entries;
            if(entries && entries.length>0){
                for(var j=0; j<entries.length; j++){
                    var service = entries[j];
                    if(service.id != id){
                        items.push({
                            label:'转换为' + service.name,
                            className:'feather-icon-settings',
                            id:'pop.' + service.id,
                            action:(function(scope){
                                return function(){
                                    var oldType = businessObject.$attrs['extension:type'];
                                    var oldId = businessObject.$attrs['extension:reference'];
                                    businessObject.$attrs['activiti:expression'] = scope.expression;
                                    businessObject.name = scope.name;
                                    businessObject.$attrs['extension:removeable'] = scope.removeable;
                                    businessObject.$attrs['extension:type'] = scope.type;
                                    businessObject.$attrs['extension:reference'] = new Date().getTime() + '-' + scope.id;
                                    modeling.updateProperties(element, {
                                        name: scope.name
                                    });
                                    if(oldType==='REMOTE_ASYNCHRONOUS' && scope.type!=='REMOTE_ASYNCHRONOUS'){
                                        //异步变到同步，删除receive
                                        var asynchronousNode = elementRegistry.filter(function(scope, svg){
                                            if(scope.businessObject && (oldId==scope.businessObject.$attrs['extension:reference']) && is(scope.businessObject, 'bpmn:ReceiveTask')){
                                                return true;
                                            }
                                        });
                                        modeling.removeElements(asynchronousNode);
                                    }else if(oldType!=='REMOTE_ASYNCHRONOUS' && scope.type==='REMOTE_ASYNCHRONOUS'){
                                        //同步变道异步，添加receive
                                        var target = elementFactory.createShape(assign({type:'bpmn:ReceiveTask'}, null));
                                        target.businessObject.name = businessObject.name + '回调';
                                        target.businessObject.$attrs['extension:removeable'] = businessObject.$attrs['extension:removeable'];
                                        target.businessObject.$attrs['extension:reference'] = businessObject.$attrs['extension:reference'];
                                        modeling.updateProperties(target, {
                                            name:businessObject.name
                                        });
                                        autoPlace.append(element, target);
                                    }
                                }
                            })(service)
                        });
                    }
                }
            }
        }
        return items;
    };

    ServicePopupMenuProvider.prototype.getModule = function(){
        return {
            __depends:[
                FlowNode.prototype.getModule()
            ],
            __init__:['servicePopupMenuProvider'],
            servicePopupMenuProvider:['type', ServicePopupMenuProvider]
        }
    };

    //原生调色板定义
    //var NativePaletteProvider = getModuleDefinetion('paletteProvider');

    //重写调色板模块
    function PaletteProvider(
        palette, create, elementFactory,
        spaceTool, lassoTool, handTool,
        globalConnect, translate, modeling,
        flowNode) {

        this._palette = palette;
        this._create = create;
        this._elementFactory = elementFactory;
        this._spaceTool = spaceTool;
        this._lassoTool = lassoTool;
        this._handTool = handTool;
        this._globalConnect = globalConnect;
        this._translate = translate;
        this._modeling = modeling;
        this._flowNode = flowNode;

        palette.registerProvider(this);
    }

    PaletteProvider.prototype.getPaletteEntries = function(element){

        var actions = {},
            create = this._create,
            elementFactory = this._elementFactory,
            spaceTool = this._spaceTool,
            lassoTool = this._lassoTool,
            handTool = this._handTool,
            globalConnect = this._globalConnect,
            translate = this._translate,
            modeling = this._modeling,
            flowNode = this._flowNode;

        function createAction(type, group, className, title, options, service) {

            function createListener(event) {
                var shape = elementFactory.createShape(assign({ type: type }, options));

                if (options) {
                    shape.businessObject.di.isExpanded = options.isExpanded;
                }

                if(service){
                    if(!shape.businessObject.$attrs) shape.businessObject.$attrs = {};
                    shape.businessObject.$attrs['activiti:expression'] = service.expression;
                    shape.businessObject.name = service.name;
                    shape.businessObject.$attrs['extension:removeable'] = service.removeable;
                    shape.businessObject.$attrs['extension:type'] = service.type;
                    shape.businessObject.$attrs['extension:reference'] = new Date().getTime() + '-' + service.id;
                    modeling.updateProperties(shape, {
                        name: service.name
                    });
                }

                create.start(event, shape);
            }

            var shortType = type.replace(/^bpmn:/, '');

            return {
                group: group,
                className: className,
                title: title || translate('Create {type}', { type: shortType }),
                action: {
                    dragstart: createListener,
                    click: createListener
                }
            };
        }

        function createParticipant(event, collapsed) {
            create.start(event, elementFactory.createParticipantShape(collapsed));
        }

        var nodeType = flowNode.getNodeType();

        var items = {};
        if(contains(nodeType, 'tool')){
            items['hand-tool'] = {
                group: 'tools',
                className: 'bpmn-icon-hand-tool',
                title: translate('Activate the hand tool'),
                action: {
                    click: function(event) {
                        handTool.activateHand(event);
                    }
                }
            };
            items['lasso-tool'] = {
                group: 'tools',
                className: 'bpmn-icon-lasso-tool',
                title: translate('Activate the lasso tool'),
                action: {
                    click: function(event) {
                        lassoTool.activateSelection(event);
                    }
                }
            };
            items['global-connect-tool'] = {
                group: 'tools',
                className: 'bpmn-icon-connection-multi',
                title: translate('Activate the global connect tool'),
                action: {
                    click: function(event) {
                        globalConnect.toggle(event);
                    }
                }
            };
        }

        if(contains(nodeType, 'gateway')){
            items['create.exclusive-gateway'] = createAction(
                'bpmn:ExclusiveGateway',
                '通用节点',
                'bpmn-icon-gateway-xor',
                translate('Create ExclusiveGateway')
            );
        }

        if(contains(nodeType, 'user')){
            items['create.task'] = createAction(
                'bpmn:UserTask',
                '通用节点',
                'feather-icon-user',
                translate('Create UserTask')
            );
        }

        if(contains(nodeType, 'service')){
            var groupEntrys = flowNode.getEntries();
            if(groupEntrys && groupEntrys.length>0){
                for(var i=0; i<groupEntrys.length; i++){
                    var groupEntry = groupEntrys[i];
                    var entries = groupEntry.entries;
                    if(entries && entries.length>0){
                        for(var j=0; j<entries.length; j++){
                            var service = entries[j];
                            items['create.' + service.uuid] = createAction(
                                'bpmn:ServiceTask',
                                groupEntry.name,
                                'feather-icon-settings',
                                service.name,
                                null,
                                service
                            );
                        }
                    }
                }
            }
        }
        assign(actions, items);

        return actions;
    };

    PaletteProvider.prototype.getModule = function(){
        return {
            __depends__:[
                FlowNode.prototype.getModule()
            ],
            __init__:['paletteProvider'],
            paletteProvider:['type', PaletteProvider]
        }
    };

    //Translate模块重写
    function Translate(template, replacements) {
        replacements = replacements || {};

        // Translate
        template = i18n[template] || template;

        // Replace
        return template.replace(/{([^}]+)}/g, function(_, key) {
            return replacements[key] || '{' + key + '}';
        });
    }

    Translate.prototype.getModule = function(){
        return {
            translate: [ 'value', Translate ]
        }
    };

    /***************************
     **********theming**********
     ***************************/

    //文本渲染
    var textRenderer = {
        defaultStyle: {
            fontSize: 14,
            lineHeight: 1.6
        },
        externalStyle: {
            fontSize: 14,
            lineHeight: 1.6
        }
    };

    /**************************
     *********节点扩展*********
     **************************/

    var activitiMetaModel = {
        "name": "activiti",
        "uri": "http://activiti.org/bpmn",
        "prefix": "activiti",
        "xml": {
            "tagAlias": "lowerCase"
        },
        "types": [
            {
                "name": "ActivitiAssignee",
                "extends": [
                    "bpmn:UserTask"
                ],
                "properties": [
                    {
                        "name": "assignee",
                        "isAttr": true,
                        "type": "String"
                    }
                ]
            },{
                "name": "ActivitiCandidateUsers",
                "extends": [
                    "bpmn:UserTask"
                ],
                "properties": [
                    {
                        "name": "candidateUsers",
                        "isAttr": true,
                        "type": "String"
                    }
                ]
            },{
                "name": "ActivitiCandidateGroups",
                "extends": [
                    "bpmn:UserTask"
                ],
                "properties": [
                    {
                        "name": "candidateGroups",
                        "isAttr": true,
                        "type": "String"
                    }
                ]
            },{
                "name": "ActivitiExpression",
                "extends": [
                    "bpmn:ServiceTask"
                ],
                "properties": [
                    {
                        "name": "expression",
                        "isAttr": true,
                        "type": "String"
                    }
                ]
            }
        ]
    };

    var extensionMetaModel = {
        "name": "extension",
        "uri": "http://sumavision.org/bpmn/extention",
        "prefix": "extension",
        "xml": {
            "tagAlias": "lowerCase"
        },
        "types": [
            {
                "name": "ExtensionReference",
                "extends": [
                    "bpmn:ServiceTask",
                    "bpmn:ReceiveTask"
                ],
                "properties": [
                    {
                        "name": "reference",
                        "isAttr": true,
                        "type": "String"
                    }
                ]
            },{
                "name": "ExtensionBaseProps",
                "extends": [
                    "bpmn:ServiceTask"
                ],
                "properties": [
                    {
                        "name": "removeable",
                        "isAttr": true,
                        "type": "Boolean"
                    },{
                        "name": "type",
                        "isAttr": true,
                        "type": "String"
                    }
                ]
            }
        ]
    };

    /**************************
     **********入口类**********
     **************************/

    /**
     * @param options.id process id
     * @param options.container dom.id selector(#xxxx)
     * @param options.nodeType array: [tools, user, service, gateWay]
     * @param options.entries
     * {
     *     key:'approvalService.managerApproval',
     *     name:'经理通过任务',
     *     expression:'#{approvalService.managerApproval(execution, time)}',
     *     icon:''
     * }
     * @param options.xml 初始化bpmn-xml 默认空的bpmn-xml
     * @param options.onReady 渲染xml成功回调
     * @param options.onSave 保存xml
     * @param options.onSaveVariable 保存变量
     * @param options.queryVariables 查询变量
     * @param options.queryUsers 查询用户
     * @param options.totalUsers 查询全部用户
     * @param options.queryRoles 查询权限
     * @param options.totalRoles 查询全部权限
     */
    function BpmnJSExtension(options){

        var defaultNodeType = ['tool', 'user', 'service', 'gateWay'];
        var processId = this._processId = options.id;
        var uuid = this._uuid = options.uuid;
        var container = this._container = options.container;
        var entries = this._entries = options.entries;
        var xml = this._xml = options.xml || emptyXml;
        var userTaskBindVariables = this._userTaskBindVariables = options.userTaskBindVariables || {show:[], set:[]};
        var nodeType = this._nodeType = options.nodeType;
        var onReady = this._onReady = options.onReady;
        var onSave = this._onSave = options.onSave;
        var onSaveVariable = this._onSaveVariable = options.onSaveVariable;
        var onDeleteVariable = this._onDeleteVariable = options.onDeleteVariable;
        var queryVariables = this._queryVariables = options.queryVariables;
        var queryUsers = this._queryUsers = options.queryUsers;
        var onBindUserClick = this._onBindUserClick = options.onBindUserClick;
        var queryRoles = this._queryRoles = options.queryRoles;
        var onBindRoleClick = this._onBindRoleClick = options.onBindRoleClick;
        var onBindVariableClick = this._onBindVariableClick = options.onBindVariableClick;
        var onEditVariableClick = this._onEditVariableClick = options.onEditVariableClick;
        var isViewer = this._isViewer = options.isViewer;
        var completeTaskIds = this._completeTaskIds = options.completeTaskIds;

        var $container = $(container);
        var id = container + '-' + new Date().getTime();

        var $tpl = $(tpl).attr('id', id.substring(1, id.length));
        $container.append($tpl);

        if(typeof queryVariables==='function'){
            queryVariables(function(varialbles){
                $tpl.find('.prop-panel-content-var tbody').append(generateVariablesTemplate(varialbles));
            });
        }

        var $form_process_id = $tpl.find('.prop-process-id');
        if(processId) $form_process_id.text('流程id: ' + processId);

        function FlowNode(){}

        FlowNode.prototype.getContainer = function(){
            return id;
        }

        FlowNode.prototype.getEntries = function(){
            return entries || [];
        };

        FlowNode.prototype.getModule = function(){
            return {
                __init__: [ 'flowNode' ],
                flowNode:['type', FlowNode]
            };
        };

        FlowNode.prototype.getNodeType = function(){
            var totalNodeType = nodeType || defaultNodeType;
            if(!contains(totalNodeType, 'tool')) totalNodeType.push('tool');
            return totalNodeType;
        };

        var bpmnInstance = new BpmnJS({
            container: id,
            keyboard: {
                bindTo: window
            },
            additionalModules: [
                ContextPadProvider.prototype.getModule(),
                PaletteProvider.prototype.getModule(),
                Translate.prototype.getModule(),
                FlowNode.prototype.getModule()
            ],
            moddleExtensions: {
                activiti: activitiMetaModel,
                extension: extensionMetaModel
            },
            textRenderer: textRenderer
        });

        var self = this;

        self.nativeInstance = bpmnInstance;

        bpmnInstance.importXML(xml, function(){
            var elementRegistry = self.get('elementRegistry');
            var overlays = self.get('overlays');
            elementRegistry.forEach(function(element){
                if(!element.businessObject) return;
                if(!element.businessObject.$attrs) element.businessObject.$attrs = {};
                if(element.businessObject.candidateUsers){
                    element.businessObject.$attrs['activiti:candidateUsers'] = element.businessObject.candidateUsers;
                    delete element.businessObject.candidateUsers;
                }
                if(element.businessObject.candidateGroups){
                    element.businessObject.$attrs['activiti:candidateGroups'] = element.businessObject.candidateGroups;
                    delete element.businessObject.candidateGroups;
                }
                if(element.businessObject.removeable){
                    element.businessObject.$attrs['extension:removeable'] = element.businessObject.removeable;
                    delete element.businessObject.removeable;
                }
                if(element.businessObject.type){
                    element.businessObject.$attrs['extension:type'] = element.businessObject.type;
                    delete element.businessObject.type;
                }
                if(element.businessObject.reference){
                    element.businessObject.$attrs['extension:reference'] = element.businessObject.reference;
                    delete element.businessObject.reference;
                }
                if(is(element.businessObject, 'bpmn:SequenceFlow') &&
                    element.businessObject.conditionExpression &&
                    element.businessObject.conditionExpression.body){
                    overlays.add(element, {
                        position: {
                            top: 5,
                            left: 5
                        },
                        html: $('<div class="sequence-flow-expression-label">'+element.businessObject.conditionExpression.body.substring(2, element.businessObject.conditionExpression.body.length-1)+'</div>')
                    });
                    element.businessObject.conditionExpression.body = '<![CDATA['+element.businessObject.conditionExpression.body+']]>';
                }
            });
            if(processId){
                elementRegistry.forEach(function(element){
                    if(is(element.businessObject, 'bpmn:Process')){
                        element.businessObject.id = processId;
                    }
                });
            }
            var $container = $(container);
            var $palette = $container.find('.djs-palette');
            var $groups = $container.find('.djs-palette .group');
            for(var i=0; i<$groups.length; i++){
                var $group = $($groups[i]);
                var $header = $('<div class="group-header"><span class="header-text">'+($group.data('group')==='tools'?'画布工具':$group.data('group'))+'</span><span class="feather-icon-chevron-right"></span></div>');
                var $entries = $('<div class="group-entries"></div>');
                if(i===0 || i===1){
                    $header.addClass('is-active');
                    $entries.show();
                }
                $entries.append($group.children());
                $group.append($header).append($entries);
                var $entryItems = $entries.children();
                for(var j=0; j<$entryItems.length; j++){
                    var $entryItem = $($entryItems[j]);
                    $entryItem.append('<span class="entry-text">'+$entryItem.attr('title')+'</span>');
                }
            }
            setTimeout(function(){
                $palette.show();
            }, 100);
            if(typeof onReady === 'function'){
                onReady.apply(self, []);
            }
            if(isViewer === true){
                $('.bpmn-editor-wrapper input').attr('readonly', 'true');
                $('.bpmn-editor-wrapper textarea').attr('readonly', 'true');
            }

            if(isViewer===true && completeTaskIds && completeTaskIds.length>0){
                var connectionElements = [];
                var modeling = self.get('modeling');
                elementRegistry.forEach(function(element){
                    if(element.type === 'bpmn:SequenceFlow') connectionElements.push(element);
                    var finded = false;
                    for(var i=0; i<completeTaskIds.length; i++){
                        if(completeTaskIds[i] === element.id){
                            finded = true;
                            break;
                        }
                    }
                    if(finded){
                        modeling.setColor([element], {
                            stroke:'#67c23a',
                            fill:'#f0f9eb'
                        });
                    }
                });
                for(var i=0; i<completeTaskIds.length; i++){
                    var sourceRef = completeTaskIds[i];
                    for(var j=0; j<completeTaskIds.length; j++){
                        var targetRef = completeTaskIds[j];
                        if(sourceRef === targetRef) continue;
                        for(var k=0; k<connectionElements.length; k++){
                            var connectionElement = connectionElements[k];
                            if(sourceRef===connectionElement.source.id && targetRef===connectionElement.target.id){
                                modeling.setColor([connectionElement], {
                                    stroke:'#67c23a'
                                });
                            }
                        }
                    }
                }
            }
        });

        var eventBus = bpmnInstance.get('eventBus');
        eventBus.on('create.end', function(e){
            var source = e.context.shape;
            //异步节点
            if(source.businessObject.$attrs['extension:type'] === 'REMOTE_ASYNCHRONOUS'){
                var modeling = bpmnInstance.get('modeling');
                var autoPlace = bpmnInstance.get('autoPlace');
                var elementFactory = bpmnInstance.get('elementFactory');
                var target = elementFactory.createShape(assign({type:'bpmn:ReceiveTask'}, null));
                target.businessObject.name = source.businessObject.name + '回调';
                target.businessObject.$attrs['extension:removeable'] = source.businessObject.$attrs['extension:removeable'];
                target.businessObject.$attrs['extension:reference'] = source.businessObject.$attrs['extension:reference'];
                modeling.updateProperties(target, {
                    name:target.businessObject.name
                });
                autoPlace.append(source, target);
            }
        });

        eventBus.on('element.click', function(e){
            var element = e.element;
            var businessObject = element.businessObject;
            if(is(businessObject, 'bpmn:UserTask')){
                showUserTaskProps.apply($container[0], [element, queryUsers, queryRoles, userTaskBindVariables]);
            }else if(is(businessObject, 'bpmn:ServiceTask')){
                var serviceType = businessObject.$attrs['extension:type'];
                if(serviceType === 'REMOTE_SYNCHRONOUS'){
                    showServiceTaskSynchronousProps.apply($container[0], [element]);
                }else if(serviceType === 'REMOTE_ASYNCHRONOUS'){
                    showServiceTaskAsynchronousProps.apply($container[0], [element]);
                }else if(serviceType === 'INTERNAL'){
                    showServiceTaskInternalProps.apply($container[0], [element]);
                }
            }else if(is(businessObject, 'bpmn:ReceiveTask')){
                var elementRegistry = bpmnInstance.get('elementRegistry');
                var target = null;
                elementRegistry.forEach(function(scopeElement){
                    if(scopeElement.businessObject &&
                        !is(scopeElement.businessObject, 'bpmn:ReceiveTask') &&
                        scopeElement.businessObject.$attrs['extension:reference'] === businessObject.$attrs['extension:reference']){
                        target = scopeElement;
                        return false;
                    }
                });
                showServiceTaskAsynchronousProps.apply($container[0], [target]);
            }else if(is(businessObject, 'bpmn:SequenceFlow') &&
                is(businessObject.sourceRef, 'bpmn:ExclusiveGateway')){
                showSequenceFlowProps.apply($container[0], [element]);
            }else if(is(businessObject, 'bpmn:Process')){

            }else{
                hideProps.apply($container[0]);
            }
        });

        $container.on('click.save.xml', '.editor-save', function(){
            var $button = $(this);
            if($button.is('.disabled')) return;
            $button.removeClass('feather-icon-save').addClass('el-icon-loading').addClass('disabled');
            var endLoading = function(){
                $button.addClass('feather-icon-save').removeClass('el-icon-loading').removeClass('disabled');
            };
            self.export(function(xml){
                if(typeof onSave === 'function'){
                    onSave.apply(self, [xml, userTaskBindVariables, endLoading]);
                }
            });
        });

        $container.on('click.bpmnJS.ext.prop.panel.node.expression.add.variable', '.prop-expression-condition', function(){
            var $button = $(this);
            var $actions = $button.closest('.prop-expression-actions');
            var $form = $actions.siblings('.el-form');
            var $textarea = $form.find('textarea');
            $textarea.val($textarea.val() + $button.data('value'));
            $textarea.trigger('input.bpmnJS.ext.prop.panel.node.expression');
        });

        $container.on('input.bpmnJS.ext.prop.panel.node.expression', '.node-expression', function(){
            var $textarea = $(this);
            var nodeId = $textarea.closest('.el-form').find('.node-id').val();
            var elementRegistry = self.get('elementRegistry');
            var moddle = self.get('moddle');
            var modeling = self.get('modeling');
            var overlays = bpmnInstance.get('overlays');
            elementRegistry.forEach(function(element){
                if(element.businessObject && element.businessObject.id===nodeId){
                    var targetOverlays = null;
                    for(var i in overlays._overlays){
                        if(overlays._overlays[i].element &&
                            overlays._overlays[i].element.id === nodeId){
                            if($(overlays._overlays[i].html).is('.sequence-flow-expression-label')){
                                targetOverlays = overlays._overlays[i];
                            }
                            break;
                        }
                    }
                    if(targetOverlays){
                        targetOverlays.html.text($textarea.val());
                    }else{
                        overlays.add(element, {
                            position: {
                                top: 5,
                                left: 5
                            },
                            html: $('<div class="sequence-flow-expression-label">'+$textarea.val()+'</div>')
                        });
                    }
                    var newCondition = moddle.create('bpmn:FormalExpression', {
                        body: '<![CDATA[${'+ $textarea.val() +'}]]>'
                    });
                    element.businessObject.conditionExpression = newCondition;
                    modeling.updateProperties(element, {
                        conditionExpression: newCondition
                    });
                    return false;
                }
            });
        });

        $container.on('input.bpmnJS.ext.prop.panel.node.label', '.node-label', function(){
            var $label = $(this);
            var $id = $label.closest('.el-form').find('.node-id');
            var id = $id.val();
            var label = $label.val();
            var elementRegistry = self.get('elementRegistry');
            var modeling = self.get('modeling');
            var target = null;
            elementRegistry.forEach(function(scopeElement){
                if(scopeElement.businessObject && scopeElement.businessObject.id === id){
                    target = scopeElement;
                    return false;
                }
            });
            modeling.updateProperties(target, {
                name:label
            });
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.node.expression.insert.variable', '.prop-expression-insert-variable', function(){
            var $button = $(this);
            var $modal = $button.siblings('.v-modal');
            var $dialog = $button.siblings('.el-dialog__wrapper');
            $modal.addClass('v-modal-enter');
            $dialog.addClass('dialog-fade-enter-active');
            $modal.show();
            $dialog.show();
            setTimeout(function(){
                $modal.removeClass('v-modal-enter');
                $dialog.removeClass('dialog-fade-enter-active');
            }, 200);
            if(typeof queryVariables === 'function'){
                queryVariables(function(varialbles){
                    var trs = '';
                    if(varialbles && varialbles.length>0){
                        for(var i=0; i<varialbles.length; i++){
                            trs += '<tr data-value="'+varialbles[i].primaryKey+'"><td>'+varialbles[i].primaryKey+'</td><td>'+varialbles[i].name+'</td></tr>';
                        }
                    }
                    $dialog.find('.prop-expression-variable-table tbody').empty().append(trs);
                });
            }
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.node.expression.insert.variable.row', '.prop-expression-variable-table tbody tr', function(){
            var $tr = $(this);
            if($tr.is('.highlight')) return;
            var $table = $tr.closest('table');
            $table.find('tr.highlight').removeClass('highlight');
            $tr.addClass('highlight');
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.node.expression.insert.variable.submit', '.prop-expression-insert-variable-submit', function(){
            var $button = $(this);
            var $dialog = $button.closest('.el-dialog__wrapper');
            var $table = $dialog.find('table');
            var $trs = $table.find('tbody tr');
            var $close = $dialog.find('.prop-panel-dialog-close').first();
            var primaryKey = '';
            for(var i=0; i<$trs.length; i++){
                var $tr = $($trs[i]);
                if($tr.is('.highlight')){
                    primaryKey = '__variableContext__.'+$tr.data('value');
                    break;
                }
            }
            if(primaryKey){
                var $textarea = $button.closest('.prop-expression-actions').siblings('.el-form').find('textarea');
                $textarea.val($textarea.val() + primaryKey);
                $textarea.trigger('input.bpmnJS.ext.prop.panel.node.expression');
            }
            $close.trigger('click.bpmnJS.ext.prop.panel.dialog.close');
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.add.variable', '.pop-panel-variable-add', function(){
            var $button = $(this);
            var $modal = $button.siblings('.v-modal');
            var $dialog = $button.siblings('.el-dialog__wrapper');
            $modal.addClass('v-modal-enter');
            $dialog.addClass('dialog-fade-enter-active');
            $modal.show();
            $dialog.show();
            setTimeout(function(){
                $modal.removeClass('v-modal-enter');
                $dialog.removeClass('dialog-fade-enter-active');
            }, 200);
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.close', '.prop-panel-dialog-close', function(){
            var $button = $(this);
            var $dialog = $button.closest('.el-dialog__wrapper');
            var $modal = $dialog.siblings('.v-modal');
            $modal.addClass('v-modal-leave');
            $dialog.addClass('dialog-fade-leave-active');
            setTimeout(function(){
                $modal.hide().removeClass('v-modal-leave');
                $dialog.hide().removeClass('dialog-fade-leave-active');
            }, 200);
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.outer.click', '.el-dialog__wrapper', function(e){
           if(e.target.className === 'el-dialog__wrapper'){
               var $dialog = $(e.target);
               var $modal = $dialog.siblings('.v-modal');
               $modal.addClass('v-modal-leave');
               $dialog.addClass('dialog-fade-leave-active');
               setTimeout(function(){
                   $modal.hide().removeClass('v-modal-leave');
                   $dialog.hide().removeClass('dialog-fade-leave-active');
               }, 200);
           }
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.add.variable.submit', '.prop-panel-variable-submit', function(){
            var $button = $(this);
            if($button.is('.is-loading')) return;
            $button.addClass('is-loading').prepend('<i class="el-icon-loading"/>')
            var $dialog = $button.closest('.el-dialog__wrapper');
            var $input = $dialog.find('input');

            var endLoading = function(){
                $button.find('i').remove();
                $button.removeClass('is-loading');
            };

            var done = function(entity){
                var $modal = $dialog.siblings('.v-modal');
                $modal.addClass('v-modal-leave');
                $dialog.addClass('dialog-fade-leave-active');
                setTimeout(function(){
                    $modal.hide().removeClass('v-modal-leave');
                    $dialog.hide().removeClass('dialog-fade-leave-active');
                }, 200);
                var tpl = generateVariablesTemplate([entity]);
                $container.find('.prop-panel-content-var tbody').prepend(tpl);
                for(var i=0; i<$input.length; i++){
                    $input[i].value = '';
                }
            };

            var variable = {
                primaryKey:$input[0].value,
                name:$input[1].value,
                defaultValue:$input[2].value
            };
            if(typeof onSaveVariable === 'function') onSaveVariable(variable, done, endLoading);
        });

        $container.on('click.bpmnJS.ext.prop.panel.dialog.add.variable.delete', '.prop-panel-variable-delete', function(){
            var $button = $(this);
            var $tr = $button.closest('tr');
            var rowId = $tr.find('td').first().data('id');
            var done = function(){
                $tr.remove();
            };
            if(typeof onDeleteVariable === 'function') onDeleteVariable(rowId, done);
        });

        //当前选中用户节点id
        var getCurrentUserTaskElementId = function(){
            return $container.find('.prop-scope-user-task .node-id').val();
        };

        //当前选中用户节点
        var getCurrentUserTaskElement = function(){
            var nodeId = $container.find('.prop-scope-user-task .node-id').val();
            var elementRegistry = self.get('elementRegistry');
            var currentElement = null;
            elementRegistry.forEach(function(element){
                if(element.id === nodeId){
                    currentElement = element;
                    return false;
                }
            });
            return currentElement;
        };

        $container.on('click.bpmnJS.ext.prop.panel.user.add', '.prop-user-binding-add', function(){
            var element = getCurrentUserTaskElement();
            var bindUsers = element.businessObject.$attrs['activiti:candidateUsers'];
            onBindUserClick(function(users){
                var $userContainer = $container.find('.prop-user-binding tbody');
                if(users && users.length>0){
                    var userIds = [];
                    if(element.businessObject.$attrs['activiti:candidateUsers']){
                        userIds = element.businessObject.$attrs['activiti:candidateUsers'].split(',');
                    }
                    for(var i=0; i<users.length; i++){
                        var $user = $('<tr><td>'+users[i].nickname+'</td><td><button class="el-button el-button--text prop-user-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                        $user.data('bind', users[i]);
                        $userContainer.append($user);
                        userIds.push(users[i].id);
                    }
                    element.businessObject.$attrs['activiti:candidateUsers'] = userIds.join(',');
                }
            }, bindUsers?bindUsers.split(','):null);
        });

        $container.on('click.bpmnJS.ext.prop.panel.user.delete', '.prop-user-binding-delete', function(){
            var $button = $(this);
            var $user = $button.closest('tr');
            var user = $user.data('bind');
            var element = getCurrentUserTaskElement();
            var bindUsers = element.businessObject.$attrs['activiti:candidateUsers'].split(',');
            for(var i=0; i<bindUsers.length; i++){
                if(bindUsers[i] == user.id){
                    bindUsers.splice(i, 1);
                    break;
                }
            }
            if(bindUsers.length > 0){
                element.businessObject.$attrs['activiti:candidateUsers'] = bindUsers.join(',');
            }else{
                delete element.businessObject.$attrs['activiti:candidateUsers'];
            }
            $user.remove();
        });

        $container.on('click.bpmnJS.ext.prop.panel.role.add', '.prop-role-binding-add', function(){
            var element = getCurrentUserTaskElement();
            var bindRoles = element.businessObject.$attrs['activiti:candidateGroups'];
            onBindRoleClick(function(roles){
                var $roleContainer = $container.find('.prop-role-binding tbody');
                if(roles && roles.length){
                    var roleIds = [];
                    if(element.businessObject.$attrs['activiti:candidateGroups']){
                        roleIds = element.businessObject.$attrs['activiti:candidateGroups'].split(',');
                    }
                    for(var i=0; i<roles.length; i++){
                        var $role = $('<tr><td>'+roles[i].name+'</td><td><button class="el-button el-button--text prop-role-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                        $role.data('bind', roles[i]);
                        $roleContainer.append($role);
                        roleIds.push(roles[i].id);
                    }
                    element.businessObject.$attrs['activiti:candidateGroups'] = roleIds.join(',');
                }

            }, bindRoles?bindRoles.split(','):null);
        });

        $container.on('click.bpmnJS.ext.prop.panel.role.delete', '.prop-role-binding-delete', function(){
            var $button = $(this);
            var $role = $button.closest('tr');
            var role = $role.data('bind');
            var element = getCurrentUserTaskElement();
            var bindRoles = element.businessObject.$attrs['activiti:candidateGroups'].split(',');
            for(var i=0; i<bindRoles.length; i++){
                if(bindRoles[i] == role.id){
                    bindRoles.splice(i, 1);
                    break;
                }
            }
            if(bindRoles.length > 0){
                element.businessObject.$attrs['activiti:candidateGroups'] = bindRoles.join(',');
            }else{
                delete element.businessObject.$attrs['activiti:candidateGroups'];
            }
            $role.remove();
        });

        $container.on('click.bpmnJS.ext.prop.panel.variable.show.add', '.prop-variable-show-binding-add', function(){
            var nodeId = getCurrentUserTaskElementId();
            var showVariables = userTaskBindVariables.show;
            var exceptVariableIds = [];
            if(showVariables && showVariables.length>0){
                for(var i=0; i<showVariables.length; i++){
                    if(showVariables[i].taskId == nodeId){
                        exceptVariableIds.push(showVariables[i].id);
                    }
                }
            }
            onBindVariableClick(function(variables){
                var $variablesShowContainer = $container.find('.prop-variable-show-binding tbody');
                userTaskBindVariables.show = userTaskBindVariables.show||[];
                for(var i=0; i<variables.length; i++){
                    var variableShow = {
                        taskId:nodeId,
                        id:variables[i].id,
                        key:variables[i].primaryKey,
                        name:variables[i].name
                    };
                    userTaskBindVariables.show.push(variableShow);
                    var $variable =$('<tr><td>'+variableShow.name+'</td><td></td><td><button class="el-button el-button--text prop-variable-show-binding-edit"><span class="el-icon-edit"></span></button><button class="el-button el-button--text prop-variable-show-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                    $variable.data('bind', variableShow);
                    $variablesShowContainer.append($variable);
                }
            }, exceptVariableIds);
        });

        $container.on('click.bpmnJS.ext.prop.panel.variable.show.edit', '.prop-variable-show-binding-edit', function(){
            var $button = $(this);
            var $variable = $button.closest('tr');
            var variableShow = $variable.data('bind');
            onEditVariableClick(function(variable){
                $($variable.find('td')[1]).text(variable.typeName);
                $variable.data('bind', variable);
            }, variableShow);
        });

        $container.on('click.bpmnJS.ext.prop.panel.variable.show.delete', '.prop-variable-show-binding-delete', function(){
            var $button = $(this);
            var $variable = $button.closest('tr');
            var variableShow = $variable.data('bind');
            var showVariables = userTaskBindVariables.show;
            for(var i=0; i<showVariables.length; i++){
                if(showVariables[i].taskId==variableShow.taskId && showVariables[i].id==variableShow.id){
                    showVariables.splice(i, 1);
                    break;
                }
            }
            $variable.remove();
        });

        $container.on('click.bpmnJS.ext.prop.panel.variable.set.add', '.prop-variable-set-binding-add', function(){
            var nodeId = getCurrentUserTaskElementId();
            var setVariables = userTaskBindVariables.set;
            var exceptVariableIds = [];
            if(setVariables && setVariables.length>0){
                for(var i=0; i<setVariables.length; i++){
                    if(setVariables[i].taskId == nodeId){
                        exceptVariableIds.push(setVariables[i].id);
                    }
                }
            }
            onBindVariableClick(function(variables){
                var $variablesSetContainer = $container.find('.prop-variable-set-binding tbody');
                userTaskBindVariables.set = userTaskBindVariables.set||[];
                for(var i=0; i<variables.length; i++){
                    var variableSet = {
                        taskId:nodeId,
                        id:variables[i].id,
                        key:variables[i].primaryKey,
                        name:variables[i].name
                    };
                    userTaskBindVariables.set.push(variableSet);
                    var $variable =$('<tr><td>'+variableSet.name+'</td><td></td><td><button class="el-button el-button--text prop-variable-show-binding-edit"><span class="el-icon-edit"></span></button><button class="el-button el-button--text prop-variable-show-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                    $variable.data('bind', variableSet);
                    $variablesSetContainer.append($variable);
                }
            }, exceptVariableIds);
        });

        $container.on('click.bpmnJS.ext.prop.panel.variable.set.edit', '.prop-variable-set-binding-edit', function(){
            var $button = $(this);
            var $variable = $button.closest('tr');
            var variableShow = $variable.data('bind');
            onEditVariableClick(function(variable){
                $($variable.find('td')[1]).text(variable.typeName);
                $variable.data('bind', variable);
            }, variableShow);
        });

        $container.on('click.bpmnJS.ext.prop.panel.variable.set.delete', '.prop-variable-set-binding-delete', function(){
            var $button = $(this);
            var $variable = $button.closest('tr');
            var variableSet = $variable.data('bind');
            var setVariables = userTaskBindVariables.set;
            for(var i=0; i<setVariables.length; i++){
                if(setVariables[i].taskId==variableSet.taskId && setVariables[i].id==variableSet.id){
                    setVariables.splice(i, 1);
                    break;
                }
            }
            $variable.remove();
        });

    }

    /**
     * 获取内部模块
     * @param name 模块名称
     */
    BpmnJSExtension.prototype.get = function(name){
        return this.nativeInstance.get(name);
    };

    /**
     * 导出xml
     * @param fn(xml) 回调
     */
    BpmnJSExtension.prototype.export = function(fn){
        var self = this;

        //修改流程id为uuid
        var uuid = self._uuid;
        if(uuid){
            var processId = self._processId;
            var elementRegistry = self.get('elementRegistry');
            var modeling = self.get('modeling');
            elementRegistry.forEach(function(element){
                if(element.businessObject &&
                    is(element.businessObject, 'bpmn:Process') &&
                    element.businessObject.id==processId){
                    element.businessObject.id = uuid;
                    return false;
                }
            });
        }

        self.nativeInstance.saveXML({format: true, preamble:false }, function(err, xml) {
            if (err) {
                return console.error('could not save BPMN 2.0 diagram', err);
            }
            if(typeof fn === 'function'){

                var reg_lt = new RegExp('&lt;','g');
                var reg_gt = new RegExp('&gt;', 'g');
                var reg_and = new RegExp('&amp;', 'g');

                var replacedXml = '';

                //处理分支条件转义字符
                while(true){
                    var matched = xml.match(/(?=<conditionExpression xsi:type="tFormalExpression">)/);
                    if(!matched) {
                        replacedXml += xml;
                        break;
                    }
                    replacedXml += xml.substring(0, matched.index);
                    var placeholder = '';
                    var endIndex = 0;
                    for(var i=matched.index; i<xml.length; i++){
                        placeholder += xml[i];
                        if(endWith(placeholder, '</conditionExpression>')){
                            endIndex = i;
                            break;
                        }
                    }
                    var replaced = placeholder.replace(reg_lt, '<')
                                              .replace(reg_gt, '>')
                                              .replace(reg_and, '&');
                    replacedXml += replaced;
                    xml = xml.substring(endIndex+1, xml.length);
                }

                fn.apply(self, [replacedXml]);
            }
        });
    };

    /**
     * 查询服务任务节点业务id
     * @returns {Array} 业务id
     */
    BpmnJSExtension.prototype.queryServiceReferenceIds = function(){
        var elementRegistry = this.get('elementRegistry');
        var referenceIds = [];
        elementRegistry.forEach(function(element){
            if(is(element.businessObject, 'bpmn:ServiceTask')){
                referenceIds.push(element.businessObject.$attrs['extension:reference'].split('-')[1]);
            }
        });
        return referenceIds;
    };

    /**************************
     *    自定义palette事件
     **************************/

    //手风琴效果
    $(document).on('click.bpmnJS.ext.entry.header', '.djs-palette .group-header, .djs-popup .group-header', function(){
        var $header = $(this);
        var $palette = $header.closest('.djs-palette');
        var $entries = $header.next();
        if(!$header.is('.is-active')){
            $header.addClass('is-active');
            $entries.slideDown(300);
        }else{
            $header.removeClass('is-active');
            $entries.slideUp(300);
        }
    });

    /***************************
     *    属性面板
     ***************************/

    //标签页
    $(document).on('click.bpmnJS.ext.prop.panel.tab', '.prop-panel-tab', function(){
        var $tab = $(this);
        var $tabs = $tab.parent();
        var $contents = $tabs.siblings('.prop-panel-contents');
        if($tab.is('.is-active')) return;
        var $oldActive = $tabs.find('.is-active');
        $contents.find('.prop-panel-content[data-id='+$oldActive.data('ref')+']').hide();
        $oldActive.removeClass('is-active');
        $tab.addClass('is-active');
        $contents.find('[data-id='+$tab.data('ref')+']').show();
    });

    var showSequenceFlowProps = function(element){
        var $container = $(this);
        var businessObject = element.businessObject;
        var $sequenceFlow = $container.find('.prop-scope-sequence-flow');
        var $nodeId = $sequenceFlow.find('.node-id');
        var $expression = $sequenceFlow.find('.node-expression');
        $nodeId.val(businessObject.id);
        if(businessObject.conditionExpression && businessObject.conditionExpression.body){
            $expression.val(businessObject.conditionExpression.body.substring(11, businessObject.conditionExpression.body.length-4));
        }else{
            $expression.val('');
        }
        hideProps.apply($container[0]);
        $sequenceFlow.show();
    };

    var showUserTaskProps = function(element, queryUsers, queryRoles, userTaskBindVariables){
        var $container = $(this);
        var businessObject = element.businessObject;
        var candidateUsers = businessObject.$attrs['activiti:candidateUsers'];
        var $userContainer = $container.find('.prop-user-binding tbody');
        $userContainer.empty();
        if(candidateUsers){
            candidateUsers = candidateUsers.split(',');
            queryUsers(candidateUsers, function(users){
                if(users && users.length>0){
                    for(var i=0; i<users.length; i++){
                        var $user = $('<tr><td>'+users[i].nickname+'</td><td><button class="el-button el-button--text prop-user-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                        $user.data('bind', users[i]);
                        $userContainer.append($user);
                    }
                }
            });
        }
        var candidateGroups = businessObject.$attrs['activiti:candidateGroups'];
        var $roleContainer = $container.find('.prop-role-binding tbody');
        $roleContainer.empty();
        if(candidateGroups){
            candidateGroups = candidateGroups.split(',');
            queryRoles(candidateGroups, function(roles){
                if(roles && roles.length){
                    for(var i=0; i<roles.length; i++){
                        var $role = $('<tr><td>'+roles[i].name+'</td><td><button class="el-button el-button--text prop-role-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                        $role.data('bind', roles[i]);
                        $roleContainer.append($role);
                    }
                }
            });
        }

        var variablesShow = userTaskBindVariables.show;
        var $variablesShowContainer = $container.find('.prop-variable-show-binding tbody');
        $variablesShowContainer.empty();
        if(variablesShow && variablesShow.length>0){
            for(var i=0; i<variablesShow.length; i++){
                if(variablesShow[i].taskId != element.id) continue;
                var $variable = $('<tr><td>'+variablesShow[i].name+'</td><td>'+(variablesShow[i].typeName||'')+'</td><td><button class="el-button el-button--text prop-variable-show-binding-edit"><span class="el-icon-edit"></span></button><button class="el-button el-button--text prop-variable-show-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                $variable.data('bind', variablesShow[i]);
                $variablesShowContainer.append($variable);
            }
        }

        var variablesSet = userTaskBindVariables.set;
        var $variablesSetContainer = $container.find('.prop-variable-set-binding tbody');
        $variablesSetContainer.empty();
        if(variablesSet && variablesSet.length>0){
            for(var i=0; i<variablesSet.length; i++){
                if(variablesSet[i].taskId != element.id) continue;
                var $variable = $('<tr><td>'+variablesSet[i].name+'</td><td>'+(variablesSet[i].typeName||'')+'</td><td><button class="el-button el-button--text prop-variable-set-binding-edit"><span class="el-icon-edit"></span></button><button class="el-button el-button--text prop-variable-set-binding-delete"><span class="el-icon-delete"></span></button></td></tr>');
                $variable.data('bind', variablesSet[i]);
                $variablesSetContainer.append($variable);
            }$variable
        }

        var $userTask = $container.find('.prop-scope-user-task');
        var $nodeId = $userTask.find('.node-id');
        var $nodeLabel = $userTask.find('.node-label');
        $nodeId.val(businessObject.id);
        $nodeLabel.val(businessObject.name);
        hideProps.apply($container[0]);
        $userTask.show();
    };

    var showServiceTaskSynchronousProps = function(element){
        var $container = $(this);
        var businessObject = element.businessObject;
        var $serviceTask = $container.find('.prop-scope-service-task-synchronous');
        var $nodeId = $serviceTask.find('.node-id');
        var $nodeLabel = $serviceTask.find('.node-label');
        $nodeId.val(businessObject.id);
        $nodeLabel.val(businessObject.name);
        hideProps.apply($container[0]);
        $serviceTask.show();
    };

    var showServiceTaskAsynchronousProps = function(element){
        var $container = $(this);
        var businessObject = element.businessObject;
        var $serviceTask = $container.find('.prop-scope-service-task-asynchronous');
        var $nodeId = $serviceTask.find('.node-id');
        var $nodeLabel = $serviceTask.find('.node-label');
        $nodeId.val(businessObject.id);
        $nodeLabel.val(businessObject.name);
        hideProps.apply($container[0]);
        $serviceTask.show();
    };

    var showServiceTaskInternalProps = function(element){
        var $container = $(this);
        var businessObject = element.businessObject;
        var $serviceTask = $container.find('.prop-scope-service-task-internal');
        var $nodeId = $serviceTask.find('.node-id');
        var $nodeLabel = $serviceTask.find('.node-label');
        $nodeId.val(businessObject.id);
        $nodeLabel.val(businessObject.name);
        hideProps.apply($container[0]);
        $serviceTask.show();
    };

    var hideProps = function(){
        var $container = $(this);
        $container.find('.prop-scope').hide();
    };

    var generateVariablesTemplate = function(variables){
        variables = variables || [];
        var tpl = '';
        for(var i=0; i<variables.length; i++){
            var tr = '<tr>';
            var variable = variables[i];
            tr += '<td data-id="'+variable.id+'" title="'+variable.primaryKey+'">'+variable.name+'</td>';
            //tr += '<td><button class="el-button el-button--text prop-panel-variable-delete"><span class="el-icon-delete"></span></button></td>';
            tr += '</tr>';
            tpl += tr;
        }
        return tpl;
    };

    return BpmnJSExtension;

});