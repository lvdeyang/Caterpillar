+function(){

	//支持动态传参
	var name, dependences, definition;
	if(arguments.length >= 3){
		name = arguments[0];
		dependences = arguments[1];
		definition = arguments[2];
	}else if(arguments.length === 2){
		dependences = arguments[0];
		definition = arguments[1];
	}else if(arguments.length === 1){
		definition = arguments[0];
	}else if(arguments.length < 1){
		return;
	}

	dependences = dependences || [];

	if(typeof define === 'function'){
		//amd 或 cmd 环境
		define(dependences, definition);
	}else{

		//手动加入依赖
		dependences = [window.jQuery];

		var exports = definition.apply(window, dependences);

		//直接扩展window对象
		if(exports && typeof exports==='object'){
			window[name] = exports;
		}
	}

}([
	'jquery'
], function($){

	//验证码图片更换
	$(document).on('click.verify', '.commons-verify', function(){
		var $img = $(this),
			basePath = window.BASEPATH;
		$img.attr('src', basePath + 'verify/code/get/109/32?' + new Date().getTime());
	});

});