define([
    'text!' + window.APPPATH + 'component/bvc2-dialog-edit-role/bvc2-dialog-edit-role.html',
    'vue',
    'element-ui'
], function(tpl, Vue){

    //组件名称
    var bvc2DialogEditRole = 'bvc2-dialog-edit-role';

    Vue.component(bvc2DialogEditRole, {
        props:['roles', 'members'],
        template:tpl,
        data:function(){
            return {
                roleData:this.roles,
                deviceData:[],
                value:[],
                chosenRow:'',
                defaultProps: {
                    key: 'id',
                    label: 'bundleName'
                },
                roleVisible:false
            }
        },
        methods:{
            saveRole:function(){
                var role_instance = this,
                    _members = role_instance.members;

                role_instance.$emit('save-role', _members);
            },
            clickRole:function(row, event, column){
                var role_instance = this,
                    _value = role_instance.value,
                    _deviceData = role_instance.deviceData,
                    _members = role_instance.members,
                    _checked = row.checked;

                role_instance.chosenRow = row;

                _value.splice(0, _value.length);
                _deviceData.splice(0, _deviceData.length);

                for(var j=0;j<_members.length;j++){
                    _deviceData.push(_members[j]);
                }

                for(var i=0;i<_checked.length;i++){
                    _value.push(_checked[i]);
                }
            },
            //newValue:穿梭框右侧变化后的key数组; direction:left/right; changeValue:穿梭框右侧变化的key数组
            changeMember:function(newValue, direction, changeValue){
                var role_instance = this,
                    _members = role_instance.members,
                    row = role_instance.chosenRow,
                    _checked = row.checked;

                _checked.splice(0, _checked.length);

                for(var i=0; i<newValue.length;i++){
                    _checked.push(newValue[i]);
                }

                if(direction == 'right'){
                    for(var j=0;j<changeValue.length;j++){
                        for(var k=0;k<_members.length;k++){
                            if(changeValue[j] == _members[k].id){
                                _members[k].roleId = row.id;
                                _members[k].roleName = row.name;
                            }
                        }
                    }
                }else if(direction == 'left'){
                    for(var m=0;m<changeValue.length;m++){
                        for(var n=0;n<_members.length;n++){
                            if(changeValue[m] == _members[n].id){
                                _members[n].roleId = null;
                                _members[n].roleName = null;
                            }
                        }
                    }
                }
            }
        }
    });

});
