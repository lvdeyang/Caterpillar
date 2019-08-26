define(function(){

    var cache = {};

    var context = {
        setProp:function(key, value){
            if(!cache[key]){
                cache[key] = value;
            }else{
                console.log('重复的key！');
            }
            return this;
        },
        getProp:function(key){
            return cache[key];
        },
        //格式化取值
        formatValue:function(key, path){
            var obj = cache[key];
            if(!obj){
                console.log('未获取到值，context：' + key);
                return '';
            }
            if(!path) return obj;
            path = path.split('.');
            var value = obj;
            for(var i=0; i<path.length; i++){
                var prop = value[path[i]];
                if(!prop){
                    console.log('未获取到值，context：' + key + ', prop:'+path[i]);
                    return '';
                }
                value = prop;
            }
            return value;
        },
        clearProp:function(key){
        	if(cache[key]){
        		cache[key] = null;
        	}
        }
    };

    return context;
});