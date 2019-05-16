//大图轮播
function banner(){
    var bn_id = 0;
    var bn_id2= 1;
    var speed33=3000;
    var qhjg = 1;
    var MyMar33;
    $("#banner .d1").hide();
    $("#banner .d1").eq(0).fadeIn("slow");
    if($("#banner .d1").length>1)
    {
        $("#banner_id li").eq(0).addClass("nuw");
        function Marquee33(){
            bn_id2 = bn_id+1;
            if(bn_id2>$("#banner .d1").length-1)
            {
                bn_id2 = 0;
            }
            $("#banner .d1").eq(bn_id).css("z-index","2");
            $("#banner .d1").eq(bn_id2).css("z-index","1");
            $("#banner .d1").eq(bn_id2).show();
            $("#banner .d1").eq(bn_id).fadeOut("slow");
            $("#banner_id li").removeClass("nuw");
            $("#banner_id li").eq(bn_id2).addClass("nuw");
            bn_id=bn_id2;
        };
        MyMar33=setInterval(Marquee33,speed33);
        $("#banner_id li").click(function(){
            var bn_id3 = $("#banner_id li").index(this);
            if(bn_id3!=bn_id&&qhjg==1)
            {
                qhjg = 0;
                $("#banner .d1").eq(bn_id).css("z-index","2");
                $("#banner .d1").eq(bn_id3).css("z-index","1");
                $("#banner .d1").eq(bn_id3).show();
                $("#banner .d1").eq(bn_id).fadeOut("slow",function(){qhjg = 1;});
                $("#banner_id li").removeClass("nuw");
                $("#banner_id li").eq(bn_id3).addClass("nuw");
                bn_id=bn_id3;
            }
        })
        $("#banner_id").hover(
            function(){
                clearInterval(MyMar33);
            }
            ,
            function(){
                MyMar33=setInterval(Marquee33,speed33);
            }
        )
    }
    else
    {
        $("#banner_id").hide();
    }
}
$(function(){
   /* //限时抢购滚动
    $('#demo-01').jCarouselLite({
        btnPrev: '#prev-01',
        btnNext: '#next-01',
        visible:4
    });
    //商城快讯滚动
    var a=new Marquee("MarqueeDiv",2,1,1000,20,20,0,0,0);
*/
  
});

(function(a){
    a.fn.hoverClass=function(b){
        var a=this;
        a.each(function(c){
            a.eq(c).hover(function(){
                $(this).addClass(b)
            },function(){
                $(this).removeClass(b)
            })
        });
        return a
    };
})(jQuery);

/*$(function(){
    $("#pc-nav").hoverClass("current");
});

$(function(){
    $("#pc-nav1").hoverClass("current");
});*/


function b() {
    h = $(window).height(),
        t = $(document).scrollTop(),
            t > h ? $("#moquu_top").show() : $("#moquu_top").hide();
}
$(document).ready(function() {
    b(),
        $("#moquu_top").click(function() {
            $(document).scrollTop(0)
        })
}),
    $(window).scroll(function() {
        b()
    });
