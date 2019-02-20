/**
 * Created by lvdeyang on 2016/1/14.
 * 日期扩展
 */

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

        var exports = definition.apply(window);

        //直接扩展window对象
        if(exports && typeof exports==='object'){
            window[name] = exports;
        }
    }

}(function(){
    Date.prototype.S = 's';
    Date.prototype.N = 'n';
    Date.prototype.H = 'h';
    Date.prototype.D = 'd';
    Date.prototype.W = 'w';
    Date.prototype.Q = 'q';
    Date.prototype.M = 'm';
    Date.prototype.Y = 'y';

    /**
     * 获取一个日期的相对日期
     * @param strInterval   s:秒，n:'分',h:'小时',d:'天',w:'星期',q:'季度',m:'月',y:'年'
     * @param Number      相对的数量
     * @returns {Date}  日期对象
     */
    Date.prototype.dateAdd = function (strInterval, Number) {
        var dtTmp = this;
        switch (strInterval) {
            case Date.prototype.S :return new Date(Date.parse(dtTmp) + (1000 * Number));
            case Date.prototype.N :return new Date(Date.parse(dtTmp) + (60000 * Number));
            case Date.prototype.H :return new Date(Date.parse(dtTmp) + (3600000 * Number));
            case Date.prototype.D :return new Date(Date.parse(dtTmp) + (86400000 * Number));
            case Date.prototype.W :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
            case Date.prototype.Q :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
            case Date.prototype.M :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
            case Date.prototype.Y :return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        }
    }
    /**
     * 判断是否为闰年
     * @returns {boolean}
     */
    Date.prototype.isLeapYear = function() {
        return (0==this.getYear()%4&&((this.getYear()%100!=0)||(this.getYear()%400==0)));
    }
    /**
     * 将字符串解析为日期对象
     * @param dateStr   格式yyyy-MM-dd HH:mm:ss
     * @returns {Date}
     */
    Date.prototype.parse = function(dateStr){
        var dateStr = dateStr.split(' ');
        var dateArr = dateStr[0].split('-');
        var timeArr = dateStr[1].split(':');
        return new Date(dateArr[0],dateArr[1]-1,dateArr[2],timeArr[0],timeArr[1],timeArr[2]);
    }
    //---------------------------------------------------
    // 日期格式化
    // 格式 YYYY/yyyy/YY/yy 表示年份
    // MM/M 月份
    // W/w 星期
    // dd/DD/d/D 日期
    // hh/HH/h/H 时间
    // mm/m 分钟
    // ss/SS/s/S 秒
    //---------------------------------------------------
    Date.prototype.format = function(formatStr) {
        var str = formatStr;
        var Week = ['日','一','二','三','四','五','六'];

        str=str.replace(/yyyy|YYYY/,this.getFullYear());
        str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

        str=str.replace(/MM/,(this.getMonth()+1)>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));
        str=str.replace(/M/g,(this.getMonth()+1));

        str=str.replace(/w|W/g,Week[this.getDay()]);

        str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
        str=str.replace(/d|D/g,this.getDate());

        str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
        str=str.replace(/h|H/g,this.getHours());
        str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
        str=str.replace(/m/g,this.getMinutes());

        str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
        str=str.replace(/s|S/g,this.getSeconds());

        return str;
    }
    
    /**
     * 两个日期做差
     * @param minuend 格式yyyy-MM-dd HH:mm:ss
     * @param subtrahend 格式yyyy-MM-dd HH:mm:ss
     * @returns {Number} 单位：秒
     */
    Date.prototype.subtraction = function(minuend, subtrahend){
        minuend = Date.prototype.parse(minuend);
        subtrahend = Date.prototype.parse(subtrahend);
        return (minuend.getTime() - subtrahend.getTime())/1000;
    }
    
    /**
     * 对数字进行格式化 0转成00格式
     * @param 数字 
     * @returns 格式化的结果
     */
    Date.prototype.formatNumber = function(i){
    	return i<10 ? ("0"+i) : (""+i);
    }
});