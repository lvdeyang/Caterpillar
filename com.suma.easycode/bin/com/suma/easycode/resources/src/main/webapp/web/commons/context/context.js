define(function(){

    var cache = {};

    var context = {
        setProp:function(key, value){
            if(!cache[key]){
                cache[key] = value;
            }else{
                console.log('ÖØ¸´µÄkey£¡');
            }
            return this;
        },
        getProp:function(key){
            return cache[key];
        }
    };

    return context;
});



