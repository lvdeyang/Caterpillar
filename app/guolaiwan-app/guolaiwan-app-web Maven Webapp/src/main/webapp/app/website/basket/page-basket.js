/**
 * Created by lvdeyang on 2017/7/03.
 */
define([

    'text!html-basket',
    'text!html-order',
    'juicer',
    'context',
    'page',
    'commons',
    'travel',
    'jquery'

], function(basket, order, juicer, context, page, commons, travel, $){

    var PAGENAME = 'basket';

    var _init = function(){

        if(!context.model || !context.model.user || !context.model.user.id){
            context.load('error', {
                status:403,
                message:'亲，您还没有登录呐！'
            });
            return;
        }

        var prepare_basket = juicer(basket);

        var prepare_order = juicer(order);

        var $content = $('<div style="width:100%; min-height:874px;"></div>');
        context.setContent($content);

        var $basketWrapper = $('<div style="width:100%; height:100%;"></div>');
        var $orderWrapper = $('<div style="width:100%; height:100%;"></div>');
        $content.append($basketWrapper).append($orderWrapper);

        var $order = null;

        var _uri = 'website/basket/get/' + context.model.user.id;

        commons.ajax.get(_uri, null, function(data){

            var $basket = $(prepare_basket.render({baskets:data.baskets}));

            $basketWrapper.append($basket);

            commons.checkbox.init({
                selector:'[name=basket-item]',
                context:$basket,
                doCheckall:true
            });

            var $checkboxes = $basket.find('[name=basket-item]');

            var _total_price = function(){
                var _total = 0;

                var _checked = [];

                $checkboxes = $basket.find('[name=basket-item]');
                if($checkboxes.length===1 && $checkboxes.is(':checked')){
                    $checkboxes.iCheck('uncheck');
                }else{
                    $checkboxes.each(function(){
                        var $checkbox = $(this);
                        if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                            _checked.push(parseInt($checkbox.val()));
                        }
                    });

                    for(var i= 0; i<_checked.length; i++){
                        for(var j= 0; j<data.baskets.length; j++){
                            if(data.baskets[j].id === _checked[i]){
                                _total += data.baskets[j].count;
                                break;
                            }
                        }
                    }
                }

                $('#price-total').text(_total);
                return _total;
            }

            //计算总价
            _total_price();

            $checkboxes.on('ifChecked', function(){
                _total_price();
            });

            $checkboxes.on('ifUnchecked', function(){
                _total_price();
            });

            //重置价钱
            var reset_count = function(num, complete){
                var $spinner = $(this);
                var $tr = $spinner.closest('tr');
                var $checkbox = $tr.find('[name=basket-item]');
                var $price = $tr.find('.price');
                var $count = $tr.find('.count');

                var _basketId = $checkbox.val();

                var _uri = 'website/basket/change/num/' + context.model.user.id + '/' + _basketId;

                commons.ajax.put(_uri, {

                    num:num

                }, function(){

                    $count.text(parseInt($price.data('price'))*num);

                    for(var i= 0, len=data.baskets.length-1; i<=len; i++){
                        if(data.baskets[i].id === parseInt(_basketId)){
                            data.baskets[i].num = num;
                            data.baskets[i].count = data.baskets[i].num * data.baskets[i].price;
                            break;
                        }
                    }

                    complete();

                    //计算总价
                    _total_price();

                });
            }

            commons.spinner.init({
                selector:'.input-spinner',
                context:$basket,
                onPlus:reset_count,
                onMinus:reset_count
            });

            travel.initBody();

            //删除购物车条目
            $basket.on('click', '.button-remove', function(){

                var $button = $(this);
                var $tr = $button.closest('tr');
                var $introduction = $tr.find('.introduction');

                var $modal = commons.modal.createAndShow({
                    type:'confirm',
                    title:'',
                    height:'auto',
                    close:'关闭',
                    save:'确定'
                });

                var $content = commons.modal.getContent($modal);

                $content.text('您确定要将“'+$introduction.text()+'”从购物车中移除吗？');

                $modal.on('click.save', '.btn-save', function(){

                    commons.modal.close($modal);

                    var $checkbox = $tr.find('[name=basket-item]');
                    var _basketId = $checkbox.val();

                    var _uri = 'website/basket/delete/' + context.model.user.id + '/' + _basketId;

                    commons.ajax['delete'](_uri, null, function(data){
                        $tr.remove();
                        _total_price();
                    });
                });

            });

            $('#basket-pay-now').on('click', function(){

                var _checked = [];

                var _$trs = []

                $checkboxes = $basket.find('[name=basket-item]');

                $checkboxes.each(function(){
                    var $checkbox = $(this);
                    if(!$checkbox.is('.checkall') && $checkbox.is(':checked')){
                        _checked.push(parseInt($checkbox.val()));
                        _$trs.push($checkbox.closest('tr'));
                    }
                });

                if(_checked.length <= 0) return;

                var _uri = 'website/order/book/' + context.model.user.id;

                commons.ajax.post(_uri, $.toJSON({

                    basketIds:_checked

                }), function(data){

                    var $tbody = $order.find('tbody');
                    $tbody.prepend($(prepare_order.render({orders:data.orders})).find('tbody>tr'));

                    for(var i=0,len=_$trs.length-1; i<=len; i++){
                        _$trs[i].remove();
                    }

                    _total_price();

                });

            });

        });

        _uri = 'website/order/get/' + context.model.user.id;

        commons.ajax.get(_uri, null, function(data){

            $order = $(prepare_order.render({orders:data.orders}));

            $orderWrapper.append($order);

            travel.initBody();
        });



    }

    var _page = page.create({
        id:PAGENAME,
        init:_init
    });

    context.register(_page);

});
