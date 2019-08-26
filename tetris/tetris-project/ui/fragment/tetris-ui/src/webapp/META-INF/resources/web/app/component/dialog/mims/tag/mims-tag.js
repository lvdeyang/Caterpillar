/**
 * Created by sms on 2019/7/25.
 */
define([
    'text!' + window.APPPATH + 'component/dialog/mims/tag/mims-tag.html',
    'restfull',
    'jquery',
    'vue',
    'element-ui',
    'css!' + window.APPPATH + 'component/dialog/mims/tag/mims-tag.css'
], function (tpl, ajax, $, Vue) {
    var pluginName = 'mi-tag-dialog';

    var ON_TAG_DIALOG_CLOSE = 'on-tag-dialog-close';

    Vue.component(pluginName, {
        template: tpl,
        data: function () {
            return {
                visible: false,
                loading: false,
                props: {
                    label: 'name',
                    children: 'subColumns',
                    disabled: 'disabled'
                },
                data: [],
                uri: '',
                checked: [],
                selected: [],
                checkStrictly: true
            }
        },
        methods: {
            open: function (uri, checked) {
                var self = this;
                self.visible = true;
                self.uri = uri;
                self.checked = checked;
                self.load();
            },
            load: function () {
                var self = this;
                var uri = self.uri;
                self.data.splice(0, self.data.length);
                ajax.post(uri, null, function (data) {
                    if (data && data.length > 0) {
                        self.data = data.concat();
                    }

                    self.$nextTick(function () {
                        self.checkStrictly = false;
                    })
                })
            },
            handleTagClose: function () {
                var self = this;
                self.visible = false;
                self.loading = false;
                self.data.splice(0, self.data.length);
                self.checked = [];
                self.selected.splice(0, self.selected);
                self.uri = '';
            },
            handleTagBindingOk: function () {
                var self = this;
                var checked = self.checked;
                var selected = [];
                var checkedNodes = self.$refs.tagTree.getCheckedNodes(true, false);

                if(checkedNodes && checkedNodes.length > 0){
                    for(var i=0; i<checkedNodes.length; i++){
                        selected.push(checkedNodes[i]);
                    }
                }
                if (!selected || selected.length <= 0) {
                    self.$message({
                        message: '您没有选择任何数据！',
                        type: 'warning'
                    });
                    return;
                }

                var startLoading = function() {
                    self.loading = true;
                };
                var endLoading = function(){
                    self.loading = false;
                };
                var close = function(){
                    self.$nextTick(function(){
                        self.checkStrictly = true;
                    });
                    self.visible = false;
                };

                self.$emit(ON_TAG_DIALOG_CLOSE, checked, selected, startLoading, endLoading, close);
            }
        },
        mounted: function () {

        }
    })
});