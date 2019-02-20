$(function(){
	
	//导航回退按钮事件
	$(document).on('click.head.goBack', '#goBack', function(){
		history.back();
	});
	
	//页尾导航按钮点击按钮
	$(document).on('click.foot.btn', '#p-footer li', function(){
		var $li = $(this),
		    href = $li.data('href');
		window.location.href = href;
	});
	
	//验证码图片更换
	$(document).on('click.verify', '.p-verify', function(){
		var $img = $(this),
			basePath = window.basePath;
		$img.attr('src', basePath + 'verify/code/get/110/43?' + new Date().getTime());
	});
	
	//输入框清空按钮
	$(document).on('click.form.clear', '.ui-icon-close', function(){
		var $clear = $(this),
			$input = $clear.siblings('input');
		$input.val('');
	});
	
	//表单验证zValidate
	
	var validate = $('.p-form').zValidate({
        errorClass:"p-error",
        errorElement:"div",
        errorPlacement:function($error, $element){
            $element.closest('.ui-form-item').after($error.prepend('<i></i>').addClass('ui-tips ui-tips-warn ui-border-b').css('text-align', 'left'));
        },
        highlight:function($element) {
            $element.closest('.ui-form-item').addClass(this.options.errorClass);
        },
        unhighlight:function($element) {
            $element.closest('.ui-form-item').removeClass(this.options.errorClass);
        },
        submit:function($form, params) {
            $form[0].submit();
        }
    });
	
	window.validate = validate;
	
	//车牌号验证
	//1.常规车牌号：仅允许以汉字开头，后面可录入六个字符，由大写英文字母和阿拉伯数字组成。如：粤B12345；
	//2.最后一个为汉字的车牌：允许以汉字开头，后面可录入六个字符，前五位字符，由大写英文字母和阿拉伯数字组成，而最后一个字符为汉字，汉字包括“挂”、“学”、“警”、“港”、“澳”。如：粤Z1234港。
	//3.新军车牌：以两位为大写英文字母开头，后面以5位阿拉伯数字组成。如：BA12345。
	validate.addMethod('vehicleNumber', function(value, $element, param){
		var number = value;
		if(param){
			var $param = $(param);
			number = number + $param.val();
		}
		var result = false;
        if (number.length == 7){
            var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
            result = express.test(number);
        }
        return result;
	});
	
	//手机号码
	validate.addMethod('mobileNumber', function(value, $element, param){
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(!myreg.test(value)){ 
		    return false; 
		} 
		return true;
	});
	
});