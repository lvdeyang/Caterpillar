require.config({
    baseUrl: window.BASEPATH,
    paths:{
        'text':window.LIBPATH + 'frame/requireJS/plugins/text',
        'css':window.LIBPATH + 'frame/requireJS/plugins/css',
        'vue':window.LIBPATH + 'frame/vue/vue-2.5.16',
        'vue-router':window.LIBPATH + 'frame/vue/vue-router-3.0.1',
        'element-ui':window.LIBPATH + 'ui/element-ui/element-ui-2.4.3.min',
        'jquery':window.LIBPATH + 'frame/jQuery/jquery-2.2.3.min',
        'json':window.LIBPATH + 'frame/jQuery/jquery.json',
        'tetris-bpmn':window.COMMONSPATH +'bpmn/ext/BpmnExtension'
    },
    shim:{
        'jquery':{
            exports:'jQuery'
        },
        'json':{
            deps:['jquery'],
            exports:'jQuery'
        }
    }
});

require([
    'tetris-bpmn',
    'jquery',
    'json'
], function(TetrisBpmnJS, $){

    $.get('demo.bpmn', null, function(xml){
        window.tetrisBpmnInstance = new TetrisBpmnJS({
            container: '#demo01',
            nodeType:['user', 'service', 'gateWay'],
            entries:[{
                key:'approvalService.managerApproval',
                name:'经理通过任务',
                expression:'#{approvalService.managerApproval(execution, time)}',
                icon:''
            },{
                key:'approvalService.ceoApproval',
                name:'CEO通过任务',
                expression:'#{approvalService.ceoApproval(execution, time)}',
                icon:''
            }],
            xml:xml,
            onReady:function(){
                console.log('渲染成功！');
            }
        });
    });

    /*function FlowNode(){}
    FlowNode.prototype.getEntries = function(){
        return [{
             key:'approvalService.managerApproval',
             name:'经理通过任务',
             expression:'#{approvalService.managerApproval(execution, time)}',
             icon:''
        },{
             key:'approvalService.ceoApproval',
             name:'CEO通过任务',
             expression:'#{approvalService.ceoApproval(execution, time)}',
             icon:''
        }];
    };

    FlowNode.prototype.getModule = function(){
        return {
            __init__: [ 'flowNode' ],
            flowNode:['type', FlowNode]
        };
    };

    var bpmnInstance = window.bpmnInstance = new BpmnJS({
        container: '#demo01',
        keyboard: {
            bindTo: window
        },
        additionalModules: [
            BpmnExt.ContextPadProvider,
            BpmnExt.PaletteProvider,
            BpmnExt.Translate,
            FlowNode.prototype.getModule()
        ],
        moddleExtensions: {
            activiti: BpmnExt.metaModel
        },
        textRenderer: BpmnExt.textRenderer
    });

    var modeling = bpmnInstance.get('modeling');
    var eventBus = bpmnInstance.get('eventBus');
    eventBus.on('create.end', function(e){
       console.log(e);
    });

    function exportDiagram() {
        bpmnInstance.saveXML({format: true, preamble:false }, function(err, xml) {
            if (err) {
                return console.error('could not save BPMN 2.0 diagram', err);
            }
            console.log('DIAGRAM', xml);
        });
    }*/

    /*bpmnInstance.createDiagram(function(){

        var canvas = bpmnInstance.get('canvas'),
            overlays = bpmnInstance.get('overlays'),
            elementRegistry = bpmnInstance.get('elementRegistry'),
            modeling = bpmnInstance.get('modeling');

        // zoom to fit full viewport
        canvas.zoom('fit-viewport');

        // Option 1: Color via Overlay
        /!*var shape = elementRegistry.get('start');

        var $overlayHtml = $('<div class="highlight-overlay">')
            .css({
                width: shape.width + 4,
                height: shape.height + 4
            });

        overlays.add('start', {
            position: {
                top: -2,
                left: -2
            },
            html: $overlayHtml
        });*!/

        // attach an overlay to a node
        /!*overlays.add('start', 'note', {
            position: {
                bottom: 0,
                right: 0
            },
            html: '<div class="diagram-note">Mixed up the labels?</div>'
        });*!/

    });*/

   /* $.get(window.BASEPATH + '/web/test/demo/empty.bpmn', null, function(xml){
        bpmnInstance.importXML(xml, function(){

        });
    });*/

    /*var moddle = bpmnJS.get('moddle');

    // create a BPMN element that can be serialized to XML during export
    var newCondition = moddle.create('bpmn:FormalExpression', {
        body: '${ value > 100 }'
    });

    // write property, no undo support
    sequenceFlow.conditionExpression = newCondition;

    var modeling = bpmnJS.get('modeling');

    modeling.updateProperties(sequenceFlowElement, {
        conditionExpression: newCondition
    });*/

    $('#save-button').click(function(){
        tetrisBpmnInstance.export(function(xml){
            console.log(xml);
        });
    });

});
