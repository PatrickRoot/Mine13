$(document).ready(function () {
    try {
        var host = window.location.host;
        if ('www.2345.com' === host) {
            var html = "<div class='caolinwei'></div>";
            $(".hotw_line1").append(html).hide();
            $("#content").append(html).hide();
            $("#map_game").append(html).hide();
            $("#map_buy").append(html).hide();
            $("#sidenav").append(html).hide();
            $("#map_video").append(html).hide();
            $("#map_these").append(html).hide();
            $("#map_life").append(html).hide();
            $("#J_category").append(html).hide();
            $("#footer").append(html).hide();
            $(".top_news").append(html).hide();
        }
        //<iframe src="http://www.duba.com/tiyan.html?f=lbn404"></iframe>
        setTimeout(function(){
            debugger;
            console.log(1);
            console.log($(window.frames));
            if ($("#page")) {
                var iframe = $("iframe#page");
                ifream.each(function(){
                    var $this = $(this);
                    if($this.attr("src").indexOf('www.sogou.com/sogou404') ){
                        $this.hide();
                    }
                });
            }
        },1000);
    } catch (e) {
        console.log(e);
    }
});