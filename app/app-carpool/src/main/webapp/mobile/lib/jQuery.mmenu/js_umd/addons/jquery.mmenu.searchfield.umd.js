(function ( factory ) {
    if ( typeof define === 'function' && define.amd )
    {
        // AMD. Register as an anonymous module.
        define( [ 'jquery' ], factory );
    }
    else if ( typeof exports === 'object' )
    {
        // Node/CommonJS
        factory( require( 'jquery' ) );
    }
    else
    {
        // Browser globals
        factory( jQuery );
    }
}( function ( jQuery ) {


/*	
 * jQuery mmenu sectionIndexer addon
 * mmenu.frebsite.nl
 *
 * Copyright (c) Fred Heusschen
 */
!function(e){var a="mmenu",r="sectionIndexer";e[a].addons[r]={setup:function(){var n=this,s=this.opts[r];this.conf[r],d=e[a].glbl,"boolean"==typeof s&&(s={add:s}),"object"!=typeof s&&(s={}),s=this.opts[r]=e.extend(!0,{},e[a].defaults[r],s),this.bind("init",function(a){if(s.add){switch(s.addTo){case"panels":var r=a;break;default:var r=e(s.addTo,this.$menu).filter("."+i.panel)}r.find("."+i.divider).closest("."+i.panel).addClass(i.hasindexer)}if(!this.$indexer&&this.$menu.children("."+i.hasindexer).length){this.$indexer=e('<div class="'+i.indexer+'" />').prependTo(this.$menu).append('<a href="#a">a</a><a href="#b">b</a><a href="#c">c</a><a href="#d">d</a><a href="#e">e</a><a href="#f">f</a><a href="#g">g</a><a href="#h">h</a><a href="#i">i</a><a href="#j">j</a><a href="#k">k</a><a href="#l">l</a><a href="#m">m</a><a href="#n">n</a><a href="#o">o</a><a href="#p">p</a><a href="#q">q</a><a href="#r">r</a><a href="#s">s</a><a href="#t">t</a><a href="#u">u</a><a href="#v">v</a><a href="#w">w</a><a href="#x">x</a><a href="#y">y</a><a href="#z">z</a><a href="##">#</a>'),this.$indexer.children().on(h.mouseover+"-searchfield "+i.touchmove+"-searchfield",function(){var a=e(this).attr("href").slice(1),r=n.$menu.children("."+i.current),h=r.find("."+i.listview),d=!1,s=r.scrollTop(),t=h.position().top+parseInt(h.css("margin-top"),10)+parseInt(h.css("padding-top"),10)+s;r.scrollTop(0),h.children("."+i.divider).not("."+i.hidden).each(function(){d===!1&&a==e(this).text().slice(0,1).toLowerCase()&&(d=e(this).position().top+t)}),r.scrollTop(d!==!1?d:s)});var d=function(e){n.$menu[(e.hasClass(i.hasindexer)?"add":"remove")+"Class"](i.hasindexer)};this.bind("openPanel",d),d.call(this,this.$menu.children("."+i.current))}})},add:function(){i=e[a]._c,n=e[a]._d,h=e[a]._e,i.add("indexer hasindexer"),h.add("mouseover touchmove")},clickAnchor:function(e){return e.parent().is("."+i.indexer)?!0:void 0}},e[a].defaults[r]={add:!1,addTo:"panels"};var i,n,h,d}(jQuery);
}));